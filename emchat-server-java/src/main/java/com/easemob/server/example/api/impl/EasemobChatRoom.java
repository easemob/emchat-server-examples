package com.easemob.server.example.api.impl;

import com.easemob.server.example.api.ChatRoomAPI;

public class EasemobChatRoom implements ChatRoomAPI {

    @Override
    public Object createChatRoom(Object payload) {
        return null;
    }

    @Override
    public Object modifyChatRoom(String roomId, Object payload) {
        return null;
    }

    @Override
    public Object deleteChatRoom(String roomId) {
        return null;
    }

    @Override
    public Object getAllChatRooms() {
        return null;
    }

    @Override
    public Object getChatRoomDetail(String roomId) {
        return null;
    }

    @Override
    public Object addSingleUserToChatRoom(String roomId, String userName) {
        return null;
    }

    @Override
    public Object addBatchUsersToChatRoom(String roomId, Object payload) {
        return null;
    }

    @Override
    public Object removeSingleUserFromChatRoom(String roomId, String userName) {
        return null;
    }

    @Override
    public Object removeBatchUsersFromChatRoom(String roomId, String[] userNames) {
        return null;
    }
}
