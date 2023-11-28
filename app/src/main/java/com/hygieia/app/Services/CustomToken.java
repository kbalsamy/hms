package com.hygieia.app.Services;

import java.util.Date;

import io.jsonwebtoken.Jwts;

public class CustomToken {

    public String token;
    private String key;
    

    public CustomToken(String key) {
        Date currentDate = new Date();
		Date expireDate = new Date(currentDate.getTime() + 1000 * 60 * 60 *2);
        this.key = key;
        this.token = Jwts.builder()
                .setSubject("hygieia")
                .claim("userName","hygieia")
                .signWith(io.jsonwebtoken.SignatureAlgorithm.HS256, key)
                .issuedAt(new Date())
                .setExpiration(expireDate)
                .compact();
    }

    

    public String getToken() {
        return token;
    }

    

    
}
