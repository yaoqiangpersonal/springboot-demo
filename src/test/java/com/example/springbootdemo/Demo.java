package com.example.springbootdemo;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.jayway.jsonpath.JsonPath;
import org.junit.Test;

import java.io.IOException;

/**
 * @author yaoqiang
 * @create 2018-10-18 23:03
 * @desc 普通测试类
 **/
public class Demo {

    @Test
    public void run(){
        String text = "454544455-";
        try {
            final ObjectMapper mapper = new ObjectMapper();
            mapper.readTree(text);
            System.out.println("true");
        } catch (IOException e) {
            System.out.println("false");
        }
    }

}
