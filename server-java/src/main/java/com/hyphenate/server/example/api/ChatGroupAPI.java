package com.hyphenate.server.example.api;

/**
 * REST API of Chat Group
 * 
 * @author Hyphenate
 * @see https://docs.hyphenate.io
 *
 */
public interface ChatGroupAPI {

	/**
	 * Get All the Groups
	 *
	 * GET
	 * 
	 * @param limit
	 *            max number of group
	 * @param cursor
	 *            page index for pagination
	 * @return
	 */
	Object getChatGroups(Long limit, String cursor);

	/**
	 * Get Group(s) Details
	 *
	 * GET
	 * 
	 * @param groupIds
	 *            group id
	 * @return
	 */
	Object getChatGroupDetails(String[] groupIds);

	/**
	 * Create a Group
	 *
	 * POST
	 * 
	 * @param payload
	 *            <code>{"groupname":"testrestgrp12","desc":"server create group","public":true,"maxusers":300,"approval":true,"owner":"jma1","members":["jma2","jma3"]}</code>
	 * @return
	 */
	Object createChatGroup(Object payload);

	/**
	 * Update Group Details
	 *
	 * PUT
	 * 
	 * @param groupId
	 *            group id
	 * @param payload
	 *            <code>{"groupname":"testrestgrp12",description":"update groupinfo","maxusers":300}</code>
	 * @return
	 */
	Object modifyChatGroup(String groupId, Object payload);

	/**
	 * Delete a Group
	 *
	 * DELETE
	 * 
	 * @param groupId
	 *            group id
	 * @return
	 */
	Object deleteChatGroup(String groupId);

	/**
	 * Get a List of Members of Group
	 *
	 * GET
	 * 
	 * @param groupId
	 *            group id
	 * @return
	 */
	Object getChatGroupUsers(String groupId);

	/**
	 * Add a Member to Group
	 *
	 * POST
	 * 
	 * @param groupId
	 *            group id
	 * @param userId
	 *            user id
	 * @return
	 */
	Object addSingleUserToChatGroup(String groupId, String userId);

	/**
	 * Add Multiple Members to Group
	 *
	 * POST
	 * 
	 * @param groupId
	 *            group id
	 * @param payload
	 *            user id
	 * @return
	 * @see com.hyphenate.server.example.comm.body.UserNamesBody
	 */
	Object addBatchUsersToChatGroup(String groupId, Object payload);

	/**
	 * Remove a Member from the Group
	 *
	 * DELETE
	 * 
	 * @param groupId
	 *            group id
	 * @param userId
	 *            user id
	 * @return
	 */
	Object removeSingleUserFromChatGroup(String groupId, String userId);

	/**
	 * Remove multiple Member from the Group
	 *
	 * DELETE
	 * 
	 * @param groupId
	 *            group id
	 * @param userIds
	 *            user id
	 * @return
	 */
	Object removeBatchUsersFromChatGroup(String groupId, String[] userIds);

	/**
	 * Update Group Owner
	 *
	 * PUT
	 * 
	 * @param groupId
	 *            group id
	 * @param payload
	 *            new user id of group owner
	 * @return
     * @see com.hyphenate.server.example.comm.body.GroupOwnerTransferBody
	 */
	Object transferChatGroupOwner(String groupId, Object payload);

	/**
	 * Get Group Blocked Users
	 *
	 * GET
	 * 
	 * @param groupId
	 *            group id
	 * @return
	 */
	Object getChatGroupBlockUsers(String groupId);

	/**
	 * Block a Group Member
	 *
	 * POST
	 * 
	 * @param groupId
	 *            group id
	 * @param userId
	 *            user id
	 * @return
	 */
	Object addSingleBlockUserToChatGroup(String groupId, String userId);

	/**
	 * Block Group Members in Batch
	 *
	 * POST
	 * 
	 * @param groupId
	 *            group id
	 * @param payload
	 *            user id
	 * @return
     * @see com.hyphenate.server.example.comm.body.UserNamesBody
	 */
	Object addBatchBlockUsersToChatGroup(String groupId, Object payload);

	/**
	 * Unblock a Group Member
	 *
	 * DELETE
	 * 
	 * @param groupId
	 *            group id
	 * @param userId
	 *            user id
	 * @return
	 */
	Object removeSingleBlockUserFromChatGroup(String groupId, String userId);

	/**
	 * Unblock Group Members in Batch
	 *
	 * DELETE
	 * 
	 * @param groupId
	 *            group id
	 * @param userIds
	 *            user id
	 * @return
	 */
	Object removeBatchBlockUsersFromChatGroup(String groupId, String[] userIds);
}
