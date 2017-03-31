package com.easemob.server.example.api.impl;


import com.easemob.server.example.api.IMUserAPI;
import com.easemob.server.example.comm.EasemobAPI;
import com.easemob.server.example.comm.OrgInfo;
import com.easemob.server.example.comm.ResponseHandler;
import com.easemob.server.example.comm.TokenUtil;
import io.swagger.client.ApiException;
import io.swagger.client.api.UsersApi;
import io.swagger.client.model.NewPassword;
import io.swagger.client.model.Nickname;
import io.swagger.client.model.RegisterUsers;
import io.swagger.client.model.UserNames;


public class EasemobIMUsers  implements IMUserAPI {

	private UsersApi api = new UsersApi();
	private ResponseHandler responseHandler = new ResponseHandler();
	@Override
	public Object createNewIMUserSingle(final Object payload) {
		return responseHandler.handle(new EasemobAPI() {
			@Override
			public Object invokeEasemobAPI() throws ApiException {
				return api.orgNameAppNameUsersPost(OrgInfo.ORG_NAME,OrgInfo.APP_NAME, (RegisterUsers) payload,TokenUtil.getAccessToken());
			}
		});
	}

	@Override
	public Object createNewIMUserBatch(final Object payload) {
		return responseHandler.handle(new EasemobAPI() {
			@Override
			public Object invokeEasemobAPI() throws ApiException {
				return api.orgNameAppNameUsersPost(OrgInfo.ORG_NAME,OrgInfo.APP_NAME, (RegisterUsers) payload,TokenUtil.getAccessToken());
			}
		});
	}

	@Override
	public Object getIMUserByUserName(final String userName) {
		return responseHandler.handle(new EasemobAPI() {
			@Override
			public Object invokeEasemobAPI() throws ApiException {
				return api.orgNameAppNameUsersUsernameGet(OrgInfo.ORG_NAME,OrgInfo.APP_NAME,TokenUtil.getAccessToken(),userName);
		}
		});
	}

	@Override
	public Object getIMUsersBatch(final Long limit,final String cursor) {
		return responseHandler.handle(new EasemobAPI() {
			@Override
			public Object invokeEasemobAPI() throws ApiException {
				return api.orgNameAppNameUsersGet(OrgInfo.ORG_NAME,OrgInfo.APP_NAME,TokenUtil.getAccessToken(),limit+"",cursor);
			}
		});
	}

	@Override
	public Object deleteIMUserByUserName(final String userName) {
		return responseHandler.handle(new EasemobAPI() {
			@Override
			public Object invokeEasemobAPI() throws ApiException {
				return api.orgNameAppNameUsersUsernameDelete(OrgInfo.ORG_NAME,OrgInfo.APP_NAME,TokenUtil.getAccessToken(),userName);
			}
		});
	}

	@Override
	public Object deleteIMUserBatch(final Long limit,final String cursor) {
		return responseHandler.handle(new EasemobAPI() {
			@Override
			public Object invokeEasemobAPI() throws ApiException {
				return api.orgNameAppNameUsersDelete(OrgInfo.ORG_NAME,OrgInfo.APP_NAME,TokenUtil.getAccessToken(),limit+"",cursor);
			}
		});
	}

	@Override
	public Object modifyIMUserPasswordWithAdminToken(final String userName, final Object payload) {
		return responseHandler.handle(new EasemobAPI() {
			@Override
			public Object invokeEasemobAPI() throws ApiException {
				return api.orgNameAppNameUsersUsernamePasswordPut(OrgInfo.ORG_NAME,OrgInfo.APP_NAME,userName, (NewPassword) payload,TokenUtil.getAccessToken());
			}
		});
	}

	@Override
	public Object modifyIMUserNickNameWithAdminToken(final String userName,final Object payload) {
		return responseHandler.handle(new EasemobAPI() {
			@Override
			public Object invokeEasemobAPI() throws ApiException {
				return api.orgNameAppNameUsersUsernamePut(OrgInfo.ORG_NAME,OrgInfo.APP_NAME,userName, (Nickname) payload,TokenUtil.getAccessToken());
			}
		});
	}

	@Override
	public Object addFriendSingle(final String userName,final String friendName) {
		return responseHandler.handle(new EasemobAPI() {
			@Override
			public Object invokeEasemobAPI() throws ApiException {
				return api.orgNameAppNameUsersOwnerUsernameContactsUsersFriendUsernamePost(OrgInfo.ORG_NAME,OrgInfo.APP_NAME,TokenUtil.getAccessToken(),userName,friendName);
			}
		});
	}

	@Override
	public Object deleteFriendSingle(final String userName,final String friendName) {
		return responseHandler.handle(new EasemobAPI() {
			@Override
			public Object invokeEasemobAPI() throws ApiException {
				return api.orgNameAppNameUsersOwnerUsernameContactsUsersFriendUsernameDelete(OrgInfo.ORG_NAME,OrgInfo.APP_NAME,TokenUtil.getAccessToken(),userName,friendName);
			}
		});
	}

