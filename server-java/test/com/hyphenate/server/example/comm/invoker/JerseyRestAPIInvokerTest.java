package com.hyphenate.server.example.comm.invoker;

import com.hyphenate.server.example.comm.ClientContext;
import com.hyphenate.server.example.comm.utils.ResponseUtils;
import com.hyphenate.server.example.comm.wrapper.HeaderWrapper;
import com.hyphenate.server.example.comm.wrapper.ResponseWrapper;
import com.fasterxml.jackson.databind.JsonNode;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpUriRequest;
import org.easymock.EasyMock;
import org.easymock.IMocksControl;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * JerseyRestAPIInvoker Tester.
 *
 * @author <Hyphenate>
 * @version 1.0
 * @since <pre>2016.11</pre>
 */
public class JerseyRestAPIInvokerTest {

    private JerseyRestAPIInvoker jerseyClient;

    @BeforeClass
    public static void beforeClass(){
        ClientContext.getInstance().init(ClientContext.INIT_FROM_PROPERTIES);
    }

    @Before
    public void before() throws Exception {
        jerseyClient = new JerseyRestAPIInvoker();
    }

    @After
    public void after() throws Exception {
    }


    @Test
    public void testSendRequest_1() throws Exception {
        IMocksControl mocksControl = EasyMock.createControl();
        HttpClient clientMock = mocksControl.createMock(HttpClient.class);
        HttpResponse responseMock = mocksControl.createMock(HttpResponse.class);
        HttpUriRequest requestMock = mocksControl.createMock(HttpUriRequest.class);
        HttpEntity entityMock = mocksControl.createMock(HttpEntity.class);
        StatusLine statusLineMock = mocksControl.createMock(StatusLine.class);
        EasyMock.expect(responseMock.getStatusLine()).andReturn(statusLineMock);
        EasyMock.expect(statusLineMock.getStatusCode()).andReturn(200);
        FileInputStream fileInputStream = new FileInputStream("test/com/hyphenate/server/example/comm/invoker/mockdata/get user001");
        fileInputStream.close();
        EasyMock.expect(entityMock.getContent()).andReturn(fileInputStream);
        EasyMock.expect(clientMock.execute(requestMock)).andReturn(responseMock);
        mocksControl.replay();

        String method = "GET";
        String url = "https://api.hyphenate.io/hyphenate/demo/users/user001";
        String token = "YWMtnIF_ZI-GEea1KgfxnnDmKAAAAVjnsTKe0OE4vMOBWCtOcrB-56YcrhOHMho";
        HeaderWrapper header = new HeaderWrapper();
        header.addAuthorization(token);
        ResponseWrapper responseWrapper = jerseyClient.sendRequest(method, url, header, null, null);
        assertEquals("200", responseWrapper.getResponseStatus().toString());
        JsonNode jsonNode = ResponseUtils.ResponseBodyToJsonNode(responseWrapper);
        assertEquals("1", jsonNode.get("count").toString());
    }
    @Test
    public void testSendRequest_2() throws Exception {
        String method = "";
        String url = "";
        ResponseWrapper responseWrapper = jerseyClient.sendRequest(method, url, null, null, null);
        List<String> messages = new ArrayList<>();
        messages.add("[ERROR]: " + method + " is an unknown type of HTTP methods.");
        messages.add("[ERROR]: Parameter url should not be null or empty.");
        messages.add("[ERROR]: Parameter url doesn't match the required format.");
        Iterator<String> iterator = responseWrapper.getMessages().iterator();
        for (String s : messages) {
            assertEquals(s, iterator.next());
        }
    }
} 
