package com.nt.response;

public class JWTResponse {
	
	private String user;
	private String token;
	
	
	public JWTResponse() {
		super();
	}


	public JWTResponse(String user, String token) {
		super();
		this.user = user;
		this.token = token;
	}


	public String getUser() {
		return user;
	}


	public void setUser(String user) {
		this.user = user;
	}


	public String getToken() {
		return token;
	}


	public void setToken(String token) {
		this.token = token;
	}


	@Override
	public String toString() {
		return "JWTResponse [user=" + user + ", token=" + token + "]";
	}
	

}
