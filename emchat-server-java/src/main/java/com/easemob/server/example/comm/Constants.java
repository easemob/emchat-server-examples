package com.easemob.server.example.comm;

/**
 * Constants
 * 
 * @author Lynch 2014-09-15
 *
 */
public interface Constants {

	// API_HTTP_SCHEMA
	public static String API_HTTP_SCHEMA = "https";
	// API_SERVER_HOST
	public static String API_SERVER_HOST = PropertiesUtils.getProperties().getProperty("API_SERVER_HOST");
	// APPKEY
	public static String APPKEY = PropertiesUtils.getProperties().getProperty("APPKEY");
	// APP_CLIENT_ID
	public static String APP_CLIENT_ID = PropertiesUtils.getProperties().getProperty("APP_CLIENT_ID");
	// APP_CLIENT_SECRET
	public static String APP_CLIENT_SECRET = PropertiesUtils.getProperties().getProperty("APP_CLIENT_SECRET");
    // ORG_ADMIN_USERNAME
    public static String ORG_ADMIN_USERNAME = PropertiesUtils.getProperties().getProperty("ORG_ADMIN_USERNAME");
    // ORG_ADMIN_PASSWORD
    public static String ORG_ADMIN_PASSWORD = PropertiesUtils.getProperties().getProperty("ORG_ADMIN_PASSWORD");
	// DEFAULT_PASSWORD
	public static String DEFAULT_PASSWORD = "1234456";
}
