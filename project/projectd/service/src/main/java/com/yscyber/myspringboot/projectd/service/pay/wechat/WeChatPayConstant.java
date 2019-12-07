package com.yscyber.myspringboot.projectd.service.pay.wechat;

/**
 * 微信支付中所要用到的常量
 *
 * EXAMPLE 作为前缀的为“示例所用的常量”
 *
 * @author Yuan
 */
public interface WeChatPayConstant {

    /**
     * 示例用，应用 ID
     */
    String EXAMPLE_APP_ID = "wxd898fcb01713c658";

    /**
     * 示例用，商户 ID
     */
    String EXAMPLE_MERCHANT_ID = "1483469312";

    /**
     * 示例用，商户密钥
     */
    String EXAMPLE_MERCHANT_KEY = "098F6BCD4621D373CADE4E832627B4F6";

    /**
     * 示例用，接收来自微信支付平台的支付通知（含支付最终结果等等）的 URL
     *
     * 微信最终的支付结果，需要以来自微信支付平台的支付通知为准！！！
     * 要求：外网必须能够访问到
     */
    String EXAMPLE_NOTIFY_URL = "http://yscyber.natapp1.cc/union-pay/wechat/native-second/async-notify";

    /**
     * 示例用，订单名称前缀
     */
    String EXAMPLE_ORDER_NAME_PREFIX = "5328260-";

    /**
     * 示例用，收款方名称
     */
    String EXAMPLE_ORDER_PAYEE = "杭州以北科技科技有限公司";

    /**
     * 告诉微信，不要再发送异步通知了
     * 来自官方文档：https://pay.weixin.qq.com/wiki/doc/api/native.php?chapter=9_7&index=8
     */
    String NO_NEED_NOTIFY_FLAG = "<xml>\n" +
            " <return_code><![CDATA[SUCCESS]]></return_code>\n" +
            " <return_msg><![CDATA[OK]]></return_msg>\n" +
            "</xml>";

}