package com.easemob.server.example.api.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.easemob.server.example.api.AuthTokenAPI;
import com.easemob.server.example.api.EasemobRestAPI;
import com.easemob.server.example.comm.wrapper.BodyWrapper;
import com.easemob.server.example.comm.constant.HTTPMethod;
import com.easemob.server.example.comm.helper.HeaderHelper;
import com.easemob.server.example.comm.wrapper.HeaderWrapper;
import com.easemob.server.example.comm.body.AuthTokenBody;

public class EasemobAuthToken extends EasemobRestAPI implements AuthTokenAPI{
	
	public static final String ROOT_URI = "/token";
	
	private static final Logger log = LoggerFactory.getLogger(EasemobAuthToken.class);
	
	@Override
	public String getResourceRootURI() {
		return ROOT_URI;
	}

	public Object getAuthToken(String clientId, String clientSecret) {
		String url = getContext().getSeriveURL() + getResourceRootURI();
		BodyWrapper body = new AuthTokenBody(clientId, clientSecret);
		HeaderWrapper header = HeaderHelper.getDefaultHeader();
		
		return getInvoker().sendRequest(HTTPMethod.METHOD_POST, url, header, body, null);
	}
}
