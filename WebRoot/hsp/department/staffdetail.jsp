<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<jsp:useBean id="dataForm" scope="request" class="com.tianjian.hsp.struts.form.OrgMenuForm" />
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
		<title><bean:message key="security.jsp.securityconfigmenus.detail.title" bundle="conf.security.security"/></title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<meta http-equiv="pragma" content="no-cache" />
		<meta http-equiv="cache-control" content="no-cache" />
		<meta http-equiv="expires" content="0" />
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
					<td class="tblLable">
						<bean:message  bundle="hsp.hspLocale" key="hsp.jsp.hspstaffbaseinfodetail.organ"/>：
					</td>
					<td class="hou">
						${dataForm.hspConfigBaseinfoName}
					</td>
					<td class="tblLable">
						<bean:message  bundle="hsp.hspLocale" key="hsp.jsp.common.userId"/>：
					</td>
					<td class="hou">
						${dataForm.empNo}
					</td>
				</tr>
				<tr>
					<td class="tblLable">
						<bean:message  bundle="hsp.hspLocale" key="hsp.jsp.common.name"/> :
					</td>
					<td class="hou">
						${dataForm.name}
					</td>
					<td class="tblLable">
						<bean:message  bundle="hsp.hspLocale" key="hsp.jsp.common.idNo"/>：
					</td>
					<td class="hou">
						${dataForm.idNo}
					</td>
				</tr>
				<tr>
					<td class="tblLable">
						<bean:message  bundle="hsp.hspLocale" key="hsp.jsp.common.birthDay"/>：
					</td>
					<td class="hou">
						${dataForm.birthdayYear}
					</td>
					<td class="tblLable">
						<bean:message  bundle="hsp.hspLocale" key="hsp.jsp.hspstaffbaseinfodetail.sex1"/>：
					</td>
					<td class="hou">
						<logic:equal name="dataForm" value="1" property="commConfigSexId">
			    		<bean:message  bundle="hsp.hspLocale" key="hsp.jsp.hspstaffbaseinfodetail.man"/>
		    		</logic:equal>
						<logic:equal name="dataForm" value="2" property="commConfigSexId">
			    		<bean:message  bundle="hsp.hspLocale" key="hsp.jsp.hspstaffbaseinfodetail.women"/>
		    		</logic:equal>
					</td>

				</tr>
				<tr>
					<td class="tblLable">
						<bean:message  bundle="hsp.hspLocale" key="hsp.jsp.hspstaffbaseinfodetail.nation1"/>：
					</td>
					<td class="hou">
						<c:forEach items="${dataForm.commConfigNationalityIdL}"
							var="commConfigNationalityIdL">
							<logic:equal name="dataForm"
								value="${commConfigNationalityIdL.itemCode}"
								property="commConfigNationalityId">
							${commConfigNationalityIdL.itemName}
						</logic:equal>
						</c:forEach>
					</td>
					<td class="tblLable">
						<bean:message  bundle="hsp.hspLocale" key="hsp.jsp.common.workDate"/>：
					</td>
					<td class="hou">
						${dataForm.startWorkDateYear}
					</td>

				</tr>
				<tr>
					<td class="tblLable">
						<bean:message  bundle="hsp.hspLocale" key="hsp.jsp.common.officePhone"/>：
					</td>
					<td class="hou">
						${dataForm.officeTel}
					</td>
					<td class="tblLable">
						<bean:message  bundle="hsp.hspLocale" key="hsp.jsp.common.phone"/>：
					</td>
					<td class="hou">
						${dataForm.mobileTel}
					</td>
				</tr>
				<tr>
					<td class="tblLable">
						<bean:message  bundle="hsp.hspLocale" key="hsp.jsp.common.doctorCardId"/>：
					</td>
					<td class="hou">
						${dataForm.workCertificateNo}
					</td>
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
				</tr>
				<tr>
				<td class="tblLable">

					<bean:message  bundle="hsp.hspLocale" key="hsp.jsp.common.majorClass"/>：

				</td>
				<td class="hou">
					<c:forEach items="${dataForm.commDictPublicCharId1List}"
						var="commDictPublicCharId1List">
						<logic:equal name="dataForm"
							value="${commDictPublicCharId1List.id}"
							property="commDictPublicCharId1">
  							${commDictPublicCharId1List.dictValue}
  						</logic:equal>
					</c:forEach>
				</td>
				<td class="tblLable">

					<bean:message  bundle="hsp.hspLocale" key="hsp.jsp.common.doctorSort"/>：

				</td>
				<td class="hou">
					<c:forEach items="${dataForm.commDictPublicCharId2List}"
						var="commDictPublicCharId2List">
						<logic:equal name="dataForm"
							value="${commDictPublicCharId2List.id}"
							property="commDictPublicCharId2">
  							${commDictPublicCharId2List.dictValue}
  						</logic:equal>
					</c:forEach>
				</td>
				</tr>

				<tr>
					<td class="tblLable">

						<bean:message  bundle="hsp.hspLocale" key="hsp.jsp.common.managePost"/>：

					</td>

					<td class="hou">
						<c:forEach items="${dataForm.commConfigPositiontitleIdList}"
							var="commConfigPositiontitleIdList">
							<logic:equal name="dataForm"
								value="${commConfigPositiontitleIdList.id}"
								property="commConfigPositiontitleId">
  							${commConfigPositiontitleIdList.dictValue}
  						</logic:equal>
						</c:forEach>
					</td>
					<td class="tblLable">
						<bean:message  bundle="hsp.hspLocale" key="hsp.jsp.common.skillJudge"/>：
					</td>

					<td class="hou">
						<c:forEach items="${dataForm.commConfigEmptitleIdList}"
							var="commConfigEmptitleIdList">
							<logic:equal name="dataForm"
								value="${commConfigEmptitleIdList.itemCode}"
								property="commConfigEmptitleId">
  							${commConfigEmptitleIdList.itemName}
  						</logic:equal>
						</c:forEach>
					</td>
				</tr>

				<tr>
					<td class="tblLable">
						<bean:message  bundle="hsp.hspLocale" key="hsp.jsp.common.skillPost"/>：
					</td>
					<td class="hou">
						<c:forEach items="${dataForm.commDictPublicCharId3List}"
							var="commDictPublicCharId3List">
							<logic:equal name="dataForm"
								value="${commDictPublicCharId3List.id}"
								property="commDictPublicCharId3">
  							${commDictPublicCharId3List.dictValue}
  						</logic:equal>
						</c:forEach>
					</td>
					<td class="tblLable">
						<bean:message  bundle="hsp.hspLocale" key="hsp.jsp.common.educationBg"/>：
					</td>
					<td class="hou">
						<c:forEach items="${dataForm.commConfigDegreeIdList}"
							var="commConfigDegreeIdList">
							<logic:equal name="dataForm" value="${commConfigDegreeIdList.id}"
								property="commConfigDegreeId">
  							${commConfigDegreeIdList.dictValue}
  						</logic:equal>
						</c:forEach>
					</td>
				</tr>

				<tr>
					<td class="tblLable">
						<bean:message  bundle="hsp.hspLocale" key="hsp.jsp.common.degree"/>：
					</td>
					<td class="hou">
						<c:forEach items="${dataForm.commConfigDegreeLevelIdList}"
							var="commConfigDegreeLevelIdList">
							<logic:equal name="dataForm"
								value="${commConfigDegreeLevelIdList.id}"
								property="commConfigDegreeLevelId">
  							${commConfigDegreeLevelIdList.dictValue}
  						</logic:equal>
						</c:forEach>
					</td>
					<td class="tblLable">
						<bean:message  bundle="hsp.hspLocale" key="hsp.jsp.common.studyMajor"/>：
					</td>
					<td class="hou">
						<c:forEach items="${dataForm.commConfigProfessionIdList}"
							var="commConfigProfessionIdList">
							<logic:equal name="dataForm"
								value="${commConfigProfessionIdList.id}"
								property="commConfigProfessionId">
  							${commConfigProfessionIdList.dictValue}
  						</logic:equal>
						</c:forEach>
					</td>
				</tr>
				<tr>
					<td class="tblLable">
						所属科室：
					</td>
					<td class="hou" colspan="3">
						${dataForm.deptName}
					</td>

				</tr>
			</table>
			
			<div
				style="width: 803px; padding-top: 5px; padding-right: 5px; line-height: 30px; text-align: center;">
				<bean:message  bundle="hsp.hspLocale" key="hsp.jsp.common.createUserName"/>：
				<font style="color: #0060BF;">${dataForm.createUserName}</font>&nbsp;&nbsp;&nbsp;&nbsp;
				<bean:message  bundle="hsp.hspLocale" key="hsp.jsp.common.createDate"/>：
				<font style="color: #0060BF;">${dataForm.year}<bean:message  bundle="hsp.hspLocale" key="hsp.jsp.common.year"/>${dataForm.month}
					<bean:message  bundle="hsp.hspLocale" key="hsp.jsp.common.month"/>${dataForm.day}<bean:message  bundle="hsp.hspLocale" key="hsp.jsp.common.day"/></font>&nbsp;&nbsp;
			</div>
			
			<div class="btnSave">
				<input type="button"  value="<bean:message  bundle="hsp.hspLocale" key="hsp.jsp.common.back1"/>" onclick="history.go(-1)"	 /> <!-- onclick="goBack('< %=request.getContextPath()%>/hsp/hspStaffBaseinfo.do?verbId=query')" -->
		        <input type="button" name="btnBack" value="<bean:message  bundle="hsp.hspLocale" key="hsp.jsp.hspconfigbaseinfodetail.print"/>" onclick="printTure();" />   
			</div>
		</form>
	</body>
</html>
