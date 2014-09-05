package com.easemob.server.example.jersey;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.glassfish.jersey.client.JerseyWebTarget;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.easemob.server.example.utils.HTTPMethod;
import com.easemob.server.example.utils.JerseyUtils;
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
public class EasemobUsers {

	/** LOGGER */
	private static Logger LOGGER = LoggerFactory.getLogger(EasemobUsers.class);

	/**
	 * 获取指定AppKey下IM用户
	 * 
	 * @param credentail
	 * @param appKey
	 * @param limit
	 *            获取的数量
	 * @return
	 */
	public static JsonNode getIMUsers(UsernamePasswordCredentail credentail, String appKey, String limit) {
		JsonNode jsonNode = null;

		// check appKey format
		if (!JerseyUtils.match("[0-9a-zA-Z]+#[0-9a-zA-Z]+", appKey)) {
			LOGGER.error("Bad format of Appkey: " + appKey);

			Map<String, Object> resultMap = new HashMap<String, Object>();
			resultMap.put("statusCode", "401");
			resultMap.put("message", "Bad format of Appkey");

			return JerseyUtils.Map2Json(resultMap);
		}

		try {
			JerseyWebTarget webTarget = EndPoints.USERS_TARGET.resolveTemplate("appkey", appKey.replaceFirst("#", "/"));

			jsonNode = JerseyUtils.sendRequest(webTarget, null, credentail, HTTPMethod.METHOD_GET, null);
		} catch (Exception e) {

		}

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

		String reqURL = "";
		JerseyWebTarget target = JerseyUtils.getJerseyClient(true).target(reqURL);
		jsonNode = JerseyUtils.sendRequest(target, JerseyUtils.Map2Json(reqBody), credentail, HTTPMethod.METHOD_GET,
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

		String reqURL = "";
		JerseyWebTarget target = JerseyUtils.getJerseyClient(true).target(reqURL);
		jsonNode = JerseyUtils.sendRequest(target, JerseyUtils.Map2Json(reqBody), credentail, HTTPMethod.METHOD_GET,
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
	public static List<String> createNewIMUser(UsernamePasswordCredentail credentail, String appKey,
			Map<String, Object> reqBody) {
		JsonNode jsonNode = null;

		Token token = credentail.getToken();

		String reqURL = "";
		JerseyWebTarget target = JerseyUtils.getJerseyClient(true).target(reqURL);
		jsonNode = JerseyUtils.sendRequest(target, JerseyUtils.Map2Json(reqBody), credentail, HTTPMethod.METHOD_GET,
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
	public static List<String> createNewIMUserBatch(UsernamePasswordCredentail credentail, String appKey,
			Map<String, Object> reqBody) {
		JsonNode jsonNode = null;

		Token token = credentail.getToken();

		String reqURL = "";
		JerseyWebTarget target = JerseyUtils.getJerseyClient(true).target(reqURL);
		jsonNode = JerseyUtils.sendRequest(target, JerseyUtils.Map2Json(reqBody), credentail, HTTPMethod.METHOD_GET,
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

		String reqURL = "";
		JerseyWebTarget target = JerseyUtils.getJerseyClient(true).target(reqURL);
		jsonNode = JerseyUtils.sendRequest(target, JerseyUtils.Map2Json(reqBody), credentail, HTTPMethod.METHOD_GET,
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

		String reqURL = "";
		JerseyWebTarget target = JerseyUtils.getJerseyClient(true).target(reqURL);
		jsonNode = JerseyUtils.sendRequest(target, JerseyUtils.Map2Json(reqBody), credentail, HTTPMethod.METHOD_GET,
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
	public static List<String> conferAppAdminRole(UsernamePasswordCredentail credentail, String appKey,
			Map<String, Object> reqBody) {
		JsonNode jsonNode = null;

		Token token = credentail.getToken();

		String reqURL = "";
		JerseyWebTarget target = JerseyUtils.getJerseyClient(true).target(reqURL);
		jsonNode = JerseyUtils.sendRequest(target, JerseyUtils.Map2Json(reqBody), credentail, HTTPMethod.METHOD_GET,
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
	public static List<String> abolishAppAdminRole(UsernamePasswordCredentail credentail, String appKey,
			Map<String, Object> reqBody) {
		JsonNode jsonNode = null;

		Token token = credentail.getToken();

		String reqURL = "";
		JerseyWebTarget target = JerseyUtils.getJerseyClient(true).target(reqURL);
		jsonNode = JerseyUtils.sendRequest(target, JerseyUtils.Map2Json(reqBody), credentail, HTTPMethod.METHOD_GET,
				null);
		JsonNode entitiesJsonNode = jsonNode.get("entities");
		List<String> friendUsernames = new ArrayList<String>();
		for (JsonNode jsonNode2 : entitiesJsonNode) {
			friendUsernames.add(jsonNode2.get("username").asText());
		}

		return friendUsernames;
	}

}
