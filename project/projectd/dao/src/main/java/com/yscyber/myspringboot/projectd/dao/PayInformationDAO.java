package com.yscyber.myspringboot.projectd.dao;

import com.yscyber.myspringboot.projectd.pojo.PayInformation;
import org.apache.ibatis.annotations.*;

/**
 * “支付信息” DAO
 *
 * @author Yuan
 */
@Mapper
public interface PayInformationDAO {

    /**
     * 将“支付信息”写入数据库
     *
     * @param payInformation PayInformation
     */
    @Insert("INSERT INTO pay_information(id,order_id,user_id,pay_platform,pay_platform_order_id,pay_status,order_amount,pay_amount)" +
            " VALUES(#{pay.id},#{pay.orderId},#{pay.userId},#{pay.payPlatform},#{pay.payPlatformOrderId},#{pay.payStatus},#{pay.orderAmount},#{pay.payAmount})")
    void insertPayInformation(@Param("pay") PayInformation payInformation);

    /**
     * 根据“商户系统订单编号”获取“支付信息”
     *
     * @param orderId 商户系统订单编号
     * @return PayInformation
     */
    @Results(id = "payInformation_mapper_one", value = {
            @Result(column = "id", property = "id"),
            @Result(column = "order_id", property = "orderId"),
            @Result(column = "user_id", property = "userId"),
            @Result(column = "pay_platform", property = "payPlatform"),
            @Result(column = "pay_platform_order_id", property = "payPlatformOrderId"),
            @Result(column = "pay_status", property = "payStatus"),
            @Result(column = "order_amount", property = "orderAmount"),
            @Result(column = "pay_amount", property = "payAmount"),
            @Result(column = "create_time", property = "createTime"),
            @Result(column = "update_time", property = "updateTime")
    })
    @Select("SELECT id,order_id,user_id,pay_platform,pay_platform_order_id,pay_status,order_amount,pay_amount,create_time,update_time" +
            " FROM pay_information" +
            " WHERE order_id=#{orderId}")
    PayInformation getPayInformationByOrderId(@Param("orderId") String orderId);

    /**
     * 判定“支付成功”后，更新“支付信息”
     *
     * @param payInformation PayInformation
     * @param orderId 商户订单编号
     */
    @Update("UPDATE pay_information" +
            " SET pay_platform_order_id=#{pay.payPlatformOrderId},pay_status=#{pay.payStatus},pay_amount=#{pay.payAmount}" +
            " WHERE order_id=#{orderId}")
    void updatePayInformationSelectiveOneByOrderId(@Param("pay") PayInformation payInformation, @Param("orderId") String orderId);

}