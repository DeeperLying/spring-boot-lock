package com.evan.wj.controller;

import com.alibaba.fastjson.JSONObject;
import com.evan.wj.result.Result;
import com.evan.wj.utils.RestTemplateConfig;
import com.evan.wj.pojo.GetWxUserAuthPojo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @author SuperLee
 * @date 2023/2/26 下午5:01
 */

@RestController
@CrossOrigin(allowCredentials = "true")
@RequestMapping("/api")
public class GetWxUserAuth {
    @Autowired
    RestTemplateConfig restTemplateConfig;

    @Value("${weChat.appId}")
    private String appId;

    @Value("${weChat.appSecret}")
    private String appSecret;

    @GetMapping(value = "/getWxUserAuthInfo")
    public Result getWxUserAuthInfo(@RequestParam(value = "code", required = true) String code) {
        System.out.println(code);
        if (!code.equals(null)) {
            Map<String, String> params = new HashMap<>(3);
            params.put("appId", appId);
            params.put("appSecret", appSecret);
            params.put("code", code);
            String url = "https://api.weixin.qq.com/sns/oauth2/access_token?appid={appId}&secret={appSecret}&code={code}&grant_type=authorization_code";
            String result = restTemplateConfig.customRestTemplate.getForObject(url, String.class, params);
            System.out.println(result+"=========");
            JSONObject weChatUserAuthInfo = JSONObject.parseObject(result);
            System.out.println(weChatUserAuthInfo.get("errmsg"));
            if (weChatUserAuthInfo.get("access_token") != null) {
                System.out.println("获取用户信息去啦～");
                return new Result(200, "success");
            } else {
                return new Result(400, "code 错误");
            }

        }

        return new Result(400, "success");

    }
}
