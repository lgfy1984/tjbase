//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_4.1.1/xslt/JavaClass.xsl
package com.tianjian.comm.struts.action;


import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.tianjian.comm.bean.CommDictPublicChar;
import com.tianjian.comm.business.ICommConfigInputDictService;
import com.tianjian.comm.business.ICommDictPublicCharSingleService;
import com.tianjian.comm.struts.form.CommDictPublicCharForm;
import com.tianjian.comm.struts.servlet.BaseCommMessage;
import com.tianjian.security.struts.action.BaseAction;
import com.tianjian.util.ResourcesUtil;
import com.tianjian.util.comm.PageBean;


public class CommDictPublicCharSingleAction extends BaseAction {

	private ICommDictPublicCharSingleService commDictPublicCharSingleService;

	private ICommConfigInputDictService commConfigInputDictService;

	public ICommConfigInputDictService getCommConfigInputDictService() {
		return commConfigInputDictService;
	}

	public void setCommConfigInputDictService(ICommConfigInputDictService commConfigInputDictService) {
		this.commConfigInputDictService = commConfigInputDictService;
	}
	public ICommDictPublicCharSingleService getCommDictPublicCharSingleService() {
		return commDictPublicCharSingleService;
	}

	
	public void setCommDictPublicCharSingleService(ICommDictPublicCharSingleService commDictPublicCharSingleService) {
		this.commDictPublicCharSingleService = commDictPublicCharSingleService;
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
			CommDictPublicCharForm hosform = (CommDictPublicCharForm) form;
			CommDictPublicChar checkData = commDictPublicCharSingleService.checkData(hosform.getId());
			if (checkData != null) {
				String message = BaseCommMessage.getMsg("1-100001");
				hosform.setMessage(message);
				commDictPublicCharSingleService.addInit(hosform);
				request.setAttribute("commDictPublicChar", hosform);
				return mapping.findForward("add");
			}
			hosform.setInputCode(commConfigInputDictService.getInputCode(hosform.getDictValue()));
			 
			commDictPublicCharSingleService.save(hosform);
			CommDictPublicCharForm hosformNew = new CommDictPublicCharForm(); 
			hosformNew.setClassCode(hosform.getClassCode());
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
			CommDictPublicCharForm hosform = (CommDictPublicCharForm) form;
			//////// page start ////////////////////////
			PageBean pb = new PageBean();
			int count;
			int page = 0;
			
			int recordCount = commDictPublicCharSingleService.getCount(hosform.getId(), hosform.getClassCode(),hosform.getDictCode(), hosform.getDictValue(), hosform.getInputCode(), hosform.getSeqNo());
			pb.setCount(recordCount);
			String pageString = request.getParameter("page");
			//int pageSize = BaseCommInit.getPageSize("PAGE_SIZE");
			int pageSize = 10;
			if(request.getSession().getAttribute("page_282881f53463c89c013463c89cbe0000")!=null){
				pageSize = Integer.parseInt((String)request.getSession().getAttribute("page_282881f53463c89c013463c89cbe0000"));
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
			String isQuery = request.getParameter("isQuery");
			if(isQuery==null||!isQuery.equals("yes")){
				commDictPublicCharSingleService.getSearch(hosform, count, pageSize);
				commDictPublicCharSingleService.serchInit(hosform);
				hosform.setIdList(null);
				request.setAttribute("commDictPublicChar", hosform);
				return mapping.findForward("query");
			}
			request.setAttribute("pb", pb);
			// ////// page end ////////////////////////
			commDictPublicCharSingleService.getSearch(hosform, count, pageSize);
			commDictPublicCharSingleService.serchInit(hosform);
			request.setAttribute("commDictPublicChar", hosform);
			return mapping.findForward("query");
		}
		catch (Exception e) {
			return mapping.findForward("fail");
		}
	}

	public ActionForward updateInit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		try {
			CommDictPublicCharForm hosform = (CommDictPublicCharForm) form;
			commDictPublicCharSingleService.updateInit(hosform);
			request.setAttribute("commDictPublicChar", hosform);
			return mapping.findForward("update");
		}
		catch (Exception e) {
			return mapping.findForward("fail");
		}
	}

	public ActionForward addInit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		try {
			CommDictPublicCharForm hosform = (CommDictPublicCharForm)form;
			String classCode = request.getParameter("classCode");
			hosform.setClassCode(classCode);
			commDictPublicCharSingleService.addInit(hosform);
			request.setAttribute("commDictPublicChar", hosform);
			return mapping.findForward("add");
		}
		catch (Exception e) {
			return mapping.findForward("fail");
		}
	}

	public ActionForward detail(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		try {
			CommDictPublicCharForm hosform = (CommDictPublicCharForm) form;
			commDictPublicCharSingleService.showInit(hosform);
			commDictPublicCharSingleService.getDetail(hosform);
			request.setAttribute("commDictPublicChar", hosform);
			return mapping.findForward("detail");
		}
		catch (Exception e) {
			return mapping.findForward("fail");
		}
	}

	public ActionForward update(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		try {
			CommDictPublicCharForm hosform = (CommDictPublicCharForm) form;
			hosform.setInputCode(commConfigInputDictService.getInputCode(hosform.getDictValue()));
			commDictPublicCharSingleService.update(hosform);
			CommDictPublicCharForm hosformNew = new CommDictPublicCharForm();
			hosformNew.setClassCode(hosform.getClassCode());
			return this.query(mapping, hosformNew, request, response);
		}
		catch (Exception e) {
			return mapping.findForward("fail");
		}
	}

	public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		try {
			CommDictPublicCharForm hosform = (CommDictPublicCharForm) form;
			commDictPublicCharSingleService.delete(hosform);
			return this.query(mapping, hosform, request, response);
		}
		catch (Exception e) {
			return mapping.findForward("fail");
		}
	}

}
