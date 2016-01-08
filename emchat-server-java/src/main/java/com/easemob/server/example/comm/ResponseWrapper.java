package com.easemob.server.example.comm;

import java.util.ArrayList;
import java.util.List;

public class ResponseWrapper {
	private static final String WARNING = "[WARNING]: ";
	private static final String ERROR = "[ERROR]: ";

	private List<String> messages = new ArrayList<String>();
	private Object responseBody;
	private Boolean hasError;

	public static ResponseWrapper getInstance() {
		return new ResponseWrapper();
	}

	public List<String> getMessages() {
		return messages;
	}

	public void addWarning(String warning) {
		messages.add(WARNING + warning);
	}

	public void addError(String error) {
		messages.add(ERROR + error);
		hasError = Boolean.TRUE;
	}

	public Boolean hasError() {
		return hasError;
	}

	public Object getResponseBody() {
		return responseBody;
	}

	public void setResponseBody(Object responseBody) {
		this.responseBody = responseBody;
	}

}
