package com.easemob.server.example.jax.rs;

import net.sf.json.JSONObject;

import org.apache.log4j.Logger;

/**
 * EasemobRESTAPI Demo
 * 
 * @author Lynch
 * 
 */
public class EasemobRESTSSLAPI {

	private static Logger logger = Logger.getLogger(HttpUtils.class);

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
	public static JSONObject createNewUser(String host, String appKey, JSONObject body, String token) {

		String orgName = appKey.substring(0, appKey.lastIndexOf("#"));
		String appName = appKey.substring(appKey.lastIndexOf("#") + 1);

		String rest = orgName + "/" + appName + "/users";

		logger.error("url:" + host + rest);
		String reqURL = "https://" + host + "/" + rest;
		String result = HttpsUtils.sendSSLRequest(reqURL, token, body.toString(),
				HttpsUtils.Method_POST);

		return JSONObject.fromObject(result);
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
	public static JSONObject deleteUser(String host, String appKey, String id, String token) {

		String orgName = appKey.substring(0, appKey.lastIndexOf("#"));
		String appName = appKey.substring(appKey.lastIndexOf("#") + 1);

		String rest = orgName + "/" + appName + "/users/" + id;

		logger.error("url:" + host + rest);
		String reqURL = "https://" + host + "/" + rest;
		String result = HttpsUtils.sendSSLRequest(reqURL, token, null, HttpsUtils.Method_DELETE);
		return JSONObject.fromObject(result);
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
			JSONObject postBody) {
		String accessToken = "";
		String orgName = appKey.substring(0, appKey.lastIndexOf("#"));
		String appName = appKey.substring(appKey.lastIndexOf("#") + 1);

		String rest = "management/token";
		if (!isAdmin) {
			rest = orgName + "/" + appName + "/token";
		}

		logger.error("url: " + "https://" + host + "/" + rest);

		String reqURL = "https://" + host + "/" + rest;
		String result = HttpsUtils.sendSSLRequest(reqURL, null, postBody.toString(),
				HttpsUtils.Method_POST);
		JSONObject jsonObject = JSONObject.fromObject(result);

		if (null == jsonObject.get("error")) {
			accessToken = (String) jsonObject.get("access_token");
			logger.error("accessToken: " + accessToken);
		} else {
			logger.error("get accessToken failured");
		}

		return accessToken;
	}

	public static void main(String[] args) {
		String host = "a1.easemob.com";
		String appKey = "easemob-playground#test1";

		// 获取IM用户token
		JSONObject getIMAccessTokenPostBody = new JSONObject();
		getIMAccessTokenPostBody.put("grant_type", "password");
		getIMAccessTokenPostBody.put("username", "testuser1");
		getIMAccessTokenPostBody.put("password", "testuser1");
		String imToken = EasemobRESTSSLAPI.getAccessToken(host, appKey, false,
				getIMAccessTokenPostBody);
		System.out.println(imToken);

		// 获取管理员token
		JSONObject getAccessTokenPostBody = new JSONObject();
		getAccessTokenPostBody.put("grant_type", "password");
		getAccessTokenPostBody.put("username", "zhangjianguo");
		getAccessTokenPostBody.put("password", "zhangjianguo");
		// String adminToken = EasemobRESTSSLAPI.getAccessToken(host, appKey, true,
		// getAccessTokenPostBody);
		// System.out.println(adminToken);

		// 创建用户
		JSONObject createNewUserPostBody = new JSONObject();
		createNewUserPostBody.put("username", "testuser2");
		createNewUserPostBody.put("password", "testuser2");
		createNewUserPostBody.put("addr", "BJFS");
		String adminToken = "YWMt1dg3rAaLEeSeLyOsOrWKZAAAAUc5mRF9trZgseW8nbEiRbTuEzm6sqlzizc";
		// EasemobRESTSSLAPI.createNewUser(host, appKey, createNewUserPostBody, adminToken);

		// 删除用户
		String id = "testuser2";
		EasemobRESTSSLAPI.deleteUser(host, appKey, id, adminToken);
	}
}
