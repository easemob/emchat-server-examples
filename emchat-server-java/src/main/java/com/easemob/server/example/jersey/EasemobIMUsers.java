package com.easemob.server.example.jersey;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.glassfish.jersey.client.JerseyWebTarget;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.easemob.server.example.utils.Constants;
import com.easemob.server.example.utils.HTTPMethod;
import com.easemob.server.example.utils.JerseyUtils;
import com.easemob.server.example.utils.Roles;
import com.easemob.server.example.vo.EndPoints;
import com.easemob.server.example.vo.Token;
import com.easemob.server.example.vo.UsernamePasswordCredentail;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * REST API Demo : 用户管理REST API Jersey2.9实现
 * 
 * Doc URL: http://developer.easemob.com/docs/emchat/rest/userapi.html
 * 
 * @author Lynch 2014-07-09
 * 
 */
public class EasemobIMUsers {

	private static Logger LOGGER = LoggerFactory.getLogger(EasemobIMUsers.class);

	private static final String APPKEY = Constants.APPKEY;

	private static JsonNodeFactory factory = new JsonNodeFactory(false);

	/**
	 * 注册IM用户[单个]
	 * 
	 * 给指定AppKey创建一个新的用户
	 */
	public static ObjectNode createNewIMUserSingle(ObjectNode dataNode) {

		ObjectNode objectNode = factory.objectNode();

		// check appKey format
		if (!JerseyUtils.match("[0-9a-zA-Z]+#[0-9a-zA-Z]+", APPKEY)) {
			LOGGER.error("Bad format of Appkey: " + APPKEY);

			objectNode.put("statusCode", "401");
			objectNode.put("message", "Bad format of Appkey");

			return objectNode;
		}

		objectNode.removeAll();

		// check properties that must be provided
		if (null != dataNode && !dataNode.has("username")) {
			LOGGER.error("Property that named username must be provided .");

			objectNode.put("statusCode", "402");
			objectNode.put("message", "Property that named username must be provided .");

			return objectNode;
		}
		if (null != dataNode && !dataNode.has("password")) {
			LOGGER.error("Property that named password must be provided .");

			objectNode.put("statusCode", "403");
			objectNode.put("message", "Property that named password must be provided .");

			return objectNode;
		}

		try {

			UsernamePasswordCredentail credentail = new UsernamePasswordCredentail(Constants.APP_ADMIN_USERNAME,
					Constants.APP_ADMIN_PASSWORD, Roles.USER_ROLE_APPADMIN);

			JerseyWebTarget webTarget = null;
			webTarget = EndPoints.USERS_TARGET.resolveTemplate("org_name", APPKEY.split("#")[0]).resolveTemplate(
					"app_name", APPKEY.split("#")[1]);

			objectNode = JerseyUtils.sendRequest(webTarget, dataNode, credentail, HTTPMethod.METHOD_POST, null);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return objectNode;
	}

	/**
	 * 注册IM用户[批量]
	 * 
	 * 给指定AppKey创建一批用户
	 */
	public static ObjectNode createNewIMUserBatch(ArrayNode dataArrayNode) {

		ObjectNode objectNode = factory.objectNode();

		// check appKey format
		if (!JerseyUtils.match("[0-9a-zA-Z]+#[0-9a-zA-Z]+", APPKEY)) {
			LOGGER.error("Bad format of Appkey: " + APPKEY);

			objectNode.put("statusCode", "401");
			objectNode.put("message", "Bad format of Appkey");

			return objectNode;
		}

		// check properties that must be provided
		if (dataArrayNode.isArray()) {
			for (JsonNode jsonNode : dataArrayNode) {
				if (null != jsonNode && !jsonNode.has("username")) {
					LOGGER.error("Property that named username must be provided .");

					objectNode.put("message", "Property that named username must be provided .");

					return objectNode;
				}
				if (null != jsonNode && !jsonNode.has("password")) {
					LOGGER.error("Property that named password must be provided .");

					objectNode.put("message", "Property that named password must be provided .");

					return objectNode;
				}
			}
		}

		try {

			UsernamePasswordCredentail credentail = new UsernamePasswordCredentail(Constants.APP_ADMIN_USERNAME,
					Constants.APP_ADMIN_PASSWORD, Roles.USER_ROLE_APPADMIN);

			JerseyWebTarget webTarget = null;
			webTarget = EndPoints.USERS_TARGET.resolveTemplate("org_name", APPKEY.split("#")[0]).resolveTemplate(
					"app_name", APPKEY.split("#")[1]);

			objectNode = JerseyUtils.sendRequest(webTarget, dataArrayNode, credentail, HTTPMethod.METHOD_POST, null);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return objectNode;
	}

	/**
	 * 注册IM用户[批量生成用户然后注册]
	 * 
	 * 给指定AppKey创建一批用户
	 * 
	 * @param usernamePrefix
	 *            生成用户名的前缀
	 * @param perNumber
	 *            批量注册时一次注册的数量
	 * @param totalNumber
	 *            生成用户注册的用户总数
	 * @return
	 */
	public static ObjectNode createNewIMUserBatchGen(String usernamePrefix, Long perNumber, Long totalNumber) {
		ObjectNode objectNode = factory.objectNode();

		if (totalNumber == 0 || perNumber == 0) {
			return objectNode;
		}

		System.out.println("你即将注册" + totalNumber + "个用户，如果大于" + perNumber + "了,会分批注册,每次注册" + perNumber + "个");

		ArrayNode genericArrayNode = EasemobIMUsers.genericArrayNode(usernamePrefix, totalNumber);
		if (totalNumber <= perNumber) {
			objectNode = EasemobIMUsers.createNewIMUserBatch(genericArrayNode);
		} else {

			for (int i = 0; i < genericArrayNode.size(); i++) {
				ArrayNode tmpArrayNode = factory.arrayNode();
				tmpArrayNode.add(genericArrayNode.get(i));
				// 300 records on one migration
				if ((i + 1) % perNumber == 0) {
					objectNode = EasemobIMUsers.createNewIMUserBatch(genericArrayNode);
					tmpArrayNode.removeAll();
					continue;
				}

				// the rest records that less than the times of 300
				if (i > (genericArrayNode.size() / perNumber * perNumber - 1)) {
					objectNode = EasemobIMUsers.createNewIMUserBatch(genericArrayNode);
					tmpArrayNode.removeAll();
				}
			}
		}

		return objectNode;
	}

	/**
	 * 获取指定AppKey下所有IM用户(分页)
	 */
	public static List<String> getIMUsersPagination(Map<String, Object> reqBody) {
		JsonNode jsonNode = null;

		String reqURL = "";
		JerseyWebTarget target = JerseyUtils.getJerseyClient(true).target(reqURL);
		jsonNode = JerseyUtils.sendRequest(target, JerseyUtils.Map2Json(reqBody), HTTPMethod.METHOD_GET, null);
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
	public static List<String> getIMUserDetailByUsername(Map<String, Object> reqBody) {
		JsonNode jsonNode = null;

		String reqURL = "";
		JerseyWebTarget target = JerseyUtils.getJerseyClient(true).target(reqURL);
		jsonNode = JerseyUtils.sendRequest(target, JerseyUtils.Map2Json(reqBody), HTTPMethod.METHOD_GET, null);
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

	public static ArrayNode genericArrayNode(String usernamePrefix, Long number) {
		ArrayNode arrayNode = factory.arrayNode();
		for (int i = 0; i < number; i++) {
			ObjectNode userNode = factory.objectNode();
			userNode.put("username", usernamePrefix + "_" + i);
			userNode.put("password", Constants.DEFAULT_PASSWORD);

			arrayNode.add(userNode);
		}

		return arrayNode;
	}

	public static void main(String[] args) {
		ObjectNode userDataNode = factory.objectNode();
		userDataNode.put("username", "lynch12");
		userDataNode.put("password", Constants.DEFAULT_PASSWORD);

		// ObjectNode createdIMUser = EasemobIMUsers.createNewIMUserSingle(userDataNode);
		/**************************************************************/
		ObjectNode node1 = factory.objectNode();
		ArrayNode dataArrayNode = factory.arrayNode();
		node1.put("username", "lynch14");
		node1.put("password", Constants.DEFAULT_PASSWORD);
		dataArrayNode.add(node1);
		ObjectNode node2 = factory.objectNode();
		node2.put("username", "lynch15");
		node2.put("password", Constants.DEFAULT_PASSWORD);
		dataArrayNode.add(node2);

		EasemobIMUsers.createNewIMUserBatch(dataArrayNode);

		/**************************************************************/
		EasemobIMUsers.createNewIMUserBatchGen("lynch", dataArrayNode);

	}
}
