package com.easemob.server.example.jax.rs.jersey;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.core.MediaType;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * REST API Demo : 聊天消息导出REST API Jersey2.9实现
 * 
 * Doc URL: http://developer.easemob.com/docs/emchat/rest/chatmessage.html
 * 
 * @author Lynch 2014-07-12
 * 
 */
public class EasemobChatMessage {

	private static Logger LOGGER = LoggerFactory.getLogger(EasemobChatMessage.class);

	/**
	 * Obtain access token
	 * 
	 * @param host
	 *            IP or Domain
	 * @param appKey
	 *            orgName#appName
	 * @param reqBody
	 *            mapData for httpReqeust Body
	 * @param isAdmin
	 *            true orgAdmin token ; false IM user token
	 * @return
	 */
	public static String getAccessToken(String host, String appKey, Map<String, Object> reqBody,
			boolean isAdmin) {
		String orgName = appKey.substring(0, appKey.lastIndexOf("#"));
		String appName = appKey.substring(appKey.lastIndexOf("#") + 1);
		String accessToken = "";

		String rest = "management/token";
		if (!isAdmin) {
			rest = orgName + "/" + appName + "/token";
		}

		String reqURL = "https://" + host + "/" + rest;

		String result = JerseyUtils.sendRequest(reqURL, reqBody, null, JerseyUtils.Method_POST,
				null);

		if ((JerseyUtils.Json2Map(result)).get("access_token") == "") {
			accessToken = (String) JerseyUtils.Json2Map(result).get("access_token");
		}

		return accessToken;
	}

	/**
	 * 图片语音文件上传
	 * 
	 * TODO 考虑连接超时咋办
	 * 
	 * @param appKey
	 * @param host
	 * @param accessToken
	 * @param filePath
	 */
	public static void mediaUpload(String appKey, String host, String accessToken, String filePath) {

		File file = new File(filePath);

		if (!file.exists()) {

			LOGGER.error("file: " + filePath + " is not exist!");

			return;
		}

		String orgName = appKey.substring(0, appKey.lastIndexOf("#"));
		String appName = appKey.substring(appKey.lastIndexOf("#") + 1);
		String rest = orgName + "/" + appName + "/chatfiles";

		String reqURL = "https://" + host + "/" + rest;

		List<NameValuePair> headers = new ArrayList<NameValuePair>();
		headers.add(new BasicNameValuePair("restrict-access", "true"));
		headers.add(new BasicNameValuePair("Content-Type", MediaType.MULTIPART_FORM_DATA));

		Map<String, Object> reqBody = new HashMap<String, Object>();
		reqBody.put("file", file.toString());

		LOGGER.info("ready to upload : " + filePath + " to " + reqURL);

		JerseyUtils.sendRequest(reqURL, reqBody, accessToken, JerseyUtils.Method_POST, headers);
	}

	/**
	 * 图片语音文件下载
	 * 
	 * @param appKey
	 * @param host
	 * @param token
	 * @param reqBody
	 * @param method
	 * @param uuid
	 */
	public static void mediaDownload(String appKey, String host, String token,
			Map<String, Object> reqBody, String method, String uuid) {

		String orgName = appKey.substring(0, appKey.lastIndexOf("#"));
		String appName = appKey.substring(appKey.lastIndexOf("#") + 1);
		String rest = orgName + "/" + appName + "/chatfiles/" + uuid;

		String reqURL = "https://" + host + "/" + rest;

		List<NameValuePair> headers = new ArrayList<NameValuePair>();
		String shareSecret = "DRGM8OZrEeO1vafuJSo2IjHBeKlIhDp0GCnFu54xOF3M6KLr";
		headers.add(new BasicNameValuePair("share-secret", shareSecret));
		headers.add(new BasicNameValuePair("Accept", "application/octet-stream"));

		JerseyUtils.sendRequest(reqURL, reqBody, token, method, headers);
	}

	/**
	 * 图片语音文件下载
	 * 
	 * @param appKey
	 * @param host
	 * @param token
	 * @param reqBody
	 * @param method
	 * @param uuid
	 */
	public static void mediaDownloadThumbnai(String appKey, String host, String token,
			Map<String, Object> reqBody, String method, String uuid) {

		String orgName = appKey.substring(0, appKey.lastIndexOf("#"));
		String appName = appKey.substring(appKey.lastIndexOf("#") + 1);
		String rest = orgName + "/" + appName + "/chatfiles/" + uuid;

		String reqURL = "https://" + host + "/" + rest;

		List<NameValuePair> headers = new ArrayList<NameValuePair>();
		String shareSecret = "DRGM8OZrEeO1vafuJSo2IjHBeKlIhDp0GCnFu54xOF3M6KLr";
		headers.add(new BasicNameValuePair("thumbnail", "true"));
		headers.add(new BasicNameValuePair("share-secret", shareSecret));
		headers.add(new BasicNameValuePair("Accept", "application/octet-stream"));

		JerseyUtils.sendRequest(reqURL, reqBody, token, method, headers);
	}

	/**
	 * 获取聊天消息
	 * 
	 * @param appKey
	 * @param host
	 * @param token
	 * @param reqBody
	 * @param method
	 * @param uuid
	 */
	public static void getChatMessages(String appKey, String host, String token,
			Map<String, Object> reqBody, String method, String uuid) {

		String orgName = appKey.substring(0, appKey.lastIndexOf("#"));
		String appName = appKey.substring(appKey.lastIndexOf("#") + 1);
		String rest = orgName + "/" + appName + "/chatfiles/" + uuid;

		String reqURL = "https://" + host + "/" + rest;

		List<NameValuePair> headers = new ArrayList<NameValuePair>();
		String shareSecret = "DRGM8OZrEeO1vafuJSo2IjHBeKlIhDp0GCnFu54xOF3M6KLr";
		headers.add(new BasicNameValuePair("thumbnail", "true"));
		headers.add(new BasicNameValuePair("share-secret", shareSecret));
		headers.add(new BasicNameValuePair("Accept", "application/octet-stream"));

		JerseyUtils.sendRequest(reqURL, reqBody, token, method, headers);
	}

	public static void main(String[] args) {
		String host = "a1.easemob.com";
		String appKey = "chatdemoui#chatdemoui";

		// 获取token
		Map<String, Object> reqBody = new HashMap<String, Object>();
		String accessToken = getAccessToken(host, appKey, reqBody, true);
		System.out.println(accessToken);

		// 图片语音文件上传
		String filePath = "";
		mediaUpload(appKey, host, accessToken, filePath);

		// 图片语音文件下载
		mediaDownload(appKey, host, accessToken, reqBody, JerseyUtils.Method_GET, "");

		// 图片缩略图下载
		mediaDownloadThumbnai(appKey, host, accessToken, reqBody, JerseyUtils.Method_GET, "");

		// 聊天消息 获取最新的20条记录
		getChatMessages(appKey, host, accessToken, reqBody, JerseyUtils.Method_GET, "");

		// 聊天消息 获取某个时间段内的消息
		getChatMessages(appKey, host, accessToken, reqBody, JerseyUtils.Method_GET, "");

		// 聊天消息 分页获取数据
		getChatMessages(appKey, host, accessToken, reqBody, JerseyUtils.Method_GET, "");
	}
}
