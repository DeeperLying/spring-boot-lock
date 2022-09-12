package com.evan.wj.controller;

import com.alibaba.fastjson.JSON;
import com.evan.wj.pojo.ArticleList;
import com.evan.wj.result.Result;
import com.evan.wj.service.ArticleListService;
import com.evan.wj.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.evan.wj.pojo.User;
import org.springframework.web.util.HtmlUtils;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@Controller
public class LoginController {

    @Autowired
    UserService userService;

    @Autowired
    ArticleListService articleListService;

    @CrossOrigin
    @PostMapping(value = "api/login")
    @ResponseBody
    public Result Login(@RequestBody User requestUser) {

        List<ArticleList> articleListList = articleListService.getArticleList();

        System.out.println(JSON.toJSONString(articleListList));

        String userName =  requestUser.getUsername();
        String password = requestUser.getPassword();
        userName = HtmlUtils.htmlEscape(userName);

        User user = userService.get(userName, password);

        System.out.print(user);

        if (user != null) {
            return new Result(200);
        } else {
            return new Result(400);
        }
    }
}
