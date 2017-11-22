<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="html" uri="/WEB-INF/struts-html.tld"%>
<%@ taglib prefix="bean" uri="/WEB-INF/struts-bean.tld"%>
<%@ taglib prefix="logic" uri="/WEB-INF/struts-logic.tld"%>
<jsp:useBean id="dataForm" scope="request" class="com.tianjian.security.struts.form.SecurityConfigPublicClassForm" />
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
		<title><bean:message key="security.jsp.securityconfigpublicclass.detail.title" bundle="conf.security.security"/></title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
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
		<form name="form" method="post">
			<input type="hidden" name="verbId" value="detail">
			<table width="100%" border="0" cellpadding="0" cellspacing="0" class="tblView" align="center">
				<tr>
					<td class="tblTitle" colspan="4">
						<span>※</span>&nbsp;&nbsp;<bean:message key="security.jsp.securityconfigpublicclass.detail.topic" bundle="conf.security.security"/>
					&nbsp;&nbsp;<span>※</span>
					</td>
				</tr>
				<tr>
					<!--<td class="qian" nowrap>
						ID:
					</td>
					<td class="hou" nowrap>
						${dataForm.data.id}
					</td>  -->		
					<td class="tblLable">
						<bean:message key="security.jsp.securityconfigpublicclass.common.className" bundle="conf.security.security"/>：
					</td>
					<td class="td_col3" colspan="3">
						${dataForm.data.className}
					</td>
				</tr>
				<tr>
					<td class="tblLable">
						<bean:message key="security.jsp.securityconfigpublicclass.common.classCode" bundle="conf.security.security"/>：
					</td>
					<td class="hou">
						${dataForm.data.classCode}
					</td>
				
					<td class="tblLable">
						<bean:message key="security.jsp.securityconfigpublicclass.common.serialNo" bundle="conf.security.security"/>：
					</td>
					<td class="hou">
						${dataForm.data.serialNo}
					</td>
				</tr>
				<tr>
					
					<td class="tblLable">
						<bean:message key="security.jsp.securityconfigpublicclass.common.inputCode" bundle="conf.security.security"/>：
					</td>
					<td class="hou">
						${dataForm.data.inputCode}
					</td>
				
					<td class="tblLable">
						<bean:message key="security.jsp.securityconfigpublicclass.common.comments" bundle="conf.security.security"/>：
					</td>
					<td class="hou">
						${dataForm.data.comments}
					</td>
				</tr>
				<tr>
					
					<td class="tblLable">
						<bean:message key="security.jsp.securityconfigpublicclass.common.parentId" bundle="conf.security.security"/>：
					</td>
					<td class="hou">
						${dataForm.data.securityConfigPublicClassObject.className}
					</td>
				
					<td class="tblLable">
						<bean:message key="security.jsp.securityconfigpublicclass.common.levelFlag" bundle="conf.security.security"/>：
					</td>
					<td class="hou">
						<%if(dataForm.getData().getLevelFlag()!=null && String.valueOf(dataForm.getData().getLevelFlag()).equals("1")){ %>
							1级
						<%}else if(dataForm.getData().getLevelFlag()!=null && String.valueOf(dataForm.getData().getLevelFlag()).equals("2")){ %>
							2级
						<%}else{ %>
						<%} %>
					</td>
				</tr>
				<tr>
					<td class="tblLable">
						<bean:message key="security.jsp.securityconfigpublicclass.common.picPath" bundle="conf.security.security"/>：
					</td>
					<td class="hou">
						${dataForm.data.picPath}
					</td>
				
					<td class="tblLable">
						<bean:message key="security.jsp.securityconfigpublicclass.common.sysFlag" bundle="conf.security.security"/>：
					</td>
					<td class="hou">
						<%if(dataForm.getData().getSysFlag()!= null && String.valueOf(dataForm.getData().getSysFlag()).equals("1")){ %>
							系统内
						<%}else if(dataForm.getData().getSysFlag()!=null && String.valueOf(dataForm.getData().getSysFlag()).equals("0")){ %>
							系统外
						<%}else{ %>
						<%} %>
					</td>
				</tr>
				<tr>
					<td class="tblLable">
						<bean:message key="security.jsp.securityconfigpublicclass.common.redirectUrl" bundle="conf.security.security"/>：
					</td>
					<td class="hou">
						${dataForm.data.redirectUrl}
					</td>
					<td class="tblLable">
						所属系统分类：
					</td>
					<td class="hou">
						<%if(dataForm.getData().getAppSysFlag()!= null && String.valueOf(dataForm.getData().getAppSysFlag()).equals("1")){ %>
							应用系统
						<%}else if(dataForm.getData().getAppSysFlag()!= null && String.valueOf(dataForm.getData().getAppSysFlag()).equals("0")){ %>
							支撑系统
						<%}else{ %>
						<%} %>
					</td>
				</tr>
			</table>
			<!-- Sheet operation button area -->
			<div class="btnSave" id="btnSave">
				<input type="button"  name="btnBack" value='<bean:message key="security.jsp.commom.button2" bundle="security"/>' onClick="history.go(-1);" />
				<input type="button"  name="btn" value="打印" onclick="QWPrint();" />  
			</div>
		</form>
	</body>
</html>
