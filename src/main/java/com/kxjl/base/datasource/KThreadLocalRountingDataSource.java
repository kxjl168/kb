package com.kxjl.base.datasource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * 动态切换数据库
 * @author kxjl
 * @date 2020年9月29日
 */
public class KThreadLocalRountingDataSource  extends AbstractRoutingDataSource {
	    @Override
	    protected Object determineCurrentLookupKey() {
	        return KDataSourceTypeManager.get();
	    }
	}
