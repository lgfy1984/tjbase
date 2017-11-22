<%@ page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="bean" uri="/WEB-INF/struts-bean.tld"%>
<jsp:useBean id="commConfigSystemtype" scope="request" class="com.tianjian.comm.struts.form.CommConfigSystemtypeForm" />
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
		<title><bean:message key="comm.jsp.commconfigsystemtype.update.title" bundle="conf.comm.CommMessageguoh"/></title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<script language="javascript" src="include/javascript/TJMessage.js"></script>
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
						alert("\""+itermname+"\""+'<bean:message key="comm.jsp.commconfigsystemtype.update.yznumber1" bundle="conf.comm.CommMessageguoh"/>'+integer+'<bean:message key="comm.jsp.commconfigsystemtype.update.yznumber2" bundle="conf.comm.CommMessageguoh"/>');
						return false;
					}
					if(arg2.length > decimal){
						alert("\""+itermname+"\""+'<bean:message key="comm.jsp.commconfigsystemtype.update.yznumber3" bundle="conf.comm.CommMessageguoh"/>'+decimal+'<bean:message key="comm.jsp.commconfigsystemtype.update.yznumber4" bundle="conf.comm.CommMessageguoh"/>');
						return false;
					}				
				}else{
					if(inputvalue.length > integer){
						alert("\""+itermname+"\""+'<bean:message key="comm.jsp.commconfigsystemtype.update.yznumber5" bundle="conf.comm.CommMessageguoh"/>'+integer+'<bean:message key="comm.jsp.commconfigsystemtype.update.yznumber6" bundle="conf.comm.CommMessageguoh"/>');						
						return false;
					}
				}			
			}else{
				alert("\""+itermname+"\"<bean:message key="comm.jsp.commconfigsystemtype.update.yznumber7" bundle="conf.comm.CommMessageguoh"/>");
				return false;
				}
			}		
		}
function saveForm(){
	if(document.form.itemCode.value == ""){
	 	alertMessage("0-000001");
	 	return ;
	}
	if(document.form.itemName.value == ""){
	 	alertMessage("0-000002");
	 	return ;
	}
	if(document.form.seqNo.value !== null){ 
	if(yznumber('seqNo','<bean:message key="comm.jsp.commconfigsystemtype.update.saveForm" bundle="conf.comm.CommMessageguoh"/>','6','2')==false){
				return;
		}
	}

	 if (confirmMessage("0-000005")){
	document.form.verbId.value = "update";
	document.form.submit();
	}
}

</script>
		<link rel="stylesheet" rev="stylesheet" href="comm/include/css/comm_add.css" />
	</head>
	<body onLoad="showMessage('','<%=commConfigSystemtype.getMessage()%>','')">
		<form name="form" method="post" action="comm/commConfigSystemtype.do">
			<input type="hidden" name="verbId" value="update" />
			<input type="hidden" name="itemCodeHidden" value=<%=commConfigSystemtype.getItemCodeHidden()%> />
			<table border="0" cellspacing="1" cellpadding="0" class="table">
				<tr>
					<td height="30px" class="biaoti1" colspan="4">
						<font color="red">※</font>&nbsp;&nbsp;<bean:message key="comm.jsp.commconfigsystemtype.update.td1" bundle="conf.comm.CommMessageguoh"/>&nbsp;&nbsp;
						<font color="red">※</font>
					</td>
				</tr>
				<tr>
					<td class="qian" nowrap>
						<font color="red">*</font> <bean:message key="comm.jsp.commconfigsystemtype.update.td2" bundle="conf.comm.CommMessageguoh"/>
					</td>
					<td class="hou" nowrap>
						<input type="text" class="kuandu" name="itemCode" size="20" maxlength="9" onKeyPress="eventOnKeyPress('itemName')" value="<%=commConfigSystemtype.getItemCode()%>" readonly/>
					</td>
					<td class="qian" nowrap>
						<font color="red">*</font> <bean:message key="comm.jsp.commconfigsystemtype.update.td3" bundle="conf.comm.CommMessageguoh"/>
					</td>
					<td class="hou" nowrap>
						<input type="text" class="kuandu" name="itemName" size="50" maxlength="20" onKeyPress="eventOnKeyPress('seqNo')" value="<%=commConfigSystemtype.getItemName()%>" />
					</td>
				</tr>
				<tr>
					<td class="qian" nowrap>
						<bean:message key="comm.jsp.commconfigsystemtype.update.td5" bundle="conf.comm.CommMessageguoh"/>
					</td>
					<td class="hou" nowrap>
						<input type="text" class="kuandu" name="seqNo" id="seqNo" size="20" maxlength="6" onKeyPress="eventOnKeyPress('comments')" value="<%=commConfigSystemtype.getSeqNo()%>" />
					</td>
					<td class="qian" nowrap>
						<bean:message key="comm.jsp.commconfigsystemtype.update.td6" bundle="conf.comm.CommMessageguoh"/>
					</td>
					<td class="hou" nowrap>
						<input type="text" class="kuandu" name="comments" id="comments" onKeyPress="eventOnKeyPress('btnSave')" size="50" maxlength="20" value="<%=commConfigSystemtype.getComments()%>" />
					</td>
				</tr>
			</table>
			<!-- Sheet operation button area -->
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td height="30" align="center">
						<input type="button" id="btnSave" name="btnSave" style="cursor: hand; background: url(include/images/tong.gif) no-repeat; border: 0; width: 50px; height: 23px; font-size: 12px;" name="btnSaveForm" value='<bean:message key="comm.jsp.commconfigsystemtype.update.td7" bundle="conf.comm.CommMessageguoh"/>' onClick="saveForm()" />
						<input type="button" id="btnHistory" name="btnHistory" style="cursor: hand; background: url(include/images/tong.gif) no-repeat; border: 0; width: 50px; height: 23px; font-size: 12px;" name="btnBack" value='<bean:message key="comm.jsp.commconfigsystemtype.update.td4" bundle="conf.comm.CommMessageguoh"/>' onClick="history.go(-1);" />
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>
