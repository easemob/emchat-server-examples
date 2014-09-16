package com.easemob.server.example.httpclient.vo;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.easemob.server.example.comm.Roles;
import com.easemob.server.example.httpclient.utils.HTTPClientUtils;
import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * 
 * @author lynch
 *
 */
public class UsernamePasswordCredentail extends Credentail {

	private static final Logger LOGGER = LoggerFactory.getLogger(UsernamePasswordCredentail.class);

	private static URL USERNAMEPASSWORD_TOKEN_URL = null;

	public UsernamePasswordCredentail(String username, String password, String role) {
		super(username, password);
		if (role.equals(Roles.USER_ROLE_ORGADMIN)) {
			// ORG管理员
			USERNAMEPASSWORD_TOKEN_URL = EndPoints.TOKEN_ORG_URL;
		} else if (role.equals(Roles.USER_ROLE_APPADMIN) || role.equals(Roles.USER_ROLE_IMUSER)) {
			// APP管理员、IM用户
			USERNAMEPASSWORD_TOKEN_URL = EndPoints.TOKEN_APP_URL;
		}
	}

	@Override
	protected URL getUrl() {
		return USERNAMEPASSWORD_TOKEN_URL;
	}

	@Override
	protected GrantType getGrantType() {
		return GrantType.PASSWORD;
	}

	@Override
	public Token getToken() {
		if (null == token || token.isExpired()) {
			try {

				HttpClient client = HTTPClientUtils.getClient(true);

				ObjectNode objectNode = factory.objectNode();
				objectNode.put("grant_type", "password");
				objectNode.put("username", tokenKey1);
				objectNode.put("password", tokenKey2);

				HttpPost httpPost = new HttpPost();
				httpPost.setURI(USERNAMEPASSWORD_TOKEN_URL.toURI());

				List<NameValuePair> headers = new ArrayList<NameValuePair>();
				headers.add(new BasicNameValuePair("Content-Type", "application/json"));

				if (null != headers && !headers.isEmpty()) {

					for (NameValuePair nameValuePair : headers) {
						httpPost.addHeader(nameValuePair.getName(), nameValuePair.getValue());
					}

				}

				HttpResponse tokenResponse = client.execute(httpPost);
				HttpEntity entity = tokenResponse.getEntity();

				String results = EntityUtils.toString(entity, "utf-8");

				LOGGER.info("-----------------------------返回结果-------------------------------statuscode:"
						+ tokenResponse.getStatusLine().toString());

				if (tokenResponse.getStatusLine().getStatusCode() == 200) {
					ObjectNode objectNode2 = factory.objectNode();
					objectNode2 = objectNode2.putObject(results);
					String accessToken = objectNode2.get("access_token").textValue();
					Long expiredAt = objectNode2.get("expires_in").asLong() + 7 * 24 * 60 * 60;

					token = new Token(accessToken, expiredAt);
				}

			} catch (Exception e) {
				throw new RuntimeException("Some errors ocuured while fetching a token by usename and passowrd .");
			}
		}

		return token;
	}
}
