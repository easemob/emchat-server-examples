package com.easemob.server.example.jersey;

import java.io.File;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.http.NameValuePair;
import org.glassfish.jersey.client.JerseyClient;
import org.glassfish.jersey.client.JerseyClientBuilder;
import org.glassfish.jersey.client.JerseyWebTarget;
import org.glassfish.jersey.media.multipart.FormDataMultiPart;
import org.glassfish.jersey.media.multipart.MultiPartFeature;
import org.glassfish.jersey.media.multipart.file.FileDataBodyPart;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;

/**
 * Jersey2.9 Utils
 * 
 * @author Lynch
 * 
 */
@SuppressWarnings("all")
public class JerseyUtils {

	private static Logger LOGGER = LoggerFactory.getLogger(EasemobChatMessage.class);

	public static String METHOD_GET = "GET";
	public static String METHOD_POST = "POST";
	public static String METHOD_PUT = "PUT";
	public static String METHOD_DELETE = "DELETE";
	public static String USER_ROLE_ORGADMIN = "orgAdmin";
	public static String USER_ROLE_APPADMIN = "appAdmin";
	public static String USER_ROLE_IMUSER = "default";

	/**
	 * Send https request whit Jersey
	 * 
	 * @return
	 */
	public static JsonNode sendRequest(String reqURL, JsonNode jsonNodeBody, String token,
			String method, List<NameValuePair> headers) throws RuntimeException {
		JsonNode jsonNode = null;

		if (!match("http(s)?://([\\w-]+\\.)+[\\w-]+(/[\\w- ./?%&=]*)?", reqURL)) {
			LOGGER.error("The URL to request is illegal");
			Map<String, Object> resultMap = new HashMap<String, Object>();
			resultMap.put("statusCode", "400");
			resultMap.put("message", "The URL to request is illegal");

			return JerseyUtils.Map2Json(resultMap);
		}

		JerseyClient jerseyClient = null;
		try {

			jerseyClient = getJerseyClient(true);

			JerseyWebTarget rootJerseyWebTarget = jerseyClient.target(reqURL);

			Invocation.Builder inBuilder = rootJerseyWebTarget.request();
			if (token != null) {
				// add headers
				inBuilder.header("Authorization", "Bearer " + token);
			}

			if (null != headers && !headers.isEmpty()) {

				for (NameValuePair nameValuePair : headers) {
					inBuilder.header(nameValuePair.getName(), nameValuePair.getValue());
				}

			}

			Response response = null;
			if (JerseyUtils.METHOD_GET.equals(method)) {

				jsonNode = inBuilder.get(JsonNode.class);

			} else if (JerseyUtils.METHOD_POST.equals(method)) {

				jsonNode = inBuilder.post(Entity.entity(jsonNodeBody, MediaType.APPLICATION_JSON),
						JsonNode.class);

			} else if (JerseyUtils.METHOD_PUT.equals(method)) {

				jsonNode = inBuilder.put(Entity.entity(jsonNodeBody, MediaType.APPLICATION_JSON),
						JsonNode.class);

			} else if (JerseyUtils.METHOD_DELETE.equals(method)) {

				jsonNode = inBuilder.delete(JsonNode.class);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return jsonNode;
	}

	/**
	 * Send https request whit Jersey
	 * 
	 * @return
	 */
	public static JsonNode sendRequestObject(String reqURL, File file, String token, String method,
			List<NameValuePair> headers) throws RuntimeException {
		JsonNode jsonNode = null;

		if (!match("http(s)?://([\\w-]+\\.)+[\\w-]+(/[\\w- ./?%&=]*)?", reqURL)) {
			LOGGER.error("The URL to request is illegal");
			Map<String, Object> resultMap = new HashMap<String, Object>();
			resultMap.put("statusCode", "400");
			resultMap.put("message", "The URL to request is illegal");

			return JerseyUtils.Map2Json(resultMap);
		}

		JerseyClient jerseyClient = null;
		Response response = null;
		try {
			JerseyWebTarget rootJerseyWebTarget = getJerseyClient(true).target(reqURL);

			Invocation.Builder inBuilder = rootJerseyWebTarget.request();
			if (token != null) {
				// add authorization str
				inBuilder.header("Authorization", "Bearer " + token);
			}

			if (null != headers && !headers.isEmpty()) {

				for (NameValuePair nameValuePair : headers) {
					inBuilder.header(nameValuePair.getName(), nameValuePair.getValue());
				}

			}

			if (JerseyUtils.METHOD_GET.equals(method)) {

				jsonNode = inBuilder.get(JsonNode.class);

			} else if (JerseyUtils.METHOD_POST.equals(method)) {
				FormDataMultiPart multiPart = new FormDataMultiPart();
				multiPart.bodyPart(new FileDataBodyPart("file", file,
						MediaType.APPLICATION_OCTET_STREAM_TYPE));

				jsonNode = inBuilder.post(Entity.entity(multiPart, MediaType.MULTIPART_FORM_DATA),
						JsonNode.class);

			} else if (JerseyUtils.METHOD_PUT.equals(method)) {

				// resultMap = inBuilder
				// .put(Entity.entity(obj, MediaType.APPLICATION_JSON), Map.class);

			} else if (JerseyUtils.METHOD_DELETE.equals(method)) {

				jsonNode = inBuilder.delete(JsonNode.class);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return jsonNode;
	}

	/**
	 * Convert JsonNode to jsonMap
	 * 
	 * @param jsonText
	 * @return
	 */
	public static Map<String, Object> Json2Map(JsonNode jsonText) {

		Map<String, Object> objMap = new HashMap<String, Object>();

		try {

			ObjectMapper mapper = new ObjectMapper();

			objMap = mapper.convertValue(jsonText, Map.class);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return objMap;
	}

	/**
	 * Convert jsonMap to JsonNode
	 * 
	 * @param jsonMap
	 * @return
	 */
	public static JsonNode Map2Json(Map<String, Object> jsonMap) {

		JsonNode jsonNode = null;
		try {

			ObjectMapper mapper = new ObjectMapper();

			jsonNode = mapper.convertValue(jsonMap, JsonNode.class);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return jsonNode;
	}

	/**
	 * Get entities from companies without "uuid", "type", "created", "modified"
	 * 
	 * @param jsonMap
	 * @return
	 */
	public static ArrayNode getEntitiesFromCompanies(JsonNode jsonNode) {

		ObjectMapper mapper = new ObjectMapper();
		ArrayNode arrayNode = mapper.createArrayNode();

		try {
			JsonNode entitiesJsonNode = jsonNode.get("entities");

			for (JsonNode jsonNode2 : entitiesJsonNode) {
				ObjectNode objectNode = (ObjectNode) jsonNode2;
				// 这四个属性移除掉
				objectNode.remove("uuid");
				objectNode.remove("type");
				objectNode.remove("created");
				objectNode.remove("modified");
				arrayNode.add(objectNode);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return arrayNode;
	}

	/**
	 * Check illegal String
	 * 
	 * @param regex
	 * @param str
	 * @return
	 */
	public static boolean match(String regex, String str) {
		Pattern pattern = Pattern.compile(regex);
		Matcher matcher = pattern.matcher(str);
		return matcher.lookingAt();
	}

	/**
	 * Obtain a JerseyClient whit SSL
	 * 
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws KeyManagementException
	 */
	private static JerseyClient getJerseyClient(boolean isSSL) throws NoSuchAlgorithmException,
			KeyManagementException {
		ClientBuilder clientBuilder = JerseyClientBuilder.newBuilder().register(
				MultiPartFeature.class);

		// Create a secure JerseyClient
		if (isSSL) {

			HostnameVerifier verifier = new HostnameVerifier() {
				@Override
				public boolean verify(String hostname, SSLSession session) {
					return true;
				}
			};

			// KeyManagerFactory kmf = KeyManagerFactory.getInstance("SunX509");

			TrustManager[] tm = new TrustManager[] { new X509TrustManager() {

				@Override
				public X509Certificate[] getAcceptedIssuers() {
					return null;
				}

				@Override
				public void checkServerTrusted(X509Certificate[] chain, String authType)
						throws CertificateException {
				}

				@Override
				public void checkClientTrusted(X509Certificate[] chain, String authType)
						throws CertificateException {
				}
			} };

			SSLContext sslContext = sslContext = SSLContext.getInstance("SSL");

			// sslContext.init(kmf.getKeyManagers(), tm, new SecureRandom());
			sslContext.init(null, tm, new SecureRandom());

			clientBuilder.sslContext(sslContext).hostnameVerifier(verifier);
		}

		return (JerseyClient) clientBuilder.build().register(JacksonJsonProvider.class);
	}
}
