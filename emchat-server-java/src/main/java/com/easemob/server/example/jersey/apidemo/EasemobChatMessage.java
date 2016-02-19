package com.easemob.server.example.jersey.apidemo;

import org.apache.commons.lang3.StringUtils;
import org.glassfish.jersey.client.JerseyWebTarget;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.easemob.server.example.api.ChatMessageAPI;
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
 * REST API Demo : 聊天记录 Jersey2.9实现
 * 
 * Doc URL: https://docs.easemob.com/doku.php?id=start:100serverintegration:30chatlog
 * 
 * @author Lynch 2014-07-12 Eric23 2016-01-06
 * 
 */
public class EasemobChatMessage implements ChatMessageAPI {

	private static final Logger LOGGER = LoggerFactory.getLogger(EasemobChatMessage.class);
	private static final JsonNodeFactory factory = new JsonNodeFactory(false);
	private static final String APPKEY = Constants.APPKEY;

    private static Credential credential = new ClientSecretCredential(Constants.APP_CLIENT_ID,
            Constants.APP_CLIENT_SECRET, Roles.USER_ROLE_APPADMIN);

    public static void main(String[] args) {
    	
    	ChatMessageAPI chatMessageAPIImpl = new EasemobChatMessage();

        // 聊天消息 获取最新的20条记录以及下一页记录
        ObjectNode messages20 = (ObjectNode) chatMessageAPIImpl.exportChatMessages(20L, null, null);
        if( null != messages20 ) {
        	LOGGER.info("最新的20条记录: " + messages20.toString());
        	
        	String cursor = messages20.get("cursor").asText();
        	if( StringUtils.isNoneBlank(cursor) ) {
                ObjectNode messagesNext20 = (ObjectNode) chatMessageAPIImpl.exportChatMessages(20L, cursor, null);
                
                if( null != messagesNext20 ) {
                	LOGGER.info("下一页的20条记录: " + messagesNext20.toString());
                }
        	}
            
        }

        // 聊天消息 获取7天以内的消息
        String currentTimestamp = String.valueOf(System.currentTimeMillis());
        String senvenDayAgo = String.valueOf(System.currentTimeMillis() - 7 * 24 * 60 * 60 * 1000);
        ObjectNode messagesIn7Days = (ObjectNode) chatMessageAPIImpl.exportChatMessages( null, null, "select * where  timestamp > " + senvenDayAgo + " and timestamp < " + currentTimestamp );
        if( null != messagesIn7Days ) {
        	LOGGER.info("7天以内的记录: " + messagesIn7Days.toString());
        }
    }


	public ObjectNode exportChatMessages(Long limit, String cursor, String query) {
		ObjectNode objectNode = factory.objectNode();
		
		// check appKey format
		if (!JerseyUtils.match("^(?!-)[0-9a-zA-Z\\-]+#[0-9a-zA-Z]+", APPKEY)) {
			LOGGER.error("Bad format of Appkey: " + APPKEY);
			objectNode.put("message", "Bad format of Appkey");
			return objectNode;
		}
		
		JerseyWebTarget webTarget = EndPoints.CHATMESSAGES_TARGET.resolveTemplate("org_name", APPKEY.split("#")[0])
				.resolveTemplate("app_name", APPKEY.split("#")[1]);
		// check the input parameter
		if ( null != limit && limit > 0 && limit <= 1000 ) {
			webTarget = webTarget.queryParam("limit", limit);
		}
		if (StringUtils.isNoneBlank(cursor)) {
			webTarget = webTarget.queryParam("cursor", cursor);
		}
		if (StringUtils.isNoneBlank(query)) {
			webTarget = webTarget.queryParam("ql", query);
		}
		
		objectNode = JerseyUtils.sendRequest(webTarget, null, credential, HTTPMethod.METHOD_GET, null);
		
		return objectNode;
	}

}
