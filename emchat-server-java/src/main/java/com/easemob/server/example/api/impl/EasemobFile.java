package com.easemob.server.example.api.impl;

import com.easemob.server.example.api.EasemobRestAPI;
import com.easemob.server.example.api.FileAPI;
import com.easemob.server.example.comm.HeaderHelper;
import com.easemob.server.example.comm.HeaderWrapper;

import java.io.File;

public class EasemobFile extends EasemobRestAPI implements FileAPI {
    private static final String ROOT_URI = "/chatfiles";

    @Override
    public String getResourceRootURI() {
        return ROOT_URI;
    }

    public Object uploadFile(Object file) {
        String url = getContext().getSeriveURL() + getResourceRootURI();
        HeaderWrapper header = HeaderHelper.getUploadHeaderWithToken();

        return getInvoker().uploadFile(url, header, (File) file);
    }

    public Object downloadFile(String fileUUID, String shareSecret, String localPath, Boolean isThumbnail) {
        String url = getContext().getSeriveURL() + getResourceRootURI();
        HeaderWrapper header = HeaderHelper.getDownloadHeaderWithToken(shareSecret, isThumbnail);

        return getInvoker().downloadFile(url, header, null);
    }
}
