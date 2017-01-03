package com.hyphenate.server.example.api.impl;

import com.hyphenate.server.example.api.ChatMessageAPI;
import com.hyphenate.server.example.api.HyphenateRestAPI;
import com.hyphenate.server.example.comm.constant.HTTPMethod;
import com.hyphenate.server.example.comm.helper.HeaderHelper;
import com.hyphenate.server.example.comm.wrapper.HeaderWrapper;
import com.hyphenate.server.example.comm.wrapper.QueryWrapper;

public class HyphenateChatMessage extends HyphenateRestAPI implements ChatMessageAPI {

    private static final String ROOT_URI = "/chatmessages";

    public Object exportChatMessages(Long limit, String cursor, String query) {
        String url = getContext().getSeriveURL() + getResourceRootURI();
        HeaderWrapper header = HeaderHelper.getDefaultHeaderWithToken();
        QueryWrapper queryWrapper = QueryWrapper.newInstance().addLimit(limit).addCursor(cursor).addQueryLang(query);

        return getInvoker().sendRequest(HTTPMethod.METHOD_GET, url, header, null, queryWrapper);
    }

    @Override
    public String getResourceRootURI() {
        return ROOT_URI;
    }
}
