package com.evan.wj.pojo;

import lombok.Data;

import java.lang.reflect.Array;
import java.util.List;

/**
 * @author SuperLee
 * @date 2023/3/11 下午4:19
 */
@Data
public class WeChatUserInfoPojo {
    public WeChatUserInfoPojo() {}

    public WeChatUserInfoPojo(
            String openid,
            String nickname,
            int sex,
            String language,
            String city,
            String province,
            String country,
            String headimgurl,
            List<Object> privilege,
            String unionid) {
        this.openid =openid;
        this.nickname = nickname;
        this.sex =sex;
        this.language = language;
        this.city =city;
        this.province = province;
        this.country = country;
        this.headimgurl =headimgurl;
        this.privilege = privilege;
        this.unionid = unionid;
    }

    String openid;

    String nickname;

    int sex;

    String language;

    String city;

    String province;

    String country;

    String headimgurl;

    List<Object> privilege;

    String unionid;
}
