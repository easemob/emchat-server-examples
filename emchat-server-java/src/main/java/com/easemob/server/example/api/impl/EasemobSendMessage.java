package com.easemob.server.example.api.impl;

import com.easemob.server.example.api.SendMessageAPI;
import com.easemob.server.example.comm.DoMethod;
import com.easemob.server.example.comm.MyHttpRequest;
import com.easemob.server.example.comm.TokenUtil;
import io.swagger.client.ApiException;
import io.swagger.client.api.MessagesApi;
import io.swagger.client.model.Msg;

public class EasemobSendMessage implements SendMessageAPI {
    private DoMethod doMethod = new DoMethod();
    private MessagesApi api = new MessagesApi();
    @Override
    public Object sendMessage(final Object payload) {
        return doMethod.sendHttpRequest(new MyHttpRequest() {
            @Override
            public Object doHttpRequest(String authorization) throws ApiException {
                return api.orgNameAppNameMessagesPost(TokenUtil.ORG_NAME,TokenUtil.APP_NAME,authorization, (Msg) payload);
            }
        }, TokenUtil.getAccessToken());
    }
}
