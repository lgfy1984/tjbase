<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="html" uri="/WEB-INF/struts-html.tld"%>
<%@ taglib prefix="bean" uri="/WEB-INF/struts-bean.tld"%>
<%@ taglib prefix="logic" uri="/WEB-INF/struts-logic.tld"%>
<jsp:useBean id="commConfigNationality" scope="request" class="com.tianjian.comm.struts.form.CommConfigNationalityForm" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<%
		if (request.getServerPort() == 80) {
		%>
		<base href="http://<%=request.getServerName()%><%=request.getContextPath()%>/">
		<%
		} else {
		%>
		<base href="http://<%=request.getServerName()%>:<%=request.getServerPort()%><%=request.getContextPath()%>/">
		<%
		}
		%>
		<title><bean:message key="comm.jsp.common.addNationMessage" bundle="conf.comm.Comm"/></title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<script language="javascript" src="<bean:message key="comm.js.comm.tjme" bundle="conf.comm.Comm"/>"></script>
		<script language="javascript" src="<bean:message key="includes.js.validator.path" bundle="security" />" defer="defer"></script>
		<script language="javascript" src="include/javascript/eventOnKeyPress.js"></script>
		<script language="javascript">
		function trim(str){  //删除左右两端的空格
			return str.replace(/(^\s*)|(\s*$)/g, "");
		}
		
		function yznumber(id,itermname,integer,decimal){
		var inputvalue= (document.getElementById(id)).value;
		if(inputvalue != ""){
			var reg = /^[0-9]+(\.[0-9]+)?$/;
			if(reg.test(inputvalue)){
				if(inputvalue.indexOf(".")!=-1){
					array = inputvalue.split(".");
					arg1 = array[0];
					arg2 = array[1];										
				if(arg1.length > integer){
						alert("\""+itermname+"\""+"<bean:message key="comm.jsp.common.warm2" bundle="conf.comm.Comm"/>"+integer+"<bean:message key="comm.jsp.common.weishu" bundle="conf.comm.Comm"/>");
						return false;
					}
					if(arg2.length > decimal){
						alert("\""+itermname+"\""+"<bean:message key="comm.jsp.common.warm1" bundle="conf.comm.Comm"/>"+decimal+"<bean:message key="comm.jsp.common.weishu" bundle="conf.comm.Comm"/>");
						return false;
					}				
				}else{
					if(inputvalue.length > integer){
						alert("\""+itermname+"\""+"<bean:message key="comm.jsp.common.warm3" bundle="conf.comm.Comm"/>"+integer+"<bean:message key="comm.jsp.common.weishu" bundle="conf.comm.Comm"/>");						
						return false;
					}
				}			
			}else{
				alert("\""+itermname+"\"<bean:message key="comm.jsp.common.musthavenumber" bundle="conf.comm.Comm"/>！");
				return false;
				}
			}		
		}
function saveForm(){
	if(!Validator.Validate(document.forms.form,3)){
      return ;
    } 
	if(document.form.itemCode.value == ""){
		alert('<bean:message key="security.jsp.securityConfigParamClass1.update.warn" bundle="security"/>');
	 	return ;
	}
	if(isNaN(document.form.itemCode.value)){
			alert('<bean:message key="comm.jsp.commconfigtrue.add.sc" bundle="conf.comm.CommMessageguoh"/>');
			return ; 
	}
	if(document.form.itemName.value == ""){
		alert('<bean:message key="security.jsp.securityConfigParamClass1.update.warn1" bundle="security"/>');
	 	return ;
	}
	if(isNaN(document.form.seqNo.value)){
		alert("<bean:message bundle='comm.commLocale' key='comm.jsp.warn1'/>");
		return;
	}
	if (confirmMessage("<bean:message key='security.jsp.commom.save' bundle='security'/>")){     
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
	<body onload="showHspMessage('<%=commConfigNationality.getMessage()%>')">
		<form name="form" method="post" action="comm/commConfigNationality.do">
			<input type="hidden" name="verbId" value="add" />
			<table align="center" border="0" cellpadding="0" cellspacing="0" class="tblFill" border="0" cellpadding="0" cellspacing="0" class="tblFill">
				<tr>
					<td class="tblTitle" colspan="4"><span>※</span><bean:message key="comm.jsp.common.addNation" bundle="conf.comm.Comm"/><span>※</span></td>
				</tr>
				<tr>
					<td class="tblLable">
						<span>*</span> <bean:message key="comm.jsp.common.item" bundle="conf.comm.Comm"/>:
					</td>
					<td>
						<input type="text" class="kuandu" name="itemCode" size="20" maxlength="8" onkeypress="eventOnKeyPress('itemName')" max="32" dataType="LimitB" msg="代码输入过长"  value="<%=commConfigNationality.getItemCode()%>" />
					</td>
					<td class="tblLable">
						<span>*</span> <bean:message key="comm.jsp.common.names" bundle="conf.comm.Comm"/>:
					</td>
					<td>
						<input type="text" class="kuandu" name="itemName" size="50" maxlength="50" onkeypress="eventOnKeyPress('seqNo')" max="40" dataType="LimitB" msg="名称输入过长"  value="<%=commConfigNationality.getItemName()%>" />
					</td>
				</tr>
				<tr>
					<td class="tblLable">
						<bean:message key="comm.jsp.common.seqNo" bundle="conf.comm.Comm"/>:
					</td>
					<td>
						<input type="text" class="kuandu" name="seqNo" id="seqNo" size="30" maxlength="20" onkeypress="eventOnKeyPress('comments')" max="11" dataType="LimitB" msg="序号输入过长"  value="<%=commConfigNationality.getSeqNo() %>"  />
					</td>
					<td class="tblLable">
						<bean:message key="comm.jsp.common.comments" bundle="conf.comm.Comm"/>:
					</td>
					<td>
						<input type="text" class="kuandu" name="comments" id="comments" size="30" maxlength="50" onkeypress="eventOnKeyPress('btnSave')"  max="40" dataType="LimitB" msg="备注输入过长" value="<%=commConfigNationality.getComments()%>" />
					</td>
				</tr>
			</table>
			<!-- Sheet operation button area -->
			<div class="btnSave">
				<input type="button" style="font-size:12px;font-family:Arial;" id="btnSave" name="btnSave"  name="btnSaveForm" value="<bean:message key="comm.jsp.common.save" bundle="conf.comm.Comm"/>" onClick="saveForm()" />
				<input type="button" style="font-size:12px;font-family:Arial;" id="btnHistory" name="btnHistory"  name="btnBack" value="<bean:message key="comm.jsp.common.return" bundle="conf.comm.Comm"/>" onClick="history.go(-1);" />
			</div>
		</form>
	</body>
</html>
