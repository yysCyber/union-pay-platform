package com.yscyber.myspringboot.projectd.controller.pay;

import com.alibaba.fastjson.JSONObject;
import com.lly835.bestpay.model.PayResponse;
import com.yscyber.myspringboot.projectd.service.pay.wechat.WeChatPayConstant;
import com.yscyber.myspringboot.projectd.service.pay.wechat.WeChatPayServiceImpl;
import com.yscyber.myspringboot.projectd.service.pay.wechat.WeChatPayTipConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

/**
 * 微信支付控制器
 *
 * @author Yuan
 */
@Controller
@RequestMapping("/wechat")
public class WeChatPayController {

    @Autowired
    private WeChatPayServiceImpl payService;

    private static final String ORDER_QR_CODE_URL_KEY = "order_qr_code_url";
    private static final String ORDER_ID_KEY = "order_id";
    private static final String ORDER_AMOUNT_KEY = "order_amount";
    private static final String ORDER_NAME_KEY = "order_name";
    private static final String ORDER_PAYEE_KEY = "order_payee";

    /**
     * ModelAndView 中“总的提示”的 key
     */
    private static final String TIP_SUMUP_KEY = "tip_sumup";

    /**
     * ModelAndView 中“详细说明”的 key
     */
    private static final String TIP_DETAIL_KEY = "tip_detail";

    /**
     * 这个 URL 是离开 支付系统
     */
    private static final String TIP_URL = "tip_url";

    /**
     * GET 请求 URL：/union-pay/wechat/native-second?id=xxx&name=xxx&amount=xxx
     * POST 请求 URL：/union-pay/wechat/native-second
     *
     * GET 存在一定的风险性，请求的参数直接暴露在 URL 中；建议使用 POST 请求，以表单的形式提交过来
     * 不进行相关数据校验，确保传入的数据正确！！！
     *
     * @param orderId 订单编号
     * @param orderName 订单名称
     * @param orderAmount 订单金额
     * @return 返回含支付二维码的支付页面或支付成功提示页面
     */
    @RequestMapping("/native-second")
    public ModelAndView pageWeChatPayNativeSecond(@RequestParam("id") String orderId,
                                                  @RequestParam("name") String orderName,
                                                  @RequestParam("amount") String orderAmount) {
        ModelAndView modelAndView = new ModelAndView();
        PayResponse payResponse = payService.createPay(orderId, orderName, orderAmount);
        // 考虑“重复支付”的情况
        if (payResponse == null) {
            // TODO 跳转至“支付成功”提示页面
            modelAndView.setViewName("wechat/wechat_pay_tip_page");
            modelAndView.addObject(TIP_SUMUP_KEY, WeChatPayTipConstant.TIP_SUCCESS_SUMUP);
            modelAndView.addObject(TIP_DETAIL_KEY, WeChatPayTipConstant.TIP_SUCCESS_DETAIL);
            modelAndView.addObject(TIP_URL, WeChatPayTipConstant.TIP_SUCCESS_URL);
        } else {
            // TODO 跳转至“支付”页面
            modelAndView.setViewName("wechat/wechat_pay_page");
            modelAndView.addObject(ORDER_QR_CODE_URL_KEY, payResponse.getCodeUrl());
            modelAndView.addObject(ORDER_ID_KEY, orderId);
            modelAndView.addObject(ORDER_AMOUNT_KEY, orderAmount);
            modelAndView.addObject(ORDER_NAME_KEY, orderName);
            modelAndView.addObject(ORDER_PAYEE_KEY, WeChatPayConstant.EXAMPLE_ORDER_PAYEE);
        }
        return modelAndView;
    }

    /**
     * 支付结果通知处理
     *
     * 微信的“统一支付平台”将以 POST 方式向该方法发出请求，将异步通知传递过来
     * URL：/union-pay/wechat/native-second/async-notify
     *
     * @param asyncNotify 异步通知、支付结果通知
     */
    @PostMapping("/native-second/async-notify")
    @ResponseBody
    public String asyncNotifyWeChatPayNativeSecond(@RequestBody String asyncNotify) {
        return payService.asyncNotifyHandle(asyncNotify);
    }


    @GetMapping("/native-second/success-tip")
    public ModelAndView pageWeChatPayNativeSecondPaySuccess() {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("wechat/wechat_pay_tip_page");
        modelAndView.addObject(TIP_SUMUP_KEY, WeChatPayTipConstant.TIP_SUCCESS_SUMUP);
        modelAndView.addObject(TIP_DETAIL_KEY, WeChatPayTipConstant.TIP_SUCCESS_DETAIL);
        modelAndView.addObject(TIP_URL, WeChatPayTipConstant.TIP_SUCCESS_URL);
        return modelAndView;
    }

    /**
     * 前端轮询，给前端“是否成功支付”的标志
     *
     * @param orderId 订单编号
     * @return JSON
     */
    @GetMapping("/native-second/is-success")
    @ResponseBody
    public String jsonWeChatPayNativeSecondIsPaySuccess(@RequestParam("orderId") String orderId) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("flag", payService.isPaySuccess(orderId));
        return jsonObject.toString();
    }

}