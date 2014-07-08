package com.easemob.server.example.jax.rs;

import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

/**
 * HttpClient Utils
 * 
 * @author Lynch
 * 
 */
@SuppressWarnings("all")
public class HttpsUtils {

	public static String Method_GET = "GET";
	public static String Method_POST = "POST";
	public static String Method_PUT = "PUT";
	public static String Method_DELETE = "DELETE";

	private static Logger logger = Logger.getLogger(HttpsUtils.class);

	/**
	 * Send SSL Request
	 * 
	 * @param reqURL
	 * @param token
	 * @param str
	 * @return
	 */
	@SuppressWarnings("finally")
	public static String sendSSLRequest(String reqURL, String token, String body, String method) {

		String responseContent = null;
		HttpClient httpClient = new DefaultHttpClient();
		X509TrustManager xtm = new X509TrustManager() {
			public void checkClientTrusted(X509Certificate[] chain, String authType)
					throws CertificateException {
			}

			public void checkServerTrusted(X509Certificate[] chain, String authType)
					throws CertificateException {
			}

			public X509Certificate[] getAcceptedIssuers() {
				return null;
			}
		};

		try {
			SSLContext ctx = SSLContext.getInstance("TLS");

			ctx.init(null, new TrustManager[] { xtm }, null);
			SSLSocketFactory socketFactory = new SSLSocketFactory(ctx);
			httpClient.getConnectionManager().getSchemeRegistry()
					.register(new Scheme("https", 443, socketFactory));
			HttpResponse response = null;

			if (method.equals(Method_POST)) {
				HttpPost httpPost = new HttpPost(reqURL);
				httpPost.setEntity(new StringEntity(body, "UTF-8"));
				if (token != null) {
					httpPost.setHeader("Authorization", "Bearer " + token);
				}
				response = httpClient.execute(httpPost);
			} else if (method.equals(Method_PUT)) {
				HttpPut httpPut = new HttpPut(reqURL);
				httpPut.setEntity(new StringEntity(body, "UTF-8"));
				if (token != null) {
					httpPut.setHeader("Authorization", "Bearer " + token);
				}
				response = httpClient.execute(httpPut);
			} else if (method.equals(Method_GET)) {
				HttpGet httpGet = new HttpGet(reqURL);
				if (token != null) {
					httpGet.setHeader("Authorization", "Bearer " + token);
				}
				response = httpClient.execute(httpGet);
			} else if (method.equals(Method_DELETE)) {
				HttpDelete httpDelete = new HttpDelete(reqURL);
				if (token != null) {
					httpDelete.setHeader("Authorization", "Bearer " + token);
				}
				response = httpClient.execute(httpDelete);
			}

			HttpEntity entity = response.getEntity();

			if (null != entity) {
				responseContent = EntityUtils.toString(entity, "UTF-8");
				EntityUtils.consume(entity);
			}
		} catch (Exception e) {
			throw new RuntimeException();
		} finally {
			httpClient.getConnectionManager().shutdown();
			return responseContent;
		}
	}
}