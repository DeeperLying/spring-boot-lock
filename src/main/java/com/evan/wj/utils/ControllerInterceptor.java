package com.evan.wj.utils;

import com.alibaba.fastjson.JSONObject;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
        if (token != null) {
            Map<String, String> tokenVerify = JWTUtils.handlerJWTVerifier(token);
            String isToken = tokenVerify.get("isToken");
            if (!Boolean.parseBoolean(isToken)) {
                try {
                    Map<String, String> data = new HashMap<>();
                    data.put("errorMsg", tokenVerify.get("errorMsg"));
                    data.put("code", "401");
                    response.addHeader("Access-Control-Allow-Credentials", "true");
                    response.addHeader("Access-Control-Allow-Origin", origin);
                    response.addHeader("Access-Control-Allow-Methods", "GET, POST, DELETE, PUT");
                    response.addHeader("Access-Control-Allow-Headers", "Content-Type,X-CAF-Authorization-Token,sessionToken,X-TOKEN");
                    response.setContentType("application/json; charset=utf-8");
                    response.setStatus(401);
                    PrintWriter writer = response.getWriter();
                    writer.print(JSONObject.toJSONString(data));
                    writer.close();
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
