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
		<title><bean:message key="comm.jsp.commconfigsystemtype.add.title" bundle="conf.comm.CommMessageguoh"/></title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<script language="javascript" src='<bean:message key="comm.js.TJMessage" bundle="conf.comm.CommMessageguoh"/>'></script>
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
                       alert("\""+itermname+"\""+'<bean:message key="comm.jsp.commconfigrh.add.yznumber5" bundle="conf.comm.CommMessageguoh"/>'+integer+'<bean:message key="comm.jsp.commconfigrh.add.yznumber6" bundle="conf.comm.CommMessageguoh"/>');						return false;
					}
				}			
			}else{
				alert("\""+itermname+"\""+'<bean:message key="comm.jsp.commconfigrh.add.yznumber7" bundle="conf.comm.CommMessageguoh"/>');
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
	if(yznumber('seqNo','<bean:message key="comm.jsp.commconfigsystemtype.add.saveForm" bundle="conf.comm.CommMessageguoh"/>','6','2')==false){
				return;
		}
	}
	
if (confirmMessage("0-000003")){ 
	document.form.verbId.value = "add";
	document.form.submit();
	}
}

</script>
		<link rel="stylesheet" rev="stylesheet" href="comm/include/css/comm_add.css" />
	</head>
	<body onLoad="showMessage('','<%=commConfigSystemtype.getMessage()%>','1')">
		<form name="form" method="post" action="comm/commConfigSystemtype.do">
			<input type="hidden" name="verbId" value="add" />
			<table border="0" cellspacing="1" cellpadding="0" class="table" align="center">
				<tr>
					<td height="30px" class="biaoti1" colspan="4">
						<font color="red">※</font>&nbsp;&nbsp;<bean:message key="comm.jsp.commconfigsystemtype.add.td1" bundle="conf.comm.CommMessageguoh"/>&nbsp;&nbsp;
						<font color="red">※</font>
					</td>
				</tr>
				<tr>
					<td class="qian" nowrap>
						<font color="red">*</font> <bean:message key="comm.jsp.commconfigsystemtype.add.td2" bundle="conf.comm.CommMessageguoh"/>
					</td>
					<td class="hou" nowrap>
						<input type="text" class="kuandu" name="itemCode" size="20" maxlength="9" onKeyPress="eventOnKeyPress('itemName')" value="<%=commConfigSystemtype.getItemCode()%>" />
					</td>
					<td class="qian" nowrap>
						<font color="red">*</font> <bean:message key="comm.jsp.commconfigsystemtype.add.td3" bundle="conf.comm.CommMessageguoh"/>
					</td>
					<td class="hou" nowrap>
						<input type="text" class="kuandu" name="itemName" size="50" maxlength="20" onKeyPress="eventOnKeyPress('seqNo')" value="<%=commConfigSystemtype.getItemName()%>" />
					</td>
				</tr>
				<tr>
					<td class="qian" nowrap>
						<bean:message key="comm.jsp.commconfigsystemtype.add.td4" bundle="conf.comm.CommMessageguoh"/>
					</td>
					<td class="hou" nowrap>
						<input type="text" class="kuandu" name="seqNo" id="seqNo" size="30" maxlength="20" onKeyPress="eventOnKeyPress('comments')" value="<%=commConfigSystemtype.getSeqNo()%>" />
					</td>
					<td class="qian" nowrap>
						<bean:message key="comm.jsp.commconfigsystemtype.add.td5" bundle="conf.comm.CommMessageguoh"/>
					</td>
					<td class="hou" nowrap>
						<input type="text" class="kuandu" name="comments" id="comments" size="30" maxlength="20" onKeyPress="eventOnKeyPress('btnSave')" value="<%=commConfigSystemtype.getComments()%>" />
					</td>
				</tr>
			</table>
			<!-- Sheet operation button area -->
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td height="30" align="center">
						<input type="button" id="btnSave" name="btnSave" style="cursor: hand; background: url(include/images/tong.gif) no-repeat; border: 0; width: 50px; height: 23px; font-size: 12px;" name="btnSaveForm" value='<bean:message key="comm.jsp.commconfigsystemtype.add.td6" bundle="conf.comm.CommMessageguoh"/>' onClick="saveForm()" />
						<input type="button" id="btnHistory" name="btnHistory" style="cursor: hand; background: url(include/images/tong.gif) no-repeat; border: 0; width: 50px; height: 23px; font-size: 12px;" name="btnBack" value='<bean:message key="comm.jsp.commconfigsystemtype.add.td7" bundle="conf.comm.CommMessageguoh"/>' onClick="history.go(-1);" />
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>
