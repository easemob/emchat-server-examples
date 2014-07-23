package com.easemob.server.example.vo;

/**
 * Access Token
 * 
 * @author Lynch
 * 
 */
public class Token {

	/** Token String */
	private String accessToken;
	/** token expired at */
	private Long expiredAt;

	public Token() {
	}

	public Token(String accessToken, Long expiredAt) {
		this.accessToken = accessToken;
		this.expiredAt = expiredAt;
	}

	public String getAccessToken() {
		return accessToken;
	}

	public Long getExpiredAt() {
		return expiredAt;
	}

	public boolean isExpired() {
		return System.currentTimeMillis() > expiredAt;
	}

	@Override
	public String toString() {
		return "Token [accessToken=" + accessToken + ", expiredAt=" + expiredAt + "]";
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
