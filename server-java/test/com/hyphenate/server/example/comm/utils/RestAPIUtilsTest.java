package com.hyphenate.server.example.comm.utils;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.net.ssl.SSLHandshakeException;
import java.net.URL;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 * RestAPIUtils Tester.
 *
 * @author <Hyphenate>
 * @version 1.0
 * @since <pre>2016.11</pre>
 */
public class RestAPIUtilsTest {

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    @Test
    public void testGetJerseyClient() throws Exception {
        //TODO: Test goes here...
    }

    @Test
    public void testGetHttpClient_1() throws Exception {
        Boolean isSSL = true;
        String carcertFilePath = "src/main/resources/cacert/mykeystore.jks";
        String cacertFilePassword = "123456";
        String url = "https://www.baidu.com";
        HttpClient client = RestAPIUtils.getHttpClient(isSSL, carcertFilePath, cacertFilePassword);
        URL target = new URL(url);
        HttpUriRequest request = new HttpGet(target.toURI());
        try {
            client.execute(request);
        } catch (SSLHandshakeException e) {
            assertTrue(e.getMessage().equals("sun.security.validator.ValidatorException: No trusted certificate found"));
        }
    }

    @Test
    public void testGetHttpClient_2() throws Exception {
        Boolean isSSL = true;
        String carcertFilePath = "src/main/resources/cacert/mykeystore.jks";
        String cacertFilePassword = "123456";
        String url = "https://a1.hyphenate.com/status";
        HttpClient client = RestAPIUtils.getHttpClient(isSSL, carcertFilePath, cacertFilePassword);
        URL target = new URL(url);
        HttpUriRequest request = new HttpGet(target.toURI());
        HttpResponse response = client.execute(request);
        assertNotNull(response);
    }

    /**
     * Method: match(String regex, String str)
     */
    @Test
    public void testMatch() throws Exception {
        //TODO: Test goes here...
    }
} 
