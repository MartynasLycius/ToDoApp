package com.test.pranto.client.db;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Map;

import org.hibernate.HibernateException;
import org.hibernate.c3p0.internal.C3P0ConnectionProvider;
import org.hibernate.engine.config.spi.ConfigurationService;
import org.hibernate.engine.jdbc.connections.spi.MultiTenantConnectionProvider;
import org.hibernate.service.spi.ServiceRegistryAwareService;
import org.hibernate.service.spi.ServiceRegistryImplementor;

public class OroMultitenantConnectionProvider implements MultiTenantConnectionProvider, ServiceRegistryAwareService {
	private C3P0ConnectionProvider connectionProvider = null;

	@Override
	public void injectServices(ServiceRegistryImplementor serviceRegistry) {
		Map lSettings = serviceRegistry.getService(ConfigurationService.class).getSettings();

		connectionProvider = new C3P0ConnectionProvider();
		connectionProvider.injectServices(serviceRegistry);
		connectionProvider.configure(lSettings);
	}

	@Override
	public boolean isUnwrappableAs(Class unwrapType) {
		return false;
	}

	@Override
	public <T> T unwrap(Class<T> unwrapType) {
		return null;
	}

	@Override
	public Connection getAnyConnection() throws SQLException {
		return connectionProvider.getConnection();
	}

	@Override
	public synchronized void releaseAnyConnection(Connection connection) throws SQLException {
		try {
			connection.createStatement().execute("SET SCHEMA 'public'"); //$NON-NLS-1$
		} catch (SQLException e) {
			//throw new HibernateException(Messages.getString("OroMultitenantConnectionProvider.1"), e); //$NON-NLS-1$
		}
		connectionProvider.closeConnection(connection);
	}

	@Override
	public Connection getConnection(String tenantIdentifier) throws SQLException {
		final Connection connection = getAnyConnection();
		try {
			connection.createStatement().execute("SET SCHEMA '" + tenantIdentifier + "'"); //$NON-NLS-1$ //$NON-NLS-2$
		} catch (SQLException e) {
			throw new HibernateException(tenantIdentifier, e); //$NON-NLS-1$ //$NON-NLS-2$
		}
		return connection;
	}

	@Override
	public void releaseConnection(String tenantIdentifier, Connection connection) throws SQLException {
		releaseAnyConnection(connection);
	}

	@Override
	public boolean supportsAggressiveRelease() {
		return false;
	}

}
