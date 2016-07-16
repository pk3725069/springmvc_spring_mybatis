package com.dao.datasource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

import com.dao.db.DBContextHolder;

public class DynamicDataSource extends AbstractRoutingDataSource{

	@Override
	protected Object determineCurrentLookupKey() {
		return DBContextHolder.getDbType();
	}

		
}
