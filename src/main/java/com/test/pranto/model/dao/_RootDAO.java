package com.test.pranto.model.dao;

import java.net.URL;
import java.sql.Connection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.SessionFactoryBuilder;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.AvailableSettings;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;

import com.test.pranto.model.base._BaseRootDAO;

public abstract class _RootDAO extends _BaseRootDAO {
	public static final String LUCENE_INDEX_DIR = "/opt/menugreat_lucene_index"; //$NON-NLS-1$
	private static StandardServiceRegistry standardServiceRegistry;

	public static void initialize(String hibernanetCfgFile, String hibernateDialectClass, String driverClass, String connectString, String databaseUser,
			String databasePassword) {
		Map<String, String> map = new HashMap<>();
		map.put("hibernate.dialect", hibernateDialectClass); //$NON-NLS-1$
		map.put("hibernate.connection.driver_class", driverClass); //$NON-NLS-1$
		map.put("hibernate.connection.url", connectString); //$NON-NLS-1$
		map.put("hibernate.connection.username", databaseUser); //$NON-NLS-1$
		map.put("hibernate.connection.password", databasePassword); //$NON-NLS-1$
		map.put("hibernate.connection.autocommit", "false"); //$NON-NLS-1$ //$NON-NLS-2$
		map.put("hibernate.max_fetch_depth", "3"); //$NON-NLS-1$ //$NON-NLS-2$
		map.put("hibernate.show_sql", "false"); //$NON-NLS-1$ //$NON-NLS-2$
		map.put("hibernate.connection.isolation", String.valueOf(Connection.TRANSACTION_READ_UNCOMMITTED)); //$NON-NLS-1$
		map.put("hibernate.cache.use_second_level_cache", "true"); //$NON-NLS-1$ //$NON-NLS-2$
		//map.put("hibernate.cache.use_query_cache", "true"); //$NON-NLS-1$ //$NON-NLS-2$
		map.put("hibernate.cache.provider_class", "org.hibernate.cache.EhCacheProvider"); //$NON-NLS-1$ //$NON-NLS-2$
		map.put("hibernate.cache.region.factory_class", "org.hibernate.cache.ehcache.SingletonEhCacheRegionFactory"); //$NON-NLS-1$ //$NON-NLS-2$

		initialize(hibernanetCfgFile, map);
	}

	public static void initialize(Map<String, String> properties) {
		initialize("cloudpos.hibernate.cfg.xml", properties); //$NON-NLS-1$
	}

	public static void initialize(String hibernanetCfgFile, Map<String, String> properties) {
		releaseConnection();

		properties.put(AvailableSettings.AUTOCOMMIT, "false"); //$NON-NLS-1$
		properties.put(AvailableSettings.MAX_FETCH_DEPTH, "3"); //$NON-NLS-1$
		properties.put(AvailableSettings.SHOW_SQL, "false"); //$NON-NLS-1$
		properties.put(AvailableSettings.USE_SECOND_LEVEL_CACHE, "false"); //$NON-NLS-1$
		properties.put("hibernate.connection.isolation", String.valueOf(Connection.TRANSACTION_READ_UNCOMMITTED)); //$NON-NLS-1$

		StandardServiceRegistryBuilder registryBuilder = new StandardServiceRegistryBuilder();
		URL resource = _RootDAO.class.getResource("/" + hibernanetCfgFile); //$NON-NLS-1$

		registryBuilder.configure(resource);
		registryBuilder.applySettings(properties);

		setupCconnectionPoolSettings(registryBuilder);

		standardServiceRegistry = registryBuilder.build();

		Metadata metaData = new MetadataSources(standardServiceRegistry).getMetadataBuilder().build();
		SessionFactoryBuilder sessionFactoryBuilder = metaData.getSessionFactoryBuilder();
		setSessionFactory(sessionFactoryBuilder.build());
	}

