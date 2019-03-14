package top.lrshuai.sms.service.impl;

import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import top.lrshuai.common.dto.ApiException;
import top.lrshuai.common.dto.ApiResultEnum;
import top.lrshuai.common.dto.Result;
import top.lrshuai.common.dto.sms.SmsDTO;
import top.lrshuai.common.utils.EncryptUtils;
import top.lrshuai.common.utils.Tools;
import top.lrshuai.sms.entity.ChuangLanSmsUtil;
import top.lrshuai.sms.entity.SmsModel;
import top.lrshuai.sms.entity.SmsSendRequest;
import top.lrshuai.sms.entity.SmsSendResponse;
import top.lrshuai.sms.service.ISmsSendService;

import java.util.TreeMap;

@Service
@Slf4j
public class SmsSendService implements ISmsSendService {

    @Value("${sms.account}")
    private String smsAccount;
    @Value("${sms.password}")
    private String smsPassword;
    @Value("${sms.postUrl}")
    private String smsPostUrl;

    @Value("${sms.sign}")
    private String sign;

    @Value("${encrypt.secret}")
    private String secret;
    
    @Override
    public Result sendSms(SmsDTO dto) throws Exception {
        //校验是否是自己人
        checkSign(dto);
        String code = Tools.random(6);
        log.info("==============手机验证码为：{}",code);
        String msg =String.format(SmsModel.SMS_MODEL,sign,code);
        if(StringUtils.isEmpty(dto.getAreaCode())){
            dto.setAreaCode("86");
        }
        SmsSendRequest smsSingleRequest = new SmsSendRequest(smsAccount, smsPassword, msg,dto.getAreaCode()+dto.getMobile(),"true");
        String requestJson = JSON.toJSONString(smsSingleRequest);
        String response = ChuangLanSmsUtil.sendSmsByPost(smsPostUrl, requestJson);
        SmsSendResponse smsSingleResponse = JSON.parseObject(response, SmsSendResponse.class);
        if(!"0".equals(smsSingleResponse.getCode())){
            log.info("response  toString is :" + smsSingleResponse);
            throw  new ApiException(ApiResultEnum.FAILED);
        }
        return Result.ok();
    }

    /**
     * 校验签名是否匹配
     * @param dto
     * @throws Exception
     */
    public void checkSign(SmsDTO dto) throws  Exception{
        String sign = EncryptUtils.getSign(Tools.getMap(dto),secret);
        if(!dto.getSign().equals(sign)){
            throw new ApiException(ApiResultEnum.AUTH_SIGN_NOT_MATCH);
        }
    }
}
