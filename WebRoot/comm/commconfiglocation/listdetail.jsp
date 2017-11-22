<%@page contentType="text/html; charset=utf-8"%>
<%@page import="com.tianjian.util.comm.PageBean"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<jsp:useBean id="commConfigLocation" scope="request" type="com.tianjian.comm.struts.form.CommConfigLocationForm" />
<jsp:useBean id="pb" scope="request" class="com.tianjian.util.comm.PageBean" />
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
		<title><bean:message key="comm.jsp.different.text8" bundle="conf.comm.comm"/></title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<script language="javascript" src="<bean:message key="include.js.TJMessage.path" bundle="conf.comm.comm"/>"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/include/javascript/searchwindow.js" defer="defer"></script>
		<link rel="stylesheet" href="<%=request.getContextPath()%>/include/css/searchwindow.css" />
		<script language="javascript" src="include/javascript/eventOnKeyPress.js"></script>
		<script language="javascript">

function submitQueryForm() { 
  document.form.page.value = 1; 
  document.form.orderNo.value = ""; 
  document.form.asc.value = ""; 
  document.form.method = "POST";
  document.form.verbId.value = "queryDetail";
  document.form.submit();
}

function commandOrderBy(order, asc) { 
  document.form.orderNo.value = order; 
  document.form.asc.value = asc; 
  document.form.method = "POST";
  document.form.verbId.value = "queryDetail";
  document.form.submit();
}

//添加
function cmdAdd() {
    document.form.verbId.value = "addInit";    
    document.form.submit();
}

//查看详细
function cmdView(id) {
    document.form.idHidden.value = id;  
    document.form.verbId.value = "detail";    
    document.form.submit();
}

//修改
function cmdEdit(id) {
    document.form.idHidden.value = id;  
    document.form.verbId.value = "updateInit";    
    document.form.submit();     
}

//删除
function cmdDel(id) {    
    if (confirmMessage("0-000004")){  
    document.form.idHidden.value = id;  
    document.form.verbId.value = "delete";    
    document.form.submit(); 
    }   
}

function goPage(page) {  
   document.form.page.value = page;
   document.form.verbId.value = "queryDetail";    
   document.form.submit();
}

function goPage2() {
     var _tp=document.getElementById('_tp');
  var _total=document.getElementById('_total'); 
    if (!isMadeOf(_tp.value,'1234567890')) {
      alert("<bean:message key="comm.jsp.common.text8" bundle="conf.comm.comm"/>!");
      return;
    }
    if (_tp.value<=0){
    	alert("<bean:message key="comm.jsp.common.text56" bundle="conf.comm.comm"/>!");
		return;
    }
    if(parseInt(_tp.value)>parseInt(_total.value)){
      alert("<bean:message key="comm.jsp.different.text66" bundle="conf.comm.comm"/>!");
      return;
    } 
  
  document.form.verbId.value = "queryDetail";    
  document.form.submit();
}

function isMadeOf(val,str)
{
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
                 searchclosed();
		  	}
 		 }
