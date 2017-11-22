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
		<title><bean:message key="Comm.jsp.commom.title" bundle="conf.comm.CommMessage" /></title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<script language="javascript" src="<bean:message  key="Comm.js.TJMessagepath"  bundle="conf.comm.CommMessage"/>"></script>
		<script language="javascript" src="include/javascript/eventOnKeyPress.js"></script>
		<script language="javascript">
function saveForm(){
    if(document.form.classCode.value == ""){
	 	alertMessage("0-000001");
	 	return ;
	}
	if(document.form.dictCode.value == ""){
	 	alertMessage("0-000001");
	 	return ;
	}
	if(document.form.dictValue.value == ""){
	 	alertMessage("0-000002");
	 	return ;
	}
	 
	if (confirmMessage("0-000003")){ 
	document.form.verbId.value = "add";
	document.form.submit();
	}
}
</script>
		<link type="text/css" rev="stylesheet" rel="stylesheet" href="include/css/form.css" />
	</head>
	<body onload="showMessage('','<%=commDictPublicChar.getMessage()%>','1')">
		<form name="form" method="post" action="comm/commDictPublicCharSingle.do">
			<input type="hidden" name="verbId" value="add" />
			<input type="hidden" name="classCode" value="<%=commDictPublicChar.getClassCode()%>">
			<input type="hidden" name="className" value="<%=commDictPublicChar.getClassName()%>">
			<table  align="center" border="0" cellpadding="0" cellspacing="0" class="tblFill" border="0" cellpadding="0" cellspacing="0" class="tblFill">
				<tr>
					<td class="tblTitle" colspan="4"><span>※</span> <bean:message key="Comm.jsp.commom.addDictInfo" bundle="conf.comm.CommMessage"/> <span>※</span></td>
				</tr>
				<tr>
					<td class="tblLable">
						<span>*</span> <bean:message key="jsp.dictCode" bundle="conf.Init"/>：
					</td>
					<td>
						<input type="text"  name="dictCode" size="20" maxlength="9" onkeypress="eventOnKeyPress('dictValue')" value="<%=commDictPublicChar.getDictCode()%>" />
					</td>
					<td class="tblLable">
						<span>*</span> <bean:message key="jsp.dictValue" bundle="conf.Init"/>：
					</td>
					<td>
						<input type="text"  name="dictValue" size="50" maxlength="50" onkeypress="eventOnKeyPress('seqNo')" value="<%=commDictPublicChar.getDictValue()%>" />
					</td>
				</tr>
				<tr>
					<td class="tblLable">
						<bean:message key="jsp.seqNo" bundle="conf.Init"/>：
					</td>
					<td>
						<input type="text"  name="seqNo" id="seqNo" size="30" maxlength="20" onkeypress="eventOnKeyPress('comments')" value="<%=commDictPublicChar.getSeqNo()%>" />
					</td>
					<td class="tblLable">
						<bean:message key="jsp.comments" bundle="conf.Init"/>：
					</td>
					<td>
						<input type="text"  name="comments" id="comments" size="30" maxlength="20" onkeypress="eventOnKeyPress('btnSaveForm')" value="<%=commDictPublicChar.getComments()%>" />
					</td>
				</tr>
			</table>
			<!-- Sheet operation button area -->
			<div class="btnSave">
				<input type="button"  name="btnSaveForm" value="<bean:message key="jsp.btnSaveForm" bundle="conf.Init"/>" onClick="saveForm()" />
				<input type="button"  name="btnBack" value="<bean:message key="jsp.btnBack" bundle="conf.Init"/>" onClick="history.go(-1);" />
			</div>
		</form>
	</body>
</html>
