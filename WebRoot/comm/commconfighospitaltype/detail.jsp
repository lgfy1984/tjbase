<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<jsp:useBean id="data" scope="request" class="com.tianjian.comm.struts.form.CommConfigHospitalTypeForm" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%if(request.getServerPort() == 80) {%>
  <base href="http://<%=request.getServerName()%><%=request.getContextPath()%>/" />
  <%} else {%>
  <base href="http://<%=request.getServerName()%>:<%=request.getServerPort()%><%=request.getContextPath()%>/" />
<%}%>
<title></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta http-equiv="pragma" content="no-cache" />
<meta http-equiv="cache-control" content="no-cache" />
<meta http-equiv="expires" content="0" />
<link type="text/css" rev="stylesheet" rel="stylesheet" href="include/css/form.css" />
</head>

<body>
<form name="form" method="post">

<input type="hidden" name="verbId" value="detail" />
<table width="100%" border="0" cellpadding="0" cellspacing="0" class="tblView" align="center">
 	<tr>
        <td class="tblTitle" colspan="4">
          <bean:message key="comm.jsp.different.text45" bundle="conf.comm.comm"/>
        </td>
    </tr>
    <tr>
       	<td class="tblLable">
            <bean:message key="comm.jsp.common.text10" bundle="conf.comm.comm"/>:
        </td>
        <td class="hou">
            <%=data.getItemCode() %></td>
        <td class="tblLable">
            <bean:message key="comm.jsp.common.text29" bundle="conf.comm.comm"/>:
        </td>
        <td class="hou">
        	<%=data.getItemName() %>  
        </td>
  	</tr>
    <tr>
       	<td class="tblLable">
            <bean:message key="comm.jsp.different.text30" bundle="conf.comm.comm"/>:
        </td>
        <td class="hou">
            <%=data.getParentItemName() %></td>
        <td class="tblLable">
            <bean:message key="comm.jsp.different.text11" bundle="conf.comm.comm"/>:
        </td>
        <td class="hou">
        	<%=data.getLevelFlag() %>  
        </td>
  	</tr>
    <tr>
     <td class="tblLable">
            <bean:message key="comm.jsp.common.text48" bundle="conf.comm.comm"/>:
        </td>
        <td class="hou">            
             <%=data.getSeqNo() %>      
              
        </td>
        <td class="tblLable">
            <bean:message key="comm.jsp.common.text38" bundle="conf.comm.comm"/>:
        </td>
        <td class="hou">            
            <%=data.getInputCode() %>  
        </td>
    </tr>
    <tr>
        <td class="tblLable">
            <bean:message key="comm.jsp.common.text4" bundle="conf.comm.comm"/>:
        </td>
        <td class="hou" colspan="3">
            <%=data.getComments() %>      
        </td>      
    </tr>  
  </table>
  <!-- Sheet operation button area -->
 	 <div class="btnSave">
		<input type="button" style="font-size:12px;font-family:Arial;"  name="btnBack" value="<bean:message key="comm.jsp.common.text19" bundle="conf.comm.comm"/>" onclick="history.go(-1);" />
	</div>
</form>
</body>
</html>
