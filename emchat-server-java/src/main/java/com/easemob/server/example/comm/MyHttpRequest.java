package com.easemob.server.example.comm;

import io.swagger.client.ApiException;

/**
 * Created by easemob on 2017/3/16.
 */
public interface MyHttpRequest {
    Object doHttpRequest(String authorization) throws ApiException;
}
