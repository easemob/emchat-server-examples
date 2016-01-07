package com.easemob.server.example.jersey.vo;

import org.apache.commons.lang3.StringUtils;

import com.easemob.server.example.comm.MsgType;

public class ImgMessageBody extends MessageBody {
	private String url;
	private String filename;
	private String secret;
	private Long width;
	private Long height;
	
	public ImgMessageBody(String url, String filename, String secret, Long width, Long height) {
		super();
		this.url = url;
		this.filename = filename;
		this.secret = secret;
		this.width = width;
		this.height = height;
		
		this.getMsgBody().put("type", MsgType.IMG);
		if( StringUtils.isNoneBlank(url) ) {
			this.getMsgBody().put("url", url);
		}
		if( StringUtils.isNoneBlank(filename) ) {
			this.getMsgBody().put("url", filename);
		}
		if( StringUtils.isNoneBlank(secret) ) {
			this.getMsgBody().put("url", secret);
		}
		if( null != width && null != height ) {
			this.getMsgBody().putObject("size").put("width", width).put("height", height);
		}
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
	
}
