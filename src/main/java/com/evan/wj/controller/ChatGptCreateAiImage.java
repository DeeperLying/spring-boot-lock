package com.evan.wj.controller;

import com.alibaba.fastjson.JSONObject;
import com.evan.wj.result.Result;
import com.evan.wj.service.ChatGptCreateAiImageService;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author SuperLee
 * @date 2023/4/2 上午10:21
 */
@RestController
@CrossOrigin(allowCredentials = "true")
@RequestMapping("/api")
public class ChatGptCreateAiImage {

    @Autowired
    ChatGptCreateAiImageService chatGptCreateAiImageService;

    @GetMapping(value = "/createAiImage")
    public Result createAiImage(@Param("value") String value) {
        System.out.println(value);
        String imageData = chatGptCreateAiImageService.createImage(value);
        JSONObject body = JSONObject.parseObject(imageData);
        return new Result(200,"success", body);
    }
}
