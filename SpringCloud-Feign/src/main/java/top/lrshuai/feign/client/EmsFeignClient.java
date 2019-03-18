package top.lrshuai.feign.client;

import feign.QueryMap;
import feign.RequestLine;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import top.lrshuai.common.dto.Result;
import top.lrshuai.common.dto.ems.EMSQueryDTO;
import top.lrshuai.common.dto.sms.SmsDTO;
import top.lrshuai.feign.config.AuthFeignConfig;

@FeignClient(name = "ems-service",configuration = {AuthFeignConfig.class})
@Component
public interface EmsFeignClient {

    /**
     * https://github.com/OpenFeign/feign  可以看例子
     * 查询物流
     * @param dto
     * @return
     */
    @RequestLine("GET /ems/markQuery")
    public Result markQuery(@QueryMap EMSQueryDTO dto);


    @RequestLine("GET /ems/KdnQuery")
    public Result KdnQuery(@QueryMap EMSQueryDTO dto);
}
