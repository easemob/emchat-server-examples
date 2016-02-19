package com.easemob.server.example.jersey.vo;

import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

public abstract class MessageBody {
	private ObjectNode msgBody = JsonNodeFactory.instance.objectNode();

	public ObjectNode getMsgBody() {
		return msgBody;
	}
}
