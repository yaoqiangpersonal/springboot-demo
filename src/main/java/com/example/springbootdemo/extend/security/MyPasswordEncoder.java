package com.example.springbootdemo.extend.security;

import com.example.springbootdemo.common.utils.MD5Util;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @Auther: yq
 * @Date: 2018-09-07 11:54
 * @Description: 密码策略
 */
public class MyPasswordEncoder implements PasswordEncoder {

    @Override
    public String encode(CharSequence rawPassword) {
        return MD5Util.encode((String) rawPassword);
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        return encodedPassword.equals(MD5Util.encode((String) rawPassword));
    }

}
