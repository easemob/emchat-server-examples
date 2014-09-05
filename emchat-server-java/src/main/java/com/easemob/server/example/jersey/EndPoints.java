package com.easemob.server.example.jersey;

import org.glassfish.jersey.client.JerseyClient;
import org.glassfish.jersey.client.JerseyWebTarget;

import com.easemob.server.example.utils.Constants;
import com.easemob.server.example.utils.JerseyUtils;

/**
 * JerseyWebTarget EndPoints
 * 
 * @author Lynch
 *
 */
public interface EndPoints {

	public final JerseyClient CLIENT = JerseyUtils.getJerseyClient(true);

	public final JerseyWebTarget ROOT_TARGET = CLIENT.target(Constants.API_HTTP_SCHEMA + "://"
			+ Constants.API_SERVER_HOST + "/");

	public JerseyWebTarget MANAGEMENT_TARGET = ROOT_TARGET.path("management");

	public JerseyWebTarget TOKEN_ORG_TARGET = MANAGEMENT_TARGET.path("token");

	JerseyWebTarget APPLICATION_TEMPLATE = ROOT_TARGET.path("{org_name}").path("{app_name}");

	public JerseyWebTarget TOKEN_APP_TARGET = APPLICATION_TEMPLATE.path("token");

	public JerseyWebTarget USERS_TARGET = APPLICATION_TEMPLATE.path("users");

	public JerseyWebTarget MESSAGES_TARGET = APPLICATION_TEMPLATE.path("messages");

	public JerseyWebTarget CHATMESSAGES_TARGET = APPLICATION_TEMPLATE.path("chatmessages");

	public JerseyWebTarget CHATFILES_TARGET = APPLICATION_TEMPLATE.path("chatfiles");
}
