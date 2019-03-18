package top.lrshuai.common.utils;

import javax.crypto.Mac;
import javax.crypto.spec.SecretKeySpec;
import java.util.Map;
import java.util.TreeMap;

/**
 * 加密工具类
 */
public class EncryptUtils {

    /**
     * 生成签名
     * @param params 这个map,需要TreeMap,按键的升序排序 ，不然（用HashMap随机存储）加密生成的结果会不同
     * @param secret
     * @return
     */
    public static String getSign(TreeMap<String, Object> params, String secret){
        String key="";
        StringBuilder sb = new StringBuilder();
        for (Map.Entry<String,Object> entrySet : params.entrySet()) {
            if("sign".equals(entrySet.getKey()) ){
                //
            }else if("key".equals(entrySet.getKey())){
                key = (String) entrySet.getValue();
            }else{
                sb.append("&").append(entrySet.getKey()).append("=").append(entrySet.getValue());
            }
        }
        sb.delete(0, 1);
        sb.append("&key=" + key);
        return sha256HMAC(sb.toString().trim(), secret).toUpperCase();
    }

    /**
     * HMAC SHA256 加密
     * @param content
     * @param secret
     * @return
     */
    public static String sha256HMAC(String content, String secret) {
        try {
            Mac sha256_HMAC = Mac.getInstance("HmacSHA256");
            SecretKeySpec secret_key = new SecretKeySpec(secret.getBytes(), "HmacSHA256");
            sha256_HMAC.init(secret_key);
            byte[] bytes = sha256_HMAC.doFinal(content.getBytes());

            StringBuffer buf = new StringBuffer();
            String stmp;
            for (int n = 0; bytes != null && n < bytes.length; n++) {
                stmp = Integer.toHexString(bytes[n] & 0XFF);
                if (stmp.length() == 1) {
                    buf.append('0');
                }
                buf.append(stmp);
            }
            return buf.toString();
        } catch (Exception e) {
            return "";
        }
    }
}
