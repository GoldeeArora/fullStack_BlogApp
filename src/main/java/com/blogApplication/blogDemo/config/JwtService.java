package com.blogApplication.blogDemo.config;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
@Service
public class JwtService {
    private static final String SECRET_KEY = "294A404E635266556A576E5A7234753778214125442A472D4B6150645367566B";

	public String extractUsername(String token) {

		return extractClaim(token, Claims::getSubject);
	}
	public <T> T extractClaim(String token,Function<Claims, T> claimResolver)
	{
		final Claims claims = extractAllClaims(token);
		return claimResolver.apply(claims);
	}
	private Claims extractAllClaims(String token){
		return Jwts.parser().setSigningKey(getSignInKey()).parseClaimsJws(token).getBody();
		}
	private Key getSignInKey() {
		byte[] keyBytes = Decoders.BASE64.decode(SECRET_KEY);
		return Keys.hmacShaKeyFor(keyBytes);
	
	}
	public String generateToken(UserDetails userDetails)
	{
		return generateToken(new HashMap<>(),userDetails);
	}
	public String generateToken(
			Map<String, Object> extractClaims,
			UserDetails userDetails
			)
	{
		return Jwts
				.builder()
				.setClaims(extractClaims)
				.setSubject(userDetails.getUsername())
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 1000*60*24))
				.signWith(SignatureAlgorithm.HS256,getSignInKey())
				.compact();
	}
	
	public boolean isTokenValid(String token,UserDetails userDetails)
	{
		final String username = extractUsername(token);
		return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
	}
	
	private boolean isTokenExpired(String token)
	{
		return extractExpiration(token).before(new Date());
	}
	private Date extractExpiration(String token)
	{
		return extractClaim(token, Claims::getExpiration);
	}

}
