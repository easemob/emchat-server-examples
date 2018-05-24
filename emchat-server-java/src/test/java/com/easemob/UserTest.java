package com.easemob;

import com.easemob.server.example.api.impl.EasemobIMUsers;
import io.swagger.client.model.*;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by easemob on 2017/3/21.
 */
public class UserTest {

    private static final Logger logger = LoggerFactory.getLogger(UserTest.class);
    private EasemobIMUsers easemobIMUsers = new EasemobIMUsers();

    @Test
    public void createUserSingleTest() {
        RegisterUsers users = new RegisterUsers();
        User user = new User().username("easemob_test_a_004").password("123456");
        users.add(user);
        Object result = easemobIMUsers.createNewIMUserSingle(users);
        logger.info(result.toString());
        Assert.assertNotNull(result);
    }

    @Test
    public void createUserBatchTest() {
        RegisterUsers users = new RegisterUsers();
        User user1 = new User().username("easemob_test_a_004").password("123456");
        User user2 = new User().username("easemob_test_b_004").password("123456");
        users.add(user1);
        users.add(user2);
        Object result = easemobIMUsers.createNewIMUserBatch(users);
        logger.info(result.toString());
        Assert.assertNotNull(result);
    }

    @Test
    public void getUserByNameTest() {
        String userName = "easemob_test_a_001";
        Object result = easemobIMUsers.getIMUserByUserName(userName);
        logger.info(result.toString());
    }

    @Test
    public void getUsersTest() {
        Object result = easemobIMUsers.getIMUsersBatch(5L, null);
        logger.info(result.toString());
    }

    @Test
    public void changePasswordTest() {
        String userName = "stringa";
        NewPassword psd = new NewPassword().newpassword("123");
        Object result = easemobIMUsers.modifyIMUserPasswordWithAdminToken(userName, psd);
        logger.info(result.toString());
    }

    @Test
    public void changeNicknameTest(){
        String userName = "easemob_test_a_001";
        Nickname nickname = new Nickname().nickname("easemob_test_a_001");
        Object result = easemobIMUsers.modifyIMUserNickNameWithAdminToken(userName, nickname);
        logger.info(result.toString());
    }

    @Test
    public void addFriendTest(){
        String userName = "easemob_test_a_004";
        String friendName = "easemob_test_b_004";
        Object result = easemobIMUsers.addFriendSingle(userName, friendName);
        logger.info(result.toString());
    }

    @Test
    public void deleteFriendTest(){
        String userName = "easemob_test_a_004";
        String friendName = "easemob_test_b_004";
        Object result = easemobIMUsers.deleteFriendSingle(userName, friendName);
        logger.info(result.toString());
    }

    @Test
    public void getFriendTest() {
        String userName = "easemob_test_a_004";
        Object result = easemobIMUsers.getFriends(userName);
        logger.info(result.toString());
    }

    @Test
    public void getBlackListTest(){
        String userName = "easemob_test_a_001";
        Object result = easemobIMUsers.getBlackList(userName);
        logger.info(result.toString());
    }

    @Test
    public void addToBlackListTest(){
        String userName = "easemob_test_a_001";
        UserName blackUserNames = new UserName();
        blackUserNames.add("easemob_test_b_002");
        blackUserNames.add("easemob_test_b_003");
        UserNames userNames = new UserNames().usernames(blackUserNames);
        Object result = easemobIMUsers.addToBlackList(userName, userNames);
        logger.info(result.toString());
    }

    @Test
    public void removeFromBlackListTest(){
        String userName = "easemob_test_a_001";
        String blackUserName = "easemob_test_b_003";
        Object result = easemobIMUsers.removeFromBlackList(userName, blackUserName);
        logger.info(result.toString());
    }

    @Test
    public void getIMUserStatusTest(){
        String userName = "easemob_test_a_001";
        Object result = easemobIMUsers.getIMUserStatus(userName);
        logger.info(result.toString());
    }

    @Test
    public void getOfflineMsgCountTest(){
        String userName = "easemob_test_a_001";
        Object result = easemobIMUsers.getOfflineMsgCount(userName);
        logger.info(result.toString());
    }

    @Test
    public void deactivateIMUserTest(){
        String userName = "easemob_test_a_001";
        Object result = easemobIMUsers.deactivateIMUser(userName);
        logger.info(result.toString());
    }

    @Test
    public void activateIMUserTest(){
        String userName = "easemob_test_a_001";
        Object result = easemobIMUsers.activateIMUser(userName);
        logger.info(result.toString());
    }

    @Test
    public void disconnectIMUserTest(){
        String userName = "easemob_test_a_001";
        Object result = easemobIMUsers.disconnectIMUser(userName);
        logger.info(result.toString());
    }

    @Test
    public void getUserJoinedChatGroups(){
        String userName = "easemob_test_a_001";
        Object result = easemobIMUsers.getIMUserAllChatGroups(userName);
        logger.info(result.toString());
    }

    @Test
    public void getUserJoinedChatRooms(){
        String userName = "easemob_test_a_001";
        Object result = easemobIMUsers.getIMUserAllChatRooms(userName);
        logger.info(result.toString());
    }
}