</script>
		<link type="text/css" rev="stylesheet" rel="stylesheet" href="include/css/form.css" />
	</head>
	<body>
		<!-- Form中的action项的值必须设置-->
		<form name="form" method="post" action="comm/commConfigLocation.do">
			<!-- Head line -->
			<input type="hidden" name="verbId" value="<%=commConfigLocation.getVerbId()%>">
			<input type="hidden" name="orderNo" value="<%=commConfigLocation.getOrderNo()%>">
			<input type="hidden" name="asc" value="<%=commConfigLocation.getAsc()%>">
			<input type="hidden" name="idHidden" value="<%=commConfigLocation.getIdHidden()%>">
			<!--查询条件-->
			<table border="0" cellpadding="0" cellspacing="0" class="tblSearch" align="center">
				<tr>
					<td class="tblTitle" colspan="4">
						<bean:message key="comm.jsp.different.text6" bundle="conf.comm.comm"/>
					</td>
				</tr>
				<tr>
					<td>
						<bean:message key="comm.jsp.different.text67" bundle="conf.comm.comm"/>：
						<input type="hidden" id="parentId" name="parentId" value="<%=commConfigLocation.getParentId() %>"/>
						<input type="text" id="parentName" name="parentName" value="<%=commConfigLocation.getParentName() %>" style="width: 125px;"/>
						<img src="<%=request.getContextPath() %>/hsp/include/images/select.gif" style="cursor:pointer;" 
										onClick="change(),searchMore('');"  />
						
						<bean:message key="comm.jsp.common.text15" bundle="conf.comm.comm"/>：
						<input name="itemCode" type="text" onkeypress="eventOnKeyPress('inputCode')" value="<%=commConfigLocation.getItemCode()%>">
					
						<bean:message key="comm.jsp.different.text46" bundle="conf.comm.comm"/>：
						<input name="itemName" type="text" onkeypress="eventOnKeyPress('inputCode')" value="<%=commConfigLocation.getItemName()%>">
					
						<bean:message key="comm.jsp.common.text38" bundle="conf.comm.comm"/>：
						<input name="inputCode" type="text" onkeypress="eventOnKeyPress('<bean:message key="comm.jsp.common.text40" bundle="conf.comm.comm"/>')" value="<%=commConfigLocation.getInputCode()%>">
					
						<input type="button"  class="btnSave" name="<bean:message key="comm.jsp.common.text40" bundle="conf.comm.comm"/>" value="<bean:message key="comm.jsp.common.text401" bundle="conf.comm.comm"/>" onClick="submitQueryForm();" />
					</td>
				</tr>
			</table>
			<!--列表标题-->
			<div id="dvh1">
			<table border="0" cellpadding="0" cellspacing="0" class="tblSearchList" align="center">
				<caption style="text-align:left;">
					<img src="<%=request.getContextPath()%>/comm/include/images/comm_list_nav_red.jpg" width="20" height="20" align="absmiddle" /> <span style="font-weight:bold; color:#333;"><bean:message key="jsp.resultlist" bundle="conf.Init"/></span>
			    </caption>
			    <thead>
			    	<tr class="lstName">
			            <th width="5%" height="26"><bean:message key="comm.jsp.common.text15" bundle="conf.comm.comm"/><div><img src="include/images/cmdOrderByAsc.gif" alt="Ascender" vspace="7" onclick="commandOrderBy('1', '0')" /><img border="0" onclick="commandOrderBy('1', '1')" alt="Descender" src="include/images/cmdOrderByDesc.gif" /></div></th>
			            <th width="15%" height="26"><bean:message key="comm.jsp.common.text17" bundle="conf.comm.comm"/><div><img src="include/images/cmdOrderByAsc.gif" alt="Ascender" vspace="7" onclick="commandOrderBy('2', '0')" /><img border="0" onclick="commandOrderBy('2', '1')" alt="Descender" src="include/images/cmdOrderByDesc.gif" /></div></th>
			            <th width="5%" height="26"><bean:message key="comm.jsp.common.text48" bundle="conf.comm.comm"/><div><img src="include/images/cmdOrderByAsc.gif" alt="Ascender" vspace="7" onclick="commandOrderBy('3', '0')" /><img border="0" onclick="commandOrderBy('3', '1')" alt="Descender" src="include/images/cmdOrderByDesc.gif" /></div></th>
			            <th width="15%" height="26"><bean:message key="comm.jsp.different.text67" bundle="conf.comm.comm"/><div><img src="include/images/cmdOrderByAsc.gif" alt="Ascender" vspace="7" onclick="commandOrderBy('4', '0')" /><img border="0" onclick="commandOrderBy('4', '1')" alt="Descender" src="include/images/cmdOrderByDesc.gif" /></div></th>
			            <th width="4%" height="26"><bean:message key="comm.jsp.common.text16" bundle="conf.comm.comm"/><div><img src="include/images/cmdOrderByAsc.gif" alt="Ascender" vspace="7" onclick="commandOrderBy('5', '0')" /><img border="0" onclick="commandOrderBy('5', '1')" alt="Descender" src="include/images/cmdOrderByDesc.gif" /></div></th>
			            <th width="3%" height="26"><bean:message key="comm.jsp.common.text6" bundle="conf.comm.comm"/></th>
			        </tr>
				</thead>
				<tbody id="interval_row_id">
					<%
							if (commConfigLocation.getIdList() != null && commConfigLocation.getIdList().length > 0) {
							for (int i = 0; i < commConfigLocation.getIdList().length; i++) {
					%>
					<tr class="list_neirong">
						<td style="text-align:left;padding-left:10px;">
							<%=commConfigLocation.getItemCodeList()[i]%>
						</td>
						<td style="text-align:left;padding-left:10px;">
							<%=commConfigLocation.getItemNameList()[i]%>
						</td>
						<td style="text-align:left;padding-left:10px;">
							<%=commConfigLocation.getSeqNoList()[i]%>
						</td>
						<td style="text-align:left;padding-left:10px;">
							<%=commConfigLocation.getParentNameList()[i]%>
						</td>
						<td style="text-align:left;padding-left:10px;">
							<%=commConfigLocation.getLevelFlagList()[i]%>
						</td>
						<td>
							<img onClick="cmdView('<%=commConfigLocation.getIdList()[i]%>')" alt="<bean:message key="comm.jsp.common.text46" bundle="conf.comm.comm"/>" src="include/images/cmdView_s.jpg" border="0" style="cursor: hand; vertical-align: middle;" />
						</td>
					</tr>
					<%
						}
						}
					%>
				</tbody>
			</table>
			</div>
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
			<!--列表内容-->
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
						<input type="hidden" id="reHref" name="reHref" value="<%=request.getContextPath()%>/comm/commConfigLocation.do?verbId=queryDetail" />
						<%@ include file="/include/changepagesize.jsp" %>
						
						<input id="_total" name="totalPage" type="hidden" value="<%=totalPage%>">
						<bean:message key="comm.jsp.common.text18" bundle="conf.comm.comm"/>
						<%=curPage%>
						<bean:message key="comm.jsp.common.text55" bundle="conf.comm.comm"/>
						<%=totalPage%>
						<bean:message key="comm.jsp.common.text22" bundle="conf.comm.comm"/>
						<%=totalNum%>
						<bean:message key="comm.jsp.common.text43" bundle="conf.comm.comm"/>&nbsp;|&nbsp;
						<%
						if (curPage > 1) {
						%>
						<a href="javascript:goPage('0')"><img src="include/images/shouye.gif" align="middle" border="0" /> </a>&nbsp;
						<a href="javascript:goPage('<%=curPage - 1%>')"><img src="include/images/shang.gif" align="middle" border="0" /> </a>&nbsp;
						<%
								} else {
								out.println("<img src='include/images/shouye_s.gif' align='middle'  border='0px' />&nbsp;&nbsp;<img src='include/images/shang_s.gif' align='middle'  border='0px' />&nbsp;");
							}
							if (curPage < totalPage) {
						%>
						<a href="javascript:goPage('<%=curPage + 1%>')"><img src="include/images/xia.gif" align="middle" border="0" /> </a>&nbsp;
						<a href="javascript:goPage('<%=totalPage%>')"><img src="include/images/mo.gif" align="middle" border="0" /> </a>&nbsp;
						<%
								} else {
								out.println("<img src='include/images/xia_x.gif' align='middle' border='0px' />&nbsp;&nbsp;<img src='include/images/mo_m.gif' align='middle'  border='0px' />&nbsp;");
							}
						%>
						| &nbsp;<bean:message key="comm.jsp.common.text12" bundle="conf.comm.comm"/>
						<input id="_tp" name="page" type="text" value="<%=curPage%>" size="2" class="txt">
						<bean:message key="comm.jsp.common.text53" bundle="conf.comm.comm"/>&nbsp;
						<img style="cursor: hand;" src="include/images/go.jpg" width="18" border="0" onClick="goPage2()" />
					</td>
				</tr>
			</table>
		</form>
		<script language="javascript" src="include/javascript/interval_row_color.js"></script>
	</body>
</html>
