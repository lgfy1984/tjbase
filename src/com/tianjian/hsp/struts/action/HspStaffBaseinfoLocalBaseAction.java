package com.tianjian.hsp.struts.action;

import org.apache.log4j.Logger;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.tianjian.core.struts.action.Action;
import com.tianjian.hsp.business.IHspStaffBaseinfoLocalBaseService;
import com.tianjian.hsp.struts.form.HspStaffBaseinfoLocalBaseForm;
import com.tianjian.security.struts.form.SessionForm;
import com.tianjian.util.comm.PageBean;

public class HspStaffBaseinfoLocalBaseAction extends Action{
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger
			.getLogger(HspStaffBaseinfoLocalBaseAction.class);

	private IHspStaffBaseinfoLocalBaseService  hspStaffBaseinfoLocalBaseService;

	public IHspStaffBaseinfoLocalBaseService getHspStaffBaseinfoLocalBaseService() {
		return hspStaffBaseinfoLocalBaseService;
	}
	public void setHspStaffBaseinfoLocalBaseService(
			IHspStaffBaseinfoLocalBaseService hspStaffBaseinfoLocalBaseService) {
		this.hspStaffBaseinfoLocalBaseService = hspStaffBaseinfoLocalBaseService;
	}

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {	
		String verbId = request.getParameter("verbId");
		logger.info("verbId=" + verbId);
		if(verbId.equals("getXml")){
			return this.getXml(mapping, form, request, response);
		}else{
			return mapping.findForward("fail");
		}
	}

	/**查询*/
	public ActionForward getXml(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
		try{
			
			HspStaffBaseinfoLocalBaseForm hosform = (HspStaffBaseinfoLocalBaseForm) form;
			HttpSession session = request.getSession(true);
			SessionForm sessionForm = (SessionForm) session.getAttribute("sessionForm");
			if(sessionForm!=null && !"".equals(sessionForm)){
				if(sessionForm.getStaffId()!=null && !"".equals(sessionForm.getStaffId())){
					hosform.setStaffId(sessionForm.getStaffId());
					hosform.setHspConfigBaseinfoId(sessionForm.getStaffHospitalId());
				}
			}

			
			PageBean pb = new PageBean();
			int count;
			int page = 0;
			//取得记录的总数
			int recordCount = this.hspStaffBaseinfoLocalBaseService.getCount(
					hosform.getHspConfigBaseinfoIdQuery(), hosform.getNameQuery(), 
					hosform.getIdNoQuery(),hosform.getStaffId(),
					hosform.getHspConfigBaseinfoId());
			pb.setCount(recordCount);
			//取得页面的参数,page"第几页"
			String pageString = request.getParameter("page");
			ServletContext application = request.getSession().getServletContext();
			int pageSize=0;
			
			pageSize = Integer.parseInt((String) application.getAttribute("hsp.PAGE_SIZE"));
		
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
			this.hspStaffBaseinfoLocalBaseService.getSearch(hosform, count, pageSize, request);
			request.setAttribute("dataForm", hosform);
			return mapping.findForward("getXml");
			
		}catch(Exception e){
			e.printStackTrace();
			return mapping.findForward("fail");
		}
	}
	
	
}
