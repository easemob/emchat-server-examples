package com.easemob.server.example.comm.invoker;

import com.easemob.server.example.comm.ClientContext;
import com.easemob.server.example.comm.wrapper.BodyWrapper;
import com.easemob.server.example.comm.wrapper.HeaderWrapper;
import com.easemob.server.example.comm.wrapper.QueryWrapper;
import com.easemob.server.example.comm.wrapper.ResponseWrapper;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static org.junit.Assert.*;

/**
 * HttpClientRestAPIInvoker Tester.
 *
 * @author <Aily>
 * @version 1.0
 * @since <pre>2016.11</pre>
 */
public class HttpClientRestAPIInvokerTest {

    HttpClientRestAPIInvoker httpClient;

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
        String method = "GET";
        String url = "https://a1.easemob.com/1122161011178276/testapp/users/user001";
        String token = "YWMtnIF_ZI-GEea1KgfxnnDmKAAAAVjnsTKe0OE4vMOBWCtOcrB-56YcrhOHMho";
        HeaderWrapper header = new HeaderWrapper();
        header.addAuthorization(token);
        BodyWrapper body = null;
        QueryWrapper query = null;
        ResponseWrapper responseWrapper = httpClient.sendRequest(method, url, header, body, query);
        assertEquals("200", responseWrapper.getResponseStatus().toString());
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(responseWrapper.getResponseBody());
        JsonNode jsonNode = mapper.readTree(json);
        assertEquals("1", jsonNode.get("count").toString());
    }

    @Test
    public void testSendRequest_2() throws Exception {
        String method = "GET";
        String url = "https://a1.easemob.com/1122161011178276/testapp/users/u1";
        String token = "YWMtnIF_ZI-GEea1KgfxnnDmKAAAAVjnsTKe0OE4vMOBWCtOcrB-56YcrhOHMho";
        HeaderWrapper header = new HeaderWrapper();
        header.addAuthorization(token);
        BodyWrapper body = null;
        QueryWrapper query = null;
        ResponseWrapper responseWrapper = httpClient.sendRequest(method, url, header, body, query);
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
        HeaderWrapper header = null;
        BodyWrapper body = null;
        QueryWrapper query = null;
        ResponseWrapper responseWrapper = httpClient.sendRequest(method, url, header, body, query);
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
        String url = "https://a1.easemob.com/1122161011178276/testapp/users/user001";
        String token = "YWMtnIF_ZI-GEea1KgfxnnDmKAAAAVjnsTKe0OE4vMOBWCtOcrB-56YcrhOHMho";
        HeaderWrapper header = new HeaderWrapper();
        header.addAuthorization(token);
        BodyWrapper body = null;
        QueryWrapper query = new QueryWrapper();
        query.addLimit(1L);
        ResponseWrapper responseWrapper = httpClient.sendRequest(method, url, header, body, query);
        assertEquals("200", responseWrapper.getResponseStatus().toString());
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(responseWrapper.getResponseBody());
        JsonNode jsonNode = mapper.readTree(json);
        int actual = Integer.parseInt(jsonNode.get("count").toString());
        assertTrue(actual == 1 || actual == 0);
    }

    @Test
    public void testSendRequest_5() throws Exception {
        String method = "GET";
        String url = "https://a1.easemob.com/1122161011178276/testapp/users";
        String token = "error";
        HeaderWrapper header = new HeaderWrapper();
        header.addAuthorization(token);
        BodyWrapper body = null;
        QueryWrapper query = null;
        ResponseWrapper responseWrapper = httpClient.sendRequest(method, url, header, body, query);
        assertEquals("401", responseWrapper.getResponseStatus().toString());
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(responseWrapper.getResponseBody());
        JsonNode jsonNode = mapper.readTree(json);
        assertEquals("\"unauthorized\"", jsonNode.get("error").toString());
    }

    @Test
    public void testUploadFile_1() throws Exception {
        String url = "https://a1.easemob.com/1122161011178276/testapp/chatfiles";
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
        String url = "https://a1.easemob.com/1122161011178276/testapp/chatfiles";
        HeaderWrapper headerWrapper = new HeaderWrapper();
        headerWrapper.addAuthorization("YWMtnIF_ZI-GEea1KgfxnnDmKAAAAVjnsTKe0OE4vMOBWCtOcrB-56YcrhOHMho");
        headerWrapper.addRestrictAccess();
        File file = new File("src/main/resources/image/01.jpg");
        ResponseWrapper responseWrapper = httpClient.uploadFile(url, headerWrapper, file);
        assertEquals("200", responseWrapper.getResponseStatus().toString());
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(responseWrapper.getResponseBody());
        JsonNode jsonNode = mapper.readTree(json);
        assertNotNull(jsonNode.get("entities").get(0).get("share-secret"));
    }

    @Test
    public void testUploadFile_3() throws Exception {
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
        String uuid = "6b9dda80-a63a-11e6-95e3-751d965c5829";
        String url = "https://a1.easemob.com/1122161011178276/testapp/chatfiles/" + uuid;
        HeaderWrapper header = new HeaderWrapper();
        header.addAuthorization("YWMtnIF_ZI-GEea1KgfxnnDmKAAAAVjnsTKe0OE4vMOBWCtOcrB-56YcrhOHMho")
                .addHeader("accept", "application/octet-stream")
                .addHeader("share-secret", "a53aiqY6EeaDcifITFyHkxDumQF33z2fFpbdemGk1UTC27kk")
                .addHeader("thumbnail", "true");
        ResponseWrapper responseWrapper = httpClient.downloadFile(url, header);
        assertEquals("200", responseWrapper.getResponseStatus().toString());
        /*InputStream in = (InputStream) (httpClient.downloadFile(url, header)).getResponseBody();
        FileOutputStream fos = new FileOutputStream("test/output");
        byte[] buffer = new byte[1024];
        int len1;
        while ((len1 = in.read(buffer)) != -1) {
            fos.write(buffer, 0, len1);
        }
        fos.close();*/
    }

    @Test
    public void testDownloadFile_2() throws Exception {
        String uuid = "6b9dda80-a63a-11e6-95e3-751d965c5829";
        String url = "https://a1.easemob.com/1122161011178276/testapp/chatfiles/" + uuid;
        HeaderWrapper header = null;
        ResponseWrapper responseWrapper = httpClient.downloadFile(url, header);
        assertEquals("200", responseWrapper.getResponseStatus().toString());
    }

} 
