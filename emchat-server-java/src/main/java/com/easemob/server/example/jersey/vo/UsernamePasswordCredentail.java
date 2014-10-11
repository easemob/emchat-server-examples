package com.easemob.server.example.jersey.vo;

import java.util.ArrayList;
import java.util.List;

import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.core.MediaType;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.glassfish.jersey.client.JerseyWebTarget;

import com.easemob.server.example.comm.Constants;
import com.easemob.server.example.comm.Roles;
import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * UsernamePasswordCredentail
 * 
 * @author Lynch 2014-09-15
 *
 */
public class UsernamePasswordCredentail extends Credentail {

	private static JerseyWebTarget USERNAMEPASSWORD_TOKEN_TARGET = null;

	public UsernamePasswordCredentail(String username, String password, String role) {
		super(username, password);

		if (role.equals(Roles.USER_ROLE_ORGADMIN)) {
			// ORG管理员
			USERNAMEPASSWORD_TOKEN_TARGET = EndPoints.TOKEN_ORG_TARGET;
		} else if (role.equals(Roles.USER_ROLE_APPADMIN) || role.equals(Roles.USER_ROLE_IMUSER)) {
			// APP管理员、IM用户
			USERNAMEPASSWORD_TOKEN_TARGET = EndPoints.TOKEN_APP_TARGET.resolveTemplate("org_name",
					Constants.APPKEY.split("#")[0]).resolveTemplate("app_name", Constants.APPKEY.split("#")[1]);
		}
	}

	@Override
	protected GrantType getGrantType() {
		return GrantType.PASSWORD;
	}

	@Override
	protected JerseyWebTarget getTokenRequestTarget() {
		return USERNAMEPASSWORD_TOKEN_TARGET;
	}

	@Override
	public Token getToken() {
		if (null == token || token.isExpired()) {
			try {
				ObjectNode objectNode = factory.objectNode();
				objectNode.put("grant_type", "password");
				objectNode.put("username", tokenKey1);
				objectNode.put("password", tokenKey2);

				List<NameValuePair> headers = new ArrayList<NameValuePair>();
				headers.add(new BasicNameValuePair("Content-Type", "application/json"));

				Invocation.Builder inBuilder = getTokenRequestTarget().request();

				if (null != headers && !headers.isEmpty()) {

					for (NameValuePair nameValuePair : headers) {
						inBuilder.header(nameValuePair.getName(), nameValuePair.getValue());
					}

				}

				ObjectNode tokenRequest = inBuilder.post(Entity.entity(objectNode, MediaType.APPLICATION_JSON),
						ObjectNode.class);

				if (null != tokenRequest.get("error")) {
					return token;
				}

				String accessToken = tokenRequest.get("access_token").asText();
				Long expiredAt = tokenRequest.get("expires_in").asLong() + 7 * 24 * 60 * 60;

				token = new Token(accessToken, expiredAt);

			} catch (Exception e) {
				throw new RuntimeException("Some errors ocuured while fetching a token by usename and passowrd .");
			}
		}

		return token;
	}
}