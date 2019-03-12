package top.lrshuai.email.service;

import org.springframework.stereotype.Component;

@Component
public interface IMailService {

	/**
	 * 发送普通文本
	 * @param to
	 * @param subject
	 * @param content
	 */
    public void sendSimpleMail(String to, String subject, String content) throws Exception;

    /**
     * 发送 html 代码的邮件
     * @param to
     * @param subject
     * @param content
     */
    public void sendHtmlMail(String to, String subject, String content)throws Exception;

    /**
     * 发送附件的邮件
     * @param to
     * @param subject
     * @param content
     * @param filePath
     */
    public void sendAttachmentsMail(String to, String subject, String content, String filePath)throws Exception;

    /**
     * 发送内嵌的文件
     * @param to
     * @param subject
     * @param content
     * @param rscPath
     * @param rscId
     */
    public void sendInlineResourceMail(String to, String subject, String content, String rscPath, String rscId)throws Exception;

    /**
     * 发送模板验证码
     * @param to 发给谁
     * @param code 验证码
     * @throws Exception
     */
    public void sendTemplate(String to,String code) throws Exception;

}
