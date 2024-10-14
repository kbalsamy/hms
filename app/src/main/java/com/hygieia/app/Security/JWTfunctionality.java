package com.hygieia.app.Security;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import org.springframework.stereotype.Service;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import io.jsonwebtoken.*;

import java.security.Key;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.HttpServletRequest;

@Service
public class JWTfunctionality implements Serializable {

	
	private JwtParser jwtParser;

	
	private final String TOKEN_HEADER = "Authorization";
    
    private final String TOKEN_PREFIX = "Bearer ";
    private final Key key = Keys.hmacShaKeyFor(Jwts.SIG.HS512.key().build().getEncoded());

   

    public JWTfunctionality() {
      
        this.jwtParser = Jwts.parser().setSigningKey(key).build();
    }



	public String GenerateToken(Authentication authentication,String role,List<String> permissions) {

		String username = authentication.getName();
		Date currentDate = new Date();
		Date expireDate = new Date(currentDate.getTime() + 1000 * 60 * 60 *2);

		String token = Jwts.builder()
                .setSubject(username)
				.claim("userName",username)
				.claim("role",role)
				.claim("permissions", permissions)
				.issuedAt(new Date())
				.setExpiration(expireDate)
				.signWith(key)
				.compact();
		System.out.println("New token :");
		System.out.println(token);
		return token;
	}

	private Claims parseJwtClaims(String token) {
        return jwtParser.parseClaimsJws(token).getBody();
    }

	public Claims resolveClaims(HttpServletRequest req) {
        try {
            String token = resolveToken(req);
            if (token != null) {
                return parseJwtClaims(token);
            }
            return null;
        } catch (ExpiredJwtException ex) {
            req.setAttribute("expired", ex.getMessage());
            throw ex;
        } catch (Exception ex) {
            req.setAttribute("invalid", ex.getMessage());
            throw ex;
        }
    }

	public String resolveToken(HttpServletRequest request) {

        String bearerToken = request.getHeader(TOKEN_HEADER);
        if (bearerToken != null && bearerToken.startsWith(TOKEN_PREFIX)) {
            return bearerToken.substring(TOKEN_PREFIX.length());
        }
        return null;
    }

	public boolean validateClaims(Claims claims) throws AuthenticationException {
        try {
            return claims.getExpiration().after(new Date());
        } catch (Exception e) {
            throw e;
        }
    }

    public String getEmail(Claims claims) {
        return claims.getSubject();
    }

    private List<String> getRoles(Claims claims) {
        return (List<String>) claims.get("roles");
    }





}
