package com.evan.wj.pojo;

import lombok.Data;

/**
 * @author SuperLee
 * @date 2023/2/26 下午9:40
 */

@Data
public class GetWxUserAuthPojo {
    public GetWxUserAuthPojo() {}

    public GetWxUserAuthPojo(String accessToken,Long expiresIn,String refreshToken, String openid,String scope) {
        this.accessToken = accessToken;
        this.expiresIn = expiresIn;
        this.refreshToken = refreshToken;
        this.openid = openid;
        this.scope = scope;
    }

    public GetWxUserAuthPojo(long errcode,String errmsg) {
        this.errcode = errcode;
        this.errmsg = errmsg;
    }

    String accessToken;

    long expiresIn;

    String refreshToken;

    String openid;

    String scope;

    long errcode;

    String errmsg;
}
