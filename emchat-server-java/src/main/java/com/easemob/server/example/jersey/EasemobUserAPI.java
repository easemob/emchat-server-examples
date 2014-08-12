package com.easemob.server.example.jersey;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.easemob.server.example.utils.HTTPMethod;
import com.easemob.server.example.vo.Token;
import com.easemob.server.example.vo.UsernamePasswordCredentail;
import com.fasterxml.jackson.databind.JsonNode;

/**
 * REST API Demo : 用户管理REST API Jersey2.9实现
 * 
 * Doc URL: http://developer.easemob.com/docs/emchat/rest/userapi.html
 * 
 * @author Lynch 2014-07-09
 * 
 */
public class EasemobUserAPI {

	/** LOGGER */
	private static Logger LOGGER = LoggerFactory.getLogger(EasemobUserAPI.class);

	/**
	 * 获取指定AppKey下所有IM用户(不分页)
	 */
	public static JsonNode getAllIMUsers(UsernamePasswordCredentail credentail, String appKey,
			Map<String, Object> reqBody) {
		JsonNode jsonNode = null;

		String reqURL = "https://a1.easemob.com/easemob-demo/chatdemoui/users?limit=150";
		jsonNode = JerseyUtils.sendRequest(reqURL, JerseyUtils.Map2Json(reqBody), credentail, HTTPMethod.METHOD_GET,
				null);
		if (jsonNode.get("error") == null) {
			LOGGER.info("");
		}

		JsonNode entitiesJsonNode = jsonNode.get("entities");

		return entitiesJsonNode;
	}

	/**
	 * 获取指定AppKey下所有IM用户(分页)
	 */
	public static List<String> getIMUsersPagination(UsernamePasswordCredentail credentail, String appKey,
			Map<String, Object> reqBody) {
		JsonNode jsonNode = null;

		Token token = credentail.getToken();

		String reqURL = "https://a1.easemob.com/easemob-demo/chatdemoui/users?limit=150";
		jsonNode = JerseyUtils.sendRequest(reqURL, JerseyUtils.Map2Json(reqBody), credentail, HTTPMethod.METHOD_GET,
				null);
		JsonNode entitiesJsonNode = jsonNode.get("entities");
		List<String> friendUsernames = new ArrayList<String>();
		for (JsonNode jsonNode2 : entitiesJsonNode) {
			friendUsernames.add(jsonNode2.get("username").asText());
		}

		return friendUsernames;
	}

	/**
	 * 获取指定AppKey下某个用户
	 */
	public static List<String> getIMUserDetailByUsername(UsernamePasswordCredentail credentail, String appKey,
			Map<String, Object> reqBody) {
		JsonNode jsonNode = null;

		Token token = credentail.getToken();

		String reqURL = "https://a1.easemob.com/easemob-demo/chatdemoui/users?limit=150";
		jsonNode = JerseyUtils.sendRequest(reqURL, JerseyUtils.Map2Json(reqBody), credentail, HTTPMethod.METHOD_GET,
				null);
		JsonNode entitiesJsonNode = jsonNode.get("entities");
		List<String> friendUsernames = new ArrayList<String>();
		for (JsonNode jsonNode2 : entitiesJsonNode) {
			friendUsernames.add(jsonNode2.get("username").asText());
		}

		return friendUsernames;
	}

	/**
	 * 给指定AppKey创建一个新的用户
	 */
	public static List<String> getIMUserDetailByUsername(UsernamePasswordCredentail credentail, String appKey,
			Map<String, Object> reqBody) {
		JsonNode jsonNode = null;

		Token token = credentail.getToken();

		String reqURL = "https://a1.easemob.com/easemob-demo/chatdemoui/users?limit=150";
		jsonNode = JerseyUtils.sendRequest(reqURL, JerseyUtils.Map2Json(reqBody), credentail, HTTPMethod.METHOD_GET,
				null);
		JsonNode entitiesJsonNode = jsonNode.get("entities");
		List<String> friendUsernames = new ArrayList<String>();
		for (JsonNode jsonNode2 : entitiesJsonNode) {
			friendUsernames.add(jsonNode2.get("username").asText());
		}

		return friendUsernames;
	}

