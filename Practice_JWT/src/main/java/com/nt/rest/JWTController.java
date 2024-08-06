package com.nt.rest;

import java.security.Principal;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nt.helper.JWTHelper;
import com.nt.request.JwtRequest;
import com.nt.response.JwtResponse;

@RestController
public class JWTController {
	@Autowired private AuthenticationManager manager;
	@Autowired private JWTHelper jwt;
	
	@PostMapping("/login")
	public ResponseEntity<JwtResponse> authenticate(@RequestBody JwtRequest req){
		System.out.println("JWTController.authenticate()");
		JwtResponse res=new JwtResponse();
		
		UsernamePasswordAuthenticationToken authenticate=
				new UsernamePasswordAuthenticationToken(req.getUsername(), req.getPassword());
		//user.getRoles().stream().map(role -> new SimpleGrantedAuthority(role)).collect(Collectors.toList())
		manager.authenticate(authenticate);
		//creating token
		String createToken = jwt.createToken(req.getUsername());
		res.setToken(createToken);
		res.setUsername(jwt.getUserName(createToken));
		
		return new ResponseEntity<JwtResponse>(res, HttpStatus.OK);
	}
	
	@GetMapping("/wish")
	public ResponseEntity<String> getWish(Principal p){
		
		return new ResponseEntity<String>("HI !! mr "+p.getName()+" Have a good day",HttpStatus.OK);
		
	}
	
	@GetMapping("/hi")
public ResponseEntity<String> getHi(Principal p){
		
		return new ResponseEntity<String>("HI !! mr "+p.getName(),HttpStatus.OK);
		
	}

}
