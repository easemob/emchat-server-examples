package com.hyphenate.server.example.api;

import com.hyphenate.server.example.comm.wrapper.BodyWrapper;
import com.hyphenate.server.example.comm.wrapper.HeaderWrapper;
import com.hyphenate.server.example.comm.wrapper.QueryWrapper;
import com.hyphenate.server.example.comm.wrapper.ResponseWrapper;

import java.io.File;

public interface RestAPIInvoker {
	ResponseWrapper sendRequest(String method, String url, HeaderWrapper header, BodyWrapper body, QueryWrapper query);
	ResponseWrapper uploadFile(String url, HeaderWrapper header, File file);
    ResponseWrapper downloadFile(String url, HeaderWrapper header);
}
