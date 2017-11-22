package com.tianjian.comm.struts.action;


import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.tianjian.security.struts.form.SessionForm;
import com.tianjian.comm.business.ICommConfigLocationSearchService;
import com.tianjian.comm.struts.form.UserSelectForm;

public class CommConfigLocationSearchAction extends Action{

	private ICommConfigLocationSearchService service;

	public ICommConfigLocationSearchService getService() {
		return service;
	}

	public void setService(ICommConfigLocationSearchService service) {
		this.service = service;
	}
	public ActionForward execute(
			ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response) throws IOException {
			try{
				String verbId = request.getParameter("verbId");

				if(verbId.equals("query")){//
					return this.query(mapping, form, request, response);
				}else if(verbId.equals("person")){
					return this.person(mapping, form, request, response);
				}else if(verbId.equals("out")){
					return this.out(mapping, form, request, response);
				}else if(verbId.equals("in")){
					return this.in(mapping, form, request, response);
				}else if(verbId.equals("logout")){
					return this.logout(mapping, form, request, response);
				}else if(verbId.equals("setCity")){
					return this.setCity(mapping, form, request, response);
				}else if(verbId.equals("setCounty")){
					return this.setCounty(mapping, form, request, response);
				}else if(verbId.equals("setTown")){
					return this.setTown(mapping, form, request, response);
				}else if(verbId.equals("setVillage")){
					return this.setVillage(mapping, form, request, response);
				}else if(verbId.equals("setGroup")){
					return this.setGroup(mapping, form, request, response);
				}else{
					return mapping.findForward("fail");
				}
			}catch(Exception e){
					e.printStackTrace();
				return mapping.findForward("fail");
			}
		}
	private ActionForward query(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		try {
			UserSelectForm hosform = (UserSelectForm)form;
			
			HttpSession session = request.getSession (true); 
			SessionForm sessionForm = (SessionForm)session.getAttribute("sessionForm");
			
			hosform.setCommProvinceId(sessionForm.getCommConfigLocationId1());
			hosform.setCommCityId(sessionForm.getCommConfigLocationId2());
			hosform.setCommCountyId(sessionForm.getCommConfigLocationId3());
			hosform.setCommCltId(sessionForm.getCommCltId());
			hosform.setCommClvId(sessionForm.getCommClvId());
			
			
			service.getDict(hosform, sessionForm.getStaffId());
			request.setAttribute("data", hosform);
			return mapping.findForward("query");
		} catch (Exception e) {
			return mapping.findForward("fail");
		}
	}
	private ActionForward out(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		try {
			UserSelectForm hosform = (UserSelectForm)form;
			
			HttpSession session = request.getSession (true); 
			SessionForm sessionForm = (SessionForm)session.getAttribute("sessionForm");
			
			hosform.setCommProvinceId(sessionForm.getCommConfigLocationId1());
			hosform.setCommCityId(sessionForm.getCommConfigLocationId2());
			hosform.setCommCountyId(sessionForm.getCommConfigLocationId3());
			hosform.setCommCltId(sessionForm.getCommCltId());
			hosform.setCommClvId(sessionForm.getCommClvId());
			
			service.getDict(hosform, sessionForm.getStaffId());
			request.setAttribute("data", hosform);
			return mapping.findForward("out");
		} catch (Exception e) {
			return mapping.findForward("fail");
		}
	}
	private ActionForward in(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		try {
			UserSelectForm hosform = (UserSelectForm)form;
			
			HttpSession session = request.getSession (true); 
			SessionForm sessionForm = (SessionForm)session.getAttribute("sessionForm");
			
			hosform.setCommProvinceId(sessionForm.getCommConfigLocationId1());
//			hosform.setCommCityId(sessionForm.getCommConfigLocationId2());
//			hosform.setCommCountyId(sessionForm.getCommConfigLocationId3());
//			hosform.setCommCltId(sessionForm.getCommCltId());
//			hosform.setCommClvId(sessionForm.getCommClvId());
			
			service.getDict(hosform, sessionForm.getStaffId());
			request.setAttribute("data", hosform);
			return mapping.findForward("in");
		} catch (Exception e) {
			return mapping.findForward("fail");
		}
	}
	private ActionForward logout(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		try {
			UserSelectForm hosform = (UserSelectForm)form;
			
			HttpSession session = request.getSession (true); 
			SessionForm sessionForm = (SessionForm)session.getAttribute("sessionForm");
			
			hosform.setCommProvinceId(sessionForm.getCommConfigLocationId1());
			hosform.setCommCityId(sessionForm.getCommConfigLocationId2());
			hosform.setCommCountyId(sessionForm.getCommConfigLocationId3());
			hosform.setCommCltId(sessionForm.getCommCltId());
			hosform.setCommClvId(sessionForm.getCommClvId());
			
			service.getDict(hosform, sessionForm.getStaffId());
			request.setAttribute("data", hosform);
			return mapping.findForward("logout");
		} catch (Exception e) {
			return mapping.findForward("fail");
		}
	}
	private ActionForward person(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		try {
			UserSelectForm hosform = (UserSelectForm)form;
			
			HttpSession session = request.getSession (true); 
			SessionForm sessionForm = (SessionForm)session.getAttribute("sessionForm");
			
			hosform.setCommProvinceId(sessionForm.getCommConfigLocationId1());
			hosform.setCommCityId(sessionForm.getCommConfigLocationId2());
			hosform.setCommCountyId(sessionForm.getCommConfigLocationId3());
			hosform.setCommCltId(sessionForm.getCommCltId());
			hosform.setCommClvId(sessionForm.getCommClvId());
			
			service.getDict(hosform, sessionForm.getStaffId());
			request.setAttribute("data", hosform);
			return mapping.findForward("person");
		} catch (Exception e) {
			return mapping.findForward("fail");
		}
	}
	private ActionForward setCity(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		UserSelectForm hosform = (UserSelectForm)form;
		String province = request.getParameter("province");
		hosform.setCommProvinceId(province);
		HttpSession session = request.getSession (true); 
		SessionForm sessionForm = (SessionForm)session.getAttribute("sessionForm");
		
		service.getCitys(hosform, sessionForm.getStaffId());
		String xmlString = getXMLData(hosform.getCommCityIds(), hosform.getCommCityNames());
		writeResponse(response, xmlString);		
		return null;
	}
	private ActionForward setCounty(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		UserSelectForm hosform = (UserSelectForm)form;
		String city = request.getParameter("city");
		hosform.setCommCityId(city);
		HttpSession session = request.getSession (true); 
		SessionForm sessionForm = (SessionForm)session.getAttribute("sessionForm");
		
		service.getCounties(hosform, sessionForm.getStaffId());
		String xmlString = getXMLData(hosform.getCommCountyIds(), hosform.getCommCountyNames());
		writeResponse(response, xmlString);		
		return null;
	}
	private ActionForward setTown(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception{
		UserSelectForm hosform = (UserSelectForm)form;
		String town = request.getParameter("town");
		hosform.setCommCountyId(town);
		HttpSession session = request.getSession (true); 
		SessionForm sessionForm = (SessionForm)session.getAttribute("sessionForm");
		
		service.getTowns(hosform, sessionForm.getStaffId());
		String xmlString = getXMLData(hosform.getCommCltIds(), hosform.getCommCltNames());
		writeResponse(response, xmlString);		
		return null;
	}
	private ActionForward setVillage(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		UserSelectForm hosform = (UserSelectForm)form;
		String village = request.getParameter("village");
		hosform.setCommCltId(village);
		HttpSession session = request.getSession (true); 
		SessionForm sessionForm = (SessionForm)session.getAttribute("sessionForm");
		
		service.getVillages(hosform, sessionForm.getStaffId());
		String xmlString = getXMLData(hosform.getCommClvIds(), hosform.getCommClvNames());
		writeResponse(response, xmlString);		
		return null;
	}
	private ActionForward setGroup(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) throws Exception{
		UserSelectForm hosform = (UserSelectForm)form;
		String group = request.getParameter("group");
		hosform.setCommClvId(group);
		service.getGroups(hosform);
		String xmlString = getXMLData(hosform.getCommGroupIds(), hosform.getCommGroupNames());
		writeResponse(response, xmlString);		
		return null;
	}
	/**����XML�����ݸ�ʽ*/
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
