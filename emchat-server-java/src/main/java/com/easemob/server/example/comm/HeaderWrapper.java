package com.easemob.server.example.comm;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.core.MediaType;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

public class HeaderWrapper {
	
	private static final String HEADER_CONTENT_TYPE = "Content-Type";
	
	private static final String HEADER_AUTH = "Authorization";
	
	private List<NameValuePair> headers = new ArrayList<NameValuePair>();
	
	public static HeaderWrapper newInstance() {
		return new HeaderWrapper();
	}
	
	public HeaderWrapper addHeader(String key, String value) {
		headers.add(new BasicNameValuePair(key, value));
		return this;
	}
	
	public HeaderWrapper addJsonContentHeader() {
		headers.add(new BasicNameValuePair(HEADER_CONTENT_TYPE, MediaType.APPLICATION_JSON));
		return this;
	}
	
	public HeaderWrapper addAuthorization(String token) {
		headers.add(new BasicNameValuePair(HEADER_AUTH, "Bearer " + token));
		return this;
	}

	public List<NameValuePair> getHeaders() {
		return headers;
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		for( NameValuePair header : headers ) {
			sb.append("[").append(header.getName()).append(":").append(header.getValue()).append("] ");
		}
		
		return sb.toString();
	}
	
}
