package com.easemob.server.example.api.impl;


import com.easemob.server.example.api.IMUserAPI;
import com.easemob.server.example.comm.EasemobAPI;
import com.easemob.server.example.comm.ResponseHandle;
import com.easemob.server.example.comm.TokenUtil;
import io.swagger.client.ApiException;
import io.swagger.client.api.UsersApi;
import io.swagger.client.model.NewPassword;
import io.swagger.client.model.Nickname;
import io.swagger.client.model.RegisterUsers;
import io.swagger.client.model.UserNames;


public class EasemobIMUsers  implements IMUserAPI {

	private UsersApi api = new UsersApi();
	private ResponseHandle responseHandle = new ResponseHandle();
	@Override
	public Object createNewIMUserSingle(final Object payload) {
		return responseHandle.handle(new EasemobAPI() {
			@Override
			public Object easemobAPIInvoker() throws ApiException {
				return api.orgNameAppNameUsersPost(TokenUtil.ORG_NAME,TokenUtil.APP_NAME, (RegisterUsers) payload,TokenUtil.getAccessToken());
			}
		});
	}

	@Override
	public Object createNewIMUserBatch(final Object payload) {
		return responseHandle.handle(new EasemobAPI() {
			@Override
			public Object easemobAPIInvoker() throws ApiException {
				return api.orgNameAppNameUsersPost(TokenUtil.ORG_NAME,TokenUtil.APP_NAME, (RegisterUsers) payload,TokenUtil.getAccessToken());
			}
		});
	}

	@Override
	public Object getIMUserByUserName(final String userName) {
		return responseHandle.handle(new EasemobAPI() {
			@Override
			public Object easemobAPIInvoker() throws ApiException {
				return api.orgNameAppNameUsersUsernameGet(TokenUtil.ORG_NAME,TokenUtil.APP_NAME,TokenUtil.getAccessToken(),userName);
		}
		});
	}

	@Override
	public Object getIMUsersBatch(final Long limit,final String cursor) {
		return responseHandle.handle(new EasemobAPI() {
			@Override
			public Object easemobAPIInvoker() throws ApiException {
				return api.orgNameAppNameUsersGet(TokenUtil.ORG_NAME,TokenUtil.APP_NAME,TokenUtil.getAccessToken(),limit+"",cursor);
			}
		});
	}

	@Override
	public Object deleteIMUserByUserName(final String userName) {
		return responseHandle.handle(new EasemobAPI() {
			@Override
			public Object easemobAPIInvoker() throws ApiException {
				return api.orgNameAppNameUsersUsernameDelete(TokenUtil.ORG_NAME,TokenUtil.APP_NAME,TokenUtil.getAccessToken(),userName);
			}
		});
	}

	@Override
	public Object deleteIMUserBatch(final Long limit,final String cursor) {
		return responseHandle.handle(new EasemobAPI() {
			@Override
			public Object easemobAPIInvoker() throws ApiException {
				return api.orgNameAppNameUsersDelete(TokenUtil.ORG_NAME,TokenUtil.APP_NAME,TokenUtil.getAccessToken(),limit+"",cursor);
			}
		});
	}

	@Override
	public Object modifyIMUserPasswordWithAdminToken(final String userName, final Object payload) {
		return responseHandle.handle(new EasemobAPI() {
			@Override
			public Object easemobAPIInvoker() throws ApiException {
				return api.orgNameAppNameUsersUsernamePasswordPut(TokenUtil.ORG_NAME,TokenUtil.APP_NAME,userName, (NewPassword) payload,TokenUtil.getAccessToken());
			}
		});
	}

	@Override
	public Object modifyIMUserNickNameWithAdminToken(final String userName,final Object payload) {
		return responseHandle.handle(new EasemobAPI() {
			@Override
			public Object easemobAPIInvoker() throws ApiException {
				return api.orgNameAppNameUsersUsernamePut(TokenUtil.ORG_NAME,TokenUtil.APP_NAME,userName, (Nickname) payload,TokenUtil.getAccessToken());
			}
		});
	}

	@Override
	public Object addFriendSingle(final String userName,final String friendName) {
		return responseHandle.handle(new EasemobAPI() {
			@Override
			public Object easemobAPIInvoker() throws ApiException {
				return api.orgNameAppNameUsersOwnerUsernameContactsUsersFriendUsernamePost(TokenUtil.ORG_NAME,TokenUtil.APP_NAME,TokenUtil.getAccessToken(),userName,friendName);
			}
		});
	}

	@Override
	public Object deleteFriendSingle(final String userName,final String friendName) {
		return responseHandle.handle(new EasemobAPI() {
			@Override
			public Object easemobAPIInvoker() throws ApiException {
				return api.orgNameAppNameUsersOwnerUsernameContactsUsersFriendUsernameDelete(TokenUtil.ORG_NAME,TokenUtil.APP_NAME,TokenUtil.getAccessToken(),userName,friendName);
			}
		});
	}

