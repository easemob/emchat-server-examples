package com.easemob.server.example.api;

/**
 * This interface is created for RestAPI of Chat Messages, it should be
 * synchronized with the API list.
 * 
 * @author Eric23 2016-01-05
 * @see http://docs.easemob.com/doku.php?id=start:100serverintegration:30chatlog
 */
public interface ChatMessageAPI {
	/**
	 * 导出聊天记录，默认返回10条 <br>
	 * GET
	 * 
	 * @param limit
	 *            单页条数，最多1000
	 * @param cursor
	 *            游标，存在更多页时产生
	 * @param query
	 *            查询语句 <code>ql=select * where timestamp>1403164734226</code>
	 * @return
	 */
	Object exportChatMessages(Long limit, String cursor, String query);
}
