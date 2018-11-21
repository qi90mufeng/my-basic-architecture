package com.my.base.db.loadbalance;

import java.util.List;

public interface LoadBalance {
	/**
	 * 根据Key查询数据库分片
	 * @param dataSourceList
	 * 		数据库Name列表
	 * @param key
	 * 		查询分片的Key
	 * @return
	 * 		数据库Name
	 */
	String select(List<String> dataSourceList, String key);
}
