package com.easemob.server.example.jax.rs.restEasy;

import java.io.IOException;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.http.client.utils.HttpClientUtils;

import com.easemob.server.example.utils.HttpsUtils;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.JsonSerializable;
import com.fasterxml.jackson.databind.ObjectMapper;

/**
 * group 相关方法通过httpclient实现
 * @author Administrator
 *
 */
public class EasemobGroupMessage {

	public static void main(String[] args) {
		
		String appkey="zdxd#ksf";
		
		//通过Appkey获取app中所有的群组
//		try {
//			getGroupsByAppKey(appkey);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}  
		
		
		 //创建一个群组
//		 List<String> memList=new ArrayList<String>();
//		 memList.add("ceshi1");
//		 memList.add("ceshi");
//		 memList.add("ceshi2");
//		 try {
//			creatChatGroups(appkey, "testabc","server create", true, "ceshi", memList);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}  
		
		
		
		 //根据groupid删除群组
		 try {
			deleteChatGroups(appkey, "1405240230386409");
		} catch (Exception e) {
			e.printStackTrace();
		}
		 
		 
		
		
	}
	
	
	

	/**
	 * 通过Appkey获取app中所有的群组
	 * @param appKey
	 * @throws IOException 
	 * @throws JsonProcessingException 
	 */
	public static void getGroupsByAppKey(String appKey) throws JsonProcessingException, IOException 
	{
		String httpUrl="https://a1.easemob.com/"+appKey.replaceFirst("#","/")+"/chatgroups";
		
		String result = HttpsUtils.sendSSLRequest(httpUrl, null, null, HttpsUtils.Method_GET);
		String groups=new ObjectMapper().readTree(result).get("data").toString();
		
		
		System.out.println("groups:"+groups);
		
		
	}
	
	 /**
	  * 创建群组
	  * @param appkey  申请到的appkey
	  * @param groupname 群组的名称
	  * @param desc   群组的描述
	  * @param publicParams  是否是公开群
	  * @param owner 群组创建者
	  * @return
	 * @throws JsonProcessingException 
	 * @throws NoSuchAlgorithmException 
	 * @throws KeyManagementException 
	  */
	 public static void creatChatGroups(String appkey,String groupname,String desc,boolean publicParams,String owner,List<String> members) throws JsonProcessingException, KeyManagementException, NoSuchAlgorithmException
	 {
		 
		 String httpUrl="https://a1.easemob.com/"+appkey.replaceFirst("#","/")+"/chatgroups";
		 
		  
		 Map<String,Object> paramsBody=new HashMap<String,Object>();
		 paramsBody.put("groupname", groupname);
		 paramsBody.put("desc",desc);
		 paramsBody.put("public", publicParams);
		 paramsBody.put("owner", owner);
		 paramsBody.put("members", members);
		 String jsonBody= new ObjectMapper().writeValueAsString(paramsBody);
		 System.out.println("jsonBody:"+jsonBody);
		 
		 
		 jsonBody="{\"groupname\":\"testabc\",\"desc\":\"server create group\",\"public\":true,\"owner\":\"ceshi\",\"members\":[\"ceshi1\",\"ceshi\",\"ceshi2\"]}";
		 System.out.println("jsonBody:"+jsonBody);
		 String result = HttpsUtils.sendSSLRequest(httpUrl, null,jsonBody, HttpsUtils.Method_POST);
		  System.out.println("group receiver message :"+result);
	 }
	 
	
	 
	 
	 
	 
	 
	 /**
	  * 删除群组
	  * @param appkey
	  * @param groupid
	 * @throws NoSuchAlgorithmException 
	 * @throws KeyManagementException 
	 * @throws IOException 
	 * @throws JsonProcessingException 
	  */
	  public static void deleteChatGroups(String appkey,String groupid) throws KeyManagementException, NoSuchAlgorithmException, JsonProcessingException, IOException
	  {
		  
		  String httpUrl="https://a1.easemob.com/"+appkey.replaceFirst("#","/")+"/chatgroups/"+groupid;	  
		  
		  String result=HttpsUtils.sendSSLRequest(httpUrl, null,null, HttpsUtils.Method_DELETE);
		  
			JsonNode resultNode=new ObjectMapper().readTree(result).get("data");
			if(resultNode.has("groupid"))
			{
				System.out.println("groupid:"+resultNode.get("groupid").asText());
				System.out.println("success:"+resultNode.get("success").asText());
			}else{
				System.out.println("error ");
			}
			
			  
			
			
			
		  
	  }
	
	
	
	
	
	
}
