package com.easemob.server.example.jersey.apidemo;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.glassfish.jersey.client.JerseyWebTarget;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.easemob.server.example.api.FileAPI;
import com.easemob.server.example.comm.Constants;
import com.easemob.server.example.comm.Roles;
import com.easemob.server.example.jersey.utils.JerseyUtils;
import com.easemob.server.example.jersey.vo.ClientSecretCredential;
import com.easemob.server.example.jersey.vo.Credential;
import com.easemob.server.example.jersey.vo.EndPoints;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

/**
 * REST API Demo : 图片语音文件上传、下载 Jersey2.9实现
 * 
 * Doc URL: https://docs.easemob.com/doku.php?id=start:100serverintegration:40fileoperation
 * 
 * @author Lynch 2014-09-09 Eric23 2016-01-06
 * 
 */
public class EasemobFiles implements FileAPI{

	private static final Logger LOGGER = LoggerFactory.getLogger(EasemobFiles.class);
	private static final String APPKEY = Constants.APPKEY;
	private static final JsonNodeFactory factory = new JsonNodeFactory(false);

    private static Credential credential = new ClientSecretCredential(Constants.APP_CLIENT_ID,
            Constants.APP_CLIENT_SECRET, Roles.USER_ROLE_APPADMIN);

    public static void main(String[] args) {
    	FileAPI fileAPIImpl = new EasemobFiles();
    	String uploadImg = "d:/logo.png";
    	String downloadImg = "d:/logo-download.png";
    	String downloadThumnail = "d:/logo-thumnail.png";
    	
    	// 上传图片文件
        File uploadImgFile = new File(uploadImg);
        ObjectNode imgDataNode = (ObjectNode) fileAPIImpl.uploadFile(uploadImgFile);
        if (null != imgDataNode) {
            LOGGER.info("上传图片文件: " + imgDataNode.toString());
        }
        
        try {
			Thread.sleep(5 * 1000L);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

        // 下载图片文件
        String imgFileUUID = imgDataNode.path("entities").get(0).path("uuid").asText();
        String shareSecret = imgDataNode.path("entities").get(0).path("share-secret").asText();
        ObjectNode downloadImgDataNode = (ObjectNode) fileAPIImpl.downloadFile(imgFileUUID, shareSecret, downloadImg, false);
        if (null != downloadImgDataNode) {
            LOGGER.info("下载图片文件: " + downloadImgDataNode.toString());
        }
        
        try {
			Thread.sleep(5 * 1000L);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

        // 下载缩略图
        ObjectNode downloadThumnailImgDataNode = (ObjectNode) fileAPIImpl.downloadFile(imgFileUUID, shareSecret, downloadThumnail, true);
        if (null != downloadThumnailImgDataNode) {
            LOGGER.info("下载缩略图: " + downloadThumnailImgDataNode.toString());
        }
    }

	public ObjectNode uploadFile(Object file) {
		ObjectNode objectNode = factory.objectNode();
		File uploadFile = null;
		
		// check the input parameter
		try {
			uploadFile = (File) file;
			
			if (!uploadFile.exists() || !uploadFile.isFile()) {
				throw new Exception();
			}
		} catch (Exception e) {
			LOGGER.error("File input is invailid or doesn't exist!");
			objectNode.put("message", "File input is invailid or doesn't exist!");
			return objectNode;
		}

		// check appKey format
		if (!JerseyUtils.match("^(?!-)[0-9a-zA-Z\\-]+#[0-9a-zA-Z]+", APPKEY)) {
			LOGGER.error("Bad format of Appkey: " + APPKEY);
			objectNode.put("message", "Bad format of Appkey");
			return objectNode;
		}
		
		
		try {
			JerseyWebTarget webTarget = EndPoints.CHATFILES_TARGET.resolveTemplate("org_name", APPKEY.split("#")[0]).resolveTemplate(
					"app_name", APPKEY.split("#")[1]);

			List<NameValuePair> headers = new ArrayList<NameValuePair>();
			headers.add(new BasicNameValuePair("restrict-access", "true"));

			objectNode = JerseyUtils.uploadFile(webTarget, uploadFile, credential, headers);

		} catch (Exception e) {
			LOGGER.error("File upload failed: " + e.getMessage());
			e.printStackTrace();
		}

		return objectNode;
	}

	public ObjectNode downloadFile(String fileUUID, String shareSecret, String localPath, Boolean isThumbnail) {
		ObjectNode objectNode = factory.objectNode();
		
		// check appKey format
		if (!JerseyUtils.match("^(?!-)[0-9a-zA-Z\\-]+#[0-9a-zA-Z]+", APPKEY)) {
			LOGGER.error("Bad format of Appkey: " + APPKEY);
			objectNode.put("message", "Bad format of Appkey");
			return objectNode;
		}
		
		// check the input parameters
		if( StringUtils.isEmpty(fileUUID) ){
			LOGGER.error("Invailid parameter of fileUUID, it should not be null.");
			objectNode.put("message", "Invailid parameter of fileUUID, it should not be null.");
			return objectNode;
		}
		if( StringUtils.isEmpty(shareSecret) ){
			LOGGER.error("Invailid parameter of shareSecret, it should not be null.");
			objectNode.put("message", "Invailid parameter of shareSecret, it should not be null.");
			return objectNode;
		}
		if( StringUtils.isEmpty(fileUUID) ){
			LOGGER.error("Invailid parameter of localPath, it should not be null.");
			objectNode.put("message", "Invailid parameter of localPath, it should not be null.");
			return objectNode;
		}
		isThumbnail = null == isThumbnail ? Boolean.FALSE : isThumbnail;

		try {
			JerseyWebTarget webTarget = EndPoints.CHATFILES_TARGET.resolveTemplate("org_name", APPKEY.split("#")[0])
					.resolveTemplate("app_name", APPKEY.split("#")[1]).path(fileUUID);
			List<NameValuePair> headers = new ArrayList<NameValuePair>();
			headers.add(new BasicNameValuePair("share-secret", shareSecret));
			headers.add(new BasicNameValuePair("Accept", "application/octet-stream"));
			if (isThumbnail) {
				headers.add(new BasicNameValuePair("thumbnail", String.valueOf(isThumbnail)));
			}
			JerseyUtils.downLoadFile(webTarget, credential, headers, new File(localPath));
		} catch (Exception e) {
			LOGGER.error("File download failed: " + e.getMessage());
			e.printStackTrace();
		}

		objectNode.put("message", "File download successfully.");
		return objectNode;
	}

}
