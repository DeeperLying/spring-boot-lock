package com.evan.wj.utils;

import com.alibaba.fastjson.JSONObject;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * @author SuperLee
 * @date 2022/10/6 下午12:54
 */

public class ControllerInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        System.out.println("Request URL: " + request.getRequestURL());
        System.out.println("拦截器");
        String token = request.getHeader("Authentication");
        String origin = request.getHeader("Origin");
        System.out.println("auth:"+ token);
        if (token != null) {
            boolean isToken = JWTUtils.handlerJWTVerifier(token);
            System.out.println(isToken);
            if (!isToken) {
                try {
                    Map<String, String> data = new HashMap<String, String>();
                    data.put("data", "拦截器跨域");
                    response.addHeader("Access-Control-Allow-Credentials", "true");
                    response.addHeader("Access-Control-Allow-Origin", origin);
                    response.addHeader("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT");
                    response.addHeader("Access-Control-Allow-Headers", "Content-Type,X-CAF-Authorization-Token,sessionToken,X-TOKEN");
                    response.setContentType("application/json; charset=utf-8");
                    PrintWriter writer = response.getWriter();
                    writer.print(JSONObject.toJSONString(data));
                    writer.close();
                    response.flushBuffer();
                    return false;
                } catch (Exception exception) {
                    System.out.println(exception);
                }

            }
            return true;
        }
        return true;
    }
}
