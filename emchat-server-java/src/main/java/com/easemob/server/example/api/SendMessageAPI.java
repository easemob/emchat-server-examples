package com.easemob.server.example.api;

import java.util.Map;

/**
 * This interface is created for RestAPI of Sending Message, it should be
 * synchronized with the API list.
 * 
 * @author Eric23 2016-01-05
 * @see http://docs.easemob.com/doku.php?id=start:100serverintegration:
 *      50messages
 */
public interface SendMessageAPI {
	/**
	 * 发送消息 <br>
	 * POST
	 * 
	 * @param targetType
	 *            消息发送目标类型, users | chatgroups | chatrooms
	 * @param targets
	 *            消息发送目标，数组形式，元素为目标标识
	 * @param msg
	 *            消息体
	 * @param from
	 *            发送者标识
	 * @param ext
	 *            扩展字段，可空
	 * @return
	 * @see com.easemob.server.example.jersey.vo.MessageBody
	 * @see com.easemob.server.example.jersey.vo.TextMessageBody
	 * @see com.easemob.server.example.jersey.vo.ImgMessageBody
	 * @see com.easemob.server.example.jersey.vo.AudioMessageBody
	 * @see com.easemob.server.example.jersey.vo.VideoMessageBody
	 * @see com.easemob.server.example.jersey.vo.CommandMessageBody
	 */
	Object sendMessage(String targetType, String[] targets, Object msg, String from, Map<String, String> ext);
}
