package com.easemob.server.example.api.impl;

import com.easemob.server.example.api.FileAPI;
import com.easemob.server.example.api.IMUserAPI;
import com.easemob.server.example.api.SendMessageAPI;
import com.easemob.server.example.comm.ClientContext;
import com.easemob.server.example.comm.EasemobRestAPIFactory;
import com.easemob.server.example.comm.body.ImgMessageBody;
import com.easemob.server.example.comm.utils.ResponseUtils;
import com.easemob.server.example.comm.wrapper.BodyWrapper;
import com.easemob.server.example.comm.wrapper.ResponseWrapper;
import com.fasterxml.jackson.databind.JsonNode;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;

import static org.junit.Assert.assertEquals;

/**
 * EasemobSendMessage Tester.
 *
 * @author Aily
 * @version 1.0
 * @since <pre>2016.11.02</pre>
 */
public class EasemobSendMessageTest {


    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }


    @Test
    public void testSendAndReceiveMessage() throws Exception {
        EasemobRestAPIFactory factory = ClientContext.getInstance().init(ClientContext.INIT_FROM_PROPERTIES).getAPIFactory();
        IMUserAPI userAPI = (EasemobIMUsers) factory.newInstance(EasemobRestAPIFactory.USER_CLASS);
        FileAPI fileAPI = (EasemobFile) factory.newInstance(EasemobRestAPIFactory.FILE_CLASS);
        SendMessageAPI sendMessageAPI = (EasemobSendMessage) factory.newInstance(EasemobRestAPIFactory.SEND_MESSAGE_CLASS);

        //get user001's offline message count before send message
        ResponseWrapper response1 = (ResponseWrapper) userAPI.getOfflineMsgCount("user001");
        JsonNode jsonNode1 = ResponseUtils.ResponseBodyToJsonNode(response1);
        assertEquals("200", response1.getResponseStatus().toString());
        int count_before = Integer.parseInt(jsonNode1.get("data").get("user001").toString());

        //upload img file
        File file = new File("src/main/resources/image/01.jpg");
        ResponseWrapper response2 = (ResponseWrapper) fileAPI.uploadFile(file);
        JsonNode jsonNode2 = ResponseUtils.ResponseBodyToJsonNode(response2);
        String uuid = jsonNode2.get("entities").get(0).get("uuid").toString();
        String share_secret = jsonNode2.get("entities").get(0).get("share-secret").toString();
        assertEquals("200", response2.getResponseStatus().toString());

        //send img message
        String targetType = "users";
        String[] targets = new String[]{"user001"};
        String url = "https://a1.easemob.com/1193161011115832/testapp/chatfiles/" + uuid;
        String filename = file.getName();
        String secret = share_secret;
        BufferedImage bufferedImg = ImageIO.read(file);
        Long imgWidth = new Long(bufferedImg.getWidth());
        Long imgHeight = new Long(bufferedImg.getHeight());
        BodyWrapper bodyWrapper = new ImgMessageBody(targetType, targets, null, null, url, filename, secret, imgHeight, imgWidth);
        ResponseWrapper response3 = (ResponseWrapper) sendMessageAPI.sendMessage(bodyWrapper);
        assertEquals("200", response3.getResponseStatus().toString());

        //get user001's offline message count after send message
        ResponseWrapper response4 = (ResponseWrapper) userAPI.getOfflineMsgCount("user001");
        JsonNode jsonNode3 = ResponseUtils.ResponseBodyToJsonNode(response4);
        int count_after = Integer.parseInt(jsonNode3.get("data").get("user001").toString());
        assertEquals("200", response4.getResponseStatus().toString());

        assertEquals(count_before + 1, count_after);
    }
} 
