package com.easemob.server.example.jersey.vo;

import org.apache.commons.lang3.StringUtils;

import com.easemob.server.example.comm.MsgType;

public class AudioMessageBody extends MessageBody {
	private String url;
	private String filename;
	private String secret;
	private Long length;

	public AudioMessageBody(String url, String filename, String secret, Long length) {
		super();
		this.url = url;
		this.filename = filename;
		this.secret = secret;
		this.length = length;

		this.getMsgBody().put("type", MsgType.AUDIO);
		if (StringUtils.isNoneBlank(url)) {
			this.getMsgBody().put("url", url);
		}
		if (StringUtils.isNoneBlank(filename)) {
			this.getMsgBody().put("url", filename);
		}
		if (StringUtils.isNoneBlank(secret)) {
			this.getMsgBody().put("url", secret);
		}
		if (null != length) {
			this.getMsgBody().put("length", length);
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

	public Long getLength() {
		return length;
	}

}
