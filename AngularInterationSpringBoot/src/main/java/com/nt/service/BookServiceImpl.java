package com.nt.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.print.attribute.standard.PageRanges;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.nt.entity.Book;
import com.nt.exception.BookNotFoundException;
import com.nt.repo.BookRepository;
import com.nt.request.BookRequest;
import com.nt.response.BookResponse;

@Service
public class BookServiceImpl implements IBookService {

	@Autowired
	private BookRepository repo;

	// 1.save Book
	@Override
	public Integer saveUpdate(BookRequest req) {
		Book book = new Book();
		BeanUtils.copyProperties(req, book);
		return repo.save(book).getBid();
	}

	// 2.Get All Books
	@Override
	public List<BookResponse> getBooks() {
		List<BookResponse> ll = new ArrayList<>();
		// Getting All Books
		List<Book> books = repo.findAll();
		// Converting Book to BookResponse
		books.forEach((data) -> {
			BookResponse br = new BookResponse();
			BeanUtils.copyProperties(data, br);
			ll.add(br);
		});
		return ll;
	}

	// 3.Delete Book
	@Override
	public Integer delete(Integer id) {
		repo.deleteById(id);
		return id;
	}

	@Override
	public BookResponse getOneBook(Integer id) throws BookNotFoundException {
		Optional<Book> b = repo.findById(id);
		if (b.isEmpty()) {
			throw new BookNotFoundException("Book is Not Found");
		} else {
			Book book = b.get();
			BookResponse res = new BookResponse();
			BeanUtils.copyProperties(book, res);
			return res;
		}
	}

	@Override
	public boolean bookAvailableOrNot(Integer id) {
		return repo.existsById(id);
	}

	@Override
	public Page<Book> getBooksWithPage(int page,String direction,String sortField) {
		Sort sort=	 direction.equalsIgnoreCase(Sort.Direction.ASC.name())?Sort.by(sortField).ascending():Sort.by(sortField).descending();
	
		Pageable of = PageRequest.of(page-1, 5,sort);
		
	
		Page<Book> p = repo.findAll(of);
	
		
		return p;
	}

}
