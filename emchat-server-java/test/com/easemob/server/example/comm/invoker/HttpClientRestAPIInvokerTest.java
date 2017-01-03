package com.hyphenate.server.example.comm.invoker;

import com.hyphenate.server.example.comm.ClientContext;
import com.hyphenate.server.example.comm.utils.ResponseUtils;
import com.hyphenate.server.example.comm.wrapper.HeaderWrapper;
import com.hyphenate.server.example.comm.wrapper.ResponseWrapper;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.CloseableHttpClient;
import org.easymock.EasyMock;
import org.easymock.IMocksControl;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.junit.Assert.*;

/**
 * HttpClientRestAPIInvoker Tester.
 *
 * @author <Hyphenate>
 * @version 1.0
 * @since <pre>2016.11</pre>
 */
public class HttpClientRestAPIInvokerTest {

    private HttpClientRestAPIInvoker httpClient;

    @BeforeClass
    public static void beforeClass(){
        ClientContext.getInstance().init(ClientContext.INIT_FROM_PROPERTIES);
    }
    @Before
    public void before() throws Exception {
        httpClient = new HttpClientRestAPIInvoker();
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
        String url = "https://a1.hyphenate.com/1122161011178276/testapp/users/user001";
        String token = "YWMtnIF_ZI-GEea1KgfxnnDmKAAAAVjnsTKe0OE4vMOBWCtOcrB-56YcrhOHMho";
        HeaderWrapper header = new HeaderWrapper();
        header.addAuthorization(token);
        ResponseWrapper responseWrapper = httpClient.sendRequest(method, url, header, null, null);
        assertEquals("200", responseWrapper.getResponseStatus().toString());
        JsonNode jsonNode = ResponseUtils.ResponseBodyToJsonNode(responseWrapper);
        assertEquals("1", jsonNode.get("count").toString());
    }

    @Test
    public void testSendRequest_2() throws Exception {
        IMocksControl mocksControl = EasyMock.createControl();
        HttpClient clientMock = mocksControl.createMock(HttpClient.class);
        HttpResponse responseMock = mocksControl.createMock(HttpResponse.class);
        HttpUriRequest requestMock = mocksControl.createMock(HttpUriRequest.class);
        HttpEntity entityMock = mocksControl.createMock(HttpEntity.class);
        StatusLine statusLineMock = mocksControl.createMock(StatusLine.class);
        EasyMock.expect(responseMock.getStatusLine()).andReturn(statusLineMock);
        EasyMock.expect(statusLineMock.getStatusCode()).andReturn(404);
        FileInputStream fileInputStream = new FileInputStream("test/com/hyphenate/server/example/comm/invoker/mockdata/get u1");
        fileInputStream.close();
        EasyMock.expect(entityMock.getContent()).andReturn(fileInputStream);
        EasyMock.expect(clientMock.execute(requestMock)).andReturn(responseMock);
        mocksControl.replay();

        String method = "GET";
        String url = "https://a1.hyphenate.com/1122161011178276/testapp/users/u1";
        String token = "YWMtnIF_ZI-GEea1KgfxnnDmKAAAAVjnsTKe0OE4vMOBWCtOcrB-56YcrhOHMho";
        HeaderWrapper header = new HeaderWrapper();
        header.addAuthorization(token);
        ResponseWrapper responseWrapper = httpClient.sendRequest(method, url, header, null, null);
        assertEquals("404", responseWrapper.getResponseStatus().toString());
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(responseWrapper.getResponseBody());
        JsonNode jsonNode = mapper.readTree(json);
        assertEquals("\"service_resource_not_found\"", jsonNode.get("error").toString());
    }

    @Test
    public void testSendRequest_3() throws Exception {
        String method = "";
        String url = "";
        ResponseWrapper responseWrapper = httpClient.sendRequest(method, url, null, null, null);
        List<String> messages = new ArrayList<>();
        messages.add("[ERROR]: " + method + " is an unknown type of HTTP methods.");
        messages.add("[ERROR]: Parameter url should not be null or empty.");
        messages.add("[ERROR]: Parameter url doesn't match the required format.");
        Iterator<String> iterator = responseWrapper.getMessages().iterator();
        for (String s : messages) {
            assertEquals(s, iterator.next());
        }
    }

    @Test
    public void testSendRequest_4() throws Exception {
        String method = "GET";
        String url = "https://a1.hyphenate.com/1122161011178276/testapp/users";
        String token = "error";
        HeaderWrapper header = new HeaderWrapper();
        header.addAuthorization(token);
        ResponseWrapper responseWrapper = httpClient.sendRequest(method, url, header, null, null);
        assertEquals("401", responseWrapper.getResponseStatus().toString());
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(responseWrapper.getResponseBody());
        JsonNode jsonNode = mapper.readTree(json);
        assertEquals("\"unauthorized\"", jsonNode.get("error").toString());
    }

