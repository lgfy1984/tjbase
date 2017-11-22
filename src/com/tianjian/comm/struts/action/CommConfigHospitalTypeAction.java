package com.tianjian.comm.struts.action;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.tianjian.comm.bean.CommConfigHospitalType;
import com.tianjian.comm.business.ICommConfigHospitalTypeService;
import com.tianjian.comm.business.ICommConfigInputDictService;
import com.tianjian.comm.struts.form.CommConfigHospitalTypeForm;
import com.tianjian.security.struts.action.BaseAction;
import com.tianjian.util.ResourcesUtil;
import com.tianjian.util.comm.PageBean;

public class CommConfigHospitalTypeAction extends BaseAction {
	private ICommConfigHospitalTypeService commConfigHospitalTypeService;
	private ICommConfigInputDictService commConfigInputDictService;
	public ICommConfigInputDictService getCommConfigInputDictService() {
		return commConfigInputDictService;
	}

	public void setCommConfigInputDictService(
			ICommConfigInputDictService commConfigInputDictService) {
		this.commConfigInputDictService = commConfigInputDictService;
	}

	public ICommConfigHospitalTypeService getCommConfigHospitalTypeService() {
		return commConfigHospitalTypeService;
	}

	public void setCommConfigHospitalTypeService(
			ICommConfigHospitalTypeService commConfigHospitalTypeService) {
		this.commConfigHospitalTypeService = commConfigHospitalTypeService;
	}
	
	
	
	public ActionForward execute(
			ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response) {
			
			String verbId = request.getParameter("verbId");
			
			if(verbId.equals("add")){
				return this.add(mapping, form, request, response);
			} else if (verbId.equals("addInit")){
			 return this.addInit(mapping, form, request, response);
			} else if (verbId.equals("query")){
			 return this.query(mapping, form, request, response);
			} else if (verbId.equals("update")){
				return this.update(mapping, form, request, response);
			} else if (verbId.equals("updateInit")){
				return this.updateInit(mapping, form, request, response);
			} else if (verbId.equals("detail")){
				return this.detail(mapping, form, request, response);
			} else if (verbId.equals("delete")){
				return this.delete(mapping, form, request, response);
			} else if (verbId.equals("getHspType")){
				return this.getHspType(mapping, form, request, response);
			} else if (verbId.equals("setLevel2")){
				return this.setLevel2(mapping, form, request, response);
			}else {
				return mapping.findForward("fail");
			}
			
		}

