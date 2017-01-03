package com.hyphenate.server.example.api;

/**
 * This interface is created for RestAPI of User Integration, it should be
 * synchronized with the API list.
 *
 * @author Hyphenate
 * @see http://docs.hyphenate.io
 */
public interface IMUserAPI {

	/**
	 * Register a user IM account
	 *
	 * POST
	 * 
	 * @param payload
	 *            <code>{"username":"${username}","password":"${password}", "nickname":"${nickname}"}</code>
	 * @return
	 */
	Object createNewIMUserSingle(Object payload);

	/**
	 * Register multiple user IM accounts
	 *
	 * POST
	 * 
	 * @param payload
	 *            <code>[{"username":"${username1}","password":"${password1}"},…,{"username":"${username2}","password":"${password2}"}]</code>
	 * @return
	 */
	Object createNewIMUserBatch(Object payload);

	/**
	 * Get user IM account
	 *
	 * GET
	 * 
	 * @param userName
	 *            username or user ID
	 * @return
	 */
	Object getIMUserByUserName(String userName);

	/**
	 * Get multiple user IM accounts
	 *
	 * GET
	 * 
	 * @param limit
	 *            number of user to get. Default 10.
	 * @param cursor
	 *            pagination, index of page if exceeding one page
	 * @return
	 */
	Object getIMUsersBatch(Long limit, String cursor);

	/**
	 * Delete a User
	 *
	 * DELETE
	 * 
	 * @param userName
	 *            username or user ID
	 * @return
	 */
	Object deleteIMUserByUserName(String userName);

	/**
	 * Delete user IM account(s)
	 *
	 * DELETE
	 * 
	 * @param limit
	 *            Number of user to be deleted. Recommend delete 100-500 at a time
	 * @return
	 */
	Object deleteIMUserBatch(Long limit, String cursor);

	/**
	 * Update user IM account password
	 *
	 * PUT
	 * 
	 * @param userName
     *            username or user ID
	 * @param payload
	 *            <code>{"newpassword" : "${newPasswordCharacters}"}</code>
	 * @return
	 */
	Object modifyIMUserPasswordWithAdminToken(String userName, Object payload);

	/**
	 * Update user nickname
	 *
	 * PUT
	 * 
	 * @param userName
	 *            username or user ID
	 * @param payload
	 *            <code>{"nickname" : "${昵称值}"}</code>
	 * @return
	 */
	Object modifyIMUserNickNameWithAdminToken(String userName, Object payload);

	/**
	 * Add contact for user <br>
	 * POST
	 * 
	 * @param userName
     *            user initiated the action
	 * @param friendName
	 *            user to be added
	 * @return
	 */
	Object addFriendSingle(String userName, String friendName);

	/**
	 * Remove contact relationship
	 *
	 * DELETE
	 * 
	 * @param userName
     *            user initiated the action
	 * @param friendName
     *            user to be removed
	 * @return
	 */
	Object deleteFriendSingle(String userName, String friendName);

	/**
	 * Get user's contact list
	 *
	 * GET
	 * 
	 * @param userName
     *            username or user ID
	 * @return
	 */
	Object getFriends(String userName);

	/**
     * Get user's blacklist
	 *
	 * GET
	 * 
	 * @param userName
     *            username or user ID
	 * @return
	 */
	Object getBlackList(String userName);

	/**
	 * Block user(s)
	 *
	 * POST
	 * 
	 * @param userName
     *            user initiated the action
	 * @param payload
     *            users to be blocked
	 *            <code>{"usernames":["user1", "user2"]}</code>
	 * @return
	 */
	Object addToBlackList(String userName, Object payload);

	/**
	 * Unblock user
	 *
	 * DELETE
	 * 
	 * @param userName
     *            user initiated the action
	 * @param blackListName
     *            users to be unblocked
	 * @return
	 */
	Object removeFromBlackList(String userName, String blackListName);

	/**
	 * Get user online status
	 *
	 * GET
	 * 
	 * @param userName
     *            username or user ID
	 * @return
	 */
	Object getIMUserStatus(String userName);

	/**
	 * Get offline message count of a user
	 *
	 * GET
	 * 
	 * @param userName
     *            username or user ID
	 * @return
	 */
	Object getOfflineMsgCount(String userName);

	/**
	 * Get user offline message status
	 *
	 * GET
	 * 
	 * @param userName
     *            username or user ID
	 * @param msgId
	 *            message ID
	 * @return
	 */
	Object getSpecifiedOfflineMsgStatus(String userName, String msgId);

	/**
	 * Deactivate user account
	 *
	 * POST
	 * 
	 * @param userName
     *            username or user ID
	 * @return
	 */
	Object deactivateIMUser(String userName);

	/**
	 * Activate User Account
	 *
	 * POST
	 * 
	 * @param userName
     *            username or user ID
	 * @return
	 */
	Object activateIMUser(String userName);

	/**
	 * Logout User
	 *
	 * GET
	 * 
	 * @param userName
     *            username or user ID
	 * @return
	 */
	Object disconnectIMUser(String userName);

	/**
	 * Get a List of Groups of User Joined
	 *
	 * GET
	 * 
	 * @param userName
     *            username or user ID
	 * @return
	 * @see http://docs.hyphenate.io
	 */
	Object getIMUserAllChatGroups(String userName);

	/**
	 * Get All the Chat Rooms of User Joined
	 *
	 * GET
	 * 
	 * @param userName
     *            username or user ID
	 * @return
	 * @see http://docs.hyphenate.io
	 */
	Object getIMUserAllChatRooms(String userName);
}
