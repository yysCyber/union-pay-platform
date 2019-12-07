package com.yscyber.myspringboot.projectd.service.pay.wechat;

/**
 * 微信支付提示页面上要显示的信息汇总
 *
 * @author Yuan
 */
public interface WeChatPayTipConstant {

    /**
     * “支付成功”提示页面上的“总提示”
     */
    String TIP_SUCCESS_SUMUP = "支付成功";

    /**
     * “支付成功”提示页面上的“细节提示”
     */
    String TIP_SUCCESS_DETAIL = "请勿重复支付！如有疑问，请及时联系管理员！";

    /**
     * “支付成功”提示页面上“离开支付平台”链接
     */
    String TIP_URL = "https://www.baidu.com";

    /**
     * “支付失败”提示页面上的“总提示”
     */
    String TIP_FAIL_SUMUP = "支付失败";

    /**
     * “支付失败”提示页面上的“细节提示”
     *
     * 原因：数据库中没有发现相应的订单号
     */
    String TIP_FAIL_NO_FIND_ORDER_IN_DB_DETAIL = "系统中未发现相应的订单号！如确认已进行了支付，请联系管理员进行紧急处理！";

    /**
     * “支付失败”提示页面上“离开支付平台”链接
     *
     * 原因：数据库中没有发现相应的订单号
     */
    String TIP_FAIL_NO_FIND_ORDER_IN_DB_URL = "https://www.baidu.com";
}