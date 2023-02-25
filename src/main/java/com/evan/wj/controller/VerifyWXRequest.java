package com.evan.wj.controller;

import com.alibaba.fastjson.JSONObject;
import com.evan.wj.result.Result;
import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @author SuperLee
 * @date 2022/12/11 下午5:01
 */
@Controller
public class VerifyWXRequest {

    private static String token = "eladminshuiniwechat";

    @CrossOrigin(allowCredentials = "true")
    @GetMapping(value = "api/wxRequest")
    @ResponseBody
    public void wxRequest(HttpServletRequest request, HttpServletResponse response) {
        // 微信加密签名.
        String signature = request.getParameter("signature");
        // 时间戳.
        String timestamp = request.getParameter("timestamp");
        // 随机数.
        String nonce = request.getParameter("nonce");
        // 随机字符串.
        String echostr = request.getParameter("echostr");
        // 请求校验，若校验成功则原样返回echostr，表示接入成功，否则接入失败.
        System.out.println(signature);
        System.out.println(timestamp);
        System.out.println(nonce);
        System.out.println(echostr);
        response.addHeader("Accept-Language", "en,zh-CN;q=0.9,zh;q=0.8");
        response.setContentType("application/json; charset=utf-8");
        PrintWriter out = null;
        try {
            out = response.getWriter();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // 请求校验，若校验成功则原样返回echostr，表示接入成功，否则接入失败.
        if (checkSignature(signature, timestamp, nonce)) {
            out.print(echostr);
        } else {
            //
            Map<String, Object> result = new HashMap<>();
            result.put("code", 200);
            result.put("errorMsg", "数据不匹配");
            out.print(JSONObject.toJSONString(result));
        }

        out.close();
        out = null;
    }

    /**
     * 校验签名
     * @param signature 微信加密签名.
     * @param timestamp 时间戳.
     * @param nonce 随机数.
     * @return
     */
    public static boolean checkSignature(String signature, String timestamp, String nonce) {
        // 对token、timestamp、和nonce按字典排序.
        String[] paramArr = new String[] {token, timestamp, nonce};
        Arrays.sort(paramArr);

        // 将排序后的结果拼接成一个字符串.
        String content  = paramArr[0].concat(paramArr[1]).concat(paramArr[2]);

        String ciphertext = null;
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            // 对拼接后的字符串进行sha1加密.
            byte[] digest = md.digest(content.toString().getBytes());
            ciphertext = byteToStr(digest);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

        // 将sha1加密后的字符串与signature进行对比.
        return ciphertext != null ? ciphertext.equals(signature.toUpperCase()) : false;
    }

    /**
     * 将字节数组转换为十六进制字符串.
     * @param byteArray
     * @return
     */
    private static String byteToStr(byte[] byteArray) {
        String strDigest = "";
        for (int i = 0; i < byteArray.length; i++) {
            strDigest += byteToHexStr(byteArray[i]);
        }
        return strDigest;
    }

    /**
     * 将字节转换为十六进制字符串.
     * @param mByte
     * @return
     */
    private static String byteToHexStr(byte mByte) {
        char[] Digit = { '0', '1' , '2', '3', '4' , '5', '6', '7' , '8', '9', 'A' , 'B', 'C', 'D' , 'E', 'F'};
        char[] tempArr = new char[2];
        tempArr[0] = Digit[(mByte >>> 4) & 0X0F];
        tempArr[1] = Digit[mByte & 0X0F];

        String s = new String(tempArr);
        return s;
    }

}
