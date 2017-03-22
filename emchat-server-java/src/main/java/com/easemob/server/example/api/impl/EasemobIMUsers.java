package com.easemob.server.example.api.impl;


import com.easemob.server.example.api.IMUserAPI;
import com.easemob.server.example.comm.Action;
import com.easemob.server.example.comm.DoMethod;
import com.easemob.server.example.comm.TokenUtil;
import io.swagger.client.ApiException;
import io.swagger.client.ApiResponse;
import io.swagger.client.api.UsersApi;
import io.swagger.client.model.RegisterUsers;
import io.swagger.client.model.User;


public class EasemobIMUsers  implements IMUserAPI {

	private UsersApi api = new UsersApi();
	private DoMethod doMethod = new DoMethod();
	@Override
	public Object createNewIMUserSingle(final Object payload) {
		return doMethod.doAction(new Action() {
			@Override
			public String send(String authorization) throws ApiException {
				return api.orgNameAppNameUsersPost(TokenUtil.ORG_NAME,TokenUtil.APP_NAME, (RegisterUsers) payload,authorization);
			}
		},TokenUtil.getAccessToken());
	}

	@Override
	public Object createNewIMUserBatch(Object payload) {
		return null;
	}

	@Override
	public Object getIMUserByUserName(String userName) {
		return null;
	}

	@Override
	public Object getIMUsersBatch(Long limit, String cursor) {
		return null;
	}

	@Override
	public Object deleteIMUserByUserName(String userName) {
		return null;
	}

	@Override
	public Object deleteIMUserBatch(Long limit, String cursor) {
		return null;
	}

	@Override
	public Object modifyIMUserPasswordWithAdminToken(String userName, Object payload) {
		return null;
	}

	@Override
	public Object modifyIMUserNickNameWithAdminToken(String userName, Object payload) {
		return null;
	}

	@Override
	public Object addFriendSingle(String userName, String friendName) {
		return null;
	}

	@Override
	public Object deleteFriendSingle(String userName, String friendName) {
		return null;
	}

	@Override
	public Object getFriends(String userName) {
		return null;
	}

	@Override
	public Object getBlackList(String userName) {
		return null;
	}

	@Override
	public Object addToBlackList(String userName, Object payload) {
		return null;
	}

	@Override
	public Object removeFromBlackList(String userName, String blackListName) {
		return null;
	}

	@Override
	public Object getIMUserStatus(String userName) {
		return null;
	}

	@Override
	public Object getOfflineMsgCount(String userName) {
		return null;
	}

	@Override
	public Object getSpecifiedOfflineMsgStatus(String userName, String msgId) {
		return null;
	}

	@Override
	public Object deactivateIMUser(String userName) {
		return null;
	}

	@Override
	public Object activateIMUser(String userName) {
		return null;
	}

	@Override
	public Object disconnectIMUser(String userName) {
		return null;
	}

	@Override
	public Object getIMUserAllChatGroups(String userName) {
		return null;
	}

	@Override
	public Object getIMUserAllChatRooms(String userName) {
		return null;
	}
}
