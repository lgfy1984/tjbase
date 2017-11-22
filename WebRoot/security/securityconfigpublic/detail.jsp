<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="html" uri="/WEB-INF/struts-html.tld"%>
<%@ taglib prefix="bean" uri="/WEB-INF/struts-bean.tld"%>
<%@ taglib prefix="logic" uri="/WEB-INF/struts-logic.tld"%>
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
	  		function closeIframe(){
				parent.document.getElementById("mask").style.visibility='hidden'
				parent.document.getElementById("iframe1").style.display = "none";
			}
		</script>
	</head>
	<body>
		<form name="form" method="post">
			<input type="hidden" name="verbId" value="detail">
			<table width="100%" border="0" cellpadding="0" cellspacing="0" class="tblView" align="center">
				<tr>
					<td class="tblTitle" colspan="6">
						<span>※</span>&nbsp;&nbsp;<bean:message key="security.jsp.securityConfigpublic.commom.item" bundle="security"/>
					 &nbsp;&nbsp;<span>※</span>
					</td>
				</tr>
				<!--<tr>
					<td class="qian" nowrap>
						ID:
					</td>
					<td class="hou" colspan="3"  nowrap>
						${dataForm.data.id}
					</td>
				</tr>  -->
				<tr>
					<td class="tblLable">
						<bean:message key="security.jsp.commmom.classCode" bundle="security"/>：
					</td>
					<td class="hou">
						${dataForm.data.reason}
					</td>
					<td class="tblLable">
						<bean:message key="security.jsp.commom.menuCode" bundle="security"/>：
					</td>
					<td class="hou">
						${dataForm.data.modCode}
						</td>
				</tr>
				<tr>
					<td class="tblLable">
						<bean:message key="security.jsp.commom.serialNo" bundle="security"/>：
					</td>
					<td class="hou">
						${dataForm.data.serialNo}
					</td>
					<td class="tblLable">
						<bean:message key="security.jsp.commom.inputcode" bundle="security"/>：
					</td>
					<td class="hou">
						${dataForm.data.inputCode}
					</td>
				</tr>
				<tr>
					<td class="tblLable">
						<bean:message key="security.jsp.commmom.comments" bundle="security"/>：
					</td>
					<td class="hou">
						${dataForm.data.comments}
					</td>
					<td class="tblLable">
						<bean:message key="security.jsp.commom.scpcId" bundle="security"/>：
					</td>
					<td class="hou">
						${dataForm.data.securityConfigPublicClassObject.className}
					</td>
				</tr>
				<tr>
					<td class="tblLable">
						<bean:message key="security.jsp.commom.SecurityConfigPublic" bundle="security"/>：
					</td>
					<td class="hou">
						${dataForm.data.reasonValue}
					</td>
					<td class="tblLable">
						<bean:message key="security.jsp.commom.picPath" bundle="security"/>：
					</td>
					<td class="hou">
						${dataForm.data.picPath}
					</td>
				</tr>
				<tr>
					<td class="tblLable">
						模块链接：
					</td>
					<td class="hou" colspan="3">
						${dataForm.data.publicUrl}
					</td>
				</tr>
				<tr>
					<td class="tblLable">
						模块目标：
					</td>
					<td class="hou" colspan="3">
						${dataForm.data.publicTarget}
					</td>
				</tr>
			</table>
			<!-- Sheet operation button area -->
			<div class="btnSave" id="btnSave">
				<input type="button" style="font-size:12px;font-family:Arial;" name="btnBack" value='返回' onClick="javascript:history.go(-1);void(0);" />
			</div>
		</form>
	</body>
</html>
