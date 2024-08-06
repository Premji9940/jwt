package com.nt.request;

public class JWTRequest {
	
	private String username;
	private String pwd;
	public JWTRequest() {
		super();
	}
	public JWTRequest(String username, String pwd) {
		super();
		this.username = username;
		this.pwd = pwd;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getPwd() {
		return pwd;
	}
	public void setPwd(String pwd) {
		this.pwd = pwd;
	}
	@Override
	public String toString() {
		return "JWTRequest [username=" + username + ", pwd=" + pwd + "]";
	}
	
	
}
