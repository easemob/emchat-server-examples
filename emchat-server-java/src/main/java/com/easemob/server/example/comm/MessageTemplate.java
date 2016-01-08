package com.easemob.server.example.comm;

public class MessageTemplate {

	public static final String BLANK_STRING_MSG = "{0} should not be null or empty.";
	public static final String UNKNOW_TYPE_MSG = "{0} is an unknow type of {1}.";
	public static final String FILE_ACCESS_MSG = "{0} is not existed or could not be accessed.";
	public static final String INVAILID_PROPERTIES_MSG = "{0} has one or more invailid | missed properties.";
	
	public static String print(String template, String[] values) {
		String msg = null;
		for ( int i=0; i<values.length; i++ ) {
			msg = template.replace("{" + i + "}", values[i]);
		}
		
		return msg;
	}
	
	public static void main(String[] args) {
		System.out.println(MessageTemplate.print(BLANK_STRING_MSG, new String[]{"UserName"}));
	}
}
