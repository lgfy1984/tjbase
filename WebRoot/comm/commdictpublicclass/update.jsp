<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="html" uri="/WEB-INF/struts-html.tld"%>
<%@ taglib prefix="bean" uri="/WEB-INF/struts-bean.tld"%>
<%@ taglib prefix="logic" uri="/WEB-INF/struts-logic.tld"%>
<jsp:useBean id="commDictPublicClass" scope="request" class="com.tianjian.comm.struts.form.CommDictPublicClassForm" />
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
		<title><bean:message key="Comm.jsp.commom.modifyDictTypeInfo" bundle="conf.comm.CommMessage" />
		</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<script language="javascript" src="<bean:message key="includes.js.validator.path" bundle="security" />" defer="defer"></script>
		<script language="javascript" src="<bean:message  key="Comm.js.TJMessagepath"  bundle="conf.comm.CommMessage"/>"></script>
		<script language="javascript" src="include/javascript/eventOnKeyPress.js"></script>
		<script language="javascript" src="include/javascript/examQueryArgsInputs.js"></script>
		<link type="text/css" rev="stylesheet" rel="stylesheet" href="include/css/form.css" />
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
						alert("\""+itermname+"\""+"<bean:message key="Comm.js.commom.msg1" bundle="conf.comm.CommMessage" />"+integer+"<bean:message key="Comm.js.commom.msg8" bundle="conf.comm.CommMessage" />");
						return false;
					}
					if(arg2.length > decimal){
						alert("\""+itermname+"\""+"<bean:message key="Comm.js.commom.msg2" bundle="conf.comm.CommMessage" />"+decimal+"<bean:message key="Comm.js.commom.msg8" bundle="conf.comm.CommMessage" />");
						 return false;
					}				
				}else{
					if(inputvalue.length > integer){
						alert("\""+itermname+"\""+"<bean:message key="Comm.js.commom.msg3" bundle="conf.comm.CommMessage" />"+integer+"<bean:message key="Comm.js.commom.msg8" bundle="conf.comm.CommMessage" />");
					 	return false;
					}
				}			
			}else{
			alert("\""+itermname+"\"<bean:message key="Comm.js.commom.msg7" bundle="conf.comm.CommMessage" />");
			 return false;
				}
			}		
		}
function saveForm(){
	//if(document.form.classCode.value == ""){
	 // en(); 
		//ymPrompt.alert({message:'<bean:message key="comm.jsp.common.text31" bundle="conf.comm.comm"/>'});
	 	
	 // return ;
	//}
	if(!Validator.Validate(document.forms.form,3)){
     	 return ;
   	}
	if(document.form.className.value == ""){
		alert('<bean:message key="comm.jsp.common.text32" bundle="conf.comm.comm"/>');
	    return ;
	}
	
	for(var i=0; i<document.form.seqNo.value.length; i++){
		if(document.form.seqNo.value.charAt(i)<'0' || document.form.seqNo.value.charAt(i)>'9')
		{
			alert('<bean:message key="Comm.js.commom.msg9" bundle="conf.comm.CommMessage"/>');
			return ;
		}
    } 
	if(/\D/.test(document.form.seqNo.value)){
		alert("<bean:message  bundle='comm.commLocale' key='comm.jsp.warn1'/>");
		return;
	}
	//修改
	if (confirmMessage("<bean:message key='comm.jsp.update.gengxin' bundle='comm.commLocale'/>")){     
	    document.form.verbId.value = "update";    
	    document.form.submit(); 
    } 
}
	function showHspMessage(message){
				if(message != ''&& message != null){
					alert(message);
					return;
				}
			}

</script>
	</head>
	<body
		onload="showHspMessage('<%=commDictPublicClass.getMessage()%>')">
		<form name="form" method="post" action="comm/commDictPublicClass.do">
			<input type="hidden" name="verbId" value="update" />
			<input type="hidden" name="classCodeHidden"
				value=<%=commDictPublicClass.getClassCodeHidden()%> />
			<table border="0" cellpadding="0" cellspacing="0" class="tblFill" align="center">
				<tr>
					 <td class="tblTitle" colspan="4"><span>※</span>
						<bean:message key="Comm.jsp.commom.modifyDictType"
							bundle="conf.comm.CommMessage" />
						<span>※</span></td>
				</tr>
				<tr>
					<td class="tblLable">
						<span>*</span>
						<bean:message key="Comm.jsp.commom.typeCode"
							bundle="conf.comm.CommMessage" />:
					</td>
					<td>
						<input type="text" class="kuandu" name="classCode" size="20"
							maxlength="9" onkeypress="eventOnKeyPress('className')"
							value="<%=commDictPublicClass.getClassCode()%>" readonly />
					</td>
					<td class="tblLable">
						<span>*</span>
						<bean:message key="Comm.jsp.commom.typeName"
							bundle="conf.comm.CommMessage" />:
					</td>
					<td>
						<input type="text" class="kuandu" name="className" size="50"
							maxlength="50" onkeypress="eventOnKeyPress('seqNo')"
							max="60" dataType="LimitB" msg="类别名称输入过长"
							value="<%=commDictPublicClass.getClassName()%>" />
					</td>
				</tr>
				<tr>
					<td class="tblLable">
						<bean:message key="jsp.seqNo" bundle="conf.Init" />:
					</td>
					<td>
						<input type="text" class="kuandu" name="seqNo" id="seqNo"
							size="20" maxlength="11" onkeypress="eventOnKeyPress('comments')"
							value="<%=commDictPublicClass.getSeqNo()%>" readonly />
					</td>
					<td class="tblLable">
						<bean:message key="jsp.comments" bundle="conf.Init" />:
					</td>
					<td>
						<input type="text" class="kuandu" name="comments" id="comments"
							size="50" maxlength="40" max="40" dataType="LimitB" msg="备注输入过长"
							onkeypress="eventOnKeyPress('btnSaveForm')"
							value="<%=commDictPublicClass.getComments()%>" />
					</td>
				</tr>
			</table>
			<!-- Sheet operation button area -->
			<div class="btnSave">
				<input type="button" name="btnSaveForm"
							style="font-size: 12px; font-family: Arial;"
							value="<bean:message key="jsp.btnSaveForm" bundle="conf.Init"/>"
							onClick="saveForm()" />
				<input type="button" name="btnBack"
							style="font-size: 12px; font-family: Arial;"
							value="<bean:message key="jsp.btnBack" bundle="conf.Init"/>"
							onClick="history.go(-1);" />
			</div>
		</form>
	</body>
</html>
