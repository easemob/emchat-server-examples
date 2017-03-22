package com.easemob.server.example.api.impl;

import com.easemob.server.example.api.ChatGroupAPI;
public class EasemobChatGroup implements ChatGroupAPI {

    @Override
    public Object getChatGroups(Long limit, String cursor) {
        return null;
    }

    @Override
    public Object getChatGroupDetails(String[] groupIds) {
        return null;
    }

    @Override
    public Object createChatGroup(Object payload) {
        return null;
    }

    @Override
    public Object modifyChatGroup(String groupId, Object payload) {
        return null;
    }

    @Override
    public Object deleteChatGroup(String groupId) {
        return null;
    }

    @Override
    public Object getChatGroupUsers(String groupId) {
        return null;
    }

    @Override
    public Object addSingleUserToChatGroup(String groupId, String userId) {
        return null;
    }

    @Override
    public Object addBatchUsersToChatGroup(String groupId, Object payload) {
        return null;
    }

    @Override
    public Object removeSingleUserFromChatGroup(String groupId, String userId) {
        return null;
    }

    @Override
    public Object removeBatchUsersFromChatGroup(String groupId, String[] userIds) {
        return null;
    }

    @Override
    public Object transferChatGroupOwner(String groupId, Object payload) {
        return null;
    }

    @Override
    public Object getChatGroupBlockUsers(String groupId) {
        return null;
    }

    @Override
    public Object addSingleBlockUserToChatGroup(String groupId, String userId) {
        return null;
    }

    @Override
    public Object addBatchBlockUsersToChatGroup(String groupId, Object payload) {
        return null;
    }

    @Override
    public Object removeSingleBlockUserFromChatGroup(String groupId, String userId) {
        return null;
    }

    @Override
    public Object removeBatchBlockUsersFromChatGroup(String groupId, String[] userIds) {
        return null;
    }
}
