package com.easemob;

import com.easemob.server.example.api.impl.EasemobChatGroup;
import io.swagger.client.model.*;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by easemob on 2017/3/22.
 */
public class ChatGroupTest {
    private EasemobChatGroup easemobChatGroup = new EasemobChatGroup();
    private static final Logger logger = LoggerFactory.getLogger(ChatGroupTest.class);

    @Test
    public void getChatGroups() {
        Long limit = 5L;
        String cursor = "";
        Object result = easemobChatGroup.getChatGroups(limit, cursor);
        logger.info(result.toString());
    }

    @Test
    public void getGroupsInfo() {
        String[] grousIds = new String[2];
        grousIds[0] = "49985791000577";
        grousIds[1] = "49985853915137";
        Object result = easemobChatGroup.getChatGroupDetails(grousIds);
        logger.info(result.toString());
    }

    @Test
    public void createGroup() {
        Group group = new Group();
        group.groupname("groupA").desc("a new group")._public(true).maxusers(50).approval(false).owner("easemob_test_a_004");
        Object result = easemobChatGroup.createChatGroup(group);
        logger.info(result.toString());
    }

    @Test
    public void changeGroupInfo() {
        ModifyGroup group = new ModifyGroup();
        String groupId = "49985853915137";
        group.description("change group info").groupname("changed group").maxusers(300);
        Object result = easemobChatGroup.modifyChatGroup(groupId, group);
        logger.info(result.toString());
    }

    @Test
    public void getUsersFromGroup() {
        String groupId = "49985853915137";
        Object result = easemobChatGroup.getChatGroupUsers(groupId);
        logger.info(result.toString());
    }

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

    @Test
    public void removeUsersFromGroup() {
        String groupId = "49985853915137";
        String[] userIds = new String[2];
        userIds[0] = "easemob_test_b_004";
        userIds[1] = "easemob_test_b_003";
        Object result = easemobChatGroup.removeBatchUsersFromChatGroup(groupId, userIds);
        logger.info(result.toString());
    }

    @Test
    public void transferGroupOwner() {
        String groupId = "49985853915137";
        NewOwner newOwner = new NewOwner();
        newOwner.newowner("easemob_test_b_004");
        Object result = easemobChatGroup.transferChatGroupOwner(groupId, newOwner);
        logger.info(result.toString());
    }

    @Test
    public void getChatGroupBlockUsers(){
        String groupId = "49985853915137";
        Object result = easemobChatGroup.getChatGroupBlockUsers(groupId);
        logger.info(result.toString());
    }

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