	private ActionForward addInit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		try {
			CommConfigHospitalTypeForm cchtform = (CommConfigHospitalTypeForm)form;
			this.commConfigHospitalTypeService.addInit(cchtform,request);
			request.setAttribute("data", cchtform);
			return mapping.findForward("add");
		} catch (Exception e) {
			e.printStackTrace();
			return mapping.findForward("fail");
		}
	}

	private ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		try {
			CommConfigHospitalTypeForm cchtform = (CommConfigHospitalTypeForm)form;
			CommConfigHospitalType data = this.commConfigHospitalTypeService.findByItemCode(cchtform.getItemCode().trim());
			if(data!=null){
				String message = ResourcesUtil.getValue("conf.comm.CommLocale", "comm.java.CommConfigHospitalTypeAction.codeExist", request) + "!";
				cchtform.setMessage(message);
				return this.addInit(mapping, form, request, response);
			}else{
				cchtform.setInputCode(commConfigInputDictService.getInputCode(cchtform.getItemName()));
				this.commConfigHospitalTypeService.save(cchtform,request);
				cchtform = new CommConfigHospitalTypeForm();
				String message = ResourcesUtil.getValue("conf.comm.CommLocale", "comm.java.commom.addSuccess", request) + "!";
				cchtform.setMessage(message);
				request.setAttribute("data", cchtform);
				return this.query(mapping, form, request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return mapping.findForward("fail");
		}
	}
	
	private ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		try {
			CommConfigHospitalTypeForm cchtform = (CommConfigHospitalTypeForm)form;
			this.commConfigHospitalTypeService.delete(cchtform);
			cchtform = new CommConfigHospitalTypeForm();
			String message = ResourcesUtil.getValue("conf.comm.CommLocale", "comm.java.commom.deleteSuccess", request) + "!";
			cchtform.setMessage(message);
			return this.query(mapping, form, request, response);
		} catch (Exception e) {
			e.printStackTrace();
			return mapping.findForward("fail");
		}
	}

	private ActionForward detail(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		try {
			CommConfigHospitalTypeForm cchtform = (CommConfigHospitalTypeForm)form;
			CommConfigHospitalType data = this.commConfigHospitalTypeService.findByItemCode(cchtform.getItemCodeHidden());
			commConfigHospitalTypeService.setForm(cchtform, data,request);
			request.setAttribute("data", cchtform);
			return mapping.findForward("detail");
		} catch (Exception e) {
			e.printStackTrace();
			return mapping.findForward("fail");
		}
	}

	private ActionForward updateInit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		try {
			CommConfigHospitalTypeForm cchtform = (CommConfigHospitalTypeForm)form;
			CommConfigHospitalType data = this.commConfigHospitalTypeService.findByItemCode(cchtform.getItemCodeHidden());
			this.commConfigHospitalTypeService.setForm(cchtform, data,request);
			this.commConfigHospitalTypeService.updateInit(cchtform,request);
			request.setAttribute("data", cchtform);
			return mapping.findForward("update");
		} catch (Exception e) {
			e.printStackTrace();
			return mapping.findForward("fail");
		}
	}

	private ActionForward update(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		try {
			CommConfigHospitalTypeForm cchtform = (CommConfigHospitalTypeForm)form;
			cchtform.setInputCode(commConfigInputDictService.getInputCode(cchtform.getItemName()));
			this.commConfigHospitalTypeService.update(cchtform,request);
			cchtform = new CommConfigHospitalTypeForm();
			String message = ResourcesUtil.getValue("conf.comm.CommLocale", "comm.java.CommConfigHospitalTypeAction.changeSucceess", request) + "!";
			cchtform.setMessage(message);
			request.setAttribute("data", cchtform);
			return this.query(mapping, form, request, response);
			
		} catch (Exception e) {
			e.printStackTrace();
			return mapping.findForward("fail");
		}
	}

	private ActionForward query(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		try {
			CommConfigHospitalTypeForm cchtform = (CommConfigHospitalTypeForm)form;
			// ////// page start ////////////////////////
			PageBean pb = new PageBean();
			int count;
			int page = 0;
			String isQuery = request.getParameter("isQuery");
			if(isQuery==null||!isQuery.equals("yes")){
				request.setAttribute("data", null);
				return mapping.findForward("query");
			}
			int recordCount = this.commConfigHospitalTypeService.getCount(cchtform);
			pb.setCount(recordCount);
			String pageString = request.getParameter("page");
			//int pageSize = CommInit.getPageSize("PAGE_SIZE");
			//ServletContext application = request.getSession().getServletContext();
			//int pageSize = Integer.parseInt((String)application.getAttribute("EHRPProject_basecomm.PAGE_SIZE"));
			int pageSize = 10;
			if(request.getSession().getAttribute("page_282881f53463c89c013463c89cbe0000")!=null){
				pageSize = Integer.parseInt((String)request.getSession().getAttribute("page_282881f53463c89c013463c89cbe0000"));
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
			
			this.commConfigHospitalTypeService.getSearch(cchtform, count, pageSize);
			request.setAttribute("data", cchtform);
			return mapping.findForward("query");
		} catch (Exception e) {
			e.printStackTrace();
			return mapping.findForward("fail");
		}
		
	}

	
	private ActionForward setLevel2(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		try {
			CommConfigHospitalTypeForm cchtform = new CommConfigHospitalTypeForm();
			String levelCode_1 = request.getParameter("levelCode1");
			cchtform.setLevelCode_1(levelCode_1);
			this.commConfigHospitalTypeService.setLevel2(cchtform,request);
			String xmlString = getXMLData(cchtform.getLevelCode_2List(), cchtform.getLevelName_2List());
			writeResponse(response, xmlString);
			
		} catch (Exception e) {
			e.printStackTrace();
			return mapping.findForward("fail");
		}
		
		return null;
	}

	
	private ActionForward getHspType(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		String input = request.getParameter("inputCode"); 
		//------------根据标示符获取结果list-----1拼音 2代码 3汉字---------------------------------------------------
		String flag = request.getParameter("flag");//
		//设置请求以及返回数据的编码类型
		//hspType 1为大类2为中类3为小类4为所有
    	String hspTypeLevel = request.getParameter("hspTypeLevel");
		try {
			request.setCharacterEncoding("UTF-8");
			response.setContentType("text/xml; charset=UTF-8");
			response.setHeader("Cache-Control", "no-cache");
			PrintWriter out = response.getWriter();
			String xmlString = this.commConfigHospitalTypeService.getXml(flag, input, hspTypeLevel,request);
			out.println(xmlString);    	
			out.close();
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return mapping.findForward("fail");
		}
	
	}

	public void writeResponse(HttpServletResponse response, String xmlString) throws IOException {
    	response.setContentType("text/xml");
    	response.setCharacterEncoding("UTF-8");
    	response.setHeader("Cache-Control", "no-cache");
    	response.getWriter().write(xmlString);
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
	
}
