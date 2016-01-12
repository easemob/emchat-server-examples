package com.easemob.server.example.api.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.easemob.server.example.api.EasemobRestAPI;
import com.easemob.server.example.api.IMUserAPI;
import com.easemob.server.example.comm.BodyWrapper;
import com.easemob.server.example.comm.HTTPMethod;
import com.easemob.server.example.comm.HeaderHelper;
import com.easemob.server.example.comm.HeaderWrapper;

public class EasemobIMUsers extends EasemobRestAPI implements IMUserAPI {

	private static final Logger log = LoggerFactory.getLogger(EasemobIMUsers.class);
	
	private static final String ROOT_URI = "/users";

	@Override
	public Object createNewIMUserSingle(Object payload) {
		String url = getContext().getSeriveURL() + getResourceRootURI();
		BodyWrapper body = (BodyWrapper) payload;
		HeaderWrapper header = HeaderHelper.getDefaultHeaderWithToken();
		
		return getInvoker().sendRequest(HTTPMethod.METHOD_POST, url, header, body);
	}

	@Override
	public Object createNewIMUserBatch(Object payload) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getIMUsersByUserName(String userName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getIMUsersBatch(Long limit, String cursor) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object deleteIMUserByUserName(String userName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object deleteIMUserBatch(Long limit) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object modifyIMUserPasswordWithAdminToken(String userName, Object payload) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object modifyIMUserNickNameWithAdminToken(String userName, Object payload) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object addFriendSingle(String userName, String friendName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object deleteFriendSingle(String userName, String friendName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getFriends(String userName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getBlackList(String userName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object addToBlackList(String userName, Object payload) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object removeFromBlackList(String userName, String blackListName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getIMUserStatus(String userName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getOfflineMsgCount(String userName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getSpecifiedOfflineMsgStatus(String userName, String msgId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object deactivateIMUser(String userName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object activateIMUser(String userName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object disconnectIMUser(String userName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getIMUserAllChatGroups(String userName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Object getIMUserAllChatRooms(String userName) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getResourceRootURI() {
		return ROOT_URI;
	}
}
