package com.easemob.server.example.jersey.apidemo;

import org.apache.commons.lang3.StringUtils;
import org.glassfish.jersey.client.JerseyWebTarget;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.easemob.server.example.comm.Constants;
import com.easemob.server.example.comm.HTTPMethod;
import com.easemob.server.example.comm.Roles;
import com.easemob.server.example.jersey.utils.JerseyUtils;
import com.easemob.server.example.jersey.vo.Credentail;
import com.easemob.server.example.jersey.vo.EndPoints;
import com.easemob.server.example.jersey.vo.UsernamePasswordCredentail;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * REST API Demo: 发送消息 REST API Jersey2.9实现
 * 
 * Doc URL: http://www.easemob.com/docs/rest/sendmessage/
 * 
 * @author Lynch 2014-07-12
 * 
 */
public class EasemobMessages {

	private static Logger LOGGER = LoggerFactory.getLogger(EasemobMessages.class);

	private static final String APPKEY = Constants.APPKEY;

	private static JsonNodeFactory factory = new JsonNodeFactory(false);

	/**
	 * 检测用户是否在线
	 * 
	 * @param token
	 * @param user
	 * @return
	 */
	public static ObjectNode getUserStatus(String targetUserPrimaryKey) {

		ObjectNode objectNode = factory.objectNode();

		// check appKey format
		if (!JerseyUtils.match("[0-9a-zA-Z\\-]+#[0-9a-zA-Z]+", APPKEY)) {
			LOGGER.error("Bad format of Appkey: " + APPKEY);

			objectNode.put("message", "Bad format of Appkey");

			return objectNode;
		}

		// check properties that must be provided
		if (StringUtils.isEmpty(targetUserPrimaryKey)) {
			LOGGER.error("You must provided a targetUserPrimaryKey .");

			objectNode.put("message", "You must provided a targetUserPrimaryKey .");

			return objectNode;
		}

		try {

			Credentail credentail = new UsernamePasswordCredentail(Constants.APP_ADMIN_USERNAME,
					Constants.APP_ADMIN_PASSWORD, Roles.USER_ROLE_APPADMIN);

			JerseyWebTarget webTarget = null;
			webTarget = EndPoints.USERS_TARGET.resolveTemplate("org_name", APPKEY.split("#")[0])
					.resolveTemplate("app_name", APPKEY.split("#")[1]).path(targetUserPrimaryKey).path("status");

			objectNode = JerseyUtils.sendRequest(webTarget, null, credentail, HTTPMethod.METHOD_GET, null);

			String userStatus = objectNode.get("data").path(targetUserPrimaryKey).asText();
			if ("online".equals(userStatus)) {
				LOGGER.error(String.format("The status of user[%s] is : [%s] .", targetUserPrimaryKey, userStatus));
			} else if ("offline".equals(userStatus)) {
				LOGGER.error(String.format("The status of user[%s] is : [%s] .", targetUserPrimaryKey, userStatus));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return objectNode;
	}

	/**
	 * 发送消息
	 * 
	 * @param targetType
	 *            消息投递者类型：users 用户, chatgroups 群组
	 * @param target
	 *            接收者ID 必须是数组,数组元素为用户ID或者群组ID
	 * @param msg
	 *            消息内容
	 * @param from
	 *            发送者
	 * @param ext
	 *            扩展字段
	 * 
	 * @return 请求响应
	 */
	public static ObjectNode sendMessages(String targetType, ArrayNode target, ObjectNode msg, String from,
			ObjectNode ext) {

		ObjectNode objectNode = factory.objectNode();

		ObjectNode dataNode = factory.objectNode();

		// check appKey format
		if (!JerseyUtils.match("[0-9a-zA-Z\\-]+#[0-9a-zA-Z]+", APPKEY)) {
			LOGGER.error("Bad format of Appkey: " + APPKEY);

			objectNode.put("message", "Bad format of Appkey");

			return objectNode;
		}

		// check properties that must be provided
		if (!("users".equals(targetType) || "chatgroups".equals(targetType))) {
			LOGGER.error("TargetType must be users or chatgroups .");

			objectNode.put("message", "TargetType must be users or chatgroups .");

			return objectNode;
		}

		try {
			// 构造消息体
			dataNode.put("target_type", targetType);
			dataNode.put("target", target);
			dataNode.put("msg", msg);
			dataNode.put("from", from);
			dataNode.put("ext", ext);

			Credentail credentail = new UsernamePasswordCredentail(Constants.APP_ADMIN_USERNAME,
					Constants.APP_ADMIN_PASSWORD, Roles.USER_ROLE_APPADMIN);

			JerseyWebTarget webTarget = null;
			webTarget = EndPoints.MESSAGES_TARGET.resolveTemplate("org_name", APPKEY.split("#")[0]).resolveTemplate(
					"app_name", APPKEY.split("#")[1]);

			objectNode = JerseyUtils.sendRequest(webTarget, dataNode, credentail, HTTPMethod.METHOD_POST, null);

			objectNode = (ObjectNode) objectNode.get("data");
			for (int i = 0; i < target.size(); i++) {
				String resultStr = objectNode.path(target.path(i).asText()).asText();
				if ("success".equals(resultStr)) {
					LOGGER.error(String.format("Message has been send to user[%s] successfully .", target.path(i)
							.asText()));
				} else if (!"success".equals(resultStr)) {
					LOGGER.error(String.format("Message has been send to user[%s] failed .", target.path(i).asText()));
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return objectNode;
	}
}
