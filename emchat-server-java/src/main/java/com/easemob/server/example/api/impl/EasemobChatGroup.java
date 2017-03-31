package com.easemob.server.example.api.impl;

import com.easemob.server.example.api.ChatGroupAPI;
import com.easemob.server.example.comm.OrgInfo;
import com.easemob.server.example.comm.ResponseHandler;
import com.easemob.server.example.comm.EasemobAPI;
import com.easemob.server.example.comm.TokenUtil;
import io.swagger.client.ApiException;
import io.swagger.client.StringUtil;
import io.swagger.client.api.GroupsApi;
import io.swagger.client.model.*;


public class EasemobChatGroup implements ChatGroupAPI {

    private ResponseHandler responseHandler = new ResponseHandler();
    private GroupsApi api = new GroupsApi();
    @Override
    public Object getChatGroups(final Long limit,final String cursor) {
        return responseHandler.handle(new EasemobAPI() {
            @Override
            public Object invokeEasemobAPI() throws ApiException {
                return api.orgNameAppNameChatgroupsGet(OrgInfo.ORG_NAME,OrgInfo.APP_NAME,TokenUtil.getAccessToken(),limit+"",cursor);
            }
        });
    }

    @Override
    public Object getChatGroupDetails(final String[] groupIds) {
        return responseHandler.handle(new EasemobAPI() {
            @Override
            public Object invokeEasemobAPI() throws ApiException {
                return api.orgNameAppNameChatgroupsGroupIdsGet(OrgInfo.ORG_NAME,OrgInfo.APP_NAME,TokenUtil.getAccessToken(),StringUtil.join(groupIds,","));
            }
        });
    }
    @Override
    public Object createChatGroup(final Object payload) {
        return responseHandler.handle(new EasemobAPI() {
            @Override
            public Object invokeEasemobAPI() throws ApiException {
                return api.orgNameAppNameChatgroupsPost(OrgInfo.ORG_NAME,OrgInfo.APP_NAME,TokenUtil.getAccessToken(), (Group) payload);
            }
        });
    }

    @Override
    public Object modifyChatGroup(final String groupId,final Object payload) {
        return responseHandler.handle(new EasemobAPI() {
            @Override
            public Object invokeEasemobAPI() throws ApiException {
                return api.orgNameAppNameChatgroupsGroupIdPut(OrgInfo.ORG_NAME,OrgInfo.APP_NAME,TokenUtil.getAccessToken(),groupId, (ModifyGroup) payload);
            }
        });
    }

    @Override
    public Object deleteChatGroup(final String groupId) {
        return responseHandler.handle(new EasemobAPI() {
            @Override
            public Object invokeEasemobAPI() throws ApiException {
                return api.orgNameAppNameChatgroupsGroupIdDelete(OrgInfo.ORG_NAME,OrgInfo.APP_NAME,TokenUtil.getAccessToken(),groupId);
            }
        });
    }

    @Override
    public Object getChatGroupUsers(final String groupId) {
        return responseHandler.handle(new EasemobAPI() {
            @Override
            public Object invokeEasemobAPI() throws ApiException {
                return api.orgNameAppNameChatgroupsGroupIdUsersGet(OrgInfo.ORG_NAME,OrgInfo.APP_NAME,TokenUtil.getAccessToken(),groupId);
            }
        });
    }

    @Override
    public Object addSingleUserToChatGroup(final String groupId,final String userId) {
        final UserNames userNames = new UserNames();
        UserName userList = new UserName();
        userList.add(userId);
        userNames.usernames(userList);
        return responseHandler.handle(new EasemobAPI() {
            @Override
            public Object invokeEasemobAPI() throws ApiException {
                return api.orgNameAppNameChatgroupsGroupIdUsersPost(OrgInfo.ORG_NAME,OrgInfo.APP_NAME,TokenUtil.getAccessToken(),groupId,userNames);
            }
        });
    }

