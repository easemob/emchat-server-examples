package com.easemob.server.example.httpclient.apidemo;

import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import com.easemob.server.example.httpclient.vo.ClientSecretCredential;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.easemob.server.example.comm.Constants;
import com.easemob.server.example.comm.Roles;
import com.easemob.server.example.httpclient.utils.HTTPClientUtils;
import com.easemob.server.example.httpclient.vo.Credential;
import com.easemob.server.example.httpclient.vo.EndPoints;
import com.easemob.server.example.httpclient.vo.UsernamePasswordCredential;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * REST API Demo : 图片语音文件上传、下载 HttpClient4.3实现
 * 
 * Doc URL: http://www.easemob.com/docs/rest/files/
 * 
 * @author Lynch 2014-09-15
 *
 */
public class EasemobFiles {
	private static Logger LOGGER = LoggerFactory.getLogger(EasemobFiles.class);

	private static final String APPKEY = Constants.APPKEY;

	private static JsonNodeFactory factory = new JsonNodeFactory(false);

    // 通过app的client_id和client_secret来获取app管理员token
    private static Credential credential = new ClientSecretCredential(Constants.APP_CLIENT_ID,
            Constants.APP_CLIENT_SECRET, Roles.USER_ROLE_APPADMIN);

	/**
	 * 图片/语音文件上传
	 * 
	 * @param uploadFile
     *
	 */
	public static ObjectNode mediaUpload(File uploadFile) {

		ObjectNode objectNode = factory.objectNode();

		if (!uploadFile.exists()) {

			LOGGER.error("file: " + uploadFile.toString() + " is not exist!");

			objectNode.put("message", "File or directory not found");

			return objectNode;
		}

		if (!HTTPClientUtils.match("^(?!-)[0-9a-zA-Z\\-]+#[0-9a-zA-Z]+", APPKEY)) {
			LOGGER.error("Bad format of Appkey: " + APPKEY);

			objectNode.put("message", "Bad format of Appkey");

			return objectNode;
		}

		try {

			List<NameValuePair> headers = new ArrayList<NameValuePair>();
			headers.add(new BasicNameValuePair("restrict-access", "true"));

			objectNode = HTTPClientUtils.uploadFile(EndPoints.CHATFILES_URL, uploadFile, credential, headers);

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

		if (!HTTPClientUtils.match("^(?!-)[0-9a-zA-Z\\-]+#[0-9a-zA-Z]+", APPKEY)) {
			LOGGER.error("Bad format of Appkey: " + APPKEY);

			objectNode.put("message", "Bad format of Appkey");

			return objectNode;
		}

		try {

			List<NameValuePair> headers = new ArrayList<NameValuePair>();
			if (!StringUtils.isEmpty(shareSecret)) {
				headers.add(new BasicNameValuePair("share-secret", shareSecret));
			}
			headers.add(new BasicNameValuePair("Accept", "application/octet-stream"));
			if (isThumbnail) {
				headers.add(new BasicNameValuePair("thumbnail", "true"));
			}

			URL mediaDownloadUrl = HTTPClientUtils
					.getURL(Constants.APPKEY.replace("#", "/") + "/chatfiles/" + fileUUID);
			downLoadedFile = HTTPClientUtils.downLoadFile(mediaDownloadUrl, credential, headers, localPath);

		} catch (Exception e) {
			e.printStackTrace();
		}

		LOGGER.error("File download successfully，file path : " + downLoadedFile.getAbsolutePath() + ".");

		objectNode.put("message", "File download successfully .");

		return objectNode;
	}

	public static void main(String[] args) {
		File uploadFile = new File("/home/lynch/Pictures/916135926.png");
		// mediaUpload(uploadFile);

		/**
		 * { "action" : "post", "application" : "2962b340-0a3b-11e4-b21b-d3b66dbe207b", "params" : { }, "path" :
		 * "/chatfiles", "uri" : "https://a1.easemob.com/belo/chatapp/chatfiles", "entities" : [ { "uuid" :
		 * "db1c8dd0-3da4-11e4-8698-b71c9a7d7d34", "type" : "chatfile", "share-secret" :
		 * "2xy04D2kEeSK5Y1QS-hzJjCR_YvxVlXBXTJqJJSxHYbCdvtu" } ], "timestamp" : 1410873898797, "duration" : 109,
		 * "organization" : "belo", "applicationName" : "chatapp" }
		 */
		String fileUUID = "a9218f70-3da3-11e4-a601-63425f17dd60";
		File localPath = new File("/home/lynch/Pictures/1111.png");
		String shareSecret = "2xy04D2kEeSK5Y1QS-hzJjCR_YvxVlXBXTJqJJSxHYbCdvtu";
		mediaDownload(fileUUID, shareSecret, localPath, false);
	}
}
