package com.yscyber.myspringboot.projectd.pojo;

import lombok.Data;
import java.util.Date;

/**
 * 支付信息
 *
 * @author Yuan
 */
@Data
public class PayInformation {

    private String id;
    private String orderId;
    private String userId;
    private Integer payPlatform;
    private String payPlatformOrderId;
    private Integer payStatus;
    private Double orderAmount;
    private Double payAmount;
    private Date createTime;
    private Date updateTime;

}