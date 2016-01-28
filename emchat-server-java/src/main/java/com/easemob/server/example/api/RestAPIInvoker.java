package com.easemob.server.example.api;

import com.easemob.server.example.comm.BodyWrapper;
import com.easemob.server.example.comm.HeaderWrapper;
import com.easemob.server.example.comm.QueryWrapper;
import com.easemob.server.example.comm.ResponseWrapper;

import java.io.File;

public interface RestAPIInvoker {
	ResponseWrapper sendRequest(String method, String url, HeaderWrapper header, BodyWrapper body, QueryWrapper query);
	ResponseWrapper uploadFile(String url, HeaderWrapper header, File file);
    ResponseWrapper downloadFile(String url, HeaderWrapper header, QueryWrapper query);
}
