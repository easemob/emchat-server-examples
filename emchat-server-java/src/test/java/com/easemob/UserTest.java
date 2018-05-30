package com.easemob;

import com.easemob.server.example.api.impl.EasemobIMUsers;
import io.swagger.client.model.*;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by easemob on 2017/3/21.
 * API 文档地址：http://docs.easemob.com/im/100serverintegration/20users
 */
public class UserTest {

    private static final Logger logger = LoggerFactory.getLogger(UserTest.class);
    private EasemobIMUsers easemobIMUsers = new EasemobIMUsers();

    /**
     * 注册 IM 用户[单个]
     */
    @Test
    public void createUserSingleTest() {
        RegisterUsers users = new RegisterUsers();
        User user = new User().username("easemob_test_a_004").password("123456");
        users.add(user);
        Object result = easemobIMUsers.createNewIMUserSingle(users);
        logger.info(result.toString());
        Assert.assertNotNull(result);
    }

    /**
     * 注册 IM 用户[批量]
     */
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

    /**
     * 获取 IM 用户[单个]
     */
    @Test
    public void getUserByNameTest() {
        String userName = "easemob_test_a_001";
        Object result = easemobIMUsers.getIMUserByUserName(userName);
        logger.info(result.toString());
    }

    /**
     * 获取 IM 用户[批量]
     */
    @Test
    public void getUsersTest() {
        Object result = easemobIMUsers.getIMUsersBatch(5L, null);
        logger.info(result.toString());
    }

    /**
     * 重置 IM 用户密码
     */
    @Test
    public void changePasswordTest() {
        String userName = "stringa";
        NewPassword psd = new NewPassword().newpassword("123");
        Object result = easemobIMUsers.modifyIMUserPasswordWithAdminToken(userName, psd);
        logger.info(result.toString());
    }

    /**
     * 修改用户推送显示昵称
     */
    @Test
    public void changeNicknameTest(){
        String userName = "easemob_test_a_001";
        Nickname nickname = new Nickname().nickname("easemob_test_a_001");
        Object result = easemobIMUsers.modifyIMUserNickNameWithAdminToken(userName, nickname);
        logger.info(result.toString());
    }

    /**
     * 给 IM 用户添加好友
     */
    @Test
    public void addFriendTest(){
        String userName = "easemob_test_a_004";
        String friendName = "easemob_test_b_004";
        Object result = easemobIMUsers.addFriendSingle(userName, friendName);
        logger.info(result.toString());
    }

    /**
     * 解除 IM 用户的好友关系
     */
    @Test
    public void deleteFriendTest(){
        String userName = "easemob_test_a_004";
        String friendName = "easemob_test_b_004";
        Object result = easemobIMUsers.deleteFriendSingle(userName, friendName);
        logger.info(result.toString());
    }

    /**
     * 获取 IM 用户的好友列表
     */
    @Test
    public void getFriendTest() {
        String userName = "easemob_test_a_004";
        Object result = easemobIMUsers.getFriends(userName);
        logger.info(result.toString());
    }

    /**
     * 获取 IM 用户的黑名单
     */
    @Test
    public void getBlackListTest(){
        String userName = "easemob_test_a_001";
        Object result = easemobIMUsers.getBlackList(userName);
        logger.info(result.toString());
    }

    /**
     * 往 IM 用户的黑名单中加人
     */
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

    /**
     * 从 IM 用户的黑名单中减人
     */
    @Test
    public void removeFromBlackListTest(){
        String userName = "easemob_test_a_001";
        String blackUserName = "easemob_test_b_003";
        Object result = easemobIMUsers.removeFromBlackList(userName, blackUserName);
        logger.info(result.toString());
    }

    /**
     * 查看用户在线状态
     */
    @Test
    public void getIMUserStatusTest(){
        String userName = "easemob_test_a_001";
        Object result = easemobIMUsers.getIMUserStatus(userName);
        logger.info(result.toString());
    }

    /**
     * 查询离线消息数
     */
    @Test
    public void getOfflineMsgCountTest(){
        String userName = "easemob_test_a_001";
        Object result = easemobIMUsers.getOfflineMsgCount(userName);
        logger.info(result.toString());
    }

    /**
     * 用户账号禁用
     */
    @Test
    public void deactivateIMUserTest(){
        String userName = "easemob_test_a_001";
        Object result = easemobIMUsers.deactivateIMUser(userName);
        logger.info(result.toString());
    }

    /**
     * 用户账号解禁
     */
    @Test
    public void activateIMUserTest(){
        String userName = "easemob_test_a_001";
        Object result = easemobIMUsers.activateIMUser(userName);
        logger.info(result.toString());
    }

    /**
     * 强制用户下线
     */
    @Test
    public void disconnectIMUserTest(){
        String userName = "easemob_test_a_001";
        Object result = easemobIMUsers.disconnectIMUser(userName);
        logger.info(result.toString());
    }

    /**
     * 获取一个用户参与的所有群组
     */
    @Test
    public void getUserJoinedChatGroups(){
        String userName = "easemob_test_a_001";
        Object result = easemobIMUsers.getIMUserAllChatGroups(userName);
        logger.info(result.toString());
    }

    /**
     * 获取一个用户参与的所有聊天室
     */
    @Test
    public void getUserJoinedChatRooms(){
        String userName = "easemob_test_a_001";
        Object result = easemobIMUsers.getIMUserAllChatRooms(userName);
        logger.info(result.toString());
    }
}
