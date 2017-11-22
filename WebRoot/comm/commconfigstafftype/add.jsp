<%@ page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="bean" uri="/WEB-INF/struts-bean.tld"%>
<jsp:useBean id="commConfigStafftype" scope="request" class="com.tianjian.comm.struts.form.CommConfigStafftypeForm" />
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
		<title><bean:message key="comm.jsp.commconfigstafftype.add.title" bundle="conf.comm.CommMessageguoh"/></title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<script language="javascript" src='<bean:message key="comm.js.TJMessage" bundle="conf.comm.CommMessageguoh"/>'></script>
		<script language="javascript" src="<bean:message key="includes.js.validator.path" bundle="security" />" defer="defer"></script>
		<script language="javascript" src="include/javascript/eventOnKeyPress.js"></script>
		<script language="javascript">
		function trim(str){
 				return str.replace(/(^\s*)|(\s*$)/g, "");
			}
function saveForm(){
	if(!Validator.Validate(document.forms.form,3)){
      return ;
    }
	if(document.form.itemCode.value == ""){
	 	alertMessage("0-000001");
	 	return ;
	}else{
		var reg1 = /^[a-zA-Z0-9|_]+$/;
		if(!reg1.test(trim(document.form.itemCode.value)))
		{
			alert('<bean:message key="comm.jsp.commconfigstafftype.add.saveForm1" bundle="conf.comm.CommMessageguoh"/>');
			return;
		}
	}
	
	if(document.form.itemName.value == ""){
	 	alertMessage("0-000002");
	 	return ;
	}
	
	if(isNaN(document.form.seqNo.value)){
		alert('<bean:message key="comm.jsp.commconfigstafftype.add.saveForm2" bundle="conf.comm.CommMessageguoh"/>'); 
		return true;
	}
	
if (confirmMessage("0-000003")){ 
	document.form.verbId.value = "add";
	document.form.submit();
	}
}

</script>
	<link type="text/css" rev="stylesheet" rel="stylesheet" href="include/css/form.css" />
	</head>
	<body onload="showMessage('','<%=commConfigStafftype.getMessage()%>','1')">
		<form name="form" method="post" action="comm/commConfigStafftype.do">
			<input type="hidden" name="verbId" value="add" />
			<table border="0" cellpadding="0" cellspacing="0" class="tblFill" align="center">
				<tr>
					<td class="tblTitle" colspan="4"><span>※</span> <bean:message key="comm.jsp.commconfigstafftype.add.td1" bundle="conf.comm.CommMessageguoh"/> <span>※</span></td>
				</tr>
				<tr>
					<td class="tblLable">
						<span>*</span> <bean:message key="comm.jsp.commconfigstafftype.add.td2" bundle="conf.comm.CommMessageguoh"/>
					</td>
					<td>
						<input type="text" name="itemCode" size="20" maxlength="32" onkeypress="eventOnKeyPress('itemName')"  max="32" dataType="LimitB" msg="代码输入过长" value="<%=commConfigStafftype.getItemCode()%>" />
					</td>
					<td class="tblLable">
						<span>*</span> <bean:message key="comm.jsp.commconfigstafftype.add.td3" bundle="conf.comm.CommMessageguoh"/>
					</td>
					<td>
						<input type="text"  name="itemName" size="50" maxlength="50" onkeypress="eventOnKeyPress('seqNo')" max="40" dataType="LimitB" msg="名称输入过长"  value="<%=commConfigStafftype.getItemName()%>" />
					</td>
				</tr>
				<tr>
					<td class="tblLable">
						<bean:message key="comm.jsp.commconfigstafftype.add.td4" bundle="conf.comm.CommMessageguoh"/>
					</td>
					<td>
						<input type="text" name="seqNo" id="seqNo" size="30" maxlength="11" onkeypress="eventOnKeyPress('comments')"  max="11" dataType="LimitB" msg="序号输入过长"  value="<%=commConfigStafftype.getSeqNo()%>" />
					</td>
					<td class="tblLable">
						<bean:message key="comm.jsp.commconfigstafftype.add.td5" bundle="conf.comm.CommMessageguoh"/>
					</td>
					<td>
						<input type="text"  name="comments" id="comments" size="30" maxlength="50" onkeypress="eventOnKeyPress('btnSaveForm')"  max="40" dataType="LimitB" msg="备注输入过长"  value="<%=commConfigStafftype.getComments()%>" />
					</td>
				</tr>
			</table>
			<!-- Sheet operation button area -->
			<div class="btnSave">
				<input type="button"  name="btnSaveForm" value='<bean:message key="comm.jsp.commconfigstafftype.add.td6" bundle="conf.comm.CommMessageguoh"/>' onClick="saveForm()" />
				<input type="button"  name="btnBack" value='<bean:message key="comm.jsp.commconfigstafftype.add.td7" bundle="conf.comm.CommMessageguoh"/>' onClick="history.go(-1);" />
			</div>
		</form>
	</body>
</html>
