<%@ page contentType="text/html; charset=UTF-8" %>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<jsp:useBean id="data" scope="request" class="com.tianjian.comm.struts.form.CommIHENameTypeForm" />
<html>
<head>
<%if(request.getServerPort() == 80) {%>
  <base href="http://<%=request.getServerName()%><%=request.getContextPath()%>/">
  <%} else {%>
  <base href="http://<%=request.getServerName()%>:<%=request.getServerPort()%><%=request.getContextPath()%>/">
<%}%>
<title><bean:message  bundle="comm.comIHE" key="comm.jsp.commIHENameType.detail"/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<link rel="stylesheet" rev="stylesheet" href="comm/include/css/comm_detail.css" />
</head>

<body>
<form name="form" method="post">

<input type="hidden" name="verbId" value="detail">

 <table border="0" cellspacing="1" cellpadding="0" class="table">
 	<tr>
        <td class="biaoti" colspan="4" nowrap >
           <bean:message  bundle="comm.comIHE" key="comm.jsp.commIHENameType.detail"/>
        </td>
    </tr>
    <tr>
       
   <td class="qian"  nowrap >
           <bean:message  bundle="comm.comIHE" key="comm.jsp.commIHENameType.segment2"/>:
        </td>
        <td class="hou" nowrap >
            <%=data.getNameTypeCode() %>
             <td class="qian"  nowrap >
            <bean:message  bundle="comm.comIHE" key="comm.jsp.commIHENameType.segment3"/>:
        </td>
        <td class="hou" nowrap >
        	<%=data.getNameTypeName() %>  
        </td>
  
    </tr>
    
    <tr>
     <td class="qian"  nowrap >
           <bean:message  bundle="comm.comIHE" key="comm.jsp.commIHENameType.segment1"/>:
        </td>
        <td class="hou" nowrap >            
             <%=data.getSeqNo() %>      
              
        </td>
        <td class="qian"  nowrap >
           <bean:message  bundle="comm.comIHE" key="comm.jsp.commIHENameType.segment4"/>:
        </td>
        <td class="hou" nowrap >            
            <%=data.getInputCode() %>  
        </td>
    </tr>
    <tr>
        <td class="qian"  nowrap >
            <bean:message  bundle="comm.comIHE" key="comm.jsp.commIHENameType.segment5"/>:
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
          
          	<input type="button"  name="btnBack" value="<bean:message  bundle="comm.commLocale" key="comm.jsp.commom.back"/>" onClick="history.go(-1);" />
           
          </td>
      </tr>
  </table>
</form>
</body>
</html>
