<%@page contentType="text/html; charset=utf-8"%>
<%@page import="com.tianjian.util.*"%>
<%@page import="com.tianjian.comm.bean.CommDictPublicChar"%>
<%@page import="com.tianjian.hsp.bean.*"%> 
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<jsp:useBean id="data" scope="request" type="com.tianjian.hsp.struts.form.HspConfigBaseinfoForm" />
<jsp:useBean id="pb" scope="request" class="com.tianjian.util.comm.PageBean" />
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
	<title><bean:message  bundle="hsp.hspLocale" key="hsp.jsp.common.itemInfoSafeguard"/></title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
	<script language="javascript" src="<%=request.getContextPath()%><bean:message  key="hsp.js.commCommMessage.path"  bundle="hsp.hspLocale"/>"></script>
	<script language="javascript">
	
		function selectAll(){
		var checkbox = document.getElementsByName('checkbx');
		if(form.checkB.checked){
			for(i=0; i<checkbox.length; i++){
				checkbox[i].checked = true;
			}
		}else{
			for(i=0; i<checkbox.length; i++){
				checkbox[i].checked = false;
			}
		}
	}			
	//新增初始化工作，转到新增页面
	function addInitForm(){
		document.form.verbId.value = "addInit";
		document.form.submit();
	}
	//删除时候用
	function deleteForm(id){ 
		var len = 0;
		if(id!=null && id!=""){
			   	len ++; 
		}
		if(len > 0){
			 if (!confirm("<bean:message  bundle="hsp.hspLocale" key="hsp.jsp.common.warn17"/>")) return false; 
			 document.form.idHidden.value = id;
			 document.form.verbId.value = "delete";
			 document.form.submit();
		}else{
			alert("<bean:message  bundle="hsp.hspLocale" key="hsp.jsp.common.warn4"/>!");
			return false;
		}
	}
	//查询时候用
	function queryForm(num){
		if(num == 1){
	    	for(i=0; i<argArray.length; i++){
				document.getElementById(argArray[i]).value = document.getElementById(argArray[i]+"2").value;		
			}	
	    }else if(num == 0){
	    	var searchdiv = document.getElementById("searchDiv");
	    	var divinput = searchdiv.getElementsByTagName("input");
	    	var divselect = searchdiv.getElementsByTagName("select");
	    	for(i=0; i<divinput.length; i++){
				divinput[i].value = "";		
			}
			for(j=0; j<divselect.length; j++){
				divselect[j].value = "";		
			}
	    }	
		document.form.page.value = 1; 
	  	document.form.orderNo.value = ""; 
	 	document.form.asc.value = ""; 
	  	document.form.method = "POST";
	  	document.form.verbId.value = "query4Web";
	  	document.form.submit();
	}
	//查看详细时候用
	function viewForm(id){
		var array =  id;
		var idHidden =id;
		var len = 0;
		if(array!=null && array!=""){ 
					len ++;  
		}
		if(len == 1){
			document.form.idHidden.value = id;
			document.form.verbId.value = "detail";
			document.form.submit();
		}
	}
	//更新准备时候用
	function updateInitForm(id){
		var array = id
		var idHidden =id;
		var len = 0;
		if(id!=null && id!=""){ 
					len ++; 
		}
		if(len == 1){
			document.form.idHidden.value = id;
			document.form.verbId.value = "updateInit";
			document.form.submit();
		}else if(len <= 0){
			alert("<bean:message  bundle="hsp.hspLocale" key="hsp.jsp.common.warn4"/>!");
			return false;
		}else if(len > 1){
			alert("<bean:message  bundle="hsp.hspLocale" key="hsp.jsp.common.warn6"/>!");
			return false;
		}
	}	
	//------------------		
	function commandOrderBy(order, asc) { 
	  	document.form.orderNo.value = order; 
	  	document.form.asc.value = asc; 
	  	document.form.method = "POST";
	  	document.form.verbId.value = "query4Web";
	  	document.form.submit();
	}
	function goPage(page) {  
	   document.form.page.value = page;
	   document.form.verbId.value = "query4Web";    
	   document.form.submit();
	}			
	function goPage2() {			  
	  var _tp=document.getElementById('_tp');
  var _total=document.getElementById('_total'); 
	    if (!isMadeOf(_tp.value,'1234567890')) {
	      alert("<bean:message  bundle="hsp.hspLocale" key="hsp.jsp.common.wrongInteger"/>!");
	      return;
	    }
	    if (_tp.value<=0){
	    	alert("<bean:message  bundle="hsp.hspLocale" key="hsp.jsp.common.pageMustBigThanZero"/>!");
			return;
	    }
	    if(parseInt(_tp.value)>parseInt(_total.value)){
	      alert("<bean:message  bundle="hsp.hspLocale" key="hsp.jsp.common.cannotBigThanTotal"/>");
	      return;
	    } 
	    
	  document.form.verbId.value = "query4Web";    
	  document.form.submit();
	}			
	function isMadeOf(val,str){
		var jj;
		var chr;
		for (jj=0;jj<val.length;++jj){
			chr=val.charAt(jj);
			if (str.indexOf(chr,0)==-1)
				return false;
		}			
		return true;
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
			clv.add(newElem);
		}
	}
	function displays(arg){
			var divdisplay = document.getElementById("hightsear");
			if(divdisplay.style.display == "none"){
				divdisplay.style.display = "block";
				arg.innerHTML = '<img src="<%=request.getContextPath()%>/stat/include/images/Cnext.gif" />';
			}else{
				divdisplay.style.display = "none";
				arg.innerHTML = '<img src="<%=request.getContextPath()%>/stat/include/images/Cprevious.gif" />';
			}
		}
