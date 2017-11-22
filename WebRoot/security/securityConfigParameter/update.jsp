<%@page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<jsp:useBean id="data" scope="request"
	class="com.tianjian.security.struts.form.SecurityConfigParameterForm" />
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
		<title><bean:message
				key="security.jsp.securityConfigParamClass1.update.title"
				bundle="security" />
		</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<script language="javascript" src="<bean:message key="security.js.gettext_staff.path" bundle="security" />"></script>
		<script type="text/javascript" src="security/include/javascript/jianbian.js"></script>
		<script language="javascript" src="include/javascript/eventOnKeyPress.js"></script>
		<script language="javascript" src="include/javascript/TJMessage.js"></script>
		<script language="javascript" src="<bean:message key="includes.js.validator.path" bundle="security" />" defer="defer"></script>
		<script language="javascript" src="<bean:message  key="Comm.js.TJMessagepath"  bundle="conf.comm.CommMessage"/>"></script>
		
		<link type="text/css" rev="stylesheet" rel="stylesheet" href="include/css/form.css" />
		<link rel="stylesheet" href="<%=request.getContextPath()%>/hsp/include/css/open.css" />
		<script language="javascript">
			function saveForm(){
				if(!Validator.Validate(document.forms.form,3)){
  			   		return ;
   				}
				if(checkspace(document.form.className.value)){
					alert('<bean:message key="security.jsp.securityConfigParamClassM.add.warn" bundle="security"/>');
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
		<form name="form" method="post"
			action="<%=request.getContextPath()%>/security/securityConfigParameter.do">
			<input type="hidden" name="verbId" value="update" />
			<input type="hidden" name="hiddenId" value="<%=data.getId()%>" />
			<input type="hidden" name="page" value="<%=data.getPageNow()%>" />
			<table border="0" cellpadding="0" cellspacing="0" class="tblFill" align="center">
				<tr>
					<td class="tblTitle" colspan="4"><span>※</span> 
						<bean:message
							key="security.jsp.securityConfigParamClass1.update.title"
							bundle="security" />
						<span>※</span></td>
				</tr>
				<tr>
					<td class="tblLable">
						<span>*</span>
						<bean:message key="security.jsp.securityConfigParamClass1.commom2"
							bundle="security" />:
					</td>
					<td>
						<input type="text" class="input" id="displayInputId_1"
							name="className" style="width: 180px;" onkeydown="huiche()"
							value="<%=data.getClassName()%>" readonly="readonly"
							onkeypress="eventOnKeyPress('hspConfigBaseinfoName')" />
						<input type="hidden" id="hiddenInputId_1"
							value="<%=data.getClassCode()%>" name="classCode" />
						<img src="security/include/images/select.gif"
							style="cursor: pointer;"
							onclick="add('<%=request.getContextPath()%>/security/securityConfigParameter.do?verbId=system','displayInputId_1','hiddenInputId_1')" />
					</td>
					<td class="tblLable">
						<bean:message key="security.jsp.securityConfigParamClass1.commom6"
							bundle="security" />:
					</td>
					<td>
						<input type="hidden" class="input" id="InputId_1"
							name="hspConfigBaseinfoId" onkeydown="huiche()"
							value="<%=data.getHspConfigBaseinfoId()%>" />
						<input type="text" class="input" name="hspConfigBaseinfoName"
							style="width: 180px;" onkeydown="huiche()" id="hidden_1"
							value="<%=data.getHspConfigBaseinfoName()%>" readonly="readonly"
							onkeypress="eventOnKeyPress('itemName')" />
						<img src="security/include/images/select.gif"
							style="cursor: pointer;"
							onclick="add('<%=request.getContextPath()%>/security/securityConfigParameter.do?verbId=getHsp','hidden_1','InputId_1')" />
					</td>
				</tr>
				<tr>
					<td class="tblLable">
						<bean:message key="security.jsp.securityConfigParamClass1.commom7"
							bundle="security" />:
					</td>
					<td>
						<input type="text" class="kuandu" id="itemName" name="itemName"
							style="width: 200px;" maxlength="100"
							max="100" dataType="LimitB" msg="配置参数名称输入过长"
							onkeypress="eventOnKeyPress('itemValue')"
							value="<%=data.getItemName()%>" />
					</td>
					<td class="tblLable">
						<bean:message key="security.jsp.securityConfigParamClass1.commom8"
							bundle="security" />:
					</td>
					<td>
						<input type="text" class="kuandu" id="itemValue" name="itemValue"
							style="width: 200px;" maxlength="400"
							max="400" dataType="LimitB" msg="配置参数值输入过长"
							onkeypress="eventOnKeyPress('usageDescription')"
							value="<%=data.getItemValue()%>" />
					</td>
				</tr>

				<tr>
					<td class="tblLable">
						<bean:message key="security.jsp.securityConfigParamClass1.commom9"
							bundle="security" />:
					</td>
					<td>
						<input type="text" class="kuandu" id="usageDescription"
							name="usageDescription" style="width: 200px;" maxlength="400"
							onkeypress="eventOnKeyPress('initValue')"
							max="400" dataType="LimitB" msg="常用方法描述输入过长"
							value="<%=data.getUsageDescription()%>" />
					</td>
					<td class="tblLable">
						<bean:message
							key="security.jsp.securityConfigParamClass1.commom10"
							bundle="security" />:
					</td>
					<td>
						<input type="text" class="kuandu" id="initValue" name="initValue"
							style="width: 200px;" maxlength="400"
							max="400" dataType="LimitB" msg="初始值输入过长"
							onkeypress="eventOnKeyPress('btnSaveForm')"
							value="<%=data.getInitValue()%>" />
					</td>
				</tr>
			</table>
			<!-- Sheet operation button area -->
			<div class="btnSave">
				<input type="button" style="font-size: 12px; font-family: Arial;"
							name="btnSaveForm"
							value="<bean:message key="security.jsp.commom.button1" bundle="security"/>"
							onClick="saveForm()" />
				<input type="button" style="font-size: 12px; font-family: Arial;"
							name="btnBack"
							value="<bean:message key="security.jsp.commom.button2" bundle="security"/>"
							onClick="history.go(-1);" />
			</div>
		</form>

	</body>
</html>