	public static void setupCconnectionPoolSettings(StandardServiceRegistryBuilder builder) {
		//min pool size
		builder.applySetting("hibernate.c3p0.min_size", "0"); //$NON-NLS-1$ //$NON-NLS-2$
		//max pool size
		builder.applySetting("hibernate.c3p0.max_size", "5"); //$NON-NLS-1$ //$NON-NLS-2$
		// When an idle connection is removed from the pool (in second)
		builder.applySetting("hibernate.c3p0.timeout", "300"); //$NON-NLS-1$ //$NON-NLS-2$
		//Number of prepared statements will be cached
		builder.applySetting("hibernate.c3p0.max_statements", "0"); //$NON-NLS-1$ //$NON-NLS-2$
		//The number of milliseconds a client calling getConnection() will wait for a Connection to be 
		//checked-in or acquired when the pool is exhausted. Zero means wait indefinitely.
		//Setting any positive value will cause the getConnection() call to time-out and break 
		//with an SQLException after the specified number of milliseconds. 
		builder.applySetting("hibernate.c3p0.checkoutTimeout", "15000"); //$NON-NLS-1$ //$NON-NLS-2$
		builder.applySetting("hibernate.c3p0.acquireRetryAttempts", "1"); //$NON-NLS-1$ //$NON-NLS-2$
		builder.applySetting("hibernate.c3p0.acquireRetryDelay", "100"); //$NON-NLS-1$ //$NON-NLS-2$
		builder.applySetting("hibernate.c3p0.acquireIncrement", "1"); //$NON-NLS-1$ //$NON-NLS-2$
		//builder.applySetting("hibernate.c3p0.maxIdleTime", "3000"); //$NON-NLS-1$ //$NON-NLS-2$
		builder.applySetting("testConnectionOnCheckout", "true"); //$NON-NLS-1$ //$NON-NLS-2$
		//idle time in seconds before a connection is automatically validated
		//builder.applySetting("hibernate.c3p0.idle_test_period", "3000"); //$NON-NLS-1$ //$NON-NLS-2$
		builder.applySetting("hibernate.c3p0.breakAfterAcquireFailure", "false"); //$NON-NLS-1$ //$NON-NLS-2$
	}

	public static void setupHikariCPSettings(StandardServiceRegistryBuilder builder) {
		builder.applySetting("hibernate.connection.provider_class", "org.hibernate.hikaricp.internal.HikariCPConnectionProvider"); //$NON-NLS-1$ //$NON-NLS-2$
		builder.applySetting("hibernate.hikari.idleTimeout", "30000"); //$NON-NLS-1$ //$NON-NLS-2$
		builder.applySetting("hibernate.hikari.minimumIdle", "0"); //$NON-NLS-1$ //$NON-NLS-2$
		builder.applySetting("hibernate.hikari.maximumPoolSize", "5"); //$NON-NLS-1$ //$NON-NLS-2$
	}

	public static void initialize(String configFileName, Configuration configuration) {
		releaseConnection();
		initialize();
	}

	public static void releaseConnection() {
		closeCurrentThreadSessions();

		if (sessionFactory != null && !sessionFactory.isClosed()) {
			sessionFactory.close();
		}
		if (standardServiceRegistry != null) {
			StandardServiceRegistryBuilder.destroy(standardServiceRegistry);
		}
	}

	public void refresh(Object obj) {
		try (Session session = createNewSession()) {
			super.refresh(obj, session);
		}
	}

	public int rowCount() {
		try (Session session = createNewSession()) {
			Criteria criteria = session.createCriteria(getReferenceClass());
			criteria.setProjection(Projections.rowCount());
			return ((Long) criteria.uniqueResult()).intValue();
		}
	}

	public int rowCount(Criteria criteria) {
		criteria.setProjection(Projections.rowCount());
		int intValue = ((Long) criteria.uniqueResult()).intValue();
		criteria.setProjection(null);
		return intValue;
	}

	public List getPageData(int pageNum, int pageSize) {
		try (Session session = createNewSession()) {
			Criteria criteria = session.createCriteria(getReferenceClass());
			criteria.setFirstResult(pageNum * pageSize);
			criteria.setMaxResults(pageSize);

			return criteria.list();
		}
	}

	public int rowCount(Map<String, Object> restrictions) {
		try (Session session = createNewSession()) {
			Criteria criteria = session.createCriteria(getReferenceClass());
			criteria.setProjection(Projections.rowCount());

			if (restrictions != null) {
				for (String key : restrictions.keySet()) {
					criteria.add(Restrictions.eq(key, restrictions.get(key)));
				}
			}
			Number result = (Number) criteria.uniqueResult();
			if (result != null) {
				return result.intValue();
			}
			return 0;
		}
	}

}
