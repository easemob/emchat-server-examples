package com.easemob.server.example.comm;

/**
 * 
 * @author Lynch 2014-09-15
 *
 */
public interface Constants {

	// API_HTTP_SCHEMA
	public static String API_HTTP_SCHEMA = PropertiesUtils.getProperties().getProperty("API_HTTP_SCHEMA");
	// API_SERVER_HOST
	public static String API_SERVER_HOST = PropertiesUtils.getProperties().getProperty("API_SERVER_HOST");
	// API_SERVER_PORT
	public static String API_SERVER_PORT = PropertiesUtils.getProperties().getProperty("API_SERVER_PORT");
	// APPKEY
	public static String APPKEY = PropertiesUtils.getProperties().getProperty("APPKEY");
	// APP_CLIENT_ID
	public static String APP_CLIENT_ID = PropertiesUtils.getProperties().getProperty("APP_CLIENT_ID");
	// APP_CLIENT_SECRET
	public static String APP_CLIENT_SECRET = PropertiesUtils.getProperties().getProperty("APP_CLIENT_SECRET");
	// APP_ADMIN_USERNAME
	public static String APP_ADMIN_USERNAME = PropertiesUtils.getProperties().getProperty("APP_ADMIN_USERNAME");
	// APP_ADMIN_PASSWORD
	public static String APP_ADMIN_PASSWORD = PropertiesUtils.getProperties().getProperty("APP_ADMIN_PASSWORD");
	// DEFAULT_PASSWORD
	public static String DEFAULT_PASSWORD = "1234456";

	// DataMigration
	// SOURCE_SVR_HOST
	public static String SOURCE_SVR_HOST = PropertiesUtils.getProperties().getProperty("SOURCE_SVR_HOST");
	// TARGET_SVR_HOST
	public static String TARGET_SVR_HOST = PropertiesUtils.getProperties().getProperty("TARGET_SVR_HOST");

}
