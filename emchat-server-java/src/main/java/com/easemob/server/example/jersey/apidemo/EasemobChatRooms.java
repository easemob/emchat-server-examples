package com.easemob.server.example.jersey.apidemo;

import org.apache.commons.lang3.StringUtils;
import org.glassfish.jersey.client.JerseyWebTarget;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.easemob.server.example.api.ChatRoomAPI;
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
 * REST API Demo : 聊天室管理 Jersey2.9实现
 * 
 * Doc URL:
 * https://docs.easemob.com/doku.php?id=start:100serverintegration:60groupmgmt
 * 
 * @author Eric23 2016-01-05
 * 
 */
public class EasemobChatRooms implements ChatRoomAPI {

	private static final Logger LOGGER = LoggerFactory.getLogger(EasemobChatRooms.class);
	private static final JsonNodeFactory factory = new JsonNodeFactory(false);
	private static final String APPKEY = Constants.APPKEY;

	private static Credential credential = new ClientSecretCredential(Constants.APP_CLIENT_ID,
			Constants.APP_CLIENT_SECRET, Roles.USER_ROLE_APPADMIN);

	public static void main(String[] args) {
		ChatRoomAPI chatRoomAPIImpl = new EasemobChatRooms();
		IMUserAPI imUserAPIImpl = new EasemobIMUsers();

		// 获取app中所有聊天室
		Object allChatRooms = chatRoomAPIImpl.getAllChatRooms();
		LOGGER.info("获取app中所有聊天室: " + allChatRooms.toString());
		
		// 获取一个聊天室的详情
		Object chatRoomDetails = chatRoomAPIImpl.getChatRoomDetail("144078918423413240");
		LOGGER.info("获取一个聊天室的详情: " + chatRoomDetails);
		
		// 创建一个聊天室
		ObjectNode newChatRoomNode = factory.objectNode();
		newChatRoomNode.put("name", "DemoRoom");
		newChatRoomNode.put("description", "This is a demo group.");
		newChatRoomNode.put("owner", "18268733716");
		newChatRoomNode.put("members", factory.arrayNode().add("18268733715"));
		Object createChatRoom = chatRoomAPIImpl.createChatRoom(newChatRoomNode);
		LOGGER.info("创建一个聊天室: " + createChatRoom);
		
		// 修改聊天室信息
		ObjectNode updateChatRoomNode = factory.objectNode();
		updateChatRoomNode.put("name", "UpdatedDemoRoom");
		Object updateChatRoom = chatRoomAPIImpl.modifyChatRoom("144078918423413240", updateChatRoomNode);
		LOGGER.info("修改聊天室信息: " + updateChatRoom);
		
		// 删除聊天室
		Object deleteChatRoom = chatRoomAPIImpl.deleteChatRoom("144078918423413240");
		LOGGER.info("删除聊天室: " + deleteChatRoom);
		
		// 聊天室加人[单个]
		Object addSingleUserToChatRoom = chatRoomAPIImpl.addSingleUserToChatRoom("103631015775306272", "18268733716");
		LOGGER.info("聊天室加人[单个]: " + addSingleUserToChatRoom);
		
		// 聊天室加人[批量]
		Object addBatchUsersToChatRoom = chatRoomAPIImpl.addBatchUsersToChatRoom("103631015775306272", new String[] {"18268733715"});
		LOGGER.info("聊天室加人[批量]: " + addBatchUsersToChatRoom);
		
		// 聊天室减人[单个]
		Object removeSingleUserFromChatRoom = chatRoomAPIImpl.removeSingleUserFromChatRoom("103631015775306272", "18268733716");
		LOGGER.info("聊天室减人[单个]: " + removeSingleUserFromChatRoom);
		
		// 聊天室减人[批量]
		Object removeBatchUsersFromChatRoom = chatRoomAPIImpl.removeBatchUsersFromChatRoom("103631015775306272", new String[] {"18268733715"});
		LOGGER.info("聊天室减人[批量]: " + removeBatchUsersFromChatRoom);
		
		// 获取一个用户参与的所有聊天室
		Object getUserAllChatRooms = imUserAPIImpl.getIMUserAllChatRooms("18268733715");
		LOGGER.info("获取一个用户参与的所有聊天室: " + getUserAllChatRooms);
	}

