<%@ page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<jsp:useBean id="commConfigSettype" scope="request" class="com.tianjian.comm.struts.form.CommConfigSettypeForm" />
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
		<title><bean:message  bundle="comm.commLocale" key="comm.jsp.commconfigeconkindupdate.text55"/></title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<script language="javascript" src="<bean:message  key="comm.js.includeTJMessage.path"  bundle="comm.commLocale"/>"></script>
		<script language="javascript" src="<bean:message key="includes.js.validator.path" bundle="security" />" defer="defer"></script>
		<script language="javascript" src="include/javascript/eventOnKeyPress.js"></script>
		<script language="javascript">
function saveForm(){
	 document.form.isQuery.value = "yes";
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
		alert("<bean:message  bundle="comm.commLocale" key="comm.jsp.commom.text33"/>!"); 
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
	<body onload="showMessage('','<%=commConfigSettype.getMessage()%>','')">
		<form name="form" method="post" action="comm/commConfigSettype.do">
			<input type="hidden" name="verbId" value="update" />
			<input type="hidden" name="isQuery" value="<%=request.getParameter("isQuery") %>"/>
			<input type="hidden" name="itemCodeHidden" value=<%=commConfigSettype.getItemCodeHidden()%> />
			<table align="center" border="0" cellpadding="0" cellspacing="0" class="tblFill" border="0" cellpadding="0" cellspacing="0" class="tblFill">
				<tr>
					<td class="tblTitle" colspan="4"><span>※</span>修改设置主办单位字典 <span>※</span></td>
				</tr>
				<tr>
					<td class="tblLable">
						<span>*</span><bean:message  bundle="comm.commLocale" key="comm.jsp.commom.code"/>：
					</td>
					<td>
						<input type="text" class="kuandu" name="itemCode" size="20" maxlength="9" onkeypress="eventOnKeyPress('itemName')" value="<%=commConfigSettype.getItemCode()%>" readonly/>
					</td>
					<td class="tblLable">
						<span>*</span><bean:message  bundle="comm.commLocale" key="comm.jsp.commom.name"/>：
					</td>
					<td>
						<input type="text" class="kuandu" name="itemName" size="50" maxlength="50" onkeypress="eventOnKeyPress('seqNo')" max="40" dataType="LimitB" msg="名称输入过长"  value="<%=commConfigSettype.getItemName()%>" />
					</td>
				</tr>
				<tr>
					<td class="tblLable">
						<bean:message  bundle="comm.commLocale" key="comm.jsp.commom.seqNo"/>：
					</td>
					<td>
						<input type="text" class="kuandu" name="seqNo" id="seqNo" size="20" maxlength="6" onkeypress="eventOnKeyPress('comments')"  max="11" dataType="LimitB" msg="序号输入过长"  value="<%=commConfigSettype.getSeqNo()%>" />
					</td>
					<td class="tblLable">
						<bean:message  bundle="comm.commLocale" key="comm.jsp.commom.comments"/>：
					</td>
					<td>
						<input type="text" class="kuandu" name="comments" id="comments" size="50" maxlength="50" onkeypress="eventOnKeyPress('btnSaveForm')"  max="40" dataType="LimitB" msg="备注输入过长"  value="<%=commConfigSettype.getComments()%>" />
					</td>
				</tr>
			</table>
			<!-- Sheet operation button area -->
			<div class="btnSave">
				<input type="button"  name="btnSaveForm" value="<bean:message  bundle="comm.commLocale" key="comm.jsp.commom.save"/>" onClick="saveForm()" />
				<input type="button"  name="btnBack" value="<bean:message  bundle="comm.commLocale" key="comm.jsp.commom.back"/>" onClick="history.go(-1);" />
			</div>
		</form>
	</body>
</html>
