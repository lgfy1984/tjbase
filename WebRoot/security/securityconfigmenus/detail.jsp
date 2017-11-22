<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="bean" uri="/WEB-INF/struts-bean.tld"%>
<%@ taglib prefix="logic" uri="/WEB-INF/struts-logic.tld"%>
<jsp:useBean id="securityConfigMenus" scope="request" class="com.tianjian.security.struts.form.SecurityConfigMenusForm" />
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
		<title><bean:message key="security.jsp.securityconfigmenus.detail.title" bundle="conf.security.security"/></title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<link type="text/css" rev="stylesheet" rel="stylesheet" href="include/css/form.css" />
		<script language="javascript">
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
					<td class="tblTitle" colspan="4">
						<bean:message key="security.jsp.securityconfigmenus.detail.title" bundle="conf.security.security"/>
					</td>
				</tr>
				<tr>
					<td class="tblLable">
						模块:
					</td>
					<td class="hou" colspan="3">
						${dataForm.data.securityConfigPublicObject.reason}
					</td>
				</tr>
				<tr>
					<td class="tblLable">
						ID:
					</td>
					<td class="hou" >
						${dataForm.data.id}
					</td>
					<td class="tblLable">
						<!--名称  --><bean:message key="jsp.name" bundle="conf.Init"/>:
					</td>
					<td class="hou" >
						${dataForm.data.menuDetail}
					</td>
				</tr>
				<tr>
					<td class="tblLable">
						<!--序号  --><bean:message key="jsp.serialNo" bundle="conf.Init"/>:
					</td>
					<td class="hou" >
						${dataForm.data.serialNo}
					</td>
					<td class="tblLable">
						<!--输入码  --><bean:message key="jsp.inputCode" bundle="conf.Init"/>:
					</td>
					<td class="hou" >
						${dataForm.data.inputCode}
					</td>
				</tr>
				<tr>
					<td class="tblLable">
						<!--链接地址  --><bean:message key="security.jsp.securityconfigmenus.common.menuUrl" bundle="conf.security.security"/>:
					</td>
					<td class="hou" colspan="3">
						${dataForm.data.menuUrl}
					</td>
				</tr>
				<tr>
					<td class="tblLable">
						<!--菜单说明  --><bean:message key="security.jsp.securityconfigmenus.common.menuNotice" bundle="conf.security.security"/>:
					</td>
					<td class="hou">
						${dataForm.data.menuNotice}
					</td>
					<td class="tblLable">
						<!--末节点标志  --><bean:message key="security.jsp.securityconfigmenus.common.endLevelFlag" bundle="conf.security.security"/>:
					</td>
					<td class="hou">
						${dataForm.data.endLevelFlag}
					</td>
				</tr>
				<tr>
					<td class="tblLable">
						<!--菜单级别  --><bean:message key="security.jsp.securityconfigmenus.common.menuLevel" bundle="conf.security.security"/>:
					</td>
					<td class="hou">
						${dataForm.data.menuLevel}
					</td>
					<td class="tblLable">
						<!--菜单序号  --><bean:message key="security.jsp.securityconfigmenus.common.menuSeq" bundle="conf.security.security"/>:
					</td>
					<td class="hou">
						${dataForm.data.menuSeq}
					</td>
				</tr>
				<tr>
					<td class="tblLable">
						<!--菜单数据  --><bean:message key="security.jsp.securityconfigmenus.common.menuData" bundle="conf.security.security"/>:
					</td>
					<td class="hou">
						${dataForm.data.menuData}
					</td>
					<td class="tblLable">
						<!--菜单方法  --><bean:message key="security.jsp.securityconfigmenus.common.menuMethod" bundle="conf.security.security"/>:
					</td>
					<td class="hou">
						${dataForm.data.menuMethod}
					</td>
				</tr>
				<tr>
					<td class="tblLable">
						<!--图标  --><bean:message key="security.jsp.securityconfigmenus.common.menuIcon" bundle="conf.security.security"/>:
					</td>
					<td class="hou" colspan="3">
						${dataForm.data.menuIcon}
					</td>
				</tr>
				<tr>
					<td class="tblLable">
						<!--菜单目标  --><bean:message key="security.jsp.securityconfigmenus.common.menuTarget" bundle="conf.security.security"/>:
					</td>
					<td class="hou" colspan="3">
						${dataForm.data.menuTarget}
					</td>
				</tr>
				<tr>
					<td class="tblLable">
						<!--父类菜单  --><bean:message key="security.jsp.securityconfigmenus.common.parentId" bundle="conf.security.security"/>:
					</td>
					<td class="hou"  colspan="3">
						${dataForm.data.securityConfigMenusObject.menuDetail}
					</td>
				</tr>
				<tr>
					<td class="tblLable">
						<bean:message key="security.jsp.securityconfigmenus.common.displayType" bundle="conf.security.security"/>:
					</td>
					<td class="hou"  colspan="3">
						<logic:equal name="dataForm" property="data.displayType" value="0">
							<bean:message key="security.jsp.securityconfigmenus.common.displayTypeOption1" bundle="conf.security.security"/>
						</logic:equal>
						<logic:equal name="dataForm" property="data.displayType" value="1">
							<bean:message key="security.jsp.securityconfigmenus.common.displayTypeOption2" bundle="conf.security.security"/>
						</logic:equal>
					</td>
				</tr>
				<tr>
					<td class="tblLable">
						<!--备注  --><bean:message key="jsp.comments" bundle="conf.Init"/>:
					</td>
					<td class="hou" colspan="3">
						${dataForm.data.comments}
					</td>
				</tr>
			</table>
			<!-- Sheet operation button area -->
			<div class="btnSave">
				<input type="button" style="font-size:12px;font-family:Arial;" name="btnBack" value='关闭' onClick="closeIframe();" />
			</div>
		</form>
	</body>
</html>
