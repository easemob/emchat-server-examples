package com.easemob.server.example.api.impl;

import com.easemob.server.example.api.ChatGroupAPI;
import com.easemob.server.example.comm.DoMethod;
import com.easemob.server.example.comm.MyHttpRequest;
import com.easemob.server.example.comm.TokenUtil;
import io.swagger.client.ApiException;
import io.swagger.client.StringUtil;
import io.swagger.client.api.GroupsApi;
import io.swagger.client.model.*;


public class EasemobChatGroup implements ChatGroupAPI {

    private DoMethod doMethod = new DoMethod();
    private GroupsApi api = new GroupsApi();
    @Override
    public Object getChatGroups(final Long limit,final String cursor) {
        return doMethod.sendHttpRequest(new MyHttpRequest() {
            @Override
            public Object doHttpRequest(String authorization) throws ApiException {
                return api.orgNameAppNameChatgroupsGet(TokenUtil.ORG_NAME,TokenUtil.APP_NAME,authorization,limit+"",cursor);
            }
        }, TokenUtil.getAccessToken());
    }

    @Override
    public Object getChatGroupDetails(final String[] groupIds) {
        return doMethod.sendHttpRequest(new MyHttpRequest() {
            @Override
            public Object doHttpRequest(String authorization) throws ApiException {
                return api.orgNameAppNameChatgroupsGroupIdsGet(TokenUtil.ORG_NAME,TokenUtil.APP_NAME,authorization,StringUtil.join(groupIds,","));
            }
        }, TokenUtil.getAccessToken());
    }

    @Override
    public Object createChatGroup(final Object payload) {
        return doMethod.sendHttpRequest(new MyHttpRequest() {
            @Override
            public Object doHttpRequest(String authorization) throws ApiException {
                return api.orgNameAppNameChatgroupsPost(TokenUtil.ORG_NAME,TokenUtil.APP_NAME,authorization, (Group) payload);
            }
        }, TokenUtil.getAccessToken());
    }

    @Override
    public Object modifyChatGroup(final String groupId,final Object payload) {
        return doMethod.sendHttpRequest(new MyHttpRequest() {
            @Override
            public Object doHttpRequest(String authorization) throws ApiException {
                return api.orgNameAppNameChatgroupsGroupIdPut(TokenUtil.ORG_NAME,TokenUtil.APP_NAME,authorization,groupId, (ModifyGroup) payload);
            }
        }, TokenUtil.getAccessToken());
    }

    @Override
    public Object deleteChatGroup(final String groupId) {
        return doMethod.sendHttpRequest(new MyHttpRequest() {
            @Override
            public Object doHttpRequest(String authorization) throws ApiException {
                return api.orgNameAppNameChatgroupsGroupIdDelete(TokenUtil.ORG_NAME,TokenUtil.APP_NAME,authorization,groupId);
            }
        }, TokenUtil.getAccessToken());
    }

    @Override
    public Object getChatGroupUsers(final String groupId) {
        return doMethod.sendHttpRequest(new MyHttpRequest() {
            @Override
            public Object doHttpRequest(String authorization) throws ApiException {
                return api.orgNameAppNameChatgroupsGroupIdUsersGet(TokenUtil.ORG_NAME,TokenUtil.APP_NAME,authorization,groupId);
            }
        }, TokenUtil.getAccessToken());
    }

    @Override
    public Object addSingleUserToChatGroup(final String groupId,final String userId) {
        final UserNames userNames = new UserNames();
        UserName userList = new UserName();
        userList.add(userId);
        userNames.usernames(userList);
        return doMethod.sendHttpRequest(new MyHttpRequest() {
            @Override
            public Object doHttpRequest(String authorization) throws ApiException {
                return api.orgNameAppNameChatgroupsGroupIdUsersPost(TokenUtil.ORG_NAME,TokenUtil.APP_NAME,authorization,groupId,userNames);
            }
        }, TokenUtil.getAccessToken());
    }

    @Override
    public Object addBatchUsersToChatGroup(final String groupId,final Object payload) {
        return doMethod.sendHttpRequest(new MyHttpRequest() {
            @Override
            public Object doHttpRequest(String authorization) throws ApiException {
                return api.orgNameAppNameChatgroupsGroupIdUsersPost(TokenUtil.ORG_NAME,TokenUtil.APP_NAME,authorization,groupId, (UserNames) payload);
            }
        }, TokenUtil.getAccessToken());
    }

