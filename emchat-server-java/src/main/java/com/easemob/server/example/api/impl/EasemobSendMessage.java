package com.easemob.server.example.api.impl;

import com.easemob.server.example.api.EasemobRestAPI;
import com.easemob.server.example.api.SendMessageAPI;
import com.easemob.server.example.comm.BodyWrapper;
import com.easemob.server.example.comm.HTTPMethod;
import com.easemob.server.example.comm.HeaderHelper;
import com.easemob.server.example.comm.HeaderWrapper;

public class EasemobSendMessage extends EasemobRestAPI implements SendMessageAPI {
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
