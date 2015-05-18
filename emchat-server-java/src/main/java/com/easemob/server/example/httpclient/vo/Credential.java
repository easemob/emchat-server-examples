package com.easemob.server.example.httpclient.vo;

import java.net.URL;

import org.apache.http.client.HttpClient;

import com.easemob.server.example.httpclient.utils.HTTPClientUtils;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;

/**
 * Credential
 * 
 * @author Lynch 2014-09-15
 *
 */
public abstract class Credential {

	protected String grantType;
	protected String tokenKey1;
	protected String tokenKey2;

	protected JsonNodeFactory factory = new JsonNodeFactory(false);

	protected HttpClient client = HTTPClientUtils.getClient(true);

	protected Token token;

	protected abstract URL getUrl();

	protected abstract GrantType getGrantType();

	public static enum GrantType {
		CLIENT_CREDENTIALS
	}

	public Credential() {
	}

	public Credential(String tokenKey1, String tokenKey2) {
		this.tokenKey1 = tokenKey1;
		this.tokenKey2 = tokenKey2;
	}

	public Credential(Token token) {
		this.token = token;
	}

	public abstract Token getToken();

	@Override
	public String toString() {
		return "Credential [grantType=" + grantType + ", tokenKey1=" + tokenKey1 + ", tokenKey2=" + tokenKey2
				+ ", token=" + token + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((grantType == null) ? 0 : grantType.hashCode());
		result = prime * result + ((token == null) ? 0 : token.hashCode());
		result = prime * result + ((tokenKey1 == null) ? 0 : tokenKey1.hashCode());
		result = prime * result + ((tokenKey2 == null) ? 0 : tokenKey2.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Credential other = (Credential) obj;
		if (grantType == null) {
			if (other.grantType != null)
				return false;
		} else if (!grantType.equals(other.grantType))
			return false;
		if (token == null) {
			if (other.token != null)
				return false;
		} else if (!token.equals(other.token))
			return false;
		if (tokenKey1 == null) {
			if (other.tokenKey1 != null)
				return false;
		} else if (!tokenKey1.equals(other.tokenKey1))
			return false;
		if (tokenKey2 == null) {
			if (other.tokenKey2 != null)
				return false;
		} else if (!tokenKey2.equals(other.tokenKey2))
			return false;
		return true;
	}
}
