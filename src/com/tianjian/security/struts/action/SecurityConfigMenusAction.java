package com.tianjian.security.struts.action;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.tianjian.comm.business.ICommConfigInputDictService;
import com.tianjian.security.bean.SecurityConfigMenus;
import com.tianjian.security.business.ISecurityConfigMenusService;
import com.tianjian.security.struts.form.SecurityConfigMenusForm;
import com.tianjian.security.struts.form.SessionForm;
import com.tianjian.util.ResourcesUtil;
import com.tianjian.util.comm.PageBean;

public class SecurityConfigMenusAction extends BaseAction {

	private ISecurityConfigMenusService securityConfigMenusService;
	private ICommConfigInputDictService commConfigInputDictService;

	public ICommConfigInputDictService getCommConfigInputDictService() {
		return commConfigInputDictService;
	}

	public void setCommConfigInputDictService(ICommConfigInputDictService commConfigInputDictService) {
		this.commConfigInputDictService = commConfigInputDictService;
	}

	public ISecurityConfigMenusService getSecurityConfigMenusService() {
		return securityConfigMenusService;
	}

	public void setSecurityConfigMenusService(ISecurityConfigMenusService securityConfigMenusService) {
		this.securityConfigMenusService = securityConfigMenusService;
	}

	public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		if (checkUser(request, response) == null) {
			return mapping.findForward("noLogin");
		}
		String verbId = request.getParameter("verbId");
		// String inputCode = request.getParameter("inputCode");
		// String flag = request.getParameter("flag");
		if (verbId.equals("add")) {
			return this.add(mapping, form, request, response);
		} else if (verbId.equals("addInit")) {
			return this.addInit(mapping, form, request, response);
		} else if (verbId.equals("addClassId")) {
			return this.addClassId(mapping, form, request, response);
		} else if (verbId.equals("query")) {
			return this.query(mapping, form, request, response);
		} else if (verbId.equals("queryDetail")) {
			return this.query(mapping, form, request, response);
		} else if (verbId.equals("update")) {
			return this.update(mapping, form, request, response);
		} else if (verbId.equals("updateInit")) {
			return this.updateInit(mapping, form, request, response);
		} else if (verbId.equals("updateMenu")) {
			return this.updateMenu(mapping, form, request, response);
		} else if (verbId.equals("detail")) {
			return this.detail(mapping, form, request, response);
		} else if (verbId.equals("delete")) {
			return this.delete(mapping, form, request, response);
		} else if (verbId.equals("getSecurityConfigMenus")) {
			return this.getSecurityConfigMenus(mapping, form, request, response);
		} else if(verbId.equals("init")){
			return this.init(mapping, form, request, response);
		} else if(verbId.equals("initPublicClass")){
			return this.initPublicClass(mapping, form, request, response);
		} else if(verbId.equals("search")){
			return this.search(mapping, form, request, response);
		} else {
			return mapping.findForward("fail");
		}
	}
	
	
	public ActionForward search(ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response){
		try {
			SecurityConfigMenusForm securityConfigMenusForm = (SecurityConfigMenusForm)form;
			String modId = securityConfigMenusForm.getModId();
			String modClassId = securityConfigMenusForm.getModClassId();
			
			HttpSession session = request.getSession(true);
			SessionForm sessionForm = (SessionForm)session.getAttribute("sessionForm");
			String staffType = sessionForm.getStaffType();
			securityConfigMenusForm = this.getSecurityConfigMenusService().getMenu(modId, staffType);
			securityConfigMenusForm.setModClassId(modClassId);
			securityConfigMenusForm.setModId(modId);
			this.getSecurityConfigMenusService().getInit(securityConfigMenusForm);
			request.setAttribute("securityConfigMenusForm", securityConfigMenusForm);
			return mapping.findForward("search");
		} catch(Exception e) {
			return mapping.findForward("fail");
		}		
	}
	
	public ActionForward initPublicClass(ActionMapping mapping,
			ActionForm form,
			HttpServletRequest request,
			HttpServletResponse response){
		try {
			SecurityConfigMenusForm hosform = new SecurityConfigMenusForm();
			this.getSecurityConfigMenusService().getInit(hosform);
			request.setAttribute("securityConfigMenusForm", hosform);
			return mapping.findForward("init");
		} catch(Exception e) {
			return mapping.findForward("fail");
		}
		
	}
	
	public ActionForward add(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		try {
			SecurityConfigMenusForm hosform = (SecurityConfigMenusForm) form;
			SecurityConfigMenus checkData = securityConfigMenusService.checkData(hosform.getData().getId());
			if (checkData != null) {
				this.write2Response(response, "[{flag:'0', msg:'该菜单的id已经存在,请重新输入!'}]");
				return null;
			}
			hosform.getData().setInputCode(commConfigInputDictService.getInputCode(hosform.getData().getMenuDetail()));
			securityConfigMenusService.save(hosform);
			this.write2Response(response, "[{flag:'1', msg:'保存成功！'}]");
			return null;
		}catch (Exception e) {
			e.printStackTrace();
			this.write2Response(response, "[{flag:'0', msg:'保存失败！'}]");
			return null;
		}
	}

	public ActionForward query(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		try {
			String verbId = request.getParameter("verbId");
			SecurityConfigMenusForm hosform = (SecurityConfigMenusForm) form;
			// ////// page start ////////////////////////
			PageBean pb = new PageBean();
			int count;
			int page = 0;
			int recordCount = securityConfigMenusService.getCount(hosform.getId(), hosform.getMenuCode(), hosform.getMenuDetail(), hosform.getInputCode(), hosform.getSerialNo());
			if(recordCount == 0){
				//查询条件有误，未查询到结果
				String str = ResourcesUtil.getValue("conf.security.securityInit", "comm.java.security.warn1", request);
				hosform.setMessage(str);
			}
			pb.setCount(recordCount);
			String pageString = request.getParameter("page");
			//ServletContext application = request.getSession().getServletContext();
			//int pageSize = Integer.parseInt((String)application.getAttribute("EHRPProject_basesecurity.PAGE_SIZE"));
			//int pageSize = BaseSecurityInit.getPageSize("PAGE_SIZE");
			int pageSize = 10;
			if(request.getSession().getAttribute("page_282881f5346450f401346450f4610000")!=null){
				pageSize = Integer.parseInt((String)request.getSession().getAttribute("page_282881f5346450f401346450f4610000"));
			}else{
				ServletContext application = request.getSession().getServletContext();
				pageSize = Integer.parseInt((String)application.getAttribute("security.PAGE_SIZE"));
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
			securityConfigMenusService.getSearch(hosform, count, pageSize);
			securityConfigMenusService.serchInit(hosform);
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
			SecurityConfigMenusForm hosform = (SecurityConfigMenusForm) form;
			securityConfigMenusService.updateInit(hosform);
			request.setAttribute("dataForm", hosform);
			return mapping.findForward("update");
		}
		catch (Exception e) {
			return mapping.findForward("fail");
		}
	}
	
	public ActionForward updateMenu(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		try {
			String menuId = request.getParameter("menuId");
			SecurityConfigMenusForm hosform = (SecurityConfigMenusForm) form;
			hosform.setIdHidden(menuId);
			securityConfigMenusService.updateInit(hosform);
			request.setAttribute("dataForm", hosform);
			return mapping.findForward("update");
		}catch (Exception e) {
			return mapping.findForward("fail");
		}
	}
	public ActionForward addInit(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		try {
			SecurityConfigMenusForm hosform = new SecurityConfigMenusForm();
			String menuId = request.getParameter("menuId");
//			hosform.getData().setSecurityConfigPublicId(parentId);
			hosform.setIdHidden(menuId);
			securityConfigMenusService.addInit(hosform);
			request.setAttribute("dataForm", hosform);
			return mapping.findForward("add");
		}catch (Exception e) {
			return mapping.findForward("fail");
		}
	}

	public ActionForward addClassId(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		try {
			SecurityConfigMenusForm hosform = new SecurityConfigMenusForm();
			String classId = request.getParameter("classId");
//			hosform.getData().setSecurityConfigPublicId(parentId);
			hosform.setModClassId(classId);
			securityConfigMenusService.addClassId(hosform);
			request.setAttribute("dataForm", hosform);
			return mapping.findForward("add");
		}catch (Exception e) {
			return mapping.findForward("fail");
		}
	}
	
	public ActionForward detail(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		try {
			SecurityConfigMenusForm hosform = (SecurityConfigMenusForm) form;
			String menuId = request.getParameter("menuId");
			hosform.setIdHidden(menuId);
			securityConfigMenusService.updateInit(hosform);
			request.setAttribute("dataForm", hosform);
			return mapping.findForward("detail");
		}
		catch (Exception e) {
			return mapping.findForward("fail");
		}
	}

	public ActionForward update(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		try {
			SecurityConfigMenusForm hosform = (SecurityConfigMenusForm) form;
			SecurityConfigMenus checkData = securityConfigMenusService.checkData(hosform.getData().getMenuCode());
			if ((checkData!=null )&&(!hosform.getData().getMenuCode().equals(hosform.getMenuCodeHidden()))) {
				ServletContext application = request.getSession().getServletContext();
				String message = (String)application.getAttribute("EHRPProject_basesecurity.1-000001"); 
				this.write2Response(response, "[{flag:'0', msg:'"+message+"'}]");
				return null;
			}
			hosform.getData().setInputCode(commConfigInputDictService.getInputCode(hosform.getData().getMenuDetail()));
			securityConfigMenusService.update(hosform);
			this.write2Response(response, "[{flag:'1', msg:'保存成功！'}]");
			return null;
		}catch (Exception e) {
			e.printStackTrace();
			this.write2Response(response, "[{flag:'0', msg:'保存失败！'}]");
			return null;
		}
	}

	public ActionForward delete(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		//树状图使用标志，如果是树状图，则返回json，不跳转
		String useForTree = request.getParameter("useForTree");
		try {
			SecurityConfigMenusForm hosform = (SecurityConfigMenusForm) form;
			String menuId = request.getParameter("menuId");
			hosform.setIdHidden(menuId);
			//查询子菜单个数
			int childrenCount = this.getSecurityConfigMenusService().getChildrenCount(hosform.getIdHidden());
			if(childrenCount > 0){
				if(useForTree != null && "1".equals(useForTree.trim())){
					this.write2Response(response, "[{flag:'0', msg:'该菜单下面有"+childrenCount+"个子类菜单，请先删除这些子类菜单!'}]");
					return null;
				}else{
					this.getSecurityConfigMenusService().addInit(hosform);
					hosform.setMessage("该菜单下面有子类菜单，请先删除子类菜单!");
					request.setAttribute("dataForm", hosform);
					return this.initPublicClass(mapping, hosform, request, response);
				}
			}
			securityConfigMenusService.delete(hosform);
			hosform.setMessage("删除成功!");
			if(useForTree != null && "1".equals(useForTree.trim())){
				this.write2Response(response, "[{flag:'1', msg:'删除成功!'}]");
				return null;
			}
			return null;
		}
		catch (Exception e) {
			e.printStackTrace();
			if(useForTree != null && "1".equals(useForTree.trim())){
				this.write2Response(response, "[{flag:'0', msg:'删除失败!'}]");
				return null;
			}else{
				return mapping.findForward("fail");
			}
		}
	}

	public ActionForward getSecurityConfigMenus(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		try {
			String inputValue = request.getParameter("inputCode");
			String iputFlag = request.getParameter("flag"); // 请求检索类型标志 是按照代码检索、拼音码检索、汉字检索
			List list = new ArrayList();
			// ------------根据标示符获取结果list-----1拼音 2代码 3汉字---------------------------------------------------
			list = this.getSecurityConfigMenusService().getAjaxDict(iputFlag, inputValue);
			// 设置请求以及返回数据的编码类型
			request.setCharacterEncoding("UTF-8");
			response.setContentType("text/xml; charset=UTF-8");
			response.setHeader("Cache-Control", "no-cache");
			PrintWriter out = response.getWriter();
			out.println("<response>");
			for (int i = 0; i < list.size(); i++) {
				// ------强制转换生成实体对象-----
				SecurityConfigMenus charbean = (SecurityConfigMenus) list.get(i);
				// -----以下xml标签不要改变--------------------------------------------------------------
				out.println("<ress>");
				out.println("<resInputCode>" + transNullToString(charbean.getInputCode()) + "</resInputCode>");
				out.println("<resItemCode>" + transNullToString(charbean.getMenuCode()) + "</resItemCode>");
				out.println("<resItemName>" + transNullToString(charbean.getMenuDetail()) + "</resItemName>");
				out.println("<resItemId>" + charbean.getId() + "</resItemId>");
				out.println("</ress>");
			}
			out.println("</response>");
			out.close();
			// TODO Auto-generated method stub
			return null;
		}
		catch (Exception e) {
			return mapping.findForward("fail");
		}
	}
	
	/**
	 * 去掉字串中的空格
	 * */
	public String transNullToString(Object obj){
		String temp = "";
		if (obj != null){
			temp = ((String)obj).trim();			
		}
		return temp;
	}
	
	public ActionForward init(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response) {
		try {
			SecurityConfigMenusForm ssform = (SecurityConfigMenusForm) form;
			request.setAttribute("dataForm", ssform);
			return mapping.findForward("query");
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
