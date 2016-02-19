package com.easemob.server.example.jersey.vo;

import com.easemob.server.example.comm.MsgType;

public class TextMessageBody extends MessageBody {
	private String msg;

	public TextMessageBody(String msg) {
		super();
		this.msg = msg;
		this.getMsgBody().put("type", MsgType.TEXT);
		this.getMsgBody().put("msg", msg);
	}

	public String getMsg() {
		return msg;
	}
}
