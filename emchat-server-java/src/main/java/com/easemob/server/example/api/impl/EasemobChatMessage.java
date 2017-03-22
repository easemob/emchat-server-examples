package com.easemob.server.example.api.impl;

import com.easemob.server.example.api.ChatMessageAPI;
import com.easemob.server.example.comm.DoMethod;
import com.easemob.server.example.comm.MyHttpRequest;
import com.easemob.server.example.comm.TokenUtil;
import io.swagger.client.ApiException;
import io.swagger.client.api.ChatHistoryApi;


public class EasemobChatMessage  implements ChatMessageAPI {

    private DoMethod doMethod = new DoMethod();
    private ChatHistoryApi api = new ChatHistoryApi();

    @Override
    public Object exportChatMessages(final Long limit,final String cursor,final String query) {
        return doMethod.sendHttpRequest(new MyHttpRequest() {
            @Override
            public Object doHttpRequest(String authorization) throws ApiException {
                return api.orgNameAppNameChatmessagesGet(TokenUtil.ORG_NAME,TokenUtil.APP_NAME,authorization,query,limit+"",cursor);
            }
        }, TokenUtil.getAccessToken());
    }
}
