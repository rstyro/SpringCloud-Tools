package top.lrshuai.common.utils;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import org.apache.commons.io.FileUtils;
import top.lrshuai.common.entity.EmsMatcher;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Pattern;

public class EmsUtils {

    /**
     * 通过订单号，获取运营商 编号
     * @param number
     * @return
     */
    public static List<String> getEmsCode(String number){
        List<String> list = new ArrayList<>();
        for(EmsMatcher matcher:EmsMatcher.values()){
            if(Pattern.matches(matcher.getReg(), number)){
                list.add(matcher.getCode());
            }
        }
        return list;
    }
}
