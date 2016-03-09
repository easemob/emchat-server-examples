package com.easemob.server.example.api.impl;

import com.easemob.server.example.api.ChatRoomAPI;
import com.easemob.server.example.api.EasemobRestAPI;
import com.easemob.server.example.comm.wrapper.BodyWrapper;
import com.easemob.server.example.comm.constant.HTTPMethod;
import com.easemob.server.example.comm.helper.HeaderHelper;
import com.easemob.server.example.comm.wrapper.HeaderWrapper;
import org.apache.commons.lang3.StringUtils;

public class EasemobChatRoom extends EasemobRestAPI implements ChatRoomAPI {
    private static final String ROOT_URI = "/chatrooms";

    public Object createChatRoom(Object payload) {
        String url = getContext().getSeriveURL() + getResourceRootURI();
        BodyWrapper body = (BodyWrapper) payload;
        HeaderWrapper header = HeaderHelper.getDefaultHeaderWithToken();

        return getInvoker().sendRequest(HTTPMethod.METHOD_POST, url, header, body, null);
    }

    public Object modifyChatRoom(String roomId, Object payload) {
        String url = getContext().getSeriveURL() + getResourceRootURI() + "/" + roomId;
        BodyWrapper body = (BodyWrapper) payload;
        HeaderWrapper header = HeaderHelper.getDefaultHeaderWithToken();

        return getInvoker().sendRequest(HTTPMethod.METHOD_PUT, url, header, body, null);
    }

    public Object deleteChatRoom(String roomId) {
        String url = getContext().getSeriveURL() + getResourceRootURI() + "/" + roomId;
        HeaderWrapper header = HeaderHelper.getDefaultHeaderWithToken();

        return getInvoker().sendRequest(HTTPMethod.METHOD_DELETE, url, header, null, null);
    }

    public Object getAllChatRooms() {
        String url = getContext().getSeriveURL() + getResourceRootURI();
        HeaderWrapper header = HeaderHelper.getDefaultHeaderWithToken();

        return getInvoker().sendRequest(HTTPMethod.METHOD_GET, url, header, null, null);
    }

    public Object getChatRoomDetail(String roomId) {
        String url = getContext().getSeriveURL() + getResourceRootURI() + "/" + roomId;
        HeaderWrapper header = HeaderHelper.getDefaultHeaderWithToken();

        return getInvoker().sendRequest(HTTPMethod.METHOD_GET, url, header, null, null);
    }

    public Object addSingleUserToChatRoom(String roomId, String userName) {
        String url = getContext().getSeriveURL() + getResourceRootURI() + "/" + roomId + "/users/" + userName;
        HeaderWrapper header = HeaderHelper.getDefaultHeaderWithToken();

        return getInvoker().sendRequest(HTTPMethod.METHOD_POST, url, header, null, null);
    }

    public Object addBatchUsersToChatRoom(String roomId, Object payload) {
        String url = getContext().getSeriveURL() + getResourceRootURI() + "/" + roomId + "/users";
        HeaderWrapper header = HeaderHelper.getDefaultHeaderWithToken();
        BodyWrapper body = (BodyWrapper) payload;

        return getInvoker().sendRequest(HTTPMethod.METHOD_POST, url, header, body, null);
    }

    public Object removeSingleUserFromChatRoom(String roomId, String userName) {
        String url = getContext().getSeriveURL() + getResourceRootURI() + "/" + roomId + "/users/" + userName;
        HeaderWrapper header = HeaderHelper.getDefaultHeaderWithToken();

        return getInvoker().sendRequest(HTTPMethod.METHOD_DELETE, url, header, null, null);
    }

    public Object removeBatchUsersFromChatRoom(String roomId, String[] userNames) {
        String url = getContext().getSeriveURL() + getResourceRootURI() + "/" + roomId + "/users/" + StringUtils.join(userNames, ",");
        HeaderWrapper header = HeaderHelper.getDefaultHeaderWithToken();

        return getInvoker().sendRequest(HTTPMethod.METHOD_DELETE, url, header, null, null);
    }

    @Override
    public String getResourceRootURI() {
        return ROOT_URI;
    }
}
