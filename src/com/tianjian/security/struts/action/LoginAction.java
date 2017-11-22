//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_4.1.1/xslt/JavaClass.xsl
package com.tianjian.security.struts.action;

import java.io.PrintWriter;
import java.util.Date;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.time.DateFormatUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.tianjian.security.bean.SecurityStaffAliveAccess;
import com.tianjian.security.business.ILoginService;
import com.tianjian.security.business.ISecurityStaffAliveAccessService;
import com.tianjian.security.struts.form.LoginForm;
import com.tianjian.security.struts.form.LoginSecondForm;
import com.tianjian.security.struts.form.SessionForm;
import com.tianjian.security.struts.servlet.LoginListener;
import com.tianjian.tenant.util.TenantIdHolder;
import com.tianjian.util.ResourcesUtil;
import com.tianjian.util.comm.MD5;
/**
 * @author ch_f001
 */
public class LoginAction extends BaseAction {

	private ILoginService service;
	private ISecurityStaffAliveAccessService securityStaffAliveAccessService;
	
	

	/**
	 * @return Returns the service.
	 */
	public ILoginService getService() {
		return service;
	}

	public ISecurityStaffAliveAccessService getSecurityStaffAliveAccessService() {
		return securityStaffAliveAccessService;
	}

	public void setSecurityStaffAliveAccessService(
			ISecurityStaffAliveAccessService securityStaffAliveAccessService) {
		this.securityStaffAliveAccessService = securityStaffAliveAccessService;
	}

