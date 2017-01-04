package com.hyphenate.server.example.api;

import com.hyphenate.server.example.comm.body.*;

/**
 * This interface is created for RestAPI of Sending Message, it should be
 * synchronized with the API list.
 * 
 * @author Eric23 2016-01-05
 * @see http://docs.hyphenate.io
 */
public interface SendMessageAPI {
	/**
	 * Send message
	 * POST
	 * 
	 * @param payload
	 *            message body
	 * @return
	 * @see MessageBody
	 * @see TextMessageBody
	 * @see ImgMessageBody
	 * @see AudioMessageBody
	 * @see VideoMessageBody
	 * @see CommandMessageBody
	 */
	Object sendMessage(Object payload);
}
