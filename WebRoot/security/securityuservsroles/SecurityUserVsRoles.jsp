<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%
request.setCharacterEncoding("UTF-8");
%>
<%
response.setContentType("text/html; charset=UTF-8");
%>
<jsp:useBean id="securityUserVsRolesForm" scope="request" class="com.tianjian.security.struts.form.SecurityUserVsRolesForm" />
<jsp:useBean id="pb" scope="request" class="com.tianjian.util.comm.PageBean" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
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
		<title><bean:message key="security.jsp.ssecurityuservsroles.SecurityUserVsRoles.title" bundle="security"/></title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<meta http-equiv="pragma" content="no-cache" />
		<meta http-equiv="cache-control" content="no-cache" />
		<meta http-equiv="expires" content="0" />
		<link rel="stylesheet" rev="stylesheet" href="include/css/form.css" />
		<link type="text/css" rev="stylesheet" rel="stylesheet" href="include/css/form.css" />
		<script language="javascript" src="<bean:message key="security.js.SecurityMessage.path" bundle="security" />"></script>
		<script src="<%=request.getContextPath()%>/include/javascript/searchsuggest.js"></script>
		<link rel="stylesheet" href="<%=request.getContextPath()%>/include/css/searchsuggest.css" />
		<script language="javascript" src="include/javascript/eventOnKeyPress.js"></script>
		<script language="javascript">
		function securityUserVsRoles_pageSubmit(url){
			var userIdSelected = document.form.userIdSelected.value;	
			var totalRows = document.form.totalRows.value;
			var temp = "";
			if(userIdSelected == ""){
				alert('<bean:message key="security.jsp.ssecurityuservsroles.SecurityUserVsRoles.warn" bundle="security"/>');
				return;
			}
			if(totalRows == "1"){
				if(document.form.checkBox.checked == true){
					temp = temp + "&rolesIdSelected=" + document.form.rolesId.value;
				}
			} else {
				for(var i = 0; i < document.form.checkBox.length; i++){
					if(document.form.checkBox[i].checked == true){
						temp = temp + "&rolesIdSelected=" + document.form.roleId[i].value;
					}
				}		
			}
	
			var xmlHttp = newXMLHttpRequest();
			var sendTo = "verbId=execute&userIdSelected=" + userIdSelected + temp;
			xmlHttp.open("POST", url, true);
			xmlHttp.setRequestHeader("Content-Type","application/x-www-form-urlencoded"); 
			var securityUserVsRoles_handlerFunction1 = getReadyStateHandler(xmlHttp, securityUserVsRoles_updateRoles1);
			xmlHttp.onreadystatechange = securityUserVsRoles_handlerFunction1;
			xmlHttp.send(sendTo);
			
		}	

function securityUserVsRoles_updateRoles1(rolesXML1) {
	var messageXML = rolesXML1.getElementsByTagName("message")[0];
	if(messageXML.childNodes[0] != null && messageXML.childNodes[0].nodeValue != null){
		message = messageXML.childNodes[0].nodeValue;
		alert(message);
		
	}	
}

function securityUserVsRoles_tableClick(row, userId, url){
	document.form.selectedRow.value = row;
	document.form.userIdSelected.value = userId;
	securityUserVsRoles_setRoles(url);
}

function securityUserVsRoles_setRoles(url){
	var userIdSelected = document.form.userIdSelected.value;	
	var xmlHttp = newXMLHttpRequest();
	var sendTo = "verbId=find&userIdSelected=" + userIdSelected;
	xmlHttp.open("POST", url, true);
	xmlHttp.setRequestHeader("Content-Type","application/x-www-form-urlencoded"); 
	var securityUserVsRoles_handlerFunction = getReadyStateHandler(xmlHttp, securityUserVsRoles_updateUserRoles);
	xmlHttp.onreadystatechange = securityUserVsRoles_handlerFunction;
	xmlHttp.send(sendTo);
}

