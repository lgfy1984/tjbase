package com.tianjian.comm.struts.action;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.tianjian.comm.bean.CommConfigDrugunits;
import com.tianjian.comm.business.ICommConfigDrugunitsService;
import com.tianjian.comm.business.ICommConfigInputDictService;
import com.tianjian.comm.struts.form.CommConfigDrugunitsForm;
import com.tianjian.security.struts.action.BaseAction;
import com.tianjian.util.ResourcesUtil;
import com.tianjian.util.comm.PageBean;

public class CommConfigDrugunitsAction extends BaseAction {
	private ICommConfigDrugunitsService commConfigDrugunitsService;
	private ICommConfigInputDictService commConfigInputDictService;

	public ICommConfigInputDictService getCommConfigInputDictService() {
		return commConfigInputDictService;
	}
	public void setCommConfigInputDictService(
			ICommConfigInputDictService commConfigInputDictService) {
		this.commConfigInputDictService = commConfigInputDictService;
	}

	public ICommConfigDrugunitsService getCommConfigDrugunitsService() {
		return commConfigDrugunitsService;
	}
	public void setCommConfigDrugunitsService(
			ICommConfigDrugunitsService commConfigDrugunitsService) {
		this.commConfigDrugunitsService = commConfigDrugunitsService;
	}
	public ActionForward execute(
			ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response) {

		String verbId = request.getParameter("verbId");
		//System.out.println("-----------verbId==" + verbId);
		if(verbId.equals("add")){
			return this.add(mapping, form, request, response);
		} 
		else if(verbId.equals("addInit")){
			return this.addInit(mapping, form, request, response);
		} 
		else if(verbId.equals("query")){
			return this.query(mapping, form, request, response);
		}
		else if(verbId.equals("detail")){
			return this.detail(mapping, form, request, response);
		}
		else if(verbId.equals("updateInit")){
			return this.updateInit(mapping, form, request, response);
		}
		else if (verbId.equals("change")){
			return this.change(mapping, form, request, response);
		} else if (verbId.equals("update")){
			return this.update(mapping, form, request, response);
		} else if (verbId.equals("delete")){
			return this.delete(mapping, form, request, response);
		} 
		else {
			return mapping.findForward("fail");
		}
	}

