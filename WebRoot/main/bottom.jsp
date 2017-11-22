<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="com.tianjian.security.struts.form.SessionForm"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%
		if (request.getServerPort() ==80) {
		%>
		<base href="http://<%=request.getServerName()%><%=request.getContextPath()%>/" />
		<%
		} else {
		%>
		<base href="http://<%=request.getServerName()%>:<%=request.getServerPort()%><%=request.getContextPath()%>/" />
		<%http://localhost:9080/TJLocalBaseProject/
		}
		%>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		 
		<title><bean:message  key="main.jsp.bottom.title" bundle="conf.main.main"/></title>
		<link type="text/css" rev="stylesheet" rel="stylesheet" href="main/include/css/secondindex.css" />
	</head>
	<body oncontextmenu="return false" onselectstart="return false" onmouseup="document.selection.empty()" onmousedown="document.selection.empty()" oncopy="document.selection.empty()">
		<div id="full">
			<div id="footer">
				<%=application.getAttribute("security.SYSTEMNAME") %>&nbsp; <% 
				SessionForm sessionFormBottom = (SessionForm)session.getAttribute("sessionForm");
				if(sessionFormBottom!=null&&sessionFormBottom.getVersionUserName()!=null){ %>用户:<%=sessionFormBottom.getVersionUserName()%><%} %>
			</div>
		</div>
	</body>
</html>
