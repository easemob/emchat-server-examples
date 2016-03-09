package com.easemob.server.example.comm.body;

import org.apache.commons.lang3.StringUtils;

import com.easemob.server.example.comm.wrapper.BodyWrapper;
import com.fasterxml.jackson.databind.node.ContainerNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;

public class IMUserBody implements BodyWrapper {
	
	private String userName;
	
	private String password;
	
	private String nickName;
	
	public IMUserBody(String userName, String password, String nickName) {
		super();
		this.userName = userName;
		this.password = password;
		this.nickName = nickName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	@Override
	public ContainerNode<?> getBody() {
		return JsonNodeFactory.instance.objectNode().put("username", userName).put("password", password).put("nickname", nickName);
	}

	@Override
	public Boolean validate() {
		return StringUtils.isNotBlank(userName) && StringUtils.isNotBlank(password);
	}

}
