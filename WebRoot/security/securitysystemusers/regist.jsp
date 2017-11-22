<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>
<%@ page import="org.apache.commons.lang.time.DateFormatUtils"%>
<%	
		String stauts = "1";
		String dateTime = DateFormatUtils.format(new Date(), "HH:mm:ss dd/MM/yyyy");
		String path = request.getContextPath();
		String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
		
		HttpSession httpSession = request.getSession(true);
		com.tianjian.security.struts.form.SessionForm sessionForm = (com.tianjian.security.struts.form.SessionForm) httpSession.getAttribute("sessionForm");
		

%>
<jsp:useBean id="dataForm" scope="request" type="com.tianjian.security.struts.form.SecuritySystemUsersForm" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<%
	if (request.getServerPort() == 80) {
	%>
	<base href="http://<%=request.getServerName()%><%=request.getContextPath()%>/"/>
	<%
	} else {
	%>
	<base href="http://<%=request.getServerName()%>:<%=request.getServerPort()%><%=request.getContextPath()%>/"/>
	<%
	}
	%>
	<title><bean:message key="security.jsp.securitysystemuse.rsregist.item" bundle="security"/></title>
	<meta http-equiv="pragma" content="no-cache"/>
	<meta http-equiv="cache-control" content="no-cache"/>
	<meta http-equiv="expires" content="0"/>
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3"/>
	<meta http-equiv="description" content="This is my page"/>
	<link type="text/css" rev="stylesheet" rel="stylesheet" href="include/css/form.css" />
	<style type="text/css">
		*{font-family:Arial;}
		#footer{
		height:27px;
		font-size:14px;
		line-height:27px;
		text-align:center;
		color:#FFFFFF;
		position:absolute;
	    margin-top: -27px; /* footer高度的负值 */
	    width:100%;
		top:100%;
		}
	</style>
	<script language="javascript" src="<bean:message key="security.js.SecurityMessage.path" bundle="security" />"></script>
	<script language="javascript" src="include/javascript/eventOnKeyPress.js"></script>
	<script type="text/javascript">
		function saveForm(){
			var staffCode = document.form.staffCode.value;
			//var regCode = document.form.regCode.value;
			var passwd = document.form.passwd.vlaue;
			var passwd_ = document.form.passwd_.value;
			if(staffCode==null || staffCode==""){
				alert('<bean:message key="security.jsp.securitysystemusers.regist.warn" bundle="security"/>');
				return false;
			}
			if(checkRegCode() && checkPwd()){
				document.form.submit();
				return true;
			}else
				return false;
		}
		function checkRegCode(){
			if(checkRegCode3('regCode_1') && checkRegCode3('regCode_2')
				&& checkRegCode3('regCode_3') && checkRegCode3('regCode_4') && checkRegCode3('regCode_5'))
				return true;
			else{
				alert('<bean:message key="security.jsp.securitysystemusers.regist.warn1" bundle="security"/>');
				return false;
			}		
		}
		function checkRegCode2(){
			var partn = /^[0-9a-zA-Z]{5}$/;
			var regCode = document.form.regCode.value;
			//alert("regCode="+regCode+"\nlength="+regCode.length);
			var march = true;
			if(regCode.length!=29) return false;
			for(var i=0; i<5; i++){
				march = partn.test(regCode.substr(6*i,5)) && march;
				if(march==false) return false;				
			}
			for(i=1; i<5; i++){
				march = ((regCode.substr(6*i-1,1)=="-") ? true : false) && march;
				if(march==false) return false;	
			}			
			return march;
		}
		function checkRegCode3(param){
			paste();
			
			var partn = /^[0-9a-zA-Z]{5}$/;
			var regCode = document.getElementsByName(param)[0].value;
			//alert("regCode="+regCode+"\nlength="+regCode.length);

			var march = true;
			if(regCode.length!=5){
				return false;
			};
			march = partn.test(regCode) && march;
			if(param=="regCode_1" && march==true)
				document.getElementsByName('regCode_2')[0].focus();
			if(param=="regCode_2" && march==true)
				document.getElementsByName('regCode_3')[0].focus();
			if(param=="regCode_3" && march==true)
				document.getElementsByName('regCode_4')[0].focus();
			if(param=="regCode_4" && march==true)
				document.getElementsByName('regCode_5')[0].focus();
			if(param=="regCode_5" && march==true)
				document.getElementsByName('passwd')[0].focus();
			return march;
		}
		function checkPwd(){
			var pAsswd = document.form.passwd.value;
			var pAsswd_ = document.form.passwd_.value;
			if(pAsswd != null && pAsswd !=""){				
				if(pAsswd_ != null && pAsswd_ !="")	{			
					if(pAsswd == pAsswd_){
						return true;
					}else{
						alert('<bean:message key="security.jsp.securitysystemusers.regist.warn2" bundle="security"/>');
						return false;
					}
				}else{
					alert('<bean:message key="security.jsp.securitysystemusers.regist.warnO" bundle="security"/>');
					return false;
				}				
			}else{
				alert('<bean:message key="security.jsp.securitysystemusers.regist.warnN" bundle="security"/>');
				return false;
			}				
		}
