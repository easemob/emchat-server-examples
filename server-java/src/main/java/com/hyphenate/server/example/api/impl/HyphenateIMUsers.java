package com.hyphenate.server.example.api.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.hyphenate.server.example.api.HyphenateRestAPI;
import com.hyphenate.server.example.api.IMUserAPI;
import com.hyphenate.server.example.comm.wrapper.BodyWrapper;
import com.hyphenate.server.example.comm.constant.HTTPMethod;
import com.hyphenate.server.example.comm.helper.HeaderHelper;
import com.hyphenate.server.example.comm.wrapper.HeaderWrapper;
import com.hyphenate.server.example.comm.wrapper.QueryWrapper;

public class HyphenateIMUsers extends HyphenateRestAPI implements IMUserAPI {

	private static final Logger log = LoggerFactory.getLogger(HyphenateIMUsers.class);
	
	private static final String ROOT_URI = "/users";

	public Object createUser(Object payload) {
		String url = getContext().getSeriveURL() + getResourceRootURI();
		BodyWrapper body = (BodyWrapper) payload;
		HeaderWrapper header = HeaderHelper.getDefaultHeaderWithToken();
		
		return getInvoker().sendRequest(HTTPMethod.METHOD_POST, url, header, body, null);
	}

	public Object createUsers(Object payload) {
		String url = getContext().getSeriveURL() + getResourceRootURI();
		BodyWrapper body = (BodyWrapper) payload;
		HeaderWrapper header = HeaderHelper.getDefaultHeaderWithToken();
		
		return getInvoker().sendRequest(HTTPMethod.METHOD_POST, url, header, body, null);
	}

	public Object getUsersByUsername(String username) {
		String url = getContext().getSeriveURL() + getResourceRootURI() + "/" + username;
		HeaderWrapper header = HeaderHelper.getDefaultHeaderWithToken();
		
		return getInvoker().sendRequest(HTTPMethod.METHOD_GET, url, header, null, null);
	}

	public Object getUsersWithPagination(Long limit, String cursor) {
		String url = getContext().getSeriveURL() + getResourceRootURI();
		HeaderWrapper header = HeaderHelper.getDefaultHeaderWithToken();
		QueryWrapper query = QueryWrapper.newInstance().addLimit(limit).addCursor(cursor);
		
		return getInvoker().sendRequest(HTTPMethod.METHOD_GET, url, header, null, query);
	}

	public Object deleteUserByUsername(String username) {
		String url = getContext().getSeriveURL() + getResourceRootURI() + "/" + username;
        HeaderWrapper header = HeaderHelper.getDefaultHeaderWithToken();

		return getInvoker().sendRequest(HTTPMethod.METHOD_DELETE, url, header, null, null);
	}

	public Object deleteUsers(Long limit, String cursor) {
		String url = getContext().getSeriveURL() + getResourceRootURI();
        HeaderWrapper header = HeaderHelper.getDefaultHeaderWithToken();
        QueryWrapper query = QueryWrapper.newInstance().addLimit(limit).addCursor(cursor);

        return getInvoker().sendRequest(HTTPMethod.METHOD_DELETE, url, header, null, query);
	}

	public Object updateUserPassword(String username, Object payload) {
        String url = getContext().getSeriveURL() + getResourceRootURI() + "/" + username + "/password";
        HeaderWrapper header = HeaderHelper.getDefaultHeaderWithToken();
        BodyWrapper body = (BodyWrapper) payload;

        return getInvoker().sendRequest(HTTPMethod.METHOD_PUT, url, header, body, null);
	}

	public Object updateUserNickName(String username, Object payload) {
        String url = getContext().getSeriveURL() + getResourceRootURI() + "/" + username;
        HeaderWrapper header = HeaderHelper.getDefaultHeaderWithToken();
        BodyWrapper body = (BodyWrapper) payload;

        return getInvoker().sendRequest(HTTPMethod.METHOD_PUT, url, header, body, null);
	}

	public Object addContact(String username, String friendName) {
        String url = getContext().getSeriveURL() + getResourceRootURI() + "/" + username + "/contacts/users/" + friendName;
        HeaderWrapper header = HeaderHelper.getDefaultHeaderWithToken();

        return getInvoker().sendRequest(HTTPMethod.METHOD_POST, url, header, null, null);
	}

	public Object deleteContact(String username, String friendName) {
        String url = getContext().getSeriveURL() + getResourceRootURI() + "/" + username + "/contacts/users/" + friendName;
        HeaderWrapper header = HeaderHelper.getDefaultHeaderWithToken();

        return getInvoker().sendRequest(HTTPMethod.METHOD_DELETE, url, header, null, null);
	}

