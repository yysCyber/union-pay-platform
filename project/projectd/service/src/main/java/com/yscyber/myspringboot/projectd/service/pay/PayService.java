package com.yscyber.myspringboot.projectd.service.pay;

import com.lly835.bestpay.model.PayResponse;
import java.math.BigDecimal;

/**
 * 支付接口
 *
 * @author Yuan
 */
public interface PayService {

    /**
     * 创建/发起 支付
     *
     * @param orderId 订单号（由业务系统生成并传递过来，比如说：由“电商系统”生成传递过来）
     * @param orderName 订单描述
     * @param orderAmount 订单金额（同样由业务系统生成并传递过来，比如说：由“电商系统”生成传递过来）
     *                    CNY：金额要求小数点2位（元、角、分）
     * @return PayResponse
     */
    PayResponse createPay(String orderId, String orderName, BigDecimal orderAmount);

    PayResponse createPay(String orderId, String orderName, String orderAmount);

    PayResponse createPay(String orderId, String orderName, double orderAmount);

    /**
     * 对支付结果通知进行处理
     *
     * @param asyncNotify 异步通知数据
     * @return 返回相关的状态码
     */
    String asyncNotifyHandle(String asyncNotify);

}