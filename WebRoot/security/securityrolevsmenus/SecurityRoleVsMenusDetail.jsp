<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<jsp:useBean id="securityRoleVsMenus" scope="request" class="com.tianjian.security.struts.form.SecurityRoleVsMenusForm" />
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
		<title><bean:message key="security.jsp.securityrolevsmenus.SecurityRoleVsMenus.title" bundle="security"/></title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<meta http-equiv="pragma" content="no-cache" />
		<meta http-equiv="cache-control" content="no-cache" />
		<meta http-equiv="expires" content="0" />
		<link rel="stylesheet" rev="stylesheet" href="include/css/form.css" />
		<script language="javascript" src="<bean:message key="include.js.treeView.MzTreeView10_addradio_rmdaohang.path" bundle="security"/>"></script>
		<script language="javascript" src="<bean:message key="security.js.SecurityMessage.path" bundle="security" />"></script>
		<script language="javascript">
	function securityRoleVsMenus_pageSubmit(url){
		var roleIdSelected = parent.document.form.roleId.value;	
		var modIdSelected = parent.document.form.modId.value;
		var totalRows = document.form.totalRows.value;
		var temp = "";
		if(roleIdSelected == ""){
			alertSecurityMessage("10002");
			return;
		}
		
		if(parseInt(totalRows) < 1){
			alertSecurityMessage("10003");
			return;
		}
		
		if(totalRows == "1"){
			if(document.form.menuIdSelected.checked == true){
				temp = temp + "&menuIdSelected=" + document.form.menuIdSelected.value;
			}
		} else {
			for(var i = 0; i < document.form.menuIdSelected.length; i++){
				if(document.form.menuIdSelected[i].checked == true){
					temp = temp + "&menuIdSelected=" + document.form.menuIdSelected[i].value;
				}
			}		
		}
		
		var xmlHttp = newXMLHttpRequest();
		var paramStr = "verbId=execute&roleIdSelected=" + roleIdSelected + "&modIdSelected=" + modIdSelected + temp;
		xmlHttp.open("POST", url, true);
		xmlHttp.setRequestHeader("Content-Type","application/x-www-form-urlencoded"); 
		var securityRoleVsMenus_handlerFunction = getReadyStateHandler(xmlHttp, securityRoleVsMenus_updateMenu);
		xmlHttp.onreadystatechange = securityRoleVsMenus_handlerFunction;
		xmlHttp.send(paramStr);
			
	}	
	
	function securityRoleVsMenus_updateMenu(menuXML) {
		var messageXML = menuXML.getElementsByTagName("message")[0];
		if(messageXML.childNodes[0] != null && messageXML.childNodes[0].nodeValue != null){
			message = messageXML.childNodes[0].nodeValue;
			alert(message);
		}
	}
	
	function publicVsRoles_tableClickRoles(row){
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
				    alert("HTTP error: " + req.status);
	      		}
	    	}
	  	}
	}
	
	
	function setMod(url){
		var modClassId = document.getElementById("modClassId").value;
		var xmlHttp = newXMLHttpRequest();
		var sendTo = url + "?verbId=setMod&modClassId=" + modClassId;
		// alert(sendTo); 
		xmlHttp.open("GET", sendTo, true);
		var handlerFunction = getReadyStateHandler(xmlHttp, updateMod);
		xmlHttp.onreadystatechange = handlerFunction;
		xmlHttp.send(null);
	}
	
	function updateMod(modXML) {
		var mod = document.getElementById("modId");
	    while (mod.options.length) {
	        mod.remove(0);
	    }
		var indexObj = modXML.getElementsByTagName("index")[0];
		var index = indexObj.childNodes[0].nodeValue;
		for (var i = 0; i < index; i++) {
			var keyObj = modXML.getElementsByTagName("key")[i];
			var valueObj = modXML.getElementsByTagName("value")[i];		
			var newElem = document.createElement("option");
			if(valueObj.childNodes[0] == null){
				newElem.text = "" ;
			} else {
				newElem.text = valueObj.childNodes[0].nodeValue ;
			}
			if(keyObj.childNodes[0] == null){
				newElem.value = "" ;
			} else {
				newElem.value = keyObj.childNodes[0].nodeValue ;
			}
			mod.add(newElem);
		}
	}



</script>
	</head>
	<body >	
	<form name="form" method="post" action="security/security_securityRoleVsMenus.do">
	<table>
	<%
					int totalShows = 0;
					if (securityRoleVsMenus.getMenuId() != null && securityRoleVsMenus.getMenuId().length > 0) {
				%>
				<script language="javascript">
						var tree = new MzTreeView("tree");
						tree.setIconPath("include/javascript/treeView/");
					<%	
						
						for(int i = 0; i < securityRoleVsMenus.getMenuId().length; i++){
							String menuId = securityRoleVsMenus.getMenuId()[i];
							String parentMenuId = securityRoleVsMenus.getParentMenuId()[i];
							String menuDetail = securityRoleVsMenus.getMenuDetail()[i];
							String endLevelFlag = securityRoleVsMenus.getEndLevelFlag()[i];	
							String temp = "ctrl:true;ctrlName:menuIdSelected;ctrlValue:" + menuId + ";ctrlChecked:" ;
							String isSelected = "1";
							if(securityRoleVsMenus.getSelectedMenuId() != null && securityRoleVsMenus.getSelectedMenuId().length > 0){
								for(int j = 0; j < securityRoleVsMenus.getSelectedMenuId().length; j++){
									if(securityRoleVsMenus.getSelectedMenuId()[j].equals(menuId)){
										isSelected = "0";
									}
								}
							}
							if(isSelected.equals("0") && !parentMenuId.equals("-1")){
								temp += "true;";
							} else {
								temp += "false;";
							}
							//temp = "";
							temp += "text:" + menuDetail + ";";
							
							System.out.println(parentMenuId + "_" + menuId + "=" + temp);
						
							totalShows = totalShows + 1;
					%>   	
							tree.nodes["<%=parentMenuId%>_<%=menuId%>"] = "<%=temp%>";
					<%	        
							        
						}
					%>
						tree.wordLine = false;
						document.write(tree.toString()); 
						tree.expandAll();

				</script>
				<%
				}
				%>
				<tr>
					<td><input type="hidden" name="totalRows" value="<%=totalShows%>" /></td>
				</tr>
				
			</table>	
			
		</form>
	</body>
</html>