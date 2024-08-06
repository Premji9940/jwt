package com.nt.book.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nt.book.entity.Book;

public interface IBookRepo extends JpaRepository<Book, Integer> {

}
