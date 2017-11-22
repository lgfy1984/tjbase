package com.tianjian.comm.struts.action;

import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.tianjian.comm.bean.CommCongihMidwifery;
import com.tianjian.comm.business.ICommCongihMidwiferyService;
import com.tianjian.comm.struts.form.CommCongihMidwiferyForm;
import com.tianjian.util.ResourcesUtil;
import com.tianjian.util.comm.PageBean;

public class CommCongihMidwiferyAction extends Action {
	private ICommCongihMidwiferyService wifeService;
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// TODO Auto-generated method stub
		String verbId=request.getParameter("verbId");
		if(verbId.equals("query")){
			return this.query(mapping, form, request, response);
		}else if(verbId.equals("getType")){
			return this.getType(mapping, form, request, response);
		}else if(verbId.equals("addInit")){
			return this.addInit(mapping, form, request, response);
		}else if(verbId.equals("add")){
			return this.add(mapping, form, request, response);
		}else if(verbId.equals("delete")){
			return this.delete(mapping, form, request, response);
		}else{
			return null;
		}
	}
	public ActionForward query(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// TODO Auto-generated method stub
		CommCongihMidwiferyForm cform=(CommCongihMidwiferyForm) form;
		PageBean pb=new PageBean();
    	int count;
    	int page = 0;
    	int recordCount = this.getWifeService().getCount(cform);
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
	    this.getWifeService().getSearch(cform, count, pageSize);
	    //mainservice.addInit(hspform);
		request.setAttribute("data", cform);
		
		return mapping.findForward("list");
	}
	public ActionForward getType(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// TODO Auto-generated method stub
		String inputCode = request.getParameter("inputCode"); 
		String flag = request.getParameter("flag");//
		try {
			request.setCharacterEncoding("UTF-8");
			response.setContentType("text/xml; charset=UTF-8");
			response.setHeader("Cache-Control", "no-cache");
			PrintWriter out = response.getWriter();
			String xmlString = this.getWifeService().getXml(flag, inputCode,request);
			out.println(xmlString);    	
			out.close();
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return mapping.findForward("fail");
		}
	}
	public ActionForward addInit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// TODO Auto-generated method stub
		CommCongihMidwiferyForm cform=new CommCongihMidwiferyForm();
		int count=this.getWifeService().getMaxNumber("CommCongihMidwifery", "seqNo");
		cform.setSeqNo(String.valueOf(count));
		
		request.setAttribute("data", cform);
		return mapping.findForward("add");
	}
	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// TODO Auto-generated method stub
		CommCongihMidwiferyForm cform=(CommCongihMidwiferyForm) form;
		CommCongihMidwiferyForm cform1=new CommCongihMidwiferyForm();
		CommCongihMidwifery com=this.getWifeService().getByIds(cform);
		if(com!=null){
			String message = ResourcesUtil.getValue("conf.comm.CommLocale", "comm.java.CommCongihMidwiferyAction.dataExist", request) + "!";
			cform1.setMessage(message);
			cform1.setSeqNo(cform.getSeqNo());
			request.setAttribute("data", cform1);
			return mapping.findForward("add");
		}else{
			this.getWifeService().save(cform);
			String message = ResourcesUtil.getValue("conf.comm.CommLocale", "comm.java.CommCongihMidwiferyAction.saveSuccess", request) + "!";
			cform1.setMessage(message);
			return this.query(mapping, cform1, request, response);
		}
		
	}
	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		// TODO Auto-generated method stub
		CommCongihMidwiferyForm cform=(CommCongihMidwiferyForm) form;
		
		this.getWifeService().delete(cform);
		String message = ResourcesUtil.getValue("conf.comm.CommLocale", "", request) + "!";
		cform.setMessage(message);
		return this.query(mapping, cform, request, response);
	}
	public ICommCongihMidwiferyService getWifeService() {
		return wifeService;
	}
	public void setWifeService(ICommCongihMidwiferyService wifeService) {
		this.wifeService = wifeService;
	}
}
