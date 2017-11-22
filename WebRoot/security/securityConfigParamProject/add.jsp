<%@page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<jsp:useBean id="data" scope="request"
	class="com.tianjian.security.struts.form.SecurityConfigParamProjectForm" />
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
				key="security.jsp.securityConfigParamClassProject.add.title"
				bundle="security" />
		</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<script language="javascript" src="<bean:message key="includes.js.validator.path" bundle="security" />" defer="defer"></script>
		<script language="javascript" src="include/javascript/eventOnKeyPress.js"></script>
		<script language="javascript" src="<%=request.getContextPath()%>/comm/include/javascript/CommMessage.js"></script>
		<link type="text/css" rev="stylesheet" rel="stylesheet" href="include/css/form.css" />
		<script language="javascript" src="include/javascript/TJMessage.js"></script>
		<script language="javascript">
				function saveForm(){
					if(!Validator.Validate(document.forms.form,3)){
     			 		return ;
   					}
   					if(document.form.projectCode.value==""){
   						alert("工程代码不能为空值");
   						return false;
   					}
					if(document.form.projectName.value==""){
						alert('<bean:message key="security.jsp.securityConfigParamClassM.add.warn" bundle="security"/>');
					    return false;
					}
					if (confirmMessage("<bean:message key='security.jsp.commom.save' bundle='security'/>")){     
					    document.form.verbId.value = "add";    
					    document.form.submit(); 
					}   
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
		<form name="form" method="post"
			action="<%=request.getContextPath()%>/security/securityConfigParamProject.do">
			<input type="hidden" name="verbId" value="add" />
			<input type="hidden" name="levelFlag" value="1" />
			<table border="0" cellpadding="0" cellspacing="0" class="tblFill" align="center">
				<tr>
					<td class="tblTitle" colspan="4"><span>※</span>
						<bean:message key="security.jsp.securityConfigParamClassProject.add.title" bundle="security" />
						<span>※</span></td>
				</tr>
				<tr>
					<td class="tblLable">
						<span>*</span>工程代码：
					</td>
					<td>
						<input type="text" class="kuandu" id="projectCode" name="projectCode"
							style="width:200px;" maxlength="32"
							max="32" dataType="LimitB" msg="项目代码输入过长"
							onkeypress="eventOnKeyPress('projectName')"
							value="<%=data.getProjectCode()%>" />
					</td>
					<td class="tblLable">
						<span>*</span>
						工程名称：
						
					</td>
					<td>
						<input type="text" class="kuandu" name="projectName"
							id="projectName" style="width:200px;" maxlength="40"
							max="40" dataType="LimitB" msg="项目名称输入过长"
							onkeypress="eventOnKeyPress('seqNo')"
							onkeyup="value=value.replace(/[^\d\w]/g,'')"
							value="<%=data.getProjectName()%>" />
					</td>
				</tr>
				<tr>
					<td class="tblLable">
						序号:
					</td>
					<td>
						<input type="text" class="kuandu" name="seqNo"
							id="seqNo" style="width:200px;" maxlength="40"
							onkeypress="eventOnKeyPress('comments')"
							max="40" dataType="LimitB" msg="功能描述输入过长"
							value="<%=data.getSeqNo()%>" />
					</td>

					<td class="tblLable">
						备注:
					</td>
					<td>
						<input type="text" class="kuandu" name="comments" id="comments"
							style="width: 200px;" maxlength="40"
							max="40" dataType="LimitB" msg="备注输入过长"
							onkeypress="eventOnKeyPress('btnSaveForm')"
							value="<%=data.getComments()%>" />
					</td>
				</tr>
			</table>
			<div class="btnSave">
				<input type="button" 
							name="btnSaveForm"
							value="<bean:message key="security.jsp.commom.button1" bundle="security"/>"
							onClick="saveForm()" />
				<input type="button" style="font-size: 12px; font-family: Arial;"
							name="btnBack"
							value="<bean:message key="security.jsp.commom.button2" bundle="security"/>"
							onClick="history.go(-1)" />
			</div>
		</form>
	</body>
</html>
