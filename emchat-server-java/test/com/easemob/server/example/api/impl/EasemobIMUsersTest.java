package com.easemob.server.example.api.impl;

import com.easemob.server.example.api.IMUserAPI;
import com.easemob.server.example.comm.ClientContext;
import com.easemob.server.example.comm.EasemobRestAPIFactory;
import com.easemob.server.example.comm.body.*;
import com.easemob.server.example.comm.wrapper.BodyWrapper;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.*;

/**
 * EasemobIMUsers Tester.
 *
 * @author Aily
 * @version 1.0
 * @since <pre>2016.11.02</pre>
 */
@RunWith(Parameterized.class)
public class EasemobIMUsersTest {
    private static EasemobRestAPIFactory factory;
    private static IMUserAPI user;
    private int n = 5;
    private int num;
    private String[] username = new String[n];
    private String[] password = new String[4];
    private String[] nickName = new String[4];
    private Long[] limit = new Long[2];
    private String[] cursor = new String[2];
    private String msgid;

    @Parameterized.Parameters
    public static Collection<Object[]> params() {
        return Arrays.asList(new Object[][]{
                //case 1 {username[n], password[3], nickName[3], limit[2], cursor[2], msgid}
                {new String[]{"user001", "user002", "user003", "user004", "user005"}, new String[]{"123", "a1.", "1d'", ""}, new String[]{"Aily", "*", "_", ""},new Long[]{3L, 0L}, new String[]{"", "123"}, "12121"},
                //case 2
                {new String[]{"user001", "user002", "user003", "user004", "user005"}, new String[]{"123", "a1.", "1d'", ".;;"}, new String[]{"Aily", "*", "_", ""},new Long[]{null, 1000L}, new String[]{"", "123"}, "@12121"},
        });
    }


    public EasemobIMUsersTest(String[] username, String[] password, String[] nickName,
                              Long[] limit, String[] cursor, String msgid) {
        this.username = username;
        this.password = password;
        this.nickName = nickName;
        this.limit = limit;
        this.cursor = cursor;
        this.msgid = msgid;
    }

    @BeforeClass
    public static void beforeClass() throws Exception {
        factory = ClientContext.getInstance().init(ClientContext.INIT_FROM_PROPERTIES).getAPIFactory();
        user = (IMUserAPI) factory.newInstance(EasemobRestAPIFactory.USER_CLASS);
    }

    @Before
    public void before() throws Exception {
        this.num = new Random().nextInt(n);
    }

    @After
    public void after() throws Exception {
    }

    @AfterClass
    public static void afterClass() throws Exception {

    }

    /**
     * Method: createNewIMUserSingle(Object payload)
     */
    @Test
    public void testCreateNewIMUserSingle() throws Exception {
        BodyWrapper userBody = new IMUserBody(username[num], password[0], nickName[0]);
        user.createNewIMUserBatch(userBody);
    }

    /**
     * Method: createNewIMUserBatch(Object payload)
     */
    @Test
    public void testCreateNewIMUserBatch() throws Exception {
        List<IMUserBody> users = new ArrayList<>();
        users.add(new IMUserBody(username[num], password[1], nickName[1]));
        users.add(new IMUserBody(username[num], password[2], nickName[2]));
        BodyWrapper usersBody = new IMUsersBody(users);
        user.createNewIMUserBatch(usersBody);
    }

    /**
     * Method: getIMUsersByUserName(String userName)
     */
    @Test
    public void testGetIMUserByUserName() throws Exception {
        user.getIMUserByUserName(username[num]);
    }

    /**
     * Method: getIMUsersBatch(Long limit, String cursor)
     */
    @Test
    public void testGetIMUsersBatch() throws Exception {
        user.getIMUsersBatch(limit[0], cursor[0]);
    }

    /**
     * Method: deleteIMUserByUserName(String userName)
     */
    @Test
    public void testDeleteIMUserByUserName() throws Exception {
        user.deleteIMUserByUserName(username[num]);
    }