    @Override
    public Object removeSingleUserFromChatGroup(final String groupId,final String userId) {
        return doMethod.sendHttpRequest(new MyHttpRequest() {
            @Override
            public Object doHttpRequest(String authorization) throws ApiException {
                return api.orgNameAppNameChatgroupsGroupIdUsersUsernameDelete(TokenUtil.ORG_NAME,TokenUtil.APP_NAME,authorization,groupId,userId);
            }
        }, TokenUtil.getAccessToken());
    }

    @Override
    public Object removeBatchUsersFromChatGroup(final String groupId,final String[] userIds) {
        return doMethod.sendHttpRequest(new MyHttpRequest() {
            @Override
            public Object doHttpRequest(String authorization) throws ApiException {
                return api.orgNameAppNameChatgroupsGroupIdUsersMembersDelete(TokenUtil.ORG_NAME,TokenUtil.APP_NAME,authorization,groupId,StringUtil.join(userIds,","));
            }
        }, TokenUtil.getAccessToken());
    }

    @Override
    public Object transferChatGroupOwner(final String groupId,final Object payload) {
        return doMethod.sendHttpRequest(new MyHttpRequest() {
            @Override
            public Object doHttpRequest(String authorization) throws ApiException {
                return api.orgNameAppNameChatgroupsGroupidPut(TokenUtil.ORG_NAME,TokenUtil.APP_NAME,authorization,groupId, (NewOwner) payload);
            }
        }, TokenUtil.getAccessToken());
    }

    @Override
    public Object getChatGroupBlockUsers(final String groupId) {
        return doMethod.sendHttpRequest(new MyHttpRequest() {
            @Override
            public Object doHttpRequest(String authorization) throws ApiException {
                return api.orgNameAppNameChatgroupsGroupIdBlocksUsersGet(TokenUtil.ORG_NAME,TokenUtil.APP_NAME,authorization,groupId);
            }
        }, TokenUtil.getAccessToken());
    }

    @Override
    public Object addSingleBlockUserToChatGroup(final String groupId,final String userId) {
        return doMethod.sendHttpRequest(new MyHttpRequest() {
            @Override
            public Object doHttpRequest(String authorization) throws ApiException {
                return api.orgNameAppNameChatgroupsGroupIdBlocksUsersUsernamePost(TokenUtil.ORG_NAME,TokenUtil.APP_NAME,authorization,groupId,userId);
            }
        }, TokenUtil.getAccessToken());
    }

    @Override
    public Object addBatchBlockUsersToChatGroup(final String groupId,final Object payload) {
        return doMethod.sendHttpRequest(new MyHttpRequest() {
            @Override
            public Object doHttpRequest(String authorization) throws ApiException {
                return api.orgNameAppNameChatgroupsGroupIdBlocksUsersPost(TokenUtil.ORG_NAME,TokenUtil.APP_NAME,authorization,groupId, (UserNames) payload);
            }
        }, TokenUtil.getAccessToken());
    }

    @Override
    public Object removeSingleBlockUserFromChatGroup(final String groupId,final String userId) {
        return doMethod.sendHttpRequest(new MyHttpRequest() {
            @Override
            public Object doHttpRequest(String authorization) throws ApiException {
                return api.orgNameAppNameChatgroupsGroupIdBlocksUsersUsernameDelete(TokenUtil.ORG_NAME,TokenUtil.APP_NAME,authorization,groupId,userId);
            }
        }, TokenUtil.getAccessToken());
    }

    @Override
    public Object removeBatchBlockUsersFromChatGroup(final String groupId,final String[] userIds) {
        return doMethod.sendHttpRequest(new MyHttpRequest() {
            @Override
            public Object doHttpRequest(String authorization) throws ApiException {
                return api.orgNameAppNameChatgroupsGroupIdBlocksUsersUsernamesDelete(TokenUtil.ORG_NAME,TokenUtil.APP_NAME,authorization,groupId,StringUtil.join(userIds,","));
            }
        }, TokenUtil.getAccessToken());
    }
}
