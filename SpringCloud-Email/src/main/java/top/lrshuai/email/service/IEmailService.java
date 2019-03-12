package top.lrshuai.email.service;

import org.springframework.stereotype.Component;
import top.lrshuai.common.dto.Result;
import top.lrshuai.email.dto.SendEmailDTO;

@Component
public interface IEmailService {
    public Result sendSimpleMail(SendEmailDTO dto) throws Exception;
    public Result sendHtmlMail(SendEmailDTO dto) throws Exception;
    public Result sendTemplate(SendEmailDTO dto) throws Exception;
}
