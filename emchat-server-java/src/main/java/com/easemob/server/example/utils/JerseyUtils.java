package com.easemob.server.example.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
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

import com.easemob.server.example.vo.Credentail;
import com.easemob.server.example.vo.Token;
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

	private static Logger LOGGER = LoggerFactory.getLogger(JerseyUtils.class);

	public static String BASEURL = "https://" + PropertiesUtils.getProperties().getProperty("Server_HOST");

	/**
	 * Send HTTPS request with Jersey
	 * 
	 * @return
	 */
	public static JsonNode sendRequest(JerseyWebTarget webTarget, JsonNode jsonNodeBody, Credentail credentail,
			String method, List<NameValuePair> headers) throws RuntimeException {
		JsonNode jsonNode = null;

		if (!match("http(s)?://([\\w-]+\\.)+[\\w-]+(/[\\w- ./?%&=]*)?", webTarget.getUri().toString())) {
			LOGGER.error("The URL to request is illegal");
			Map<String, Object> resultMap = new HashMap<String, Object>();
			resultMap.put("statusCode", "400");
			resultMap.put("message", "The URL to request is illegal");

			return JerseyUtils.Map2Json(resultMap);
		}

		try {

			Invocation.Builder inBuilder = webTarget.request();
			if (credentail != null) {
				Token.applyAuthentication(inBuilder, credentail);
			}

			if (null != headers && !headers.isEmpty()) {

				for (NameValuePair nameValuePair : headers) {
					inBuilder.header(nameValuePair.getName(), nameValuePair.getValue());
				}

			}

			Response response = null;
			if (HTTPMethod.METHOD_GET.equals(method)) {

				jsonNode = inBuilder.get(JsonNode.class);

			} else if (HTTPMethod.METHOD_POST.equals(method)) {

				jsonNode = inBuilder.post(Entity.entity(jsonNodeBody, MediaType.APPLICATION_JSON), JsonNode.class);

			} else if (HTTPMethod.METHOD_PUT.equals(method)) {

				jsonNode = inBuilder.put(Entity.entity(jsonNodeBody, MediaType.APPLICATION_JSON), JsonNode.class);

			} else if (HTTPMethod.METHOD_DELETE.equals(method)) {

				jsonNode = inBuilder.delete(JsonNode.class);

			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		return jsonNode;
	}

	/**
	 * DownLoadFile whit Jersey
	 * 
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws KeyManagementException
	 * @throws IOException
	 */
	public static JsonNode downLoadFile(JerseyWebTarget jerseyWebTarget, String token, List<NameValuePair> headers,
			File localPath) throws RuntimeException, KeyManagementException, NoSuchAlgorithmException, IOException {
		JsonNode jsonNode = null;

		Invocation.Builder inBuilder = jerseyWebTarget.request();
		if (token != null) {
			// add headers
			inBuilder.header("Authorization", "Bearer " + token);
		}

		if (null != headers && !headers.isEmpty()) {

			for (NameValuePair nameValuePair : headers) {
				inBuilder.header(nameValuePair.getName(), nameValuePair.getValue());
			}

		}

		File file = inBuilder.get(File.class);
		file.renameTo(localPath);
		FileWriter fr = new FileWriter(file);
		fr.flush();

		return jsonNode;
	}

	/**
	 * UploadFile whit Jersey
	 * 
	 * @return
	 */
	public static JsonNode uploadFile(JerseyWebTarget jerseyWebTarget, File file, String token,
			List<NameValuePair> headers) throws RuntimeException {
		JsonNode jsonNode = null;

		Response response = null;
		try {

			Invocation.Builder inBuilder = jerseyWebTarget.request();
			if (token != null) {
				// add authorization str
				inBuilder.header("Authorization", "Bearer " + token);
			}

			if (null != headers && !headers.isEmpty()) {

				for (NameValuePair nameValuePair : headers) {
					inBuilder.header(nameValuePair.getName(), nameValuePair.getValue());
				}

			}

			FormDataMultiPart multiPart = new FormDataMultiPart();
			multiPart.bodyPart(new FileDataBodyPart("file", file, MediaType.APPLICATION_OCTET_STREAM_TYPE));

			jsonNode = inBuilder.post(Entity.entity(multiPart, MediaType.MULTIPART_FORM_DATA), JsonNode.class);

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

		if (null != jsonMap) {
			try {

				ObjectMapper mapper = new ObjectMapper();

				jsonNode = mapper.convertValue(jsonMap, JsonNode.class);

			} catch (Exception e) {
				e.printStackTrace();
			}
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

		if (null != jsonNode) {
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
	public static JerseyClient getJerseyClient(boolean isSSL) {
		ClientBuilder clientBuilder = JerseyClientBuilder.newBuilder().register(MultiPartFeature.class);

		// Create a secure JerseyClient
		if (isSSL) {
			try {
				HostnameVerifier verifier = new HostnameVerifier() {
					public boolean verify(String hostname, SSLSession session) {
						return true;
					}
				};

				TrustManager[] tm = new TrustManager[] { new X509TrustManager() {

					public X509Certificate[] getAcceptedIssuers() {
						return null;
					}

					public void checkServerTrusted(X509Certificate[] chain, String authType)
							throws CertificateException {
					}

					public void checkClientTrusted(X509Certificate[] chain, String authType)
							throws CertificateException {
					}
				} };

				SSLContext sslContext = sslContext = SSLContext.getInstance("SSL");

				sslContext.init(null, tm, new SecureRandom());

				clientBuilder.sslContext(sslContext).hostnameVerifier(verifier);
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			} catch (KeyManagementException e) {
				e.printStackTrace();
			}
		}

		return (JerseyClient) clientBuilder.build().register(JacksonJsonProvider.class);
	}

}
