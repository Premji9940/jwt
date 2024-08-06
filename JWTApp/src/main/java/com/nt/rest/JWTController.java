package com.nt.rest;

import java.security.Principal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.nt.entity.UserData;
import com.nt.exception.TokenExpiredException;
import com.nt.helper.JwtHelper;
import com.nt.request.JWTRequest;
import com.nt.response.JWTResponse;
import com.nt.service.UserServiceImpl;

@RestController
public class JWTController {

	@Autowired
	JwtHelper helper;
	@Autowired
	AuthenticationManager manager;
	@Autowired
	private UserDetailsService userDetails;
	
	@Autowired private UserServiceImpl service;
	
	@PostMapping("/save")
	public ResponseEntity<String> save(@RequestBody UserData data){
		service.save(data);
		return new ResponseEntity<String>("User has saved",HttpStatus.OK);
	}

	@PostMapping("/log")
	public ResponseEntity<JWTResponse> login(@RequestBody JWTRequest req) {
		System.out.println("username "+req.getUsername()+"\t"+req.getPwd());
		
		// checking authentication
		doAuthenticationCredentials(req.getUsername(), req.getPwd());

		//String userName = helper.getUsernameFromToken("eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJwcmVtIiwiaXNzIjoidHJhaW5lciBieSBwcmVtamkiLCJpYXQiOjE3MDE3NzQ4NzEsImV4cCI6MTcwMTc3NTc3MX0.fxsLlJpJsPPxO5QSCycoJw_XO6J43BBe4ScVIzfuQZpcuO2yCtoj0pxXc_zY2ywHCnG3VnY3Y8hrc7pJRITwmw");
		//System.out.println("usrname from token"+userName);
		UserDetails users = userDetails.loadUserByUsername(req.getUsername());
		System.out.println(users.getUsername());
		String token = helper.generateToken(users.getUsername());

		return new ResponseEntity<JWTResponse>(new JWTResponse(users.getUsername(), token), HttpStatus.OK);

	}

	@GetMapping("/wish")
	public ResponseEntity<String> wish(Principal p) {
		
		
		return new ResponseEntity<String>("Hi, Mr "+p.getName(), HttpStatus.OK);
	}

	// helper method to do authentication
	public void doAuthenticationCredentials(String user, String pwd) {
		// this can perform authentication for credentials
		UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(user, pwd);

		try {
			manager.authenticate(auth);
		} catch (BadCredentialsException e) {

			throw new BadCredentialsException(" Invalid Username or Password  !!");
		}
	}

	// For user defined exception
	@ExceptionHandler(BadCredentialsException.class)
	public String exceptionHandler() {
		return "Credentials Invalid !!";
	}
	// For user defined exception
		@ExceptionHandler(TokenExpiredException.class)
		public String exceptionHandlerForToken() {
			return "Token Expired";
		}

}
