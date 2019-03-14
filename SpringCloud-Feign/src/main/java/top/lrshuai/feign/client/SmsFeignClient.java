package top.lrshuai.feign.client;

import feign.QueryMap;
import feign.RequestLine;
import org.springframework.cloud.openfeign.FeignClient;
import top.lrshuai.common.dto.Result;
import top.lrshuai.common.dto.sms.SmsDTO;
import top.lrshuai.feign.config.AuthFeignConfig;

@FeignClient(name = "sms-service",configuration = {AuthFeignConfig.class})
public interface SmsFeignClient {

    /**
     * https://github.com/OpenFeign/feign  可以看例子
     * 发送短信
     * @param dto
     * @return
     */
    @RequestLine("POST /sms/send")
    public Result send(@QueryMap SmsDTO dto);
}
