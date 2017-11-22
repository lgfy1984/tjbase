package com.tianjian.security.struts.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionError;
import org.apache.struts.action.ActionErrors;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.tianjian.security.bean.SecurityStaffAliveAccess;
import com.tianjian.security.business.ISecurityStaffAliveAccessService;
import com.tianjian.security.business.service.SecurityStaffAliveAccessServiceImpl;
import com.tianjian.security.struts.form.SessionForm;
import com.tianjian.util.ApplicationContextUtil;
import com.tianjian.util.comm.MD5;
/**
 * 基础的ACTION方法，用于初始化控制
 * @author ch_f001
 *
 */
public class BaseAction extends Action {
	private ISecurityStaffAliveAccessService securityStaffAliveAccessService;

	public ISecurityStaffAliveAccessService getSecurityStaffAliveAccessService() {
		return securityStaffAliveAccessService;
	}
	public void setSecurityStaffAliveAccessService(
			ISecurityStaffAliveAccessService securityStaffAliveAccessService) {
		this.securityStaffAliveAccessService = securityStaffAliveAccessService;
	}
	public BaseAction() {
		super();
	}
	// ----------检查用户-------------------------
	protected String checkUser(HttpServletRequest request, HttpServletResponse response) {
		SessionForm userForm = null;
		userForm = (SessionForm) request.getSession().getAttribute("sessionForm");

		if (userForm == null) {
			//System.out.println("you have no loginning!!!!");
			
			ActionErrors errors = new ActionErrors();
			errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionError("errors.login"));
			this.saveErrors(request, errors);
			return null;
		} else {
			//是否禁用单点登录
			String disableSingleSignOn = (String)request.getSession().getServletContext().getAttribute("security.DISEABLE_SINGLE_SIGN_ON");
			if(!"1".equals(disableSingleSignOn)){//如果没有禁用单点登录,则查询登录日志，判断sesssionId是否一致
				System.out.println("当前sessionID是:" + request.getSession().getId());
				SecurityStaffAliveAccessServiceImpl service = (SecurityStaffAliveAccessServiceImpl) WebApplicationContextUtils.getRequiredWebApplicationContext(request.getSession().getServletContext()).getBean("securityStaffAliveAccessService");
				SecurityStaffAliveAccess sSAA = service.getByStaffId(userForm.getStaffId());
				if(sSAA!=null){
					if(sSAA.getSessionId()!=null&&!sSAA.getSessionId().equals(MD5.toMD5(request.getSession().getId()))){
						 return  null; 
					}
				}
			}
			//session不同 返回null
			return userForm.getStaffCode();
			//return null;
		}
	}
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		if (checkUser(request,response)==null){
			return mapping.findForward("fail");
		}else{
			return null;
		}
	}

//	// ----------检查用户-------------------------
//	protected String checkUser(HttpServletRequest request, HttpServletResponse response) {
//		Customer user = null;
//		user = (Customer) request.getSession().getAttribute("user");
//		if (user == null) {
//			System.out.println("you have no loginning!!!!");
//			ActionErrors errors = new ActionErrors();
//			errors.add(ActionErrors.GLOBAL_MESSAGE, new ActionError("errors.login"));
//			this.saveErrors(request, errors);
//			return null;
//		} else {
//			return user.getCustName();
//		}
//		
//	}
}
