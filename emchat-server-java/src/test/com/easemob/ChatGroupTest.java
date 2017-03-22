package com.easemob;

import com.easemob.server.example.api.impl.EasemobChatGroup;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by easemob on 2017/3/22.
 */
public class ChatGroupTest {
    private EasemobChatGroup easemobChatGroup = new EasemobChatGroup();
    private Logger logger = LoggerFactory.getLogger(ChatGroupTest.class);

    @Test
    public void getChatGroups(){
        Long limit = 5L;
        String cursor = "";
        Object result = easemobChatGroup.getChatGroups(limit,cursor);

      //  logger.info(result.toString());
    }

    @Test
    public void getGroupsInfo(){
        String[] grousIds  = new String[2];
        grousIds[0] = "11189173157890";
        grousIds[1] = "259168197054300592";
        Object result = easemobChatGroup.getChatGroupDetails(grousIds);
        logger.info(result.toString());
    }
}
