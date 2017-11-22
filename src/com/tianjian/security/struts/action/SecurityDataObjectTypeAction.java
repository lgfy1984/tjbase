package com.tianjian.security.struts.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.tianjian.security.bean.SecurityDataObjectType;
import com.tianjian.security.business.ISecurityDataObjectTypeService;
import com.tianjian.security.struts.form.SecurityDataObjectTypeForm;
import com.tianjian.util.ResourcesUtil;
import com.tianjian.util.comm.PageBean;

public class SecurityDataObjectTypeAction extends Action {
	private ISecurityDataObjectTypeService   securityDataObjectTypeService;
	
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String verbId=request.getParameter("verbId");
		System.out.println(verbId);
		if(verbId.equals("addinit")){
			return addInit(mapping, form, request, response);
		}else if(verbId.equals("add")){
			return add(mapping, form, request, response);
		}else if(verbId.equals("checkItemCode")){
			return checkId(mapping, form, request, response);
		}else if(verbId.equals("query")){
			return query(mapping, form, request, response);
		}else if(verbId.equals("queryDetail")){
			return query(mapping, form, request, response);
		}else if(verbId.equals("detail")){
			return detail(mapping, form, request, response);
		}else if(verbId.equals("delete")){
			return delete(mapping, form, request, response);
		}else if(verbId.equals("updateInit")){
			return updateInit(mapping, form, request, response);
		}else if(verbId.equals("update")){
			return update(mapping, form, request, response);
		}else if(verbId.equals("init")){
			return this.init(mapping, form, request, response);
		}else if(verbId.equals("initDetail")){
			return this.init(mapping, form, request, response);	
		}else{
			return null;
		}
	}
	public ActionForward addInit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return mapping.findForward("addInit");
	}
	public ActionForward update(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		SecurityDataObjectTypeForm sdofform=(SecurityDataObjectTypeForm)form;
		securityDataObjectTypeService.update(sdofform);
		sdofform.setId("");
		sdofform.setDataObjectTypeName("");
		sdofform.setComulnName("");
		return this.query(mapping, sdofform, request, response);
	}
	public ActionForward updateInit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String id=request.getParameter("itemCodeHidden");
		SecurityDataObjectType type=securityDataObjectTypeService.detail(id);
		request.setAttribute("id", type.getId());
		request.setAttribute("dataObjectTypeName", type.getDataObjectTypeName());
		request.setAttribute("columnName", type.getColumnName());
		return mapping.findForward("update");
	}
	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		SecurityDataObjectTypeForm sdofform=(SecurityDataObjectTypeForm)form;
		securityDataObjectTypeService.delete(sdofform);
		sdofform.setId("");
		return  this.query(mapping, sdofform, request, response);
	}
	public ActionForward detail(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		SecurityDataObjectTypeForm sdofform=(SecurityDataObjectTypeForm)form;
		securityDataObjectTypeService.detail(sdofform);
		request.setAttribute("data", sdofform);
		return mapping.findForward("detail");
	}
	public ActionForward query(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
			String verbId=request.getParameter("verbId");
			SecurityDataObjectTypeForm sdofform=(SecurityDataObjectTypeForm)form;
			
////////	page start   ////////////////////////
	    	PageBean pb=new PageBean();
	    	int count;
	    	int page = 0;
	    	int recordCount=securityDataObjectTypeService.getCount(sdofform.getId(), sdofform.getDataObjectTypeName(), sdofform.getComulnName());
	    	
	    	pb.setCount(recordCount);
	        String pageString = request.getParameter("page");
	        int pageSize = 10;
	        if(request.getSession().getAttribute("page_2828810b39b36c340139b36c347f0000")!=null){
				pageSize = Integer.parseInt((String)request.getSession().getAttribute("page_2828810b39b36c340139b36c347f0000"));
			}else{
				ServletContext application = request.getSession().getServletContext();
				pageSize = Integer.parseInt((String)application.getAttribute("comm.PAGE_SIZE"));
			}
	        //int pageSize = 10;
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
////////	page end   ////////////////////////
	        
	        securityDataObjectTypeService.getSearch(sdofform, count, pageSize);
		    request.setAttribute("data", sdofform);	
		    if(verbId.equals("queryDetail")){
				return mapping.findForward("queryDetail");
			}else{
				return mapping.findForward("query");
			}
	}
	public ActionForward checkId(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
			String id=request.getParameter("id");
		 	response.setHeader("Cache-Control","no-store");
			response.setHeader("Pragrma","no-cache");
			response.setDateHeader("Expires",0);
			response.setContentType("text/html;charset=utf-8");
			if(id.trim().length()>0){
				int count=securityDataObjectTypeService.check(id);
				if(count==1){
					PrintWriter out;
					try {
						out = response.getWriter();
						//out.print("数据对象类型ID已经存在!");
						out.print(ResourcesUtil.getValue("conf.security.securityInit", "security.java.commom.warn7", request));
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}
		    return mapping.findForward("");
	}
	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String id=request.getParameter("id");
		String dataObjectTypeName=request.getParameter("dataObjectTypeName");
		String comulnName=request.getParameter("comulnName");
		int count=securityDataObjectTypeService.check(id);
		if(count==1){
			request.setAttribute("message", ResourcesUtil.getValue("conf.security.securityInit", "security.java.commom.warn7", request));//
			request.setAttribute("id", id);
			request.setAttribute("dataObjectTypeName", dataObjectTypeName);
			request.setAttribute("comulnName", comulnName);
			return this.addInit(mapping, form, request, response);
		}else{
		boolean b=securityDataObjectTypeService.save(id, dataObjectTypeName, comulnName);
		if(b){
			//request.setAttribute("message", "添加成功！");
			SecurityDataObjectTypeForm sdofform = new SecurityDataObjectTypeForm();
			sdofform.setMessage(ResourcesUtil.getValue("conf.security.securityInit", "security.java.commom.warn1", request));
			return this.query(mapping, sdofform, request, response);
		}else{
			request.setAttribute("id", id);
			request.setAttribute("dataObjectTypeName", dataObjectTypeName);
			request.setAttribute("comulnName", comulnName);
			//request.setAttribute("message", "添加失败！");
			request.setAttribute("message", ResourcesUtil.getValue("conf.security.securityInit", "security.java.commom.warn2", request));
		}
	}
		return mapping.findForward("addInit");
	}
	
	public ActionForward init(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		try {
			String verbId = request.getParameter("verbId");
			SecurityDataObjectTypeForm ssform = (SecurityDataObjectTypeForm) form;
			request.setAttribute("data", ssform);
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
	
	public ISecurityDataObjectTypeService getSecurityDataObjectTypeService() {
		return securityDataObjectTypeService;
	}
	public void setSecurityDataObjectTypeService(
			ISecurityDataObjectTypeService securityDataObjectTypeService) {
		this.securityDataObjectTypeService = securityDataObjectTypeService;
	}
}
