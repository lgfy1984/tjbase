<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="html" uri="/WEB-INF/struts-html.tld"%>
<%@ taglib prefix="bean" uri="/WEB-INF/struts-bean.tld"%>
<%@ taglib prefix="logic" uri="/WEB-INF/struts-logic.tld"%>
<jsp:useBean id="commConfigNormal" scope="request" class="com.tianjian.comm.struts.form.CommConfigNormalForm" />
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
<input type="hidden" name="verbId" value="detail" />
<table width="100%" border="0" cellpadding="0" cellspacing="0" class="tblView" align="center">
 	<tr>
        <td class="tblTitle" colspan="4">
           <bean:message key="comm.jsp.common.zycMessage" bundle="conf.comm.Comm"/>
        </td>
    </tr>
    <tr>
   		<td class="tblLable">
            <bean:message key="comm.jsp.common.item" bundle="conf.comm.Comm"/>:
        </td>
        <td class="hou"><%=commConfigNormal.getItemCode() %></td>
        <td class="tblLable">
            <bean:message key="comm.jsp.common.names" bundle="conf.comm.Comm"/>:
        </td>
        <td class="hou">
        	<%=commConfigNormal.getItemName() %>  
        </td>
  
    </tr>
    
    <tr>
     	<td class="tblLable">
            <bean:message key="comm.jsp.common.seqNo" bundle="conf.comm.Comm"/>:
        </td>
        <td class="hou">            
             <%=commConfigNormal.getSeqNo() %>      
        </td>
        <td class="tblLable">
            <bean:message key="comm.jsp.common.inputItemCode" bundle="conf.comm.Comm"/>:
        </td>
        <td class="hou">            
            <%=commConfigNormal.getInputCode() %>  
        </td>
    </tr>
    <tr>
        <td class="tblLable">
            <bean:message key="comm.jsp.common.comments" bundle="conf.comm.Comm"/>:
        </td>
        <td class="hou" colspan="3">
            <%=commConfigNormal.getComments() %>
        </td>     
    </tr>
 </table>
  <!-- Sheet operation button area -->
  <div class="btnSave" id="btnSave">
  		 <input type="button"  name="btnBack" value="<bean:message key="comm.jsp.common.return" bundle="conf.comm.Comm"/>" onclick="history.go(-1);" />
  		 <input type="button"  style="font-size:12px;font-family:Arial;" name="btn" value="打印" onclick="QWPrint();" />             
  </div>
</form>
</body>
</html>
