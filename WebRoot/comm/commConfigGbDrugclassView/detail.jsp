<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<jsp:useBean id="data" scope="request" class="com.tianjian.comm.struts.form.CommConfigGbDrugclassForm" />
<html>
<head>
<%if(request.getServerPort() == 80) {%>
  <base href="http://<%=request.getServerName()%><%=request.getContextPath()%>/">
  <%} else {%>
  <base href="http://<%=request.getServerName()%>:<%=request.getServerPort()%><%=request.getContextPath()%>/">
<%}%>
<title></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link rel="stylesheet" rev="stylesheet" href="comm/include/css/comm_detail.css" />
</head>

<body>
<form name="form" method="post">

<input type="hidden" name="verbId" value="detail">

 <table border="0" align="center" cellspacing="1" cellpadding="0" class="table">
 	<tr>
        <td class="biaoti1" colspan="4" nowrap >
           <bean:message key="comm.jsp.common.text52" bundle="conf.comm.comm"/>
        </td>
    </tr>
    <tr>
       
   <td class="qian"  nowrap >
            <bean:message key="comm.jsp.common.text10" bundle="conf.comm.comm"/>：
        </td>
        <td class="hou" nowrap >
            <%=data.getItemCode() %>
            </td>
             <td class="qian"  nowrap >
            <bean:message key="comm.jsp.common.text29" bundle="conf.comm.comm"/>：
        </td>
        <td class="hou" nowrap >
        	<%=data.getItemName() %>  
        </td>
  
    </tr>
    
    <tr>
        <td class="qian"  nowrap >
            <bean:message key="comm.jsp.common.text38" bundle="conf.comm.comm"/>：
        </td>
        <td class="hou" nowrap >            
            <%=data.getInputCode() %>  
        </td>
        <td class="qian"  nowrap >
            <bean:message key="comm.jsp.common.text48" bundle="conf.comm.comm"/>：
        </td>
        <td class="hou" nowrap >            
             <%=data.getSeqNo() %>      
              
        </td>
    </tr>
     <tr>
        <td class="qian"  nowrap >
            <bean:message key="comm.jsp.common.text25" bundle="conf.comm.comm"/>：
        </td>
        <td class="hou" nowrap >            
            <%=data.getLevelFlag() %>  
        </td>
        <td class="qian"  nowrap >
            <bean:message key="comm.jsp.common.text20" bundle="conf.comm.comm"/>：
        </td>
        <td class="hou" nowrap >            
             <%=data.getParentId() %>      
              
        </td>
    </tr>
    <tr>
        <td class="qian"  nowrap >
            <bean:message key="comm.jsp.common.text4" bundle="conf.comm.comm"/>：
        </td>
        <td class="hou" colspan="3">
            <%=data.getComments() %>
        </td>
      
        
    
       
    </tr>
   
  </table>


  <!-- Sheet operation button area -->
  <table width="100%"  align="center" border="0" cellpadding="0" cellspacing="0">
      <tr>
          <td height="30" align="center">
          
          	<input type="button" style="cursor:hand; background:url(include/images/tong.gif) no-repeat; border:0; width:50px; height:23px; font-size:12px;" name="btnBack" value="<bean:message key="comm.jsp.common.text19" bundle="conf.comm.comm"/>" onClick="history.go(-1);" />
           
          </td>
      </tr>
  </table>
</form>
</body>
</html>
