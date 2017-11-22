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
<title><bean:message key="comm.jsp.different.text55" bundle="conf.comm.comm"/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<script language="javascript" src="<bean:message key="comm.js.CommMessage.path" bundle="conf.comm.comm"/>"></script>
<script language="javascript" src="include/javascript/eventOnKeyPress.js"></script>
<script language="javascript">
function saveForm(){
	if(checkspace(document.form.itemCode.value)){
	 	alertCommMessage("<bean:message key="comm.jsp.common.text31" bundle="conf.comm.comm"/>");
	 	return ;
	}
	if(checkspace(document.form.itemName.value)){
	 	alertCommMessage("<bean:message key="comm.jsp.common.text32" bundle="conf.comm.comm"/>");
	 	return ;
	}
	
	if(!(document.form.seqNo.value=="")){
	 	if(isNUM(document.form.seqNo.value)==null){
	 	alertCommMessage("<bean:message key="comm.jsp.different.text58" bundle="conf.comm.comm"/>");
	 	return ;}
	 	
	}
	document.form.verbId.value = "update";
if(confirm("确定要修改吗？")){
	document.form.submit();
	}
}
function checkspace(checkstr) {
		var str = '';
		for(i = 0; i < checkstr.length; i++) {
		str = str + ' ';
		}
		return (str == checkstr);
		}
function isNUM(val) {
    var rexPatn = /^[0-9]{0,11}?$/;
    var res = rexPatn.exec(val); 
    return res;
}

function goback(url){
	location.href=url;
}
</script>

<link type="text/css" rev="stylesheet" rel="stylesheet" href="include/css/form.css" />
</head>

<body>
<form name="form" method="post" action="<%=request.getContextPath()%>/comm/commConfigGbDrugclass.do" >

<input type="hidden" name="verbId" value="add" />

 <table align="center" border="0" cellpadding="0" cellspacing="0" class="tblFill">
    <tr>
        <td class="tblTitle" colspan="4">
            <span>※</span><bean:message key="comm.jsp.different.text56" bundle="conf.comm.comm"/><span>※</span>
        </td>
    </tr>
    <tr>
        <td class="tblLable" >
        		<span>*</span><bean:message key="comm.jsp.common.text10" bundle="conf.comm.comm"/>： 
        </td>
        
        <td >
        	<%=data.getItemCode() %>
        	<input type="hidden" name="itemCode" value="<%=data.getItemCode() %>" />
         </td>
         <td class="tblLable" >
         	 <span>*</span><bean:message key="comm.jsp.common.text29" bundle="conf.comm.comm"/>： 
        </td>
        <td >
            <input type="text" style="height:25px"  name="itemName" maxlength="50" onkeypress="eventOnKeyPress('seqNo')" value="<%=data.getItemName() %>" />
        </td>
    </tr>
    <tr>
    	<td class="tblLable" >
          <bean:message key="comm.jsp.common.text48" bundle="conf.comm.comm"/>：
        </td>
        <td>
         <input type="text" style="height:25px"   name="seqNo" id="seqNo" max="11" dataType="LimitB" msg="序号输入过长" value="<%=data.getSeqNo() %>" />   
        </td>
        <td class="tblLable" >
            <bean:message key="comm.jsp.common.text4" bundle="conf.comm.comm"/>：
        </td>
        <td>
            <input type="text" style="height:25px"  name="comments" id="comments"   max="40" dataType="LimitB" msg="备注输入过长"  value="<%=data.getComments() %>" />
        </td>
        
     	
    </tr>
  </table>


  <!-- Sheet operation button area -->
 <div class="btnSave">
          <input type="button" class="btnSave" name="btnSaveForm" value="<bean:message key="comm.jsp.common.text3" bundle="conf.comm.comm"/>" onClick="saveForm()" />
          <input type="button" class="btnSave" name="btnBack" value="<bean:message key="comm.jsp.common.text19" bundle="conf.comm.comm"/>" onClick="goback('<%=request.getContextPath()%>/comm/commConfigGbDrugclass.do?verbId=query')" />
</div>
</form>

</body>
</html>
