<%@page import="java.util.List"%>
<%@page import="java.util.Iterator"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<jsp:useBean id="dataForm" class="com.tianjian.hsp.struts.form.HspStaffBaseinfoForm" scope="request"></jsp:useBean>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
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
		<script language="javascript" src="<%=request.getContextPath()%>/ciha/include/javascript/gettext_staff.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/ciha/include/javascript/jianbian.js"></script>
		<script language="javascript">	
		function trim(str){
 			return str.replace(/(^\s*)|(\s*$)/g, "");
		}
		function huiche(){
			if(event.keyCode==13){
				event.keyCode=9
			}
		}
		function saveForm(){
			document.form.submit();
		}
		
		function printTure() 
	{
		var budivList = document.getElementsByName("budiv");
		for(var i=0; i<budivList.length; i++){
			budivList[i].style.display="none";
		}
		window.print();
		for(var i=0; i<budivList.length; i++){
			budivList[i].style.display="";
		}
	}
		function goBack(url){
			window.location=url;
		}
	</script>
	</head>
	
	<body>
		<form name="form"
			action="<%=request.getContextPath()%>/hsp/hspStaffBaseinfo.do"
			method="post" enctype="multipart/form-data">
			<input type="hidden" name="verbId" value="add" />
			<table width="100%" border="0" cellpadding="0" cellspacing="0" class="tblView" align="center">
				<tr>
					<td class="tblTitle" colspan="4">
						<bean:message  bundle="hsp.hspLocale" key="hsp.jsp.hspstaffbaseinfodetail.userDetailInfo"/> 
					</td>
				</tr>
				<tr>
					<td class="tblLable" style="width:20%">
						<bean:message  bundle="hsp.hspLocale" key="hsp.jsp.hspstaffbaseinfodetail.organ"/>：
					</td>
					<td class="hou"  style="width:30%">
						${dataForm.hspConfigBaseinfoName}
					</td>
					<td class="tblLable" style="width:20%">
						科室：
					</td>
					<td class="hou" style="width:30%">
						${dataForm.deptName}
					</td>
				</tr>
				<tr>
					<td class="tblLable">
						<bean:message  bundle="hsp.hspLocale" key="hsp.jsp.common.userId"/>：
					</td>
					<td class="hou">
						${dataForm.empNo}
					</td>
					<td class="tblLable">
						<bean:message  bundle="hsp.hspLocale" key="hsp.jsp.common.name"/> :
					</td>
					<td class="hou">
						${dataForm.name}
					</td>
				</tr>
				<tr>
					<td class="tblLable">
						<bean:message  bundle="hsp.hspLocale" key="hsp.jsp.common.idNo"/>：
					</td>
					<td class="hou">
						${dataForm.idNo}
					</td>
					<td class="tblLable">
						<bean:message  bundle="hsp.hspLocale" key="hsp.jsp.common.birthDay"/>：
					</td>
					<td class="hou">
						${dataForm.birthdayYear}
					</td>
				</tr>
				<tr>
					<td class="tblLable">
						<bean:message  bundle="hsp.hspLocale" key="hsp.jsp.hspstaffbaseinfodetail.sex1"/>：
					</td>
					<td class="hou">
						${dataForm.commConfigSexName }
					</td>

					<td class="tblLable">
						<bean:message  bundle="hsp.hspLocale" key="hsp.jsp.hspstaffbaseinfodetail.nation1"/>：
					</td>
					<td class="hou">
						${dataForm.commConfigNationalityName}
					</td>
				</tr>
				<tr>
					<td class="tblLable">
						<bean:message  bundle="hsp.hspLocale" key="hsp.jsp.common.workDate"/>：
					</td>
					<td class="hou">
						${dataForm.startWorkDateYear}
					</td>

					<td class="tblLable">
						<bean:message  bundle="hsp.hspLocale" key="hsp.jsp.common.officePhone"/>：
					</td>
					<td class="hou">
						${dataForm.officeTel}
					</td>
				</tr>
				<tr>
					<td class="tblLable">
						<bean:message  bundle="hsp.hspLocale" key="hsp.jsp.common.phone"/>：
					</td>
					<td class="hou">
						${dataForm.mobileTel}
					</td>
					<td class="tblLable">
						<bean:message  bundle="hsp.hspLocale" key="hsp.jsp.common.doctorCardId"/>：
					</td>
					<td class="hou">
						${dataForm.workCertificateNo}
					</td>
				</tr>
				<tr>
					<td class="tblLable">
						<bean:message  bundle="hsp.hspLocale" key="hsp.jsp.common.onStatusSign"/>：
					</td>
					<td class="hou">
						<logic:equal name="dataForm" value="1" property="islocation">
			    		<bean:message  bundle="hsp.hspLocale" key="hsp.jsp.common.on"/>
		    		</logic:equal>
						<logic:equal name="dataForm" value="0" property="islocation">
			    		<bean:message  bundle="hsp.hspLocale" key="hsp.jsp.common.notIn"/>
		    		</logic:equal>
					</td>
					<td class="tblLable">
	
						<bean:message  bundle="hsp.hspLocale" key="hsp.jsp.common.majorClass"/>：
	
					</td>
					<td class="hou">
						${dataForm.commDictPublicCharName1 }
					</td>
				</tr>
				<tr>
					<td class="tblLable">
	
						<bean:message  bundle="hsp.hspLocale" key="hsp.jsp.common.doctorSort"/>：
	
					</td>
					<td class="hou">
						${dataForm.commDictPublicCharName2 }
					</td>
					<td class="tblLable">

						<bean:message  bundle="hsp.hspLocale" key="hsp.jsp.common.managePost"/>：

					</td>

					<td class="hou">
						${dataForm.commConfigPositiontitleName }
					</td>
				</tr>
				<tr>
					<td class="tblLable">
						<bean:message  bundle="hsp.hspLocale" key="hsp.jsp.common.skillJudge"/>：
					</td>

					<td class="hou">
						${dataForm.commConfigEmptitleName }
					</td>
					<td class="tblLable">
						<bean:message  bundle="hsp.hspLocale" key="hsp.jsp.common.skillPost"/>：
					</td>
					<td class="hou">
						${dataForm.commDictPublicCharName3 }
					</td>
				</tr>
				<tr>
					<td class="tblLable">
						<bean:message  bundle="hsp.hspLocale" key="hsp.jsp.common.educationBg"/>：
					</td>
					<td class="hou">
						${dataForm.commConfigDegreeName }
					</td>
					<td class="tblLable">
						<bean:message  bundle="hsp.hspLocale" key="hsp.jsp.common.degree"/>：
					</td>
					<td class="hou">
						${dataForm.commConfigDegreeLevelName }
					</td>
				</tr>
				<tr>
					<td class="tblLable">
						<bean:message  bundle="hsp.hspLocale" key="hsp.jsp.common.studyMajor"/>：
					</td>
					<td class="hou" colspan="3">
						${dataForm.commConfigProfessionName }
					</td>
				</tr>
			</table>
			<div
				style="width: 803px; padding-top: 5px; padding-right: 5px; line-height: 30px; text-align: right;">
				<bean:message  bundle="hsp.hspLocale" key="hsp.jsp.common.createUserName"/>：
				<font style="color: #0060BF;">${dataForm.createUserName}</font>&nbsp;&nbsp;&nbsp;&nbsp;
				<bean:message  bundle="hsp.hspLocale" key="hsp.jsp.common.createDate"/>：
				<font style="color: #0060BF;">${dataForm.year}<bean:message  bundle="hsp.hspLocale" key="hsp.jsp.common.year"/>${dataForm.month}
					<bean:message  bundle="hsp.hspLocale" key="hsp.jsp.common.month"/>${dataForm.day}<bean:message  bundle="hsp.hspLocale" key="hsp.jsp.common.day"/></font>&nbsp;&nbsp;
			</div>
			<div class="btnSave">
				<%
					if(!"1".equals(request.getParameter("useForTree"))){
				%>
				<input type="button"  value="<bean:message  bundle="hsp.hspLocale" key="hsp.jsp.common.back1"/>" onclick="history.go(-1)"	 /> <!-- onclick="goBack('< %=request.getContextPath()%>/hsp/hspStaffBaseinfo.do?verbId=query')" -->
				<%
					}
				%>
		        <input type="button" name="btnBack" value="<bean:message  bundle="hsp.hspLocale" key="hsp.jsp.hspconfigbaseinfodetail.print"/>" onclick="printTure();" />   
			</div>
		</form>
	</body>
</html>
