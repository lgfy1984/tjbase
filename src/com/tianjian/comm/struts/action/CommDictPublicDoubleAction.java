//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_4.1.1/xslt/JavaClass.xsl
package com.tianjian.comm.struts.action;


import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.tianjian.comm.bean.CommDictPublicDouble;
import com.tianjian.comm.business.ICommDictPublicDoubleService;
import com.tianjian.comm.struts.form.CommDictPublicDoubleForm;
import com.tianjian.security.struts.action.BaseAction;
import com.tianjian.util.ResourcesUtil;
import com.tianjian.util.comm.PageBean;
import com.tianjian.util.comm.TJInputDict;


public class CommDictPublicDoubleAction extends BaseAction {

	private ICommDictPublicDoubleService commDictPublicDoubleService;

	public ICommDictPublicDoubleService getcommDictPublicDoubleService() {
		return commDictPublicDoubleService;
	}

	public void setcommDictPublicDoubleService(ICommDictPublicDoubleService commDictPublicDoubleService) {
		this.commDictPublicDoubleService = commDictPublicDoubleService;
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
			CommDictPublicDoubleForm hosform = (CommDictPublicDoubleForm) form;
			CommDictPublicDouble checkData = commDictPublicDoubleService.checkData(hosform.getId());
			if (checkData != null) {
				//String message = BaseCommMessage.getMsg("1-000001");
				ServletContext application = request.getSession().getServletContext();
				String message = (String)application.getAttribute("comm.0-000001");
				hosform.setMessage(message);
				commDictPublicDoubleService.addInit(hosform);
				request.setAttribute("commDictPublicDouble", hosform);
				return mapping.findForward("add");
			}
			TJInputDict dict = new TJInputDict(hosform.getDictValue());
			
			hosform.setInputCode(dict.getInputCode());
			commDictPublicDoubleService.save(hosform);
			CommDictPublicDoubleForm hosformNew = new CommDictPublicDoubleForm();
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
			CommDictPublicDoubleForm ssform = (CommDictPublicDoubleForm) form;
			request.setAttribute("commDictPublicDouble", ssform);
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
			CommDictPublicDoubleForm hosform = (CommDictPublicDoubleForm) form;
			//////// page start ////////////////////////
			PageBean pb = new PageBean();
			int count;
			int page = 0;
			
			int recordCount = commDictPublicDoubleService.getCount(hosform.getId(), hosform.getClassCode(),hosform.getDictCode(), hosform.getDictValue(), hosform.getInputCode(), hosform.getSeqNo());
			pb.setCount(recordCount);
			String pageString = request.getParameter("page");
			//int pageSize = BaseCommInit.getPageSize("PAGE_SIZE");
			//ServletContext application = request.getSession().getServletContext();
			//int pageSize = Integer.parseInt((String)application.getAttribute("EHRPProject_basecomm.PAGE_SIZE"));
			int pageSize = 10;
			if(request.getSession().getAttribute("page_282881f534636ef10134636ef16a0000")!=null){
				pageSize = Integer.parseInt((String)request.getSession().getAttribute("page_282881f534636ef10134636ef16a0000"));
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
			commDictPublicDoubleService.getSearch(hosform, count, pageSize);
			commDictPublicDoubleService.serchInit(hosform);
			request.setAttribute("commDictPublicDouble", hosform);
			if(verbId.equals("queryDetail")){
				return mapping.findForward("queryDetail");
			}else{
				return mapping.findForward("query");			
			}
		}catch (Exception e) {
			return mapping.findForward("fail");
		}
	}

	public ActionForward updateInit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		try {
			CommDictPublicDoubleForm hosform = (CommDictPublicDoubleForm) form;
			commDictPublicDoubleService.updateInit(hosform);
			request.setAttribute("commDictPublicDouble", hosform);
			return mapping.findForward("update");
		}
		catch (Exception e) {
			return mapping.findForward("fail");
		}
	}

	public ActionForward addInit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		try {
			//-------------------------新的实例化对象---------------------------------------
			CommDictPublicDoubleForm hosform = new CommDictPublicDoubleForm();
			commDictPublicDoubleService.addInit(hosform);
	
			request.setAttribute("commDictPublicDouble", hosform);
			return mapping.findForward("add");
		}
		catch (Exception e) {
			return mapping.findForward("fail");
		}
	}

	public ActionForward detail(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		try {
			CommDictPublicDoubleForm hosform = (CommDictPublicDoubleForm) form;
			commDictPublicDoubleService.showInit(hosform);
			commDictPublicDoubleService.getDetail(hosform);
			
			request.setAttribute("commDictPublicDouble", hosform);
			return mapping.findForward("detail");
		}
		catch (Exception e) {
			return mapping.findForward("fail");
		}
	}

	public ActionForward update(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		try {
			CommDictPublicDoubleForm hosform = (CommDictPublicDoubleForm) form;
 			TJInputDict dict = new TJInputDict(hosform.getDictValue());	
 			hosform.setInputCode(dict.getInputCode());
			commDictPublicDoubleService.update(hosform);
			CommDictPublicDoubleForm hosformNew = new CommDictPublicDoubleForm();
			return this.query(mapping, hosformNew, request, response);
		}
		catch (Exception e) {
			return mapping.findForward("fail");
		}
	}

	public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		try {
			CommDictPublicDoubleForm hosform = (CommDictPublicDoubleForm) form;
			commDictPublicDoubleService.delete(hosform);
			return this.query(mapping, hosform, request, response);
		}
		catch (Exception e) {
			return mapping.findForward("fail");
		}
	}

}
