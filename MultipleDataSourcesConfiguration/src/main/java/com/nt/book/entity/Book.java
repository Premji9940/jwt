package com.nt.book.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="BOOK")

public class Book {
	@Id
	@GeneratedValue
	private Integer bid;
	private String author;
	private Integer price;
	
	public Book() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Book(Integer bid, String author, Integer price) {
		super();
		this.bid = bid;
		this.author = author;
		this.price = price;
	}

	public Integer getBid() {
		return bid;
	}

	public void setBid(Integer bid) {
		this.bid = bid;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Integer getPrice() {
		return price;
	}

	public void setPrice(Integer price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "Book [bid=" + bid + ", author=" + author + ", price=" + price + "]";
	}

}