	public ObjectNode getAllChatRooms() {
		ObjectNode objectNode = factory.objectNode();

		// check appKey format
		if (!JerseyUtils.match("^(?!-)[0-9a-zA-Z\\-]+#[0-9a-zA-Z]+", APPKEY)) {
			LOGGER.error("Bad format of Appkey: " + APPKEY);
			objectNode.put("message", "Bad format of Appkey");
			return objectNode;
		}

		JerseyWebTarget webTarget = EndPoints.CHATROOMS_TARGET.resolveTemplate("org_name", APPKEY.split("#")[0])
				.resolveTemplate("app_name", APPKEY.split("#")[1]);

		objectNode = JerseyUtils.sendRequest(webTarget, null, credential, HTTPMethod.METHOD_GET, null);

		return objectNode;
	}

	public ObjectNode getChatRoomDetail(String roomId) {
		ObjectNode objectNode = factory.objectNode();

		// check appKey format
		if (!JerseyUtils.match("^(?!-)[0-9a-zA-Z\\-]+#[0-9a-zA-Z]+", APPKEY)) {
			LOGGER.error("Bad format of Appkey: " + APPKEY);
			objectNode.put("message", "Bad format of Appkey");
			return objectNode;
		}

		// check the input parameter
		if (StringUtils.isBlank(roomId)) {
			LOGGER.error("RoomId should not be null or empty.");
			objectNode.put("message", "RoomId should not be null or empty.");
			return objectNode;
		}

		JerseyWebTarget webTarget = EndPoints.CHATROOMS_TARGET.resolveTemplate("org_name", APPKEY.split("#")[0])
				.resolveTemplate("app_name", APPKEY.split("#")[1]).path(roomId);
		objectNode = JerseyUtils.sendRequest(webTarget, null, credential, HTTPMethod.METHOD_GET, null);

		return objectNode;
	}

