<%@ page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="bean" uri="/WEB-INF/struts-bean.tld"%>
<jsp:useBean id="commConfigUnitgrade" scope="request" class="com.tianjian.comm.struts.form.CommConfigUnitgradeForm" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
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
						<bean:message key="comm.jsp.commconfigunitgrade.detail.title" bundle="conf.comm.CommMessageguoh"/>
					</td>
				</tr>
				<tr>
					<td class="tblLable">
						<bean:message key="comm.jsp.commconfigunitgrade.add.td3" bundle="conf.comm.CommMessageguoh"/>
					</td>
					<td class="hou">
						<%=commConfigUnitgrade.getItemCode()%>
					<td class="tblLable">
						<bean:message key="comm.jsp.commconfigunitgrade.add.td4" bundle="conf.comm.CommMessageguoh"/>
					</td>
					<td class="hou">
						<%=commConfigUnitgrade.getItemName()%>
					</td>
				</tr>
				<tr>
					<td class="tblLable">
						<bean:message key="comm.jsp.commconfigunitgrade.add.td2" bundle="conf.comm.CommMessageguoh"/>
					</td>
					<td class="hou">
						<%=commConfigUnitgrade.getSeqNo()%>
					</td>
					<td class="tblLable">
						<bean:message key="comm.jsp.commconfigunitgrade.detail.td4" bundle="conf.comm.CommMessageguoh"/>
					</td>
					<td class="hou">
						<%=commConfigUnitgrade.getInputCode()%>
					</td>
				</tr>
				<tr>
					<td class="tblLable">
						<bean:message key="comm.jsp.commconfigunitgrade.add.td5" bundle="conf.comm.CommMessageguoh"/>
					</td>
					<td class="hou" colspan="3">
						<%=commConfigUnitgrade.getComments()%>
					</td>
				</tr>
			</table>
			<!-- Sheet operation button area -->
			<div class="btnSave">
				<input type="button"  name="btnBack" value='<bean:message key="comm.jsp.commconfigunitgrade.detail.input" bundle="conf.comm.CommMessageguoh"/>' onClick="history.go(-1);" />
			</div>
		</form>
	</body>
</html>
