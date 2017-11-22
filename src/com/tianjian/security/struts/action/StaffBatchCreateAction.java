package com.tianjian.security.struts.action;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.Action;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.tianjian.security.business.IStaffBatchCreateService;
import com.tianjian.security.struts.form.StaffBatchCreateForm;
import com.tianjian.util.comm.MD5;

/**
 * 用于批量创建操作人员，为每个医院创建一个用于维护卫生人员的操作人员。
 * @author weiwo
 *
 */
public class StaffBatchCreateAction extends Action{
	private IStaffBatchCreateService staffBatchCreateService;
	
	public IStaffBatchCreateService getStaffBatchCreateService() {
		return staffBatchCreateService;
	}

	public void setStaffBatchCreateService(
			IStaffBatchCreateService staffBatchCreateService) {
		this.staffBatchCreateService = staffBatchCreateService;
	}

	@Override
	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		try{
			String verbId = request.getParameter("verbId");
			if("init".equals(verbId)){
				return this.init(mapping, form, request, response);
			}else if("query".equals(verbId)){
				return this.query(mapping, form, request, response);
			}else if("createBySelectedHspIds".equals(verbId)){
				return this.createBySelectedHspIds(mapping, form, request, response);
			}else if("createAll".equals(verbId)){
				return this.createAll(mapping, form, request, response);
			}else{
				return mapping.findForward("fail");
			}
		}catch (Exception e) {
			e.printStackTrace();
		}
		return mapping.findForward("error");
	}
	
	public ActionForward init(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		StaffBatchCreateForm sbcForm = (StaffBatchCreateForm) form;
		int count = this.staffBatchCreateService.getCount(sbcForm);
		sbcForm.setCount(count);
		request.setAttribute("data", sbcForm);
		return mapping.findForward("list");
	}
	
	public ActionForward query(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		StaffBatchCreateForm sbcForm = (StaffBatchCreateForm) form;
		int count = this.staffBatchCreateService.getCount(sbcForm);
		sbcForm.setCount(count);
		List<?> resultList = this.staffBatchCreateService.list(sbcForm);
		sbcForm.setResultList(resultList);
		request.setAttribute("data", sbcForm);
		return mapping.findForward("list");
	}
	
	public ActionForward createBySelectedHspIds(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		StaffBatchCreateForm sbcForm = (StaffBatchCreateForm) form;
		ServletContext application = request.getSession(true).getServletContext();
		String defaultPassword = (String)application.getAttribute("USER_PASSWORD");
		if(defaultPassword != null){
			defaultPassword = MD5.toMD5(defaultPassword); 
		}else{
			defaultPassword = MD5.toMD5("666666"); 
		}
		this.staffBatchCreateService.createBySelectedHspIds(sbcForm, defaultPassword);
		sbcForm.setVerbId("query");
		return this.query(mapping, form, request, response);
	}
	
	public ActionForward createAll(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response)
	throws Exception {
		StaffBatchCreateForm sbcForm = (StaffBatchCreateForm) form;
		ServletContext application = request.getSession(true).getServletContext();
		String defaultPassword = (String)application.getAttribute("USER_PASSWORD");
		if(defaultPassword != null){
			defaultPassword = MD5.toMD5(defaultPassword); 
		}else{
			defaultPassword = MD5.toMD5("666666"); 
		}
		//判断是否所有的机构都成功创建操作员,如果是就跳转初始化页面,不是就显示列表
		if(this.staffBatchCreateService.createAll(sbcForm, defaultPassword)){
			sbcForm.setVerbId("init");
			return this.init(mapping, form, request, response);
		}else{
			sbcForm.setVerbId("query");
			return this.query(mapping, form, request, response);
		}
	}

}
