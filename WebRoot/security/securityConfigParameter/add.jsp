<%@page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<jsp:useBean id="data" scope="request"
	class="com.tianjian.security.struts.form.SecurityConfigParameterForm" />
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
				key="security.jsp.securityConfigParamClass1.add.title"
				bundle="security" />
		</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<script language="javascript" src="<bean:message key="include.js.TJMessage.path" bundle="security" />"></script>
		<script language="javascript" src="<bean:message key="includes.js.validator.path" bundle="security" />" defer="defer"></script>
		<script language="javascript" src="include/javascript/eventOnKeyPress.js"></script>
		<script language="javascript" src="security/include/javascript/gettext_parameter.js"></script>
		<link rel="stylesheet" href="<%=request.getContextPath()%>/include/css/open.css" />
		<script type="text/javascript" src="security/include/javascript/jianbian.js"></script>
		<link type="text/css" rev="stylesheet" rel="stylesheet" href="include/css/form.css" />
		<script language="javascript" src="<bean:message  key="Comm.js.TJMessagepath"  bundle="conf.comm.CommMessage"/>"></script>
		
		<script language="javascript">
		function saveForm(){
			if(!Validator.Validate(document.forms.form,3)){
  			   return ;
   			}
			if(document.form.className.value==""){
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
					lert(message);
					return;
				}
			}
</script>
	</head>
	<body onload="showMessage('<%=data.getMessage()%>')">
		<form name="form" method="post"
			action="<%=request.getContextPath()%>/security/securityConfigParameter.do">
			<input type="hidden" name="verbId" value="add" />
			<input type="hidden" name="levelFlag" value="1" />
			<table border="0" cellpadding="0" cellspacing="0" class="tblFill" align="center">
				<tr>
					<td class="tblTitle" colspan="4"><span>※</span> 
						<bean:message
							key="security.jsp.securityConfigParamClass1.add.title"
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
							name="className" style="font-size: 12px; width: 180px"
							onkeydown="huiche()" value="<%=data.getClassName()%>"
							readonly="readonly" onkeypress="eventOnKeyPress('hidden_1')" />
						<input type="hidden" id="hiddenInputId_1"
							value="<%=data.getClassCode()%>" name="classCode" />
						<img src="security/include/images/select.gif"
							style="cursor: pointer;"
							onclick="add('<%=request.getContextPath()%>/security/securityConfigParamClass1.do?verbId=system','displayInputId_1','hiddenInputId_1')" />
					</td>
					<td class="tblLable">
						<bean:message key="security.jsp.securityConfigParamClass1.commom6"
							bundle="security" />
						:
					</td>
					<td>
						<input type="hidden" class="input" id="InputId_1"
							name="hspConfigBaseinfoId" onkeydown="huiche()"
							value="<%=data.getHspConfigBaseinfoId()%>" />
						<input type="text" class="input" name="hspConfigBaseinfoName"
							style="font-size: 12px; width: 180px" onkeydown="huiche()"
							id="hidden_1" value="<%=data.getHspConfigBaseinfoName()%>"
							readonly="readonly" onkeypress="eventOnKeyPress('itemName')" />
						<img src="security/include/images/select.gif"
							style="cursor: pointer;"
							onclick="add('<%=request.getContextPath()%>/security/securityConfigParamClass1.do?verbId=getHsp','hidden_1','InputId_1')" />
					</td>

				</tr>
				<tr>
					<td class="tblLable">
						<bean:message key="security.jsp.securityConfigParamClass1.commom7"
							bundle="security" />
						:
					</td>
					<td>
						<input type="text" class="kuandu" id="itemName" name="itemName"
							style="font-size: 12px; width: 200px" size="50" maxlength="100"
							onkeypress="eventOnKeyPress('itemValue')" max="100" dataType="LimitB" msg="配置参数名称输入过长"
							value="<%=data.getItemName()%>" />
					</td>
					<td class="tblLable">
						<bean:message key="security.jsp.securityConfigParamClass1.commom8"
							bundle="security" />
						:
					</td>
					<td>
						<input type="text" class="kuandu" id="itemValue" name="itemValue"
							style="font-size: 12px; width: 200px" size="50" maxlength="400"
							onkeypress="eventOnKeyPress('usageDescription')"
							max="400" dataType="LimitB" msg="配置参数值输入过长"
							value="<%=data.getItemValue()%>" />
					</td>
				</tr>
				<tr>
					<td class="tblLable">
						<bean:message key="security.jsp.securityConfigParamClass1.commom9"
							bundle="security" />
						:
					</td>
					<td>
						<input type="text" class="kuandu" id="usageDescription"
							name="usageDescription" style="font-size: 12px; width: 200px"
							size="50" maxlength="400" max="400" dataType="LimitB" msg="常用方法描述输入过长"
							onkeypress="eventOnKeyPress('initValue')"
							value="<%=data.getUsageDescription()%>" />
					</td>
					<td class="tblLable">
						<bean:message
							key="security.jsp.securityConfigParamClass1.commom10"
							bundle="security" />
						:
					</td>
					<td>
						<input type="text" class="kuandu" name="initValue" id="initValue"
							style="font-size: 12px; width: 200px" size="100" maxlength="400"
							onkeypress="eventOnKeyPress('btnSaveForm')" 
							max="400" dataType="LimitB" msg="初始值输入过长"
							value="<%=data.getInitValue()%>" />
					</td>
				</tr>
			</table>
			<!-- Sheet operation button area -->
			<div class="btnSave">
				<input type="button" style="font-size: 12px; font-family: Arial;"
							name="btnSaveForm" style="font-size:12px;font-family:Arial;"
							value="<bean:message key="security.jsp.commom.button1" bundle="security"/>"
							onClick="saveForm()" />
				<input type="button" style="font-size: 12px; font-family: Arial;"
							name="btnBack" style="font-size:12px;font-family:Arial;"
							value="<bean:message key="security.jsp.commom.button2" bundle="security"/>"
							onClick="history.go(-1)" />
			</div>
		</form>
	</body>
</html>
