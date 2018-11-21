package com.my.base.db;

import java.util.List;

import com.my.base.db.loadbalance.LoadBalance;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * 负载均衡动态数据源
 *
 */
public class LoadBalanceDataSource extends AbstractRoutingDataSource {
	private static final ThreadLocal<String> contextHolder = new ThreadLocal<String>();
	//负载均衡器
	private LoadBalance loadBalance;
	//数据库
	private List<String> dataSourceList;
	
	public LoadBalanceDataSource(List<String> dataSourceList, LoadBalance loadBalance) {
		this.dataSourceList = dataSourceList;
		this.loadBalance = loadBalance;
	}

	@Override
	protected Object determineCurrentLookupKey() {
		return this.loadBalance.select(dataSourceList, contextHolder.get());
	}
	
	public static void setKey(String key) {
		contextHolder.set(key);
	}
}
