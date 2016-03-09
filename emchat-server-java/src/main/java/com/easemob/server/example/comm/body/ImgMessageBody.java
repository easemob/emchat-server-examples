package com.easemob.server.example.comm.body;

import com.fasterxml.jackson.databind.node.ContainerNode;
import org.apache.commons.lang3.StringUtils;

import com.easemob.server.example.comm.constant.MsgType;

import java.util.Map;

public class ImgMessageBody extends MessageBody {
    private String url;
    private String filename;
    private String secret;
    private Long width;
    private Long height;

    public ImgMessageBody(String targetType, String[] targets, String from, Map<String, String> ext, String url, String filename, String secret, Long width, Long height) {
        super(targetType, targets, from, ext);
        this.url = url;
        this.filename = filename;
        this.secret = secret;
        this.width = width;
        this.height = height;
    }

    public String getUrl() {
        return url;
    }

    public String getFilename() {
        return filename;
    }

    public String getSecret() {
        return secret;
    }

    public Long getWidth() {
        return width;
    }

    public Long getHeight() {
        return height;
    }

    public ContainerNode<?> getBody() {
        if(!this.isInit()) {
            this.getMsgBody().put("type", MsgType.IMG);
            this.getMsgBody().put("url", url);
            this.getMsgBody().put("filename", filename);
            this.getMsgBody().put("secret", secret);

            if (null != width && null != height) {
                this.getMsgBody().putObject("size").put("width", width).put("height", height);
            }

            this.setInit(true);
        }
        return this.getMsgBody();
    }

    public Boolean validate() {
        return super.validate() && StringUtils.isNoneBlank(url) && StringUtils.isNoneBlank(filename) && StringUtils.isNoneBlank(secret);
    }
}
