<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

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
    <title><bean:message key="security.jsp.commom.item7" bundle="security"/></title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<script language="javascript" src="<bean:message key="include.js.TJMessage.path" bundle="security" />"></script>
	<script language="javascript" src="include/javascript/eventOnKeyPress.js"></script>
    <link type="text/css" rev="stylesheet" rel="stylesheet" href="include/css/form.css" />
  </head>
  <script type="text/javascript">
   function saveForm(){	
		if(document.getElementById("id").value==""){
			alert("<bean:message key="security.jsp.commom.warn7" bundle="security"/>");
			return;
		}
		if(document.getElementById("dataObjectTypeName").value==""){
			alert("<bean:message key="security.jsp.commom.warn8" bundle="security"/>");
			return;
		}
		if(document.getElementById("comulnName").value==""){
			alert("<bean:message key="security.jsp.commom.warn9" bundle="security"/>");
			return;
		}
		if (confirmMessage("0-000003")){ 
			document.form.verbId.value = "update";
			document.form.submit();
		}
	}
  </script>
  <body onLoad="showMessage('','${message}','1')">
  	<form name="form" method="post" action="securityData.do">
  	    <input type="hidden" name="verbId" value="update" />
			<table border="0" cellpadding="0" cellspacing="0" class="tblFill" align="center">
				<tr>
					<td class="tblTitle" colspan="4"><span>※</span> 
						<bean:message key="security.jsp.securityDataObject.update.title" bundle="security"/>
						<span>※</span></td>
				</tr>
				<tr>
					<td class="tblLable">
						<span>*</span> <bean:message key="security.jsp.commom.dataObjectTypeName" bundle="security"/>：
					</td>
					<td>
						<input type="text" class="kuandu" name="dataObjectTypeName" id=dataObjectTypeName size="20" maxlength="20" onKeyPress="eventOnKeyPress('comulnName')"  value="${dataObjectTypeName}"/>
					</td>
					<td class="tblLable">
						<span>*</span> <bean:message key="security.jsp.commom.ID" bundle="security"/>：
					</td>
					<td>
						<input type="text" readonly="readonly" class="kuandu" name="id" id=id size="20" maxlength="32" onKeyPress="eventOnKeyPress('dataObjectTypeName')"  value="${id }"/>
					</td>
				</tr>
				<tr>
					<td class="tblLable">
					<span>*</span> <bean:message key="security.jsp.commom.comulnName" bundle="security"/>：
					</td>
					<td colspan="3">
						<input type="text" class="kuandu" name="comulnName" value="${columnName }" id=comulnName size="32" maxlength="32" onKeyPress="eventOnKeyPress('saveinput')" />
					</td>
				</tr>
			</table>
			<div class="btnSave">
				<input type="button" id="saveinput"  name="btnSaveForm" value="<bean:message key="security.jsp.commom.button1" bundle="security"/>" onClick="saveForm()" />
				<input type="button"  name="btnBack" value="<bean:message key="security.jsp.commom.button2" bundle="security"/>" onClick="history.go(-1);" />
			</div>
  	</form>
  </body>
</html>
