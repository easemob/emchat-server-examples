package com.easemob.server.example.api.impl;

import com.easemob.server.example.api.SendMessageAPI;
import com.easemob.server.example.comm.ClientContext;
import com.easemob.server.example.comm.EasemobRestAPIFactory;
import com.easemob.server.example.comm.body.*;
import com.easemob.server.example.comm.wrapper.BodyWrapper;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.*;

/**
 * EasemobSendMessage Tester.
 *
 * @author Aily
 * @version 1.0
 * @since <pre>2016.11.02</pre>
 */
@RunWith(Parameterized.class)
public class EasemobSendMessageTest {
    private static EasemobRestAPIFactory factory;
    private static SendMessageAPI sendMessageAPI;
    private BodyWrapper messageBody;
    public EasemobSendMessageTest(BodyWrapper messageBody){
        this.messageBody = messageBody;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> params() {
        return Arrays.asList(new Object[][]{
                //case 1: String targetType, String[] targets, String from, Map<String, String> ext, String msg
                {new TextMessageBody("users", new String[]{"user001","user002"}, "userno", new HashMap<String, String>() {{ put("ext1", "card"); put("ext2", "emoticon");}}, "hello")},
                //case 2: String targetType, String[] targets, String from, Map<String, String> ext, String url, String filename, String secret, Long width, Long height
                {new ImgMessageBody("users", new String[]{"userno"}, null, null, "https://a1.easemob.com/1193161011115832/testapp/chatfiles/c8393fa0-a49b-11e6-880c-6b3caa6b5620", "down.jpg", "yDk_qqSbEea9yJuH3EamqWcKdbrPZJdrSMajDkpeeZBonTA4", 400L, 300L)},
                //case 3: String targetType, String[] targets, String from, Map<String, String> ext, String url, String filename, String secret, Long length
                {new AudioMessageBody("users", new String[]{"user001"}, "user001", null, "https://a1.easemob.com/1193161011115832/testapp/chatfiles/ca209d40-a49b-11e6-9b08-7b56fe4f9642", "audio.mp3", "yiCdSqSbEeaiIlmy-3okSdmDfM4YvMPX9mmdg4CBTzdRe_3M", 60L)},
                //case 4: String targetType, String[] targets, String from, Map<String, String> ext, String url, String filename, String secret, Long length, Long fileLength,String thumb, String thumbSecret
                {new VideoMessageBody("users", new String[]{"user001", "user002"}, null, null, "https://a1.easemob.com/1193161011115832/testapp/chatfiles/cfcf3c10-a49b-11e6-bdfc-833250457e06", "vedio.mp3", "z888GqSbEeaJJqF2NilKQ3EqzuMGjqgiNZzc2zEnaClbaCq9", 60L, 58130L, "", "")},
                //case 5: String targetType, String[] targets, String from, Map<String, String> ext, String action
                {new CommandMessageBody("users", new String[]{"user001", "user002"}, null, null, "action1")}
        });
    }

    @BeforeClass
    public static void beforeClass() throws Exception {
        factory = ClientContext.getInstance().init(ClientContext.INIT_FROM_PROPERTIES).getAPIFactory();
        sendMessageAPI = (SendMessageAPI) factory.newInstance(EasemobRestAPIFactory.SEND_MESSAGE_CLASS);
    }

    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }


    @Test
    public void testSendMessage() throws Exception {
        sendMessageAPI.sendMessage(messageBody);
    }


} 
