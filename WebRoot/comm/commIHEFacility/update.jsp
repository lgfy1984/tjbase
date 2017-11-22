<%@ page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<jsp:useBean id="data" scope="request" class="com.tianjian.comm.struts.form.CommIHEFacilityForm" />
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
		<title><bean:message  bundle="comm.comIHE" key="comm.jsp.commIHEFacility.update"/></title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<script language="javascript" src="<bean:message  key="comm.js.includeTJMessage.path"  bundle="comm.commLocale"/>"></script>
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
						alert("\""+itermname+"\""+"<bean:message  bundle="comm.commLocale" key="comm.jsp.commom.integerBigThan"/>"+integer+"<bean:message  bundle="comm.commLocale" key="comm.jsp.commom.text28"/>!");
						return false;
					}
					if(arg2.length > decimal){
						alert("\""+itermname+"\""+"<bean:message  bundle="comm.commLocale" key="comm.jsp.commom.decimalLessThan"/>"+decimal+"<bean:message  bundle="comm.commLocale" key="comm.jsp.commom.text28"/>!");
						return false;
					}				
				}else{
					if(inputvalue.length > integer){
						alert("\""+itermname+"\""+"<bean:message  bundle="comm.commLocale" key="comm.jsp.commom.integerBigThan"/>"+integer+"<bean:message  bundle="comm.commLocale" key="comm.jsp.commom.text28"/>!");						
						return false;
					}
				}			
			}else{
				alert("\""+itermname+"\"<bean:message  bundle="comm.commLocale" key="comm.jsp.commom.warn1"/>!");
				return false;
				}
			}		
		}
function saveForm(){
	if(document.form.facilityUniversalId.value == ""){
	 	alertMessage("0-000001");
	 	return ;
	}
	if(document.form.facilityNamespaceId.value == ""){
	 	alertMessage("0-000002");
	 	return ;
	}
	
	if(document.form.seqNo.value !== null){ 
	if(yznumber('seqNo','<bean:message  bundle="comm.commLocale" key="comm.jsp.commom.seqNo"/>','6','2')==false){
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
	<body onload="showMessage('','<%=data.getMessage()%>','')">
		<form name="form" method="post" action="comm/commIHEFacility.do">
			<input type="hidden" name="verbId" value="update" />
			<input type="hidden" name="idHidden" value=<%=data.getIdHidden()%> />
			<table border="0" cellspacing="1" cellpadding="0" class="table">
				<tr>
					<td height="30px" class="biaoti" colspan="4">
						<font color="red">※</font>&nbsp;&nbsp;<bean:message  bundle="comm.comIHE" key="comm.jsp.commIHEFacility.update"/>&nbsp;&nbsp;
						<font color="red">※</font>
					</td>
				</tr>
				<tr>
					<td class="qian" nowrap>
						<bean:message  bundle="comm.comIHE" key="comm.jsp.segment10"/>:
					</td>
					<td class="hou" nowrap>
						<input type="text" class="kuandu" name="seqNo" id="seqNo" size="20" maxlength="6" onkeypress="eventOnKeyPress('universalId')" value="<%=data.getSeqNo()%>" />
					</td>
					<td class="qian" nowrap>
						<font color="red">*</font><bean:message  bundle="comm.comIHE" key="comm.jsp.segment11"/>:
					</td>
					<td class="hou" nowrap>
						<input type="text" class="kuandu" name="facilityUniversalId" size="50" maxlength="50" onkeypress="eventOnKeyPress('universalIdType')" value="<%=data.getFacilityUniversalId()%>" />
					</td>
				</tr>
				<tr>
					<td class="qian" nowrap>
						<font color="red">*</font><bean:message  bundle="comm.comIHE" key="comm.jsp.segment12"/>:
					</td>
					<td class="hou" nowrap>
						<input type="text" class="kuandu" name="facilityUniversalIdType" size="20" maxlength="16" onkeypress="eventOnKeyPress('namespaceId')" value="<%=data.getFacilityUniversalIdType()%>" readonly/>
					</td>
					<td class="qian" nowrap>
						<bean:message  bundle="comm.comIHE" key="comm.jsp.segment13"/>:
					</td>
					<td class="hou" nowrap>
						<input type="text" class="kuandu" name="facilityNamespaceId" id="namespaceId" size="50" maxlength="50" value="<%=data.getFacilityNamespaceId()%>" onkeypress="eventOnKeyPress('btnSave')"/>
					</td>
				</tr>
			</table>
			<!-- Sheet operation button area -->
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td height="30" align="center">
						<input type="button" name="btnSave" id="btnSave"   value="<bean:message  bundle="comm.commLocale" key="comm.jsp.commom.save"/>" onClick="saveForm()" />
						<input type="button" name="btnHistory" id="btnHistory" value="<bean:message  bundle="comm.commLocale" key="comm.jsp.commom.back"/>" onClick="history.go(-1);" />
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>
