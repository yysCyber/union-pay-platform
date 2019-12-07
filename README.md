# union-pay-platform
> 基于 best-pay-sdk 这一开源的支付 SDK 构建应用于 Java Web 开发的“统一支付平台”，支持微信、支付宝等。  
I want to make a pay platform based on best-pay-sdk which is an open source SDK，The platform will support Alipay,WeChat-Pay,etc.

### 声明（declare）
1、所提供的一切仅仅是一种示例，开发者可以根据实际情况决定是否参考。  
2、本开源项目是个人学习的一个小阶段，如有不妥之处，请不吝赐教。  
3、感谢`best-pay-sdk`简化了“支付”核心代码的编写以及`best-pay-sdk`的开发者“廖师兄”。

### 联系我（contact me）
1、Email：y2161624113@163.com<br/>
> 要求 邮件主题：union-pay-platform

### 目录说明（catalog）
1、project/projectd：工程  
2、SQL：SQL 建库、建表语句脚本  
3、video：演示视频（以“版本号”命名）  
4、document：文档（以“版本号”命名）

### 当前版本（current version）
v1.0-Beta
> 支持“微信”的“Native 模式二”这一支付模式（support WeChat Native 2 Pay）。  
演示视频地址：<>  
文档地址：<>

### 使用前准备工作（prepared work）
1、大致了解微信 Native 支付的机制。  
2、best-pay-sdk，参考 <https://github.com/Pay-Group/best-pay-sdk> 。  
3、申请到使用微信 Native 支付的资质。  
4、内网穿透工具的使用。  
5、使用 jquery.qrcode.js 在前端页面生成二维码图片，参考 <https://github.com/jeromeetienne/jquery-qrcode>  
......  

参考网址：
> 1、best-pay-sdk GitHub：<https://github.com/Pay-Group/best-pay-sdk>  
2、微信 Native 支付相关开发文档：<https://pay.weixin.qq.com/wiki/doc/api/native.php?chapter=6_1>  
<https://pay.weixin.qq.com/wiki/doc/api/native.php?chapter=6_5>  
<https://pay.weixin.qq.com/wiki/doc/api/native.php?chapter=9_1>  
<https://pay.weixin.qq.com/wiki/doc/api/native.php?chapter=9_7&index=8>  
3、内网穿透工具 NATAPP：<https://natapp.cn>  
4、jQuery 生成二维码工具 GitHub：<https://github.com/jeromeetienne/jquery-qrcode>  
......


### 所使用到的技术（technology）
1、语言：Java （要求 jdk 1.8 以上）  
2、框架：Spring Boot/Thymeleaf/MyBatis  
3、数据库：要求 MySQL 5.7 以上  
4、项目管理工具：Maven 3.6.1  
**注意：除“要求”字眼处为强制性的，其他的版本仅供参考；具体一些未提及的版本细节见`pom.xml`**
