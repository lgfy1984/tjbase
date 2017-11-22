<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<jsp:useBean id="commConfigLocation" scope="request" class="com.tianjian.comm.struts.form.CommConfigLocationForm" />
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
		<title><bean:message key="comm.jsp.different.text31" bundle="conf.comm.comm"/></title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<script language="javascript" src="<bean:message key="include.js.TJMessage.path" bundle="conf.comm.comm"/>"></script>
		<link rel="stylesheet" href="<%=request.getContextPath()%>/include/css/searchwindow.css" />
		<script type="text/javascript" src="<%=request.getContextPath()%>/include/javascript/searchwindow.js" defer="defer"></script>
		<script language="javascript" src="include/javascript/eventOnKeyPress.js"></script>
		<script language="javascript">
			function saveForm(){
				if(document.form.itemCode.value == ""){
				 	alertMessage("0-000001");
				 	return ;
				}
				
				if(document.form.itemName.value == ""){
				 	alertMessage("0-000002");
				 	return ;
				}
				
			if (confirmMessage("0-000003")){ 
				document.form.verbId.value = "add";
				document.form.submit();
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
						<!--处理市-->
			function setCity(url){
				var province = document.getElementById("commConfigLocationId1").value;
				var xmlHttp = newXMLHttpRequest();
				var sendTo = url + "?verbId=setCity&commConfigLocationId1=" + province;
				xmlHttp.open("GET", sendTo, true);
				var handlerFunction = getReadyStateHandler(xmlHttp, updateCity);
				xmlHttp.onreadystatechange = handlerFunction;
				xmlHttp.send(null);
			}
			<!--更新市-->
			function updateCity(cityXML) {
				var city = document.getElementById("commConfigLocationId2");
			    while (city.options.length) {
			        city.remove(0);
			    }
			    
				var indexObj = cityXML.getElementsByTagName("index")[0];
				var index = indexObj.childNodes[0].nodeValue;
				for (var i = 0; i < index; i++) {
					var keyObj = cityXML.getElementsByTagName("key")[i];
					var valueObj = cityXML.getElementsByTagName("value")[i];
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
					city.add(newElem);
				}
			}
			function xuanzhe(value){
		  	 if(value==null||value==""){
		  	 	return;
		  	 }
		  	 if(value=="1"){
		  	 	document.getElementById("shi1").style.display="block";
		  	 	document.getElementById("shi2").style.display="block";
		  	 	document.getElementById("levelFlag1").value="2";
		  	 }
		  	 if(value=="2"){
		  	 	document.getElementById("shi1").style.display="block";
		  	 	document.getElementById("shi2").style.display="block";
		  	 	document.getElementById("xian1").style.display="block";
		  	 	document.getElementById("xian2").style.display="block";
		  	 	document.getElementById("levelFlag1").value="3";
  	 		}
 		 }
 		 function addLocation(){
 		 	var locationId = document.getElementById("sdtoId").value;
 		 	if(locationId==null||locationId==""){
		  	 	return;
		  	 }
		  	if(locationId == "1"){
		  		var locationId1 = document.getElementById("commConfigLocationId1").value;
		  		var se = document.getElementById("commConfigLocationId1");
		  		var option=se.getElementsByTagName("option");   
		  		 var str = "" ;   
                 for(var i=0;i<option.length;++i){   
	                 if(option[i].selected){   
	                	str = option[i].text   
	                 }   
                 } 
                 document.getElementById("parentId").value = locationId1;
                 document.getElementById("parentName").value = str;
                 document.getElementById("levelFlag").value = "2";
                 searchclosed();
		  	}
		  	if(locationId=="2"){
		  		var locationId1 = document.getElementById("commConfigLocationId2").value;
		  		var se = document.getElementById("commConfigLocationId2");
		  		var option=se.getElementsByTagName("option");   
		  		 var str = "" ;   
                 for(var i=0;i<option.length;++i){   
	                 if(option[i].selected){   
	                	str = option[i].text   
	                 }   
                 } 
                 document.getElementById("parentId").value = locationId1;
                 document.getElementById("parentName").value = str;
                 document.getElementById("levelFlag").value = "3";
                 searchclosed();
		  	}
 		 }
		</script>
		<link type="text/css" rev="stylesheet" rel="stylesheet" href="include/css/form.css" />
	</head>
	<body onload="showMessage('','<%=commConfigLocation.getMessage()%>','1')">
		<form name="form" method="post" action="comm/commConfigLocation.do">
			<input type="hidden" name="verbId" value="add" />
						<table align="center" border="0" cellpadding="0" cellspacing="0" class="tblFill" border="0" cellpadding="0" cellspacing="0" class="tblFill">
							<tr>
								<td class="tblTitle" colspan="4"><span>※</span><bean:message key="comm.jsp.different.text31" bundle="conf.comm.comm"/><span>※</span></td>
							</tr>
							<tr>
								<td class="tblLable">
									 <bean:message key="comm.jsp.common.text39" bundle="conf.comm.comm"/>：
								</td>
								<td>
									<input type="hidden" id="parentId" name="parentId" />
									<input type="text" id="parentName" name="parentName" style="width: 225px;"/>
									<img src="<%=request.getContextPath() %>/hsp/include/images/select.gif" style="cursor:pointer;" 
										onClick="change(),searchMore('');"  />
								</td>
								<td class="tblLable">
									<span>*</span> <bean:message key="comm.jsp.common.text16" bundle="conf.comm.comm"/>：
								</td>
								<td>
									<input type="text" name="levelFlag" id="levelFlag" size="20" maxlength="9" onkeypress="eventOnKeyPress('itemCode')" value="1" />
								</td>
							</tr>
							<tr>
								<td class="tblLable">
									<span>*</span> <bean:message key="comm.jsp.common.text15" bundle="conf.comm.comm"/>：
								</td>
								<td>
									<input type="text"  name="itemCode" size="20" maxlength="9" onkeypress="eventOnKeyPress('itemName')" value="<%=commConfigLocation.getItemCode()%>" />
								</td>
								<td class="tblLable">
									<span>*</span> <bean:message key="comm.jsp.common.text17" bundle="conf.comm.comm"/>：
								</td>
								<td>
									<input type="text" name="itemName" size="50" maxlength="50" onkeypress="eventOnKeyPress('seqNo')" value="<%=commConfigLocation.getItemName()%>" />
							</tr>
							<tr>
								<td class="tblLable">
									<bean:message key="comm.jsp.common.text48" bundle="conf.comm.comm"/>：
								</td>
								<td>
									<input type="text" name="seqNo" id="seqNo" size="30" maxlength="20" onkeypress="eventOnKeyPress('comments')" value="<%=commConfigLocation.getSeqNo()%>" />
								</td>
								<td class="tblLable">
									<bean:message key="comm.jsp.different.text65" bundle="conf.comm.comm"/>：
								</td>
								<td>
									<input type="text" name="comments" id="comments" size="30" maxlength="20" onkeypress="eventOnKeyPress('btnSaveForm')" value="<%=commConfigLocation.getComments()%>" />
								</td>
							</tr>
						</table>
						<div id="searchDiv" style="display:none;">
							<table border="0" width="650" cellspacing="0" cellpadding="0" id="maintable"
								align="center"
								style="font-size: 12px; margin: 0px 0px 1px 0px; text-align: center; border: 1px solid #BFBFBF;">
								<tr>
									<td height="25" colspan="4" class="trbg">
										选择所属区域
									</td>
								</tr>
								<tr class="trbg">
									<td   height="25">
										所属区域类型:
									</td>
									<td height="25">
										<select name="sdtoId" id="sdtoId" onkeypress="huiche()" style="width:135px" onchange="xuanzhe(this.value)" >
											<option value=""></option>
											<option value="1">省级</option>
											<option value="2">市级</option>
										</select>
									</td>
									<td align="center"  height="25">
										级别:
									</td>
									<td  align="left" height="25">
										<input type="text" name="levelFlag1" id="levelFlag1" onkeypress="huiche()" style="width:135px" />
									</td>
								</tr>
								<tr class="trbg">
									<td align="center"  height="25">
										<div id="shi1" style="display:none;" >
											<bean:message key="security.jsp.commom.commConfigLocationId1" bundle="security"/>：
										</div>
									</td>
									<td align="center"  height="25">
										<div id="shi2" style="display:none;">
											<select name="commConfigLocationId1" id="commConfigLocationId1" size="135px" onkeypress="huiche()" onchange="setCity('empi/securityUserBaseinfo.do')" > 
									            <%
												if(commConfigLocation.getCommConfigLocationId1s() != null && commConfigLocation.getCommConfigLocationId1s().length > 0){
													for(int i = 0; i < commConfigLocation.getCommConfigLocationId1s().length; i++){
														String tempId = commConfigLocation.getCommConfigLocationId1s()[i];
														String tempName = commConfigLocation.getCommConfigLocationId1_names()[i];
												%>
									        	<option value="<%=tempId %>" <%=tempId.equals(commConfigLocation.getCommConfigLocationId1()) ? "selected" : "" %>>
													<%=tempName %>
												</option>
												<%
													}
												}
												%>  
					    					</select>          
										</div>
									</td>
									<td align="center"  height="25">
										<div id="xian1" style="display:none;">
							            	<bean:message key="security.jsp.commom.commConfigLocationId2" bundle="security"/>：
							            </div>
									</td>
									<td align="center"  height="25">
										<div id="xian2" style="display:none;">
								            <select class="kuandu" name="commConfigLocationId2" size="135px" id="commConfigLocationId2" onkeypress="huiche()" onchange="" > 
									            <%
												if(commConfigLocation.getCommConfigLocationId2s() != null && commConfigLocation.getCommConfigLocationId2s().length > 0){
													for(int i = 0; i < commConfigLocation.getCommConfigLocationId2s().length; i++){
														String tempId = commConfigLocation.getCommConfigLocationId2s()[i];
														String tempName = commConfigLocation.getCommConfigLocationId2_names()[i];
												%>
									        	<option value="<%=tempId %>" <%=tempId.equals(commConfigLocation.getCommConfigLocationId2()) ? "selected" : "" %>>
													<%=tempName %>
												</option>
												<%
													}
												}
												%>  
								    		</select>        	
										</div>
									</td>
								</tr>
							</table>
							<table border="0" width="650" cellspacing="0" cellpadding="0" 
								align="center"
								style="font-size: 12px; margin: 0px 0px 1px 0px; text-align: center; border: 1px solid #BFBFBF;">
								<tr class="trbg">
									<td height="35" colspan="4" align="center"
										style="background-color: #F7F7F7">
										<input type="button"
											style="cursor: hand; background: url(include/images/tong.gif) no-repeat; border: 0; width: 50px; height: 23px; font-size: 12px;"
											name="选择" value="选&nbsp;&nbsp;&nbsp;择"
											onclick="addLocation();" />
										<input type="button"
											style="cursor: hand; background: url(include/images/tong.gif) no-repeat; border: 0; width: 50px; height: 23px; font-size: 12px;"
											name="关闭" value="关&nbsp;&nbsp;&nbsp;闭" onClick="searchclosed();" />
									</td>
								</tr>
							</table>
						</div>
			<!-- Sheet operation button area -->
			<div class="btnSave">
				<input type="button"  name="btnSaveForm" value="<bean:message key="comm.jsp.common.text3" bundle="conf.comm.comm"/>"  onClick="saveForm()" />
				<input type="button"  name="btnBack" value="<bean:message key="comm.jsp.common.text19" bundle="conf.comm.comm"/>"  onClick="history.go(-1);" />
			</div>
		</form>
	</body>
</html>
