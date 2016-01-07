package com.easemob.server.example.jersey.apidemo;

import org.apache.commons.lang3.StringUtils;
import org.glassfish.jersey.client.JerseyWebTarget;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.easemob.server.example.api.ChatGroupAPI;
import com.easemob.server.example.api.IMUserAPI;
import com.easemob.server.example.comm.Constants;
import com.easemob.server.example.comm.HTTPMethod;
import com.easemob.server.example.comm.Roles;
import com.easemob.server.example.jersey.utils.JerseyUtils;
import com.easemob.server.example.jersey.vo.ClientSecretCredential;
import com.easemob.server.example.jersey.vo.Credential;
import com.easemob.server.example.jersey.vo.EndPoints;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * REST API Demo : 群组管理 Jersey2.9实现
 * 
 * Doc URL:
 * https://docs.easemob.com/doku.php?id=start:100serverintegration:60groupmgmt
 * 
 * @author Lynch 2014-09-12 Eric23 2016-01-05
 * 
 */
public class EasemobChatGroups implements ChatGroupAPI {

	private static final Logger LOGGER = LoggerFactory.getLogger(EasemobChatGroups.class);
	private static final JsonNodeFactory factory = new JsonNodeFactory(false);
	private static final String APPKEY = Constants.APPKEY;

	private static Credential credential = new ClientSecretCredential(Constants.APP_CLIENT_ID,
			Constants.APP_CLIENT_SECRET, Roles.USER_ROLE_APPADMIN);

