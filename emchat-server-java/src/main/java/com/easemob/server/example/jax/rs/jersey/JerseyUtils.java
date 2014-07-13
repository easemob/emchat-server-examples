package com.easemob.server.example.jax.rs.jersey;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.core.MediaType;

import org.glassfish.jersey.client.ClientConfig;
import org.glassfish.jersey.client.JerseyClient;
import org.glassfish.jersey.client.JerseyClientBuilder;
import org.glassfish.jersey.client.JerseyWebTarget;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;

/**
 * Jersey2.9 Utils
 * 
 * @author Lynch
 * 
 */
@SuppressWarnings("all")
public class JerseyUtils {

	public static String Method_GET = "GET";
	public static String Method_POST = "POST";
	public static String Method_PUT = "PUT";
	public static String Method_DELETE = "DELETE";

	/**
	 * Send https request whit Jersey
	 * 
	 * @return
	 */
	public static String sendRequest(String reqURL, Map<String, Object> reqBody, String token,
			String method) {

		Map<String, Object> resultMap = new HashMap<String, Object>();

		ClientConfig clientConfig = new ClientConfig();
		JerseyClient jerseyClient = (JerseyClient) JerseyClientBuilder.newClient().register(
				JacksonJsonProvider.class);

		JerseyWebTarget rootJerseyWebTarget = jerseyClient.target(reqURL);
		Invocation.Builder inBuilder = rootJerseyWebTarget.request(MediaType.APPLICATION_JSON);

		if (token != null) {
			// add headers
			inBuilder.header("Authentication", "Bearer " + token);
		}

		if (method.equals(JerseyUtils.Method_GET)) {
			resultMap = inBuilder.get(Map.class);
		} else if (method.equals(JerseyUtils.Method_GET)) {
			resultMap = inBuilder.post(Entity.entity(reqBody, MediaType.APPLICATION_JSON),
					Map.class);
		} else if (method.equals(JerseyUtils.Method_PUT)) {
			resultMap = inBuilder
					.put(Entity.entity(reqBody, MediaType.APPLICATION_JSON), Map.class);
		} else if (method.equals(JerseyUtils.Method_DELETE)) {
			resultMap = inBuilder.delete(Map.class);
		}

		return Map2Json(resultMap);
	}

	/**
	 * Convert jsonString to jsonMap
	 * 
	 * @param jsonText
	 * @return
	 */
	public static Map<String, Object> Json2Map(String jsonText) {

		Map<String, Object> objMap = new HashMap<String, Object>();

		try {

			ObjectMapper mapper = new ObjectMapper();
			objMap = mapper.readValue(jsonText, Map.class);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return objMap;
	}

	/**
	 * Convert jsonMap to jsonString
	 * 
	 * @param jsonMap
	 * @return
	 */
	public static String Map2Json(Map<String, Object> jsonMap) {

		String jsonText = "";

		try {

			ObjectMapper mapper = new ObjectMapper();

			jsonText = mapper.writeValueAsString(jsonMap);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return jsonText;
	}

	public static void main(String[] args) {
		String reqURL = "http://a1.easemob.com/management/token";
		Map<String, Object> reqBody = new HashMap<String, Object>();
		reqBody.put("grant_type", "password");
		reqBody.put("username", "easemobdemoadmin");
		reqBody.put("password", "thepushbox");
		JerseyUtils.sendRequest(reqURL, reqBody, null, null);
	}
}
