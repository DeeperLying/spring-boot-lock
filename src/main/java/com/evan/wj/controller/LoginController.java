package com.evan.wj.controller;

import com.evan.wj.result.Result;
import com.evan.wj.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.evan.wj.pojo.User;
import org.springframework.web.util.HtmlUtils;

import java.util.Objects;

@Controller
public class LoginController {

    @Autowired
    UserService userService;

    @CrossOrigin
    @PostMapping(value = "api/login")
    @ResponseBody
    public Result Login(@RequestBody User requestUser) {


        String userName =  requestUser.getName();
        String password = requestUser.getPassword();
        userName = HtmlUtils.htmlEscape(userName);

        User user = userService.get(userName, password);

        System.out.print(user);

//        if(Objects.equals("admin", userName) && Objects.equals("123456", requestUser.getPassword()) ) {
//            return new Result(200);
//        } else {
//            return new Result(400);
//        }
        if (user != null) {
            return new Result(200);
        } else {
            return new Result(400);
        }
    }
}
