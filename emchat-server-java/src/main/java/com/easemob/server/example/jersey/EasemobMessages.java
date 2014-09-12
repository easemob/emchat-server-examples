package com.easemob.server.example.jersey;

import java.io.File;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.glassfish.jersey.client.JerseyWebTarget;

import com.easemob.server.example.utils.Constants;
import com.easemob.server.example.utils.JerseyUtils;
import com.easemob.server.example.vo.EndPoints;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * REST API Demo: 发送消息REST API Jersey2.9实现
 * 
 * Doc URL: http://developer.easemob.com/docs/emchat/rest/sendmessage.html
 * 
 * @author Lynch 2014-07-12
 * 
 */
public class EasemobMessages {

	public static void main(String[] args) throws KeyManagementException, NoSuchAlgorithmException, IOException {

		String appkey = "easemob-playground#test1";
		//
		String token = "YWMtWJ-etggfEeSZ4pfPDaJNRgAAAUdD7YReiepTP1RGOrMvCLvIib2jK8tyVfE";

		// 检测某个用户是否在线
		// String checkUser="ceshi1";
		// try {
		// getUserStatus(appkey, token, checkUser);
		// } catch (Exception e) {
		// e.printStackTrace();
		// }

		// 获取token
		// try {
		// token=getAccessToken(appkey, "admin", "123456");
		// System.out.println("token:"+token);
		//
		// } catch (KeyManagementException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// } catch (NoSuchAlgorithmException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// } catch (IOException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }

		// 发送Text消息
		// List<String> toUsernames=new ArrayList<String>();
		// toUsernames.add("ceshi1");
		// toUsernames.add("ceshi2");
		//
		// String fromUser="ceshi";
		// String txtContent="Hello,It is a test message!";
		//
		// Map<String, String> sendResult = sendTextMessage(appkey, token,
		// txtContent, fromUser, toUsernames);
		// for (String toUsername : toUsernames) {
		//
		// String isSuccess = sendResult.get(toUsername);
		// if (isSuccess.equals("success")) {
		// System.out.println("send message to " + toUsername
		// + " success!");
		// } else {
		// System.out.println("send message to " + toUsername + " fail!");
		// }
		// }

	}

	/**
	 * 检测用户是否在线
	 * 
	 * @param token
	 * @param user
	 * @return
	 */
	public static boolean getUserStatus(String appKey, String token, String targetUserName) throws Exception {

		String HTTP_URL = "https://a1.easemob.com/" + appKey.replaceFirst("#", "/") + "/users/" + targetUserName
				+ "/status";

		JerseyWebTarget target = JerseyUtils.getJerseyClient(true).target(HTTP_URL).path("/{targetUserName}/status")
				.resolveTemplate("targetUserName", targetUserName);
		Response response = target.request(MediaType.APPLICATION_JSON_TYPE).header("Authorization", "Bearer " + token)
				.buildGet().invoke();
		String result = response.readEntity(String.class);
		ObjectMapper objectMapper = new ObjectMapper();
		String content = objectMapper.readTree(result).get("data").get(targetUserName).asText();
		System.out.println(result);
		if (content.equals("online")) {
			return true;
		} else if (content.equals("offline")) {
			return false;
		}
		return false;
	}

	/**
	 * 发送文本消息
	 * 
	 * @param textContent
	 *            消息内容
	 * @param username
	 *            发送人
	 * @return true发送成功 false 发送失败
	 * @throws IOException
	 * @throws NoSuchAlgorithmException
	 * @throws KeyManagementException
	 */
	public static Map<String, String> sendTextMessage(String appKey, String token, String textContent, String fromUser,
			List<String> toUsernames) throws IOException, KeyManagementException, NoSuchAlgorithmException {
		String httpUrl = "https://a1.easemob.com/" + appKey.replaceFirst("#", "/") + "/messages";
		Map<String, Object> body = new HashMap<String, Object>();
		body.put("target_type", "users");
		body.put("target", toUsernames);
		Map<String, String> msgBody = new HashMap<String, String>();
		msgBody.put("type", "txt");
		msgBody.put("msg", "hello from rest");
		body.put("msg", msgBody);
		body.put("from", "ceshi");
		Map<String, String> extBody = new HashMap<String, String>();
		extBody.put("attr1", "v1");
		extBody.put("attr2", "v2");
		body.put("ext", extBody);
		ObjectMapper mapper = new ObjectMapper();

		JerseyWebTarget target = JerseyUtils.getJerseyClient(true).target(httpUrl);
		Response response = target.request(MediaType.APPLICATION_JSON_TYPE).header("Authorization", "Bearer " + token)
				.buildPost(Entity.json(body)).invoke();
		String resultMsg = response.readEntity(String.class);
		String content = mapper.readTree(resultMsg).get("data").toString();
		Map<String, String> result = mapper.readValue(content, Map.class);
		System.out.println("resultMsg:" + resultMsg);
		return result;
	}

	/**
	 * 获取用户token
	 * 
	 * @param appKey
	 * @param username
	 *            用户名
	 * @param password
	 *            密码
	 * @return
	 * @throws IOException
	 * @throws NoSuchAlgorithmException
	 * @throws KeyManagementException
	 */
	public static String getAccessToken(String appKey, String username, String password) throws IOException,
			KeyManagementException, NoSuchAlgorithmException {
		String host = "https://a1.easemob.com";
		String orgName = appKey.substring(0, appKey.lastIndexOf("#"));
		String appName = appKey.substring(appKey.lastIndexOf("#") + 1);

		JerseyWebTarget target = JerseyUtils.getJerseyClient(true).target(host).path("/{org}/{app}/token")
				.resolveTemplate("org", orgName).resolveTemplate("app", appName);
		Map<String, String> payload = new HashMap<String, String>();
		payload.put("grant_type", "password");
		payload.put("username", username);
		payload.put("password", password);
		Response response = target.request(MediaType.APPLICATION_JSON_TYPE).buildPost(Entity.json(payload)).invoke();
		String result = response.readEntity(String.class);

		ObjectMapper objectMapper = new ObjectMapper();
		String token = objectMapper.readTree(result).get("access_token").asText();
		return token;

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

}
