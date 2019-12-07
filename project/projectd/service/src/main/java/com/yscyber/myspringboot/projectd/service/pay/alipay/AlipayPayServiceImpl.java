package com.yscyber.myspringboot.projectd.service.pay.alipay;

import com.lly835.bestpay.enums.BestPayTypeEnum;
import com.lly835.bestpay.model.PayRequest;
import com.lly835.bestpay.model.PayResponse;
import com.lly835.bestpay.service.impl.BestPayServiceImpl;
import com.yscyber.myspringboot.projectd.service.pay.PayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;

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
    BestPayServiceImpl bestPayService;

    @Override
    public PayResponse createPay(String orderId, String orderName, BigDecimal orderAmount) {
        PayRequest payRequest = new PayRequest();
        payRequest.setPayTypeEnum(BestPayTypeEnum.ALIPAY_PC);
        payRequest.setOrderId(orderId);
        payRequest.setOrderName(AlipayPayConstant.EXAMPLE_ORDER_NAME_PREFIX + orderName);
        payRequest.setOrderAmount(orderAmount.doubleValue());

        // TODO 将订单信息写入数据库

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
        System.out.println("async:\n" + asyncNotify);
        // TODO 根据官方文档，支付宝建议：“1、验签；2、商户需要验证该通知数据中的 out_trade_no 是否为商户系统中创建的订单号；3、判断 total_amount 是否确实为该订单的实际金额（即商户订单创建时的金额）；4、校验通知中的 seller_id（或者seller_email) 是否为该笔交易对应的操作方（一个商户可能有多个seller_id/seller_email）；5、验证接口调用方的 app_id”

        // TODO 关于这个异步通知的处理，个人感觉 SDK 是有局限的，可以考虑根据官方文档中给出的异步通知格式进行解析

        // TODO 实际上最难的是“验签”

        // 1、校验签名
        // 在 com.lly835.bestpay.service.impl.BestPayServiceImpl 的 asyncNotify 方法中提供了一些“签名校验”等，如果失败，将抛出异常
        // asyncNotify 方法返回值为 PayResponse 是将“通知”进行了解析，使用者可以很轻易的拿到一些参数
        PayResponse payResponse = bestPayService.asyncNotify(asyncNotify);

        // TODO 2、读取数据库中的订单信息，与 PayResponse 中的参数进行比对校验


        // TODO 3、根据校验结果，修改数据库中的订单状态

        // 告诉支付宝，最终已经确认了订单状态（不管是否支付成功！！！），不要再向 notify_url 发送通知
        return AlipayPayConstant.NO_NEED_NOTIFY_FLAG;
    }

}