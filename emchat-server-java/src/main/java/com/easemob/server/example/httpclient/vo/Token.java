package com.easemob.server.example.httpclient.vo;

import org.apache.http.client.methods.HttpEntityEnclosingRequestBase;
import org.apache.http.client.methods.HttpRequestBase;

/**
 * Token
 * 
 * @author Lynch 2014-09-15
 *
 */
public class Token {
	private String accessToken;
	private Long expiredAt;

	public Token() {
	}

	public Token(String accessToken, Long expiredAt) {
		this.accessToken = accessToken;
		this.expiredAt = expiredAt;
	}

	public static void applyAuthentication(HttpEntityEnclosingRequestBase httpMethodEntity, Credential credentail) {
		applyAuthentication(httpMethodEntity, credentail.getToken());
	}

	public static void applyAuthentication(HttpEntityEnclosingRequestBase httpMethodEntity, Token token) {
		httpMethodEntity.addHeader("Authorization", "Bearer " + token.toString());
	}

	public static void applyAuthentication(HttpRequestBase httpMethodEntity, Credential credentail) {
		applyAuthentication(httpMethodEntity, credentail.getToken());
	}

	public static void applyAuthentication(HttpRequestBase httpMethodEntity, Token token) {
		httpMethodEntity.addHeader("Authorization", "Bearer " + token.toString());
	}

	public boolean isExpired() {
		return System.currentTimeMillis() > expiredAt;
	}

	@Override
	public String toString() {
		return accessToken;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((accessToken == null) ? 0 : accessToken.hashCode());
		result = prime * result + ((expiredAt == null) ? 0 : expiredAt.hashCode());
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
		Token other = (Token) obj;
		if (accessToken == null) {
			if (other.accessToken != null)
				return false;
		} else if (!accessToken.equals(other.accessToken))
			return false;
		if (expiredAt == null) {
			if (other.expiredAt != null)
				return false;
		} else if (!expiredAt.equals(other.expiredAt))
			return false;
		return true;
	}
}
