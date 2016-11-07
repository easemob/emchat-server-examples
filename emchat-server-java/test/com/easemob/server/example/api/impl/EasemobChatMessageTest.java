package com.easemob.server.example.api.impl;

import com.easemob.server.example.api.ChatMessageAPI;
import com.easemob.server.example.comm.ClientContext;
import com.easemob.server.example.comm.EasemobRestAPIFactory;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

/**
 * EasemobChatMessage Tester.
 *
 * @author <Aily>
 * @version 1.0
 * @since <pre>2016.11</pre>
 */

@RunWith(Parameterized.class)
public class EasemobChatMessageTest {
    private static EasemobRestAPIFactory factory;
    private static ChatMessageAPI chatMessageAPI;
    private Long limit;
    private String cursor;
    private String query;

    public EasemobChatMessageTest(Long limit, String cursor, String query){
        this.limit = limit;
        this.cursor = cursor;
        this.query = query;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> params() {
        return Arrays.asList(new Object[][]{
                {10L, null, "select+*+where+timestamp>1403164734226"},
                {null, null, null},
                {10L, "123", null}
        });
    }

    @BeforeClass
    public static void beforeClass() throws Exception {
        factory = ClientContext.getInstance().init(ClientContext.INIT_FROM_PROPERTIES).getAPIFactory();
        chatMessageAPI = (ChatMessageAPI)factory.newInstance(EasemobRestAPIFactory.MESSAGE_CLASS);
    }
    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: exportChatMessages(Long limit, String cursor, String query)
     */
    @Test
    public void testExportChatMessages() throws Exception {
        chatMessageAPI.exportChatMessages(limit, cursor, query);
    }


} 
