package com.tianjian.security.struts.action;

import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.tianjian.security.bean.SecurityConfigParameter;
import com.tianjian.security.business.ISecurityConfigParameterService;
import com.tianjian.security.struts.form.SecurityConfigParameterForm;
import com.tianjian.util.ResourcesUtil;
import com.tianjian.util.comm.PageBean;

public class SecurityConfigParameterAction extends Action {
	private ISecurityConfigParameterService securityConfigParameterService;

	public ISecurityConfigParameterService getSecurityConfigParameterService() {
		return securityConfigParameterService;
	}

	public void setSecurityConfigParameterService(
			ISecurityConfigParameterService securityConfigParameterService) {
		this.securityConfigParameterService = securityConfigParameterService;
	}

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String verbId = request.getParameter("verbId");
		if (verbId.equals("query")) {
			return this.query(mapping, form, request, response);
		}else if(verbId.equals("queryDetail")){
			return this.query(mapping, form, request, response);
		}else if (verbId.equals("detail")) {
			return this.detail(mapping, form, request, response);
		} else if (verbId.equals("delete")) {
			return this.delete(mapping, form, request, response);
		} else if (verbId.equals("updateInit")) {
			return this.updateInit(mapping, form, request, response);
		} else if (verbId.equals("update")) {
			return this.update(mapping, form, request, response);
		} else if (verbId.equals("add")) {
			return this.add(mapping, form, request, response);
		} else if (verbId.equals("addList")) {
			return this.addList(mapping, form, request, response);
		} else if (verbId.equals("addInit")) {
			return this.addInit(mapping, form, request, response);
		}else if (verbId.equals("system")){
			return this.getSystem(mapping, form, request, response);
		}else if (verbId.equals("refreshInit")){
			return this.refreshInit(mapping, form, request, response);
		}else if(verbId.equals("getHsp")){
			return this.getHsp(mapping, form, request, response);
		}else if(verbId.equals("init")){
			return this.init(mapping, form, request, response);
		}else if(verbId.equals("initDetail")){
			return this.init(mapping, form, request, response);
		} else {
			return null;
		}
	}

	public ActionForward query(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		try {
			String verbId = request.getParameter("verbId");
			SecurityConfigParameterForm scpcForm = (SecurityConfigParameterForm) form;
			PageBean pb = new PageBean();
			int count;
			int page = 0;
			int recordCount = securityConfigParameterService.getCount(scpcForm);
			if(recordCount == 0){
				//查询条件有误，未查询到结果
				String str = ResourcesUtil.getValue("conf.security.securityInit", "comm.java.security.warn1", request);
				scpcForm.setMessage(str);
			}
			pb.setCount(recordCount);
			String pageString = "";
			if(scpcForm.getPageNow() != null&&scpcForm.getPageNow() != ""){
				pageString = scpcForm.getPageNow();
			}else{
				pageString = request.getParameter("page");
			}
			//ServletContext application = request.getSession().getServletContext();
			//int pageSize = Integer.parseInt((String)application.getAttribute("EHRPProject_basesecurity.PAGE_SIZE"));
			int pageSize = 10;
			if(request.getSession().getAttribute("page_282881f53464511d013464511d870000")!=null){
				pageSize = Integer.parseInt((String)request.getSession().getAttribute("page_282881f53464511d013464511d870000"));
			}else{
				ServletContext application = request.getSession().getServletContext();
				pageSize = Integer.parseInt((String)application.getAttribute("security.PAGE_SIZE"));
			}
			
			//int pageSize = 10;
			pb.setPageSize(pageSize);
			if (pageString == null || pageString.equals("")
					|| pageString.equals("0")) {
				page = 1;
				pb.setPage(1);
				count = (page - 1) * pageSize;
			} else {
				page = Integer.parseInt(pageString);
				pb.setPage(page);
				count = (page - 1) * pageSize;
			}

			request.setAttribute("pb", pb);
			securityConfigParameterService.getData(scpcForm, count, pageSize);
			request.setAttribute("data", scpcForm);
			if(verbId.equals("queryDetail")){
				return mapping.findForward("queryDetail");
			}else{
				return mapping.findForward("query");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return mapping.findForward("fail");
	}

	public ActionForward detail(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		SecurityConfigParameterForm scpcForm = (SecurityConfigParameterForm) form;
		securityConfigParameterService.getByClassCode(scpcForm);
		request.setAttribute("data", scpcForm);
		return mapping.findForward("detail");
	}

	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		SecurityConfigParameterForm scpcForm = (SecurityConfigParameterForm) form;
		securityConfigParameterService.deleteData(scpcForm.getHiddenId());
		SecurityConfigParameterForm sf=new SecurityConfigParameterForm();
		return this.query(mapping, sf, request, response);
	}

	public ActionForward update(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String pageString = request.getParameter("page");
		SecurityConfigParameterForm sf=new SecurityConfigParameterForm();
		sf.setPageNow(pageString);
		SecurityConfigParameterForm scpcForm = (SecurityConfigParameterForm) form;
		securityConfigParameterService.updateData(scpcForm);	
		return this.query(mapping, sf, request, response);
	}

	public ActionForward updateInit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String pageString = request.getParameter("page");
		SecurityConfigParameterForm scpcForm = (SecurityConfigParameterForm) form;
		scpcForm.setPageNow(pageString);
		securityConfigParameterService.getByClassCode(scpcForm);
		request.setAttribute("data", scpcForm);
		return mapping.findForward("update");
	}

	public ActionForward add(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		SecurityConfigParameterForm scpcForm = (SecurityConfigParameterForm) form;
		System.out.println(scpcForm.getItemName());
		this.securityConfigParameterService.saveData(scpcForm);
		SecurityConfigParameterForm  hspform = new SecurityConfigParameterForm();
		return this.query(mapping, hspform, request, response);
	}
	
	public ActionForward addList(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response) throws Exception{
		SecurityConfigParameterForm scpcForm = (SecurityConfigParameterForm) form;
		boolean flag = securityConfigParameterService.saveList(scpcForm);
		if(flag){
			SecurityConfigParameterForm  hspform = new SecurityConfigParameterForm();
			return this.query(mapping, hspform, request, response);
		}else{
			return mapping.findForward("fail");
		}
		
	}
	public ActionForward addInit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		return mapping.findForward("addInit");
	}

	
	public ActionForward refreshInit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		System.out.println("-------------initAll start----------");
		List<?> list = securityConfigParameterService.getData();
		for(int i=0;i<list.size();i++){
			//SecurityConfigParamclass securityConfigParamclass = (SecurityConfigParamclass)list.get(i);
			String classCode = String.valueOf(transNullToString(((Object[])list.get(i))[0]));
			String className = String.valueOf(transNullToString(((Object[])list.get(i))[1]));
			List<?> l = securityConfigParameterService.findByClassCode(classCode);
			for(int a=0;a<l.size();a++){
				SecurityConfigParameter securityConfigParameter = (SecurityConfigParameter)l.get(a);
				String itemName = securityConfigParameter.getItemName();
				ServletContext application =  request.getSession().getServletContext();
				String applicationName = className + "." + itemName;
				System.out.println(applicationName + ":" +securityConfigParameter.getItemValue());
				application.setAttribute(applicationName, securityConfigParameter.getItemValue());
			}
		}
		System.out.println("-------------initAll end------------");
		return this.query(mapping, form, request, response);
	}
	
	/**弹出层*/
	public ActionForward getSystem(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {				
		String inputCode = request.getParameter("inputCode"); 
		String flag = request.getParameter("flag");//
		try {
			request.setCharacterEncoding("UTF-8");
			response.setContentType("text/xml; charset=UTF-8");
			response.setHeader("Cache-Control", "no-cache");
			PrintWriter out = response.getWriter();
			String xmlString = securityConfigParameterService.getXml(flag, inputCode,request);
			out.println(xmlString);    	
			out.close();
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return mapping.findForward("fail");
		}
	}
	public ActionForward init(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		try {
			String verbId = request.getParameter("verbId");
			SecurityConfigParameterForm ssform = (SecurityConfigParameterForm) form;
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
	
	/**弹出层*/
	public ActionForward getHsp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {				
		String inputCode = request.getParameter("inputCode"); 
		String flag = request.getParameter("flag");//
		try {
			request.setCharacterEncoding("UTF-8");
			response.setContentType("text/xml; charset=UTF-8");
			response.setHeader("Cache-Control", "no-cache");
			PrintWriter out = response.getWriter();
			String xmlString = securityConfigParameterService.getHsp(flag, inputCode,request);
			out.println(xmlString);    	
			out.close();
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return mapping.findForward("fail");
		}
	}
	
	/**
	 * 去掉字串中的空格
	 * */
	public String transNullToString(Object obj){
		String temp = "";
		if (obj != null){
			temp = ((String)obj).trim();			
		}
		return temp;
	}
}
