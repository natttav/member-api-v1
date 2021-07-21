package com.nattav.model;

public class JWTRequest {

	private String token;

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public JWTRequest(String token) {
		super();
		this.token = token;
	}
	
}
