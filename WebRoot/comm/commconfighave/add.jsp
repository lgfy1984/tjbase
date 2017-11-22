<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<jsp:useBean id="commConfigHave" scope="request" class="com.tianjian.comm.struts.form.CommConfigHaveForm" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%if(request.getServerPort() == 80) {%>
<base href="http://<%=request.getServerName()%><%=request.getContextPath()%>/" />
<%} else {%>
<base href="http://<%=request.getServerName()%>:<%=request.getServerPort()%><%=request.getContextPath()%>/" />
<%}%>
<title><bean:message key="comm.jsp.common.text57" bundle="conf.comm.comm"/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta http-equiv="pragma" content="no-cache" />
<meta http-equiv="cache-control" content="no-cache" />
<meta http-equiv="expires" content="0" />
<script language="javascript" src="<bean:message key="comm.js.CommMessage.path" bundle="conf.comm.comm"/>"></script>
<script language="javascript" src="<bean:message key="includes.js.validator.path" bundle="security" />" defer="defer"></script>
<script language="javascript" src="include/javascript/TJMessage.js"></script>
<script language="javascript" src="include/javascript/eventOnKeyPress.js"></script>
		<script language="javascript">
function saveForm(){
	if(!Validator.Validate(document.forms.form,3)){
      return ;
   }
	if(isNaN(document.form.seqNo.value)){
		alert('<bean:message key="comm.jsp.common.text49" bundle="conf.comm.comm"/>'); 
		return true;
	}
	if(isNaN(document.form.itemCode.value)){
		alert('<bean:message key="comm.jsp.common.warn1" bundle="conf.comm.comm"/>'); 
		return true;
	}
	if(document.form.itemCode.value == ""){
		alert('<bean:message key="comm.jsp.common.text31" bundle="conf.comm.comm"/>');
	 	return ;
	}
	if(document.form.itemName.value == ""){
	 	alert('<bean:message key="comm.jsp.common.text32" bundle="conf.comm.comm"/>');
	 	return ;
	}
	if (confirmMessage("<bean:message key='comm.jsp.add.tianjia' bundle='comm.commLocale'/>")){     
	    document.form.verbId.value = "add";    
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
<body onload="showHspMessage('<%=commConfigHave.getMessage()%>')" >
<form name="form" method="post" action= "comm/commConfigHave.do" >
<input type="hidden" name="verbId" value="add" />
<table border="0" cellpadding="0" cellspacing="0" class="tblFill" align="center">
	<tr>
          <td class="tblTitle" colspan="4"><span>※</span>
          <bean:message key="comm.jsp.different.text41" bundle="conf.comm.comm"/>
          <span>※</span></td>
     </tr>
    <tr>
        <td class="tblLable">
         	<span>*</span> <bean:message key="comm.jsp.common.text10" bundle="conf.comm.comm"/>:
        </td>
        <td>
            <input type="text"  name="itemCode" size="20" maxlength="2"
            	onkeypress="eventOnKeyPress('itemName')"  max="32" dataType="LimitB" msg="代码输入过长"
              value="<%=commConfigHave.getItemCode() %>" />
        </td>  
        <td class="tblLable">
         	 <span>*</span> <bean:message key="comm.jsp.common.text29" bundle="conf.comm.comm"/>:
        </td>
        <td>
            <input type="text"  name="itemName" size="50" maxlength="50"
              onkeypress="eventOnKeyPress('seqNo')" max="40" dataType="LimitB" msg="名称输入过长"
              value="<%=commConfigHave.getItemName() %>" />
        </td>
    </tr>
    <tr>
        <td class="tblLable">
            <bean:message key="comm.jsp.common.text48" bundle="conf.comm.comm"/>:
        </td>
        <td>
            <input type="text"  name="seqNo" id="principal_phone" size="30" maxlength="20"
               onkeypress="eventOnKeyPress('comments')"  max="11" dataType="LimitB" msg="序号输入过长"
              value="<%=commConfigHave.getSeqNo() %>"  />
        </td>
   		<td class="tblLable">
            <bean:message key="comm.jsp.common.text4" bundle="conf.comm.comm"/>:
        </td>
        <td>
            <input type="text"  name="comments" id="principal" size="30" maxlength="50" onkeypress="eventOnKeyPress('btnSaveForm')" 
           	 max="40" dataType="LimitB" msg="备注输入过长"
             value="<%=commConfigHave.getComments() %>" />
        </td>
     </tr>
  </table>
  <!-- Sheet operation button area -->
  <div class="btnSave">
  		<input type="button"  name="btnSaveForm" value="<bean:message key="comm.jsp.common.text3" bundle="conf.comm.comm"/>" onclick="saveForm()" />
        <input type="button"  name="btnBack" value="<bean:message key="comm.jsp.common.text19" bundle="conf.comm.comm"/>" onclick="history.go(-1);" />  
  </div>
</form>
</body>
</html>
