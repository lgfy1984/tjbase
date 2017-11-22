package com.tianjian.hsp.struts.action;

import java.util.Date;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.tianjian.hsp.business.IHspConfigBaseinfoService;
import com.tianjian.hsp.business.IHspLogoutRecordService;
import com.tianjian.hsp.struts.form.HspConfigBaseinfoForm;
import com.tianjian.hsp.struts.servlet.HspInit;
import com.tianjian.security.struts.form.SessionForm;
import com.tianjian.util.comm.PageBean;

public class HspLogoutRecordAction extends Action{
	public IHspConfigBaseinfoService service;
	public IHspLogoutRecordService hspLogoutRecordService;
	
	public IHspConfigBaseinfoService getService() {
		return service;
	}
	public void setService(IHspConfigBaseinfoService service) {
		this.service = service;
	}
	public IHspLogoutRecordService getHspLogoutRecordService() {
		return hspLogoutRecordService;
	}
	public void setHspLogoutRecordService(
			IHspLogoutRecordService hspLogoutRecordService) {
		this.hspLogoutRecordService = hspLogoutRecordService;
	}
	
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String verbId = "";
		verbId = request.getParameter("verbId");
		System.out.println(verbId);
		if (verbId.equals("addLogOut")) {
			return this.addLogOut(mapping, form, request, response);
		}else if (verbId.equals("addLogOutInit")) {
			return this.addLogOutInit(mapping, form, request, response);
		}else if (verbId.equals("queryInit")) {
			return this.queryInit(mapping, form, request, response);
		} else if (verbId.equals("query")) {
			return this.query(mapping, form, request, response);
		} else if (verbId.equals("queryDetail")) {
			return this.query(mapping, form, request, response);
		}else if (verbId.equals("queryLogOut")) {
			return this.queryLogOut(mapping, form, request, response);
		} else if (verbId.equals("queryLogOutDetail")) {
			return this.queryLogOut(mapping, form, request, response);
		} else if (verbId.equals("detail")) {
			return this.detail(mapping, form, request, response);
		} else if (verbId.equals("detailLogOut")) {
			return this.detailLogOut(mapping, form, request, response);
		} else {
			return mapping.findForward("fail");
		}
	}
	public ActionForward queryInit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		try {			
			String verbId = request.getParameter("verbId");
			HttpSession session = request.getSession (true); 
			SessionForm sessionForm = (SessionForm)session.getAttribute("sessionForm");
			HspConfigBaseinfoForm hosform = (HspConfigBaseinfoForm)form;
			if(hosform==null)
				hosform = new HspConfigBaseinfoForm();
			if(sessionForm.getStaffHospitalId()!=null&&!sessionForm.getStaffHospitalId().equals(""))
				hosform.setStaffHspId(sessionForm.getStaffHospitalId());
			PageBean pb=new PageBean();
			int count;
			int page = 0;
			int recordCount = 0;

			pb.setCount(recordCount);
//			String pageString = request.getParameter("page");
			int pageSize = 10;
			if(request.getSession().getAttribute("page_282881f53463cbef013463cbef2c5312")!=null){
				pageSize = Integer.parseInt((String)request.getSession().getAttribute("page_282881f53463cbef013463cbef2c5312"));
			}else{
				ServletContext application = request.getSession().getServletContext();
				pageSize = Integer.parseInt((String)application.getAttribute("hsp.PAGE_SIZE"));
			}			
			pb.setPageSize(pageSize);
		    pb.setPage(0);
			request.setAttribute("pb",pb );
			this.service.getSearch(hosform, request,0, 0);
			request.setAttribute("data", hosform);	
			if(verbId.equals("init")){
				return mapping.findForward("queryDetail");
			}else{
				return mapping.findForward("query");
			}
			
	     } catch(Exception e) {
			HspInit.println(e);
			return mapping.findForward("fail");
		}
	}
	public ActionForward detailLogOut(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		try {
			HspConfigBaseinfoForm hsbForm = (HspConfigBaseinfoForm) form;
			if(hsbForm.getIdHidden()==null||hsbForm.getIdHidden().equals("")||hsbForm.getIdHidden().equals("null")){
				hsbForm.setId(request.getParameter("id"));
			}
			if(hsbForm.getId()==null||hsbForm.getId().equals("")){
				hsbForm.setId(hsbForm.getIdHidden());
			}
			this.hspLogoutRecordService.updateInit(hsbForm);
			request.setAttribute("dataForm", hsbForm);
			return mapping.findForward("detailLogOut");
		}
		catch (Exception e) {
			return mapping.findForward("fail");
		}
	}
	public ActionForward detail(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		String canceledFlag = request.getParameter("canceledFlag");
		if(canceledFlag!=null&&canceledFlag.equals("1")){
			request.setAttribute("canceledFlag", canceledFlag);
			return this.detailLogOut(mapping, form, request, response);	
		}
		try {
			HttpSession session = request.getSession (true); 
			SessionForm sessionForm = (SessionForm)session.getAttribute("sessionForm");
			HspConfigBaseinfoForm hosform = (HspConfigBaseinfoForm)form;
			hosform.setId(hosform.getIdHidden());
			this.service.updateInit(hosform,request,sessionForm.getStaffId());
			this.service.getDetail(hosform);
			request.setAttribute("data", hosform);		        
			return  mapping.findForward("detail");
		} catch(Exception e) {
			e.printStackTrace();
			return mapping.findForward("fail");
		}	
	}
	public ActionForward addLogOutInit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		try {
			HttpSession session = request.getSession (true); 
			SessionForm sessionForm = (SessionForm)session.getAttribute("sessionForm");
			HspConfigBaseinfoForm hosform = (HspConfigBaseinfoForm)form;
			hosform.setId(hosform.getIdHidden());
			if(hosform.getIdHidden()==null||hosform.getIdHidden().equals("")||hosform.getIdHidden().equals("null")){
				hosform.setId(request.getParameter("id"));
			}
			this.service.updateInit(hosform,request,sessionForm.getStaffId());
			this.service.getDetail(hosform);		
			
			hosform.setCreateUserId1(sessionForm.getStaffId());
			hosform.setCreateUserName1(sessionForm.getStaffName());
			hosform.setLogoutReason("");
			hosform.setLogoutTime(DateFormatUtils.format(new Date(), "yyyy-MM-dd"));
			
			request.setAttribute("data", hosform);
			
			return mapping.findForward("addLogOutInit");
		}catch (Exception e) {
			return mapping.findForward("fail");
		}
	}
	public ActionForward addLogOut(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		HspConfigBaseinfoForm hForm = (HspConfigBaseinfoForm) form;
		if(hForm.getIdHidden()==null||hForm.getIdHidden().equals("")||hForm.getIdHidden().equals("null")){
			hForm.setId(request.getParameter("id"));
		}
		if(hForm.getId()==null||hForm.getId().equals("")){
			hForm.setId(hForm.getIdHidden());
		}
		this.hspLogoutRecordService.save(hForm);
		this.service.delete(hForm);
		HspConfigBaseinfoForm hFormNew = new HspConfigBaseinfoForm();
		return this.query(mapping, hFormNew, request, response);
	}
	public ActionForward queryLogOut(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		try {
			String verbId = request.getParameter("verbId");
			HspConfigBaseinfoForm hForm = (HspConfigBaseinfoForm) form;
			if(hForm == null )
				hForm = new HspConfigBaseinfoForm();
			// ////// page start ////////////////////////
			PageBean pb = new PageBean();
			int count;
			int page = 0;
			int recordCount = this.hspLogoutRecordService.getCount(hForm.getHspConfigBaseinfoId(), hForm.getItemCode(), hForm.getItemName());
			pb.setCount(recordCount);
			String pageString = request.getParameter("page");
			
			int pageSize = 10;
			if(request.getSession().getAttribute("page_282881f53463cbef013463cbef2c5312")!=null){
				pageSize = Integer.parseInt((String)request.getSession().getAttribute("page_282881f53463cbef013463cbef2c5312"));
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
			this.hspLogoutRecordService.getSearch(hForm, count, pageSize);
			this.hspLogoutRecordService.serchInit(hForm);
			request.setAttribute("data", hForm);
			if(verbId.equals("queryLogOutDetail")){
				return mapping.findForward("queryDetail");
			}else{
				return mapping.findForward("query");			
			}		
		}catch (Exception e) {
			e.printStackTrace();
		    return mapping.findForward("fail");
	    }
	}
	public ActionForward query(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		String canceledFlag = request.getParameter("canceledFlag");
		if(canceledFlag!=null&&canceledFlag.equals("1")){
			request.setAttribute("canceledFlag", canceledFlag);
			return this.queryLogOut(mapping, form, request, response);	
		}
		try {
			String verbId = request.getParameter("verbId");
			HttpSession session = request.getSession (true); 
			SessionForm sessionForm = (SessionForm)session.getAttribute("sessionForm");
			System.out.println(sessionForm.getStaffId()+")))))))))");
			HspConfigBaseinfoForm hosform = (HspConfigBaseinfoForm)form;
			if(hosform == null )
				hosform = new HspConfigBaseinfoForm();
			hosform.setStaffHspId(sessionForm.getStaffHospitalId());;
////////		page start   ////////////////////////
			PageBean pb=new PageBean();
			int count;
			int page = 0;
			int recordCount = service.getCount(hosform);

			pb.setCount(recordCount);
			String pageString = request.getParameter("page");
			//int pageSize = HspInit.getPageSize("PAGE_SIZE");
			int pageSize = 10;
			if(request.getSession().getAttribute("page_282881f53463cbef013463cbef2c0000")!=null){
				pageSize = Integer.parseInt((String)request.getSession().getAttribute("page_282881f53463cbef013463cbef2c0000"));
			}else{
				ServletContext application = request.getSession().getServletContext();
				pageSize = Integer.parseInt((String)application.getAttribute("hsp.PAGE_SIZE"));
			}
			
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

			service.getSearch(hosform, request,count, pageSize);
			request.setAttribute("data", hosform);		        
			if(verbId.equals("queryDetail")){
				return mapping.findForward("queryDetail");
			}else{
				return mapping.findForward("query");
			}
			
		} catch(Exception e) {
			HspInit.println(e);
			return mapping.findForward("fail");
		}
	}

}
