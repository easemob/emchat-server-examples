package com.easemob.server.example.vo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.glassfish.jersey.client.JerseyWebTarget;

import com.easemob.server.example.jersey.JerseyUtils;
import com.easemob.server.example.utils.HTTPMethod;
import com.fasterxml.jackson.databind.JsonNode;

public class ClientSecretCredentail extends Credentail {

	private static JerseyWebTarget CLIENT_TOKEN_TARGET = null;

	public ClientSecretCredentail(String appKey, String clientID, String clientSecret, boolean isOrgAdmin) {
		super(clientID, clientSecret);
		if (isOrgAdmin) {
			// org管理员
			CLIENT_TOKEN_TARGET = ROOT_TOKEN_TARGET.path("management").path("token");
		} else {
			// app管理员、IM用户
			CLIENT_TOKEN_TARGET = ROOT_TOKEN_TARGET.path(appKey.replace("#", "/")).path("token");
		}
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
						JerseyUtils.Map2Json(jsonNodeBody), null, HTTPMethod.METHOD_POST, headers);

				if (null != tokenRequest.get("error")) {
					return token;
				}

				String accessToken = tokenRequest.get("access_token").asText();
				long expiredAt = tokenRequest.get("expire_in").asLong() + 7 * 24 * 60 * 60;

				token = new Token(accessToken, expiredAt);
			} catch (Exception e) {
				throw new RuntimeException("Some errors ocuured while fetching a token by usename and passowrd .");
			}
		}

		return token;
	}
}
