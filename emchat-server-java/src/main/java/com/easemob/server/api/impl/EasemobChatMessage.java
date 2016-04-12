package com.easemob.server.api.impl;

import com.easemob.server.api.EasemobRestAPI;
import com.easemob.server.comm.constant.HTTPMethod;
import com.easemob.server.comm.helper.HeaderHelper;
import com.easemob.server.comm.wrapper.HeaderWrapper;
import com.easemob.server.comm.wrapper.QueryWrapper;
import com.easemob.server.api.ChatMessageAPI;

public class EasemobChatMessage extends EasemobRestAPI implements ChatMessageAPI {

    private static final String ROOT_URI = "chatmessages";

    public Object exportChatMessages(Long limit, String cursor, String query) {
        String url = getContext().getSeriveURL() + getResourceRootURI();
        HeaderWrapper header = HeaderHelper.getDefaultHeaderWithToken();
        QueryWrapper queryWrapper = QueryWrapper.newInstance().addLimit(limit).addCursor(cursor).addQueryLang(query);

        return getInvoker().sendRequest(HTTPMethod.METHOD_DELETE, url, header, null, queryWrapper);
    }

    @Override
    public String getResourceRootURI() {
        return ROOT_URI;
    }
}