	public static void main(String[] args) {
		ChatGroupAPI chatGroupAPIImpl = new EasemobChatGroups();
		IMUserAPI imUserAPIImpl = new EasemobIMUsers();

		// 获取app中所有群组
		Object allChatGroups = chatGroupAPIImpl.getChatGroups(null, null);
		LOGGER.info("获取app中所有群组: " + allChatGroups.toString());
		
		// 分页获取app下的群组
		Object top20ChatGroups = chatGroupAPIImpl.getChatGroups(20L, null);
		LOGGER.info("获取前20个的群组: " + top20ChatGroups.toString());
		String cursor = null;
		if( null != top20ChatGroups ) {
			cursor = ((ObjectNode)top20ChatGroups).path("cursor").asText();
			if( StringUtils.isNotBlank(cursor) ) {
				Object next20ChatGroups = chatGroupAPIImpl.getChatGroups(20L, cursor);
				LOGGER.info("获取后20个的群组: " + next20ChatGroups.toString());
			}
		}
		
		// 获取一个或者多个群组的详情
		Object chatGroupDetails = chatGroupAPIImpl.getChatGroupDetails(new String[] {"103630409828401628", "103631015775306272"});
		LOGGER.info("获取一个或者多个群组的详情: " + chatGroupDetails);
		
		// 创建一个群组
		ObjectNode newChatGroupNode = factory.objectNode();
		newChatGroupNode.put("groupname", "DemoGroup");
		newChatGroupNode.put("desc", "This is a demo group.");
		newChatGroupNode.put("public", true);
		newChatGroupNode.put("owner", "18268733716");
		newChatGroupNode.put("members", factory.arrayNode().add("18268733715"));
		Object createChatGroup = chatGroupAPIImpl.createChatGroup(newChatGroupNode);
		LOGGER.info("创建一个群组: " + createChatGroup);
		
		// 修改群组信息
		ObjectNode updateChatGroupNode = factory.objectNode();
		updateChatGroupNode.put("groupname", "UpdatedDemoGroup");
		Object updateChatGroup = chatGroupAPIImpl.modifyChatGroup("103630409828401628", updateChatGroupNode);
		LOGGER.info("修改群组信息: " + updateChatGroup);
		
		// 删除群组
		Object deleteChatGroup = chatGroupAPIImpl.deleteChatGroup("103630409828401628");
		LOGGER.info("删除群组: " + deleteChatGroup);
		
		// 获取群组中的所有成员
		Object getAllUsersOfChatGroup = chatGroupAPIImpl.getChatGroupUsers("103631015775306272");
		LOGGER.info("获取群组中的所有成员: " + getAllUsersOfChatGroup);
		
		// 群组加人[单个]
		Object addSingleUserToChatGroup = chatGroupAPIImpl.addSingleUserToChatGroup("103631015775306272", "18268733716");
		LOGGER.info("群组加人[单个]: " + addSingleUserToChatGroup);
		
		// 群组加人[批量]
		Object addBatchUsersToChatGroup = chatGroupAPIImpl.addBatchUsersToChatGroup("103631015775306272", new String[] {"18268733715"});
		LOGGER.info("群组加人[批量]: " + addBatchUsersToChatGroup);
		
		// 群组减人[单个]
		Object removeSingleUserFromChatGroup = chatGroupAPIImpl.removeSingleUserFromChatGroup("103631015775306272", "18268733716");
		LOGGER.info("群组减人[单个]: " + removeSingleUserFromChatGroup);
		
		// 群组减人[批量]
		Object removeBatchUsersFromChatGroup = chatGroupAPIImpl.removeBatchUsersFromChatGroup("103631015775306272", new String[] {"18268733715"});
		LOGGER.info("群组减人[批量]: " + removeBatchUsersFromChatGroup);
		
		// 获取一个用户参与的所有群组
		Object getUserAllChatGroups = imUserAPIImpl.getIMUserAllChatGroups("18268733715");
		LOGGER.info("获取一个用户参与的所有群组: " + getUserAllChatGroups);
		
		// 群组转让
		Object transferChatGroupOwner = chatGroupAPIImpl.transferChatGroupOwner("103631015775306272", "18268733715");
		LOGGER.info("群组转让: " + transferChatGroupOwner);
		
		// 查询群组黑名单
		Object getChatGroupBlockUsers = chatGroupAPIImpl.getChatGroupBlockUsers("103631015775306272");
		LOGGER.info("查询群组黑名单: " + getChatGroupBlockUsers);
		
		// 群组黑名单个添加
		Object addSingleUserToChatGroupBlockList = chatGroupAPIImpl.addSingleBlockUserToChatGroup("103631015775306272", "18268733716");
		LOGGER.info("群组黑名单个添加: " + addSingleUserToChatGroupBlockList);
		
		// 群组黑名单批量添加
		Object addBatchUsersToChatGroupBlockList = chatGroupAPIImpl.addBatchBlockUsersToChatGroup("103631015775306272", new String[] {"18268733715"});
		LOGGER.info("群组黑名单批量添加: " + addBatchUsersToChatGroupBlockList);
		
		// 群组黑名单单个删除
		Object removeSingleUserFromChatGroupBlockList = chatGroupAPIImpl.removeSingleBlockUserFromChatGroup("103631015775306272", "18268733716");
		LOGGER.info("群组黑名单单个删除: " + removeSingleUserFromChatGroupBlockList);
		
		// 群组黑名单批量删除
		Object removeBatchUsersFromChatGroupBlockList = chatGroupAPIImpl.removeBatchBlockUsersFromChatGroup("103631015775306272", new String[] {"18268733715"});
		LOGGER.info("群组黑名单批量删除: " + removeBatchUsersFromChatGroupBlockList);
	}

	public ObjectNode getChatGroups(Long limit, String cursor) {
		ObjectNode objectNode = factory.objectNode();

		// check appKey format
		if (!JerseyUtils.match("^(?!-)[0-9a-zA-Z\\-]+#[0-9a-zA-Z]+", APPKEY)) {
			LOGGER.error("Bad format of Appkey: " + APPKEY);
			objectNode.put("message", "Bad format of Appkey");
			return objectNode;
		}

		JerseyWebTarget webTarget = EndPoints.CHATGROUPS_TARGET.resolveTemplate("org_name", APPKEY.split("#")[0])
				.resolveTemplate("app_name", APPKEY.split("#")[1]);
		if (null != limit && limit > 0L) {
			webTarget = webTarget.queryParam("limit", limit);
		}
		if (StringUtils.isNoneBlank(cursor)) {
			webTarget = webTarget.queryParam("cursor", cursor);
		}

		objectNode = JerseyUtils.sendRequest(webTarget, null, credential, HTTPMethod.METHOD_GET, null);

		return objectNode;
	}

