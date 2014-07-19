package com.easemob.server.example.jax.rs.httpclient;

import java.util.HashMap;
import java.util.Map;

/**
 * REST API Demo : 用户管理REST API HttpClient4.3实现
 * 
 * Doc URL: http://developer.easemob.com/docs/emchat/rest/userapi.html
 * 
 * @author Lynch 2014-07-09
 * 
 */
public class EasemobUserAPI {

	/**
	 * 创建用户
	 * 
	 * @param host
	 *            IP或者域名
	 * @param port
	 *            端口
	 * @param appKey
	 *            easemob-demo#chatdemo
	 * @param postBody
	 *            封装了用户属性的json对象
	 * @param token
	 *            admin级别token
	 * @return
	 */
	public static String createNewUser(String host, String appKey, Map<String, Object> body,
			String token) {
		String orgName = appKey.substring(0, appKey.lastIndexOf("#"));
		String appName = appKey.substring(appKey.lastIndexOf("#") + 1);

		String rest = orgName + "/" + appName + "/users";

		String reqURL = "https://" + host + "/" + rest;
		String result = HttpsUtils.sendSSLRequest(reqURL, token, HttpsUtils.Map2Json(body),
				HttpsUtils.Method_POST);
		return result;
	}

	/**
	 * 删除用户
	 * 
	 * @param host
	 *            IP或者域名
	 * @param port
	 *            端口
	 * @param appKey
	 *            easemob-demo#chatdemo
	 * @param id
	 *            usename or uuid
	 * @param token
	 *            admin级别token
	 * @return
	 */
	public static String deleteUser(String host, String appKey, String id, String token) {

		String orgName = appKey.substring(0, appKey.lastIndexOf("#"));
		String appName = appKey.substring(appKey.lastIndexOf("#") + 1);

		String rest = orgName + "/" + appName + "/users/" + id;

		String reqURL = "https://" + host + "/" + rest;
		String result = HttpsUtils.sendSSLRequest(reqURL, token, null, HttpsUtils.Method_DELETE);

		return result;
	}

	/**
	 * 获取token
	 * 
	 * @param host
	 *            IP或者域名
	 * @param port
	 *            端口
	 * @param appKey
	 *            easemob-demo#chatdemo
	 * @param isAdmin
	 *            org管理员token true, IM用户token false
	 * @param postBody
	 *            POST请求体
	 * @return
	 */
	public static String getAccessToken(String host, String appKey, Boolean isAdmin,
			Map<String, Object> postBody) {
		String orgName = appKey.substring(0, appKey.lastIndexOf("#"));
		String appName = appKey.substring(appKey.lastIndexOf("#") + 1);
		String accessToken = "";
		String rest = "management/token";
		if (!isAdmin) {
			rest = orgName + "/" + appName + "/token";
		}

		String reqURL = "https://" + host + "/" + rest;
		String result = HttpsUtils.sendSSLRequest(reqURL, null, HttpsUtils.Map2Json(postBody),
				HttpsUtils.Method_POST);
		Map<String, String> resultMap = HttpsUtils.Json2Map(result);

		accessToken = resultMap.get("access_token");

		return accessToken;
	}

	public static void main(String[] args) {
		String host = "a1.easemob.com";
		// String appKey = "easemob-playground#test1";
		String appKey = "kuaiju#kuaiju";

		// 获取IM用户token
		Map<String, Object> getIMAccessTokenPostBody = new HashMap<String, Object>();
		getIMAccessTokenPostBody.put("grant_type", "password");
		getIMAccessTokenPostBody.put("username", "testuser1");
		getIMAccessTokenPostBody.put("password", "testuser1");
		// String imToken = EasemobUserAPI.getAccessToken(host, appKey, false,
		// getIMAccessTokenPostBody);
		// System.out.println(imToken);

		// 获取管理员token
		Map<String, Object> getAccessTokenPostBody = new HashMap<String, Object>();
		getAccessTokenPostBody.put("grant_type", "password");
		getAccessTokenPostBody.put("username", "zhangjianguo");
		getAccessTokenPostBody.put("password", "zhangjianguo");
		// String adminToken = EasemobRESTSSLAPI.getAccessToken(host, appKey, true,
		// getAccessTokenPostBody);
		// System.out.println(adminToken);

		// 创建用户
		Map<String, Object> createNewUserPostBody = new HashMap<String, Object>();
		createNewUserPostBody.put("username", "testuser1");
		createNewUserPostBody.put("password", "testuser1");
		createNewUserPostBody.put("addr", "BJFS");
		// String adminToken = "YWMtVWWpUAhTEeSQZieN8wRN4QAAAUdFQji_f3OeR3Me_gfQIW5zWOa6smv6Wyg";
		String adminToken = "YWMtWcC3uAvyEeSYSjNGzdq6RAAAAUdc_YU36rPpaIvmtTQmtogqmPV9sDq92_Q";
		EasemobUserAPI.createNewUser(host, appKey, createNewUserPostBody, adminToken);

		// 删除用户
		String id = "testuser2";
		EasemobUserAPI.deleteUser(host, appKey, id, adminToken);
	}
}
