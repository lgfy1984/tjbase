<%@ page contentType="text/html; charset=UTF-8"%>
<%@page import="com.tianjian.comm.bean.CommDictPublicChar"%>
<%@page import="com.tianjian.comm.bean.CommConfigUnitgrade"%>
<%@page import="com.tianjian.comm.bean.CommConfigUnittype"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<jsp:useBean id="data" scope="request" class="com.tianjian.hsp.struts.form.HspConfigBaseinfoForm" />
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
		<title><bean:message bundle="hsp.hspLocale" key="hsp.jsp.hspconfigbaseinfoadd.addItemInfo" />
		</title>
		<meta http-equiv="x-ua-compatible" content="ie=8" />
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<meta http-equiv="pragma" content="no-cache" />
		<meta http-equiv="cache-control" content="no-cache" />
		<meta http-equiv="expires" content="0" />
		<script language="javascript"
			src="<%=request.getContextPath()%><bean:message  key="hsp.js.commCommMessage.path"  bundle="hsp.hspLocale"/>"
			defer="defer"></script>
		<script language="javascript" src="<%=request.getContextPath()%>/include/javascript/eventOnKeyPress.js" defer="defer"></script>
		<script language="javascript" src="<%=request.getContextPath()%>/include/javascript/utrim.js" defer="defer"></script>
		<script language="javascript"
			src="<%=request.getContextPath()%><bean:message  key="hsp.js.includevalidator.path"  bundle="hsp.hspLocale"/>"
			defer="defer"></script>
		<script type="text/javascript"
			src="<%=request.getContextPath()%><bean:message  key="hsp.js.checkbox.path"  bundle="hsp.hspLocale"/>" defer="defer"></script>
		<script language="javascript"
			src="<%=request.getContextPath()%><bean:message  key="hsp.js.includegettext_staff.path"  bundle="hsp.hspLocale"/>"
			defer="defer"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/include/javascript/jianbian.js" defer="defer"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/include/javascript/jquery-1.4.4.min.js"
			defer="defer"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/include/javascript/cookie.js"
			defer="defer"></script>
		<link type="text/css" rev="stylesheet" rel="stylesheet" href="include/css/form.css" />
		<link rel="stylesheet" href="<%=request.getContextPath()%>/include/css/open.css" />

		<script language="javascript">
		var xmlrequest;
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
	
			function check(){
					createXMLHttpRequest();
					var itmCode = document.getElementById("itemCode").value;
					var uri = "<%=request.getContextPath()%>/hsp/hspConfigBaseinfo.do?verbId=checkItemCode&itemCode="+itmCode+"";
					xmlrequest.open("GET", uri, true);
					xmlrequest.onreadystatechange = processResponse;
					xmlrequest.send(null);
				}
			function saveForm(){
				if(!Validator.Validate(document.forms.form,3)){
       				return;
   				}
				if(document.form.itemName.value == ""){
				 	alert("<bean:message bundle="hsp.hspLocale" key="hsp.jsp.hspconfigbaseinfoupdate.warn2" />!");
				 	return;
				}
				if(document.form.itemCode.value == ""){
				 	alert("<bean:message bundle="hsp.hspLocale" key="hsp.jsp.common.warn20" />!");
				 	return;
				}			
				if(document.form.commConfigHospitalTypeId.value == ""){
				 	alert("<bean:message bundle="hsp.hspLocale" key="hsp.jsp.common.warn21" />!");
				 	return;
				}
				if(!CheckInputWord("1")) {
				   return;
				}
				if (!confirm("<bean:message key='security.jsp.commom.save' bundle='security'/>")){
					return;
				}
				<%
					if("1".equals(request.getParameter("useForTree"))){
				%>
				document.form.verbId.value = "add";
			    var formData = $("#form").serialize();
			    $.ajax({
			    	dataType: "text/html",
			    	type:"POST",
			    	url:"<%=request.getContextPath()%>/hsp/hspConfigBaseinfo.do",
			    	processData:true,
			    	data:formData,
			    	error: function(a, b, c){
		        		alert(a + "-" + b + "-" + c);
		        	},
			    	success:function(data){
			    		if(data != null){
			    			try{
			    				var json = eval(data);
			    			}catch(e){
			    				alert("请重新登录！");
			    				return;
			    			}
			    			if(json[0].flag == '1'){
			      				var parentItemCode = $("input[name='parentItemCode']").val();
			      				if(parentItemCode != null && parentItemCode.length > 0){
			      					parent.frames["treeFrame"].refreshByIdAndType("hsp", null, parentItemCode);
			      				}
			      				alert(json[0].msg);
			    				location.reload(false);	
			    			}else{
			    				alert(json[0].msg);
			    			}
			    		}
			    	}
			    });	
				<%
					}else{
				%>
				document.form.verbId.value = "add";
				document.form.submit();
				<%
					}
				%>
			}
			function checkNum(s,name){
				if(!isInteger(s)){
					alert(name+"<bean:message bundle="hsp.hspLocale" key="hsp.jsp.common.warn26" />!");
					return false;
				}else{
					return true;	
				}
			}
			function cerInfo(){
			    var isGetCer = document.form.isGetCer.value;
				if(isGetCer=="1"){
				   document.getElementById("cerInfoDiv").style.display=""
				}else{
				   document.getElementById("cerInfoDiv").style.display="none";
				}
			}			
			function newXMLHttpRequest() {
				var xmlreq = false;
				if(window.XMLHttpRequest){
					xmlreq = new XMLHttpRequest();		
				}else if(window.ActiveXObject){
				    try{
			      		xmlreq = new ActiveXObject("Msxml2.XMLHTTP");
					}catch(e1){
						try{
			        		xmlreq = new ActiveXObject("Microsoft.XMLHTTP");
						}catch(e2){
							xmlreq = false;
				    	}
			    	}
				}	
				return xmlreq;
			}
	        function goBack(url){
					window.location=url;
			}

			function processResponse(){
				if (xmlrequest.readyState == 4){
					if (xmlrequest.status == 200){
						if(xmlrequest.responseText.length>0){
							alert("<bean:message bundle="hsp.hspLocale" key="hsp.jsp.common.warn35" />!");
							return ;
						}else{
							saveForm();
						}
					}else{
						window.alert("<bean:message bundle="hsp.hspLocale" key="hsp.jsp.common.warn36" />!");
						return;
					}
				}
			}		
			function getReadyStateHandler(req, responseXmlHandler) {
				return function () {
					if(req.readyState == 4){
			      		if (req.status == 200){
			      			//alert(req.responseText); 
						    responseXmlHandler(req.responseXML);
						}else{
						    alert("HTTP error: " + req.status);
			      		}
			    	}
			  	}
			}
			function huiche(){
				if(event.keyCode==13){
					event.keyCode=9
				}				
			} 		   
		   	var smsLength = parseInt('40');		  
		   	function CheckInputWord(needAlert){
		   		//检查字数
		    	var word1 = document.getElementById("comments").value;
		    	var word = word1.replace(/(^\s*)|(\s*$)/g,"");
		    	var num = word.length;
		    	if(num<=smsLength){
		       		return true;
		    	}else{
			     	//去掉该汉字
			     	//document.getElementById("comments").value = document.getElementById("comments").value.substring(0,smsLength);
			     	if (needAlert==1){
				        //对象失去焦点，同时弹出
				        alert("<bean:message bundle="hsp.hspLocale" key="hsp.jsp.common.warn29" />"+smsLength+"<bean:message bundle="hsp.hspLocale" key="hsp.jsp.common.warn29_1" />!");
				        document.getElementById("comments").focus();
				        return false;
			     	}
		    	}
		   	}			   
			function checkDomain(){
				var enDomain = document.form.domainName.value;
				var i;
				var ii;
				var aa;
				if(enDomain=="" || enDomain==" " || enDomain.length < 4) {
				  	alert("<bean:message bundle="hsp.hspLocale" key="hsp.jsp.common.warn37" />!");
				  	document.form.domainName.focus();
				  	return false;
				}				   
				var checkOK = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz-0123456789|.!=+%/_: ";
				var allValid = true;
				for(i=0; i<enDomain.length; i++){
				  	ch = enDomain.charAt(i);
				  	for (j = 0;  j < checkOK.length;  j++)
				  	if (ch == checkOK.charAt(j))
				   		break;
				  	if (j == checkOK.length){
					   allValid = false;
					   break;
				  	}
				}				 
				if (!allValid){
				  	alert("<bean:message bundle="hsp.hspLocale" key="hsp.jsp.common.warn38" />!");
				  	document.form.domainName.focus();
				  	return (false);
				}
				if(enDomain.length>0){
					ii=enDomain.indexOf(".")
				    if(ii==-1){
					   alert("<bean:message bundle="hsp.hspLocale" key="hsp.jsp.hspconfigbaseinfoupdate.warn1" />!")
					   document.form.domainName.focus();
					   return false
				    }
				}
				return true; 
			}
	function getReadyStateHandler(req, responseXmlHandler) {
				return function () {
					if(req.readyState == 4){
			      		if (req.status == 200){
			      			//alert(req.responseText); 
						    responseXmlHandler(req.responseXML);
						}else{
						    alert("HTTP error: " + req.status);
			      		}
			    	}
			  	}
			}
	<!-- 处理市 -->
			function setCity(url){
				var province = document.getElementById("commConfigLocationId1").value;
				var xmlHttp = newXMLHttpRequest();
				var sendTo = url + "?verbId=setCity&province=" + province;
				xmlHttp.open("GET", sendTo, true);
				var handlerFunction = getReadyStateHandler(xmlHttp, updateCity);
				xmlHttp.onreadystatechange = handlerFunction;
				xmlHttp.send(null);
			}
			<!-- 更新市 -->
			function updateCity(cityXML) {
				var city = document.getElementById("commConfigLocationId2");
			    while (city.options.length) {
			        city.remove(0);
			    }				    
			    var country = document.getElementById("commConfigLocationId3");
			    while (country.options.length) {
			        country.remove(0);
			    }				    
			    var clt = document.getElementById("commConfigLocationTownId");
			    while (clt.options.length) {
			        clt.remove(0);
			    }				    
			    var clv = document.getElementById("commClvId");
			    while (clv.options.length) {
			       clv.remove(0);
			    }				    
				var indexObj = cityXML.getElementsByTagName("index")[0];
				var index = indexObj.childNodes[0].nodeValue;
				for(var i=0; i<index; i++){
					var keyObj = cityXML.getElementsByTagName("key")[i];
					var valueObj = cityXML.getElementsByTagName("value")[i];
					var newElem = document.createElement("option");
					if(valueObj.childNodes[0] == null){
						newElem.text = "" ;
					}else{
						newElem.text = valueObj.childNodes[0].nodeValue ;
					}
					if(keyObj.childNodes[0] == null){
						newElem.value = "" ;
					}else{
						newElem.value = keyObj.childNodes[0].nodeValue ;
					}
					city.add(newElem);
				}
			}	
			<!-- 设置县 -->
			function setCounty(url){
				var city = document.getElementById("commConfigLocationId2").value;
				var xmlHttp = newXMLHttpRequest();
				var sendTo = url + "?verbId=setCounty&city=" + city;
				xmlHttp.open("GET", sendTo, true);
				var handlerFunction = getReadyStateHandler(xmlHttp, updateCounty);
				xmlHttp.onreadystatechange = handlerFunction;
				xmlHttp.send(null);
			}
			<!-- 更新县 -->
			function updateCounty(countyXML) {
				var country = document.getElementById("commConfigLocationId3");
			    while (country.options.length) {
			        country.remove(0);
			    }			    
			    var clt = document.getElementById("commConfigLocationTownId");
			    while (clt.options.length) {
			        clt.remove(0);
			    }			    
			    var clv = document.getElementById("commClvId");
			    while (clv.options.length) {
			       clv.remove(0);
			    }			    
				var indexObj = countyXML.getElementsByTagName("index")[0];
				var index = indexObj.childNodes[0].nodeValue;
				for(var i=0; i<index; i++){
					var keyObj = countyXML.getElementsByTagName("key")[i];
					var valueObj = countyXML.getElementsByTagName("value")[i];
					var newElem = document.createElement("option");
					if(valueObj.childNodes[0] == null){
						newElem.text = "" ;
					}else{
						newElem.text = valueObj.childNodes[0].nodeValue ;
					}
					if(keyObj.childNodes[0] == null){
						newElem.value = "" ;
					}else{
						newElem.value = keyObj.childNodes[0].nodeValue ;
					}
					country.add(newElem);
				}
			}			
			<!-- 设置乡镇 -->
			function setTown(url){
				var town = document.getElementById("commConfigLocationId3").value;
				var xmlHttp = newXMLHttpRequest();
				var sendTo = url + "?verbId=setTown&town=" + town;
				xmlHttp.open("GET", sendTo, true);
				var handlerFunction = getReadyStateHandler(xmlHttp, updateTown);
				xmlHttp.onreadystatechange = handlerFunction;
				xmlHttp.send(null);
			}
			<!-- 更新乡镇 -->
			function updateTown(countyXML) {				   
			    var clt = document.getElementById("commConfigLocationTownId");
			    while (clt.options.length) {
			        clt.remove(0);
			    }			    
			    var clv = document.getElementById("commClvId");
			    while (clv.options.length) {
			       clv.remove(0);
			    }			    
				var indexObj = countyXML.getElementsByTagName("index")[0];
				var index = indexObj.childNodes[0].nodeValue;
				for(var i=0; i<index; i++){
					var keyObj = countyXML.getElementsByTagName("key")[i];
					var valueObj = countyXML.getElementsByTagName("value")[i];
					var newElem = document.createElement("option");
					if(valueObj.childNodes[0] == null){
						newElem.text = "" ;
					}else{
						newElem.text = valueObj.childNodes[0].nodeValue ;
					}
					if(keyObj.childNodes[0] == null){
						newElem.value = "" ;
					}else{
						newElem.value = keyObj.childNodes[0].nodeValue ;
					}
					clt.add(newElem);
				}
			}
			<!-- 设置村 -->
			function setVillage(url){
				var village = document.getElementById("commConfigLocationTownId").value;
				var xmlHttp = newXMLHttpRequest();
				var sendTo = url + "?verbId=setVillage&village=" + village;
				xmlHttp.open("GET", sendTo, true);
				var handlerFunction = getReadyStateHandler(xmlHttp, updateVillage);
				xmlHttp.onreadystatechange = handlerFunction;
				xmlHttp.send(null);
			}
			<!-- 更新村 -->
			function updateVillage(countyXML) {
			    var clv = document.getElementById("commClvId");
			    while (clv.options.length) {
			       clv.remove(0);
			    }			    
				var indexObj = countyXML.getElementsByTagName("index")[0];
				var index = indexObj.childNodes[0].nodeValue;
				for(var i=0; i<index; i++){
					var keyObj = countyXML.getElementsByTagName("key")[i];
					var valueObj = countyXML.getElementsByTagName("value")[i];
					var newElem = document.createElement("option");
					if(valueObj.childNodes[0] == null){
						newElem.text = "" ;
					}else{
						newElem.text = valueObj.childNodes[0].nodeValue ;
					}
					if(keyObj.childNodes[0] == null){
						newElem.value = "" ;
					}else{
						newElem.value = keyObj.childNodes[0].nodeValue ;
					}
					clv.add(newElem);
				}
			}
			function extendInfo(){
				var _div = $('#moreinfo_div');
				var srcEle = document.getElementById('extendTd');
				while(srcEle.childNodes !=null && srcEle.childNodes.length > 0){
					srcEle.removeChild(srcEle.childNodes[0]);
				}
				if(_div.css("display") == "none"){
					var _text = document.createTextNode("隐藏 ︽");
					srcEle.appendChild(_text);
					Set_Cookie("isHiddenMore_hsp", true, 7);
				}else{
					var _text = document.createTextNode("显示更多︾");
					srcEle.appendChild(_text);
					Set_Cookie("isHiddenMore_hsp", false, 7);
				}
				_div.slideToggle();
			}
			
			window.onload = function(){
				var _div = $('#moreinfo_div');
				var srcEle = document.getElementById('extendTd');
				if(Get_Cookie("isHiddenMore_hsp") == "false"){
					if(_div.css("display") != "none"){
						while(srcEle.childNodes !=null && srcEle.childNodes.length > 0){
							srcEle.removeChild(srcEle.childNodes[0]);
						}
						var _text = document.createTextNode("显示更多︾");
						srcEle.appendChild(_text);
						_div.css("display", "none");
					}
				}else{
					if(_div.css("display") == "none"){
						while(srcEle.childNodes !=null && srcEle.childNodes.length > 0){
							srcEle.removeChild(srcEle.childNodes[0]);
						}
						var _text = document.createTextNode("隐藏 ︽");
						srcEle.appendChild(_text);
						_div.css("display", "block");
					}
				}
			};
			
