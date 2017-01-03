package com.hyphenate.server.example.api.impl;

import com.hyphenate.server.example.api.HyphenateRestAPI;
import com.hyphenate.server.example.api.SendMessageAPI;
import com.hyphenate.server.example.comm.wrapper.BodyWrapper;
import com.hyphenate.server.example.comm.constant.HTTPMethod;
import com.hyphenate.server.example.comm.helper.HeaderHelper;
import com.hyphenate.server.example.comm.wrapper.HeaderWrapper;

public class HyphenateSendMessage extends HyphenateRestAPI implements SendMessageAPI {
    private static final String ROOT_URI = "/messages";

    @Override
    public String getResourceRootURI() {
        return ROOT_URI;
    }

    public Object sendMessage(Object payload) {
        String  url = getContext().getSeriveURL() + getResourceRootURI();
        HeaderWrapper header = HeaderHelper.getDefaultHeaderWithToken();
        BodyWrapper body = (BodyWrapper) payload;

        return getInvoker().sendRequest(HTTPMethod.METHOD_POST, url, header, body, null);
    }
}
