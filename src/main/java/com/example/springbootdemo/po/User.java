package com.example.springbootdemo.po;

import lombok.Data;

/**
 * @Auther: yq
 * @Date: 2018-09-24 10:55
 * @Description:
 */
@Data
public class User {
    private Long id;
    private String name;
    private Integer age;
    private String email;
}
