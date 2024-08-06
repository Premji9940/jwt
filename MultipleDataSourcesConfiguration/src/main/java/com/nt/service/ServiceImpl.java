package com.nt.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nt.book.entity.Book;
import com.nt.book.repo.IBookRepo;
import com.nt.request.BookRequest;
import com.nt.request.UserRequest;
import com.nt.response.BookResponse;
import com.nt.response.UserResponse;
import com.nt.user.entity.User;
import com.nt.user.repo.IUserRepo;

@Service
public class ServiceImpl {
	
	@Autowired private IBookRepo brepo;
	
	@Autowired private IUserRepo urepo;
	
	
	public BookResponse saveBook(BookRequest req) {
		Book book=new Book();
		BeanUtils.copyProperties(req, book);		
		Book save = brepo.save(book);
		BookResponse response=new BookResponse();		
		BeanUtils.copyProperties(save,response);
		System.out.println(save+"\t\t"+response);
		return response;		
	}
	public UserResponse saveUser(UserRequest req) {
		User user=new User();
		BeanUtils.copyProperties(req, user);		
		User save = urepo.save(user);
		UserResponse response=new UserResponse();		
		BeanUtils.copyProperties(save,response );
		return response;		
	}

}
