<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="html" uri="/WEB-INF/struts-html.tld"%>
<%@ taglib prefix="bean" uri="/WEB-INF/struts-bean.tld"%>
<%@ taglib prefix="logic" uri="/WEB-INF/struts-logic.tld"%>
<jsp:useBean id="commConfigProfession" scope="request" class="com.tianjian.comm.struts.form.CommConfigProfessionForm" />
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
			<table border="0" cellspacing="1" cellpadding="0" class="table">
				<tr>
					<td class="biaoti" colspan="4" nowrap>
						<bean:message key="comm.jsp.common.zyzdMessage" bundle="conf.comm.Comm"/>
					</td>
				</tr>
				<tr>
					<td class="qian" nowrap>
						<bean:message key="comm.jsp.common.item" bundle="conf.comm.Comm"/>：
					</td>
					<td class="hou" nowrap>
						<%=commConfigProfession.getItemCode()%>
					<td class="qian" nowrap>
						<bean:message key="comm.jsp.common.names" bundle="conf.comm.Comm"/>：
					</td>
					<td class="hou" nowrap>
						<%=commConfigProfession.getItemName()%>
					</td>
				</tr>
				<tr>
					<td class="qian" nowrap>
						<bean:message key="comm.jsp.common.seqNo" bundle="conf.comm.Comm"/>：
					</td>
					<td class="hou" nowrap>
						<%=commConfigProfession.getSeqNo()%>
					</td>
					<td class="qian" nowrap>
						<bean:message key="comm.jsp.common.inputItemCode" bundle="conf.comm.Comm"/>：
					</td>
					<td class="hou" nowrap>
						<%=commConfigProfession.getInputCode()%>
					</td>
				</tr>
				<tr>
					<td class="qian" nowrap>
						<bean:message key="comm.jsp.common.comments" bundle="conf.comm.Comm"/>：
					</td>
					<td class="hou" nowrap>
						<%=commConfigProfession.getComments()%>
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
						<input type="button" name="btnBack" value="<bean:message key="comm.jsp.common.return" bundle="conf.comm.Comm"/>" onClick="history.go(-1);" />
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>
