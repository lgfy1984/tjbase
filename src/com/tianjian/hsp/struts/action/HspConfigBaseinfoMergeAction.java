package com.tianjian.hsp.struts.action;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.tianjian.hsp.business.IHspConfigBaseinfoMergeService;
import com.tianjian.hsp.struts.form.HspConfigBaseinfoMergeForm;
import com.tianjian.security.struts.action.BaseAction;
import com.tianjian.security.struts.form.SessionForm;
import com.tianjian.util.comm.PageBean;

public class HspConfigBaseinfoMergeAction extends BaseAction {
	
	private IHspConfigBaseinfoMergeService hspConfigBaseinfoMerge;

	public IHspConfigBaseinfoMergeService getHspConfigBaseinfoMerge() {
		return hspConfigBaseinfoMerge;
	}

	public void setHspConfigBaseinfoMerge(
			IHspConfigBaseinfoMergeService hspConfigBaseinfoMerge) {
		this.hspConfigBaseinfoMerge = hspConfigBaseinfoMerge;
	}
	
	public ActionForward execute(ActionMapping mapping,ActionForm form,
			HttpServletRequest request,HttpServletResponse response) {
		String verbId = request.getParameter("verbId");
		System.out.println("-----------verbId==" + verbId);
		if(verbId.equals("query")){
			return this.query(mapping, form, request, response);
		}else if(verbId.equals("compareQuery")){
			return this.compareQuery(mapping, form, request, response);
		}else if(verbId.equals("compareAct")){
			return this.compareAct(mapping, form, request, response);
		}else if(verbId.equals("init")){
			return this.init(mapping, form, request, response);
		}else{
			return mapping.findForward("fail");
		}
	 }

	private ActionForward compareAct(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		HspConfigBaseinfoMergeForm hcbmForm = (HspConfigBaseinfoMergeForm)form;
		try{
			SessionForm sessionForm = (SessionForm)request.getSession().getAttribute("sessionForm");
			hcbmForm.setCreateUserId(sessionForm.getStaffId());
			hcbmForm.setCreateUserName(sessionForm.getStaffName());
			this.hspConfigBaseinfoMerge.mgInit(hcbmForm);
			hcbmForm.setMessage("合并成功");
	        request.setAttribute("data", hcbmForm);	
			return mapping.findForward("success");
		}catch(Exception e){
			e.printStackTrace();
			return mapping.findForward("fail");
		}
	}

	private ActionForward compareQuery(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		try{
//			String verbId = request.getParameter("verbId");
			HspConfigBaseinfoMergeForm hcbmForm = (HspConfigBaseinfoMergeForm)form;
			PageBean pb = new PageBean();
			int count;
			int page = 0;
			//取得记录的总数
			int recordCount = this.hspConfigBaseinfoMerge.getCount(hcbmForm);
			pb.setCount(recordCount);
			//取得页面的参数,page"第几页"
			String pageString = request.getParameter("page");
			ServletContext application = request.getSession().getServletContext();
			int pageSize=0;
			
			if(request.getSession().getAttribute("page_282881f339b4e9c20139b4e9c23a0000")!=null){
				pageSize = Integer.parseInt((String)request.getSession().getAttribute("page_282881f339b4e9c20139b4e9c23a0000"));
			}else{
			     pageSize = Integer.parseInt((String) application.getAttribute("hsp.PAGE_SIZE"));
			}

			//int pageSize = Integer.parseInt((String) application.getAttribute("hsp.PAGE_SIZE"));
			//int pageSize = CommInit.getPageSize("PAGE_SIZE");
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
			//从数据库中查询符合条件的数据.
			this.hspConfigBaseinfoMerge.search(hcbmForm, count, pageSize);
			request.setAttribute("data", hcbmForm);
			
			return mapping.findForward("compareQuery");
		}catch(Exception e){
			e.printStackTrace();
			return mapping.findForward("fail");
		}
	}

	private ActionForward init(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		HspConfigBaseinfoMergeForm hcbmForm = (HspConfigBaseinfoMergeForm)form;
		if(hcbmForm==null)
			hcbmForm = new HspConfigBaseinfoMergeForm();
		HttpSession session = request.getSession (true); 
		SessionForm sessionForm = (SessionForm)session.getAttribute("sessionForm");
		this.hspConfigBaseinfoMerge.init(hcbmForm,request,sessionForm.getStaffId());
		request.setAttribute("data", hcbmForm);
		return mapping.findForward("query");
	}

	private ActionForward query(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		try{
			String verbId = request.getParameter("verbId");
			HspConfigBaseinfoMergeForm hcbmForm = (HspConfigBaseinfoMergeForm)form;
			PageBean pb = new PageBean();
			int count;
			int page = 0;
			//取得记录的总数
			int recordCount = this.hspConfigBaseinfoMerge.getCount(hcbmForm);
			pb.setCount(recordCount);
			//取得页面的参数,page"第几页"
			String pageString = request.getParameter("page");
			ServletContext application = request.getSession().getServletContext();
			int pageSize=0;
			
			if(request.getSession().getAttribute("page_282881f339b4e9c20139b4e9c23a0000")!=null){
				pageSize = Integer.parseInt((String)request.getSession().getAttribute("page_282881f339b4e9c20139b4e9c23a0000"));
			}else{
			     pageSize = Integer.parseInt((String) application.getAttribute("hsp.PAGE_SIZE"));
			}

			//int pageSize = Integer.parseInt((String) application.getAttribute("hsp.PAGE_SIZE"));
			//int pageSize = CommInit.getPageSize("PAGE_SIZE");
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
			HttpSession session = request.getSession (true); 
			SessionForm sessionForm = (SessionForm)session.getAttribute("sessionForm");
			//从数据库中查询符合条件的数据.
			this.hspConfigBaseinfoMerge.init(hcbmForm, request, sessionForm.getStaffId());
			this.hspConfigBaseinfoMerge.search(hcbmForm, count, pageSize);
			request.setAttribute("data", hcbmForm);
			if(verbId.equals("queryDetail")){
				return mapping.findForward("queryDetail");
			}else{
				return mapping.findForward("query");
			}
		}catch(Exception e){
			e.printStackTrace();
			return mapping.findForward("fail");
		}
	}
	

}
