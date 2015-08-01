package com.easemob.server.example.httpclient.vo;

import java.net.URL;

import com.easemob.server.example.comm.Constants;
import com.easemob.server.example.httpclient.utils.HTTPClientUtils;

/**
 * HTTPClient EndPoints
 * 
 * @author Lynch 2014-09-15
 *
 */
public interface EndPoints {

	public static final URL TOKEN_APP_URL = HTTPClientUtils.getURL(Constants.APPKEY.replace("#", "/") + "/token");

	public static final URL USERS_URL = HTTPClientUtils.getURL(Constants.APPKEY.replace("#", "/") + "/users");

	public static final URL MESSAGES_URL = HTTPClientUtils.getURL(Constants.APPKEY.replace("#", "/") + "/messages");

	public static final URL CHATMESSAGES_URL = HTTPClientUtils.getURL(Constants.APPKEY.replace("#", "/")
			+ "/chatmessages");

	public static final URL CHATGROUPS_URL = HTTPClientUtils.getURL(Constants.APPKEY.replace("#", "/") + "/chatgroups");

	public static final URL CHATFILES_URL = HTTPClientUtils.getURL(Constants.APPKEY.replace("#", "/") + "/chatfiles");

}
