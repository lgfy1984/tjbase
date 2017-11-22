<%@ page pageEncoding="utf-8"%>
<%@ page contentType="text/html;charset=utf-8"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/tj-html.tld" prefix="tj"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title><bean:message key="security.jsp.securityconfigmenus.view.title" bundle="security"/></title>
<meta http-equiv=Content-Type content="text/html; charset=utf-8"/>
<link rel="StyleSheet" href="include/css/form.css" type="text/css" />
</head>
<body>
<div align="center"><html:form action="/securityConfigMenus.do">
	<html:hidden property="op" />
	<html:hidden property="dataId" />
	<table width="829" border="0" cellspacing="1" cellpadding="0" class="table">
		<tr>
			<td colspan="4" class="list_nav"><bean:message key="security.jsp.securityconfigmenus.view.title" bundle="security"/></td>
		</tr>
		<tr>
		    <td class="name"><bean:message key="security.jsp.commom.menuCode" bundle="security"/>：</td>
			<td class="value"><tj:write property="data.menuCode" /></td>
			<td class="name"><bean:message key="security.jsp.commom.SecurityConfigPublic" bundle="security"/>：</td>
			<td class="value"><tj:write property="data.securityConfigPublic.reason"/></td>	
		</tr>
		<tr>
			<td class="name"><bean:message key="security.jsp.commom.menuDetail" bundle="security"/>：</td>
			<td class="value"><tj:write property="data.menuDetail" /></td>
			<td class="name"><bean:message key="security.jsp.commom.menuNotice" bundle="security"/>：</td>
			<td class="value"><tj:write property="data.menuNotice"  /></td>
		</tr>
		<tr>
			<td class="name"><bean:message key="security.jsp.commom.menuUrl" bundle="security"/>：</td>
			<td class="value"><tj:write property="data.menuUrl" /></td>
			<td class="name"><bean:message key="security.jsp.commom.endLevelFlag" bundle="security"/>：</td>
			<td class="value"><tj:write property="data.endLevelFlag"  /></td>
		</tr>
		<tr>
			<td class="name"><bean:message key="security.jsp.commom.menuLevel" bundle="security"/>：</td>
			<td class="value"><tj:write property="data.menuLevel" /></td>
			<td class="name"><bean:message key="security.jsp.commom.menuSeq" bundle="security"/> ：</td>
			<td class="value"><tj:write property="data.menuSeq"  /></td>
		</tr>
		<tr>
			<td class="name"><bean:message key="security.jsp.commom.menuIcon" bundle="security"/>：</td>
			<td class="value"><tj:write property="data.menuIcon" /></td>
		     <td class="name"><bean:message key="security.jsp.commom.parentId" bundle="security"/>：</td>
			<td class="value"><tj:write property="data.securityConfigMenus.menuDetail"  /></td>
		</tr>
		<tr>
			<td class="name"><bean:message key="security.jsp.commom.menuTarget" bundle="security"/>：</td>
			<td class="value"><tj:write property="data.menuTarget" /></td>
			<td class="name"><bean:message key="security.jsp.commom.menuMethod" bundle="security"/>：</td>
			<td class="value"><tj:write property="data.menuMethod"  /></td>
		</tr>
		<tr>
		   <td class="name"><bean:message key="security.jsp.commom.menuData" bundle="security"/>：</td>
		<td class="value2" colspan="3"><tj:write property="data.menuData"  /></td>

		</tr>
		<tr>
			<td class="name"><bean:message key="security.jsp.commmom.comments" bundle="security"/>：</td>
			<td class="value2" colspan="3"><tj:write property="data.comments" /></td>
		</tr>
	</table>
	<table width="100%" border="0" cellpadding="0" cellspacing="0">
		 <tr>
            <td height="35" align="center">
				<input value="<bean:message key="security.jsp.commom.button2" bundle="security"/>" type="button" class="button" onclick="window.history.back();" />
			</td>
		</tr>
	</table>
</html:form></div>
</body>
</html>
