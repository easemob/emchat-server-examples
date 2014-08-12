package com.easemob.server.example.jersey;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.ws.rs.client.WebTarget;

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
public class EasemobUserFriendsAPI {

	/** LOGGER */
	private static Logger LOGGER = LoggerFactory.getLogger(EasemobUserFriendsAPI.class);

	// 获取这个app下的所有用户 GET https://a1.easemob.com/easemob-demo/chatdemoui/users
	/**
	 * 获取指定org和app下所有IM用户(不分页)
	 */
	public static List<String> getAllIMUsers(UsernamePasswordCredentail credentail, String appKey,
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
	 * 获取指定org和app下所有IM用户(分页)
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
	 * 获取这个app下的用户stliu的详情
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
	 * 在这个app下创建一个新的用户
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
	 * 在这个app下批量创建用户
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
	 * 在这个app下删除IM用户
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
	 * 批量在这个app下删除IM用户
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
	 * 添加好友
	 * 
	 * @param friendUsernames
	 *            好友列表
	 * @return
	 */
	public static JsonNode contactsFriend(WebTarget webTarget, List<String> friendUsernames) {
		JsonNode jsonNode = null;
		for (String friendUsername : friendUsernames) {
			String reqURL = "https://a1.easemob.com/easemob-demo/chatdemoui/users/88888/contacts/users/"
					+ friendUsername;
			JerseyUtils.sendRequest(reqURL, jsonNodeBody, credentail, jer, headers);
			jsonNode = JerseyUtils.sendRequest(reqURL, null,
					"YWMtVA8nUBIZEeS7gg2K43Yp_AAAAUeFT0tr01cWcszmuNgRGbFETzRgXXbgEDw", HTTPMethod.METHOD_POST, null);
		}

		return jsonNode;
	}

}
