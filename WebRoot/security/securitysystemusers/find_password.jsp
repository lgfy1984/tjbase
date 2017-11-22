<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>
<%@ page import="com.tianjian.security.struts.form.FindPasswordForm"%>
<%@ page import="org.apache.commons.lang.time.DateFormatUtils"%>
<%	
		String stauts = "1";
		
		String dateTime = DateFormatUtils.format(new Date(), "HH:mm:ss dd/MM/yyyy");
		FindPasswordForm findPasswordForm = (FindPasswordForm) request.getAttribute("findPasswordForm");
		if (findPasswordForm == null) {
			findPasswordForm = new FindPasswordForm();
		}
		
	
		String path = request.getContextPath();
		String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
		
		HttpSession httpSession = request.getSession(true);
		com.tianjian.security.struts.form.SessionForm sessionForm = (com.tianjian.security.struts.form.SessionForm) httpSession.getAttribute("sessionForm");
		

%>
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
	<title><bean:message key="security.jsp.findpassword.item" bundle="security"/></title>
	<meta http-equiv="pragma" content="no-cache"/>
	<meta http-equiv="cache-control" content="no-cache"/>
	<meta http-equiv="expires" content="0"/>
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3"/>
	<meta http-equiv="description" content="This is my page"/>
	<link rel="shortcut icon" type="image/ico" href="favicon.ico" />
	<link rel="stylesheet" type="text/css" href="main/include/css/index_n.css" />
	<link type="text/css" rev="stylesheet" rel="stylesheet" href="include/css/form.css" />
	<script language="javascript" src="<bean:message key="security.js.SecurityMessage.path" bundle="security" />"></script>
	<script language="javascript" src="include/javascript/eventOnKeyPress.js"></script>
	<script type="text/javascript">
		function saveForm(){
			var username = document.form.userName.value;
			if(username == null || username == ""){
				alert('<bean:message key="security.jsp.findpassword.warn" bundle="security"/>');
				return false;
			}
			if(checkRegCode()){
				document.form.submit();
				return true;
			}else{
				return false;
			}
			
		}
		function checkRegCode() {
			var email = document.form.email.value; 
     		if(email!='') {
   				if(regIsEmail(email)){ 
					 return true; 
 			    }else{
					alert('<bean:message key="security.jsp.findpassword.warn2" bundle="security"/>');
					return false;
 			    } 
  			}else{
				alert('<bean:message key="security.jsp.findpassword.warn1" bundle="security"/>');
  				return false;
  			}		
		}
		 function regIsEmail(fData){
       	 	var reg = new RegExp(/^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/);
       		return reg.test(fData);
    	}
	</script>
	<script type="text/javascript">
			function juzhong(){
				var bodyheight = window.screen.availHeight - 70;
				//alert(bodyheight);
				document.getElementById("full").style.marginTop=(bodyheight-411)/2;
			}
			function showMessage(msg){
				if(msg!='null'){
					alert(msg);
					return false;
				}	
			}
			 
		</script>
</head>
<body  onload="showMessage('<%=findPasswordForm.getMessage()%>');" scroll="no">
	<form name="form" method="post" action="security/findPassword.do">
		<input type="hidden" name="verbId" value="modify"/>
		<%@ include file="/main/header.jsp" %>
		
		<div id="top">
		    <img src="main/include/images/laba_06.png" width="14" height="16" align="absmiddle" /> 欢迎您！今天是<%=dateTime %>
		</div>
		
		<div style="display: block;" id="iframe1">
			<table border="0" cellpadding="0" cellspacing="0" class="tblFill" align="center">
				<tr>
					<td class="tblTitle" colspan="2">
						<span>※</span> &nbsp;&nbsp;<bean:message key="security.jsp.findpassword.item" bundle="security"/>&nbsp;&nbsp;
						<span>※</span> 
					</td>
				</tr>
				<tr>
					<td class="tblLable">
						<bean:message key="security.jsp.findpassword.username" bundle="security"/>
					</td>
					<td>
						<input type="text" class="kuandu" name="userName" onkeypress="eventOnKeyPress('email')" value="" />
					</td>
				</tr>
				<tr>
					<td class="tblLable">
						<bean:message key="security.jsp.findpassword.email" bundle="security"/>
					</td>
					<td>
						<input type="text" class="kuandu" name="email" onkeypress="eventOnKeyPress('btnSaveForm')" value="" />
					</td>
				</tr>			
			</table>
			<!-- Sheet operation button area -->
			<div class="btnSave">
				<input type="button" name="btnSaveForm" value="<bean:message key="security.jsp.findpassword.button" bundle="security"/>" onclick="saveForm();" />
				<input type="button" name="btnBack" value="<bean:message key="security.jsp.commom.button2" bundle="security"/>" onclick="window.location.href='<%=request.getContextPath()%>/index.jsp'" />
			</div>
		</div>
		
		<div id="footer"><%=application.getAttribute("security.SYSTEMNAME") %></div>
	</form>
</body>
</html>
