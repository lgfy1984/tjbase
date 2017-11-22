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
<title><bean:message key="comm.jsp.common.alterZycMessage" bundle="conf.comm.Comm"/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta http-equiv="pragma" content="no-cache" />
<meta http-equiv="cache-control" content="no-cache" />
<meta http-equiv="expires" content="0" />
<script language="javascript" src="<bean:message key="comm.js.comm.message" bundle="conf.comm.Comm"/>"></script>
<script language="javascript" src="<bean:message  key="Comm.js.TJMessagepath"  bundle="conf.comm.CommMessage"/>"></script>
<script language="javascript" src="include/javascript/TJMessage.js"></script>
<script language="javascript" src="include/javascript/eventOnKeyPress.js"></script>
<script language="javascript">
function saveForm(){
	if(isNaN(document.form.seqNo.value)){
		alert('<bean:message key="comm.jsp.common.alert1" bundle="conf.comm.Comm"/>'); 
		return true;
	}
	if(document.form.itemCode.value == ""){
	 	alert('<bean:message key="comm.jsp.common.notnull" bundle="conf.comm.Comm"/>');
	 	return ;
	}
	if(document.form.itemName.value == ""){
	 	alert('<bean:message key="comm.jsp.common.unname" bundle="conf.comm.Comm"/>');
	 	return ;
	}
	//修改
	if (confirmMessage("<bean:message key='comm.jsp.update.gengxin' bundle='comm.commLocale'/>")){     
    	document.form.verbId.value = "update";    
    	document.form.submit(); 
    }   
}
function showHspMessage(message){
				if(message != ''&& message != null){
					alert(message);
					return;
				}
			}
</script>
<link type="text/css" rev="stylesheet" rel="stylesheet" href="include/css/form.css" />
</head>

<body onload="showHspMessage('<%=commConfigNormal.getMessage()%>')" >
<form name="form" method="post" action= "comm/commConfigNormal.do" >

<input type="hidden" name="verbId" value="update" />
 <table  border="0" cellpadding="0" cellspacing="0" class="tblFill" border="0" cellpadding="0" cellspacing="0" class="tblFill">
    <tr>
      	<td class="tblTitle" colspan="4"><span>※</span><bean:message key="comm.jsp.common.alterZyc" bundle="conf.comm.Comm"/><span>※</span></td>
    </tr>
    <tr>
        <td class="tblLable">
           <span>*</span> <bean:message key="comm.jsp.common.item" bundle="conf.comm.Comm"/>:
        </td>
        <td>
            <input type="text" name="itemCode" size="20" maxlength="9"
            	onkeypress="eventOnKeyPress('itemName')"
              value="<%=commConfigNormal.getItemCode() %>" readonly />
        </td>  
        <td class="tblLable">
            <span>*</span> <bean:message key="comm.jsp.common.names" bundle="conf.comm.Comm"/>:
        </td>
        <td>
            <input type="text" name="itemName" size="50" maxlength="50"
            onkeypress="eventOnKeyPress('seqNo')"
              value="<%=commConfigNormal.getItemName() %>" />
        </td>
    </tr>
  
    <tr>
        <td class="tblLable">
            <bean:message key="comm.jsp.common.seqNo" bundle="conf.comm.Comm"/>:
        </td>
        <td>
            <input type="text" name="seqNo" id="seqNo" size="20" maxlength="6"
              onkeypress="eventOnKeyPress('comments')"
                value="<%=commConfigNormal.getSeqNo() %>" readonly />
        </td>  
      <td class="tblLable">
      <bean:message key="comm.jsp.common.comments" bundle="conf.comm.Comm"/>:
  </td>
  <td>
      <input type="text" name="comments" id="comments" size="50" maxlength="50"
    onkeypress="eventOnKeyPress('btnSaveForm')"
        value="<%=commConfigNormal.getComments() %>" />
        </td>
    </tr>
     
  </table>


  <!-- Sheet operation button area -->
  <div class="btnSave">
  		<input type="button" name="btnSaveForm" value="<bean:message key="comm.jsp.common.save" bundle="conf.comm.Comm"/>" onclick="saveForm()" />
        <input type="button" name="btnBack"  value="<bean:message key="comm.jsp.common.return" bundle="conf.comm.Comm"/>" onclick="history.go(-1);" />
  </div>
</form>

</body>
</html>
