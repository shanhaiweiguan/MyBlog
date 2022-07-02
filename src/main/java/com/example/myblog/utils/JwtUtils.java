package com.example.myblog.utils;

import io.jsonwebtoken.Jwt;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class JwtUtils {

    //设置密钥
    private static final String jwtToken = "shanhaiweiguan!@#";

    //生成token
    public static String createToken(String username){
        Map<String, Object> bobyData = new HashMap<>();
        bobyData.put("username", username);
        JwtBuilder jwtBuilder = Jwts.builder()
                .signWith(SignatureAlgorithm.HS256, jwtToken) //签发密钥
                .setClaims(bobyData)//设置boby数据
                .setIssuedAt(new Date())//设置生成时间
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 60));//过期时间设置为一个小时
        String token = jwtBuilder.compact();
        return token;
    }

    //
    public static Map<String, Object> checkToken(String token){

        try {
            Jwt parse = Jwts.parser().setSigningKey(jwtToken).parse(token);
            return (Map<String, Object>) parse.getBody();
        }catch(Exception e){
            e.printStackTrace();
        }
        return null;
    }

    public static void main(String[] args) {
        String token = JwtUtils.createToken("guochenrui");
        System.out.println(token);
        Map<String, Object> map = JwtUtils.checkToken(token);
        System.out.println(map.get("username"));
    }

}
