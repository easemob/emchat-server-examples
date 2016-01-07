package com.easemob.server.example.jersey.vo;

import org.glassfish.jersey.client.JerseyClient;
import org.glassfish.jersey.client.JerseyWebTarget;

import com.easemob.server.example.comm.Constants;
import com.easemob.server.example.jersey.utils.JerseyUtils;

/**
 * JerseyWebTarget EndPoints
 * 
 * @author Lynch 2014-09-15 Eric23 2016-01-05
 *
 */
public interface EndPoints {

	final JerseyClient CLIENT = JerseyUtils.getJerseyClient(true);

	final JerseyWebTarget ROOT_TARGET = CLIENT
			.target(Constants.API_HTTP_SCHEMA + "://" + Constants.API_SERVER_HOST + "/");
	
	/*
	 * BASIC
	 */

	JerseyWebTarget APPLICATION_TEMPLATE = ROOT_TARGET.path("{org_name}").path("{app_name}");

	JerseyWebTarget TOKEN_APP_TARGET = APPLICATION_TEMPLATE.path("token");
	
	/*
	 * USERS
	 */

	JerseyWebTarget USERS_TARGET = APPLICATION_TEMPLATE.path("users");

	JerseyWebTarget USERS_ADDFRIENDS_TARGET = APPLICATION_TEMPLATE.path("users").path("{ownerUserName}")
			.path("contacts").path("users").path("{friendUserName}");

	JerseyWebTarget USER_BLACKLIST_TARGET = APPLICATION_TEMPLATE.path("users").path("{owner_username}").path("blocks")
			.path("users").path("{blocked_username}");
	
	JerseyWebTarget USERS_STATUS_TARGET = USERS_TARGET.path("{username}").path("status");
	
	JerseyWebTarget USERS_OFFLINE_MSG_COUNT_TARGET = USERS_TARGET.path("{owner_username}").path("offline_msg_count");
	
	JerseyWebTarget USERS_OFFLINE_MSG_STATUS_TARGET = USERS_TARGET.path("{username}").path("offline_msg_status").path("{msg_id}");
	
	JerseyWebTarget USERS_DEACTIVATE_TARGET = USERS_TARGET.path("{username}").path("deactivate");
	
	JerseyWebTarget USERS_ACTIVATE_TARGET = USERS_TARGET.path("{username}").path("activate");
	
	JerseyWebTarget USERS_DISCONNECT_TARGET = USERS_TARGET.path("{username}").path("disconnect");
	
	JerseyWebTarget USERS_CHATGROUPS_TARGET = USERS_TARGET.path("{username}").path("joined_chatgroups");
	
	JerseyWebTarget USERS_CHATROOMS_TARGET = USERS_TARGET.path("{username}").path("joined_chatrooms");
	
	/*
	 * MESSAGES
	 */

	JerseyWebTarget MESSAGES_TARGET = APPLICATION_TEMPLATE.path("messages");
	
	/*
	 * CHAT FILES
	 */
	
	JerseyWebTarget CHATFILES_TARGET = APPLICATION_TEMPLATE.path("chatfiles");
	
	/*
	 * CHAT MESSAGES
	 */

	JerseyWebTarget CHATMESSAGES_TARGET = APPLICATION_TEMPLATE.path("chatmessages");
	
	/*
	 * CHAT GROUPS
	 */

	JerseyWebTarget CHATGROUPS_TARGET = APPLICATION_TEMPLATE.path("chatgroups");
	
	JerseyWebTarget CHATGROUPS_USERS_TARGET = CHATGROUPS_TARGET.path("{group_id}").path("users");

	JerseyWebTarget CHATGROUPS_BLOCK_USERS_TARGET = CHATGROUPS_TARGET.path("{group_id}").path("blocks").path("users");
	
	/*
	 * CHAT ROOMS
	 */
	
	JerseyWebTarget CHATROOMS_TARGET = APPLICATION_TEMPLATE.path("chatrooms");
	
	JerseyWebTarget CHATROOMS_USERS_TARGET = CHATROOMS_TARGET.path("{chatroomid}").path("users");
}
