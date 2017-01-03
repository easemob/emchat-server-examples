package com.hyphenate.server.example.api.impl;

import com.hyphenate.server.example.api.ChatGroupAPI;
import com.hyphenate.server.example.api.HyphenateRestAPI;
import com.hyphenate.server.example.comm.constant.HTTPMethod;
import com.hyphenate.server.example.comm.helper.HeaderHelper;
import com.hyphenate.server.example.comm.wrapper.BodyWrapper;
import com.hyphenate.server.example.comm.wrapper.HeaderWrapper;
import com.hyphenate.server.example.comm.wrapper.QueryWrapper;
import org.apache.commons.lang3.StringUtils;

public class HyphenateChatGroup extends HyphenateRestAPI implements ChatGroupAPI {
    private static final String ROOT_URI = "/chatgroups";

    public Object getChatGroups(Long limit, String cursor) {
        String url = getContext().getSeriveURL() + getResourceRootURI();
        HeaderWrapper header = HeaderHelper.getDefaultHeaderWithToken();
        QueryWrapper query = QueryWrapper.newInstance().addLimit(limit).addCursor(cursor);

        return getInvoker().sendRequest(HTTPMethod.METHOD_GET, url, header, null, query);
    }

    public Object getChatGroupDetails(String[] groupIds) {
        String url = getContext().getSeriveURL() + getResourceRootURI() + "/" + StringUtils.join(groupIds, ",");
        HeaderWrapper header = HeaderHelper.getDefaultHeaderWithToken();

        return getInvoker().sendRequest(HTTPMethod.METHOD_GET, url, header, null, null);
    }

    public Object createChatGroup(Object payload) {
        String url = getContext().getSeriveURL() + getResourceRootURI();
        HeaderWrapper header = HeaderHelper.getDefaultHeaderWithToken();
        BodyWrapper body = (BodyWrapper) payload;

        return getInvoker().sendRequest(HTTPMethod.METHOD_POST, url, header, body, null);
    }

    public Object modifyChatGroup(String groupId, Object payload) {
        String url = getContext().getSeriveURL() + getResourceRootURI() + "/" + groupId;
        HeaderWrapper header = HeaderHelper.getDefaultHeaderWithToken();
        BodyWrapper body = (BodyWrapper) payload;

        return getInvoker().sendRequest(HTTPMethod.METHOD_PUT, url, header, body, null);
    }

    public Object deleteChatGroup(String groupId) {
        String url = getContext().getSeriveURL() + getResourceRootURI() + "/" + groupId;
        HeaderWrapper header = HeaderHelper.getDefaultHeaderWithToken();

        return getInvoker().sendRequest(HTTPMethod.METHOD_DELETE, url, header, null, null);
    }

    public Object getChatGroupUsers(String groupId) {
        String url = getContext().getSeriveURL() + getResourceRootURI() + "/" + groupId + "/users";
        HeaderWrapper header = HeaderHelper.getDefaultHeaderWithToken();

        return getInvoker().sendRequest(HTTPMethod.METHOD_GET, url, header, null, null);
    }

    public Object addSingleUserToChatGroup(String groupId, String userId) {
        String url = getContext().getSeriveURL() + getResourceRootURI() + "/" + groupId + "/users/" + userId;
        HeaderWrapper header = HeaderHelper.getDefaultHeaderWithToken();

        return getInvoker().sendRequest(HTTPMethod.METHOD_POST, url, header, null, null);
    }

    public Object addBatchUsersToChatGroup(String groupId, Object payload) {
        String url = getContext().getSeriveURL() + getResourceRootURI() + "/" + groupId + "/users";
        HeaderWrapper header = HeaderHelper.getDefaultHeaderWithToken();
        BodyWrapper body = (BodyWrapper) payload;

        return getInvoker().sendRequest(HTTPMethod.METHOD_POST, url, header, body, null);
    }

    public Object removeSingleUserFromChatGroup(String groupId, String userId) {
        String url = getContext().getSeriveURL() + getResourceRootURI() + "/" + groupId + "/users/" + userId;
        HeaderWrapper header = HeaderHelper.getDefaultHeaderWithToken();

        return getInvoker().sendRequest(HTTPMethod.METHOD_DELETE, url, header, null, null);
    }

    public Object removeBatchUsersFromChatGroup(String groupId, String[] userIds) {
        String url = getContext().getSeriveURL() + getResourceRootURI() + "/" + groupId + "/users/" + StringUtils.join(userIds, ",");
        HeaderWrapper header = HeaderHelper.getDefaultHeaderWithToken();

        return getInvoker().sendRequest(HTTPMethod.METHOD_DELETE, url, header, null, null);
    }

    public Object transferChatGroupOwner(String groupId, Object payload) {
        String url = getContext().getSeriveURL() + getResourceRootURI() + "/" + groupId;
        HeaderWrapper header = HeaderHelper.getDefaultHeaderWithToken();
        BodyWrapper body = (BodyWrapper) payload;

        return getInvoker().sendRequest(HTTPMethod.METHOD_PUT, url, header, body, null);
    }

    public Object getChatGroupBlockUsers(String groupId) {
        String url = getContext().getSeriveURL() + getResourceRootURI() + "/" + groupId + "/blocks/users";
        HeaderWrapper header = HeaderHelper.getDefaultHeaderWithToken();

        return getInvoker().sendRequest(HTTPMethod.METHOD_GET, url, header, null, null);
    }

    public Object addSingleBlockUserToChatGroup(String groupId, String userId) {
        String url = getContext().getSeriveURL() + getResourceRootURI() + "/" + groupId + "/blocks/users/" + userId;
        HeaderWrapper header = HeaderHelper.getDefaultHeaderWithToken();

        return getInvoker().sendRequest(HTTPMethod.METHOD_POST, url, header, null, null);
    }

    public Object addBatchBlockUsersToChatGroup(String groupId, Object payload) {
        String url = getContext().getSeriveURL() + getResourceRootURI() + "/" + groupId + "/blocks/users";
        HeaderWrapper header = HeaderHelper.getDefaultHeaderWithToken();
        BodyWrapper body = (BodyWrapper) payload;

        return getInvoker().sendRequest(HTTPMethod.METHOD_POST, url, header, body, null);
    }

    public Object removeSingleBlockUserFromChatGroup(String groupId, String userId) {
        String url = getContext().getSeriveURL() + getResourceRootURI() + "/" + groupId + "/blocks/users/" + userId;
        HeaderWrapper header = HeaderHelper.getDefaultHeaderWithToken();

        return getInvoker().sendRequest(HTTPMethod.METHOD_DELETE, url, header, null, null);
    }

    public Object removeBatchBlockUsersFromChatGroup(String groupId, String[] userIds) {
        String url = getContext().getSeriveURL() + getResourceRootURI() + "/" + groupId + "/blocks/users/" + StringUtils.join(userIds, ",");
        HeaderWrapper header = HeaderHelper.getDefaultHeaderWithToken();

        return getInvoker().sendRequest(HTTPMethod.METHOD_DELETE, url, header, null, null);
    }

    @Override
    public String getResourceRootURI() {
        return ROOT_URI;
    }
}
