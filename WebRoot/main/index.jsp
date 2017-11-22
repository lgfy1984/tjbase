<%@ page pageEncoding="UTF-8"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<%@ page import="com.tianjian.security.struts.form.LoginMenuForm"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%
	LoginMenuForm loginMenuForm = (LoginMenuForm) request.getAttribute("loginMenuForm");
	if (loginMenuForm != null) {
		String[] pubIds = loginMenuForm.getPublicId();
		session.setAttribute("pubIds", pubIds);
		String[] picPaths = loginMenuForm.getPicPath();
		session.setAttribute("picPaths", picPaths);
		String[] pubNames = loginMenuForm.getPublicName();
		session.setAttribute("pubNames", pubNames);
		String[] menuIds = loginMenuForm.getMenuId();
		session.setAttribute("menuIds", menuIds);
		String[] parentMenuIds = loginMenuForm.getParentMenuId();
		session.setAttribute("parentMenuIds", parentMenuIds);
		String[] menuNames = loginMenuForm.getMenuName();
		session.setAttribute("menuNames", menuNames);
		String selectedPublicId = loginMenuForm.getSelectedPublicId();
		session.setAttribute("selectedPublicId", selectedPublicId);
		String[] publicReasonNames = loginMenuForm.getPublicReasonName();
		session.setAttribute("publicReasonNames", publicReasonNames);
	}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%
		if (request.getServerPort() == 80) {
		%>
		<base href="http://<%=request.getServerName()%><%=request.getContextPath()%>/" />
		<%
		} else {
		%>
		<base href="http://<%=request.getServerName()%>:<%=request.getServerPort()%><%=request.getContextPath()%>/" />
		<%
		}
		%>
		<title><%=application.getAttribute("security.SYSTEMNAME") %></title>
	</head>
	<FRAMESET id="frame" name="frame" border=0 frameSpacing=0 rows="*" frameBorder=no cols="0,*,0">
	<FRAME name="leftFrame" src="" frameBorder=0 noResize scrolling=no>
	<FRAME name="indexFrame" src="main/main.jsp" scrolling=auto frameBorder=0 noResize>
	<FRAME name="rightFrame" src="" frameBorder=0 noResize scrolling=no>
	</FRAMESET>
	<noframes>
	  
		<body>
			 <bean:message key="main.jsp.index.body" bundle="conf.main.main"/>
		</body>
	</noframes>
</html>
