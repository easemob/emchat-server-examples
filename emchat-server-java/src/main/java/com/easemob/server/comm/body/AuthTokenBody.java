package com.easemob.server.comm.body;

import com.easemob.server.comm.wrapper.BodyWrapper;
import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.databind.node.ContainerNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;

public class AuthTokenBody implements BodyWrapper {
	
	private String grantType = "client_credentials";
	
	private String clientId;
	
	private String clientSecret;

	public AuthTokenBody(String clientId, String clientSecret) {
		super();
		this.clientId = clientId;
		this.clientSecret = clientSecret;
	}

	public String getGrantType() {
		return grantType;
	}

	public void setGrantType(String grantType) {
		this.grantType = grantType;
	}

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public String getClientSecret() {
		return clientSecret;
	}

	public void setClientSecret(String clientSecret) {
		this.clientSecret = clientSecret;
	}

	public ContainerNode<?> getBody() {
		return JsonNodeFactory.instance.objectNode().put("grant_type", grantType).put("client_id", clientId).put("client_secret", clientSecret);
	}

	public Boolean validate() {
		return StringUtils.isNotBlank(clientId) && StringUtils.isNotBlank(clientSecret);
	}

}
