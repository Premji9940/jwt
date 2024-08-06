package com.nt.request;

public class BookRequest {

	private Integer bid;
	private String author;
	private Integer price;
	
	public BookRequest(Integer bid, String author, Integer price) {
		super();
		this.bid = bid;
		this.author = author;
		this.price = price;
	}

	public BookRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Integer getBid() {
		return bid;
	}

	public void setBid(Integer bid) {
		this.bid = bid;
	}

	public String getauthor() {
		return author;
	}

	public void setauthor(String author) {
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
		return "BookRequest [bid=" + bid + ", author=" + author + ", price=" + price + "]";
	}
	
}
