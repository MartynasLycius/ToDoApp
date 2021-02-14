/**
 * ************************************************************************
 * * The contents of this file are subject to the MRPL 1.2
 * * (the  "License"),  being   the  Mozilla   Public  License
 * * Version 1.1  with a permitted attribution clause; you may not  use this
 * * file except in compliance with the License. You  may  obtain  a copy of
 * * the License at http://www.floreantpos.org/license.html
 * * Software distributed under the License  is  distributed  on  an "AS IS"
 * * basis, WITHOUT WARRANTY OF ANY KIND, either express or implied. See the
 * * License for the specific  language  governing  rights  and  limitations
 * * under the License.
 * * The Original Code is FLOREANT POS.
 * * The Initial Developer of the Original Code is OROCUBE LLC
 * * All portions are Copyright (C) 2015 OROCUBE LLC
 * * All Rights Reserved.
 * ************************************************************************
 */
package com.test.pranto.servlet;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.Map;

import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.AvailableSettings;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.hibernate.tool.hbm2ddl.SchemaUpdate;
import org.hibernate.tool.schema.TargetType;

import com.test.pranto.model.dao._RootDAO;

public class DatabaseUtil {
	public static final Integer DATABASE_VERSION = 1;

	public static void checkConnection(String connectionString, String hibernateDialect, String hibernateConnectionDriverClass, String user, String password)
			throws Exception {
		try {

			Class.forName(hibernateConnectionDriverClass);
			DriverManager.getConnection(connectionString, user, password);

		} catch (Exception e) {
			throw new Exception(e.getMessage()); //$NON-NLS-1$
		}
	}

	public static void initialize() throws Exception {
		_RootDAO.initialize();
	}

	public static void initialize(String hibernanetCfgFile) throws Exception {
		_RootDAO.initialize(hibernanetCfgFile);
	}

	public static void initialize(String hibernanetCfgFile, String hibernateDialectClass, String driverClass, String connectString, String databaseUser,
			String databasePassword) {
		_RootDAO.initialize(hibernanetCfgFile, hibernateDialectClass, driverClass, connectString, databaseUser, databasePassword);
	}

	public static void initialize(String hibernanetCfgFile, Map<String, String> properties) {
		_RootDAO.initialize(hibernanetCfgFile, properties);
	}

	public static void initialize(Map<String, String> properties) {
		_RootDAO.initialize("todo.hibernate.cfg.xml", properties); //$NON-NLS-1$
	}

	public static boolean createDatabase(String hibernateCfgFileName, String connectionString, String hibernateDialect, String hibernateConnectionDriverClass,
			String user, String password, boolean exportSampleData, boolean testData) {
		try {
			_RootDAO.releaseConnection();

			StandardServiceRegistryBuilder registryBuilder = new StandardServiceRegistryBuilder().configure(hibernateCfgFileName);
			registryBuilder.applySetting("hibernate.dialect", hibernateDialect); //$NON-NLS-1$
			registryBuilder.applySetting("hibernate.connection.driver_class", hibernateConnectionDriverClass); //$NON-NLS-1$
			registryBuilder.applySetting("hibernate.connection.url", connectionString); //$NON-NLS-1$
			registryBuilder.applySetting("hibernate.connection.username", user); //$NON-NLS-1$
			registryBuilder.applySetting("hibernate.connection.password", password); //$NON-NLS-1$
			registryBuilder.applySetting("hibernate.hbm2ddl.auto", "create"); //$NON-NLS-1$ //$NON-NLS-2$
			registryBuilder.applySetting("hibernate.c3p0.checkoutTimeout", "0"); //$NON-NLS-1$ //$NON-NLS-2$
			registryBuilder.applySetting("hibernate.cache.use_second_level_cache", "false"); //$NON-NLS-1$ //$NON-NLS-2$

			StandardServiceRegistry standardRegistry = registryBuilder.build();
			Metadata metaData = new MetadataSources(standardRegistry).getMetadataBuilder().build();

			SchemaExport schemaExport = new SchemaExport();
			EnumSet<TargetType> enumSet = EnumSet.of(TargetType.DATABASE);
			schemaExport.create(enumSet, metaData);

			_RootDAO.setSessionFactory(metaData.buildSessionFactory());
			if (!exportSampleData) {
				StandardServiceRegistryBuilder.destroy(standardRegistry);
				return true;
			}
			StandardServiceRegistryBuilder.destroy(standardRegistry);
			return true;
		} catch (Exception e) {
			throw e;
		}
	}

