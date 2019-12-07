package com.yscyber.myspringboot.projectd.service.pay.wechat;

/**
 * 微信支付“异步通知”（支付结果通知）处理结果枚举
 *
 * @author Yuan
 */
public enum WeChatPayAsyncNotifyHandleEnum {

    SUCCESS(0, WeChatPayTipConstant.TIP_SUCCESS_SUMUP, WeChatPayTipConstant.TIP_SUCCESS_DETAIL, WeChatPayTipConstant.TIP_URL),

    FAIL_NO_FIND_ORDER(1, WeChatPayTipConstant.TIP_FAIL_SUMUP, WeChatPayTipConstant.TIP_FAIL_NO_FIND_ORDER_IN_DB_DETAIL, WeChatPayTipConstant.TIP_FAIL_NO_FIND_ORDER_IN_DB_URL),

    FAIL_AMOUNT_INCONFORMITY(2, "", "", "");

    private int code;
    private String tipSumup;
    private String tipDetail;
    private String tipUrl;

    WeChatPayAsyncNotifyHandleEnum(int code, String tipSumup, String tipDetail, String tipUrl) {
        this.code = code;
        this.tipSumup = tipSumup;
        this.tipDetail = tipDetail;
        this.tipUrl = tipUrl;
    }

    public int getCode() {
        return code;
    }

    public String getTipSumup() {
        return tipSumup;
    }

    public String getTipDetail() {
        return tipDetail;
    }

    public String getTipUrl() {
        return tipUrl;
    }

}