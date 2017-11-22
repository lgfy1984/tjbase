<%@ page language="java" pageEncoding="UTF-8" import="com.tianjian.comm.bean.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<jsp:useBean id="data" scope="request"
	class="com.tianjian.hsp.struts.form.HspStaffBaseinfoForm" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
  
	<meta http-equiv="pragma" content="no-cache"/>
	<meta http-equiv="cache-control" content="no-cache"/>
	<meta http-equiv="expires" content="0"/>    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3"/>
	<meta http-equiv="description" content="This is my page"/>
	<script language="javascript" src="<%=request.getContextPath()%>/include/javascript/eventOnKeyPress.js"></script>
	<script language="javascript" src="<%=request.getContextPath()%>/<bean:message  key="comm.js.includeTJMessage.path"  bundle="comm.commLocale"/>"></script>
	<script language="javascript">
		function saveForm(){
			if (confirmMessage("0-000008")){ 
		       document.form.verbId.value = "addLogOut";
		       document.form.submit();
		    }
		}
	</script>
	<link type="text/css" rev="stylesheet" rel="stylesheet" href="<%=request.getContextPath()%>/include/css/form.css" />
  </head>
  <body onload="showCommMessage('','${message }','1'),load()">
  	 <form name="form" action="<%=request.getContextPath()%>/hsp/hspStaffLogoutRecord.do" method="post"> 
  	 	<input type="hidden" id="verbId" name="verbId" value="addLogOut" />
  	 	<input type="hidden" id="idHidden" name="idHidden" value="<%=data.getId()%>" />
  		 <table align="center" border="0" cellpadding="0" cellspacing="0" class="tblFill" border="0" cellpadding="0" cellspacing="0" class="tblFill">
  				 <tr>
					<td class="tblTitle" colspan="4"><span>※</span> 
						卫生人员注销
					 <span>※</span></td>
				</tr>
			    <tr>
					<td class="tblLable">
						<bean:message  bundle="hsp.hspLocale" key="hsp.jsp.hspstaffbaseinfodetail.organ"/>：
					</td>
					<td class="hou">
						${data.theHspName}
					</td>
					<td class="tblLable">
						<bean:message  bundle="hsp.hspLocale" key="hsp.jsp.common.userId"/>：
					</td>
					<td class="hou">
						${data.empNo}
					</td>
				</tr>
				<tr>
					<td class="tblLable">
						<bean:message  bundle="hsp.hspLocale" key="hsp.jsp.common.name"/> ：
					</td>
					<td class="hou">
						${data.name}
					</td>
					<td class="tblLable">
						<bean:message  bundle="hsp.hspLocale" key="hsp.jsp.common.idNo"/>：
					</td>
					<td class="hou">
						${data.idNo}
					</td>
				</tr>
				<tr>
					<td class="tblLable">
						<bean:message  bundle="hsp.hspLocale" key="hsp.jsp.common.birthDay"/>：
					</td>
					<td class="hou">
						${data.birthdayYear}
					</td>
					<td class="tblLable">
						<bean:message  bundle="hsp.hspLocale" key="hsp.jsp.hspstaffbaseinfodetail.sex1"/>：
					</td>
					<td class="hou">
						<logic:equal name="data" value="1" property="commConfigSexId">
			    		<bean:message  bundle="hsp.hspLocale" key="hsp.jsp.hspstaffbaseinfodetail.man"/>
		    		</logic:equal>
						<logic:equal name="data" value="2" property="commConfigSexId">
			    		<bean:message  bundle="hsp.hspLocale" key="hsp.jsp.hspstaffbaseinfodetail.women"/>
		    		</logic:equal>
					</td>

				</tr>
				<tr>
					<td class="tblLable">
						<bean:message  bundle="hsp.hspLocale" key="hsp.jsp.hspstaffbaseinfodetail.nation1"/>：
					</td>
					<td class="hou">
						<c:forEach items="${data.commConfigNationalityIdL}"
							var="commConfigNationalityIdL">
							<logic:equal name="data"
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
						${data.startWorkDateYear}
					</td>

				</tr>
				<tr>
					<td class="tblLable">
						<bean:message  bundle="hsp.hspLocale" key="hsp.jsp.common.officePhone"/>：
					</td>
					<td class="hou">
						${data.officeTel}
					</td>
					<td class="tblLable">
						<bean:message  bundle="hsp.hspLocale" key="hsp.jsp.common.phone"/>：
					</td>
					<td class="hou">
						${data.mobileTel}
					</td>
				</tr>
				<tr>
					<td class="tblLable">
						<bean:message  bundle="hsp.hspLocale" key="hsp.jsp.common.doctorCardId"/>：
					</td>
					<td class="hou">
						${data.workCertificateNo}
					</td>
					<td class="tblLable">
						<bean:message  bundle="hsp.hspLocale" key="hsp.jsp.common.onStatusSign"/>：
					</td>
					<td class="hou">
						<logic:equal name="data" value="1" property="islocation">
			    		<bean:message  bundle="hsp.hspLocale" key="hsp.jsp.common.on"/>
		    		</logic:equal>
						<logic:equal name="data" value="0" property="islocation">
			    		<bean:message  bundle="hsp.hspLocale" key="hsp.jsp.common.notIn"/>
		    		</logic:equal>
					</td>
				</tr>
				<tr>
				<td class="tblLable">

					<bean:message  bundle="hsp.hspLocale" key="hsp.jsp.common.majorClass"/>：

				</td>
				<td class="hou">
					<c:forEach items="${data.commDictPublicCharId1List}"
						var="commDictPublicCharId1List">
						<logic:equal name="data"
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
					<c:forEach items="${data.commDictPublicCharId2List}"
						var="commDictPublicCharId2List">
						<logic:equal name="data"
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
						<c:forEach items="${data.commConfigPositiontitleIdList}"
							var="commConfigPositiontitleIdList">
							<logic:equal name="data"
								value="${commConfigPositiontitleIdList.itemCode}"
								property="commConfigPositiontitleId">
  							${commConfigPositiontitleIdList.itemName}
  						</logic:equal>
						</c:forEach>
					</td>
					<td class="tblLable">
						<bean:message  bundle="hsp.hspLocale" key="hsp.jsp.common.skillJudge"/>：
					</td>

					<td class="hou">
						<c:forEach items="${data.commConfigEmptitleIdList}"
							var="commConfigEmptitleIdList">
							<logic:equal name="data"
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
						<c:forEach items="${data.commDictPublicCharId3List}"
							var="commDictPublicCharId3List">
							<logic:equal name="data"
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
						<c:forEach items="${data.commConfigDegreeIdList}"
							var="commConfigDegreeIdList">
							<logic:equal name="data" value="${commConfigDegreeIdList.itemCode}"
								property="commConfigDegreeId">
  							${commConfigDegreeIdList.itemName}
  						</logic:equal>
						</c:forEach>
					</td>
				</tr>

				<tr>
					<td class="tblLable">
						<bean:message  bundle="hsp.hspLocale" key="hsp.jsp.common.degree"/>：
					</td>
					<td class="hou">
						<c:forEach items="${data.commConfigDegreeLevelIdList}"
							var="commConfigDegreeLevelIdList">
							<logic:equal name="data"
								value="${commConfigDegreeLevelIdList.itemCode}"
								property="commConfigDegreeLevelId">
  							${commConfigDegreeLevelIdList.itemName}
  						</logic:equal>
						</c:forEach>
					</td>
					<td class="tblLable">
						<bean:message  bundle="hsp.hspLocale" key="hsp.jsp.common.studyMajor"/>：
					</td>
					<td class="hou">
						<c:forEach items="${data.commConfigProfessionIdList}"
							var="commConfigProfessionIdList">
							<logic:equal name="data"
								value="${commConfigProfessionIdList.itemCode}"
								property="commConfigProfessionId">
  							${commConfigProfessionIdList.itemName}
  						</logic:equal>
						</c:forEach>
					</td>
				</tr>
				<tr>
					<td class="tblLable">
						<bean:message  bundle="hsp.hspLocale" key="hsp.jsp.common.createUserName"/>：
					</td>
					<td class="hou">
						${data.createUserName}
					</td>
					<td class="tblLable">
					    <bean:message  bundle="hsp.hspLocale" key="hsp.jsp.common.createDate"/>：
					</td>
					<td class="hou">
					    ${data.createDate}
					</td>
				</tr>
				<tr>
        <td class="tblLable"   >
            <bean:message  bundle="comm.comIHE" key="comm.jsp.segment9"/>：
        </td>
        <td class="hou"  >
            <input type="hidden" name="logoutTime" value="<%=data.getLogoutTime() %>" />
            <%=data.getLogoutTime() %>
        </td>
        <td class="tblLable"   >
           <bean:message  bundle="comm.comIHE" key="comm.jsp.segment14"/>：
        </td>
        <td>
			<input type="text" class="kuandu" name="logoutReason" id="logoutReason" size="50" maxlength="50" value="<%=data.getLogoutReason()%>" onkeypress="eventOnKeyPress('btnSave')"/>
		</td>   
    </tr>
    <tr>
        <td class="tblLable"   >
            <bean:message  bundle="comm.comIHE" key="comm.jsp.segment15"/>：
        </td>
        <td class="hou"  >
             <input type="hidden" name="createUserId1" value="<%=data.getCreateUserId1() %>" />
            <%=data.getCreateUserId1() %>
        </td>
        <td class="tblLable"   >
           <bean:message  bundle="comm.comIHE" key="comm.jsp.segment16"/>：
        </td>
        <td class="hou"  >
            <input type="hidden" name="createUserName1" value="<%=data.getCreateUserName1() %>" />
        	<%=data.getCreateUserName1() %>
        </td>        
    </tr>
  		</table>
  			<!-- Sheet operation button area -->
  			<div class="btnSave" id="budiv">
	        	<input type="button" name="btnSaveForm" id="btnSave" value="<bean:message  bundle="hsp.hspLocale" key="hsp.jsp.hspstaffbaseinfoupdate.save1"/>" onclick="saveForm()" />
	        	<input type="button" name="btnBack" value="<bean:message  bundle="hsp.hspLocale" key="hsp.jsp.hspstaffbaseinfoupdate.back"/>" onclick="history.go(-1)" />
	     	</div>   
  	</form>
  </body>
</html>
