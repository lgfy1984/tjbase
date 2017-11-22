package com.tianjian.comm.struts.action;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.tianjian.comm.bean.CommConfigDegreeLevel;
import com.tianjian.comm.business.ICommConfigDegreeLevelService;
import com.tianjian.comm.business.ICommConfigInputDictService;
import com.tianjian.comm.struts.form.CommConfigDegreeLevelForm;
import com.tianjian.util.comm.PageBean;

public class CommConfigDegreeLevelAction extends Action{

	private ICommConfigDegreeLevelService service;
	private ICommConfigInputDictService commConfigInputDictService;
	public ICommConfigInputDictService getCommConfigInputDictService() {
		return commConfigInputDictService;
	}
	public void setCommConfigInputDictService(
			ICommConfigInputDictService commConfigInputDictService) {
		this.commConfigInputDictService = commConfigInputDictService;
	}
	public ICommConfigDegreeLevelService getService() {
		return service;
	}
	public void setService(ICommConfigDegreeLevelService service) {
		this.service = service;
	}

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		String verbId = request.getParameter("verbId");
		System.out.println(this.getClass().getName()+".verbId="+verbId);
		if("addInit".equals(verbId)){
			return this.addInit(mapping, form, request, response);
		}else if("add".equals(verbId)){
			return this.add(mapping, form, request, response);
		}else if("init".equals(verbId)){
			return this.init(mapping, form, request, response);
		}else if("query".equals(verbId)){
			return this.query(mapping, form, request, response);
		}else if("detail".equals(verbId)){
			return this.detail(mapping, form, request, response);
		}else if("updateInit".equals(verbId)){
			return this.updateInit(mapping, form, request, response);
		}else if ("update".equals(verbId)){
			return this.update(mapping, form, request, response);
		}else if ("delete".equals(verbId)){
			return this.delete(mapping, form, request, response);
		}else {
			return mapping.findForward("fail");
		}
	}

	private ActionForward addInit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		try{
			CommConfigDegreeLevelForm hosform = new CommConfigDegreeLevelForm();
			this.getService().addInit(hosform);
			request.setAttribute("data", hosform);
			return mapping.findForward("add");
		}catch(Exception e){
//			e.printStackTrace();
			return mapping.findForward("fail");
		}
	}

	private ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		try{
			CommConfigDegreeLevelForm hosform = (CommConfigDegreeLevelForm)form;
			CommConfigDegreeLevel dataChecked = this.getService().checkData(hosform);
			if(dataChecked!=null){
				hosform.setMessage("代码已被占用,请修改!");
				this.getService().addInit(hosform);
				request.setAttribute("data", hosform);
				return mapping.findForward("add");
			}
			hosform.setInputCode(this.getCommConfigInputDictService().getInputCode(hosform.getItemName()));
			this.getService().save(hosform);
			request.setAttribute("data", hosform);
			return this.addInit(mapping, form, request, response);
		}catch(Exception e){
//			e.printStackTrace();
			return mapping.findForward("fail");
		}
	}
	private ActionForward init(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		try{
			CommConfigDegreeLevelForm hosform = (CommConfigDegreeLevelForm)form;
			request.setAttribute("data", hosform);
			return mapping.findForward("query");
		}catch(Exception e){
//			e.printStackTrace();
			return mapping.findForward("fail");
		}
	}
	
	private ActionForward query(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		try{
			CommConfigDegreeLevelForm hosform = (CommConfigDegreeLevelForm)form;

			PageBean pb = new PageBean();
			int count = 0;
			int page = 0;
			int recordCount = service.getCount(hosform);

			pb.setCount(recordCount);
			String pageString = request.getParameter("page");
			//int pageSize = CommInit.getPageSize("PAGE_SIZE");
			ServletContext application = request.getSession().getServletContext();
			int pageSize = Integer.parseInt((String)application.getAttribute("comm.PAGE_SIZE"));
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
			request.setAttribute("pb",pb );

			this.getService().getSearch(hosform, count, pageSize);
			this.getService().searchInit(hosform);

			request.setAttribute("data", hosform);
			return mapping.findForward("query");
		}catch(Exception e){
//			e.printStackTrace();
			return mapping.findForward("fail");
		}
	}

	private ActionForward detail(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		try{
			CommConfigDegreeLevelForm hosform = (CommConfigDegreeLevelForm)form;
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
			CommConfigDegreeLevelForm hosform = (CommConfigDegreeLevelForm)form;
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
			CommConfigDegreeLevelForm hosform = (CommConfigDegreeLevelForm)form;
			hosform.setInputCode(this.getCommConfigInputDictService().getInputCode(hosform.getItemName()));
			this.getService().update(hosform);
			hosform = null;
			hosform = new CommConfigDegreeLevelForm();
			hosform.setMessage("Update succeeded!");
			return this.query(mapping, hosform, request, response);
		}catch(Exception e){
//			e.printStackTrace();
			return mapping.findForward("fail");
		}
	}

	private ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		try{
			CommConfigDegreeLevelForm hosform = (CommConfigDegreeLevelForm)form;
			this.getService().delete(hosform);
			hosform = null;
			hosform = new CommConfigDegreeLevelForm();
			hosform.setMessage("Delete succeeded!");
			return this.query(mapping, hosform, request, response);
		}catch(Exception e){
//			e.printStackTrace();
			return mapping.findForward("fail");
		}
	}
}
