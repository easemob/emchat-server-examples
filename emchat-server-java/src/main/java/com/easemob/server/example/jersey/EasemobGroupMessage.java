package com.easemob.server.example.jersey;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
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

import org.apache.http.entity.StringEntity;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * 群聊天信息和用户管理
 * 
 * @author Administrator
 * 
 */
public class EasemobGroupMessage {

	public static void main(String[] args) {
		String appkey = "easemob-playground#test1";
		String admin_token = "YWMtE0P96A5REeSsN-Vbfng5bAAAAUdshcBI_5VrO7yVbvR_tB8WBzvoF-8T6MM";// 管理员帐号获取的token

		// 获取app中所有的群组
//		try {
//			getGroupsByAppKey(appkey,admin_token);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}

		// 创建一个群组
//		 List<String> memList=new ArrayList<String>();
//		 memList.add("21W58S");
//		 memList.add("I18KCI");
//		 memList.add("IDX674");
//		 try {
//		 creatChatGroups(appkey,admin_token, "ok","server create", true, "testuser1",
//		 memList);
//		 } catch (Exception e) {
//		 e.printStackTrace();
//		 }

		// 根据groupid删除群组
//		 try {
//		 deleteChatGroups(appkey,admin_token, "1405670678636704");
//		 } catch (Exception e) {
//		 e.printStackTrace();
//		 }

		// 获取群组中的所有成员
//		try {
//			getUsersByGroupId(appkey,admin_token, "1405671199160961");
//		} catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}

		
		//在群组中添加一个人
		
//		try {
//			addUserToGroup(appkey, admin_token, "1405671199160961", "9UWIT6");
//		} catch (Exception e) {
//			e.printStackTrace();
//		}  
		
		//在群组中减少一个人
//		try {
//			deleteUserFromGroup(appkey, admin_token, "1405671199160961", "9UWIT6");
//		} catch (Exception e) {
//			e.printStackTrace();
//		}  
		
		
		
	}

	/**
	 * 创建群组
	 * 
	 * @param appkey
	 *            申请到的appkey
	 * @param groupname
	 *            群组的名称
	 * @param desc
	 *            群组的描述
	 * @param publicParams
	 *            是否是公开群
	 * @param owner
	 *            群组创建者
	 * @return
	 * @throws JsonProcessingException
	 * @throws NoSuchAlgorithmException
	 * @throws KeyManagementException
	 */
	public static void creatChatGroups(String appkey, String admin_token,
			String groupname, String desc, boolean publicParams, String owner,
			List<String> members) throws JsonProcessingException,
			KeyManagementException, NoSuchAlgorithmException {

		String httpUrl = "https://a1.easemob.com/"
				+ appkey.replaceFirst("#", "/") + "/chatgroups";
		Map<String, Object> paramsBody = new HashMap<String, Object>();
		paramsBody.put("groupname", groupname);
		paramsBody.put("desc", desc);
		paramsBody.put("owner", owner);
		paramsBody.put("public", publicParams);
		paramsBody.put("members", members);

		Client client = getClient(true);
		WebTarget target = client.target(httpUrl);
		Response response = target.request(MediaType.APPLICATION_JSON_TYPE)
				.header("Authorization", "Bearer " + admin_token)
				.buildPost(Entity.json(paramsBody)).invoke();
		String result = response.readEntity(String.class);
		System.out.println("group receiver message :" + result);
	}

	/**
	 * 删除群组
	 * 
	 * @param appkey
	 * @param groupid
	 * @throws NoSuchAlgorithmException
	 * @throws KeyManagementException
	 * @throws IOException
	 * @throws JsonProcessingException
	 */
	public static void deleteChatGroups(String appkey, String admin_token,
			String groupid) throws KeyManagementException,
			NoSuchAlgorithmException, JsonProcessingException, IOException {

		String httpUrl = "https://a1.easemob.com/"
				+ appkey.replaceFirst("#", "/") + "/chatgroups/" + groupid;
		Client client = getClient(true);
		WebTarget target = client.target(httpUrl);
		Response response = target.request(MediaType.APPLICATION_JSON_TYPE)
				.header("Authorization", "Bearer " + admin_token).buildDelete()
				.invoke();
		String result = response.readEntity(String.class);
		JsonNode resultNode = new ObjectMapper().readTree(result).get("data");
		if (resultNode.has("groupid")) {
			System.out.println("groupid:" + resultNode.get("groupid").asText());
			System.out.println("success:" + resultNode.get("success").asText());
		} else {
			System.out.println("error ");
		}

	}

