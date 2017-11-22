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
<title><bean:message key="Comm.jsp.commom.ptpDictInfo" bundle="conf.comm.CommMessage"/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta http-equiv="pragma" content="no-cache" />
<meta http-equiv="cache-control" content="no-cache" />
<meta http-equiv="expires" content="0" />
<script language="javascript" src="<bean:message  key="Comm.js.TJMessagepath"  bundle="conf.comm.CommMessage"/>"></script>
<script language="javascript" src="include/javascript/eventOnKeyPress.js"></script>
<script type="text/javascript" src="<bean:message  key="Comm.js.validatepath"  bundle="conf.comm.CommMessage"/>"></script>
<script language="javascript">
function selectInputValue(self){				
	document.form(self).select();
}
function huiche(){
	if(event.keyCode==13){
		event.keyCode=9
	}
}
function saveForm(){
	if(document.form.itemCode.value == ""){
	 	alertCommMessage("0-000001");
	 	return ;
	}
	if(document.form.itemName.value == ""){
	 	alertCommMessage("0-000002");
	 	return ;
	}
	 if (confirmMessage("0-000005")){
	document.form.verbId.value = "update";
	document.form.submit();
	}
}

</script>
<link rel="stylesheet" rev="stylesheet" href="comm/include/css/comm_add.css" />
</head>

<body onload="showMessage('','<%=commGbCoptp.getMessage() %>','')" >
<form name="form" method="post" action= "comm/commGbCoptp.do" >
<input type="hidden" name="verbId" value="update" />
<input type="hidden" name="itemCodeHidden" value="<%=commGbCoptp.getItemCodeHidden() %>" />
 <table border="0" cellspacing="1" cellpadding="0" class="table">
    <tr>
              <td height="30px" class="biaoti" colspan="4"><font color="red">※</font>&nbsp;&nbsp;修改专业技术职务字典&nbsp;&nbsp;<font color="red">※</font></td>
              
          </tr>
    <tr>
        <td class="qian"><bean:message key="jsp.seqNo" bundle="conf.Init"/>：
        </td>
        <td class="hou">
            <input type="text" id="postalcode" maxlength="6" onkeypress="eventOnKeyPress('itemCode')"
                class="kuandu" name="seqNo" size="20" value="<%=commGbCoptp.getSeqNo() %>" />
        </td>
        <td class="qian">
           <bean:message key="Comm.jsp.commom.code" bundle="conf.comm.CommMessage"/>：
        </td>
        <td class="hou">
            <input type="text" class="kuandu" name="itemCode" size="20" maxlength="9"
            	onkeypress="eventOnKeyPress('itemName')" readonly onkeydown="huiche()"
              value="<%=commGbCoptp.getItemCode() %>" />
        </td>  
     
    </tr> 
    <tr>
        <td class="qian">
          <font color="red">*</font> <bean:message key="Comm.jsp.commom.name" bundle="conf.comm.CommMessage"/>：
        </td>
        <td class="hou">
            <input type="text" class="kuandu" name="itemName" size="50" maxlength="50"
              onkeypress="eventOnKeyPress('parentItemCode')" 
              value="<%=commGbCoptp.getItemName() %>" />
        </td>
        <td class="qian">
         <font color="red">*</font> <bean:message key="Comm.jsp.commom.belongType" bundle="conf.comm.CommMessage"/>：
        </td>
        <td class="hou">
				<select name="parentItemCode" onkeypress="eventOnKeyPress('comments')">
				<%
				if (commGbCoptp.getParentItemNames() != null && commGbCoptp.getParentItemNames().length > 0) {
					for (int i = 0; i < commGbCoptp.getParentItemNames().length; i++) {
						String tempId = commGbCoptp.getParentItemCodes()[i];
						String tempName = commGbCoptp.getParentItemNames()[i];
				%>
					<option value="<%=tempId%>"
							<%=tempId.equals(commGbCoptp.getParentItemCode()) ? "selected"
								: ""%>>
							<%=tempName%>
					</option>
				<%
					}
				}
				%>
				</select>
        </td>  
    </tr>
    <tr>
        <td class="qian"> <bean:message key="jsp.comments" bundle="conf.Init"/>：
        </td>
        <td class="hou2" colspan="3">
            <input type="text" class="kuandu" name="comments" id="comments" size="50" maxlength="40"
              value="<%=commGbCoptp.getComments() %>" onkeypress="eventOnKeyPress('btnSaveForm')"/>
        </td>
    </tr>
  </table>


  <!-- Sheet operation button area -->
  <table width="100%" border="0" cellpadding="0" cellspacing="0">
      <tr>
          <td height="30" align="center">
          <input type="button" style="cursor:hand; background:url(include/images/tong.gif) no-repeat; border:0; width:50px; height:23px; font-size:12px;" name="btnSaveForm" value="<bean:message key="jsp.btnSaveForm" bundle="conf.Init"/>" onclick="saveForm()" />
          <input type="button" style="cursor:hand; background:url(include/images/tong.gif) no-repeat; border:0; width:50px; height:23px; font-size:12px;" name="btnBack" value="<bean:message key="jsp.btnBack" bundle="conf.Init"/>" onclick="history.go(-1);" />         
          </td>
      </tr>
  </table>
</form>

</body>
</html>
