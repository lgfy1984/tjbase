package com.tianjian.comm.struts.action;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.tianjian.comm.bean.CommConfigSettype;
import com.tianjian.comm.business.ICommConfigSettypeService;
import com.tianjian.comm.business.ICommConfigInputDictService;
import com.tianjian.comm.struts.form.CommConfigSettypeForm;
import com.tianjian.security.struts.action.BaseAction;
import com.tianjian.util.ResourcesUtil;
import com.tianjian.util.comm.PageBean;

public class CommConfigSettypeAction extends BaseAction {
	private ICommConfigSettypeService commConfigSettypeService;
	private ICommConfigInputDictService commConfigInputDictService;

	public ICommConfigInputDictService getCommConfigInputDictService() {
		return commConfigInputDictService;
	}

	public void setCommConfigInputDictService(ICommConfigInputDictService commConfigInputDictService) {
		this.commConfigInputDictService = commConfigInputDictService;
	}
	public ICommConfigSettypeService getCommConfigSettypeService() {
		return commConfigSettypeService;
	}

	public void setCommConfigSettypeService(ICommConfigSettypeService commConfigSettypeService) {
		this.commConfigSettypeService = commConfigSettypeService;
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
			CommConfigSettypeForm hosform = (CommConfigSettypeForm) form;
			System.out.println(hosform.getItemCode()+"&&&&&&");
			CommConfigSettype checkData = commConfigSettypeService.checkData(hosform.getItemCode());
			if (checkData != null) {
				System.out.println("*********");
				//String message = BaseCommMessage.getMsg("1-000001");
				ServletContext application = request.getSession(true).getServletContext();
				String message = (String)application.getAttribute("comm.0-000001");
				hosform.setMessage(message);
				commConfigSettypeService.addInit(hosform);
				request.setAttribute("commConfigSettype", hosform);
				return mapping.findForward("add");
			}
			System.out.println(hosform.getItemCode()+"&&&&&&");
			hosform.setInputCode(commConfigInputDictService.getInputCode(hosform.getItemName()));
			commConfigSettypeService.save(hosform);
			CommConfigSettypeForm hosformNew = new CommConfigSettypeForm();
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
			CommConfigSettypeForm hosform = (CommConfigSettypeForm) form;
			// ////// page start ////////////////////////
			PageBean pb = new PageBean();
			int count;
			int page = 0;
			int recordCount = commConfigSettypeService.getCount(hosform.getItemCode(), hosform.getItemName(), hosform.getInputCode(), hosform.getSeqNo());
			pb.setCount(recordCount);
			String pageString = request.getParameter("page");
			//int pageSize = BaseCommInit.getPageSize("PAGE_SIZE");
			int pageSize = 10;
			if(request.getSession().getAttribute("page_282881f53463c89c013463c89cbe0000")!=null){
				pageSize = Integer.parseInt((String)request.getSession().getAttribute("page_282881f53463c89c013463c89cbe0000"));
			}else{
				ServletContext application = request.getSession(true).getServletContext();
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
				request.setAttribute("data", null);
				commConfigSettypeService.getSearch(hosform, count, pageSize);
				commConfigSettypeService.serchInit(hosform);
				hosform.setItemCodeList(null);
				request.setAttribute("commConfigSettype", hosform);
				return mapping.findForward("query");
			}
			request.setAttribute("pb", pb);
			// ////// page end ////////////////////////
			commConfigSettypeService.getSearch(hosform, count, pageSize);
			commConfigSettypeService.serchInit(hosform);
			request.setAttribute("commConfigSettype", hosform);
			return mapping.findForward("query");
		}
		catch (Exception e) {
			e.printStackTrace();
			return mapping.findForward("fail");
		}
	}

	public ActionForward updateInit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		try {
			CommConfigSettypeForm hosform = (CommConfigSettypeForm) form;
			commConfigSettypeService.updateInit(hosform);
			request.setAttribute("commConfigSettype", hosform);
			return mapping.findForward("update");
		}
		catch (Exception e) {
			return mapping.findForward("fail");
		}
	}

	public ActionForward addInit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		try {
			CommConfigSettypeForm hosform = new CommConfigSettypeForm();
			commConfigSettypeService.addInit(hosform);
			request.setAttribute("commConfigSettype", hosform);
			return mapping.findForward("add");
		}
		catch (Exception e) {
			return mapping.findForward("fail");
		}
	}

	public ActionForward detail(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		try {
			CommConfigSettypeForm hosform = (CommConfigSettypeForm) form;
			commConfigSettypeService.showInit(hosform);
			request.setAttribute("commConfigSettype", hosform);
			return mapping.findForward("detail");
		}
		catch (Exception e) {
			return mapping.findForward("fail");
		}
	}

	public ActionForward update(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		try {
			CommConfigSettypeForm hosform = (CommConfigSettypeForm) form;
			hosform.setInputCode(commConfigInputDictService.getInputCode(hosform.getItemName()));
			commConfigSettypeService.update(hosform);
			CommConfigSettypeForm hosformNew = new CommConfigSettypeForm();
			return this.query(mapping, hosformNew, request, response);
		}
		catch (Exception e) {
			return mapping.findForward("fail");
		}
	}

	public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		try {
			CommConfigSettypeForm hosform = (CommConfigSettypeForm) form;
			commConfigSettypeService.delete(hosform);
			return this.query(mapping, hosform, request, response);
		}
		catch (Exception e) {
			return mapping.findForward("fail");
		}
	}
}
