package com.example.XyhubAuthentication.security;

public class RefreshTokenResponse {

	
	private String apiKey;
	
	private String exp;

	public String getApiKey() {
		return apiKey;
	}

	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}

	public String getExp() {
		return exp;
	}

	public void setExp(String exp) {
		this.exp = exp;
	}

	public RefreshTokenResponse(String apiKey, String exp) {
		super();
		this.apiKey = apiKey;
		this.exp = exp;
	}
	
	
	
}
