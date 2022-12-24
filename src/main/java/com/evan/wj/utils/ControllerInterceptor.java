package com.evan.wj.utils;

import com.alibaba.fastjson.JSONObject;
import io.netty.handler.codec.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author SuperLee
 * @date 2022/10/6 下午12:54
 */

public class ControllerInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        String token = request.getHeader("Authentication");
        String origin = request.getHeader("Origin");

        System.out.println(request.getMethod());
        System.out.println(token);
        System.out.println(token == null);
        // 如果是OPTIONS则结束请求
        if (HttpMethod.OPTIONS.toString().equals(request.getMethod())) {
            response.setHeader("Access-Control-Allow-Origin", request.getHeader("origin"));
            response.setHeader("Access-Control-Allow-Credentials", "true");
            response.setHeader("Access-Control-Allow-Methods", "GET, HEAD, POST, PUT, PATCH, DELETE, OPTIONS");
            response.setHeader("Access-Control-Max-Age", "1800");
            response.setHeader("Access-Control-Allow-Headers", "Authentication, content-type");
            response.setStatus(HttpStatus.NO_CONTENT.value());
            return false;
        }

        if ("undefined".equals(token) || token == null) {
            return result(response, origin);
        }
        Map<String, String> tokenVerify = JWTUtils.handlerJWTVerifier(token);
        String isToken = tokenVerify.get("isToken");
        if (!Boolean.parseBoolean(isToken)) {
            return result(response, origin, tokenVerify.get("errorMsg"));
        }
        return true;

    }

    private boolean result(HttpServletResponse response, String origin) {
        try {
        Map<String, String> data = new HashMap<>();
        data.put("errorMsg", "当前用户未登录");
        data.put("code", "401");
        response.addHeader("Access-Control-Allow-Credentials", "true");
        response.addHeader("Access-Control-Allow-Origin", origin);
        response.addHeader("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT");
        response.addHeader("Access-Control-Allow-Headers", "Content-Type,X-CAF-Authorization-Token,sessionToken,X-TOKEN");
        response.setContentType("application/json; charset=utf-8");
        response.setStatus(401);
        PrintWriter writer = null;
        writer = response.getWriter();
        writer.print(JSONObject.toJSONString(data));
        writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    private boolean result(HttpServletResponse response, String origin, String message) {
        try {
            Map<String, String> data = new HashMap<>();
            data.put("errorMsg", message);
            data.put("code", "401");
            response.addHeader("Access-Control-Allow-Credentials", "true");
            response.addHeader("Access-Control-Allow-Origin", origin);
            response.addHeader("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT");
            response.addHeader("Access-Control-Allow-Headers", "Content-Type,X-CAF-Authorization-Token,sessionToken,X-TOKEN");
            response.setContentType("application/json; charset=utf-8");
            response.setStatus(401);
            PrintWriter writer = null;
            writer = response.getWriter();
            writer.print(JSONObject.toJSONString(data));
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
