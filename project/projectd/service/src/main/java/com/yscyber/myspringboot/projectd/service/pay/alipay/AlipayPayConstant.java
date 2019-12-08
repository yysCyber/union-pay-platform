package com.yscyber.myspringboot.projectd.service.pay.alipay;

/**
 * 支付宝支付所要用到的常量
 *
 * EXAMPLE 作为前缀的为“示例常量”
 *
 * @author Yuan
 */
public interface AlipayPayConstant {

    /**
     * 示例用，应用 ID
     */
    String EXAMPLE_APP_ID = "";

    /**
     * 示例用，商户私钥
     *
     * 发起支付时由商户私钥进行签名
     */
    String EXAMPLE_MERCHANT_PRIVATE_KEY = "";

    /**
     * 示例用，支付宝公钥
     *
     * 接收异步通知（支付结果通知）时，由支付宝公钥进行验签
     */
    String EXAMPLE_ALIPAY_PUBLIC_KEY = ""

    /**
     * 示例用，订单名称前缀
     */
    String EXAMPLE_ORDER_NAME_PREFIX = "";

    /**
     * 示例用，接收来自支付宝支付通知（含支付最终结果等等）的 URL
     *
     * 最终的支付结果，需要以来自支付宝支的支付通知为准！！！
     * 要求：外网必须能够访问到
     */
    String EXAMPLE_NOTIFY_URL = "http://xxx.xxx.xxx/union-pay/alipay/pc/async-notify";

    /**
     * 示例用，支付成功后将要跳转的 URL
     */
    String EXAMPLE_RETURN_URL = "http://xxx.xxx.xxx/union-pay/alipay/pc/success-tip";

    /**
     * 告诉支付宝，不要再发送异步通知了
     * 来自官方文档：https://docs.open.alipay.com/270/105902
     */
    String NO_NEED_NOTIFY_FLAG = "success";

}