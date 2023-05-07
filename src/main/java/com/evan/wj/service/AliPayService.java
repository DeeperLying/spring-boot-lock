package com.evan.wj.service;

import com.alibaba.fastjson.JSONObject;
import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.CertAlipayRequest;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.request.AlipayTradeWapPayRequest;
import com.alipay.api.response.AlipayTradeWapPayResponse;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * @author SuperLee
 * @date 2023/4/5 下午2:08
 */

@Service
public class AliPayService {
    private final String appId = "2021000122673476";
    private final String merchantPrivateKey = "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCDmU6MwPrB/yJDgsOL5dBxrFEptsOVLIt3YIUWKyoEiVFxKD7r1Mhe1TQ9iRt+vRFkPUG0phGARmfVTbqFDjy73fkEfMHNmL+qNXUiPVHe7XUGEFCjpmSvESw9U4Sml2/hNEB48XV9TVTOHL1Wtma8EA6vdqRFNibA6zfC/eCp5cu2nRx9OLH3H5RjmhZw4t+31R2nUls9BLizIJslXx4ZTFoeLlMknc5kOxZwEEDwT0t3r6SJn8w4ksqsq88iDh+QE8Ojcq0OLXV3dQS9zb+JjsPL8T5PD6Njr3v6IeeHPZOBTxO2gvYOKQuHhnCfALNeIi1CczJ0inRUY5ds3Sv3AgMBAAECggEAcTrGmFsvJx5kF9x6bnPFnxkaGJ81t7PFKGj2+fUXySFx3sA3K2lBIj+mHFBNRQZe4XXHhCzjoBB0JNclZniyjx7VtSO3CTSlrUkjyNskB4EfEKGXEMgJl4xaGUY0O7bPP+Fam/V+Ftsj3dhinSqCSMw0JosZmqeWPqXkq7UPyn4+RyxYslE+hMD1Tub+8PKxtnUytd5a7z78W8j6/rAIt3TLf1kJ/3NWfzix3nvmw0oMOE5oJsTTU0VrPo9jjaq8lzWIYq1e5Vln31kV5T58MVnZbFshQmkyO8Yakuym91d/0jYL/NlDTk4Lyi4N7TcxtYqQummdGlMOs/cwJ2G9iQKBgQDJfdFNSL5+KjdLyW14JF8LxfpmdMYkw48QlEID+HatOsGiS/d2IGu4xnusIOlpkL7Kzyya0g8M64Y51xmWPVTDQz2lIE5z5HR4IC8PYNsZ1+BZMAJ3DnKbUwtVRuppMH5B2L0TeXSOo8Bl0rTGJvrsvMC145zwvo5vaMr15T+pawKBgQCnMx0n4TPOuWkwZEHUVmU53Vka/2Mp+lofwuvoM0zNblKOJPATZcUgk9c+qEVW1HVer0OugoMosImWZ6FZQOjpA6zr57b//STf5pyKy/N2YnSY7ukOJkHbw855mlihdHfIfKgSrz8+UWbFmkLnaT/Lyx+w7Dr3ojw1aSk7BVtupQKBgCmOvrWO9GM3N9J0yBRIhCX8fmnVWezJPN+xhHAnNesCvF9KBc8iXrVskqNs4ld4mDFiS5PfEWWbykJxwABBxBLF8pf1MtkqXG9OTAaC9o28/bPHeCqwGyk4FpVPHBf3/YJ9PnoeydCFq7AUpl5cOdLMF38sY/kvhFcsKPhQgTHjAoGAb2ohGIz5r9xfWs7ECxGTHI8CLHpYAwX/fkCuYKmmmRHV00CL9fEP/dhmSytoIScsGu8hY602ZyvUUzQ1ETuuuG8pkH10tXpjUwvh7p9fWdjbp3k0NaiKpD4DXYbbXqM0mpVujhzXQNiPakeUI44/47Y0JPoVhSRHiLPNtLWqKr0CgYBmVUPmh4jCLxNzgobk4ZzaRlQegdiBAbM0SUy5llRULS6Gk+ZNKg0OBeD4NokMdMJqKVnOg9owwwtoMI3GTNHu4XlfpoTrOXGnJwUZNybBPEEhSZhubjg8D/nVMY8tcDahWY04+HnAdxNkAViGYRlsVDSQa2ao4qKUcPOVVI4OJA==";
    private final String alipayPublicKey = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAjel3/uH6gHMspkzwnFU2Z8a9B5dq49+/L6JNjQyvVPyIEAS+XBfQ3gLIhrMeX0GE1yMMUig79Lk/HYNNYqQyrdzRnlcLoQe13cpkrCtj2+nMIosB4RP5maJUweBKfpTo0P5fwMlgCpHGM5NiyzKJz/LwrupM7T42KxFX2Tvn8qMgFJ+sAZB4+lWU+CyOqjQYj8v21sWzlCYZmPAvZVitkpcBfEIYA3ZABgXk/dfq7aKR40TrLQttOYoR96tT0GIL30QCaljLfmm9ZdJNJKKYMDxkZJ8knEtDfTcdswpxNA/R+3ehIvKwQLnviCrYf8ECgcpK0g+Yqm4JMD1WnSe+fQIDAQAB";

    private String notifyUrl;
    private String returnUrl;

    private String signType;
    private String charset;
    private String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";

    public Object aliPay(Map shop) throws AlipayApiException {
        int total_amount = Integer.parseInt(shop.get("sale").toString());
        String subject = shop.get("goods_title").toString();
        System.out.println(subject + "=====>");

        AlipayClient alipayClient = new DefaultAlipayClient(gatewayUrl, appId, merchantPrivateKey, "json","UTF-8", alipayPublicKey,"RSA2");

        AlipayTradeWapPayRequest request = new AlipayTradeWapPayRequest();
        request.setNotifyUrl("");
        request.setReturnUrl("");
        JSONObject bizContent = new JSONObject();
        bizContent.put("out_trade_no", getOutTradeNo());
        bizContent.put("total_amount", total_amount);
        bizContent.put("subject", subject);
        bizContent.put("product_code", "QUICK_WAP_WAY");

        request.setBizContent(bizContent.toString());
        AlipayTradeWapPayResponse response = alipayClient.pageExecute(request);
        if(response.isSuccess()){
            System.out.println(response + "view look left right");
            System.out.println(response.getBody() + "view look left right");
            System.out.println("调用成功");
            return response.getBody();
        } else {
            System.out.println("调用失败");
            return response.getBody();
        }
    }

    private String getOutTradeNo() {
       String out_trade_no =  UUID.randomUUID().toString();
       System.out.println(out_trade_no.replaceAll("-",""));
       String id = out_trade_no.replaceAll("-","");
       return id;
    }

}
