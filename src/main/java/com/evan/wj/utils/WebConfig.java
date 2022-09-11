package com.evan.wj.utils;

import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Controller;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

//@Controller
//public class WebConfig implements WebMvcConfigurer {
//
//    public void addResourceHandlers(ResourceHandlerRegistry registry){
//        registry.addResourceHandler("/img/**").addResourceLocations("classpath:/static/img/");
//    }
//}

//@Configuration
//public class WebConfig implements WebMvcConfigurer {
//
//    @Override
//    public void addResourceHandlers(ResourceHandlerRegistry registry) {
//        //其中image表示访问的前缀。"file:F:/img/"是文件真实的存储路径
//        //registry.addResourceHandler("/image/**").addResourceLocations("file:F:/img/");
//        System.out.println("---------");
//        registry.addResourceHandler("/img/**").addResourceLocations("classpath:/static/");
//    }
//
//}

//@Configuration
//public class WebConfig extends WebMvcConfigurationSupport {
//    @Override
//    protected void addInterceptors(InterceptorRegistry registry) {
//        //registry.addInterceptor(new UserRoleAuthorizationInterceptor()).addPathPatterns("/pics/**");
//        super.addInterceptors(registry);
//    }
//
//    @Override
//    protected void addResourceHandlers(ResourceHandlerRegistry registry) {
//        registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
//        super.addResourceHandlers(registry);
//    }
//}