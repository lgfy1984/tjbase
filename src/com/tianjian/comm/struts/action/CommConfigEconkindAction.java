//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_4.1.1/xslt/JavaClass.xsl
package com.tianjian.comm.struts.action;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.tianjian.comm.bean.CommConfigEconkind;
import com.tianjian.comm.business.ICommConfigEconkindService;
import com.tianjian.comm.business.ICommConfigInputDictService;
import com.tianjian.comm.struts.form.CommConfigEconkindForm;
import com.tianjian.security.struts.action.BaseAction;
import com.tianjian.util.ResourcesUtil;
import com.tianjian.util.comm.PageBean;

public class CommConfigEconkindAction extends BaseAction {

	private ICommConfigEconkindService commConfigEconkindService;
	private ICommConfigInputDictService commConfigInputDictService;

	public ICommConfigInputDictService getCommConfigInputDictService() {
		return commConfigInputDictService;
	}

	public void setCommConfigInputDictService(ICommConfigInputDictService commConfigInputDictService) {
		this.commConfigInputDictService = commConfigInputDictService;
	}
	public ICommConfigEconkindService getCommConfigEconkindService() {
		return commConfigEconkindService;
	}

	public void setCommConfigEconkindService(ICommConfigEconkindService commConfigEconkindService) {
		this.commConfigEconkindService = commConfigEconkindService;
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
			CommConfigEconkindForm hosform = (CommConfigEconkindForm) form;
			CommConfigEconkind checkData = commConfigEconkindService.checkData(hosform.getItemCode());
			if (checkData != null) {
				//String message = BaseCommMessage.getMsg("1-000001");
				ServletContext application = request.getSession().getServletContext();
				String message = (String)application.getAttribute("comm.0-000001");
				hosform.setMessage(message);
				commConfigEconkindService.addInit(hosform);
				request.setAttribute("commConfigEconkind", hosform);
				return mapping.findForward("add");
			}
			hosform.setInputCode(commConfigInputDictService.getInputCode(hosform.getItemName()));
			commConfigEconkindService.save(hosform);
			CommConfigEconkindForm hosformNew = new CommConfigEconkindForm();
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
			CommConfigEconkindForm hosform = (CommConfigEconkindForm) form;
			// ////// page start ////////////////////////
			PageBean pb = new PageBean();
			int count;
			int page = 0;
			int recordCount = commConfigEconkindService.getCount(hosform.getItemCode(), hosform.getItemName(), hosform.getInputCode(), hosform.getSeqNo());
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
			commConfigEconkindService.getSearch(hosform, count, pageSize);
			commConfigEconkindService.serchInit(hosform);
			request.setAttribute("commConfigEconkind", hosform);
			return mapping.findForward("query");
		}
		catch (Exception e) {
			e.printStackTrace();
			return mapping.findForward("fail");
		}
	}

	public ActionForward updateInit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		try {
			CommConfigEconkindForm hosform = (CommConfigEconkindForm) form;
			commConfigEconkindService.updateInit(hosform);
			request.setAttribute("commConfigEconkind", hosform);
			return mapping.findForward("update");
		}
		catch (Exception e) {
			return mapping.findForward("fail");
		}
	}

	public ActionForward addInit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		try {
			CommConfigEconkindForm hosform = new CommConfigEconkindForm();
			commConfigEconkindService.addInit(hosform);
			request.setAttribute("commConfigEconkind", hosform);
			return mapping.findForward("add");
		}
		catch (Exception e) {
			return mapping.findForward("fail");
		}
	}

	public ActionForward detail(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		try {
			CommConfigEconkindForm hosform = (CommConfigEconkindForm) form;
			commConfigEconkindService.showInit(hosform);
			request.setAttribute("commConfigEconkind", hosform);
			return mapping.findForward("detail");
		}
		catch (Exception e) {
			return mapping.findForward("fail");
		}
	}

	public ActionForward update(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		try {
			CommConfigEconkindForm hosform = (CommConfigEconkindForm) form;
			hosform.setInputCode(commConfigInputDictService.getInputCode(hosform.getItemName()));
			commConfigEconkindService.update(hosform);
			CommConfigEconkindForm hosformNew = new CommConfigEconkindForm();
			return this.query(mapping, hosformNew, request, response);
		}
		catch (Exception e) {
			return mapping.findForward("fail");
		}
	}

	public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		try {
			CommConfigEconkindForm hosform = (CommConfigEconkindForm) form;
			commConfigEconkindService.delete(hosform);
			return this.query(mapping, hosform, request, response);
		}
		catch (Exception e) {
			return mapping.findForward("fail");
		}
	}
}
