package top.lrshuai.email.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.lrshuai.common.dto.Result;
import top.lrshuai.email.dto.SendEmailDTO;
import top.lrshuai.email.service.IEmailService;

@RestController
@RequestMapping("/mail")
public class MailController {

    @Autowired
    private IEmailService emailService;

    /**
     * 发送普通的邮件
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "发送普通的邮件")
    @PostMapping("/sendSimpleMail")
    public Result sendSimpleMail(SendEmailDTO dto) throws Exception {
        return emailService.sendSimpleMail(dto);
    }

    /**
     * 发送html 邮件
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "发送html 邮件")
    @PostMapping("/sendHtmlMail")
    public Result sendHtmlMail(SendEmailDTO dto) throws Exception {
        return emailService.sendHtmlMail(dto);
    }

    /**
     * 发送sendTemplate 邮件
     * @return
     * @throws Exception
     */
    @ApiOperation(value = "发送sendTemplate 邮件")
    @PostMapping("/sendTemplate")
    public Result sendTemplate(SendEmailDTO dto) throws Exception {
        return emailService.sendTemplate(dto);
    }

}
