package com.example.springbootdemo.common.utils;



import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.DocumentContext;
import com.jayway.jsonpath.JsonPath;
import net.minidev.json.JSONArray;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

/**
 * @Auther: yq
 * @Date: 2018-08-02 11:44
 * @Description:
 */
public class JsonPathUtil {

    private JsonPathUtil(){}

    protected final static Log logger = LogFactory.getLog(JsonPathUtil.class);

    public static Object getByPath(String text, String path){
        DocumentContext jc = JsonPath.parse(text);
        return jc.read(path);
    }

    public static Map<String,Object> paseMap(String text, Map<String,Object> keyAndJsonPath){
        DocumentContext jc = JsonPath.parse(text);
        Set<Map.Entry<String,Object>> entrys = keyAndJsonPath.entrySet();
        for(Map.Entry<String,Object> e:entrys){
            keyAndJsonPath.put(e.getKey(),check(read(jc,e.getValue().toString())));
        }
        return keyAndJsonPath;
    }

    private static Object read(DocumentContext jc, String s) {
        try {
            return jc.read(s);
        } catch (Exception e) {
            logger.error("jsonPath解析" + s + "出现错误:,json:" + jc.jsonString());
            return "";
        }
    }


    public static boolean isJson(String json) {
        if (StringUtils.isEmpty(json)) {
            return false;
        }
        try {
            final ObjectMapper mapper = new ObjectMapper();
            mapper.readTree(json);
            return true;
        } catch (IOException e) {
            return false;
        }
    }


    /**
     * 对解析结果进行分析
     *
     * @param o 解析结果
     * @return
     */
    private static Object check(Object o){

        if(o instanceof JSONArray){

            StringBuilder sb = new StringBuilder(100);
            //如果是数组形式，那么返回只能以字符串形式返回
            //((JSONArray) o).forEach(s-> sb.append(excludeChar(s.toString())).append(','));
            for(Object oo:(JSONArray) o){
                sb.append(excludeChar(oo.toString())).append(',');
            }

            return sb.toString();
        }else if(o instanceof String && !StringUtils.isEmpty(o)){
            //字符检验
            return excludeChar((String)o);
        }else{
            //其它类型不做处理
            return o;
        }
    }

    /**
     *
     * 排除字符
     *
     * @param s 原始字符串
     * @return 处理过后的字符串
     */
    private static String excludeChar(String s){
        return s.replaceAll("[^一-龢\\w\\s\\p{P}]", "");
    }

}
