package com.easemob.server.example.api.impl;

import com.easemob.server.example.api.ChatGroupAPI;
import com.easemob.server.example.comm.ResponseHandle;
import com.easemob.server.example.comm.EasemobAPI;
import com.easemob.server.example.comm.TokenUtil;
import io.swagger.client.ApiException;
import io.swagger.client.StringUtil;
import io.swagger.client.api.GroupsApi;
import io.swagger.client.model.*;


public class EasemobChatGroup implements ChatGroupAPI {

    private ResponseHandle responseHandle = new ResponseHandle();
    private GroupsApi api = new GroupsApi();
    @Override
    public Object getChatGroups(final Long limit,final String cursor) {
        return responseHandle.handle(new EasemobAPI() {
            @Override
            public Object easemobAPIInvoker() throws ApiException {
                return api.orgNameAppNameChatgroupsGet(TokenUtil.ORG_NAME,TokenUtil.APP_NAME,TokenUtil.getAccessToken(),limit+"",cursor);
            }
        });
    }

    @Override
    public Object getChatGroupDetails(final String[] groupIds) {
        return responseHandle.handle(new EasemobAPI() {
            @Override
            public Object easemobAPIInvoker() throws ApiException {
                return api.orgNameAppNameChatgroupsGroupIdsGet(TokenUtil.ORG_NAME,TokenUtil.APP_NAME,TokenUtil.getAccessToken(),StringUtil.join(groupIds,","));
            }
        });
    }
    @Override
    public Object createChatGroup(final Object payload) {
        return responseHandle.handle(new EasemobAPI() {
            @Override
            public Object easemobAPIInvoker() throws ApiException {
                return api.orgNameAppNameChatgroupsPost(TokenUtil.ORG_NAME,TokenUtil.APP_NAME,TokenUtil.getAccessToken(), (Group) payload);
            }
        });
    }

    @Override
    public Object modifyChatGroup(final String groupId,final Object payload) {
        return responseHandle.handle(new EasemobAPI() {
            @Override
            public Object easemobAPIInvoker() throws ApiException {
                return api.orgNameAppNameChatgroupsGroupIdPut(TokenUtil.ORG_NAME,TokenUtil.APP_NAME,TokenUtil.getAccessToken(),groupId, (ModifyGroup) payload);
            }
        });
    }

    @Override
    public Object deleteChatGroup(final String groupId) {
        return responseHandle.handle(new EasemobAPI() {
            @Override
            public Object easemobAPIInvoker() throws ApiException {
                return api.orgNameAppNameChatgroupsGroupIdDelete(TokenUtil.ORG_NAME,TokenUtil.APP_NAME,TokenUtil.getAccessToken(),groupId);
            }
        });
    }

    @Override
    public Object getChatGroupUsers(final String groupId) {
        return responseHandle.handle(new EasemobAPI() {
            @Override
            public Object easemobAPIInvoker() throws ApiException {
                return api.orgNameAppNameChatgroupsGroupIdUsersGet(TokenUtil.ORG_NAME,TokenUtil.APP_NAME,TokenUtil.getAccessToken(),groupId);
            }
        });
    }

    @Override
    public Object addSingleUserToChatGroup(final String groupId,final String userId) {
        final UserNames userNames = new UserNames();
        UserName userList = new UserName();
        userList.add(userId);
        userNames.usernames(userList);
        return responseHandle.handle(new EasemobAPI() {
            @Override
            public Object easemobAPIInvoker() throws ApiException {
                return api.orgNameAppNameChatgroupsGroupIdUsersPost(TokenUtil.ORG_NAME,TokenUtil.APP_NAME,TokenUtil.getAccessToken(),groupId,userNames);
            }
        });
    }

    @Override
    public Object addBatchUsersToChatGroup(final String groupId,final Object payload) {
        return responseHandle.handle(new EasemobAPI() {
            @Override
            public Object easemobAPIInvoker() throws ApiException {
                return api.orgNameAppNameChatgroupsGroupIdUsersPost(TokenUtil.ORG_NAME,TokenUtil.APP_NAME,TokenUtil.getAccessToken(),groupId, (UserNames) payload);
            }
        });
    }

