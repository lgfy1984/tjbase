<%@ page language="java"  pageEncoding="utf-8"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<jsp:useBean id="data" scope="request" class="com.tianjian.security.struts.form.SecurityDataObjectVsRoleForm"/>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
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
    
    <title><bean:message key="security.jsp.securityDataObjectVsRolea.detail.title" bundle="security"/></title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
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
  <table width="100%" border="0" cellpadding="0" cellspacing="0" class="tblView" align="center">
 	<tr>
        <td class="tblTitle" colspan="4">
          <bean:message key="security.jsp.securityDataObjectVsRolea.detail.title" bundle="security"/>
        </td>
    </tr>
    <tr>
    	<td class="tblLable">
    		<bean:message key="security.jsp.commom.securityStaffBaseInfo" bundle="security"/>：
    	</td>
    	<td class="hou">
    		<%=data.getSecurityStaffBaseInfoName() %>
    	</td>
    	<td class="tblLable">
    		<bean:message key="security.jsp.commom.sdtoId" bundle="security"/>：
    	</td>
    	<td class="hou">
    		<%=data.getSdoIdName() %>
    	</td>
    </tr>
    <tr>
    	<td class="tblLable">
    		<bean:message key="security.jsp.commom.sdtoValue" bundle="security"/> ：
    	</td>
    	<td class="hou" colspan="3">
    		<%=data.getSdoValueName() %>
    	</td>
    </tr>
  </table>
  <div class="btnSave" id="btnSave">
  	<input type="button"  name="btnBack" value="<bean:message key="security.jsp.commom.button2" bundle="security"/>" onClick="history.go(-1);" />
  	<input type="button"  style="font-size:12px;font-family:Arial;" name="btn" value="打印" onclick="QWPrint();" />  
  </div>
  </body>
</html>