	@Override
	public Object getFriends(final String userName) {
		return responseHandler.handle(new EasemobAPI() {
			@Override
			public Object invokeEasemobAPI() throws ApiException {
				return api.orgNameAppNameUsersOwnerUsernameContactsUsersGet(OrgInfo.ORG_NAME,OrgInfo.APP_NAME,TokenUtil.getAccessToken(),userName);
			}
		});
	}

	@Override
	public Object getBlackList(final String userName) {
		return responseHandler.handle(new EasemobAPI() {
			@Override
			public Object invokeEasemobAPI() throws ApiException {
				return api.orgNameAppNameUsersOwnerUsernameBlocksUsersGet(OrgInfo.ORG_NAME,OrgInfo.APP_NAME,TokenUtil.getAccessToken(),userName);
			}
		});
	}

	@Override
	public Object addToBlackList(final String userName,final Object payload) {
		return responseHandler.handle(new EasemobAPI() {
			@Override
			public Object invokeEasemobAPI() throws ApiException {
				return api.orgNameAppNameUsersOwnerUsernameBlocksUsersPost(OrgInfo.ORG_NAME,OrgInfo.APP_NAME,TokenUtil.getAccessToken(),userName, (UserNames) payload);
			}
		});
	}

	@Override
	public Object removeFromBlackList(final String userName,final String blackListName) {
		return responseHandler.handle(new EasemobAPI() {
			@Override
			public Object invokeEasemobAPI() throws ApiException {
				return api.orgNameAppNameUsersOwnerUsernameBlocksUsersBlockedUsernameDelete(OrgInfo.ORG_NAME,OrgInfo.APP_NAME,TokenUtil.getAccessToken(),userName,blackListName);
			}
		});
	}

	@Override
	public Object getIMUserStatus(final String userName) {
		return responseHandler.handle(new EasemobAPI() {
			@Override
			public Object invokeEasemobAPI() throws ApiException {
				return api.orgNameAppNameUsersUsernameStatusGet(OrgInfo.ORG_NAME,OrgInfo.APP_NAME,TokenUtil.getAccessToken(),userName);
			}
		});
	}

	@Override
	public Object getOfflineMsgCount(final String userName) {
		return responseHandler.handle(new EasemobAPI() {
			@Override
			public Object invokeEasemobAPI() throws ApiException {
				return api.orgNameAppNameUsersOwnerUsernameOfflineMsgCountGet(OrgInfo.ORG_NAME,OrgInfo.APP_NAME,TokenUtil.getAccessToken(),userName);
			}
		});
	}

	@Override
	public Object getSpecifiedOfflineMsgStatus(final String userName,final String msgId) {
		return responseHandler.handle(new EasemobAPI() {
			@Override
			public Object invokeEasemobAPI() throws ApiException {
				return api.orgNameAppNameUsersUsernameOfflineMsgStatusMsgIdGet(OrgInfo.ORG_NAME,OrgInfo.APP_NAME,TokenUtil.getAccessToken(),userName,msgId);
			}
		});
	}

	@Override
	public Object deactivateIMUser(final String userName) {
		return responseHandler.handle(new EasemobAPI() {
			@Override
			public Object invokeEasemobAPI() throws ApiException {
				return api.orgNameAppNameUsersUsernameDeactivatePost(OrgInfo.ORG_NAME,OrgInfo.APP_NAME,TokenUtil.getAccessToken(),userName);
			}
		});
	}

	@Override
	public Object activateIMUser(final String userName) {
		return responseHandler.handle(new EasemobAPI() {
			@Override
			public Object invokeEasemobAPI() throws ApiException {
				return api.orgNameAppNameUsersUsernameActivatePost(OrgInfo.ORG_NAME,OrgInfo.APP_NAME,TokenUtil.getAccessToken(),userName);
			}
		});
	}

	@Override
	public Object disconnectIMUser(final String userName) {
		return responseHandler.handle(new EasemobAPI() {
			@Override
			public Object invokeEasemobAPI() throws ApiException {
				return api.orgNameAppNameUsersUsernameDisconnectGet(OrgInfo.ORG_NAME,OrgInfo.APP_NAME,TokenUtil.getAccessToken(),userName);
			}
		});
	}

	@Override
	public Object getIMUserAllChatGroups(final String userName) {
		return responseHandler.handle(new EasemobAPI() {
			@Override
			public Object invokeEasemobAPI() throws ApiException {
				return api.orgNameAppNameUsersUsernameJoinedChatgroupsGet(OrgInfo.ORG_NAME,OrgInfo.APP_NAME,TokenUtil.getAccessToken(),userName);
			}
		});
	}

	@Override
	public Object getIMUserAllChatRooms(final String userName) {
		return responseHandler.handle(new EasemobAPI() {
			@Override
			public Object invokeEasemobAPI() throws ApiException {
				return api.orgNameAppNameUsersUsernameJoinedChatroomsGet(OrgInfo.ORG_NAME,OrgInfo.APP_NAME,TokenUtil.getAccessToken(),userName);
			}
		});
	}
}
