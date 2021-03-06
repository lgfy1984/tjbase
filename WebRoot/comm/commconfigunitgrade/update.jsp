<%@ page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="bean" uri="/WEB-INF/struts-bean.tld"%>

<jsp:useBean id="commConfigUnitgrade" scope="request" class="com.tianjian.comm.struts.form.CommConfigUnitgradeForm" />
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
		<title><bean:message key="comm.jsp.commconfigunitgrade.update.title" bundle="conf.comm.CommMessageguoh"/></title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<script language="javascript" src='<bean:message key="comm.js.TJMessage" bundle="conf.comm.CommMessageguoh"/>'></script>
		<script language="javascript" src="<bean:message key="includes.js.validator.path" bundle="security" />" defer="defer"></script>
		<script language="javascript" src="include/javascript/eventOnKeyPress.js"></script>
		<script language="javascript">
function saveForm(){
    if(!Validator.Validate(document.forms.form,3)){
      return ;
   }
	if(document.form.itemCode.value == ""){
	 	alertMessage("0-000001");
	 	return ;
	}
	if(document.form.itemName.value == ""){
	 	alertMessage("0-000002");
	 	return ;
	}
	if(isNaN(document.form.seqNo.value)){
		alert('<bean:message key="comm.jsp.commconfigunitgrade.update.saveForm1" bundle="conf.comm.CommMessageguoh"/>'); 
		return true;
	}

	 if (confirmMessage("0-000005")){
	document.form.verbId.value = "update";
	document.form.submit();
	}
}


</script>
		<link type="text/css" rev="stylesheet" rel="stylesheet" href="include/css/form.css" />
	</head>
	<body onload="showMessage('','<%=commConfigUnitgrade.getMessage()%>','')">
		<form name="form" method="post" action="comm/commConfigUnitgrade.do">
			<input type="hidden" name="verbId" value="update" />
			<input type="hidden" name="itemCodeHidden" value=<%=commConfigUnitgrade.getItemCodeHidden()%> />
			<table  align="center" border="0" cellpadding="0" cellspacing="0" class="tblFill" border="0" cellpadding="0" cellspacing="0" class="tblFill">
				<tr>
					<td class="tblTitle" colspan="4"><span>※</span><bean:message key="comm.jsp.commconfigunitgrade.update.td1" bundle="conf.comm.CommMessageguoh"/><span>※</span></td>
				</tr>
				<tr>
					<td class="tblLable">
						<span>*</span> <bean:message key="comm.jsp.commconfigtrue.add.td3" bundle="conf.comm.CommMessageguoh"/>
					</td>
					<td>
						<input type="text"  name="itemCode" size="20" maxlength="9" onkeypress="eventOnKeyPress('itemName')" value="<%=commConfigUnitgrade.getItemCode()%>" readonly/>
					</td>
					<td class="tblLable">
						<span>*</span> <bean:message key="comm.jsp.commconfigtrue.add.td4" bundle="conf.comm.CommMessageguoh"/>
					</td>
					<td>
						<input type="text" name="itemName" size="50" maxlength="50" onkeypress="eventOnKeyPress('seqNo')"  max="40" dataType="LimitB" msg="名称输入过长"  value="<%=commConfigUnitgrade.getItemName()%>" />
					</td>
				</tr>
				<tr>
					<td class="tblLable">
						<bean:message key="comm.jsp.commconfigtrue.add.td2" bundle="conf.comm.CommMessageguoh"/>
					</td>
					<td>
						<input type="text"  name="seqNo" id="seqNo" size="20" maxlength="6" onkeypress="eventOnKeyPress('comments')"  vmax="11" dataType="LimitB" msg="序号输入过长"  value="<%=commConfigUnitgrade.getSeqNo()%>" />
					</td>
					<td class="tblLable">
						<bean:message key="comm.jsp.commconfigtrue.add.td5" bundle="conf.comm.CommMessageguoh"/>
					</td>
					<td>
						<input type="text"  name="comments" id="comments" size="50" maxlength="50" onkeypress="eventOnKeyPress('btnSaveForm')"   max="40" dataType="LimitB" msg="备注输入过长" value="<%=commConfigUnitgrade.getComments()%>" />
					</td>
				</tr>
			</table>
			<!-- Sheet operation button area -->
			<div class="btnSave">
				<input type="button"  name="btnSaveForm" value='<bean:message key="comm.jsp.commconfigtrue.add.td6" bundle="conf.comm.CommMessageguoh"/>' onClick="saveForm()" />
				<input type="button"  name="btnBack" value='<bean:message key="comm.jsp.commconfigtrue.add.td7" bundle="conf.comm.CommMessageguoh"/>' onClick="history.go(-1);" />
			</div>
		</form>
	</body>
</html>
