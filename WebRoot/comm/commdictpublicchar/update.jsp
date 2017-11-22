<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="html" uri="/WEB-INF/struts-html.tld"%>
<%@ taglib prefix="bean" uri="/WEB-INF/struts-bean.tld"%>
<%@ taglib prefix="logic" uri="/WEB-INF/struts-logic.tld"%>
<jsp:useBean id="commDictPublicChar" scope="request"
	class="com.tianjian.comm.struts.form.CommDictPublicCharForm" />
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
		<title><bean:message
				key="Comm.jsp.commom.modifyGeneralDictInfo"
				bundle="conf.comm.CommMessage" />
		</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<script language="javascript" src="<bean:message key="includes.js.validator.path" bundle="security" />" defer="defer"></script>
		<script language="javascript" src="<bean:message  key="Comm.js.TJMessagepath"  bundle="conf.comm.CommMessage"/>"></script>
		<script language="javascript" src="include/javascript/eventOnKeyPress.js"></script>
		<script language="javascript" src="<bean:message key="security.js.gettext_staff.path" bundle="security" />"></script>
		<link rel="stylesheet" href="<%=request.getContextPath()%>/hsp/include/css/open.css" />
		<script language="javascript">
	function yznumber(id,itermname,integer,decimal){
		var inputvalue= (document.getElementById(id)).value;
		if(inputvalue != ""){
			var reg = /^[0-9]+(\.[0-9]+)?$/;
			showHspMessage('<%=commDictPublicChar.getMessage()%>')
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
		function trim(str){  //删除左右两端的空格
			return str.replace(/(^\s*)|(\s*$)/g, "");
		}
	function saveForm(){
		if(!Validator.Validate(document.forms.form,3)){
	      return ;
	   	}
	    if(document.form.classCode.value == ""){
	 		alert('<bean:message key="Comm.js.commom.msg10" bundle="conf.comm.CommMessage" />');
	 		return ;
		}
	if(document.form.dictCode.value == ""){
	 	 alert('<bean:message key="comm.jsp.common.text31" bundle="conf.comm.comm"/>');
	 	 return;
	}
	if(document.form.dictCode.value !== null){ 
		if(yznumber('dictCode','<bean:message key="Comm.js.commom.msg5" bundle="conf.comm.CommMessage"/>','6','2')==false){
			return;
		}
	}
	if(document.form.dictValue.value == ""){
		alert('<bean:message key="comm.jsp.common.text32" bundle="conf.comm.comm"/>');
	    return ;
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
		<link type="text/css" rev="stylesheet" rel="stylesheet" href="include/css/form.css" />
	</head>
	<body
		onload="showHspMessage('<%=commDictPublicChar.getMessage()%>')">
		<form name="form" method="post" action="comm/commDictPublicChar.do">
			<input type="hidden" name="verbId" value="update" />
			<input type="hidden" name="id"
				value="<%=commDictPublicChar.getId()%>" />
			<input type="hidden" name="idHidden"
				value=<%=commDictPublicChar.getIdHidden()%> />
			<table align="center" border="0" cellpadding="0" cellspacing="0" class="tblFill" border="0" cellpadding="0" cellspacing="0" class="tblFill">
				<tr>
					<td class="tblTitle" colspan="4"><span>※</span> 
						<bean:message key="Comm.jsp.commom.modifyGeneralDict"
							bundle="conf.comm.CommMessage" />
						<span>※</span></td>
				</tr>
				<tr>
					<td class="tblLable">
						<span>*</span>
						<bean:message key="Comm.jsp.commom.typeName"
							bundle="conf.comm.CommMessage" />
						:
					</td>
					<td>
						<input type="text" class="input" id="displayInputId_1"
							name="className" style="font-size: 12px; width: 180px"
							onkeydown="huiche()" value="<%=commDictPublicChar.getClassName()%>"
							readonly="readonly" onkeypress="eventOnKeyPress('hidden_1')" />
						<input type="hidden" id="hiddenInputId_1"
							value="<%=commDictPublicChar.getClassCode()%>" name="classCode" />
						<img src="security/include/images/select.gif"
							style="cursor: pointer;"
							onclick="add('<%=request.getContextPath()%>/comm/commDictPublicChar.do?verbId=getClass','displayInputId_1','hiddenInputId_1')" />
					</td>
					<td class="tblLable">
						<span>*</span>
						<bean:message key="jsp.dictCode" bundle="conf.Init" />
						:
					</td>
					<td>
						<input type="text"  name="dictCode" id="dictCode"
							size="20" maxlength="20" onkeypress="eventOnKeyPress('dictValue')"
							max="20" dataType="LimitB" msg="项目代码输入过长"
							value="<%=commDictPublicChar.getDictCode()%>" />
					</td>
				</tr>
				<tr>
					<td class="tblLable">
						<span>*</span>
						<bean:message key="jsp.dictValue" bundle="conf.Init" />
						:
					</td>
					<td>
						<input type="text" name="dictValue" size="50"
							maxlength="60" onkeypress="eventOnKeyPress('seqNo')"
							max="60" dataType="LimitB" msg="项目名称输入过长"
							value="<%=commDictPublicChar.getDictValue()%>" />
					</td>
					<td class="tblLable">
						<bean:message key="jsp.seqNo" bundle="conf.Init" />
						:
					</td>
					<td>
						<input type="text" name="seqNo" id="seqNo"
							size="20" maxlength="11" onkeypress="eventOnKeyPress('comments')"
							value="<%=commDictPublicChar.getSeqNo()%>" readonly />
					</td>
				</tr>
				<tr>
					<td class="tblLable">
						<bean:message key="jsp.comments" bundle="conf.Init" />
						:
					</td>
					<td colspan="3">
						<input type="text" name="comments" id="comments"
							size="50" onkeypress="eventOnKeyPress('btnSaveForm')"
							max="40" dataType="LimitB" msg="备注输入过长"
							maxlength="40" value="<%=commDictPublicChar.getComments()%>" />
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
