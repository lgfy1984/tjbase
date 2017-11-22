package com.tianjian.tenant.util;

public class TenantIdHolder {
	private static final ThreadLocal<String> tenantIdholder = new ThreadLocal<String>();

	public static void set(String tenantId) {
		tenantIdholder.set(tenantId);
	}

	public static String get() {
		return tenantIdholder.get();
	}

	public static void remove() {
		tenantIdholder.remove();
	}
}
