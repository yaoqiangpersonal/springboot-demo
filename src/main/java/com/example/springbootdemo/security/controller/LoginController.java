package com.example.springbootdemo.security.controller;

import com.example.springbootdemo.common.utils.Msg;
import com.example.springbootdemo.security.po.User;
import com.example.springbootdemo.security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class LoginController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/index")
    @ResponseBody
    public Msg login(@AuthenticationPrincipal User loginedUser, @RequestParam(name = "logout", required = false) String logout) {
        if (logout != null) {
            return null;
        }
        return Msg.success();
    }
}
