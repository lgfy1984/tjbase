//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_4.1.1/xslt/JavaClass.xsl
package com.tianjian.comm.struts.action;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.tianjian.comm.business.ICommIHEFacilityService;
import com.tianjian.comm.struts.form.CommIHEFacilityForm;
import com.tianjian.security.struts.action.BaseAction;
import com.tianjian.util.comm.PageBean;

public class CommIHEFacilityAction extends BaseAction {

	private ICommIHEFacilityService commIHEFacilityServiceImpl;
	

	public ICommIHEFacilityService getCommIHEFacilityServiceImpl() {
		return commIHEFacilityServiceImpl;
	}

	public void setCommIHEFacilityServiceImpl(
			ICommIHEFacilityService commIHEFacilityServiceImpl) {
		this.commIHEFacilityServiceImpl = commIHEFacilityServiceImpl;
	}

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		String verbId = request.getParameter("verbId");
		if (verbId.equals("add")) {
			return this.add(mapping, form, request, response);
		} else if (verbId.equals("addInit")) {
			return this.addInit(mapping, form, request, response);
		} else if (verbId.equals("query")) {
			return this.query(mapping, form, request, response);
		} else if (verbId.equals("update")) {
			return this.update(mapping, form, request, response);
		} else if (verbId.equals("updateInit")) {
			return this.updateInit(mapping, form, request, response);
		} else if (verbId.equals("detail")) {
			return this.detail(mapping, form, request, response);
		} else if (verbId.equals("delete")) {
			return this.delete(mapping, form, request, response);
		} else {
			return mapping.findForward("fail");
		}
	}

	public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		try {
			CommIHEFacilityForm hosform = (CommIHEFacilityForm) form;
//			CommConfigAbo checkData = commConfigAboService.checkData(hosform.getItemCode());
//			if (checkData != null) {
//				//String message = BaseCommMessage.getMsg("0-000001");
//				ServletContext application = request.getSession().getServletContext();
//				String message = (String)application.getAttribute("comm.0-000001");
//				hosform.setMessage(message);
//				commConfigAboService.addInit(hosform);
//				request.setAttribute("commConfigAbo", hosform);
//				return mapping.findForward("add");
//			}
//			hosform.setInputCode(commConfigInputDictService.getInputCode(hosform.getItemName()));
			this.commIHEFacilityServiceImpl.save(hosform);
			CommIHEFacilityForm hosformNew = new CommIHEFacilityForm();
			return this.query(mapping, hosformNew, request, response);
		}
		catch (Exception e) {
			return mapping.findForward("fail");
		}
	}

	public ActionForward query(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		try {
			CommIHEFacilityForm hosform = (CommIHEFacilityForm) form;
			// ////// page start ////////////////////////
			PageBean pb = new PageBean();
			int count;
			int page = 0;
			int recordCount = this.commIHEFacilityServiceImpl.getCount(hosform.getFacilityUniversalId(), hosform.getFacilityUniversalIdType(), hosform.getFacilityNamespaceId(), hosform.getSeqNo());
			pb.setCount(recordCount);
			String pageString = request.getParameter("page");
			//int pageSize = BaseCommInit.getPageSize("PAGE_SIZE");
			//ServletContext application = request.getSession().getServletContext();
			//int pageSize = Integer.parseInt((String)application.getAttribute("EHRPProject_basecomm.PAGE_SIZE"));
			int pageSize = 10;
			if(request.getSession().getAttribute("page_2828810b369604120136960412480000")!=null){
				pageSize = Integer.parseInt((String)request.getSession().getAttribute("page_2828810b369604120136960412480000"));
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
			this.commIHEFacilityServiceImpl.getSearch(hosform, count, pageSize);
			this.commIHEFacilityServiceImpl.serchInit(hosform);
			request.setAttribute("data", hosform);
			return mapping.findForward("query");
		}
		catch (Exception e) {
			e.printStackTrace();
			return mapping.findForward("fail");
		}
	}

	public ActionForward updateInit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		try {
			CommIHEFacilityForm hosform = (CommIHEFacilityForm) form;
			hosform.setId(hosform.getId());
			this.commIHEFacilityServiceImpl.updateInit(hosform);
			request.setAttribute("data", hosform);
			return mapping.findForward("update");
		}
		catch (Exception e) {
			return mapping.findForward("fail");
		}
	}

	public ActionForward addInit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		try {
			CommIHEFacilityForm hosform = new CommIHEFacilityForm();
			this.commIHEFacilityServiceImpl.addInit(hosform);
			request.setAttribute("data", hosform);
			return mapping.findForward("add");
		}
		catch (Exception e) {
			return mapping.findForward("fail");
		}
	}

	public ActionForward detail(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		try {
			CommIHEFacilityForm hosform = (CommIHEFacilityForm) form;
			this.commIHEFacilityServiceImpl.updateInit(hosform);
			request.setAttribute("data", hosform);
			return mapping.findForward("detail");
		}
		catch (Exception e) {
			return mapping.findForward("fail");
		}
	}

	public ActionForward update(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		try {
			CommIHEFacilityForm hosform = (CommIHEFacilityForm) form;
			//hosform.setInputCode(commConfigInputDictService.getInputCode(hosform.getItemName()));
			this.commIHEFacilityServiceImpl.update(hosform);
			CommIHEFacilityForm hosformNew = new CommIHEFacilityForm();
			return this.query(mapping, hosformNew, request, response);
		}
		catch (Exception e) {
			return mapping.findForward("fail");
		}
	}

	public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		try {
			CommIHEFacilityForm hosform = (CommIHEFacilityForm) form;
			this.commIHEFacilityServiceImpl.delete(hosform);
			return this.query(mapping, hosform, request, response);
		}
		catch (Exception e) {
			return mapping.findForward("fail");
		}
	}

	
}
