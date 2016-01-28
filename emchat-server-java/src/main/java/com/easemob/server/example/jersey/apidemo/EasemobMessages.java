package com.easemob.server.example.jersey.apidemo;

import java.io.File;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.glassfish.jersey.client.JerseyWebTarget;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.easemob.server.example.api.FileAPI;
import com.easemob.server.example.api.SendMessageAPI;
import com.easemob.server.example.comm.Constants;
import com.easemob.server.example.comm.HTTPMethod;
import com.easemob.server.example.comm.MsgTargetType;
import com.easemob.server.example.comm.Roles;
import com.easemob.server.example.jersey.utils.JerseyUtils;
import com.easemob.server.example.comm.body.AudioMessageBody;
import com.easemob.server.example.jersey.vo.ClientSecretCredential;
import com.easemob.server.example.comm.body.CommandMessageBody;
import com.easemob.server.example.jersey.vo.Credential;
import com.easemob.server.example.jersey.vo.EndPoints;
import com.easemob.server.example.comm.body.ImgMessageBody;
import com.easemob.server.example.comm.body.MessageBody;
import com.easemob.server.example.comm.body.TextMessageBody;
import com.easemob.server.example.comm.body.VideoMessageBody;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * REST API Demo: 发送消息 REST API Jersey2.9实现
 * 
 * Doc URL: https://docs.easemob.com/doku.php?id=start:100serverintegration:50messages
 * 
 * @author Lynch 2014-07-12 Eric23 2016-01-05
 * 
 */
public class EasemobMessages implements SendMessageAPI{

	private static final Logger LOGGER = LoggerFactory.getLogger(EasemobMessages.class);
	private static final String APPKEY = Constants.APPKEY;
	private static final JsonNodeFactory factory = new JsonNodeFactory(false);

    private static Credential credential = new ClientSecretCredential(Constants.APP_CLIENT_ID,
            Constants.APP_CLIENT_SECRET, Roles.USER_ROLE_APPADMIN);

    public static void main(String[] args) {
    	FileAPI fileAPIImpl = new EasemobFiles();
    	SendMessageAPI sendMessageAPIImpl = new EasemobMessages();
    	String from = "example_user";
    	String target1 = "example_user_0";
    	String target2 = "example_user_1";
    	String targetGroup = "";
    	String uploadImg = "d:/logo.png";
    	String uploadAudio = "";
    	String uploadVideo = "";
    	String imgSharedSecret = null;
    	String audioSharedSecret = null;
    	String videoSharedSecret = null;
    	String imgFileUrl = null;
    	String audioFileUrl = null;
    	String videoFileUrl = null;
    	
        // 给用户发一条文本消息
        ObjectNode sendTxtMessageusernode = (ObjectNode) sendMessageAPIImpl.sendMessage(MsgTargetType.USERS, new String[] {target1, target2}, new TextMessageBody("Hello world!"), from, null);
        if (null != sendTxtMessageusernode) {
            LOGGER.info("给用户发一条文本消息: " + sendTxtMessageusernode.toString());
        }

        // 给一个群组发文本消息
        ObjectNode sendTxtMessagegroupnode = (ObjectNode) sendMessageAPIImpl.sendMessage(MsgTargetType.GROUPS, new String[] {targetGroup}, new TextMessageBody("Hello world!"), from, null);
        if (null != sendTxtMessagegroupnode) {
            LOGGER.info("给一个群组发文本消息: " + sendTxtMessagegroupnode.toString());
        }

        // 给用户发一条图片消息
        File uploadImgFile = new File(uploadImg);
        ObjectNode imgDataNode = (ObjectNode) fileAPIImpl.uploadFile(uploadImgFile);
        
        if (null != imgDataNode) {
        	LOGGER.info("上传图片文件: " + imgDataNode.toString());
        	
            String imgFileUUID = imgDataNode.path("entities").get(0).path("uuid").asText();
            imgSharedSecret = imgDataNode.path("entities").get(0).path("share-secret").asText();
            imgFileUrl = EndPoints.CHATFILES_TARGET.resolveTemplate("org_name", APPKEY.split("#")[0]).resolveTemplate("app_name",
                    APPKEY.split("#")[1]).getUri().toString() + imgFileUUID;
            
            ObjectNode sendimgMessageusernode = (ObjectNode) sendMessageAPIImpl.sendMessage(MsgTargetType.USERS, new String[] {target1, target2}, new ImgMessageBody(imgFileUrl, "logo.png", imgSharedSecret, 125L, 125L), from, null);
            if (null != sendimgMessageusernode) {
                LOGGER.info("给用户发图片消息: " + sendimgMessageusernode.toString());
            }
        }
        
        // 给用户发一条音频消息
        File uploadAudioFile = new File(uploadAudio);
        ObjectNode audioDataNode = (ObjectNode) fileAPIImpl.uploadFile(uploadAudioFile);
        
        if (null != audioDataNode) {
        	LOGGER.info("上传音频文件: " + audioDataNode.toString());
        	
            String audioFileUUID = audioDataNode.path("entities").get(0).path("uuid").asText();
            audioSharedSecret = audioDataNode.path("entities").get(0).path("share-secret").asText();
            audioFileUrl = EndPoints.CHATFILES_TARGET.resolveTemplate("org_name", APPKEY.split("#")[0]).resolveTemplate("app_name",
                    APPKEY.split("#")[1]).getUri().toString() + audioFileUUID;
            
            ObjectNode sendAudioMessageNode = (ObjectNode) sendMessageAPIImpl.sendMessage(MsgTargetType.USERS, new String[] {target1, target2}, new AudioMessageBody(audioFileUrl, "demo.mp3", audioSharedSecret, 10L), from, null);
            if (null != sendAudioMessageNode) {
                LOGGER.info("给用户发音频消息: " + sendAudioMessageNode.toString());
            }
        }
        
        // 给用户发送一条视频消息
        File uploadVideoFile = new File(uploadVideo);
        ObjectNode videoDataNode = (ObjectNode) fileAPIImpl.uploadFile(uploadVideoFile);
        
        if (null != videoDataNode) {
        	LOGGER.info("上传视频文件: " + videoDataNode.toString());
        	
            String videoFileUUID = videoDataNode.path("entities").get(0).path("uuid").asText();
            videoSharedSecret = videoDataNode.path("entities").get(0).path("share-secret").asText();
            videoFileUrl = EndPoints.CHATFILES_TARGET.resolveTemplate("org_name", APPKEY.split("#")[0]).resolveTemplate("app_name",
                    APPKEY.split("#")[1]).getUri().toString() + videoFileUUID;
            
            ObjectNode sendVideoMessageNode = (ObjectNode) sendMessageAPIImpl.sendMessage(MsgTargetType.USERS, new String[] {target1, target2}, new VideoMessageBody(videoFileUrl, "demo.mp4", videoSharedSecret, 10L, 100L, imgFileUrl, imgSharedSecret ), from, null);
            if (null != sendVideoMessageNode) {
                LOGGER.info("给用户发音频消息: " + sendVideoMessageNode.toString());
            }
        }

        // 给用户发一条透传消息
        Map<String, String> extension = new HashMap<String, String>();
        extension.put("executor", "admin");
        ObjectNode sendCmdMessageNode = (ObjectNode) sendMessageAPIImpl.sendMessage(MsgTargetType.USERS, new String[] {target1, target2}, new CommandMessageBody("Hello world!"), from, extension);
        if (null != sendCmdMessageNode) {
            LOGGER.info("给用户发一条透传消息: " + sendCmdMessageNode.toString());
        }
    }


