package com.hyphenate.server.example.api;

/**
 * This interface is created for RestAPI of Chat Messages, it should be
 * synchronized with the API list.
 *
 * @author Hyphenate
 * @see http://docs.hyphenate.io
 */
public interface ChatMessageAPI {
	/**
	 * Get Message History
	 *
	 * GET
	 * 
	 * @param limit
	 *            total number of messages per page by pagination at a time
	 * @param cursor
	 *            Get messages by pagination. Obtained "cursor" from the previous GET messages call response.
	 * @param query
	 *            query <code>ql=select * where timestamp>1403164734226</code>
	 * @return
	 */
	Object exportChatMessages(Long limit, String cursor, String query);
}
