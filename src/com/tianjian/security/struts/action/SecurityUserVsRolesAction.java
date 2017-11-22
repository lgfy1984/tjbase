package com.tianjian.security.struts.action;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.tianjian.security.business.service.SecurityUserVsRolesServiceImpl;
import com.tianjian.security.struts.form.SecurityUserVsRolesForm;
import com.tianjian.security.struts.form.SessionForm;
import com.tianjian.util.ResourcesUtil;
import com.tianjian.util.comm.PageBean;

public class SecurityUserVsRolesAction extends BaseAction {

	private SecurityUserVsRolesServiceImpl service;
	
	public SecurityUserVsRolesServiceImpl getService() {
		return service;
	}

	public void setService(SecurityUserVsRolesServiceImpl service) {
		this.service = service;
	}

	public ActionForward execute(
		ActionMapping mapping,
		ActionForm form,
		HttpServletRequest request,
		HttpServletResponse response) {
		
		if (checkUser(request, response) == null) {
			return mapping.findForward("noLogin");
		}
		String verbId = request.getParameter("verbId");
		
		if(verbId.equals("search")){
			return this.search(mapping, form, request, response);
		} else if(verbId.equals("find")){
			return this.find(mapping, form, request, response);
		} else if(verbId.equals("execute")){
			return this.save(mapping, form, request, response);
		} else {
			return mapping.findForward("fail");
		}		

	}
	
	public ActionForward search(ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response){
		try {
			SecurityUserVsRolesForm securityUserVsRolesForm = (SecurityUserVsRolesForm)form;
			
			HttpSession session = request.getSession(true);
			SessionForm sessionForm = (SessionForm)session.getAttribute("sessionForm");
			String staffType = sessionForm.getStaffType();
			
			PageBean pb = new PageBean();
			int count;
			int page = 0;
			int recordCount = this.getService().count(securityUserVsRolesForm,staffType,sessionForm.getStaffHospitalId());
			if(recordCount == 0){
				//查询条件有误，未查询到结果
				String str = ResourcesUtil.getValue("conf.security.securityInit", "comm.java.security.warn1", request);
				securityUserVsRolesForm.setMessage(str);
			}
			pb.setCount(recordCount);
			String pageString = "";
			if(securityUserVsRolesForm.getPageNow() != null && securityUserVsRolesForm.getPageNow() != ""){
				pageString = securityUserVsRolesForm.getPageNow();
			}else{
				pageString = request.getParameter("page");
			}
			//ServletContext application = request.getSession().getServletContext();
			//int pageSize = Integer.parseInt((String)application.getAttribute("EHRPProject_basesecurity.PAGE_SIZE"));
			int pageSize = 10;
			if(request.getSession().getAttribute("page_2828810b39763bf50139763bf5cf0000")!=null){
				pageSize = Integer.parseInt((String)request.getSession().getAttribute("page_2828810b39763bf50139763bf5cf0000"));
			}else{
				ServletContext application = request.getSession().getServletContext();
				pageSize = Integer.parseInt((String)application.getAttribute("security.PAGE_SIZE"));
			}
			
			//int pageSize = 10;
			pb.setPageSize(pageSize);
			if (pageString == null || pageString.equals("")
					|| pageString.equals("0")) {
				page = 1;
				pb.setPage(1);
				count = (page - 1) * pageSize;
			} else {
				page = Integer.parseInt(pageString);
				pb.setPage(page);
				count = (page - 1) * pageSize;
			}

			request.setAttribute("pb", pb);
			
			service.getInitData(securityUserVsRolesForm,staffType,sessionForm.getStaffHospitalId(),count, pageSize);
			request.setAttribute("securityUserVsRolesForm", securityUserVsRolesForm);
			return mapping.findForward("init");
		} catch(Exception e) {
			return mapping.findForward("fail");
		}
		
	}
	
	public ActionForward find(ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response){
		try {
			String userIdSelected = request.getParameter("userIdSelected");
			String[] rolesSelected = service.getUserRoles(userIdSelected);
			String xmlString = this.getXMLModRoles(rolesSelected);
    		writeResponse(response, xmlString);
		} catch(Exception e) {
			return mapping.findForward("fail");
		}
		return null;
	}
	
	public ActionForward save(ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response){
		try {
			String userIdSelected = request.getParameter("userIdSelected");
			String[] rolesIdSelected = request.getParameterValues("rolesIdSelected");					
			service.saveUserVsRoles(userIdSelected, rolesIdSelected);
			//String xmlString = this.getXMLMessage("添加成功!");
			String xmlString = this.getXMLMessage(ResourcesUtil.getValue("conf.security.securityInit", "security.java.struts.action.SecurityUserVsRolesAction.warn", request));
			writeResponse(response, xmlString);
		} catch(Exception e) {
			return mapping.findForward("fail");
		}
		return null;
	}
	
	private String getXMLModRoles(String[] value) {
		String xmlString = "";
		
		xmlString = xmlString + "<?xml version=\"1.0\" encoding=\"gb2312\"?>";
		xmlString = xmlString + "<root>";
		int len = 0;
		if(value != null && value.length > 0){
			len = value.length;
		} else {
			len = 0;
		}
		xmlString = xmlString + "<index>" + len + "</index>";
		if(len > 0){
			for (int i = 0; i < value.length; i++) {
				xmlString = xmlString + "<value>" + value[i] + "</value>";
			}			
		}
		xmlString = xmlString + "</root>";
		
		return xmlString;
	}
	
	private String getXMLMessage(String message) {
		String xmlString = "";
		
		xmlString = xmlString + "<?xml version=\"1.0\" encoding=\"gb2312\"?>";
		xmlString = xmlString + "<root>";
		xmlString = xmlString + "<message>" + message + "</message>";
		xmlString = xmlString + "</root>";
		
		return xmlString;
	}
	
	public void writeResponse(HttpServletResponse response, String xmlString) throws IOException {
    	response.setContentType("text/xml");
    	response.setCharacterEncoding("gb2312");
    	response.setHeader("Cache-Control", "no-cache");
    	response.getWriter().write(xmlString);
	}

}

