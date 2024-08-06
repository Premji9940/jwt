package com.nt.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nt.entity.Book;
import com.nt.exception.BookNotFoundException;
import com.nt.request.BookRequest;
import com.nt.response.BookResponse;
import com.nt.service.BookServiceImpl;

@RestController
@RequestMapping("/book")
@CrossOrigin
public class BookController {

	@Autowired
	private BookServiceImpl service;

	// 1.save
	
	@PostMapping("/save")
	public ResponseEntity<Integer> save(@RequestBody BookRequest req) {
		return new ResponseEntity<Integer>(service.saveUpdate(req), HttpStatus.CREATED);

	}

	// 2.get Books
	@GetMapping("/books")
	public ResponseEntity<List<BookResponse>> getBooks() {
		return new ResponseEntity<List<BookResponse>>(service.getBooks(), HttpStatus.OK);
	}
	// 2.get Books with pagination
		@GetMapping("/books/{page}/{direction}/{field}")
		public Page<Book> getBooks(@PathVariable int page,@PathVariable String direction,@PathVariable String field) {
			
			return service.getBooksWithPage(page,direction,field);
		}

	// 3.Delete Book
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Integer> delete(@PathVariable("id") Integer id) {
		return new ResponseEntity<Integer>(service.delete(id), HttpStatus.OK);

	}

	// 4.update
	@PutMapping("/update")
	public ResponseEntity<Integer> update( @RequestBody BookRequest req)throws BookNotFoundException {
		
		return new ResponseEntity<Integer>(service.saveUpdate(req), HttpStatus.OK);

	}

	// 5.one Book
	@GetMapping("/book/{id}")
	public ResponseEntity<BookResponse> oneBook(@PathVariable("id") Integer id) throws BookNotFoundException {

		return new ResponseEntity<BookResponse>(service.getOneBook(id), HttpStatus.OK);

	}
	

}
