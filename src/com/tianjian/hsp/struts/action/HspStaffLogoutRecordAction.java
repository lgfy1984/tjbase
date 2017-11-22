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

import com.tianjian.hsp.business.IHspStaffBaseinfoService;
import com.tianjian.hsp.business.IHspStaffLogoutRecordService;
import com.tianjian.hsp.struts.form.HspStaffBaseinfoForm;
import com.tianjian.security.business.ICheckRoleCodeService;
import com.tianjian.security.struts.form.SessionForm;
import com.tianjian.util.Converter;
import com.tianjian.util.comm.PageBean;

public class HspStaffLogoutRecordAction extends Action{
	public IHspStaffBaseinfoService hspStaffBaseinfoService;
	public IHspStaffLogoutRecordService hspStaffLogoutRecordService;
	public ICheckRoleCodeService checkRoleCodeService;
	
	public IHspStaffBaseinfoService getHspStaffBaseinfoService() {
		return hspStaffBaseinfoService;
	}
	public void setHspStaffBaseinfoService(
			IHspStaffBaseinfoService hspStaffBaseinfoService) {
		this.hspStaffBaseinfoService = hspStaffBaseinfoService;
	}
	public IHspStaffLogoutRecordService getHspStaffLogoutRecordService() {
		return hspStaffLogoutRecordService;
	}
	public void setHspStaffLogoutRecordService(
			IHspStaffLogoutRecordService hspStaffLogoutRecordService) {
		this.hspStaffLogoutRecordService = hspStaffLogoutRecordService;
	}
	public ICheckRoleCodeService getCheckRoleCodeService() {
		return checkRoleCodeService;
	}
	public void setCheckRoleCodeService(ICheckRoleCodeService checkRoleCodeService) {
		this.checkRoleCodeService = checkRoleCodeService;
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
			HspStaffBaseinfoForm hForm = (HspStaffBaseinfoForm) form;
			
			request.setAttribute("dataForm", hForm);
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
	public ActionForward detailLogOut(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		try {
			HspStaffBaseinfoForm hsbForm = (HspStaffBaseinfoForm) form;
			this.hspStaffLogoutRecordService.updateInit(hsbForm);
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
		HspStaffBaseinfoForm hsbForm = (HspStaffBaseinfoForm) form;
		//对所选取的取获取ID,然后再保存起来
		
		this.hspStaffBaseinfoService.updateInit(hsbForm);
		this.hspStaffBaseinfoService.getDetail(hsbForm);//空 字典放在下面取
		
		request.setAttribute("dataForm", hsbForm);
		return  mapping.findForward("detail");	
	}
	public ActionForward addLogOutInit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		SessionForm sessionForm = (SessionForm)request.getSession().getAttribute("sessionForm");
		
		try {
			HspStaffBaseinfoForm hForm = (HspStaffBaseinfoForm) form;
			hForm.setCreateUserId1(sessionForm.getStaffId());
			hForm.setCreateUserName1(sessionForm.getStaffName());
			hForm.setLogoutReason("");
			
			hForm.setLogoutTime(DateFormatUtils.format(new Date(), "yyyy-MM-dd"));
			this.hspStaffBaseinfoService.updateInit(hForm);
			this.hspStaffBaseinfoService.getDetail(hForm);
			hForm.setCreateDate(hForm.getCreateDate().substring(0, 10));
			request.setAttribute("data", hForm);
			return mapping.findForward("addLogOutInit");
		}catch (Exception e) {
			return mapping.findForward("fail");
		}
	}
	public ActionForward addLogOut(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		HspStaffBaseinfoForm hForm = (HspStaffBaseinfoForm) form;
		this.hspStaffLogoutRecordService.save(hForm);
		if(hForm.getId()==null||hForm.getId().equals("")){
			hForm.setId(hForm.getIdHidden());
		}
		this.hspStaffBaseinfoService.delete(hForm);
		HspStaffBaseinfoForm hFormNew = new HspStaffBaseinfoForm();
		return this.query(mapping, hFormNew, request, response);
	}
	public ActionForward queryLogOut(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		try {
			String verbId = request.getParameter("verbId");
			HspStaffBaseinfoForm hForm = (HspStaffBaseinfoForm) form;
			// ////// page start ////////////////////////
			PageBean pb = new PageBean();
			int count;
			int page = 0;
			int recordCount = this.hspStaffLogoutRecordService.getCount(hForm.getHspStaffBaseinfoId(),hForm.getEmpNo(),hForm.getName(),hForm.getIdNo());
			pb.setCount(recordCount);
			String pageString = request.getParameter("page");
			
			int pageSize = 10;
			if(request.getSession().getAttribute("page_2828810b369603cc01369603cc481234")!=null){
				pageSize = Integer.parseInt((String)request.getSession().getAttribute("page_2828810b369603cc01369603cc481234"));
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
			this.hspStaffLogoutRecordService.getSearch(hForm, count, pageSize);
			this.hspStaffLogoutRecordService.serchInit(hForm);
			request.setAttribute("dataForm", hForm);
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
		try{
			String verbId = request.getParameter("verbId");
			String townCode = request.getParameter("townCode");
			String pdClass = request.getParameter("pdClass");
			String eipClass = request.getParameter("eipClass");
			String clvId = request.getParameter("clvId");
			String hcbId = request.getParameter("hcbId");
			
			String ageS = request.getParameter("ageS");
			String ageE = request.getParameter("ageE");
			String sexId = request.getParameter("sexId");
			String degreeId = request.getParameter("degreeId");
			String patpId = request.getParameter("patpId");
			
			String isLocation = request.getParameter("isLocation");
			
			HspStaffBaseinfoForm hosform = (HspStaffBaseinfoForm) form;
			HttpSession session = request.getSession(true);
			SessionForm sessionForm = (SessionForm) session.getAttribute("sessionForm");
			if(sessionForm!=null && !"".equals(sessionForm)){
				if(sessionForm.getStaffId()!=null && !"".equals(sessionForm.getStaffId())){
					hosform.setStaffId(sessionForm.getStaffId());
					hosform.setStaffHspId(sessionForm.getStaffHospitalId());
					//验证操作员是否机构卫生人员维护角色,如果是则只允许维护自己所在机构的卫生人员
					if(this.checkRoleCodeService.checkRoleCodeByIds(sessionForm.getStaffRoles(), "9")){
						hosform.setHspConfigBaseinfoId(sessionForm.getStaffHospitalId());
						hosform.setStaffManagerRole(true);
					}
				}
			}
			if(hosform.getHspConfigBaseinfoId() != null && hosform.getHspConfigBaseinfoId().trim().length() > 0){
				hosform.setHspConfigBaseinfoName(this.hspStaffBaseinfoService.getHspNameById(hosform.getHspConfigBaseinfoId().trim()));
			}
			if(ageS!=null&&ageS.trim().length()>0&&!ageS.trim().equals("null")&&
					ageE!=null&&ageE.trim().length()>0&&!ageE.trim().equals("null")){}else{
				ageS = null;
				ageE = null;
			}
			if(sexId!=null&&sexId.trim().length()>0&&!sexId.trim().equals("null")){
				hosform.setCommConfigSexId(sexId);
			}
			if(degreeId!=null&&degreeId.trim().length()>0&&!degreeId.trim().equals("null")){
				hosform.setCommConfigDegreeId(degreeId);
			}
			if(patpId!=null&&patpId.trim().length()>0&&!patpId.trim().equals("null")){
				hosform.setCommDictPublicCharId3(patpId);
			}
			if(eipClass!=null&&eipClass.trim().length()>0&&!eipClass.trim().equals("null")){
				hosform.setCommDictPublicCharId1(eipClass);
			}
			if(hcbId!=null&&hcbId.trim().length()>0&&!hcbId.trim().equals("null")){
				hosform.setHspConfigBaseinfoId(hcbId);
			}
			if(pdClass!=null&&pdClass.trim().length()>0&&eipClass!=null&&eipClass.trim().length()>0
					&&!eipClass.trim().equals("null")&&!pdClass.trim().equals("null")){
				hosform.setCommDictPublicCharId1(eipClass);
				hosform.setCommDictPublicCharId2(pdClass);
			}
			//去掉尾数0
			if(clvId!=null&&clvId.trim().length()>0&&!clvId.trim().equals("null")){
				while(true){
					int l = clvId.trim().length();
					if(clvId.charAt(l-1)=='0'){
						clvId = clvId.substring(0, l-1);
					}else{
						break;
					}
				}	
				hosform.setCommClvId(clvId);
			}
			if(townCode==null||townCode.trim().length()<=0||townCode.trim().equals("null")){
				townCode = null;
			}
			
			if(isLocation != null){
				hosform.setIslocation(Converter.toLong(isLocation));
			}
			PageBean pb = new PageBean();
			int count;
			int page = 0;
			//取得记录的总数
			int recordCount = this.hspStaffBaseinfoService.getCount(hosform.getIdHidden(),
					hosform.getHspConfigBaseinfoId(), hosform.getName(), hosform.getIdNo(),
					hosform.getEmpNo(),hosform.getCommConfigNationalityId(),
					hosform.getWorkCertificateNo(),hosform.getCommDictPublicCharId1(),
					hosform.getCommDictPublicCharId2(),hosform.getCommConfigPositiontitleId(),
					hosform.getCommConfigEmptitleId(),hosform.getCommDictPublicCharId3(),
					hosform.getCommConfigDegreeId(),hosform.getCommConfigDegreeLevelId(),
					hosform.getCommConfigProfessionId(),hosform.getCommConfigLocationId3(),
					hosform.getCommConfigLocationTownId(),hosform.getCommClvId(),hosform.getStaffId(),
					townCode,hosform.getCommConfigSexId(),hosform.getStaffHspId(),ageS, ageE, hosform.getIslocation());
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
			this.hspStaffBaseinfoService.getSearch(hosform, count, pageSize, request,townCode,ageS,ageE);
			request.setAttribute("dataForm", hosform);
			if(verbId.equals("queryDetail")){
				//报表统计-卫生人员-各地区卫生人员数
				if(townCode!=null&&townCode.trim().length()>0&&!townCode.trim().equals("null")){
					request.setAttribute("townCode", townCode);
					request.setAttribute("isBiao", "1");
				}
				//报表统计-卫生人员-医师执业类别
				if(pdClass!=null&&pdClass.trim().length()>0&&eipClass!=null&&eipClass.trim().length()>0&&!eipClass.trim().equals("null")&&!pdClass.trim().equals("null")){
					request.setAttribute("pdClass", pdClass);
					request.setAttribute("eipClass", eipClass);
					if(clvId!=null&&clvId.trim().length()>0){
						request.setAttribute("clvId", clvId);
					}
					request.setAttribute("isBiao", "1");
				}
				//报表统计-卫生人员-各卫生机构人员数
				if(hcbId!=null&&hcbId.trim().length()>0&&!hcbId.trim().equals("null")){
					request.setAttribute("hcbId", hcbId);
					request.setAttribute("isBiao", "1");	
				}
				//报表统计-卫生人员-卫生人员性别、年龄、学历、职称构成
				//年龄
				if(ageS!=null&&ageS.trim().length()>0&&!ageS.trim().equals("null")&&
						ageE!=null&&ageE.trim().length()>0&&!ageE.trim().equals("null")&&
						eipClass!=null&&eipClass.trim().length()>0&&!eipClass.trim().equals("null")){
					request.setAttribute("ageS", ageS);
					request.setAttribute("ageE", ageE);
					request.setAttribute("eipClass", eipClass);
					request.setAttribute("isBiao", "1");
				}
				//性别
				if(sexId!=null&&sexId.trim().length()>0&&!sexId.trim().equals("null")&&
						eipClass!=null&&eipClass.trim().length()>0&&!eipClass.trim().equals("null")){
					request.setAttribute("sexId", sexId);
					request.setAttribute("eipClass", eipClass);
					request.setAttribute("isBiao", "1");
				}
				//学历
				if(degreeId!=null&&degreeId.trim().length()>0&&!degreeId.trim().equals("null")&&
						eipClass!=null&&eipClass.trim().length()>0&&!eipClass.trim().equals("null")){
					request.setAttribute("degreeId", degreeId);
					request.setAttribute("eipClass", eipClass);
					request.setAttribute("isBiao", "1");
				}
				//职称
				if(patpId!=null&&patpId.trim().length()>0&&!patpId.trim().equals("null")&&
						eipClass!=null&&eipClass.trim().length()>0&&!eipClass.trim().equals("null")){
					request.setAttribute("patpId", patpId);
					request.setAttribute("eipClass", eipClass);
					request.setAttribute("isBiao", "1");
				}
				
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
