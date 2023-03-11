package com.evan.wj.service;

import com.evan.wj.pojo.WeChatUserInfoPojo;
import com.evan.wj.utils.RestTemplateConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

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

    @Autowired
    RestTemplate customResultTypeRestTemplate;

    public void getWeChatSnsapiUserinfo(String access_token, String openId) {
        System.out.println(access_token+"ppp++++++"+openId);

        Map params = new HashMap<String,Object>(3);
        params.put("access_token", access_token);
        params.put("openId", openId);
        params.put("lang", "zh_CN");
        String path = "https://api.weixin.qq.com/sns/userinfo?access_token={access_token}&openid={openId}&lang={lang}";
        try {
            WeChatUserInfoPojo result = customResultTypeRestTemplate.getForObject(path, WeChatUserInfoPojo.class,params);
            System.out.println(result+"=======success");
        }catch (HttpClientErrorException httpClientErrorException) {
            System.out.println(httpClientErrorException.getResponseBodyAsString());
        }
    }
}
