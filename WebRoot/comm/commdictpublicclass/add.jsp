<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="html" uri="/WEB-INF/struts-html.tld"%>
<%@ taglib prefix="bean" uri="/WEB-INF/struts-bean.tld"%>
<%@ taglib prefix="logic" uri="/WEB-INF/struts-logic.tld"%>
<jsp:useBean id="commDictPublicClass" scope="request"
	class="com.tianjian.comm.struts.form.CommDictPublicClassForm" />
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
		<title><bean:message key="Comm.jsp.commom.addDictTypeInfo"
				bundle="conf.comm.CommMessage" />
		</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<script language="javascript" src="<bean:message key="includes.js.validator.path" bundle="security" />" defer="defer"></script>
		<script language="javascript" src="<bean:message  key="Comm.js.TJMessagepath"  bundle="conf.comm.CommMessage"/>"></script>
		<script language="javascript" src="include/javascript/eventOnKeyPress.js"></script>
		<link type="text/css" rev="stylesheet" rel="stylesheet" href="include/css/form.css" />
		<script language="javascript">
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
				alert(" \""+itermname+"\" <bean:message key="Comm.js.commom.msg7" bundle="conf.comm.CommMessage" /> ");
				return false;
				}
			}		
		}
		function trim(str){  //删除左右两端的空格
			return str.replace(/(^\s*)|(\s*$)/g, "");
		}
function saveForm(){
	if(!Validator.Validate(document.forms.form,3)){
      return ;
   	}
	var reg1 = /^[a-zA-Z]{1}[a-zA-Z0-9|_]+$/;
	if(document.form.classCode.value == ""){
	 	alert('<bean:message key="Comm.js.commom.msg10" bundle="conf.comm.CommMessage" />');
	 	return ;
	}
	if(document.form.className.value == ""){
	 	alert('<bean:message key="Comm.js.commom.msg11" bundle="conf.comm.CommMessage" />');
	 	return ;
	}
	if(!reg1.test(document.form.classCode.value)){
		alert('<bean:message key="Comm.js.commom.msg12" bundle="conf.comm.CommMessage" />');
		return;
	}
	if(/\D/.test(document.form.seqNo.value)){
		alert("<bean:message  bundle='comm.commLocale' key='comm.jsp.warn1'/>");
		return;
	}
	if(document.form.seqNo.value !== null){ 
	if(yznumber('seqNo','<bean:message key="Comm.js.commom.msg6" bundle="conf.comm.CommMessage" />','6','2')==false){
				return;
		}
	}
	if (confirmMessage("<bean:message key='comm.jsp.add.tianjia' bundle='comm.commLocale'/>")){     
	    document.form.verbId.value = "add";    
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
			<input type="hidden" name="verbId" value="add" />
			<table border="0" cellpadding="0" cellspacing="0" class="tblFill" align="center">
				<tr>
					 <td class="tblTitle" colspan="4"><span>※</span>
						<bean:message key="Comm.jsp.commom.addDictType"
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
							maxlength="32" onkeypress="eventOnKeyPress('className')"
							max="32" dataType="LimitB" msg="类别代码输入过长"
							value="<%=commDictPublicClass.getClassCode()%>" />
					</td>
					<td class="tblLable">
						<span>*</span>
						<bean:message key="Comm.jsp.commom.typeName"
							bundle="conf.comm.CommMessage" />:
					</td>
					<td>
						<input type="text" class="kuandu" name="className" size="50"
							maxlength="60" onkeypress="eventOnKeyPress('seqNo')" max="60"
							dataType="LimitB" msg="类别名称输入过长"
							value="<%=commDictPublicClass.getClassName()%>" />
					</td>
				</tr>
				<tr>
					<td class="tblLable">
						<bean:message key="jsp.seqNo" bundle="conf.Init" />:
					</td>
					<td>
						<input type="text" class="kuandu" name="seqNo" id="seqNo"
							size="30" maxlength="11" onkeypress="eventOnKeyPress('comments')"
							value="<%=commDictPublicClass.getSeqNo()%>" readonly />
					</td>
					<td class="tblLable">
						<bean:message key="jsp.comments" bundle="conf.Init" />:
					</td>
					<td>
						<input type="text" class="kuandu" name="comments" id="comments"
							size="30" maxlength="40" max="40" dataType="LimitB" msg="备注输入过长"
							value="<%=commDictPublicClass.getComments()%>"
							onkeypress="eventOnKeyPress('btnSaveForm')" />
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
