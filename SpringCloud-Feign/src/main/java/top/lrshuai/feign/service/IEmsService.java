package top.lrshuai.feign.service;

import top.lrshuai.common.dto.Result;
import top.lrshuai.common.dto.ems.EMSQueryDTO;

public interface IEmsService {
    public Result markQuery(EMSQueryDTO dto) throws Exception;
    public Result kdniaoQuery(EMSQueryDTO dto) throws Exception;
}
