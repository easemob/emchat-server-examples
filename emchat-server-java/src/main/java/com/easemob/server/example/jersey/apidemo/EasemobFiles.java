package com.easemob.server.example.jersey.apidemo;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import com.easemob.server.example.jersey.vo.ClientSecretCredential;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.glassfish.jersey.client.JerseyWebTarget;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.easemob.server.example.comm.Constants;
import com.easemob.server.example.comm.Roles;
import com.easemob.server.example.jersey.utils.JerseyUtils;
import com.easemob.server.example.jersey.vo.Credential;
import com.easemob.server.example.jersey.vo.EndPoints;
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
public class EasemobFiles {

	private static final Logger LOGGER = LoggerFactory.getLogger(EasemobFiles.class);

	private static final String APPKEY = Constants.APPKEY;

	private static final JsonNodeFactory factory = new JsonNodeFactory(false);

    // 通过app的client_id和client_secret来获取app管理员token
    private static Credential credential = new ClientSecretCredential(Constants.APP_CLIENT_ID,
            Constants.APP_CLIENT_SECRET, Roles.USER_ROLE_APPADMIN);

    public static void main(String[] args) {
        /**
         * 上传图片文件
         * curl示例
         * curl --verbose --header "Authorization: Bearer {token}" --header "restrict-access:true" --form file=@/Users/stliu/a.jpg
         * https://a1.easemob.com/easemob-playground/test1/chatfiles
         */
        File uploadImgFile = new File("/tmp/24849.jpg");
        ObjectNode imgDataNode = mediaUpload(uploadImgFile);
        if (null != imgDataNode) {
            LOGGER.info("上传图片文件: " + imgDataNode.toString());
        }
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
        }

        /**
         * 下载图片文件
         * curl示例
         * curl -O -H "share-secret: " --header "Authorization: Bearer {token}" -H "Accept: application/octet-stream"
         * http://a1.easemob.com/easemob-playground/test1/chatfiles/0c0f5f3a-e66b-11e3-8863-f1c202c2b3ae
         */
        String imgFileUUID = imgDataNode.path("entities").get(0).path("uuid").asText();
        String shareSecret = imgDataNode.path("entities").get(0).path("share-secret").asText();
        File downloadedImgFileLocalPath = new File(uploadImgFile.getPath().substring(0, uploadImgFile.getPath().lastIndexOf(".")) + "-1.jpg");
        boolean isThumbnail = false;
        ObjectNode downloadImgDataNode = mediaDownload(imgFileUUID, shareSecret, downloadedImgFileLocalPath, isThumbnail);
        if (null != downloadImgDataNode) {
            LOGGER.info("下载图片文件: " + downloadImgDataNode.toString());
        }
        try {
            Thread.sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
        }

        /**
         * 下载缩略图
         * curl示例
         * curl -O -H "thumbnail: true" -H "share-secret: {secret}" -H "Authorization: Bearer {token}" -H "Accept: application/octet-stream"
         * http://a1.easemob.com/easemob-playground/test1/chatfiles/0c0f5f3a-e66b-11e3-8863-f1c202c2b3ae
         */
        File downloadedLocalPathThumnailImg = new File(uploadImgFile.getPath().substring(0, uploadImgFile.getPath().lastIndexOf(".")) + "-2.jpg");
        isThumbnail = true;
        ObjectNode downloadThumnailImgDataNode = mediaDownload(imgFileUUID, shareSecret, downloadedLocalPathThumnailImg, isThumbnail);
        if (null != downloadThumnailImgDataNode) {
            LOGGER.info("下载缩略图: " + downloadThumnailImgDataNode.toString());
        }

        /**
         * 上传语音文件
         * curl示例
         * curl --verbose --header "Authorization: Bearer {token}" --header "restrict-access:true" --form file=@/Users/stliu/music.MP3
         * https://a1.easemob.com/easemob-playground/test1/chatfiles
         */
        File uploadAudioFile = new File("/tmp/music.MP3");
        ObjectNode audioDataNode = mediaUpload(uploadAudioFile);
        if (null != audioDataNode) {
            LOGGER.info("上传语音文件: " + audioDataNode.toString());
        }

        try {
            Thread.sleep(1000);
        } catch (Exception e) {
            e.printStackTrace();
        }

        /**
         * 下载语音文件
         * curl示例
         * curl -O -H "share-secret: {secret}" --header "Authorization: Bearer {token}" -H "Accept: application/octet-stream"
         * http://a1.easemob.com/easemob-playground/test1/chatfiles/0c0f5f3a-e66b-11e3-8863-f1c202c2b3ae
         */
        String audioFileUUID = audioDataNode.path("entities").get(0).path("uuid").asText();
        String audioFileShareSecret = audioDataNode.path("entities").get(0).path("share-secret").asText();
        File audioFileLocalPath = new File(uploadAudioFile.getPath().substring(0, uploadAudioFile.getPath().lastIndexOf(".")) + "-1.MP3");
        ObjectNode downloadAudioDataNode = mediaDownload(audioFileUUID, audioFileShareSecret, audioFileLocalPath, null);
        if (null != downloadAudioDataNode) {
            LOGGER.info("下载语音文件: " + downloadAudioDataNode.toString());
        }
    }

    /**
	 * 图片/语音文件上传
	 * 
	 * @param uploadFile
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
			JerseyWebTarget webTarget = EndPoints.CHATFILES_TARGET.resolveTemplate("org_name", APPKEY.split("#")[0]).resolveTemplate(
					"app_name", APPKEY.split("#")[1]);

			List<NameValuePair> headers = new ArrayList<NameValuePair>();
			headers.add(new BasicNameValuePair("restrict-access", "true"));

			objectNode = JerseyUtils.uploadFile(webTarget, uploadFile, credential, headers);

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
	public static ObjectNode mediaDownload(String fileUUID, String shareSecret, File localPath, Boolean isThumbnail) {
		ObjectNode objectNode = factory.objectNode();
		File downLoadedFile = null;
		if (!JerseyUtils.match("^(?!-)[0-9a-zA-Z\\-]+#[0-9a-zA-Z]+", APPKEY)) {
			LOGGER.error("Bad format of Appkey: " + APPKEY);
			objectNode.put("message", "Bad format of Appkey");
			return objectNode;
		}

		try {
			JerseyWebTarget webTarget = EndPoints.CHATFILES_TARGET.resolveTemplate("org_name", APPKEY.split("#")[0])
					.resolveTemplate("app_name", APPKEY.split("#")[1]).path(fileUUID);
			List<NameValuePair> headers = new ArrayList<NameValuePair>();
			headers.add(new BasicNameValuePair("share-secret", shareSecret));
			headers.add(new BasicNameValuePair("Accept", "application/octet-stream"));
			if (isThumbnail != null && isThumbnail) {
				headers.add(new BasicNameValuePair("thumbnail", String.valueOf(isThumbnail)));
			}
			downLoadedFile = JerseyUtils.downLoadFile(webTarget, credential, headers, localPath);
            LOGGER.error("File download successfully，file path : " + downLoadedFile.getAbsolutePath() + ".");
		} catch (Exception e) {
			e.printStackTrace();
		}

		objectNode.put("message", "File download successfully .");
		return objectNode;
	}

}
