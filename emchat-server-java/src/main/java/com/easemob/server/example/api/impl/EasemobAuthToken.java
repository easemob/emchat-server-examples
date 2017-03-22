package com.easemob.server.example.api.impl;

import com.easemob.server.example.api.AuthTokenAPI;
import com.easemob.server.example.comm.TokenUtil;


public class EasemobAuthToken implements AuthTokenAPI{

	@Override
	public Object getAuthToken(){
		return TokenUtil.getAccessToken();
	}
}
