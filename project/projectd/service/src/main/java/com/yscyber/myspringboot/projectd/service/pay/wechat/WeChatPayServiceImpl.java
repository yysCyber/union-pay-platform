package com.yscyber.myspringboot.projectd.service.pay.wechat;

import com.lly835.bestpay.enums.BestPayTypeEnum;
import com.lly835.bestpay.model.PayRequest;
import com.lly835.bestpay.model.PayResponse;
import com.lly835.bestpay.service.impl.BestPayServiceImpl;
import com.yscyber.myspringboot.projectd.dao.PayInformationDAO;
import com.yscyber.myspringboot.projectd.pojo.PayInformation;
import com.yscyber.myspringboot.projectd.pojo.PayInformationConstant;
import com.yscyber.myspringboot.projectd.service.pay.PayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.UUID;

/**
 * 微信 “Native 模式二”支付
 *
 * 官方文档：https://pay.weixin.qq.com/wiki/doc/api/native.php?chapter=6_5
 * 开源支付 SDK 文档：https://github.com/Pay-Group/best-pay-sdk/blob/develop/doc/use.md
 *
 * @author Yuan
 */
@Service
public class WeChatPayServiceImpl implements PayService {

    /**
     * BestPayServiceImpl 自动注入，确保单例
     */
    @Autowired
    BestPayServiceImpl bestPayService;

    @Autowired
    private PayInformationDAO payInformationDAO;

    @Override
    public PayResponse createPay(String orderId, String orderName, BigDecimal orderAmount) {

        // TODO 1、将订单信息写入数据库

        // 1、判断该订单号是否已经“成功支付”
        PayInformation payInformation = payInformationDAO.getPayInformationByOrderId(orderId);

        if (payInformation != null) {
            if (payInformation.getPayStatus() == PayInformationConstant.PAY_STATUS_SUCCESS) {
                return null;
            } else {
                // 确保 orderAmount 与数据库中一致
                // TODO 未来可能需要考虑确保 orderName 与数据库一致
                if (orderAmount.compareTo(BigDecimal.valueOf(payInformation.getOrderAmount())) != 0) {
                    orderAmount = BigDecimal.valueOf(payInformation.getOrderAmount());
                }
            }
        } else {
            payInformation = new PayInformation();
            payInformation.setId(createUUID());
            payInformation.setOrderId(orderId);
            payInformation.setOrderAmount(orderAmount.doubleValue());
            payInformation.setPayPlatform(PayInformationConstant.PLATFORM_WECHAT);
            payInformation.setPayStatus(PayInformationConstant.PAY_STATUS_NO_PAY);
            payInformation.setPayAmount(null);
            payInformation.setUserId(null);
            payInformation.setPayPlatformOrderId(null);
            payInformationDAO.insertPayInformation(payInformation);
        }

        // TODO 2、发起支付
        PayRequest payRequest = new PayRequest();
        payRequest.setPayTypeEnum(BestPayTypeEnum.WXPAY_NATIVE);
        payRequest.setOrderId(orderId);
        payRequest.setOrderName(WeChatPayConstant.EXAMPLE_ORDER_NAME_PREFIX + orderName);
        payRequest.setOrderAmount(orderAmount.doubleValue());
        return bestPayService.pay(payRequest);
    }

    @Override
    public PayResponse createPay(String orderId, String orderName, String orderAmount) {
        return createPay(orderId, orderName, new BigDecimal(orderAmount));
    }

    @Override
    public PayResponse createPay(String orderId, String orderName, double orderAmount) {
        return createPay(orderId, orderName, new BigDecimal(String.format("%.2f", orderAmount)));
    }

    @Override
    public String asyncNotifyHandle(String asyncNotify) {
        // TODO 根据官方文档，建议：“商户系统对于支付结果通知的内容一定要做签名验证,并校验返回的订单金额是否与商户侧的订单金额一致”

        // TODO 关于这个异步通知的处理，个人感觉 SDK 是有一定的局限性的，可以考虑根据官方文档中给出的异步通知格式进行解析

        // TODO 1、校验签名
        // 在 com.lly835.bestpay.service.impl.BestPayServiceImpl 的 asyncNotify 方法中提供了一些“签名校验”等，如果失败，将抛出异常
        // asyncNotify 方法返回值为 PayResponse 是将“通知”的 XML 进行了解析，使用者可以很轻易的拿到一些参数
        PayResponse payResponse = bestPayService.asyncNotify(asyncNotify);

        // TODO 2、读取数据库中的订单信息，与 PayResponse 中的参数进行比对校验
        PayInformation payInformationLocal = payInformationDAO.getPayInformationByOrderId(payResponse.getOrderId());
        if (payInformationLocal == null) {
            // 告警
            throw new RuntimeException("\n【支付异常】数据库中未发现相应的订单号！\n异常订单号：" + payResponse.getOrderId() + "\n");
        } else {
            if (BigDecimal.valueOf(payInformationLocal.getOrderAmount()).compareTo(BigDecimal.valueOf(payResponse.getOrderAmount())) != 0) {
                throw new RuntimeException("\n【支付异常】实际支付金额与订单金额不一致！\n异常订单号：" + payResponse.getOrderId() + "\n");
            }
            // TODO 3、根据校验结果，修改数据库中的订单状态以及相关信息
            payInformationLocal.setPayPlatformOrderId(payResponse.getOutTradeNo());
            payInformationLocal.setPayAmount(payResponse.getOrderAmount());
            payInformationLocal.setPayStatus(PayInformationConstant.PAY_STATUS_SUCCESS);
            payInformationDAO.updatePayInformationSelectiveOneByOrderId(payInformationLocal, payResponse.getOrderId());
        }

        // 4、告诉微信，最终已经确认了订单状态（不管是否支付成功！！！），不要再向 notify_url 发送通知
        return WeChatPayConstant.NO_NEED_NOTIFY_FLAG;

    }

    /**
     * 判断订单是否成功支付
     *
     * @param orderId 订单编号
     * @return boolean
     */
    public boolean isPaySuccess(String orderId) {
        PayInformation payInformation = payInformationDAO.getPayInformationByOrderId(orderId);
        if (payInformation == null) {
            return false;
        } else {
            return payInformation.getPayStatus() == PayInformationConstant.PAY_STATUS_SUCCESS;
        }
    }

    private String createUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

}