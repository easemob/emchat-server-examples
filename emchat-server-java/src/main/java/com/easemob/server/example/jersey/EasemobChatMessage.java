package com.easemob.server.example.jersey;

import java.io.File;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.easemob.server.example.utils.HTTPMethod;
import com.easemob.server.example.utils.Roles;
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
	public static String getAccessToken(String host, String appKey, Map<String, Object> reqBody, String role) {
		JsonNode jsonNode = null;
		String accessToken = "";

		// check appKey format
		if (!JerseyUtils.match("[0-9a-zA-Z]+#[0-9a-zA-Z]+", appKey)) {
			LOGGER.error("Bad format of Appkey: " + appKey);

			Map<String, Object> resultMap = new HashMap<String, Object>();
			resultMap.put("statusCode", "401");
			resultMap.put("message", "Bad format of Appkey");

			return JerseyUtils.Map2Json(resultMap).textValue();
		}

		String rest = "";
		if (Roles.USER_ROLE_ORGADMIN.equals(role)) {
			rest = "/management/token";
		} else if (Roles.USER_ROLE_APPADMIN.equals(role) || Roles.USER_ROLE_IMUSER.equals(role)) {
			rest = "/token";
		}

		String reqURL = "https://" + host + "/" + appKey.replaceFirst("#", "/") + rest;

		jsonNode = JerseyUtils.sendRequest(reqURL, JerseyUtils.Map2Json(reqBody), null, HTTPMethod.METHOD_POST, null);

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
	public static JsonNode mediaUpload(String appKey, String host, String accessToken, String filePath) {
		Map<String, Object> resultMap = new HashMap<String, Object>();

		File file = new File(filePath);

		if (!file.exists()) {

			LOGGER.error("file: " + filePath + " is not exist!");
			resultMap.put("statusCode", "401");
			resultMap.put("message", "File or directory not found");

			return JerseyUtils.Map2Json(resultMap);
		}

		if (!JerseyUtils.match("[0-9a-zA-Z]+#[0-9a-zA-Z]+", appKey)) {
			LOGGER.error("Bad format of Appkey: " + appKey);

			resultMap.put("statusCode", "401");
			resultMap.put("message", "Bad format of Appkey");

			return JerseyUtils.Map2Json(resultMap);
		}

		String reqURL = "https://" + host + "/" + appKey.replaceFirst("#", "/") + "/chatfiles";

		List<NameValuePair> headers = new ArrayList<NameValuePair>();
		headers.add(new BasicNameValuePair("restrict-access", "true"));

		LOGGER.info("ready to upload : " + filePath + " to " + reqURL);

		JsonNode sendResponse = JerseyUtils.uploadFile(reqURL, file, accessToken, headers);

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
	public static JsonNode mediaDownload(String appKey, String host, String token, String fileUUID, String shareSecret,
			File localPath, boolean isThumbnail) {

		Map<String, Object> resultMap = new HashMap<String, Object>();
		if (!JerseyUtils.match("[0-9a-zA-Z-_]+#[0-9a-zA-Z-_]+", appKey)) {
			LOGGER.error("Bad format of Appkey: " + appKey);

			resultMap.put("statusCode", "401");
			resultMap.put("message", "Bad format of Appkey");

			return JerseyUtils.Map2Json(resultMap);
		}

		String rest = appKey.replace("#", "/") + "/chatfiles/" + fileUUID;
		String reqURL = "https://" + host + "/" + rest;

		List<NameValuePair> headers = new ArrayList<NameValuePair>();
		headers.add(new BasicNameValuePair("share-secret", shareSecret));
		headers.add(new BasicNameValuePair("Accept", "application/octet-stream"));
		if (isThumbnail) {
			headers.add(new BasicNameValuePair("thumbnail", "true"));
		}

		try {
			JerseyUtils.downLoadFile(reqURL, token, headers, localPath);
		} catch (KeyManagementException e) {
			LOGGER.error("File jerseyClient error : " + e.getMessage());
			return JerseyUtils.Map2Json(resultMap);
		} catch (NoSuchAlgorithmException e) {
			LOGGER.error("File jerseyClient error : " + e.getMessage());
			return JerseyUtils.Map2Json(resultMap);
		} catch (RuntimeException e) {
			resultMap.put("statusCode", "200");
			resultMap.put("message", "File or directory not found");
			LOGGER.error("RuntimeException : " + e.getMessage());
			return JerseyUtils.Map2Json(resultMap);
		} catch (IOException e) {
			LOGGER.error("File I/O error : " + e.getMessage());
			return JerseyUtils.Map2Json(resultMap);
		}

		LOGGER.error("File download successfully .");
		resultMap.put("statusCode", "200");
		resultMap.put("message", "File or directory not found");

		return JerseyUtils.Map2Json(resultMap);
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
	public static void imageDownloadThumbnai(String appKey, String host, String token, Map<String, Object> reqBody,
			String method, String uuid) {

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
	public static JsonNode getChatMessages(String appKey, String host, String token, String httpMethod,
			String queryString) {

		String orgName = appKey.substring(0, appKey.lastIndexOf("#"));
		String appName = appKey.substring(appKey.lastIndexOf("#") + 1);
		String rest = orgName + "/" + appName + "/chatmessages" + queryString;

		String reqURL = "https://" + host + "/" + rest;

		List<NameValuePair> headers = new ArrayList<NameValuePair>();
		headers.add(new BasicNameValuePair("Content-Type", "application/json"));

		JsonNode jsonNode = JerseyUtils.sendRequest(reqURL, null, token, httpMethod, headers);
		return jsonNode.get("entities");
	}

	/**
	 * Main Test
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		String host = "a1.easemob.com";
		String appKey = "easemob-playground#test1";

		// 获取token
		Map<String, Object> reqBody = new HashMap<String, Object>();
		reqBody.put("grant_type", "password");
		reqBody.put("username", "zhangjianguo");
		reqBody.put("password", "zhangjianguo");
		String accessToken = getAccessToken(host, appKey, reqBody, Roles.USER_ROLE_APPADMIN);
		System.out.println(accessToken);

		// 图片文件上传
		String imageFilePath = "C:/Users/lynch/Pictures/b12d7b37ba15e1a87319915157cde2b0.jpg";
		mediaUpload(appKey, host, accessToken, imageFilePath);

		// 语音文件上传
		String videoFilePath = "C:/Users/lynch/Music/4minute - Love Tension.mp3";
		mediaUpload(appKey, host, accessToken, videoFilePath);

		// 图片文件下载
		File localFileName = new File("C:/Users/lynch/Pictures/a.jpg");
		String shareSecretOfIamgeFile = "RkD6AA4wEeSyjz0_iTyr_tCR-vbm7AjdlWFEXfQ6gYhDaY00";
		String UUIDOfIamgeFile = "463e890a-0e30-11e4-854d-b9fec8a2574e";
		mediaDownload(appKey, host, accessToken, UUIDOfIamgeFile, shareSecretOfIamgeFile, localFileName, false);

		// 缩略图下载
		File localFileNameThumbnail = new File("C:/Users/lynch/Pictures/aa.jpg");
		mediaDownload(appKey, host, accessToken, UUIDOfIamgeFile, shareSecretOfIamgeFile, localFileNameThumbnail, true);

		// 语音文件下载
		String shareSecretOfAudioFile = "uIk18A85EeSkRLkCA06Nc0GDRxusksq6pHo_uUYng9unmSkG";
		String UUIDOfAudioFile = "b88676da-0f39-11e4-a143-9bb69516d9a0";
		mediaDownload(appKey, host, accessToken, UUIDOfAudioFile, shareSecretOfAudioFile, localFileName, false);

		// 聊天消息
		Map<String, Object> appAdminTokenReqBody = new HashMap<String, Object>();
		reqBody.put("grant_type", "password");
		reqBody.put("username", "easemobdemoadmin");
		reqBody.put("password", "thepushbox");
		String appAdminToken = getAccessToken(host, appKey, appAdminTokenReqBody, Roles.USER_ROLE_APPADMIN);

		// 获取最新的20条记录
		String queryString = "?ql=order+by+timestamp+desc&limit=20";
		JsonNode messages = getChatMessages(appKey, host, appAdminToken, HTTPMethod.METHOD_GET, queryString);

		// 聊天消息 获取7天以内的消息
		String startTime = String.valueOf(System.currentTimeMillis());
		String endTime = String.valueOf(System.currentTimeMillis() - 7 * 24 * 60 * 60 * 1000);
		queryString = "ql=select * where " + startTime + " < timestamp and timestamp < " + endTime
				+ " order by timestamp desc";
		JsonNode messagesSevenDays = getChatMessages(appKey, host, accessToken, HTTPMethod.METHOD_GET, queryString);

		// 聊天消息 分页获取数据
		queryString = "ql=order by timestamp desc&limit=20&cursor=MTYxOTcyOTYyNDpnR2tBQVFNQWdHa0FCZ0ZHczBKN0F3Q0FkUUFRYUdpdkt2ZU1FZU9vNU4zVllyT2pqUUNBZFFBUWFHaXZJUGVNRWVPMjdMRWo5b0w4dEFB";
		JsonNode messagesPagenation = getChatMessages(appKey, host, accessToken, HTTPMethod.METHOD_GET, queryString);

	}
}