	public static boolean hasTable(DatabaseMetaData metaData, String tableName) throws SQLException {
		ResultSet resultSet = null;
		try {
			resultSet = metaData.getTables(null, null, null, new String[] { "TABLE" }); //$NON-NLS-1$
			ArrayList<String> tableNameList = new ArrayList<>();

			while (resultSet.next()) {
				String tableName2 = resultSet.getString("TABLE_NAME"); //$NON-NLS-1$
				tableNameList.add(tableName2.toLowerCase());
			}

			if (tableNameList.contains(tableName.toLowerCase())) {
				return true;
			}
			return false;
		} finally {
			if (resultSet != null) {
				resultSet.close();
			}
		}
	}

	public static String getActualTableName(DatabaseMetaData metaData, String tableName) throws SQLException {
		ResultSet resultSet = null;
		try {
			resultSet = metaData.getTables(null, null, null, new String[] { "TABLE" }); //$NON-NLS-1$
			while (resultSet.next()) {
				String tableName2 = resultSet.getString("TABLE_NAME"); //$NON-NLS-1$
				if (tableName2.equalsIgnoreCase(tableName)) {
					return tableName2;
				}
			}

			return null;
		} finally {
			if (resultSet != null) {
				resultSet.close();
			}
		}
	}

	public static String getActualColumnName(DatabaseMetaData metaData, String actutalTableName, String virtualColumnName) throws SQLException {
		ResultSet sourcecolumns = null;
		try {
			sourcecolumns = metaData.getColumns(null, null, actutalTableName, null);
			while (sourcecolumns.next()) {
				String columnName = sourcecolumns.getString("COLUMN_NAME"); //$NON-NLS-1$
				if (columnName.equalsIgnoreCase(virtualColumnName)) {
					return columnName;
				}
			}
			return null;
		} finally {
			if (sourcecolumns != null) {
				sourcecolumns.close();
			}
		}
	}

	public static void updateSchema(String connectionString, final String schema, String user, String password) throws Exception {
		StandardServiceRegistryBuilder registryBuilder = new StandardServiceRegistryBuilder().configure("todo.hibernate.cfg.xml"); //$NON-NLS-1$
		registryBuilder.applySetting(AvailableSettings.DIALECT, "org.hibernate.dialect.PostgreSQLDialect");
		registryBuilder.applySetting(AvailableSettings.DRIVER, "org.postgresql.Driver");
		registryBuilder.applySetting(AvailableSettings.URL, connectionString); //$NON-NLS-1$
		registryBuilder.applySetting(AvailableSettings.USER, user); //$NON-NLS-1$
		registryBuilder.applySetting(AvailableSettings.PASS, password); //$NON-NLS-1$
		registryBuilder.applySetting(AvailableSettings.MAX_FETCH_DEPTH, "3"); //$NON-NLS-1$ //$NON-NLS-2$
		registryBuilder.applySetting(AvailableSettings.ISOLATION, String.valueOf(Connection.TRANSACTION_READ_UNCOMMITTED)); //$NON-NLS-1$
		registryBuilder.applySetting(AvailableSettings.USE_SECOND_LEVEL_CACHE, "false"); //$NON-NLS-1$ //$NON-NLS-2$
		registryBuilder.applySetting(AvailableSettings.DEFAULT_SCHEMA, schema); //$NON-NLS-1$ //$NON-NLS-2$

		registryBuilder.applySetting("hibernate.search.default.indexBase", _RootDAO.LUCENE_INDEX_DIR); //$NON-NLS-1$ //$NON-NLS-2$
		registryBuilder.applySetting("hibernate.search.autoregister_listeners", "false"); //$NON-NLS-1$ //$NON-NLS-2$
		registryBuilder.applySetting("hibernate.search.indexing_strategy", "manual"); //$NON-NLS-1$ //$NON-NLS-2$

		StandardServiceRegistry standardRegistry = registryBuilder.build();
		Metadata metaData = new MetadataSources(standardRegistry).getMetadataBuilder().build();
		SchemaUpdate schemaUpdate = new SchemaUpdate();
		EnumSet<TargetType> enumSet = EnumSet.of(TargetType.DATABASE, TargetType.STDOUT);
		schemaUpdate.execute(enumSet, metaData);

		SessionFactory sessionFactory = metaData.buildSessionFactory();
		_RootDAO.setSessionFactory(sessionFactory);
		sessionFactory.close();
		StandardServiceRegistryBuilder.destroy(standardRegistry);
	}
}
