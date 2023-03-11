package com.evan.wj.service;

import com.evan.wj.utils.RestTemplateConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.util.HashMap;
import java.util.Map;

/**
 * @author SuperLee
 * @date 2023/3/11 下午1:09
 */
@Service
public class GetWeChatUserInfo {
    @Autowired
    RestTemplateConfig restTemplateConfig;

    public void getWeChatSnsapiUserinfo(String access_token, int openId) {
        Map params = new HashMap<String,Object>(3);
        params.put("access_token", access_token);
        params.put("openId", openId);
        params.put("lang", "zh_CN");
        String path = "https://api.weixin.qq.com/sns/userinfo?access_token=${access_token}&openid=${openId}&lang=${lang}";
        try {
            String result = restTemplateConfig.customRestTemplate.getForObject(path,String.class,params);
            System.out.println(result+"=======success");
        }catch (HttpClientErrorException httpClientErrorException) {
            System.out.println(httpClientErrorException.getResponseBodyAsString());
        }
    }
}
