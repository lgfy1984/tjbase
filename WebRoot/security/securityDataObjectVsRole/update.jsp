<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<jsp:useBean id="data" scope="request" class="com.tianjian.security.struts.form.SecurityDataObjectVsRoleForm"/>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
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
    
    <title><bean:message key="security.jsp.securityDataObjectVsRole.update.title" bundle="security"/></title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
    <script language="javascript" src="include/javascript/TJMessage.js"></script>
	<script language="javascript" src="include/javascript/eventOnKeyPress.js"></script>
	<script type="text/javascript" src="<bean:message key="include.js.checkbox_radio.path" bundle="security" />"></script>
	<script language="javascript" src="<bean:message key="security.js.gettext_staff.path" bundle="security" />"></script>
	<script type="text/javascript" src="<%=basePath%>hsp/include/javascript/jianbian.js"></script>
    <link type="text/css" rev="stylesheet" rel="stylesheet" href="include/css/form.css" />
    <link rel="stylesheet" href="include/css/open.css" />
  </head>
  <script type="text/javascript">
  function saveForm(){
  		
  		if(document.getElementById("displayInputId_2").value==""){
			alert("<bean:message key="security.jsp.commom.warn10" bundle="security"/>");
			return;
		}
		if(document.getElementById("sdtoId").value==""){
			alert("<bean:message key="security.jsp.commom.warn11" bundle="security"/>");
			return;
		}
		if(document.getElementById("displayInputId_1").value==""){
			alert("<bean:message key="security.jsp.commom.warn12" bundle="security"/>");
			return;
		}
		if (confirmMessage("0-000003")){ 
			document.form.verbId.value = "update";
			document.form.submit();
		}
	}
function check(url,id1,id2){
	var id=document.getElementById("sdtoId").value;
	var path=url+"&id="+id;
	add(path,id1,id2);
}
  	 function createXMLHttpRequest(){
	if(window.XMLHttpRequest){ 
		xmlrequest = new XMLHttpRequest();
	}else if(window.ActiveXObject){
		try{
			xmlrequest = new ActiveXObject("Msxml2.XMLHTTP");
		}catch(e){
			try{
				xmlrequest = new ActiveXObject("Microsoft.XMLHTTP");
			}catch(e){}
		}
	}
 }
	function chick(){
			createXMLHttpRequest();
			var id=document.getElementById("id").value;
			var uri = "security/securityDateVs.do?verbId=checkItemCode&id="+id+"";
			xmlrequest.open("GET", uri, true);
			xmlrequest.onreadystatechange = processResponse;
			xmlrequest.send(null);
	}
function processResponse(){
	if (xmlrequest.readyState == 4){
		if (xmlrequest.status == 200){
			if(xmlrequest.responseText.length==0){
		
			}else{
				alert(xmlrequest.responseText);
			}
		}else{
			window.alert("<bean:message key="security.jsp.securityDataObject.add.warn3" bundle="security"/>.");
		}
	}
}
  </script>
  <body onLoad="showMessage('','<%=data.getMessage() %>','1')">
  		<form name="form" method="post" action="security/securityDateVs.do">
  	    <input type="hidden" name="verbId" value="update" />
  	    <input type="hidden" name="id" id="id" value="<%=data.getId() %>" />
			<table align="center" border="0" cellpadding="0" cellspacing="0" class="tblFill" border="0" cellpadding="0" cellspacing="0" class="tblFill">
				<tr>
					<td class="tblTitle" colspan="4">
						<span>※</span>&nbsp;&nbsp;<bean:message key="security.jsp.securityDataObjectVsRole.update.item" bundle="security"/>&nbsp;&nbsp;
						<span>※</span>
					</td>
				</tr>
				<tr>
					<td class="tblLable">
						<span>*</span><bean:message key="security.jsp.commom.securityStaffBaseInfo" bundle="security"/>：
					</td>
					<td style="padding-right:40px;">
						<input type="text" class="kuandu" name="securityStaffBaseInfo" value="<%=data.getSecurityStaffBaseInfoName() %>" id=displayInputId_2 size="32" maxlength="32" onKeyPress="eventOnKeyPress('sdtoId')" />
						 <!--这是准备存储到数据的字段-->
						<input type="hidden" id="hiddenInputId_2" value="<%=data.getSecurityStaffBaseInfo() %>" name="securityStaffBaseInfoes" />
						
						<img src="<%=basePath%>hsp/include/images/select.gif" style="cursor:pointer;position:absolute;" onClick="add('<%=basePath%>security/securityDateVs.do?verbId=getSecurityStaff','displayInputId_2','hiddenInputId_2')" />
					</td>
					<td class="tblLable">
					<bean:message key="security.jsp.commom.sdtoId" bundle="security"/>：
					</td>
					<td>
					<select name="sdtoId" onkeypress="eventOnKeyPress('sdtoValue')" id="sdtoId" style="width: 250px;">
						<%
						if(data.getSdotIdList()!=null&&data.getSdotIdList().size()>0){
							for(int i=0;i<data.getSdotIdList().size();i++){
								Object[] obj=(Object[])data.getSdotIdList().get(i);
							%>
							<option value="<%=obj[0] %>"<%=obj[0].equals(data.getSdotId())? " selected":"" %>><%=obj[1] %></option>
							<%
							}
						}
						 %>
					</select>
					</td>
				</tr>
				<tr>
					<td class="tblLable">
					<bean:message key="security.jsp.commom.sdtoValue" bundle="security"/>：
					</td>
					<td colspan="3" style="padding-right:40px;">
					<input type="text" class="kuandu" name="sdtoValue" readonly="readonly" value="<%=data.getSdoValueName() %>" id="displayInputId_1" size="32" maxlength="32" onKeyPress="eventOnKeyPress('btnSaveForm')" />
					 <!--这是准备存储到数据的字段-->
						<input type="hidden" id="hiddenInputId_1" value="<%=data.getSdoValue() %>" name="sdtoValueis" />
						<img src="<%=basePath%>hsp/include/images/select.gif" style="cursor:pointer;position:absolute;" onClick="check('<%=basePath%>security/securityDateVs.do?verbId=getLocation','displayInputId_1','hiddenInputId_1')" />
					
					</td>
				</tr>
			</table>
			<div class="btnSave">
				<input type="button" id="saveinput"  name="btnSaveForm" value="<bean:message key="security.jsp.commom.button1" bundle="security"/>" onClick="saveForm()" />
				<input type="button"  name="btnBack" value="<bean:message key="security.jsp.commom.button2" bundle="security"/>" onClick="history.go(-1);" />
			</div>
  	</form>
  </body>
</html>
