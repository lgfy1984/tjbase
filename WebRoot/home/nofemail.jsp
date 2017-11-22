<%@ page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="bean" uri="/WEB-INF/struts-bean.tld"%>
<%@ taglib prefix="html" uri="/WEB-INF/struts-html.tld"%>
<%@ taglib prefix="bean" uri="/WEB-INF/struts-bean.tld"%>
<%@ taglib prefix="logic" uri="/WEB-INF/struts-logic.tld"%>
<html>
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
		<title>error</title>
	</head>
	<body style="text-align: center;">
		<table width="300" height="200" border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="#BFBFBF" style="font-size: 12px;">
			<tr>
				<td width="265" height="25" style="color: #000000; padding: 0px 0px 0px 0px; background: url(include/images/bg.gif) repeat-x;">
					<span style="background: url(include/images/error_title.png ) no-repeat 0 4px; padding-left: 20px; margin-left: 5px; display: block; height: 25px; line-height: 25px;"><bean:message key="home.jsp.nologin.td1" bundle="conf.home.Message"/></span>
				</td>
			</tr>
			<tr>
				<td height="145" align="center" bgcolor="#ffffff">
					<img src="include/images/pic.gif" />
				</td>
			</tr>
			<tr>
				<td height="30" align="center" bgcolor="#F7F7F7">
					<bean:message key="home.jsp.nofemail.table2" bundle="conf.home.Message"/>
				</td>
			</tr>
		</table>
	</body>
</html>