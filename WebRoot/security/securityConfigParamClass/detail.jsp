<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<jsp:useBean id="data" scope="request" class="com.tianjian.security.struts.form.SecurityConfigParamClassForm" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<%if(request.getServerPort() == 80) {%>
  <base href="http://<%=request.getServerName()%><%=request.getContextPath()%>/">
  <%} else {%>
  <base href="http://<%=request.getServerName()%>:<%=request.getServerPort()%><%=request.getContextPath()%>/">
<%
	}
%>
<title><bean:message key="security.jsp.securityConfigParamClassM.detail.title" bundle="security"/></title>
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
        <td class="tblTitle" colspan="6">
          <span>※</span> <bean:message key="security.jsp.securityConfigParamClassM.detail.title" bundle="security"/><span>※</span>
        </td>
    </tr>
    <tr>
    	<td class="tblLable">
            程序包代码:
        </td>
        <td class="hou">
        	<%=data.getClassCode()%>  
        </td>
		<td class="tblLable">
            工程名称:
        </td>
        <td class="hou">
        	<%=data.getProjectName()%>  
        </td>   
    </tr>
    <tr>
    	<td class="tblLable">
            <bean:message key="security.jsp.securityConfigParamClass1.commom2" bundle="security"/>:
        </td>
        <td class="hou">
        	<%=data.getClassName()%>  
        </td>

		<td class="tblLable">
            <bean:message key="security.jsp.securityConfigParamClass1.commom3" bundle="security"/>:
        </td>
        <td class="hou">            
            <%=data.getInputCode()%>  
        </td>
    </tr>
	<tr>     
        <td class="tblLable">
            <bean:message key="security.jsp.securityConfigParamClass1.commom4" bundle="security"/>:
        </td>
        <td class="hou">            
             <%=data.getFunctionDescription()%>           
        </td>

         <td class="tblLable">
            <bean:message key="security.jsp.securityConfigParamClass1.commom5" bundle="security"/>:
        </td>
        <td class="hou">            
            <%=data.getComments()%>  
        </td>
    </tr>
  </table>
  <!-- Sheet operation button area -->
  <div class="btnSave" id="btnSave">
  	<input type="button" style="font-size:12px;font-family:Arial;" name="btnBack" value="<bean:message key="security.jsp.commom.button2" bundle="security"/>" onClick="history.go(-1)" />
  	<input type="button"  style="font-size:12px;font-family:Arial;" name="btn" value="打印" onclick="QWPrint();" />  
  </div>
</form>
</body>
</html>
