//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_4.1.1/xslt/JavaClass.xsl
package com.tianjian.comm.struts.action;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.tianjian.comm.bean.CommConfigVocation;
import com.tianjian.comm.business.ICommConfigInputDictService;
import com.tianjian.comm.business.ICommConfigVocationService;
import com.tianjian.comm.struts.form.CommConfigVocationForm;
import com.tianjian.security.struts.action.BaseAction;
import com.tianjian.util.comm.PageBean;

public class CommConfigVocationAction extends BaseAction {

	private ICommConfigVocationService commConfigVocationService;
	private ICommConfigInputDictService commConfigInputDictService;
	
	public ICommConfigVocationService getCommConfigVocationService() {
		return commConfigVocationService;
	}
	public void setCommConfigVocationService(
			ICommConfigVocationService commConfigVocationService) {
		this.commConfigVocationService = commConfigVocationService;
	}
	public ICommConfigInputDictService getCommConfigInputDictService() {
		return commConfigInputDictService;
	}
	public void setCommConfigInputDictService(ICommConfigInputDictService commConfigInputDictService) {
		this.commConfigInputDictService = commConfigInputDictService;
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
			CommConfigVocationForm hosform = (CommConfigVocationForm) form;
			CommConfigVocation checkData = commConfigVocationService.checkData(hosform.getItemCode());
			if (checkData != null) {
				//String message = CommMessage.getMsg("0-000001");
				ServletContext application = request.getSession().getServletContext();
				String message = (String)application.getAttribute("comm.0-000001");
				hosform.setMessage(message);
				commConfigVocationService.addInit(hosform);
				request.setAttribute("commConfigVocation", hosform);
				return mapping.findForward("add");
			}
			hosform.setInputCode(commConfigInputDictService.getInputCode(hosform.getItemName()));
			commConfigVocationService.save(hosform);
			CommConfigVocationForm hosformNew = new CommConfigVocationForm();
			return this.query(mapping, hosformNew, request, response);
		}
		catch (Exception e) {
			return mapping.findForward("fail");
		}
	}
	
	public ActionForward init(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		try {
			String verbId = request.getParameter("verbId");
			CommConfigVocationForm ssform = (CommConfigVocationForm) form;
			request.setAttribute("commConfigVocation", ssform);
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
			CommConfigVocationForm hosform = (CommConfigVocationForm) form;
			// ////// page start ////////////////////////
			PageBean pb = new PageBean();
			int count;
			int page = 0;
			int recordCount = commConfigVocationService.getCount(hosform.getItemCode(), hosform.getItemName(), hosform.getInputCode(), hosform.getSeqNo());
			pb.setCount(recordCount);
			String pageString = request.getParameter("page");
			//int pageSize = CommInit.getPageSize("PAGE_SIZE");
			int pageSize = 10;
			if(request.getSession().getAttribute("page_2828810b39763bf50139763bf5cf0000")!=null){
				pageSize = Integer.parseInt((String)request.getSession().getAttribute("page_2828810b39763bf50139763bf5cf0000"));
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
			commConfigVocationService.getSearch(hosform, count, pageSize);
			commConfigVocationService.serchInit(hosform);
			request.setAttribute("commConfigVocation", hosform);
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
			CommConfigVocationForm hosform = (CommConfigVocationForm) form;
			hosform.setItemCode(hosform.getItemCodeHidden());
			commConfigVocationService.updateInit(hosform);
			request.setAttribute("commConfigVocation", hosform);
			return mapping.findForward("update");
		}
		catch (Exception e) {
			return mapping.findForward("fail");
		}
	}

	public ActionForward addInit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		try {
			CommConfigVocationForm hosform = new CommConfigVocationForm();
			commConfigVocationService.addInit(hosform);
			request.setAttribute("commConfigVocation", hosform);
			return mapping.findForward("add");
		}
		catch (Exception e) {
			return mapping.findForward("fail");
		}
	}

	public ActionForward detail(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		try {
			CommConfigVocationForm hosform = (CommConfigVocationForm) form;
			hosform.setItemCode(hosform.getItemCodeHidden());
			commConfigVocationService.updateInit(hosform);
			request.setAttribute("commConfigVocation", hosform);
			return mapping.findForward("detail");
		}
		catch (Exception e) {
			return mapping.findForward("fail");
		}
	}

	public ActionForward update(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		try {
			CommConfigVocationForm hosform = (CommConfigVocationForm) form;			
			hosform.setInputCode(commConfigInputDictService.getInputCode(hosform.getItemName()));
			commConfigVocationService.update(hosform);
			CommConfigVocationForm hosformNew = new CommConfigVocationForm();
			return this.query(mapping, hosformNew, request, response);
		}
		catch (Exception e) {
			return mapping.findForward("fail");
		}
	}

	public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		try {
			CommConfigVocationForm hosform = (CommConfigVocationForm) form;
			commConfigVocationService.delete(hosform);
			CommConfigVocationForm hosformNew = new CommConfigVocationForm();
			return this.query(mapping, hosformNew, request, response);
		}
		catch (Exception e) {
			return mapping.findForward("fail");
		}
	}

}
