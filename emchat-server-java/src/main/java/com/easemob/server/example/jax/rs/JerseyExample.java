package com.easemob.server.example.jax.rs;

import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.easemob.server.example.utils.HttpsUtils;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JerseyExample {

	
	
	
	
	
	
	public static void main(String[] args) {
		
		String appkey = "zdxd#ksf";

		String token = "YWMtWJ-etggfEeSZ4pfPDaJNRgAAAUdD7YReiepTP1RGOrMvCLvIib2jK8tyVfE";
		String checkUser="ceshi1";
		try {
			getUserStatus(appkey, token, checkUser);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	
	
	
	
	
	
	
	/**
	 * 检测用户是否在线
	 * 
	 * @param token
	 * @param user
	 * @return
	 */
	public static boolean getUserStatus(String appKey, String token,
			String targetUserName) throws Exception {
		 
		String HTTP_URL = "https://a1.easemob.com/"
				+ appKey.replaceFirst("#", "/") + "/users/" + targetUserName
				+ "/status";

		Client client = getClient(true);
		WebTarget target = ((javax.ws.rs.client.Client) client).target(HTTP_URL)
				.path("/{targetUserName}/status").resolveTemplate("targetUserName", targetUserName);
		Response response = target.request(MediaType.APPLICATION_JSON_TYPE).header("Authorization", "Bearer "+token).buildGet().invoke();
		String result = response.readEntity(String.class);
		ObjectMapper objectMapper = new ObjectMapper();
		String content =objectMapper.readTree(result).get("data").get(targetUserName).asText();
		System.out.println(result);
		if (content.equals("online")) {
			return true;
		} else if (content.equals("offline")) {
			return false;
		}
		return false;
	}
	
	
	private static Client getClient(boolean https)
			throws KeyManagementException, NoSuchAlgorithmException {
		ClientBuilder builder = ClientBuilder.newBuilder();
		if (https) {
			HostnameVerifier hv = new HostnameVerifier() {
				@Override
				public boolean verify(String hostname, SSLSession session) {
					System.out.println("Warning: URL Host: " + hostname
							+ " vs. " + session.getPeerHost());
					return true;
				}
			};
			// Create a trust manager that does not validate certificate chains
			TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
				public void checkClientTrusted(
						java.security.cert.X509Certificate[] certs,
						String authType) {
				}

				public void checkServerTrusted(
						java.security.cert.X509Certificate[] certs,
						String authType) {
				}

				public java.security.cert.X509Certificate[] getAcceptedIssuers() {
					return null;
				}
			} };

			// Install the all-trusting trust manager
			SSLContext sc = SSLContext.getInstance("SSL");

			sc.init(null, trustAllCerts, new java.security.SecureRandom());
			builder.sslContext(sc).hostnameVerifier(hv);
		}
		return builder.build();

	}
	
	
	
}
