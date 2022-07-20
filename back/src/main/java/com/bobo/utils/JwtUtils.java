package com.bobo.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;

public final class JwtUtils {

    final static String key = "boboSecret";
    final static Integer ttl = 1*60*60*1000;  //一天 24*60*60*1000
    static public String generateToken( String name ) {
        String s = Jwts.builder()
                .setHeaderParam("typ", "JWT")
                .signWith(SignatureAlgorithm.HS512, key)
                .setId(name)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis()+ttl))
                .compact();
        return s;
    }

    public static String parseToken( String token ) {       //传入token，我要用我的钥匙看看能不能打开这个toekn,
        try {
            Claims claims = Jwts.parser().setSigningKey(key).parseClaimsJws(token).getBody();  // 传入key,打得开我就把里头想要的数据返回
            return claims.getId();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}