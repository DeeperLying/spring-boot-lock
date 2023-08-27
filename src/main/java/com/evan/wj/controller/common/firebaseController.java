package com.evan.wj.controller.common;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.evan.wj.result.Result;
import com.evan.wj.service.FirbaseSerive;
import com.evan.wj.utils.FirebaseEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.annotations.Mapping;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author SuperLee
 * @date 2023/8/20 下午3:19
 */
@RestController
@RequestMapping(value = "/api/firebase")
@CrossOrigin(allowCredentials = "true")
public class firebaseController {
    @Autowired
    FirebaseEntity firebaseEntity;

    @Autowired
    FirbaseSerive firbaseSerive;

    @GetMapping(value = "/sendMessage")
    public void sendMessage() {
//        firebaseEntity.sendMessage();
    }

    @PostMapping(value = "/saveToken")
    public Result saveToken(@RequestBody Map data) {
        Object userIdObj = data.get("userId");
        Object firebaseTokenObj = data.get("firebaseToken");
        if (userIdObj instanceof Integer && firebaseTokenObj instanceof String) {
            int userId = (int) userIdObj;
            String firebaseToken = (String) firebaseTokenObj;
            Result response =  firbaseSerive.saveToken(userId, firebaseToken);
            return response;
            // 执行后续操作
        } else {
            // 数据类型不匹配，进行错误处理
            return new Result(400, "参数不对");
        }
    }
}
