package com.hygieia.app.Security;

import java.io.Serializable;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JWTfunctionality implements Serializable {
    @Value("${hygieia.jwt.key}")
	private String Secret_key;

	private SecretKey getKey() {
		return Keys.hmacShaKeyFor(Decoders.BASE64.decode(Secret_key));
	}

	public String extractusername(String token) {
		return extractClaims(token, Claims::getSubject);
	}

	public Date extractExpiration(String token) {
		return (Date) extractClaims(token, Claims::getExpiration);
	}

	public <T> T extractClaims(String token, Function<Claims, T> claimResolver) {
		final Jws<Claims> claims = extractAllClaims(token);
		return claimResolver.apply(claims.getPayload());
	}

	public Jws<Claims> extractAllClaims(String token) {
		return Jwts.parser().verifyWith(getKey()).build().parseSignedClaims(token);
	}

	private Boolean IsTokenexpired(String token) {
		return extractExpiration(token).before(new Date());

	}

	public String GenerateToken(UserDetails userdetails) {
		Map<String, Object> claims = new HashMap<>();
		return createToken(claims, userdetails.getUsername());
	}

	private String createToken(Map<String, Object> claims, String subject) {

		JwtBuilder test = Jwts.builder().claims(claims).subject(subject).issuedAt(new Date(System.currentTimeMillis()))
				.expiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
				.signWith(getKey());
		return test.compact();
	}

	public Boolean validateToken(String token, UserDetails userDetails) {
		final String username = extractusername(token);
		return (username.equals(userDetails.getUsername()) && !IsTokenexpired(token));
	}
    
}
