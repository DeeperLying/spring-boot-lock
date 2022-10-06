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
                .excludePathPatterns("/api/login");
    }
}
