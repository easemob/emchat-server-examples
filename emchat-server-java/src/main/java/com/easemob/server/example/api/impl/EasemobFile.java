package com.easemob.server.example.api.impl;

import com.easemob.server.example.api.FileAPI;
import com.easemob.server.example.comm.Action;
import com.easemob.server.example.comm.DoMethod;
import com.easemob.server.example.comm.TokenUtil;
import io.swagger.client.ApiException;
import io.swagger.client.api.UploadAndDownloadFilesApi;
import java.io.File;


public class EasemobFile implements FileAPI {
    private DoMethod doMethod = new DoMethod();
    private UploadAndDownloadFilesApi api = new UploadAndDownloadFilesApi();
    @Override
    public Object uploadFile(final Object file) {
        return doMethod.doAction(new Action() {
            @Override
            public String send(String authorization) throws ApiException {
                return api.orgNameAppNameChatfilesPost(TokenUtil.ORG_NAME,TokenUtil.APP_NAME,authorization,(File)file,true);
        }},TokenUtil.getAccessToken());
    }

    @Override
    public Object downloadFile(String fileUUID, String shareSecret, Boolean isThumbnail) {
        return null;
    }
}
