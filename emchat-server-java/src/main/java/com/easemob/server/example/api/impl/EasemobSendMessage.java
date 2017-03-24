package com.easemob.server.example.api.impl;

import com.easemob.server.example.api.SendMessageAPI;
import com.easemob.server.example.comm.ResponseHandle;
import com.easemob.server.example.comm.EasemobAPI;
import com.easemob.server.example.comm.TokenUtil;
import io.swagger.client.ApiException;
import io.swagger.client.api.MessagesApi;
import io.swagger.client.model.Msg;

public class EasemobSendMessage implements SendMessageAPI {
    private ResponseHandle responseHandle = new ResponseHandle();
    private MessagesApi api = new MessagesApi();
    @Override
    public Object sendMessage(final Object payload) {
        return responseHandle.handle(new EasemobAPI() {
            @Override
            public Object easemobAPIInvoker() throws ApiException {
                return api.orgNameAppNameMessagesPost(TokenUtil.ORG_NAME,TokenUtil.APP_NAME,TokenUtil.getAccessToken(), (Msg) payload);
            }
        });
    }
}
