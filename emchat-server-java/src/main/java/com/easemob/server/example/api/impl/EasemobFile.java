package com.easemob.server.example.api.impl;

import com.easemob.server.example.api.FileAPI;
import com.easemob.server.example.comm.MyHttpRequest;
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
        return doMethod.sendHttpRequest(new MyHttpRequest() {
            @Override
            public Object doHttpRequest(String authorization) throws ApiException {
                return api.orgNameAppNameChatfilesPost(TokenUtil.ORG_NAME,TokenUtil.APP_NAME,authorization,(File)file,true);
             }
        },TokenUtil.getAccessToken());
    }

    @Override
    public Object downloadFile(final String fileUUID,final  String shareSecret,final Boolean isThumbnail) {
        return doMethod.sendHttpRequest(new MyHttpRequest() {
            @Override
            public Object doHttpRequest(String authorization) throws ApiException {
               return api.orgNameAppNameChatfilesUuidGet(TokenUtil.ORG_NAME,TokenUtil.APP_NAME,authorization,fileUUID,shareSecret,isThumbnail);
            }
        },TokenUtil.getAccessToken());
    }
}