function securityUserVsRoles_updateUserRoles(rolesXML) {
	var totalRows = document.form.totalRows.value;
	if(totalRows == "1"){
		document.form.checkBox.checked = false;
	} else {
		for(var j = 0; j < document.form.checkBox.length; j++){
			document.form.checkBox[j].checked = false;
		}
	}
	
	var index = -1;
	var indexXML = rolesXML.getElementsByTagName("index")[0];
	if(indexXML.childNodes[0] != null && indexXML.childNodes[0].nodeValue != null){
		index = indexXML.childNodes[0].nodeValue;
		if(parseInt(index) < 1){
			return;
		}
	}
	
	for(var i = 0; i < parseInt(index); i++){
		var valueXML = rolesXML.getElementsByTagName("value")[i];
		if(valueXML.childNodes[0] != null && valueXML.childNodes[0].nodeValue != null){
			value = valueXML.childNodes[0].nodeValue;
			if(totalRows == "1"){
				if(document.form.roleId.value == value){
					document.form.checkBox.checked = true;
				}
			} else {
				for(var j = 0; j < document.form.checkBox.length; j++){
					if(document.form.roleId[j].value == value){
						document.form.checkBox[j].checked = true;
					}
				}
			}
		}
	}	
}

function securityUserVsRoles_tableClickRoles(row){
	var totalRows = document.form.totalRows.value;
	
	if(totalRows == "1"){
		if(document.form.checkBox.checked){
			document.form.checkBox.checked = false;
		} else {
			document.form.checkBox.checked = true;
		}
	} else {
		if(document.form.checkBox[row].checked){
			document.form.checkBox[row].checked = false;
		} else {
			document.form.checkBox[row].checked = true;
		}
	}
}

function newXMLHttpRequest() {
	var xmlreq = false;
	if (window.XMLHttpRequest) {
		xmlreq = new XMLHttpRequest();		
	} else if (window.ActiveXObject) {
	    try {
      		xmlreq = new ActiveXObject("Msxml2.XMLHTTP");
		} catch (e1) {
			try {
        		xmlreq = new ActiveXObject("Microsoft.XMLHTTP");
			} catch (e2) {
			xmlreq = false;
	    	}
    	}
	}	
	return xmlreq;
}

function getReadyStateHandler(req, responseXmlHandler) {
	return function () {
		if (req.readyState == 4) {
      		if (req.status == 200) {
      			//alert(req.responseText); 
			    responseXmlHandler(req.responseXML);
			} else {
			    
("HTTP error: " + req.status);
      		}
    	}
  	}
}

function zcTableMouseOver(row, expect){
	var objTB = document.getElementById("tjTable1");
    var j = objTB.rows.length;
    for(var m = j - 1; m >= 1; m--) {
 		var t = objTB.rows[m];
 		if(m != expect){ 
	 		if(row == m){
	    		t.style.backgroundColor = "#D3D3D3";
	 		} else if(m % 2 == 0){
	  			t.style.backgroundColor = "#F5F5F5";
	 		} else {
	 			t.style.backgroundColor = "#FFFFFF";
	 		}
 		}
    }
}
function goPage(page) {  
   document.form.page.value = page;
   document.form.verbId.value = "search";    
   document.form.submit();
}

function goPage2() {
  var _tp=document.getElementById('_tp');
  var _total=document.getElementById('_total'); 
    if (!isMadeOf(_tp.value,'1234567890')) {
		alert('<bean:message key="security.jsp.commom.warn" bundle="security"/>!');
      return;
    }
    if (_tp.value<=0){
		alert('<bean:message key="security.jsp.commom.warn1" bundle="security"/>!');
		return;
    }
    if(parseInt(_tp.value)>parseInt(_total.value)){
	  alert('<bean:message key="security.jsp.commom.warn2" bundle="security"/>!');
      return;
    } 
    
  document.form.verbId.value = "search";    
  document.form.submit();
}


function zc_tableMouseOver(row){
    var ex = document.form.selectedRow.value;
    zcTableMouseOver(row, ex);
}

function zcTableClick(row){
	var objTB = document.getElementById("tjTable1");
    var j = objTB.rows.length;
    for(var m = j - 1; m >= 1; m--) {
 		var t = objTB.rows[m];
 		if(row == m){
    		t.style.backgroundColor = "#C71585";
 		} else if(m % 2 == 0){
  			t.style.backgroundColor = "#F5F5F5";
 		} else {
 			t.style.backgroundColor = "#FFFFFF";
 		}
    }
}

function securityUserVsRoles_pageSearch(){
	document.form.verbId.value = "search";
	document.form.submit();
}


