package com.nt;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.nt.helper.JWTHelper;

@Component
public class TestRunner implements CommandLineRunner{
	
	@Autowired private JWTHelper helper;

	@Override
	public void run(String... args) throws Exception {
		String createToken = helper.createToken("prem kumar");
		System.out.println(createToken);
		
		String userName = helper.getUserName(createToken);
		System.out.println(userName);
		System.out.println(helper.getExpiredDate(createToken)+" "+helper.readToken(createToken).getIssuedAt());
		System.out.println(helper.isTokenExpired(createToken));
		System.out.println(helper.validateToken(createToken, userName));
	}
	
	

}
