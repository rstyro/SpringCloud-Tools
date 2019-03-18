package top.lrshuai.ems.service;

import top.lrshuai.common.dto.Result;
import top.lrshuai.ems.dto.EMSQueryDTO;

public interface IEmsService {
    /**
     * 阿里云市场，全球快递物流查询
     * https://market.aliyun.com/products/57126001/cmapi032282.html?spm=5176.2020520132.101.2.25437218iQvGMq#sku=yuncode2628200001
     * @param dto
     * @return
     * @throws Exception
     */
    public Result queryEMS(EMSQueryDTO dto) throws Exception;

    /**
     * 快递鸟查询
     * http://www.kdniao.com/reg
     * @param dto
     * @return
     * @throws Exception
     */
    public Result queryKDNEMS(EMSQueryDTO dto) throws Exception;
}
