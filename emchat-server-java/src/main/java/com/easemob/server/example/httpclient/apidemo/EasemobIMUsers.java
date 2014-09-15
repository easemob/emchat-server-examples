package com.easemob.server.example.httpclient.apidemo;

import java.util.Map;

/**
 * REST API Demo :用户体系集成 REST API HttpClient4.3实现
 * 
 * Doc URL: http://www.easemob.com/docs/rest/userapi
 * 
 * @author Lynch 2014-09-15
 * 
 */
public class EasemobIMUsers {

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
	public static String createNewUser(String host, String appKey, Map<String, Object> body, String token) {
		String orgName = appKey.substring(0, appKey.lastIndexOf("#"));
		String appName = appKey.substring(appKey.lastIndexOf("#") + 1);

		String rest = orgName + "/" + appName + "/users";

		String reqURL = "https://" + host + "/" + rest;
		String result = "";
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
		String result = "";

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
	public static String getAccessToken(String host, String appKey, Boolean isAdmin, Map<String, Object> postBody) {
		String orgName = appKey.substring(0, appKey.lastIndexOf("#"));
		String appName = appKey.substring(appKey.lastIndexOf("#") + 1);
		String accessToken = "";
		String rest = "management/token";
		if (!isAdmin) {
			rest = orgName + "/" + appName + "/token";
		}

		String reqURL = "https://" + host + "/" + rest;
		String result = "";

		return accessToken;
	}

}
