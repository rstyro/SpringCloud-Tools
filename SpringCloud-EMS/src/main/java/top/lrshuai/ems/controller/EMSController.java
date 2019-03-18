package top.lrshuai.ems.controller;

import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.lrshuai.common.dto.Result;
import top.lrshuai.ems.dto.EMSQueryDTO;
import top.lrshuai.ems.service.IEmsService;

@RestController
@RequestMapping("/ems")
public class EMSController {

    @Autowired
    private IEmsService emsService;

    /**
     * 查询物流信息
     * @param dto
     * @return
     * @throws Exception
     */
    @GetMapping("/markQuery")
    @ApiOperation("阿里云市场api查询物流信息")
    public Result query(EMSQueryDTO dto) throws Exception{
        return emsService.queryEMS(dto);
    }

    /**
     * 查询物流信息
     * @param dto
     * @return
     * @throws Exception
     */
    @GetMapping("/KdnQuery")
    @ApiOperation("快递鸟api查询物流信息")
    public Result queryKDNEMS(EMSQueryDTO dto) throws Exception{
        return emsService.queryKDNEMS(dto);
    }
}
