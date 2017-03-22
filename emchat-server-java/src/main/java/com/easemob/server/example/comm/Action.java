package com.easemob.server.example.comm;

import io.swagger.client.ApiException;
import io.swagger.client.ApiResponse;

/**
 * Created by easemob on 2017/3/16.
 */
public interface Action  {
    String doHttpRequest(String authorization) throws ApiException;
}
