<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<jsp:useBean id="viewRegistCodeForm" scope="request" type="com.tianjian.security.struts.form.ViewRegistCodeForm" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%
		if (request.getServerPort() == 80) {
		%>
		<base href="http://<%=request.getServerName()%><%=request.getContextPath()%>/" />
		<%
		} else {
		%>
		<base href="http://<%=request.getServerName()%>:<%=request.getServerPort()%><%=request.getContextPath()%>/" />
		<%
		}
		%>
		<title><bean:message key="security.jsp.securitysystemusers.viewcodeselect.title" bundle="security"/></title>
		<meta http-equiv="pragma" content="no-cache" />
		<meta http-equiv="cache-control" content="no-cache" />
		<meta http-equiv="expires" content="0" />
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3" />
		<meta http-equiv="description" content="This is my page" />
		
		<script language="javascript" src="include/javascript/eventOnKeyPress.js"></script>
		<script type="text/javascript">
			function subForm(){
				var queryValue=document.form.queryValue.value;
				if(queryValue==null || queryValue==""){
					alert('<bean:message key="security.jsp.securitysystemusers.viewcodeselect.warn" bundle="security"/>!');
					return;
				}else{
					document.form.verbId.value="view";
					document.form.submit();
				}
			}	
		</script>

		<script language="javascript">
		  function down(){ 
		    	   if(event.keyCode==13){
		    	      subForm();
		    	     } 	
		           } 
		  document.onkeydown=down;
		  function showMessage(message){
			if(message != ''&& message != null){
				alert(message);
				return;
			}
		}
	</script>
	<link rel="stylesheet" rev="stylesheet" href="<%=request.getContextPath() %>/security/include/css/securitycontrolselect.css" />
	</head>
	<body onload="form.queryValue.focus();showMessage('<%=viewRegistCodeForm.getMessage() %>');">
		<form action="security/viewRegistCode.do" method="post" name="form">
			<input type="hidden" name="verbId" value="" />
			<table align="center"  border="0" cellpadding="0" cellspacing="0"    class="tblFill">
				<tr>
					<td class="tblTitle" >
						<bean:message key="security.jsp.securitysystemusers.viewcodeselect.item" bundle="security"/></td>
				</tr>
			</table>
			<div id="full" >
				<div id="xuanze">
					<div id="left"></div>
					<div id="center0" style="">
						<bean:message key="security.jsp.securitysystemusers.viewcodeselect.item1" bundle="security"/>
					</div>
					<div id="right_10"></div>
					<div id="xuanzeneirong">
						<ul id="leibiao">
							<li style="font-family:Arial;font-size:12px;">
								<input type="radio" value="2" id="queryType" name="queryType" checked="checked" />
								<bean:message key="security.jsp.commom.staffCode"  bundle="security"/>
							</li>
							<li style="font-family:Arial;font-size:12px;">
								<input type="radio" value="1" id="queryType" name="queryType" />
								<bean:message key="security.jsp.commom.name" bundle="security"/>
							</li>
							<li style="font-family:Arial;font-size:12px;">
								<input type="radio" value="3" id="queryType" name="queryType" />
								<bean:message key="security.jsp.commom.idNo" bundle="security"/>
							</li>
						</ul>
					</div>
					<div id="right0"></div>
				</div>
				<div id="tiaojian" style="font-family:Arial;font-size:12px;" >
					<bean:message key="security.jsp.securitysystemusers.viewcodeselect.item2" bundle="security"/>
					<br/>
					<br/>
					<input name="queryValue" onkeypress="eventOnKeyPress('picbutton')"  type="text" style="width:350px;font-family:Arial;font-size:12px;" />
				</div>
				
				<div class="btnSave">
					<input type="button"  id="picbutton" style="font-family:Arial;font-size:12px;" name="picbutton" value="<bean:message key="security.jsp.commom.button10" bundle="security"/>" onclick="subForm()" />
				</div>
			</div>
		</form>
	</body>
</html>
