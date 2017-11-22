package com.tianjian.hsp.struts.action;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.tianjian.hsp.business.IHspStaffBaseinfoMergeService;
import com.tianjian.hsp.struts.form.HspStaffBaseinfoMergeForm;
import com.tianjian.security.struts.action.BaseAction;
import com.tianjian.security.struts.form.SessionForm;
import com.tianjian.util.comm.PageBean;

public class HspStaffBaseinfoMergeAction extends BaseAction {
	
	private IHspStaffBaseinfoMergeService hspStaffBaseinfoMerge;

	public IHspStaffBaseinfoMergeService getHspStaffBaseinfoMerge() {
		return hspStaffBaseinfoMerge;
	}

	public void setHspStaffBaseinfoMerge(
			IHspStaffBaseinfoMergeService hspStaffBaseinfoMerge) {
		this.hspStaffBaseinfoMerge = hspStaffBaseinfoMerge;
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
		HspStaffBaseinfoMergeForm hsbmForm = (HspStaffBaseinfoMergeForm)form;
		try{
			SessionForm sessionForm = (SessionForm)request.getSession().getAttribute("sessionForm");
			hsbmForm.setCreateUserId(sessionForm.getStaffId());
			hsbmForm.setCreateUserName(sessionForm.getStaffName());
			this.hspStaffBaseinfoMerge.mgInit(hsbmForm);
			hsbmForm.setMessage("合并成功");
	        request.setAttribute("data", hsbmForm);	
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
			HspStaffBaseinfoMergeForm hsbmForm = (HspStaffBaseinfoMergeForm)form;
			PageBean pb = new PageBean();
			int count;
			int page = 0;
			//取得记录的总数
			int recordCount = this.hspStaffBaseinfoMerge.getCount(hsbmForm);
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
			this.hspStaffBaseinfoMerge.search(hsbmForm, count, pageSize);
			request.setAttribute("data", hsbmForm);
			
			return mapping.findForward("compareQuery");
		}catch(Exception e){
			e.printStackTrace();
			return mapping.findForward("fail");
		}
	}

	private ActionForward init(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		HspStaffBaseinfoMergeForm hsbmForm = (HspStaffBaseinfoMergeForm)form;
		if(hsbmForm==null)
			hsbmForm = new HspStaffBaseinfoMergeForm();
		this.hspStaffBaseinfoMerge.init(hsbmForm);
		request.setAttribute("data", hsbmForm);
		return mapping.findForward("query");
	}

	private ActionForward query(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		try{
			String verbId = request.getParameter("verbId");
			HspStaffBaseinfoMergeForm hsbmForm = (HspStaffBaseinfoMergeForm)form;
			PageBean pb = new PageBean();
			int count;
			int page = 0;
			//取得记录的总数
			int recordCount = this.hspStaffBaseinfoMerge.getCount(hsbmForm);
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
			this.hspStaffBaseinfoMerge.search(hsbmForm, count, pageSize);
			this.hspStaffBaseinfoMerge.init(hsbmForm);
			request.setAttribute("data", hsbmForm);
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
