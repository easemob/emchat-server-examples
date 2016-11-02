package com.easemob.server.example.comm.body;

import com.fasterxml.jackson.databind.node.ContainerNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import org.apache.commons.lang3.StringUtils;

import com.easemob.server.example.comm.constant.MsgType;

import java.util.Map;

public class VideoMessageBody extends MessageBody {
	private String url;
	private String filename;
	private String secret;
	private Long length;
	private Long fileLength;
	private String thumb;
	private String thumbSecret;
	
	public VideoMessageBody(String targetType, String[] targets, String from, Map<String, String> ext, String url, String filename, String secret, Long length, Long fileLength,
							String thumb, String thumbSecret) {
		super(targetType, targets, from, ext);
		this.url = url;
		this.filename = filename;
		this.secret = secret;
		this.length = length;
		this.fileLength = fileLength;
		this.thumb = thumb;
		this.thumbSecret = thumbSecret;

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

	public Long getFileLength() {
		return fileLength;
	}

	public String getThumb() {
		return thumb;
	}

	public String getThumbSecret() {
		return thumbSecret;
	}

	public ContainerNode<?> getBody() {
		if(!this.isInit()){
			ObjectNode msg = this.getMsgBody().putObject("msg");
			msg.put("type", MsgType.VIDEO);
			msg.put("url", url);
			msg.put("filename", filename);
			msg.put("secret", secret);
			msg.put("length", length);
			msg.put("file_length", fileLength);
			msg.put("thumb", thumb);
			msg.put("thumb_secret", thumbSecret);
			this.setInit(true);
		}
		return this.getMsgBody();
	}

	@Override
	public Boolean validate() {
		return super.validate() && StringUtils.isNoneBlank(url) && StringUtils.isNoneBlank(filename) && StringUtils.isNoneBlank(secret) && null != length && null != fileLength && StringUtils.isNoneBlank(thumb) && StringUtils.isNoneBlank(thumbSecret);
	}
}
