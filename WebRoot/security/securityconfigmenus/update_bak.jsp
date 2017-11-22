<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="html" uri="/WEB-INF/struts-html.tld"%>
<%@ taglib prefix="bean" uri="/WEB-INF/struts-bean.tld"%>
<%@ taglib prefix="logic" uri="/WEB-INF/struts-logic.tld"%>
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
		<title><bean:message key="security.jsp.securityconfigmenus.update.title" bundle="security"/></title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<script language="javascript" src="<bean:message key="comm.js.CommMessage.path" bundle="security" />"></script>
		<script language="javascript" src="include/javascript/eventOnKeyPress.js"></script>
		<script language="javascript">
function saveForm(){
	if(document.form.menuCode.value == ""){
	 	alertCommMessage("0-000001");
	 	return ;
	}
	if(document.form.menuDetail.value == ""){
	 	alertCommMessage("0-000002");
	 	return ;
	}

	 
	document.form.verbId.value = "update";
	document.form.submit();
}


</script>
		<link rel="stylesheet" rev="stylesheet" href="comm/include/css/comm_add.css" />
	</head>
	<body onload="showCommMessage('','${dataForm.message}','')">
		<form name="form" method="post" action="security/securityConfigMenus.do">
			<input type="hidden" name="verbId" value="update" />
			<input type="hidden" name="data.id" value="${dataForm.data.id}" />
			<table border="0" cellspacing="1" cellpadding="0" class="table">
				<tr>
					<td height="30px" class="biaoti" colspan="4">
						<font color="red">※</font>&nbsp;&nbsp;<bean:message key="security.jsp.securityconfigmenus.update.title" bundle="security"/>&nbsp;&nbsp;
						<font color="red">※</font>
					</td>
				</tr>
				<tr>
					<td class="qian" nowrap>
						<font color="red">*</font> <bean:message key="security.jsp.commom.menuCode" bundle="security"/>：
					</td>
					<td class="hou" nowrap>
						<input type="text" class="kuandu" name="data.menuCode" id="menuCode" size="20" maxlength="9" onkeypress="eventOnKeyPress('menuDetail')" value="${dataForm.data.menuCode}" />
					</td>
					<td class="qian" nowrap>
						<font color="red">*</font> <bean:message key="security.jsp.commmom.classCode" bundle="security"/>：
					</td>
					<td class="hou" nowrap>
						<input type="text" class="kuandu" name="data.menuDetail" id="menuDetail" size="50" maxlength="50" onkeypress="eventOnKeyPress('serialNo')" value="${dataForm.data.menuDetail}" />
					</td>
				</tr>
				<tr>
					<td class="qian" nowrap>
						<bean:message key="security.jsp.commom.serialNo" bundle="security"/>：
					</td>
					<td class="hou" nowrap>
						<input type="text" class="kuandu" name="data.serialNo" id="serialNo" size="30" maxlength="20" onkeypress="eventOnKeyPress('comments')" value="${dataForm.data.serialNo}" />
					</td>
					<td class="qian" nowrap>
						<bean:message key="security.jsp.commmom.comments" bundle="security"/>：
					</td>
					<td class="hou" nowrap>
						<input type="text" class="kuandu" name="data.comments" id="comments" size="30" maxlength="20" value="${dataForm.data.comments}" />
					</td>
				</tr>
				<tr>
					<td class="qian" nowrap>
						<bean:message key="security.jsp.commom.menuNotice" bundle="security"/>：
					</td>
					<td class="hou" nowrap>
						<input type="text" class="kuandu" name="data.menuNotice" id="menuNotice" size="30" maxlength="20" onkeypress="eventOnKeyPress('comments')" value="${dataForm.data.menuNotice}" />
					</td>
					<td class="qian" nowrap>
						<bean:message key="security.jsp.commom.menuUrl" bundle="security"/>：
					</td>
					<td class="hou" nowrap>
						<input type="text" class="kuandu" name="data.menuUrl" id="menuUrl" size="30" maxlength="20" value="${dataForm.data.menuUrl}" />
					</td>
				</tr>
				<tr>
					<td class="qian" nowrap>
						<bean:message key="security.jsp.commom.endLevelFlag" bundle="security"/>：
					</td>
					<td class="hou" nowrap>
						<input type="text" class="kuandu" name="data.endLevelFlag" id="endLevelFlag" size="30" maxlength="20" onkeypress="eventOnKeyPress('comments')" value="${dataForm.data.endLevelFlag}" />
					</td>
					<td class="qian" nowrap>
						<bean:message key="security.jsp.commom.menuLevel" bundle="security"/>：
					</td>
					<td class="hou" nowrap>
						<input type="text" class="kuandu" name="data.menuLevel" id="menuLevel" size="30" maxlength="20" value="${dataForm.data.menuLevel}" />
					</td>
				</tr>
				<tr>
					<td class="qian" nowrap>
						<bean:message key="security.jsp.commom.menuSeq" bundle="security"/>：
					</td>
					<td class="hou" nowrap>
						<input type="text" class="kuandu" name="data.menuSeq" id="menuSeq" size="30" maxlength="20" onkeypress="eventOnKeyPress('comments')" value="${dataForm.data.menuSeq}" />
					</td>
					<td class="qian" nowrap>
						<bean:message key="security.jsp.commom.menuIcon" bundle="security"/>：
					</td>
					<td class="hou" nowrap>
						<input type="text" class="kuandu" name="data.menuIcon" id="menuIcon" size="30" maxlength="20" value="${dataForm.data.menuIcon}" />
					</td>
				</tr>
				<tr>
					<td class="qian" nowrap>
						<bean:message key="security.jsp.commom.menuData" bundle="security"/>：
					</td>
					<td class="hou" nowrap>
						<input type="text" class="kuandu" name="data.menuData" id="menuData" size="30" maxlength="20" onkeypress="eventOnKeyPress('comments')" value="${dataForm.data.menuData}" />
					</td>
					<td class="qian" nowrap>
						<bean:message key="security.jsp.commom.menuTarget" bundle="security"/>：
					</td>
					<td class="hou" nowrap>
						<input type="text" class="kuandu" name="data.menuTarget" id="menuTarget" size="30" maxlength="20" value="${dataForm.data.menuTarget}" />
					</td>
				</tr>
				<tr>
					<td class="qian" nowrap>
						<bean:message key="security.jsp.commom.menuMethod" bundle="security"/>：
					</td>
					<td class="hou" nowrap>
						<input type="text" class="kuandu" name="data.menuMethod" id="menuMethod" size="30" maxlength="20" onkeypress="eventOnKeyPress('comments')" value="${dataForm.data.menuMethod}" />
					</td>
					<td class="qian" nowrap>
						<bean:message key="security.jsp.commom.parentIdname" bundle="security"/>：
					</td>
						<td class="hou" nowrap>
						<select id="parentId" class="select" name="data.parentId" onkeypress="eventOnKeyPress('btnSaveForm')">
							<option value=""></option>
							<logic:notEmpty name="dataForm" property="securityConfigMenusList">
								<logic:iterate id="commmBean" name="dataForm" property="securityConfigMenusList" indexId="ind">
									<logic:equal name="commmBean" value="${dataForm.data.parentId}" property="id">
										<option value="${commmBean.id}" selected="selected">
											${commmBean.menuDetail}
										</option>
									</logic:equal>
									<logic:notEqual name="commmBean" value="${dataForm.data.parentId}" property="id">
										<option value="${commmBean.id}">
											${commmBean.menuDetail}
										</option>
									</logic:notEqual>
								</logic:iterate>
							</logic:notEmpty>
						</select>
					</td>
				</tr>
				<tr>
					<td class="qian" nowrap>
						<bean:message key="security.jsp.commom.securityConfigPublicId" bundle="security"/>：
					</td>
					<td class="hou" nowrap>
						<select id="securityConfigPublicId" class="select" name="data.securityConfigPublicId" onkeypress="eventOnKeyPress('picPath')">
							<option value=""></option>
							<logic:notEmpty name="dataForm" property="securityConfigPublicList">
								<logic:iterate id="commmBean" name="dataForm" property="securityConfigPublicList" indexId="ind">
									<logic:equal name="commmBean" value="${dataForm.data.securityConfigPublicId}" property="id">
										<option value="${commmBean.id}" selected="selected">
											${commmBean.reason}
										</option>
									</logic:equal>
									<logic:notEqual name="commmBean" value="${dataForm.data.securityConfigPublicId}" property="id">
										<option value="${commmBean.id}">
											${commmBean.reason}
										</option>
									</logic:notEqual>
								</logic:iterate>
							</logic:notEmpty>
						</select>
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
						<input type="button"  name="btnSaveForm" value="<bean:message key="security.jsp.commom.button1" bundle="security"/>" onClick="saveForm()" />
						<input type="button"  name="btnBack" value="<bean:message key="security.jsp.commom.button2" bundle="security"/>" onClick="history.go(-1);" />
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>
