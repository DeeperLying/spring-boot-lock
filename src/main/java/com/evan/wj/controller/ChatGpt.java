package com.evan.wj.controller;

import com.alibaba.fastjson.JSONObject;
import com.evan.wj.result.Result;
import com.evan.wj.service.ChatGptService;
import com.theokanning.openai.OpenAiService;
import com.theokanning.openai.completion.CompletionChoice;
import com.theokanning.openai.completion.CompletionRequest;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author SuperLee
 * @date 2023/3/4 下午8:31
 */
@RestController
@CrossOrigin(allowCredentials = "true")
@RequestMapping(value = "/api")
public class ChatGpt {

    @Autowired
    ChatGptService chatGptService;

    @GetMapping(value = "/chat")
    public Result chat(@RequestParam(value = "value", required = true) String value, HttpServletResponse response, HttpServletRequest request) {
        String chatOpenId = request.getHeader("chatOpenId");
        System.out.println(chatOpenId + "============chat Id");

        String result =  chatGptService.openChat(value);
        response.addHeader("Accept-Language", "en,zh-CN;q=0.9,zh;q=0.8");
        response.setHeader("Content-Type","application/json;charset=UTF-8");
        try {
            JSONObject body = JSONObject.parseObject(result);
            return new Result(200, body);
        }catch (Exception e) {
           return new Result(400, result);
        }

    }
}
