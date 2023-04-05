package com.evan.wj.controller;

import com.evan.wj.result.Result;
import com.evan.wj.service.AliPayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @author SuperLee
 * @date 2023/4/5 上午11:41
 */

@RestController
@CrossOrigin(allowCredentials = "true")
@RequestMapping("/api")
public class AliPayController {

    @Autowired
    AliPayService aliPayService;

    @PostMapping(value = "/comeHerePay")
    public Result comeHereAliPay(@RequestBody Map data, HttpServletResponse httpServletResponse) {
        System.out.println(data + "source");

        try {
            Object body =  aliPayService.aliPay();
            return new Result(200, "success", body);
        } catch (Exception e) {
           System.out.println(e+"errorMessage");
            return new Result(500, e.getMessage());
        }

    }
}
