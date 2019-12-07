-- 数据库 MySQL 5.7 以上

-- 支付系统进行独立的数据库设计

-- 建库语句
CREATE DATABASE my_pay_v1
DEFAULT CHARACTER SET utf8mb4
COLLATE utf8mb4_bin;

-- 建表语句

-- 一些字段需要结合“微信支付”、“支付宝支付”的官方文档、SDK 源码

/*
	表说明：
		可能存在不合理之处，鼓励大家在熟悉整个项目工程后，进行修改
		
		该表仅仅是记录“支付”信息，至于“订单”的一些细节信息不会在该表中体现
*/

/*
	字段解释：
	
		1、order_id、user_id 等一些字段的数据类型要根据实际情况来定；
		
		2、关于像 id、order_id 等使用什么策略，需要考虑（程序将采用 UUID）；
		
		3、user_id 字段在程序中并没有被真正用到，放在这里只是想说“可以根据实际情况对表进行调整”（前提：熟悉整个工程）
		
		4、pay_platform 支付平台
			|__ 1：支付宝
			|__ 2：微信
		
		5、pay_status 支付状态
			|__ 0：未支付
			|__ 1：已成功支付（所有的数据都正确）
			|__ 2：其他，暂不考虑
*/
CREATE TABLE pay_information(

	id CHAR(32) COMMENT 'UUID',
	
	order_id CHAR(32) NOT NULL COMMENT '商家订单编号',
	
	user_id CHAR(32) DEFAULT NULL COMMENT '下订单的用户编号',
	
	pay_platform TINYINT UNSIGNED NOT NULL COMMENT '支付平台',
	
	pay_platform_order_id VARCHAR(50) DEFAULT NULL COMMENT '支付平台订单编号',
	
	pay_status TINYINT UNSIGNED NOT NULL COMMENT '支付状态',
	
	order_amount DECIMAL(20,2) NOT NULL COMMENT '订单金额',
	
	pay_amount DECIMAL(20,2) DEFAULT NULL COMMENT '实际支付金额',
	
	create_time DATETIME DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
	
	update_time DATETIME DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
	
	CONSTRAINT pk_pay_information PRIMARY KEY (id),
	CONSTRAINT uk_order_id UNIQUE KEY (order_id),
	CONSTRAINT uk_pay_platform_order_id UNIQUE KEY (pay_platform_order_id)
	
);