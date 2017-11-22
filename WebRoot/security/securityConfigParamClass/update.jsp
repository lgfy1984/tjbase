<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<jsp:useBean id="data" scope="request"
	class="com.tianjian.security.struts.form.SecurityConfigParamClassForm" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<%
			if (request.getServerPort() == 80) {
		%>
		<base
			href="http://<%=request.getServerName()%><%=request.getContextPath()%>/">
		<%
			} else {
		%>
		<base
			href="http://<%=request.getServerName()%>:<%=request.getServerPort()%><%=request.getContextPath()%>/">
		<%
			}
		%>
		<title><bean:message
				key="security.jsp.securityConfigParamClassM.update.title"
				bundle="security" />
		</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<script language="javascript" src="<bean:message key="include.js.TJMessage.path" bundle="security" />"></script>
		<script language="javascript" src="include/javascript/eventOnKeyPress.js"></script>
		<script language="javascript" src="<bean:message key="security.js.gettext_staff.path" bundle="security" />"></script>
		<script type="text/javascript" src="security/include/javascript/jianbian.js"></script>
		<script language="javascript" src="<bean:message key="includes.js.validator.path" bundle="security" />" defer="defer"></script>
		<link type="text/css" rev="stylesheet" rel="stylesheet" href="include/css/form.css" />
		<link rel="stylesheet" href="include/css/open.css" />
		<script language="javascript">
			function saveForm(){
				if(!Validator.Validate(document.forms.form,3)){
     			 return ;
   				}
				if(checkspace(document.form.className.value)){
					alert({message:'<bean:message key="security.jsp.securityConfigParamClassM.add.warn" bundle="security"/>'});
				 	return ;
				}
				if (confirmMessage("<bean:message key='security.jsp.commom.update' bundle='security'/>")){     
				    document.form.verbId.value = "update";    
				    document.form.submit(); 
				    }   
			}
			function checkspace(checkstr) {
					var str = '';
					for(i = 0; i < checkstr.length; i++) {
						str = str + ' ';
					}
					return (str == checkstr);
			}
			function isNUM(val) {
			    var rexPatn = /^[0-9]{0,11}?$/;
			    var res = rexPatn.exec(val); 
			    return res;
			}

			function goback(url){
				location.href=url;
			}
			function showMessage(message){
				if(message != ''&& message != null){
					alert(message);
					return;
				}
			}
</script>
	</head>

	<body onload="showMessage('<%=data.getMessage()%>')">
		<form name="form" method="post" action="<%=request.getContextPath()%>/security/securityConfigParamClass.do">

			<input type="hidden" name="verbId" value="update" />
			<input type="hidden" name="classCode"
				value="<%=data.getClassCode()%>" />
			<input type="hidden" name="page" value="<%=data.getPageNow()%>" />
			<table  border="0" cellpadding="0" cellspacing="0" class="tblFill" align="center">
				<tr>
					<td class="tblTitle" colspan="4"><span>※</span>
						<bean:message
							key="security.jsp.securityConfigParamClassM.update.title"
							bundle="security" />
						<span>※</span></td>
				</tr>
				<tr>
					<td class="tblLable">
						程序包代码：
					</td>
					<td>
						<input type="text" class="kuandu" id="classCode" name="classCode"
							style="width: 200px;" maxlength="32" readonly
							max="32" dataType="LimitB" msg="程序包代码输入过长"
							onkeypress="eventOnKeyPress('projectName')"
							value="<%=data.getClassCode()%>" />
					</td>
					<td class="tblLable">
						<span>*</span>
						工程名称:
					</td>
					<td>
						<input type="text" class="input" id="displayInputId_1"
							name="projectName" style="font-size: 12px; width: 180px"
							onkeydown="huiche()" value="<%=data.getProjectName()%>"
							readonly="readonly" onkeypress="eventOnKeyPress('className')" />
						<input type="hidden" id="hiddenInputId_1"
							value="<%=data.getProjectCode()%>" name="projectCode" />
						<img src="security/include/images/select.gif"
							style="cursor: pointer;"
							onclick="add('<%=request.getContextPath()%>/security/securityConfigParamProject.do?verbId=getProject','displayInputId_1','hiddenInputId_1')" />
					</td>
				</tr>
				<tr>
					<td class="tblLable">
						<span>*</span>
						<bean:message key="security.jsp.securityConfigParamClass1.commom2"
							bundle="security" />
						:
					</td>
					<td>
						<input type="text" class="kuandu" name="className"
							style="width: 200px;" maxlength="40"
							max="40" dataType="LimitB" msg="程序包名称输入过长"
							onkeypress="eventOnKeyPress('functionDescription')"
							value="<%=data.getClassName()%>" />
					</td>
					<td class="tblLable">
						<bean:message key="security.jsp.securityConfigParamClass1.commom4"
							bundle="security" />
						:
					</td>
					<td>
						<input type="text" class="kuandu" name="functionDescription" id="functionDescription"
							style="width: 200px;" maxlength="40"
							max="40" dataType="LimitB" msg="功能描述输入过长"
							onkeypress="eventOnKeyPress('comments')"
							value="<%=data.getFunctionDescription()%>" />
					</td>
				</tr>
				<tr>
					<td class="tblLable">
						<bean:message key="security.jsp.securityConfigParamClass1.commom5"
							bundle="security" />
						:
					</td>
					<td colspan="3">
						<input type="text" class="kuandu" id="comments" name="comments"
							maxlength="40" max="40" dataType="LimitB" msg="备注输入过长"
							onkeypress="eventOnKeyPress('btnSaveForm')"
							value="<%=data.getComments()%>" />
					</td>
				</tr>
			</table>


			<!-- Sheet operation button area -->
			<div class="btnSave">
				<input type="button" name="btnSaveForm" value="<bean:message key="security.jsp.commom.button1" bundle="security"/>" onClick="saveForm()" />
				<input type="button" name="btnBack" 	value="<bean:message key="security.jsp.commom.button2" bundle="security"/>" onClick="history.go(-1)" />
			</div>
		</form>

	</body>
</html>
