//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_4.1.1/xslt/JavaClass.xsl
package com.tianjian.comm.struts.action;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.tianjian.comm.bean.CommMenuHelp;
import com.tianjian.comm.business.ICommMenuHelpService;
import com.tianjian.comm.struts.form.CommMenuHelpForm;
import com.tianjian.security.struts.action.BaseAction;
import com.tianjian.security.struts.form.SessionForm;
import com.tianjian.util.comm.PageBean;

public class CommMenuHelpAction extends BaseAction {

	private ICommMenuHelpService commMenuHelpService;
	

	public ICommMenuHelpService getCommMenuHelpService() {
		return commMenuHelpService;
	}

	public void setCommMenuHelpService(ICommMenuHelpService commMenuHelpService) {
		this.commMenuHelpService = commMenuHelpService;
	}

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		String verbId = request.getParameter("verbId");
		if (verbId.equals("add")) {
			return this.add(mapping, form, request, response);
		} else if (verbId.equals("addInit")) {
			return this.addInit(mapping, form, request, response);
		} else if (verbId.equals("query")) {
			return this.query(mapping, form, request, response);
		}else if(verbId.equals("queryDetail")){
			return this.query(mapping, form, request, response);
		} else if (verbId.equals("update")) {
			return this.update(mapping, form, request, response);
		} else if (verbId.equals("updateInit")) {
			return this.updateInit(mapping, form, request, response);
		} else if (verbId.equals("detail")) {
			return this.detail(mapping, form, request, response);
		} else if (verbId.equals("help")) {
			return this.help(mapping, form, request, response);
		} else if (verbId.equals("delete")) {
			return this.delete(mapping, form, request, response);
		}else if(verbId.equals("init")){
			return this.init(mapping, form, request, response);
		}else if(verbId.equals("initDetail")){
			return this.init(mapping, form, request, response);	
		} else {
			return mapping.findForward("fail");
		}
	}

	public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		try {
			CommMenuHelpForm hosform = (CommMenuHelpForm) form;
			CommMenuHelp checkData = commMenuHelpService.checkData(hosform.getIdHidden());
			if (checkData != null) {
				//String message = BaseCommMessage.getMsg("0-000001");
				ServletContext application = request.getSession().getServletContext();
				String message = (String)application.getAttribute("comm.0-000001");
				hosform.setMessage(message);
				commMenuHelpService.addInit(hosform);
				request.setAttribute("data", hosform);
				return mapping.findForward("add");
			}
			
			HttpSession session = request.getSession (true); 
			SessionForm sessionForm = (SessionForm)session.getAttribute("sessionForm");
			if(sessionForm!=null){
				hosform.setCreateUserId(sessionForm.getStaffId());
				hosform.setCreateUserName(sessionForm.getStaffName());
			}
			
			commMenuHelpService.save(hosform);
			CommMenuHelpForm hosformNew = new CommMenuHelpForm();
			return this.query(mapping, hosformNew, request, response);
		}
		catch (Exception e) {
			return mapping.findForward("fail");
		}
	}
	
	public ActionForward init(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		try {
			String verbId = request.getParameter("verbId");
			CommMenuHelpForm ssform = (CommMenuHelpForm) form;
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
	
	public ActionForward query(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		try {
			String verbId = request.getParameter("verbId");
			CommMenuHelpForm hosform = (CommMenuHelpForm) form;
			// ////// page start ////////////////////////
			PageBean pb = new PageBean();
			int count;
			int page = 0;
			int recordCount = commMenuHelpService.getCount(hosform.getMenuName(), hosform.getMenuHelpTitle(), hosform.getCreateDate(), hosform.getSeqNo());
			pb.setCount(recordCount);
			String pageString = request.getParameter("page");
			//int pageSize = BaseCommInit.getPageSize("PAGE_SIZE");
			//ServletContext application = request.getSession().getServletContext();
			//int pageSize = Integer.parseInt((String)application.getAttribute("EHRPProject_basecomm.PAGE_SIZE"));
			int pageSize = 10;
			if(request.getSession().getAttribute("page_282881f53463902b013463902b220000")!=null){
				pageSize = Integer.parseInt((String)request.getSession().getAttribute("page_282881f53463902b013463902b220000"));
			}else{
				ServletContext application = request.getSession().getServletContext();
				pageSize = Integer.parseInt((String)application.getAttribute("comm.PAGE_SIZE"));
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
			commMenuHelpService.getSearch(hosform, count, pageSize);
			commMenuHelpService.serchInit(hosform);
			request.setAttribute("data", hosform);
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
			CommMenuHelpForm hosform = (CommMenuHelpForm) form;
			hosform.setId(hosform.getIdHidden());
			commMenuHelpService.updateInit(hosform);
			request.setAttribute("data", hosform);
			return mapping.findForward("update");
		}
		catch (Exception e) {
			return mapping.findForward("fail");
		}
	}

	public ActionForward addInit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		try {
			CommMenuHelpForm hosform = new CommMenuHelpForm();
			commMenuHelpService.addInit(hosform);
			request.setAttribute("data", hosform);
			return mapping.findForward("add");
		}
		catch (Exception e) {
			return mapping.findForward("fail");
		}
	}
	
	public ActionForward help(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		try {
			String menuFlag = request.getParameter("menuFlag");
			CommMenuHelpForm hosform = (CommMenuHelpForm) form;
			hosform.setMenuFlag(menuFlag);
			this.commMenuHelpService.help(hosform);
			request.setAttribute("data", hosform);
			return mapping.findForward("help");
		}
		catch (Exception e) {
			return mapping.findForward("fail");
		}
	}
	
	public ActionForward detail(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		try {
			CommMenuHelpForm hosform = (CommMenuHelpForm) form;
			hosform.setId(hosform.getIdHidden());
			commMenuHelpService.updateInit(hosform);
			request.setAttribute("data", hosform);
			return mapping.findForward("detail");
		}
		catch (Exception e) {
			return mapping.findForward("fail");
		}
	}

	public ActionForward update(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		try {
			CommMenuHelpForm hosform = (CommMenuHelpForm) form;
			
			HttpSession session = request.getSession (true); 
			SessionForm sessionForm = (SessionForm)session.getAttribute("sessionForm");
			if(sessionForm!=null){
				hosform.setCreateUserId(sessionForm.getStaffId());
				hosform.setCreateUserName(sessionForm.getStaffName());
			}
			
			commMenuHelpService.update(hosform);
			CommMenuHelpForm hosformNew = new CommMenuHelpForm();
			return this.query(mapping, hosformNew, request, response);
		}catch (Exception e) {
			return mapping.findForward("fail");
		}
	}

	public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		try {
			CommMenuHelpForm hosform = (CommMenuHelpForm) form;
			commMenuHelpService.delete(hosform);
			return this.query(mapping, hosform, request, response);
		}
		catch (Exception e) {
			return mapping.findForward("fail");
		}
	}

	
}
