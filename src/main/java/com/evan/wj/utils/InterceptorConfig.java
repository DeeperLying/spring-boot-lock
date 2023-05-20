package com.evan.wj.utils;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @author SuperLee
 * @date 2022/10/6 下午1:06
 */

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new ControllerInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/api/wxRequest")
                .excludePathPatterns("/api/getWxUserAuthInfo")
                .excludePathPatterns("/api/chat")
                .excludePathPatterns("/api/createAiImage")
                .excludePathPatterns("/api/comeHerePay")
                .excludePathPatterns("/api/createGoods")
                .excludePathPatterns("/api/getGoodsList")
                .excludePathPatterns("/api/aliPayAsyncNotify")
                .excludePathPatterns("/api/getOrderList")
                .excludePathPatterns("/api/login");
    }
}