	public ObjectNode getChatGroupDetails(String[] groupIds) {
		ObjectNode objectNode = factory.objectNode();

		// check appKey format
		if (!JerseyUtils.match("^(?!-)[0-9a-zA-Z\\-]+#[0-9a-zA-Z]+", APPKEY)) {
			LOGGER.error("Bad format of Appkey: " + APPKEY);
			objectNode.put("message", "Bad format of Appkey");
			return objectNode;
		}

		// check the input parameter
		if (null == groupIds || 0 == groupIds.length) {
			LOGGER.error("GroupIds should not be null or empty.");
			objectNode.put("message", "GroupIds should not be null or empty.");
			return objectNode;
		}

		JerseyWebTarget webTarget = EndPoints.CHATGROUPS_TARGET.resolveTemplate("org_name", APPKEY.split("#")[0])
				.resolveTemplate("app_name", APPKEY.split("#")[1]).path(StringUtils.join(groupIds, ","));
		objectNode = JerseyUtils.sendRequest(webTarget, null, credential, HTTPMethod.METHOD_GET, null);

		return objectNode;
	}

	public ObjectNode createChatGroup(Object payload) {
		ObjectNode objectNode = factory.objectNode();
		ObjectNode dataObjectNode = (ObjectNode) payload;

		// check appKey format
		if (!JerseyUtils.match("^(?!-)[0-9a-zA-Z\\-]+#[0-9a-zA-Z]+", APPKEY)) {
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
		if (!dataObjectNode.has("owner")) {
			LOGGER.error("Property that named owner must be provided .");
			objectNode.put("message", "Property that named owner must be provided .");
			return objectNode;
		}
		if (dataObjectNode.has("members") && (!dataObjectNode.path("members").isArray() || 0 == dataObjectNode.path("members").size())) {
			LOGGER.error("Property that named members should be an array with elements.");
			objectNode.put("message", "Property that named members should be an array with elements.");
			return objectNode;
		}

		try {

			JerseyWebTarget webTarget = EndPoints.CHATGROUPS_TARGET.resolveTemplate("org_name", APPKEY.split("#")[0])
					.resolveTemplate("app_name", APPKEY.split("#")[1]);

			objectNode = JerseyUtils.sendRequest(webTarget, dataObjectNode, credential, HTTPMethod.METHOD_POST, null);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return objectNode;
	}

	public ObjectNode modifyChatGroup(String groupId, Object payload) {
		ObjectNode objectNode = factory.objectNode();
		ObjectNode dataObjectNode = (ObjectNode) payload;

		// check appKey format
		if (!JerseyUtils.match("^(?!-)[0-9a-zA-Z\\-]+#[0-9a-zA-Z]+", APPKEY)) {
			LOGGER.error("Bad format of Appkey: " + APPKEY);
			objectNode.put("message", "Bad format of Appkey");
			return objectNode;
		}
		
		// check properties that must be provided
		if (dataObjectNode.has("groupname") && StringUtils.isBlank(dataObjectNode.path("groupname").asText())) {
			LOGGER.error("Property groupname provided should not be null or empty.");
			objectNode.put("message", "Property groupname provided should not be null or empty.");
			return objectNode;
		}
		if (dataObjectNode.has("desc") && StringUtils.isBlank(dataObjectNode.path("desc").asText())) {
			LOGGER.error("Property desc provided should not be null or empty.");
			objectNode.put("message", "Property desc provided should not be null or empty.");
			return objectNode;
		}
		
		JerseyWebTarget webTarget = EndPoints.CHATGROUPS_TARGET.resolveTemplate("org_name", APPKEY.split("#")[0])
				.resolveTemplate("app_name", APPKEY.split("#")[1]).path(groupId);

		objectNode = JerseyUtils.sendRequest(webTarget, dataObjectNode, credential, HTTPMethod.METHOD_PUT, null);
		
		return objectNode;
	}

	public ObjectNode deleteChatGroup(String groupId) {
		ObjectNode objectNode = factory.objectNode();

		// check appKey format
		if (!JerseyUtils.match("^(?!-)[0-9a-zA-Z\\-]+#[0-9a-zA-Z]+", APPKEY)) {
			LOGGER.error("Bad format of Appkey: " + APPKEY);
			objectNode.put("message", "Bad format of Appkey");
			return objectNode;
		}
		
		// check the input parameter
		if(StringUtils.isBlank(groupId)) {
			LOGGER.error("Property that named groupId must be provided.");
			objectNode.put("message", "Property that named groupId must be provided.");
			return objectNode;
		}

		try {
			JerseyWebTarget webTarget = EndPoints.CHATGROUPS_TARGET.resolveTemplate("org_name", APPKEY.split("#")[0])
					.resolveTemplate("app_name", APPKEY.split("#")[1]).path(groupId);

			objectNode = JerseyUtils.sendRequest(webTarget, null, credential, HTTPMethod.METHOD_DELETE, null);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return objectNode;
	}

	public ObjectNode getChatGroupUsers(String groupId) {
		ObjectNode objectNode = factory.objectNode();

		// check appKey format
		if (!JerseyUtils.match("^(?!-)[0-9a-zA-Z\\-]+#[0-9a-zA-Z]+", APPKEY)) {
			LOGGER.error("Bad format of Appkey: " + APPKEY);
			objectNode.put("message", "Bad format of Appkey");
			return objectNode;
		}
		
		// check the input parameter
		if(StringUtils.isBlank(groupId)) {
			LOGGER.error("Property that named groupId must be provided.");
			objectNode.put("message", "Property that named groupId must be provided.");
			return objectNode;
		}

		try {

			JerseyWebTarget webTarget = EndPoints.CHATGROUPS_USERS_TARGET.resolveTemplate("org_name", APPKEY.split("#")[0])
					.resolveTemplate("app_name", APPKEY.split("#")[1]).resolveTemplate("group_id", groupId);

			objectNode = JerseyUtils.sendRequest(webTarget, null, credential, HTTPMethod.METHOD_GET, null);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return objectNode;
	}

	public ObjectNode addSingleUserToChatGroup(String groupId, String userId) {
		ObjectNode objectNode = factory.objectNode();

		// check appKey format
		if (!JerseyUtils.match("^(?!-)[0-9a-zA-Z\\-]+#[0-9a-zA-Z]+", APPKEY)) {
			LOGGER.error("Bad format of Appkey: " + APPKEY);
			objectNode.put("message", "Bad format of Appkey");
			return objectNode;
		}
		
		// check the input parameter
		if(StringUtils.isBlank(groupId)) {
			LOGGER.error("Property that named groupId must be provided.");
			objectNode.put("message", "Property that named groupId must be provided.");
			return objectNode;
		}
		if(StringUtils.isBlank(userId)) {
			LOGGER.error("Property that named userId must be provided.");
			objectNode.put("message", "Property that named userId must be provided.");
			return objectNode;
		}

		try {

			JerseyWebTarget webTarget = EndPoints.CHATGROUPS_USERS_TARGET.resolveTemplate("org_name", APPKEY.split("#")[0])
					.resolveTemplate("app_name", APPKEY.split("#")[1]).resolveTemplate("group_id", groupId).path(userId);

			objectNode = JerseyUtils.sendRequest(webTarget, null, credential, HTTPMethod.METHOD_POST, null);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return objectNode;
	}

	public ObjectNode addBatchUsersToChatGroup(String groupId, String[] userIds) {
		ObjectNode objectNode = factory.objectNode();
		ObjectNode dataNode = factory.objectNode();
		
		// check appKey format
		if (!JerseyUtils.match("^(?!-)[0-9a-zA-Z\\-]+#[0-9a-zA-Z]+", APPKEY)) {
			LOGGER.error("Bad format of Appkey: " + APPKEY);
			objectNode.put("message", "Bad format of Appkey");
			return objectNode;
		}
		
		// check the input parameter
		if(StringUtils.isBlank(groupId)) {
			LOGGER.error("Property that named groupId must be provided.");
			objectNode.put("message", "Property that named groupId must be provided.");
			return objectNode;
		}
		if(null == userIds || 0 == userIds.length) {
			LOGGER.error("Property that named userIds must be provided.");
			objectNode.put("message", "Property that named userIds must be provided.");
			return objectNode;
		}

		try {
			JerseyWebTarget webTarget = EndPoints.CHATGROUPS_USERS_TARGET.resolveTemplate("org_name", APPKEY.split("#")[0])
					.resolveTemplate("app_name", APPKEY.split("#")[1]).resolveTemplate("group_id", groupId);
			
			dataNode.put("usernames", JerseyUtils.buildTarget(userIds));
			objectNode = JerseyUtils.sendRequest(webTarget, dataNode, credential, HTTPMethod.METHOD_POST, null);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return objectNode;
	}

	public ObjectNode removeSingleUserFromChatGroup(String groupId, String userId) {
		ObjectNode objectNode = factory.objectNode();

		// check appKey format
		if (!JerseyUtils.match("^(?!-)[0-9a-zA-Z\\-]+#[0-9a-zA-Z]+", APPKEY)) {
			LOGGER.error("Bad format of Appkey: " + APPKEY);
			objectNode.put("message", "Bad format of Appkey");
			return objectNode;
		}

		// check the input parameter
		if(StringUtils.isBlank(groupId)) {
			LOGGER.error("Property that named groupId must be provided.");
			objectNode.put("message", "Property that named groupId must be provided.");
			return objectNode;
		}
		if(StringUtils.isBlank(userId)) {
			LOGGER.error("Property that named userId must be provided.");
			objectNode.put("message", "Property that named userId must be provided.");
			return objectNode;
		}

		try {

			JerseyWebTarget webTarget = EndPoints.CHATGROUPS_USERS_TARGET.resolveTemplate("org_name", APPKEY.split("#")[0])
					.resolveTemplate("app_name", APPKEY.split("#")[1]).resolveTemplate("group_id", groupId).path(userId);

			objectNode = JerseyUtils.sendRequest(webTarget, null, credential, HTTPMethod.METHOD_DELETE, null);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return objectNode;
	}

	public ObjectNode removeBatchUsersFromChatGroup(String groupId, String[] userIds) {
		ObjectNode objectNode = factory.objectNode();
		
		// check appKey format
		if (!JerseyUtils.match("^(?!-)[0-9a-zA-Z\\-]+#[0-9a-zA-Z]+", APPKEY)) {
			LOGGER.error("Bad format of Appkey: " + APPKEY);
			objectNode.put("message", "Bad format of Appkey");
			return objectNode;
		}
		
		// check the input parameter
		if(StringUtils.isBlank(groupId)) {
			LOGGER.error("Property that named groupId must be provided.");
			objectNode.put("message", "Property that named groupId must be provided.");
			return objectNode;
		}
		if(null == userIds || 0 == userIds.length) {
			LOGGER.error("Property that named userIds must be provided.");
			objectNode.put("message", "Property that named userIds must be provided.");
			return objectNode;
		}

		try {
			JerseyWebTarget webTarget = EndPoints.CHATGROUPS_USERS_TARGET.resolveTemplate("org_name", APPKEY.split("#")[0])
					.resolveTemplate("app_name", APPKEY.split("#")[1]).resolveTemplate("group_id", groupId).path(StringUtils.join(userIds, ","));
			
			objectNode = JerseyUtils.sendRequest(webTarget, null, credential, HTTPMethod.METHOD_DELETE, null);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return objectNode;
	}

	public ObjectNode transferChatGroupOwner(String groupId, String newOwnerId) {
		ObjectNode objectNode = factory.objectNode();
		ObjectNode dataObject = factory.objectNode();

		// check appKey format
		if (!JerseyUtils.match("^(?!-)[0-9a-zA-Z\\-]+#[0-9a-zA-Z]+", APPKEY)) {
			LOGGER.error("Bad format of Appkey: " + APPKEY);
			objectNode.put("message", "Bad format of Appkey");
			return objectNode;
		}

		// check the input parameter
		if(StringUtils.isBlank(groupId)) {
			LOGGER.error("Property that named groupId must be provided.");
			objectNode.put("message", "Property that named groupId must be provided.");
			return objectNode;
		}
		if(StringUtils.isBlank(newOwnerId)) {
			LOGGER.error("Property that named newOwnerId must be provided.");
			objectNode.put("message", "Property that named newOwnerId must be provided.");
			return objectNode;
		}

		try {

			JerseyWebTarget webTarget = EndPoints.CHATGROUPS_TARGET.resolveTemplate("org_name", APPKEY.split("#")[0])
					.resolveTemplate("app_name", APPKEY.split("#")[1]).path(groupId);

			dataObject.put("newowner", newOwnerId);
			objectNode = JerseyUtils.sendRequest(webTarget, dataObject, credential, HTTPMethod.METHOD_PUT, null);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return objectNode;
	}

	public ObjectNode getChatGroupBlockUsers(String groupId) {
		ObjectNode objectNode = factory.objectNode();

		// check appKey format
		if (!JerseyUtils.match("^(?!-)[0-9a-zA-Z\\-]+#[0-9a-zA-Z]+", APPKEY)) {
			LOGGER.error("Bad format of Appkey: " + APPKEY);
			objectNode.put("message", "Bad format of Appkey");
			return objectNode;
		}
		
		// check the input parameter
		if(StringUtils.isBlank(groupId)) {
			LOGGER.error("Property that named groupId must be provided.");
			objectNode.put("message", "Property that named groupId must be provided.");
			return objectNode;
		}

		try {
			JerseyWebTarget webTarget = EndPoints.CHATGROUPS_BLOCK_USERS_TARGET.resolveTemplate("org_name", APPKEY.split("#")[0])
					.resolveTemplate("app_name", APPKEY.split("#")[1]).resolveTemplate("group_id", groupId);

			objectNode = JerseyUtils.sendRequest(webTarget, null, credential, HTTPMethod.METHOD_GET, null);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return objectNode;
	}

	public ObjectNode addSingleBlockUserToChatGroup(String groupId, String userId) {
		ObjectNode objectNode = factory.objectNode();

		// check appKey format
		if (!JerseyUtils.match("^(?!-)[0-9a-zA-Z\\-]+#[0-9a-zA-Z]+", APPKEY)) {
			LOGGER.error("Bad format of Appkey: " + APPKEY);
			objectNode.put("message", "Bad format of Appkey");
			return objectNode;
		}
		
		// check the input parameter
		if(StringUtils.isBlank(groupId)) {
			LOGGER.error("Property that named groupId must be provided.");
			objectNode.put("message", "Property that named groupId must be provided.");
			return objectNode;
		}
		if(StringUtils.isBlank(userId)) {
			LOGGER.error("Property that named userId must be provided.");
			objectNode.put("message", "Property that named userId must be provided.");
			return objectNode;
		}

		try {

			JerseyWebTarget webTarget = EndPoints.CHATGROUPS_BLOCK_USERS_TARGET.resolveTemplate("org_name", APPKEY.split("#")[0])
					.resolveTemplate("app_name", APPKEY.split("#")[1]).resolveTemplate("group_id", groupId).path(userId);

			objectNode = JerseyUtils.sendRequest(webTarget, null, credential, HTTPMethod.METHOD_POST, null);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return objectNode;
	}

	public ObjectNode addBatchBlockUsersToChatGroup(String groupId, String[] userIds) {
		ObjectNode objectNode = factory.objectNode();
		ObjectNode dataNode = factory.objectNode();
		
		// check appKey format
		if (!JerseyUtils.match("^(?!-)[0-9a-zA-Z\\-]+#[0-9a-zA-Z]+", APPKEY)) {
			LOGGER.error("Bad format of Appkey: " + APPKEY);
			objectNode.put("message", "Bad format of Appkey");
			return objectNode;
		}
		
		// check the input parameter
		if(StringUtils.isBlank(groupId)) {
			LOGGER.error("Property that named groupId must be provided.");
			objectNode.put("message", "Property that named groupId must be provided.");
			return objectNode;
		}
		if(null == userIds || 0 == userIds.length) {
			LOGGER.error("Property that named userIds must be provided.");
			objectNode.put("message", "Property that named userIds must be provided.");
			return objectNode;
		}

		try {
			JerseyWebTarget webTarget = EndPoints.CHATGROUPS_BLOCK_USERS_TARGET.resolveTemplate("org_name", APPKEY.split("#")[0])
					.resolveTemplate("app_name", APPKEY.split("#")[1]).resolveTemplate("group_id", groupId);
			
			dataNode.put("usernames", JerseyUtils.buildTarget(userIds));
			objectNode = JerseyUtils.sendRequest(webTarget, dataNode, credential, HTTPMethod.METHOD_POST, null);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return objectNode;
	}

	public ObjectNode removeSingleBlockUserFromChatGroup(String groupId, String userId) {
		ObjectNode objectNode = factory.objectNode();

		// check appKey format
		if (!JerseyUtils.match("^(?!-)[0-9a-zA-Z\\-]+#[0-9a-zA-Z]+", APPKEY)) {
			LOGGER.error("Bad format of Appkey: " + APPKEY);
			objectNode.put("message", "Bad format of Appkey");
			return objectNode;
		}

		// check the input parameter
		if(StringUtils.isBlank(groupId)) {
			LOGGER.error("Property that named groupId must be provided.");
			objectNode.put("message", "Property that named groupId must be provided.");
			return objectNode;
		}
		if(StringUtils.isBlank(userId)) {
			LOGGER.error("Property that named userId must be provided.");
			objectNode.put("message", "Property that named userId must be provided.");
			return objectNode;
		}

		try {

			JerseyWebTarget webTarget = EndPoints.CHATGROUPS_BLOCK_USERS_TARGET.resolveTemplate("org_name", APPKEY.split("#")[0])
					.resolveTemplate("app_name", APPKEY.split("#")[1]).resolveTemplate("group_id", groupId).path(userId);

			objectNode = JerseyUtils.sendRequest(webTarget, null, credential, HTTPMethod.METHOD_DELETE, null);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return objectNode;
	}

	public ObjectNode removeBatchBlockUsersFromChatGroup(String groupId, String[] userIds) {
		ObjectNode objectNode = factory.objectNode();
		
		// check appKey format
		if (!JerseyUtils.match("^(?!-)[0-9a-zA-Z\\-]+#[0-9a-zA-Z]+", APPKEY)) {
			LOGGER.error("Bad format of Appkey: " + APPKEY);
			objectNode.put("message", "Bad format of Appkey");
			return objectNode;
		}
		
		// check the input parameter
		if(StringUtils.isBlank(groupId)) {
			LOGGER.error("Property that named groupId must be provided.");
			objectNode.put("message", "Property that named groupId must be provided.");
			return objectNode;
		}
		if(null == userIds || 0 == userIds.length) {
			LOGGER.error("Property that named userIds must be provided.");
			objectNode.put("message", "Property that named userIds must be provided.");
			return objectNode;
		}

		try {
			JerseyWebTarget webTarget = EndPoints.CHATGROUPS_BLOCK_USERS_TARGET.resolveTemplate("org_name", APPKEY.split("#")[0])
					.resolveTemplate("app_name", APPKEY.split("#")[1]).resolveTemplate("group_id", groupId).path(StringUtils.join(userIds, ","));
			
			objectNode = JerseyUtils.sendRequest(webTarget, null, credential, HTTPMethod.METHOD_DELETE, null);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return objectNode;
	}

}
