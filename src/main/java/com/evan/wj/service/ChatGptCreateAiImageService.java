package com.evan.wj.service;

import com.evan.wj.result.Result;
import com.evan.wj.utils.RestTemplateConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 * @author SuperLee
 * @date 2023/4/2 上午11:13
 */

@Service
public class ChatGptCreateAiImageService {

    @Value("${chatGpt.token}")
    String OPENAPI_TOKEN;

    @Autowired
    RestTemplateConfig restTemplateConfig;

    public String createImage(String desc) {
        String baseUrl = "https://api.openai.com/v1/images/generations";
        MultiValueMap header = new LinkedMultiValueMap();
        header.add("Content-Type","application/json; charset=utf-8");
        header.add("Authorization","Bearer " + OPENAPI_TOKEN);

        Map params = new HashMap<>();
        params.put("prompt", desc);
        params.put("n", 1);
        params.put("size", "256x256");

        HttpEntity<Object> requestEntity = new HttpEntity<>(params, header);
        System.out.println("进没进来");
        try {
            restTemplateConfig.customRestTemplate.getMessageConverters().set(1, new StringHttpMessageConverter(StandardCharsets.UTF_8));
            ResponseEntity<String> result = restTemplateConfig.customRestTemplate.exchange(baseUrl, HttpMethod.POST, requestEntity,  String.class);
            System.out.println(result);
            return result.getBody();
        } catch (HttpClientErrorException e) {
            System.out.println(e);
            System.out.println(e.getResponseBodyAsString());
            return !"null".equals(e) ? e.toString() : "";
        }
    }
}
