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

    long errcode;

    String errmsg;
}
