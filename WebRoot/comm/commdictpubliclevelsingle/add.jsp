<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="html" uri="/WEB-INF/struts-html.tld"%>
<%@ taglib prefix="bean" uri="/WEB-INF/struts-bean.tld"%>
<%@ taglib prefix="logic" uri="/WEB-INF/struts-logic.tld"%>
<jsp:useBean id="commDictPublicLevel" scope="request" class="com.tianjian.comm.struts.form.CommDictPublicLevelForm" />
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
		<title><bean:message key="Comm.jsp.commom.addGeneralLevelDictInfo" bundle="conf.comm.CommMessage"/></title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<script language="javascript" src="<bean:message  key="Comm.js.TJMessagepath"  bundle="conf.comm.CommMessage"/>"></script>
		<script language="javascript" src="include/javascript/eventOnKeyPress.js"></script>
		<script language="javascript">
function saveForm(){
    if(document.form.classLevel.value == ""){
	 	alertMessage("0-000001");
	 	return ;
	}
	if(document.form.itemName.value == ""){
	 	alertMessage("0-000001");
	 	return ;
	}
	if(document.form.tableCode.value == ""){
	 	alertMessage("0-000002");
	 	return ;
	}
	 
if (confirmMessage("0-000003")){ 
	document.form.verbId.value = "add";
	document.form.submit();
	}
}
</script>
		<link rel="stylesheet" rev="stylesheet" href="comm/include/css/comm_add.css" />
	</head>
	<body onload="showMessage('','<%=commDictPublicLevel.getMessage()%>','1')">
		<form name="form" method="post" action="comm/commDictPublicLevelSingle.do">
			<input type="hidden" name="verbId" value="add" />
			<input type="hidden" name="tableCode" value="<%=commDictPublicLevel.getTableCode()%>">
			<input type="hidden" name="tableName" value="<%=commDictPublicLevel.getTableName()%>">
			<table border="0" cellspacing="1" cellpadding="0" class="table">
				<tr>
					<td height="30px" class="biaoti" colspan="4">
						<font color="red">※</font>&nbsp;&nbsp;<bean:message key="Comm.jsp.commom.addDictInfo" bundle="conf.comm.CommMessage"/>&nbsp;&nbsp;
						<font color="red">※</font>
					</td>
				</tr>
				<tr>
					<td class="qian" nowrap>
						<font color="red">*</font><bean:message key="Comm.jsp.commom.parentItemName" bundle="conf.comm.CommMessage"/>：
					</td>
					<td class="hou" nowrap>
						<select class="kuandu" name="parentItemCode" id="parentItemCode" onkeypress="eventOnKeyPress('classLevel')">
							<%
									if (commDictPublicLevel.getParentItemCodes() != null && commDictPublicLevel.getParentItemCodes().length > 0) {
									for (int i = 0; i < commDictPublicLevel.getParentItemCodes().length; i++) {
										String tempId = commDictPublicLevel.getParentItemCodes()[i];
										String tempName = commDictPublicLevel.getParentItemNames()[i];
							%>
							<option value="<%=tempId%>" <%=tempId.equals(commDictPublicLevel.getParentItemCode()) ? "selected" : ""%>>
								<%=tempName%>
							</option>
							<%
								}
								}
							%>
						</select>
					</td>
					<td class="qian" nowrap>
						<font color="red">*</font> <bean:message key="Comm.jsp.commom.itemLevel" bundle="conf.comm.CommMessage"/>：
					</td>
					<td class="hou" nowrap>
						<input type="text" class="kuandu" name="classLevel" size="20" maxlength="9" onkeypress="eventOnKeyPress('itemName')" value="<%=commDictPublicLevel.getClassLevel()%>" />
					</td>
				</tr>
				<tr>
					<td class="qian" nowrap>
						<font color="red">*</font> <bean:message key="jsp.dictValue" bundle="conf.Init"/>：
					</td>
					<td class="hou" nowrap>
						<input type="text" class="kuandu" name="itemName" size="50" maxlength="50" onkeypress="eventOnKeyPress('seqInLevel')" value="<%=commDictPublicLevel.getItemName()%>" />
					<td class="qian" nowrap>
						<bean:message key="jsp.seqNo" bundle="conf.Init"/>：
					</td>
					<td class="hou" nowrap>
						<input type="text" class="kuandu" name="seqInLevel" id="seqInLevel" size="30" maxlength="20" onkeypress="eventOnKeyPress('comments')" value="<%=commDictPublicLevel.getSeqInLevel()%>" />
					</td>
				</tr>
				<tr>
					<td class="qian" nowrap>
						<bean:message key="jsp.comments" bundle="conf.Init"/>：
					</td>
					<td class="hou" nowrap>
						<input type="text" class="kuandu" name="comments" id="comments" size="30" maxlength="20" onkeypress="eventOnKeyPress('btnSaveForm')" value="<%=commDictPublicLevel.getComments()%>" />
					<td class="qian" nowrap>
					</td>
					<td class="hou" nowrap>
				</tr>
			</table>
			<!-- Sheet operation button area -->
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td height="30" align="center">
						<input type="button" style="cursor: hand; background: url(include/images/tong.gif) no-repeat; border: 0; width: 50px; height: 23px; font-size: 12px;" name="btnSaveForm" value="<bean:message key="jsp.btnSaveForm" bundle="conf.Init"/>" onClick="saveForm()" />
						<input type="button" style="cursor: hand; background: url(include/images/tong.gif) no-repeat; border: 0; width: 50px; height: 23px; font-size: 12px;" name="btnBack" value="<bean:message key="jsp.btnBack" bundle="conf.Init"/>" onClick="history.go(-1);" />
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>
