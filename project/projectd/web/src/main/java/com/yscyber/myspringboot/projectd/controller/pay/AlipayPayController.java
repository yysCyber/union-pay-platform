package com.yscyber.myspringboot.projectd.controller.pay;

import com.lly835.bestpay.model.PayResponse;
import com.yscyber.myspringboot.projectd.service.pay.alipay.AlipayPayServiceImpl;
import com.yscyber.myspringboot.projectd.service.pay.alipay.AlipayPayTipConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

/**
 * 支付宝支付控制器
 *
 * @author Yuan
 */
@Controller
@RequestMapping("/alipay")
public class AlipayPayController {

    @Autowired
    private AlipayPayServiceImpl payService;

    private static final String PAY_PAGE_BODY_KEY = "pay_page_body";

    private static final String TIP_SUMUP_KEY = "tip_sumup";
    private static final String TIP_DETAIL_KEY = "tip_detail";
    private static final String TIP_URL = "tip_url";


    /**
     * GET 请求 URL：/union-pay/alipay/pc?id=xxx&name=xxx&amount=xxx
     * POST 请求 URL：/union-pay/alipay/pc
     *
     * 存在一定的风险性，请求的参数直接暴露在 URL 中
     * 不进行相关数据校验，确保传入的数据正确！！！
     *
     * @param orderId 订单编号
     * @param orderName 订单名称
     * @param orderAmount 订单金额
     * @return 返回含支付页面
     */
    @RequestMapping("/pc")
    public ModelAndView pageAlipayPayPc(@RequestParam("id") String orderId,
                                                  @RequestParam("name") String orderName,
                                                  @RequestParam("amount") String orderAmount) {
        ModelAndView modelAndView = new ModelAndView();

        PayResponse payResponse = payService.createPay(orderId, orderName, orderAmount);
        if (payResponse == null) {
            modelAndView.setViewName("alipay/alipay_pay_tip_page");
            modelAndView.addObject(TIP_SUMUP_KEY, AlipayPayTipConstant.TIP_SUCCESS_SUMUP);
            modelAndView.addObject(TIP_DETAIL_KEY, AlipayPayTipConstant.TIP_SUCCESS_DETAIL);
            modelAndView.addObject(TIP_URL, AlipayPayTipConstant.TIP_SUCCESS_URL);
        } else {
            modelAndView.setViewName("alipay/alipay_pay_page");
            modelAndView.addObject(PAY_PAGE_BODY_KEY, payResponse.getBody());
        }
        return modelAndView;
    }

    @GetMapping("/pc/success-tip")
    public ModelAndView pageAlipayPayPcTempSuccess() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("alipay/alipay_pay_tip_page");
        modelAndView.addObject(TIP_SUMUP_KEY, AlipayPayTipConstant.TIP_SUCCESS_SUMUP);
        modelAndView.addObject(TIP_DETAIL_KEY, AlipayPayTipConstant.TIP_SUCCESS_DETAIL);
        modelAndView.addObject(TIP_URL, AlipayPayTipConstant.TIP_SUCCESS_URL);
        return modelAndView;
    }


    /**
     * 支付结果通知处理
     *
     * 微信的“统一支付平台”将以 POST 方式向该方法发出请求，将异步通知传递过来
     * URL：/union-pay/alipay/pc/async-notify
     *
     * @param asyncNotify 异步通知、支付结果通知
     */
    @PostMapping("/pc/async-notify")
    @ResponseBody
    public String asyncNotifyAlipayPayPc(@RequestBody String asyncNotify) {
        System.out.println("alipay async notify：\n" + asyncNotify);
        return payService.asyncNotifyHandle(asyncNotify);
    }

}