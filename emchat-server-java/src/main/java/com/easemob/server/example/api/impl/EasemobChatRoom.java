package com.easemob.server.example.api.impl;

import com.easemob.server.example.api.ChatRoomAPI;
import com.easemob.server.example.api.EasemobRestAPI;
import com.easemob.server.example.comm.BodyWrapper;
import com.easemob.server.example.comm.HTTPMethod;
import com.easemob.server.example.comm.HeaderHelper;
import com.easemob.server.example.comm.HeaderWrapper;

public class EasemobChatRoom extends EasemobRestAPI implements ChatRoomAPI {
    private static final String ROOT_URI = "/chatrooms";

    public Object createChatRoom(Object payload) {
        String url = getContext().getSeriveURL() + getResourceRootURI();
        BodyWrapper body = (BodyWrapper) payload;
        HeaderWrapper header = HeaderHelper.getDefaultHeaderWithToken();

        return getInvoker().sendRequest(HTTPMethod.METHOD_POST, url, header, body, null);
    }

    public Object modifyChatRoom(String roomId, Object payload) {
        return null;
    }

    public Object deleteChatRoom(String roomId) {
        return null;
    }

    public Object getAllChatRooms() {
        return null;
    }

    public Object getChatRoomDetail(String roomId) {
        return null;
    }

    public Object addSingleUserToChatRoom(String roomId, String userName) {
        return null;
    }

    public Object addBatchUsersToChatRoom(String roomId, String[] userNames) {
        return null;
    }

    public Object removeSingleUserFromChatRoom(String roomId, String userName) {
        return null;
    }

    public Object removeBatchUsersFromChatRoom(String roomId, String[] userNames) {
        return null;
    }

    @Override
    public String getResourceRootURI() {
        return ROOT_URI;
    }
}
