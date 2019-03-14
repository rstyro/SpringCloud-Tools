package top.lrshuai.feign.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import top.lrshuai.common.dto.Result;
import top.lrshuai.common.dto.sms.SmsDTO;
import top.lrshuai.common.utils.EncryptUtils;
import top.lrshuai.common.utils.Tools;
import top.lrshuai.feign.client.SmsFeignClient;
import top.lrshuai.feign.service.ISmsService;

@Service
public class SmsService implements ISmsService {

    @Autowired
    private SmsFeignClient smsFeignClient;

    @Value("${encrypt.key}")
    private String key;

    @Value("${encrypt.secret}")
    private String secret;

    /**
     * 发送短信
     * @param dto
     * @return
     * @throws Exception
     */
    @Override
    public Result sendSMS(SmsDTO dto) throws Exception {
        dto.setTimestamp(System.currentTimeMillis()+"");
        dto.setType(1);
        dto.setKey(key);
        dto.setSign(EncryptUtils.getSign(Tools.getMap(dto),secret));
        return smsFeignClient.send(dto);
    }
}
