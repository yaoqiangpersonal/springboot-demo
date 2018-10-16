package com.example.springbootdemo.security.controller;

import com.example.springbootdemo.common.utils.Msg;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@RequestMapping(value = "/user")
@RestController
public class UserController {

	@RequestMapping(method = RequestMethod.GET)
    @ResponseBody
    public Object list(HttpServletRequest request) {
		return "Get all User";
    }

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public Object detail(@PathVariable Integer id) {
		return "Get a user";
    }
    
    @RequestMapping(method = RequestMethod.POST)
    @ResponseBody
    public Object create(HttpServletRequest request) {
		return "POST a user";
    }

    @RequestMapping(method = RequestMethod.PUT)
    @ResponseBody
    public Object update(HttpServletRequest request) {
		return "PUT a user";
    }

    @RequestMapping(value = "/success")
    @ResponseBody
    public Msg success(Principal loginedUser, @RequestParam(name = "logout", required = false) String logout) {
        if (logout != null) {
            return null;
        };
        System.out.println(loginedUser);
        return Msg.success().add("user",loginedUser);
    }

}