	public Object getContacts(String username) {
        String url = getContext().getSeriveURL() + getResourceRootURI() + "/" + username + "/contacts/users";
        HeaderWrapper header = HeaderHelper.getDefaultHeaderWithToken();

        return getInvoker().sendRequest(HTTPMethod.METHOD_GET, url, header, null, null);
	}

	public Object getBlacklist(String username) {
        String url = getContext().getSeriveURL() + getResourceRootURI() + "/" + username + "/blocks/users";
        HeaderWrapper header = HeaderHelper.getDefaultHeaderWithToken();

        return getInvoker().sendRequest(HTTPMethod.METHOD_GET, url, header, null, null);
	}

	public Object blockUsers(String username, Object payload) {
        String url = getContext().getSeriveURL() + getResourceRootURI() + "/" + username + "/blocks/users";
        HeaderWrapper header = HeaderHelper.getDefaultHeaderWithToken();
        BodyWrapper body = (BodyWrapper) payload;

        return getInvoker().sendRequest(HTTPMethod.METHOD_POST, url, header, body, null);
	}

	public Object unblockUsers(String username, String blacklistID) {
        String url = getContext().getSeriveURL() + getResourceRootURI() + "/" + username + "/blocks/users/" + blacklistID;
        HeaderWrapper header = HeaderHelper.getDefaultHeaderWithToken();

        return getInvoker().sendRequest(HTTPMethod.METHOD_DELETE, url, header, null, null);
	}

	public Object getUserStatus(String username) {
        String url = getContext().getSeriveURL() + getResourceRootURI() + "/" + username + "/status";
        HeaderWrapper header = HeaderHelper.getDefaultHeaderWithToken();

        return getInvoker().sendRequest(HTTPMethod.METHOD_GET, url, header, null, null);
	}

	public Object getUndeliveredMessageCountOfUser(String username) {
        String url = getContext().getSeriveURL() + getResourceRootURI() + "/" + username + "/offline_msg_count";
        HeaderWrapper header = HeaderHelper.getDefaultHeaderWithToken();

        return getInvoker().sendRequest(HTTPMethod.METHOD_GET, url, header, null, null);
	}

	public Object getUndeliveredMessageStatusOfUser(String username, String msgId) {
        String url = getContext().getSeriveURL() + getResourceRootURI() + "/" + username + "/offline_msg_status/" + msgId;
        HeaderWrapper header = HeaderHelper.getDefaultHeaderWithToken();

        return getInvoker().sendRequest(HTTPMethod.METHOD_GET, url, header, null, null);
	}

	public Object deactivateUser(String username) {
        String url = getContext().getSeriveURL() + getResourceRootURI() + "/" + username + "/deactivate";
        HeaderWrapper header = HeaderHelper.getDefaultHeaderWithToken();

        return getInvoker().sendRequest(HTTPMethod.METHOD_POST, url, header, null, null);
	}

	public Object activateUser(String username) {
        String url = getContext().getSeriveURL() + getResourceRootURI() + "/" + username + "/activate";
        HeaderWrapper header = HeaderHelper.getDefaultHeaderWithToken();

        return getInvoker().sendRequest(HTTPMethod.METHOD_POST, url, header, null, null);
	}

	public Object logoutUser(String username) {
        String url = getContext().getSeriveURL() + getResourceRootURI() + "/" + username + "/disconnect";
        HeaderWrapper header = HeaderHelper.getDefaultHeaderWithToken();

        return getInvoker().sendRequest(HTTPMethod.METHOD_GET, url, header, null, null);
	}

	public Object getGroupsOfUserJoined(String username) {
        String url = getContext().getSeriveURL() + getResourceRootURI() + "/" + username + "/joined_chatgroups";
        HeaderWrapper header = HeaderHelper.getDefaultHeaderWithToken();

        return getInvoker().sendRequest(HTTPMethod.METHOD_GET, url, header, null, null);
	}

	public Object getChatRoomsOfUserJoined(String username) {
        String url = getContext().getSeriveURL() + getResourceRootURI() + "/" + username + "/joined_chatrooms";
        HeaderWrapper header = HeaderHelper.getDefaultHeaderWithToken();

        return getInvoker().sendRequest(HTTPMethod.METHOD_GET, url, header, null, null);
	}

	@Override
	public String getResourceRootURI() {
		return ROOT_URI;
	}
}
