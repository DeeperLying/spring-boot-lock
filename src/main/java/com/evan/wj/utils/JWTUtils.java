package com.evan.wj.utils;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.TokenExpiredException;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.evan.wj.pojo.User;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.Map;

/**
 * @author SuperLee
 * @date 2022/10/6 上午11:47
 */

@Component
public class JWTUtils {

    public static String secret="superLee";

    public String createToken(User user) {
        String token = JWT.create()
        .withIssuer("Lee")
        .withSubject("Token")
        .withAudience("client")
        .withClaim("username", user.getUsername())
        .withIssuedAt(new Date())
        .withExpiresAt(expireDate())
        .sign(Algorithm.HMAC256(secret));
        return token;
    }

    public static boolean handlerJWTVerifier(String token) {
        try {
            JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(secret)).build();
            DecodedJWT decodedJWT =  jwtVerifier.verify(token);
            return true;
        } catch (TokenExpiredException tokenExpiredException) {
            System.out.println(tokenExpiredException);
        }
        return false;
    }

    public Date expireDate() {
        long time = 30*60*1000; // 过期时间
        Date now = new Date();
        Date afterDate = new Date(now.getTime() + time);
        return afterDate;
    }
}
