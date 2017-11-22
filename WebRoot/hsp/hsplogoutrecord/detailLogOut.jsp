<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
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
		 function QWPrint(){
			     if(document.all.PrintActiveX == undefined || document.all.PrintActiveX ==null){
					document.body.innerHTML="<object id=\"PrintActiveX\" style=\"display: none\" classid=\"clsid:3ede745c-4adb-42a6-ab25-5621edbdfd6b\" codebase=\"<%=request.getContextPath()%>/include/QWPrint.cab#version=1,3,8,2\" ></object>" + document.body.innerHTML;
				}
		        PrintActiveX.pageName = "A4"; //设置纸张类型
	            //设置页眉页脚
	            
				printed = true;
			    document.getElementById("budiv").style.display="none";
			     document.getElementById("creat").style.display="none";
			       document.getElementById("editDiv").style.display="none";
			    PrintActiveX.PrintView();
			    document.getElementById("budiv").style.display="block";
			     document.getElementById("creat").style.display="block";
			      document.getElementById("editDiv").style.display="block";
	   
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
						卫生机构注销详细
						<div id="editDiv"> <img src="include/images/print.gif" width="14" height="14" /><a href="javascript:QWPrint();"> 打印</a></div>
					</td>
					
				</tr>
				<tr>
					<td class="tblLable">
						机构ID：
					</td>
					<td class="hou">
						${dataForm.hspConfigBaseinfoId}
					</td>
					<td class="tblLable">
					</td>
					<td class="hou">
					</td>
				</tr>
				<tr>
					<td class="tblLable">
						<bean:message  bundle="hsp.hspLocale" key="hsp.jsp.common.itemCode"/>：
					</td>
					<td class="hou">
						${dataForm.itemCode}
					</td>
					<td class="tblLable">
						<bean:message  bundle="hsp.hspLocale" key="hsp.jsp.common.itemName"/>：
					</td>
					<td class="hou">
						${dataForm.itemName}
					</td>
				</tr>
				
				<tr>
					<td class="tblLable">
						<bean:message  bundle="hsp.hspLocale" key="hsp.jsp.common.createUserName"/>：
					</td>
					<td class="hou">
						${dataForm.createUserName}
					</td>
					<td class="tblLable">
					    <bean:message  bundle="hsp.hspLocale" key="hsp.jsp.common.createDate"/>：
					</td>
					<td class="hou">
					    ${dataForm.createDate}
					</td>
				</tr>
				<tr>
        <td class="tblLable"   >
            <bean:message  bundle="comm.comIHE" key="comm.jsp.segment9"/>：
        </td>
        <td class="hou"  >
            ${dataForm.logoutTime }
        </td>
        <td class="tblLable"   >
           <bean:message  bundle="comm.comIHE" key="comm.jsp.segment14"/>：
        </td>
        <td class="hou"  >
        	${dataForm.logoutReason}
        </td>        
    </tr>
    <tr>
        <td class="tblLable"   >
            <bean:message  bundle="comm.comIHE" key="comm.jsp.segment15"/>：
        </td>
        <td class="hou"  >
            ${dataForm.createUserId1}
        </td>
        <td class="tblLable"   >
           <bean:message  bundle="comm.comIHE" key="comm.jsp.segment16"/>：
        </td>
        <td class="hou"  >
        	${dataForm.createUserName1}
        </td>        
    </tr>
			</table>
			<div class="btnSave" name="budiv" id="budiv">
				<input type="button"  value="<bean:message  bundle="hsp.hspLocale" key="hsp.jsp.common.back1"/>" 	onclick="history.go(-1);" />
		       
			</div>
		</form>
	</body>
</html>
