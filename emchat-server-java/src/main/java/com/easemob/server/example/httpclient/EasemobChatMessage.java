package com.easemob.server.example.httpclient;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.http.util.EntityUtils;

import com.easemob.server.example.utils.CloudOperationCallback;
import com.easemob.server.example.utils.CustomMultiPartEntity;
import com.easemob.server.example.utils.EMCallBack;
import com.easemob.server.example.utils.HttpsUtils;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * REST API Demo : 聊天消息导出REST API 实现 利用HttpClient实现
 * 
 * Doc URL: http://developer.easemob.com/docs/emchat/rest/chatmessage.html
 * 
 * @author Lynch 2014-07-12
 * 
 */
public class EasemobChatMessage {

	long totalSize = 0;

	public boolean sendFiletoServerHttp(final String localFilePath,
			final String remoteUrl, final Map<String, String> headers,
			final CloudOperationCallback listener) throws Exception {
		File sourceFile = new File(localFilePath);
		if (!sourceFile.isFile()) {
			listener.onError("Source file doesn't exist");
			return false;
		}
		HttpPost httpPost = null;
		HttpResponse response = null;
		try {
			httpPost = new HttpPost(remoteUrl);
			CustomMultiPartEntity multipartEntity = new CustomMultiPartEntity(
					new CustomMultiPartEntity.ProgressListener() {
						@Override
						public void transferred(long num) {
							listener.onProgress((int) ((num / (float) totalSize) * 100));
						}
					});
			if (headers != null) {
				for (Entry<String, String> item : headers.entrySet()) {
					httpPost.addHeader(item.getKey(), item.getValue());
				}
			}

			String remoteFileName = remoteUrl;
			if (remoteFileName.indexOf("/") > 0) {
				String path = remoteFileName.substring(0,
						remoteFileName.lastIndexOf("/"));
				remoteFileName = remoteFileName.substring(remoteFileName
						.lastIndexOf("/"));
				multipartEntity.addPart("path", new StringBody(path));
			}

			String mimeType;

			if (sourceFile.getName().endsWith(".3gp")
					|| sourceFile.getName().endsWith(".amr")) {
				mimeType = "audio/3gp";
			} else {
				mimeType = "image/png";
			}
			multipartEntity.addPart("file", new FileBody(sourceFile,
					remoteFileName, mimeType, "UTF-8"));

			HttpParams httpParameters = new BasicHttpParams();
			HttpConnectionParams.setConnectionTimeout(httpParameters, 10000);
			HttpConnectionParams.setSoTimeout(httpParameters, 20000);
			HttpConnectionParams.setTcpNoDelay(httpParameters, true);
			totalSize = multipartEntity.getContentLength();
			httpPost.setEntity(multipartEntity);
			HttpClient httpclient = new DefaultHttpClient(httpParameters);

			javax.net.ssl.X509TrustManager xtm = new javax.net.ssl.X509TrustManager() {

				@Override
				public X509Certificate[] getAcceptedIssuers() {
					// TODO Auto-generated method stub
					return null;
				}

				@Override
				public void checkServerTrusted(X509Certificate[] chain,
						String authType) throws CertificateException {
					// TODO Auto-generated method stub

				}

				@Override
				public void checkClientTrusted(X509Certificate[] chain,
						String authType) throws CertificateException {
					// TODO Auto-generated method stub

				}
			};

			SSLContext ctx = SSLContext.getInstance("TLS");
			ctx.init(null, new TrustManager[] { xtm }, null);
			SSLSocketFactory socketFactory = new SSLSocketFactory(ctx);
			httpclient.getConnectionManager().getSchemeRegistry()
					.register(new Scheme("https", 443, socketFactory));

			response = httpclient.execute(httpPost);
			BufferedReader rd = new BufferedReader(new InputStreamReader(
					response.getEntity().getContent()));
			String line;
			String lastLine = null;
			StringBuilder sb = new StringBuilder();
			while ((line = rd.readLine()) != null) {
				lastLine = line;
				sb.append(line);
			}

			rd.close();
			if (lastLine != null && lastLine.contains("Invalid file")) {
				listener.onError("Invalid file");
				return false;
			} else {
				listener.onProgress(100);
				listener.onSuccess(sb.toString());
			}

			return true;
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e.getMessage());
		} finally {

		}

	}

	public static void main(String[] args) throws Exception {
		String remoteUrl = "http://a1.easemob.com/easemob-demo/chatdemoui/chatfiles/5d7cfa8a-074f-11e4-ad2a-51472d5ba0ab";
		// apacheApi.sendFiletoServerHttp("C:\\a.png", remoteUrl, headers, new
		// CloudOperationCallback() {
		//
		// @Override
		// public void onSuccess(String result) {
		//
		// System.out.println("result:"+result);
		// }
		//
		// @Override
		// public void onProgress(int progress) {
		// System.out.println("progress:"+progress);
		//
		// }
		//
		// @Override
		// public void onError(String msg) {
		// System.out.println("errorMsg:"+msg);
		// }
		// });
		// headers.put("Accept", "application/octet-stream");
		// apacheApi.downloadFile(remoteUrl, "D:\\m.png", headers, new
		// CloudOperationCallback() {
		//
		// @Override
		// public void onSuccess(String result) {
		// System.out.println("file download success");
		//
		// }
		//
		// @Override
		// public void onProgress(int progress) {
		// System.out.println("progress:"+progress);
		//
		// }
		//
		// @Override
		// public void onError(String msg) {
		// System.out.println("errorMsg:"+msg);
		//
		// }
		// });
		String appkey = "easemob-playground#test1";

		String token = "YWMtWJ-etggfEeSZ4pfPDaJNRgAAAUdD7YReiepTP1RGOrMvCLvIib2jK8tyVfE";
		// 获取Token
		// token=getAccessToken(appkey, "zhangjianguo", "zhangjianguo");
		// System.out.println("token:"+token);

		// 检测用户是否在线

		// String checkUser="ceshi1";
		//
		// boolean userStatus = getUserStatus(appkey, token , checkUser);
		// //
		// if(userStatus)
		// {
		// System.out.println("用户在线");
		// }else{
		// System.out.println("用户离线状态");
		// }
		//

		// 发送Text消息
		// List<String> toUsernames=new ArrayList<String>();
		// toUsernames.add("ceshi1");
		// toUsernames.add("ceshi2");
		//
		// String fromUser="ceshi";
		// String txtContent="Hello,It is a test message!";
		//
		// Map<String, String> sendResult = sendTextMessage(appkey,
		// token,txtContent,fromUser, toUsernames);
		// for (String toUsername : toUsernames) {
		//
		// String isSuccess = sendResult.get(toUsername);
		// if(isSuccess.equals("success"))
		// {
		// System.out.println("send message to "+toUsername+" success!");
		// }else{
		// System.out.println("send message to "+toUsername+" fail!");
		// }
		//
		// }

		// 发送Image消息
		// final List<String> toUsernames=new ArrayList<String>();
		// toUsernames.add("ceshi1");
		// toUsernames.add("ceshi2");
		//
		// String fromUser = "ceshi";
		// String filePath="C:\\a.png";
		// sendImageMessage(appkey, token,
		// filePath, fromUser, toUsernames,new EMCallBack() {
		//
		// @Override
		// public void onSuccess() {
		//
		// System.out.println("image message send sucess");
		//
		// }
		//
		// @Override
		// public void onProgress(int progress, String status) {
		// // TODO Auto-generated method stub
		// System.out.println("image send progress:"+progress);
		//
		// }
		//
		// @Override
		// public void onError(int code, String message) {
		// System.out.println("image message send fail:"+message);
		//
		// }
		// });
		//

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
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("Authorization", "Bearer " + token);
		ObjectMapper objectMapper = new ObjectMapper();
		String resultMsg = HttpsUtils.sendSSLRequest(HTTP_URL, token, null,
				HttpsUtils.Method_GET);
		String content = objectMapper.readTree(resultMsg).get("data")
				.get(targetUserName).asText();
		if (content.equals("online")) {
			return true;
		} else if (content.equals("offline")) {
			return false;
		}
		return false;
	}

	/**
	 * 获取用户token
	 * 
	 * @param appKey
	 * @param username
	 * @param password
	 * @return
	 * @throws IOException
	 */
	public static String getAccessToken(String appKey, String username,
			String password) throws IOException {
		String token = "";
		String HttpUrl = "https://a1.easemob.com/"
				+ appKey.replaceFirst("#", "/") + "/token";
		Map<String, String> headers = new HashMap<String, String>();
		headers.put("grant_type", "password");
		headers.put("username", username);
		headers.put("password", password);
		ObjectMapper objectMapper = new ObjectMapper();
		try {
			String resultMsg = HttpsUtils.sendSSLRequest(HttpUrl, null,
					objectMapper.writeValueAsString(headers),
					HttpsUtils.Method_POST);

			token = objectMapper.readTree(resultMsg).get("access_token")
					.asText();

			// token=objectMapper.readValue(resultMsg,
			// Map.class).get("access_token").toString();
			System.out.println("token:" + resultMsg);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return token;

	}

	/**
	 * 发送文本消息
	 * 
	 * @param textContent
	 *            消息内容
	 * @param username
	 *            发送人
	 * @return true发送成功 false 发送失败
	 * @throws IOException
	 */
	public static Map<String, String> sendTextMessage(String appKey,
			String token, String textContent, String fromUser,
			List<String> toUsernames) throws IOException {
		String httpUrl = "https://a1.easemob.com/"
				+ appKey.replaceFirst("#", "/") + "/messages";
		Map<String, Object> body = new HashMap<String, Object>();
		body.put("target_type", "users");
		body.put("target", toUsernames);
		Map<String, String> msgBody = new HashMap<String, String>();
		msgBody.put("type", "txt");
		msgBody.put("msg", "hello from rest");
		body.put("msg", msgBody);
		body.put("from", "ceshi");
		Map<String, String> extBody = new HashMap<String, String>();
		extBody.put("attr1", "v1");
		extBody.put("attr2", "v2");
		body.put("ext", extBody);

		ObjectMapper mapper = new ObjectMapper();
		String resultMsg = HttpsUtils.sendSSLRequest(httpUrl, token,
				mapper.writeValueAsString(body), HttpsUtils.Method_POST);
		String content = mapper.readTree(resultMsg).get("data").toString();
		Map<String, String> result = mapper.readValue(content, Map.class);
		System.out.println("resultMsg:" + resultMsg);
		return result;
	}

	/**
	 * 发送图片消息
	 * 
	 * @param textContent
	 *            消息内容
	 * @param username
	 *            发送人
	 * @return true发送成功 false 发送失败
	 * @throws Exception
	 */
	public static void sendImageMessage(final String appKey,
			final String token, final String filePath, String fromUser,
			final List<String> toUsernames, final EMCallBack callback)
			throws Exception {

		final String remoteUrl = "http://a1.easemob.com/"
				+ appKey.replaceFirst("#", "/") + "/chatfiles";

		EasemobChatMessage httpok = new EasemobChatMessage();

		Map<String, String> headers = new HashMap<String, String>();
		headers.put("restrict-access", "true");
		headers.put("Authorization", "Bearer " + token);

		httpok.sendFiletoServerHttp(filePath, remoteUrl, headers,
				new CloudOperationCallback() {

					@Override
					public void onSuccess(String result) {
						String uuid = "";
						String secret = "";
						try {

							JsonNode jsonNode = new ObjectMapper().readTree(
									result).get("entities");

							uuid = jsonNode.get(0).get("uuid").asText();
							if (jsonNode.get(0).has("share-secret")) {
								secret = jsonNode.get(0).get("share-secret")
										.asText();
							}

							ObjectMapper mapper = new ObjectMapper();
							String remoteFile = remoteUrl + uuid;
							String httpUrl = "https://a1.easemob.com/"
									+ appKey.replaceFirst("#", "/")
									+ "/messages";
							Map<String, Object> body = new HashMap<String, Object>();
							body.put("target_type", "users");
							body.put("target", toUsernames);
							Map<String, String> msgBody = new HashMap<String, String>();
							msgBody.put("type", "img");
							msgBody.put("url", remoteFile);
							msgBody.put("filename",
									new File(filePath).getName());
							msgBody.put("thumb", remoteFile);
							msgBody.put("secret", secret);
							body.put("msg", msgBody);
							body.put("from", "ceshi");
							Map<String, String> extBody = new HashMap<String, String>();
							extBody.put("attr1", "v1");
							extBody.put("attr2", "v2");
							body.put("ext", extBody);
							String resultMsg = HttpsUtils.sendSSLRequest(
									httpUrl, token,
									mapper.writeValueAsString(body),
									HttpsUtils.Method_POST);
							String content = mapper.readTree(resultMsg)
									.get("data").toString();
							Map<String, String> resultMap = mapper.readValue(
									content, Map.class);
							System.out.println("resultMsg:" + resultMsg);

						} catch (Exception e) {
							System.out
									.println("sendImageMessage json parse exception remotefilepath:"
											+ remoteUrl);
						}

						//

					}

					@Override
					public void onProgress(int progress) {
						if (callback != null) {
							callback.onProgress(progress, null);
						}

					}

					@Override
					public void onError(String msg) {
						if (callback != null) {
							callback.onError(EMCallBack.ERROR_SEND, msg);
						}
					}
				});

	}

	/**
	 * 以Post方式发送请求
	 * 
	 * @param url
	 *            请求地址
	 * @param params
	 *            参数 ，
	 * @return
	 * @throws Exception
	 */
	public static String httpPost(String url, String params,
			Map<String, String> headers) throws Exception {
		String response = null;
		HttpClient httpclient = new DefaultHttpClient();

		// 创建HttpPost对象
		HttpPost httppost = new HttpPost(url);

		if (headers != null) {
			for (Entry<String, String> item : headers.entrySet()) {
				httppost.addHeader(item.getKey(), item.getValue());
			}
		}

		try {
			// 设置httpPost请求参数
			httppost.setEntity(new StringEntity(params));
			// 使用execute方法发送HTTP Post请求，并返回HttpResponse对象
			HttpResponse httpResponse = httpclient.execute(httppost);
			int statusCode = httpResponse.getStatusLine().getStatusCode();
			if (statusCode == HttpStatus.SC_OK) {
				// 获得返回结果
				response = EntityUtils.toString(httpResponse.getEntity());
			} else {
				response = "返回码：" + statusCode;
			}
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		return response;
	}

	/**
	 * 以Get方式发送请求
	 * 
	 * @param url
	 *            请求路径
	 * @param params
	 *            请求参数
	 * @return
	 */
	public static String httpGet(String url, Map<String, String> params,
			Map<String, String> headers) throws Exception {

		String response = null;
		HttpClient httpclient = new DefaultHttpClient();

		if (params != null) {
			url = url + "?";
			for (Entry<String, String> item : params.entrySet()) {
				url += item.getKey() + "=" + item.getValue();
				url += "&";
			}
			url = url.substring(0, url.length() - 1);
		}

		// 创建HttpGet对象
		HttpGet httpGet = new HttpGet(url);

		if (headers != null) {
			for (Entry<String, String> header : headers.entrySet()) {
				httpGet.addHeader(header.getKey(), header.getValue());
			}

		}

		HttpResponse httpResponse;
		try {
			// 使用execute方法发送HTTP GET请求，并返回HttpResponse对象
			httpResponse = httpclient.execute(httpGet);
			int statusCode = httpResponse.getStatusLine().getStatusCode();
			if (statusCode == HttpStatus.SC_OK) {
				// 获得返回结果
				response = EntityUtils.toString(httpResponse.getEntity());
			} else {
				response = "返回码：" + statusCode;
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new Exception(e.getMessage());
		}
		return response;

	}

}
