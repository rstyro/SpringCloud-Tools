package top.lrshuai.common.utils;

import top.lrshuai.common.dto.sms.SmsDTO;

import java.util.Random;
import java.util.TreeMap;
import java.util.UUID;

public class Tools {
    public static String getUUID(){
        return UUID.randomUUID().toString().replace("-", "");
    }

    public static String random(int n){
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < n; i++) {
            sb.append(new Random().nextInt(10));
        }
        return sb.toString();
    }

    /**
     * 短信加密需要啦
     * @param dto
     * @return
     */
    public static TreeMap<String,Object> getMap(SmsDTO dto){
        TreeMap<String,Object> treeMap = new TreeMap<>();
        treeMap.put("areaCode",dto.getAreaCode());
        treeMap.put("mobile",dto.getMobile());
        treeMap.put("timestamp",dto.getTimestamp());
        treeMap.put("type",dto.getType());
        treeMap.put("key",dto.getKey());
        return treeMap;
    }
}
