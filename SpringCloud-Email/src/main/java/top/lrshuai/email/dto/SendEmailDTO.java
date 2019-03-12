package top.lrshuai.email.dto;

import lombok.Data;

@Data
public class SendEmailDTO {
    /* 发送给谁*/
    private String toAddress;

    /* 标题*/
    private String title;

    /* 发送内容*/
    private String content;
}
