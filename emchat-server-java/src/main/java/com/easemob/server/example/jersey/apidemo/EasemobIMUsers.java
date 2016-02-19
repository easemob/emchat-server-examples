package com.easemob.server.example.jersey.apidemo;

import org.apache.commons.lang3.StringUtils;
import org.glassfish.jersey.client.JerseyWebTarget;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.easemob.server.example.api.IMUserAPI;
import com.easemob.server.example.comm.Constants;
import com.easemob.server.example.comm.HTTPMethod;
import com.easemob.server.example.comm.Roles;
import com.easemob.server.example.jersey.utils.JerseyUtils;
import com.easemob.server.example.jersey.vo.ClientSecretCredential;
import com.easemob.server.example.jersey.vo.Credential;
import com.easemob.server.example.jersey.vo.EndPoints;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * REST API Demo : 用户体系集成 Jersey2.9实现
 * 
 * Doc URL:
 * https://docs.easemob.com/doku.php?id=start:100serverintegration:20users
 * 
 * @author Lynch 2014-09-09 Eric23 2016-01-05
 */
public class EasemobIMUsers implements IMUserAPI {

	private static final Logger LOGGER = LoggerFactory.getLogger(EasemobIMUsers.class);
	private static final String APPKEY = Constants.APPKEY;
	private static final JsonNodeFactory factory = new JsonNodeFactory(false);

	private static Credential credential = new ClientSecretCredential(Constants.APP_CLIENT_ID,
			Constants.APP_CLIENT_SECRET, Roles.USER_ROLE_APPADMIN);

