package com.evan.wj.pojo;

import lombok.Data;

/**
 * @author SuperLee
 * @date 2023/2/26 下午9:40
 */

@Data
public class GetWxUserAuthPojo {
    public GetWxUserAuthPojo() {}

    public GetWxUserAuthPojo(String access_token,Long expiresIn,String refreshToken, String openid,String scope) {
        this.access_token = access_token;
        this.expires_in = expiresIn;
        this.refresh_token = refreshToken;
        this.openid = openid;
        this.scope = scope;
    }

    public GetWxUserAuthPojo(long errcode,String errmsg) {
        this.errcode = errcode;
        this.errmsg = errmsg;
    }

    String access_token;

    long expires_in;

    String refresh_token;

    String openid;

    String scope;

    long errcode;

    String errmsg;
}
