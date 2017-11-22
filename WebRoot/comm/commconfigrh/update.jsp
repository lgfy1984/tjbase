<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="bean" uri="/WEB-INF/struts-bean.tld"%>
<jsp:useBean id="commConfigRh" scope="request" class="com.tianjian.comm.struts.form.CommConfigRhForm" />
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
		<title><bean:message key="comm.jsp.commconfigrh.update.title" bundle="conf.comm.CommMessageguoh"/></title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<script language="javascript" src='<bean:message key="comm.js.TJMessage" bundle="conf.comm.CommMessageguoh"/>'></script>
		<script language="javascript" src="<bean:message key="includes.js.validator.path" bundle="security" />" defer="defer"></script>
		<script language="javascript" src="include/javascript/eventOnKeyPress.js"></script>
		<script language="javascript">
		function trim(str){  //删除左右两端的空格
			return str.replace(/(^\s*)|(\s*$)/g, "");
		}
		
		function yznumber(id,itermname,integer,decimal){
		var inputvalue= (document.getElementById(id)).value;
		if(inputvalue != ""){
			var reg = /^[0-9]+(\.[0-9]+)?$/;
			if(reg.test(inputvalue)){
				if(inputvalue.indexOf(".")!=-1){
					array = inputvalue.split(".");
					arg1 = array[0];
					arg2 = array[1];										
					if(arg1.length > integer){
						alert("\""+itermname+"\""+'<bean:message key="comm.jsp.commconfigrh.add.yznumber1" bundle="conf.comm.CommMessageguoh"/>'+integer+'<bean:message key="comm.jsp.commconfigrh.add.yznumber2" bundle="conf.comm.CommMessageguoh"/>');
						return false;
					}
					if(arg2.length > decimal){
							alert("\""+itermname+"\""+'<bean:message key="comm.jsp.commconfigrh.add.yznumber3" bundle="conf.comm.CommMessageguoh"/>'+decimal+'<bean:message key="comm.jsp.commconfigrh.add.yznumber4" bundle="conf.comm.CommMessageguoh"/>');
						return false;
					}				
				}else{
					if(inputvalue.length > integer){
						alert("\""+itermname+"\""+'<bean:message key="comm.jsp.commconfigrh.add.yznumber5" bundle="conf.comm.CommMessageguoh"/>'+integer+'<bean:message key="comm.jsp.commconfigrh.add.yznumber6" bundle="conf.comm.CommMessageguoh"/>');						
						return false;
					}
				}			
			}else{
				alert("\""+itermname+"\""+'<bean:message key="comm.jsp.commconfigrh.add.yznumber7" bundle="conf.comm.CommMessageguoh"/>');
				return false;
				}
			}		
		}
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
	if(document.form.seqNo.value !== null){ 
	if(yznumber('seqNo','<bean:message key="comm.jsp.commconfigrh.add.saveForm" bundle="conf.comm.CommMessageguoh"/>','6','2')==false){
				return;
		}
	}

	 if (confirmMessage("0-000005")){
	document.form.verbId.value = "update";
	document.form.submit();
	}
}

</script>
		<link type="text/css" rev="stylesheet" rel="stylesheet" href="include/css/form.css" />
	</head>
	<body onload="showMessage('','<%=commConfigRh.getMessage()%>','')">
		<form name="form" method="post" action="comm/commConfigRh.do">
			<input type="hidden" name="verbId" value="update" />
			<input type="hidden" name="itemCodeHidden" value=<%=commConfigRh.getItemCodeHidden()%> />
			<table align="center" border="0" cellpadding="0" cellspacing="0" class="tblFill" border="0" cellpadding="0" cellspacing="0" class="tblFill">
				<tr>
					<td class="tblTitle" colspan="4"><span>※</span><bean:message key="comm.jsp.commconfigrh.update.td1" bundle="conf.comm.CommMessageguoh"/><span>※</span></td>
				</tr>
				<tr>
					<td class="tblLable">
						<span>*</span> <bean:message key="comm.jsp.commconfigrh.update.td2" bundle="conf.comm.CommMessageguoh"/>
					</td>
					<td>
						<input type="text" name="itemCode" size="20" maxlength="9" onkeypress="eventOnKeyPress('itemName')" value="<%=commConfigRh.getItemCode()%>" readonly/>
					</td>
					<td class="tblLable">
						<span>*</span> <bean:message key="comm.jsp.commconfigrh.update.td3" bundle="conf.comm.CommMessageguoh"/>
					</td>
					<td>
						<input type="text" name="itemName" size="50" maxlength="20" onkeypress="eventOnKeyPress('seqNo')"  max="40" dataType="LimitB" msg="名称输入过长" value="<%=commConfigRh.getItemName()%>" />
					</td>
				</tr>
				<tr>
					<td class="tblLable">
						<bean:message key="comm.jsp.commconfigrh.update.td5" bundle="conf.comm.CommMessageguoh"/>
					</td>
					<td>
						<input type="text"  name="seqNo" id="seqNo" size="20" maxlength="6" onkeypress="eventOnKeyPress('comments')" max="11" dataType="LimitB" msg="序号输入过长"  value="<%=commConfigRh.getSeqNo()%>" />
					</td>
					<td class="tblLable">
						<bean:message key="comm.jsp.commconfigrh.update.td6" bundle="conf.comm.CommMessageguoh"/>
					</td>
					<td>
						<input type="text" name="comments" id="comments" size="50" maxlength="100" onkeypress="eventOnKeyPress('btnSave')"  max="40" dataType="LimitB" msg="备注输入过长" value="<%=commConfigRh.getComments()%>" />
					</td>
				</tr>
			</table>
			<!-- Sheet operation button area -->
			<div class="btnSave">
				<input type="button" id="btnSve" name="btnSave"  name="btnSaveForm" value='<bean:message key="comm.jsp.commconfigrh.update.td7" bundle="conf.comm.CommMessageguoh"/>' onClick="saveForm()" />
				<input type="button" id="btnHistory" name="btnHistory"  name="btnBack" value='<bean:message key="comm.jsp.commconfigrh.update.td4" bundle="conf.comm.CommMessageguoh"/>' onClick="history.go(-1);" />
			</div>
		</form>
	</body>
</html>
