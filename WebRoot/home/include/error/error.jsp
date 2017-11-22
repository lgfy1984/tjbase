<%@ page contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="bean" uri="/WEB-INF/struts-bean.tld"%>
<%@ taglib prefix="html" uri="/WEB-INF/struts-html.tld"%>
<%@ taglib prefix="bean" uri="/WEB-INF/struts-bean.tld"%>
<%@ taglib prefix="logic" uri="/WEB-INF/struts-logic.tld"%>
<%if(request.getServerPort() == 80) {%>
  <base href="http://<%=request.getServerName()%><%=request.getContextPath()%>/">
  <%} else {%>
  <base href="http://<%=request.getServerName()%>:<%=request.getServerPort()%><%=request.getContextPath()%>/">
<%}%>
<html>
<head>
<title>
error
</title>
</head>
<body>
<table width="500" height="300" border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="#6595d6"  style="font-size:12px;">
  <tr>
    <td width="35" height="30" align="right"><img src="home/include/images/115.gif" width="16" height="16" /></td>
    <td width="465" height="30" style="color:#FFFFFF;"><bean:message key="home.jsp.include.error.error.td1" bundle="conf.home.Message"/>
</td>
  </tr>
  <tr>
    <td height="200" colspan="2" align="center" bgcolor="#ffffff"><img src="home/include/images/error.gif" width="85" height="85" />
	</td>
  </tr>
  <tr>
    <td height="70" align="center" colspan="2" bgcolor="#cedff1">${reason}<a href="home/login.jsp" onclick="parent.history.back(); return false;"><bean:message key="home.jsp.include.error.error.td2" bundle="conf.home.Message"/></a></td>
  </tr>
  
</table>
</body>
</html>
