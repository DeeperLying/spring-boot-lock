package com.evan.wj.controller;

import com.alibaba.fastjson.JSON;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.evan.wj.pojo.ArticleList;
import com.evan.wj.result.Result;
import com.evan.wj.service.ArticleListService;
import com.evan.wj.service.UserService;
import com.evan.wj.utils.EmailService;
import com.evan.wj.utils.JWTUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import com.evan.wj.pojo.User;
import org.springframework.web.util.HtmlUtils;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
public class LoginController {

    @Autowired
    UserService userService;

    @Autowired
    ArticleListService articleListService;

    @Autowired
    JWTUtils jwtUtils;

    @Autowired
    EmailService emailService;

    @CrossOrigin(allowCredentials = "true")
    @PostMapping(value = "api/login")
    @ResponseBody
    public ResponseEntity<Map> Login(HttpServletResponse response, @RequestBody User requestUser) {

        List<ArticleList> articleListList = articleListService.getArticleList();

        String userName =  requestUser.getUsername();
        String password = requestUser.getPassword();
        userName = HtmlUtils.htmlEscape(userName);
        User user = userService.get(userName, password);
        Map<String, Object> data = new HashMap<>();
        if (user != null) {
            String token = jwtUtils.createToken(user);
            data.put("token", token);
            // return new Result(200, data);
            return new ResponseEntity<Map>(data, HttpStatus.OK);
        } else {
            Map<String, Object> dataList = new HashMap<>();
            data.put("errorMessage", "用户名或密码错误");
            data.put("data", dataList);
            return new ResponseEntity<Map>(data, HttpStatus.BAD_REQUEST);
            // return new Result(400);
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
