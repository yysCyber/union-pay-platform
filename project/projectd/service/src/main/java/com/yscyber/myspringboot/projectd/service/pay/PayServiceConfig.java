package com.yscyber.myspringboot.projectd.service.pay;

import com.lly835.bestpay.config.AliPayConfig;
import com.lly835.bestpay.config.WxPayConfig;
import com.lly835.bestpay.service.impl.BestPayServiceImpl;
import com.yscyber.myspringboot.projectd.service.pay.alipay.AlipayPayConstant;
import com.yscyber.myspringboot.projectd.service.pay.wechat.WeChatPayConstant;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * 为了方便后续的使用，将 BestPayService 抽取出来，以后“自动注入”使用（单例）
 *
 * TODO 参数为“示例参数”
 *
 * @author Yuan
 */
@Component
public class PayServiceConfig {

    /**
     * 相当于：<bean id="bestPayService" class="com.lly835.bestpay.service.impl.BestPayServiceImpl"/>
     *
     * @return BestPayServiceImpl
     */
    @Bean
    public BestPayServiceImpl bestPayService() {
        BestPayServiceImpl bestPayService = new BestPayServiceImpl();

        // 微信支付配置
        WxPayConfig wxPayConfig = new WxPayConfig();
        wxPayConfig.setAppId(WeChatPayConstant.EXAMPLE_APP_ID);
        wxPayConfig.setMchId(WeChatPayConstant.EXAMPLE_MERCHANT_ID);
        wxPayConfig.setMchKey(WeChatPayConstant.EXAMPLE_MERCHANT_KEY);
        wxPayConfig.setNotifyUrl(WeChatPayConstant.EXAMPLE_NOTIFY_URL);
        bestPayService.setWxPayConfig(wxPayConfig);

        // 支付宝配置
        AliPayConfig aliPayConfig = new AliPayConfig();
        aliPayConfig.setAppId(AlipayPayConstant.EXAMPLE_APP_ID);
        aliPayConfig.setPrivateKey(AlipayPayConstant.EXAMPLE_MERCHANT_PRIVATE_KEY);
        aliPayConfig.setAliPayPublicKey(AlipayPayConstant.EXAMPLE_ALIPAY_PUBLIC_KEY);
        aliPayConfig.setNotifyUrl(AlipayPayConstant.EXAMPLE_NOTIFY_URL);
        aliPayConfig.setReturnUrl(AlipayPayConstant.EXAMPLE_RETURN_URL);
        bestPayService.setAliPayConfig(aliPayConfig);

        return bestPayService;
    }

}