package top.lrshuai.feign.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.lrshuai.common.dto.Result;
import top.lrshuai.common.dto.ems.EMSQueryDTO;
import top.lrshuai.feign.client.EmsFeignClient;
import top.lrshuai.feign.service.IEmsService;

@Service
public class EmsService implements IEmsService {

    @Autowired
    private EmsFeignClient emsFeignClient;

    @Override
    public Result markQuery(EMSQueryDTO dto) throws Exception {
        return emsFeignClient.markQuery(dto);
    }

    @Override
    public Result kdniaoQuery(EMSQueryDTO dto) throws Exception {
        return emsFeignClient.KdnQuery(dto);
    }
}
