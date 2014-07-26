package com.easemob.server.example.vo;

import org.glassfish.jersey.client.JerseyWebTarget;

import com.easemob.server.example.jersey.JerseyUtils;

public abstract class Credentail {

	protected static JerseyWebTarget ROOT_TOKEN_TARGET = JerseyUtils.getJerseyClient(true).target(JerseyUtils.BASEURL);

	protected String grantType;
	protected String secretKey;
	protected String secretValue;

	protected Token token;

	protected abstract JerseyWebTarget getTokenRequestTarget();

	protected abstract GrantType getGrantType();

	public static enum GrantType {
		client_credentials, password
	}

	public Credentail() {
	}

	public Credentail(String secretKey, String secretValue) {
		this.secretKey = secretKey;
		this.secretValue = secretValue;
	}

	public Credentail(Token token) {
		this.token = token;
	}

	public abstract Token getToken();

	@Override
	public String toString() {
		return "Credentail [grantType=" + grantType + ", secretKey=" + secretKey + ", secretValue=" + secretValue
				+ ", token=" + token + "]";
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((grantType == null) ? 0 : grantType.hashCode());
		result = prime * result + ((secretKey == null) ? 0 : secretKey.hashCode());
		result = prime * result + ((secretValue == null) ? 0 : secretValue.hashCode());
		result = prime * result + ((token == null) ? 0 : token.hashCode());
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
		Credentail other = (Credentail) obj;
		if (grantType == null) {
			if (other.grantType != null)
				return false;
		} else if (!grantType.equals(other.grantType))
			return false;
		if (secretKey == null) {
			if (other.secretKey != null)
				return false;
		} else if (!secretKey.equals(other.secretKey))
			return false;
		if (secretValue == null) {
			if (other.secretValue != null)
				return false;
		} else if (!secretValue.equals(other.secretValue))
			return false;
		if (token == null) {
			if (other.token != null)
				return false;
		} else if (!token.equals(other.token))
			return false;
		return true;
	}

}
