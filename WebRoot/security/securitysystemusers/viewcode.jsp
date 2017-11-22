<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<jsp:useBean id="viewRegistCodeForm" scope="request" type="com.tianjian.security.struts.form.ViewRegistCodeForm" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%
		if (request.getServerPort() == 80) {
		%>
		<base href="http://<%=request.getServerName()%><%=request.getContextPath()%>/" />
		<%
		} else {
		%>
		<base href="http://<%=request.getServerName()%>:<%=request.getServerPort()%><%=request.getContextPath()%>/" />
		<%
		}
		%>
		<title><bean:message key="security.jsp.securitysystemuser.sviewcode.title" bundle="security"/></title>
		<meta http-equiv="pragma" content="no-cache" />
		<meta http-equiv="cache-control" content="no-cache" />
		<meta http-equiv="expires" content="0" />
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3" />
		<meta http-equiv="description" content="This is my page" />
		<link rel="stylesheet" rev="stylesheet" href="include/css/form.css" />
		<script language="javascript">
			function goBack(){
				location.href="<%=request.getContextPath()%>/security/viewRegistCode.do?verbId=viewInit&navtext=操作员管理主菜单-操作员注册码获取";
			}
		</script>
	</head>
	<body>
		<form action="security/viewRegistCode.do" method="post" name="form">
			<input type="hidden" name="verbId" value="<%=viewRegistCodeForm.getVerbId()%>" />
			<div align="center">
			<!--列表标题-->
			<div align="center" >
			
			<!--列表内容-->
			<table border="0" cellpadding="0" cellspacing="0" class="tblSearchList" align="center">
				<caption style="text-align:left;">
					<img src="<%= request.getContextPath()%>/comm/include/images/comm_list_nav_red.jpg" width="20" height="20"  align="absmiddle" /> <span style="font-weight:bold; color:#333;"><bean:message key="jsp.resultlist" bundle="conf.Init"/></span>
			    </caption>
			    <thead>
			    	<tr class="lstName">
			            <th width="5%" height="26"><bean:message key="security.jsp.commom.staffCode" bundle="security"/></th>
			            <th width="5%" height="26"><bean:message key="security.jsp.commom.name" bundle="security"/></th>
			            <th width="10%" height="26"><bean:message key="security.jsp.commom.idNo" bundle="security"/></th>
			            <th width="15%" height="26"><bean:message key="security.jsp.commom.regCode" bundle="security"/></th>
			            <th width="5%" height="26"><bean:message key="security.jsp.securitysystemusers.viewcode.isRegisted" bundle="security"/></th>
			            <th width="8%" height="26"><bean:message key="security.jsp.securitysystemusers.viewcode.startTime" bundle="security"/></th>
			        </tr>
				</thead>
				<tbody id="interval_row_id">
					<%
							if (viewRegistCodeForm.getIdList() != null && viewRegistCodeForm.getIdList().length > 0) {
							for (int i = 0; i < viewRegistCodeForm.getIdList().length; i++) {
					%>
					<tr>
						<td><%=viewRegistCodeForm.getStaffCodeList()[i]%></td>
						<td><%=viewRegistCodeForm.getNameList()[i]%></td>
						<td><%=viewRegistCodeForm.getIdNoList()[i]%></td>
						<td><%=viewRegistCodeForm.getRegistCodeList()[i]%></td>
						<td><%=viewRegistCodeForm.getIsRegistedList()[i]%></td>
						<td><%=viewRegistCodeForm.getStartTimeList()[i]%></td>
					</tr>
					<%
						}
						}
					%>
				</tbody>
			</table>
			</div>
			<!-- Sheet operation button area -->
			<div class="btnSave">
				<input type="button"  name="btnBack"  value="<bean:message key="security.jsp.commom.button2" bundle="security"/>" onclick="goBack();" />
			</div>
		</form>
		<script language="javascript" src="include/javascript/interval_row_color.js"></script>
	</body>
</html>
