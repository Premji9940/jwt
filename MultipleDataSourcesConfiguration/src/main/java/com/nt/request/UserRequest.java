package com.nt.request;

public class UserRequest {

	
	private Integer uid;
	private String name;
	
	public UserRequest() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserRequest(Integer uid, String name) {
		super();
		this.uid = uid;
		this.name = name;
	}

	public Integer getId() {
		return uid;
	}

	public void setId(Integer uid) {
		this.uid = uid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "UserRequest [id=" + uid + ", name=" + name + "]";
	}
	
}
