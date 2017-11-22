<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="html" uri="/WEB-INF/struts-html.tld"%>
<%@ taglib prefix="bean" uri="/WEB-INF/struts-bean.tld"%>
<%@ taglib prefix="logic" uri="/WEB-INF/struts-logic.tld"%>
<jsp:useBean id="data" scope="request" class="com.tianjian.comm.struts.form.CommConfigPopulationNatureForm" />
<html>
<head>
<%if(request.getServerPort() == 80) {%>
  <base href="http://<%=request.getServerName()%><%=request.getContextPath()%>/">
  <%} else {%>
  <base href="http://<%=request.getServerName()%>:<%=request.getServerPort()%><%=request.getContextPath()%>/">
<%}%>
<title><bean:message key="comm.jsp.common.nature" bundle="conf.comm.Comm"/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link rel="stylesheet" rev="stylesheet" href="comm/include/css/comm_detail.css" />
</head>
<script language="javascript">
	function goback(){
		document.form.verbId.value="query";
		document.form.submit();
	}
</script>
<body>
<form name="form" method="post" action="comm/commPopulationNature.do">

<input type="hidden" name="verbId" value="">
<input type="hidden" name="itemCode" value="<%=data.getItemCodeHidden() %>">
<input type="hidden" name="itemName" value="<%=data.getItemNameHidden() %>">
<input type="hidden" name="inputCode" value="<%=data.getInputCodeHidden() %>">
<input type="hidden" name="page" value="<%=data.getMypage() %>">
 <table border="0" cellspacing="1" cellpadding="0" class="table">
 	
 	<tr>
        <td class="biaoti" colspan="4" nowrap >
           <bean:message key="comm.jsp.common.personMessage" bundle="conf.comm.Comm"/>
        </td>
    </tr>
    <tr>
       
   		<td class="qian"  nowrap >
            <bean:message key="comm.jsp.common.item" bundle="conf.comm.Comm"/>：
        </td>
        <td class="hou" nowrap >
            <%=data.getItemCode() %>
             
        </td>
        <td class="qian" nowrap>
      	 <bean:message key="comm.jsp.common.names" bundle="conf.comm.Comm"/>：
        </td>
        <td class="hou" nowrap >
        	<%=data.getItemName() %>  
        </td>
  
    </tr>
    
    <tr>
     <td class="qian"  nowrap >
            <bean:message key="comm.jsp.common.seqNo" bundle="conf.comm.Comm"/>：
        </td>
        <td class="hou" nowrap >            
             <%=data.getSeqNo() %>      
              
        </td>
        <td class="qian"  nowrap >
            <bean:message key="comm.jsp.common.inputItemCode" bundle="conf.comm.Comm"/>：
        </td>
        <td class="hou" nowrap >            
            <%=data.getInputCode() %>  
        </td>
    </tr>
    <tr>
        <td class="qian"  nowrap >
            <bean:message key="comm.jsp.common.comments" bundle="conf.comm.Comm"/>：
        </td>
        <td class="hou" nowrap >
            <%=data.getComments() %>
        </td>
        <td class="qian"  nowrap >
           
        </td>
        <td class="hou" nowrap >
        
        </td>  

    
       
    </tr>
   
  </table>


  <!-- Sheet operation button area -->
  <table width="100%" border="0" cellpadding="0" cellspacing="0">
      <tr>
          <td height="30" align="center">
          
          	<input type="button"
          	 name="btnBack" value="<bean:message key="comm.jsp.common.return" bundle="conf.comm.Comm"/>" onClick="goback()" />
           
          </td>
      </tr>
  </table>
</form>
</body>
</html>
