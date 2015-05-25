package com.easemob.server.example.httpclient.vo;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.easemob.server.example.comm.Roles;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * ClientSecretCredential
 * 
 * @author Lynch 2014-09-15
 *
 */
public class ClientSecretCredential extends Credential {

	private static final Logger LOGGER = LoggerFactory.getLogger(ClientSecretCredential.class);

	private static URL CLIENTSECRETCREDENTAIL_TOKEN_URL = null;

	@Override
	protected URL getUrl() {
		return CLIENTSECRETCREDENTAIL_TOKEN_URL;
	}

	public ClientSecretCredential(String clientID, String clientSecret, String role) {
		super(clientID, clientSecret);

		if (role.equals(Roles.USER_ROLE_APPADMIN)) {
			CLIENTSECRETCREDENTAIL_TOKEN_URL = EndPoints.TOKEN_APP_URL;
		}
	}

	@Override
	protected GrantType getGrantType() {
		return GrantType.CLIENT_CREDENTIALS;
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

				HttpPost httpPost = new HttpPost();
				httpPost.setURI(CLIENTSECRETCREDENTAIL_TOKEN_URL.toURI());

                for (NameValuePair nameValuePair : headers) {
                    httpPost.addHeader(nameValuePair.getName(), nameValuePair.getValue());
                }
				httpPost.setEntity(new StringEntity(objectNode.toString(), "UTF-8"));

				HttpResponse tokenResponse = client.execute(httpPost);
				HttpEntity entity = tokenResponse.getEntity();

				String results = EntityUtils.toString(entity, "UTF-8");

				LOGGER.info("-----------------------------返回结果-------------------------------statuscode:"
						+ tokenResponse.getStatusLine().toString());

				if (tokenResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {

					ObjectMapper mapper = new ObjectMapper();

					JsonFactory factory = mapper.getJsonFactory();
					JsonParser jp = factory.createJsonParser(results);
					JsonNode json = mapper.readTree(jp);

					String accessToken = json.get("access_token").asText();
					Long expiredAt = System.currentTimeMillis() + json.get("expires_in").asLong() * 1000;

					token = new Token(accessToken, expiredAt);
				}
			} catch (Exception e) {
				throw new RuntimeException("Some errors occurred while fetching a token by username and password .");
			}
		}

		return token;
	}

}