	/**
	 * 给指定AppKey批量创建用户
	 */
	public static List<String> getIMUserDetailByUsername(UsernamePasswordCredentail credentail, String appKey,
			Map<String, Object> reqBody) {
		JsonNode jsonNode = null;

		Token token = credentail.getToken();

		String reqURL = "https://a1.easemob.com/easemob-demo/chatdemoui/users?limit=150";
		jsonNode = JerseyUtils.sendRequest(reqURL, JerseyUtils.Map2Json(reqBody), credentail, HTTPMethod.METHOD_GET,
				null);
		JsonNode entitiesJsonNode = jsonNode.get("entities");
		List<String> friendUsernames = new ArrayList<String>();
		for (JsonNode jsonNode2 : entitiesJsonNode) {
			friendUsernames.add(jsonNode2.get("username").asText());
		}

		return friendUsernames;
	}

	/**
	 * 删除指定AppKey下IM用户
	 */
	public static List<String> deleteIMUserByUsername(UsernamePasswordCredentail credentail, String appKey,
			Map<String, Object> reqBody) {
		JsonNode jsonNode = null;

		Token token = credentail.getToken();

		String reqURL = "https://a1.easemob.com/easemob-demo/chatdemoui/users?limit=150";
		jsonNode = JerseyUtils.sendRequest(reqURL, JerseyUtils.Map2Json(reqBody), credentail, HTTPMethod.METHOD_GET,
				null);
		JsonNode entitiesJsonNode = jsonNode.get("entities");
		List<String> friendUsernames = new ArrayList<String>();
		for (JsonNode jsonNode2 : entitiesJsonNode) {
			friendUsernames.add(jsonNode2.get("username").asText());
		}

		return friendUsernames;
	}

	/**
	 * 批量指定AppKey下删除IM用户
	 */
	public static List<String> deleteIMUserByUsernameBatch(UsernamePasswordCredentail credentail, String appKey,
			Map<String, Object> reqBody) {
		JsonNode jsonNode = null;

		Token token = credentail.getToken();

		String reqURL = "https://a1.easemob.com/easemob-demo/chatdemoui/users?limit=150";
		jsonNode = JerseyUtils.sendRequest(reqURL, JerseyUtils.Map2Json(reqBody), credentail, HTTPMethod.METHOD_GET,
				null);
		JsonNode entitiesJsonNode = jsonNode.get("entities");
		List<String> friendUsernames = new ArrayList<String>();
		for (JsonNode jsonNode2 : entitiesJsonNode) {
			friendUsernames.add(jsonNode2.get("username").asText());
		}

		return friendUsernames;
	}

	/**
	 * 指定AppKey设置APP管理员
	 */
	public static List<String> deleteIMUserByUsernameBatch(UsernamePasswordCredentail credentail, String appKey,
			Map<String, Object> reqBody) {
		JsonNode jsonNode = null;

		Token token = credentail.getToken();

		String reqURL = "https://a1.easemob.com/easemob-demo/chatdemoui/users?limit=150";
		jsonNode = JerseyUtils.sendRequest(reqURL, JerseyUtils.Map2Json(reqBody), credentail, HTTPMethod.METHOD_GET,
				null);
		JsonNode entitiesJsonNode = jsonNode.get("entities");
		List<String> friendUsernames = new ArrayList<String>();
		for (JsonNode jsonNode2 : entitiesJsonNode) {
			friendUsernames.add(jsonNode2.get("username").asText());
		}

		return friendUsernames;
	}

	/**
	 * 撤销指定AppKey某个APP管理员
	 */
	public static List<String> deleteIMUserByUsernameBatch(UsernamePasswordCredentail credentail, String appKey,
			Map<String, Object> reqBody) {
		JsonNode jsonNode = null;

		Token token = credentail.getToken();

		String reqURL = "https://a1.easemob.com/easemob-demo/chatdemoui/users?limit=150";
		jsonNode = JerseyUtils.sendRequest(reqURL, JerseyUtils.Map2Json(reqBody), credentail, HTTPMethod.METHOD_GET,
				null);
		JsonNode entitiesJsonNode = jsonNode.get("entities");
		List<String> friendUsernames = new ArrayList<String>();
		for (JsonNode jsonNode2 : entitiesJsonNode) {
			friendUsernames.add(jsonNode2.get("username").asText());
		}

		return friendUsernames;
	}

}
