﻿<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<jsp:useBean id="commConfigInputDict" scope="request" class="com.tianjian.comm.struts.form.CommConfigInputDictForm" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%if(request.getServerPort() == 80) {%>
<base href="http://<%=request.getServerName()%><%=request.getContextPath()%>/" />
<%} else {%>
<base href="http://<%=request.getServerName()%>:<%=request.getServerPort()%><%=request.getContextPath()%>/" />
<%}%>
<title><bean:message key="comm.jsp.common.text59" bundle="conf.comm.comm"/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta http-equiv="pragma" content="no-cache" />
<meta http-equiv="cache-control" content="no-cache" />
<meta http-equiv="expires" content="0" />
<script language="javascript" src="<bean:message key="comm.js.CommMessage.path" bundle="conf.comm.comm"/>"></script>
<script language="javascript" src="<bean:message key="includes.js.validator.path" bundle="security" />" defer="defer"></script>
<script language="javascript" src="include/javascript/eventOnKeyPress.js"></script>
<script language="javascript" src="<%=request.getContextPath()%>/include/javascript/utrim.js"></script>
<script language="javascript">
function saveForm(){
    if(!Validator.Validate(document.forms.form,3)){
      return ;
    }
	if(trim(document.form.itemCode.value)==""){
		alert("<bean:message key="comm.jsp.common.text31" bundle="conf.comm.comm"/>");
		return;
	}
	if(trim(document.form.itemName.value)==""){
		alert("<bean:message key="comm.jsp.common.text32" bundle="conf.comm.comm"/>");
		return;
	}
	if(trim(document.form.inputCode.value)==""){
		alert("<bean:message key="comm.jsp.common.text33" bundle="conf.comm.comm"/>");
		return;
	}
	if(trim(document.form.inputCodeWb.value)==""){
		alert("<bean:message key="comm.jsp.common.text34" bundle="conf.comm.comm"/>");
		return;
	}
	document.form.verbId.value = "add";
	document.form.submit();
}
				function goback(url){
					location.href=url;
				}
</script>
<link type="text/css" rev="stylesheet" rel="stylesheet" href="include/css/form.css" />
</head>

<body onload="showCommMessage('','<%=commConfigInputDict.getMessage() %>','1')" >
<form name="form" method="post" action= "comm/commConfigInputDict.do" >
<input type="hidden" name="verbId" value="add" />

<table border="0" cellpadding="0" cellspacing="0" class="tblFill" align="center">
    <tr>
     	<td class="tblTitle" colspan="4"><span>※</span><bean:message key="comm.jsp.different.text39" bundle="conf.comm.comm"/><span>※</span></td>          
    </tr>
    <tr>
        <td class="tblLable">
         	<span>*</span> <bean:message key="comm.jsp.common.text10" bundle="conf.comm.comm"/>：
        </td>
        <td>
            <input type="text" s name="itemCode" size="20" maxlength="10"
            	onkeypress="eventOnKeyPress('itemName')" max="10" dataType="LimitB" msg="代码输入过长"
              value="<%=commConfigInputDict.getItemCode() %>" />
        </td>  
            <td class="tblLable">
          	<span>*</span> <bean:message key="comm.jsp.common.text29" bundle="conf.comm.comm"/>：
        </td>
        <td>
            <input type="text"  name="itemName" size="50" maxlength="1"
              onkeypress="eventOnKeyPress('inputCode')" max="10" dataType="LimitB" msg="名称输入过长"
              value="<%=commConfigInputDict.getItemName() %>" />
        </td>
    </tr>
	<tr>

		<td class="tblLable">
			<span>*</span> <bean:message key="comm.jsp.common.text38" bundle="conf.comm.comm"/>：
		</td>
		<td>
			<input type="text"  name="inputCode" size="20"
				maxlength="5" onkeypress="eventOnKeyPress('inputCodeWb')" max="5" dataType="LimitB" msg="输入码输入过长"
				value="<%=commConfigInputDict.getInputCode()%>" />
		</td>
		<td class="tblLable">
			<span>*</span> <bean:message key="comm.jsp.common.text45" bundle="conf.comm.comm"/>：
		</td>
		<td>
			<input type="text"  name="inputCodeWb" size="50"
				maxlength="5" onkeypress="eventOnKeyPress('comments')"  max="5" dataType="LimitB" msg="五笔码输入过长"
				value="<%=commConfigInputDict.getInputCodeWb()%>" />
		</td>
	</tr>
	
    <tr>
        <td class="tblLable">
            <bean:message key="comm.jsp.common.text4" bundle="conf.comm.comm"/>：
        </td>
        <td colspan="3">
            <input type="text"  name="comments" id="principal" size="30" onkeypress="eventOnKeyPress('btnSaveForm')"  maxlength="40" max="40" dataType="LimitB" msg="备注输入过长"
          		 value="<%=commConfigInputDict.getComments() %>" />
        </td>
    </tr>
  </table>
  <!-- Sheet operation button area -->
  <div class="btnSave">
  		<input type="button"  name="btnSaveForm" value="<bean:message key="comm.jsp.common.text3" bundle="conf.comm.comm"/>" onclick="saveForm()" />
        <input type="button"  name="btnBack" value="<bean:message key="comm.jsp.common.text19" bundle="conf.comm.comm"/>" onclick="goback('<%=request.getContextPath() %>/comm/commConfigInputDict.do?verbId=query');" /> 
  </div>
</form>
</body>
</html>
