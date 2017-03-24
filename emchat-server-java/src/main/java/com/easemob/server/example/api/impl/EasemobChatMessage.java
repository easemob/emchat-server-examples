package com.easemob.server.example.api.impl;

import com.easemob.server.example.api.ChatMessageAPI;
import com.easemob.server.example.comm.ResponseHandle;
import com.easemob.server.example.comm.EasemobAPI;
import com.easemob.server.example.comm.TokenUtil;
import io.swagger.client.ApiException;
import io.swagger.client.api.ChatHistoryApi;


public class EasemobChatMessage  implements ChatMessageAPI {

    private ResponseHandle responseHandle = new ResponseHandle();
    private ChatHistoryApi api = new ChatHistoryApi();

    @Override
    public Object exportChatMessages(final Long limit,final String cursor,final String query) {
        return responseHandle.handle(new EasemobAPI() {
            @Override
            public Object easemobAPIInvoker() throws ApiException {
                return api.orgNameAppNameChatmessagesGet(TokenUtil.ORG_NAME,TokenUtil.APP_NAME,TokenUtil.getAccessToken(),query,limit+"",cursor);
            }
        });
    }
}
