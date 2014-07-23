package com.easemob.server.example.jersey;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.JsonNode;

/**
 * REST API Demo : 用户管理REST API Jersey2.9实现
 * 
 * Doc URL: http://developer.easemob.com/docs/emchat/rest/userapi.html
 * 
 * @author Lynch 2014-07-09
 * 
 */
public class EasemobUserAPI {
	/** LOGGER */
	private static Logger LOGGER = LoggerFactory.getLogger(EasemobUserAPI.class);

	/**
	 * 获取指定org和app下所有IM用户
	 */
	public static List<String> getAllIMUsers(Map<String, Object> reqBody) {
		JsonNode jsonNode = null;

		String reqURL = "https://a1.easemob.com/easemob-demo/chatdemoui/users?limit=150";

		jsonNode = JerseyUtils.sendRequest(reqURL, JerseyUtils.Map2Json(reqBody),
				"YWMtVA8nUBIZEeS7gg2K43Yp_AAAAUeFT0tr01cWcszmuNgRGbFETzRgXXbgEDw", JerseyUtils.METHOD_GET, null);
		JsonNode entitiesJsonNode = jsonNode.get("entities");
		List<String> friendUsernames = new ArrayList<String>();
		for (JsonNode jsonNode2 : entitiesJsonNode) {
			friendUsernames.add(jsonNode2.get("username").asText());
		}

		return friendUsernames;
	}

	public static JsonNode contactsFriend(List<String> friendUsernames) {
		JsonNode jsonNode = null;
		for (String friendUsername : friendUsernames) {
			String reqURL = "https://a1.easemob.com/easemob-demo/chatdemoui/users/88888/contacts/users/"
					+ friendUsername;
			jsonNode = JerseyUtils.sendRequest(reqURL, null,
					"YWMtVA8nUBIZEeS7gg2K43Yp_AAAAUeFT0tr01cWcszmuNgRGbFETzRgXXbgEDw", JerseyUtils.METHOD_POST, null);
		}

		return jsonNode;
	}

	public static void main(String[] args) {
		contactsFriend(getAllIMUsers(null));

	}
}
