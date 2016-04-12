package com.easemob.server.comm.body;

import com.fasterxml.jackson.databind.node.ContainerNode;
import org.apache.commons.lang3.StringUtils;

import com.easemob.server.comm.constant.MsgType;

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
			this.getMsgBody().put("type", MsgType.VIDEO);
			this.getMsgBody().put("url", url);
			this.getMsgBody().put("filename", filename);
			this.getMsgBody().put("secret", secret);
			this.getMsgBody().put("length", length);
			this.getMsgBody().put("file_length", fileLength);
			this.getMsgBody().put("thumb", thumb);
			this.getMsgBody().put("thumb_secret", thumbSecret);

			this.setInit(true);
		}

		return this.getMsgBody();
	}

	@Override
	public Boolean validate() {
		return super.validate() && StringUtils.isNoneBlank(url) && StringUtils.isNoneBlank(filename) && StringUtils.isNoneBlank(secret) && null != length && null != fileLength && StringUtils.isNoneBlank(thumb) && StringUtils.isNoneBlank(thumbSecret);
	}
}
