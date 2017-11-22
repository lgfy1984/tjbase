<%@page contentType="text/html; charset=utf-8"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<jsp:useBean id="data" scope="request" class="com.tianjian.hsp.struts.form.HspConfigBaseinfoMergeForm" />
<jsp:useBean id="pb" scope="request" class="com.tianjian.util.comm.PageBean" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<%
		if (request.getServerPort() == 80) {
		%>
		<base
			href="http://<%=request.getServerName()%><%=request.getContextPath()%>/">
		<%
		} else {
		%>
		<base
			href="http://<%=request.getServerName()%>:<%=request.getServerPort()%><%=request.getContextPath()%>/">
		<%
		}
		%>
		<title>查询页面</title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<script language="javascript" src="<%=request.getContextPath()%>/comm/include/javascript/CommMessage.js"></script>
		
		<link rel="stylesheet" href="include/calendar/theme.css" />
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
			function saveForm(idHidden){
				 document.form.idHidden.value = idHidden;
			     var ChkEls=document.getElementsByName("id");
				 var checkedIds="";
				 for(var i=0;i < ChkEls.length;i++){
				 	 if(ChkEls.item(i).type != "checkbox")continue;
				 		 var oEl = ChkEls.item(i);
				  		 if(oEl.checked) {
				   			checkedIds = checkedIds + (oEl.value + ",");
				  		 }
				 }
				 document.form.checkValue.value = checkedIds;
				 if (checkedIds == ""){  
					 alert("请选择对比条件");
					 return false;
				 }
			    
			    document.form.method = "POST";
				document.form.verbId.value = "compareQuery";
				document.form.submit();
			}
			function submitQueryForm() { 
			  document.form.method = "POST";
			  document.form.verbId.value = "query";
			  document.form.submit();
			}

			function goPage(page) {  
			   document.form.page.value = page;
			   document.form.verbId.value = "query";    
			   document.form.submit();
			}
			
			function goPage2() {
			  
			  with(document.all) {
			    if (!isMadeOf(_tp.value,'1234567890')) {
			      alert("错误的整数!");
			      return;
			    }
			    if (_tp.value<=0){
			    	alert("页数必须大于0!");
					return;
			    }
			    if(parseInt(_tp.value)>parseInt(_total.value)){
			      alert("不能大于总页数!");
			      return;
			    } 
			  }  
			  document.form.verbId.value = "query";    
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
			function commandOrderBy(order, asc) { 
			  	document.form.orderNo.value = order; 
			  	document.form.asc.value = asc; 
			  	document.form.method = "POST";
			  	document.form.verbId.value = "query";
			  	document.form.submit();
			}	
</script>
		<link type="text/css" rev="stylesheet" rel="stylesheet" href="include/css/form.css" />
	</head>
	<body onload="showCommMessage('','<%=data.getMessage() %>','1')">
		<!-- Form中的action项的值必须设置-->
		<form name="form" method="post" action="<%=request.getContextPath()%>/hsp/hspConfigBaseinfoMerge.do">
			<!-- Head line -->
			<input type="hidden" name="queryFlag" value="queryFlag" />
			<input type="hidden" name="verbId" value="<%=data.getVerbId()%>" />
			<input type="hidden" name="orderNo" value="<%=data.getOrderNo()%>" />
			<input type="hidden" name="asc" value="<%=data.getAsc()%>" />
			<input type="hidden" name="idHidden" value="<%=data.getId()%>" />
			<input type="hidden" name="checkValue" value="<%=data.getCheckValue()%>" />

			<!--查询条件-->
			<table  border="0" cellpadding="0" cellspacing="0" class="tblSearch" align="center">
				<tr>
					<td class="tblTitle" colspan="4">
						<span>※</span>卫生机构信息<span>※</span>
					</td>
				</tr>
				<tr>
					<td>
						<input type="checkbox" name="id" value="itemName"  />机构名称：
						<input name="name" id="id" type="text"  value="<%=data.getItemName()%>" />
						<input type="checkbox" name="id" value="itemAddress"   />机构地址：
						<select name="commConfigLocationId1" onkeydown="huiche()" id="commConfigLocationId1"
							onchange="setCity('comm/commConfigLocationGroup.do')">
							<option value=""></option>
							<%
								if (data.getCommConfigLocationId1s() != null
										&& data.getCommConfigLocationId1s().length > 0) {
									for (int i = 0; i < data.getCommConfigLocationId1s().length; i++) {
										String tempId = data.getCommConfigLocationId1s()[i];
										String tempName = data.getCommConfigLocationId1_names()[i];
							%>
							<option value="<%=tempId%>"
								<%=tempId.equals(data
											.getCommConfigLocationId1()) ? " selected"
									: ""%>><%=tempName%></option>
							<%
								}
								}
							%>
						</select>省（市）<select name="commConfigLocationId2" onkeydown="huiche()" id="commConfigLocationId2"
							onchange="setCounty('comm/commConfigLocationGroup.do')">
							<option value=""></option>
							<%
								if (data.getCommConfigLocationId2s() != null
										&& data.getCommConfigLocationId2s().length > 0) {
									for (int i = 0; i < data.getCommConfigLocationId2s().length; i++) {
										String tempId = data.getCommConfigLocationId2s()[i];
										String tempName = data.getCommConfigLocationId2_names()[i];
							%>
							<option value="<%=tempId%>"
								<%=tempId.equals(data.getCommConfigLocationId2()) ? " selected"	: ""%>><%=tempName%></option>
							<%
								}
								}
							%>
						</select>市（地区）<select name="commConfigLocationId3" onkeydown="huiche()" id="commConfigLocationId3"
							onchange="setTown('comm/commConfigLocationGroup.do')">
							<option value=""></option>
							<%
								if (data.getCommConfigLocationId3s() != null
										&& data.getCommConfigLocationId3s().length > 0) {
									for (int i = 0; i < data.getCommConfigLocationId3s().length; i++) {
										String tempId = data.getCommConfigLocationId3s()[i];
										String tempName = data.getCommConfigLocationId3_names()[i];
							%>
							<option value="<%=tempId%>"<%=tempId.equals(data.getCommConfigLocationId3()) ? "selected": ""%>><%=tempName%></option>
							<%
								}
								}
							%>
						</select>县（区）
						输入码：
						<input name="inputCode" id="inputCode" type="text" value="<%=data.getInputCode()%>" />
						<input type="button" class="btnSave" name="查询" value="查&nbsp&nbsp询" onClick="submitQueryForm();" />
					</td>
				</tr>
			</table>
			<!--列表标题-->
			<div id="dvh1">
			<table border="0" cellpadding="0" cellspacing="0" class="tblSearchList" align="center">
				<caption style="text-align:left;">
					<img src="<%=request.getContextPath()%>/comm/include/images/comm_list_nav_red.jpg" width="20" height="20" align="absmiddle" /> <span style="font-weight:bold; color:#333;"><bean:message  bundle="comm.commLocale" key="comm.jsp.commom.selectList"/></span>
			    </caption>
				  <thead>
			    	<tr class="lstName">
			    		<th width="5%" height="26">机构代码<div><img src="include/images/cmdOrderByAsc.gif" alt="Ascender" vspace="7" onclick="commandOrderBy('1', '0')" /><img border="0" onclick="commandOrderBy('1', '1')" alt="Descender" src="include/images/cmdOrderByDesc.gif" /></div></th>
			            <th width="5%" height="26">机构名称<div><img src="include/images/cmdOrderByAsc.gif" alt="Ascender" vspace="7" onclick="commandOrderBy('2', '0')" /><img border="0" onclick="commandOrderBy('2', '1')" alt="Descender" src="include/images/cmdOrderByDesc.gif" /></div></th>
			            <th width="4%" height="26">机构地址<div><img src="include/images/cmdOrderByAsc.gif" alt="Ascender" vspace="7" onclick="commandOrderBy('3', '0')" /><img border="0" onclick="commandOrderBy('3', '1')" alt="Descender" src="include/images/cmdOrderByDesc.gif" /></div></th>
			            <th width="5%" height="26">序号<div><img src="include/images/cmdOrderByAsc.gif" alt="Ascender" vspace="7" onclick="commandOrderBy('4', '0')" /><img border="0" onclick="commandOrderBy('4', '1')" alt="Descender" src="include/images/cmdOrderByDesc.gif" /></div></th>
			          	<th width="5%" height="26">输入码<div><img src="include/images/cmdOrderByAsc.gif" alt="Ascender" vspace="7" onclick="commandOrderBy('5', '0')" /><img border="0" onclick="commandOrderBy('5', '1')" alt="Descender" src="include/images/cmdOrderByDesc.gif" /></div></th>
			          	<th width="3%" height="26">对比</th>
			        </tr>
				</thead>
			
			<!--列表内容-->
				<tbody id="interval_row_id">
					<%if (data.getIdArray() != null && data.getIdArray().length > 0) {
						for (int i = 0; i < data.getIdArray().length; i++) {
					%>
						<tr>
							<td>
								<%=data.getItemCodeArray()[i] %>
							</td>
							<td style="text-align:left;padding-left:10px;">
								<%=data.getItemNameArray()[i]%>
							</td>
							<td style="text-align:left;padding-left:10px;">
								<%=data.getItemAddressArray()[i]%>
							</td>
							<td>
								<%=data.getSeqNoArray()[i] %>
							</td>
							<td style="text-align:left;padding-left:10px;">
								<%=data.getInputCodeArray()[i] %>
							</td>
							<td>
								<img onClick="saveForm('<%=data.getIdArray()[i]%>')" alt="<bean:message  bundle="comm.commLocale" key="comm.jsp.commom.detail"/>" src="include/images/cmdView_s.jpg" border="0" style="cursor: hand; vertical-align: middle;" />
							</td>
					</tr>
					<%
						}
						}
					%>
				</tbody>
				</table>
				</div>
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
						<input type="hidden" id="pageId" name="pageId" value="page_2828810b3a6785e1013a6785e1ba0000"/>
						<input type="hidden" id="reHref" name="reHref" value="<%=request.getContextPath()%>/empi/empicustomcompare.do?verbId=query"/>
						<%@ include file="/include/changepagesize.jsp" %>
						<input id="_total" name="totalPage" type="hidden" value="<%=totalPage%>"/>
						第
						<%=curPage%>
						页/共
						<%=totalPage%>
						页&nbsp;共
						<%=totalNum%>
						条记录&nbsp;|&nbsp;
						<%
						if (curPage > 1) {
						%>
						<a href="javascript:goPage('0')"><img src="include/images/shouye.gif" align="middle" border="0" /></a>&nbsp;&nbsp;<a href="javascript:goPage('<%=curPage - 1%>')"><img src="include/images/shang.gif" align="middle" border="0" /></a>&nbsp;
						<%
								} else {
								out
								.println("<img src='include/images/shouye_s.gif' align='middle'  border='0' />&nbsp;&nbsp;<img src='include/images/shang_s.gif' align='middle'  border='0' />&nbsp;");
							}
							if (curPage < totalPage) {
						%>
						<a href="javascript:goPage('<%=curPage + 1%>')"><img src="include/images/xia.gif" align="middle" border="0" /></a>&nbsp;&nbsp;<a href="javascript:goPage('<%=totalPage%>')"><img src="include/images/mo.gif" align="middle" border="0" /></a>
						<%
								} else {
								out
								.println("<img src='include/images/xia_x.gif' align='middle' border='0' />&nbsp;&nbsp;<img src='include/images/mo_m.gif' align='middle'  border='0' />");
							}
						%>
						| &nbsp;到第
						<input id="_tp" name="page" type="text" value="<%=curPage%>"
							size="2" class="txt"/>
						页&nbsp;
						<img style="cursor: hand;" src="include/images/go.jpg" width="18"
							border="0" onClick="goPage2()" />

					</td>
				</tr>
			</table>
		</form>
		<script>	
		 Calendar.setup({
			  inputField    : "dateOfBirth",
			  button        : "dateOfBirthButton",
			  ifFormat      : "%Y-%m-%d",
			  showsTime     :  false
			});
		 
		</script>
		<script language="javascript"
			src="include/javascript/interval_row_color.js"></script>
</html>
