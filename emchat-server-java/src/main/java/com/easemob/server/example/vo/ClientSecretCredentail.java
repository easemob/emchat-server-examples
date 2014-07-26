package com.easemob.server.example.vo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.glassfish.jersey.client.JerseyWebTarget;

import com.easemob.server.example.jersey.JerseyUtils;
import com.fasterxml.jackson.databind.JsonNode;

public class ClientSecretCredentail extends Credentail {

	private static JerseyWebTarget CLIENT_TOKEN_TARGET = JerseyUtils.getJerseyClient(true).target(JerseyUtils.BASEURL)
			.path("{appKey}");

	public ClientSecretCredentail(String appKey, String clientID, String clientSecret) {
		super(clientID, clientSecret);
		CLIENT_TOKEN_TARGET = CLIENT_TOKEN_TARGET.resolveTemplate("appKey", appKey.replace("#", "/"));
	}

	@Override
	protected GrantType getGrantType() {
		return GrantType.CLIENT_CREDENTIALS;
	}

	@Override
	protected JerseyWebTarget getTokenRequestTarget() {
		return CLIENT_TOKEN_TARGET;
	}

	@Override
	public Token getToken() {
		if (null == token || token.isExpired()) {
			try {
				Map<String, Object> jsonNodeBody = new HashMap<String, Object>();
				jsonNodeBody.put("grant_type", grantType);
				jsonNodeBody.put("client_id", secretKey);
				jsonNodeBody.put("client_secret", secretValue);
				List<NameValuePair> headers = new ArrayList<NameValuePair>();
				headers.add(new BasicNameValuePair("Content-Type", "application/json"));
				JsonNode tokenRequest = JerseyUtils.sendRequestNew(getTokenRequestTarget(),
						JerseyUtils.Map2Json(jsonNodeBody), null, JerseyUtils.METHOD_POST, headers);
			} catch (Exception e) {
			}
		}

		return token;
	}

}