</script>
	<script language="javascript">
	
	  function huiche(){
		if(event.keyCode==13){
			event.keyCode=9
		}
		
	} 
		</script>
<link rel="stylesheet" href="<%=request.getContextPath()%>/include/css/form.css" />
<script language="javascript" src="<%=request.getContextPath()%>/ciha/include/javascript/gettext_staff.js"></script>
<link rel="stylesheet" href="<%=request.getContextPath()%>/include/css/open.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/include/javascript/searchwindow.js"></script>	
<script type="text/javascript" src="<%=request.getContextPath()%>/include/javascript/jianbian.js"></script>
<link rel="stylesheet" href="<%=request.getContextPath()%>/include/css/searchwindow.css" />	
</head>
<body>
<!-- Form中的action项的值必须设置-->
<form name="form" method="post" action="<%=request.getContextPath()%>/hsp/hspConfigBaseinfo.do">
	<!-- Head line -->
	<input type="hidden" name="verbId" value="<%=data.getVerbId()%>" />
	<input type="hidden" name="orderNo" value="<%=data.getOrderNo()%>" />
	<input type="hidden" name="asc" value="<%=data.getAsc()%>" />
	<input type="hidden" name="idHidden" value="<%=data.getIdHidden()%>" />
	<!--查询条件-->
	<table width="829" align="center" style="background-color:#828283;" cellpadding="0" cellspacing="1" border="0">
		<tr>
			<td class="searchBg"><bean:message  bundle="hsp.hspLocale" key="hsp.jsp.common.selectCondition"/></td>
		</tr>				
		<tr>
			<td class="date_img">
				<bean:message  bundle="hsp.hspLocale" key="hsp.jsp.hspconfigbaseinfolist4web.code"/>：<input name="itemCode" id="itemCode" type="text" onkeydown="huiche()" class="input" value="<%=data.getItemCode()%>" />
				<bean:message  bundle="hsp.hspLocale" key="hsp.jsp.common.mingch1"/>：<input name="itemName" id="itemName" type="text" style="width:250px;" onkeydown="huiche()" class="input" value="<%=data.getItemName()%>" />
				<bean:message  bundle="hsp.hspLocale" key="hsp.jsp.hspconfigbaseinfolist4web.inputCode"/>：<input name="inputCode" id="inputCode" type="text"   class="input"   onkeydown="if(event.keyCode==13){event.keyCode=9;document.getElementById('queryImg').className='queryImg_down'}"  value="<%=data.getInputCode()%>" />				
				<input type="button" style="cursor: hand; background: url(include/images/tong.gif) no-repeat; border: 0; width: 50px; height: 23px; font-size: 12px;" name="<bean:message  bundle="hsp.hspLocale" key="hsp.jsp.common.search2"/>" value="<bean:message  bundle="hsp.hspLocale" key="hsp.jsp.common.search1"/>" onclick="queryForm(0);" />
				<input type="button" style="cursor: hand; background: url(include/images/tong.gif) no-repeat; border: 0; width: 50px; height: 23px; font-size: 12px;" name="<bean:message  bundle="hsp.hspLocale" key="hsp.jsp.common.highGrade1"/>" value="<bean:message  bundle="hsp.hspLocale" key="hsp.jsp.common.highGrade"/>" onclick="searchMore('itemCode#itemName#inputCode');" />
				<div id="searchDiv" style="display:none;">
					<table border="0" width="600" cellspacing="0" cellpadding="0" align="center" style="font-size: 14px; margin: 0px 0px 1px 0px; text-align: center; border: 1px solid #BFBFBF;">
						<tr>
							<td height="25" colspan="6" class="trbg"><bean:message  bundle="hsp.hspLocale" key="hsp.jsp.common.highGradeSearch"/></td>
						</tr>				
						<tr>
							<td width="87" height="35" align="right">
								<bean:message  bundle="hsp.hspLocale" key="hsp.jsp.common.code"/>：
							</td>
							<td align="left" width="105">
								<input type="text" style="width:95px;" id="itemCode2" name="itemCode2" value="" onkeydown="huiche()"/>
							</td>							
							<td width="87" height="35" align="right">							
								<bean:message  bundle="hsp.hspLocale" key="hsp.jsp.common.mingch"/>：
							</td>
							<td align="left" width="105">
								<input type="text" style="width:95px;" id="itemName2" name="itemName2" value="" onkeydown="huiche()"/>
							</td>
							<td width="100" height="35" align="right">									
								<bean:message  bundle="hsp.hspLocale" key="hsp.jsp.common.inputCode"/>：
							</td>
							<td align="left" width="116">
								<input type="text" style="width:95px;" id="inputCode2" name="inputCode2" value="" onkeydown="huiche()"/>
							</td>
						</tr>						
						<tr>
							<td height="33" align="right">									
								<bean:message  bundle="hsp.hspLocale" key="hsp.jsp.common.parentItem"/>：
							</td>
							<td align="left" colspan="3">
								<input type="text" id="displayInputId_1" name="parentItemCode_name" style="width:262px;" value="<%=data.getParentItemName() %>" readonly="true" onkeydown="huiche()" />
						 	   <!--这是准备存储到数据的字段-->
								<input type="hidden" id="hiddenInputId_1" value="<%=data.getParentItemCode() %>" name="parentItemCode" />
								<!--弹出的选择按钮，add(arg1,arg2,arg3)其中arg1代表基本录入框的id，arg2代表保存存储到数据库字段对应的input的id
								arg3代表需要传递到.do的数据库检索参数
								-->
								<!-- hspType 1为除去卫生服务站2为只包括服务站和服务中心3为所有 -->
								<img src="<%=request.getContextPath()%>/ciha/include/images/select.gif" style="cursor:pointer;" onclick="add('<%=request.getContextPath()%>/hsp/hspConfigBaseinfo.do?verbId=getHsp&hspType=1','displayInputId_1','hiddenInputId_1')" />
							</td>
							<td height="33" align="right">									
								<bean:message  bundle="hsp.hspLocale" key="hsp.jsp.common.itemGrade"/>：
							</td>
							<td align="left">
								<select name="commConfigUnitgradeId" style="width:99px;" onkeydown="huiche()" id="commConfigUnitgradeId" onkeypress="eventOnKeyPress('commConfigEconkindId')">
									<option></option>
										<%if(data.getC010501aList().size()>0 && data.getC010501aList()!=null){
										for(int i=0;i<data.getC010501aList().size();i++){
										     
										     
										%>
									  <option value="<%=((CommDictPublicChar)data.getC010501aList().get(i)).getId()%>"><%=((CommDictPublicChar)data.getC010501aList().get(i)).getDictValue()%></option>
									  <%}
										
										} %>									 
								</select>
							</td>
						</tr>
						<tr>
							<td height="33" align="right">									
								<bean:message  bundle="hsp.hspLocale" key="hsp.jsp.common.itemSort"/>：
							</td>
							<td align="left" colspan="3">
								<input type="text" id="displayInputId_2" name="11" style="width:262px;" value="" readonly="true"  onkeydown="huiche()" />
						 	    <!--这是准备存储到数据的字段-->
								<input type="hidden" id="hiddenInputId_2" value="" name="commConfigHospitalTypeId" />
								<!--弹出的选择按钮，add(arg1,arg2,arg3)其中arg1代表基本录入框的id，arg2代表保存存储到数据库字段对应的input的id
								arg3代表需要传递到.do的数据库检索参数
								-->
								<!-- hspType 1为除去卫生服务站2为只包括服务站和服务中心3为所有 -->
								<img src="<%=request.getContextPath()%>/ciha/include/images/select.gif" style="cursor:pointer;" onclick="add('<%=request.getContextPath()%>/comm/commConfigHospitalType.do?verbId=getHspType&hspTypeLevel=3','displayInputId_2','hiddenInputId_2')" />
							</td>						
							<td height="33" align="right">									
								<bean:message  bundle="hsp.hspLocale" key="hsp.jsp.common.itemDegree"/>：
							</td>
							<td align="left">
								<select name="commConfigUnittypeId" style="width:99px;" onkeydown="huiche()" id="commConfigUnittypeId" onkeypress="eventOnKeyPress('commConfigUnitgradeId')">
									<option />
									<%if(data.getC010501bList().size()>0 && data.getC010501bList()!=null){
										for(int i=0;i<data.getC010501bList().size();i++){
										     
										     
										%>
									  <option value="<%=((CommDictPublicChar)data.getC010501bList().get(i)).getId()%>" /><%=((CommDictPublicChar)data.getC010501bList().get(i)).getDictValue()%>
									  <%}
										
										} %>	
								</select>
							</td>
						<tr>
							<td align="right">									
								<bean:message  bundle="hsp.hspLocale" key="hsp.jsp.common.itemGroup"/>：
							</td>
							<td align="left">
								<select name="commConfigFtManageId" style="width:99px;"  onkeydown="huiche()"
							id="commConfigFtManageId"
							onkeypress="eventOnKeyPress('commConfigUnitgradeId')">
							<%
										if (data.getCommConfigFtManageIds() != null
										&& data.getCommConfigFtManageIds().length > 0) {
									for (int i = 0; i < data.getCommConfigFtManageIds().length; i++) {
										String tempId = data.getCommConfigFtManageIds()[i];
										String tempName = data.getCommConfigFtManageId_names()[i];
							%>
							<option value="<%=tempId%>" <%=tempId.equals(data.getCommConfigFtManageId()) ? "selected" : ""%>>
								<%=tempName%>
							</option>
							<%
								}
								}
							%>
						</select>
							</td>
							<td align="right">									
								<bean:message  bundle="hsp.hspLocale" key="hsp.jsp.common.econkind"/>：
							</td>
							<td align="left">
								<select name="commConfigEconkindId" style="width:99px;" onkeydown="huiche()"
							id="commConfigEconkindId"
							>
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
							<td height="33" align="right">									
								<bean:message  bundle="hsp.hspLocale" key="hsp.jsp.hspconfigbaseinfodetail.cclt"/>：
							</td>
							<td align="left">
								<select name="commConfigLocationTownId" style="width:99px;" onkeydown="huiche()"
							id="commConfigLocationTownId"
							onkeypress="eventOnKeyPress('commClvId')"
							onchange="setVillage('comm/commConfigLocationGroup.do')">
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
						<tr>
							<td align="right">									
								<bean:message  bundle="hsp.hspLocale" key="hsp.jsp.common.clv"/>：
							</td>
							<td align="left">
								<select name="commClvId" style="width:99px;" onkeydown="huiche()"
							id="commClvId"
							onkeypress="eventOnKeyPress('address')"
							>
							<%
										if (data.getCommClvIds() != null
										&& data.getCommClvIds().length > 0) {
									for (int i = 0; i < data.getCommClvIds().length; i++) {
										String tempId = data.getCommClvIds()[i];
										String tempName = data.getCommClvId_names()[i];
							%>
							<option value="<%=tempId%>"
								<%=tempId.equals(data
											.getCommClvId()) ? "selected"
									: ""%>>
								<%=tempName%>
							</option>
							<%
								}
								}
							%>
						</select>
							</td>
							<td align="right"></td>
							<td align="left"></td>
							<td align="right"></td>
							<td align="left"></td>
						</tr>								
						<tr>
							<td height="35" colspan="8" align="center" style="background-color:#F7F7F7">
								<input type="button" style="cursor: hand; background: url(include/images/tong.gif) no-repeat; border: 0; width: 50px; height: 23px; font-size: 12px;" name="<bean:message  bundle="hsp.hspLocale" key="hsp.jsp.common.close1"/>" value="<bean:message  bundle="hsp.hspLocale" key="hsp.jsp.common.close"/>" onclick="searchclosed();" />								
								<input type="button" style="cursor: hand; background: url(include/images/tong.gif) no-repeat; border: 0; width: 50px; height: 23px; font-size: 12px;" name="<bean:message  bundle="hsp.hspLocale" key="hsp.jsp.common.search2"/>" value="<bean:message  bundle="hsp.hspLocale" key="hsp.jsp.common.search1"/>" onclick="queryForm(1);" />
							</td>								
						</tr>
						
					</table>
				</div>
			</td>
		</tr>
	</table>
	<table width="829" align="center" cellpadding="0" cellspacing="0" border="0">
		<tr>
			<td height="4" align="left" style="padding-top:4px;">
			</td>
		</tr>			
	</table>
	<table class="list_table" align="center" cellpadding="0" cellspacing="1" border="0">
		<tr class="tdnav"> 
			<td width="130">
				<bean:message  bundle="hsp.hspLocale" key="hsp.jsp.common.itemCode"/>
			</td>
			<td width="260">
				<bean:message  bundle="hsp.hspLocale" key="hsp.jsp.common.itemName"/>
			</td>
			<td width="120">
				<bean:message  bundle="hsp.hspLocale" key="hsp.jsp.common.itemGrade1"/>
			</td>
			<td width="100">
				<bean:message  bundle="hsp.hspLocale" key="hsp.jsp.common.itemDegree1"/>
			</td>
			<td width="80">
				<bean:message  bundle="hsp.hspLocale" key="hsp.jsp.common.seqNo"/>
			</td>
			<td width="30">
				<bean:message  bundle="hsp.hspLocale" key="hsp.jsp.common.search"/>
			</td>
		</tr>
		<tbody id="interval_row_id">
		
				
			<%if (data.getIdList() != null
					&& data.getIdList().length > 0) {
				for (int i = 0; i < data.getIdList().length; i++) 
			{
			%>
			<tr class="list_neirong"> 
				<td class="td_shenglue leftPadding">
					<%=data.getItemCodeList()[i]%>
				</td>
				<td class="td_shenglue leftPadding">
					<%=data.getItemNameList()[i]%>
				</td>
				<td class="td_shenglue leftPadding">
				  <%if(data.getC010501aList().size()>0 && data.getC010501aList()!=null && data.getCommConfigUnitgradeIdList()[i]!=null){
						for(int k=0;k<data.getC010501aList().size();k++){
						       String veryId ="";
						       if(data.getCommConfigUnitgradeIdList()[i]!=null && data.getCommConfigUnitgradeIdList()[i]!="" ){
						       veryId = data.getCommConfigUnitgradeIdList()[i];
						      }
					          String tempId = ((CommDictPublicChar )data.getC010501aList().get(k)).getId();
					          String tempName=((CommDictPublicChar ) data.getC010501aList().get(k)).getDictValue();
					          if(veryId.equalsIgnoreCase(tempId)){  
						     
						%>
						 <%=((CommDictPublicChar)(data.getC010501aList().get(k))).getDictValue()%>
						<%} 
						  }
						} %>    
				</td>
				<td class="td_shenglue leftPadding">
					<%if(data.getC010501bList().size()>0 && data.getC010501bList()!=null && data.getCommConfigUnittypeIdList()[i]!=null){
						for(int j=0;j<data.getC010501bList().size();j++){
						     String veryId="";
						     if(data.getCommConfigUnittypeIdList()[i]!=null &&  data.getCommConfigUnittypeIdList()[i]!=""){
						        veryId = data.getCommConfigUnittypeIdList()[i];
						      }
					          String tempId = ((CommDictPublicChar )data.getC010501bList().get(j)).getId();
					          String tempName= ((CommDictPublicChar )data.getC010501bList().get(j)).getDictValue();
					          if(veryId.equalsIgnoreCase(tempId)){ 
						%>
						 <%=((CommDictPublicChar)data.getC010501bList().get(j)).getDictValue()%>
						<%} 
						 }
						} %>
				</td>
				<td class="td_shenglue ">
					<%=data.getSeqNoList()[i]%>
				</td>
				<td class="td_shenglue ">
					 <img
					src="<%=request.getContextPath()%>/hsp/include/images/cmdView_s.jpg"
					style="cursor:hand; vertical-align:middle;" title="<bean:message  bundle="hsp.hspLocale" key="hsp.jsp.common.select"/>" onclick="viewForm('<%=data.getIdList()[i]%>')" />
				</td>
			</tr>
			<%
				}
				}
			%>
		</tbody>
		<tr>
					<td colspan="6" align="center" bgcolor="#ffffff" height="35px">
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

						<input id="_total" name="totalPage" type="hidden" value="<%=totalPage%>" />
						<bean:message  bundle="hsp.hspLocale" key="hsp.jsp.common.D"/>
						<%=curPage%>
						<bean:message  bundle="hsp.hspLocale" key="hsp.jsp.common.pageTotal1"/>
						<%=totalPage%>
						<bean:message  bundle="hsp.hspLocale" key="hsp.jsp.common.pageTotal"/>
						<%=totalNum%>
						<bean:message  bundle="hsp.hspLocale" key="hsp.jsp.common.record"/>
						<%
						if (curPage > 1) {
						%>
						<a href="javascript:goPage('0')"><img
								src="include/images/shouye.gif" align="middle" border="0" /></a>&nbsp;
						<a href="javascript:goPage('<%=curPage - 1%>')"><img
								src="include/images/shang.gif" align="middle" border="0" /></a>&nbsp;
						<%
								} else {
								out.println("<img src='include/images/shouye_s.gif' align='middle'  border='0px' />&nbsp;&nbsp;<img src='include/images/shang_s.gif' align='middle'  border='0px' />&nbsp;");
							}
							if (curPage < totalPage) {
						%>
						<a href="javascript:goPage('<%=curPage + 1%>')"><img
								src="include/images/xia.gif" align="middle" border="0" /></a>&nbsp;
						<a href="javascript:goPage('<%=totalPage%>')"><img
								src="include/images/mo.gif" align="middle" border="0" /></a>&nbsp;
						<%
								} else {
								out.println("<img src='include/images/xia_x.gif' align='middle' border='0px' />&nbsp;&nbsp;<img src='include/images/mo_m.gif' align='middle'  border='0px' />&nbsp;");
							}
						%>
						<bean:message  bundle="hsp.hspLocale" key="hsp.jsp.common.text1"/>
						<input id="_tp" name="page" type="text" value="<%=curPage%>" size="2" class="txt" />
						<bean:message  bundle="hsp.hspLocale" key="hsp.jsp.common.page"/>&nbsp;
						<img style="cursor: hand;" src="include/images/go.jpg" width="18" border="0" onclick="goPage2()" />
					</td>
				</tr>
			</table>
		</form>
	<script language="javascript" src="include/javascript/interval_row_color.js"></script>
	</body>
</html>
