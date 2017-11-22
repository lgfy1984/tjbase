<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<jsp:useBean id="data" scope="request" class="com.tianjian.security.struts.form.SecurityUserVsRolesOpForm" />
<html>
<head>
<%if(request.getServerPort() == 80) {%>
	<base href="http://<%=request.getServerName()%><%=request.getContextPath()%>/">
<%} else {%>
	<base href="http://<%=request.getServerName()%>:<%=request.getServerPort()%><%=request.getContextPath()%>/">
<%}%>
<title><bean:message key="security.jsp.securityuservsrolesop.detail.title" bundle="security"/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">

<link type="text/css" rev="stylesheet" rel="stylesheet" href="include/css/form.css" />
</head>

<body>
	<form name="form" method="post" action= "comm/securityStaffBaseinfo.do" >
		<input type="hidden" name="verbId" value="add" />
		<input type="hidden" name="id" value="<%=data.getId() %>">
	
		<table width="100%" border="0" cellpadding="0" cellspacing="0" class="tblView" align="center">
		    <tr>
				<td class="tblTitle" colspan="4">
					<bean:message key="security.jsp.securityuservsrolesop.detail.item" bundle="security"/>
				</td>
		    </tr>
		    <tr>
		        <td class="tblLable">
		           <bean:message key="security.jsp.securityuservsrolesop.detail.staffCode" bundle="security"/>：
		        </td>
		        <td class="hou" colspan="3">
		            <%=data.getStaffCode() %>
		        </td>  
		    </tr>
		    <tr>
		        <td class="tblLable">
		           <bean:message key="security.jsp.securitystaffbaseinfo.list4web.securityStaffBaseinfoName" bundle="security"/>：
		        </td>
		        <td class="hou" colspan="3">
		 			<%=data.getHspConfigBaseinfoName() %>
		        </td>
		    </tr>
		    <tr>
		        <td class="tblLable">
		          <bean:message key="security.jsp.commom.name" bundle="security"/>：
		        </td>
		        <td class="hou">
		            <%=data.getName() %>
		        </td>      
		        <td class="tblLable">
		           <bean:message key="security.jsp.ssecurityuservsroles.SecurityUserVsRoles.userInput" bundle="security"/>：
		        </td>
		        <td class="hou">
		 			<%=data.getNameEn() %>
		        </td>
		    </tr>
		    <tr>
		        <td class="tblLable">
		          <bean:message key="security.jsp.commom.birthDay" bundle="security"/>：
		        </td>
		        <td class="hou">
		            <%=data.getDateOfBirth() %>
		        </td>       
		        <td class="tblLable">
		           <bean:message key="security.jsp.commom.commConfigSexId" bundle="security"/>：
		        </td>
		        <td class="hou">
		 			<%=data.getCommConfigSexName() %>
		        </td>
		    </tr>
		    <tr>
		        <td class="tblLable">
		         <bean:message key="security.jsp.commom.idNo" bundle="security"/>：
		        </td>
		        <td class="hou">
		            <%=data.getIdNo() %>
		        </td>       
		        <td class="tblLable">
		           <bean:message key="security.jsp.commom.commConfigStafftypeId" bundle="security"/>：
		        </td>
		        <td class="hou">
		 			<%=data.getCommConfigStafftypeName() %>
		        </td>
		    </tr>
		    
		    <tr>  
		        <td class="tblLable">
		           <bean:message key="security.jsp.commom.islocation" bundle="security"/>：
		        </td>
		        <td class="hou" colspan="3">
		 			<%=data.getIslocationName() %>
		        </td>
		    </tr>
		    <tr>      
		        <td class="tblLable">
		        	<bean:message key="security.jsp.commmom.comments" bundle="security"/>：
		        </td>
		        <td class="hou" colspan="3">
		            <%=data.getComments()%>
		        </td>
		    </tr>    
		</table>
		  
		<table width="100%" border="0" cellpadding="0" cellspacing="0">
			<tr>
				<td height="30" align="center">
		    		<input type="button" style="font-size:12px;font-family:Arial;" name="btnBack" value="<bean:message key="security.jsp.commom.button2" bundle="security"/>" onClick="history.go(-1);" />           
		   		</td>
			</tr>
		</table>
	</form>
</body>
</html>
