<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<jsp:useBean id="securityStaffBaseinfo" scope="request" class="com.tianjian.security.struts.form.SecurityStaffBaseinfoForm" />
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
		<title><bean:message key="security.jsp.securitystaffbaseinfo.detail.title" bundle="security"/></title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<meta http-equiv="pragma" content="no-cache" />
		<meta http-equiv="cache-control" content="no-cache" />
		<meta http-equiv="expires" content="0" />
		<link type="text/css" rev="stylesheet" rel="stylesheet" href="include/css/form.css" />
		<script language="javascript">
		    function QWPrint(){
			     if(document.all.PrintActiveX == undefined || document.all.PrintActiveX ==null){
					document.body.innerHTML="<object id=\"PrintActiveX\" style=\"display: none\" classid=\"clsid:3ede745c-4adb-42a6-ab25-5621edbdfd6b\" codebase=\"<%=request.getContextPath()%>/include/QWPrint.cab#version=1,3,8,2\" ></object>" + document.body.innerHTML;
				}
		        PrintActiveX.pageName = "A4"; //设置纸张类型
	            //设置页眉页脚
	            
				printed = true;
			    document.getElementById("btnSave").style.display="none";
			    PrintActiveX.PrintView();
			    document.getElementById("btnSave").style.display="block";
	   
	  		}
		</script>
	</head>
	<body>
		<form name="form" method="post" action="security/securityStaffBaseinfo.do">
			<input type="hidden" name="verbId" value="add" />
			<input type="hidden" name="id" value="<%=securityStaffBaseinfo.getId()%>" />
			<input type="hidden" name="message" value="<%=securityStaffBaseinfo.getMessage()%>" />
			<table width="100%" border="0" cellpadding="0" cellspacing="0" class="tblView">
				<tr>
					<td class="tblTitle" colspan="4">
						<span>※</span>&nbsp;&nbsp;<bean:message key="security.jsp.securitystaffbaseinfo.detail.title" bundle="security"/>
						&nbsp;&nbsp;<span>※</span>
					</td>
				</tr>
				<tr>
					<td  class="tblLable">
						<bean:message key="security.jsp.commom.staffCode" bundle="security"/>：
					</td>
					<td class="hou"　style="width:30%">
						<%=securityStaffBaseinfo.getStaffCode()%>
					</td>
					<td class="tblLable">
						<bean:message key="security.jsp.commom.name" bundle="security"/>：
					</td>
					<td class="hou" style="width:30%">
						<%=securityStaffBaseinfo.getName()%>
					</td>
				</tr>
				<tr>
					<td class="tblLable" >
						<bean:message key="security.jsp.commom.hspConfigBaseinfoName" bundle="security"/>：
					</td>
					<td class="hou" colspan="3">
						<%=securityStaffBaseinfo.getHspConfigBaseinfoName()%>
					</td>
					
				</tr>
				<tr>
					<td class="tblLable">
						<bean:message key="security.jsp.commom.nameEn" bundle="security"/>：
					</td>
					<td class="hou">
						<%=securityStaffBaseinfo.getNameEn()%>
					</td>
					<td class="tblLable">
						<bean:message key="security.jsp.commom.birthDay" bundle="security"/>：
					</td>
					<%
						if(securityStaffBaseinfo.getYear() != null && securityStaffBaseinfo.getYear().length() > 0){
					 %>
					 <td class="hou">
					 	<%=securityStaffBaseinfo.getYear()%>-<%=securityStaffBaseinfo.getMonth()%>-<%=securityStaffBaseinfo.getDay()%>		
					 </td>
					<%
						}else{
					 %>
					 <td class="hou">
					 </td>
					 <%
						}
					 %>
				</tr>
				<tr>
					<td class="tblLable">
						<bean:message key="security.jsp.commom.commConfigSexId" bundle="security"/>：
					</td>
					<td class="hou">
						<%=securityStaffBaseinfo.getCommConfigSexName()%>
					</td>

					<td class="tblLable">
						<bean:message key="security.jsp.securitystaffbaseinfo.commom1" bundle="security"/>：
					</td>
					<td  class="hou">
						<%=securityStaffBaseinfo.getEmail() %>
					</td>
				</tr>
				<tr>
					<td class="tblLable">
						<bean:message key="security.jsp.commom.idNo" bundle="security"/>：
					</td>
					<td class="hou">
						<%=securityStaffBaseinfo.getIdNo()%>
					</td>

					<td class="tblLable">
						<bean:message key="security.jsp.commom.commConfigStafftypeId" bundle="security"/>：
					</td>
					<td class="hou">
						<%=securityStaffBaseinfo.getCommConfigStafftypeName()%>
					</td>
				</tr>
				<tr>
					<td class="tblLable">
						<bean:message key="security.jsp.commom.islocation" bundle="security"/>：
					</td>
					<td class="hou">
						<%=securityStaffBaseinfo.getIslocationName()%>
					</td>

					<td class="tblLable">
						<bean:message key="security.jsp.commom.mobilecode" bundle="security"/>：
					</td>
					<td class="hou">
						<%= securityStaffBaseinfo.getPhone() %>
					</td>
				</tr>
				<tr>
					<td class="tblLable">
						注册码：
					</td>
					<td class="hou">
						<%=securityStaffBaseinfo.getRegistCode() %>
					</td>
					<td class="tblLable">
						注册码生成时间：
					</td>
					<td class="hou">
						<%=securityStaffBaseinfo.getRegTime() %>
					</td>
				</tr>
				<tr>
					<td class="tblLable">
						激活时间：
					</td>
					<td class="hou">
						<%=securityStaffBaseinfo.getStartTime() %>
					</td>
					<td class="tblLable">
						有效截止日期：
					</td>
					<td class="hou">
						<%=securityStaffBaseinfo.getStopDate() %>
					</td>
				</tr>
				<tr>
					<td class="tblLable">
						<bean:message key="security.jsp.commmom.comments" bundle="security"/>：
					</td>
					<td class="hou" colspan="3">
						<%=securityStaffBaseinfo.getComments()%>
					</td>
				</tr>
				<tr>
				</tr>
				<!--<tr>
					<td class="tdLeft">
						<bean:message key="security.jsp.commom.homePageType" bundle="security"/>：
					</td>
					<td class="tdRight">
						<%if(securityStaffBaseinfo.getHomePageType().equals("1")){
							%>
							<bean:message key="security.jsp.commom.item8" bundle="security"/>
							<%
						}%>
						<%if(securityStaffBaseinfo.getHomePageType().equals("2")){
							%>
							<bean:message key="security.jsp.commom.item9" bundle="security"/>
							<%
						}%>
						<%if(securityStaffBaseinfo.getHomePageType().equals("3")){
							%>
							<bean:message key="security.jsp.commom.item10" bundle="security"/>
							<%
						}%>
					</td>
				</tr>-->
			</table>
			<div style="width: 803px; padding-top: 5px; padding-right: 5px; line-height: 30px; text-align: right;" >
				<bean:message key="security.jsp.securitystaffbaseinfo.detail.userName" bundle="security"/>：
				<font style="color: #0060BF;"><%=securityStaffBaseinfo.getCreateUserName()%></font>&nbsp;&nbsp;&nbsp;&nbsp; <bean:message key="security.jsp.securitystaffbaseinfo.detail.recordDate" bundle="security"/>：
				<%
					if(securityStaffBaseinfo.getCreateDateYear() != null && securityStaffBaseinfo.getCreateDateYear().length() > 0){
				 %>
				<font style="color: #0060BF;"><%=securityStaffBaseinfo.getCreateDateYear()%>-<%=securityStaffBaseinfo.getCreateDateMonth()%>-<%=securityStaffBaseinfo.getCreateDateDay()%></font>&nbsp;&nbsp;
				<%
					}
				 %>
			</div>
			<div class="btnSave" id="btnSave">
				<input type="button"  style="font-size:12px;font-family:Arial;" name="btn" value="打印" onclick="QWPrint();" />  
			</div>
		</form>
	</body>
</html>
