package top.lrshuai.email.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import top.lrshuai.email.service.IMailService;

import javax.mail.internet.MimeMessage;
import java.io.File;

@Service
public class MailService implements IMailService {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private TemplateEngine templateEngine;

    @Value("${mail.from}")
    private String from;

    /**
     * 发送文本邮件
     * @param to
     * @param subject 标题
     * @param content
     */
    @Override
    public void sendSimpleMail(String to, String subject, String content) throws Exception{
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(content);
        mailSender.send(message);
        logger.info("简单邮件已经发送。");
    }

    /**
     * 发送html邮件
     * @param to
     * @param subject
     * @param content
     */
    @Override
    public void sendHtmlMail(String to, String subject, String content) throws Exception{
        MimeMessage message = mailSender.createMimeMessage();
        //true表示需要创建一个multipart message
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setFrom(from);
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(content, true);

        mailSender.send(message);
        logger.info("html邮件发送成功");
    }


    /**
     * 发送带附件的邮件
     * @param to
     * @param subject
     * @param content
     * @param filePath
     */
    public void sendAttachmentsMail(String to, String subject, String content, String filePath)throws Exception{
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setFrom(from);
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(content, true);

        FileSystemResource file = new FileSystemResource(new File(filePath));
        String fileName = filePath.substring(filePath.lastIndexOf(File.separator));
        helper.addAttachment(fileName, file);
        mailSender.send(message);
        logger.info("带附件的邮件已经发送。");
    }


    /**
     * 发送正文中有静态资源（图片）的邮件
     * @param to
     * @param subject
     * @param content
     * @param rscPath
     * @param rscId
     */
    public void sendInlineResourceMail(String to, String subject, String content, String rscPath, String rscId)throws Exception{
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setFrom(from);
        helper.setTo(to);
        helper.setSubject(subject);
        helper.setText(content, true);
        System.out.println("content="+content);
        System.out.println("rscId="+rscId);
        System.out.println("rscPath="+rscPath);
        FileSystemResource res = new FileSystemResource(new File(rscPath));
        helper.addInline(rscId, res);
        mailSender.send(message);
        logger.info("嵌入静态资源的邮件已经发送。");
    }

    /**
     * 发送模板邮件
     * @param to 发给谁
     * @param code 验证码
     */
    @Override
    public void sendTemplate(String to, String code) throws Exception {
        //是导这个包import org.thymeleaf.context.Context;
        Context context = new Context();
        context.setVariable("username","Rstyro");
        context.setVariable("code",code);
        //获取thymeleaf的html模板
        String emailContent= templateEngine.process("model/code", context);
        this.sendHtmlMail(to,"这是thymeleaf模板邮件",emailContent);
    }
}
