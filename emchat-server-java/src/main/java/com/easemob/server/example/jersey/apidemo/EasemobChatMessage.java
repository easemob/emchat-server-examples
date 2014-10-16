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
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * REST API Demo : 聊天记录 Jersey2.9实现
 * 
 * Doc URL: http://www.easemob.com/docs/rest/chatmessage/
 * 
 * @author Lynch 2014-07-12
 * 
 */
public class EasemobChatMessage {

	private static Logger LOGGER = LoggerFactory.getLogger(EasemobChatMessage.class);

	private static JsonNodeFactory factory = new JsonNodeFactory(false);

	private static final String APPKEY = Constants.APPKEY;

	/**
	 * 获取聊天消息
	 * 
	 * @param appKey
	 * @param host
	 * @param token
	 * @param reqBody
	 * @param method
	 * @param uuid
	 */
	public static ObjectNode getChatMessages(ObjectNode queryStrNode) {

		ObjectNode objectNode = factory.objectNode();

		// check appKey format
		if (!JerseyUtils.match("^(?!-)[0-9a-zA-Z\\-]+#[0-9a-zA-Z]+", APPKEY)) {
			LOGGER.error("Bad format of Appkey: " + APPKEY);

			objectNode.put("message", "Bad format of Appkey");

			return objectNode;
		}

		try {

			Credentail credentail = new UsernamePasswordCredentail(Constants.APP_ADMIN_USERNAME,
					Constants.APP_ADMIN_PASSWORD, Roles.USER_ROLE_APPADMIN);

			JerseyWebTarget webTarget = null;
			webTarget = EndPoints.CHATMESSAGES_TARGET.resolveTemplate("org_name", APPKEY.split("#")[0])
					.resolveTemplate("app_name", APPKEY.split("#")[1]);
			if (null != queryStrNode && !StringUtils.isEmpty(queryStrNode.get("ql").asText())) {
				webTarget.queryParam("ql", queryStrNode.get("ql").asText());
			}
			if (null != queryStrNode && null != queryStrNode.get("limit") &&!StringUtils.isEmpty(queryStrNode.get("limit").asText())) {
				webTarget.queryParam("limit", queryStrNode.get("limit").asText());
			}

			objectNode = JerseyUtils.sendRequest(webTarget, null, credentail, HTTPMethod.METHOD_GET, null);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return objectNode;
	}

	/**
	 * Main Test
	 * 
	 * @param args
	 */
	public static void main(String[] args) {

		// 聊天消息 获取最新的20条记录
		ObjectNode queryStrNode = factory.objectNode();
		queryStrNode.put("ql", "order+by+timestamp+desc");
		queryStrNode.put("limit", "20");
		ObjectNode messages = getChatMessages(queryStrNode);

		// 聊天消息 获取7天以内的消息
		String currentTimestamp = String.valueOf(System.currentTimeMillis());
		String senvenDayAgo = String.valueOf(System.currentTimeMillis() - 7 * 24 * 60 * 60 * 1000);
		ObjectNode queryStrNode1 = factory.objectNode();
		queryStrNode1.put("ql", "select * where  timestamp > " + senvenDayAgo + " and timestamp < " + currentTimestamp);
		ObjectNode messages1 = getChatMessages(queryStrNode1);

		// 聊天消息 分页获取
		ObjectNode queryStrNode2 = factory.objectNode();
		queryStrNode2.put("ql", "order+by+timestamp+desc");
		queryStrNode2.put("limit", "20");
		// 第一页
		ObjectNode messages2 = getChatMessages(queryStrNode2);
		// 第二页
		String cursor = messages2.get("cursor").asText();
		queryStrNode2.put("cursor", cursor);
		ObjectNode messages3 = getChatMessages(queryStrNode2);

	}
}
