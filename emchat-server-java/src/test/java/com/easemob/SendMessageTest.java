package com.easemob;

import com.easemob.server.example.api.impl.EasemobSendMessage;
import com.google.gson.GsonBuilder;
import io.swagger.client.model.Msg;
import io.swagger.client.model.MsgContent;
import io.swagger.client.model.UserName;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by easemob on 2017/3/22.
 */
public class SendMessageTest {
    private EasemobSendMessage easemobSendMessage = new EasemobSendMessage();

    @Test
    public void sendText() {
        Msg msg = new Msg();
        MsgContent msgContent = new MsgContent();
        msgContent.type(MsgContent.TypeEnum.TXT).msg("helloword");
        UserName userName = new UserName();
        userName.add("qwqwqww");
        Map<String,Object> ext = new HashMap<>();
        ext.put("test_key","test_value");
        msg.from("stringa").target(userName).targetType("users").msg(msgContent).ext(ext);
        System.out.println(new GsonBuilder().create().toJson(msg));
        Object result = easemobSendMessage.sendMessage(msg);
        System.out.println(result);
    }

    @Test
    public void sendImage() {
        Msg msg = new Msg();
        ImageMsgContent msgContent = new ImageMsgContent();
        msgContent.url("http://test_url").secret("test_sec").filename("filename").size(new ImageMsgContent.Size(480, 720))
                .type(MsgContent.TypeEnum.IMG).msg("this is an image message");
        UserName userName = new UserName();
        userName.add("receiver");
        msg.from("sender").target(userName).targetType("users").msg(msgContent);
        System.out.println(new GsonBuilder().create().toJson(msg));
        Object result = easemobSendMessage.sendMessage(msg);
        System.out.println(result);
    }

    static class ImageMsgContent extends MsgContent {
        private String url;
        private String filename;
        private String secret;
        private Size size;

        ImageMsgContent url(String url) {
            this.url = url;
            return this;
        }

        ImageMsgContent filename(String filename) {
            this.filename = filename;
            return this;
        }

        ImageMsgContent secret(String secret) {
            this.secret = secret;
            return this;
        }

        ImageMsgContent size(Size size) {
            this.size = size;
            return this;
        }

        static class Size {
            private long width;
            private long height;

            Size(long width, long height) {
                this.width = width;
                this.height = height;
            }
        }
    }
}
