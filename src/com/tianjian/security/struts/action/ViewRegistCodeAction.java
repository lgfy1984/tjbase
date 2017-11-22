package com.tianjian.security.struts.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.tianjian.security.business.IViewRegistCodeService;
import com.tianjian.security.struts.form.ViewRegistCodeForm;

/**
 * 查询医疗人员注册码用Action
 * @author DZENALL
 */
public class ViewRegistCodeAction extends BaseAction {

	/*-----------------------------------
	 * 定义接口以及引入接口
	 -----------------------------------*/
	private IViewRegistCodeService service;

	public IViewRegistCodeService getService() {
		return service;
	}

	public void setService(IViewRegistCodeService service) {
		this.service = service;
	}

	/*---------------------------------------------------------
	 * Action入口
	 ----------------------------------------------------------*/
	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		try {
			if (checkUser(request, response) == null) {
				return mapping.findForward("noLogin");
			}
			String verbId = request.getParameter("verbId");
			if ("view".equalsIgnoreCase(verbId)) {// 如果verbId为view，那么执行查找并将结果返回
				ViewRegistCodeForm hosform = (ViewRegistCodeForm) form;
				this.getService().viewQuery(hosform);
				request.setAttribute("data", hosform);
				return mapping.findForward("view");
			} else {// 如果verbId非view，则转到医疗人员注册码查询页面
				ViewRegistCodeForm hosformNew = new ViewRegistCodeForm();
				request.setAttribute("viewRegistCodeForm", hosformNew);
				return mapping.findForward("viewInit");
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			return mapping.findForward("fail");
		}
	}
}