	public ObjectNode createChatRoom(Object payload) {
		ObjectNode objectNode = factory.objectNode();
		ObjectNode dataObjectNode = (ObjectNode) payload;

		// check appKey format
		if (!JerseyUtils.match("^(?!-)[0-9a-zA-Z\\-]+#[0-9a-zA-Z]+", APPKEY)) {
			LOGGER.error("Bad format of Appkey: " + APPKEY);
			objectNode.put("message", "Bad format of Appkey");
			return objectNode;
		}

		// check properties that must be provided
		if (!dataObjectNode.has("name")) {
			LOGGER.error("Property that named name must be provided .");
			objectNode.put("message", "Property that named name must be provided .");
			return objectNode;
		}
		if (!dataObjectNode.has("description")) {
			LOGGER.error("Property that named description must be provided .");
			objectNode.put("message", "Property that named description must be provided .");
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

			JerseyWebTarget webTarget = EndPoints.CHATROOMS_TARGET.resolveTemplate("org_name", APPKEY.split("#")[0])
					.resolveTemplate("app_name", APPKEY.split("#")[1]);

			objectNode = JerseyUtils.sendRequest(webTarget, dataObjectNode, credential, HTTPMethod.METHOD_POST, null);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return objectNode;
	}

	public ObjectNode modifyChatRoom(String roomId, Object payload) {
		ObjectNode objectNode = factory.objectNode();
		ObjectNode dataObjectNode = (ObjectNode) payload;

		// check appKey format
		if (!JerseyUtils.match("^(?!-)[0-9a-zA-Z\\-]+#[0-9a-zA-Z]+", APPKEY)) {
			LOGGER.error("Bad format of Appkey: " + APPKEY);
			objectNode.put("message", "Bad format of Appkey");
			return objectNode;
		}
		
		// check properties that must be provided
		if (dataObjectNode.has("name") && StringUtils.isBlank(dataObjectNode.path("name").asText())) {
			LOGGER.error("Property name provided should not be null or empty.");
			objectNode.put("message", "Property name provided should not be null or empty.");
			return objectNode;
		}
		if (dataObjectNode.has("description") && StringUtils.isBlank(dataObjectNode.path("description").asText())) {
			LOGGER.error("Property description provided should not be null or empty.");
			objectNode.put("message", "Property description provided should not be null or empty.");
			return objectNode;
		}
		
		JerseyWebTarget webTarget = EndPoints.CHATROOMS_TARGET.resolveTemplate("org_name", APPKEY.split("#")[0])
				.resolveTemplate("app_name", APPKEY.split("#")[1]).path(roomId);

		objectNode = JerseyUtils.sendRequest(webTarget, dataObjectNode, credential, HTTPMethod.METHOD_PUT, null);
		
		return objectNode;
	}

	public ObjectNode deleteChatRoom(String roomId) {
		ObjectNode objectNode = factory.objectNode();

		// check appKey format
		if (!JerseyUtils.match("^(?!-)[0-9a-zA-Z\\-]+#[0-9a-zA-Z]+", APPKEY)) {
			LOGGER.error("Bad format of Appkey: " + APPKEY);
			objectNode.put("message", "Bad format of Appkey");
			return objectNode;
		}
		
		// check the input parameter
		if(StringUtils.isBlank(roomId)) {
			LOGGER.error("Property that named roomId must be provided.");
			objectNode.put("message", "Property that named roomId must be provided.");
			return objectNode;
		}

		try {
			JerseyWebTarget webTarget = EndPoints.CHATROOMS_TARGET.resolveTemplate("org_name", APPKEY.split("#")[0])
					.resolveTemplate("app_name", APPKEY.split("#")[1]).path(roomId);

			objectNode = JerseyUtils.sendRequest(webTarget, null, credential, HTTPMethod.METHOD_DELETE, null);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return objectNode;
	}

	public ObjectNode addSingleUserToChatRoom(String roomId, String userId) {
		ObjectNode objectNode = factory.objectNode();

		// check appKey format
		if (!JerseyUtils.match("^(?!-)[0-9a-zA-Z\\-]+#[0-9a-zA-Z]+", APPKEY)) {
			LOGGER.error("Bad format of Appkey: " + APPKEY);
			objectNode.put("message", "Bad format of Appkey");
			return objectNode;
		}
		
		// check the input parameter
		if(StringUtils.isBlank(roomId)) {
			LOGGER.error("Property that named roomId must be provided.");
			objectNode.put("message", "Property that named roomId must be provided.");
			return objectNode;
		}
		if(StringUtils.isBlank(userId)) {
			LOGGER.error("Property that named userId must be provided.");
			objectNode.put("message", "Property that named userId must be provided.");
			return objectNode;
		}

		try {

			JerseyWebTarget webTarget = EndPoints.CHATROOMS_USERS_TARGET.resolveTemplate("org_name", APPKEY.split("#")[0])
					.resolveTemplate("app_name", APPKEY.split("#")[1]).resolveTemplate("chatroomid", roomId).path(userId);

			objectNode = JerseyUtils.sendRequest(webTarget, null, credential, HTTPMethod.METHOD_POST, null);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return objectNode;
	}

	public ObjectNode addBatchUsersToChatRoom(String roomId, String[] userIds) {
		ObjectNode objectNode = factory.objectNode();
		ObjectNode dataNode = factory.objectNode();
		
		// check appKey format
		if (!JerseyUtils.match("^(?!-)[0-9a-zA-Z\\-]+#[0-9a-zA-Z]+", APPKEY)) {
			LOGGER.error("Bad format of Appkey: " + APPKEY);
			objectNode.put("message", "Bad format of Appkey");
			return objectNode;
		}
		
		// check the input parameter
		if(StringUtils.isBlank(roomId)) {
			LOGGER.error("Property that named roomId must be provided.");
			objectNode.put("message", "Property that named roomId must be provided.");
			return objectNode;
		}
		if(null == userIds || 0 == userIds.length) {
			LOGGER.error("Property that named userIds must be provided.");
			objectNode.put("message", "Property that named userIds must be provided.");
			return objectNode;
		}

		try {
			JerseyWebTarget webTarget = EndPoints.CHATROOMS_USERS_TARGET.resolveTemplate("org_name", APPKEY.split("#")[0])
					.resolveTemplate("app_name", APPKEY.split("#")[1]).resolveTemplate("chatroomid", roomId);
			
			dataNode.put("usernames", JerseyUtils.buildTarget(userIds));
			objectNode = JerseyUtils.sendRequest(webTarget, dataNode, credential, HTTPMethod.METHOD_POST, null);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return objectNode;
	}

	public ObjectNode removeSingleUserFromChatRoom(String roomId, String userId) {
		ObjectNode objectNode = factory.objectNode();

		// check appKey format
		if (!JerseyUtils.match("^(?!-)[0-9a-zA-Z\\-]+#[0-9a-zA-Z]+", APPKEY)) {
			LOGGER.error("Bad format of Appkey: " + APPKEY);
			objectNode.put("message", "Bad format of Appkey");
			return objectNode;
		}

		// check the input parameter
		if(StringUtils.isBlank(roomId)) {
			LOGGER.error("Property that named roomId must be provided.");
			objectNode.put("message", "Property that named roomId must be provided.");
			return objectNode;
		}
		if(StringUtils.isBlank(userId)) {
			LOGGER.error("Property that named userId must be provided.");
			objectNode.put("message", "Property that named userId must be provided.");
			return objectNode;
		}

		try {

			JerseyWebTarget webTarget = EndPoints.CHATROOMS_USERS_TARGET.resolveTemplate("org_name", APPKEY.split("#")[0])
					.resolveTemplate("app_name", APPKEY.split("#")[1]).resolveTemplate("chatroomid", roomId).path(userId);

			objectNode = JerseyUtils.sendRequest(webTarget, null, credential, HTTPMethod.METHOD_DELETE, null);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return objectNode;
	}

	public ObjectNode removeBatchUsersFromChatRoom(String roomId, String[] userIds) {
		ObjectNode objectNode = factory.objectNode();
		
		// check appKey format
		if (!JerseyUtils.match("^(?!-)[0-9a-zA-Z\\-]+#[0-9a-zA-Z]+", APPKEY)) {
			LOGGER.error("Bad format of Appkey: " + APPKEY);
			objectNode.put("message", "Bad format of Appkey");
			return objectNode;
		}
		
		// check the input parameter
		if(StringUtils.isBlank(roomId)) {
			LOGGER.error("Property that named roomId must be provided.");
			objectNode.put("message", "Property that named roomId must be provided.");
			return objectNode;
		}
		if(null == userIds || 0 == userIds.length) {
			LOGGER.error("Property that named userIds must be provided.");
			objectNode.put("message", "Property that named userIds must be provided.");
			return objectNode;
		}

		try {
			JerseyWebTarget webTarget = EndPoints.CHATROOMS_USERS_TARGET.resolveTemplate("org_name", APPKEY.split("#")[0])
					.resolveTemplate("app_name", APPKEY.split("#")[1]).resolveTemplate("chatroomid", roomId).path(StringUtils.join(userIds, ","));
			
			objectNode = JerseyUtils.sendRequest(webTarget, null, credential, HTTPMethod.METHOD_DELETE, null);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return objectNode;
	}



}
