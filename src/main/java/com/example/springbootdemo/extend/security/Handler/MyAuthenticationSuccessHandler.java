package com.example.springbootdemo.extend.security.Handler;

import com.example.springbootdemo.common.utils.Msg;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.Writer;

/**
 * @Auther: yq
 * @Date: 2018-10-17 17:18
 * @Description:
 */
public class MyAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {
        //jackson 对象转换
        httpServletResponse.setCharacterEncoding("UTF-8");
        PrintWriter w = httpServletResponse.getWriter();
        //这句话的意思，是让浏览器用utf8来解析返回的数据
        //httpServletResponse.setHeader("Content-type", "text/html;charset=UTF-8");
        //这句话的意思，是告诉servlet用UTF-8转码，而不是用默认的ISO8859

        ObjectMapper mapper = new ObjectMapper();
        w.write(mapper.writeValueAsString(Msg.success().add("authentication",authentication)));
        w.close();
    }
}
