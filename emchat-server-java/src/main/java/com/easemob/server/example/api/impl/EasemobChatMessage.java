package com.easemob.server.example.api.impl;

import com.easemob.server.example.api.ChatMessageAPI;
import com.easemob.server.example.api.EasemobRestAPI;
import com.easemob.server.example.comm.HTTPMethod;
import com.easemob.server.example.comm.HeaderHelper;
import com.easemob.server.example.comm.HeaderWrapper;
import com.easemob.server.example.comm.QueryWrapper;

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
