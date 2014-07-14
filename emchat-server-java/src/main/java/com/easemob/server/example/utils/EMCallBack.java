package com.easemob.server.example.utils;

public interface EMCallBack {

	public final static int ERROR_EXCEPTION=-1;
	public final static int ERROR_SEND =-2;
	public final static int ERROR_FILE_NOT_FOUND=-3;
	
	public void onSuccess();
	public void onError(int code,String message);
	public void onProgress(int progress,String status);
	
	
	/**
	 * the addition data returned from callback
	 * it maybe null
	 */
	public Object data=null;
	
	
	
}
