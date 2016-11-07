package com.easemob.server.example.api.impl;

import com.easemob.server.example.api.ChatGroupAPI;
import com.easemob.server.example.comm.ClientContext;
import com.easemob.server.example.comm.EasemobRestAPIFactory;
import com.easemob.server.example.comm.body.ChatGroupBody;
import com.easemob.server.example.comm.body.GroupOwnerTransferBody;
import com.easemob.server.example.comm.body.ModifyChatGroupBody;
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
 * EasemobChatGroup Tester.
 *
 * @author <Aily>
 * @version 1.0
 * @since <pre>2016.11.04</pre>
 */
@RunWith(Parameterized.class)
public class EasemobChatGroupTest {

    private static EasemobRestAPIFactory factory;
    private static ChatGroupAPI chatGroupAPI;
    private int n1, n2;
    private int num1, num2;
    private String[] groupId = new String[n1];
    private String[] username = new String[n2];
    private Long limit;
    private String cursor;
    private String groupName;
    private String desc;
    private Boolean isPublic;
    private Long maxUsers;
    private Boolean approval;
    private String owner;


    public EasemobChatGroupTest(int n1, int n2, String[] groupId, String[] username, Long limit, String cursor, String groupName,
                                String desc, Boolean isPublic, Long maxUsers, Boolean approval, String owner){
        this.n1 = n1;
        this.n2 = n2;
        this.groupId = groupId;
        this.username = username;
        this.limit = limit;
        this.cursor = cursor;
        this.groupName = groupName;
        this.desc = desc;
        this.isPublic = isPublic;
        this.maxUsers = maxUsers;
        this.approval = approval;
        this.owner = owner;
    }

    @Parameterized.Parameters
    public static Collection<Object[]> params() {
        return Arrays.asList(new Object[][]{
                //case 1
                {3, 3, new String[]{"261709153657947716","abc","261709403097399728"}, new String[]{"user100","user101","userno"}, 10L, null, "happy", "create", true, 300L, true, "user100"},
                //case 2
                {3, 3, new String[]{"261709403097399728","261709153657947716","261709648900391348"}, new String[]{"user100","user101","userno"}, null, null, "%happy", "create", true, 300L, true, null}
        });
    }

    @BeforeClass
    public static void besforeClass() {
        factory = ClientContext.getInstance().init(ClientContext.INIT_FROM_PROPERTIES).getAPIFactory();
        chatGroupAPI = (ChatGroupAPI) factory.newInstance(EasemobRestAPIFactory.CHATGROUP_CLASS);
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
     * Method: createChatGroup(Object payload)
     */
    @Test
    public void testCreateChatGroup() throws Exception {
        ChatGroupBody chatGroupBody = new ChatGroupBody(groupName, desc, isPublic, maxUsers, approval, owner, username);
        chatGroupAPI.createChatGroup(chatGroupBody);
    }

    /**
     * Method: getChatGroups(Long limit, String cursor)
     */
    @Test
    public void testGetChatGroups() throws Exception {
        chatGroupAPI.getChatGroups(limit, cursor);
    }

    /**
     * Method: getChatGroupDetails(String[] groupIds)
     */
    @Test
    public void testGetChatGroupDetails() throws Exception {
        chatGroupAPI.getChatGroupDetails(groupId);
    }


    /**
     * Method: modifyChatGroup(String groupId, Object payload)
     */
    @Test
    public void testModifyChatGroup() throws Exception {
        ModifyChatGroupBody modifyChatGroupBody = new ModifyChatGroupBody(groupName, desc, maxUsers);
        chatGroupAPI.modifyChatGroup(groupId[num1], modifyChatGroupBody);
    }

    /**
     * Method: deleteChatGroup(String groupId)
     */
    @Test
    public void testDeleteChatGroup() throws Exception {
        chatGroupAPI.deleteChatGroup(groupId[num1]);
    }

    /**
     * Method: getChatGroupUsers(String groupId)
     */
    @Test
    public void testGetChatGroupUsers() throws Exception {
        chatGroupAPI.getChatGroupUsers(groupId[num1]);
    }

    /**
     * Method: addSingleUserToChatGroup(String groupId, String userId)
     */
    @Test
    public void testAddSingleUserToChatGroup() throws Exception {
        chatGroupAPI.addSingleBlockUserToChatGroup(groupId[num1], username[num2]);
    }

    /**
     * Method: addBatchUsersToChatGroup(String groupId, Object payload)
     */
    @Test
    public void testAddBatchUsersToChatGroup() throws Exception {
        BodyWrapper bodyWrapper = new UserNamesBody(username);
        chatGroupAPI.addBatchBlockUsersToChatGroup(groupId[num1], bodyWrapper);
    }

    /**
     * Method: removeSingleUserFromChatGroup(String groupId, String userId)
     */
    @Test
    public void testRemoveSingleUserFromChatGroup() throws Exception {
        chatGroupAPI.removeSingleUserFromChatGroup(groupId[num1], username[num2]);
    }

    /**
     * Method: removeBatchUsersFromChatGroup(String groupId, String[] userIds)
     */
    @Test
    public void testRemoveBatchUsersFromChatGroup() throws Exception {
        chatGroupAPI.removeBatchBlockUsersFromChatGroup(groupId[num1], username);
    }

    /**
     * Method: transferChatGroupOwner(String groupId, Object payload)
     */
    @Test
    public void testTransferChatGroupOwner() throws Exception {
        BodyWrapper bodyWrapper = new GroupOwnerTransferBody(username[num2]);
        chatGroupAPI.transferChatGroupOwner(groupId[num1], bodyWrapper);
    }

    /**
     * Method: getChatGroupBlockUsers(String groupId)
     */
    @Test
    public void testGetChatGroupBlockUsers() throws Exception {
        chatGroupAPI.getChatGroupBlockUsers(groupId[num1]);
    }

    /**
     * Method: addSingleBlockUserToChatGroup(String groupId, String userId)
     */
    @Test
    public void testAddSingleBlockUserToChatGroup() throws Exception {
        chatGroupAPI.addSingleBlockUserToChatGroup(groupId[num1], username[num2]);
    }

    /**
     * Method: addBatchBlockUsersToChatGroup(String groupId, Object payload)
     */
    @Test
    public void testAddBatchBlockUsersToChatGroup() throws Exception {
        BodyWrapper bodyWrapper = new UserNamesBody(username);
        chatGroupAPI.addBatchBlockUsersToChatGroup(groupId[num1], bodyWrapper);
    }

    /**
     * Method: removeSingleBlockUserFromChatGroup(String groupId, String userId)
     */
    @Test
    public void testRemoveSingleBlockUserFromChatGroup() throws Exception {
        chatGroupAPI.removeSingleBlockUserFromChatGroup(groupId[num1], username[num2]);
    }

    /**
     * Method: removeBatchBlockUsersFromChatGroup(String groupId, String[] userIds)
     */
    @Test
    public void testRemoveBatchBlockUsersFromChatGroup() throws Exception {
        chatGroupAPI.removeBatchBlockUsersFromChatGroup(groupId[num1], username);
    }


} 
