package com.test.pranto.client.db;

import org.hibernate.context.spi.CurrentTenantIdentifierResolver;

public class SchemaResolver implements CurrentTenantIdentifierResolver {

	public static final String TENANT_PUBLIC = "public";
	private static final ThreadLocal<String> tenant = new ThreadLocal<>();

	public static void setTenant(String tenantName) {
		tenant.set(tenantName);
	}

	@Override
	public String resolveCurrentTenantIdentifier() {
		String currentTenant = getCurrentTenant();
		//		PosLog.info(getClass(), "schema is: " + currentTenant);
		return currentTenant;
	}

	@Override
	public boolean validateExistingCurrentSessions() {
		return false;
	}

	/**
	 * Initialize a tenant by storing the tenant identifier in both the HTTP session and the ThreadLocal
	 *
	 * @param   String  tenant  Tenant identifier to be stored
	 */
	public static void initTenant(String tenant) {
		setTenant(tenant);
	}

	public static void setPublicTenant() {
		tenant.set(TENANT_PUBLIC);
	}

	public static String getCurrentTenant() {
		return TENANT_PUBLIC;
	}

	public static void setTenantWithoutSettingToSession(String storeId) {
		tenant.set(storeId);
	}
}