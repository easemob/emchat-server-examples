package com.easemob.server.example.jersey.utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.List;
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

import com.easemob.server.example.comm.HTTPMethod;
import com.easemob.server.example.jersey.vo.Credential;
import com.easemob.server.example.jersey.vo.Token;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;

/**
 * Jersey2.9 Utils
 * 
 * @author Lynch 2014-09-15
 * 
 */
@SuppressWarnings("all")
public class JerseyUtils {

	private static final Logger LOGGER = LoggerFactory.getLogger(JerseyUtils.class);

	private static final JsonNodeFactory factory = new JsonNodeFactory(false);

	/**
	 * Send HTTPS request with Jersey
	 * 
	 * @return
	 */
	public static ObjectNode sendRequest(JerseyWebTarget jerseyWebTarget, Object body, Credential credential,
			String method, List<NameValuePair> headers) throws RuntimeException {

		ObjectNode objectNode = factory.objectNode();

		if (!match("http(s)?://([\\w-]+\\.)+[\\w-]+(/[\\w- ./?%&=]*)?", jerseyWebTarget.getUri().toString())) {
			LOGGER.error("The URL to request is illegal");

			objectNode.put("message", "The URL to request is illegal");

			return objectNode;
		}

		try {

			Invocation.Builder inBuilder = jerseyWebTarget.request();
			if (credential != null) {
				 Token.applyAuthentication(inBuilder, credential);
			}

			if (null != headers && !headers.isEmpty()) {

				for (NameValuePair nameValuePair : headers) {
					inBuilder.header(nameValuePair.getName(), nameValuePair.getValue());
				}

			}

			Response response = null;
			if (HTTPMethod.METHOD_GET.equals(method)) {

				response = inBuilder.get(Response.class);

			} else if (HTTPMethod.METHOD_POST.equals(method)) {

				response = inBuilder.post(Entity.entity(body, MediaType.APPLICATION_JSON), Response.class);

			} else if (HTTPMethod.METHOD_PUT.equals(method)) {

				response = inBuilder.put(Entity.entity(body, MediaType.APPLICATION_JSON), Response.class);

			} else if (HTTPMethod.METHOD_DELETE.equals(method)) {

				response = inBuilder.delete(Response.class);

			}

			objectNode = response.readEntity(ObjectNode.class);
			objectNode.put("statusCode", response.getStatus());

		} catch (Exception e) {
			e.printStackTrace();
		}

		return objectNode;
	}

	/**
	 * DownLoadFile whit Jersey
	 * 
	 * @return
	 * @throws NoSuchAlgorithmException
	 * @throws KeyManagementException
	 * @throws IOException
	 */
	public static File downLoadFile(JerseyWebTarget jerseyWebTarget, Credential credential,
			List<NameValuePair> headers, File localPath) throws IOException {

		Invocation.Builder inBuilder = jerseyWebTarget.request();

		if (credential != null) {
            Token.applyAuthentication(inBuilder, credential);
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

		return file;

	}

	/**
	 * UploadFile whit Jersey
	 * 
	 * @return
	 */
	public static ObjectNode uploadFile(JerseyWebTarget jerseyWebTarget, File file, Credential credential,
			List<NameValuePair> headers) throws RuntimeException {
		ObjectNode objectNode = factory.objectNode();

		try {

			Invocation.Builder inBuilder = jerseyWebTarget.request();
			if (credential != null) {
				Token.applyAuthentication(inBuilder, credential);
			}

			if (null != headers && !headers.isEmpty()) {

				for (NameValuePair nameValuePair : headers) {
					inBuilder.header(nameValuePair.getName(), nameValuePair.getValue());
				}

			}

			FormDataMultiPart multiPart = new FormDataMultiPart();
			multiPart.bodyPart(new FileDataBodyPart("file", file, MediaType.APPLICATION_OCTET_STREAM_TYPE));

			objectNode = inBuilder.post(Entity.entity(multiPart, MediaType.MULTIPART_FORM_DATA), ObjectNode.class);
		} catch (Exception e) {
			e.printStackTrace();
		}

		return objectNode;
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
