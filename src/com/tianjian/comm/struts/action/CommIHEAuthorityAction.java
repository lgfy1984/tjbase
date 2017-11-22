//Created by MyEclipse Struts
// XSL source (default): platform:/plugin/com.genuitec.eclipse.cross.easystruts.eclipse_4.1.1/xslt/JavaClass.xsl
package com.tianjian.comm.struts.action;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.tianjian.comm.bean.CommIHEAuthority;
import com.tianjian.comm.business.ICommIHEAuthorityService;
import com.tianjian.comm.struts.form.CommIHEAuthorityForm;
import com.tianjian.security.struts.action.BaseAction;
import com.tianjian.util.comm.PageBean;

public class CommIHEAuthorityAction extends BaseAction {

	private ICommIHEAuthorityService  commIHEAuthorityServiceImpl;


	public ICommIHEAuthorityService getCommIHEAuthorityServiceImpl() {
		return commIHEAuthorityServiceImpl;
	}

	public void setCommIHEAuthorityServiceImpl(
			ICommIHEAuthorityService commIHEAuthorityServiceImpl) {
		this.commIHEAuthorityServiceImpl = commIHEAuthorityServiceImpl;
	}

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		String verbId = request.getParameter("verbId");
		if (verbId.equals("add")) {
			return this.add(mapping, form, request, response);
		} else if (verbId.equals("addInit")) {
			return this.addInit(mapping, form, request, response);
		} else if (verbId.equals("query")) {
			return this.query(mapping, form, request, response);
		} else if(verbId.equals("queryDetail")){
			return this.query(mapping, form, request, response);
		} else if (verbId.equals("update")) {
			return this.update(mapping, form, request, response);
		} else if (verbId.equals("updateInit")) {
			return this.updateInit(mapping, form, request, response);
		} else if (verbId.equals("init")) {
			return this.init(mapping, form, request, response);
		} else if (verbId.equals("initDetail")) {
			return this.init(mapping, form, request, response);
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
			CommIHEAuthorityForm hosform = (CommIHEAuthorityForm) form;
			CommIHEAuthority checkData = this.commIHEAuthorityServiceImpl.checkData(hosform.getId());
			if (checkData != null) {
				//String message = BaseCommMessage.getMsg("0-000001");
				hosform.setMessage("该分配权限编码已经存在,请重新输入! ");
				this.commIHEAuthorityServiceImpl.addInit(hosform);
				request.setAttribute("data", hosform);
				return mapping.findForward("add");
			}
			//hosform.setInputCode(commConfigInputDictService.getInputCode(hosform.getItemName()));
			this.commIHEAuthorityServiceImpl.save(hosform);
			CommIHEAuthorityForm hosformNew = new CommIHEAuthorityForm();
			return this.query(mapping, hosformNew, request, response);
		}
		catch (Exception e) {
			return mapping.findForward("fail");
		}
	}
	
	public ActionForward init(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		try {
			String verbId = request.getParameter("verbId");
			CommIHEAuthorityForm hosform = (CommIHEAuthorityForm) form;
			request.setAttribute("data", hosform);
			if(verbId.equals("initDetail")){
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
	
	public ActionForward query(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		try {
			String verbId = request.getParameter("verbId");
			CommIHEAuthorityForm hosform = (CommIHEAuthorityForm) form;
			// ////// page start ////////////////////////
			PageBean pb = new PageBean();
			int count;
			int page = 0;
			int recordCount = this.commIHEAuthorityServiceImpl.getCount(hosform.getUniversalId(), hosform.getUniversalIdType(), hosform.getNamespaceId(), hosform.getSeqNo());
			pb.setCount(recordCount);
			String pageString = request.getParameter("page");
			//int pageSize = BaseCommInit.getPageSize("PAGE_SIZE");
			//ServletContext application = request.getSession().getServletContext();
			//int pageSize = Integer.parseInt((String)application.getAttribute("EHRPProject_basecomm.PAGE_SIZE"));
			int pageSize = 10;
			if(request.getSession().getAttribute("page_2828810b369603cc01369603cc480000")!=null){
				pageSize = Integer.parseInt((String)request.getSession().getAttribute("page_2828810b369603cc01369603cc480000"));
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
			this.commIHEAuthorityServiceImpl.getSearch(hosform, count, pageSize);
			this.commIHEAuthorityServiceImpl.serchInit(hosform);
			request.setAttribute("data", hosform);
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
			CommIHEAuthorityForm hosform = (CommIHEAuthorityForm) form;
			hosform.setId(hosform.getId());
			this.commIHEAuthorityServiceImpl.updateInit(hosform);
			request.setAttribute("data", hosform);
			return mapping.findForward("update");
		}
		catch (Exception e) {
			return mapping.findForward("fail");
		}
	}

	public ActionForward addInit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		try {
			CommIHEAuthorityForm hosform = new CommIHEAuthorityForm();
			this.commIHEAuthorityServiceImpl.addInit(hosform);
			request.setAttribute("data", hosform);
			return mapping.findForward("add");
		}
		catch (Exception e) {
			return mapping.findForward("fail");
		}
	}

	public ActionForward detail(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		try {
			CommIHEAuthorityForm hosform = (CommIHEAuthorityForm) form;
			this.commIHEAuthorityServiceImpl.updateInit(hosform);
			request.setAttribute("data", hosform);
			return mapping.findForward("detail");
		}
		catch (Exception e) {
			return mapping.findForward("fail");
		}
	}

	public ActionForward update(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		try {
			CommIHEAuthorityForm hosform = (CommIHEAuthorityForm) form;
			this.commIHEAuthorityServiceImpl.update(hosform);
			CommIHEAuthorityForm hosformNew = new CommIHEAuthorityForm();
			return this.query(mapping, hosformNew, request, response);
		}catch (Exception e) {
			e.printStackTrace();
			return mapping.findForward("fail");
		}
	}

	public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		try {
			CommIHEAuthorityForm hosform = (CommIHEAuthorityForm) form;
			this.commIHEAuthorityServiceImpl.delete(hosform);
			return this.query(mapping, hosform, request, response);
		}
		catch (Exception e) {
			return mapping.findForward("fail");
		}
	}

	
}
