package com.backend.boiler.plate.Utils;

import com.zaxxer.hikari.HikariConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Utils {
	private static final Logger logger = LoggerFactory.getLogger(Utils.class);

	public static HikariConfig getDbConfig(String dataSourceClassName, String url, String port, String databaseName,
			String user, String password) {
		HikariConfig config = new HikariConfig();
		config.setDataSourceClassName(dataSourceClassName);
		config.addDataSourceProperty("serverName", url);
		config.addDataSourceProperty("portNumber", port);
		config.addDataSourceProperty("databaseName", databaseName);
		config.addDataSourceProperty("user", user);
		config.addDataSourceProperty("password", password);
		config.setMinimumIdle(2);
		config.setIdleTimeout(120000);
		config.setMaximumPoolSize(5);
		return config;
	}

}
