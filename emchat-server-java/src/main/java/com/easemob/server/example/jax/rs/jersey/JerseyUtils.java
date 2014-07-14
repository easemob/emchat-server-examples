package com.easemob.server.example.jax.rs.jersey;

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

import org.apache.http.NameValuePair;
import org.glassfish.jersey.client.JerseyClient;
import org.glassfish.jersey.client.JerseyClientBuilder;
import org.glassfish.jersey.client.JerseyWebTarget;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

	private static Logger LOGGER = LoggerFactory.getLogger(EasemobChatMessage.class);

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
			String method, List<NameValuePair> headers) throws RuntimeException {

		if (match("http(s)?://([\\w-]+\\.)+[\\w-]+(/[\\w- ./?%&=]*)?", reqURL)) {
			LOGGER.error("The URL to request is illegel");

			return "";
		}
		Map<String, Object> resultMap = new HashMap<String, Object>();

		JerseyClient jerseyClient = null;
		try {

			try {
				jerseyClient = getJerseyClient(true);
			} catch (KeyManagementException e) {
				e.printStackTrace();
			} catch (NoSuchAlgorithmException e) {
				e.printStackTrace();
			}

			JerseyWebTarget rootJerseyWebTarget = jerseyClient.target(reqURL);

			Invocation.Builder inBuilder = rootJerseyWebTarget.request();
			if (token != null) {
				// add headers
				inBuilder.header("Authentication", "Bearer " + token);
			}
			if (null != headers && !headers.isEmpty()) {
				if (headers.iterator().hasNext()) {
					inBuilder.header(headers.iterator().next().getName(), headers.iterator().next()
							.getValue());
				}
			}

			if (JerseyUtils.Method_GET.equals(method)) {
				resultMap = inBuilder.get(Map.class);
			} else if (JerseyUtils.Method_POST.equals(method)) {
				resultMap = inBuilder.post(Entity.entity(reqBody, MediaType.APPLICATION_JSON),
						Map.class);
			} else if (JerseyUtils.Method_PUT.equals(method)) {
				resultMap = inBuilder.put(Entity.entity(reqBody, MediaType.APPLICATION_JSON),
						Map.class);
			} else if (JerseyUtils.Method_DELETE.equals(method)) {
				resultMap = inBuilder.delete(Map.class);
			}
		} catch (Exception e) {
			throw new RuntimeException("");
		}

		if (resultMap.get("") == "") {

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

	/**
	 * Check Illigel String
	 * 
	 * @param regex
	 * @param str
	 * @return
	 */
	private static boolean match(String regex, String str) {
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

		ClientBuilder clientBuilder = JerseyClientBuilder.newBuilder();

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

	public static void main(String[] args) {
		String reqURL = "http://a1.easemob.com/management/token";
		Map<String, Object> reqBody = new HashMap<String, Object>();
		reqBody.put("grant_type", "password");
		reqBody.put("username", "easemobdemoadmin");
		reqBody.put("password", "thepushbox");
		JerseyUtils.sendRequest(reqURL, reqBody, null, Method_POST, null);
	}
}
