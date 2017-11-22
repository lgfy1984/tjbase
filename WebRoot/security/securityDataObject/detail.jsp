<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<jsp:useBean id="data" scope="request" class="com.tianjian.security.struts.form.SecurityDataObjectTypeForm"></jsp:useBean>

<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
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
    
    <title><bean:message key="security.jsp.securityDataObject.detail.title" bundle="security"/></title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<link type="text/css" rev="stylesheet" rel="stylesheet" href="include/css/form.css" />
  </head>
  
  <body>
  <table  width="100%" border="0" cellpadding="0" cellspacing="0" class="tblView" align="center">
 	<tr>
        <td class="tblTitle" colspan="4">
         <span>※</span>&nbsp;&nbsp; <bean:message key="security.jsp.securityDataObject.detail.item" bundle="security"/>
       &nbsp;&nbsp;<span>※</span>
        </td>
    </tr>
    <tr>
    	<td class="tblLable">
    		<bean:message key="security.jsp.commom.dataObjectTypeName" bundle="security"/>：
    	</td>
    	<td class="hou"　style="width:30%">
    		<%=data.getDataObjectTypeName() %>
    	</td>
    	<td class="tblLable">
    		<bean:message key="security.jsp.commom.ID" bundle="security"/>：
    	</td>
    	<td class="hou">
    		<%=data.getId() %>
    	</td>
    </tr>
    <tr>
    	<td class="tblLable">
    		<bean:message key="security.jsp.commom.comulnName" bundle="security"/>：
    	</td>
    	<td class="hou" colspan="3">
    		<%=data.getComulnName() %>
    	</td>
    </tr>
  </table>
  <div class="btnSave">
  	<input type="button"  name="btnBack" value="<bean:message key="security.jsp.commom.button2" bundle="security"/>" onClick="history.go(-1);" />
  </div>
  </body>
</html>
