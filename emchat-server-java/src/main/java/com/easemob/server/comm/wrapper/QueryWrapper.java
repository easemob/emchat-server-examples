package com.easemob.server.comm.wrapper;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;

public class QueryWrapper {
	
	private static final String LIMIT = "limit";
	
	private static final String CURSOR = "cursor";

	private static final String QUERY = "ql";
	
	private List<NameValuePair> queries = new ArrayList<NameValuePair>();
	
	public static QueryWrapper newInstance() {
		return new QueryWrapper();
	}
	
	public QueryWrapper addQuery(String key, String value) {
		if( StringUtils.isBlank(key) || StringUtils.isBlank(value) ){
			return this;
		}
		
		queries.add(new BasicNameValuePair(key, value));
		return this;
	}
	
	public QueryWrapper addLimit(Long limit) {
		limit = null == limit ? 10L : limit;

		return addQuery(LIMIT, limit.toString());
	}
	
	public QueryWrapper addCursor(String cursor) {
		return addQuery(CURSOR, cursor);
	}

	public QueryWrapper addQueryLang(String ql) {
		return addQuery(QUERY, ql);
	}

	public List<NameValuePair> getQueries() {
		return queries;
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		for( NameValuePair query : queries ) {
			sb.append("[").append(query.getName()).append(":").append(query.getValue()).append("] ");
		}
		
		return sb.toString();
	}
}
