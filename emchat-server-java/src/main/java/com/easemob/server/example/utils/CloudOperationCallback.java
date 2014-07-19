package com.easemob.server.example.utils;

public interface CloudOperationCallback {

	public void onSuccess(String result);
	public void onError(String msg);
	public void onProgress(int progress);
}