	private ActionForward updateInit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		try {

			CommConfigDrugunitsForm ccdform = (CommConfigDrugunitsForm)form;
			commConfigDrugunitsService.updateInit(ccdform);
			request.setAttribute("data", ccdform);		        
			return  mapping.findForward("update");

		} catch(Exception e) {
			return mapping.findForward("fail");
		}	
	}
	public ActionForward update(ActionMapping mapping
			, ActionForm form
			, HttpServletRequest request
			, HttpServletResponse response){
		try {
			CommConfigDrugunitsForm ccdform = (CommConfigDrugunitsForm)form;
			ccdform.setInputCode(this.getCommConfigInputDictService().getInputCode(ccdform.getItemName()));
			commConfigDrugunitsService.update(ccdform);
			ccdform.setItemCode("");
			ccdform.setItemName("");
			ccdform.setSeqNo("");
			ccdform.setComments("");
			ccdform.setInputCode("");
			return this.query(mapping, ccdform, request, response);

		} catch(Exception e) {
			return mapping.findForward("fail");
		}		
	}

	public ActionForward delete(ActionMapping mapping
			, ActionForm form
			, HttpServletRequest request
			, HttpServletResponse response){
		try{
			CommConfigDrugunitsForm ccdform = (CommConfigDrugunitsForm)form;
			commConfigDrugunitsService.delete(ccdform);
			ccdform.setItemCode("");
			return  this.query(mapping, ccdform, request, response);
		}catch(Exception e) {
			return mapping.findForward("fail");
		}
	}

	public ActionForward detail(ActionMapping mapping
			, ActionForm form
			, HttpServletRequest request
			, HttpServletResponse response){
		try{
			CommConfigDrugunitsForm ccdform = (CommConfigDrugunitsForm)form;
			commConfigDrugunitsService.updateInit(ccdform);
			request.setAttribute("data", ccdform);
			return  mapping.findForward("detail");
		}catch(Exception e) {
			return mapping.findForward("fail");
		}
	}
	public ActionForward addInit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response){
		//CommConfigDrugunitsForm ccdform = (CommConfigDrugunitsForm)form;
		return  mapping.findForward("add");
	}
	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		try {
			CommConfigDrugunitsForm ccdform = (CommConfigDrugunitsForm)form;
			CommConfigDrugunits  checkData = commConfigDrugunitsService.checkData(ccdform);
			if(checkData != null){
				String message = ResourcesUtil.getValue("conf.comm.CommLocale", "comm.java.commom.codeExistReinput", request)+"!";
				ccdform.setMessage(message);
				request.setAttribute("data", ccdform);
				return this.addInit(mapping, form, request, response);
			}
			ccdform.setInputCode(this.getCommConfigInputDictService().getInputCode(ccdform.getItemName()));
			commConfigDrugunitsService.save(ccdform);
			ccdform.setItemCode("");
			ccdform.setItemName("");
			ccdform.setInputCode("");
			ccdform.setSeqNo("");
			ccdform.setComments("");
			String message = ResourcesUtil.getValue("conf.comm.CommLocale", "comm.java.commom.addSuccess", request) + "!";
		  ccdform.setMessage(message);
			request.setAttribute("data", ccdform);
			return this.query(mapping, form, request, response);
		} catch (RuntimeException e) {
			// TODO Auto-generated catch block
			return mapping.findForward("fail");
		}


	}

	public ActionForward query(ActionMapping mapping
			, ActionForm form
			, HttpServletRequest request
			, HttpServletResponse response){
		try {

			CommConfigDrugunitsForm ccdform = (CommConfigDrugunitsForm)form;

////////		page start   ////////////////////////
			PageBean pb=new PageBean();
			int count;
			int page = 0;
			int recordCount = commConfigDrugunitsService.getCount(ccdform.getItemCode(), ccdform.getItemName(), ccdform.getInputCode());

			pb.setCount(recordCount);
			String pageString = request.getParameter("page");
			int pageSize = 10;
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
////////		page end   ////////////////////////

			commConfigDrugunitsService.getSearch(ccdform, count, pageSize);
			request.setAttribute("data", ccdform);		        

			return mapping.findForward("query");
		} catch(Exception e) {
			return mapping.findForward("fail");
		}

	}

	public ActionForward change(ActionMapping mapping
			, ActionForm form
			, HttpServletRequest request
			, HttpServletResponse response){
		try {

			CommConfigDrugunitsForm ccdform = (CommConfigDrugunitsForm)form;

////////		page start   ////////////////////////
			PageBean pb=new PageBean();
			int count;
			int page = 0;
			int recordCount = commConfigDrugunitsService.getCount(ccdform.getItemCode(), ccdform.getItemName(), ccdform.getInputCode());

			pb.setCount(recordCount);
			String pageString = request.getParameter("page");
			int pageSize = 2;
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
////////		page end   ////////////////////////

			commConfigDrugunitsService.getSearch(ccdform, count, pageSize);
			request.setAttribute("data", ccdform);		        

			return mapping.findForward("query");
		} catch(Exception e) {
			return mapping.findForward("fail");
		}

	}

	/**构造XML的内容格式*/
	public String getXMLData(String[] key, String[] value) {
		String xmlString = "";

		xmlString = xmlString + "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
		xmlString = xmlString + "<root>";
		if(key != null){
			xmlString = xmlString + "<index>" + key.length + "</index>";
			for (int i = 0; i < key.length; i++) {
				xmlString = xmlString + "<key>" + key[i] + "</key>";
				xmlString = xmlString + "<value>" + value[i] + "</value>";
			}
		} else {
			xmlString = xmlString + "<index>0</index>";
		}
		xmlString = xmlString + "</root>";

		return xmlString;
	}

	public void writeResponse(HttpServletResponse response, String xmlString) throws IOException {
		response.setContentType("text/xml");
		response.setCharacterEncoding("UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		response.getWriter().write(xmlString);
	}

}
