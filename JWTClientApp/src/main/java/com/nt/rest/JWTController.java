package com.nt.rest;



import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nt.request.JwtRequest;
import com.nt.response.JwtResponse;
import com.nt.service.JWTService;


@RestController
public class JWTController {
	
	@Autowired private JWTService service;
	
	public static String token;
	@GetMapping("/get")
	public ResponseEntity<JwtResponse> getData(){
		JwtRequest req =new JwtRequest();
		req.setUsername("premji");
		req.setPassword("9940");
		req.setRoles(Set.of("Admin"));
		JwtResponse res = service.executeProvider(req);
		token=res.getToken();
		System.out.println(token);

		return new ResponseEntity<JwtResponse>(res,HttpStatus.OK);
	}

	
	@GetMapping("/wish")
	public ResponseEntity<String> getMsg(){
		String msg = service.getMsg(token);
		return new ResponseEntity<String>(msg,HttpStatus.OK);
	}
	@GetMapping("/hi")
	public ResponseEntity<String> getHi(){
		String msg = service.getHi(token);
		return new ResponseEntity<String>(msg,HttpStatus.OK);
	}
	

}
