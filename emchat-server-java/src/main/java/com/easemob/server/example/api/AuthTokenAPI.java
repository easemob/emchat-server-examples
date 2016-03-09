package com.easemob.server.example.api;

public interface AuthTokenAPI{	
	Object getAuthToken(String clientId, String clientSecret);
}