    /**
     * Method: deleteIMUserBatch(Long limit)
     */
    @Test
    public void testDeleteIMUserBatch() throws Exception {
        user.deleteIMUserBatch(limit[1], cursor[1]);
    }

    /**
     * Method: modifyIMUserPasswordWithAdminToken(String userName, Object payload)
     */
    @Test
    public void testModifyIMUserPasswordWithAdminToken() throws Exception {
        BodyWrapper resetPasswordBody = new ResetPasswordBody(password[3]);
        user.modifyIMUserPasswordWithAdminToken(username[num], resetPasswordBody);
    }

    /**
     * Method: modifyIMUserNickNameWithAdminToken(String userName, Object payload)
     */
    @Test
    public void testModifyIMUserNickNameWithAdminToken() throws Exception {
        BodyWrapper modifyNicknameBody = new ModifyNicknameBody(nickName[3]);
        user.modifyIMUserNickNameWithAdminToken(username[num], modifyNicknameBody);
    }

    /**
     * Method: addFriendSingle(String userName, String friendName)
     */
    @Test
    public void testAddFriendSingle() throws Exception {
        user.addFriendSingle(username[num], username[num]);
    }

    /**
     * Method: deleteFriendSingle(String userName, String friendName)
     */
    @Test
    public void testDeleteFriendSingle() throws Exception {
        user.deleteFriendSingle(username[num], username[num]);
    }

    /**
     * Method: getFriends(String userName)
     */
    @Test
    public void testGetFriends() throws Exception {
        user.getFriends(username[num]);
    }

    /**
     * Method: getBlackList(String userName)
     */
    @Test
    public void testGetBlackList() throws Exception {
        user.getBlackList(username[num]);
    }

    /**
     * Method: addToBlackList(String userName, Object payload)
     */
    @Test
    public void testAddToBlackList() throws Exception {
        String[] usernames = {username[num], username[num]};
        BodyWrapper usernameBody = new UserNamesBody(usernames);
        user.addToBlackList(username[num], usernameBody);
    }

    /**
     * Method: removeFromBlackList(String userName, String blackListName)
     */
    @Test
    public void testRemoveFromBlackList() throws Exception {
        user.removeFromBlackList(username[num], username[num]);
    }

    /**
     * Method: getIMUserStatus(String userName)
     */
    @Test
    public void testGetIMUserStatus() throws Exception {
        user.getIMUserStatus(username[num]);
    }

    /**
     * Method: getOfflineMsgCount(String userName)
     */
    @Test
    public void testGetOfflineMsgCount() throws Exception {
        user.getOfflineMsgCount(username[num]);
    }

    /**
     * Method: getSpecifiedOfflineMsgStatus(String userName, String msgId)
     */
    @Test
    public void testGetSpecifiedOfflineMsgStatus() throws Exception {
        user.getSpecifiedOfflineMsgStatus(username[num], msgid);
    }

    /**
     * Method: deactivateIMUser(String userName)
     */
    @Test
    public void testDeactivateIMUser() throws Exception {
        user.deactivateIMUser(username[num]);
    }

    /**
     * Method: activateIMUser(String userName)
     */
    @Test
    public void testActivateIMUser() throws Exception {
        user.activateIMUser(username[num]);
    }

    /**
     * Method: disconnectIMUser(String userName)
     */
    @Test
    public void testDisconnectIMUser() throws Exception {
        user.LogoutIMUser(username[num]);
    }

    /**
     * Method: getIMUserAllChatGroups(String userName)
     */
    @Test
    public void testGetIMUserAllChatGroups() throws Exception {
        user.getIMUserAllChatGroups(username[num]);
    }

    /**
     * Method: getIMUserAllChatRooms(String userName)
     */
    @Test
    public void testGetIMUserAllChatRooms() throws Exception {
        user.getIMUserAllChatGroups(username[num]);
    }

} 
