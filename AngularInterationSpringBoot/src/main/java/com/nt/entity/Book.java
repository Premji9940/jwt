package com.nt.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

@Entity
public class Book {
	
	@Id
	@GeneratedValue
	private Integer bid;
	private String book_name;
	private String book_author;
	private Double book_price;
	
	public Integer getBid() {
		return bid;
	}

	public void setBid(Integer bid) {
		this.bid = bid;
	}

	public String getBook_name() {
		return book_name;
	}

	public Book() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Book(Integer bid, String book_name, String book_author, Double book_price) {
		super();
		this.bid = bid;
		this.book_name = book_name;
		this.book_author = book_author;
		this.book_price = book_price;
	}

	public void setBook_name(String book_name) {
		this.book_name = book_name;
	}

	public String getBook_author() {
		return book_author;
	}

	public void setBook_author(String book_author) {
		this.book_author = book_author;
	}

	public Double getBook_price() {
		return book_price;
	}

	public void setBook_price(Double book_price) {
		this.book_price = book_price;
	}

	@Override
	public String toString() {
		return "Book [bid=" + bid + ", book_name=" + book_name + ", book_author=" + book_author + ", book_price="
				+ book_price + "]";
	}

	
}
