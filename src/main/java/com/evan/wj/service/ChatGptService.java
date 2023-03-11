package com.evan.wj.service;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPObject;
import com.evan.wj.utils.RestTemplateConfig;
import com.theokanning.openai.OpenAiService;
import com.theokanning.openai.completion.CompletionChoice;
import com.theokanning.openai.completion.CompletionRequest;
import org.apache.http.HttpHeaders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.HttpClientErrorException;

import javax.xml.ws.spi.http.HttpHandler;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author SuperLee
 * @date 2023/3/4 下午9:24
 */
@Service
public class ChatGptService {
    private final int TIMEOUT = 30000;
    private final String OPENAPI_TOKEN = "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";

    @Autowired
    RestTemplateConfig restTemplateConfig;

    public String openChat(String value) {
        String baseUrl = "https://api.openai.com/v1/completions";
        MultiValueMap header = new LinkedMultiValueMap();
        header.add("Content-Type","application/json; charset=utf-8");
        header.add("Authorization","Bearer " + OPENAPI_TOKEN);

        Map params = new HashMap<>();
        params.put("model", "text-davinci-003");
        params.put("prompt", value);
        params.put("temperature", 1);
        params.put("max_tokens", 1000);

        HttpEntity<Object> requestEntity = new HttpEntity<>(params, header);
        try {
            restTemplateConfig.customRestTemplate.getMessageConverters().set(1, new StringHttpMessageConverter(StandardCharsets.UTF_8));
            ResponseEntity<String> result = restTemplateConfig.customRestTemplate.exchange(baseUrl, HttpMethod.POST, requestEntity,  String.class);
            return result.getBody();
        } catch (HttpClientErrorException e) {
            return !"null".equals(e) ? e.toString() : "";
        }
    }
}
