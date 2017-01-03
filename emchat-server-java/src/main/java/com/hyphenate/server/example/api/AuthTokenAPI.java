package com.hyphenate.server.example.api;

public interface AuthTokenAPI {	

	/**
	 * Request an Authentication Token <br>
	 * POST
	 *
	 * @param clientId
	 *            can be found in the application details page of the Hyphenate console
	 * @param clientSecret
	 *            can be found in the application details page of the Hyphenate console
	 * @return
	 */
	Object getAuthToken(String clientId, String clientSecret);
}
