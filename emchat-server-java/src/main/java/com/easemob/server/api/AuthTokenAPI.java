package com.easemob.server.api;

public interface AuthTokenAPI{	
	Object getAuthToken(String clientId, String clientSecret);
}
