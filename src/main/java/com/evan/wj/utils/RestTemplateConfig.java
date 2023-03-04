package com.evan.wj.utils;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.List;

/**
 * @author SuperLee
 * @date 2023/2/26 下午6:46
 */

@Component
public class RestTemplateConfig {

    public RestTemplate customRestTemplate = new RestTemplate(getClientHttpRequestFactory());

    private SimpleClientHttpRequestFactory getClientHttpRequestFactory() {
        SimpleClientHttpRequestFactory clientHttpRequestFactory
                = new SimpleClientHttpRequestFactory();
        // 连接超时设置 10s
        clientHttpRequestFactory.setConnectTimeout(60_000);

        // 读取超时设置 10s
        clientHttpRequestFactory.setReadTimeout(60_000);
        return clientHttpRequestFactory;
    }
}
