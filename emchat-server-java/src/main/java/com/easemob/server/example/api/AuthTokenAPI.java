package com.easemob.server.example.api;

public interface AuthTokenAPI extends RestAPI{	
	Object getAuthToken(String clientId, String clientSecret);
}
