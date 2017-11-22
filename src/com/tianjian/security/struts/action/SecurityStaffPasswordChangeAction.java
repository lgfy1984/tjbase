package com.tianjian.security.struts.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.tianjian.security.business.ISecurityStaffPasswordChangeService;
import com.tianjian.security.struts.form.SecurityStaffPasswordChangeForm;
import com.tianjian.security.struts.form.SessionForm;
import com.tianjian.util.ResourcesUtil;

public class SecurityStaffPasswordChangeAction extends BaseAction {

	public SecurityStaffPasswordChangeAction() {}

	private ISecurityStaffPasswordChangeService service;

	public ISecurityStaffPasswordChangeService getService() {
		return service;
	}

	public void setService(ISecurityStaffPasswordChangeService service) {
		this.service = service;
	}

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		if (checkUser(request, response) == null) {
			return mapping.findForward("noLogin");
		}
		String verbId = request.getParameter("verbId");
		if (verbId.equals("init")) {
			return init(mapping, form, request, response);
		} else if (verbId.equals("update")) {
			return update(mapping, form, request, response);
		} else {
			return mapping.findForward("fail");
		}
	}

	public ActionForward init(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		try {
			SecurityStaffPasswordChangeForm spcform = (SecurityStaffPasswordChangeForm) form;
			HttpSession session = request.getSession(true);
			SessionForm sessionForm = new SessionForm();
			sessionForm = (SessionForm) session.getAttribute("sessionForm");
			if (sessionForm != null) {
				spcform.setStaffId(sessionForm.getStaffId());
			}
			request.setAttribute("securityStaffPasswordChange", spcform);
			return mapping.findForward("init");
		}
		catch (Exception e) {
			return mapping.findForward("fail");
		}
	}

	public ActionForward update(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		try {
			SecurityStaffPasswordChangeForm spcform = (SecurityStaffPasswordChangeForm) form;
			boolean checkData = service.checkData(spcform.getStaffId(), spcform.getPasswd());
			if (checkData==false) {
				//spcform.setMessage("原密码输入错误！");
				spcform.setMessage(ResourcesUtil.getValue("conf.security.securityInit", "security.java.struts.action.SecurityStaffPasswordChangeAction.warn", request));
				request.setAttribute("securityStaffPasswordChange", spcform);
				return mapping.findForward("init");
			}
			service.update(spcform);
			SecurityStaffPasswordChangeForm newform = new SecurityStaffPasswordChangeForm();
			//newform.setMessage("密码修改成功！");
			newform.setMessage(ResourcesUtil.getValue("conf.security.securityInit", "security.java.struts.action.SecurityStaffPasswordChangeAction.warn1", request));
			newform.setStaffId(spcform.getStaffId());
			request.setAttribute("securityStaffPasswordChange", newform);
			return mapping.findForward("init");
		}
		catch (Exception e) {
			return mapping.findForward("fail");
		}
	}
}