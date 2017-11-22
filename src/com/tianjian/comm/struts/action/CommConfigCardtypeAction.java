package com.tianjian.comm.struts.action;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.tianjian.comm.bean.CommConfigCardtype;
import com.tianjian.comm.business.ICommConfigCardtypeService;
import com.tianjian.comm.business.ICommConfigInputDictService;
import com.tianjian.comm.struts.form.CommConfigCardtypeForm;
import com.tianjian.util.ResourcesUtil;
import com.tianjian.util.comm.PageBean;

public class CommConfigCardtypeAction extends Action {

	private ICommConfigCardtypeService commConfigCardtypeService;
	private ICommConfigInputDictService commConfigInputDictService;
	
	
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// TODO Auto-generated method stub
		String verbId=request.getParameter("verbId");
		if(verbId.equals("query")){
			return this.query(mapping, form, request, response);
		}else if(verbId.equals("add")){
			return this.add(mapping, form, request, response);
		}else if(verbId.equals("addInit")){
			return this.addInit(mapping, form, request, response);
		}else if(verbId.equals("delete")){
			return this.delete(mapping, form, request, response);
		}else if(verbId.equals("updateInit")){
			return this.updateInit(mapping, form, request, response);
		}else if(verbId.equals("detail")){
			return this.detail(mapping, form, request, response);
		}else{
			return mapping.findForward("fail");
		}
		
	}
	public ActionForward query(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// TODO Auto-generated method stub;
		CommConfigCardtypeForm hosform;
		hosform = (CommConfigCardtypeForm)form;
		if(hosform==null){
			hosform =new CommConfigCardtypeForm();
		}
		if(hosform.getVerbId().trim().equals("add")){
			hosform=new CommConfigCardtypeForm();
			String message = ResourcesUtil.getValue("conf.comm.CommLocale", "comm.java.CommConfigCardtypeAction.cardAddSuccess", request) + "!";
			hosform.setMessage(message);
    	}
		PageBean pb=new PageBean();
    	int count;
    	int page = 0;
    	int recordCount = commConfigCardtypeService.getCount(hosform.getItemCode()
				, hosform.getItemName()
				, hosform.getInputCode()
				, hosform.getSeqNo());
    	pb.setCount(recordCount);
        String pageString = request.getParameter("page");
        //int pageSize = UserinfoInit.getPageSize("PAGE_SIZE");
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
        commConfigCardtypeService.getSearch(hosform
				, count
				, pageSize);
        if(hosform.getVerbId().trim().equals("delete")){
        	String message = ResourcesUtil.getValue("conf.comm.CommLocale", "comm.java.CommConfigCardtypeAction.cardDelSuccess", request) + "!";
        	hosform.setMessage(message);
        }else if(hosform.getVerbId().trim().equals("updateInit")){
        	String message = ResourcesUtil.getValue("conf.comm.CommLocale", "comm.java.CommConfigCardtypeAction.cardStatusUpdateSuccess", request) + "!";
        	hosform.setMessage(message);
        }
        request.setAttribute("commConfigCardtype",hosform);
        return mapping.findForward("query");
	}
	public ActionForward addInit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// TODO Auto-generated method stub;
		CommConfigCardtypeForm hosform;
		hosform=(CommConfigCardtypeForm) form;
		if(hosform==null){
			hosform =new CommConfigCardtypeForm();
		}
		request.setAttribute("commConfigCardtype", hosform);
		return mapping.findForward("add");
	}
	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// TODO Auto-generated method stub;
		CommConfigCardtypeForm hosform=(CommConfigCardtypeForm) form;
		CommConfigCardtype nCommConfigCardtype=commConfigCardtypeService.checkData(hosform.getItemCode());
		if(nCommConfigCardtype!=null){
			String message = ResourcesUtil.getValue("conf.comm.CommLocale", "comm.java.CommConfigCardtypeAction.cardCodeExist", request) + "!";
			hosform.setMessage(message);
			request.setAttribute("commConfigCardtype", hosform);
		}
		hosform.setInputCode(commConfigInputDictService.getInputCode(hosform.getItemName()));
		commConfigCardtypeService.save(hosform);
        return this.query(mapping, form, request, response);
	}
	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// TODO Auto-generated method stub;
		CommConfigCardtypeForm hosform=(CommConfigCardtypeForm) form;
		commConfigCardtypeService.delete(hosform);
		return this.query(mapping, form, request, response);
	}
	public ActionForward updateInit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// TODO Auto-generated method stub;
		CommConfigCardtypeForm hosform=(CommConfigCardtypeForm) form;
		if(hosform.getStopFlag().trim().equals("0")){
			hosform.setStopFlag("1");
		}else{
			hosform.setStopFlag("0");
		}
		commConfigCardtypeService.update(hosform);
		return this.query(mapping, form, request, response);
	}
	public ActionForward detail(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// TODO Auto-generated method stub;
		CommConfigCardtypeForm hosform=(CommConfigCardtypeForm) form;
		
		commConfigCardtypeService.serchInit(hosform);
		request.setAttribute("commConfigCardtype", hosform);
		return mapping.findForward("detail");
	}
	public ICommConfigCardtypeService getCommConfigCardtypeService() {
		return commConfigCardtypeService;
	}
	public void setCommConfigCardtypeService(
			ICommConfigCardtypeService commConfigCardtypeService) {
		this.commConfigCardtypeService = commConfigCardtypeService;
	}
	public ICommConfigInputDictService getCommConfigInputDictService() {
		return commConfigInputDictService;
	}
	public void setCommConfigInputDictService(
			ICommConfigInputDictService commConfigInputDictService) {
		this.commConfigInputDictService = commConfigInputDictService;
	}

}
