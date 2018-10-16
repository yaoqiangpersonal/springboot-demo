package com.example.springbootdemo;


import com.example.springbootdemo.security.po.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

/**
 * @author yaoqiang
 * @create 2018-10-16 23:11
 * @desc mvc测试
 **/
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc // 注入MockMvc
public class ApplicationTestMVC {

    @Autowired
    private MockMvc mvc;

    //url
    private String url;

    //参数
    private Object params;

    //返回结果
    private MvcResult mvcResult;

    @Test
    public void mvcTest() throws Exception {
        url = "/login";

        paramsCreate();

        mvcCreate();

        resultHandler();
    }

    private void paramsCreate() {
        User b = new User();
        b.setUsername("admin");
        b.setPassword("admin");
        params = b;
    }

    private void mvcCreate() throws Exception {
        /* 构建request 发送请求GET请求
         * MockMvcRequestBuilders 中有很多 请求方式。像get、post、put、delete等等
         */

        ObjectMapper mapper = new ObjectMapper();
        mvcResult = mvc.perform(MockMvcRequestBuilders.post(url)
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content(mapper.writeValueAsString(params))
                .accept(MediaType.APPLICATION_JSON)) // 断言返回结果是json
                .andReturn();// 得到返回结果
    }

    private void resultHandler() throws Exception {
        MockHttpServletResponse response = mvcResult.getResponse();
        //拿到请求返回码
        int status = response.getStatus();
        //拿到结果
        String contentAsString = response.getContentAsString();

        System.err.println(status);
        System.err.println(contentAsString);
    }
}
