<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="bean" uri="/WEB-INF/struts-bean.tld"%>
<jsp:useBean id="commConfigSex" scope="request" class="com.tianjian.comm.struts.form.CommConfigSexForm" />
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
		<title></title>
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
						<bean:message key="comm.jsp.commconfigsex.detail.title" bundle="conf.comm.CommMessageguoh"/>
					</td>
				</tr>
				<tr>
					<td class="tblLable">
						<bean:message key="comm.jsp.commconfigsex.detail.td1" bundle="conf.comm.CommMessageguoh"/>
					</td>
					<td class="hou">
						<%=commConfigSex.getItemCode()%>
					<td class="tblLable" nowrap>
						<bean:message key="comm.jsp.commconfigsex.detail.td2" bundle="conf.comm.CommMessageguoh"/>
					</td>
					<td class="hou">
						<%=commConfigSex.getItemName()%>
					</td>
				</tr>
				<tr>
					<td class="tblLable">
						<bean:message key="comm.jsp.commconfigsex.detail.td3" bundle="conf.comm.CommMessageguoh"/>
					</td>
					<td class="hou">
						<%=commConfigSex.getSeqNo()%>
					</td>
					<td class="tblLable">
						<bean:message key="comm.jsp.commconfigsex.detail.td4" bundle="conf.comm.CommMessageguoh"/>
					</td>
					<td class="hou">
						<%=commConfigSex.getInputCode()%>
					</td>
				</tr>
				<tr>
					<td class="tblLable">
						<bean:message key="comm.jsp.commconfigsex.detail.td5" bundle="conf.comm.CommMessageguoh"/>
					</td>
					<td class="hou" colspan="3">
						<%=commConfigSex.getComments()%>
					</td>
				</tr>
			</table>
			<!-- Sheet operation button area -->
			<div class="btnSave" id="btnSave">
				<input type="button"  name="btnBack" value='<bean:message key="comm.jsp.commconfigsex.detail.input" bundle="conf.comm.CommMessageguoh"/>' onClick="history.go(-1);" />
				<input type="button"  style="font-size:12px;font-family:Arial;" name="btn" value="打印" onclick="QWPrint();" />  
			</div>
		</form>
	</body>
</html>