function paste()   
  {          
         // var copySN=clipboardData.getData("Text");	  火狐下不能使用		
         	var copySN = document.form.regCode_1.value;
          if (copySN.length==29) {
	          document.form.regCode_1.value=copySN.substring(0,5);   
	          document.form.regCode_2.value=copySN.substring(6,11);
	          document.form.regCode_3.value=copySN.substring(12,17);
	          document.form.regCode_4.value=copySN.substring(18,23);
	          document.form.regCode_5.value=copySN.substring(24,29);
	          document.form.regCode_5.focus();
         }else if(copySN.length>5){
        	 document.form.regCode_1.value=copySN.substring(0,5);  
         }
        
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
	 function sendRequest(code){
			createXMLHttpRequest();	
			var staffCode = document.getElementById (code).value;
			var url = "<%=request.getContextPath()%>/security/SecuritySystemUsersRegist.do?verbId=verification&staffCode="+staffCode+"";		
            xmlrequest.open("GET", url, true);          
			xmlrequest.onreadystatechange = processResponse;
			xmlrequest.send(null);
		}
      function processResponse(){
			if (xmlrequest.readyState == 4){
				if (xmlrequest.status == 200){
               		document.getElementById("message").innerHTML = xmlrequest.responseText;
				 } 
			}
		}
	function contact(){
			 	//var a = '<bean:message key="main.jsp.top.div.alertSiteUrl" bundle="conf.main.main"/>：';
			 	//alert(a+'<a href =http://www.mpps.gob.ve target=_blank>http://www.mpps.gob.ve</a>',height:250,width:400,fixPosition:true,dragOut:false);
		 	}
	function showMessage(msgString){
		if(msgString != ""){
			alert(msgString);
			return;
		}
	}
	</script>
	<link rel="stylesheet" type="text/css" href="main/include/css/index_n.css" />
	<link rel="shortcut icon" type="image/ico" href="favicon.ico" />
</head>
<body onload="showMessage('<%=dataForm.getMessage()%>')" >
	<form name="form" method="post" action="security/SecuritySystemUsersRegist.do">
		<input type="hidden" name="verbId" value="regist" />
		<%@ include file="/main/header.jsp" %>
		
		<div id="top">
		    <img src="main/include/images/laba_06.png" width="14" height="16" align="absmiddle" /> 欢迎您！今天是<%=dateTime %>
		</div>
		
		<div style="display: block;" id="iframe1">
			<table border="0" cellpadding="0" cellspacing="0" class="tblFill" align="center" >
				<tr>
					<td class="tblTitle" colspan="2"> 
						<span>※</span>&nbsp;&nbsp;<bean:message key="security.jsp.securitysystemuse.rsregist.item" bundle="security"/>&nbsp;&nbsp;
						<span>※</span>
					</td>
				</tr>
				<tr>
					<td class="tblLable">
						<bean:message key="security.jsp.commom.staffCode" bundle="security"/>
					</td>
					<td class="houother">
						<input type="text" class="kuandu" name="staffCode" id="staffCode" onchange="sendRequest(this.id)" onkeypress="eventOnKeyPress('regCode_1')" value="<%=dataForm.getStaffCode()%>" />
						<font color="red" id="message"></font>
					</td>
				</tr>
				<tr>
					<td class="tblLable">
						<bean:message key="security.jsp.commom.regCode" bundle="security"/>
					</td>
					<td class="" style="background-color:#ffffff; text-align:left; padding-left:5px;">
						<input type="text" class="kuandu" name="regCode_1" style="width: 70px;" maxlength="29" value="<%=dataForm.getRegCode_1()%>" onkeypress="eventOnKeyPress('regCode_2')" onpaste="javascript:paste();" onChange="checkRegCode3('regCode_1')" />
						-
						<input type="text" class="kuandu" name="regCode_2" style="width: 70px;" maxlength="5" onkeypress="eventOnKeyPress('regCode_3')" value="<%=dataForm.getRegCode_2()%>" onChange="checkRegCode3('regCode_2')" />
						-
						<input type="text" class="kuandu" name="regCode_3" style="width: 70px;" maxlength="5" onkeypress="eventOnKeyPress('regCode_4')" value="<%=dataForm.getRegCode_3()%>" onChange="checkRegCode3('regCode_3')" />
						-
						<input type="text" class="kuandu" name="regCode_4" style="width: 70px;" maxlength="5" onkeypress="eventOnKeyPress('regCode_5')" value="<%=dataForm.getRegCode_4()%>" onChange="checkRegCode3('regCode_4')" />
						-
						<input type="text" class="kuandu" name="regCode_5" style="width: 70px;" maxlength="5" onkeypress="eventOnKeyPress('passwd')" value="<%=dataForm.getRegCode_5()%>" onChange="checkRegCode3('regCode_5')" />
					</td>
				</tr>
				<tr>
					<td class="tblLable">
						<bean:message key="security.jsp.securitysystemusers.regist.passwd" bundle="security"/>
					</td>
					<td class="houother">
						<input type="password" class="kuandu" name="passwd" maxlength="15" onkeypress="eventOnKeyPress('passwd_')" value="<%=dataForm.getPasswd()%>" />
					</td>
				</tr>
				<tr>
					<td class="tblLable">
						<bean:message key="security.jsp.securitysystemusers.regist.passwd_" bundle="security"/>
					</td>
					<td class="houother">
						<input type="password" class="kuandu" name="passwd_" maxlength="15" onkeypress="eventOnKeyPress('btnSaveForm')" value="<%=dataForm.getPasswd_()%>" />
					</td>
				</tr>
			</table>
			<!-- Sheet operation button area -->
			<div class="btnSave">
				<input type="button"  name="btnSaveForm" value="<bean:message key="security.jsp.securitysystemusers.regist.button" bundle="security"/>" onClick="saveForm()" />
				<input type="button"  name="btnBack" value="<bean:message key="security.jsp.commom.button2" bundle="security"/>" onClick="window.location.href='<%=request.getContextPath()%>/index.jsp'" />
			</div>
		</div>
		
		<div id="footer" >
			<%=application.getAttribute("security.SYSTEMNAME") %>
		</div>
	</form>
</body>
</html>
