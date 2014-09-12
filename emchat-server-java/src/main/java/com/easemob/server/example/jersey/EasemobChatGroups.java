package com.easemob.server.example.jersey;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.easemob.server.example.utils.Constants;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;

/**
 * REST API Demo : 群组管理 Jersey2.9实现
 * 
 * Doc URL: http://www.easemob.com/docs/rest/groups/
 * 
 * @author Lynch 2014-09-12
 * 
 */
public class EasemobChatGroups {

	private static Logger LOGGER = LoggerFactory.getLogger(EasemobChatGroups.class);

	private static JsonNodeFactory factory = new JsonNodeFactory(false);

	private static final String APPKEY = Constants.APPKEY;

	/**
	 * 获取app中所有的群组ID
	 * 
	 * 
	 * @return
	 */
	public static void getAllChatgroupids() {

	}

	/**
	 * 获取一个或者多个群组的详情
	 * 
	 * @return
	 */
	public static void getGroupDetailsByChatgroupid() {

	}

	/**
	 * 创建群组
	 * 
	 */
	public static void creatChatGroups() {

	}

	/**
	 * 删除群组
	 * 
	 */
	public static void deleteChatGroups() {

	}

	/**
	 * 获取群组中的所有成员
	 * 
	 */
	public static void getAllMemberssByGroupId() {

	}

	/**
	 * 在群组中添加一个人
	 * 
	 */
	public static void addUserToGroup() {

	}

	/**
	 * 在群组中减少一个人
	 * 
	 */
	public static void deleteUserFromGroup() {

	}

}
