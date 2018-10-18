package com.example.springbootdemo.security.controller;


import com.example.springbootdemo.common.utils.Msg;
import com.example.springbootdemo.security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.security.Principal;


@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/check")
    @ResponseBody
    public Msg login(Principal loginedUser, @RequestParam(name = "logout", required = false) String logout) {
        if (logout != null) {
            return null;
        };
        return Msg.success().add("user",loginedUser);
    }
}
