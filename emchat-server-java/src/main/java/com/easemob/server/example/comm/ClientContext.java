package com.easemob.server.example.comm;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ClientContext {
	
	/*
	 * Configuration Source Type
	 */
	public static final String INIT_FROM_PROPERTIES = "FILE";
	
	public static final String INIT_FROM_CLASS = "CLASS";
	
	/*
	 * Implementation List
	 */
	public static final String JERSEY_API = "jersey";
	
	public static final String HTTPCLIENT_API = "httpclient";
	
	/*
	 * Properties
	 */
	private static final String API_PROTOCAL_KEY = "API_PROTOCAL";
	
	private static final String API_HOST_KEY = "API_HOST";
	
	private static final String API_ORG_KEY = "API_ORG";
	
	private static final String API_APP_KEY = "API_APP";
	
	private static final String APP_CLIENT_ID_KEY = "APP_CLIENT_ID";
	
	private static final String APP_CLIENT_SECRET_KEY = "APP_CLIENT_SECRET";
	
	private static final String APP_IMP_LIB_KEY = "APP_IMP_LIB";
	
	private static final Logger log = LoggerFactory.getLogger(ClientContext.class);
	
	private static ClientContext context;
		
	private Boolean initialized = Boolean.FALSE;
	
	private String protocal;
	
	private String host;
	
	private String org;
	
	private String app;
	
	private String clientId;
	
	private String clientSecret;
	
	private String impLib;
	
	private EasemobRestAPIFactory factory;
	
	private TokenGenerator token; // Wrap the token generator
	
	private ClientContext() {};
	
	public static ClientContext getInstance() {
		if( null == context ) {
			context = new ClientContext();
		}
		
		return context;
	}
	
	public ClientContext init(String type) {
		if( initialized ) {
			log.warn("Context has been initialized already, skipped!");
			return context;
		}
		
		if( StringUtils.isBlank(type) ) {
			log.warn("Context initialization type was set to FILE by default.");
			type = INIT_FROM_PROPERTIES;
		}
		
		if( INIT_FROM_PROPERTIES.equals(type) ) {
			initFromPropertiesFile();
		} 
		else if( INIT_FROM_CLASS.equals(type) ){
			initFromStaticClass();
		} 
		else {
			log.error(MessageTemplate.print(MessageTemplate.UNKNOW_TYPE_MSG, new String[]{type, "context initialization"}));
			return context; // Context not initialized
		}
		
		// Initialize the token generator by default
		if( context.initialized ) {
			token = new TokenGenerator(context);
		}
		
		return context;
	}
	
	public EasemobRestAPIFactory getAPIFactory() {
		if( !context.isInitialized() ) {
			log.error(MessageTemplate.INVAILID_CONTEXT_MSG);
			throw new RuntimeException(MessageTemplate.INVAILID_CONTEXT_MSG);
		}
		
		if( null == this.factory ) {
			this.factory = EasemobRestAPIFactory.getInstance(context);
		}
		
		return this.factory;
	}
	
	public String getSeriveURL() {
		if (null == context || !context.isInitialized()) {
			log.error(MessageTemplate.INVAILID_CONTEXT_MSG);
			throw new RuntimeException(MessageTemplate.INVAILID_CONTEXT_MSG);
		}

		String serviceURL = context.getProtocal() + "://" + context.getHost() + "/" + context.getOrg() + "/" + context.getApp();

		return serviceURL;
	}
	
	public String getAuthToken() {
		if( null == token ) {
			log.error(MessageTemplate.INVAILID_TOKEN_MSG);
			throw new RuntimeException(MessageTemplate.INVAILID_TOKEN_MSG);
		}
		
		return token.request(Boolean.FALSE);
	}
	
	private void initFromPropertiesFile() {
		Properties p = new Properties();

		try {
			InputStream inputStream = ClientContext.class.getClassLoader().getResourceAsStream("config.properties");
			p.load(inputStream);
		} catch (IOException e) {
			log.error(MessageTemplate.print(MessageTemplate.FILE_ACCESS_MSG, new String[]{"config.properties"}));
			return; // Context not initialized
		}
		
		String protocal = p.getProperty(API_PROTOCAL_KEY);
		String host = p.getProperty(API_HOST_KEY);
		String org = p.getProperty(API_ORG_KEY);
		String app = p.getProperty(API_APP_KEY);
		String clientId = p.getProperty(APP_CLIENT_ID_KEY);
		String clientSecret = p.getProperty(APP_CLIENT_SECRET_KEY);
		String impLib = p.getProperty(APP_IMP_LIB_KEY);
		
		if( StringUtils.isBlank(protocal) || StringUtils.isBlank(host) || StringUtils.isBlank(org) || StringUtils.isBlank(app) || StringUtils.isBlank(clientId) || StringUtils.isBlank(clientSecret) || StringUtils.isBlank(impLib) ) {
			log.error(MessageTemplate.print(MessageTemplate.INVAILID_PROPERTIES_MSG, new String[]{"config.properties"}));
			return; // Context not initialized
		}
		
		context.protocal = protocal;
		context.host = host;
		context.org = org;
		context.app = app;
		context.clientId = clientId;
		context.clientSecret = clientSecret;
		context.impLib = impLib;
		
		log.debug("protocal: " + context.protocal);
		log.debug("host: " + context.host);
		log.debug("org: " + context.org);
		log.debug("app: " + context.app);
		log.debug("clientId: " + context.clientId);
		log.debug("clientSecret: " + context.clientSecret);
		log.debug("impLib: " + context.impLib);
		
		initialized = Boolean.TRUE;
	}
	
	private ClientContext initFromStaticClass() {
		// TODO
		return null;
	}

	public String getProtocal() {
		return protocal;
	}

	public String getHost() {
		return host;
	}

	public String getOrg() {
		return org;
	}

	public String getApp() {
		return app;
	}

	public String getClientId() {
		return clientId;
	}

	public String getClientSecret() {
		return clientSecret;
	}
	
	public Boolean isInitialized() {
		return initialized;
	}
	
	public String getImpLib() {
		return impLib;
	}
	
	public static void main(String[] args) {
		ClientContext.getInstance().init(null);
	}
	
}
