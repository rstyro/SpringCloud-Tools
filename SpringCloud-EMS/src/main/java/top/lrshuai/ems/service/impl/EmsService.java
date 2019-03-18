package top.lrshuai.ems.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.HttpResponse;
import org.apache.http.util.EntityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;
import top.lrshuai.common.dto.ApiException;
import top.lrshuai.common.dto.ApiResultEnum;
import top.lrshuai.common.dto.Result;
import top.lrshuai.common.utils.EmsUtils;
import top.lrshuai.ems.dto.EMSQueryDTO;
import top.lrshuai.ems.entity.EmsApi;
import top.lrshuai.ems.service.IEmsService;
import top.lrshuai.ems.utils.HttpUtils;
import top.lrshuai.ems.utils.KdniaoTrackQueryAPI;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Slf4j
public class EmsService implements IEmsService {

    @Autowired
    private KdniaoTrackQueryAPI kdniaoTrackQueryAPI;

    @Autowired
    private RestTemplate restTemplate;

    @Value("${ems.market.appCode}")
    private String appCode;

    @Value("${ems.market.host}")
    private String host;

    /**
     * 阿里云市场 的 物流API
     * @param dto
     * @return
     * @throws Exception
     */
    @Override
    public Result queryEMS(EMSQueryDTO dto) throws Exception {
        if(StringUtils.isEmpty(dto.getNo())){
            throw new ApiException(ApiResultEnum.PARAMETER_NULL);
        }
        String method = "GET";
        Map<String, String> headers = new HashMap<String, String>();
        //最后在header中的格式(中间是英文空格)为Authorization:APPCODE 83359fd73fe94948385f570e3c139105
        headers.put("Authorization", "APPCODE " + appCode);
        Map<String, String> querys = new HashMap<String, String>();
        if(!StringUtils.isEmpty(dto.getLg())){
            querys.put("lg", dto.getLg());
        }else{
            querys.put("lg", "CN");
        }
        querys.put("no", dto.getNo());
        querys.put("type", dto.getType());
        HttpResponse response = HttpUtils.doGet(host, EmsApi.QUERY, method, headers, querys);
        String result = EntityUtils.toString(response.getEntity());
        JSONObject jsonObject = JSON.parseObject(result);
        if(!jsonObject.getString("code").equals("OK")){
            log.error("快递物流查询失败，返回值为：{} ,请求参数为：{}",JSON.toJSONString(jsonObject),JSON.toJSONString(dto));
            if(jsonObject.getString("code").equals("204")){
                throw new ApiException(ApiResultEnum.FAILED);
            }else if(jsonObject.getString("code").equals("205")){
                throw new ApiException(ApiResultEnum.FAILED);
            }
        }
        return Result.ok(jsonObject);
    }

    /**
     * 快递鸟 的api
     * @param dto
     * @return
     * @throws Exception
     */
    @Override
    public Result queryKDNEMS(EMSQueryDTO dto) throws Exception {
        if(StringUtils.isEmpty(dto.getNo())){
            throw new ApiException(ApiResultEnum.PARAMETER_NULL);
        }
        JSONObject jsonObject = new JSONObject();
        String type = dto.getType();
        //根据单号，获取运营商编码
        if(StringUtils.isEmpty(type)){
            List<String> listCode = EmsUtils.getEmsCode(dto.getNo());
            System.out.println("list=code"+listCode);
            if(listCode != null && listCode.size() >0){
                for(String code:listCode){
                   String result =  kdniaoTrackQueryAPI.getOrderTracesByJson(code, dto.getNo());
                    jsonObject = JSON.parseObject(result);
                    if (!jsonObject.getJSONArray("Traces").isEmpty()) {
                        break;
                    }
                }
            }else{
                throw new ApiException(ApiResultEnum.EMS_CODE_NOT_FOUND);
            }
        }else{
            String orderTracesByJson = kdniaoTrackQueryAPI.getOrderTracesByJson(type, dto.getNo());
            jsonObject = JSON.parseObject(orderTracesByJson);
        }
        return Result.ok(jsonObject);
    }
}
