<%@ page contentType="text/html; charset=UTF-8" %>
<%@taglib prefix="bean" uri="/WEB-INF/struts-bean.tld"%>
<jsp:useBean id="commConfigTrue" scope="request" class="com.tianjian.comm.struts.form.CommConfigTrueForm" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%if(request.getServerPort() == 80) {%>
  <base href="http://<%=request.getServerName()%><%=request.getContextPath()%>/" />
  <%} else {%>
  <base href="http://<%=request.getServerName()%>:<%=request.getServerPort()%><%=request.getContextPath()%>/" />
<%}%>
<title><bean:message key="comm.jsp.commconfigtrue.add.title" bundle="conf.comm.CommMessageguoh"/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<meta http-equiv="pragma" content="no-cache" />
<meta http-equiv="cache-control" content="no-cache" />
<meta http-equiv="expires" content="0" />
<script language="javascript" src="include/javascript/TJMessage.js"></script>
<script language="javascript" src='<bean:message key="comm.js.commMessageguoh" bundle="conf.comm.CommMessageguoh"/>'></script>
<script language="javascript" src="<bean:message key="includes.js.validator.path" bundle="security" />" defer="defer"></script>
<script language="javascript" src="include/javascript/eventOnKeyPress.js"></script>
<script language="javascript">
function saveForm(){
	if(!Validator.Validate(document.forms.form,3)){
      return ;
   }
	if(isNaN(document.form.seqNo.value)){
		alert('<bean:message key="comm.jsp.commconfigtrue.add.saveForm1" bundle="conf.comm.CommMessageguoh"/>');	
		return true;
	}
	if(isNaN(document.form.itemCode.value)){
	  	alert('<bean:message key="comm.jsp.commconfigtrue.add.sc" bundle="conf.comm.CommMessageguoh"/>'); 
		return true;
	}
	if(document.form.itemCode.value == ""){
		alert('<bean:message key="comm.jsp.commconfigtrue.add.saveForm2" bundle="conf.comm.CommMessageguoh"/>');
		return;

	}
	if(document.form.itemName.value == ""){
	 	alert('<bean:message key="comm.jsp.commconfigtrue.add.saveForm3" bundle="conf.comm.CommMessageguoh"/>');
	 	return ;
	}
 	//if (!confirm(getCommMessage("0-000003","",""))) return false;  
	//document.form.verbId.value = "add";
	//document.form.submit();
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

<body onload="showHspMessage('<%=commConfigTrue.getMessage()%>')" >
<form name="form" method="post" action= "comm/commConfigTrue.do" >

<input type="hidden" name="verbId" value="add" />
 <table border="0" cellpadding="0" cellspacing="0" class="tblFill" border="0" cellpadding="0" cellspacing="0" class="tblFill">
    <tr>
        <td class="tblTitle" colspan="4"><span>※</span><bean:message key="comm.jsp.commconfigtrue.add.td1" bundle="conf.comm.CommMessageguoh"/> <span>※</span></td>
    </tr>
    <tr>
        <td class="tblLable">
         <span>*</span> <bean:message key="comm.jsp.commconfigtrue.add.td3" bundle="conf.comm.CommMessageguoh"/>:
        </td>
        <td>
            <input type="text"  name="itemCode" size="20" maxlength="2"
            	onkeypress="eventOnKeyPress('itemName')" onkeyup="value=value.replace(/[^\d\w]/g,'')" max="32" dataType="LimitB" msg="代码输入过长"
              value="<%=commConfigTrue.getItemCode() %>" />
        </td> 
        <td class="tblLable">
          	<span>*</span> <bean:message key="comm.jsp.commconfigtrue.add.td4" bundle="conf.comm.CommMessageguoh"/>:
        </td>
        <td>
            <input type="text" name="itemName" size="50" maxlength="50"
              onkeypress="eventOnKeyPress('seqNo')" onkeyup="value=value.replace(/[^\d\w]/g,'')" max="40" dataType="LimitB" msg="名称输入过长"
              value="<%=commConfigTrue.getItemName() %>" />
        </td>  
    </tr>
    <tr> 
        
         <td class="tblLable">
            <bean:message key="comm.jsp.commconfigtrue.add.td2" bundle="conf.comm.CommMessageguoh"/>:
        </td>
        <td>
            <input type="text"  name="seqNo" id="seqNo" size="30" maxlength="20"
               onkeypress="eventOnKeyPress('comments')"  max="11" dataType="LimitB" msg="序号输入过长"
              value="<%=commConfigTrue.getSeqNo() %>"   />
        </td> 
     <td class="tblLable">
            <bean:message key="comm.jsp.commconfigtrue.add.td5" bundle="conf.comm.CommMessageguoh"/>:
        </td>
        <td>
            <input type="text" name="comments" id="comments" size="30" maxlength="50"
         	  onkeypress="eventOnKeyPress('btnSaveForm')" max="40" dataType="LimitB" msg="备注输入过长"
              value="<%=commConfigTrue.getComments() %>" />
        </td>
   </tr>
  </table>


  <!-- Sheet operation button area -->
  <div class="btnSave">
  		<input type="button"  name="btnSaveForm" style="font-size:12px;font-family:Arial;" value='<bean:message key="comm.jsp.commconfigtrue.add.td6" bundle="conf.comm.CommMessageguoh"/>' onclick="saveForm()" />
        <input type="button"  name="btnBack" style="font-size:12px;font-family:Arial;" value='<bean:message key="comm.jsp.commconfigtrue.add.td7" bundle="conf.comm.CommMessageguoh"/>' onclick="history.go(-1);" />         
  </div>
</form>
</body>
</html>
