package com.example.XyhubAuthentication.security;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;
//
//import com.example.XyhubAuthentication.model.XyhubUserDetails;
import com.example.XyhubCommon.constants.AuthConstants;
//import com.example.XyhubCommon.constants.XyhubUserDetails;
import com.example.XyhubCommon.constants.XyhubUserDetails;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtTokenUtil implements Serializable {

	private static final long serialVersionUID = -2550185165626007488L;

	/** token format: hour * minutes * seconds */
	public static final long JWT_TOKEN_VALIDITY = 3 * 60 * 60;

	public static final long CONSUMER_JWT_TOKEN = 168 * 60 * 60;

//	public static final long CONSUMER_JWT_TOKEN =  3 * 60;

	@Value("${jwt.secret}")
	private String secret;

	// retrieve username from jwt token
	public String getUsernameFromToken(String token) {
		return getClaimFromToken(token, Claims::getSubject);
	}

	// retrieve expiration date from jwt token
	public Date getExpirationDateFromToken(String token) {
		return getClaimFromToken(token, Claims::getExpiration);
	}

	public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = getAllClaimsFromToken(token);
		return claimsResolver.apply(claims);
	}

	// for retrieveing any information from token we will need the secret key
	private Claims getAllClaimsFromToken(String token) {
		return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
	}

	// check if the token has expired
	private Boolean isTokenExpired(String token) {
		final Date expiration = getExpirationDateFromToken(token);
		return expiration.before(new Date());
	}

	// generate token for user
	public String generateToken(XyhubUserDetails user, UserDetails userDetails) {
		Map<String, Object> claims = new HashMap<>();
		claims.put(AuthConstants.USERNAME, user.getUsername());
		claims.put(AuthConstants.NAME, user.getName());
		claims.put(AuthConstants.USER_ID, user.getId());
		claims.put(AuthConstants.PERMISSIONS, user.getPermission());
		claims.put(AuthConstants.USER_ROLE, user.getUserRole());

//		claims.put(AuthConstants., claims)
//		claims.put(AuthConstants.SCOPE_ID, user.getScopeId());
//		claims.put(AuthConstants.USER_ROLE, user.getUserRole());
//		claims.put(AuthConstants.USER_ID, user.getId());
		return doGenerateToken(claims, userDetails.getUsername());
	}

	// while creating the token -
	// 1. Define claims of the token, like Issuer, Expiration, Subject, and the ID
	// 2. Sign the JWT using the HS512 algorithm and secret key.
	// 3. According to JWS Compact
	// Serialization(https://tools.ietf.org/html/draft-ietf-jose-json-web-signature-41#section-3.1)
	// compaction of the JWT to a URL-safe string
	private String doGenerateToken(Map<String, Object> claims, String subject) {

		return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
				.signWith(SignatureAlgorithm.HS512, secret).compact();
	}

	public Object getNamedClaimFromToken(String token, String claim) {
		return getClaimFromToken(token, claims -> claims.get(claim));
	}

	public String generateConumerToken(XyhubUserDetails user) {
		Map<String, Object> claims = new HashMap<>();

//		claims.put(AuthConstants.NAME, user.getName());
//		claims.put(AuthConstants.USER_ROLE, user.getUserRole());
//		claims.put(AuthConstants.SCOPE_ID, user.getScopeId());
		// claims.put(AuthConstants.USER_ID, user.getId());
		return doConsumerGenerateToken(claims, user.getUsername());
	}

	private String doConsumerGenerateToken(Map<String, Object> claims, String subject) {

		return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + (CONSUMER_JWT_TOKEN * 1000)))
				.signWith(SignatureAlgorithm.HS512, secret).compact();
	}

	public Claims getAllClaimsFromTokenForRF(String token) {
		Claims claims;
		try {
			claims = Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();

		}

		catch (ExpiredJwtException e) {
			return e.getClaims();

		}

		catch (Exception e) {

			throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Token is expired or invalid");
		}

		return claims;
	}

	// validate token
	public Boolean validateToken(String token, UserDetails userDetails) {
		final String username = getUsernameFromToken(token);
		return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
	}
}
