package com.yscyber.myspringboot.projectd.service.pay.wechat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import java.math.BigDecimal;

@SpringBootTest
@RunWith(SpringRunner.class)
public class WeChatPayServiceImplTest {

    @Autowired
    WeChatPayServiceImpl weChatPayService;

    @Test
    public void demo1() {
        weChatPayService.createPay("0001", "测试0001", new BigDecimal("0.01"));
    }

}