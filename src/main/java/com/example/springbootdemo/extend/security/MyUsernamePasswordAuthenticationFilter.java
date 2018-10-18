package com.example.springbootdemo.extend.security;

import com.example.springbootdemo.common.utils.JsonPathUtil;
import com.example.springbootdemo.extend.security.Handler.MyAuthenticationFailureHandler;
import com.example.springbootdemo.extend.security.Handler.MyAuthenticationSuccessHandler;

import org.apache.commons.io.IOUtils;

import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.http.HttpServletRequest;



/**
 * @Auther: yq
 * @Date: 2018-10-16 18:44
 * @Description:  参数转换
 */
public class MyUsernamePasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private String body;

    public MyUsernamePasswordAuthenticationFilter(String successUrl) {

        setRequiresAuthenticationRequestMatcher(new AntPathRequestMatcher("/login", "POST"));
        setAuthenticationSuccessHandler(new MyAuthenticationSuccessHandler());
        setAuthenticationFailureHandler(new MyAuthenticationFailureHandler());
    }

    @Override
    protected String obtainPassword(HttpServletRequest request) {
        return get(getPasswordParameter(),request);
    }

    @Override
    protected String obtainUsername(HttpServletRequest request) {
        return get(getUsernameParameter(),request);
    }

    /**
     * 获取参数方式
     *
     * @param key 关键词
     * @param request   请求体
     * @return 返回参数
     */
    private String get(String key,HttpServletRequest request){
        try {
            //post请求，获取username和password
            byte[] arr = IOUtils.toByteArray(request.getInputStream());
            if(arr.length != 0)
                body = new String(arr);
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
