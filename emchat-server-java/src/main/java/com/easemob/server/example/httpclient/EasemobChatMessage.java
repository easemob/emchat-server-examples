package com.easemob.server.example.httpclient;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

/**
 * REST API Demo : 聊天消息导出REST API 实现 利用HttpClient实现
 * 
 * Doc URL: http://developer.easemob.com/docs/emchat/rest/chatmessage.html
 * 
 * @author Lynch 2014-09-15
 * 
 */
public class EasemobChatMessage {
	String param = "";

	long totalSize = 0;

	public boolean sendFiletoServerHttp(final String localFilePath, final String remoteUrl,
			final Map<String, String> headers) throws Exception {

		return false;

	}

	/**
	 * 检测用户是否在线
	 * 
	 * @param token
	 * @param user
	 * @return
	 */
	public static boolean getUserStatus(String appKey, String token, String targetUserName) throws Exception {

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
	public static String getAccessToken(String appKey, String username, String password) throws IOException {
		String token = "";

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
	public static Map<String, String> sendTextMessage(String appKey, String token, String textContent, String fromUser,
			List<String> toUsernames) throws IOException {
		Map<String, String> result = null;

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
	public static void sendImageMessage(final String appKey, final String token, final String filePath,
			String fromUser, final List<String> toUsernames) throws Exception {

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
	public static String httpPost(String url, String params, Map<String, String> headers) throws Exception {
		String response = null;

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
	public static String httpGet(String url, Map<String, String> params, Map<String, String> headers) throws Exception {

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