	/**
	 * @param service The service to set.
	 */
	public void setService(ILoginService service) {
		this.service = service;
	}

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		String verbId = request.getParameter("verbId");
		if (verbId.equals("login")) {
			return this.login(mapping, form, request, response);
		}else if (verbId.equals("loginInit")) {
			return this.loginInit(mapping, form, request, response);
		} else if (verbId.equals("reLogin")) {
			return this.reLogin(mapping, form, request, response);
		} else if (verbId.equals("getEndPublicClass")) {
			return this.getEndPublicClass(mapping, form, request, response);
		}else {
			return mapping.findForward("fail");
		}
	}

	public ActionForward loginInit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		LoginForm mainForm = (LoginForm) form;
		try {
			service.checkVersionRegistCode(mainForm,request);
		} catch (Exception e) {
			e.printStackTrace();
			return mapping.findForward("fail");
		}
		request.setAttribute("loginForm", mainForm);
		return mapping.findForward("init");
	}
	
	
	public ActionForward login(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		try {
			LoginForm mainForm = (LoginForm) form;
			LoginSecondForm loginSecondForm = new LoginSecondForm();
			//--------------首先验证验证码----------------------
			
			//如果需要验证
			ServletContext application = request.getSession().getServletContext();
			String verifyFlag = (String)application.getAttribute("security.LOGIN_NEED_VERIFY");
			if(verifyFlag!=null&&verifyFlag.equalsIgnoreCase("TRUE")){
				String loginVerCode = (String)request.getSession().getAttribute("LOGINVERCODE");
				if(!mainForm.getLoginVerCode().trim().equalsIgnoreCase(loginVerCode)){
					mainForm.setMessage("验证码输入错误！");
					request.setAttribute("loginForm", mainForm);
					return mapping.findForward("init");
				}
			}
			
			
			if (mainForm.getMessage().trim().length() > 0) {
				request.setAttribute("loginForm", mainForm);
				return mapping.findForward("init");
			}
			
			// -------------开始检查版本注册信息的内容------------
//			service.checkVersionRegistCode(mainForm,request);
//			if (mainForm.getMessage().trim().length() > 0) {
//				request.setAttribute("loginForm", mainForm);
//				return mapping.findForward("init");
//			}
			// -------------开始构造loginMenuForm的内容------------
			service.checkUser(mainForm,request);
			if (mainForm.getMessage().trim().length() > 0) {
				request.setAttribute("loginForm", mainForm);
				return mapping.findForward("init");
			}
			if(mainForm.getTenantId() != null && mainForm.getTenantId().trim().length() > 0){
				//设置租户id到当前线程中
				TenantIdHolder.set(mainForm.getTenantId());
			}
			service.getPublicClass(mainForm.getStaffId(), loginSecondForm ,request);
			if (loginSecondForm.getPcList() != null && loginSecondForm.getPcList().size() > 0) {
				
				SessionForm sessionForm = new SessionForm();
				sessionForm.setVersionUserName(mainForm.getVersionUserName());
				sessionForm.setVersionStopDate(mainForm.getVersionStopDate());
				sessionForm.setStaffName(mainForm.getName()); // ----目前取到
				sessionForm.setStaffId(mainForm.getStaffId()); // --- userId---
				sessionForm.setStaffCode(mainForm.getStaffCode()); // ---login staff code---
				sessionForm.setSystemLimitedFlag(mainForm.getSystemUserLimitedFlag());
				sessionForm.setStaffHospitalId(mainForm.getHospitalId());
				sessionForm.setHomePageType(mainForm.getHomePageType());
				sessionForm.setStaffType(mainForm.getStaffType());
				sessionForm.setStaffRoles(loginSecondForm.getRolesId());
				sessionForm.setStaffLoginTime(DateFormatUtils.format(new Date(), "yyyy/MM/dd HH:mm:ss"));//操作员本次登录时间
				sessionForm.setStaffLicenseStopDate(mainForm.getStaffLicenseStopDate());
				sessionForm.setTenantId(mainForm.getTenantId());
				sessionForm.setMaps(mainForm.getMaps());
				
				HttpSession session = request.getSession(true);
				session.setAttribute("sessionForm", sessionForm);
				session.setAttribute("longin",new LoginListener());
				//登录成功后同时保存到数据库SECURITY_STAFF_ALIVE_ACCESS
				//1.查询是否存在记录 如存在则修改 如不存在则添加
				
				
				SecurityStaffAliveAccess sSAA = securityStaffAliveAccessService.getByStaffId(mainForm.getStaffId());
				if(sSAA==null){
					sSAA = new SecurityStaffAliveAccess();
				}
				sSAA.setSecurityStaffBaseinfoId(mainForm.getStaffId());
				sSAA.setLoginIp(request.getRemoteAddr());
				sSAA.setSessionId(MD5.toMD5(session.getId()));
				sSAA.setStartTime(new Date());
				sSAA.setComments("");
				
				securityStaffAliveAccessService.saveOrUpdate(sSAA);
				
				request.setAttribute("loginSecondForm", loginSecondForm);
				return mapping.findForward("ok");
			} else {
				//mainForm.setMessage("该用户还没有获得菜单权限，请与管理员联系！");
				mainForm.setMessage(ResourcesUtil.getValue("conf.security.securityInit", "security.java.commom.warn", request));
				request.setAttribute("loginForm", mainForm);
				return mapping.findForward("init");
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			return mapping.findForward("fail");
		}
	}

	public ActionForward reLogin(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		try {
			LoginForm mainForm = (LoginForm) form;
			LoginSecondForm loginSecondForm = new LoginSecondForm();
			SessionForm sessionForm = new SessionForm();
			HttpSession session = request.getSession(true);
			sessionForm = (SessionForm) session.getAttribute("sessionForm");
			// -------------开始构造loginMenuForm的内容------------
			service.getPublicClass(sessionForm.getStaffId(), loginSecondForm, request);
			loginSecondForm.setPublicClassId(sessionForm.getSelectedPublicClassId());
			if (loginSecondForm.getPcList() != null && loginSecondForm.getPcList().size() > 0) {
				request.setAttribute("loginSecondForm", loginSecondForm);
				return mapping.findForward("ok");
			} else {
				//mainForm.setMessage("该用户还没有获得菜单权限，请与管理员联系！");
				mainForm.setMessage(ResourcesUtil.getValue("conf.security.securityInit", "security.java.commom.warn", request));
				request.setAttribute("loginForm", mainForm);
				return mapping.findForward("init");
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			return mapping.findForward("noLogin");
		}
	}
	
	 public ActionForward getEndPublicClass(ActionMapping mapping, ActionForm form,HttpServletRequest request, HttpServletResponse response){
		 if(checkUser(request, response) == null)
	            return mapping.findForward("noLogin");	
		 try {
	  			request.setCharacterEncoding("UTF-8");
	  			response.setContentType("text/xml; charset=UTF-8");
	  			response.setHeader("Cache-Control", "no-cache");
	  			PrintWriter out = response.getWriter();
	  			//
	  			String publicClassId = request.getParameter("publicClassId");
	  			HttpSession session = request.getSession(true);
	  			SessionForm sessionForm ;
				sessionForm = (SessionForm) session.getAttribute("sessionForm");
				
				String xmlString = service.getEndPublicClass(publicClassId,sessionForm.getStaffId(),request);				
				System.out.println(xmlString);
	  			out.write(xmlString);    	
	  			out.close();
	  		} catch (Exception e) {
	  			e.printStackTrace();
	  		}
	  		return null;
	  	}
	
}
