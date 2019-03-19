[![jdkversions](https://img.shields.io/badge/build-passing-success.svg)]()
[![jdkversions](https://img.shields.io/badge/Java-1.8%2B-green.svg)]()
[![SpringClound](https://img.shields.io/badge/SpringClound-Greenwich-success.svg)]()
[![SpringBoot](https://img.shields.io/badge/SpringBoot-V2.1.0-success.svg)]()
[![jdkversions](https://img.shields.io/badge/Swagger_UI-v2.7.0-success.svg)]()
[![eureka](https://img.shields.io/badge/Eureka-green.svg)]()
[![feign](https://img.shields.io/badge/Feign-green.svg)]()
[![MIT](https://img.shields.io/badge/license-MIT-ff69b4.svg)]()

# SpringCloud-Tools
打算弄一个 微服务版本 工具集合

## 已实现的功能

### 发邮件
普通发邮件，带附件发邮件，自定义模板发邮件...

### 发短信
原本想找免费的，但是呢找了好久，都不靠谱，还是选择收费的吧

### 图片上传
上传到自己的服务器，上传到本地，上传到阿里OSS，上传到AWS S3

### 快递查询
一、快递鸟 即时查询（主要是只有这个是免费的）
二、阿里云市场的 国际版 物流查询（这个支持多语言返回）

### 慢慢更新....

## 用到的技能
感觉也没啥技术含量，就是 `ribbon` 和 `feign` 调用服务的一些例子，全局异常捕获啥的。
每个模块都是用 `Swagger-ui` 进行测试


## 快速开始

先启动 `SpringCloud-Eureka`、然后再启动其他服务,然后打开Swagger-ui 接口页面即可