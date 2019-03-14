package top.lrshuai.feign.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import top.lrshuai.common.dto.Result;
import top.lrshuai.common.dto.sms.SmsDTO;
import top.lrshuai.feign.client.SmsFeignClient;
import top.lrshuai.feign.service.ISmsService;

@RestController
public class FeignController {

    @Autowired
    private ISmsService smsService;
    /**
     * 发送短信
     * @param dto
     * @return
     */
    @PostMapping("/sendSMS")
    public Result sendSMS(SmsDTO dto) throws Exception {
        return smsService.sendSMS(dto);
    }
}