	public ObjectNode sendMessage(String targetType, String[] targets, Object msg, String from, Map<String, String> ext) {

		ObjectNode objectNode = factory.objectNode();
		ObjectNode dataNode = factory.objectNode();
		MessageBody body = (MessageBody) msg;

		// check appKey format
		if (!JerseyUtils.match("^(?!-)[0-9a-zA-Z\\-]+#[0-9a-zA-Z]+", APPKEY)) {
			LOGGER.error("Bad format of Appkey: " + APPKEY);
			objectNode.put("message", "Bad format of Appkey");
			return objectNode;
		}

		// check the input parameters
		if ( !MsgTargetType.USERS.equals(targetType) && !MsgTargetType.GROUPS.equals(targetType) && !MsgTargetType.ROOMS.equals(targetType) ) {
			LOGGER.error("TargetType must be one of users | chatgroups | chatrooms.");
			objectNode.put("message", "TargetType must be one of users | chatgroups | chatrooms.");
			return objectNode;
		}
		if( null == targets || 0 == targets.length ) {
			LOGGER.error("Target should not be null or empty.");
			objectNode.put("message", "Target should not be null or empty.");
			return objectNode;
		}
		if( null == msg ) {
			LOGGER.error("Message should not be null or empty.");
			objectNode.put("message", "Message should not be null or empty.");
			return objectNode;
		}
		if( StringUtils.isEmpty(from) ) {
			LOGGER.error("From should not be null or empty.");
			objectNode.put("message", "From should not be null or empty.");
			return objectNode;
		}

		try {
			// 构造消息体
			dataNode.put("target_type", targetType);
			ArrayNode targetsNode = JerseyUtils.buildTarget(targets);
			dataNode.put("target", targetsNode);
			dataNode.put("msg", body.getMsgBody());
			dataNode.put("from", from);
			if( null != ext && !ext.isEmpty() ) {
				dataNode.put("ext", buildExt(ext));
			}

			JerseyWebTarget webTarget = EndPoints.MESSAGES_TARGET.resolveTemplate("org_name", APPKEY.split("#")[0]).resolveTemplate(
					"app_name", APPKEY.split("#")[1]);

			objectNode = JerseyUtils.sendRequest(webTarget, dataNode, credential, HTTPMethod.METHOD_POST, null);

			objectNode = (ObjectNode) objectNode.get("data");
			for (int i = 0; i < targetsNode.size(); i++) {
				String resultStr = objectNode.path(targetsNode.path(i).asText()).asText();
				if ("success".equals(resultStr)) {
					LOGGER.error(String.format("Message has been send to user[%s] successfully .", targetsNode.path(i).asText()));
				} else if (!"success".equals(resultStr)) {
					LOGGER.error(String.format("Message has been send to user[%s] failed .", targetsNode.path(i).asText()));
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return objectNode;
	}
	
	private ObjectNode buildExt(Map<String, String> ext) {
		ObjectNode node = factory.objectNode();
		
		for( Iterator<String> iter = ext.keySet().iterator(); iter.hasNext(); iter.next() ) {
			node.put(iter.toString(), ext.get(iter));
		}
		
		return node;
	}
	
}
