package com.hyphenate.server.example.api;

/**
 * This interface is created for REST API of Chat Room, it should be synchronized
 * with the API list.
 *
 * @author Hyphenate
 * @see http://docs.hyphenate.io
 */
public interface ChatRoomAPI {
	/**
	 * Create a Chat Room
	 *
	 * POST
	 * 
	 * @param payload
	 *            <code>{name":"testchatroom","description":"server create chatroom","maxusers":300,"owner":"jma1","members":["jma2","jma3"]}</code>
	 * @return
	 */
	Object createChatRoom(Object payload);

	/**
	 * Modify the Details of a Chat Room
	 *
	 * PUT
	 * 
	 * @param roomId
	 *            chat room id
	 * @param payload
	 *            <code>{"name":"test chatroom","description":
	 *            "update chatroominfo","maxusers":200}
	 * @return
	 */
	Object modifyChatRoom(String roomId, Object payload);

	/**
	 * Delete a Chat Room
	 *
	 * DELETE
	 * 
	 * @param roomId
	 *            chat room id
	 * @return
	 */
	Object deleteChatRoom(String roomId);

	/**
	 * Get All the Chat Rooms
	 *
	 * GET
	 * 
	 * @return
	 */
	Object getAllChatRooms();

	/**
	 * Get the Details of a chat room
	 *
	 * GET
	 * 
	 * @param roomId
	 *            chat room id
	 * @return
	 */
	Object getChatRoomDetail(String roomId);

	/**
	 * Add a member to a chat room
	 *
	 * POST
	 * 
	 * @param roomId
	 *            chat room id
	 * @param userName
	 *            user to be added
	 * @return
	 */
	Object addSingleUserToChatRoom(String roomId, String userName);

	/**
	 * Add multiple members to a chat room
	 *
	 * POST
	 * 
	 * @param roomId
	 *            chat room id
	 * @param payload
	 *            users to be added
	 * @return
	 * @see com.hyphenate.server.example.comm.body.UserNamesBody
	 */
	Object addBatchUsersToChatRoom(String roomId, Object payload);

	/**
	 * Remove a member from a chat room
	 * DELETE
	 * 
	 * @param roomId
	 *            chat room id
	 * @param userName
	 *            user to be removed
	 * @return
	 */
	Object removeSingleUserFromChatRoom(String roomId, String userName);

	/**
	 * Remove multiple members from a chat room
	 *
	 * DELETE
	 * 
	 * @param roomId
	 *            chat room id
	 * @param userNames
	 *            users to be removed
	 * @return
	 */
	Object removeBatchUsersFromChatRoom(String roomId, String[] userNames);
}
