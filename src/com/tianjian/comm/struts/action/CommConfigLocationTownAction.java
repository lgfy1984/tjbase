package com.tianjian.comm.struts.action;

import java.io.IOException;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.tianjian.comm.bean.CommConfigLocationTown;
import com.tianjian.comm.business.ICommConfigInputDictService;
import com.tianjian.comm.business.ICommConfigLocationTownService;
import com.tianjian.comm.struts.form.CommConfigLocationTownForm;
import com.tianjian.comm.struts.servlet.CommConfigLocationTownInit;
import com.tianjian.util.ResourcesUtil;
import com.tianjian.util.comm.PageBean;
/**
 * COMM_CONFIG_LOCATION_TOWN ACTION
 * @AUTHOR DZENALL
 * @SINCE 2008-9-17
 */
public class CommConfigLocationTownAction extends Action{

	private String flag = CommConfigLocationTownInit.getProperty("PRINT_FLAG");
	private ICommConfigLocationTownService service;
	private ICommConfigInputDictService commConfigInputDictService;
	public ICommConfigLocationTownService getService() {
		return service;
	}
	public void setService(ICommConfigLocationTownService service) {
		this.service = service;
	}
	public ICommConfigInputDictService getCommConfigInputDictService() {
		return commConfigInputDictService;
	}
	public void setCommConfigInputDictService(
			ICommConfigInputDictService commConfigInputDictService) {
		this.commConfigInputDictService = commConfigInputDictService;
	}
	private void print(Object obj){	
		if(flag.equalsIgnoreCase("true")){
			System.out.println(obj);
		}
	}

	
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
		try{
			String verbId = request.getParameter("verbId");
			this.print("verbId="+verbId);

			if(verbId.equals("addInit")){//
				return this.addInit(mapping, form, request, response);
			}else if(verbId.equals("add")){//
				return this.add(mapping, form, request, response);
			}else if(verbId.equals("query")){
				return this.query(mapping, form, request, response);
			}else if(verbId.equals("change")){
				return this.change(mapping, form, request, response);
			}else if(verbId.equals("delete")){
				return this.delete(mapping, form, request, response);
			}else if(verbId.equals("detail")){
				return this.detail(mapping, form, request, response);
			}else if(verbId.equals("updateInit")){
				return this.updateInit(mapping, form, request, response);
			}else if(verbId.equals("update")){
				return this.update(mapping, form, request, response);
			}else if(verbId.equals("setCity")){
				return this.setCity(mapping, form, request, response);
			}else if(verbId.equals("setCounty")){
				return this.setCounty(mapping, form, request, response);
			}else if(verbId.equals("setItemCode")){
				return this.setItemCode(mapping, form, request, response);
			}else{
				return mapping.findForward("fail");
			}
		}catch(Exception e){
			if(flag.equalsIgnoreCase("true")){
				e.printStackTrace();
			}
			return mapping.findForward("fail");
		}
	}	

	private ActionForward addInit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
		CommConfigLocationTownForm hosform = (CommConfigLocationTownForm)form;
		
		this.getService().addInit(hosform);
		
		String itemCode=this.getService().getItemCode(hosform);
		String newItemCode=hosform.getCommConfigLocationId3()+itemCode;
		hosform.setItemCode(newItemCode); 
		
		request.setAttribute("data", hosform);
		return mapping.findForward("add");
	}
	private ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
		CommConfigLocationTownForm hosform = (CommConfigLocationTownForm)form;

		CommConfigLocationTown data = this.getService().checkData(hosform.getItemCode().trim());
		if(data == null){
			hosform.setInputCode(this.getCommConfigInputDictService().getInputCode(hosform.getItemName()));
			this.getService().add(hosform,request);
			CommConfigLocationTownForm hosformNew = new CommConfigLocationTownForm();	
			hosformNew.setMessage(hosform.getMessage());
			return this.query(mapping, hosformNew, request, response);
		}else{
			String message = ResourcesUtil.getValue("conf.comm.CommLocale", "comm.java.CommConfigLocationTownAction.addFail", request) + "!";
			hosform.setMessage(message);
			request.setAttribute("data", hosform);
			return this.addInit(mapping, hosform, request, response);
		}
	}
	private ActionForward query(ActionMapping mapping, ActionForm form,	HttpServletRequest request, HttpServletResponse response){
		CommConfigLocationTownForm hosform = (CommConfigLocationTownForm)form;

		PageBean pb = new PageBean();
		int count;
		int page = 0;
		int recordCount = this.getService().getCount(hosform.getItemCode(), hosform.getItemName(), hosform.getInputCode());
		pb.setCount(recordCount);
		String pageString = request.getParameter("page");
		//int pageSize = CommConfigLocationTownInit.getPageSize("PAGE_SIZE");
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
		request.setAttribute("data", hosform);		        

		return mapping.findForward("query");
	}
	private ActionForward change(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
		CommConfigLocationTownForm hosform = (CommConfigLocationTownForm)form;

		PageBean pb = new PageBean();
		int count;
		int page = 0;
		int recordCount = this.getService().getCount(hosform.getItemCode(), hosform.getItemName(), hosform.getInputCode());
		pb.setCount(recordCount);
		String pageString = request.getParameter("page");
		//int pageSize = CommConfigLocationTownInit.getPageSize("PAGE_SIZE");
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
		request.setAttribute("data", hosform);		        

		return mapping.findForward("change");
	}
	private ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
		CommConfigLocationTownForm hosform = (CommConfigLocationTownForm)form;
		try{
			this.getService().delete(hosform, request);
		}catch(org.springframework.dao.DataIntegrityViolationException e){
			if(flag.equals("true")){
				e.printStackTrace();
			}
			String message = ResourcesUtil.getValue("conf.comm.CommLocale", "comm.java.commom.cannotDel", request) + "!";
			hosform.setMessage(message);
		}
		hosform.setId("");
		hosform.setIdHidden("");
		hosform.setInputCode("");
		hosform.setItemCode("");
		hosform.setItemName("");
		return this.query(mapping, form, request, response);
	}
	private ActionForward detail(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		CommConfigLocationTownForm hosform = (CommConfigLocationTownForm)form;
		hosform.setId(hosform.getIdHidden().trim());
		this.getService().updateInit(hosform);
		this.getService().getDetail(hosform);
		request.setAttribute("data", hosform);		        
		return  mapping.findForward("detail");
	}
	private ActionForward updateInit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
		CommConfigLocationTownForm hosform = (CommConfigLocationTownForm)form;
		hosform.setId(hosform.getIdHidden().trim());
		this.getService().updateInit(hosform);
		this.getService().getDetail(hosform);
		request.setAttribute("data", hosform);		        
		return  mapping.findForward("update");
	}
	private ActionForward update(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
		CommConfigLocationTownForm hosform = (CommConfigLocationTownForm)form;		
		this.getService().update(hosform, request);
		CommConfigLocationTownForm hosformNew = new CommConfigLocationTownForm();
		hosform.setMessage(hosform.getMessage());
		return  this.query(mapping, hosformNew, request, response);
	}
	private ActionForward setCity(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws IOException{
		CommConfigLocationTownForm hosform = (CommConfigLocationTownForm)form;
		String province = request.getParameter("province");
		hosform.setCommConfigLocationId1(province);
		this.getService().getCitys(hosform);
		String xmlString = getXMLData(hosform.getCommConfigLocationIds2(), hosform.getCommConfigLocationNames2());
		writeResponse(response, xmlString);		
		return null;
	}
	private ActionForward setCounty(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws IOException {
		CommConfigLocationTownForm hosform = (CommConfigLocationTownForm)form;
		String city = request.getParameter("city");
		hosform.setCommConfigLocationId2(city);
		this.getService().getCounties(hosform);
		String xmlString = getXMLData(hosform.getCommConfigLocationIds3(), hosform.getCommConfigLocationNames3());
		writeResponse(response, xmlString);		
		return null;
	}
	//获取乡镇编号
	private ActionForward setItemCode(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)  throws Exception{
		CommConfigLocationTownForm hosform = (CommConfigLocationTownForm)form;
		String country = request.getParameter("country");
		hosform.setCommConfigLocationId3(country);
		
		String itemCode=this.getService().getItemCode(hosform);
		String newItemCode=country+itemCode;
		
		String xmlString = getItemCodeXMLData(hosform.getCommConfigLocationId3(), newItemCode);
		writeResponse(response, xmlString);		
		return null;
	}
	public String getItemCodeXMLData(String country, String itemCode) {
		String xmlString = "";

		xmlString = xmlString + "<?xml version=\"1.0\" encoding=\"UTF-8\"?>";
		xmlString = xmlString + "<root>";
		xmlString = xmlString + "<country>" + country + "</country>";
		xmlString = xmlString + "<itemCode>" + itemCode + "</itemCode>";

		xmlString = xmlString + "</root>";

		return xmlString;
	}
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