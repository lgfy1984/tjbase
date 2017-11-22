<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="html" uri="/WEB-INF/struts-html.tld"%>
<%@ taglib prefix="bean" uri="/WEB-INF/struts-bean.tld"%>
<%@ taglib prefix="logic" uri="/WEB-INF/struts-logic.tld"%>
<jsp:useBean id="commDictPublicChar" scope="request" class="com.tianjian.comm.struts.form.CommDictPublicCharForm" />
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
					    <bean:message key="Comm.jsp.commom.GeneralDictDetailInfo" bundle="conf.comm.CommMessage" />
					</td>
				</tr>
				<tr>
					<td class="tblLable">
						ID：
					</td>
					<td class="hou">
						<%=commDictPublicChar.getId()%>
					</td>
					<td class="tblLable">
						<bean:message key="jsp.dictCode" bundle="conf.Init"/>：
					</td>
					<td class="hou">
						<%=commDictPublicChar.getDictCode()%>
					</td>
				</tr>
				<tr>
					<td class="tblLable">
						<bean:message key="jsp.dictValue" bundle="conf.Init"/>：
					</td>
					<td class="hou">
						<%=commDictPublicChar.getDictValue()%>
					</td>
					<td class="tblLable">
						<bean:message key="jsp.seqNo" bundle="conf.Init"/>：
					</td>
					<td class="hou">
						<%=commDictPublicChar.getSeqNo()%>
					</td>
				</tr>
				<tr>
					<td class="tblLable">
						<bean:message key="jsp.inputNo" bundle="conf.Init"/>：
					</td>
					<td class="hou">
						<%=commDictPublicChar.getInputCode()%>
					</td>
					<td class="tblLable">
						<bean:message key="jsp.comments" bundle="conf.Init"/>：
					</td>
					<td class="hou">
						<%=commDictPublicChar.getComments()%>
					</td>
				</tr>
			</table>
			<!-- Sheet operation button area -->
			<div class="btnSave">
				<input type="button"  name="btnBack" value="<bean:message key="jsp.btnBack" bundle="conf.Init"/>" onClick="history.go(-1);" />
			</div>
		</form>
	</body>
</html>
