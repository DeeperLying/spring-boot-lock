package com.evan.wj.controller;

import com.evan.wj.dao.UserCustomizeDao;
import com.evan.wj.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @author SuperLee
 * @date 2023/7/22 下午11:23
 */
@RestController
@CrossOrigin(allowCredentials = "true")
@RequestMapping(value = "/api")
public class UserController {
    @Autowired
    UserCustomizeDao userCustomizeDao;

    @PostMapping(value = "/updateUserInfo")
    public void updateUserInfo (@RequestBody User user) {
        System.out.println(user + "===");
        userCustomizeDao.updateUserInfo(user.getId(), user.getHeadimgurl());
    }
}
