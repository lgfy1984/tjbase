package com.tianjian.tenant.util;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

import com.tianjian.security.struts.form.SessionForm;

public class TenantIdFilter implements Filter {
	private static Logger log = LogManager.getLogger(TenantIdFilter.class);

	public void init(FilterConfig arg0) throws ServletException {
	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		SessionForm sessionForm = (SessionForm) req.getSession(true)
				.getAttribute("sessionForm");
		try {
			if (sessionForm != null) {
				String tenantId = sessionForm.getTenantId();
				if (tenantId != null && tenantId.trim().length() > 0) {
					TenantIdHolder.set(tenantId);// 将租户id设置到线程变量中，方便获取
					log.info("当前租户ID：【"+tenantId+"】");
				}
			}
		} catch (Exception ex) {
			log.error("设置租户ID到线程变量时发生异常！", ex);
		}
		chain.doFilter(request, response);
		TenantIdHolder.remove();//清除线程变量
	}

	public void destroy() {
	}
}