	/**
	 * 获取群组中的所有成员
	 * 
	 * @param appkey
	 * @param groupid
	 * @throws NoSuchAlgorithmException
	 * @throws KeyManagementException
	 */
	public static void getUsersByGroupId(String appkey,String admin_token, String groupid)
			throws KeyManagementException, NoSuchAlgorithmException {

		String httpUrl = "https://a1.easemob.com/"
				+ appkey.replaceFirst("#", "/") + "/chatgroups/" + groupid
				+ "/users";

		Client client = getClient(true);
		WebTarget target = client.target(httpUrl);
		Response response = target.request(MediaType.APPLICATION_JSON_TYPE).header("Authorization", "Bearer " + admin_token)
				.buildGet().invoke();
		String result = response.readEntity(String.class);
		System.out.println("result:" + result);

	}
	
	
	/**
	 * 在群组中添加一个人
	 * @throws NoSuchAlgorithmException 
	 * @throws KeyManagementException 
	 */
	public static void addUserToGroup(String appkey,String admin_token,String groupid,String username) throws KeyManagementException, NoSuchAlgorithmException
	{
		String httpUrl = "https://a1.easemob.com/"
				+ appkey.replaceFirst("#", "/") + "/chatgroups/" + groupid
				+ "/users/"+username;

		Client client = getClient(true);
		WebTarget target = client.target(httpUrl);
		Response response = target.request(MediaType.APPLICATION_JSON_TYPE).header("Authorization", "Bearer " + admin_token)
				.buildPost(null).invoke();
		String result = response.readEntity(String.class);
		System.out.println("result:" + result);
		
		
		
	}
	
	
	
	
	/**
	 * 在群组中减少一个人
	 * @throws NoSuchAlgorithmException 
	 * @throws KeyManagementException 
	 */
	public static void deleteUserFromGroup(String appkey,String admin_token,String groupid,String username) throws KeyManagementException, NoSuchAlgorithmException
	{
		
		String httpUrl = "https://a1.easemob.com/"
				+ appkey.replaceFirst("#", "/") + "/chatgroups/" + groupid
				+ "/users/"+username;

		Client client = getClient(true);
		WebTarget target = client.target(httpUrl);
		Response response = target.request(MediaType.APPLICATION_JSON_TYPE).header("Authorization", "Bearer " + admin_token).buildDelete()
				.invoke();
		String result = response.readEntity(String.class);
		System.out.println("result:" + result);
		
		
	}
	
	
	
	
	
	
	
	
	

	/**
	 * 通过Appkey获取app中所有的群组
	 * 
	 * @param appKey
	 * @throws NoSuchAlgorithmException
	 * @throws KeyManagementException
	 * @throws IOException
	 * @throws JsonProcessingException
	 */
	public static void getGroupsByAppKey(String appkey,String admin_token)
			throws KeyManagementException, NoSuchAlgorithmException,
			JsonProcessingException, IOException {
		String httpUrl = "https://a1.easemob.com/"
				+ appkey.replaceFirst("#", "/") + "/chatgroups";
		Client client = getClient(true);
		WebTarget target = client.target(httpUrl);
		Response response = target.request(MediaType.APPLICATION_JSON_TYPE).header("Authorization", "Bearer " + admin_token)
				.buildGet().invoke();
		String result = response.readEntity(String.class);
		String groups = new ObjectMapper().readTree(result).get("data")
				.toString();

		System.out.println("groups:" + groups);

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