    @Override
    public Object addBatchUsersToChatGroup(final String groupId,final Object payload) {
        return responseHandler.handle(new EasemobAPI() {
            @Override
            public Object invokeEasemobAPI() throws ApiException {
                return api.orgNameAppNameChatgroupsGroupIdUsersPost(OrgInfo.ORG_NAME,OrgInfo.APP_NAME,TokenUtil.getAccessToken(),groupId, (UserNames) payload);
            }
        });
    }

    @Override
    public Object removeSingleUserFromChatGroup(final String groupId,final String userId) {
        return responseHandler.handle(new EasemobAPI() {
            @Override
            public Object invokeEasemobAPI() throws ApiException {
                return api.orgNameAppNameChatgroupsGroupIdUsersUsernameDelete(OrgInfo.ORG_NAME,OrgInfo.APP_NAME,TokenUtil.getAccessToken(),groupId,userId);
            }
        });
    }

    @Override
    public Object removeBatchUsersFromChatGroup(final String groupId,final String[] userIds) {
        return responseHandler.handle(new EasemobAPI() {
            @Override
            public Object invokeEasemobAPI() throws ApiException {
                return api.orgNameAppNameChatgroupsGroupIdUsersMembersDelete(OrgInfo.ORG_NAME,OrgInfo.APP_NAME,TokenUtil.getAccessToken(),groupId,StringUtil.join(userIds,","));
            }
        });
    }

    @Override
    public Object transferChatGroupOwner(final String groupId,final Object payload) {
        return responseHandler.handle(new EasemobAPI() {
            @Override
            public Object invokeEasemobAPI() throws ApiException {
                return api.orgNameAppNameChatgroupsGroupidPut(OrgInfo.ORG_NAME,OrgInfo.APP_NAME,TokenUtil.getAccessToken(),groupId, (NewOwner) payload);
            }
        });
    }

    @Override
    public Object getChatGroupBlockUsers(final String groupId) {
        return responseHandler.handle(new EasemobAPI() {
            @Override
            public Object invokeEasemobAPI() throws ApiException {
                return api.orgNameAppNameChatgroupsGroupIdBlocksUsersGet(OrgInfo.ORG_NAME,OrgInfo.APP_NAME,TokenUtil.getAccessToken(),groupId);
            }
        });
    }

    @Override
    public Object addSingleBlockUserToChatGroup(final String groupId,final String userId) {
        return responseHandler.handle(new EasemobAPI() {
            @Override
            public Object invokeEasemobAPI() throws ApiException {
                return api.orgNameAppNameChatgroupsGroupIdBlocksUsersUsernamePost(OrgInfo.ORG_NAME,OrgInfo.APP_NAME,TokenUtil.getAccessToken(),groupId,userId);
            }
        });
    }

    @Override
    public Object addBatchBlockUsersToChatGroup(final String groupId,final Object payload) {
        return responseHandler.handle(new EasemobAPI() {
            @Override
            public Object invokeEasemobAPI() throws ApiException {
                return api.orgNameAppNameChatgroupsGroupIdBlocksUsersPost(OrgInfo.ORG_NAME,OrgInfo.APP_NAME,TokenUtil.getAccessToken(),groupId, (UserNames) payload);
            }
        });
    }

    @Override
    public Object removeSingleBlockUserFromChatGroup(final String groupId,final String userId) {
        return responseHandler.handle(new EasemobAPI() {
            @Override
            public Object invokeEasemobAPI() throws ApiException {
                return api.orgNameAppNameChatgroupsGroupIdBlocksUsersUsernameDelete(OrgInfo.ORG_NAME,OrgInfo.APP_NAME,TokenUtil.getAccessToken(),groupId,userId);
            }
        });
    }

    @Override
    public Object removeBatchBlockUsersFromChatGroup(final String groupId,final String[] userIds) {
        return responseHandler.handle(new EasemobAPI() {
            @Override
            public Object invokeEasemobAPI() throws ApiException {
                return api.orgNameAppNameChatgroupsGroupIdBlocksUsersUsernamesDelete(OrgInfo.ORG_NAME,OrgInfo.APP_NAME,TokenUtil.getAccessToken(),groupId,StringUtil.join(userIds,","));
            }
        });
    }
}
