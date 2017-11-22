package com.tianjian.hsp.struts.action;

import java.io.InputStream;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.apache.struts.upload.FormFile;

import com.tianjian.comm.bean.CommConfigCountry;
import com.tianjian.comm.bean.CommConfigNationality;
import com.tianjian.comm.bean.CommConfigRelationship;
import com.tianjian.empi.bean.SecurityUserBaseinfo;
import com.tianjian.hsp.bean.HspStaffBaseinfo;
import com.tianjian.hsp.business.IHspStaffBaseinfoService;
import com.tianjian.hsp.struts.form.HspStaffBaseinfoForm;
import com.tianjian.hsp.struts.servlet.HspInit;
import com.tianjian.security.business.ICheckRoleCodeService;
import com.tianjian.security.struts.form.SessionForm;
import com.tianjian.util.ChineseSpelling;
import com.tianjian.util.Converter;
import com.tianjian.util.ResourcesUtil;
import com.tianjian.util.comm.PageBean;
import com.tianjian.util.comm.UtilTrans;

public class HspStaffBaseinfoAction extends Action {
	public IHspStaffBaseinfoService hspStaffBaseinfoService;
	public ICheckRoleCodeService checkRoleCodeService;
	
	public IHspStaffBaseinfoService getHspStaffBaseinfoService() {
		return hspStaffBaseinfoService;
	}
	public void setHspStaffBaseinfoService(
			IHspStaffBaseinfoService hspStaffBaseinfoService) {
		this.hspStaffBaseinfoService = hspStaffBaseinfoService;
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
		String  verbId = request.getParameter("verbId");
		  System.out.println(verbId);
		if ("add".equals(verbId)) {
			return this.add(mapping, form, request, response);
		} else if("nationality".equals(verbId)){
			return this.nationality(mapping, form, request, response);
		}else if("relationship".endsWith(verbId)){
			return this.relationship(mapping, form, request, response);
		}else if("country".equals(verbId)){
			return this.country(mapping, form, request, response);
		} else if ("addInit".equals(verbId)) {
			return this.addInit(mapping, form, request, response);
		} else if ("query".equals(verbId)) {
			return this.query(mapping, form, request, response);
		} else if ("queryDetail".equals(verbId)) {
			return this.query(mapping, form, request, response);
		}  else if ("queryDetails".equals(verbId)) {
			return this.query(mapping, form, request, response);
		}else if ("getXml".equals(verbId)) {
			return this.getXml(mapping, form, request, response);
		}  else if ("init".equals(verbId)) {
			return this.init(mapping, form, request, response);
		}else if("initquery".equals(verbId)){
			return this.init(mapping, form, request, response);
		} 
		else if ("query4Web".equals(verbId)) {
			return this.query4Web(mapping, form, request, response);
		} else if ("update".equals(verbId)) {
			return this.update(mapping, form, request, response);
		} else if ("updateInit".equals(verbId)) {
			return this.updateInit(mapping, form, request, response);
		} else if ("detail".equals(verbId)) {
			return this.detail(mapping, form, request, response);
		} else if ("delete".equals(verbId)) {
			return this.delete(mapping, form, request, response);
		} else if ("hspToSecurity".equals(verbId)) {
			return this.hspToSecurity(mapping, form, request, response);
		}else if ("hspSaveToSecurity".equals(verbId)) {
			return this.hspSaveToSecurity(mapping, form, request, response);
		}else if ("getHsp".equals(verbId)){
			return this.getHsp(mapping, form, request, response);
		}else if("elsExport".equals(verbId)){
			return this.elsExport(mapping, form, request, response);
		}else if("elsImport".equals(verbId)){
			return this.elsImport(mapping, form, request, response);
		}else if("getPeople".equals(verbId)){
			return this.getPeople(mapping, form, request, response);
		}else if("getAllPeople".equals(verbId)){
			return this.getAllPeople(mapping, form, request, response);
		}else if("getInfomation".equals(verbId)){
			return this.getInfomation(mapping, form, request, response);
		}else if("getHspConfigBase".equals(verbId)){
			return this.getHspConfigBase(mapping, form, request, response);
		}else {
			return mapping.findForward("fail");
		}
	}
	public ActionForward getHspConfigBase(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		try {
			String inputCode = request.getParameter("inputCode");
			String flag = request.getParameter("flag");
			HttpSession session = request.getSession(true);
			SessionForm sessionForm = (SessionForm) session.getAttribute("sessionForm");
			request.setCharacterEncoding("UTF-8");
			response.setContentType("text/xml; charset=UTF-8");
			response.setHeader("Cache-Control", "no-cache");
			String xmlString=this.hspStaffBaseinfoService.getBaseInfo(flag, inputCode, sessionForm.getStaffHospitalId(), request);
			response.getWriter().println(xmlString);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	/**查询*/
	public ActionForward init(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		try{
			int pageSize =0;
			String verbId = request.getParameter("verbId");
			HspStaffBaseinfoForm hosform = (HspStaffBaseinfoForm) form;
			PageBean pb = new PageBean();
			pb.setCount(0);
		    pb.setPage(0);
		    ServletContext application = request.getSession().getServletContext();
		    if(request.getSession().getAttribute("page_282881f339b4e9c20139b4e9c23a0000")!=null){
		    	// System.out.println("每页显示的条数的条数的session====page_282881f339b4e9c20139b4e9c23a0000");
				pageSize = Integer.parseInt((String)request.getSession().getAttribute("page_282881f339b4e9c20139b4e9c23a0000"));
			}else{
			     pageSize = Integer.parseInt((String) application.getAttribute("hsp.PAGE_SIZE"));
			}
		   
		    HttpSession session = request.getSession(true);
			SessionForm sessionForm = (SessionForm)session.getAttribute("sessionForm");
//			hosform.setHspConfigBaseinfoId(sessionForm.getStaffHospitalId());
			//验证操作员是否机构卫生人员维护角色,如果是则只允许维护自己所在机构的卫生人员
			if(this.checkRoleCodeService.checkRoleCodeByIds(sessionForm.getStaffRoles(), "9")){
				hosform.setHspConfigBaseinfoId(sessionForm.getStaffHospitalId());
				hosform.setHspConfigBaseinfoName(this.hspStaffBaseinfoService.getHspNameById(sessionForm.getStaffHospitalId()));
				hosform.setStaffManagerRole(true);
			}
		    pb.setPageSize(pageSize);
	        request.setAttribute("pb", pb);
	        this.hspStaffBaseinfoService.getDetail(hosform);
//			this.hspStaffBaseinfoService.getSearch(hosform, 0, 0, request,null,null,null);
			request.setAttribute("dataForm", hosform);
			if(verbId.equals("init")){
				return mapping.findForward("queryDetail");
			}else{
				return mapping.findForward("query");
			}
		}catch(Exception e){
			e.printStackTrace();
			return mapping.findForward("fail");
		}
	
	}
	
	
	
	/**查询*/
	public ActionForward query(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
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
					townCode,hosform.getCommConfigSexId(),hosform.getStaffHspId(),ageS,ageE, hosform.getIslocation());
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
			if("queryDetail".equals(verbId) || "queryDetails".equals(verbId)){
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
	
	/**查询*/
	public ActionForward getXml(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) throws Exception {
		try{
			String townCode = request.getParameter("townCode");

			String ageS = request.getParameter("ageS");
			String ageE = request.getParameter("ageE");

			
			HspStaffBaseinfoForm hosform = (HspStaffBaseinfoForm) form;
			HttpSession session = request.getSession(true);
			SessionForm sessionForm = (SessionForm) session.getAttribute("sessionForm");
			if(sessionForm!=null && !"".equals(sessionForm)){
				if(sessionForm.getStaffId()!=null && !"".equals(sessionForm.getStaffId())){
					hosform.setStaffId(sessionForm.getStaffId());
					hosform.setStaffHspId(sessionForm.getStaffHospitalId());
				}
			}

			if(townCode==null||townCode.trim().length()<=0||townCode.trim().equals("null")){
				townCode = null;
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
					townCode,hosform.getCommConfigSexId(),hosform.getStaffHspId(),ageS,ageE, hosform.getIslocation());
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
			this.hspStaffBaseinfoService.getSearch(hosform, count, pageSize, request, townCode, ageS, ageE);
			request.setAttribute("dataForm", hosform);
			return mapping.findForward("getXml");
			
		}catch(Exception e){
			e.printStackTrace();
			return mapping.findForward("fail");
		}
	}
	
	/**hsp2security*/
	public ActionForward hspToSecurity(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		try{
			HspStaffBaseinfoForm hosform = (HspStaffBaseinfoForm) form;
			HttpSession session = request.getSession(true);
			SessionForm sessionForm = (SessionForm) session.getAttribute("sessionForm");
			if(sessionForm!=null && !"".equals(sessionForm)){
				if(sessionForm.getStaffId()!=null && !"".equals(sessionForm.getStaffId())){
					hosform.setStaffId(sessionForm.getStaffId());
					hosform.setStaffHspId(sessionForm.getStaffHospitalId());
				}
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
					hosform.getCommConfigLocationTownId(),hosform.getCommClvId(),hosform.getStaffId(),null,null,hosform.getStaffHspId(),null,null, hosform.getIslocation());
			pb.setCount(recordCount);
			//取得页面的参数,page"第几页"
			String pageString = request.getParameter("page");
			ServletContext application = request.getSession().getServletContext();
			int pageSize = Integer.parseInt((String) application.getAttribute("hsp.PAGE_SIZE"));
			//int pageSize = CommInit.getPageSize("PAGE_SIZE");
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
			//从数据库中查询符合条件的数据.
			this.hspStaffBaseinfoService.getSearch(hosform, count, pageSize, request,null,null,null);
			this.hspStaffBaseinfoService.searchInit(hosform);
			request.setAttribute("dataForm", hosform);
			return mapping.findForward("hspToSecurity");
		}catch(Exception e){
			e.printStackTrace();
			return mapping.findForward("fail");
		}
	}
	/**query4Web*/
	public ActionForward query4Web(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		try{
			HttpSession session = request.getSession(true);
			SessionForm sessionForm = (SessionForm) session.getAttribute("sessionForm");
			HspStaffBaseinfoForm hosform = (HspStaffBaseinfoForm) form;
			
			PageBean pb = new PageBean();
			int count;
			int page = 0;
			//取得记录的总数
			int recordCount = this.hspStaffBaseinfoService.getCountAll(hosform.getIdHidden(),
					hosform.getHspConfigBaseinfoId(), hosform.getName(), hosform.getIdNo(),
					hosform.getEmpNo(),hosform.getCommConfigNationalityId(),
					hosform.getWorkCertificateNo(),hosform.getCommDictPublicCharId1(),
					hosform.getCommDictPublicCharId2(),hosform.getCommConfigPositiontitleId(),
					hosform.getCommConfigEmptitleId(),hosform.getCommDictPublicCharId3(),
					hosform.getCommConfigDegreeId(),hosform.getCommConfigDegreeLevelId(),
					hosform.getCommConfigProfessionId(),sessionForm.getStaffId());
			pb.setCount(recordCount);
			//取得页面的参数,page"第几页"
			String pageString = request.getParameter("page");
			ServletContext application = request.getSession().getServletContext();
			int pageSize = Integer.parseInt((String) application.getAttribute("hsp.PAGE_SIZE"));
			//int pageSize = CommInit.getPageSize("PAGE_SIZE");
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
			//从数据库中查询符合条件的数据.
			this.hspStaffBaseinfoService.getSearchAll(hosform, count, pageSize,sessionForm.getStaffId(), request);
			request.setAttribute("dataForm", hosform);
			return mapping.findForward("query4Web");
		}catch(Exception e){
			e.printStackTrace();
			return mapping.findForward("fail");
		}
	}
	/**保存*/
	public ActionForward add(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String useForTree = request.getParameter("useForTree");
		try{
			HspStaffBaseinfoForm hspStaffBaseinfo = (HspStaffBaseinfoForm)form;
			HttpSession session = request.getSession (true); 
			SessionForm sessionForm = (SessionForm)session.getAttribute("sessionForm");
			hspStaffBaseinfo.setCreateUserId(sessionForm.getStaffId());
			hspStaffBaseinfo.setCreateUserName(sessionForm.getStaffName());
			hspStaffBaseinfo.setCreateDate(UtilTrans.transDateToStringFull(new Date()));
			int checkData=this.hspStaffBaseinfoService.checkData(hspStaffBaseinfo.getEmpNo());
			if(checkData!=1){
				String message = ResourcesUtil.getValue("conf.hsp.HspLocale", "hsp.java.hspStaffBaseinfoAction.warn2", request);
				hspStaffBaseinfo.setMessage(message);
				if("1".equals(useForTree)){
					this.write2Response(response, "[{flag:'0', msg:'"+message+"'}]");
					return null;
				}else{
					this.hspStaffBaseinfoService.addInit(hspStaffBaseinfo);
					request.setAttribute("data", hspStaffBaseinfo);
					return mapping.findForward("add");
				}
				
			}
			this.hspStaffBaseinfoService.save(hspStaffBaseinfo);
			String message = ResourcesUtil.getValue("conf.hsp.HspLocale", "hsp.java.hspStaffBaseinfoAction.warn5", request);
			if("1".equals(useForTree)){
				this.write2Response(response, "[{flag:'1', msg:'保存成功！'}]");
				return null;
			}else{
				request.setAttribute("message", message);
				return this.addInit(mapping, form, request, response);
			}
		}catch(Exception e){
			if("1".equals(useForTree)){
				this.write2Response(response, "[{flag:'0', msg:'保存失败！'}]");
				return null;
			}else{
				e.printStackTrace();
				return mapping.findForward("fail");
			}
		}
	}	
	
	/**保存security*/
	public ActionForward hspSaveToSecurity(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HspStaffBaseinfoForm hspStaffBaseinfo = (HspStaffBaseinfoForm)form;
		HttpSession session = request.getSession (true); 
		SessionForm sessionForm = (SessionForm)session.getAttribute("sessionForm");
//		hspStaffBaseinfo.setCreateUserId(sessionForm.getStaffId());
//		hspStaffBaseinfo.setCreateUserName(sessionForm.getStaffName());
//		hspStaffBaseinfo.setCreateDate(new DateVsString().transDateToStringFull(new Date()));
//		
		String[] arrayIds=request.getParameterValues("checkbx");
		for(int i=0;i<arrayIds.length;i++){
			hspStaffBaseinfo.setIdHidden(arrayIds[i]);
		this.hspStaffBaseinfoService.saveHspToSecurity(hspStaffBaseinfo);
		}
		hspStaffBaseinfo.setSecurityStaffBaseinfoIdArray(arrayIds);
		request.setAttribute("dataForm", sessionForm);
		HspStaffBaseinfoForm hspStaffBaseinfo1 = new HspStaffBaseinfoForm();
		return this.hspToSecurity(mapping, hspStaffBaseinfo1, request, response);
	}	
	
	/**民族的弹出框*/
	public ActionForward nationality(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		try{
			String inputCode = request.getParameter("inputCode");
			String flag = request.getParameter("flag");
			List<?> nationalityList = this.hspStaffBaseinfoService.getAjaxNationality(flag, inputCode);
			request.setCharacterEncoding("UTF-8");
			response.setContentType("text/xml; charset=UTF-8");
			response.setHeader("Cache-Control", "no-cache");
			PrintWriter out;
			try{
				out = response.getWriter();
				out.println("<response>");
				for (int i = 0; i < nationalityList.size(); i++) {
					CommConfigNationality national = (CommConfigNationality) nationalityList.get(i);
					out.println("<ress>");
					out.println("<resInputCode>" + national.getInputCode()+ "</resInputCode>");
					out.println("<resItemCode>" + national.getItemCode()+ "</resItemCode>");
					out.println("<resItemName>"+ national.getItemName()+ "</resItemName>");
					out.println("</ress>");
				}
				out.println("</response>");
				out.close();				
			}catch(Exception e){
				e.printStackTrace();
			}
		}catch(UnsupportedEncodingException e){
			e.printStackTrace();
		}
		return null;
	}
	/**国籍的弹出框*/
	public ActionForward country(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		try{
			String inputCode = request.getParameter("inputCode");
			String flag = request.getParameter("flag");
			List<?> countryList = this.hspStaffBaseinfoService.getAjaxCountry(flag, inputCode);
			//int count=countryList.size();
			request.setCharacterEncoding("UTF-8");
			response.setContentType("text/xml; charset=UTF-8");
			response.setHeader("Cache-Control", "no-cache");
			PrintWriter out;
			StringBuffer buffer;
			try{
				buffer=new StringBuffer();
				out = response.getWriter();
				buffer.append("<response>");
				for (int i = 0; i < countryList.size(); i++) {
					CommConfigCountry country = (CommConfigCountry) countryList.get(i);
					buffer.append("<ress>");
					buffer.append("<resInputCode>" + country.getInputCode()+ "</resInputCode>");
					buffer.append("<resItemCode>" + country.getItemCode()+ "</resItemCode>");
					buffer.append("<resItemName>"+ country.getItemName()+ "</resItemName>");
					buffer.append("</ress>");
				}
				buffer.append("</response>");
				System.out.println(buffer);
				out.print(buffer);
				out.close();				
			}catch(Exception e){
				e.printStackTrace();
			}
		}catch(UnsupportedEncodingException e){
			e.printStackTrace();
		}
		return null;
	}
	public ActionForward getPeople(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		try{
			HttpSession session = request.getSession (true); 
			SessionForm sessionForm = (SessionForm)session.getAttribute("sessionForm");
			String inputCode = request.getParameter("inputCode");
			String flag = request.getParameter("flag");
			List<?> peopleList = this.hspStaffBaseinfoService.getPeoples(sessionForm.getStaffHospitalId(), flag, inputCode);
			request.setCharacterEncoding("UTF-8");
			response.setContentType("text/xml; charset=UTF-8");
			response.setHeader("Cache-Control", "no-cache");
			PrintWriter out;
			try{
				out = response.getWriter();
				out.println("<response>");
				for (int i = 0; i < peopleList.size(); i++) {
					HspStaffBaseinfo nHspStaffBaseinfo = (HspStaffBaseinfo) peopleList.get(i);
					out.println("<ress>");
					out.println("<resInputCode>" + nHspStaffBaseinfo.getInputCode()+ "</resInputCode>");
					out.println("<resItemCode>" + nHspStaffBaseinfo.getEmpNo()+ "</resItemCode>");
					out.println("<resItemName>"+ nHspStaffBaseinfo.getName()+ "</resItemName>");
					out.println("<resItemId>" + nHspStaffBaseinfo.getId() + "</resItemId>");
					out.println("</ress>");
				}
				out.println("</response>");
				out.close();				
			}catch(Exception e){
				e.printStackTrace();
			}
		}catch(UnsupportedEncodingException e){
			e.printStackTrace();
		}
		return null;
	}
	
	public ActionForward getAllPeople(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		try{
			String inputCode = request.getParameter("inputCode");
			String flag = request.getParameter("flag");
			List<?> peopleList = this.hspStaffBaseinfoService.getAllPeoples( flag, inputCode);
			request.setCharacterEncoding("UTF-8");
			response.setContentType("text/xml; charset=UTF-8");
			response.setHeader("Cache-Control", "no-cache");
			PrintWriter out;
			try{
				out = response.getWriter();
				out.println("<response>");
				for (int i = 0; i < peopleList.size(); i++) {
					SecurityUserBaseinfo nSecurityUserBaseinfo = (SecurityUserBaseinfo) peopleList.get(i);
					out.println("<ress>");
					out.println("<resInputCode>" + nSecurityUserBaseinfo.getInputCode()+ "</resInputCode>");
					out.println("<resItemCode>" + nSecurityUserBaseinfo.getPmi()+ "</resItemCode>");
					out.println("<resItemName>"+ nSecurityUserBaseinfo.getName()+ "</resItemName>");
					out.println("<resItemId>" + nSecurityUserBaseinfo.getId() + "</resItemId>");
					out.println("</ress>");
				}
				out.println("</response>");
				out.close();				
			}catch(Exception e){
				e.printStackTrace();
			}
		}catch(UnsupportedEncodingException e){
			e.printStackTrace();
		}
		return null;
	}
	/**关系弹出框*/
	public ActionForward relationship(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		try{
			String inputCode = request.getParameter("inputCode");
			String flag = request.getParameter("flag");
			List<?> relationshipList = this.hspStaffBaseinfoService.getAjaxRelationship(flag, inputCode);
			request.setCharacterEncoding("UTF-8");
			response.setContentType("text/xml; charset=UTF-8");
			response.setHeader("Cache-Control", "no-cache");
			PrintWriter out;
			try{
				out = response.getWriter();
				out.println("<response>");
				for (int i = 0; i < relationshipList.size(); i++) {
					CommConfigRelationship relationship = (CommConfigRelationship) relationshipList.get(i);
					out.println("<ress>");
					out.println("<resInputCode>" + relationship.getInputCode()+ "</resInputCode>");
					out.println("<resItemCode>" + relationship.getItemCode()+ "</resItemCode>");
					out.println("<resItemName>"+ relationship.getItemName()+ "</resItemName>");
					out.println("</ress>");
				}
				out.println("</response>");
				out.close();				
			}catch(Exception e){
				e.printStackTrace();
			}
		}catch(UnsupportedEncodingException e){
			e.printStackTrace();
		}
		return null;
	}
	/**添加前的初始化*/
	public ActionForward addInit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		try{
			HspStaffBaseinfoForm hosform = new HspStaffBaseinfoForm();
			String hspId = request.getParameter("hspId");
			String deptCode = request.getParameter("deptCode");
			hosform.setHspConfigBaseinfoId(hspId);
			hosform.setDeptCode(deptCode);
			SessionForm sessionForm = (SessionForm)request.getSession(true).getAttribute("sessionForm");
			//验证操作员是否机构卫生人员维护角色,如果是则只允许维护自己所在机构的卫生人员
			if(this.checkRoleCodeService.checkRoleCodeByIds(sessionForm.getStaffRoles(), "9")){
				hosform.setHspConfigBaseinfoId(sessionForm.getStaffHospitalId());
				hosform.setStaffManagerRole(true);
			}
			this.hspStaffBaseinfoService.addInit(hosform);
		
			request.setAttribute("data", hosform);
			return mapping.findForward("add");
		}catch(Exception e){
			e.printStackTrace();
		}
		return super.execute(mapping, form, request, response);
	}
	
	/**可多选的删除*/
	public ActionForward delete(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String useForTree = request.getParameter("useForTree");
		try{
			HspStaffBaseinfoForm hsbForm = (HspStaffBaseinfoForm) form;
			
					this.hspStaffBaseinfoService.delete(hsbForm);
//					System.out.println("ID 值为 " + deleteIds[i] + " 的记录删除");
				 
			//加不加这个?
			HspStaffBaseinfoForm h = new HspStaffBaseinfoForm();
			String message = ResourcesUtil.getValue("conf.hsp.HspLocale", "hsp.java.common.deleteSuccess", request);
			if("1".equals(useForTree)){
				this.write2Response(response, "[{flag:'1', msg:'删除成功!'}]");
				return null;
			}else{
				request.setAttribute("message", message);
				return this.query(mapping, h, request, response);
			}
		}
		catch(Exception e){
			e.printStackTrace();
			if("1".equals(useForTree)){
				this.write2Response(response, "[{flag:'0', msg:'删除失败!'}]");
				return null;
			}else{
				return mapping.findForward("fail");
			}
		}
	}
	
	/**查看记录的详细信息*/
	public ActionForward detail(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		HspStaffBaseinfoForm hsbForm = (HspStaffBaseinfoForm) form;
		this.hspStaffBaseinfoService.detail(hsbForm);
		request.setAttribute("dataForm", hsbForm);
		return  mapping.findForward("detail");	
	}
	
	/**更新记录*/
	public ActionForward update(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		String useForTree = request.getParameter("useForTree");
		try{
			HspStaffBaseinfoForm hsbForm = (HspStaffBaseinfoForm)form;
			HttpSession session = request.getSession (true); 
			SessionForm sessionForm = (SessionForm)session.getAttribute("sessionForm");
			//验证操作员是否机构卫生人员维护角色,如果是则只允许维护自己所在机构的卫生人员
			if(this.checkRoleCodeService.checkRoleCodeByIds(sessionForm.getStaffRoles(), "9")){
				hsbForm.setHspConfigBaseinfoId(sessionForm.getStaffHospitalId());
				hsbForm.setTheHspName(this.hspStaffBaseinfoService.getHspNameById(sessionForm.getStaffHospitalId()));
				hsbForm.setStaffManagerRole(true);
			}
			hsbForm.setCreateUserId(sessionForm.getStaffId());
			hsbForm.setCreateUserName(sessionForm.getStaffName());
			hsbForm.setId(hsbForm.getIdHidden());
//			System.out.println("HIDE ID is " + hsbForm.getIdHidden());
			this.hspStaffBaseinfoService.update(hsbForm);
			String message = ResourcesUtil.getValue("conf.hsp.HspLocale", "hsp.java.common.msg1", request);
			if("1".equals(useForTree)){
				this.write2Response(response, "[{flag:'1', msg:'保存成功！'}]");
				return null;
			}else{
				request.setAttribute("message", message);
				HspStaffBaseinfoForm newForm = new HspStaffBaseinfoForm();
				return this.query(mapping, newForm, request, response);
			}
		}catch(Exception e){
			e.printStackTrace();
			if("1".equals(useForTree)){
				this.write2Response(response, "[{flag:'0', msg:'保存失败！'}]");
				return null;
			}else{
				return mapping.findForward("fail");
			}
		}
	}
	
	
	/**跳转到更新页面的的初始化*/
	public ActionForward updateInit(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		try{
			HspStaffBaseinfoForm hsbForm = (HspStaffBaseinfoForm)form;
			SessionForm sessionForm = (SessionForm)request.getSession(true).getAttribute("sessionForm");
			//验证操作员是否机构卫生人员维护角色,如果是则只允许维护自己所在机构的卫生人员
			if(this.checkRoleCodeService.checkRoleCodeByIds(sessionForm.getStaffRoles(), "9")){
				hsbForm.setHspConfigBaseinfoId(sessionForm.getStaffHospitalId());
				hsbForm.setTheHspName(this.hspStaffBaseinfoService.getHspNameById(sessionForm.getStaffHospitalId()));
				hsbForm.setStaffManagerRole(true);
			}
			this.hspStaffBaseinfoService.updateInit(hsbForm);
			request.setAttribute("dataForm", hsbForm);
			
			return mapping.findForward("update");
		}catch(Exception e){
			e.printStackTrace();
			return mapping.findForward("fail");
		}
	}
	/**弹出层*/
	public ActionForward getHsp(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		/*限定所在地区医疗*/
		HttpSession session = request.getSession (true); 
		SessionForm sessionForm = (SessionForm)session.getAttribute("sessionForm");
		String staffId =sessionForm.getStaffId();
				
		String inputCode = request.getParameter("inputCode"); 
		//------------根据标示符获取结果list-----1拼音 2代码 3汉字---------------------------------------------------
		//String flag = "1";
		String flag = request.getParameter("flag");//
		//设置请求以及返回数据的编码类型
		//hspType 1为除去卫生服务站2为只包括服务站和服务中心3为所有
    	String hspType = request.getParameter("hspType");
		try {
			request.setCharacterEncoding("UTF-8");
			response.setContentType("text/xml; charset=UTF-8");
			response.setHeader("Cache-Control", "no-cache");
			PrintWriter out = response.getWriter();
			String xmlString = this.hspStaffBaseinfoService.getXml(flag, inputCode,hspType,staffId, request);
			out.println(xmlString);    	
			out.close();
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			return mapping.findForward("fail");
		}
	}
	private ActionForward elsExport(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		try {
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
			String fileName = "Hospital staff infomations.xls";
			fileName = new String(fileName.getBytes("GBK"), "ISO-8859-1");

			//response.setContentType("application/vnd.ms-excel");	
			response.setContentType("application/application/octet-stream");
			response.setHeader("Content-Disposition", "attachment;filename="+fileName);

			HSSFWorkbook workbook = this.getHspStaffBaseinfoService().getExcel(hosform, townCode,ageS,ageE, fileName, request);
			if(workbook==null){
				String message = ResourcesUtil.getValue("conf.hsp.HspLocale", "hsp.java.common.userExcelCreateFail", request);
				hosform.setMessage(message);
			}else{
				// 新建一输出文件流
				ServletOutputStream fOut = response.getOutputStream();
				// 把相应的Excel 工作簿存盘
				workbook.write(fOut);
				fOut.flush();
				fOut.close();
			}
			return null;
		} catch(Exception e) {
			HspInit.println(e);
			return mapping.findForward("fail");
		}
	}
	private ActionForward elsImport(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		HspStaffBaseinfoForm hosform = (HspStaffBaseinfoForm)form;
		FormFile formfile = hosform.getFile();
		try{
			InputStream inputstream = formfile.getInputStream();
			String message  = this.getHspStaffBaseinfoService().elsImport(inputstream, request);
			if(message==null || message.trim().length()<=0){
				message = ResourcesUtil.getValue("conf.hsp.HspLocale", "hsp.java.common.importSuccess", request);
				request.setAttribute("message", message);
			}
			request.setAttribute("message", message);
			return mapping.findForward("elsImport");
		}catch(Exception e){
			String message = ResourcesUtil.getValue("conf.hsp.HspLocale", "hsp.java.common.importFail", request);
			request.setAttribute("message", message);
			HspInit.println(e);
			return mapping.findForward("elsImport");
		}		
	}
	
	public ActionForward getInfomation(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		try{
			HspStaffBaseinfoForm nHspStaffBaseinfoForm=(HspStaffBaseinfoForm) form;
			
			this.hspStaffBaseinfoService.addInit(nHspStaffBaseinfoForm);
			
			SecurityUserBaseinfo nSecurityUserBaseinfo = this.hspStaffBaseinfoService.findOneById(nHspStaffBaseinfoForm.getSecurityUserBaseinfoId());
			
			this.hspStaffBaseinfoService.dataToForm(nSecurityUserBaseinfo, nHspStaffBaseinfoForm);
			
			request.setAttribute("data", nHspStaffBaseinfoForm);
			
			return mapping.findForward("add");
		}catch(Exception e){
			e.printStackTrace();
		}
		return null;
	}
	
	private void write2Response(HttpServletResponse response, String str){
		try {
			response.setContentType("text/html");
			response.setCharacterEncoding("UTF-8");
			PrintWriter out = response.getWriter();
			out.print(str);
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
	
}
