package com.tianjian.security.struts.action;

import com.tianjian.security.bean.SecurityConfigMenus;
import com.tianjian.security.bean.SecurityConfigPublic;
import com.tianjian.security.business.ILoginSecondService;
import com.tianjian.security.struts.form.LoginMenuForm;
import com.tianjian.security.struts.form.LoginSecondForm;
import com.tianjian.security.struts.form.SessionForm;
import com.tianjian.util.ResourcesUtil;
import com.tianjian.util.comm.Encrypt;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.apache.commons.lang.StringUtils;
import org.apache.struts.action.ActionForm;
import org.apache.struts.action.ActionForward;
import org.apache.struts.action.ActionMapping;

public class LoginSecondAction extends BaseAction
{
  private ILoginSecondService service;

  public ILoginSecondService getService()
  {
    return this.service;
  }

  public void setService(ILoginSecondService service)
  { 
    this.service = service;
  }

  public ActionForward execute(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {
    String verbId = request.getParameter("verbId");

    if (verbId.equals("login"))
      return login(mapping, form, request, response);
    if (verbId.equals("getPublic"))
    	return getPublic(mapping, form, request, response);
    if (verbId.equals("mod"))
      return getMenu(mapping, form, request, response);
    if (verbId.equals("getMenuByParent"))
      return getMenuByParent(mapping, form, request, response);
    if (verbId.equals("findMenuByPublicId")) {
      return findMenuByPublicId(mapping, form, request, response);
    }
    return mapping.findForward("fail");
  }

  public ActionForward login(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {
    try
    {
      LoginSecondForm loginSecondForm = (LoginSecondForm)form;
      LoginMenuForm loginMenuForm = new LoginMenuForm();
      SessionForm sessionForm = new SessionForm();
      HttpSession session = request.getSession(true);
      sessionForm = (SessionForm)session.getAttribute("sessionForm");
      sessionForm.setSelectedPublicClassId(loginSecondForm.getPublicClassId());

      String publicClassParentId = getService().findByPublicClassId(loginSecondForm.getPublicClassId());

      if ((publicClassParentId != null) && (publicClassParentId.length() > 0))
      {
        getService().getEndPublicClass(sessionForm, publicClassParentId, sessionForm.getStaffId(), request);
      }
      else {
        getService().findByPublicClass(sessionForm, loginSecondForm.getPublicClassId());
      }

      this.service.getLoginSecondSessionForm(sessionForm);

      loginMenuForm.setSelectedPublicClassId(loginSecondForm.getPublicClassId());
      loginMenuForm.setStaffId(sessionForm.getStaffId());
      loginMenuForm.setRolesId(sessionForm.getStaffRoles());

      List ls = this.service.getPublic(loginMenuForm);

      StringBuilder sb = new StringBuilder();
      for (int i = 0; i < ls.size(); i++) {
        sb.append("{text:'").append(transNullToString(((Object[])ls.get(i))[1]))
          .append("',id:'").append(transNullToString(((Object[])ls.get(i))[0]))
          .append("', handler:function(){if(this.menu != null) return ;getMenuTree(url3,{'publicId':'" + transNullToString(((Object[])ls.get(i))[0]) + "'}, this);}},");
      }

      if (StringUtils.isNotBlank(sb.toString())) sb.deleteCharAt(sb.length() - 1);

      request.setAttribute("menu", sb.toString());

      this.service.getPublicAndMenu(loginMenuForm);

      if ((loginMenuForm.getMenuId() != null) && (loginMenuForm.getMenuId().length > 0)) {
        request.setAttribute("loginMenuForm", loginMenuForm);
        request.setAttribute("loginSecondForm", loginSecondForm);
        return mapping.findForward("ok");
      }

      loginMenuForm.setMessage(ResourcesUtil.getValue("conf.security.securityInit", "security.java.commom.warn", request));
      request.setAttribute("loginMenuForm", loginMenuForm);
      return mapping.findForward("init");
    }
    catch (Exception e) {
      e.printStackTrace();
    }return mapping.findForward("fail");
  }

  private ActionForward getPublic(ActionMapping mapping, ActionForm form, HttpServletRequest request,
			HttpServletResponse response) {
		try{
			LoginSecondForm loginSecondForm = (LoginSecondForm)form;
			 LoginMenuForm loginMenuForm = new LoginMenuForm();
			 SessionForm sessionForm = new SessionForm();
			 HttpSession session = request.getSession(true);
			 sessionForm = (SessionForm)session.getAttribute("sessionForm");
			 if(sessionForm == null){
				 return mapping.findForward("noLogin");
			 }
			 String staffEncryptStr = "";
			  if(sessionForm != null){
				  Encrypt encrypt = new Encrypt();
				  staffEncryptStr = encrypt.encryptString(sessionForm.getStaffId());
			  }
			 sessionForm.setSelectedPublicClassId(loginSecondForm.getPublicClassId());
			 loginMenuForm.setSelectedPublicClassId(loginSecondForm.getPublicClassId());
			 loginMenuForm.setStaffId(sessionForm.getStaffId());
			 loginMenuForm.setRolesId(sessionForm.getStaffRoles());
			
			 List<?> ls = this.service.getPublic(loginMenuForm);
			
			 StringBuilder sb = new StringBuilder("[");
			 for (Iterator<?> it = ls.iterator(); it.hasNext(); ) {
				 SecurityConfigPublic pub = (SecurityConfigPublic) it.next();
				 sb.append("{text:'").append(transNullToString(pub.getReason()))
				 	.append("',id:'").append(transNullToString(pub.getId()));
				 if(StringUtils.isNotBlank(pub.getPublicUrl())){
					 if(StringUtils.isNotBlank(pub.getPublicTarget())){
						 sb.append("', handler:function(){window.open('").append(pub.getPublicUrl()).append("')}");
					 }else{
						 sb.append("', handler:function(){addPage(this, '").append(pub.getId())
						  	.append("', '").append(pub.getReason())
						  	.append("','").append(handMenuUrl(pub.getPublicUrl(), staffEncryptStr))
						  	.append("');}");
					 }
				 }else{
					 sb.append("', handler:function(){if(this.menu != null) return ;getMenuTree(url3,{'publicId':'").append(transNullToString(pub.getId())).append("'}, this);}");
				 }
				 sb.append("},");
			 }
			 if (sb.length() > 0 && ',' == sb.charAt(sb.length()-1)){
			  	sb.deleteCharAt(sb.length() - 1);
			 }
			 sb.append("]");
	     	response.setContentType("text/html");
			response.setCharacterEncoding("UTF-8");
			PrintWriter out = response.getWriter();
			out.print(sb.toString());
			out.flush();
			out.close();
		}catch (Exception e) {
			e.printStackTrace();
		}
	     return null;
	}
  
  public ActionForward getMenu(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {
    try
    {
      LoginMenuForm loginMenuForm = new LoginMenuForm();

      String pubId = request.getParameter("pubId");
      loginMenuForm.setSelectedPublicId(pubId);
      HttpSession session = request.getSession(true);
      SessionForm sessionForm = (SessionForm)session.getAttribute("sessionForm");
      sessionForm.setSelectedPublicId(pubId);
      String[] userRoles = sessionForm.getStaffRoles();
      loginMenuForm.setRolesId(userRoles);
      loginMenuForm.setStaffId(sessionForm.getStaffId());
      this.service.getMenuDetail(pubId, userRoles, loginMenuForm);
      request.setAttribute("loginMenuForm", loginMenuForm);
      return mapping.findForward("detail");
    } catch (Exception e) {
      e.printStackTrace();
    }return mapping.findForward("fail");
  }

  public ActionForward getMenuByParent(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {
    try
    {
      String menuId = request.getParameter("menuId");

      HttpSession session = request.getSession(true);
      SessionForm sessionForm = (SessionForm)session.getAttribute("sessionForm");

      String[] userRoles = sessionForm.getStaffRoles();

      SecurityConfigMenus pmenu = this.service.findById(menuId);
      String pmenuDetail = pmenu.getMenuDetail();
      request.setAttribute("pmenuDetail", pmenuDetail);

      List ls = this.service.findByParentId(menuId, userRoles);
      request.setAttribute("menus", ls);
      return mapping.findForward("menuontheright");
    } catch (Exception e) {
      e.printStackTrace();
    }return mapping.findForward("fail");
  }

  public ActionForward findMenuByPublicId(ActionMapping mapping, ActionForm form, HttpServletRequest request, HttpServletResponse response)
  {
	  try{
		  String publicId = request.getParameter("publicId");
		  SessionForm sessionForm = new SessionForm();
		  HttpSession session = request.getSession(true);
		  sessionForm = (SessionForm)session.getAttribute("sessionForm");
		  List<?> menuList = this.service.getMenusByPubId(sessionForm, publicId);
		  
		  String staffEncryptStr = "";
		  if(sessionForm != null){
			  Encrypt encrypt = new Encrypt();
			  staffEncryptStr = encrypt.encryptString(sessionForm.getStaffId());
		  }
		  StringBuilder json = new StringBuilder("{items:[");
		  if(menuList != null){
			  //一级菜单的list
			  List<SecurityConfigMenus> ls_1 = new ArrayList<SecurityConfigMenus>();
			  //key=父菜单id， value=子菜单的list
			  Map<String, List<SecurityConfigMenus>> map = new HashMap<String, List<SecurityConfigMenus>>();
			  for(Iterator<?> it = menuList.iterator(); it.hasNext(); ){
				  SecurityConfigMenus menu = (SecurityConfigMenus) it.next();
				  String parentId = menu.getParentId();
				  if(StringUtils.isBlank(parentId)){//如果没有父菜单id，则是一级菜单
					  ls_1.add(menu);
				  }else{
					  List<SecurityConfigMenus> childList = map.get(parentId);
					  if(childList == null){
						  childList = new ArrayList<SecurityConfigMenus>();
						  map.put(parentId, childList);
					  }
					  childList.add(menu);
				  }
			  }
			  //迭代获取json串，从顶层菜单开始
			  json.append(createMenuJson(ls_1, map, staffEncryptStr));
		  }
		  json.append("]}");
		  
	      response.setContentType("text/xml;charset=utf-8");
	      PrintWriter out = response.getWriter();
	      out.println(json.toString());
	      out.close();
	  }catch (Exception e) {
		  e.printStackTrace();
	  }
	  return null;
  }
  
  //迭代获取json串
  private StringBuilder createMenuJson(List<SecurityConfigMenus> ls, Map<String, List<SecurityConfigMenus>> map, String staffEncryptStr) {
	  StringBuilder sb = new StringBuilder();
	  if(ls != null){
		  for(SecurityConfigMenus menu : ls){
			  sb.append("{text:'").append(menu.getMenuDetail()).append("'");
			  List<SecurityConfigMenus> childList = map.get(menu.getId());
			  if(childList != null && childList.size() > 0){
				  //迭代直到没有子菜单
				  sb.append(", menu:{items:[")
				  	.append(this.createMenuJson(childList, map, staffEncryptStr))
				  	.append("]}");
				  map.remove(menu.getId());
			  }else{
				  sb.append(", handler:function(){addPage(this, '").append(menu.getId())
				  	.append("', '").append(menu.getMenuDetail())
				  	.append("','").append(handMenuUrl(menu.getMenuUrl(), staffEncryptStr))
				  	.append("');}");
			  }
			  sb.append("},");
		  }
		  if(sb.charAt(sb.length() - 1) == ','){
			  sb.deleteCharAt(sb.length() - 1);
		  }
	  }
	  return sb;
  }

  private String transNullToString(Object obj) {
    String temp = "";
    if (obj != null) {
      temp = String.valueOf(obj);
    }
    return temp.trim();
  }

  private String handMenuUrl(String url, String staffEncryptStr)
  {
    url = transNullToString(url);
    if (url.trim().length() <= 0)
    {
      url = "main/default.jsp";
    }
    url = this.service.replaceUrl(url, "?", staffEncryptStr);
    return url;
  }

}