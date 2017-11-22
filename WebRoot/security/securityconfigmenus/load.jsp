<%@ page pageEncoding="utf-8"%>
<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/tj-html.tld" prefix="tj"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title><bean:message key="security.jsp.securityconfigmenus.load.title" bundle="security"/></title>
<meta http-equiv=Content-Type content="text/html; charset=utf-8"/>
<script language="javascript" src="<bean:message key="include.js.TJMessage.path" bundle="security" />"></script>
<script language="javascript" src="<%=request.getContextPath() %>/include/javascript/cascadingUtil.js"></script>
<script language="javascript" src="<%=request.getContextPath() %>/include/javascript/eventOnKeyPress.js"></script>
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
var menuUrl=document.getElementById("menuUrl").value;
var menuLevel=document.getElementById("menuLevel").value;
var menuSeq=document.getElementById("menuSeq").value;
if(id==""){
      alert("<bean:message key="security.jsp.securityconfigmenus.load.warn" bundle="security"/>！！");
      return false;
     }

if(name==""){
      alert("<bean:message key="security.jsp.securityconfigmenus.load.warn1" bundle="security"/>！！");
      return false;
     }
if(menuUrl==""){
      alert("<bean:message key="security.jsp.securityconfigmenus.load.warn2" bundle="security"/>！！");
      return false;
     }
if(menuLevel==""){
      alert("<bean:message key="security.jsp.securityconfigmenus.load.warn3" bundle="security"/>！！");
      return false;
     }
if(menuSeq==""){
      alert("<bean:message key="security.jsp.securityconfigmenus.load.warn4" bundle="security"/>！！");
      return false;
     }
else{
     document.securityconfigmenusactionform.submit();
}
}
</script>
<link rel="StyleSheet" href="include/css/form.css" type="text/css" />
</head>
<body>
<div align="center"><html:form action="/securityConfigMenus.do">
	<html:hidden property="op" />
	<html:hidden property="dataId" />
	<table width="829" class="table" border="0" cellspacing="1" cellpadding="0" align="center">
		<tr>
			<td colspan="4" class="list_nav"><bean:message key="security.jsp.securityconfigmenus.load.title" bundle="security"/></td>
		</tr>
		<tr>
			<td class="name"><font color="#ff0000">*</font> <bean:message key="security.jsp.commom.menuCode" bundle="security"/>：</td>
			<td class="value"><html:text property="data.menuCode" styleId="id" maxlength="20" onkeydown="eventOnKeyDown()" /></td>
			<td class="name"><font color="#ff0000">*</font> <bean:message key="security.jsp.commom.SecurityConfigPublic" bundle="security"/>：</td>
			<td class="value"><html:select property="data.securityConfigPublicId" style="width:256px">
					          <tj:optionsEX text="reason" source="SecurityConfigPublic"/>
				              </html:select></td>
		</tr>
		<tr>
		    <td class="name"><font color="#ff0000">*</font> <bean:message key="security.jsp.commom.parentId" bundle="security"/>：</td>
			<td class="value"> <html:select property="data.parentId" style="width:256px">
					          <tj:optionsEX text="menuDetail" source=" from SecurityConfigMenus s where s.endLevelFlag=0" labelClass="securityConfigPublicId"/>
				              </html:select></td>
		    <td class="name"><font color="#ff0000">*</font> <bean:message key="security.jsp.commom.endLevelFlag" bundle="security"/>：</td>
			<td class="value">
			<html:select property="data.endLevelFlag" style="width:256px">
			<html:option value="1"><bean:message key="security.jsp.securityconfigmenus.update.item" bundle="security"/></html:option>
			<html:option value="0"><bean:message key="security.jsp.securityconfigmenus.update.item1" bundle="security"/></html:option>
			</html:select></td>
			
		</tr>
		<tr>
			<td class="name"><font color="#ff0000">*</font><bean:message key="security.jsp.commom.menuDetail" bundle="security"/>：</td>
			<td class="value"><html:text property="data.menuDetail" styleId="name" maxlength="100" onkeydown="eventOnKeyDown()"/></td>
			<td class="name"><font color="#ff0000">*</font > <bean:message key="security.jsp.commom.menuUrl" bundle="security"/> ：</td>
			<td class="value"><html:text property="data.menuUrl" styleId="menuUrl" maxlength="100" onkeydown="eventOnKeyDown()"/></td>
		</tr>
		<tr>
			<td class="name"><font color="#ff0000">*</font><bean:message key="security.jsp.commom.menuLevel" bundle="security"/>：</td>
			<td class="value"><html:text property="data.menuLevel" styleId="menuLevel" maxlength="1" onkeydown="eventOnKeyDown()"/></td>
			<td class="name"><font color="#ff0000">*</font><bean:message key="security.jsp.commom.menuSeq" bundle="security"/>：</td>
			<td class="value"><html:text property="data.menuSeq" styleId="menuSeq" maxlength="3" onkeydown="eventOnKeyDown()"/></td>
		</tr>
		<tr>
			<td class="name"><bean:message key="security.jsp.commom.menuNotice" bundle="security"/>：</td>
			<td class="value"><html:text property="data.menuNotice" maxlength="100" onkeydown="eventOnKeyDown()"/></td>
			<td class="name"><bean:message key="security.jsp.commom.menuIcon" bundle="security"/>：</td>
			<td class="value"><html:text property="data.menuIcon" maxlength="50" onkeydown="eventOnKeyDown()"/></td>
		</tr>
		
		<tr>
			<td class="name"><bean:message key="security.jsp.commom.menuTarget" bundle="security"/>：</td>
			<td class="value"><html:text property="data.menuTarget" maxlength="50" onkeydown="eventOnKeyDown()"/></td>
			<td class="name"><bean:message key="security.jsp.commom.menuMethod" bundle="security"/>：</td>
			<td class="value" ><html:text property="data.menuMethod" maxlength="50" onkeydown="eventOnKeyDown()"/></td>
		</tr>
		<tr>
			<td class="name"><bean:message key="security.jsp.commom.menuData" bundle="security"/>：</td>
			<td class="value2" colspan="3"><html:text property="data.menuData" maxlength="150" onkeydown="eventOnKeyDown()"/></td>
			
			
		</tr>
		<tr>
		<td class="name"><bean:message key="security.jsp.commmom.comments" bundle="security"/>：</td>
			<td class="value2" colspan="3"><html:text property="data.comments" maxlength="40" onkeydown="eventOnKeyDown()"/></td>
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
<script type="text/javascript">dynamicSelect("data.securityConfigPublicId","data.parentId");
</script>
</body>
</html>
