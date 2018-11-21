package com.my.base.db.loadbalance;

import java.util.List;

import org.apache.commons.lang3.StringUtils;

/**
 * Id范围分片
 * 缺点：分片不均匀
 * 优点：加机器不用对已有数据进行迁移
 *
 */
public class IdRangeLoadBalance implements LoadBalance {
	private Long range;
	public IdRangeLoadBalance(Long range) {
		this.range = range;
	}
	
	@Override
	public String select(List<String> dataSourceList, String key) {
		if (!StringUtils.isNumeric(key)) return null;
		Long id = null;
		try {
			id = Long.parseLong(key);
		} catch (Exception e) {
		}
		if (id != null) {
			int p = (int) (id / range);
			return dataSourceList.get(p);
		}
		
		return null;
	}
}
