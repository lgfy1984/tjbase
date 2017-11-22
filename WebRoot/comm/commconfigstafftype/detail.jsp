<%@ page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="bean" uri="/WEB-INF/struts-bean.tld"%>
<jsp:useBean id="commConfigStafftype" scope="request" class="com.tianjian.comm.struts.form.CommConfigStafftypeForm" />
<html>
	<head>
		<%
		if (request.getServerPort() == 80) {
		%>
		<base href="http://<%=request.getServerName()%><%=request.getContextPath()%>/">
		<%
		} else {
		%>
		<base href="http://<%=request.getServerName()%>:<%=request.getServerPort()%><%=request.getContextPath()%>/">
		<%
		}
		%>
		<title></title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<link type="text/css" rev="stylesheet" rel="stylesheet" href="include/css/form.css" />
	</head>
	<body>
		<form name="form" method="post">
			<input type="hidden" name="verbId" value="detail">
			<table width="100%" border="0" cellpadding="0" cellspacing="0" class="tblView" align="center">
				<tr>
					<td class="tblTitle" colspan="4">
						<bean:message key="comm.jsp.commconfigstafftype.detail.title" bundle="conf.comm.CommMessageguoh"/>
					</td>
				</tr>
				<tr>
					<td class="tblLable">
						<bean:message key="comm.jsp.commconfigstafftype.detail.td1" bundle="conf.comm.CommMessageguoh"/>
					</td>
					<td class="hou">
						<%=commConfigStafftype.getItemCode()%>
					<td class="tblLable">
						<bean:message key="comm.jsp.commconfigstafftype.detail.td2" bundle="conf.comm.CommMessageguoh"/>
					</td>
					<td class="hou">
						<%=commConfigStafftype.getItemName()%>
					</td>
				</tr>
				<tr>
					<td class="tblLable">
						<bean:message key="comm.jsp.commconfigstafftype.detail.td3" bundle="conf.comm.CommMessageguoh"/>
					</td>
					<td class="hou">
						<%=commConfigStafftype.getSeqNo()%>
					</td>
					<td class="tblLable">
						<bean:message key="comm.jsp.commconfigstafftype.detail.td4" bundle="conf.comm.CommMessageguoh"/>
					</td>
					<td class="hou">
						<%=commConfigStafftype.getInputCode()%>
					</td>
				</tr>
				<tr>
					<td class="tblLable">
						<bean:message key="comm.jsp.commconfigstafftype.detail.td5" bundle="conf.comm.CommMessageguoh"/>
					</td>
					<td class="td_col3" colspan="5">
						<%=commConfigStafftype.getComments()%>
					</td>
				</tr>
			</table>
			<!-- Sheet operation button area -->
			<div class="btnSave"><input type="button"  name="btnBack" value='<bean:message key="comm.jsp.commconfigstafftype.detail.input" bundle="conf.comm.CommMessageguoh"/>' onClick="history.go(-1);" /></div>
		</form>
	</body>
</html>
