<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="html" uri="/WEB-INF/struts-html.tld"%>
<%@ taglib prefix="bean" uri="/WEB-INF/struts-bean.tld"%>
<%@ taglib prefix="logic" uri="/WEB-INF/struts-logic.tld"%>
<jsp:useBean id="commDictPublicLevel" scope="request" class="com.tianjian.comm.struts.form.CommDictPublicLevelForm" />
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
		<title><bean:message key="Comm.jsp.commom.updateGeneralLevelDictInfo" bundle="conf.comm.CommMessage"/></title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<script language="javascript" src="<bean:message  key="Comm.js.TJMessagepath"  bundle="conf.comm.CommMessage"/> "></script>
		<script language="javascript" src="<bean:message key="includes.js.validator.path" bundle="security" />" defer="defer"></script>
		<script language="javascript" src="include/javascript/eventOnKeyPress.js"></script>
		<script language="javascript" src="<bean:message key="security.js.gettext_staff.path" bundle="security" />"></script>
		<link rel="stylesheet" href="<%=request.getContextPath()%>/hsp/include/css/open.css" />
		<script language="javascript">
		function trim(str){  //删除左右两端的空格
			return str.replace(/(^\s*)|(\s*$)/g, "");
		}
	function saveForm(){
		if(!Validator.Validate(document.forms.form,3)){
         return ;
   	    }
	    if(document.form.tableCode.value == ""){
	 	alert('<bean:message key="Comm.js.commom.msg16" bundle="conf.comm.CommMessage" />');
	 	return ;
	}
	if(document.form.classLevel.value == ""){
	 alert('<bean:message key="comm.jsp.common.warn31" bundle="conf.comm.comm"/>');
	 return;
	}
	if(/\D/.test(document.form.seqInLevel.value)){
		alert("<bean:message  bundle='comm.commLocale' key='comm.jsp.warn1'/>");
		return;
	}
	if(document.form.itemName.value == ""){
		alert({message:'<bean:message key="comm.jsp.common.warn32" bundle="conf.comm.comm"/>');
	   return ;
	}
	
	for(var i=0; i<document.form.classLevel.value.length; i++){
		if(document.form.classLevel.value.charAt(i)<'0' || document.form.classLevel.value.charAt(i)>'9'){
			alert('<bean:message key="Comm.js.commom.msg17" bundle="conf.comm.CommMessage"/>');
			return ;
		}
    } 
    for(var i=0; i<document.form.itemName.value.length; i++){
		if(document.form.itemName.value.charAt(i)<'0' || document.form.itemName.value.charAt(i)>'9'){
			alert('<bean:message key="Comm.js.commom.msg15" bundle="conf.comm.CommMessage"/>');
			return ;
		}
    } 
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
	<body onload="showHspMessage('<%=commDictPublicLevel.getMessage()%>')">
		<form name="form" method="post" action="comm/commDictPublicLevel.do">
			<input type="hidden" name="verbId" value="update" />
			<input type="hidden" name="itemCode" value="<%=commDictPublicLevel.getItemCode()%>" />
			<input type="hidden" name="idHidden" value=<%=commDictPublicLevel.getIdHidden()%> />
			<table align="center" border="0" cellpadding="0" cellspacing="0" class="tblFill" border="0" cellpadding="0" cellspacing="0" class="tblFill">
				<tr>
					    <td class="tblTitle" colspan="4"><span>※</span><bean:message key="Comm.jsp.commom.modifyGeneralDict"bundle="conf.comm.CommMessage"/>
						<span>※</span></td>
				</tr>
				<tr>
					<td class="tblLable">
						<span>*</span> <bean:message key="Comm.jsp.commom.dictType" bundle="conf.comm.CommMessage"/>:
					</td>
					<td>
						<input type="text" class="input" id="displayInputId_1"
							name="tableName" style="font-size: 12px; width: 180px"
							onkeydown="huiche()" value="<%=commDictPublicLevel.getTableName()%>"
							readonly="readonly" onkeypress="eventOnKeyPress('hidden_1')" />
						<input type="hidden" id="hiddenInputId_1"
							value="<%=commDictPublicLevel.getTableCodes()%>" name="tableCode" />
						<img src="security/include/images/select.gif"
							style="cursor: pointer;"
							onclick="add('<%=request.getContextPath()%>/comm/commDictPublicChar.do?verbId=getClass','displayInputId_1','hiddenInputId_1')" />
					</td>
					<td class="tblLable">
						 <bean:message key="Comm.jsp.commom.parentItemName" bundle="conf.comm.CommMessage"/>:
					</td>
					<td>
						<select name="parentItemCode" id="parentItemCode" onkeypress="eventOnKeyPress('classLevel')">
							<%
									if (commDictPublicLevel.getParentItemCodes() != null && commDictPublicLevel.getParentItemCodes().length > 0) {
									for (int i = 0; i < commDictPublicLevel.getParentItemCodes().length; i++) {
										String tempId = commDictPublicLevel.getParentItemCodes()[i];
										String tempName = commDictPublicLevel.getParentItemNames()[i];
							%>
							<option value="<%=tempId%>" <%=tempId.equals(commDictPublicLevel.getParentItemCode()) ? "selected" : ""%>>
								<%=tempName%>
							</option>
							<%
								}
								}
							%>
						</select>
					</td>
				</tr>
				<tr>
					<td class="tblLable">
						<span>*</span> <bean:message key="Comm.jsp.commom.itemLevel" bundle="conf.comm.CommMessage"/>: 	
					</td>
					<td>
						<input type="text"  name="classLevel" size="20" maxlength="5" onkeypress="eventOnKeyPress('itemName')" max="5" dataType="LimitB" msg="项目级别输入过长" value="<%=commDictPublicLevel.getClassLevel()%>" />
					</td>
					<td class="tblLable">
						<span>*</span> <bean:message key="jsp.dictValue" bundle="conf.Init"/>:
					</td>
					<td>
						<input type="text" name="itemName" size="50" maxlength="50" onkeypress="eventOnKeyPress('seqInLevel')"  max="60" dataType="LimitB" msg="项目名称输入过长" value="<%=commDictPublicLevel.getItemName()%>" />
					</td>
				</tr>
				<tr>
					<td class="tblLable">
						<bean:message key="jsp.seqNo" bundle="conf.Init"/>:
					</td>
					<td>
						<input type="text"  name="seqInLevel" id="seqInLevel" size="20" maxlength="6" onkeypress="eventOnKeyPress('comments')" value="<%=commDictPublicLevel.getSeqInLevel()%>"  readonly/>
					</td>
					<td class="tblLable">
						<bean:message key="jsp.comments" bundle="conf.Init"/>:
					</td>
					<td>
						<input type="text" name="comments" id="comments" size="50" maxlength="50" onkeypress="eventOnKeyPress('btnSaveForm')" max="40" dataType="LimitB" msg="备注输入过长" value="<%=commDictPublicLevel.getComments()%>" />
					</td>
				</tr>
			</table>
			<!-- Sheet operation button area -->
			<div class="btnSave">
				<input type="button"  name="btnSaveForm" value="<bean:message key="jsp.btnSaveForm" bundle="conf.Init"/>" onClick="saveForm()" />
				<input type="button"  name="btnBack" value="<bean:message key="jsp.btnBack" bundle="conf.Init"/>" onClick="history.go(-1);" />
			</div>
		</form>
	</body>
</html>
