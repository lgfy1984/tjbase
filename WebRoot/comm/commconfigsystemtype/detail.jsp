<%@ page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="bean" uri="/WEB-INF/struts-bean.tld"%>
<jsp:useBean id="commConfigSystemtype" scope="request" class="com.tianjian.comm.struts.form.CommConfigSystemtypeForm" />
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
		<link rel="stylesheet" rev="stylesheet" href="comm/include/css/comm_detail.css" />
	</head>
	<body>
		<form name="form" method="post">
			<input type="hidden" name="verbId" value="detail">
			<table border="0" cellspacing="1" cellpadding="0" class="table" align="center">
				<tr>
					<td class="biaoti1" colspan="4" nowrap>
						<bean:message key="comm.jsp.commconfigsystemtype.detail.title" bundle="conf.comm.CommMessageguoh"/>
					</td>
				</tr>
				<tr>
					<td class="qian" nowrap>
						<bean:message key="comm.jsp.commconfigsystemtype.detail.td1" bundle="conf.comm.CommMessageguoh"/>
					</td>
					<td class="hou" nowrap>
						<%=commConfigSystemtype.getItemCode()%>
					<td class="qian" nowrap>
						<bean:message key="comm.jsp.commconfigsystemtype.detail.td2" bundle="conf.comm.CommMessageguoh"/>
					</td>
					<td class="hou" nowrap>
						<%=commConfigSystemtype.getItemName()%>
					</td>
				</tr>
				<tr>
					<td class="qian" nowrap>
						<bean:message key="comm.jsp.commconfigsystemtype.detail.td3" bundle="conf.comm.CommMessageguoh"/>
					</td>
					<td class="hou" nowrap>
						<%=commConfigSystemtype.getSeqNo()%>
					</td>
					<td class="qian" nowrap>
						<bean:message key="comm.jsp.commconfigsystemtype.detail.td4" bundle="conf.comm.CommMessageguoh"/>
					</td>
					<td class="hou" nowrap>
						<%=commConfigSystemtype.getInputCode()%>
					</td>
				</tr>
				<tr>
					<td class="qian" nowrap>
						<bean:message key="comm.jsp.commconfigsystemtype.detail.td5" bundle="conf.comm.CommMessageguoh"/>
					</td>
					<td class="hou" nowrap>
						<%=commConfigSystemtype.getComments()%>
					</td>
					<td class="qian" nowrap>
					</td>
					<td class="hou" nowrap>
					</td>
				</tr>
			</table>
			<!-- Sheet operation button area -->
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td height="30" align="center">
						<input type="button" style="cursor: hand; background: url(include/images/tong.gif) no-repeat; border: 0; width: 50px; height: 23px; font-size: 12px;" name="btnBack" value='<bean:message key="comm.jsp.commconfigsystemtype.detail.input" bundle="conf.comm.CommMessageguoh"/>' onClick="history.go(-1);" />
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>
