package com.easemob.server.example.jersey;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.glassfish.jersey.client.JerseyWebTarget;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.easemob.server.example.utils.HTTPMethod;
import com.easemob.server.example.utils.JerseyUtils;
import com.easemob.server.example.utils.PropertiesUtils;
import com.easemob.server.example.utils.Roles;
import com.easemob.server.example.vo.Credentail;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;

/**
 * 数据迁移:可以实现跨Server/ORG/APP做数据迁移
 * 
 * @author Lynch 2014-07-18
 * 
 */
public class DataMigration {

	private static Logger LOGGER = LoggerFactory.getLogger(DataMigration.class);

	private static String APPKEY = PropertiesUtils.getProperties().getProperty("APPKEY");

	private static String OLD_SVR_HOST = PropertiesUtils.getProperties().getProperty("OLD_SVR_HOST");

	private static String NEW_SVR_HOST = PropertiesUtils.getProperties().getProperty("NEW_SVR_HOST");

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
	public static String getAccessToken(String host, String appKey, Map<String, Object> reqBody, Credentail credentail,
			String role) {

		String accessToken = "";

		if (!JerseyUtils.match("[0-9a-zA-Z]+#[0-9a-zA-Z]+", appKey)) {
			Map<String, Object> resultMap = new HashMap<String, Object>();
			LOGGER.error("Bad format of Appkey: " + appKey);
			resultMap.put("statusCode", "401");
			resultMap.put("message", "Bad format of Appkey");

			return JerseyUtils.Map2Json(resultMap).textValue();
		}

		String rest = "";
		if (Roles.USER_ROLE_ORGADMIN.equals(role)) {
			rest = "management/token";
		} else if (Roles.USER_ROLE_APPADMIN.equals(role) || Roles.USER_ROLE_IMUSER.equals(role)) {
			rest = appKey.replaceFirst("#", "/") + "/token";
		}

		String reqURL = "https://" + host + "/" + rest;
		List<NameValuePair> headers = new ArrayList<NameValuePair>();
		headers.add(new BasicNameValuePair("Content-Type", "application/json"));

		JerseyWebTarget target = JerseyUtils.getJerseyClient(true).target(reqURL);
		JsonNode sendRequest = JerseyUtils.sendRequest(target, JerseyUtils.Map2Json(reqBody), null,
				HTTPMethod.METHOD_POST, headers);
		if (sendRequest.get("access_token").textValue() != "") {
			accessToken = sendRequest.get("access_token").textValue();
		}

		System.out.println(accessToken);

		return accessToken;
	}

	/**
	 * 从就服务器上获取数据
	 * 
	 * 
	 * @param appKey
	 * @param host
	 * @param accessToken
	 * @param filePath
	 */
	public static JsonNode getDataFromOldSvr(String appKey, String host, Credentail credentail, String accessToken) {

		if (!JerseyUtils.match("[0-9a-zA-Z]+#[0-9a-zA-Z]+", appKey)) {
			LOGGER.error("Bad format of Appkey: " + appKey);

			Map<String, Object> resultMap = new HashMap<String, Object>();
			resultMap.put("statusCode", "401");
			resultMap.put("message", "Bad format of Appkey");

			return JerseyUtils.Map2Json(resultMap);
		}

		// 数据量大于1000的时候从第二次起用这个URL,加上游标
		// String reqURL = "https://" + host + "/" + appKey.replaceFirst("#", "/")
		// + "/users?limit=1000&cursor=LTU2ODc0MzQzOlZudXctdFdmRWVPNG5fUHFEbFJ5dHc";

		// 数据量小于1000用这个URL或者大于1000的场景第一次也用这个URL
		String reqURL = "https://" + host + "/" + appKey.replaceFirst("#", "/") + "/users?limit=1000";

		List<NameValuePair> headers = new ArrayList<NameValuePair>();
		headers.add(new BasicNameValuePair("Content-Type", "application/json"));

		JerseyWebTarget target = JerseyUtils.getJerseyClient(true).target(reqURL);
		JsonNode sendResponse = JerseyUtils.sendRequest(target, null, credentail, HTTPMethod.METHOD_GET, headers);

		return sendResponse;
	}

	/**
	 * 注册到新服务器上
	 * 
	 * 
	 * @param appKey
	 * @param host
	 * @param accessToken
	 * @param filePath
	 */
	public static JsonNode postDataToNewSvr(String appKey, String host, Credentail credentail, JsonNode entitiesPure) {

		if (!JerseyUtils.match("[0-9a-zA-Z]+#[0-9a-zA-Z]+", appKey)) {
			LOGGER.error("Bad format of Appkey: " + appKey);

			Map<String, Object> resultMap = new HashMap<String, Object>();
			resultMap.put("statusCode", "401");
			resultMap.put("message", "Bad format of Appkey");

			return JerseyUtils.Map2Json(resultMap);
		}

		String reqURL = "http://" + host + "/" + appKey.replaceFirst("#", "/") + "/users";

		List<NameValuePair> headers = new ArrayList<NameValuePair>();
		headers.add(new BasicNameValuePair("Content-Type", "application/json"));

		JerseyWebTarget target = JerseyUtils.getJerseyClient(true).target(reqURL);
		JsonNode sendResponse = JerseyUtils.sendRequest(target, entitiesPure, credentail, HTTPMethod.METHOD_POST,
				headers);

		return sendResponse;
	}

	public static void main(String[] args) {

		// 从就服务器上获取数据
		Map<String, Object> getTokenOnOldServerReqBody = new HashMap<String, Object>();
		getTokenOnOldServerReqBody.put("grant_type", "password");
		getTokenOnOldServerReqBody.put("username", "zhangjianguo");
		getTokenOnOldServerReqBody.put("password", "zhangjianguo");

		String accessTokenOnOldServer = "";

		// JsonNode jsonString = getDataFromOldSvr(APPKEY, OLD_SVR_HOST, accessTokenOnOldServer);
		// ArrayNode entitiesPure = JerseyUtils.getEntitiesFromCompanies(jsonString);

		// 注册到新服务器
		Map<String, Object> getTokenOnNewServerReqBody = new HashMap<String, Object>();
		getTokenOnNewServerReqBody.put("grant_type", "password");
		getTokenOnNewServerReqBody.put("username", "testuser1");
		getTokenOnNewServerReqBody.put("password", "testuser1");
		// String accessTokenOnNewServer = getAccessToken(NEW_SVR_HOST, APPKEY, getTokenOnNewServerReqBody,
		// Roles.USER_ROLE_ORGADMIN);

		ObjectMapper mapper = new ObjectMapper();
		ArrayNode arrayNode = mapper.createArrayNode();

		for (int i = 0; i < entitiesPure.size(); i++) {
			arrayNode.add(entitiesPure.get(i)); // 300 records on onemigration
			if ((i + 1) % 300 == 0) {
				postDataToNewSvr(APPKEY, NEW_SVR_HOST, accessTokenOnNewServer, arrayNode);
				arrayNode.removeAll();
			}
			// the rest records that less than 300
			if (i > (entitiesPure.size() / 300 * 300 - 1)) {
				postDataToNewSvr(APPKEY, NEW_SVR_HOST, accessTokenOnNewServer, arrayNode);
				arrayNode.removeAll();
			}
		}

	}
}
