package com.yscyber.myspringboot.projectd.pojo;

/**
 * “支付信息”中所用到的常量
 *
 * @author Yuan
 */
public interface PayInformationConstant {

    /**
     * 支付平台：支付宝
     */
    int PLATFORM_ALIPAY = 1;

    /**
     * 支付平台：微信
     */
    int PLATFORM_WECHAT = 2;

    /**
     * 支付状态：成功支付
     */
    int PAY_STATUS_SUCCESS = 1;

    /**
     * 支付状态：未支付
     */
    int PAY_STATUS_NO_PAY = 0;

}