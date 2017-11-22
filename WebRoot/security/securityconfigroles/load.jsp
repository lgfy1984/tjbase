<%@ page pageEncoding="utf-8"%>
<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/tj-html.tld" prefix="tj"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title><bean:message key="security.jsp.securityConfigroles.load.title" bundle="security"/></title>
<meta http-equiv=Content-Type content="text/html; charset=utf-8"/>
<script language="javascript" src="<bean:message key="include.js.lib.path" bundle="security" />"></script>
<script language="javascript">
function comments(){
if(document.getElementById("text").value.length>39){
document.getElementById("text").value = document.getElementById("text").value.substr(0,39);

}
}
</script>
<script language="javascript">
function tijiao(){
var id=document.getElementById("id").value;
var name=document.getElementById("name").value;

if(id==""){
      alert("<bean:message key="security.jsp.securityConfigroles.load.warn" bundle="security"/>！！");
      return false;
     }
if(name==""){
      alert("<bean:message key="security.jsp.securityConfigroles.load.warn1" bundle="security"/>！！");
      return false;
     }
else{
     document.securityconfigrolesactionform.submit();
}
}
</script>
<script language="javascript" src="<%=request.getContextPath() %>/include/javascript/eventOnKeyPress.js"></script>
<link rel="StyleSheet" href="include/css/form.css" type="text/css" />
</head>
<body>
<div align="center"><html:form action="/securityConfigRoles.do">
	<html:hidden property="op" />
	<html:hidden property="dataId" />
	<table width="829" class="table" border="0" cellspacing="1" cellpadding="0" align="center">
		<tr>
			<td colspan="4" class="list_nav"><bean:message key="security.jsp.securityConfigroles.load.title" bundle="security"/></td>
		</tr>
		<tr>
			<td class="name"><font color="#ff0000">*</font><bean:message key="security.jsp.commom.roleCode" bundle="security"/>：</td>
			<td class="value"><html:text property="data.roleCode" styleId="id" maxlength="20" onkeydown="eventOnKeyDown()"/></td>
			<td class="name"><font color="#ff0000">*</font><bean:message key="security.jsp.commom.roleDetail" bundle="security"/>：</td>
			<td class="value"><html:text property="roleDetail" styleId="name" maxlength="40" onkeydown="eventOnKeyDown()"/></td>
		</tr>
		
		<tr>
			<td class="name"><bean:message key="security.jsp.commmom.comments" bundle="security"/>：</td>
			<td class="value2" colspan="3"><html:text property="data.comments" maxlength="40" onkeydown="eventOnKeyDown()" /></td>
		</tr>
	</table>
	  <table width="100%" border="0" cellpadding="0" cellspacing="0">
		 <tr>
            <td height="35" align="center">
			<input value="<bean:message key="security.jsp.commom.button1" bundle="security"/>" type="button" class="button" onclick="tijiao();" /> 
			<input value="<bean:message key="security.jsp.commom.button2" bundle="security"/>" type="button" class="button" onclick="tjsubmit(null,null,'list');" />
			</td>
		</tr>
	</table>
</html:form></div>
</body>
</html>
