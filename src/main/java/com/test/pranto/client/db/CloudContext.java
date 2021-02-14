package com.test.pranto.client.db;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.hibernate.MultiTenancyStrategy;
import org.hibernate.cfg.AvailableSettings;

import com.test.pranto.model.dao._RootDAO;

@WebListener
public class CloudContext implements ServletContextListener {

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		_RootDAO.releaseConnection();
	}

	@Override
	public void contextInitialized(ServletContextEvent servletContextEvent) {
		try {
			ServletContext servletContext = servletContextEvent.getServletContext();
			String dbHost = servletContext.getInitParameter("jdbc.database_host"); //$NON-NLS-1$
			String dbPort = servletContext.getInitParameter("jdbc.database_port"); //$NON-NLS-1$
			String path = String.format("jdbc:postgresql://%s:%s/", dbHost, dbPort); //$NON-NLS-1$
			String dbName = servletContext.getInitParameter("jdbc.database_name"); //$NON-NLS-1$
			String username = servletContext.getInitParameter("jdbc.database_user"); //$NON-NLS-1$
			String password = servletContext.getInitParameter("jdbc.database_password"); //$NON-NLS-1$
			String connectString = path + dbName + "?prepareThreshold=0";

			DatabaseUtil.updateSchema(connectString, "public", username, password);

			Map<String, String> properties = new HashMap<>();
			properties.put(AvailableSettings.DIALECT, "org.hibernate.dialect.PostgreSQLDialect");
			properties.put(AvailableSettings.DRIVER, "org.postgresql.Driver");
			properties.put(AvailableSettings.URL, connectString);
			properties.put(AvailableSettings.USER, username);
			properties.put(AvailableSettings.PASS, password);
			properties.put("hibernate.show_sql", "true");

			properties.put(AvailableSettings.MULTI_TENANT, MultiTenancyStrategy.SCHEMA.name());
			properties.put(AvailableSettings.MULTI_TENANT_CONNECTION_PROVIDER, "com.test.pranto.servlet.OroMultitenantConnectionProvider"); //$NON-NLS-1$
			properties.put(AvailableSettings.MULTI_TENANT_IDENTIFIER_RESOLVER, "com.test.pranto.servlet.SchemaResolver"); //$NON-NLS-1$

			//hibernate-search-orm
			properties.put("hibernate.search.default.locking_strategy", "none"); //$NON-NLS-1$ //$NON-NLS-2$
			properties.put("hibernate.search.default.directory_provider", "filesystem"); //$NON-NLS-1$ //$NON-NLS-2$

			DatabaseUtil.initialize(properties);
			SchemaResolver.setPublicTenant();
		} catch (Exception e) {
			System.out.println(e);
		}
	}
}