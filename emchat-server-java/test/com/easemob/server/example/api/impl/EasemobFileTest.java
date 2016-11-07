package com.easemob.server.example.api.impl;

import com.easemob.server.example.api.FileAPI;
import com.easemob.server.example.comm.ClientContext;
import com.easemob.server.example.comm.EasemobRestAPIFactory;
import com.easemob.server.example.comm.wrapper.ResponseWrapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Collection;

/**
 * EasemobFile Tester.
 *
 * @author <Aily>
 * @version 1.0
 * @since <pre>2016.11</pre>
 */
@RunWith(Parameterized.class)
public class EasemobFileTest {
    private static EasemobRestAPIFactory factory;
    private static FileAPI fileAPI;
    private String filePath;
    private Boolean isThumbnail;
    private ResponseWrapper fileResponse;


    @Parameterized.Parameters
    public static Collection<Object[]> params() {
        return Arrays.asList(new Object[][]{
                {"src/main/resources/image/01.jpg", false},
                {"src/main/resources/audio/audio.mp3", false},
                {"src/main/resources/vedio/web.mp4", true}
        });
    }

    public EasemobFileTest(String filePath, Boolean isThumbnail){
        this.filePath = filePath;
        this.isThumbnail = isThumbnail;
    }

    @BeforeClass
    public static void beforeClass() throws Exception {
        factory = ClientContext.getInstance().init(ClientContext.INIT_FROM_PROPERTIES).getAPIFactory();
        fileAPI = (FileAPI)factory.newInstance(EasemobRestAPIFactory.FILE_CLASS);
    }
    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }


    /**
     * Method: uploadFile(Object file)
     */
    @Test
    public void testUploadFile() throws Exception {

    }

    /**
     * Method: downloadFile(String fileUUID, String shareSecret, Boolean isThumbnail)
     */
    @Test
    public void testDownloadFile() throws Exception {
        fileResponse = (ResponseWrapper) fileAPI.uploadFile(new File(filePath));
        String uuid = ((ObjectNode) fileResponse.getResponseBody()).get("entities").get(0).get("uuid").asText();
        String shareSecret = ((ObjectNode) fileResponse.getResponseBody()).get("entities").get(0).get("share-secret").asText();
        InputStream in = (InputStream) ((ResponseWrapper) fileAPI.downloadFile(uuid, shareSecret, isThumbnail)).getResponseBody();
        FileOutputStream fos = new FileOutputStream("test/output");
        byte[] buffer = new byte[1024];
        int len1;
        while ((len1 = in.read(buffer)) != -1) {
            fos.write(buffer, 0, len1);
        }
        fos.close();
    }


} 
