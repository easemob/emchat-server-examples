package com.easemob.server.example.api.impl;

import com.easemob.server.example.api.ChatRoomAPI;
import com.easemob.server.example.comm.ClientContext;
import com.easemob.server.example.comm.EasemobRestAPIFactory;
import com.easemob.server.example.comm.body.ChatRoomBody;
import com.easemob.server.example.comm.body.ModifyChatRoomBody;
import com.easemob.server.example.comm.body.UserNamesBody;
import com.easemob.server.example.comm.wrapper.BodyWrapper;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;
import java.util.Random;

/**
 * EasemobChatRoom Tester.
 *
 * @author <Aily>
 * @version 1.0
 * @since <pre>2016.11</pre>
 */
@RunWith(Parameterized.class)
public class EasemobChatRoomTest {
    private static EasemobRestAPIFactory factory;
    private static ChatRoomAPI chatRoomAPI;
    private int n1, n2;
    private int num1, num2;
    private String[] chatRoomId = new String[n1];
    private String[] username = new String[n2];
    private String chatRoomName;
    private String desc;
    private Long maxUsers;
    private String owner;

    public EasemobChatRoomTest(int n1, int n2, String[] chatRoomId, String[] username, String chatRoomName, String desc,
                               Long maxUsers, String owner){
        this.n1 = n1;
        this.n2 = n2;
        this.chatRoomId = chatRoomId;
        this.username = username;
        this.chatRoomId = chatRoomId;
        this.chatRoomName = chatRoomName;
        this.desc = desc;
        this.maxUsers = maxUsers;
        this.owner = owner;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> params() {
        return Arrays.asList(new Object[][]{
                //case 1: int n1, int n2, String[] chatRoomId, String[] username, String chatRoomName, String desc, Long maxUsers, String owner
                {3, 3, new String[]{"261712259087073716", "261712313877266860", "261712412707652008"}, new String[]{"user001","user002","user003"}, "testchatroom1", "", 300L, "user100"},
                //case 2
                {3, 3, new String[]{"261712259087073716", "261712313877266860", "261712412707652008"}, new String[]{"user001","user002","user003"}, "testchatroom2", "", 300L, "userno"}
        });
    }

    @BeforeClass
    public static void beforeClass() throws Exception {
        factory = ClientContext.getInstance().init(ClientContext.INIT_FROM_PROPERTIES).getAPIFactory();
        chatRoomAPI = (ChatRoomAPI) factory.newInstance(EasemobRestAPIFactory.CHATROOM_CLASS);
    }

    @Before
    public void before() throws Exception {
        this.num1 = new Random().nextInt(n1);
        this.num2 = new Random().nextInt(n2);
    }

    @After
    public void after() throws Exception {
    }

    /**
     * Method: createChatRoom(Object payload)
     */
    @Test
    public void testCreateChatRoom() throws Exception {
        BodyWrapper bodyWrapper = new ChatRoomBody(chatRoomName, desc, maxUsers, owner, username);
        chatRoomAPI.createChatRoom(bodyWrapper);
    }

    /**
     * Method: modifyChatRoom(String roomId, Object payload)
     */
    @Test
    public void testModifyChatRoom() throws Exception {
        BodyWrapper bodyWrapper = new ModifyChatRoomBody(chatRoomName, desc, maxUsers);
        chatRoomAPI.modifyChatRoom(chatRoomId[num1], bodyWrapper);
    }

    /**
     * Method: deleteChatRoom(String roomId)
     */
    @Test
    public void testDeleteChatRoom() throws Exception {
        chatRoomAPI.deleteChatRoom(chatRoomId[num1]);
    }

    /**
     * Method: getAllChatRooms()
     */
    @Test
    public void testGetAllChatRooms() throws Exception {
        chatRoomAPI.getAllChatRooms();
    }

    /**
     * Method: getChatRoomDetail(String roomId)
     */
    @Test
    public void testGetChatRoomDetail() throws Exception {
        chatRoomAPI.getChatRoomDetail(chatRoomId[num1]);
    }

    /**
     * Method: addSingleUserToChatRoom(String roomId, String userName)
     */
    @Test
    public void testAddSingleUserToChatRoom() throws Exception {
        chatRoomAPI.addSingleUserToChatRoom(chatRoomId[num1], username[num2]);
    }

    /**
     * Method: addBatchUsersToChatRoom(String roomId, Object payload)
     */
    @Test
    public void testAddBatchUsersToChatRoom() throws Exception {
        BodyWrapper bodyWrapper = new UserNamesBody(username);
        chatRoomAPI.addBatchUsersToChatRoom(chatRoomId[num1], bodyWrapper);
    }

    /**
     * Method: removeSingleUserFromChatRoom(String roomId, String userName)
     */
    @Test
    public void testRemoveSingleUserFromChatRoom() throws Exception {
        chatRoomAPI.removeSingleUserFromChatRoom(chatRoomId[num1], username[num2]);
    }

    /**
     * Method: removeBatchUsersFromChatRoom(String roomId, String[] userNames)
     */
    @Test
    public void testRemoveBatchUsersFromChatRoom() throws Exception {
        chatRoomAPI.removeBatchUsersFromChatRoom(chatRoomId[num1], username);
    }

} 
