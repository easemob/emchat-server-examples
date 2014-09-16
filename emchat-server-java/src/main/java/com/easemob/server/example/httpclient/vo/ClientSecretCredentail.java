package com.easemob.server.example.httpclient.vo;

import java.net.URL;

/**
 * 
 * @author lynch
 *
 */
public class ClientSecretCredentail extends Credentail {

	@Override
	protected URL getUrl() {
		return null;
	}

	@Override
	protected GrantType getGrantType() {
		return null;
	}

	@Override
	public Token getToken() {
		return null;
	}

}
