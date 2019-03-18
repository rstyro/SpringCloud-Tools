package top.lrshuai.common.dto.sms;

import lombok.Data;

@Data
public class SmsDTO {
    //手机
    private String mobile;

    // 发送短信的类型:1 —- 其他，2 — 手机注册，3 —- 手机绑定，4 —— 找回密码，
    private Integer type;

    //区号
    private String areaCode;

    // 毫秒级别时间戳
    private String timestamp;

    //公钥
    private String key;
    // 加密签名
    private String sign;
}
