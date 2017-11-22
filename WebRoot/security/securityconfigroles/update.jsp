<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="html" uri="/WEB-INF/struts-html.tld"%>
<%@ taglib prefix="bean" uri="/WEB-INF/struts-bean.tld"%>
<%@ taglib prefix="logic" uri="/WEB-INF/struts-logic.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<%if(request.getServerPort() == 80) {%>
  <base href="http://<%=request.getServerName()%><%=request.getContextPath()%>/">
  <%} else {%>
  <base href="http://<%=request.getServerName()%>:<%=request.getServerPort()%><%=request.getContextPath()%>/">
<%}%>
<title><bean:message key="security.jsp.securityConfigroles.update.item" bundle="security"/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<script language="javascript" src="<bean:message key="include.js.TJMessage.path" bundle="security" />"></script>
<script language="javascript" src="include/javascript/eventOnKeyPress.js"></script>
<script language="javascript" src="<bean:message key="includes.js.validator.path" bundle="security" />" defer="defer"></script>
<script language="javascript">
function saveForm(){
	if(!Validator.Validate(document.forms.form,3)){
      return ;
   	}
	if(document.form.roleCode.value == ""){
		alert('<bean:message key="security.jsp.securityConfigParamClass1.update.warn" bundle="security"/>');
	 	return ;
	}
	if(isNaN(document.form.roleCode.value)){
			alert('<bean:message key="comm.jsp.commconfigtrue.add.sc" bundle="conf.comm.CommMessageguoh"/>');
			return ; 
	}
	if(document.form.roleDetail.value == ""){
		alert('<bean:message key="security.jsp.securityConfigParamClass1.update.warn1" bundle="security"/>');
	 	return ;
	}
	if(isNaN(document.form.serialNo.value)){
		alert('<bean:message key="security.jsp.securityConfigpublic.update.warn" bundle="security"/>');
		return true;
	}
	if (confirmMessage("<bean:message key='security.jsp.commom.update' bundle='security'/>")){     
	    document.form.verbId.value = "update";    
	    document.form.submit(); 
    }   
}
function showMessage(message){
				if(message != ''&& message != null){
					alert(message);
					return;
				}
			}

</script>
<link type="text/css" rev="stylesheet" rel="stylesheet" href="include/css/form.css" />
</head>

<body onload="showMessage('${dataForm.message}')" >
<form name="form" method="post" action= "security/securityConfigRoles.do" >

<input type="hidden" name="verbId" value="update" />
<input type="hidden" name="data.id" value="${dataForm.data.id}" />
<input type="hidden" name="idHidden" value="${dataForm.idHidden}" />
<input type="hidden" name="roleCodeHidden" value="${dataForm.roleCodeHidden}" />
 <table border="0" cellpadding="0" cellspacing="0" class="tblFill" align="center">
    <tr>
       <td class="tblTitle" colspan="4"><span>※</span> 
       		<bean:message key="security.jsp.securityConfigroles.update.item" bundle="security"/>
      		<span>※</span>
		</td>
    </tr>
    <tr>

        <td class="tblLable" >
          <span>*</span> <bean:message key="security.jsp.commmom.classCode" bundle="security"/>：
        </td>
        <td style="width:30%">
            <input type="text" name="data.roleDetail" id=roleDetail size="50" maxlength="40"
              onkeypress="eventOnKeyPress('serialNo')" max="40" dataType="LimitB" msg="名称输入过长"
              value="${dataForm.data.roleDetail}" />
        </td>
        <td class="tblLable"><span>*</span> 
         <bean:message key="security.jsp.commom.menuCode" bundle="security"/>：
        </td>
        <td>
            <input type="text" name="data.roleCode" id=roleCode size="20" maxlength="20"
            	onkeypress="eventOnKeyPress('roleDetail')" max="20" dataType="LimitB" msg="代码输入过长"
              value="${dataForm.data.roleCode}" />
        </td>  
    </tr>
    <tr>
      
        <td class="tblLable" >
            <bean:message key="security.jsp.commom.serialNo" bundle="security"/>：
        </td>
        <td>
            <input type="text" name="data.serialNo" id="serialNo" size="30" maxlength="11"
               onkeypress="eventOnKeyPress('comments')"
              value="${dataForm.data.serialNo}" readonly/>
        </td>

          <td class="tblLable" >
            <bean:message key="security.jsp.commmom.comments" bundle="security"/>：
        </td>
        <td>
            <input type="text" name="data.comments"
            	 id="comments" size="30" maxlength="40"
            	 max="40" dataType="LimitB" msg="备注输入过长" 
            	onkeypress="eventOnKeyPress('btnSaveForm')" 
             value="${dataForm.data.comments}" />
        </td>
    </tr>
    
     
  </table>


  <!-- Sheet operation button area -->
  <div class="btnSave">
  	    <input type="button"  name="btnSaveForm" value="<bean:message key="security.jsp.commom.button1" bundle="security"/>" onClick="saveForm()" />
        <input type="button"  name="btnBack" value="<bean:message key="security.jsp.commom.button2" bundle="security"/>" onClick="history.go(-1);" />
  </div>
</form>

</body>
</html>
