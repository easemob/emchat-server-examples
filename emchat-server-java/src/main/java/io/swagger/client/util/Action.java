package io.swagger.client.util;

import io.swagger.client.ApiException;
import io.swagger.client.ApiResponse;

/**
 * Created by easemob on 2017/3/16.
 */
public interface Action  {
    <M>ApiResponse<M> send(String authorization) throws ApiException;
}
