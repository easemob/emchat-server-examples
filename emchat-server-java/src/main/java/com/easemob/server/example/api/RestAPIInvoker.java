package com.easemob.server.example.api;

import com.easemob.server.example.comm.BodyWrapper;
import com.easemob.server.example.comm.HeaderWrapper;
import com.easemob.server.example.comm.ResponseWrapper;

public interface RestAPIInvoker {
	ResponseWrapper sendRequest(String method, String url, HeaderWrapper header, BodyWrapper body);
}
