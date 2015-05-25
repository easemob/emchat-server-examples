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
 * ClientSecretCredential
 * 
 * @author Lynch 2014-09-15
 *
 */
public class ClientSecretCredential extends Credential {

	private static JerseyWebTarget CLIENT_TOKEN_TARGET = null;
    private static final String  GRANT_TYPE = "client_credentials";

	public ClientSecretCredential(String clientID, String clientSecret, String role) {
		super(clientID, clientSecret);

		if (role.equals(Roles.USER_ROLE_APPADMIN)) {
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
				objectNode.put("grant_type", GRANT_TYPE);
				objectNode.put("client_id", tokenKey1);
				objectNode.put("client_secret", tokenKey2);
				List<NameValuePair> headers = new ArrayList<NameValuePair>();
				headers.add(new BasicNameValuePair("Content-Type", "application/json"));

				ObjectNode tokenRequest = JerseyUtils.sendRequest(getTokenRequestTarget(), objectNode, null,
						HTTPMethod.METHOD_POST, headers);

				if (null != tokenRequest.get("error")) {
                    throw new RuntimeException("Some errors occurred while fetching a token by " +
                            "grant_type[" + GRANT_TYPE + "] client_id[" + tokenKey1 + "]" +
                            " and client_secret[" + tokenKey2 + "] .");
				}

				String accessToken = tokenRequest.get("access_token").asText();

				Long expiredAt = System.currentTimeMillis() + tokenRequest.get("expires_in").asLong() * 1000;

				token = new Token(accessToken, expiredAt);
			} catch (Exception e) {
                throw new RuntimeException("Some errors occurred while fetching a token by " +
                        "grant_type[" + GRANT_TYPE + "] client_id[" + tokenKey1 + "]" +
                        " and client_secret[" + tokenKey2 + "] .");
            }
		}

		return token;
	}
}
