package com.easemob.server.example.jersey.vo;

import org.apache.commons.lang3.StringUtils;

import com.easemob.server.example.comm.MsgType;

public class VideoMessageBody extends MessageBody {
	private String url;
	private String filename;
	private String secret;
	private Long length;
	private Long fileLength;
	private String thumb;
	private String thumbSecret;
	
	public VideoMessageBody(String url, String filename, String secret, Long length, Long fileLength,
			String thumb, String thumbSecret) {
		super();
		this.url = url;
		this.filename = filename;
		this.secret = secret;
		this.length = length;
		this.fileLength = fileLength;
		this.thumb = thumb;
		this.thumbSecret = thumbSecret;
		
		this.getMsgBody().put("type", MsgType.VIDEO);
		if( StringUtils.isNoneBlank(url) ) {
			this.getMsgBody().put("url", url);
		}
		if( StringUtils.isNoneBlank(filename) ) {
			this.getMsgBody().put("url", filename);
		}
		if( StringUtils.isNoneBlank(secret) ) {
			this.getMsgBody().put("url", secret);
		}
		if( null != length ) {
			this.getMsgBody().put("length", length);
		}
		if( null != fileLength ) {
			this.getMsgBody().put("file_length", fileLength);
		}
		if( StringUtils.isNoneBlank(thumb) ) {
			this.getMsgBody().put("thumb", thumb);
		}
		if( StringUtils.isNoneBlank(thumbSecret) ) {
			this.getMsgBody().put("thumb_secret", thumbSecret);
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

	public Long getFileLength() {
		return fileLength;
	}

	public String getThumb() {
		return thumb;
	}

	public String getThumbSecret() {
		return thumbSecret;
	}
	
	
}
