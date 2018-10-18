package com.example.springbootdemo.security.exception;

import org.springframework.security.core.AuthenticationException;

/**
 * @Auther: yq
 * @Date: 2018-10-18 10:54
 * @Description:
 */
public class PasswordException extends AuthenticationException {
    public PasswordException(String msg, Throwable t) {
        super(msg, t);
    }

    public PasswordException(String msg) {
        super(msg);
    }
}
