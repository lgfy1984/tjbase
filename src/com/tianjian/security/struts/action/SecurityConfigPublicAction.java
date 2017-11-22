package com.tianjian.security.struts.action;


import java.io.PrintWriter;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.tianjian.comm.business.ICommConfigInputDictService;
import com.tianjian.security.bean.SecurityConfigPublic;
import com.tianjian.security.business.ISecurityConfigPublicService;
import com.tianjian.security.struts.form.SecurityConfigMenusForm;
import com.tianjian.security.struts.form.SecurityConfigPublicForm;
import com.tianjian.security.struts.form.SessionForm;
import com.tianjian.util.ResourcesUtil;
import com.tianjian.util.comm.PageBean;

public class SecurityConfigPublicAction extends BaseAction {

	private ISecurityConfigPublicService securityConfigPublicService;
	private ICommConfigInputDictService commConfigInputDictService;

	public ICommConfigInputDictService getCommConfigInputDictService() {
		return commConfigInputDictService;
	}

	public void setCommConfigInputDictService(ICommConfigInputDictService commConfigInputDictService) {
		this.commConfigInputDictService = commConfigInputDictService;
	}
	public ISecurityConfigPublicService getSecurityConfigPublicService() {
		return securityConfigPublicService;
	}

	public void setSecurityConfigPublicService(ISecurityConfigPublicService securityConfigPublicService) {
		this.securityConfigPublicService = securityConfigPublicService;
	}

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		if (checkUser(request, response) == null) {
			return mapping.findForward("noLogin");
		}
		String verbId = request.getParameter("verbId");
		if ("add".equals(verbId)) {
			return this.add(mapping, form, request, response);
		} else if ("addInit".equals(verbId)) {
			return this.addInit(mapping, form, request, response);
		} else if ("query".equals(verbId)) {
			return this.query(mapping, form, request, response);
		} else if ("queryDetail".equals(verbId)) {
			return this.query(mapping, form, request, response);
		} else if ("update".equals(verbId)) {
			return this.update(mapping, form, request, response);
		} else if ("updateInit".equals(verbId)) {
			return this.updateInit(mapping, form, request, response);
		} else if("initPublicClass".equals(verbId)){
			return this.initPublicClass(mapping, form, request, response);
		} else if("search".equals(verbId)){
			return this.search(mapping, form, request, response);
		} else if ("detail".equals(verbId)) {
			return this.detail(mapping, form, request, response);
		} else if ("delete".equals(verbId)) {
			return this.delete(mapping, form, request, response);
		} else if ("init".equals(verbId)){
			return this.init(mapping, form, request, response);
		}else if("initDetail".equals(verbId)){
			return this.init(mapping, form, request, response);	
		} else {
			return mapping.findForward("fail");
		}
	}
	
	public ActionForward search(ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response){
		try {
			SecurityConfigPublicForm hosform = (SecurityConfigPublicForm)form;
			String modClassId = request.getParameter("classId");
			hosform.setSecurityPublicClassId(modClassId);
			HttpSession session = request.getSession(true);
			SessionForm sessionForm = (SessionForm)session.getAttribute("sessionForm");
			String staffType = sessionForm.getStaffType();
			this.getSecurityConfigPublicService().getPublic(hosform, modClassId, staffType);
			request.setAttribute("data", hosform);
			return mapping.findForward("search");
		} catch(Exception e) {
			return mapping.findForward("fail");
		}		
	}
	
	public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		try {
			SecurityConfigPublicForm hosform = (SecurityConfigPublicForm) form;
			
			SecurityConfigPublic checkData = securityConfigPublicService.checkData(hosform.getData().getModCode());
			if (checkData != null) {
				hosform.setMessage(ResourcesUtil.getValue("conf.security.securityInit", "security.java.commom.warn10", request));
				this.write2Response(response, "[{flag:'0', msg:'"+hosform.getMessage()+"'}]");
				return null;
			}
			hosform.getData().setInputCode(commConfigInputDictService.getInputCode(hosform.getData().getReason()));
			securityConfigPublicService.save(hosform);
			this.write2Response(response, "[{flag:'1', msg:'保存成功！'}]");
			return null;
		}
		catch (Exception e) {
			e.printStackTrace();
			this.write2Response(response, "[{flag:'0', msg:'保存失败！'}]");
			return null;
		}
	}

	public ActionForward query(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		try {
			String verbId = request.getParameter("verbId");
			SecurityConfigPublicForm hosform = (SecurityConfigPublicForm) form;
			// ////// page start ////////////////////////
			PageBean pb = new PageBean();
			int count;
			int page = 0;
			int recordCount = securityConfigPublicService.getCount(hosform.getId(),hosform.getModCode(), hosform.getReasonValue(), hosform.getInputCode(), hosform.getSerialNo(),hosform.getParentId());
			if(recordCount == 0){
				//查询条件有误，未查询到结果
				String str = ResourcesUtil.getValue("conf.security.securityInit", "comm.java.security.warn1", request);
				hosform.setMessage(str);
			}
			pb.setCount(recordCount);
			String pageString = request.getParameter("page");
//			ServletContext application = request.getSession().getServletContext();
//			int pageSize = Integer.parseInt((String)application.getAttribute("DEXCSProject_basesecurity.PAGE_SIZE"));

	        //ServletContext application = request.getSession().getServletContext();
	        //int pageSize = Integer.parseInt((String)application.getAttribute("EHRPProject_basesecurity.PAGE_SIZE"));
			int pageSize = 10;
			if(request.getSession().getAttribute("page_282881f5346450c601346450c6b30000")!=null){
				pageSize = Integer.parseInt((String)request.getSession().getAttribute("page_282881f5346450c601346450c6b30000"));
			}else{
				ServletContext application = request.getSession().getServletContext();
				pageSize = Integer.parseInt((String)application.getAttribute("security.PAGE_SIZE"));
			}
			//int pageSize = BaseSecurityInit.getPageSize("PAGE_SIZE");
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
			securityConfigPublicService.getSearch(hosform, count, pageSize);
			securityConfigPublicService.serchInit(hosform);
			request.setAttribute("dataForm", hosform);
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
			SecurityConfigPublicForm hosform = (SecurityConfigPublicForm) form;
			securityConfigPublicService.updateInit(hosform);
			request.setAttribute("dataForm", hosform);
			return mapping.findForward("update");
		}
		catch (Exception e) {
			e.printStackTrace();
			return mapping.findForward("fail");
		}
	}

	public ActionForward addInit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		try {
			SecurityConfigPublicForm hosform = new SecurityConfigPublicForm();
			String classId = request.getParameter("classId");
			hosform.setSecurityPublicClassId(classId);
			securityConfigPublicService.addInit(hosform);
			request.setAttribute("dataForm", hosform);
			return mapping.findForward("add");
		}
		catch (Exception e) {
			e.printStackTrace();
			return mapping.findForward("fail");
		}
	}

	public ActionForward detail(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		try {
			SecurityConfigPublicForm hosform = (SecurityConfigPublicForm) form;
			securityConfigPublicService.showInit(hosform);
			request.setAttribute("dataForm", hosform);
			return mapping.findForward("detail");
		}
		catch (Exception e) {
			e.printStackTrace();
			return mapping.findForward("fail");
		}
	}

	public ActionForward update(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		try {
			SecurityConfigPublicForm hosform = (SecurityConfigPublicForm) form;
			hosform.getData().setInputCode(commConfigInputDictService.getInputCode(hosform.getData().getReason()));
			securityConfigPublicService.update(hosform);
			this.write2Response(response, "[{flag:'1', msg:'保存成功！'}]");
			return null;
		}
		catch (Exception e) {
			e.printStackTrace();
			this.write2Response(response, "[{flag:'0', msg:'保存失败！'}]");
			return null;
		}
	}

	public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		String useForTree = request.getParameter("useForTree");
		try {
			SecurityConfigPublicForm hosform = (SecurityConfigPublicForm) form;
			//获取该模块下的菜单数
			int menusCount = this.securityConfigPublicService.getMenusCountByPublicId(hosform.getIdHidden());
			if(menusCount > 0){
				if(useForTree != null && "1".equals(useForTree.trim())){
					this.write2Response(response, "[{flag:'0', msg:'该模块下面有"+menusCount+"个菜单，请先删除这些菜单!'}]");
					return null;
				}else{
					hosform.setMessage("该模块下面有"+menusCount+"个菜单，请先删除这些菜单!");
					return this.query(mapping, hosform, request, response);
				}
			}else{
				securityConfigPublicService.delete(hosform);
				if(useForTree != null && "1".equals(useForTree.trim())){
					this.write2Response(response, "[{flag:'1', msg:'删除成功!'}]");
					return null;
				}else{
					return this.query(mapping, hosform, request, response);
				}
			}
		}
		catch (Exception e) {
			e.printStackTrace();
			if(useForTree != null && "1".equals(useForTree.trim())){
				this.write2Response(response, "[{flag:'0', msg:'删除失败!'}]");
				return null;
			}
			return mapping.findForward("fail");
		}
	}
	
	public ActionForward initPublicClass(ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response){
		try {
			SecurityConfigPublicForm hosform = new SecurityConfigPublicForm();
			this.getSecurityConfigPublicService().getInit(hosform);
			request.setAttribute("data", hosform);
			return mapping.findForward("init");
		} catch(Exception e) {
			return mapping.findForward("fail");
		}
		
	}
	
	public ActionForward init(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		try {
			String verbId = request.getParameter("verbId");
			SecurityConfigPublicForm ssform = (SecurityConfigPublicForm) form;
			securityConfigPublicService.serchInit(ssform);
			request.setAttribute("dataForm", ssform);
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
	
	private void write2Response(HttpServletResponse response, String str){
		try {
			response.setContentType("application/json");
			response.setCharacterEncoding("UTF-8");
			PrintWriter out = response.getWriter();
			out.print(str);
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}		
	}
}
