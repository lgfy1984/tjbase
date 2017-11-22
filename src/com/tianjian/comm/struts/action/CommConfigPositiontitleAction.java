package com.tianjian.comm.struts.action;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.tianjian.comm.bean.CommConfigPositiontitle;
import com.tianjian.comm.business.ICommConfigInputDictService;
import com.tianjian.comm.business.ICommConfigPositiontitleService;
import com.tianjian.comm.struts.form.CommConfigPositiontitleForm;
import com.tianjian.security.struts.action.BaseAction;
import com.tianjian.util.comm.PageBean;

public class CommConfigPositiontitleAction extends BaseAction {

	private ICommConfigPositiontitleService commConfigPositiontitleService;
	private ICommConfigInputDictService commConfigInputDictService;
	
	public ICommConfigPositiontitleService getCommConfigPositiontitleService() {
		return commConfigPositiontitleService;
	}
	public void setCommConfigPositiontitleService(
			ICommConfigPositiontitleService commConfigPositiontitleService) {
		this.commConfigPositiontitleService = commConfigPositiontitleService;
	}
	public ICommConfigInputDictService getCommConfigInputDictService() {
		return commConfigInputDictService;
	}
	public void setCommConfigInputDictService(ICommConfigInputDictService commConfigInputDictService) {
		this.commConfigInputDictService = commConfigInputDictService;
	}

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		String verbId = request.getParameter("verbId");
		if ("add".equals(verbId)) {
			return this.add(mapping, form, request, response);
		} else if ("addInit".equals(verbId)) {
			return this.addInit(mapping, form, request, response);
		} else if ("init".equals(verbId)) {
			return this.init(mapping, form, request, response);
		} else if ("query".equals(verbId)) {
			return this.query(mapping, form, request, response);
		} else if ("update".equals(verbId)) {
			return this.update(mapping, form, request, response);
		} else if ("updateInit".equals(verbId)) {
			return this.updateInit(mapping, form, request, response);
		} else if ("detail".equals(verbId)) {
			return this.detail(mapping, form, request, response);
		} else if ("delete".equals(verbId)) {
			return this.delete(mapping, form, request, response);
		} else {
			return mapping.findForward("fail");
		}
	}

	public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		try {
			CommConfigPositiontitleForm hosform = (CommConfigPositiontitleForm) form;
			CommConfigPositiontitle checkData = commConfigPositiontitleService.checkData(hosform.getItemCode());
			if (checkData != null) {
				//String message = CommMessage.getMsg("0-000001");
				ServletContext application = request.getSession().getServletContext();
				String message = (String)application.getAttribute("comm.0-000001");
				hosform.setMessage(message);
				commConfigPositiontitleService.addInit(hosform);
				request.setAttribute("commConfigPositiontitle", hosform);
				return mapping.findForward("add");
			}
			hosform.setInputCode(commConfigInputDictService.getInputCode(hosform.getItemName()));
			this.commConfigPositiontitleService.save(hosform);
			CommConfigPositiontitleForm hosformNew = new CommConfigPositiontitleForm();
			return this.query(mapping, hosformNew, request, response);
		}
		catch (Exception e) {
			return mapping.findForward("fail");
		}
	}

	public ActionForward init(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		try {
			CommConfigPositiontitleForm hosform = (CommConfigPositiontitleForm) form;
			request.setAttribute("commConfigPositiontitle", hosform);
			return mapping.findForward("query");
		}
		catch (Exception e) {
			e.printStackTrace();
			return mapping.findForward("fail");
		}
	}
	
	public ActionForward query(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		try {
			CommConfigPositiontitleForm hosform = (CommConfigPositiontitleForm) form;
			// ////// page start ////////////////////////
			PageBean pb = new PageBean();
			int count;
			int page = 0;
			int recordCount = commConfigPositiontitleService.getCount(hosform.getItemCode(), hosform.getItemName(), hosform.getInputCode(), hosform.getSeqNo());
			pb.setCount(recordCount);
			String pageString = request.getParameter("page");
			//int pageSize = CommInit.getPageSize("PAGE_SIZE");
			ServletContext application = request.getSession().getServletContext();
			int pageSize = Integer.parseInt((String)application.getAttribute("comm.PAGE_SIZE"));
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
			commConfigPositiontitleService.getSearch(hosform, count, pageSize);
			commConfigPositiontitleService.serchInit(hosform);
			request.setAttribute("commConfigPositiontitle", hosform);
			return mapping.findForward("query");
		}
		catch (Exception e) {
			e.printStackTrace();
			return mapping.findForward("fail");
		}
	}

	public ActionForward updateInit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		try {
			CommConfigPositiontitleForm hosform = (CommConfigPositiontitleForm) form;
			hosform.setItemCode(hosform.getItemCodeHidden());
			commConfigPositiontitleService.updateInit(hosform);
			request.setAttribute("commConfigPositiontitle", hosform);
			return mapping.findForward("update");
		}
		catch (Exception e) {
			return mapping.findForward("fail");
		}
	}

	public ActionForward addInit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		try {
			CommConfigPositiontitleForm hosform = new CommConfigPositiontitleForm();
			commConfigPositiontitleService.addInit(hosform);
			request.setAttribute("commConfigPositiontitle", hosform);
			return mapping.findForward("add");
		}
		catch (Exception e) {
			return mapping.findForward("fail");
		}
	}

	public ActionForward detail(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		try {
			CommConfigPositiontitleForm hosform = (CommConfigPositiontitleForm) form;
			hosform.setItemCode(hosform.getItemCodeHidden());
			commConfigPositiontitleService.updateInit(hosform);
			request.setAttribute("commConfigPositiontitle", hosform);
			return mapping.findForward("detail");
		}
		catch (Exception e) {
			return mapping.findForward("fail");
		}
	}

	public ActionForward update(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		try {
			CommConfigPositiontitleForm hosform = (CommConfigPositiontitleForm) form;			
			hosform.setInputCode(commConfigInputDictService.getInputCode(hosform.getItemName()));
			commConfigPositiontitleService.update(hosform);
			CommConfigPositiontitleForm hosformNew = new CommConfigPositiontitleForm();
			return this.query(mapping, hosformNew, request, response);
		}
		catch (Exception e) {
			return mapping.findForward("fail");
		}
	}

	public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		try {
			CommConfigPositiontitleForm hosform = (CommConfigPositiontitleForm) form;
			commConfigPositiontitleService.delete(hosform);
			CommConfigPositiontitleForm hosformNew = new CommConfigPositiontitleForm();
			return this.query(mapping, hosformNew, request, response);
		}
		catch (Exception e) {
			return mapping.findForward("fail");
		}
	}
}
