package com.easemob.server.example.jersey.vo;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.glassfish.jersey.client.JerseyWebTarget;

import com.easemob.server.example.comm.Constants;
import com.easemob.server.example.comm.HTTPMethod;
import com.easemob.server.example.comm.Roles;
import com.easemob.server.example.jersey.utils.JerseyUtils;
import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * ClientSecretCredentail
 * 
 * @author Lynch 2014-09-15
 *
 */
public class ClientSecretCredentail extends Credentail {

	private static JerseyWebTarget CLIENT_TOKEN_TARGET = null;

	public ClientSecretCredentail(String clientID, String clientSecret, String role) {
		super(clientID, clientSecret);

		if (role.equals(Roles.USER_ROLE_ORGADMIN)) {
			// ORG管理员
			CLIENT_TOKEN_TARGET = EndPoints.TOKEN_ORG_TARGET;
		} else if (role.equals(Roles.USER_ROLE_APPADMIN) || role.equals(Roles.USER_ROLE_IMUSER)) {
			// APP管理员、IM用户
			CLIENT_TOKEN_TARGET = EndPoints.TOKEN_APP_TARGET
					.resolveTemplate("org_name", Constants.APPKEY.split("#")[0]).resolveTemplate("app_name",
							Constants.APPKEY.split("#")[1]);
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
				ObjectNode objectNode = factory.objectNode();
				objectNode.put("grant_type", "client_credentials");
				objectNode.put("client_id", tokenKey1);
				objectNode.put("client_secret", tokenKey2);
				List<NameValuePair> headers = new ArrayList<NameValuePair>();
				headers.add(new BasicNameValuePair("Content-Type", "application/json"));

				ObjectNode tokenRequest = JerseyUtils.sendRequest(getTokenRequestTarget(), objectNode, null,
						HTTPMethod.METHOD_POST, headers);

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
