package com.tianjian.security.struts.action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.tianjian.security.bean.SecurityLicense;
import com.tianjian.security.business.ISecuritySystemUsersService;
import com.tianjian.security.struts.form.SecuritySystemUsersForm;
import com.tianjian.util.ResourcesUtil;
/**
 * SECURITY_SYSTEM_USERS用户登陆表用Action
 * @author DZENALL
 * @since 2008-3-25 08:48
 */
public class SecuritySystemUsersAction extends Action {

	private ISecuritySystemUsersService service;

	public ISecuritySystemUsersService getService(){return service;}
	public void setService(ISecuritySystemUsersService service){this.service = service;}
	
	
	/**
	 * 程序主入口
	 */
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){        
		String verbId = request.getParameter("verbId");
		////////////////////////////////////////////////
	 
		////////////////////////////////////////////////
        if(verbId.equals("regist")){
            return regist(mapping, form, request, response);
        } else if(verbId.equals("registInit")){
            return registInit(mapping, form, request, response);
        } else if(verbId.equals("verification")){
        	return verification(mapping, form, request, response);
        }
        else {
            return mapping.findForward("fail");
        }		
	}
	
	/**
	 * 註冊前的初始化
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward registInit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
		try{
			SecuritySystemUsersForm hosForm = new SecuritySystemUsersForm();
			service.registInit(hosForm);
			request.setAttribute("dataForm", hosForm);
			return mapping.findForward("regist");
		}catch(Exception e){
			e.printStackTrace();
			return mapping.findForward("fail");
		}
		
	}
	
	public ActionForward verification(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
		try{
			 SecuritySystemUsersForm hosForm = (SecuritySystemUsersForm)form;
			 //SecurityStaffBaseinfo securitySystemUsers = service.checkData(hosForm);
			 request.setCharacterEncoding("UTF-8");
			 response.setContentType("text/html;charset=utf-8");
			 PrintWriter out = response.getWriter();
			 if(service.isRegisted(hosForm.getStaffCode())){
				   out.println(ResourcesUtil.getValue("conf.security.securityInit", "security.java.struts.action.verification.warn", request));
			 }else{
				   out.println(ResourcesUtil.getValue("conf.security.securityInit", "security.java.struts.action.verification.warn1", request));
			 } 
			out.close();
		}catch(Exception e){
			e.printStackTrace();
			return mapping.findForward("fail");
		}
		return null;
	}
	/**
	 * 註冊過程
	 * @param mapping
	 * @param form
	 * @param request
	 * @param response
	 * @return
	 */
	public ActionForward regist(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response){
		try{
			SecuritySystemUsersForm hosForm = (SecuritySystemUsersForm)form;
			
			String reg1 = hosForm.getRegCode_1();
			String reg2 = hosForm.getRegCode_2();
			String reg3 = hosForm.getRegCode_3();
			String reg4 = hosForm.getRegCode_4();
			String reg5 = hosForm.getRegCode_5();
			String reg = reg1+"-"+reg2+"-"+reg3+"-"+reg4+"-"+reg5;
			
			//判断注册码是否存在于系统内
			SecurityLicense securityLicense = this.service.checkRegistCode(reg.trim());
			
			if(securityLicense == null){
				hosForm.setMessage("该注册码非法!");
				service.registInit(hosForm);
				request.setAttribute("dataForm", hosForm);
				return mapping.findForward("regist");
			}
			
			//判断注册码是否已经被使用
			if(securityLicense.getStartTime()!=null ){
				hosForm.setMessage("该注册码已经被使用!");
				service.registInit(hosForm);
				request.setAttribute("dataForm", hosForm);
				return mapping.findForward("regist");
			}
			
			//判断用户名是否可以使用
			if(service.isRegisted(hosForm.getStaffCode())){
				hosForm.setMessage(ResourcesUtil.getValue("conf.security.securityInit", "security.java.struts.action.verification.warn", request));
				service.registInit(hosForm);
				request.setAttribute("dataForm", hosForm);
				return mapping.findForward("regist");
			}
			
			hosForm.setSecurityStaffBaseinfoId(securityLicense.getSecurityStaffBaseinfoId());
			service.save(hosForm);
			
			SecuritySystemUsersForm hosFormNew = new SecuritySystemUsersForm();
			service.registInit(hosFormNew);
			hosFormNew.setMessage("注册成功!");
			hosFormNew.setMessage(ResourcesUtil.getValue("conf.security.securityInit", "security.java.struts.action.SecuritySystemUsersAction.warn3", request));
			request.setAttribute("dataForm", hosFormNew);
			return mapping.findForward("regist");
			
		    /*String regCode = service.hasLicense(hosForm.getStaffCode());
			if(regCode == null || regCode.trim().length() < 0){
				//hosForm.setMessage("该用户未获得注册码!");
				hosForm.setMessage(ResourcesUtil.getValue("conf.security.securityInit", "security.java.struts.action.SecuritySystemUsersAction.warn1", request));
				service.registInit(hosForm);
				request.setAttribute("dataForm", hosForm);
				return mapping.findForward("regist");
			}
			if(service.isRegisted(hosForm.getStaffCode())){
				//hosForm.setMessage("该用户已经注册!");
				hosForm.setMessage(ResourcesUtil.getValue("conf.security.securityInit", "security.java.struts.action.SecuritySystemUsersAction.warn2", request));
				service.registInit(hosForm);
				request.setAttribute("dataForm", hosForm);
				return mapping.findForward("regist");
				if(reg.equals(regCode)){
				service.save(hosForm);
				
				SecuritySystemUsersForm hosFormNew = new SecuritySystemUsersForm();
				service.registInit(hosFormNew);
				//hosFormNew.setMessage("注册成功!");
				hosFormNew.setMessage(ResourcesUtil.getValue("conf.security.securityInit", "security.java.struts.action.SecuritySystemUsersAction.warn3", request));
				request.setAttribute("dataForm", hosFormNew);
				return mapping.findForward("regist");
			}else{
				//hosForm.setMessage("注册码输入错误!");
				hosForm.setMessage(ResourcesUtil.getValue("conf.security.securityInit", "security.java.struts.action.SecuritySystemUsersAction.warn4", request));
				service.registInit(hosForm);
				request.setAttribute("dataForm", hosForm);
				return mapping.findForward("regist");
			}		
			}*/
			
			
		}catch(Exception e){
			e.printStackTrace();
			return mapping.findForward("fail");
		}		
	}
}
