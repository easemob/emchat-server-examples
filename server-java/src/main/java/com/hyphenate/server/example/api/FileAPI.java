package com.hyphenate.server.example.api;

/**
 * This interface is created for RestAPI of File Upload and Download, it should
 * be synchronized with the API list.
 * 
 * @author Eric23 2016-01-05
 * @see http://docs.hyphenate.io
 */
public interface FileAPI {

	/**
	 * Upload file <br>
	 * POST
	 * 
	 * @param file
	 *            file type. Ex, address, streaming, etc
	 * @return
	 */
	Object uploadFile(Object file);

	/**
	 * Download file <br>
	 * GET
	 * 
	 * @param fileUUID
	 *            file unique indicator. Obtain it from response parameter, Response-entities-uuid, after file uploaded
	 * @param shareSecret
	 *            file access secret key. Obtain it from response parameter, Response-entities-share-secret, after file uploaded
	 * @param isThumbnail
	 *            if downloading image file
	 * @return
	 */
	Object downloadFile(String fileUUID, String shareSecret, Boolean isThumbnail);
}
