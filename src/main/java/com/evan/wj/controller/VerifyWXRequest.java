package com.evan.wj.controller;

import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author SuperLee
 * @date 2022/12/11 下午5:01
 * @param signature
 * @param timestamp
 * @param nonce
 * @param echostr
 * @return
 */
@Controller
public class VerifyWXRequest {

    @CrossOrigin(allowCredentials = "true")
    @GetMapping(value = "api/wxRequest")
    public String wxRequest(
            @Param("signature") String signature,
            @Param("timestamp") String timestamp,
            @Param("nonce") String nonce,
            @Param("echostr") String echostr) {
        return "123";

    }

}
