package top.lrshuai.feign.service;

import top.lrshuai.common.dto.Result;
import top.lrshuai.common.dto.sms.SmsDTO;

public interface ISmsService {
    public Result sendSMS(SmsDTO dto) throws Exception;
}
