package com.evan.wj.controller;

import com.alibaba.fastjson.JSON;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.evan.wj.dao.UserDao;
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

    @Autowired
    UserDao userDao;

    @CrossOrigin(allowCredentials = "true")
    @PostMapping(value = "api/login")
    @ResponseBody
    public ResponseEntity<Map> Login(HttpServletResponse response, @RequestBody User requestUser) {

        List<ArticleList> articleListList = articleListService.getArticleList();

        String email =  requestUser.getEmail();
        String password = requestUser.getPassword();
        //userName = HtmlUtils.htmlEscape(userName);
        Map user = userService.get(email, password);
        System.out.println(user +"user");
        Map<String, Object> data = new HashMap<>();
        if (!user.isEmpty()) {
            String userName = user.get("userName").toString();
            String token = jwtUtils.createUserAndPhoneToken(userName);
            data.put("token", token);
            data.put("code", 200);
            data.put("data", user);
            // return new Result(200, data);
            return new ResponseEntity<Map>(data, HttpStatus.OK);
        } else {
            Map<String, Object> dataList = new HashMap<>();
            data.put("errorMessage", "邮箱或密码错误");
            data.put("data", dataList);
            data.put("code", 400);
            return new ResponseEntity<Map>(data, HttpStatus.BAD_REQUEST);
            // return new Result(400);
        }
    }

    @CrossOrigin(allowCredentials = "true")
    @PostMapping(value = "api/login/phone")
    @ResponseBody
    public ResponseEntity<Map> LoginPhone(HttpServletResponse response, @RequestBody User requestUser) {

        List<ArticleList> articleListList = articleListService.getArticleList();

        String phone =  requestUser.getPhone();
        String password = requestUser.getPassword();
        //userName = HtmlUtils.htmlEscape(userName);
        Map user = userService.getPhone(phone, password);
        System.out.println(user +"user12");
        Map<String, Object> data = new HashMap<>();
        if (!user.isEmpty()) {
            String userName = user.get("userName").toString();
            String token = jwtUtils.createUserAndPhoneToken(userName);
            data.put("token", token);
            data.put("code", 200);
            data.put("data", user);
            // return new Result(200, data);
            return new ResponseEntity<Map>(data, HttpStatus.OK);
        } else {
            Map<String, Object> dataList = new HashMap<>();
            data.put("errorMessage", "手机号或密码错误");
            data.put("data", dataList);
            data.put("code", 400);
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

    @CrossOrigin(allowCredentials = "true")
    @PostMapping(value = "api/register/phone")
    @ResponseBody
    public Result registerPhone(@RequestBody User user, HttpSession httpSession) {
        String username = user.getUsername();
        String password = user.getPassword();
        String phone = user.getPhone();
        int isRegister = userDao.savePhoneUser(username, password, phone);

        if (isRegister == 1) {
            return new Result(200, "success");
        }
        return new Result(400, "注册失败");
    }

}
