package com.tianjian.comm.struts.action;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.tianjian.comm.bean.CommConfigPopulationNature;
import com.tianjian.comm.business.ICommConfigInputDictService;
import com.tianjian.comm.business.ICommConfigPopulationNatureService;
import com.tianjian.comm.business.ICommCongihMidwiferyService;
import com.tianjian.comm.struts.form.CommConfigPopulationNatureForm;
import com.tianjian.util.Converter;
import com.tianjian.util.ResourcesUtil;
import com.tianjian.util.comm.PageBean;

public class CommConfigPopulationNatureAction extends Action {

	private ICommConfigPopulationNatureService natureService;
	private ICommConfigInputDictService commConfigInputDictService;
	private ICommCongihMidwiferyService midService;
	
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// TODO Auto-generated method stub
		String verbId=request.getParameter("verbId");
		if(verbId.equals("query")){
			return this.query(mapping, form, request, response);
		}else if(verbId.equals("addInit")){
			return this.addInit(mapping, form, request, response);
		}else if(verbId.equals("add")){
			return this.add(mapping, form, request, response);
		}else if(verbId.equals("delete")){
			return this.delete(mapping, form, request, response);
		}else if(verbId.equals("detail")){
			return this.detail(mapping, form, request, response);
		}else{
			return null;
		}
	}
	public ActionForward query(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// TODO Auto-generated method stub
		
		CommConfigPopulationNatureForm cform=(CommConfigPopulationNatureForm) form;
		PageBean pb=new PageBean();
    	int count;
    	int page = 0;
    	int recordCount = this.getNatureService().getCount(cform);
    	pb.setCount(recordCount);
    	String pageString =request.getParameter("page");
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
	    this.getNatureService().getSearch(cform, count, pageSize);
		request.setAttribute("data", cform);
		
		return mapping.findForward("list");
	}
	public ActionForward addInit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// TODO Auto-generated method stub
		CommConfigPopulationNatureForm cform=new CommConfigPopulationNatureForm();
		String page=request.getParameter("page");
		int count =this.getMidService().getMaxNumber("CommConfigPopulationNature", "seqNo");
		cform.setSeqNo(String.valueOf(count));
		cform.setMypage(page);
		request.setAttribute("data", cform);
		return mapping.findForward("add");
	}
	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// TODO Auto-generated method stub
		CommConfigPopulationNatureForm cform=(CommConfigPopulationNatureForm) form;
		
		CommConfigPopulationNature comm=this.getNatureService().getByItemCode(cform.getItemCode());
		
		if(comm!=null){
			String message = ResourcesUtil.getValue("conf.comm.CommLocale", "comm.java.commom.codeExistRewrite", request) + "!";
			cform.setMessage(message);
			request.setAttribute("data", cform);
			return mapping.findForward("add");
		}else{
			cform.setInputCode(commConfigInputDictService.getInputCode(cform.getItemName()));
			this.getNatureService().save(cform);
			CommConfigPopulationNatureForm cform1=new CommConfigPopulationNatureForm();
			String message = ResourcesUtil.getValue("conf.comm.CommLocale", "comm.java.commom.addSuccess", request) + "!";
			cform1.setMessage(message);
			return this.query(mapping, cform1, request, response);
		}
	}
	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// TODO Auto-generated method stub
		CommConfigPopulationNatureForm cform=(CommConfigPopulationNatureForm) form;
		
		this.getNatureService().delete(cform.getItemCodeHidden());
		
		String message = ResourcesUtil.getValue("conf.comm.CommLocale", "comm.java.commom.deleteSuccess", request) + "!";
		cform.setMessage(message);
		return this.query(mapping, cform, request, response);
	}
	public ActionForward detail(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// TODO Auto-generated method stub
		CommConfigPopulationNatureForm cform=(CommConfigPopulationNatureForm) form;
		String page= request.getParameter("page");
		CommConfigPopulationNature comm=this.getNatureService().getByItemCode(cform.getItemCodeHidden());
		CommConfigPopulationNatureForm cform1=new CommConfigPopulationNatureForm();
		cform1.setMypage(page);
		cform1.setSeqNo(Converter.toBlank(comm.getSeqNo()));
		cform1.setItemCode(Converter.toBlank(comm.getItemCode()));
		cform1.setItemName(Converter.toBlank(comm.getItemName()));
		cform1.setInputCode(Converter.toBlank(comm.getInputCode()));
		cform1.setComments(Converter.toBlank(comm.getComments()));
		cform1.setItemCodeHidden(Converter.toBlank(cform.getItemCode()));
		cform1.setInputCodeHidden(Converter.toBlank(cform.getInputCode()));
		cform1.setItemNameHidden(Converter.toBlank(cform.getItemName()));
		request.setAttribute("data", cform1);
		return mapping.findForward("detail");
	}
	public ICommConfigPopulationNatureService getNatureService() {
		return natureService;
	}
	public void setNatureService(ICommConfigPopulationNatureService natureService) {
		this.natureService = natureService;
	}
	public ICommConfigInputDictService getCommConfigInputDictService() {
		return commConfigInputDictService;
	}
	public void setCommConfigInputDictService(
			ICommConfigInputDictService commConfigInputDictService) {
		this.commConfigInputDictService = commConfigInputDictService;
	}
	public ICommCongihMidwiferyService getMidService() {
		return midService;
	}
	public void setMidService(ICommCongihMidwiferyService midService) {
		this.midService = midService;
	}
}
