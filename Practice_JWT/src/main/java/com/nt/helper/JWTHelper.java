package com.nt.helper;

import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JWTHelper {

	@Value("${app.secret}")
	private String secret;
	// 1.create Token For UserName

	@SuppressWarnings("deprecation")
	public String createToken(String subject) {

		return Jwts.builder().setIssuer("prem kumar kalla").setIssuedAt(new Date(System.currentTimeMillis()))
				.setSubject(subject).signWith(SignatureAlgorithm.HS512, secret)
				.setExpiration(new Date(System.currentTimeMillis() + TimeUnit.MINUTES.toMillis(15))).compact();
	}
	// 2.Reading the Generate Token

	public Claims readToken(String token) {
		return Jwts.parserBuilder().setSigningKey(secret)

				.build().parseClaimsJws(token).getBody();
		// or

		// return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
	}

	// 3.get User Name from token
	public String getUserName(String token) {
		return readToken(token).getSubject();
	}

	// 4.Expire Date
	public Date getExpiredDate(String token) {
		return readToken(token).getExpiration();
	}

	// 5.Validating token is expired or not
	public boolean isTokenExpired(String token) {
		Date expiredDate = getExpiredDate(token);
		return expiredDate.before(new Date(System.currentTimeMillis()));

	}

	// 6.validating the user name
	public boolean validateToken(String token, String name) {
		String userName = getUserName(token);
		return (userName.equals(name) && !isTokenExpired(token));
	}
}
