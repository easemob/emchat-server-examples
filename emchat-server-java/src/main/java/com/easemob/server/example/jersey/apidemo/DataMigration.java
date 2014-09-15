package com.easemob.server.example.jersey.apidemo;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.glassfish.jersey.client.JerseyWebTarget;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.easemob.server.example.comm.Constants;
import com.easemob.server.example.comm.HTTPMethod;
import com.easemob.server.example.comm.PropertiesUtils;
import com.easemob.server.example.jersey.utils.JerseyUtils;
import com.easemob.server.example.jersey.vo.Credentail;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * 数据迁移:可以实现跨Server/ORG/APP做数据迁移
 * 
 * @author Lynch 2014-07-18
 * 
 */
public class DataMigration {

	private static Logger LOGGER = LoggerFactory.getLogger(DataMigration.class);

	private static JsonNodeFactory factory = new JsonNodeFactory(false);

	private static String APPKEY = Constants.APPKEY;

	private static String OLD_SVR_HOST = PropertiesUtils.getProperties().getProperty("OLD_SVR_HOST");

	private static String NEW_SVR_HOST = PropertiesUtils.getProperties().getProperty("NEW_SVR_HOST");

	/**
	 * 从就服务器上获取数据
	 * 
	 * 
	 * @param APPKEY
	 * @param host
	 * @param accessToken
	 * @param filePath
	 */
	public static ObjectNode getDataFromOldSvr(Credentail credentail, String accessToken) {

		ObjectNode objectNode = factory.objectNode();

		if (!JerseyUtils.match("[0-9a-zA-Z]+#[0-9a-zA-Z]+", APPKEY)) {
			LOGGER.error("Bad format of APPKEY: " + APPKEY);

			objectNode.put("message", "Bad format of APPKEY");

			return objectNode;
		}

		// 数据量大于1000的时候从第二次起用这个URL,加上游标
		// String reqURL = "https://" + host + "/" + APPKEY.replaceFirst("#", "/")
		// + "/users?limit=1000&cursor=LTU2ODc0MzQzOlZudXctdFdmRWVPNG5fUHFEbFJ5dHc";

		// 数据量小于1000用这个URL或者大于1000的场景第一次也用这个URL
		// String reqURL = "https://" + host + "/" + APPKEY.replaceFirst("#", "/") + "/users?limit=1000";

		List<NameValuePair> headers = new ArrayList<NameValuePair>();
		headers.add(new BasicNameValuePair("Content-Type", "application/json"));

		JerseyWebTarget target = JerseyUtils.getJerseyClient(true).target("");
		JsonNode sendResponse = JerseyUtils.sendRequest(target, null, credentail, HTTPMethod.METHOD_GET, headers);

		return objectNode;
	}

	/**
	 * 注册到新服务器上
	 * 
	 * 
	 * @param APPKEY
	 * @param host
	 * @param accessToken
	 * @param filePath
	 */
	public static ObjectNode postDataToNewSvr(String APPKEY, String host, Credentail credentail, JsonNode entitiesPure) {

		ObjectNode objectNode = factory.objectNode();

		if (!JerseyUtils.match("[0-9a-zA-Z]+#[0-9a-zA-Z]+", APPKEY)) {
			LOGGER.error("Bad format of APPKEY: " + APPKEY);

			objectNode.put("message", "Bad format of APPKEY");

			return objectNode;
		}

		String reqURL = "http://" + host + "/" + APPKEY.replaceFirst("#", "/") + "/users";

		List<NameValuePair> headers = new ArrayList<NameValuePair>();
		headers.add(new BasicNameValuePair("Content-Type", "application/json"));

		JerseyWebTarget target = JerseyUtils.getJerseyClient(true).target(reqURL);
		JsonNode sendResponse = JerseyUtils.sendRequest(target, entitiesPure, credentail, HTTPMethod.METHOD_POST,
				headers);

		return objectNode;
	}

}
