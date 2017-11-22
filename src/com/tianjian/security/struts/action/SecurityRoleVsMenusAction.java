package com.tianjian.security.struts.action;

import java.io.PrintWriter;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

import com.tianjian.security.business.ISecurityRoleVsMenusService;

public class SecurityRoleVsMenusAction extends BaseAction {

	private ISecurityRoleVsMenusService service;

	public ISecurityRoleVsMenusService getService() {
		return service;
	}

	public void setService(ISecurityRoleVsMenusService service) {
		this.service = service;
	}

	public ActionForward execute(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {

		if (checkUser(request, response) == null) {
			return mapping.findForward("noLogin");
		}
		String verbId = request.getParameter("verbId");
		if ("init".equals(verbId)) {
			return mapping.findForward("init");
		} else if ("getRoleTreeNodes".equals(verbId)) {
			return this.getRoleTreeNodes(mapping, form, request, response);
		} else if ("getMenuTreeNodes".equals(verbId)) {
			return this.getMenuTreeNodes(mapping, form, request, response);
		} else if ("getMenusBySelectedRole".equals(verbId)) {
			return this.getMenusBySelectedRole(mapping, form, request, response);
		} else if ("save".equals(verbId)) {
			return this.save(mapping, form, request, response);
		} else {
			return mapping.findForward("fail");
		}

	}

	public ActionForward getRoleTreeNodes(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		try {
			String json = service.getRoleTreeNodes();
			this.write2Response(response, json);
		} catch (Exception e) {
		}
		return null;
		
	}
	public ActionForward getMenuTreeNodes(ActionMapping mapping,
			ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		try {
			String json = service.getMenuTreeNodes();
			this.write2Response(response, json);
		} catch (Exception e) {
		}
		return null;

	}
	
	public ActionForward getMenusBySelectedRole(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		try {
			String roleId = request.getParameter("roleId");
			List<String> menuIds = this.service.getMenuIdsByRoleId(roleId);
			if(menuIds != null){
				StringBuilder sb = new StringBuilder("[");
				Iterator<String> ite = menuIds.iterator();
				while(ite.hasNext()){
					sb.append("'").append(ite.next()).append("'");
					if(ite.hasNext()){
						sb.append(",");
					}
				}
				sb.append("]");
				write2Response(response, sb.toString());
			}
		} catch (Exception e) {
		}
		return null;
	}


	private void write2Response(HttpServletResponse response, String str) {
		try {
			response.setContentType("text/xml");
			response.setHeader("Cache-Control", "no-cache");
			response.setCharacterEncoding("UTF-8");
			PrintWriter out = response.getWriter();
			out.print(str);
			out.flush();
			out.close();
		} catch (Exception e) {
		}
	}

	public ActionForward save(ActionMapping mapping, ActionForm form,
			HttpServletRequest request, HttpServletResponse response) {
		try {
			String roleId = request.getParameter("roleId");
			String menuIds = request.getParameter("menuIds");
			service.saveSecurityRoleVsMenus(roleId,  menuIds);
			write2Response(response, "保存成功！");
		} catch (Exception e) {
			write2Response(response, "保存失败！");
		}
		return null;
	}


}
