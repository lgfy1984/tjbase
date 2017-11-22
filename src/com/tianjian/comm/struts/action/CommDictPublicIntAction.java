//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_4.1.1/xslt/JavaClass.xsl
package com.tianjian.comm.struts.action;


import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.tianjian.comm.bean.CommDictPublicInt;
import com.tianjian.comm.business.ICommDictPublicIntService;
import com.tianjian.comm.struts.form.CommDictPublicIntForm;
import com.tianjian.security.struts.action.BaseAction;
import com.tianjian.util.ResourcesUtil;
import com.tianjian.util.comm.PageBean;
import com.tianjian.util.comm.TJInputDict;


public class CommDictPublicIntAction extends BaseAction {

	private ICommDictPublicIntService commDictPublicIntService;

	public ICommDictPublicIntService getcommDictPublicIntService() {
		return commDictPublicIntService;
	}

	public void setcommDictPublicIntService(ICommDictPublicIntService commDictPublicIntService) {
		this.commDictPublicIntService = commDictPublicIntService;
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
		} else if(verbId.equals("queryDetail")){
			return this.query(mapping, form, request, response);
		} else if (verbId.equals("update")) {
			return this.update(mapping, form, request, response);
		} else if (verbId.equals("updateInit")) {
			return this.updateInit(mapping, form, request, response);
		} else if (verbId.equals("detail")) {
			return this.detail(mapping, form, request, response);
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
			CommDictPublicIntForm hosform = (CommDictPublicIntForm) form;
			CommDictPublicInt checkData = commDictPublicIntService.checkData(hosform.getId());
			if (checkData != null) {
				//String message = BaseCommMessage.getMsg("600001");
				ServletContext application = request.getSession().getServletContext();
				String message = (String)application.getAttribute("comm.0-000001");
				hosform.setMessage(message);
				commDictPublicIntService.addInit(hosform);
				request.setAttribute("commDictPublicInt", hosform);
				return mapping.findForward("add");
			}
			TJInputDict dict = new TJInputDict(hosform.getDictValue());
			
			hosform.setInputCode(dict.getInputCode());
			commDictPublicIntService.save(hosform);
			CommDictPublicIntForm hosformNew = new CommDictPublicIntForm();
			String message = ResourcesUtil.getValue("conf.comm.CommLocale", "comm.java.commom.dataSaveSuccess", request) + "!";
			hosformNew.setMessage(message);
			return this.query(mapping, hosformNew, request, response);
		}
		catch (Exception e) {
			return mapping.findForward("fail");
		}
	}

	public ActionForward init(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		try {
			String verbId = request.getParameter("verbId");
			CommDictPublicIntForm ssform = (CommDictPublicIntForm) form;
			request.setAttribute("commDictPublicInt", ssform);
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
			CommDictPublicIntForm hosform = (CommDictPublicIntForm) form;
			//////// page start ////////////////////////
			PageBean pb = new PageBean();
			int count;
			int page = 0;
			
			int recordCount = commDictPublicIntService.getCount(hosform.getId(), hosform.getClassCode(),hosform.getDictCode(), hosform.getDictValue(), hosform.getInputCode(), hosform.getSeqNo());
			pb.setCount(recordCount);
			String pageString = request.getParameter("page");
			//int pageSize = BaseCommInit.getPageSize("PAGE_SIZE");
			//ServletContext application = request.getSession().getServletContext();
			//int pageSize = Integer.parseInt((String)application.getAttribute("EHRPProject_basecomm.PAGE_SIZE"));
			int pageSize = 10;
			if(request.getSession().getAttribute("page_282881f534636ed60134636ed7030000")!=null){
				pageSize = Integer.parseInt((String)request.getSession().getAttribute("page_282881f534636ed60134636ed7030000"));
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
			commDictPublicIntService.getSearch(hosform, count, pageSize);
			commDictPublicIntService.serchInit(hosform);
			request.setAttribute("commDictPublicInt", hosform);
			if(verbId.equals("queryDetail")){
				return mapping.findForward("queryDetail");
			}else{
				return mapping.findForward("query");			
			}
		}
		catch (Exception e) {
			return mapping.findForward("fail");
		}
	}

	public ActionForward updateInit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		try {
			CommDictPublicIntForm hosform = (CommDictPublicIntForm) form;
			commDictPublicIntService.updateInit(hosform);
			request.setAttribute("commDictPublicInt", hosform);
			return mapping.findForward("update");
		}
		catch (Exception e) {
			return mapping.findForward("fail");
		}
	}

	public ActionForward addInit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		try {
			//-------------------------新的实例化对象---------------------------------------
			CommDictPublicIntForm hosform = new CommDictPublicIntForm(); 
			commDictPublicIntService.addInit(hosform);
	         request.setAttribute("commDictPublicInt", hosform);
			return mapping.findForward("add");
		}
		catch (Exception e) {
			return mapping.findForward("fail");
		}
	}

	public ActionForward detail(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		try {
			CommDictPublicIntForm hosform = (CommDictPublicIntForm) form;
			commDictPublicIntService.showInit(hosform);
			commDictPublicIntService.getDetail(hosform);
			request.setAttribute("commDictPublicInt", hosform);
			return mapping.findForward("detail");
		}
		catch (Exception e) {
			return mapping.findForward("fail");
		}
	}

	public ActionForward update(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		try {
			CommDictPublicIntForm hosform = (CommDictPublicIntForm) form;
 			TJInputDict dict = new TJInputDict(hosform.getDictValue());	
 			hosform.setInputCode(dict.getInputCode());
			commDictPublicIntService.update(hosform);
			CommDictPublicIntForm hosformNew = new CommDictPublicIntForm();
			return this.query(mapping, hosformNew, request, response);
		}
		catch (Exception e) {
			return mapping.findForward("fail");
		}
	}

	public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		try {
			CommDictPublicIntForm hosform = (CommDictPublicIntForm) form;
			commDictPublicIntService.delete(hosform);
			return this.query(mapping, hosform, request, response);
		}
		catch (Exception e) {
			return mapping.findForward("fail");
		}
	}

}
