<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="html" uri="/WEB-INF/struts-html.tld"%>
<%@ taglib prefix="bean" uri="/WEB-INF/struts-bean.tld"%>
<%@ taglib prefix="logic" uri="/WEB-INF/struts-logic.tld"%>
<jsp:useBean id="data" scope="request" class="com.tianjian.comm.struts.form.CommConfigLocationVillageForm" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%if (request.getServerPort() == 80) {%>
		<base href="http://<%=request.getServerName()%><%=request.getContextPath()%>/" />
		<%} else {%>
		<base href="http://<%=request.getServerName()%>:<%=request.getServerPort()%><%=request.getContextPath()%>/" />
		<%}%>
		<title><bean:message key="comm.jsp.common.czd" bundle="conf.comm.Comm"/>add.jsp</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
		<meta http-equiv="pragma" content="no-cache"/>
		<meta http-equiv="cache-control" content="no-cache"/>
		<meta http-equiv="expires" content="0"/>
		<script language="javascript" src="<bean:message key="comm.js.comm.message" bundle="conf.comm.Comm"/>"></script>
		<script language="javascript" src="<bean:message key="includes.js.validator.path" bundle="security" />" defer="defer"></script>
		<script language="javascript" src="include/javascript/eventOnKeyPress.js"></script>
		<script language="javascript" src="include/javascript/utrim.js"></script>
		<script language="javascript">
			function saveForm(){
			    if(!Validator.Validate(document.forms.form,3)){
				      return ;
				   }
				if(document.form.commProvinceId.value == ""){
				 	alertCommMessage("<bean:message key="comm.jsp.common.selectProvince" bundle="conf.comm.Comm"/>");
				 	return ;
				}
				if(document.form.commCityId.value == ""){
				 	alertCommMessage("<bean:message key="comm.jsp.common.selectCity" bundle="conf.comm.Comm"/>");
				 	return ;
				}
				if(document.form.commCountyId.value == ""){
				 	alertCommMessage("<bean:message key="comm.jsp.common.selectTown" bundle="conf.comm.Comm"/>");
				 	return ;
				}
				if(document.form.commCltId.value == ""){
				 	alertCommMessage("<bean:message key="comm.jsp.common.alert14" bundle="conf.comm.Comm"/>");
				 	return ;
				}
				if(document.form.seqNo.value == ""){
				 	alertCommMessage("<bean:message key="comm.jsp.common.notnullsqlno" bundle="conf.comm.Comm"/>");
				 	return ;
				}
				if(isNaN(document.form.seqNo.value)){
			       alert("<bean:message key="comm.jsp.common.alert1" bundle="conf.comm.Comm"/>"); 
			       return true;
			    }
				   var num=document.form.villagerNum.value;
				   num=num.replace(/[ ]/g,"");
				   var familyNum=document.form.familyNum.value ;
				if(num!=""){
					if(!dataViladate(num)){
				  		alert(" <bean:message key="comm.jsp.common.alert4" bundle="conf.comm.Comm"/>！");
				  		return;
					}
				
					if(!dataViladate(familyNum)){
				    	alertCommMessage(" <bean:message key="comm.jsp.common.alert3" bundle="conf.comm.Comm"/>！");
				     	return;
				    }
				}
			    if(document.form.itemCode.value == ""){
				 	alertCommMessage("<bean:message key="comm.jsp.common.writeVillageName" bundle="conf.comm.Comm"/>");
				 	return ;
				}
				if(isNaN(document.form.itemCode.value)){
			       alert("<bean:message key="comm.jsp.common.alert6" bundle="conf.comm.Comm"/>"); 
			       return true;
			    }
			    if(document.form.itemCode.value.length !=10){
				 	alert("<bean:message key="comm.jsp.common.alert5" bundle="conf.comm.Comm"/>");
				 	return ;
				}
				if(document.form.itemName.value == ""){
				 	alertCommMessage("<bean:message key="comm.jsp.common.writeVillageName" bundle="conf.comm.Comm"/>");
				 	return ;
				}
				 
				document.form.verbId.value = "add";
				document.form.submit();
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
			function setCity(url){
				var province = document.getElementById("commProvinceId").value;
				var xmlHttp = newXMLHttpRequest();
				var sendTo = url + "?verbId=setCity&province=" + province;
				//alert(sendTo);
				xmlHttp.open("GET", sendTo, true);
				var handlerFunction = getReadyStateHandler(xmlHttp, updateCity);
				xmlHttp.onreadystatechange = handlerFunction;
				xmlHttp.send(null);
			}
			function setCounty(url){
				var city = document.getElementById("commCityId").value;
				var xmlHttp = newXMLHttpRequest();
				var sendTo = url + "?verbId=setCounty&city=" + city;
				xmlHttp.open("GET", sendTo, true);
				var handlerFunction = getReadyStateHandler(xmlHttp, updateCounty);
				xmlHttp.onreadystatechange = handlerFunction;
				xmlHttp.send(null);
			}
			function setTown(url){
				var town = document.getElementById("commCountyId").value;
				var xmlHttp = newXMLHttpRequest();
				var sendTo = url + "?verbId=setTown&town=" + town;
				//alert(sendTo);
				xmlHttp.open("GET", sendTo, true);
				var handlerFunction = getReadyStateHandler(xmlHttp, updateTown);
				xmlHttp.onreadystatechange = handlerFunction;
				xmlHttp.send(null);
			}
			function updateCity(cityXML) {
				var city = document.getElementById("commCityId");
			    while (city.options.length) {
			        city.remove(0);
			    }
			    
			    var country = document.getElementById("commCountyId");
			    while (country.options.length) {
			        country.remove(0);
			    }
			    
			    var clt = document.getElementById("commCltId");
			    while (clt.options.length) {
			        clt.remove(0);
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
			function updateCounty(countyXML) {
				var county = document.getElementById("commCountyId");
				while (county.options.length) {
			        county.remove(0);
			    }
			    
			    var clt = document.getElementById("commCltId");
			    while (clt.options.length) {
			        clt.remove(0);
			    }
			    
				var indexObj = countyXML.getElementsByTagName("index")[0];
				var index = indexObj.childNodes[0].nodeValue;
				for (var i = 0; i < index; i++) {
					var keyObj = countyXML.getElementsByTagName("key")[i];
					var valueObj = countyXML.getElementsByTagName("value")[i];
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
					county.add(newElem);
				}
			}			
			function updateTown(countyXML) {
			    var clt = document.getElementById("commCltId");
			    while (clt.options.length) {
			       clt.remove(0);
			    }
			    
				var indexObj = countyXML.getElementsByTagName("index")[0];
				var index = indexObj.childNodes[0].nodeValue;
				for (var i = 0; i < index; i++) {
					var keyObj = countyXML.getElementsByTagName("key")[i];
					var valueObj = countyXML.getElementsByTagName("value")[i];
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
					clt.add(newElem);
				}
			}
			function updateItemCode(countyXML){
				var code=countyXML.getElementsByTagName("country")[0].childNodes[0].nodeValue;
				var itemCode=countyXML.getElementsByTagName("itemCode")[0].childNodes[0].nodeValue;

				//document.getElementById("sasa").innerHTML = code;
				//document.all('cmi').value=cmi;
				document.form.itemCode.value = itemCode;
			}
			function setItemCode(){
				var country=document.getElementById("commCltId").value;
				var xmlHttp = newXMLHttpRequest();
				var url = "<%=request.getContextPath()%>/comm/commConfigLocationVillage.do?verbId=setItemCode&country="+country;
				xmlHttp.open("GET", url, true);
				var handlerFunction = getReadyStateHandler(xmlHttp, updateItemCode);
				xmlHttp.onreadystatechange = handlerFunction;
				xmlHttp.send(null);
			}
		</script>
		<link rel="stylesheet" rev="stylesheet" href="comm/include/css/comm_add.css" />
	</head>
	
	<body onload="showCommMessage('','<%=data.getMessage()%>','1');document.form.commProvinceId.focus();">
		<form name="form" method="post" action="comm/commConfigLocationVillage.do">&nbsp; 
			 <input type="hidden" name="verbId" value="add" />
			<input type="hidden" name="id" value="<%=data.getId()%>"/>
			<table border="0" cellspacing="1" cellpadding="0" class="table" align="center">
				<tr>
					<td height="30px" class="biaoti" colspan="4">
						<font color="red">※</font>&nbsp;&nbsp;<bean:message key="comm.jsp.common.czd" bundle="conf.comm.Comm"/>&nbsp;&nbsp;
						<font color="red">※</font>
					</td>
				</tr>				
				<tr>
					<td class="qian">
						<font color="red">*</font> <bean:message key="comm.jsp.common.province" bundle="conf.comm.Comm"/>：
					</td>
					<td class="hou">
						<select  name="commProvinceId"
							id="commProvinceId"
							onkeypress="eventOnKeyPress('commCityId')"
							onchange="setCity('comm/commConfigLocationVillage.do')">
							<%
								if (data.getCommProvinceIds() != null && data.getCommProvinceIds().length > 0) {
									for (int i = 0; i < data.getCommProvinceIds().length; i++) {
										String tempId = data.getCommProvinceIds()[i];
										String tempName = data.getCommProvinceNames()[i];
							%>
							<option value="<%=tempId%>"
								<%=tempId.equals(data.getCommProvinceId()) ? "selected"
									: ""%>>
								<%=tempName%>
							</option>
							<%
									}
								}
							%>
						</select>
					</td>
					<td class="qian">
						<font color="red">*</font> <bean:message key="comm.jsp.common.city" bundle="conf.comm.Comm"/>：
					</td>
					<td class="hou">
						<select  name="commCityId"
							id="commCityId"
							onkeypress="eventOnKeyPress('commCountyId')"
							onchange="setCounty('comm/commConfigLocationVillage.do')">
							<%
								if (data.getCommCityIds() != null && data.getCommCityIds().length > 0) {
									for (int i = 0; i < data.getCommCityIds().length; i++) {
										String tempId = data.getCommCityIds()[i];
										String tempName = data.getCommCityNames()[i];
							%>
							<option value="<%=tempId%>"
								<%=tempId.equals(data
											.getCommCityId()) ? "selected"
									: ""%>>
								<%=tempName%>
							</option>
							<%
									}
								}
							%>
						</select>
					</td>
				</tr>
				<tr>
					<td class="qian">
						<font color="red">*</font> <bean:message key="comm.jsp.common.town" bundle="conf.comm.Comm"/>：
					</td>
					<td class="hou">
						<select  name="commCountyId"
							id="commCountyId"
							onkeypress="eventOnKeyPress('commCltId')"
							onchange="setTown('comm/commConfigLocationVillage.do')">
							<%
								if (data.getCommCountyIds() != null && data.getCommCountyIds().length > 0) {
									for (int i = 0; i < data.getCommCountyIds().length; i++) {
										String tempId = data.getCommCountyIds()[i];
										String tempName = data.getCommCountyNames()[i];
							%>
							<option value="<%=tempId%>"
								<%=tempId.equals(data
											.getCommCountyId()) ? "selected"
									: ""%>>
								<%=tempName%>
							</option>
							<%
									}
								}
							%>
						</select>
					</td>
					<td class="qian">
						<font color="red">*</font> <bean:message key="comm.jsp.common.tv" bundle="conf.comm.Comm"/>：
					</td>
					<td class="hou">
						<select  name="commCltId"
							id="commCltId"
							onkeypress="eventOnKeyPress('seqNo')" onchange="setItemCode()">
							<%
								if (data.getCommCltIds() != null && data.getCommCltIds().length > 0) {
									for (int i = 0; i < data.getCommCltIds().length; i++) {
										String tempId = data.getCommCltIds()[i];
										String tempName = data.getCommCltNames()[i];
							%>
							<option value="<%=tempId%>"
								<%=tempId.equals(data.getCommCltId()) ? "selected"
									: ""%>>
								<%=tempName%>
							</option>
							<%
									}
								}
							%>
						</select>
					</td>
				</tr>
				<tr>
					<td class="qian">
						<font color="red">*</font> <bean:message key="comm.jsp.common.seqNo" bundle="conf.comm.Comm"/>：
					</td>
					<td class="hou">
						<input type="text" class="kuandu" name="seqNo" size="20"
							maxlength="9" onkeypress="eventOnKeyPress('itemCode')"  max="11" dataType="LimitB" msg="序号输入过长"
							value="<%=data.getSeqNo()%>" />
					</td>
					<td class="qian">
						<font color="red">*</font> <bean:message key="comm.jsp.common.item" bundle="conf.comm.Comm"/>：
					</td>
					<td class="hou">
						<input type="text" class="kuandu" name="itemCode" size="20"
							maxlength="10" onkeypress="eventOnKeyPress('villagerNum')"   max="32" dataType="LimitB" msg="代码输入过长"
							value="<%=data.getItemCode()%>" />
					</td>
				</tr>
				<tr>					
					<td class="qian">
						<font color="red">*</font> <bean:message key="comm.jsp.common.names" bundle="conf.comm.Comm"/>：
					</td>
					<td class="hou">
						<input type="text" class="kuandu" name="itemName" size="20"
							maxlength="9"
							onkeypress="eventOnKeyPress('contactPersonName')"  max="100" dataType="LimitB" msg="名称输入过长"
							value="<%=data.getItemName()%>" />
					</td>
					<td class="qian">
					</td>
					<td class="hou">
					</td>
				</tr>
				
				<tr>
					<td class="qian">&nbsp; <bean:message key="comm.jsp.common.totalPerson" bundle="conf.comm.Comm"/>： 
					</td>
					<td class="hou"> 
					<input type="text" name="villagerNum" class="kuandu" size="20"  max="8" dataType="LimitB" msg="总人数输入过长"
					onkeypress="eventOnKeyPress('familyNum')"  value="<%=data.getVillagerNum() %>" />
					</td>
					<td class="qian">&nbsp; <bean:message key="comm.jsp.common.totalFamily" bundle="conf.comm.Comm"/>： 
					</td>
					<td class="hou"> 
					<input type="text" name="familyNum" class="kuandu" size="20"   max="8" dataType="LimitB" msg="总家庭数输入过长"
					onkeypress="eventOnKeyPress('itemName')"  value="<%=data.getFamilyNum()%>" />
					</td>
				</tr>
				 
				<tr>
					<td class="qian">&nbsp; <bean:message key="comm.jsp.common.contactPersonName" bundle="conf.comm.Comm"/>： 
					</td>
					<td class="hou"> 
					<input type="text" name="contactPersonName" class="kuandu" size="20"  max="40" dataType="LimitB" msg="村联系人输入过长"
					onkeypress="eventOnKeyPress('phohe')"  value="<%=data.getContactPersonName()%>" />
					</td>
					<td class="qian">&nbsp; <bean:message key="comm.jsp.common.phone" bundle="conf.comm.Comm"/>： 
					</td>
					<td class="hou"> 
					<input type="text" name="phohe" size="20" class="kuandu"   max="32" dataType="LimitB" msg="联系电话输入过长"
					onkeypress="eventOnKeyPress('comments')" value="<%=data.getPhohe()%>" />
					</td>
				</tr>
				<tr>
					<td class="qian">
						<bean:message key="comm.jsp.common.comments" bundle="conf.comm.Comm"/>：
					</td>
					<td class="hou">
						<input type="text" class="kuandu" name="comments" id="comments"  max="40" dataType="LimitB" msg="备注输入过长"
							size="30" maxlength="20" value="<%=data.getComments()%>" 
							onkeypress="eventOnKeyPress('btnSaveForm')"/>

					</td>
					<td class="qian">
					</td>
					<td class="hou">
					</td>
			</table>

			<!-- Sheet operation button area -->
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td height="30" align="center">
						<input type="button"
							name="btnSaveForm" value="<bean:message key="comm.jsp.common.save" bundle="conf.comm.Comm"/>" onclick="saveForm()" />
						<input type="button"
							name="btnBack" value="<bean:message key="comm.jsp.common.return" bundle="conf.comm.Comm"/>" onclick="history.go(-1);" />
					</td>
				</tr>
			</table>
		</form>

	</body>
</html>