</script>
	</head>
	<body>
		<form name="form" id="form" method="post" action="hsp/hspConfigBaseinfo.do">
			<input type="hidden" name="verbId" value="" />
			<input type="hidden" name="useForTree" value="<%=request.getParameter("useForTree") %>"/>
			<br />
			<table align="center" border="0" cellpadding="0" cellspacing="0" class="tblFill" style="margin-top: -1px">
				<tr>
					<td class="tblTitle" colspan="4">
						<span>※</span>
						<bean:message bundle="hsp.hspLocale" key="hsp.jsp.hspconfigbaseinfoadd.addItem" />
						<span>※</span>
					</td>
				</tr>
				<tr>
					<td class="tblLable">
						<span>*</span>上级机构：
					</td>
					<td colspan="3" style="padding-right: 40px;">
						<input type="text" id="displayInputId_1" name="parentItemCode_name" value="<%=data.getParentItemName()%>" readonly
							onkeydown="huiche()" />
						<input type="hidden" id="hiddenInputId_1" value="<%=data.getParentItemCode()%>" name="parentItemCode" />
						<!--弹出的选择按钮，add(arg1,arg2,arg3)其中arg1代表基本录入框的id，arg2代表保存存储到数据库字段对应的input的id
							arg3代表需要传递到.do的数据库检索参数
						-->
						<!-- hspType 1为除去卫生服务站2为只包括服务站和服务中心3为所有 -->
						<img src="<%=request.getContextPath()%>/hsp/include/images/select.gif"
							style="cursor: pointer; position: absolute;"
							onclick="add('<%=request.getContextPath()%>/hsp/hspConfigBaseinfo.do?verbId=getHsp&hspType=1','displayInputId_1','hiddenInputId_1')" />
					</td>
				</tr>
				<tr>

					<td class="tblLable">
						<span>*</span>
						<bean:message bundle="hsp.hspLocale" key="hsp.jsp.common.itemName" />
						：
					</td>
					<td style="width: 30%">
						<input type="text" name="itemName"
							msg="<bean:message  bundle="hsp.hspLocale" key="hsp.jsp.hspconfigbaseinfoupdate.itemNameInputWrong"/>"
							maxlength="100" min="1" max="100" dataType="LimitB" onkeydown="huiche()" value="<%=data.getItemName()%>" />
					</td>
					<td class="tblLable">
						<span>*</span>
						<bean:message bundle="hsp.hspLocale" key="hsp.jsp.common.itemCode" />
						：
					</td>
					<td>
						<input type="text" name="itemCode" id="itemCode" maxlength="32"  min="1" max="32" dataType="LimitB" msg="机构代码的长度应在1-32位字节范围内" onkeydown="huiche()"
							value="<%=data.getItemCode()%>" />
					</td>
				</tr>

				<tr>
					<td class="tblLable">
						<span>*</span>
						<bean:message bundle="hsp.hspLocale" key="hsp.jsp.common.itemSort" />
						：
					</td>
					<td style="padding-right: 40px;">
						<!--以下是显示基本录入框的input-->
						<input type="text" class="input" id="displayInputId_2" name="11" value="<%=data.getCchtName()%>" readonly="true"
							onkeydown="huiche()" dataType="Require" msg="请选择机构类别"/>
						<!--这是准备存储到数据的字段-->
						<input type="hidden" id="hiddenInputId_2" value="<%=data.getCommConfigHospitalTypeId()%>"
							name="commConfigHospitalTypeId" />
						<!--弹出的选择按钮，add(arg1,arg2,arg3)其中arg1代表基本录入框的id，arg2代表保存存储到数据库字段对应的input的id
						arg3代表需要传递到.do的数据库检索参数
						-->
						<!-- hspType 1为除去卫生服务站2为只包括服务站和服务中心3为所有 -->
						<img src="<%=request.getContextPath()%>/hsp/include/images/select.gif"
							style="cursor: pointer; position: absolute;"
							onclick="add('<%=request.getContextPath()%>/comm/commConfigHospitalType.do?verbId=getHspType&hspTypeLevel=3','displayInputId_2','hiddenInputId_2')" />
					</td>
					<td class="tblLable">
						<span>*</span>机构分类：
					</td>
					<td>
						<select name="commConfigFtManageId" onkeydown="huiche()" id="commConfigFtManageId" dataType="Require" msg="请选择机构分类">
							<%
								if (data.getCommConfigFtManageIds() != null
										&& data.getCommConfigFtManageIds().length > 0) {
									for (int i = 0; i < data.getCommConfigFtManageIds().length; i++) {
										String tempId = data.getCommConfigFtManageIds()[i];
										String tempName = data.getCommConfigFtManageId_names()[i];
							%>
							<option value="<%=tempId%>"
								<%=tempId.equals(data
											.getCommConfigFtManageId()) ? "selected"
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
					<td class="tblLable">
						<span>*</span>经济性质：
					</td>
					<td>
						<select name="commConfigEconkindId" onkeydown="huiche()" id="commConfigEconkindId" dataType="Require" msg="请选择经济性质">
							<%
								if (data.getCommConfigEconkindIds() != null
										&& data.getCommConfigEconkindIds().length > 0) {
									for (int i = 0; i < data.getCommConfigEconkindIds().length; i++) {
										String tempId = data.getCommConfigEconkindIds()[i];
										String tempName = data.getCommConfigEconkindId_names()[i];
							%>
							<option value="<%=tempId%>"
								<%=tempId.equals(data
											.getCommConfigEconkindId()) ? "selected"
									: ""%>>
								<%=tempName%>
							</option>
							<%
								}
								}
							%>
						</select>
					</td>
					<td class="tblLable">
						<span>*</span>省（市）：
					</td>
					<td>
						<select name="commConfigLocationId1" onkeydown="huiche()" id="commConfigLocationId1"
							onchange="setCity('comm/commConfigLocationGroup.do')"  dataType="Require" msg="请选择省">
							<%
								if (data.getCommConfigLocationId1s() != null
										&& data.getCommConfigLocationId1s().length > 0) {
									for (int i = 0; i < data.getCommConfigLocationId1s().length; i++) {
										String tempId = data.getCommConfigLocationId1s()[i];
										String tempName = data.getCommConfigLocationId1_names()[i];
							%>
							<option value="<%=tempId%>"
								<%=tempId.equals(data
											.getCommConfigLocationId1()) ? "selected"
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
					<td class="tblLable">
						<span>*</span>市（地区）：
					</td>
					<td>
						<select name="commConfigLocationId2" onkeydown="huiche()" id="commConfigLocationId2"
							onchange="setCounty('comm/commConfigLocationGroup.do')"  dataType="Require" msg="请选择市">
							<%
								if (data.getCommConfigLocationId2s() != null
										&& data.getCommConfigLocationId2s().length > 0) {
									for (int i = 0; i < data.getCommConfigLocationId2s().length; i++) {
										String tempId = data.getCommConfigLocationId2s()[i];
										String tempName = data.getCommConfigLocationId2_names()[i];
							%>
							<option value="<%=tempId%>"
								<%=tempId.equals(data
											.getCommConfigLocationId2()) ? "selected"
									: ""%>>
								<%=tempName%>
							</option>
							<%
								}
								}
							%>
						</select>

					</td>
					<td class="tblLable">
						县（区）：
					</td>
					<td>

						<select name="commConfigLocationId3" onkeydown="huiche()" id="commConfigLocationId3"
							onchange="setTown('comm/commConfigLocationGroup.do')">
							<%
								if (data.getCommConfigLocationId3s() != null
										&& data.getCommConfigLocationId3s().length > 0) {
									for (int i = 0; i < data.getCommConfigLocationId3s().length; i++) {
										String tempId = data.getCommConfigLocationId3s()[i];
										String tempName = data.getCommConfigLocationId3_names()[i];
							%>
							<option value="<%=tempId%>"
								<%=tempId.equals(data
											.getCommConfigLocationId3()) ? "selected"
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
					<td class="tblLable">
						乡&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;镇：
					</td>
					<td>
						<select name="commConfigLocationTownId" onkeydown="huiche()" id="commConfigLocationTownId"
							onchange="setVillage('comm/commConfigLocationGroup.do')">
							<option></option>
							<%
								if (data.getCommConfigLocationTownIds() != null
										&& data.getCommConfigLocationTownIds().length > 0) {
									for (int i = 0; i < data.getCommConfigLocationTownIds().length; i++) {
										String tempId = data.getCommConfigLocationTownIds()[i];
										String tempName = data.getCommConfigLocationTownId_names()[i];
							%>
							<option value="<%=tempId%>"
								<%=tempId.equals(data
											.getCommConfigLocationTownId()) ? "selected"
									: ""%>>
								<%=tempName%>
							</option>
							<%
								}
								}
							%>
						</select>

					</td>
					<td class="tblLable">
						村 ：
					</td>
					<td>
						<select name="commClvId" onkeydown="huiche()" id="commClvId">
							<%
								if (data.getCommClvIds() != null && data.getCommClvIds().length > 0) {
									for (int i = 0; i < data.getCommClvIds().length; i++) {
										String tempId = data.getCommClvIds()[i];
										String tempName = data.getCommClvId_names()[i];
							%>
							<option value="<%=tempId%>" <%=tempId.equals(data.getCommClvId()) ? "selected"
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
			</table>

			<div id="moreinfo_div" style="display: none">
				<table align="center" border="0" cellpadding="0" cellspacing="0" class="tblFill" style="margin-top: -1px">
					<tr>
						<td class="tblLable">
							<bean:message bundle="hsp.hspLocale" key="hsp.jsp.hspconfigbaseinfoupdate.address" />
							：
						</td>
						<td style="width: 30%">
							<input type="text" name="address" onkeydown="huiche()" id="address" maxlength="50" value="<%=data.getAddress()%>" />
						</td>

						<td class="tblLable">
							<bean:message bundle="hsp.hspLocale" key="hsp.jsp.common.zipCode" />
							：
						</td>
						<td>
							<input type="text" onkeydown="huiche()" name="zipcode" id="zipcode" onblur="checkPostCode('zipcode')"
								maxlength="6" value="<%=data.getZipcode()%>" />
						</td>
					</tr>
					<tr>
						<td class="tblLable">
							<bean:message bundle="hsp.hspLocale" key="hsp.jsp.hspconfigbaseinfoupdate.contactPersonName" />
							：
						</td>
						<td>
							<input type="text" onkeydown="huiche()" name="contactPersonName" id="contactPersonName" maxlength="50"
								value="<%=data.getContactPersonName()%>" />
						</td>
						<td class="tblLable">
							<bean:message bundle="hsp.hspLocale" key="hsp.jsp.hspconfigbaseinfoupdate.phone" />
							：
						</td>
						<td>
							<input type="text" onkeydown="huiche()" name="phone" id="tel" onblur="checktel()" maxlength="15"
								value="<%=data.getPhone()%>" />
						</td>
					</tr>
					<tr>
						<td class="tblLable">
							<bean:message bundle="hsp.hspLocale" key="hsp.jsp.common.email" />
							：
						</td>
						<td>
							<input type="text" onkeydown="huiche()" name="EMail" onblur="checkemail()" id="email" maxlength="25"
								value="<%=data.getEMail()%>" />
						</td>
						<td class="tblLable">
							<bean:message bundle="hsp.hspLocale" key="hsp.jsp.common.domainName" />
							：
						</td>
						<td>
							<input type="text" onkeydown="huiche()" name="domainName" id="domainName" maxlength="50" id="domainName"
								value="<%=data.getDomainName()%>" />
						</td>
					</tr>
					<tr>
						<td class="tblLable">
							机&nbsp;&nbsp;构&nbsp;&nbsp;级：
						</td>

						<td>
							<select name="commConfigUnitgradeId" onkeydown="huiche()" id="commConfigUnitgradeId">
								<option></option>
								<%
									if (data.getC010501bList().size() > 0
											&& data.getC010501bList() != null) {
										for (int i = 0; i < data.getC010501bList().size(); i++) {
											String veryId = "";
											if (data.getCommConfigUnitgradeId() != null) {
												veryId = data.getCommConfigUnitgradeId();
											}
											String tempId = ((CommConfigUnitgrade) data
													.getC010501bList().get(i)).getSeqNo()
													+ "";
											String tempName = ((CommConfigUnitgrade) data
													.getC010501bList().get(i)).getItemName();
								%>
								<option value="<%=((CommConfigUnitgrade) data.getC010501bList()
									.get(i)).getSeqNo()%>"
									<%=(tempId.equals(veryId)) ? "selected" : ""%> />
								<%=((CommConfigUnitgrade) data.getC010501bList()
									.get(i)).getItemName()%>
								<%
									}
									}
								%>
							</select>
						</td>
						<td class="tblLable">
							机&nbsp;&nbsp;构&nbsp;&nbsp;等：
						</td>
						<td>
							<select name="commConfigUnittypeId" id="commConfigUnittypeId" onkeydown="huiche()">
								<option></option>
								<%
									if (data.getC010501aList().size() > 0
											&& data.getC010501aList() != null) {
										for (int i = 0; i < data.getC010501aList().size(); i++) {
											String veryId = "";
											if (data.getCommConfigUnittypeId() != null) {
												veryId = data.getCommConfigUnittypeId();
											}
											String tempId = ((CommConfigUnittype) data
													.getC010501aList().get(i)).getSeqNo()
													+ "";
											String tempName = ((CommConfigUnittype) data
													.getC010501aList().get(i)).getItemName();
								%>
								<option value="<%=((CommConfigUnittype) data.getC010501aList().get(
									i)).getSeqNo()%>"
									<%=(tempId.equals(veryId)) ? "selected" : ""%> />
								<%=((CommConfigUnittype) data.getC010501aList().get(
									i)).getItemName()%>
								<%
									}
									}
								%>
							</select>
						</td>
					</tr>
					<tr>
						<td class="tblLable">
							设置主办单位：
						</td>
						<td>
							<select name="commConfigSetTypes" id="commConfigSetTypes" onkeydown="huiche()">
								<option></option>
								<%
									if (data.getCommConfigSetTypeId() != null
											&& data.getCommConfigSetTypeId().length > 0) {
										for (int i = 0; i < data.getCommConfigSetTypeId().length; i++) {
								%>
								<option value="<%=data.getCommConfigSetTypeId()[i]%>"
									<%=data.getCommConfigSetTypeId()[i].equals(data
									.getCommConfigSetTypes()) ? " selected"
							: ""%>><%=data.getCommConfigSetType()[i]%></option>
								<%
									}
									}
								%>
							</select>
						</td>
						<td class="tblLable">
							政府办卫生机构隶属关系：
						</td>
						<td>
							<select name="commConfigCovaffrss" id="commConfigCovaffrss" onkeydown="huiche()">
								<option value=""></option>
								<%
									if (data.getCommConfigCovaffrsId() != null
											&& data.getCommConfigCovaffrsId().length > 0) {
										for (int i = 0; i < data.getCommConfigCovaffrsId().length; i++) {
								%>
								<option value="<%=data.getCommConfigCovaffrsId()[i]%>"
									<%=data.getCommConfigCovaffrsId()[i].equals(data
									.getCommConfigCovaffrss()) ? "  selected"
							: ""%>><%=data.getCommConfigCovaffrs()[i]%></option>
								<%
									}
									}
								%>
							</select>
						</td>
					</tr>

					<tr>
						<td class="tblLable">
							床位数：
						</td>
						<td>
							<input type="text" name="authorizedBedNum" onkeydown="huiche()" onblur="checkemail()" id="authorizedBedNum"
								maxlength="25" value="<%=data.getAuthorizedBedNum()%>" />
						</td>
						<td class="tblLable">
							实有床位数：
						</td>
						<td>
							<input type="text" name="outspreadBedNum" onkeydown="huiche()" onblur="checkemail()" id="outspreadBedNum"
								maxlength="25" value="<%=data.getOutspreadBedNum()%>" />
						</td>
					</tr>
					<tr>
						<td class="tblLable">
							<bean:message bundle="hsp.hspLocale" key="hsp.jsp.common.seqNo" />
							：
						</td>
						<td colspan="3">
							<input type="text" name="seqNo" onkeydown="huiche()" onblur="checkemail()" id="seqNo" maxlength="25"
								value="<%=data.getSeqNo()%>" onkeydown="onlyNum();" />
						</td>
					</tr>
					<tr>
						<td class="tblLable">
							<bean:message bundle="hsp.hspLocale" key="hsp.jsp.hspconfigbaseinfoupdate.comments1" />
							：
						</td>
						<td class="textareaTd" colspan="3">
							<textarea name="comments" id="comments" onkeydown="huiche()" rows="3"><%=data.getComments()%></textarea>
						</td>
					</tr>
				</table>
			</div>
			<table align="center" border="0" cellpadding="0" cellspacing="0" class="tblFill" style="margin-top: -1px">
				<tr>
					<td class="tblLable" style="text-align: center; width:80px; height: 12px; line-height: 12px"
						id="extendTd"
						onclick="extendInfo()">
						显示更多︾
					</td>
				</tr>
			</table>

			<!-- Sheet operation button area -->
			<div class="btnSave">
				<input type="button" name="btnSaveForm"
					value="<bean:message bundle="hsp.hspLocale" key="hsp.jsp.hspstaffbaseinfoupdate.save1" />" onclick="check();" />
				<%
					if(!"1".equals(request.getParameter("useForTree"))){
				%>
				<input type="button" name="btnBack"
					value="<bean:message  bundle="hsp.hspLocale" key="hsp.jsp.hspstaffbaseinfoupdate.back"/>" onclick="history.go(-1)" />
				<%
					}
				%>
			</div>
		</form>
	</body>
</html>