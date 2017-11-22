<%@page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@page import="com.tianjian.util.comm.PageBean"%>
<jsp:useBean id="securityStaffPasswordChange" scope="request" type="com.tianjian.security.struts.form.SecurityStaffPasswordChangeForm" />
<jsp:useBean id="pb" scope="request" class="com.tianjian.util.comm.PageBean" />
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
		<title><bean:message key="security.jsp.securitystaffpasswordchange.init.title" bundle="security"/></title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<script language="javascript" src="include/javascript/eventOnKeyPress.js"></script>
		<script language="javascript">
function submitQueryForm() { 
	if(document.form.passwd.value == ""){
		alert('<bean:message key="security.jsp.commom.warn18" bundle="security"/>');
  		return;
	 }
  if(document.form.newPasswd.value == ""){
		alert('<bean:message key="security.jsp.commom.warn19" bundle="security"/>');
	  	return;
  }
  if(document.form.confirmPassword.value == ""){
		alert('<bean:message key="security.jsp.commom.warn20" bundle="security"/>');
	  	return;
  }
  if(document.form.newPasswd.value != document.form.confirmPassword.value){
		alert('<bean:message key="security.jsp.commom.warn21" bundle="security"/>');
	  	return;
  }
  if (confirm("<bean:message key='security.jsp.securitystaffpasswordchange.init.config' bundle='security'/>")){     
    document.form.method = "POST"
    document.form.verbId.value = "update";    
    document.form.submit(); 
   }   
  
}
function back(){
	document.form.reset();	 
}
function showInitMessage(message){
	if(message != ""){
	 	alert(message);
	 	return ;
	}	
	
}
</script>
		<link type="text/css" rev="stylesheet" rel="stylesheet" href="include/css/form.css" />
		
	</head>
	<body onload="showInitMessage('<%=securityStaffPasswordChange.getMessage()%>');">
		<!-- Form中的action项的值必须设置-->
		<form name="form" method="post" action="security/securityStaffPasswordChange.do" autocomplete=“off”>
			<!-- Head line -->
			<input type="hidden" name="verbId" value="update">
			<input type="hidden" name="staffId" value="<%=securityStaffPasswordChange.getStaffId()%>">
			<table align="center" border="0" cellpadding="0" cellspacing="0" class="tblFill" >
				<tr>
					<td class="tblTitle" colspan="2">
						<span>※</span><bean:message key="security.jsp.securitystaffpasswordchange.init.title" bundle="security"/><span>※</span></td>
				</tr>
				
				<TR>
					<td class="tblLable">
						<span>*</span> <bean:message key="security.jsp.commom.passwd" bundle="security"/>：
					</TD>
					<TD >
						<input name="passwd" type="password" maxlength="15"  onkeypress="eventOnKeyPress('newPasswd')" value="<%=securityStaffPasswordChange.getPasswd()%>" />
						<span class="tip"></span>
					</TD>
				</TR>
				
				<TR>
					<td class="tblLable" >
						<span>*</span> <bean:message key="security.jsp.commom.newPasswd" bundle="security"/>：
					</TD>
					<TD >
						<input name="newPasswd" type="password" maxlength="15" onkeypress="eventOnKeyPress('confirmPassword')" value="<%=securityStaffPasswordChange.getNewPasswd()%>" />
						<span class="tip"></span>
					</TD>
				</TR>
				
				<TR>
					<td class="tblLable" >
						<span>*</span> <bean:message key="security.jsp.commom.item11" bundle="security"/>：
					</TD>
					<TD >
						<input name="confirmPassword" type="password" maxlength="15 onkeypress="eventOnKeyPress('tijiao')" value="<%=securityStaffPasswordChange.getNewPasswd()%>" />
					    <span class="tip"></span>
					</TD>
				</TR>
				</TABLE>
				<div class="btnSave">
						<input type="button" style="font-family:Arial;font-size:12px;"  name="tijiao" value="提交" onClick="submitQueryForm();" />
						<input type="button" style="font-family:Arial;font-size:12px;" name="btnBack" value="<bean:message key='security.jsp.commom.button11' bundle='security'/>" onClick="back();;" />
				</div>
		</form>
	</body>
</html>
