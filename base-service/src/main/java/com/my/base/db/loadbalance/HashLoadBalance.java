package com.my.base.db.loadbalance;

import java.util.List;

/**
 * hash数据库分片
 * 缺点：加新的机器需要rehash所有的数据库
 * 优点：分片均匀
 *
 */
public class HashLoadBalance implements LoadBalance {
	@Override
	public String select(List<String> dataSourceList, String hashKey) {
		if (hashKey == null) return null;
		int size = dataSourceList.size();
		int p = hashKey.hashCode() % size;
		return dataSourceList.get(p);
	}
	
}
