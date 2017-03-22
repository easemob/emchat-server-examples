package com.easemob.server.example.api;

/**
 * This interface is created for RestAPI of Chat Room, it should be synchronized
 * with the API list.
 * 
 * @author Eric23 2016-01-05
 * @see http://docs.easemob.com/
 */
public interface ChatRoomAPI {
	/**
	 * 创建聊天室 <br>
	 * POST
	 * 
	 * @param payload
	 *            <code>{name":"testchatroom","description":"server create chatroom","maxusers":300,"owner":"jma1","members":["jma2","jma3"]}</code>
	 * @return
	 */
	Object createChatRoom(Object payload);

	/**
	 * 修改聊天室信息 <br>
	 * PUT
	 * 
	 * @param roomId
	 *            聊天室标识
	 * @param payload
	 *            <code>{"name":"test chatroom","description":
	 *            "update chatroominfo","maxusers":200}
	 * @return
	 */
	Object modifyChatRoom(String roomId, Object payload);

	/**
	 * 删除聊天室 <br>
	 * DELETE
	 * 
	 * @param roomId
	 *            聊天室标识
	 * @return
	 */
	Object deleteChatRoom(String roomId);

	/**
	 * 获取app中所有的聊天室 <br>
	 * GET
	 * 
	 * @return
	 */
	Object getAllChatRooms();

	/**
	 * 获取一个聊天室详情 <br>
	 * GET
	 * 
	 * @param roomId
	 *            聊天室标识
	 * @return
	 */
	Object getChatRoomDetail(String roomId);

	/**
	 * 聊天室成员添加[单个] <br>
	 * POST
	 * 
	 * @param roomId
	 *            聊天室标识
	 * @param userName
	 *            用户ID或用户名
	 * @return
	 */
	Object addSingleUserToChatRoom(String roomId, String userName);

	/**
	 * 聊天室成员添加[批量] <br>
	 * POST
	 * 
	 * @param roomId
	 *            聊天室标识
	 * @param payload
	 *            用户ID或用户名，数组形式
	 * @return
	 * @see com.easemob.server.example.comm.body.UserNamesBody
	 */
	Object addBatchUsersToChatRoom(String roomId, Object payload);

	/**
	 * 聊天室成员删除[单个] <br>
	 * DELETE
	 * 
	 * @param roomId
	 *            聊天室标识
	 * @param userName
	 *            用户ID或用户名
	 * @return
	 */
	Object removeSingleUserFromChatRoom(String roomId, String userName);

	/**
	 * 聊天室成员删除[批量] <br>
	 * DELETE
	 * 
	 * @param roomId
	 *            聊天室标识
	 * @param userNames
	 *            用户ID或用户名，数组形式
	 * @return
	 */
	Object removeBatchUsersFromChatRoom(String roomId, String[] userNames);
}
