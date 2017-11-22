<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="html" uri="/WEB-INF/struts-html.tld"%>
<%@ taglib prefix="bean" uri="/WEB-INF/struts-bean.tld"%>
<%@ taglib prefix="logic" uri="/WEB-INF/struts-logic.tld"%>
<jsp:useBean id="data" scope="request" class="com.tianjian.comm.struts.form.CommConfigPopulationNatureForm" />
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
		<title><bean:message key="comm.jsp.common.addPersonMessage" bundle="conf.comm.Comm"/></title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<script language="javascript" src="<bean:message key="comm.js.comm.tjme" bundle="conf.comm.Comm"/>"></script>		
        <script language="javascript" src="<bean:message key="includes.js.validator.path" bundle="security" />" defer="defer"></script>
		<script language="javascript" src="include/javascript/eventOnKeyPress.js"></script>
		<script language="javascript">
		function trim(str){  //删除左右两端的空格
			return str.replace(/(^\s*)|(\s*$)/g, "");
		}
		
function saveForm(){
	var itemCode = trim(document.form.itemCode.value);
	 if(!Validator.Validate(document.forms.form,3)){
      return ;
    }
	if(itemCode == ""){
	 	alertMessage("0-000001");
	 	return ;
	}
	if(itemCode.length>16){
		alert("<bean:message key="comm.jsp.common.alert7" bundle="conf.comm.Comm"/>"+itemCode.length+"<bean:message key="comm.jsp.common.wei" bundle="conf.comm.Comm"/>");
	}
	var itemName = trim(document.form.itemName.value);
	if(itemName == ""){
	 	alertMessage("0-000002");
	 	return ;
	}
	if(itemName.length>20){
		alert("<bean:message key="comm.jsp.common.alert10" bundle="conf.comm.Comm"/>"+itemName.length+"<bean:message key="comm.jsp.common.wei" bundle="conf.comm.Comm"/>");
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
	document.form.itemCode.value="";
	document.form.itemName.value="";
	document.form.verbId.value = "query";
	document.form.submit();
}
</script>
		<link rel="stylesheet" rev="stylesheet" href="comm/include/css/comm_add.css" />
	</head>
	<body onload="showMessage('','<%=data.getMessage()%>','1')">
		<form name="form" method="post" action="comm/commPopulationNature.do">
			<input type="hidden" name="verbId" value="add" />
			<input type="hidden" name="page" value="<%=data.getMypage() %>" />
			<table border="0" cellspacing="1" cellpadding="0" class="table">
				<tr>
					<td height="30px" class="biaoti" colspan="4">
						<font color="red">※</font>&nbsp;&nbsp;<bean:message key="comm.jsp.common.addPerson" bundle="conf.comm.Comm"/>&nbsp;&nbsp;
						<font color="red">※</font>
					</td>
				</tr>
				<tr>
					<td class="qian" nowrap>
						<font color="red">*</font> <bean:message key="comm.jsp.common.item" bundle="conf.comm.Comm"/>：
					</td>
					<td class="hou" nowrap>
						<input type="text" class="kuandu" name="itemCode" size="20" maxlength="16" onkeydown="huiche()" max="32" dataType="LimitB" msg="代码输入过长" value="<%=data.getItemCode()%>" />
					</td>
					<td class="qian" nowrap>
						<font color="red">*</font> <bean:message key="comm.jsp.common.names" bundle="conf.comm.Comm"/>：
					</td>
					<td class="hou" nowrap>
						<input type="text" class="kuandu" name="itemName" size="50" maxlength="20" onkeydown="huiche()"  max="40" dataType="LimitB" msg="名称输入过长" value="<%=data.getItemName()%>" />
					</td>
				</tr>
				<tr>
					<td class="qian" nowrap>
						<bean:message key="comm.jsp.common.seqNo" bundle="conf.comm.Comm"/>：
					</td>
					<td class="hou" nowrap>
						<input type="text" class="kuandu" name="seqNo"  size="30" maxlength="20" onkeydown="huiche()"  max="11" dataType="LimitB" msg="序号输入过长"  value="<%=data.getSeqNo()%>" />
					</td>
					<td class="qian" nowrap>
						<bean:message key="comm.jsp.common.comments" bundle="conf.comm.Comm"/>：
					</td>
					<td class="hou" nowrap>
						<input type="text" class="kuandu" name="comments" size="30" maxlength="20" value="<%=data.getComments()%>"   max="40" dataType="LimitB" msg="备注输入过长"  onkeydown="huiche()"/>
					</td>
				</tr>
			</table>
			<!-- Sheet operation button area -->
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td height="30" align="center">
						<input type="button" name="btnSave" id="btnSave"
						 name="btnSaveForm" value="<bean:message key="comm.jsp.common.save" bundle="conf.comm.Comm"/>" onClick="saveForm()" />
						<input type="button" name="btnHistory" id="btnHistory" 
						name="btnBack" value="<bean:message key="comm.jsp.common.return" bundle="conf.comm.Comm"/>" onClick="goback();" />
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>
