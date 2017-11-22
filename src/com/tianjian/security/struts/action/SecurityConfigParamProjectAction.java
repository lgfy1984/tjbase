package com.tianjian.security.struts.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.tianjian.comm.business.ICommConfigInputDictService;
import com.tianjian.security.bean.SecurityConfigParamProject;
import com.tianjian.security.business.ISecurityConfigParamProjectService;
import com.tianjian.security.struts.form.SecurityConfigParamProjectForm;
import com.tianjian.util.ResourcesUtil;
import com.tianjian.util.comm.PageBean;

public class SecurityConfigParamProjectAction extends Action {
	private ISecurityConfigParamProjectService securityConfigParamProjectService;
	private ICommConfigInputDictService commConfigInputDictService;

	
	public ICommConfigInputDictService getCommConfigInputDictService() {
		return commConfigInputDictService;
	}

	public void setCommConfigInputDictService(
			ICommConfigInputDictService commConfigInputDictService) {
		this.commConfigInputDictService = commConfigInputDictService;
	}

	public ISecurityConfigParamProjectService getSecurityConfigParamProjectService() {
		return securityConfigParamProjectService;
	}

	public void setSecurityConfigParamProjectService(
			ISecurityConfigParamProjectService securityConfigParamProjectService) {
		this.securityConfigParamProjectService = securityConfigParamProjectService;
	}

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String verbId = request.getParameter("verbId");
		if (verbId.equals("query")) {
			return this.query(mapping, form, request, response);
		} else if (verbId.equals("queryDetail")) {
			return this.query(mapping, form, request, response);
		} else if (verbId.equals("detail")) {
			return this.detail(mapping, form, request, response);
		} else if (verbId.equals("delete")) {
			return this.delete(mapping, form, request, response);
		} else if (verbId.equals("updateInit")) {
			return this.updateInit(mapping, form, request, response);
		} else if (verbId.equals("update")) {
			return this.update(mapping, form, request, response);
		} else if (verbId.equals("add")) {
			return this.add(mapping, form, request, response);
		} else if (verbId.equals("addInit")) {
			return this.addInit(mapping, form, request, response);
		} else if (verbId.equals("checkClassCode")) {
			return this.checkClassCode(mapping, form, request, response);
		}else if(verbId.equals("getProject")){
			return this.getProject(mapping, form, request, response);
		}else if (verbId.equals("init")){
			return this.init(mapping, form, request, response);
		}else if(verbId.equals("initDetail")){
			return this.init(mapping, form, request, response);
		} else {
			return null;
		}
	}

	public ActionForward query(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		try {
			String verbId = request.getParameter("verbId");
			SecurityConfigParamProjectForm scpcForm = (SecurityConfigParamProjectForm) form;
			PageBean pb = new PageBean();
			int count;
			int page = 0;
			int recordCount = this.getSecurityConfigParamProjectService().count(scpcForm);
			if(recordCount == 0){
				//查询条件有误，未查询到结果
				String str = ResourcesUtil.getValue("conf.security.securityInit", "comm.java.security.warn1", request);
				scpcForm.setMessage(str);
			}
			pb.setCount(recordCount);
			String pageString = "";
			if(scpcForm.getPageNow() != null&&scpcForm.getPageNow() != ""){
				pageString = scpcForm.getPageNow();
			}else{
				pageString = request.getParameter("page");
			}
			//int pageSize = 10;
			int pageSize = 10;
			if(request.getSession().getAttribute("page_2828810b3711d1b5013711d1b5250000")!=null){
				pageSize = Integer.parseInt((String)request.getSession().getAttribute("page_2828810b3711d1b5013711d1b5250000"));
			}else{
				ServletContext application = request.getSession().getServletContext();
				pageSize = Integer.parseInt((String)application.getAttribute("security.PAGE_SIZE"));
			}
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
			this.getSecurityConfigParamProjectService().getData(scpcForm, count, pageSize);
			request.setAttribute("data", scpcForm);
			if(verbId.equals("queryDetail")){
				return mapping.findForward("queryDetail");
			}else{
				return mapping.findForward("query");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mapping.findForward("query");
	}

	public ActionForward detail(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		SecurityConfigParamProjectForm scpcForm = (SecurityConfigParamProjectForm) form;
		this.getSecurityConfigParamProjectService().getByClassCode(scpcForm);
		request.setAttribute("data", scpcForm);
		return mapping.findForward("detail");
	}

	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		SecurityConfigParamProjectForm scpcForm = (SecurityConfigParamProjectForm) form;
		this.getSecurityConfigParamProjectService().deleteData(scpcForm.getProjectCodeHidden());
		SecurityConfigParamProjectForm sf=new SecurityConfigParamProjectForm();
		return this.query(mapping, sf, request, response);
	}

	public ActionForward update(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String pageString = request.getParameter("page");
		SecurityConfigParamProjectForm scpcForm = (SecurityConfigParamProjectForm) form;
		scpcForm.setPageNow(pageString);
		scpcForm.setInputCode(commConfigInputDictService.getInputCode(scpcForm.getProjectName()));
		this.securityConfigParamProjectService.updateData(scpcForm);
		SecurityConfigParamProjectForm sf=new SecurityConfigParamProjectForm();
		return this.query(mapping, sf, request, response);
	}

	public ActionForward updateInit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String pageString = request.getParameter("page");
		SecurityConfigParamProjectForm scpcForm = (SecurityConfigParamProjectForm) form;
		scpcForm.setPageNow(pageString);
		this.getSecurityConfigParamProjectService().getByClassCode(scpcForm);
		request.setAttribute("data", scpcForm);
		return mapping.findForward("update");
	}

	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {	
		SecurityConfigParamProjectForm scpcForm = (SecurityConfigParamProjectForm) form;
		SecurityConfigParamProject checkData = this.getSecurityConfigParamProjectService().checkData(scpcForm.getProjectCode());
		if(checkData != null){
			String message = ResourcesUtil.getValue("conf.security.securityInit", "security.jsp.securityConfigParamClassM.add.warn1", request);
			scpcForm.setMessage(message);	
			request.setAttribute("data", scpcForm);		
			return mapping.findForward("addInit");
		}
		scpcForm.setInputCode(commConfigInputDictService.getInputCode(scpcForm.getProjectName()));
		this.getSecurityConfigParamProjectService().saveData(scpcForm);
		SecurityConfigParamProjectForm sf=new SecurityConfigParamProjectForm();
		return this.query(mapping, sf, request, response);
	}

	public ActionForward addInit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		SecurityConfigParamProjectForm scpcForm = (SecurityConfigParamProjectForm)form;
		this.getSecurityConfigParamProjectService().seqNoMaker(scpcForm);
		request.setAttribute("data", scpcForm);
		return mapping.findForward("addInit");
	}
	public ActionForward init(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		try {
			String verbId = request.getParameter("verbId");
			SecurityConfigParamProjectForm ssform = (SecurityConfigParamProjectForm) form;
			request.setAttribute("data", ssform);
			if(verbId.equals("initDetail")){
				return mapping.findForward("queryDetail");
			}else{
				return mapping.findForward("query");
			}
		}
		catch (Exception e) {
			return mapping.findForward("fail");
		}
	}
	
	public ActionForward checkClassCode(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String classCode = request.getParameter("classCode");
		response.setHeader("Cache-Control", "no-store");
		response.setHeader("Pragrma", "no-cache");
		response.setDateHeader("Expires", 0);
		response.setContentType("text/html;charset=utf-8");
		if (classCode.trim().length() > 0) {
			int count = this.getSecurityConfigParamProjectService().check(classCode);
			if (count == 1) {
				PrintWriter out;
				try {
					out = response.getWriter();
					out.print("数据对象类型classCode已经存在!");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return mapping.findForward("");
	}
	
	
	/**弹出层*/
	public ActionForward getProject(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {				
		String inputCode = request.getParameter("inputCode"); 
		String flag = request.getParameter("flag");//
		try {
			request.setCharacterEncoding("UTF-8");
			response.setContentType("text/xml; charset=UTF-8");
			response.setHeader("Cache-Control", "no-cache");
			PrintWriter out = response.getWriter();
			String xmlString = this.getSecurityConfigParamProjectService().getXml(flag, inputCode,request);
			out.println(xmlString);    	
			out.close();
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return mapping.findForward("fail");
		}
	}
}
