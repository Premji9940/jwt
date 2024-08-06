package com.nt.response;



public class BookResponse {

	private Integer bid;
	private String author;
	private Integer price;
	public BookResponse(Integer bid, String author, Integer price) {
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
	public BookResponse() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "BookResponse [bid=" + bid + ", author=" + author + ", price=" + price + "]";
	}
	

	
	
}