    @Override
    public Object removeSingleUserFromChatGroup(final String groupId,final String userId) {
        return responseHandle.handle(new EasemobAPI() {
            @Override
            public Object easemobAPIInvoker() throws ApiException {
                return api.orgNameAppNameChatgroupsGroupIdUsersUsernameDelete(TokenUtil.ORG_NAME,TokenUtil.APP_NAME,TokenUtil.getAccessToken(),groupId,userId);
            }
        });
    }

    @Override
    public Object removeBatchUsersFromChatGroup(final String groupId,final String[] userIds) {
        return responseHandle.handle(new EasemobAPI() {
            @Override
            public Object easemobAPIInvoker() throws ApiException {
                return api.orgNameAppNameChatgroupsGroupIdUsersMembersDelete(TokenUtil.ORG_NAME,TokenUtil.APP_NAME,TokenUtil.getAccessToken(),groupId,StringUtil.join(userIds,","));
            }
        });
    }

    @Override
    public Object transferChatGroupOwner(final String groupId,final Object payload) {
        return responseHandle.handle(new EasemobAPI() {
            @Override
            public Object easemobAPIInvoker() throws ApiException {
                return api.orgNameAppNameChatgroupsGroupidPut(TokenUtil.ORG_NAME,TokenUtil.APP_NAME,TokenUtil.getAccessToken(),groupId, (NewOwner) payload);
            }
        });
    }

    @Override
    public Object getChatGroupBlockUsers(final String groupId) {
        return responseHandle.handle(new EasemobAPI() {
            @Override
            public Object easemobAPIInvoker() throws ApiException {
                return api.orgNameAppNameChatgroupsGroupIdBlocksUsersGet(TokenUtil.ORG_NAME,TokenUtil.APP_NAME,TokenUtil.getAccessToken(),groupId);
            }
        });
    }

    @Override
    public Object addSingleBlockUserToChatGroup(final String groupId,final String userId) {
        return responseHandle.handle(new EasemobAPI() {
            @Override
            public Object easemobAPIInvoker() throws ApiException {
                return api.orgNameAppNameChatgroupsGroupIdBlocksUsersUsernamePost(TokenUtil.ORG_NAME,TokenUtil.APP_NAME,TokenUtil.getAccessToken(),groupId,userId);
            }
        });
    }

    @Override
    public Object addBatchBlockUsersToChatGroup(final String groupId,final Object payload) {
        return responseHandle.handle(new EasemobAPI() {
            @Override
            public Object easemobAPIInvoker() throws ApiException {
                return api.orgNameAppNameChatgroupsGroupIdBlocksUsersPost(TokenUtil.ORG_NAME,TokenUtil.APP_NAME,TokenUtil.getAccessToken(),groupId, (UserNames) payload);
            }
        });
    }

    @Override
    public Object removeSingleBlockUserFromChatGroup(final String groupId,final String userId) {
        return responseHandle.handle(new EasemobAPI() {
            @Override
            public Object easemobAPIInvoker() throws ApiException {
                return api.orgNameAppNameChatgroupsGroupIdBlocksUsersUsernameDelete(TokenUtil.ORG_NAME,TokenUtil.APP_NAME,TokenUtil.getAccessToken(),groupId,userId);
            }
        });
    }

    @Override
    public Object removeBatchBlockUsersFromChatGroup(final String groupId,final String[] userIds) {
        return responseHandle.handle(new EasemobAPI() {
            @Override
            public Object easemobAPIInvoker() throws ApiException {
                return api.orgNameAppNameChatgroupsGroupIdBlocksUsersUsernamesDelete(TokenUtil.ORG_NAME,TokenUtil.APP_NAME,TokenUtil.getAccessToken(),groupId,StringUtil.join(userIds,","));
            }
        });
    }
}
