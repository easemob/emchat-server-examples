package com.easemob;

import com.easemob.server.example.api.impl.EasemobSendMessage;
import io.swagger.client.model.Msg;
import io.swagger.client.model.MsgContent;
import io.swagger.client.model.UserName;
import org.junit.Test;

/**
 * Created by easemob on 2017/3/22.
 */
public class SendMessageTest {
    private EasemobSendMessage easemobSendMessage = new EasemobSendMessage();

    @Test
    public void send() {
        Msg msg = new Msg();
        MsgContent msgContent = new MsgContent();
        msgContent.type(MsgContent.TypeEnum.TXT).msg("helloword");
        UserName userName = new UserName();
        userName.add("qwqwqww");
        msg.from("stringa").target(userName).targetType("users").msg(msgContent);
        Object result = easemobSendMessage.sendMessage(msg);
        System.out.println(result);
    }
}
