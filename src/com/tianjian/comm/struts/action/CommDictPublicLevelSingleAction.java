//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_4.1.1/xslt/JavaClass.xsl
package com.tianjian.comm.struts.action;


import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.tianjian.comm.bean.CommDictPublicLevel;
import com.tianjian.comm.business.ICommConfigInputDictService;
import com.tianjian.comm.business.ICommDictPublicLevelSingleService;
import com.tianjian.comm.struts.form.CommDictPublicLevelForm;
import com.tianjian.security.struts.action.BaseAction;
import com.tianjian.util.ResourcesUtil;
import com.tianjian.util.comm.PageBean;


public class CommDictPublicLevelSingleAction extends BaseAction {

	private ICommDictPublicLevelSingleService commDictPublicLevelSingleService;
	private ICommConfigInputDictService commConfigInputDictService;

	public ICommConfigInputDictService getCommConfigInputDictService() {
		return commConfigInputDictService;
	}

	public void setCommConfigInputDictService(ICommConfigInputDictService commConfigInputDictService) {
		this.commConfigInputDictService = commConfigInputDictService;
	}
	public ICommDictPublicLevelSingleService getCommDictPublicLevelSingleService() {
		return commDictPublicLevelSingleService;
	}

	public void setCommDictPublicLevelSingleService(ICommDictPublicLevelSingleService commDictPublicLevelSingleService) {
		this.commDictPublicLevelSingleService = commDictPublicLevelSingleService;
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
		} else if (verbId.equals("update")) {
			return this.update(mapping, form, request, response);
		} else if (verbId.equals("updateInit")) {
			return this.updateInit(mapping, form, request, response);
		} else if (verbId.equals("detail")) {
			return this.detail(mapping, form, request, response);
		} else if (verbId.equals("delete")) {
			return this.delete(mapping, form, request, response);
		} else {
			return mapping.findForward("fail");
		}
	}

	public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		try {
			CommDictPublicLevelForm hosform = (CommDictPublicLevelForm) form;
			CommDictPublicLevel checkData = commDictPublicLevelSingleService.checkData(hosform.getItemCode());
			if (checkData != null) {
				//String message = BaseCommMessage.getMsg("600001");
				ServletContext application = request.getSession().getServletContext();
				String message = (String)application.getAttribute("comm.0-000001");
				hosform.setMessage(message);
				commDictPublicLevelSingleService.addInit(hosform);
				request.setAttribute("commDictPublicLevel", hosform);
				return mapping.findForward("add");
			}

 			hosform.setInputCode(commConfigInputDictService.getInputCode(hosform.getItemName()));
			commDictPublicLevelSingleService.save(hosform);
			CommDictPublicLevelForm hosformNew = new CommDictPublicLevelForm();
			hosformNew.setTableCode(hosform.getTableCode());
			String message = ResourcesUtil.getValue("conf.comm.CommLocale", "comm.java.commom.dataSaveSuccess", request) + "!";
			hosformNew.setMessage(message);
			return this.query(mapping, hosformNew, request, response);
		}
		catch (Exception e) {
			return mapping.findForward("fail");
		}
	}

	public ActionForward query(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		try {
			CommDictPublicLevelForm hosform = (CommDictPublicLevelForm) form;
			//////// page start ////////////////////////
			PageBean pb = new PageBean();
			int count;
			int page = 0;
			
			int recordCount = commDictPublicLevelSingleService.getCount(hosform.getItemCode(), hosform.getItemName(),hosform.getInputCode(), hosform.getParentItemCode(), hosform.getClassLevel(),hosform.getSeqInLevel(), hosform.getTableCode());
			pb.setCount(recordCount);
			String pageString = request.getParameter("page");
			//int pageSize = BaseCommInit.getPageSize("PAGE_SIZE");
			ServletContext application = request.getSession().getServletContext();
			int pageSize = Integer.parseInt((String)application.getAttribute("comm.PAGE_SIZE"));
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
			commDictPublicLevelSingleService.getSearch(hosform, count, pageSize);
			commDictPublicLevelSingleService.serchInit(hosform);
			request.setAttribute("commDictPublicLevel", hosform);
			return mapping.findForward("query");
		}
		catch (Exception e) {
			return mapping.findForward("fail");
		}
	}

	public ActionForward updateInit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		try {
			CommDictPublicLevelForm hosform = (CommDictPublicLevelForm) form;
			hosform.setItemCode(hosform.getIdHidden());
			commDictPublicLevelSingleService.updateInit(hosform);
			request.setAttribute("commDictPublicLevel", hosform);
			return mapping.findForward("update");
		}
		catch (Exception e) {
			return mapping.findForward("fail");
		}
	}

	public ActionForward addInit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		try {
			//-----------------------------------------------------------
			CommDictPublicLevelForm hosform = (CommDictPublicLevelForm)form;
			commDictPublicLevelSingleService.addInit(hosform);
			request.setAttribute("commDictPublicLevel", hosform);
			return mapping.findForward("add");
		}
		catch (Exception e) {
			return mapping.findForward("fail");
		}
	}

	public ActionForward detail(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		try {
			CommDictPublicLevelForm hosform = (CommDictPublicLevelForm) form;
			hosform.setItemCode(hosform.getIdHidden());
			commDictPublicLevelSingleService.updateInit(hosform);
			commDictPublicLevelSingleService.getDetail(hosform);
			request.setAttribute("commDictPublicLevel", hosform);
			return mapping.findForward("detail");
		}
		catch (Exception e) {
			return mapping.findForward("fail");
		}
	}

	public ActionForward update(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		try {
			CommDictPublicLevelForm hosform = (CommDictPublicLevelForm) form;
 			hosform.setInputCode(commConfigInputDictService.getInputCode(hosform.getItemName()));
			commDictPublicLevelSingleService.update(hosform);
			CommDictPublicLevelForm hosformNew = new CommDictPublicLevelForm();
			hosformNew.setTableCode(hosform.getTableCode());
			return this.query(mapping, hosformNew, request, response);
		}
		catch (Exception e) {
			return mapping.findForward("fail");
		}
	}

	public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		try {
			CommDictPublicLevelForm hosform = (CommDictPublicLevelForm) form;
			commDictPublicLevelSingleService.delete(hosform);
			return this.query(mapping, hosform, request, response);
		}
		catch (Exception e) {
			return mapping.findForward("fail");
		}
	}

}
