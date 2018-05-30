package com.easemob;

import com.easemob.server.example.api.impl.EasemobChatGroup;
import io.swagger.client.model.*;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by easemob on 2017/3/22.
 * API 文档地址：http://docs.easemob.com/im/100serverintegration/60groupmgmt
 */
public class ChatGroupTest {
    private EasemobChatGroup easemobChatGroup = new EasemobChatGroup();
    private static final Logger logger = LoggerFactory.getLogger(ChatGroupTest.class);

    /**
     * 分页获取 APP 下的群组
     */
    @Test
    public void getChatGroups() {
        Long limit = 5L;
        String cursor = "";
        Object result = easemobChatGroup.getChatGroups(limit, cursor);
        logger.info(result.toString());
    }

    /**
     * 获取群组详情
     */
    @Test
    public void getGroupsInfo() {
        String[] grousIds = new String[2];
        grousIds[0] = "49985791000577";
        grousIds[1] = "49985853915137";
        Object result = easemobChatGroup.getChatGroupDetails(grousIds);
        logger.info(result.toString());
    }

    /**
     * 创建一个群组
     */
    @Test
    public void createGroup() {
        Group group = new Group();
        group.groupname("groupA").desc("a new group")._public(true).maxusers(50).approval(false).owner("easemob_test_a_004");
        Object result = easemobChatGroup.createChatGroup(group);
        logger.info(result.toString());
    }

    /**
     * 修改群组信息
     */
    @Test
    public void changeGroupInfo() {
        ModifyGroup group = new ModifyGroup();
        String groupId = "49985853915137";
        group.description("change group info").groupname("changed group").maxusers(300);
        Object result = easemobChatGroup.modifyChatGroup(groupId, group);
        logger.info(result.toString());
    }

    /**
     * 分页获取群组成员
     */
    @Test
    public void getUsersFromGroup() {
        String groupId = "49985853915137";
        Object result = easemobChatGroup.getChatGroupUsers(groupId);
        logger.info(result.toString());
    }

    /**
     * 添加群组成员[批量]
     */
    @Test
    public void addUsers() {
        String groupId = "49985853915137";
        UserNames userNames = new UserNames();
        UserName userList = new UserName();
        userList.add("easemob_test_b_004");
        userList.add("easemob_test_b_003");
        userNames.usernames(userList);
        Object result = easemobChatGroup.addBatchUsersToChatGroup(groupId, userNames);
        logger.info(result.toString());
    }

    /**
     * 移除群组成员[批量]
     */
    @Test
    public void removeUsersFromGroup() {
        String groupId = "49985853915137";
        String[] userIds = new String[2];
        userIds[0] = "easemob_test_b_004";
        userIds[1] = "easemob_test_b_003";
        Object result = easemobChatGroup.removeBatchUsersFromChatGroup(groupId, userIds);
        logger.info(result.toString());
    }

    /**
     * 转让群组
     */
    @Test
    public void transferGroupOwner() {
        String groupId = "49985853915137";
        NewOwner newOwner = new NewOwner();
        newOwner.newowner("easemob_test_b_004");
        Object result = easemobChatGroup.transferChatGroupOwner(groupId, newOwner);
        logger.info(result.toString());
    }

    /**
     * 查询群组黑名单
     */
    @Test
    public void getChatGroupBlockUsers(){
        String groupId = "49985853915137";
        Object result = easemobChatGroup.getChatGroupBlockUsers(groupId);
        logger.info(result.toString());
    }

    /**
     * 添加用户至群组黑名单
     */
    @Test
    public void addBlockUsers() {
        String groupId = "49985853915137";
        UserNames userNames = new UserNames();
        UserName userList = new UserName();
        userList.add("easemob_test_b_004");
        userList.add("easemob_test_b_003");
        userNames.usernames(userList);
        Object result = easemobChatGroup.addBatchBlockUsersToChatGroup(groupId, userNames);
        logger.info(result.toString());
    }

    /**
     * 从群组黑名单移除用户
     */
    @Test
    public void removeBlockUser() {
        String groupId = "49985853915137";
        String[] userIds = new String[2];
        userIds[0] = "easemob_test_b_004";
        userIds[1] = "easemob_test_b_003";
        Object result = easemobChatGroup.removeBatchBlockUsersFromChatGroup(groupId, userIds);
        logger.info(result.toString());
    }
}
