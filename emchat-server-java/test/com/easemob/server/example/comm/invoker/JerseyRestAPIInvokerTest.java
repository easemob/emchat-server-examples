package com.easemob.server.example.comm.invoker;

import com.easemob.server.example.comm.ClientContext;
import com.easemob.server.example.comm.body.IMUserBody;
import com.easemob.server.example.comm.body.IMUsersBody;
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

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

/**
 * JerseyRestAPIInvoker Tester.
 *
 * @author <Aily>
 * @version 1.0
 * @since <pre>2016.11</pre>
 */
public class JerseyRestAPIInvokerTest {
    
    JerseyRestAPIInvoker jerseyClient;

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

    /**
     * Method: sendRequest(String method, String url, HeaderWrapper header, BodyWrapper body, QueryWrapper query)
     */
    @Test
    public void testSendRequest_1() throws Exception {
        String method = "GET";
        String url = "https://a1.easemob.com/1122161011178276/testapp/users/user1";
        String token = "YWMtnIF_ZI-GEea1KgfxnnDmKAAAAVjnsTKe0OE4vMOBWCtOcrB-56YcrhOHMho";
        HeaderWrapper header = new HeaderWrapper();
        header.addAuthorization(token);
        BodyWrapper body = null;
        QueryWrapper query = null;
        ResponseWrapper responseWrapper = jerseyClient.sendRequest(method, url, header, body, query);
        assertEquals("200", responseWrapper.getResponseStatus().toString());
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(responseWrapper.getResponseBody());
        JsonNode jsonNode = mapper.readTree(json);
        assertEquals("1", jsonNode.get("count").toString());
    }

    @Test
    public void testSendRequest_2() throws Exception {
        String method = "POST";
        String url = "https://a1.easemob.com/1122161011178276/testapp/users";
        String token = "YWMtnIF_ZI-GEea1KgfxnnDmKAAAAVjnsTKe0OE4vMOBWCtOcrB-56YcrhOHMho";
        HeaderWrapper header = new HeaderWrapper();
        header.addAuthorization(token);
        List<IMUserBody> users = new ArrayList<>();
        users.add(new IMUserBody("@usertest01", "123456", "sunny"));
        users.add(new IMUserBody("usertest02", "123456", "@"));
        BodyWrapper body = new IMUsersBody(users);
        QueryWrapper query = null;
        ResponseWrapper responseWrapper = jerseyClient.sendRequest(method, url, header, body, query);
        assertEquals("400", responseWrapper.getResponseStatus().toString());
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(responseWrapper.getResponseBody());
        JsonNode jsonNode = mapper.readTree(json);
        assertEquals("\"username [@usertest01] is not legal\"", jsonNode.get("error_description").toString());
    }

    @Test
    public void testSendRequest_3() throws Exception {
        String method = "DELETE";
        String url = "https://a1.easemob.com/1122161011178276/testapp/user/usererror";
        String token = "YWMtnIF_ZI-GEea1KgfxnnDmKAAAAVjnsTKe0OE4vMOBWCtOcrB-56YcrhOHMho";
        HeaderWrapper header = new HeaderWrapper();
        header.addAuthorization(token);
        BodyWrapper body = null;
        QueryWrapper query = null;
        ResponseWrapper responseWrapper = jerseyClient.sendRequest(method, url, header, body, query);
        assertEquals("404", responseWrapper.getResponseStatus().toString());
        ObjectMapper mapper = new ObjectMapper();
        String json = mapper.writeValueAsString(responseWrapper.getResponseBody());
        JsonNode jsonNode = mapper.readTree(json);
        assertEquals("\"Service resource not found\"", jsonNode.get("error_description").toString());
    }

    /**
     * Method: uploadFile(String url, HeaderWrapper header, File file)
     */
    @Test
    public void testUploadFile() throws Exception {
        //TODO: Test goes here...
    }

    /**
     * Method: downloadFile(String url, HeaderWrapper header)
     */
    @Test
    public void testDownloadFile() throws Exception {
        //TODO: Test goes here...
    }


    /**
     * Method: buildHeader(Invocation.Builder inBuilder, HeaderWrapper header)
     */
    @Test
    public void testBuildHeader() throws Exception {
//TODO: Test goes here... 
/* 
try { 
   Method method = JerseyRestAPIInvoker.getClass().getMethod("buildHeader", Invocation.Builder.class, HeaderWrapper.class); 
   method.setAccessible(true); 
   method.invoke(<Object>, <Parameters>); 
} catch(NoSuchMethodException e) { 
} catch(IllegalAccessException e) { 
} catch(InvocationTargetException e) { 
} 
*/
    }

    /**
     * Method: buildQuery(JerseyWebTarget target, QueryWrapper query)
     */
    @Test
    public void testBuildQuery() throws Exception {
//TODO: Test goes here... 
/* 
try { 
   Method method = JerseyRestAPIInvoker.getClass().getMethod("buildQuery", JerseyWebTarget.class, QueryWrapper.class); 
   method.setAccessible(true); 
   method.invoke(<Object>, <Parameters>); 
} catch(NoSuchMethodException e) { 
} catch(IllegalAccessException e) { 
} catch(InvocationTargetException e) { 
} 
*/
    }

} 
