package org.example.smart.security.util;

import java.nio.charset.StandardCharsets;
import java.util.Date;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;

import org.example.smart.security.model.CustomUserDetails;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Jwts;

@Component
public class JwtUtil {
	private final SecretKey secretKey;

	@Value("${jwt.token.access.expiration}")
	private Long accessTokenExpiration;

	@Value("${jwt.token.refresh.expiration}")
	private Long refreshTokenExpiration;

	public JwtUtil(@Value("${spring.security.secret.key}") String secretKey) {
		this.secretKey = new SecretKeySpec(secretKey.getBytes(StandardCharsets.UTF_8),
			Jwts.SIG.HS256.key().build().getAlgorithm());
	}

	public boolean validateToken(String token, UserDetails userDetails) {
		String serviceNumber = getServiceNumber(token);
		return serviceNumber.equals(userDetails.getUsername()) && isTokenExpired(token);
	}

	public boolean isTokenExpired(String token){
		return Jwts.parser()
			.verifyWith(secretKey)
			.build()
			.parseSignedClaims(token)
			.getPayload()
			.getExpiration()
			.before(new Date());
	}

	public String getServiceNumber(String token) {
		return Jwts.parser()
			.verifyWith(secretKey)
			.build()
			.parseSignedClaims(token)
			.getPayload()
			.get("serviceNumber", String.class);
	}

	public Long getUserId(String token){
		String userId = Jwts.parser()
			.verifyWith(secretKey)
			.build()
			.parseSignedClaims(token)
			.getPayload()
			.get("userId", String.class);
		return Long.parseLong(userId);
	}

	public String generateToken(String serviceNumber, Long userId, Long expired) {
		Date now = new Date();
		Date expiration = new Date(now.getTime() + expired);

		return Jwts.builder()
			.claim("serviceNumber", serviceNumber)
			.claim("userId", userId)
			.issuedAt(now)
			.expiration(expiration)
			.signWith(secretKey)
			.compact();
	}

	public String generateAccessToken(String serviceNumber, Long userId) {
		return generateToken(serviceNumber, userId, accessTokenExpiration);
	}

	public String generateRefreshToken(String serviceNumber, Long userId) {
		return generateToken(serviceNumber, userId, refreshTokenExpiration);
	}
}
