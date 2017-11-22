package com.tianjian.security.struts.action;


import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.tianjian.comm.struts.form.CommConfigAboForm;
import com.tianjian.security.business.ISecurityXtSysLogService;
import com.tianjian.security.struts.form.SecurityXtSysLogForm;
import com.tianjian.util.ResourcesUtil;
import com.tianjian.util.comm.PageBean;

public class SecurityXtSysLogAction extends BaseAction {

	private ISecurityXtSysLogService xtsyslogService;
	
	public ISecurityXtSysLogService getXtsyslogService() {
		return xtsyslogService;
	}

	public void setXtsyslogService(ISecurityXtSysLogService xtsyslogService) {
		this.xtsyslogService = xtsyslogService;
	}

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
//		if (checkUser(request, response) == null) {
//			return mapping.findForward("noLogin");
//		}
		String verbId = request.getParameter("verbId");
		if (verbId.equals("query")) {
			return this.query(mapping, form, request, response);
		}else if (verbId.equals("detail")) {
				return this.detail(mapping, form, request, response);
		}else if (verbId.equals("init")) {
			return this.init(mapping, form, request, response);
		} else {
			return mapping.findForward("fail");
		}
	}
	
	public ActionForward init(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		try {
			SecurityXtSysLogForm ssform = (SecurityXtSysLogForm) form;
			request.setAttribute("data", ssform);
			return mapping.findForward("query");
		}catch (Exception e) {
			return mapping.findForward("fail");
		}
	}
	
	public ActionForward detail(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		try {
			SecurityXtSysLogForm hosform = (SecurityXtSysLogForm) form;
			this.xtsyslogService.showInit(hosform);
			request.setAttribute("data", hosform);
			return mapping.findForward("detail");
		}
		catch (Exception e) {
			return mapping.findForward("fail");
		}
	}

	public ActionForward query(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		try {
			SecurityXtSysLogForm hosform = (SecurityXtSysLogForm) form;
			// ////// page start ////////////////////////
			PageBean pb = new PageBean();
			int count;
			int page = 0;
			int recordCount = this.getXtsyslogService().getCount(hosform);
			if(recordCount == 0){
				//查询条件有误，未查询到结果
				String str = ResourcesUtil.getValue("conf.security.securityInit", "comm.java.security.warn1", request);
				hosform.setMessage(str);
			}
			pb.setCount(recordCount);
			String pageString = request.getParameter("page");
			//ServletContext application = request.getSession().getServletContext();
			//int pageSize = Integer.parseInt((String)application.getAttribute("EHRPProject_basesecurity.PAGE_SIZE"));
			//int pageSize = BaseSecurityInit.getPageSize("PAGE_SIZE");
			int pageSize = 10;
			if(request.getSession().getAttribute("page_282881f5346450f401346450f4610000")!=null){
				pageSize = Integer.parseInt((String)request.getSession().getAttribute("page_282881f5346450f401346450f4610000"));
			}else{
				ServletContext application = request.getSession().getServletContext();
				pageSize = Integer.parseInt((String)application.getAttribute("security.PAGE_SIZE"));
			}
			
			pb.setPageSize(pageSize);
			if (pageString == null || pageString.equals("") || pageString.equals("0")) {
				page = 1;
				pb.setPage(1);
				count = (page - 1) * pageSize;
			} else {
				page = Integer.parseInt(pageString);
				pb.setPage(page);
				count = (page - 1) * pageSize;
			}
			request.setAttribute("pb", pb);
			// ////// page end ////////////////////////
			this.getXtsyslogService().getSearch(hosform, count, pageSize);
			request.setAttribute("data", hosform);
			return mapping.findForward("query");
		}catch (Exception e) {
			e.printStackTrace();
			return mapping.findForward("fail");
		}
	}
}
