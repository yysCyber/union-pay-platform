package com.yscyber.myspringboot.projectd.service.pay.alipay;

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
 * 支付宝“电脑网站支付”支付
 *
 * 官方文档：https://docs.open.alipay.com/270
 * 开源支付 SDK 文档：https://github.com/Pay-Group/best-pay-sdk/blob/develop/doc/use.md
 *
 * @author Yuan
 */
@Service
public class AlipayPayServiceImpl implements PayService {

    @Autowired
    private BestPayServiceImpl bestPayService;

    @Autowired
    private PayInformationDAO payInformationDAO;


    @Override
    public PayResponse createPay(String orderId, String orderName, BigDecimal orderAmount) {

        PayInformation payInformation = payInformationDAO.getPayInformationByOrderId(orderId);
        if (payInformation != null) {
            if (payInformation.getPayStatus() == PayInformationConstant.PAY_STATUS_SUCCESS) {
                return null;
            } else {
                if (orderAmount.compareTo(BigDecimal.valueOf(payInformation.getOrderAmount())) != 0) {
                    orderAmount = BigDecimal.valueOf(payInformation.getOrderAmount());
                }
            }
        } else {
            // TODO 将订单信息写入数据库
            payInformation = new PayInformation();
            payInformation.setId(createUUID());
            payInformation.setOrderId(orderId);
            payInformation.setOrderAmount(orderAmount.doubleValue());
            payInformation.setPayPlatform(PayInformationConstant.PLATFORM_ALIPAY);
            payInformation.setPayStatus(PayInformationConstant.PAY_STATUS_NO_PAY);
            payInformation.setPayAmount(null);
            payInformation.setUserId(null);
            payInformation.setPayPlatformOrderId(null);
            payInformationDAO.insertPayInformation(payInformation);
        }

        // TODO 发起支付
        PayRequest payRequest = new PayRequest();
        payRequest.setPayTypeEnum(BestPayTypeEnum.ALIPAY_PC);
        payRequest.setOrderId(orderId);
        payRequest.setOrderName(AlipayPayConstant.EXAMPLE_ORDER_NAME_PREFIX + orderName);
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
        // TODO 根据官方文档，支付宝建议：“1、验签；2、商户需要验证该通知数据中的 out_trade_no 是否为商户系统中创建的订单号；3、判断 total_amount 是否确实为该订单的实际金额（即商户订单创建时的金额）；4、校验通知中的 seller_id（或者seller_email) 是否为该笔交易对应的操作方（一个商户可能有多个seller_id/seller_email）；5、验证接口调用方的 app_id”

        // TODO 关于这个异步通知的处理，个人感觉 SDK 是有局限的，可以考虑根据官方文档中给出的异步通知格式进行解析

        // TODO 实际上最难的是“验签”

        // 1、校验签名
        // 在 com.lly835.bestpay.service.impl.BestPayServiceImpl 的 asyncNotify 方法中提供了一些“签名校验”等，如果失败，将抛出异常
        // asyncNotify 方法返回值为 PayResponse 是将“通知”进行了解析，使用者可以很轻易的拿到一些参数
        PayResponse payResponse = bestPayService.asyncNotify(asyncNotify);

        // TODO 2、读取数据库中的订单信息，与 PayResponse 中的参数进行比对校验
        PayInformation payInformationLocal = payInformationDAO.getPayInformationByOrderId(payResponse.getOrderId());
        if (payInformationLocal == null) {
            throw new RuntimeException("\n【支付异常】数据库中未发现相应的订单号！\n异常订单号：" + payResponse.getOrderId() + "\n");
        } else {
            if (BigDecimal.valueOf(payInformationLocal.getOrderAmount()).compareTo(BigDecimal.valueOf(payResponse.getOrderAmount())) != 0) {
                throw new RuntimeException("\n【支付异常】实际支付金额与订单金额不一致！\n异常订单号：" + payResponse.getOrderId() + "\n");
            }

            // TODO 3、根据校验结果，修改数据库中的订单状态
            payInformationLocal.setPayPlatformOrderId(payResponse.getOutTradeNo());
            payInformationLocal.setPayStatus(PayInformationConstant.PAY_STATUS_SUCCESS);
            payInformationLocal.setPayAmount(payResponse.getOrderAmount());
            payInformationDAO.updatePayInformationSelectiveOneByOrderId(payInformationLocal, payResponse.getOrderId());
        }

        // TODO 在此处，该“统一支付平台”可以考虑向对应的“商户系统”发送相关的消息，例如：“订单支付完成，商户系统修改对应的订单状态”

        // TODO 4、告诉支付宝，最终已经确认了订单状态（不管是否支付成功！！！），不要再向 notify_url 发送通知
        return AlipayPayConstant.NO_NEED_NOTIFY_FLAG;
    }

    /**
     * 生成一个32位不带“-”的 UUID
     *
     * @return String
     */
    private String createUUID() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

}