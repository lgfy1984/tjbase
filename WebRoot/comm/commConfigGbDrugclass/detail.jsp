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
<link type="text/css" rev="stylesheet" rel="stylesheet" href="include/css/form.css" />
<script type="text/javascript">
function qprint(){
    if(document.all.PrintActiveX == undefined || document.all.PrintActiveX ==null){
		document.body.innerHTML="<object id=\"PrintActiveX\" style=\"display: none\" classid=\"clsid:3ede745c-4adb-42a6-ab25-5621edbdfd6b\" codebase=\"<%=request.getContextPath()%>/include/QWPrint.cab#version=1,3,8,2\" ></object>"+document.body.innerHTML;
	}
	        PrintActiveX.pageName = "A4"; //设置纸张类型
       
			printed = true;
    
		    document.getElementById("buttonTable").style.display="none";
		     document.getElementById("editDiv").style.display="none";
		    PrintActiveX.PrintView();
		    document.getElementById("buttonTable").style.display="block";
		     document.getElementById("editDiv").style.display="block";
}

function goback(url){
	location.href=url;
}

//修改
function cmdEdit(id) {
    document.form.itemCodeHidden.value = id;  
    document.form.verbId.value = "updateInit";    
    document.form.submit();     
}

</script>
</head>

<body>
<form name="form" method="post">

<input type="hidden" name="verbId" value="detail">
<input type="hidden" name="itemCodeHidden" value="<%=data.getItemCodeHidden()%>">
 <table  width="100%" border="0" cellpadding="0" cellspacing="0" class="tblView" align="center">
 	<tr>
        <td class="tblTitle" colspan="4" >
           <bean:message key="comm.jsp.common.text52" bundle="conf.comm.comm"/>
           <div id="editDiv"><img src="include/images/114.gif" width="14" height="14" /><a href="javascript:cmdEdit('<%=data.getItemCode() %>')" > 修改</a><img src="include/images/printer.png" width="14" height="14" /><a href="javascript:qprint();"> 打印</a></div>
        </td>
    </tr>
    <tr>
       
   <td  class="tblLable">
            <bean:message key="comm.jsp.common.text10" bundle="conf.comm.comm"/>：
        </td>
        <td class="hou" nowrap >
            <%=data.getItemCode() %>
            </td>
             <td  class="tblLable">
            <bean:message key="comm.jsp.common.text29" bundle="conf.comm.comm"/>：
        </td>
        <td class="hou" nowrap >
        	<%=data.getItemName() %>  
        </td>
  
    </tr>
    
    <tr>
        <td  class="tblLable" >
            <bean:message key="comm.jsp.common.text38" bundle="conf.comm.comm"/>：
        </td>
        <td class="hou" nowrap >            
            <%=data.getInputCode() %>  
        </td>
        <td  class="tblLable">
            <bean:message key="comm.jsp.common.text48" bundle="conf.comm.comm"/>：
        </td>
        <td class="hou" nowrap >            
             <%=data.getSeqNo() %>      
              
        </td>
    </tr>
     <tr>
        <td  class="tblLable">
            <bean:message key="comm.jsp.common.text25" bundle="conf.comm.comm"/>：
        </td>
        <td class="hou" nowrap >            
            <%=data.getLevelFlag() %>  
        </td>
        <td  class="tblLable">
            <bean:message key="comm.jsp.common.text20" bundle="conf.comm.comm"/>：
        </td>
        <td class="hou" nowrap >            
             <%=data.getParentId() %>      
              
        </td>
    </tr>
    <tr>
        <td  class="tblLable">
            <bean:message key="comm.jsp.common.text4" bundle="conf.comm.comm"/>：
        </td>
        <td class="hou" colspan="3">
            <%=data.getComments() %>
        </td>
      
        
    
       
    </tr>
   
  </table>

    <div class="btnSave">
  <!-- Sheet operation button area -->
  <table width="100%" border="0" align="center" cellpadding="0" cellspacing="0" id="buttonTable">
      <tr>
          <td height="30" align="center">
          
          	<input type="button" style="cursor:hand; background:url(include/images/tong.gif) no-repeat; border:0; width:50px; height:23px; font-size:12px;" name="btnBack" value="<bean:message key="comm.jsp.common.text19" bundle="conf.comm.comm"/>" onClick="goback('<%=request.getContextPath()%>/comm/commConfigGbDrugclass.do?verbId=query')"  />
           
          </td>
      </tr>
  </table>
   </div>
</form>
</body>
</html>
