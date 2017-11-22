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
    
    <title><bean:message key="security.jsp.securityDataObjectVsRole.add.title" bundle="security"/></title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
    <script language="javascript" src="include/javascript/TJMessage.js"></script>
	<script language="javascript" src="include/javascript/eventOnKeyPress.js"></script>
	
	<script language="javascript" src="<bean:message key="security.js.gettext_staff11.path" bundle="security" />"></script>
	<script type="text/javascript" src="<%=basePath%>include/javascript/jianbian.js"></script>
    <link type="text/css" rev="stylesheet" rel="stylesheet" href="include/css/form.css" />
    <script language="javascript" src="<bean:message key="security.js.treeView.MzTreeView10.path" bundle="security" />"></script>
    <link rel="stylesheet" href="<%=basePath%>include/css/open.css" />
  </head>
  <script language="javascript">
  function save(path){
  	var id=document.getElementById("hiddenInputId_2").value;
  	var stdoId=document.getElementById("sdtoId").value;
  	if(id==null||id==""){
  		alert("<bean:message key="security.jsp.securityDataObjectVsRole.adds.warn" bundle="security"/>!");
  		return;
  	}
  	if(stdoId==null||stdoId==""){
  		alert("<bean:message key="security.jsp.commom.warn13" bundle="security"/>!");
  		return;
  	}
  	if(stdoId=="2"){
  		var s=document.getElementById("commConfigLocationId1").value;
  		if(s==""||s==null){
  			alert("<bean:message key="security.jsp.securityDataObjectVsRole.adds.warn2" bundle="security"/>!");
  			return;
  		}
  	}
  	if(stdoId=="3"){
  		var s=document.getElementById("commConfigLocationId2").value;
  		if(s==""||s==null){
  			alert("<bean:message key="security.jsp.securityDataObjectVsRole.adds.warn3" bundle="security"/>!");
  			return;
  		}
  	}
  	if(stdoId=="4"){
  		var s=document.getElementById("commConfigLocationId3").value;
  		if(s==""||s==null){
  			alert("<bean:message key="security.jsp.securityDataObjectVsRole.adds.warn4" bundle="security"/>!");
  			return;
  		}
  	}
  	if(stdoId=="5"){
  		var s=document.getElementById("commConfigLocationTownId").value;
  		if(s==""||s==null){
  			alert("<bean:message key="security.jsp.securityDataObjectVsRole.adds.warn5" bundle="security"/>!");
  			return;
  		}
  	}
  	var temp="verbId=dealThing&id="+id+"&stdoId="+stdoId;	
  	if(document.form.menuIdSelected==null){
  		return;
  	}
  	for(i=0;i<document.form.menuIdSelected.length;i++){
  		if(document.form.menuIdSelected[i].checked == true){
  			temp += "&selectId="+document.form.menuIdSelected[i].value;
  		}
  	}
  	var xmlHttp=newXMLHttpRequest();
  	var dothis=getReadyStateHandler(xmlHttp,giveMessage);
  	var url=path+"/security/securityDateVs.do";
  	xmlHttp.onreadystatechange=dothis; 
  	xmlHttp.open("POST", url, true);
	xmlHttp.setRequestHeader("Content-Type","application/x-www-form-urlencoded"); 
  	xmlHttp.send(temp);
  }
  function lookisorno(same){
  	for(i=0;i<document.form.menuIdSelected.length;i++){
  		if(same==document.form.menuIdSelected[i].value){
  			return false;
  		}
  	}
  }
  function giveMessage(xmlHttp){
  	 var messages=xmlHttp.getElementsByTagName("message");
  	 if(messages[0]!=null){
  	 	if(messages[0].childNodes[0]!=null){
  	 		alert(messages[0].childNodes[0].nodeValue);
  	 	}
  	 }
  }
  function zhankai(){
  	var id=document.getElementById("sdtoId").value;
  	if(id==""||id==null){
  		alert("<bean:message key="security.jsp.commom.warn13" bundle="security"/>!");
  		return;
  	}
  	if(id=="2"){
  		var s=document.getElementById("commConfigLocationId1").value;
  		if(s==""||s==null){
  			//alert("请选择省!");
  			return;
  		}
  	}
  	if(id=="3"){
  		var s=document.getElementById("commConfigLocationId2").value;
  		if(s==""||s==null){
  			//alert("请选择市!");
  			return;
  		}
  	}
  	if(id=="4"){
  		var s=document.getElementById("commConfigLocationId3").value;
  		if(s==""||s==null){
  			//alert("请选择县!");
  			return;
  		}
  	}
  	if(id=="5"){
  		var s=document.getElementById("commConfigLocationTownId").value;
  		if(s==""||s==null){
  			//alert("请选择乡镇!");
  			return;
  		}
  	}
  	document.form.verbId.value="zhanKai";
  	document.form.submit();
  }
  function huiche(){
  	if(event.keyCode==13){
  		event.keyCode=9;
  	}
  }
  function load(){
  	 var table=document.getElementById("mainTable");
  	 var tbody=table.getElementsByTagName("tbody");
  	 var tr=document.createElement("tr");
  	 	tr = createTable(tr,"2");
  	 	tr = createTable(tr,"3");
  	 	tr.id="tr_1";
  	 	tr.style.display="none";
  	 	var tra=document.createElement("tr");
  	 	var tra = createTable(tra,"4");
  	 	var tra = createTable(tra,"5");
  	 	tra.id="tr_2";
  	 	tra.style.display="none";
  	 	tbody[0].appendChild(tr);
  	 	tbody[0].appendChild(tra);
  	 	table.appendChild(tbody[0]);
  	 	xuanzhe(<%=data.getSdotId()%>);
	if(document.getElementById("commConfigLocationId1").value!=null&&document.getElementById("commConfigLocationId1").value!=""
	&&(document.getElementById("commConfigLocationId2").value==null||document.getElementById("commConfigLocationId2").value=="")){
		setCity('empi/securityUserBaseinfo.do');
	}
	if(document.getElementById("commConfigLocationId2").value!=null&&document.getElementById("commConfigLocationId2").value!=""
	&&(document.getElementById("commConfigLocationId3").value==null||document.getElementById("commConfigLocationId3").value=="")){
		setCounty('empi/securityUserBaseinfo.do');
	}
	if(document.getElementById("commConfigLocationId3").value!=null&&document.getElementById("commConfigLocationId3").value!=""
	&&(document.getElementById("commConfigLocationTownId").value==null||document.getElementById("commConfigLocationTownId").value=="")){
		setTown('empi/securityUserBaseinfo.do');
	}
}
function createTable(tr,number ){
	var element1=document.createElement("td");
	var element2=document.createElement("td");
 	element1.className="qian";
 	var td1,td2;
 	if(number=="2"){
 		element1.id="shi_1";
 		element2.id="shi_2"
 		td1=document.getElementById("shi1");
 		td2=document.getElementById("shi2");
 	}
 	if(number=="3"){
 		element1.id="xian_1";
 		element2.id="xian_2"
 		td1=document.getElementById("xian1");
 		td2=document.getElementById("xian2");
 	}
 	if(number=="4"){
 		element1.id="xiang_1";
 		element2.id="xiang_2"
 		td1=document.getElementById("xiang1");
 		td2=document.getElementById("xiang2");
 	}
 	if(number=="5"){
 		element1.id="cun_1";
 		element2.id="cun_2"
 		td1=document.getElementById("cun1");
 		td2=document.getElementById("cun2");
 	}
 	td1.style.display="block";
 	element1.appendChild(td1);
 	element2.className="hou";
 	td2.style.display="block";
 	element2.appendChild(td2);
 	element1.style.display="none";
 	element2.style.display="none";
 	tr.appendChild(element1);
	tr.appendChild(element2);
 	return tr;
}
  function xuanzhe(value){
  	 if(value==null||value==""){
  	 	return;
  	 }
  	 if(value=="1"){
  	 	document.getElementById("tr_1").style.display="none";
  	 	document.getElementById("tr_2").style.display="none";
  	 }
  	 if(value=="2"){
  	 	document.getElementById("tr_1").style.display="block";
  	 	document.getElementById("tr_2").style.display="none";
  	 	document.getElementById("shi_1").style.display="block";
  	 	document.getElementById("shi_2").style.display="block";
  	 	document.getElementById("xian_1").style.display="block";
  	 	document.getElementById("xian_2").style.display="block";
  	 	document.getElementById("xian1").style.display="none";
  	 	document.getElementById("xian2").style.display="none";
  	 }
  	 if(value=="3"){
  	 	document.getElementById("tr_1").style.display="block";
  	 	document.getElementById("tr_2").style.display="none";
  	 	document.getElementById("shi_1").style.display="block";
  	 	document.getElementById("shi_2").style.display="block";
  	 	document.getElementById("xian_1").style.display="block";
  	 	document.getElementById("xian_2").style.display="block";
  	 	document.getElementById("xian1").style.display="block";
  	 	document.getElementById("xian2").style.display="block";
  	 }
  	 if(value=="4"){
  	 	document.getElementById("tr_1").style.display="block";
  	 	document.getElementById("tr_2").style.display="block";
  	 	document.getElementById("shi_1").style.display="block";
  	 	document.getElementById("shi_2").style.display="block";
  	 	document.getElementById("xian_1").style.display="block";
  	 	document.getElementById("xian_2").style.display="block";
  	 	document.getElementById("xian1").style.display="block";
  	 	document.getElementById("xian2").style.display="block";
  	 	document.getElementById("xiang_1").style.display="block";
  	 	document.getElementById("xiang_2").style.display="block";
  	 	document.getElementById("cun_1").style.display="block";
  	 	document.getElementById("cun_2").style.display="block";
  	 	document.getElementById("cun1").style.display="none";
  	 	document.getElementById("cun2").style.display="none";
  	 }
  	 if(value=="5"){
  	 	document.getElementById("tr_1").style.display="block";
  	 	document.getElementById("tr_2").style.display="block";
  	 	document.getElementById("shi_1").style.display="block";
  	 	document.getElementById("shi_2").style.display="block";
  	 	document.getElementById("xian_1").style.display="block";
  	 	document.getElementById("xian_2").style.display="block";
  	 	document.getElementById("xian1").style.display="block";
  	 	document.getElementById("xian2").style.display="block";
  	 	document.getElementById("xiang_1").style.display="block";
  	 	document.getElementById("xiang_2").style.display="block";
  	 	document.getElementById("cun_1").style.display="block";
  	 	document.getElementById("cun_2").style.display="block";
  	 	document.getElementById("cun1").style.display="block";
  	 	document.getElementById("cun2").style.display="block";
  	 }
  }
  function doAjax(url){
  		var xmlHttp=newXMLHttpRequest();
  		var dothis=getReadyStateHandler(xmlHttp,zhankaiData);
  		xmlHttp.open("GET",url,true);
  		xmlHttp.onreadystatechange=dothis;
  		xmlHttp.send(null);
  }
  function zhankaiData(xml){
  	var tree = new MzTreeView("tree");
	tree.setIconPath("include/javascript/treeView/");
  	var temp="";
  	var parentId;
  	var parents=xml.getElementsByTagName("parent");
  	if(parents[0]!=null){
  		if(parents[0].childNodes[0]!=null){
  			parentId = parents[0].childNodes[0].nodeValue;
  		}else{
  			parentId="100";
  		}
  	}else{
  		parentId="100";
  	}
  	if(parents[1]!=null){
  		if(parents[1].childNodes[0]!=null){
  			temp = "text:"+parents[1].childNodes[0].nodeValue+";";
  		}else{
  			temp = "text:<bean:message key="security.jsp.securityDataObjectVsRole.adds.item1" bundle="security"/>;"
  		}
  	}else{
  		temp = "text:<bean:message key="security.jsp.securityDataObjectVsRole.adds.item1" bundle="security"/>;"
  	}
  	tree.nodes["-1_"+parentId]=temp;
  	var indexes=xml.getElementsByTagName("index")[0];
  	var index=indexes.childNodes[0].nodeValue;
  	for(i=0;i<index;i++){
  		var key=xml.getElementsByTagName("key")[0].childNodes[0].nodeValue;
  		var value=xml.getElementsByTagName("value")[0].childNodes[0].nodeValue;
  		var check=xml.getElementsByTagName("check")[0].childNodes[0].nodeValue;
  		temp="ctrl:true;ctrlName:menuIdSelected;ctrlValue:" + key + ";ctrlChecked:" ;
  		if(check=="0"){
  			temp += "false;";
  		}else{
  			temp += "true;";
  		}
  		temp += "text:"+value+";";
  		alert(temp);
  		tree.nodes[parentId+"_"+key]=temp;
  	}
  	tree.wordLine = false;
  	//tree.toString();
  	//document.write(tree.toString()); 
  	//document.getElementById("quanxianxuanzhe").innerHTML=tree.toString();
  	//tree.expandAll();
  }
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
			document.form.verbId.value = "add";
			document.form.submit();
		}
	}
