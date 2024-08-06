package com.nt.response;

public class UserResponse {

	private Integer uid;
	private String name;

	public UserResponse() {
		super();
		// TODO Auto-generated constructor stub
	}

	public UserResponse(Integer uid, String name) {
		super();
		this.uid = uid;
		this.name = name;
	}

	public Integer getuid() {
		return uid;
	}

	public void setuid(Integer uid) {
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
		return "UserRequest [uid=" + uid + ", name=" + name + "]";
	}

}
