package com.evan.wj.controller;

import com.alibaba.fastjson.JSON;
import com.evan.wj.pojo.ArticleList;
import com.evan.wj.result.Result;
import com.evan.wj.service.ArticleListService;
import com.evan.wj.service.UserService;
import com.evan.wj.utils.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.evan.wj.pojo.User;
import org.springframework.web.util.HtmlUtils;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class LoginController {

    @Autowired
    UserService userService;

    @Autowired
    ArticleListService articleListService;

    @Autowired
    EmailService emailService;

    @CrossOrigin
    @PostMapping(value = "api/login")
    @ResponseBody
    public Result Login(@RequestBody User requestUser) {

        List<ArticleList> articleListList = articleListService.getArticleList();

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

    @CrossOrigin(allowCredentials = "true")
    @GetMapping(value = "api/sendEmail")
    @ResponseBody
    public Result sendEmail(@RequestParam("email") String email, HttpSession httpSession) {
        boolean isSendEmail = emailService.sendEmail(email, httpSession);
        if (isSendEmail) {
            return new Result(200, "success");
        }
        return new Result(400, "邮件发送失败");
    }

    @CrossOrigin(allowCredentials = "true")
    @PostMapping(value = "api/register")
    @ResponseBody
    public Result sendEmail(@RequestBody User user, HttpSession httpSession) {
        boolean isRegister = userService.register(user, httpSession);

        if (isRegister) {
            return new Result(200, "success");
        }
        return new Result(400, "注册失败");
    }

}
