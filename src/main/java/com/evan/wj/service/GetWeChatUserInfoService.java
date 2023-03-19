package com.evan.wj.service;

import com.alibaba.fastjson.JSONObject;
import com.evan.wj.dao.WeChatUserInfoDao;
import com.evan.wj.pojo.WeChatUserInfoPojo;
import com.evan.wj.utils.RestTemplateConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

/**
 * @author SuperLee
 * @date 2023/3/11 下午1:09
 */
@Service
public class GetWeChatUserInfoService {
    @Autowired
    RestTemplateConfig restTemplateConfig;

    @Autowired
    RestTemplate customResultTypeRestTemplate;

    @Autowired
    WeChatUserInfoDao weChatUserInfoDao;

    public void getWeChatSnsapiUserinfo(String access_token, String openId) {
        System.out.println(access_token+"ppp++++++"+openId);

        Map params = new HashMap<String,Object>(3);
        params.put("access_token", access_token);
        params.put("openId", openId);
        params.put("lang", "zh_CN");
        String path = "https://api.weixin.qq.com/sns/userinfo?access_token={access_token}&openid={openId}&lang={lang}";
        try {
            String result = restTemplateConfig.customRestTemplate.getForObject(path, String.class,params);
            System.out.println(result+"=======success");
            JSONObject resultObj = JSONObject.parseObject(result);
            System.out.println(resultObj.get("openid")+"===========");
            String openid = resultObj.get("openid").toString();
            String nickname = resultObj.get("nickname").toString();
            int sex = (int)resultObj.get("sex");
            String city = resultObj.get("city").toString();
            String province = resultObj.get("province").toString();
            String country = resultObj.get("country").toString();
            String headimgurl = resultObj.get("headimgurl").toString();
            String privilege = resultObj.get("privilege").toString();
            Object unionid = resultObj.get("unionid");
            String unionidStr;
            if ("".equals(resultObj.get("unionid")) || unionid == null){
                System.out.println("空指针");
                unionidStr = "";
            } else {
                unionidStr = resultObj.get("unionid").toString();
            }
           int isSave =  weChatUserInfoDao.saveWeChatUserInfo(openid,nickname, sex, city, province, country,headimgurl,privilege,unionidStr);
           System.out.println(isSave + "=================save");
        }catch (HttpClientErrorException httpClientErrorException) {
            System.out.println(httpClientErrorException.getResponseBodyAsString());
        }
    }
}
