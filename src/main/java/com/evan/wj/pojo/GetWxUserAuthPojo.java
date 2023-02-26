package com.evan.wj.pojo;

import lombok.Data;

/**
 * @author SuperLee
 * @date 2023/2/26 下午9:40
 */

@Data
public class GetWxUserAuthPojo {
    String access_token;

    String expires_in;

    String refresh_token;

    String openid;

    String scope;

    Number errcode;

    String errmsg;
}
