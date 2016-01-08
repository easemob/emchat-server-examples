package com.easemob.server.example.jersey.apidemo;

import org.apache.commons.lang3.StringUtils;

import com.easemob.server.example.api.AuthTokenAPI;
import com.easemob.server.example.comm.MessageTemplate;
import com.easemob.server.example.comm.ResponseWrapper;

public class EasemobAuthToken implements AuthTokenAPI{
	
	public static final String ROOT_URI = "token";

	@Override
	public String getResourceRootURI() {
		return ROOT_URI;
	}

	@Override
	public Object getAuthToken(String clientId, String clientSecret) {
		ResponseWrapper response = ResponseWrapper.getInstance();
		
		if( StringUtils.isBlank(clientId) || StringUtils.isBlank(clientSecret) ) {
			response.addError(MessageTemplate.print(MessageTemplate.BLANK_STRING_MSG, new String[]{"ClientID or ClientSecret"}));
			return response;
		}
		
		
		
		return null;
	}

}
