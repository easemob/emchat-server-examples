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
     * <p>
     * POST
     *
     * @param payload <code>{"username":"${username}","password":"${password}", "nickname":"${nickname}"}</code>
     * @return
     */
    Object createUser(Object payload);

    /**
     * Register multiple user IM accounts
     * <p>
     * POST
     *
     * @param payload <code>[{"username":"${username1}","password":"${password1}"},…,{"username":"${username2}","password":"${password2}"}]</code>
     * @return
     */
    Object createUsers(Object payload);

    /**
     * Get a User
     * <p>
     * GET
     *
     * @param username username or user ID
     * @return
     */
    Object getUsersByUsername(String username);

    /**
     * Get multiple user IM accounts
     * <p>
     * GET
     *
     * @param limit  number of user to get. Default 10.
     * @param cursor pagination, index of page if exceeding one page
     * @return
     */
    Object getUsersWithPagination(Long limit, String cursor);

    /**
     * Delete a User
     * <p>
     * DELETE
     *
     * @param username username or user ID
     * @return
     */
    Object deleteUserByUsername(String username);

    /**
     * Delete user IM account(s)
     * <p>
     * DELETE
     *
     * @param limit Number of user to be deleted. Recommend delete 100-500 at a time
     * @return
     */
    Object deleteUsers(Long limit, String cursor);

    /**
     * Reset User's Password
     * <p>
     * PUT
     *
     * @param username username or user ID
     * @param payload  <code>{"newpassword" : "${newPasswordCharacters}"}</code>
     * @return
     */
    Object updateUserPassword(String username, Object payload);

    /**
     * Update User's APNs Display Name
     * <p>
     * PUT
     *
     * @param username username or user ID
     * @param payload  <code>{"nickname" : "${nickname}"}</code>
     * @return
     */
    Object updateUserNickName(String username, Object payload);

    /**
     * Add Contact for User
     * <p>
     * POST
     *
     * @param username   user initiated the action
     * @param friendName user to be added
     * @return
     */
    Object addContact(String username, String friendName);

    /**
     * Remove Contact from User
     * <p>
     * DELETE
     *
     * @param username   user initiated the action
     * @param friendName user to be removed
     * @return
     */
    Object deleteContact(String username, String friendName);

    /**
     * Get a List of Contacts
     * <p>
     * GET
     *
     * @param username username or user ID
     * @return
     */
    Object getContacts(String username);

    /**
     * Get a List of Blocked Users
     * <p>
     * GET
     *
     * @param username username or user ID
     * @return
     */
    Object getBlacklist(String username);

    /**
     * Block User(s)
     * <p>
     * POST
     *
     * @param username user initiated the action
     * @param payload  users to be blocked
     *                 <code>{"usernames":["user1", "user2"]}</code>
     * @return
     */
    Object blockUsers(String username, Object payload);

    /**
     * Unblock User(s)
     * <p>
     * DELETE
     *
     * @param username    user initiated the action
     * @param blacklistID users to be unblocked
     * @return
     */
    Object unblockUsers(String username, String blacklistID);

    /**
     * Get User Online Status
     * <p>
     * GET
     *
     * @param username username or user ID
     * @return
     */
    Object getUserStatus(String username);

    /**
     * Get Offline Message Count
     * <p>
     * GET
     *
     * @param username username or user ID
     * @return
     */
    Object getUndeliveredMessageCountOfUser(String username);

    /**
     * Get Offline Message Status
     * <p>
     * GET
     *
     * @param username username or user ID
     * @param msgId    message ID
     * @return
     */
    Object getUndeliveredMessageStatusOfUser(String username, String msgId);

    /**
     * Deactivate User Account
     * <p>
     * POST
     *
     * @param username username or user ID
     * @return
     */
    Object deactivateUser(String username);

    /**
     * Activate User Account
     * <p>
     * POST
     *
     * @param username username or user ID
     * @return
     */
    Object activateUser(String username);

    /**
     * Logout User
     * <p>
     * GET
     *
     * @param username username or user ID
     * @return
     */
    Object logoutUser(String username);

    /**
     * Get a List of Groups of User Joined
     * <p>
     * GET
     *
     * @param username username or user ID
     * @return
     */
    Object getGroupsOfUserJoined(String username);

    /**
     * Get All the Chat Rooms of User Joined
     * <p>
     * GET
     *
     * @param username username or user ID
     * @return
     */
    Object getChatRoomsOfUserJoined(String username);
}
