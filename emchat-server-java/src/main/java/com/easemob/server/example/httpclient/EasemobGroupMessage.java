package com.easemob.server.example.httpclient;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * group 相关方法通过httpclient实现
 * 
 * @author Liyuzhao
 * 
 */
public class EasemobGroupMessage {

	public static void main(String[] args) {

		String appkey = "";
		String adminToken = "";

		// 通过Appkey获取app中所有的群组
		// try {
		// getGroupsByAppKey(appkey,adminToken);
		// } catch (Exception e) {
		// e.printStackTrace();
		// }

		// 创建一个群组
		// List<String> memList=new ArrayList<String>();
		// memList.add("21W58S");
		// memList.add("I18KCI");
		// memList.add("IDX674");
		// try {
		// creatChatGroups(appkey,adminToken, "testabc","server create", true, "testuser1",
		// memList);
		// } catch (Exception e) {
		// e.printStackTrace();
		// }

		// 根据groupid删除群组
		// try {
		// deleteChatGroups(appkey,adminToken, "1405670317105784");
		// } catch (Exception e) {
		// e.printStackTrace();
		// }

		// 获取群组中的所有成员
		// try {
		// getUsersByGroupId(appkey,adminToken, "1405671199160961");
		// } catch (Exception e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }

		// 在群组中添加一个人

		// try {
		// addUserToGroup(appkey, adminToken, "1405671199160961", "ZSMIQG");
		// } catch (Exception e) {
		// e.printStackTrace();
		// }

		// 在群组中减少一个人
		// try {
		// deleteUserFromGroup(appkey, adminToken, "1405671199160961", "21W58S");
		// } catch (Exception e) {
		// e.printStackTrace();
		// }

	}

	/**
	 * 通过Appkey获取app中所有的群组
	 * 
	 * @param appKey
	 * @throws IOException
	 * @throws JsonProcessingException
	 */
	public static void getGroupsByAppKey(String appKey, String admin_token) throws JsonProcessingException, IOException {
		String httpUrl = "";

		String result = "";
		String groups = new ObjectMapper().readTree(result).get("data").toString();

		System.out.println("groups:" + groups);

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
	public static void creatChatGroups(String appkey, String admin_token, String groupname, String desc,
			boolean publicParams, String owner, List<String> members) throws JsonProcessingException,
			KeyManagementException, NoSuchAlgorithmException {

		String httpUrl = "";

		Map<String, Object> paramsBody = new HashMap<String, Object>();
		paramsBody.put("groupname", groupname);
		paramsBody.put("desc", desc);
		paramsBody.put("public", publicParams);
		paramsBody.put("owner", owner);
		paramsBody.put("members", members);
		String jsonBody = new ObjectMapper().writeValueAsString(paramsBody);
		System.out.println("jsonBody:" + jsonBody);
		String result = "";
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
	public static void deleteChatGroups(String appkey, String admin_token, String groupid)
			throws KeyManagementException, NoSuchAlgorithmException, JsonProcessingException, IOException {

		String httpUrl = "";

		String result = "";

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
	 * @throws IOException
	 * @throws JsonProcessingException
	 */
	public static void getUsersByGroupId(String appkey, String admin_token, String groupid)
			throws JsonProcessingException, IOException {

		String httpUrl = "";

		String result = "";

		System.out.println("result:" + result);
		//

	}

	/**
	 * 在群组中添加一个人
	 * 
	 * @param appkey
	 * @param admin_token
	 * @param groupid
	 * @param username
	 */
	public static void addUserToGroup(String appkey, String admin_token, String groupid, String username) {

		String httpUrl = "";

		String result = "";

		System.out.println("result:" + result);

	}

	public static void deleteUserFromGroup(String appkey, String adminToken, String groupid, String username) {

		String httpUrl = "";

		String result = "";
		System.out.println("result:" + result);
	}
}
