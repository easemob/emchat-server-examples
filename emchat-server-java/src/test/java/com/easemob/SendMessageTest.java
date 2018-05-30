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
 * API 地址：http://docs.easemob.com/im/100serverintegration/50messages
 */
public class SendMessageTest {
    private EasemobSendMessage easemobSendMessage = new EasemobSendMessage();

    /**
     * 发送文本消息
     */
    @Test
    public void sendText() {
        Msg msg = new Msg();
        MsgContent msgContent = new MsgContent();
        msgContent.type(MsgContent.TypeEnum.TXT).msg("helloword");
        UserName userName = new UserName();
        userName.add("easemob_test_a_004");
        Map<String,Object> ext = new HashMap<>();
        ext.put("test_key","test_value");
        msg.from("easemob_test_b_004").target(userName).targetType("users").msg(msgContent).ext(ext);
        System.out.println(new GsonBuilder().create().toJson(msg));
        Object result = easemobSendMessage.sendMessage(msg);
        System.out.println(result);
    }

    /**
     * 发送图片消息
     */
    @Test
    public void sendImage() {
        Msg msg = new Msg();
        FileMsgContent msgContent = new FileMsgContent();
        msgContent.url("http://a1.easemob.com/1122161011178276/testapp/chatfiles/bd825310-5cde-11e8-b1f2-5f70c14699e0").secret("vYJTGlzeEei-kpPGPscCt7pY9PB4ipz04rs8o9uqgh3jzlGt").filename("filename.jpg").size(new FileMsgContent.Size(480, 720))
                .type(MsgContent.TypeEnum.IMG);
        UserName userName = new UserName();
        userName.add("easemob_test_a_004");
        msg.from("easemob_test_b_004").target(userName).targetType("users").msg(msgContent);
        System.out.println(new GsonBuilder().create().toJson(msg));
        Object result = easemobSendMessage.sendMessage(msg);
        System.out.println(result);
    }

    /**
     * 发送语音消息
     */
    @Test
    public void sendAudio() {
        Msg msg = new Msg();
        FileMsgContent msgContent = new FileMsgContent();
        msgContent.url("https://a1.easemob.com/1122161011178276/testapp/chatfiles/0e9656e0-5e6d-11e8-b447-33a9eeb51e5a").secret("DpZW6l5tEeiw16_MfaWGq2m486jTAjjJOVB1ZbqfbwyvkKc5").file_length(8678).length(6).filename("filename.amr").type(MsgContent.TypeEnum.AUDIO);
        UserName userName = new UserName();
        userName.add("easemob_test_a_004");
        msg.from("easemob_test_b_004").target(userName).targetType("users").msg(msgContent);
        System.out.println(new GsonBuilder().create().toJson(msg));
        Object result = easemobSendMessage.sendMessage(msg);
        System.out.println(result);
    }

    /**
     * 发送视频消息
     */
    @Test
    public void sendVideo() {
        Msg msg = new Msg();
        FileMsgContent msgContent = new FileMsgContent();
        msgContent.url("http://a1.easemob.com/1122161011178276/testapp/chatfiles/bd825310-5cde-11e8-b1f2-5f70c14699e0").secret("vYJTGlzeEei-kpPGPscCt7pY9PB4ipz04rs8o9uqgh3jzlGt").thumb("http://a1.easemob.com/1122161011178276/testapp/chatfiles/bd825310-5cde-11e8-b1f2-5f70c14699e0").thumb_secret("vYJTGlzeEei-kpPGPscCt7pY9PB4ipz04rs8o9uqgh3jzlGt").file_length(58103).length(5).filename("filename.mp4").type(MsgContent.TypeEnum.VIDEO);
        UserName userName = new UserName();
        userName.add("easemob_test_a_004");
        msg.from("easemob_test_b_004").target(userName).targetType("users").msg(msgContent);
        System.out.println(new GsonBuilder().create().toJson(msg));
        Object result = easemobSendMessage.sendMessage(msg);
        System.out.println(result);
    }

    /**
     * 发送透传消息
     */
    @Test
    public void sendCmd() {
        Msg msg = new Msg();
        MsgContent msgContent = new MsgContent().type(MsgContent.TypeEnum.CMD);
        UserName userName = new UserName();
        userName.add("easemob_test_a_004");
        msg.from("easemob_test_b_004").target(userName).targetType("users").action("action").msg(msgContent);
        System.out.println(new GsonBuilder().create().toJson(msg));
        Object result = easemobSendMessage.sendMessage(msg);
        System.out.println(result);
    }

    /**
     * 发送文本消息带扩展
     */
    @Test
    public void sendTextWhitExt() {
        Msg msg = new Msg();
        MsgContent msgContent = new MsgContent();
        msgContent.type(MsgContent.TypeEnum.TXT).msg("扩展消息");
        UserName userName = new UserName();
        userName.add("easemob_test_a_004");
        Map<String,Object> ext = new HashMap<>();
        ext.put("test_key","test_value");
        msg.from("easemob_test_b_004").target(userName).targetType("users").msg(msgContent).ext(ext);
        System.out.println(new GsonBuilder().create().toJson(msg));
        Object result = easemobSendMessage.sendMessage(msg);
        System.out.println(result);
    }

    static class FileMsgContent extends MsgContent {
        private String url;
        private String filename;
        private String secret;
        private Size size;
        private long length;
        private String thumb;
        private long file_length;
        private String thumb_secret;

        FileMsgContent url(String url) {
            this.url = url;
            return this;
        }

        FileMsgContent filename(String filename) {
            this.filename = filename;
            return this;
        }

        FileMsgContent secret(String secret) {
            this.secret = secret;
            return this;
        }

        FileMsgContent size(Size size) {
            this.size = size;
            return this;
        }

        FileMsgContent length(long length) {
            this.length = length;
            return this;
        }

        FileMsgContent thumb(String thumb) {
            this.thumb = thumb;
            return this;
        }

        FileMsgContent file_length(long file_length) {
            this.file_length = file_length;
            return this;
        }

        FileMsgContent thumb_secret(String thumb_secret) {
            this.thumb_secret = thumb_secret;
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
