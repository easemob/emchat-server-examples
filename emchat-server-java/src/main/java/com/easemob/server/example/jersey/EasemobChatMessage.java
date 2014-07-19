package com.easemob.server.example.jersey;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.JsonNode;

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
			String role) {
		JsonNode jsonNode = null;
		String accessToken = "";

		// check appKey format
		if (!JerseyUtils.match("http(s)?://([\\w-]+\\.)+[\\w-]+(/[\\w- ./?%&=]*)?", appKey)) {
			LOGGER.error("Bad format of Appkey: " + appKey);

			Map<String, Object> resultMap = new HashMap<String, Object>();
			resultMap.put("statusCode", "401");
			resultMap.put("message", "Bad format of Appkey");

			return JerseyUtils.Map2Json(resultMap).textValue();
		}

		String rest = "";
		if (JerseyUtils.USER_ROLE_ORGADMIN.equals(role)) {
			rest = "/management/token";
		} else if (JerseyUtils.USER_ROLE_APPADMIN.equals(role)
				|| JerseyUtils.USER_ROLE_IMUSER.equals(role)) {
			rest = "/token";
		}

		String reqURL = "https://" + host + "/" + appKey.replaceFirst("#", "/") + rest;

		jsonNode = JerseyUtils.sendRequest(reqURL, JerseyUtils.Map2Json(reqBody), null,
				JerseyUtils.METHOD_POST, null);

		if (jsonNode.get("access_token").textValue() != "") {
			accessToken = jsonNode.get("access_token").textValue();
		}

		return accessToken;
	}

	/**
	 * 图片/语音文件上传
	 * 
	 * 
	 * @param appKey
	 * @param host
	 * @param accessToken
	 * @param filePath
	 */
	public static JsonNode mediaUpload(String appKey, String host, String accessToken,
			String filePath) {
		Map<String, Object> resultMap = new HashMap<String, Object>();

		File file = new File(filePath);

		if (!file.exists()) {

			LOGGER.error("file: " + filePath + " is not exist!");
			resultMap.put("statusCode", "401");
			resultMap.put("message", "Bad format of Appkey");

			return JerseyUtils.Map2Json(resultMap);
		}

		if (!JerseyUtils.match("[0-9a-zA-Z](+)#[0-9a-zA-Z](+)", appKey)) {
			LOGGER.error("Bad format of Appkey: " + appKey);

			resultMap.put("statusCode", "401");
			resultMap.put("message", "Bad format of Appkey");

			return JerseyUtils.Map2Json(resultMap);
		}

		String reqURL = "https://" + host + "/" + appKey.replaceFirst("#", "/") + "/chatfiles";

		List<NameValuePair> headers = new ArrayList<NameValuePair>();
		headers.add(new BasicNameValuePair("restrict-access", "true"));

		LOGGER.info("ready to upload : " + filePath + " to " + reqURL);

		JsonNode sendResponse = JerseyUtils.sendRequestObject(reqURL, file, accessToken,
				JerseyUtils.METHOD_POST, headers);

		return sendResponse;
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

		String rest = appKey.replace("#", "/") + "/chatfiles/" + uuid;

		String reqURL = "https://" + host + "/" + rest;

		List<NameValuePair> headers = new ArrayList<NameValuePair>();
		String shareSecret = "DRGM8OZrEeO1vafuJSo2IjHBeKlIhDp0GCnFu54xOF3M6KLr";
		headers.add(new BasicNameValuePair("share-secret", shareSecret));
		headers.add(new BasicNameValuePair("Accept", "application/octet-stream"));

		JerseyUtils.sendRequest(reqURL, JerseyUtils.Map2Json(reqBody), token, method, headers);
	}

	/**
	 * 下载缩略图
	 * 
	 * @param appKey
	 * @param host
	 * @param token
	 * @param reqBody
	 * @param method
	 * @param uuid
	 */
	public static void imageDownloadThumbnai(String appKey, String host, String token,
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

		JerseyUtils.sendRequest(reqURL, JerseyUtils.Map2Json(reqBody), token, method, headers);
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

		JerseyUtils.sendRequest(reqURL, JerseyUtils.Map2Json(reqBody), token, method, headers);
	}

	public static void main(String[] args) {
		String host = "a1.easemob.com";
		String appKey = "belo#myapptest";

		// 获取token
		Map<String, Object> reqBody = new HashMap<String, Object>();
		reqBody.put("grant_type", "password");
		reqBody.put("username", "apptestAdmin");
		reqBody.put("password", "123456789");
		String accessToken = getAccessToken(host, appKey, reqBody, JerseyUtils.USER_ROLE_APPADMIN);
		System.out.println(accessToken);

		// 图片语音文件上传
		String imageFilePath = "C:/Users/lynch/Pictures/b12d7b37ba15e1a87319915157cde2b0.jpg";
		String videoFilePath = "C:/Users/lynch/Music/4minute - Love Tension.mp3";
		mediaUpload(appKey, host,
				"YWMtT4Ydzg4sEeSM0kFz7ZriMAAAAUdrlM99rGDiPdW9fvxXvYki0n5NwTXYYSM", imageFilePath);
		mediaUpload(appKey, host,
				"YWMtT4Ydzg4sEeSM0kFz7ZriMAAAAUdrlM99rGDiPdW9fvxXvYki0n5NwTXYYSM", videoFilePath);

		// 图片语音文件下载
		mediaDownload(appKey, host, accessToken, reqBody, JerseyUtils.METHOD_GET, "");

		// 图片缩略图下载
		imageDownloadThumbnai(appKey, host, accessToken, reqBody, JerseyUtils.METHOD_GET, "");

		// 聊天消息 获取最新的20条记录
		getChatMessages(appKey, host, accessToken, reqBody, JerseyUtils.METHOD_GET, "");

		// 聊天消息 获取某个时间段内的消息
		getChatMessages(appKey, host, accessToken, reqBody, JerseyUtils.METHOD_GET, "");

		// 聊天消息 分页获取数据
		getChatMessages(appKey, host, accessToken, reqBody, JerseyUtils.METHOD_GET, "");

	}
}
