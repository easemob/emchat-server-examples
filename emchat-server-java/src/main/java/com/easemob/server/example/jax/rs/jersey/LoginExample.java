package com.easemob.server.example.jax.rs.jersey;

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

/**
 * 本实例使用了 JAX-RS 2.0的client API, 这是JAVA 中调用REST服务的标准API
 * 
 * 
 * @author stliu
 * @date 6/23/14
 */
public class LoginExample {
	public static void main(String[] args) throws NoSuchAlgorithmException, KeyManagementException {
		String host = "https://a1.easemob.com";
		String orgName = "easemob-demo";
		String appName = "chatdemoui";
		String username = "20000";
		String password = "123456";

		Client client = getClient(true);
		WebTarget target = ((javax.ws.rs.client.Client) client).target(host)
				.path("/{org}/{app}/token").resolveTemplate("org", orgName)
				.resolveTemplate("app", appName);
		Map<String, String> payload = new HashMap<String, String>();
		payload.put("grant_type", "password");
		payload.put("username", username);
		payload.put("password", password);
		Response response = target.request(MediaType.APPLICATION_JSON_TYPE)
				.buildPost(Entity.json(payload)).invoke();
		String result = response.readEntity(String.class);
		System.out.println(result);
	}

	private static Client getClient(boolean https) throws KeyManagementException,
			NoSuchAlgorithmException {
		ClientBuilder builder = ClientBuilder.newBuilder();
		if (https) {
			HostnameVerifier hv = new HostnameVerifier() {
				@Override
				public boolean verify(String hostname, SSLSession session) {
					System.out.println("Warning: URL Host: " + hostname + " vs. "
							+ session.getPeerHost());
					return true;
				}
			};
			// Create a trust manager that does not validate certificate chains
			TrustManager[] trustAllCerts = new TrustManager[] { new X509TrustManager() {
				public void checkClientTrusted(java.security.cert.X509Certificate[] certs,
						String authType) {
				}

				public void checkServerTrusted(java.security.cert.X509Certificate[] certs,
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
