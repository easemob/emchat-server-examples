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
import org.glassfish.jersey.client.JerseyWebTarget;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.easemob.server.example.utils.Constants;
import com.easemob.server.example.utils.HTTPMethod;
import com.easemob.server.example.utils.JerseyUtils;
import com.easemob.server.example.utils.Roles;
import com.easemob.server.example.vo.EndPoints;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

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

	private static JsonNodeFactory factory = new JsonNodeFactory(false);

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
	public static ObjectNode getAccessToken(Map<String, Object> reqBody, String role) {
		JsonNode jsonNode = null;

		ObjectNode objectNode = factory.objectNode();

		String appKey = Constants.APPKEY;
		// check appKey format
		if (!JerseyUtils.match("[0-9a-zA-Z]+#[0-9a-zA-Z]+", appKey)) {
			LOGGER.error("Bad format of Appkey: " + appKey);

			objectNode.put("message", "Bad format of Appkey");

		}

		try {

			JerseyWebTarget webTarget = null;
			if (Roles.USER_ROLE_ORGADMIN.equals(role)) {
				webTarget = EndPoints.TOKEN_ORG_TARGET.resolveTemplate("org_name", appKey.split("#")[0])
						.resolveTemplate("app_name", appKey.split("#")[1]);
			} else if (Roles.USER_ROLE_APPADMIN.equals(role) || Roles.USER_ROLE_IMUSER.equals(role)) {
				webTarget = EndPoints.TOKEN_APP_TARGET.resolveTemplate("org_name", appKey.split("#")[0])
						.resolveTemplate("app_name", appKey.split("#")[1]);
			}

			jsonNode = JerseyUtils.sendRequest(webTarget, , null, HTTPMethod.METHOD_POST,
					null);
			if (jsonNode.get("access_token").textValue() != "") {
				resultMap.put("statusCode", "200");
				resultMap.put("access_token", jsonNode.get("access_token").textValue());
				resultMap.put("expires_in", jsonNode.get("access_token").asLong());
				jsonNode = JerseyUtils.Map2Json(resultMap);
			}

		} catch (Exception e) {

		}

		return jsonNode;
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
	public static ObjectNode mediaUpload(String accessToken, String filePath) {
		ObjectNode objectNode = factory.objectNode();

		String appKey = Constants.APPKEY;

		File file = new File(filePath);
		if (!file.exists()) {

			LOGGER.error("file: " + filePath + " is not exist!");
			objectNode.put("statusCode", "401");
			objectNode.put("message", "File or directory not found");

			return objectNode;
		}

		if (!JerseyUtils.match("[0-9a-zA-Z]+#[0-9a-zA-Z]+", appKey)) {
			LOGGER.error("Bad format of Appkey: " + appKey);

			objectNode.put("statusCode", "401");
			objectNode.put("message", "Bad format of Appkey");

			return objectNode;
		}

		try {

			JerseyWebTarget webTarget = null;
			webTarget = EndPoints.CHATFILES_TARGET.resolveTemplate("org_name", appKey.split("#")[0]).resolveTemplate(
					"app_name", appKey.split("#")[1]);

			// LOGGER.info("ready to upload : " + filePath + " to " + webTarget.);

			List<NameValuePair> headers = new ArrayList<NameValuePair>();
			headers.add(new BasicNameValuePair("restrict-access", "true"));

			objectNode = JerseyUtils.uploadFile(webTarget, file, accessToken, headers);

			/*
			 * {"action":"post", "application":"2962b340-0a3b-11e4-b21b-d3b66dbe207b","params":{}, "path":"/chatfiles",
			 * "uri" :
			 * "https://a1.easemob.com/belo/chatapp/chatfiles","entities":[{"uuid":"6ec47ab0-3491-11e4-8f22-5fe10cba652a"
			 * ,"type":"chatfile","share-secret":"bsR6ujSREeSNZ3EVLLFqVadYix_ES4pffHZfrOb4-pPseyze"}],"timestamp":
			 * 1409875996123,"duration":2,"organization":"belo","applicationName":"chatapp"}
			 */

		} catch (Exception e) {

		}

		return objectNode;
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
	public static JsonNode mediaDownload(String token, String fileUUID, String shareSecret, File localPath,
			boolean isThumbnail) {

		String appKey = Constants.APPKEY;

		Map<String, Object> resultMap = new HashMap<String, Object>();
		if (!JerseyUtils.match("[0-9a-zA-Z-_]+#[0-9a-zA-Z-_]+", appKey)) {
			LOGGER.error("Bad format of Appkey: " + appKey);

			resultMap.put("statusCode", "401");
			resultMap.put("message", "Bad format of Appkey");

			return JerseyUtils.Map2Json(resultMap);
		}

		try {
			JerseyWebTarget webTarget = null;
			webTarget = EndPoints.CHATFILES_TARGET.resolveTemplate("org_name", appKey.split("#")[0])
					.resolveTemplate("app_name", appKey.split("#")[1]).path(fileUUID);

			List<NameValuePair> headers = new ArrayList<NameValuePair>();
			headers.add(new BasicNameValuePair("share-secret", shareSecret));
			headers.add(new BasicNameValuePair("Accept", "application/octet-stream"));
			if (isThumbnail) {
				headers.add(new BasicNameValuePair("thumbnail", "true"));
			}

			JerseyUtils.downLoadFile(webTarget, token, headers, localPath);
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

		// JerseyUtils.sendRequest(reqURL, JerseyUtils.Map2Json(reqBody), token, method, headers);
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

		// JsonNode jsonNode = JerseyUtils.sendRequest(reqURL, null, token, httpMethod, headers);
		JsonNode jsonNode = new EasemobChatMessage().getAccessToken(null, "");
		return jsonNode.get("entities");
	}

	/**
	 * Main Test
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		// 获取token
		Map<String, Object> reqBody = new HashMap<String, Object>();
		reqBody.put("grant_type", "password");
		reqBody.put("username", "chatappAdmin");
		reqBody.put("password", "9876543");
		// String accessToken = getAccessToken(reqBody, Roles.USER_ROLE_APPADMIN).get("access_token").textValue();
		String accessToken = "YWMt_tsO6DQUEeSQwheELDW36wAAAUl09nrzMZbh-NkjgcmN02D6xmXfdD4S-1E";
		System.out.println(accessToken);

		// 图片文件上传
		String imageFilePath = "/home/lynch/Pictures/zjg.jpg";
		JsonNode mediaUploadNode = mediaUpload(accessToken, imageFilePath);

		// 图片文件下载
		// 文件下载后保存成下面的文件
		File localFileName = new File("/home/lynch/Pictures/zjg2.jpg");
		String shareSecretOfIamgeFile = mediaUploadNode.path("share-secret").asText();
		String UUIDOfIamgeFile = mediaUploadNode.path("uuid").asText();
		JsonNode mediaDownloadNode = mediaDownload(accessToken, UUIDOfIamgeFile, shareSecretOfIamgeFile, localFileName,
				false);

		// 语音文件上传
		String videoFilePath = "C:/Users/lynch/Music/4minute - Love Tension.mp3";
		// mediaUpload(appKey, host, accessToken, videoFilePath);

		// 缩略图下载
		File localFileNameThumbnail = new File("C:/Users/lynch/Pictures/aa.jpg");
		// mediaDownload(appKey, host, accessToken, UUIDOfIamgeFile, shareSecretOfIamgeFile, localFileNameThumbnail,
		// true);

		// 语音文件下载
		String shareSecretOfAudioFile = "uIk18A85EeSkRLkCA06Nc0GDRxusksq6pHo_uUYng9unmSkG";
		String UUIDOfAudioFile = "b88676da-0f39-11e4-a143-9bb69516d9a0";
		// mediaDownload(appKey, host, accessToken, UUIDOfAudioFile, shareSecretOfAudioFile, localFileName, false);

		// 聊天消息
		Map<String, Object> appAdminTokenReqBody = new HashMap<String, Object>();
		reqBody.put("grant_type", "password");
		reqBody.put("username", "easemobdemoadmin");
		reqBody.put("password", "thepushbox");
		// String appAdminToken = getAccessToken(appKey, appAdminTokenReqBody, Roles.USER_ROLE_APPADMIN).get("")
		// .textValue();
		// 获取最新的20条记录
		String queryString = "?ql=order+by+timestamp+desc&limit=20";
		// JsonNode messages = getChatMessages(appKey, host, appAdminToken, HTTPMethod.METHOD_GET, queryString);

		// 聊天消息 获取7天以内的消息
		String startTime = String.valueOf(System.currentTimeMillis());
		String endTime = String.valueOf(System.currentTimeMillis() - 7 * 24 * 60 * 60 * 1000);
		queryString = "ql=select * where " + startTime + " < timestamp and timestamp < " + endTime
				+ " order by timestamp desc";
		// JsonNode messagesSevenDays = getChatMessages(appKey, host, accessToken, HTTPMethod.METHOD_GET, queryString);

		// 聊天消息 分页获取数据
		queryString = "ql=order by timestamp desc&limit=20&cursor=MTYxOTcyOTYyNDpnR2tBQVFNQWdHa0FCZ0ZHczBKN0F3Q0FkUUFRYUdpdkt2ZU1FZU9vNU4zVllyT2pqUUNBZFFBUWFHaXZJUGVNRWVPMjdMRWo5b0w4dEFB";
		// JsonNode messagesPagenation = getChatMessages(appKey, host, accessToken, HTTPMethod.METHOD_GET, queryString);

	}
}