	public static void main(String[] args) {
		IMUserAPI userAPIImpl = new EasemobIMUsers();

		String demoUserName = "example_user";
		String demoFriendName = "example_user_friend";

		// 注册单个用户
		ObjectNode createNewIMUserSingleNode = (ObjectNode) userAPIImpl.createNewIMUserSingle(JsonNodeFactory.instance
				.objectNode().put("username", demoUserName).put("password", Constants.DEFAULT_PASSWORD));
		if (null != createNewIMUserSingleNode) {
			LOGGER.info("注册用户[单个]: " + createNewIMUserSingleNode.toString());
		}
		createNewIMUserSingleNode = (ObjectNode) userAPIImpl.createNewIMUserSingle(JsonNodeFactory.instance.objectNode()
				.put("username", demoFriendName).put("password", Constants.DEFAULT_PASSWORD));
		if (null != createNewIMUserSingleNode) {
			LOGGER.info("注册用户[单个]: " + createNewIMUserSingleNode.toString());
		}

		// 批量注册用户
		String usernamePrefix = demoUserName;
		Long perNumber = 20L;
		Long totalNumber = 50L;
		ObjectNode createNewIMUserBatchGenNode = ((EasemobIMUsers) userAPIImpl).createNewIMUserBatchGen(usernamePrefix,
				perNumber, totalNumber);
		if (null != createNewIMUserBatchGenNode) {
			LOGGER.info("注册用户[批量]: " + createNewIMUserBatchGenNode.toString());
		}

		// 获取单个用户
		ObjectNode getIMUsersByUserNameNode = (ObjectNode) userAPIImpl.getIMUsersByUserName(demoUserName);
		if (null != getIMUsersByUserNameNode) {
			LOGGER.info("获取用户[主键查询]: " + getIMUsersByUserNameNode.toString());
		}

		// 批量获取用户[带游标和不带游标]
		ObjectNode getIMUserBatchNode = (ObjectNode) userAPIImpl.getIMUsersBatch(10L, null);
		if (null != getIMUserBatchNode) {
			LOGGER.info("获取用户[批量]: " + getIMUserBatchNode.toString());

			String cursor = getIMUserBatchNode.get("cursor").asText();
			if (StringUtils.isNotBlank(cursor)) {
				LOGGER.info("获取用户[批量]: 存在更多记录，准备获取下一页: " + cursor);
				getIMUserBatchNode = (ObjectNode) userAPIImpl.getIMUsersBatch(10L, cursor);
				if (null != getIMUserBatchNode) {
					LOGGER.info("获取用户[批量，带游标]: " + getIMUserBatchNode.toString());
				}
			}
		}

		// 重置用户密码
		ObjectNode modifyIMUserPasswordWithAdminTokenNode = (ObjectNode) userAPIImpl.modifyIMUserPasswordWithAdminToken(
				demoUserName, JsonNodeFactory.instance.objectNode().put("newpassword", Constants.DEFAULT_PASSWORD));
		if (null != modifyIMUserPasswordWithAdminTokenNode) {
			LOGGER.info("重置用户密码 : " + modifyIMUserPasswordWithAdminTokenNode.toString());
		}

		// 添加单个好友
		ObjectNode addFriendSingleNode = (ObjectNode) userAPIImpl.addFriendSingle(demoUserName, demoFriendName);
		if (null != addFriendSingleNode) {
			LOGGER.info("添加好友[单个]: " + addFriendSingleNode.toString());
		}

		// 查看好友
		ObjectNode getFriendsNode = (ObjectNode) userAPIImpl.getFriends(demoUserName);
		if (null != getFriendsNode) {
			LOGGER.info("查看好友: " + getFriendsNode.toString());
		}

		// 解除好友关系
		ObjectNode deleteFriendSingleNode = (ObjectNode) userAPIImpl.deleteFriendSingle(demoUserName, demoFriendName);
		if (null != deleteFriendSingleNode) {
			LOGGER.info("解除好友关系: " + deleteFriendSingleNode.toString());
		}

		// 修改用户昵称
		ObjectNode modifyIMUserNickNameWithAdminTokenNode = (ObjectNode) userAPIImpl.modifyIMUserNickNameWithAdminToken(
				demoUserName, JsonNodeFactory.instance.objectNode().put("nickname", "Easemob"));
		if (null != modifyIMUserNickNameWithAdminTokenNode) {
			LOGGER.info("修改用户昵称: " + modifyIMUserNickNameWithAdminTokenNode.toString());
		}

		// 添加黑名单
		ObjectNode blacklist = JsonNodeFactory.instance.objectNode();
		blacklist.putArray("usernames").add(demoFriendName);
		ObjectNode addToBlackListNode = (ObjectNode) userAPIImpl.addToBlackList(demoUserName, blacklist);
		if (null != addToBlackListNode) {
			LOGGER.info("添加用户黑名单: " + addToBlackListNode.toString());
		}

		// 获取黑名单
		ObjectNode getBlackListNode = (ObjectNode) userAPIImpl.getBlackList(demoUserName);
		if (null != getBlackListNode) {
			LOGGER.info("获取用户黑名单: " + getBlackListNode.toString());
		}

		// 移除黑名单
		ObjectNode removeFromBlackListNode = (ObjectNode) userAPIImpl.removeFromBlackList(demoUserName, demoFriendName);
		if (null != removeFromBlackListNode) {
			LOGGER.info("移除用户黑名单: " + removeFromBlackListNode.toString());
		}

		// 获取用户在线状态
		ObjectNode getUserStatusNode = (ObjectNode) userAPIImpl.getIMUserStatus(demoUserName);
		if (null != getUserStatusNode) {
			LOGGER.info("获取用户在线状态: " + getUserStatusNode.toString());
		}

		// 获取用户离线消息数
		ObjectNode getOfflineMsgCountNode = (ObjectNode) userAPIImpl.getOfflineMsgCount(demoUserName);
		if (null != getOfflineMsgCountNode) {
			LOGGER.info("获取用户离线消息数: " + getOfflineMsgCountNode.toString());
		}

		// 获取用户离线消息状态
		ObjectNode getOfflineMsgStatusNode = (ObjectNode) userAPIImpl.getSpecifiedOfflineMsgStatus(demoUserName,
				"10001");
		if (null != getOfflineMsgStatusNode) {
			LOGGER.info("获取用户离线消息状态: " + getOfflineMsgStatusNode.toString());
		}

		// 禁用用户
		ObjectNode deactivateUserNode = (ObjectNode) userAPIImpl.deactivateIMUser(demoUserName);
		if (null != deactivateUserNode) {
			LOGGER.info("禁用用户: " + deactivateUserNode.toString());
		}

		// 启用用户
		ObjectNode activateUserNode = (ObjectNode) userAPIImpl.activateIMUser(demoUserName);
		if (null != activateUserNode) {
			LOGGER.info("启用用户: " + activateUserNode.toString());
		}

		// 强制用户下线
		ObjectNode disconnectUserNode = (ObjectNode) userAPIImpl.disconnectIMUser(demoUserName);
		if (null != disconnectUserNode) {
			LOGGER.info("强制用户下线: " + disconnectUserNode.toString());
		}

		// 删除单个用户
		ObjectNode deleteIMUserByUserNameNode = (ObjectNode) userAPIImpl.deleteIMUserByUserName(demoFriendName);
		if (null != deleteIMUserByUserNameNode) {
			LOGGER.info("删除用户[单个]: " + deleteIMUserByUserNameNode.toString());
		}

		// 批量删除用户
		ObjectNode deleteIMUserByUsernameBatchNode = (ObjectNode) userAPIImpl.deleteIMUserBatch(2L);
		if (null != deleteIMUserByUsernameBatchNode) {
			LOGGER.info("删除用户[批量]: " + deleteIMUserByUsernameBatchNode.toString());
		}
	}

