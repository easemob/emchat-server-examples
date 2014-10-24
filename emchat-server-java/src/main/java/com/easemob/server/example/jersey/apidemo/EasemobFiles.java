package com.easemob.server.example.jersey.apidemo;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.glassfish.jersey.client.JerseyWebTarget;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.easemob.server.example.comm.Constants;
import com.easemob.server.example.comm.Roles;
import com.easemob.server.example.jersey.utils.JerseyUtils;
import com.easemob.server.example.jersey.vo.Credentail;
import com.easemob.server.example.jersey.vo.EndPoints;
import com.easemob.server.example.jersey.vo.UsernamePasswordCredentail;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * REST API Demo : 图片语音文件上传、下载 Jersey2.9实现
 * 
 * Doc URL: http://www.easemob.com/docs/rest/files/
 * 
 * @author Lynch 2014-09-09
 * 
 */
class EasemobFiles {

	private static Logger LOGGER = LoggerFactory.getLogger(EasemobFiles.class);

	private static final String APPKEY = Constants.APPKEY;

	private static JsonNodeFactory factory = new JsonNodeFactory(false);

	/**
	 * 图片/语音文件上传
	 * 
	 * 
	 * @param appKey
	 * @param host
	 * @param accessToken
	 * @param filePath
	 */
	public static ObjectNode mediaUpload(File uploadFile) {

		ObjectNode objectNode = factory.objectNode();

		if (!uploadFile.exists()) {

			LOGGER.error("file: " + uploadFile.toString() + " is not exist!");

			objectNode.put("message", "File or directory not found");

			return objectNode;
		}

		if (!JerseyUtils.match("^(?!-)[0-9a-zA-Z\\-]+#[0-9a-zA-Z]+", APPKEY)) {
			LOGGER.error("Bad format of Appkey: " + APPKEY);

			objectNode.put("message", "Bad format of Appkey");

			return objectNode;
		}

		try {

			Credentail credentail = new UsernamePasswordCredentail(Constants.APP_ADMIN_USERNAME,
					Constants.APP_ADMIN_PASSWORD, Roles.USER_ROLE_APPADMIN);

			JerseyWebTarget webTarget = null;
			webTarget = EndPoints.CHATFILES_TARGET.resolveTemplate("org_name", APPKEY.split("#")[0]).resolveTemplate(
					"app_name", APPKEY.split("#")[1]);

			List<NameValuePair> headers = new ArrayList<NameValuePair>();
			headers.add(new BasicNameValuePair("restrict-access", "true"));

			objectNode = JerseyUtils.uploadFile(webTarget, uploadFile, credentail, headers);

		} catch (Exception e) {
			e.printStackTrace();
		}

		return objectNode;
	}

	/**
	 * 图片语音文件下载
	 * 
	 * 
	 * @param fileUUID
	 *            文件在DB的UUID
	 * @param shareSecret
	 *            文件在DB中保存的shareSecret
	 * @param localPath
	 *            下载后文件存放地址
	 * @param isThumbnail
	 *            是否下载缩略图 true:缩略图 false:非缩略图
	 * @return
	 */
	public static ObjectNode mediaDownload(String fileUUID, String shareSecret, File localPath, boolean isThumbnail) {

		ObjectNode objectNode = factory.objectNode();

		File downLoadedFile = null;

		if (!JerseyUtils.match("^(?!-)[0-9a-zA-Z\\-]+#[0-9a-zA-Z]+", APPKEY)) {
			LOGGER.error("Bad format of Appkey: " + APPKEY);

			objectNode.put("message", "Bad format of Appkey");

			return objectNode;
		}

		try {

			Credentail credentail = new UsernamePasswordCredentail(Constants.APP_ADMIN_USERNAME,
					Constants.APP_ADMIN_PASSWORD, Roles.USER_ROLE_APPADMIN);

			JerseyWebTarget webTarget = null;
			webTarget = EndPoints.CHATFILES_TARGET.resolveTemplate("org_name", APPKEY.split("#")[0])
					.resolveTemplate("app_name", APPKEY.split("#")[1]).path(fileUUID);
			webTarget = webTarget.queryParam("share-secret", "shareSecret").queryParam("Authorization",
					"Bearer YWMtXTzzLkX_EeSRRA0PhthlrwAAAUnqX7TBUDddVXrfAPHQyGJzZRyRKzGtw8E");
			List<NameValuePair> headers = new ArrayList<NameValuePair>();
			headers.add(new BasicNameValuePair("share-secret", shareSecret));
			headers.add(new BasicNameValuePair("Accept", "application/octet-stream"));
			if (isThumbnail) {
				headers.add(new BasicNameValuePair("thumbnail", "true"));
			}

			downLoadedFile = JerseyUtils.downLoadFile(webTarget, credentail, headers, localPath);

		} catch (Exception e) {
			e.printStackTrace();
		}

		LOGGER.error("File download successfully，file path : " + downLoadedFile.getAbsolutePath() + ".");

		objectNode.put("message", "File download successfully .");

		return objectNode;
	}

	public static void main(String[] args) {
		File localPath = new File("/home/lynch/Downloads/invite3.png");
		String fileUUID = "6f2d3d10-4865-11e4-8de6-33cd2cee963f";
		String shareSecret = "by09GkhlEeSd_lklcKzQoPlFAO608MunnThPSBO5UB2L6rb6";
		/*
		 * { "action" : "post", "application" : "4d7e4ba0-dc4a-11e3-90d5-e1ffbaacdaf5", "path" : "/chatfiles", "uri" :
		 * "https://a1.easemob.com/easemob-demo/chatdemoui/chatfiles", "entities" : [ { "uuid" :
		 * "6f2d3d10-4865-11e4-8de6-33cd2cee963f", "type" : "chatfile", "share-secret" :
		 * "by09GkhlEeSd_lklcKzQoPlFAO608MunnThPSBO5UB2L6rb6" } ], "timestamp" : 1412056122209, "duration" : 3,
		 * "organization" : "easemob-demo", "applicationName" : "chatdemoui" }
		 */
//		mediaDownload(fileUUID, shareSecret, localPath, false);
		
		File uploadFile = new File("/home/lynch/music.MP3");
		mediaUpload(uploadFile);
	}
}
