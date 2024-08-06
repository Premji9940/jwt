package com.nt.helper;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class JwtHelper {

	@Value("${app.secret}")
	private String secret;

	// 1.generate the token
	@SuppressWarnings("deprecation")
	public String generateToken(String subject) {
		return Jwts.builder()
				
				.setSubject(subject).setIssuer("trainer by premji")
				.setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + TimeUnit.MINUTES.toMillis(1)))
				.signWith(SignatureAlgorithm.HS512, secret.getBytes()).compact();

	}

	// 2.To Read the Token
	@SuppressWarnings("deprecation")
	public Claims getClaims(String token) {
		return Jwts.parser().
				//here we must write secret.getBytes()
				setSigningKey(secret.getBytes())

				.parseClaimsJws(token).getBody();
//Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
	}

	// 3.To get Expire Date
	public Date getExpDate(String token) {
		return getClaims(token).getExpiration();
	}

	// 4.To Get Subject or user name
	public String getUserName(String token) {
		return getClaims(token).getSubject();
	}

	// 5.Validate the Expire Date of Token
	public boolean isTokenExpire(String token) {
		Date expDate = getExpDate(token);

		return expDate.before(new Date(System.currentTimeMillis()));

	}

	// 6. validate user name and data base, expire date
	public boolean validateToken(String token, String user) {
		String tokenUserName = getUserName(token);
		return (user.equals(tokenUserName) && !isTokenExpire(token));
	}
	
/*	
	 //requirement :
    public static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60;

    //    public static final long JWT_TOKEN_VALIDITY =  60;
    private String secret = "afafasfafafasfasfasfafacasdasfasxASFACASDFACASDFASFASFDAFASFASDAADSCSDFADCVSGCFVADXCcadwavfsfarvf";

    //retrieve username from jwt token
    public String getUsernameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    //retrieve expiration date from jwt token
    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }

    //for retrieveing any information from token we will need the secret key
    private Claims getAllClaimsFromToken(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }

    //check if the token has expired
    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    //generate token for user
    public String generateToken(UserDetails userDetails) {
        Map<String, Object> claims = new HashMap<>();
        return doGenerateToken(claims, userDetails.getUsername());
    }

    //while creating the token -
    //1. Define  claims of the token, like Issuer, Expiration, Subject, and the ID
    //2. Sign the JWT using the HS512 algorithm and secret key.
    //3. According to JWS Compact Serialization(https://tools.ietf.org/html/draft-ietf-jose-json-web-signature-41#section-3.1)
    //   compaction of the JWT to a URL-safe string
    private String doGenerateToken(Map<String, Object> claims, String subject) {

        return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000))
                .signWith(SignatureAlgorithm.HS512, secret).compact();
    }

    //validate token
    public Boolean validateToken(String token, UserDetails userDetails) {
        final String username = getUsernameFromToken(token);
        return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }


*/

}
