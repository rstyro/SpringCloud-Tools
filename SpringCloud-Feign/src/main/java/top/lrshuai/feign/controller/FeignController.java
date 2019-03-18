package top.lrshuai.feign.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import top.lrshuai.common.dto.Result;
import top.lrshuai.common.dto.ems.EMSQueryDTO;
import top.lrshuai.common.dto.sms.SmsDTO;
import top.lrshuai.feign.client.SmsFeignClient;
import top.lrshuai.feign.service.IEmsService;
import top.lrshuai.feign.service.ISmsService;

@Api(value = "feign controller",tags = {"feign 调用其他服务"})
@RestController
public class FeignController {

    @Autowired
    private ISmsService smsService;

    @Autowired
    private IEmsService emsService;
    /**
     * 发送短信
     * @param dto
     * @return
     */
    @PostMapping("/sendSMS")
    @ApiOperation("发送短信")
    public Result sendSMS(SmsDTO dto) throws Exception {
        return smsService.sendSMS(dto);
    }

    @PostMapping("/kdniaoQuery")
    @ApiOperation("快递鸟查询物流")
    public Result kdniaoQuery(EMSQueryDTO dto) throws Exception {
        return emsService.kdniaoQuery(dto);
    }

    @PostMapping("/markQuery")
    @ApiOperation("阿里云市场查询物流")
    public Result markQuery(EMSQueryDTO dto) throws Exception {
        return emsService.markQuery(dto);
    }

}
