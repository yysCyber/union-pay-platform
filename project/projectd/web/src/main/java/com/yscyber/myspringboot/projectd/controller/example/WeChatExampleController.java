package com.yscyber.myspringboot.projectd.controller.example;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 示例
 *
 * @author Yuan
 */
@Controller
@RequestMapping("/wechat")
public class WeChatExampleController {

    @GetMapping("/example")
    public ModelAndView pageWechat() {
        return new ModelAndView("example/wechat");
    }

}