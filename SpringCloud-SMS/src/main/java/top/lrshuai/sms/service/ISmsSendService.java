package top.lrshuai.sms.service;

import top.lrshuai.common.dto.Result;
import top.lrshuai.common.dto.sms.SmsDTO;

public interface ISmsSendService {
    public Result sendSms(SmsDTO dto) throws  Exception;
}
