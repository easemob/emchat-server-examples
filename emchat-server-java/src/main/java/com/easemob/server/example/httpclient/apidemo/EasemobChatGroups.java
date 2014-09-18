package com.easemob.server.example.httpclient.apidemo;

import java.net.URL;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.easemob.server.example.comm.Constants;
import com.easemob.server.example.comm.HTTPMethod;
import com.easemob.server.example.comm.Roles;
import com.easemob.server.example.httpclient.utils.HTTPClientUtils;
import com.easemob.server.example.httpclient.vo.ClientSecretCredentail;
import com.easemob.server.example.httpclient.vo.Credentail;
import com.easemob.server.example.httpclient.vo.EndPoints;
import com.easemob.server.example.httpclient.vo.UsernamePasswordCredentail;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * REST API Demo : 群组管理 HttpClient4.3实现
 * 
 * Doc URL: http://www.easemob.com/docs/rest/groups/
 * 
 * @author Lynch 2014-09-15
 *
 */
public class EasemobChatGroups {

	private static Logger LOGGER = LoggerFactory.getLogger(EasemobChatGroups.class);

	private static JsonNodeFactory factory = new JsonNodeFactory(false);

	private static final String APPKEY = Constants.APPKEY;

	/**
	 * 获取APP中所有的群组ID
	 * 
	 * @return
	 */
	public static ObjectNode getAllChatgroupids() {

		ObjectNode objectNode = factory.objectNode();

		// check appKey format
		if (!HTTPClientUtils.match("[0-9a-zA-Z]+#[0-9a-zA-Z]+", APPKEY)) {
			LOGGER.error("Bad format of Appkey: " + APPKEY);

			objectNode.put("message", "Bad format of Appkey");

			return objectNode;
		}

		try {
			/*
			 * Credentail credentail = new UsernamePasswordCredentail(Constants.APP_ADMIN_USERNAME,
			 * Constants.APP_ADMIN_PASSWORD, Roles.USER_ROLE_APPADMIN);
			 */
			Credentail credentail2 = new ClientSecretCredentail(Constants.APP_CLIENT_ID, Constants.APP_CLIENT_SECRET,
					Roles.USER_ROLE_APPADMIN);
			objectNode = HTTPClientUtils.sendHTTPRequest(EndPoints.CHATGROUPS_URL, credentail2, null,
					HTTPMethod.METHOD_GET);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return objectNode;
	}

	/**
	 * 获取一个或者多个群组的详情
	 * 
	 * @return
	 */
	public static ObjectNode getGroupDetailsByChatgroupid(String[] chatgroupIDs) {
		ObjectNode objectNode = factory.objectNode();

		// check appKey format
		if (!HTTPClientUtils.match("[0-9a-zA-Z]+#[0-9a-zA-Z]+", APPKEY)) {
			LOGGER.error("Bad format of Appkey: " + APPKEY);

			objectNode.put("message", "Bad format of Appkey");

			return objectNode;
		}

		try {

			Credentail credentail = new UsernamePasswordCredentail(Constants.APP_ADMIN_USERNAME,
					Constants.APP_ADMIN_PASSWORD, Roles.USER_ROLE_APPADMIN);

			URL groupDetailsByChatgroupidUrl = HTTPClientUtils.getURL(Constants.APPKEY.replace("#", "/")
					+ "/chatgroups/" + chatgroupIDs.toString());

			objectNode = HTTPClientUtils.sendHTTPRequest(groupDetailsByChatgroupidUrl, credentail, null,
					HTTPMethod.METHOD_GET);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return objectNode;
	}

	/**
	 * 创建群组
	 * 
	 */
	public static ObjectNode creatChatGroups(ObjectNode dataObjectNode) {

		ObjectNode objectNode = factory.objectNode();

		// check appKey format
		if (!HTTPClientUtils.match("[0-9a-zA-Z]+#[0-9a-zA-Z]+", APPKEY)) {
			LOGGER.error("Bad format of Appkey: " + APPKEY);

			objectNode.put("message", "Bad format of Appkey");

			return objectNode;
		}

		// check properties that must be provided
		if (!dataObjectNode.has("groupname")) {
			LOGGER.error("Property that named groupname must be provided .");

			objectNode.put("message", "Property that named groupname must be provided .");

			return objectNode;
		}
		if (!dataObjectNode.has("desc")) {
			LOGGER.error("Property that named desc must be provided .");

			objectNode.put("message", "Property that named desc must be provided .");

			return objectNode;
		}
		if (!dataObjectNode.has("public")) {
			LOGGER.error("Property that named public must be provided .");

			objectNode.put("message", "Property that named public must be provided .");

			return objectNode;
		}
		if (!dataObjectNode.has("approval")) {
			LOGGER.error("Property that named approval must be provided .");

			objectNode.put("message", "Property that named approval must be provided .");

			return objectNode;
		}
		if (!dataObjectNode.has("owner")) {
			LOGGER.error("Property that named owner must be provided .");

			objectNode.put("message", "Property that named owner must be provided .");

			return objectNode;
		}
		if (!dataObjectNode.has("members") || dataObjectNode.path("members").isArray()) {
			LOGGER.error("Property that named members must be provided .");

			objectNode.put("message", "Property that named members must be provided .");

			return objectNode;
		}

		try {

			Credentail credentail = new UsernamePasswordCredentail(Constants.APP_ADMIN_USERNAME,
					Constants.APP_ADMIN_PASSWORD, Roles.USER_ROLE_APPADMIN);

			objectNode = HTTPClientUtils.sendHTTPRequest(EndPoints.CHATGROUPS_URL, credentail, dataObjectNode,
					HTTPMethod.METHOD_POST);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return objectNode;
	}

	/**
	 * 删除群组
	 * 
	 */
	public static ObjectNode deleteChatGroups(String chatgroupid) {
		ObjectNode objectNode = factory.objectNode();

		// check appKey format
		if (!HTTPClientUtils.match("[0-9a-zA-Z]+#[0-9a-zA-Z]+", APPKEY)) {
			LOGGER.error("Bad format of Appkey: " + APPKEY);

			objectNode.put("message", "Bad format of Appkey");

			return objectNode;
		}

		try {

			Credentail credentail = new UsernamePasswordCredentail(Constants.APP_ADMIN_USERNAME,
					Constants.APP_ADMIN_PASSWORD, Roles.USER_ROLE_APPADMIN);

			URL deleteChatGroupsUrl = HTTPClientUtils.getURL(Constants.APPKEY.replace("#", "/") + "/chatgroups/"
					+ chatgroupid);

			objectNode = HTTPClientUtils.sendHTTPRequest(deleteChatGroupsUrl, credentail, null,
					HTTPMethod.METHOD_DELETE);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return objectNode;
	}

	/**
	 * 获取群组中的所有成员
	 * 
	 */
	public static ObjectNode getAllMemberssByGroupId(String chatgroupid) {

		ObjectNode objectNode = factory.objectNode();

		// check appKey format
		if (!HTTPClientUtils.match("[0-9a-zA-Z]+#[0-9a-zA-Z]+", APPKEY)) {
			LOGGER.error("Bad format of Appkey: " + APPKEY);

			objectNode.put("message", "Bad format of Appkey");

			return objectNode;
		}

		try {

			Credentail credentail = new UsernamePasswordCredentail(Constants.APP_ADMIN_USERNAME,
					Constants.APP_ADMIN_PASSWORD, Roles.USER_ROLE_APPADMIN);

			URL allMemberssByGroupIdUrl = HTTPClientUtils.getURL(Constants.APPKEY.replace("#", "/") + "/chatgroups/"
					+ chatgroupid + "/users");
			objectNode = HTTPClientUtils.sendHTTPRequest(allMemberssByGroupIdUrl, credentail, null,
					HTTPMethod.METHOD_GET);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return objectNode;
	}

	/**
	 * 在群组中添加一个人
	 * 
	 */
	public static ObjectNode addUserToGroup(String chatgroupid, String userprimarykey) {

		ObjectNode objectNode = factory.objectNode();

		// check appKey format
		if (!HTTPClientUtils.match("[0-9a-zA-Z]+#[0-9a-zA-Z]+", APPKEY)) {
			LOGGER.error("Bad format of Appkey: " + APPKEY);

			objectNode.put("message", "Bad format of Appkey");

			return objectNode;
		}

		try {

			Credentail credentail = new UsernamePasswordCredentail(Constants.APP_ADMIN_USERNAME,
					Constants.APP_ADMIN_PASSWORD, Roles.USER_ROLE_APPADMIN);

			URL allMemberssByGroupIdUrl = HTTPClientUtils.getURL(Constants.APPKEY.replace("#", "/") + "/chatgroups/"
					+ chatgroupid + "/users");

			objectNode = HTTPClientUtils.sendHTTPRequest(allMemberssByGroupIdUrl, credentail, null,
					HTTPMethod.METHOD_POST);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return objectNode;
	}

	/**
	 * 在群组中减少一个人
	 * 
	 */
	public static ObjectNode deleteUserFromGroup(String chatgroupid, String userprimarykey) {
		ObjectNode objectNode = factory.objectNode();

		// check appKey format
		if (!HTTPClientUtils.match("[0-9a-zA-Z]+#[0-9a-zA-Z]+", APPKEY)) {
			LOGGER.error("Bad format of Appkey: " + APPKEY);

			objectNode.put("message", "Bad format of Appkey");

			return objectNode;
		}

		try {

			Credentail credentail = new UsernamePasswordCredentail(Constants.APP_ADMIN_USERNAME,
					Constants.APP_ADMIN_PASSWORD, Roles.USER_ROLE_APPADMIN);

			URL allMemberssByGroupIdUrl = HTTPClientUtils.getURL(Constants.APPKEY.replace("#", "/") + "/chatgroups/"
					+ chatgroupid + "/users/" + userprimarykey);

			objectNode = HTTPClientUtils.sendHTTPRequest(allMemberssByGroupIdUrl, credentail, null,
					HTTPMethod.METHOD_DELETE);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return objectNode;
	}

	public static void main(String[] args) {
		getAllChatgroupids();
	}
}
