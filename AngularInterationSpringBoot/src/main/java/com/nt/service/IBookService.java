package com.nt.service;

import java.util.List;

import org.springframework.data.domain.Page;

import com.nt.entity.Book;
import com.nt.exception.BookNotFoundException;
import com.nt.request.BookRequest;
import com.nt.response.BookResponse;

public interface IBookService {

	Integer saveUpdate(BookRequest req);

	List<BookResponse> getBooks();
	
	Page<Book> getBooksWithPage(int page,String direction,String field);
	Integer delete(Integer id);
	BookResponse getOneBook(Integer id) throws BookNotFoundException;
	boolean bookAvailableOrNot(Integer id);
	
	
	
}
