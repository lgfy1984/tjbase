<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<jsp:useBean id="data" scope="request" class="com.tianjian.comm.struts.form.CommConfigHospitalClassForm" />
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
		<title><bean:message key="comm.jsp.different.text35" bundle="conf.comm.comm"/></title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<script language="javascript" src="<bean:message key="include.js.TJMessage.path" bundle="conf.comm.comm"/>"></script>
		<script language="javascript" src="<bean:message key="includes.js.validator.path" bundle="security" />" defer="defer"></script>
		<script language="javascript" src="include/javascript/eventOnKeyPress.js"></script>
		<script language="javascript">
		function trim(str){  //删除左右两端的空格
			return str.replace(/(^\s*)|(\s*$)/g, "");
		}
		
function saveForm(){
	var itemCode = trim(document.form.classCode.value);
   if(!Validator.Validate(document.forms.form,3)){
      return ;
     }
	if(itemCode == ""){
	 	alertMessage("0-000001");
	 	return ;
	}
	if(itemCode.length>16){
		alert("<bean:message key="comm.jsp.different.text5" bundle="conf.comm.comm"/>"+itemCode.length+"<bean:message key="comm.jsp.different.text511" bundle="conf.comm.comm"/>");
	}
	var itemName = trim(document.form.className.value);
	if(itemName == ""){
	 	alertMessage("0-000002");
	 	return ;
	}
	if(itemName.length>20){
		alert("<bean:message key="comm.jsp.different.text24" bundle="conf.comm.comm"/>"+itemName.length+"<bean:message key="comm.jsp.different.text511" bundle="conf.comm.comm"/>");
	}
if (confirmMessage("0-000003")){ 
	document.form.verbId.value = "add";
	document.form.submit();
	}
}
function huiche(){
	if(event.keyCode==13){
		event.keyCode=9;
	}
}
function goback(){
	document.form.classCode.value="";
	document.form.className.value="";
	document.form.verbId.value = "query";
	document.form.submit();
}
</script>
		<link rel="stylesheet" rev="stylesheet" href="comm/include/css/comm_add.css" />
	</head>
	<body onload="showMessage('','<%=data.getMessage()%>','1')">
		<form name="form" method="post" action="comm/commConfigHospitalClass.do">
			<input type="hidden" name="verbId" value="add" />
			<input type="hidden" name="page" value="<%=data.getMypage() %>" />
			<table border="0" cellspacing="1" cellpadding="0" class="table">
				<tr>
					<td height="30px" class="biaoti" colspan="4">
						<font color="red">※</font>&nbsp;&nbsp;<bean:message key="comm.jsp.different.text34" bundle="conf.comm.comm"/>&nbsp;&nbsp;
						<font color="red">※</font>
					</td>
				</tr>
				<tr>
					<td class="qian" nowrap>
						<font color="red">*</font> <bean:message key="comm.jsp.common.text10" bundle="conf.comm.comm"/>：
					</td>
					<td class="hou" nowrap>
						<input type="text" class="kuandu" name="classCode" size="20" maxlength="16" onkeydown="huiche()"  max="32" dataType="LimitB" msg="代码输入过长" value="<%=data.getClassCode()%>" />
					</td>
					<td class="qian" nowrap>
						<font color="red">*</font> <bean:message key="comm.jsp.common.text29" bundle="conf.comm.comm"/>：
					</td>
					<td class="hou" nowrap>
						<input type="text" class="kuandu" name="className" size="50" maxlength="20" onkeydown="huiche()"  max="60" dataType="LimitB" msg="名称输入过长" value="<%=data.getClassName()%>" />
					</td>
				</tr>
				<tr>
					<td class="qian" nowrap>
						<bean:message key="comm.jsp.common.text48" bundle="conf.comm.comm"/>：
					</td>
					<td class="hou" nowrap>
						<input type="text" class="kuandu" name="seqNo" readonly="readonly" size="30" maxlength="20" onkeydown="huiche()"  max="11" dataType="LimitB" msg="序号输入过长"  value="<%=data.getSeqNo()%>" />
					</td>
					<td class="qian" nowrap>
						<bean:message key="comm.jsp.common.text4" bundle="conf.comm.comm"/>：
					</td>
					<td class="hou" nowrap>
						<input type="text" class="kuandu" name="comments" size="30" maxlength="20"  max="40" dataType="LimitB" msg="备注输入过长"  value="<%=data.getComments()%>" onkeydown="huiche()"/>
					</td>
				</tr>
			</table>
			<!-- Sheet operation button area -->
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td height="30" align="center">
						<input type="button" name="btnSave" id="btnSave"  name="btnSaveForm" value="<bean:message key="comm.jsp.common.text3" bundle="conf.comm.comm"/>" onClick="saveForm()" />
						<input type="button" name="btnHistory" id="btnHistory"  name="btnBack" value="<bean:message key="comm.jsp.common.text19" bundle="conf.comm.comm"/>" onClick="goback();" />
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>
