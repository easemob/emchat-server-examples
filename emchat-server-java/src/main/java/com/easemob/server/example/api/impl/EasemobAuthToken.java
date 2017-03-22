package com.easemob.server.example.api.impl;

import com.easemob.server.example.api.AuthTokenAPI;
import com.easemob.server.example.comm.TokenUtil;
import io.swagger.client.ApiException;


public class EasemobAuthToken implements AuthTokenAPI{

	@Override
	public Object getAuthToken(){
		return TokenUtil.getAccessToken();
	}
}
