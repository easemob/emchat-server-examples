package com.hyphenate.server.example.comm;

import com.hyphenate.server.example.api.HyphenateRestAPI;
import com.hyphenate.server.example.api.RestAPIInvoker;
import com.hyphenate.server.example.comm.invoker.HttpClientRestAPIInvoker;
import com.hyphenate.server.example.comm.invoker.JerseyRestAPIInvoker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HyphenateRestAPIFactory {
	
	public static final String TOKEN_CLASS = "HyphenateAuthToken";
	
	public static final String USER_CLASS = "HyphenateIMUsers";
	
	public static final String FILE_CLASS = "HyphenateFile";
	
	public static final String MESSAGE_CLASS = "HyphenateChatMessage";
	
	public static final String SEND_MESSAGE_CLASS = "HyphenateSendMessage";
	
	public static final String CHATGROUP_CLASS = "HyphenateChatGroup";
	
	public static final String CHATROOM_CLASS = "HyphenateChatRoom";
	
	private static final String BASE_PACKAGE = "com.hyphenate.server.example.api.impl";
	
	private static final String METHOD_SET_CONTEXT = "setContext";
	
	private static final String METHOD_SET_INVOKER = "setInvoker";
	
	private static final Logger log = LoggerFactory.getLogger(HyphenateRestAPIFactory.class);
	
	private static HyphenateRestAPIFactory factory;
	
	private ClientContext context;
	
	private RestAPIInvoker jersey = new JerseyRestAPIInvoker();
	
	private RestAPIInvoker httpclient = new HttpClientRestAPIInvoker();

	private HyphenateRestAPIFactory(ClientContext context) {
		this.context = context;
	}
	
	public static HyphenateRestAPIFactory getInstance(ClientContext context) {
		if( null == factory ) {
			if( null == context || !context.isInitialized() ) {
				log.warn(MessageTemplate.INVAILID_CONTEXT_MSG);
				log.warn(MessageTemplate.AUTOMATIC_CONTEXT_MSG);
				context = ClientContext.getInstance().init(ClientContext.INIT_FROM_PROPERTIES);
				
				if( !context.isInitialized() ) {
					log.error(MessageTemplate.INVAILID_CONTEXT_MSG);
					throw new RuntimeException(MessageTemplate.INVAILID_CONTEXT_MSG);
				}
			}
			
			factory = new HyphenateRestAPIFactory(context);
		}
		
		return factory;
	}
	
	public HyphenateRestAPI newInstance(String className) {
		
		String impLib = context.getImpLib();
		
		if( !ClientContext.JERSEY_API.equals(impLib) && !ClientContext.HTTPCLIENT_API.equals(impLib) ) {
			String msg = MessageTemplate.print(MessageTemplate.UNKNOWN_TYPE_MSG, new String[]{impLib, "restapi implementation"});
			log.error(msg);
			throw new RuntimeException(msg);
		}
		
		return (HyphenateRestAPI)getClassInstance(className);
	}
	
	private Object getClassInstance(String className) {
		Class<?> targetClass = null;
		Object newObj = null;
		
		try {
			targetClass = Class.forName(BASE_PACKAGE + "." + className);
			newObj = targetClass.newInstance();
		} catch (Exception e) {
			String msg = MessageTemplate.print(MessageTemplate.ERROR_CLASS_MSG, new String[]{className});
			log.error(msg, e);
			throw new RuntimeException(msg);
		}
		
		// Inject the context and invoker, they are defined in HyphenateRestAPI
		try {
			targetClass.getMethod(METHOD_SET_CONTEXT, ClientContext.class).invoke(newObj, this.context);
		} catch (Exception e) {
			String msg = MessageTemplate.print(MessageTemplate.ERROR_METHOD_MSG, new String[]{METHOD_SET_CONTEXT});
			log.error(msg, e);
			throw new RuntimeException(msg);
		}
		
		RestAPIInvoker invoker = null;
		if( ClientContext.HTTPCLIENT_API.equals(context.getImpLib()) ) {
			invoker = httpclient;
		}
		else {
			invoker = jersey;
		}
		try {
			targetClass.getMethod(METHOD_SET_INVOKER, RestAPIInvoker.class).invoke(newObj, invoker);
		} catch (Exception e) {
			String msg = MessageTemplate.print(MessageTemplate.ERROR_METHOD_MSG, new String[]{METHOD_SET_INVOKER});
			log.error(msg, e);
			throw new RuntimeException(msg);
		}
		
		return newObj;
	}

	public ClientContext getContext() {
		return context;
	}
	
}
