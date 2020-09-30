package com.kxjl.admin.util;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.mapping.DatabaseIdProvider;

public class MyDbProvider implements DatabaseIdProvider {

	private static final Log log = LogFactory.getLog(MyDbProvider.class);
	private Properties properties;

	@Override
	public void setProperties(Properties p) {
		this.properties = p;
	}

	@Override
	public String getDatabaseId(DataSource dataSource) throws SQLException {
		Connection connection = dataSource.getConnection();
		DatabaseMetaData metaData = connection.getMetaData();
		String databaseProductName = metaData.getDatabaseProductName();
		log.debug("Current DataBase Product Name is: " + databaseProductName);
		for (Object key : properties.keySet()) {
			if (key.equals(databaseProductName)) {
				log.debug("Find a matched property value: " + properties.get(key));
				return (String) properties.get(key);
			}
		}
		return null;
	}


}