	@Override
	public Object getFriends(final String userName) {
		return responseHandle.handle(new EasemobAPI() {
			@Override
			public Object easemobAPIInvoker() throws ApiException {
				return api.orgNameAppNameUsersOwnerUsernameContactsUsersGet(TokenUtil.ORG_NAME,TokenUtil.APP_NAME,TokenUtil.getAccessToken(),userName);
			}
		});
	}

	@Override
	public Object getBlackList(final String userName) {
		return responseHandle.handle(new EasemobAPI() {
			@Override
			public Object easemobAPIInvoker() throws ApiException {
				return api.orgNameAppNameUsersOwnerUsernameBlocksUsersGet(TokenUtil.ORG_NAME,TokenUtil.APP_NAME,TokenUtil.getAccessToken(),userName);
			}
		});
	}

	@Override
	public Object addToBlackList(final String userName,final Object payload) {
		return responseHandle.handle(new EasemobAPI() {
			@Override
			public Object easemobAPIInvoker() throws ApiException {
				return api.orgNameAppNameUsersOwnerUsernameBlocksUsersPost(TokenUtil.ORG_NAME,TokenUtil.APP_NAME,TokenUtil.getAccessToken(),userName, (UserNames) payload);
			}
		});
	}

	@Override
	public Object removeFromBlackList(final String userName,final String blackListName) {
		return responseHandle.handle(new EasemobAPI() {
			@Override
			public Object easemobAPIInvoker() throws ApiException {
				return api.orgNameAppNameUsersOwnerUsernameBlocksUsersBlockedUsernameDelete(TokenUtil.ORG_NAME,TokenUtil.APP_NAME,TokenUtil.getAccessToken(),userName,blackListName);
			}
		});
	}

	@Override
	public Object getIMUserStatus(final String userName) {
		return responseHandle.handle(new EasemobAPI() {
			@Override
			public Object easemobAPIInvoker() throws ApiException {
				return api.orgNameAppNameUsersUsernameStatusGet(TokenUtil.ORG_NAME,TokenUtil.APP_NAME,TokenUtil.getAccessToken(),userName);
			}
		});
	}

	@Override
	public Object getOfflineMsgCount(final String userName) {
		return responseHandle.handle(new EasemobAPI() {
			@Override
			public Object easemobAPIInvoker() throws ApiException {
				return api.orgNameAppNameUsersOwnerUsernameOfflineMsgCountGet(TokenUtil.ORG_NAME,TokenUtil.APP_NAME,TokenUtil.getAccessToken(),userName);
			}
		});
	}

	@Override
	public Object getSpecifiedOfflineMsgStatus(final String userName,final String msgId) {
		return responseHandle.handle(new EasemobAPI() {
			@Override
			public Object easemobAPIInvoker() throws ApiException {
				return api.orgNameAppNameUsersUsernameOfflineMsgStatusMsgIdGet(TokenUtil.ORG_NAME,TokenUtil.APP_NAME,TokenUtil.getAccessToken(),userName,msgId);
			}
		});
	}

	@Override
	public Object deactivateIMUser(final String userName) {
		return responseHandle.handle(new EasemobAPI() {
			@Override
			public Object easemobAPIInvoker() throws ApiException {
				return api.orgNameAppNameUsersUsernameDeactivatePost(TokenUtil.ORG_NAME,TokenUtil.APP_NAME,TokenUtil.getAccessToken(),userName);
			}
		});
	}

	@Override
	public Object activateIMUser(final String userName) {
		return responseHandle.handle(new EasemobAPI() {
			@Override
			public Object easemobAPIInvoker() throws ApiException {
				return api.orgNameAppNameUsersUsernameActivatePost(TokenUtil.ORG_NAME,TokenUtil.APP_NAME,TokenUtil.getAccessToken(),userName);
			}
		});
	}

	@Override
	public Object disconnectIMUser(final String userName) {
		return responseHandle.handle(new EasemobAPI() {
			@Override
			public Object easemobAPIInvoker() throws ApiException {
				return api.orgNameAppNameUsersUsernameDisconnectGet(TokenUtil.ORG_NAME,TokenUtil.APP_NAME,TokenUtil.getAccessToken(),userName);
			}
		});
	}

	@Override
	public Object getIMUserAllChatGroups(final String userName) {
		return responseHandle.handle(new EasemobAPI() {
			@Override
			public Object easemobAPIInvoker() throws ApiException {
				return api.orgNameAppNameUsersUsernameJoinedChatgroupsGet(TokenUtil.ORG_NAME,TokenUtil.APP_NAME,TokenUtil.getAccessToken(),userName);
			}
		});
	}

	@Override
	public Object getIMUserAllChatRooms(final String userName) {
		return responseHandle.handle(new EasemobAPI() {
			@Override
			public Object easemobAPIInvoker() throws ApiException {
				return api.orgNameAppNameUsersUsernameJoinedChatroomsGet(TokenUtil.ORG_NAME,TokenUtil.APP_NAME,TokenUtil.getAccessToken(),userName);
			}
		});
	}
}
