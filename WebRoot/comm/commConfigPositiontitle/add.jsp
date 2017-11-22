<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="html" uri="/WEB-INF/struts-html.tld"%>
<%@ taglib prefix="bean" uri="/WEB-INF/struts-bean.tld"%>
<%@ taglib prefix="logic" uri="/WEB-INF/struts-logic.tld"%>
<jsp:useBean id="commConfigPositiontitle" scope="request" class="com.tianjian.comm.struts.form.CommConfigPositiontitleForm" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%if(request.getServerPort() == 80) {%>
<base href="http://<%=request.getServerName()%><%=request.getContextPath()%>/" />
<%} else {%>
<base href="http://<%=request.getServerName()%>:<%=request.getServerPort()%><%=request.getContextPath()%>/" />
<%}%>
<title><bean:message key="comm.jsp.common.xzzw" bundle="conf.comm.Comm"/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta http-equiv="pragma" content="no-cache" />
<meta http-equiv="cache-control" content="no-cache" />
<meta http-equiv="expires" content="0" />
<script language="javascript" src="<bean:message key="comm.js.comm.message" bundle="conf.comm.Comm"/>"></script>
<script language="javascript" src="<bean:message key="includes.js.validator.path" bundle="security" />" defer="defer"></script>
<script language="javascript" src="include/javascript/eventOnKeyPress.js"></script>
<script language="javascript">
function saveForm(){
    if(!Validator.Validate(document.forms.form,3)){
      return ;
    }
	if(document.form.itemCode.value == ""){
	 	alertCommMessage("0-000003");
	 	return ;
	}
	if(document.form.itemName.value == ""){
	 	alertCommMessage("0-000004");
	 	return ;
	}

	document.form.verbId.value = "add";
	document.form.submit();
}

</script>
<link type="text/css" rev="stylesheet" rel="stylesheet" href="include/css/form.css" />
</head>

<body onload="showCommMessage('','<%=commConfigPositiontitle.getMessage() %>','1')" >
<form name="form" method="post" action= "comm/commConfigPositiontitle.do" >

<input type="hidden" name="verbId" value="add" />

 <table  align="center" border="0" cellpadding="0" cellspacing="0" class="tblFill" border="0" cellpadding="0" cellspacing="0" class="tblFill">
   		 <tr>
              <td class="tblTitle" colspan="4"><span>※</span> <bean:message key="comm.jsp.common.addXzzw" bundle="conf.comm.Comm"/> <span>※</span></td>
          </tr>
    <tr>
        <td class="tblLable">
            <bean:message key="comm.jsp.common.seqNo" bundle="conf.comm.Comm"/>：
        </td>
        <td>
            <input type="text" name="seqNo" id="seqNo" size="30" maxlength="20"
               onkeypress="eventOnKeyPress('comments')" max="11" dataType="LimitB" msg="序号输入过长"
              value="<%=commConfigPositiontitle.getSeqNo() %>" />
        </td>
        <td class="tblLable">
         	<span>*</span> <bean:message key="comm.jsp.common.item" bundle="conf.comm.Comm"/>：
        </td>
        <td>
            <input type="text" name="itemCode" size="20" maxlength="9"
            	onkeypress="eventOnKeyPress('itemName')"  max="8" dataType="LimitB" msg="代码输入过长"
              value="<%=commConfigPositiontitle.getItemCode() %>" />
        </td>  
    
    </tr>
    <tr>
        <td class="tblLable">
          <span>*</span> <bean:message key="comm.jsp.common.names" bundle="conf.comm.Comm"/>：
        </td>
        <td>
            <input type="text" name="itemName" size="50" maxlength="50"
              	onkeypress="eventOnKeyPress('seqNo')" max="32" dataType="LimitB" msg="名称输入过长"
              	value="<%=commConfigPositiontitle.getItemName() %>" />
        </td>
        <td class="tblLable">
            <bean:message key="comm.jsp.common.comments" bundle="conf.comm.Comm"/>：
        </td>
        <td>
            <input type="text" name="comments" id="comments" size="30" maxlength="50" max="32" dataType="LimitB" msg="备注输入过长"
          		 value="<%=commConfigPositiontitle.getComments() %>" />
        </td>
    </tr>
  </table>


  <!-- Sheet operation button area -->
  	 <div class="btnSave">
		<input type="button"  name="btnSaveForm" value="<bean:message key="comm.jsp.common.save" bundle="conf.comm.Comm"/>" onclick="saveForm()" />
        <input type="button"  name="btnBack" value="<bean:message key="comm.jsp.common.return" bundle="conf.comm.Comm"/>" onclick="history.go(-1);" />         
	 </div>
</form>
</body>
</html>
