package com.easemob.server.api;

import com.easemob.server.comm.wrapper.BodyWrapper;
import com.easemob.server.comm.wrapper.HeaderWrapper;
import com.easemob.server.comm.wrapper.QueryWrapper;
import com.easemob.server.comm.wrapper.ResponseWrapper;

import java.io.File;

public interface RestAPIInvoker {
	ResponseWrapper sendRequest(String method, String url, HeaderWrapper header, BodyWrapper body, QueryWrapper query);
	ResponseWrapper uploadFile(String url, HeaderWrapper header, File file);
    ResponseWrapper downloadFile(String url, HeaderWrapper header, QueryWrapper query);
}
