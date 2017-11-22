package com.tianjian.comm.struts.action;

import java.text.MessageFormat;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.tianjian.comm.bean.CommConfigInputDict;
import com.tianjian.comm.business.ICommConfigInputDict2Service;
import com.tianjian.comm.struts.form.CommConfigInputDictForm;
import com.tianjian.security.struts.action.BaseAction;
import com.tianjian.util.ResourcesUtil;
import com.tianjian.util.comm.PageBean;

public class CommConfigInputDict2Action extends BaseAction {

	private ICommConfigInputDict2Service commConfigInputDict2Service;
	
	public ICommConfigInputDict2Service getCommConfigInputDict2Service() {
		return commConfigInputDict2Service;
	}

	public void setCommConfigInputDict2Service(
			ICommConfigInputDict2Service commConfigInputDict2Service) {
		this.commConfigInputDict2Service = commConfigInputDict2Service;
	}

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		if(checkUser(request, response) == null)
            return mapping.findForward("noLogin");
		String verbId = request.getParameter("verbId");
		if (verbId.equals("add")) {
			return this.add(mapping, form, request, response);
		} else if (verbId.equals("addInit")) {
			return this.addInit(mapping, form, request, response);
		} else if (verbId.equals("query")) {
			return this.query(mapping, form, request, response);
		}else if(verbId.equals("queryDetail")){
			return this.query(mapping, form, request, response);
		} else if (verbId.equals("update")) {
			return this.update(mapping, form, request, response);
		} else if (verbId.equals("updateInit")) {
			return this.updateInit(mapping, form, request, response);
		} else if (verbId.equals("detail")) {
			return this.detail(mapping, form, request, response);
		} else if (verbId.equals("delete")) {
			return this.delete(mapping, form, request, response);
		}else if(verbId.equals("init")){
			return this.init(mapping, form, request, response);
		}else if(verbId.equals("initDetail")){
			return this.init(mapping, form, request, response);	
		} else {
			return mapping.findForward("fail");
		}
	}

	public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		try {
			CommConfigInputDictForm hosform = (CommConfigInputDictForm) form;
			CommConfigInputDict checkData = commConfigInputDict2Service.checkData(hosform.getItemCode(),hosform.getItemName());
			if (checkData != null) {
				//String message = CommMessage.getMsg("0-000001");
				ServletContext application = request.getSession().getServletContext();
				String message = (String)application.getAttribute("comm.0-000001");
				if(null != message && message.length() > 0){
					hosform.setMessage(message);//没有取到值
				}else{
					message = ResourcesUtil.getValue("conf.comm.CommLocale", "comm.java.CommConfigInputDict2Action.warn1", request) + "!";
					message = MessageFormat.format(message, new Object[]{hosform.getItemCode(), hosform.getItemName()});
					hosform.setMessage(message);
				}
				commConfigInputDict2Service.addInit(hosform);
				request.setAttribute("commConfigInputDict", hosform);
				return mapping.findForward("add");
			}
			this.commConfigInputDict2Service.save(hosform);
			CommConfigInputDictForm hosformNew = new CommConfigInputDictForm();
			String message = ResourcesUtil.getValue("conf.comm.CommLocale", "comm.java.commom.addSuccess", request) + "!";
			hosformNew.setMessage(message);
			return this.query(mapping, hosformNew, request, response);
		}
		catch (Exception e) {
			return mapping.findForward("fail");
		}
	}

	public ActionForward init(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		try {
			String verbId = request.getParameter("verbId");
			CommConfigInputDictForm ssform = (CommConfigInputDictForm) form;
			request.setAttribute("commConfigInputDict", ssform);
			if(verbId.equals("initDetail")){
				return mapping.findForward("queryDetail");
			}else{
				return mapping.findForward("query");
			}
		}
		catch (Exception e) {
			return mapping.findForward("fail");
		}
	}
	
	public ActionForward query(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		try {
			String verbId = request.getParameter("verbId");
			CommConfigInputDictForm hosform = (CommConfigInputDictForm) form;
			// ////// page start ////////////////////////
			PageBean pb = new PageBean();
			int count;
			int page = 0;
			int recordCount = commConfigInputDict2Service.getCount(hosform.getItemCode(), hosform.getItemName(), hosform.getInputCode(), hosform.getInputCodeWb(), hosform.getInputCode1(), hosform.getInputCode2());
			pb.setCount(recordCount);
			String pageString = request.getParameter("page");
			//int pageSize = CommInit.getPageSize("PAGE_SIZE");
			int pageSize = 10;
			if(request.getSession().getAttribute("page_2828810b39763ab50139763ab5de0000")!=null){
				pageSize = Integer.parseInt((String)request.getSession().getAttribute("page_2828810b39763ab50139763ab5de0000"));
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
			commConfigInputDict2Service.getSearch(hosform, count, pageSize);
			commConfigInputDict2Service.serchInit(hosform);
			request.setAttribute("commConfigInputDict", hosform);
			if(verbId.equals("queryDetail")){
				return mapping.findForward("queryDetail");
			}else{
				return mapping.findForward("query");			
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			return mapping.findForward("fail");
		}
	}

	public ActionForward updateInit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		try {
			CommConfigInputDictForm hosform = (CommConfigInputDictForm) form;
			hosform.setItemCode(hosform.getItemCodeHidden());
			commConfigInputDict2Service.updateInit(hosform);
			request.setAttribute("commConfigInputDict", hosform);
			return mapping.findForward("update");
		}
		catch (Exception e) {
			return mapping.findForward("fail");
		}
	}

	public ActionForward addInit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		try {
			CommConfigInputDictForm hosform = new CommConfigInputDictForm();
			commConfigInputDict2Service.addInit(hosform);
			request.setAttribute("commConfigInputDict", hosform);
			return mapping.findForward("add");
		}
		catch (Exception e) {
			return mapping.findForward("fail");
		}
	}

	public ActionForward detail(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		try {
			CommConfigInputDictForm hosform = (CommConfigInputDictForm) form;
			hosform.setItemCode(hosform.getItemCodeHidden());
			commConfigInputDict2Service.updateInit(hosform);
			request.setAttribute("commConfigInputDict", hosform);
			return mapping.findForward("detail");
		}
		catch (Exception e) {
			return mapping.findForward("fail");
		}
	}

	public ActionForward update(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		try {
			CommConfigInputDictForm hosform = (CommConfigInputDictForm) form;
			commConfigInputDict2Service.update(hosform);
			CommConfigInputDictForm hosformNew = new CommConfigInputDictForm();
			String message = ResourcesUtil.getValue("conf.comm.CommLocale", "comm.java.commom.updateSuccess", request) + "!";
			hosformNew.setMessage(message);
			return this.query(mapping, hosformNew, request, response);
		}
		catch (Exception e) {
			return mapping.findForward("fail");
		}
	}

	public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		try {
			CommConfigInputDictForm hosform = (CommConfigInputDictForm) form;
			commConfigInputDict2Service.delete(hosform);
			CommConfigInputDictForm hosformNew = new CommConfigInputDictForm();
			return this.query(mapping, hosformNew, request, response);
		}
		catch (Exception e) {
			return mapping.findForward("fail");
		}
	}
}