</script>
	</head>
	<body>
		<form name="form" method="post" action="security/security_securityUserVsRoles.do">
			<input type="hidden" name="verbId" value="<%=securityUserVsRolesForm.getVerbId()%>" />
			<input type="hidden" name="totalRows" value="<%=securityUserVsRolesForm.getRoleId() != null ? securityUserVsRolesForm.getRoleId().length : 0%>" />
			<input type="hidden" name="selectedRow" value="" />
			<input type="hidden" name="userIdSelected" value="" />
			<table border="0" cellpadding="0" cellspacing="0" class="tblSearch" align="center">
				<tr>
					<td class="tblTitle" colspan="2">
						<bean:message key="security.jsp.ssecurityuservsroles.SecurityUserVsRoles.title" bundle="security"/>
					</td>
				</tr>
				<tr>
					<td colspan="2">
						<bean:message key="security.jsp.commom.staffCode" bundle="security"/>：
						<input type="text"  name="userId" maxlength="20" onkeypress="eventOnKeyPress('userName')" value="<%=securityUserVsRolesForm.getUserId()%>" />
						
						<bean:message key="security.jsp.commom.name" bundle="security"/>：
						<input id="userName" name="userName" type="text"  value="<%=securityUserVsRolesForm.getUserName()%>" 
							onkeyup="GiveOptions(event, '<%=request.getContextPath()%>/searchSuggest.do', 'getStaffName_0000000001')" 
							onkeydown="huanhang(event)" />
						<span id="spanOutput" class="spanTextDropdown" style="display: none;"></span>
						
						<bean:message key="security.jsp.commom.hspConfigBaseinfoName" bundle="security"/>：
						<input type="text" id="hspConfigBaseinfoName" name="hspConfigBaseinfoName" value="<%=securityUserVsRolesForm.getHspConfigBaseinfoName()%>"
							onkeyup="GiveOptions(event, '<%=request.getContextPath()%>/searchSuggest.do', 'getHspName_00000000004', 'hspConfigBaseinfoId')"
							onkeydown="huanhang(event)" />
					    <input type="hidden" name="hspConfigBaseinfoId" id="hspConfigBaseinfoId" value="<%=securityUserVsRolesForm.getHspConfigBaseinfoId()%>" />
					    
						
						<input type="button" class="btnSave"value="<bean:message key="security.jsp.securityConfigParamClass.query.button1" bundle="security"/>" name="btnsubmit" onclick="securityUserVsRoles_pageSearch()" />
					</td>
				</tr>
				<%
					if (securityUserVsRolesForm.getUserIds() != null) {
				 %>
				<tr>
					<td align="center" valign="top" bgcolor="#ffffff" width="60%">
						<div id="tjDrag1">
							<table id="tjTable1" border="0" cellpadding="0" cellspacing="0" class="tblSearchList">
								<tr class="lstName">
									<td align="center" width="20%">
										<bean:message key="security.jsp.commom.serialNo" bundle="security"/>
									</td>
									<td align="center" width="25%">
										<bean:message key="security.jsp.commom.name" bundle="security"/>
									</td>
									<td align="center" width="25%">
										用户名
									</td>
									<td align="center" width="30%">
										医疗机构
									</td>
								</tr>
								<%
									for (int i = 0; i < securityUserVsRolesForm.getUserIds().length; i++) {
								%>
								<tr class="left_yonghu" onclick="zcTableClick('<%=i + 1%>');securityUserVsRoles_tableClick('<%=i + 1%>', '<%=securityUserVsRolesForm.getUserIds()[i]%>', 'security/security_securityUserVsRoles.do')" onmouseover="zc_tableMouseOver('<%=i + 1%>')" bgcolor="#ffffff">
									<td>
										<%=i + 1%>
									</td>
									<td style="text-align:left;padding-left:10px;">
										<%=securityUserVsRolesForm.getUsers()[i].trim()%>
									</td>
									<td style="text-align:left;padding-left:10px;">
										<%=securityUserVsRolesForm.getUserNames()[i].trim()%>
									</td>
									<td style="text-align:left;padding-left:10px;">
										<%=securityUserVsRolesForm.getHspConfigs()[i].trim()%>
									</td>
								</tr>
								<%
										}
								}
								%>
							</table>
							<%
								if(securityUserVsRolesForm.getUserIds() != null){ 
							%>
							<table width="100%" align="center" class="tblScrollFooter">
								<tr>
									<td colspan="11" align="center" class="footer">
						 			<%
						 				int curPage = 0;
										int totalNum = 0;
										int pageSize = 0;
							
										curPage = pb.getPage();
										totalNum = pb.getCount();
										pageSize = pb.getPageSize();
							
										int totalPage = totalNum / pageSize;
										if (totalNum % pageSize > 0)
											totalPage += 1;
										if (totalPage == 0) {
											curPage = 0;
										}
									%>
										<input type="hidden" id="pageId" name="pageId" value="page_2828810b39763bf50139763bf5cf0000" />
										<input type="hidden" id="reHref" name="reHref" value="<%=request.getContextPath()%>/security/security_securityUserVsRoles.do?verbId=search" />
										<%@ include file="/include/changepagesize.jsp" %>
											
										<input id="_total" name="totalPage" type="hidden" value="<%=totalPage%>" />
										<bean:message key="security.jsp.commom.item2" bundle="security"/><%=curPage%><bean:message key="security.jsp.commom.item3" bundle="security"/><%=totalPage%><bean:message key="security.jsp.commom.item4" bundle="security"/><%=totalNum%><bean:message key="security.jsp.commom.item5" bundle="security"/>&nbsp;|&nbsp;
									<%
										if (curPage > 1) {
									%>
										<a href="javascript:goPage('0')"><img src="include/images/shouye.gif" align="middle" border="0" /></a>&nbsp; <a href="javascript:goPage('<%=curPage - 1 %>')"><img src="include/images/shang.gif" align="middle" border="0" /></a>&nbsp;
									<%
										} else {
											out.println("<img src='include/images/shouye_s.gif' align='middle' border='0px' />&nbsp;&nbsp;<img src='include/images/shang_s.gif' align='middle' border='0px' />&nbsp;");
										}
										if (curPage < totalPage) {
									%>
										<a href="javascript:goPage('<%=curPage + 1 %>')"><img src="include/images/xia.gif" align="middle" border="0" /></a>&nbsp; <a href="javascript:goPage('<%=totalPage %>')"><img src="include/images/mo.gif" align="middle" border="0" /></a>&nbsp;
									<%
										} else {
											out.println("<img src='include/images/xia_x.gif' align='middle' border='0px' />&nbsp;&nbsp;<img src='include/images/mo_m.gif' align='middle' border='0px' />&nbsp;");
										}
									%>
										| &nbsp;<bean:message key="jsp.pagetext3" bundle="conf.Init"/>
										<input id="_tp" name="page" type="text" value="<%=curPage%>" size="2" class="txt" />
										<bean:message key="jsp.pagetext4" bundle="conf.Init"/>
										&nbsp;
										<img style="cursor:hand;" src="include/images/go.jpg" width="18" border="0" onClick="goPage2()" />
									</td>
								</tr>
							</table>
							<%
							 	}
							 %>
						</div>
					</td>
					<%
						if (securityUserVsRolesForm.getRoleId() != null) {
					 %>
					<td align="center" bgcolor="#ffffff" width="40%">
						<div id="tjDrag2">
							<table id="tjTable2" border="0" cellpadding="0" cellspacing="0" class="tblSearchList">
								<tr class="lstName">
									<td align="center" width="12%">
										<bean:message key="security.jsp.commom.serialNo" bundle="security"/>
									</td>
									<td align="center" width="13%">
										<bean:message key="security.jsp.ssecurityuservsroles.SecurityUserVsRoles.item" bundle="security"/>
									</td>
									<td align="center" width="75%">
										<bean:message key="security.jsp.securityrolevsmenus.SecurityRoleVsMenus.roleId" bundle="security"/>
									</td>
								</tr>
								<%
									for (int i = 0; i < securityUserVsRolesForm.getRoleId().length; i++) {
								%>
								<tr class="left_yonghu bg_color_white" onclick="securityUserVsRoles_tableClickRoles('<%=i%>')">
									<td>
										<%=i + 1%>
									</td>
									<td>
										<input type="checkbox" name="checkBox" value="" onclick="securityUserVsRoles_tableClickRoles('<%=i%>')" />
										<input type="hidden" name="roleId" value="<%=securityUserVsRolesForm.getRoleId()[i]%>" />
									</td>
									<td id="neirong_neirong_td3" class="tdLeftTop">
										<%=securityUserVsRolesForm.getRoleDetail()[i]%>
									</td>
								</tr>
								<%
										}
								}
								%>
							</table>
						</div>
					</td>
				</tr>
			</table>
			<div class="btnSave">
				<%
				if (securityUserVsRolesForm.getRoleId() != null && securityUserVsRolesForm.getRoleId().length > 0 && securityUserVsRolesForm.getUserIds() != null && securityUserVsRolesForm.getUserIds().length > 0) {
				%>
					<input type="button"  name="<bean:message key="security.jsp.commom.button1" bundle="security"/>" value="<bean:message key="security.jsp.commom.button1" bundle="security"/>" onclick="securityUserVsRoles_pageSubmit('security/security_securityUserVsRoles.do')" />
				<%
				}
				%>
			</div>
		</form>
	</body>
</html>