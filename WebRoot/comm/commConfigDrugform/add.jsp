<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<jsp:useBean id="data" scope="request" class="com.tianjian.comm.struts.form.CommConfigDrugformForm" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%if(request.getServerPort() == 80) {%>
		<base href="http://<%=request.getServerName()%><%=request.getContextPath()%>/"/>
		<%} else {%>
		<base href="http://<%=request.getServerName()%>:<%=request.getServerPort()%><%=request.getContextPath()%>/"/>
		<%}%>
		<title><bean:message key="comm.jsp.different.text33" bundle="conf.comm.comm"/>ADD.JSP</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
		<meta http-equiv="pragma" content="no-cache"/>
		<meta http-equiv="cache-control" content="no-cache"/>
		<meta http-equiv="expires" content="0"/>
		<script language="javascript" src="<bean:message key="comm.js.CommMessage.path" bundle="conf.comm.comm"/>"></script>
		<script language="javascript" src="<bean:message key="includes.js.validator.path" bundle="security" />" defer="defer"></script>
		<script language="javascript" src="include/javascript/eventOnKeyPress.js"></script>
		<script language="javascript">
			function saveForm(){
				if(!Validator.Validate(document.forms.form,3)){
	             return ;
	            }
				if(isNaN(document.form.seqNo.value)){
					alert("<bean:message key="comm.jsp.common.text49" bundle="conf.comm.comm"/>"); 
					return true;
				}
				if(document.form.itemCode.value == ""){
				 	alertCommMessage("0-000001");
				 	return ;
				}
				if(document.form.itemName.value == ""){
				 	alertCommMessage("0-000002");
				 	return ;
				}
			
				document.form.verbId.value = "add";
				if(confirm("确定要保存吗？")){
				document.form.submit();
				}
				
			}
			function goBack(){
				document.form.verbId.value="query";
				document.form.submit();
			}
		</script>
		<link type="text/css" rev="stylesheet" rel="stylesheet" href="include/css/form.css" />
	</head>

	<body onload="showCommMessage('','<%=data.getMessage() %>','1')">
		<form name="form" method="post" action="comm/commConfigDrugform.do">

			<input type="hidden" name="verbId" value="add" />
			<table align="center" border="0" cellpadding="0" cellspacing="0" class="tblFill" border="0" cellpadding="0" cellspacing="0" class="tblFill">
				<tr>
					<td class="tblTitle" colspan="4"><span>※</span>添加药品剂型字典<span>※</span></td>
				</tr>
				<tr>
					<td class="tblLable">
						<span>*</span> <bean:message key="comm.jsp.common.text10" bundle="conf.comm.comm"/>：
					</td>
					<td>
						<input type="text" name="itemCode" size="20" maxlength="9" onkeypress="eventOnKeyPress('itemName')"  max="32" dataType="LimitB" msg="代码输入过长" value="<%=data.getItemCode() %>" />
					</td>
					<td class="tblLable">
						<span>*</span> <bean:message key="comm.jsp.common.text29" bundle="conf.comm.comm"/>：
					</td>
					<td>
						<input type="text" name="itemName" size="50" maxlength="50" onkeypress="eventOnKeyPress('seqNo')"  max="32" dataType="LimitB" msg="名称输入过长" value="<%=data.getItemName() %>" />
					</td>
				</tr>
				<tr>
					<td class="tblLable">
						<bean:message key="comm.jsp.common.text48" bundle="conf.comm.comm"/>：
					</td>
					<td>
						<input type="text" name="seqNo" id="seqNo" size="30" maxlength="20" onkeypress="eventOnKeyPress('comments')"   max="11" dataType="LimitB" msg="序号输入过长"  value="<%=data.getSeqNo() %>" />
					</td>
					<td class="tblLable">
						<bean:message key="comm.jsp.common.text4" bundle="conf.comm.comm"/>：
					</td>
					<td>
						<input type="text" name="comments" id="comments" size="30" maxlength="50" onkeypress="eventOnKeyPress('btnSaveForm')"   max="40" dataType="LimitB" msg="备注输入过长"  value="<%=data.getComments() %>" />
					</td>
				</tr>
			</table>

			<!-- Sheet operation button area -->
			<div class="btnSave">
				<input type="button" name="btnSaveForm" value="<bean:message key="comm.jsp.common.text3" bundle="conf.comm.comm"/>" onClick="saveForm()" />
				<input type="button" name="btnBack" value="<bean:message key="comm.jsp.common.text19" bundle="conf.comm.comm"/>" onClick="goBack()" />
			</div>
		</form>
	</body>
</html>