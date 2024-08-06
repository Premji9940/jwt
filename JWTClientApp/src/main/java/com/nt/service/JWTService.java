package com.nt.service;

import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.nt.cons.AppConstant;
import com.nt.request.JwtRequest;
import com.nt.response.JwtResponse;

@Service
public class JWTService {

	RestTemplate r = new RestTemplate();
	HttpHeaders header = new HttpHeaders();
	public JwtResponse executeProvider(JwtRequest req) {
		ResponseEntity<JwtResponse> res = r.postForEntity(AppConstant.LOGIN_ENDPOIN, req, JwtResponse.class);
		//

		return res.getBody();
	}

	public String getMsg(String token) {
		System.out.println(AppConstant.HI_ENDPOIN);
		
		return Reuse(AppConstant.WISH_ENDPOIN,token);

	}
	public String getHi(String token) {
		
		
		return Reuse(AppConstant.HI_ENDPOIN,token);
	
	}

	
	public String Reuse(String Url,String token) {
		
		header.add(AppConstant.AUTHORISATION, AppConstant.BEARER+token);
		HttpEntity<String> entity = new HttpEntity<String>(header);
		ResponseEntity<String> res = r.exchange(Url, HttpMethod.GET, entity, String.class);
		String result = res.getBody();
		return result;
	
		
	}
}
