package com.nt.response;


public class BookResponse {
	
	private Integer bid;
	private String book_name;
	private String book_author;
	private Double book_price;
	public BookResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	public BookResponse(String book_name, String book_author, Double book_price) {
		super();
		this.book_name = book_name;
		this.book_author = book_author;
		this.book_price = book_price;
	}
	public BookResponse(Integer bid, String book_name, String book_author, Double book_price) {
		super();
		this.bid = bid;
		this.book_name = book_name;
		this.book_author = book_author;
		this.book_price = book_price;
	}
	public String getBook_name() {
		return book_name;
	}
	public void setBook_name(String book_name) {
		this.book_name = book_name;
	}
	public Integer getbid() {
		return bid;
	}
	public void setbid(Integer bid) {
		this.bid = bid;
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
		return "BookResponse [bid=" + bid + ", book_name=" + book_name + ", book_author=" + book_author
				+ ", book_price=" + book_price + "]";
	}	

}
