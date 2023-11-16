package com.hygieia.app.Security;

import java.io.Serializable;
import java.util.Date;
import org.springframework.stereotype.Service;
import org.springframework.security.core.Authentication;
import io.jsonwebtoken.Jwts;
import java.security.Key;
import io.jsonwebtoken.security.Keys;

@Service
public class JWTfunctionality implements Serializable {
	public static final Key key = Keys.hmacShaKeyFor(Jwts.SIG.HS512.key().build().getEncoded());

	public String GenerateToken(Authentication authentication) {

		String username = authentication.getName();
		Date currentDate = new Date();
		Date expireDate = new Date(currentDate.getTime() + 7000);

		String token = Jwts.builder()
				.subject(username)
				.issuedAt(new Date())
				.expiration(expireDate)
				.signWith(key)
				.compact();
		System.out.println("New token :");
		System.out.println(token);
		return token;
	}


}
