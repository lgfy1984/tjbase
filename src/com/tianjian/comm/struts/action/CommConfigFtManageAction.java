package com.tianjian.comm.struts.action;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.tianjian.comm.bean.CommConfigFtManage;
import com.tianjian.comm.business.ICommConfigFtManageService;
import com.tianjian.comm.business.ICommConfigInputDictService;
import com.tianjian.comm.struts.form.CommConfigFtManageForm;
import com.tianjian.util.ResourcesUtil;
import com.tianjian.util.comm.PageBean;

/**
 * 机构分类管理字典
 * @author DZENALL
 * @since 2009-03-17 16:41
 */
public class CommConfigFtManageAction extends Action{

	private ICommConfigFtManageService service;
	private ICommConfigInputDictService commConfigInputDictService;	
	public ICommConfigFtManageService getService() {
		return service;
	}
	public void setService(ICommConfigFtManageService service) {
		this.service = service;
	}
	public ICommConfigInputDictService getCommConfigInputDictService() {
		return commConfigInputDictService;
	}
	public void setCommConfigInputDictService(ICommConfigInputDictService commConfigInputDictService) {
		this.commConfigInputDictService = commConfigInputDictService;
	}


	
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String verbId = request.getParameter("verbId");
		if(verbId.equals("addInit")){
			return this.addInit(mapping, form, request, response);
		}else if(verbId.equals("add")){
			return this.add(mapping, form, request, response);
		}else if(verbId.equals("query")){
			return this.query(mapping, form, request, response);
		}else if(verbId.equals("detail")){
			return this.detail(mapping, form, request, response);
		}else if(verbId.equals("updateInit")){
			return this.updateInit(mapping, form, request, response);
		}else if (verbId.equals("update")){
			return this.update(mapping, form, request, response);
		}else if (verbId.equals("delete")){
			return this.delete(mapping, form, request, response);
		}else {
			return mapping.findForward("fail");
		}
	}

	private ActionForward addInit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		try{
			CommConfigFtManageForm hosform = (CommConfigFtManageForm)form;
			this.getService().addInit(hosform);
			request.setAttribute("data", hosform);		        
			return mapping.findForward("add");
		}catch(Exception e){
			e.printStackTrace();
			return mapping.findForward("fail");
		}
	}

	private ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		try{
			CommConfigFtManageForm hosform = (CommConfigFtManageForm)form;
			CommConfigFtManage dataChecked = this.getService().checkData(hosform);
			if(dataChecked!=null){
				String message = ResourcesUtil.getValue("conf.comm.CommLocale", "comm.java.commom.dataClash", request) + "!";
				hosform.setMessage(message);
				request.setAttribute("data", hosform);
				return mapping.findForward("add");
			}
			hosform.setInputCode(this.getCommConfigInputDictService().getInputCode(hosform.getItemName()));
			this.getService().save(hosform);
			hosform = null;
			hosform = new CommConfigFtManageForm();
			String message = ResourcesUtil.getValue("conf.comm.CommLocale", "comm.java.commom.newAddSuccess", request) + "!";
			hosform.setMessage(message);
			request.setAttribute("data", hosform);		        
			return this.addInit(mapping, hosform, request, response);
		}catch(Exception e){
			e.printStackTrace();
			return mapping.findForward("fail");
		}
	}

	private ActionForward query(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		try{
			CommConfigFtManageForm hosform = (CommConfigFtManageForm)form;

			PageBean pb = new PageBean();
			int count = 0;
			int page = 0;
			int recordCount = service.getCount(hosform);

			pb.setCount(recordCount);
			String pageString = request.getParameter("page");
			//int pageSize = CommInit.getPageSize("PAGE_SIZE");
			int pageSize = 10;
			if(request.getSession().getAttribute("page_282881f53463c89c013463c89cbe0000")!=null){
				pageSize = Integer.parseInt((String)request.getSession().getAttribute("page_282881f53463c89c013463c89cbe0000"));
			}else{
				ServletContext application = request.getSession().getServletContext();
				pageSize = Integer.parseInt((String)application.getAttribute("comm.PAGE_SIZE"));
			}
			pb.setPageSize(pageSize);
			if(pageString == null || pageString.equals("") || pageString.equals("0")){
				page = 1;
				pb.setPage(1);
				count = (page - 1) * pageSize;
			}else{	        	
				page = Integer.parseInt(pageString);
				pb.setPage(page);
				count = (page - 1) * pageSize  ;
			}
			String isQuery = request.getParameter("isQuery");
			if(isQuery==null||!isQuery.equals("yes")){
				request.setAttribute("data", null);
				return mapping.findForward("query");
			}
			request.setAttribute("pb",pb );

			this.getService().getSearch(hosform, count, pageSize);
			this.getService().searchInit(hosform);

			request.setAttribute("data", hosform);
			return mapping.findForward("query");
		}catch(Exception e){
			e.printStackTrace();
			return mapping.findForward("fail");
		}
	}

	private ActionForward detail(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		try{
			CommConfigFtManageForm hosform = (CommConfigFtManageForm)form;
			this.getService().updateInit(hosform);
			request.setAttribute("data", hosform);		        
			return mapping.findForward("detail");
		}catch(Exception e){
//			e.printStackTrace();
			return mapping.findForward("fail");
		}
	}

	private ActionForward updateInit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		try{
			CommConfigFtManageForm hosform = (CommConfigFtManageForm)form;
			request.setAttribute("data", hosform);	
			this.getService().updateInit(hosform);
			return mapping.findForward("update");
		}catch(Exception e){
//			e.printStackTrace();
			return mapping.findForward("fail");
		}
	}

	private ActionForward update(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		try{
			CommConfigFtManageForm hosform = (CommConfigFtManageForm)form;
			hosform.setInputCode(this.getCommConfigInputDictService().getInputCode(hosform.getItemName()));
			this.getService().update(hosform);
			hosform = null;
			hosform = new CommConfigFtManageForm();
			String message = ResourcesUtil.getValue("conf.comm.CommLocale", "comm.java.commom.updateSuccess", request) + "!";
			hosform.setMessage(message);
			return this.query(mapping, hosform, request, response);
		}catch(Exception e){
//			e.printStackTrace();
			return mapping.findForward("fail");
		}
	}

	private ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		try{
			CommConfigFtManageForm hosform = (CommConfigFtManageForm)form;
			this.getService().delete(hosform);
			hosform = null;
			hosform = new CommConfigFtManageForm();
			String message = ResourcesUtil.getValue("conf.comm.CommLocale", "comm.java.commom.deleteSuccess", request) + "!";
			hosform.setMessage(message);
			return this.query(mapping, hosform, request, response);
		}catch(Exception e){
//			e.printStackTrace();
			return mapping.findForward("fail");
		}
	}	
}