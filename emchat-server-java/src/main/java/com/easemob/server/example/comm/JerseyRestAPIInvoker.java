package com.easemob.server.example.comm;

import java.io.IOException;

import javax.ws.rs.client.Entity;
import javax.ws.rs.client.Invocation;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.NameValuePair;
import org.glassfish.jersey.client.JerseyClient;
import org.glassfish.jersey.client.JerseyWebTarget;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.easemob.server.example.api.RestAPIInvoker;
import com.easemob.server.example.jersey.utils.JerseyUtils;
import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.fasterxml.jackson.databind.node.ObjectNode;

public class JerseyRestAPIInvoker implements RestAPIInvoker {
	
	private static final Logger log = LoggerFactory.getLogger(JerseyRestAPIInvoker.class);

	@Override
	public ResponseWrapper sendRequest(String method, String url, HeaderWrapper header, BodyWrapper body) {
		
		ResponseWrapper responseWrapper = new ResponseWrapper();
		ObjectNode responseNode = JsonNodeFactory.instance.objectNode();

		responseWrapper.setResponseBody(responseNode);
		
		if( !HTTPMethod.METHOD_GET.equalsIgnoreCase(method) && !HTTPMethod.METHOD_POST.equalsIgnoreCase(method) && !HTTPMethod.METHOD_PUT.equalsIgnoreCase(method) && !HTTPMethod.METHOD_DELETE.equalsIgnoreCase(method) ) {
			String msg = MessageTemplate.print(MessageTemplate.UNKNOW_TYPE_MSG, new String[]{method, "HTTP methods"});
			responseWrapper.addError(msg);
		}
		if( StringUtils.isBlank(url) ) {
			String msg = MessageTemplate.print(MessageTemplate.BLANK_OBJ_MSG, new String[]{"Parameter url"});
			responseWrapper.addError(msg);
		}
		if( !JerseyUtils.match("http(s)?://([\\w-]+\\.)+[\\w-]+(/[\\w- ./?%&=]*)?", url) ) {
			String msg = MessageTemplate.print(MessageTemplate.INVAILID_FORMAT_MSG, new String[]{"Parameter url"});
			responseWrapper.addError(msg);
		}
		if( null == header ) {
			String msg = MessageTemplate.print(MessageTemplate.BLANK_OBJ_MSG, new String[]{"Parameter header"});
			responseWrapper.addError(msg);
		}
		if( null != body && !body.validate() ) {
			responseWrapper.addError(MessageTemplate.INVALID_BODY_MSG);
		}
		
		if( responseWrapper.hasError() ) {
			return responseWrapper;
		}
		
		log.debug("=============Request=============");
		log.debug("Method: " + method);
		log.debug("URL: " + url);
		log.debug("Header: " + header);
		log.debug("Body: " + body.getBody());
		log.debug("===========Request End===========");
		
		JerseyClient client = JerseyUtils.getJerseyClient( StringUtils.startsWithIgnoreCase(url, "HTTPS") );
		JerseyWebTarget target = client.target(url);
		
		Invocation.Builder inBuilder = target.request();
		
		buildHeader(inBuilder, header);

        Response response = null;
        Object b = null == body ? null : body.getBody();
        if (HTTPMethod.METHOD_GET.equals(method)) {
            response = inBuilder.get(Response.class);
        } 
        else if (HTTPMethod.METHOD_POST.equals(method)) {
            response = inBuilder.post(Entity.entity(b, MediaType.APPLICATION_JSON), Response.class);
        } 
        else if (HTTPMethod.METHOD_PUT.equals(method)) {
            response = inBuilder.put(Entity.entity(b, MediaType.APPLICATION_JSON), Response.class);
        } 
        else if (HTTPMethod.METHOD_DELETE.equals(method)) {
            response = inBuilder.delete(Response.class);
        }
        responseWrapper.setResponseStatus(response.getStatus());
        
        String responseContent = response.readEntity(String.class);
        ObjectMapper mapper = new ObjectMapper();
        JsonFactory factory = mapper.getFactory();
        JsonParser jp;
		try {
			jp = factory.createParser(responseContent);
			responseNode = mapper.readTree(jp);
			responseWrapper.setResponseBody(responseNode);
		} catch (IOException e) {
			log.error(MessageTemplate.STR2JSON_ERROR_MSG, e);
			responseWrapper.addError(MessageTemplate.STR2JSON_ERROR_MSG);
		}
		
		log.debug("=============Response=============");
		log.debug(responseWrapper.toString());
		log.debug("===========Response End===========");
		return responseWrapper;
	}
	
	private void buildHeader(Invocation.Builder inBuilder, HeaderWrapper header) {
		if ( null != header && !header.getHeaders().isEmpty() ) {
            for (NameValuePair nameValuePair : header.getHeaders()) {
                inBuilder.header(nameValuePair.getName(), nameValuePair.getValue());
            }
        }
	}

}
