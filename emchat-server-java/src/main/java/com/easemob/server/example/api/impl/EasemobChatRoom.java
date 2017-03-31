package com.easemob.server.example.api.impl;

import com.easemob.server.example.api.ChatRoomAPI;
import com.easemob.server.example.comm.OrgInfo;
import com.easemob.server.example.comm.ResponseHandle;
import com.easemob.server.example.comm.EasemobAPI;
import com.easemob.server.example.comm.TokenUtil;
import io.swagger.client.ApiException;
import io.swagger.client.StringUtil;
import io.swagger.client.api.ChatRoomsApi;
import io.swagger.client.model.Chatroom;
import io.swagger.client.model.ModifyChatroom;
import io.swagger.client.model.UserNames;

public class EasemobChatRoom implements ChatRoomAPI {
    private ResponseHandle responseHandle = new ResponseHandle();
    private ChatRoomsApi api = new ChatRoomsApi();

    @Override
    public Object createChatRoom(final Object payload) {
        return responseHandle.handle(new EasemobAPI() {
            @Override
            public Object easemobAPIInvoker() throws ApiException {
                return api.orgNameAppNameChatroomsPost(OrgInfo.ORG_NAME,OrgInfo.APP_NAME,TokenUtil.getAccessToken(), (Chatroom) payload);
            }
        });
    }

    @Override
    public Object modifyChatRoom(final String roomId,final Object payload) {
        return responseHandle.handle(new EasemobAPI() {
            @Override
            public Object easemobAPIInvoker() throws ApiException {
                return api.orgNameAppNameChatroomsChatroomIdPut(OrgInfo.ORG_NAME,OrgInfo.APP_NAME,TokenUtil.getAccessToken(),roomId, (ModifyChatroom) payload);
            }
        });
    }

    @Override
    public Object deleteChatRoom(final String roomId) {
        return responseHandle.handle(new EasemobAPI() {
            @Override
            public Object easemobAPIInvoker() throws ApiException {
                return api.orgNameAppNameChatroomsChatroomIdDelete(OrgInfo.ORG_NAME,OrgInfo.APP_NAME,TokenUtil.getAccessToken(),roomId);
            }
        });
    }

    @Override
    public Object getAllChatRooms() {
        return responseHandle.handle(new EasemobAPI() {
            @Override
            public Object easemobAPIInvoker() throws ApiException {
                return api.orgNameAppNameChatroomsGet(OrgInfo.ORG_NAME,OrgInfo.APP_NAME,TokenUtil.getAccessToken());
            }
        });
    }

    @Override
    public Object getChatRoomDetail(final String roomId) {
        return responseHandle.handle(new EasemobAPI() {
            @Override
            public Object easemobAPIInvoker() throws ApiException {
                return api.orgNameAppNameChatroomsChatroomIdGet(OrgInfo.ORG_NAME,OrgInfo.APP_NAME,TokenUtil.getAccessToken(),roomId);
            }
        });
    }

    @Override
    public Object addSingleUserToChatRoom(final String roomId,final String userName) {
        return responseHandle.handle(new EasemobAPI() {
            @Override
            public Object easemobAPIInvoker() throws ApiException {
                return api.orgNameAppNameChatroomsChatroomIdUsersUsernamePost(OrgInfo.ORG_NAME,OrgInfo.APP_NAME,TokenUtil.getAccessToken(),roomId,userName);
            }
        });
    }

    @Override
    public Object addBatchUsersToChatRoom(final String roomId,final Object payload) {
        return responseHandle.handle(new EasemobAPI() {
            @Override
            public Object easemobAPIInvoker() throws ApiException {
                return api.orgNameAppNameChatroomsChatroomIdUsersPost(OrgInfo.ORG_NAME,OrgInfo.APP_NAME,TokenUtil.getAccessToken(),roomId, (UserNames) payload);
            }
        });
    }

    @Override
    public Object removeSingleUserFromChatRoom(final String roomId,final String userName) {
        return responseHandle.handle(new EasemobAPI() {
            @Override
            public Object easemobAPIInvoker() throws ApiException {
                return api.orgNameAppNameChatroomsChatroomIdUsersUsernameDelete(OrgInfo.ORG_NAME,OrgInfo.APP_NAME,TokenUtil.getAccessToken(),roomId,userName);
            }
        });
    }

    @Override
    public Object removeBatchUsersFromChatRoom(final String roomId,final String[] userNames) {
        return responseHandle.handle(new EasemobAPI() {
            @Override
            public Object easemobAPIInvoker() throws ApiException {
                return api.orgNameAppNameChatroomsChatroomIdUsersUsernamesDelete(OrgInfo.ORG_NAME,OrgInfo.APP_NAME,TokenUtil.getAccessToken(),roomId, StringUtil.join(userNames,","));
            }
        });
    }
}
