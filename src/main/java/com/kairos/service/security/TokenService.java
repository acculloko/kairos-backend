package com.kairos.service.security;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.kairos.domain.user.User;

@Service
public class TokenService {
	@Value("${api.security.token.secret}")
	private String secret;
	
	public String generateToken(User user) {
        return JWT.create()
                .withSubject(user.getEmail()) // `sub`
                .withClaim("name", user.getName())
                .withClaim("userId", user.getId())            // Store user ID
                .withClaim("role", user.getRole().toString())                // Store role as String
                .withIssuer("kairos")                 // Issuer
                .withIssuedAt(new Date())               // Issue time
                .withExpiresAt(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10)) // Expiration (10 hours)
                .sign(Algorithm.HMAC256(secret));   // Sign token
    }
	
	public String validateToken(String token) {
		try {
			Algorithm algorithm = Algorithm.HMAC256(secret);
			return JWT.require(algorithm)
					.withIssuer("kairos")
					.build()
					.verify(token)
					.getSubject();
		} catch (JWTVerificationException exception) {
			return "";
		}
	}
	
	private Instant genExpirationDate() {
		return LocalDateTime.now().plusHours(2).toInstant(ZoneOffset.of("-03:00"));
	}

}
