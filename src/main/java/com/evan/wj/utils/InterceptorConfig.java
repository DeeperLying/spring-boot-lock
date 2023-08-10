package com.evan.wj.utils;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
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
//                .excludePathPatterns("/api/chat")
//                .excludePathPatterns("/api/createAiImage")
//                .excludePathPatterns("/api/comeHerePay")
//                .excludePathPatterns("/api/createGoods")
//                .excludePathPatterns("/api/getGoodsList")
//                .excludePathPatterns("/api/aliPayAsyncNotify")
//                .excludePathPatterns("/api/getOrderList")
//                .excludePathPatterns("/api/rabbitMQ")
                .excludePathPatterns("/api/getArticleList")
                .excludePathPatterns("/api/sendEmail")
                .excludePathPatterns("/api/register")
                .excludePathPatterns("/api/register/phone")
                .excludePathPatterns("/api/login/phone")
                .excludePathPatterns("/api/login")
                .excludePathPatterns("/api/getArticle")
                .excludePathPatterns("/api/comment/getComments")
                .excludePathPatterns("/img/**");
    }

//    TODO 在本地我就直连服务器，本身springboot static目录就有映射，不需要在意端口号是否暴露。正是环境我也不需要通过这种方式映射因为同样会暴露真实端口号，我直接用Nginx代理
//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        /**
//         * 将/upload/**映射到file:D://upload
//         */
//        registry.addResourceHandler("/img/**").addResourceLocations("file:/media/images/");
//    }
}
