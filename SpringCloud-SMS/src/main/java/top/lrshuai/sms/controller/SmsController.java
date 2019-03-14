package top.lrshuai.sms.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.lrshuai.common.dto.Result;
import top.lrshuai.common.dto.sms.SmsDTO;
import top.lrshuai.sms.service.ISmsSendService;

@RestController
@RequestMapping("/sms")
public class SmsController {
    @Autowired
    private ISmsSendService smsSendService;

    /**
     * 发送短信
     * @param dto
     * @return
     * @throws Exception
     */
    @PostMapping("send")
    public Result send(SmsDTO dto) throws Exception {
        return smsSendService.sendSms(dto);
    }
}
