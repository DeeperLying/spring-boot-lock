package com.evan.wj.utils;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.MediaType;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.cbor.MappingJackson2CborHttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * @author SuperLee
 * @date 2023/2/26 下午6:46
 */

@Configuration
public class RestTemplateConfig {

    public RestTemplate customRestTemplate = new RestTemplate(getClientHttpRequestFactory());

    // 因为weChat的response type 是text/* 所以做了MediaType的支持
    @Bean
    public RestTemplate customResultTypeRestTemplate() {
        RestTemplate customRestTemplate = new RestTemplate(getClientHttpRequestFactory());
        List<HttpMessageConverter<?>> messageConverters = new ArrayList<HttpMessageConverter<?>>();
        MappingJackson2HttpMessageConverter converter = new MappingJackson2HttpMessageConverter();
        converter.setSupportedMediaTypes(Collections.singletonList(MediaType.ALL));
        messageConverters.add(converter);
        customRestTemplate.setMessageConverters(messageConverters);
        return customRestTemplate;
    }

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
