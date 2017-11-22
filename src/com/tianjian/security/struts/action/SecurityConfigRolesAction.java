package com.tianjian.security.struts.action;


import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.tianjian.comm.business.ICommConfigInputDictService;
import com.tianjian.security.bean.SecurityConfigRoles;
import com.tianjian.security.business.ISecurityConfigRolesService;
import com.tianjian.security.struts.form.SecurityConfigRolesForm;
import com.tianjian.util.ResourcesUtil;
import com.tianjian.util.comm.PageBean;

public class SecurityConfigRolesAction extends BaseAction {

	private ISecurityConfigRolesService securityConfigRolesService;
	private ICommConfigInputDictService commConfigInputDictService;

	public ICommConfigInputDictService getCommConfigInputDictService() {
		return commConfigInputDictService;
	}

	public void setCommConfigInputDictService(ICommConfigInputDictService commConfigInputDictService) {
		this.commConfigInputDictService = commConfigInputDictService;
	}
	public ISecurityConfigRolesService getSecurityConfigRolesService() {
		return securityConfigRolesService;
	}

	public void setSecurityConfigRolesService(ISecurityConfigRolesService securityConfigRolesService) {
		this.securityConfigRolesService = securityConfigRolesService;
	}

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		if (checkUser(request, response) == null) {
			return mapping.findForward("noLogin");
		}
		String verbId = request.getParameter("verbId");
		if (verbId.equals("add")) {
			return this.add(mapping, form, request, response);
		} else if (verbId.equals("addInit")) {
			return this.addInit(mapping, form, request, response);
		} else if (verbId.equals("query")) {
			return this.query(mapping, form, request, response);
		} else if (verbId.equals("queryDetail")) {
			return this.query(mapping, form, request, response);
		} else if (verbId.equals("update")) {
			return this.update(mapping, form, request, response);
		} else if (verbId.equals("updateInit")) {
			return this.updateInit(mapping, form, request, response);
		} else if (verbId.equals("detail")) {
			return this.detail(mapping, form, request, response);
		} else if (verbId.equals("delete")) {
			return this.delete(mapping, form, request, response);
		} else if (verbId.equals("init")){
			return this.init(mapping, form, request, response);
		}else if(verbId.equals("initDetail")){
			return this.init(mapping, form, request, response);	
		} else {
			return mapping.findForward("fail");
		}
	}

	public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		try {
			SecurityConfigRolesForm hosform = (SecurityConfigRolesForm) form;
			SecurityConfigRoles checkData = securityConfigRolesService.checkData(hosform.getData().getRoleCode());
			if (checkData != null) {
				//ServletContext application = request.getSession().getServletContext();
				//String message = (String)application.getAttribute("EHRPProject_basesecurity.1-000001");
				//String message = BaseSecurityMessage.getMsg("1-000001");
				hosform.setMessage(ResourcesUtil.getValue("conf.security.securityInit", "security.java.securityRoles.jwarn1", request));
				securityConfigRolesService.addInit(hosform);
				request.setAttribute("dataForm", hosform);
				return mapping.findForward("add");
			}
			hosform.getData().setInputCode(commConfigInputDictService.getInputCode(hosform.getData().getRoleDetail()));
			securityConfigRolesService.save(hosform);
			SecurityConfigRolesForm hosformNew = new SecurityConfigRolesForm();
			return this.query(mapping, hosformNew, request, response);
		}
		catch (Exception e) {
			return mapping.findForward("fail");
		}
	}

	public ActionForward query(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		try {
			String verbId = request.getParameter("verbId");
			SecurityConfigRolesForm hosform = (SecurityConfigRolesForm) form;
			// ////// page start ////////////////////////
			PageBean pb = new PageBean();
			int count;
			int page = 0;
			int recordCount = securityConfigRolesService.getCount(hosform.getId(),hosform.getRoleCode(), hosform.getRoleDetail(), hosform.getInputCode(), hosform.getSerialNo());
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
			if(request.getSession().getAttribute("page_282881f5346450df01346450df1f0000")!=null){
				pageSize = Integer.parseInt((String)request.getSession().getAttribute("page_282881f5346450df01346450df1f0000"));
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
			securityConfigRolesService.getSearch(hosform, count, pageSize);
			securityConfigRolesService.serchInit(hosform);
			request.setAttribute("dataForm", hosform);
			if(verbId.equals("queryDetail")){
				return mapping.findForward("queryDetail");
			}else{
				return mapping.findForward("query");
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			return mapping.findForward("fail");
		}
	}

	public ActionForward updateInit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		try {
			SecurityConfigRolesForm hosform = (SecurityConfigRolesForm) form;
			securityConfigRolesService.updateInit(hosform);
			request.setAttribute("dataForm", hosform);
			return mapping.findForward("update");
		}
		catch (Exception e) {
			return mapping.findForward("fail");
		}
	}

	public ActionForward addInit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		try {
			SecurityConfigRolesForm hosform = new SecurityConfigRolesForm();
			securityConfigRolesService.addInit(hosform);
			request.setAttribute("dataForm", hosform);
			return mapping.findForward("add");
		}
		catch (Exception e) {
			return mapping.findForward("fail");
		}
	}

	public ActionForward detail(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		try {
			SecurityConfigRolesForm hosform = (SecurityConfigRolesForm) form;
			securityConfigRolesService.showInit(hosform);
			request.setAttribute("dataForm", hosform);
			return mapping.findForward("detail");
		}
		catch (Exception e) {
			return mapping.findForward("fail");
		}
	}

	public ActionForward update(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		try {
			SecurityConfigRolesForm hosform = (SecurityConfigRolesForm) form;
			//---------------------检查代码唯一性---------------------------------------------------------
			SecurityConfigRoles checkData = securityConfigRolesService.checkData(hosform.getData().getRoleCode());
		 	if ((checkData!=null )&&(!hosform.getData().getRoleCode().equals(hosform.getRoleCodeHidden()))) {
		 		//ServletContext application = request.getSession().getServletContext();
		 		//String message = (String)application.getAttribute("EHRPProject_basesecurity.1-000001");
		 		hosform.setMessage(ResourcesUtil.getValue("conf.security.securityInit", "security.java.securityRoles.jwarn1", request));
				//String message = BaseSecurityMessage.getMsg("1-000001");
				//hosform.setMessage(message);
		 		securityConfigRolesService.updateInit(hosform);
				request.setAttribute("dataForm", hosform);
				return mapping.findForward("update");
			}
			//-------------------------------------------------------------------------------
			hosform.getData().setInputCode(commConfigInputDictService.getInputCode(hosform.getData().getRoleDetail()));
			securityConfigRolesService.update(hosform);
			SecurityConfigRolesForm hosformNew = new SecurityConfigRolesForm();
			return this.query(mapping, hosformNew, request, response);
		}
		catch (Exception e) {
			return mapping.findForward("fail");
		}
	}

	public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		try {
			SecurityConfigRolesForm hosform = (SecurityConfigRolesForm) form;
			securityConfigRolesService.delete(hosform);
			return this.query(mapping, hosform, request, response);
		}
		catch (Exception e) {
			return mapping.findForward("fail");
		}
	}
	
	public ActionForward init(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		try {
			String verbId = request.getParameter("verbId");
			SecurityConfigRolesForm ssform = (SecurityConfigRolesForm) form;
			request.setAttribute("dataForm", ssform);
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
}
