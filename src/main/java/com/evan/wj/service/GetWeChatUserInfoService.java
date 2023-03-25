package com.evan.wj.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.evan.wj.dao.WeChatUserInfoDao;
import com.evan.wj.pojo.WeChatUserInfoPojo;
import com.evan.wj.utils.JWTUtils;
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

    @Autowired
    JWTUtils jwtUtils;

    public Object getWeChatSnsapiUserinfo(String access_token, String openId) {
        Map params = new HashMap<String,Object>(3);
        params.put("access_token", access_token);
        params.put("openId", openId);
        params.put("lang", "zh_CN");
        String path = "https://api.weixin.qq.com/sns/userinfo?access_token={access_token}&openid={openId}&lang={lang}";
        try {
            Object result = customResultTypeRestTemplate.getForObject(path, Object.class,params);
            Map userInfo = (Map)result;
            System.out.println(result + "userinfo");
            System.out.println(userInfo.get("openid"));
            String openid = userInfo.get("openid").toString();
            String nickname = userInfo.get("nickname").toString();
            int sex = (int)userInfo.get("sex");
            String city = userInfo.get("city").toString();
            String province = userInfo.get("province").toString();
            String country = userInfo.get("country").toString();
            String headimgurl = userInfo.get("headimgurl").toString();
            String privilege = userInfo.get("privilege").toString();
            Object unionid = userInfo.get("unionid");
            String unionidStr;
            if ("".equals(userInfo.get("unionid")) || unionid == null){
                System.out.println("空指针");
                unionidStr = "";
            } else {
                unionidStr = userInfo.get("unionid").toString();
            }

            int isFindOpenId = weChatUserInfoDao.findUserOpenid(openid);

            System.out.println(isFindOpenId);

            if (isFindOpenId == 1) {
                Map dataResult = getDataResult(openid);
                return dataResult;
            } else {
                int isSave =  weChatUserInfoDao.saveWeChatUserInfo(openid,nickname, sex, city, province, country,headimgurl,privilege,unionidStr);
               if(isSave == 1) {
                   Map dataResult = getDataResult(openid);
                   return dataResult;
               } else {
                   return "Save user info fail";
               }
            }

        }catch (Exception exception) {
            return exception.toString();
        }
    }

    private Map getDataResult(String openid) {
        WeChatUserInfoPojo getUserInfo = weChatUserInfoDao.findUserInfo(openid);
        System.out.println(getUserInfo+"mysql user info");
        System.out.println(getUserInfo.getNickname() + "mysql返回的数据");
        String token = jwtUtils.createToken(getUserInfo);
        Map resultData = new HashMap(2);
        resultData.put("userInfo", getUserInfo);
        resultData.put("token", token);
        System.out.println(resultData+"最终返回的数据");
        return resultData;
    }
}