function check(url,id1,id2){
	var id=document.getElementById("sdtoId").value;
	if(id==""){
	   alert("<bean:message key="security.jsp.commom.warn13" bundle="security"/>");
	   return false;
	}
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
    
    //var county = document.getElementById("commConfigLocationId3");
   // var countyElem = document.createElement("option");
   // countyElem.text = county.options[0].text;
   // countyElem.value = county.options[0].value;
    //while (county.options.length) {
        //county.remove(0);
   // }
   // county.add(countyElem);
    
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
<!--设置县-->
function setCounty(url){
	var city = document.getElementById("commConfigLocationId2").value;
	var xmlHttp = newXMLHttpRequest();
	var sendTo = url + "?verbId=setCounty&commConfigLocationId2=" + city;
	xmlHttp.open("GET", sendTo, true);
	var handlerFunction = getReadyStateHandler(xmlHttp, updateCounty);
	xmlHttp.onreadystatechange = handlerFunction;
	xmlHttp.send(null);
}
<!--更新县-->
function updateCounty(countyXML) {
	var county = document.getElementById("commConfigLocationId3");
	while (county.options.length) {
        county.remove(0);
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
			    //var clv = document.getElementById("commClvId");
			    //while (clv.options.length) {
			      // clv.remove(0);
			   // }			    
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
	function goback(){
		document.form.verbId.value="query";
		document.form.submit();
	}
  </script>
  <body onload="showMessage('','${meaage }','1');load()">
  		<form name="form" method="post" action="security/securityDateVs.do">
  	    <input type="hidden" name="verbId" value="add" />
			<table id="mainTable"  align="center" border="0" cellpadding="0" cellspacing="0" class="tblFill" border="0" cellpadding="0" cellspacing="0" class="tblFill">
				<tr>
					<td class="tblTitle" colspan="4">
						<span>※</span>&nbsp;&nbsp;<bean:message key="security.jsp.securityDataObject.add.title" bundle="security"/>&nbsp;&nbsp;
						<span>※</span>
					</td>
				</tr>
				<tr>
					<td class="tblLable">
						<span>*</span> <bean:message key="security.jsp.commom.securityStaffBaseInfo" bundle="security"/>：
					</td>
					<td style="padding-right:40px;">
						<input type="text"  onchange="zhankai()" onkeydown="huiche()" readonly="readonly" name="securityStaffBaseInfo" value="<%=data.getSecurityStaffBaseInfoName() %>" id="displayInputId_2"  maxlength="32" onkeypress="eventOnKeyPress('sdtoId')" />
						 <!--这是准备存储到数据的字段-->
						<input type="hidden"  id="hiddenInputId_2" value="<%=data.getSecurityStaffBaseInfo() %>" name="securityStaffBaseInfoes" />
						
						<img src="<%=basePath%>hsp/include/images/select.gif" style="cursor:pointer;position:absolute;" onclick="add('<%=basePath%>security/securityDateVs.do?verbId=getSecurityStaff','displayInputId_2','hiddenInputId_2')" />
					</td>
					<td class="tblLable">
						<bean:message key="security.jsp.commom.sdtoId" bundle="security"/>：
					</td>
					<td >
					<select name="sdtoId" onkeypress="huiche()" onchange="xuanzhe(this.value),zhankai()" id="sdtoId">
						<option></option>
						<%
						if(data.getSdotIdList()!=null&&data.getSdotIdList().size()>0){
							for(int i=0;i<data.getSdotIdList().size();i++){
								Object[] obj=(Object[])data.getSdotIdList().get(i);
							%>
							<option value="<%=obj[0] %>" <%=obj[0].equals(data.getSdotId())? " selected":"" %>><%=obj[1] %></option>
							<%
							}
						}
						 %>
					</select>
					</td>
				</tr>
				</table>
				
					<div id="shi1" style="display:none;background:#F7F7F7;" class="tblLable">
						<span>*</span><bean:message key="security.jsp.commom.commConfigLocationId1" bundle="security"/>：
					</div>
						
							<div id="shi2" style="display: none">
								<select name="commConfigLocationId1" id="commConfigLocationId1"  onkeypress="huiche()" onchange="setCity('empi/securityUserBaseinfo.do'),zhankai()" > 
						            <%
									if(data.getCommConfigLocationId1s() != null && data.getCommConfigLocationId1s().length > 0){
										for(int i = 0; i < data.getCommConfigLocationId1s().length; i++){
											String tempId = data.getCommConfigLocationId1s()[i];
											String tempName = data.getCommConfigLocationId1_names()[i];
									%>
						        	<option value="<%=tempId %>" <%=tempId.equals(data.getCommConfigLocationId1()) ? "selected" : "" %>>
										<%=tempName %>
									</option>
									<%
										}
									}
									%>  
		    					</select>          
							</div>
						
							<div id="xian1" style="display: none;background:#F7F7F7;" class="tblLable">
				            	<span>*</span><bean:message key="security.jsp.commom.commConfigLocationId2" bundle="security"/>：
				            </div>
				            
							<div id="xian2" style="display: none">
					            <select class="kuandu" name="commConfigLocationId2" id="commConfigLocationId2" onkeypress="huiche()" onchange="setCounty('empi/securityUserBaseinfo.do'),zhankai()" > 
						            <%
									if(data.getCommConfigLocationId2s() != null && data.getCommConfigLocationId2s().length > 0){
										for(int i = 0; i < data.getCommConfigLocationId2s().length; i++){
											String tempId = data.getCommConfigLocationId2s()[i];
											String tempName = data.getCommConfigLocationId2_names()[i];
									%>
						        	<option value="<%=tempId %>" <%=tempId.equals(data.getCommConfigLocationId2()) ? "selected" : "" %>>
										<%=tempName %>
									</option>
									<%
										}
									}
									%>  
					    		</select>        	
							</div>
							
			<div id="xiang1" style="display: none;background:#F7F7F7;" class="tblLable">
           	 	<span>*</span><bean:message key="security.jsp.commom.commConfigLocationId3" bundle="security"/>：
            </div>
            <div id="xiang2" style="display: none">
	            <select class="kuandu" name="commConfigLocationId3" id="commConfigLocationId3" onkeypress="huiche()" onchange="setTown('empi/securityUserBaseinfo.do'),zhankai()"> 
		            <%
					if(data.getCommConfigLocationId3s() != null && data.getCommConfigLocationId3s().length > 0){
						for(int i = 0; i < data.getCommConfigLocationId3s().length; i++){
							String tempId = data.getCommConfigLocationId3s()[i];
							String tempName = data.getCommConfigLocationId3_names()[i];
					%>
		        	<option value="<%=tempId %>" <%=tempId.equals(data.getCommConfigLocationId3()) ? "selected" : "" %>>
						<%=tempName %>
					</option>
					<%
						}
					}
					%>  
	    		</select>    
			</div>
			
			<div id="cun1" style="display: none;background:#F7F7F7;" class="tblLable">
				<span>*</span><bean:message key="security.jsp.commom.commConfigLocationTownId" bundle="security"/>：
			</div>
			<div id="cun2" style="display: none">
				<select name="commConfigLocationTownId" class="kuandu" id="commConfigLocationTownId," onkeypress="huiche()" onchange="zhankai()">
							<%
								if (data.getCommConfigLocationTownIds() != null
										&& data.getCommConfigLocationTownIds().length > 0) {
									for (int i = 0; i < data.getCommConfigLocationTownIds().length; i++) {
										String tempId = data.getCommConfigLocationTownIds()[i];
										String tempName = data.getCommConfigLocationTownId_names()[i];
							%>
							<option value="<%=tempId%>"
								<%=tempId.equals(data.getCommConfigLocationTownId()) ? "selected"
									: ""%>><%=tempName%></option>
							<%
								}
								}
							%>
				</select>
			</div>
			
			
			<div id="quanxianxuanzhe" style="border:1px solid #BFBFBF;text-align: left;" align="center" class="securityRoleVsMenus_div_1">
				<%if(data.getSdotIds()!=null&&data.getSdotIds().length>0){ %>
				<script language="javascript">
					var tree = new MzTreeView("tree");
					tree.setIconPath("include/javascript/treeView/");
					tree.nodes["-1_<%=data.getParentId()%>"]="text:<%=data.getParentName()%>;";
					<%
						for(int i=0;i<data.getSdotIds().length;i++){
							String parentId=data.getParentId();
							String value=data.getSdotIds()[i];
							String name=data.getSdoValues()[i];
							String temp="ctrl:true;ctrlName:menuIdSelected;ctrlValue:" + value + ";ctrlChecked:" ;
							String check=data.getChecks()[i];
							if(check.trim().equals("0")){
								temp += "false;";
							}else{
								temp += "true;";
							}
							temp += "text:"+name+";";
					%>
						tree.nodes["<%=parentId%>_<%=value%>"]="<%=temp%>";
					<%
						}
					%>
					tree.wordLine = false;
					document.write(tree.toString()); 
					tree.expandAll();
				</script>
				<%} %>
			</div>
			
			<div class="btnSave">
				<input type="button" id="saveinput"  name="btnSaveForm" value="<bean:message key="security.jsp.commom.button1" bundle="security"/>" onClick="save('<%=request.getContextPath() %>')" />
				<input type="button"  name="btnBack" value="<bean:message key="security.jsp.commom.button2" bundle="security"/>" onClick="history.go(-1);" />
			</div>
  	</form>
  </body>
</html>
