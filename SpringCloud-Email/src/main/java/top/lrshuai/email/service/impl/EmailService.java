package top.lrshuai.email.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import top.lrshuai.common.dto.ApiException;
import top.lrshuai.common.dto.ApiResultEnum;
import top.lrshuai.common.dto.Result;
import top.lrshuai.email.dto.SendEmailDTO;
import top.lrshuai.email.service.IEmailService;
import top.lrshuai.email.service.IMailService;

import java.util.UUID;

@Service
public class EmailService implements IEmailService {

    @Autowired
    private IMailService mailService;

    /**
     * 发送普通的邮件
     * @param dto
     * @return
     * @throws Exception
     */
    @Override
    public Result sendSimpleMail(SendEmailDTO dto) throws Exception {
        check(dto.getToAddress());
        check(dto.getContent());
        mailService.sendSimpleMail(dto.getToAddress(),dto.getTitle(),dto.getContent());
        return Result.ok();
    }

    /**
     * 发送 html 格式的文件
     * @param dto
     * @return
     * @throws Exception
     */
    @Override
    public Result sendHtmlMail(SendEmailDTO dto) throws Exception {
        check(dto.getToAddress());
        check(dto.getContent());
        mailService.sendHtmlMail(dto.getToAddress(),dto.getTitle(),dto.getContent());
        return Result.ok();
    }

    @Override
    public Result sendTemplate(SendEmailDTO dto) throws Exception {
        System.out.println("dto="+dto);
        check(dto.getToAddress());
        mailService.sendTemplate(dto.getToAddress(), UUID.randomUUID().toString().replace("-",""));
        return Result.ok();
    }

    /**
     * 校验参数是否为空
     * @param keyword
     */
    public void check(String keyword){
        if(StringUtils.isEmpty(keyword)){
            throw new ApiException(ApiResultEnum.PARAMETER_NULL);
        }
    }
}
