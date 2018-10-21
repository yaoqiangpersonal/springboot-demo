package com.example.springbootdemo.extend.security;

import com.example.springbootdemo.common.utils.JsonLoginUtil;
import org.springframework.security.web.authentication.*;


import javax.servlet.http.HttpServletRequest;



/**
 * @Auther: yq
 * @Date: 2018-10-16 18:44
 * @Description:  参数转换
 */
public class MyUsernamePasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private String body;

    public MyUsernamePasswordAuthenticationFilter() {
    }

    @Override
    protected String obtainPassword(HttpServletRequest request) {
        return JsonLoginUtil.getParams(request,getPasswordParameter());
    }

    @Override
    protected String obtainUsername(HttpServletRequest request) {
        return JsonLoginUtil.getParams(request,getUsernameParameter());
    }
}