    @Test
    public void testUploadFile_1() throws Exception {
        IMocksControl mocksControl = EasyMock.createControl();
        CloseableHttpClient clientMock = mocksControl.createMock(CloseableHttpClient.class);
        CloseableHttpResponse responseMock = mocksControl.createMock(CloseableHttpResponse.class);
        HttpPost httpPostMock = mocksControl.createMock(HttpPost.class);
        HttpEntity entityMock = mocksControl.createMock(HttpEntity.class);
        StatusLine statusLineMock = mocksControl.createMock(StatusLine.class);
        EasyMock.expect(responseMock.getStatusLine()).andReturn(statusLineMock);
        EasyMock.expect(statusLineMock.getStatusCode()).andReturn(404);
        FileInputStream fileInputStream = new FileInputStream("test/com/hyphenate/server/example/comm/invoker/mockdata/upload file");
        fileInputStream.close();
        EasyMock.expect(entityMock.getContent()).andReturn(fileInputStream);
        EasyMock.expect(clientMock.execute(httpPostMock)).andReturn(responseMock);
        mocksControl.replay();

        String url = "https://a1.hyphenate.com/1122161011178276/testapp/chatfiles";
        HeaderWrapper headerWrapper = new HeaderWrapper();
        headerWrapper.addAuthorization("YWMtnIF_ZI-GEea1KgfxnnDmKAAAAVjnsTKe0OE4vMOBWCtOcrB-56YcrhOHMho");
        File file = new File("src/main/resources/audio/audio.mp3");
        ResponseWrapper responseWrapper = httpClient.uploadFile(url, headerWrapper, file);
        assertEquals("200", responseWrapper.getResponseStatus().toString());
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(responseWrapper.getResponseBody());
        JsonNode jsonNode = mapper.readTree(json);
        assertNull(jsonNode.get("entities").get(0).get("share-secret"));
    }

    @Test
    public void testUploadFile_2() throws Exception {
        String url = "";
        HeaderWrapper headerWrapper = new HeaderWrapper();
        File file = new File("");
        ResponseWrapper responseWrapper = httpClient.uploadFile(url, headerWrapper, file);
        List<String> messages = new ArrayList<>();
        messages.add("[ERROR]: Parameter url should not be null or empty.");
        messages.add("[ERROR]: Parameter url doesn't match the required format.");
        messages.add("[ERROR]: Request body is invalid.");
        Iterator<String> iterator = responseWrapper.getMessages().iterator();
        for (String s : messages) {
            assertEquals(s, iterator.next());
        }
    }

    @Test
    public void testDownloadFile_1() throws Exception {
        IMocksControl mocksControl = EasyMock.createControl();
        CloseableHttpClient clientMock = mocksControl.createMock(CloseableHttpClient.class);
        CloseableHttpResponse responseMock = mocksControl.createMock(CloseableHttpResponse.class);
        HttpGet httpGetMock = mocksControl.createMock(HttpGet.class);
        HttpEntity entityMock = mocksControl.createMock(HttpEntity.class);
        StatusLine statusLineMock = mocksControl.createMock(StatusLine.class);
        EasyMock.expect(responseMock.getStatusLine()).andReturn(statusLineMock);
        EasyMock.expect(statusLineMock.getStatusCode()).andReturn(200);
        EasyMock.expect(entityMock.getContent()).andReturn(new ByteArrayInputStream(new byte[]{}));
        EasyMock.expect(clientMock.execute(httpGetMock)).andReturn(responseMock);
        mocksControl.replay();

        String uuid = "6b9dda80-a63a-11e6-95e3-751d965c5829";
        String url = "https://a1.hyphenate.com/1122161011178276/testapp/chatfiles/" + uuid;
        HeaderWrapper header = new HeaderWrapper();
        header.addAuthorization("YWMtnIF_ZI-GEea1KgfxnnDmKAAAAVjnsTKe0OE4vMOBWCtOcrB-56YcrhOHMho")
                .addHeader("accept", "application/octet-stream")
                .addHeader("share-secret", "a53aiqY6EeaDcifITFyHkxDumQF33z2fFpbdemGk1UTC27kk")
                .addHeader("thumbnail", "true");
        ResponseWrapper responseWrapper = httpClient.downloadFile(url, header);
        assertEquals("200", responseWrapper.getResponseStatus().toString());
    }

    @Test
    public void testDownloadFile_2() throws Exception {
        IMocksControl mocksControl = EasyMock.createControl();
        CloseableHttpClient clientMock = mocksControl.createMock(CloseableHttpClient.class);
        CloseableHttpResponse responseMock = mocksControl.createMock(CloseableHttpResponse.class);
        HttpGet httpGetMock = mocksControl.createMock(HttpGet.class);
        HttpEntity entityMock = mocksControl.createMock(HttpEntity.class);
        StatusLine statusLineMock = mocksControl.createMock(StatusLine.class);
        EasyMock.expect(responseMock.getStatusLine()).andReturn(statusLineMock);
        EasyMock.expect(statusLineMock.getStatusCode()).andReturn(200);
        EasyMock.expect(entityMock.getContent()).andReturn(new ByteArrayInputStream(new byte[]{}));
        EasyMock.expect(clientMock.execute(httpGetMock)).andReturn(responseMock);
        mocksControl.replay();
        String uuid = "6b9dda80-a63a-11e6-95e3-751d965c5829";
        String url = "https://a1.hyphenate.com/1122161011178276/testapp/chatfiles/" + uuid;
        ResponseWrapper responseWrapper = httpClient.downloadFile(url, null);
        assertEquals("200", responseWrapper.getResponseStatus().toString());
    }

} 
