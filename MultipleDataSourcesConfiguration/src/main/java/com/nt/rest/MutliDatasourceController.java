package com.nt.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nt.request.BookRequest;
import com.nt.request.UserRequest;
import com.nt.response.BookResponse;
import com.nt.response.UserResponse;
import com.nt.service.ServiceImpl;

@RestController
public class MutliDatasourceController {
	@Autowired private ServiceImpl service;
	
	@GetMapping("/book")
	public BookResponse saveBook() {
		return service.saveBook(new BookRequest(1,"JAVA",450));
	}
	
	@GetMapping("/user")
	public UserResponse saveUser() {
		return service.saveUser(new UserRequest(100, "premji"));
	}

}
