<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="html" uri="/WEB-INF/struts-html.tld"%>
<%@ taglib prefix="bean" uri="/WEB-INF/struts-bean.tld"%>
<%@ taglib prefix="logic" uri="/WEB-INF/struts-logic.tld"%>
<jsp:useBean id="commGbCoptp" scope="request" class="com.tianjian.comm.struts.form.CommGbCoptpForm" />
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
<link rel="stylesheet" rev="stylesheet" href="comm/include/css/comm_detail.css" />
</head>

<body>
<form name="form" method="post">

<input type="hidden" name="verbId" value="detail" />

 <table border="0" cellspacing="1" cellpadding="0" class="table">
 	<tr>
        <td class="biaoti" colspan="4">
         <bean:message key="Comm.jsp.commom.ptpDictDetailInfo" bundle="conf.comm.CommMessage"/>
        </td>
    </tr>
    <tr>
       
   <td class="qian">
          <bean:message key="Comm.jsp.commom.code" bundle="conf.comm.CommMessage"/>：
        </td>
        <td class="hou">
            <%=commGbCoptp.getItemCode() %></td>
             <td class="qian">
          <bean:message key="Comm.jsp.commom.name" bundle="conf.comm.CommMessage"/>：
        </td>
        <td class="hou">
        	<%=commGbCoptp.getItemName() %>  
        </td>
  
    </tr>
    
    <tr>
     <td class="qian">
          <bean:message key="jsp.seqNo" bundle="conf.Init"/>：
        </td>
        <td class="hou">            
             <%=commGbCoptp.getSeqNo() %>      
              
        </td>
        <td class="qian">
            <bean:message key="jsp.inputNo" bundle="conf.Init"/>：
        </td>
        <td class="hou">            
            <%=commGbCoptp.getInputCode() %>  
        </td>
    </tr>
    <tr>
     <td class="qian">
            <bean:message key="Comm.jsp.commom.level" bundle="conf.comm.CommMessage"/>：
        </td>
        <td class="hou">            
             <%=commGbCoptp.getLevelFlag() %>      
              
        </td>
        <td class="qian">
            <bean:message key="Comm.jsp.commom.belongType" bundle="conf.comm.CommMessage"/>：
        </td>
        <td class="hou">            
            <%=commGbCoptp.getParentItemCodeName() %>  
        </td>
    </tr>    
    <tr>
        <td class="qian">
            <bean:message key="jsp.comments" bundle="conf.Init"/>：
        </td>
        <td class="hou2" colspan="3">
            <%=commGbCoptp.getComments() %>      
        </td>      
    </tr>  
  </table>
  <!-- Sheet operation button area -->
  <table width="100%" border="0" cellpadding="0" cellspacing="0">
      <tr>
          <td height="30" align="center">
          
          	<input type="button" style="cursor:hand; background:url(include/images/tong.gif) no-repeat; border:0; width:50px; height:23px; font-size:12px;" name="btnBack" value="<bean:message key="jsp.btnBack" bundle="conf.Init"/>" onclick="history.go(-1);" />
           
          </td>
      </tr>
  </table>
</form>
</body>
</html>
