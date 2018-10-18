package com.example.springbootdemo.extend.security.Handler;

import com.example.springbootdemo.common.utils.Msg;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Writer;

/**
 * @Auther: yq
 * @Date: 2018-10-18 10:06
 * @Description:
 */
public class MyAuthenticationFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, AuthenticationException e) throws IOException, ServletException {
        //jackson 对象转换
        httpServletResponse.setCharacterEncoding("UTF-8");
        Writer w = httpServletResponse.getWriter();
        ObjectMapper mapper = new ObjectMapper();
        //设置对应状态码
        httpServletResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        e.printStackTrace();
        w.write(mapper.writeValueAsString(Msg.passwordError().add("exception",e)));
        w.close();
    }
}
