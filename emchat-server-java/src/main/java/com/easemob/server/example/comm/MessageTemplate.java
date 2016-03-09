package com.easemob.server.example.comm;

public class MessageTemplate {

	public static final String BLANK_OBJ_MSG = "{0} should not be null or empty.";
	public static final String UNKNOW_TYPE_MSG = "{0} is an unknow type of {1}.";
	public static final String FILE_ACCESS_MSG = "{0} is not existed or could not be accessed.";
	public static final String INVAILID_PROPERTIES_MSG = "{0} has one or more invailid | missed properties.";
	public static final String INVAILID_FORMAT_MSG = "{0} doesn't match the required format.";
	public static final String INVAILID_CONTEXT_MSG = "Context is null or has not been initialized.";
	public static final String AUTOMATIC_CONTEXT_MSG = "Try to build context via file automatically.";
	public static final String ERROR_CLASS_MSG = "Generating service instance {0} failed.";
	public static final String ERROR_METHOD_MSG = "Injecting service method {0} failed.";
	public static final String STR2JSON_ERROR_MSG = "Parsing string to json object failed.";
	public static final String INVALID_BODY_MSG = "Request body is invalid.";
	public static final String INVAILID_TOKEN_MSG = "Token generator is null or has not been initialized.";
	public static final String REFRESH_TOKEN_MSG = "Token has been refreshed with {0} and will be expired at {1}.";
	public static final String REFRESH_TOKEN_ERROR_MSG = "Initializing token failed, return current token by default.";
	
	public static String print(String template, String[] values) {
		if( null == values || values.length == 0 ) {
			return template;
		}
		
		String msg = template;
		for ( int i=0; i<values.length; i++ ) {
			msg = msg.replace("{" + i + "}", values[i]);
		}
		
		return msg;
	}
	
	public static void main(String[] args) {
		System.out.println(MessageTemplate.print(BLANK_OBJ_MSG, new String[]{"UserName"}));
		System.out.println(MessageTemplate.print(UNKNOW_TYPE_MSG, new String[]{"type1", "types"}));
	}
}
