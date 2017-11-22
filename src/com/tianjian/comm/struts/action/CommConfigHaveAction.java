//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_4.1.1/xslt/JavaClass.xsl
package com.tianjian.comm.struts.action;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.tianjian.comm.bean.CommConfigHave;
import com.tianjian.comm.business.ICommConfigHaveService;
import com.tianjian.comm.business.ICommConfigInputDictService;
import com.tianjian.comm.struts.form.CommConfigHaveForm;
import com.tianjian.security.struts.action.BaseAction;
import com.tianjian.util.comm.PageBean;

public class CommConfigHaveAction extends BaseAction {

	private ICommConfigHaveService commConfigHaveService;
	private ICommConfigInputDictService commConfigInputDictService;
	
	public ICommConfigHaveService getCommConfigHaveService() {
		return commConfigHaveService;
	}
	public void setCommConfigHaveService(
			ICommConfigHaveService commConfigHaveService) {
		this.commConfigHaveService = commConfigHaveService;
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
			CommConfigHaveForm hosform = (CommConfigHaveForm) form;
			CommConfigHave checkData = commConfigHaveService.checkData(hosform.getItemCode());
			if (checkData != null) {
				//String message = CommMessage.getMsg("0-000001");
				ServletContext application = request.getSession().getServletContext();
				String message = (String)application.getAttribute("comm.0-000001");
				hosform.setMessage(message);
				commConfigHaveService.addInit(hosform);
				request.setAttribute("commConfigHave", hosform);
				return mapping.findForward("add");
			}
			hosform.setInputCode(commConfigInputDictService.getInputCode(hosform.getItemName()));
			this.commConfigHaveService.save(hosform);
			CommConfigHaveForm hosformNew = new CommConfigHaveForm();
			return this.query(mapping, hosformNew, request, response);
		}
		catch (Exception e) {
			return mapping.findForward("fail");
		}
	}

	public ActionForward init(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		try {
			String verbId = request.getParameter("verbId");
			CommConfigHaveForm ssform = (CommConfigHaveForm) form;
			request.setAttribute("commConfigHave", ssform);
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
			CommConfigHaveForm hosform = (CommConfigHaveForm) form;
			// ////// page start ////////////////////////
			PageBean pb = new PageBean();
			int count;
			int page = 0;
			int recordCount = commConfigHaveService.getCount(hosform.getItemCode(), hosform.getItemName(), hosform.getInputCode(), hosform.getSeqNo());
			pb.setCount(recordCount);
			String pageString = request.getParameter("page");
			//int pageSize = CommInit.getPageSize("PAGE_SIZE");
			//ServletContext application = request.getSession().getServletContext();
			//int pageSize = Integer.parseInt((String)application.getAttribute("EHRPProject_basecomm.PAGE_SIZE"));
			int pageSize = 10;
			if(request.getSession().getAttribute("page_282881f534638fc20134638fc2ca0000")!=null){
				pageSize = Integer.parseInt((String)request.getSession().getAttribute("page_282881f534638fc20134638fc2ca0000"));
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
			commConfigHaveService.getSearch(hosform, count, pageSize);
			commConfigHaveService.serchInit(hosform);
			request.setAttribute("commConfigHave", hosform);
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
			CommConfigHaveForm hosform = (CommConfigHaveForm) form;
			hosform.setItemCode(hosform.getItemCodeHidden());
			commConfigHaveService.updateInit(hosform);
			request.setAttribute("commConfigHave", hosform);
			return mapping.findForward("update");
		}
		catch (Exception e) {
			return mapping.findForward("fail");
		}
	}

	public ActionForward addInit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		try {
			CommConfigHaveForm hosform = new CommConfigHaveForm();
			commConfigHaveService.addInit(hosform);
			request.setAttribute("commConfigHave", hosform);
			return mapping.findForward("add");
		}
		catch (Exception e) {
			return mapping.findForward("fail");
		}
	}

	public ActionForward detail(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		try {
			CommConfigHaveForm hosform = (CommConfigHaveForm) form;
			hosform.setItemCode(hosform.getItemCodeHidden());
			commConfigHaveService.updateInit(hosform);
			request.setAttribute("commConfigHave", hosform);
			return mapping.findForward("detail");
		}
		catch (Exception e) {
			return mapping.findForward("fail");
		}
	}

	public ActionForward update(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		try {
			CommConfigHaveForm hosform = (CommConfigHaveForm) form;			
			hosform.setInputCode(commConfigInputDictService.getInputCode(hosform.getItemName()));
			commConfigHaveService.update(hosform);
			CommConfigHaveForm hosformNew = new CommConfigHaveForm();
			return this.query(mapping, hosformNew, request, response);
		}
		catch (Exception e) {
			return mapping.findForward("fail");
		}
	}

	public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		try {
			CommConfigHaveForm hosform = (CommConfigHaveForm) form;
			commConfigHaveService.delete(hosform);
			CommConfigHaveForm hosformNew = new CommConfigHaveForm();
			return this.query(mapping, hosformNew, request, response);
		}
		catch (Exception e) {
			return mapping.findForward("fail");
		}
	}

}
