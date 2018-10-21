package com.example.springbootdemo.common.utils;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;

/**
 * @Auther: yq
 * @Date: 2018-10-21 16:50
 * @Description:
 */
public class JsonLoginUtil {
    private final static ThreadLocal<String> threadLocal = new ThreadLocal<>();
    private static Logger logger = LoggerFactory.getLogger(JsonLoginUtil.class);

    public static String getParams(HttpServletRequest request,String key) {
        try {
            //post请求，获取username和password
            String body = null;
            byte[] arr = IOUtils.toByteArray(request.getInputStream());
            if(arr.length != 0) {
                body = new String(arr);
                threadLocal.set(body);
            }else{
                body = threadLocal.get();
            }
            if(JsonPathUtil.isJson(body)) {
                String namePath = "$." + key;
                String result = JsonPathUtil.getByPath(body, namePath).toString();
                return result;
            }else{
                return request.getParameter(key);
            }
        } catch (Exception e) {
            logger.error("获取" + key + "出现问题，返回空");
            return "";
        }
    }
}