	public ObjectNode createNewIMUserSingle(Object payload) {
		ObjectNode dataNode = (ObjectNode) payload;
		ObjectNode objectNode = factory.objectNode();

		// check appKey format
		if (!JerseyUtils.match("^(?!-)[0-9a-zA-Z\\-]+#[0-9a-zA-Z]+", APPKEY)) {
			LOGGER.error("Bad format of Appkey: " + APPKEY);

			objectNode.put("message", "Bad format of Appkey");

			return objectNode;
		}

		objectNode.removeAll();

		// check properties that must be provided
		if (null != dataNode && !dataNode.has("username")) {
			LOGGER.error("Property that named username must be provided .");

			objectNode.put("message", "Property that named username must be provided .");

			return objectNode;
		}
		if (null != dataNode && !dataNode.has("password")) {
			LOGGER.error("Property that named password must be provided .");

			objectNode.put("message", "Property that named password must be provided .");

			return objectNode;
		}

		try {
			JerseyWebTarget webTarget = EndPoints.USERS_TARGET.resolveTemplate("org_name", APPKEY.split("#")[0])
					.resolveTemplate("app_name", APPKEY.split("#")[1]);

			objectNode = JerseyUtils.sendRequest(webTarget, dataNode, credential, HTTPMethod.METHOD_POST, null);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return objectNode;
	}

	public ObjectNode createNewIMUserBatch(Object payload) {
		ArrayNode dataArrayNode = (ArrayNode) payload;
		ObjectNode objectNode = factory.objectNode();

		// check appKey format
		if (!JerseyUtils.match("^(?!-)[0-9a-zA-Z\\-]+#[0-9a-zA-Z]+", APPKEY)) {
			LOGGER.error("Bad format of Appkey: " + APPKEY);
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
		} else {
			LOGGER.error("Bad format of payload, it should be an array with json format.");
			objectNode.put("message", "Bad format of payload, it should be an array with json format.");
			return objectNode;
		}

		try {
			JerseyWebTarget webTarget = EndPoints.USERS_TARGET.resolveTemplate("org_name", APPKEY.split("#")[0])
					.resolveTemplate("app_name", APPKEY.split("#")[1]);
			objectNode = JerseyUtils.sendRequest(webTarget, dataArrayNode, credential, HTTPMethod.METHOD_POST, null);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return objectNode;
	}

	public ObjectNode getIMUsersByUserName(String userName) {
		ObjectNode objectNode = factory.objectNode();

		// check appKey format
		if (!JerseyUtils.match("^(?!-)[0-9a-zA-Z\\-]+#[0-9a-zA-Z]+", APPKEY)) {
			LOGGER.error("Bad format of Appkey: " + APPKEY);

			objectNode.put("message", "Bad format of Appkey");

			return objectNode;
		}

		// check properties that must be provided
		if (StringUtils.isEmpty(userName)) {
			LOGGER.error("The primaryKey that will be useed to query must be provided .");

			objectNode.put("message", "The primaryKey that will be useed to query must be provided .");

			return objectNode;
		}

		try {

			JerseyWebTarget webTarget = EndPoints.USERS_TARGET.resolveTemplate("org_name", APPKEY.split("#")[0])
					.resolveTemplate("app_name", APPKEY.split("#")[1]).path(userName);

			objectNode = JerseyUtils.sendRequest(webTarget, null, credential, HTTPMethod.METHOD_GET, null);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return objectNode;
	}

	public ObjectNode getIMUsersBatch(Long limit, String cursor) {
		ObjectNode objectNode = factory.objectNode();

		// check appKey format
		if (!JerseyUtils.match("^(?!-)[0-9a-zA-Z\\-]+#[0-9a-zA-Z]+", APPKEY)) {
			LOGGER.error("Bad format of Appkey: " + APPKEY);
			objectNode.put("message", "Bad format of Appkey");
			return objectNode;
		}

		// check the input parameters
		if (null == limit || limit <= 0) {
			LOGGER.error("Invailid parameter of limit: " + limit);
			objectNode.put("message", "Invailid parameter of limit");
			return objectNode;
		}

		JerseyWebTarget webTarget = EndPoints.USERS_TARGET.resolveTemplate("org_name", APPKEY.split("#")[0])
				.resolveTemplate("app_name", APPKEY.split("#")[1]).queryParam("limit", limit);
		if (StringUtils.isNoneBlank(cursor)) {
			webTarget = webTarget.queryParam("cursor", cursor);
		}

		objectNode = JerseyUtils.sendRequest(webTarget, null, credential, HTTPMethod.METHOD_GET, null);

		return objectNode;
	}

	public ObjectNode deleteIMUserByUserName(String userName) {
		ObjectNode objectNode = factory.objectNode();

		// check appKey format
		if (!JerseyUtils.match("^(?!-)[0-9a-zA-Z\\-]+#[0-9a-zA-Z]+", APPKEY)) {
			LOGGER.error("Bad format of Appkey: " + APPKEY);

			objectNode.put("message", "Bad format of Appkey");

			return objectNode;
		}

		try {
			JerseyWebTarget webTarget = EndPoints.USERS_TARGET.resolveTemplate("org_name", APPKEY.split("#")[0])
					.resolveTemplate("app_name", APPKEY.split("#")[1]).path(userName);

			objectNode = JerseyUtils.sendRequest(webTarget, null, credential, HTTPMethod.METHOD_DELETE, null);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return objectNode;
	}

	public ObjectNode deleteIMUserBatch(Long limit) {

		ObjectNode objectNode = factory.objectNode();

		// check appKey format
		if (!JerseyUtils.match("^(?!-)[0-9a-zA-Z\\-]+#[0-9a-zA-Z]+", APPKEY)) {
			LOGGER.error("Bad format of Appkey: " + APPKEY);
			objectNode.put("message", "Bad format of Appkey");
			return objectNode;
		}

		// check the input parameters
		if (null == limit || limit <= 0) {
			LOGGER.error("Invailid parameter of limit: " + limit);
			objectNode.put("message", "Invailid parameter of limit");
			return objectNode;
		}

		try {

			JerseyWebTarget webTarget = EndPoints.USERS_TARGET.resolveTemplate("org_name", APPKEY.split("#")[0])
					.resolveTemplate("app_name", APPKEY.split("#")[1]).queryParam("limit", String.valueOf(limit));

			objectNode = JerseyUtils.sendRequest(webTarget, null, credential, HTTPMethod.METHOD_DELETE, null);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return objectNode;
	}

	public ObjectNode modifyIMUserPasswordWithAdminToken(String userName, Object payload) {
		ObjectNode dataObjectNode = (ObjectNode) payload;
		ObjectNode objectNode = factory.objectNode();

		// check appKey format
		if (!JerseyUtils.match("^(?!-)[0-9a-zA-Z\\-]+#[0-9a-zA-Z]+", APPKEY)) {
			LOGGER.error("Bad format of Appkey: " + APPKEY);

			objectNode.put("message", "Bad format of Appkey");

			return objectNode;
		}

		if (StringUtils.isEmpty(userName)) {
			LOGGER.error("Property that named userName must be provided，the value is username or uuid of imuser.");

			objectNode.put("message",
					"Property that named userName must be provided，the value is username or uuid of imuser.");

			return objectNode;
		}

		if (null != dataObjectNode && !dataObjectNode.has("newpassword")) {
			LOGGER.error("Property that named newpassword must be provided .");

			objectNode.put("message", "Property that named newpassword must be provided .");

			return objectNode;
		}

		try {

			JerseyWebTarget webTarget = EndPoints.USERS_TARGET.resolveTemplate("org_name", APPKEY.split("#")[0])
					.resolveTemplate("app_name", APPKEY.split("#")[1]).path(userName).path("password");

			objectNode = JerseyUtils.sendRequest(webTarget, dataObjectNode, credential, HTTPMethod.METHOD_PUT, null);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return objectNode;
	}

	public ObjectNode modifyIMUserNickNameWithAdminToken(String userName, Object payload) {
		ObjectNode dataObjectNode = (ObjectNode) payload;
		ObjectNode objectNode = factory.objectNode();

		// check appKey format
		if (!JerseyUtils.match("^(?!-)[0-9a-zA-Z\\-]+#[0-9a-zA-Z]+", APPKEY)) {
			LOGGER.error("Bad format of Appkey: " + APPKEY);
			objectNode.put("message", "Bad format of Appkey");
			return objectNode;
		}

		// check input parameter
		if (StringUtils.isEmpty(userName)) {
			LOGGER.error("Property that named userName must be provided，the value is username or uuid of imuser.");
			objectNode.put("message",
					"Property that named userName must be provided，the value is username or uuid of imuser.");
			return objectNode;
		}

		// check payload
		if (null != dataObjectNode && !dataObjectNode.has("nickname")) {
			LOGGER.error("Property that named nickname must be provided .");
			objectNode.put("message", "Property that named nickname must be provided .");
			return objectNode;
		}

		try {
			JerseyWebTarget webTarget = EndPoints.USERS_TARGET.resolveTemplate("org_name", APPKEY.split("#")[0])
					.resolveTemplate("app_name", APPKEY.split("#")[1]).path(userName);

			objectNode = JerseyUtils.sendRequest(webTarget, dataObjectNode, credential, HTTPMethod.METHOD_PUT, null);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return objectNode;
	}

	public ObjectNode addFriendSingle(String ownerUserName, String friendUserName) {
		ObjectNode objectNode = factory.objectNode();

		// check appKey format
		if (!JerseyUtils.match("^(?!-)[0-9a-zA-Z\\-]+#[0-9a-zA-Z]+", APPKEY)) {
			LOGGER.error("Bad format of Appkey: " + APPKEY);

			objectNode.put("message", "Bad format of Appkey");

			return objectNode;
		}

		if (StringUtils.isEmpty(ownerUserName)) {
			LOGGER.error("Your userName must be provided，the value is username or uuid of imuser.");

			objectNode.put("message", "Your userName must be provided，the value is username or uuid of imuser.");

			return objectNode;
		}

		if (StringUtils.isEmpty(friendUserName)) {
			LOGGER.error("The userName of friend must be provided，the value is username or uuid of imuser.");

			objectNode.put("message",
					"The userName of friend must be provided，the value is username or uuid of imuser.");

			return objectNode;
		}

		try {
			JerseyWebTarget webTarget = EndPoints.USERS_ADDFRIENDS_TARGET
					.resolveTemplate("org_name", APPKEY.split("#")[0]).resolveTemplate("app_name", APPKEY.split("#")[1])
					.resolveTemplate("ownerUserName", ownerUserName).resolveTemplate("friendUserName", friendUserName);

			objectNode = JerseyUtils.sendRequest(webTarget, null, credential, HTTPMethod.METHOD_POST, null);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return objectNode;
	}

	public ObjectNode deleteFriendSingle(String ownerUserName, String friendUserName) {
		ObjectNode objectNode = factory.objectNode();

		// check appKey format
		if (!JerseyUtils.match("^(?!-)[0-9a-zA-Z\\-]+#[0-9a-zA-Z]+", APPKEY)) {
			LOGGER.error("Bad format of Appkey: " + APPKEY);

			objectNode.put("message", "Bad format of Appkey");

			return objectNode;
		}

		if (StringUtils.isEmpty(ownerUserName)) {
			LOGGER.error("Your userName must be provided，the value is username or uuid of imuser.");

			objectNode.put("message", "Your userName must be provided，the value is username or uuid of imuser.");

			return objectNode;
		}

		if (StringUtils.isEmpty(friendUserName)) {
			LOGGER.error("The userName of friend must be provided，the value is username or uuid of imuser.");

			objectNode.put("message",
					"The userName of friend must be provided，the value is username or uuid of imuser.");

			return objectNode;
		}

		try {

			JerseyWebTarget webTarget = EndPoints.USERS_ADDFRIENDS_TARGET
					.resolveTemplate("org_name", APPKEY.split("#")[0]).resolveTemplate("app_name", APPKEY.split("#")[1])
					.resolveTemplate("ownerUserName", ownerUserName).resolveTemplate("friendUserName", friendUserName);

			objectNode = JerseyUtils.sendRequest(webTarget, null, credential, HTTPMethod.METHOD_DELETE, null);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return objectNode;
	}

	public ObjectNode getFriends(String ownerUserName) {
		ObjectNode objectNode = factory.objectNode();

		// check appKey format
		if (!JerseyUtils.match("^(?!-)[0-9a-zA-Z\\-]+#[0-9a-zA-Z]+", APPKEY)) {
			LOGGER.error("Bad format of Appkey: " + APPKEY);

			objectNode.put("message", "Bad format of Appkey");

			return objectNode;
		}

		if (StringUtils.isEmpty(ownerUserName)) {
			LOGGER.error("Your userName must be provided，the value is username or uuid of imuser.");

			objectNode.put("message", "Your userName must be provided，the value is username or uuid of imuser.");

			return objectNode;
		}

		try {
			JerseyWebTarget webTarget = EndPoints.USERS_ADDFRIENDS_TARGET
					.resolveTemplate("org_name", APPKEY.split("#")[0]).resolveTemplate("app_name", APPKEY.split("#")[1])
					.resolveTemplate("ownerUserName", ownerUserName).resolveTemplate("friendUserName", "");

			objectNode = JerseyUtils.sendRequest(webTarget, null, credential, HTTPMethod.METHOD_GET, null);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return objectNode;
	}

	public ObjectNode getBlackList(String userName) {
		ObjectNode objectNode = factory.objectNode();

		// check appKey format
		if (!JerseyUtils.match("^(?!-)[0-9a-zA-Z\\-]+#[0-9a-zA-Z]+", APPKEY)) {
			LOGGER.error("Bad format of Appkey: " + APPKEY);

			objectNode.put("message", "Bad format of Appkey");

			return objectNode;
		}

		if (StringUtils.isEmpty(userName)) {
			LOGGER.error("Your userName must be provided，the value is username or uuid of imuser.");

			objectNode.put("message", "Your userName must be provided，the value is username or uuid of imuser.");

			return objectNode;
		}

		try {
			JerseyWebTarget webTarget = EndPoints.USER_BLACKLIST_TARGET
					.resolveTemplate("org_name", APPKEY.split("#")[0]).resolveTemplate("app_name", APPKEY.split("#")[1])
					.resolveTemplate("owner_username", userName).resolveTemplate("blocked_username", "");

			objectNode = JerseyUtils.sendRequest(webTarget, null, credential, HTTPMethod.METHOD_GET, null);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return objectNode;
	}

	public ObjectNode addToBlackList(String userName, Object payload) {
		ObjectNode dataObjectNode = (ObjectNode) payload;
		ObjectNode objectNode = factory.objectNode();

		// check appKey format
		if (!JerseyUtils.match("^(?!-)[0-9a-zA-Z\\-]+#[0-9a-zA-Z]+", APPKEY)) {
			LOGGER.error("Bad format of Appkey: " + APPKEY);
			objectNode.put("message", "Bad format of Appkey");
			return objectNode;
		}

		// check the input parameter
		if (StringUtils.isEmpty(userName)) {
			LOGGER.error("Your userName must be provided，the value is username or uuid of imuser.");
			objectNode.put("message", "Your userName must be provided，the value is username or uuid of imuser.");
			return objectNode;
		}

		JerseyWebTarget webTarget = EndPoints.USER_BLACKLIST_TARGET.resolveTemplate("org_name", APPKEY.split("#")[0])
				.resolveTemplate("app_name", APPKEY.split("#")[1]).resolveTemplate("owner_username", userName)
				.resolveTemplate("blocked_username", "");
		objectNode = JerseyUtils.sendRequest(webTarget, dataObjectNode, credential, HTTPMethod.METHOD_POST, null);

		return objectNode;
	}

	public ObjectNode removeFromBlackList(String userName, String blackListName) {
		ObjectNode objectNode = factory.objectNode();

		// check appKey format
		if (!JerseyUtils.match("^(?!-)[0-9a-zA-Z\\-]+#[0-9a-zA-Z]+", APPKEY)) {
			LOGGER.error("Bad format of Appkey: " + APPKEY);
			objectNode.put("message", "Bad format of Appkey");
			return objectNode;
		}

		// check the input parameter
		if (StringUtils.isEmpty(userName)) {
			LOGGER.error("Your userName must be provided，the value is username or uuid of imuser.");
			objectNode.put("message", "Your userName must be provided，the value is username or uuid of imuser.");
			return objectNode;
		}
		if (StringUtils.isEmpty(blackListName)) {
			LOGGER.error("Your blackListName must be provided，the value is blackListName or uuid of imuser.");
			objectNode.put("message",
					"Your blackListName must be provided，the value is blackListName or uuid of imuser.");
			return objectNode;
		}

		JerseyWebTarget webTarget = EndPoints.USER_BLACKLIST_TARGET.resolveTemplate("org_name", APPKEY.split("#")[0])
				.resolveTemplate("app_name", APPKEY.split("#")[1]).resolveTemplate("owner_username", userName)
				.resolveTemplate("blocked_username", blackListName);
		objectNode = JerseyUtils.sendRequest(webTarget, null, credential, HTTPMethod.METHOD_DELETE, null);

		return objectNode;
	}

	public ObjectNode getIMUserStatus(String userName) {
		ObjectNode objectNode = factory.objectNode();

		// check appKey format
		if (!JerseyUtils.match("^(?!-)[0-9a-zA-Z\\-]+#[0-9a-zA-Z]+", APPKEY)) {
			LOGGER.error("Bad format of Appkey: " + APPKEY);
			objectNode.put("message", "Bad format of Appkey");
			return objectNode;
		}

		// check the input parameter
		if (StringUtils.isEmpty(userName)) {
			LOGGER.error("Your userName must be provided，the value is username or uuid of imuser.");
			objectNode.put("message", "Your userName must be provided，the value is username or uuid of imuser.");
			return objectNode;
		}

		JerseyWebTarget webTarget = EndPoints.USERS_STATUS_TARGET.resolveTemplate("org_name", APPKEY.split("#")[0])
				.resolveTemplate("app_name", APPKEY.split("#")[1]).resolveTemplate("username", userName);
		objectNode = JerseyUtils.sendRequest(webTarget, null, credential, HTTPMethod.METHOD_GET, null);

		return objectNode;
	}

	public ObjectNode getOfflineMsgCount(String userName) {
		ObjectNode objectNode = factory.objectNode();

		// check appKey format
		if (!JerseyUtils.match("^(?!-)[0-9a-zA-Z\\-]+#[0-9a-zA-Z]+", APPKEY)) {
			LOGGER.error("Bad format of Appkey: " + APPKEY);
			objectNode.put("message", "Bad format of Appkey");
			return objectNode;
		}

		// check the input parameter
		if (StringUtils.isEmpty(userName)) {
			LOGGER.error("Your userName must be provided，the value is username or uuid of imuser.");
			objectNode.put("message", "Your userName must be provided，the value is username or uuid of imuser.");
			return objectNode;
		}

		JerseyWebTarget webTarget = EndPoints.USERS_OFFLINE_MSG_COUNT_TARGET
				.resolveTemplate("org_name", APPKEY.split("#")[0]).resolveTemplate("app_name", APPKEY.split("#")[1])
				.resolveTemplate("owner_username", userName);
		objectNode = JerseyUtils.sendRequest(webTarget, null, credential, HTTPMethod.METHOD_GET, null);

		return objectNode;
	}

	public ObjectNode getSpecifiedOfflineMsgStatus(String userName, String msgId) {
		ObjectNode objectNode = factory.objectNode();

		// check appKey format
		if (!JerseyUtils.match("^(?!-)[0-9a-zA-Z\\-]+#[0-9a-zA-Z]+", APPKEY)) {
			LOGGER.error("Bad format of Appkey: " + APPKEY);
			objectNode.put("message", "Bad format of Appkey");
			return objectNode;
		}

		// check the input parameter
		if (StringUtils.isEmpty(userName)) {
			LOGGER.error("Your userName must be provided，the value is username or uuid of imuser.");
			objectNode.put("message", "Your userName must be provided，the value is username or uuid of imuser.");
			return objectNode;
		}
		if (StringUtils.isEmpty(msgId)) {
			LOGGER.error("Your msgId must be provided.");
			objectNode.put("message", "Your msgId must be provided.");
			return objectNode;
		}

		JerseyWebTarget webTarget = EndPoints.USERS_OFFLINE_MSG_STATUS_TARGET
				.resolveTemplate("org_name", APPKEY.split("#")[0]).resolveTemplate("app_name", APPKEY.split("#")[1])
				.resolveTemplate("username", userName).resolveTemplate("msg_id", msgId);
		objectNode = JerseyUtils.sendRequest(webTarget, null, credential, HTTPMethod.METHOD_GET, null);

		return objectNode;
	}

	public ObjectNode deactivateIMUser(String userName) {
		ObjectNode objectNode = factory.objectNode();

		// check appKey format
		if (!JerseyUtils.match("^(?!-)[0-9a-zA-Z\\-]+#[0-9a-zA-Z]+", APPKEY)) {
			LOGGER.error("Bad format of Appkey: " + APPKEY);
			objectNode.put("message", "Bad format of Appkey");
			return objectNode;
		}

		// check the input parameter
		if (StringUtils.isEmpty(userName)) {
			LOGGER.error("Your userName must be provided，the value is username or uuid of imuser.");
			objectNode.put("message", "Your userName must be provided，the value is username or uuid of imuser.");
			return objectNode;
		}

		JerseyWebTarget webTarget = EndPoints.USERS_DEACTIVATE_TARGET.resolveTemplate("org_name", APPKEY.split("#")[0])
				.resolveTemplate("app_name", APPKEY.split("#")[1]).resolveTemplate("username", userName);
		objectNode = JerseyUtils.sendRequest(webTarget, null, credential, HTTPMethod.METHOD_POST, null);

		return objectNode;
	}

	public ObjectNode activateIMUser(String userName) {
		ObjectNode objectNode = factory.objectNode();

		// check appKey format
		if (!JerseyUtils.match("^(?!-)[0-9a-zA-Z\\-]+#[0-9a-zA-Z]+", APPKEY)) {
			LOGGER.error("Bad format of Appkey: " + APPKEY);
			objectNode.put("message", "Bad format of Appkey");
			return objectNode;
		}

		// check the input parameter
		if (StringUtils.isEmpty(userName)) {
			LOGGER.error("Your userName must be provided，the value is username or uuid of imuser.");
			objectNode.put("message", "Your userName must be provided，the value is username or uuid of imuser.");
			return objectNode;
		}

		JerseyWebTarget webTarget = EndPoints.USERS_ACTIVATE_TARGET.resolveTemplate("org_name", APPKEY.split("#")[0])
				.resolveTemplate("app_name", APPKEY.split("#")[1]).resolveTemplate("username", userName);
		objectNode = JerseyUtils.sendRequest(webTarget, null, credential, HTTPMethod.METHOD_POST, null);

		return objectNode;
	}

	public ObjectNode disconnectIMUser(String userName) {
		ObjectNode objectNode = factory.objectNode();

		// check appKey format
		if (!JerseyUtils.match("^(?!-)[0-9a-zA-Z\\-]+#[0-9a-zA-Z]+", APPKEY)) {
			LOGGER.error("Bad format of Appkey: " + APPKEY);
			objectNode.put("message", "Bad format of Appkey");
			return objectNode;
		}

		// check the input parameter
		if (StringUtils.isEmpty(userName)) {
			LOGGER.error("Your userName must be provided，the value is username or uuid of imuser.");
			objectNode.put("message", "Your userName must be provided，the value is username or uuid of imuser.");
			return objectNode;
		}

		JerseyWebTarget webTarget = EndPoints.USERS_DISCONNECT_TARGET.resolveTemplate("org_name", APPKEY.split("#")[0])
				.resolveTemplate("app_name", APPKEY.split("#")[1]).resolveTemplate("username", userName);
		objectNode = JerseyUtils.sendRequest(webTarget, null, credential, HTTPMethod.METHOD_GET, null);

		return objectNode;
	}
	
	public ObjectNode getIMUserAllChatGroups(String userName) {
		ObjectNode objectNode = factory.objectNode();

		// check appKey format
		if (!JerseyUtils.match("^(?!-)[0-9a-zA-Z\\-]+#[0-9a-zA-Z]+", APPKEY)) {
			LOGGER.error("Bad format of Appkey: " + APPKEY);
			objectNode.put("message", "Bad format of Appkey");
			return objectNode;
		}

		// check the input parameter
		if (StringUtils.isEmpty(userName)) {
			LOGGER.error("Your userName must be provided，the value is username or uuid of imuser.");
			objectNode.put("message", "Your userName must be provided，the value is username or uuid of imuser.");
			return objectNode;
		}

		JerseyWebTarget webTarget = EndPoints.USERS_CHATGROUPS_TARGET.resolveTemplate("org_name", APPKEY.split("#")[0])
				.resolveTemplate("app_name", APPKEY.split("#")[1]).resolveTemplate("username", userName);
		objectNode = JerseyUtils.sendRequest(webTarget, null, credential, HTTPMethod.METHOD_GET, null);

		return objectNode;
	}
	
	public ObjectNode getIMUserAllChatRooms(String userName) {
		ObjectNode objectNode = factory.objectNode();

		// check appKey format
		if (!JerseyUtils.match("^(?!-)[0-9a-zA-Z\\-]+#[0-9a-zA-Z]+", APPKEY)) {
			LOGGER.error("Bad format of Appkey: " + APPKEY);
			objectNode.put("message", "Bad format of Appkey");
			return objectNode;
		}

		// check the input parameter
		if (StringUtils.isEmpty(userName)) {
			LOGGER.error("Your userName must be provided，the value is username or uuid of imuser.");
			objectNode.put("message", "Your userName must be provided，the value is username or uuid of imuser.");
			return objectNode;
		}

		JerseyWebTarget webTarget = EndPoints.USERS_CHATROOMS_TARGET.resolveTemplate("org_name", APPKEY.split("#")[0])
				.resolveTemplate("app_name", APPKEY.split("#")[1]).resolveTemplate("username", userName);
		objectNode = JerseyUtils.sendRequest(webTarget, null, credential, HTTPMethod.METHOD_GET, null);

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
	private ObjectNode createNewIMUserBatchGen(String usernamePrefix, Long perNumber, Long totalNumber) {
		ObjectNode objectNode = factory.objectNode();

		System.out.println("你即将注册" + totalNumber + "个用户，如果大于" + perNumber + "了,会分批注册,每次注册" + perNumber + "个");

		ArrayNode genericArrayNode = this.genericArrayNode(usernamePrefix, totalNumber);
		if (totalNumber <= perNumber) {
			objectNode = this.createNewIMUserBatch(genericArrayNode);
		} else {

			for (int i = 0; i < genericArrayNode.size(); i++) {
				ArrayNode tmpArrayNode = factory.arrayNode();
				tmpArrayNode.add(genericArrayNode.get(i));
				// 300 records on one migration
				if ((i + 1) % perNumber == 0) {
					objectNode = this.createNewIMUserBatch(tmpArrayNode);
					tmpArrayNode.removeAll();
					continue;
				}

				// the rest records that less than the times of 300
				if (i > (genericArrayNode.size() / perNumber * perNumber - 1)) {
					objectNode = this.createNewIMUserBatch(tmpArrayNode);
					tmpArrayNode.removeAll();
				}
			}
		}

		return objectNode;
	}
	

	/**
	 * 指定前缀和数量生成用户基本数据
	 * 
	 * @param usernamePrefix
	 * @param number
	 * @return
	 */
	private ArrayNode genericArrayNode(String usernamePrefix, Long number) {
		ArrayNode arrayNode = factory.arrayNode();
		for (int i = 0; i < number; i++) {
			ObjectNode userNode = factory.objectNode();
			userNode.put("username", usernamePrefix + "_" + i);
			userNode.put("password", Constants.DEFAULT_PASSWORD);

			arrayNode.add(userNode);
		}

		return arrayNode;
	}

	
	

}
