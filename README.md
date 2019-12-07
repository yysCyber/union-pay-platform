# union-pay-platform
> 基于 best-pay-sdk 这一开源的支付 SDK 构建应用于 Java Web 开发的“统一支付平台”，支持微信、支付宝等。<br/><br/>
> I want to make a pay platform based on best-pay-sdk which is an open source SDK，The platform will support Alipay,WeChat-Pay,etc.


### 当前版本（current version）
v1.0（Beta）
> 支持“微信”的“Native 模式二”这一支付模式（support WeChat Native 2 Pay）。


### 使用前准备工作（prepared work）
1、大致了解微信 Native 支付的机制。<br/>
2、best-pay-sdk，参考 <https://github.com/Pay-Group/best-pay-sdk> 。<br/>
3、申请到使用微信 Native 支付的资质。<br/>
4、内网穿透工具的使用。<br/>
......<br/>

参考网址：
> 1、best-pay-sdk GitHub：<https://github.com/Pay-Group/best-pay-sdk><br/>
> 2、微信 Native 支付相关开发文档：<https://pay.weixin.qq.com/wiki/doc/api/native.php?chapter=6_1><br/>
> <https://pay.weixin.qq.com/wiki/doc/api/native.php?chapter=6_5><br/>
> <https://pay.weixin.qq.com/wiki/doc/api/native.php?chapter=9_1><br/>
> <https://pay.weixin.qq.com/wiki/doc/api/native.php?chapter=9_7&index=8><br/>
> 3、内网穿透工具 NATAPP：<https://natapp.cn><br/>
> 4、`best-pay-sdk`作者“廖师兄”的网课：<https://coding.imooc.com/class/392.html><br/>
> ......


### 所使用到的技术（technology）
1、语言：Java （要求 jdk 1.8 以上）<br/>
2、框架：Spring Boot/Thymeleaf/MyBatis<br/>
3、数据库：要求 MySQL 5.7 以上<br/>
4、项目管理工具：Maven 3.6.1<br/>
**注意：除“要求”字眼处为强制性的，其他的版本仅供参考；具体一些未提及的版本细节见`pom.xml`**

### 怎么使用（how to use）
