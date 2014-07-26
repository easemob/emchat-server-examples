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

public class UsernamePasswordCredentail extends Credentail {

	private static JerseyWebTarget USERNAMEPASSWORD_TOKEN_TARGET = null;

	public UsernamePasswordCredentail(String appKey, String secretKey, String secretValue, boolean isMgmt) {
		super(secretKey, secretValue);
		if (isMgmt) {
			USERNAMEPASSWORD_TOKEN_TARGET = ROOT_TOKEN_TARGET.path("management").path("token");
		} else {
			USERNAMEPASSWORD_TOKEN_TARGET = ROOT_TOKEN_TARGET.path(appKey.replace("#", "/")).path("token");
		}
	}

	@Override
	protected GrantType getGrantType() {
		return GrantType.password;
	}

	@Override
	protected JerseyWebTarget getTokenRequestTarget() {
		return USERNAMEPASSWORD_TOKEN_TARGET;
	}

	@Override
	public Token getToken() {
		if (null == token || token.isExpired()) {
			try {
				Map<String, Object> jsonNodeBody = new HashMap<String, Object>();
				jsonNodeBody.put("grant_type", GrantType.password);
				jsonNodeBody.put("username", secretKey);
				jsonNodeBody.put("password", secretValue);

				List<NameValuePair> headers = new ArrayList<NameValuePair>();
				headers.add(new BasicNameValuePair("Content-Type", "application/json"));

				JsonNode tokenRequest = JerseyUtils.sendRequestNew(getTokenRequestTarget(),
						JerseyUtils.Map2Json(jsonNodeBody), null, JerseyUtils.METHOD_POST, headers);
				if (null != tokenRequest.get("error")) {
					return token;
				}

				String accessToken = tokenRequest.get("access_token").textValue();
				Long expiredAt = tokenRequest.get("expires_in").asLong() + 7 * 24 * 60 * 60;

				token = new Token(accessToken, expiredAt);

			} catch (Exception e) {
				throw new RuntimeException("Some errors ocuured while fetching a token by usename and passowrd .");
			}
		}
		return token;
	}

}