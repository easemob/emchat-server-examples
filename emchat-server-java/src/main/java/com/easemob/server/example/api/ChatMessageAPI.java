package com.easemob.server.example.api;

/**
 * This interface is created for RestAPI of Chat Messages, it should be
 * synchronized with the API list.
 *
 * @author Eric23 2016-01-05
 * @see http://docs.easemob.com/
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
	 *
	 * 此接口已经过期，下个版本将会提供新接口
	 */
	Object exportChatMessages(Long limit, String cursor, String query);

    /**
     * 获取聊天记录下载链接
     * GET
     * @param timeStr
     *              目标聊天记录的时间
     * @return
     */
	Object exportChatMessages(String timeStr);
}
