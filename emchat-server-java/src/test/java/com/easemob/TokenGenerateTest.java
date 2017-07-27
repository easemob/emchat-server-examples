package com.easemob;

import com.easemob.server.example.api.impl.EasemobAuthToken;
import io.swagger.client.ApiException;
import org.junit.Assert;
import org.junit.Test;

/**
 * Created by easemob on 2017/3/21.
 */
public class TokenGenerateTest {
    private EasemobAuthToken easemobAuthToken = new EasemobAuthToken();

    @Test
    public void testTokenGet() throws ApiException {
        System.out.println(easemobAuthToken.getAuthToken());
        Assert.assertNotNull(easemobAuthToken.getAuthToken());
    }
}
