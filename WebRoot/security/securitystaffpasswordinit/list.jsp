<%@page contentType="text/html; charset=utf-8"%>
<%@page import="com.tianjian.util.comm.TJInit"%>
<jsp:useBean id="securityStaffPasswordInit" scope="request" class="com.tianjian.security.struts.form.SecurityStaffPasswordInitForm" />
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@page import="com.tianjian.util.comm.PageBean"%>
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
		<title><bean:message key="security.jsp.securitystaffpasswordinit.list.title" bundle="security"/></title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<script type="text/javascript" src="include/javascript/TJMessage.js"></script>
		<script type="text/javascript" src="include/javascript/daohang.js"></script>
		<script language="javascript" src="include/javascript/eventOnKeyPress.js"></script>
		<script language="javascript" src="<bean:message key="security.js.gettext_staff.path" bundle="security" />"></script>
		<script type="text/javascript" src="security/include/javascript/jianbian.js"></script>
		<link type="text/css" rev="stylesheet" rel="stylesheet" href="include/css/form.css" />
		<link rel="stylesheet" href="<%=request.getContextPath()%>/hsp/include/css/open.css" />
		<script src="<%=request.getContextPath()%>/include/javascript/searchsuggest.js"></script>
		<link rel="stylesheet" href="<%=request.getContextPath()%>/include/css/searchsuggest.css" />
 		<script type="text/javascript" src="<%=request.getContextPath()%>/include/javascript/searchwindow.js"></script>	
		
		<script language="javascript">
		function confirminit(staffId, url){
				if (confirmMessage("<bean:message key='security.jsp.securitystaffpasswordinit.list.warn' bundle='security'/>?")){     
				   	 cmdPasswordInit(staffId, url);
				   }   
			}
function submitQueryForm() { 
  
  document.form.page.value = 1; 
  document.form.orderNo.value = ""; 
  document.form.asc.value = ""; 
  document.form.method = "POST";
  document.form.verbId.value = "query";
  document.form.submit();
}

function commandOrderBy(order, asc) { 
  
  document.form.orderNo.value = order; 
  document.form.asc.value = asc; 
  document.form.method = "POST";
  document.form.verbId.value = "query";
  document.form.submit();
}

function cmdViewCase(userId, url) {
    url = url + "?userId=" + userId + "&verbId=query";
	window.open(url, "", "height=600, width=1024, top=" + (screen.availHeight-600)/2 + ", left=" + (screen.availWidth-1024)/2 + ", directions=no, toolbar=no, menubar=no, scrollbars=no, resizable=no,location=no, status=yes");
	
}

function cmdViewClinic(userId, url) {
    url = url + "?userId=" + userId + "&verbId=query";
	document.location.href = url;
	
}

function cmdViewDetail(userId, url) {
    url = url + "&staffId=" + userId + "";
	document.location.href = url;
	
}

function openNewWindow(url){
	window.open(url, "", "height=600, width=1024, top=" + (screen.availHeight-600)/2 + ", left=" + (screen.availWidth-1024)/2 + ", directions=no, toolbar=no, menubar=no, scrollbars=no, resizable=no,location=no, status=yes");
}

function cmdViewUser(url){
	document.location.href = url;
}

function goPage(page) {  
   
  document.form.page.value = page;
  document.form.verbId.value = "query";    
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
  document.form.verbId.value = "query";    
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

function showInitMessage(message){
	if(message != ""){	
   	  	alert(message);
	 	return ;
	}	
}
////////////Ajax-start
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
			  function huiche(){
				if(event.keyCode==13){
						event.keyCode=9
					}
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
/////
function cmdPasswordInit(staffId, url){
	var xmlHttp = newXMLHttpRequest();
	url = url + "&staffId=" + staffId + "";	
	xmlHttp.open("GET", url, true);
	
	var securityPasswordInit_handlerFunction = getReadyStateHandler(xmlHttp, securityPasswordInit_update);
	xmlHttp.onreadystatechange = securityPasswordInit_handlerFunction;
	xmlHttp.send(null);
	
}	

function securityPasswordInit_update(xml) {
	var messageXML = xml.getElementsByTagName("message")[0];
	if(messageXML.childNodes[0] != null && messageXML.childNodes[0].nodeValue != null){
		message = messageXML.childNodes[0].nodeValue;
		alert(message);
	}	 
}
function showMessage(message){
				if(message != ''&& message != null){
					alert(message);
					return;
				}
			}

////////Ajax-stop

</script>
	</head>
	<body onload="showMessage('<%=securityStaffPasswordInit.getMessage()%>')">
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
		<!-- Form中的action项的值必须设置-->
		<form name="form" method="post" action="security/securityStaffPasswordInit.do" autocomplete=“off”>
			<!-- Head line -->
			<input type="hidden" name="verbId" value="<%=securityStaffPasswordInit.getVerbId()%>" />
			<input type="hidden" name="orderNo" value="<%=securityStaffPasswordInit.getOrderNo()%>" />
			<input type="hidden" name="asc" value="<%=securityStaffPasswordInit.getAsc()%>" />
			<input type="hidden" name="staffHspId" value="<%=securityStaffPasswordInit.getStaffHspId() %>" />
			<table border="0" cellpadding="0" cellspacing="0" class="tblSearch">
				<tr>
					<td class="tblTitle" colspan="6">
						<bean:message key="security.jsp.securitystaffpasswordinitlist.item" bundle="security"/>
					</td>
				</tr>
				<tr>
					<td>
						<bean:message key="security.jsp.commom.hspConfigBaseinfoName" bundle="security"/>：
						<input type="text" class="input" id="itemName" name="itemName" 
						value="<%=securityStaffPasswordInit.getItemName()%>" 
						onkeyup="GiveOptions(event, '<%=request.getContextPath()%>/searchSuggest.do', 'getHspName_00000000004')"
						onkeydown="huanhang(event)"/>
						<!--  <input type="hidden" id="itemCode"  name="itemCode" value="<%=securityStaffPasswordInit.getItemCode()%>" />-->
				
						&nbsp;&nbsp;<bean:message key="security.jsp.commom.staffCode" bundle="security"/>：
						<input name="staffId" id="staffId" onKeyDown="huiche()" 
							type="text" value="<%=securityStaffPasswordInit.getStaffId()%>" />
						
						
						&nbsp;&nbsp;<bean:message key="security.jsp.commom.name" bundle="security"/>：
						 <input name="name" id="name" 
						 type="text"  value="<%=securityStaffPasswordInit.getName()%>" 
						 onkeyup="GiveOptions(event, '<%=request.getContextPath()%>/searchSuggest.do', 'getStaffName_0000000001')" 
						 onkeydown="huanhang(event)"/>
						 
						 <span id="spanOutput" class="spanTextDropdown" style="display: none;"></span>
						
						&nbsp;&nbsp;
						<input type="button" class="btnSave"  name="<bean:message key="security.jsp.securityrolevsmenus.SecurityRoleVsMenus.item1" bundle="security"/>" value="<bean:message key="security.jsp.securityrolevsmenus.SecurityRoleVsMenus.item1" bundle="security"/>" onclick="submitQueryForm();" />
					</td>
				</tr>
			</table>
			<!--列表标题-->
			<div id="dvh1">
			<table border="0" cellpadding="0" cellspacing="0" class="tblSearchList">
				 <caption style="text-align:left;">
						<img src="<%=request.getContextPath()%>/comm/include/images/comm_list_nav_red.jpg" width="20" height="20" align="absmiddle" /> 
						<span style="font-weight:bold; color:#333;"><bean:message key="security.jsp.commom.item1" bundle="security"/></span>
	   			 </caption>
				 <thead>
			    	<tr class="lstName">
			    		<th width="3%"  height="26">序号<div><img src="include/images/cmdOrderByAsc.gif" alt="Ascender" vspace="7" onclick="commandOrderBy('1', '0')" /><img border="0" onclick="commandOrderBy('1', '1')" alt="Descender" src="include/images/cmdOrderByDesc.gif" /></div></th>
			            <th width="10%"  height="26"><bean:message key="security.jsp.commom.hspConfigBaseinfoName" bundle="security"/><div><img src="include/images/cmdOrderByAsc.gif" alt="Ascender" vspace="7" onclick="commandOrderBy('1', '0')" /><img border="0" onclick="commandOrderBy('1', '1')" alt="Descender" src="include/images/cmdOrderByDesc.gif" /></div></th>
			            <th width="5%" height="26"><bean:message key="security.jsp.commom.staffCode" bundle="security"/><div><img src="include/images/cmdOrderByAsc.gif" alt="Ascender" vspace="7" onclick="commandOrderBy('2', '0')" /><img border="0" onclick="commandOrderBy('2', '1')" alt="Descender" src="include/images/cmdOrderByDesc.gif" /></div></th>
			            <th width="5%"  height="26"><bean:message key="security.jsp.commom.name" bundle="security"/><div><img src="include/images/cmdOrderByAsc.gif" alt="Ascender" vspace="7" onclick="commandOrderBy('3', '0')" /><img border="0" onclick="commandOrderBy('3', '1')" alt="Descender" src="include/images/cmdOrderByDesc.gif" /></div></th>
			            <th width="4%"  height="26"><bean:message key="security.jsp.commom.commConfigSexId" bundle="security"/><div><img src="include/images/cmdOrderByAsc.gif" alt="Ascender" vspace="7" onclick="commandOrderBy('4', '0')" /><img border="0" onclick="commandOrderBy('4', '1')" alt="Descender" src="include/images/cmdOrderByDesc.gif" /></div></th>
			            <th width="10%" height="26">激活时间<div><img src="include/images/cmdOrderByAsc.gif" alt="Ascender" vspace="7" onclick="commandOrderBy('5', '0')" /><img border="0" onclick="commandOrderBy('5', '1')" alt="Descender" src="include/images/cmdOrderByDesc.gif" /></div></th>
			            <th width="10%" height="26">有效时间<div><img src="include/images/cmdOrderByAsc.gif" alt="Ascender" vspace="7" onclick="commandOrderBy('6', '0')" /><img border="0" onclick="commandOrderBy('6', '1')" alt="Descender" src="include/images/cmdOrderByDesc.gif" /></div></th>
			            <th width="3%"  height="26"><bean:message key="security.jsp.securitystaffpasswordinit.list.item1" bundle="security"/></th>
			        </tr>
				</thead>
				<tbody id="interval_row_id">
					<%
						if (securityStaffPasswordInit.getIdList() != null && securityStaffPasswordInit.getIdList().length > 0) {
							for (int i = 0; i < securityStaffPasswordInit.getIdList().length; i++) {
					%><tr class="list_neirong">
						<td style="text-align:center;"><%=(curPage-1)*pageSize+i+1%></td>
						<td style="text-align:left;padding-left:10px;"><%=securityStaffPasswordInit.getItemNameList()[i]%></td>
						<td><%=securityStaffPasswordInit.getStaffIdList()[i]%></td>
						<td style="text-align:left;padding-left:10px;"><%=securityStaffPasswordInit.getNameList()[i]%></td>
						<td style="text-align:left;padding-left:10px;"><%=securityStaffPasswordInit.getSexList()[i]%></td>
						<td style="text-align:left;padding-left:10px;"><%=securityStaffPasswordInit.getRegTimes()[i]%></td>
						<td style="text-align:left;padding-left:10px;"><%=securityStaffPasswordInit.getStopTimes()[i]%></td>
						<td>
							<img onclick="confirminit('<%=securityStaffPasswordInit.getIdList()[i]%>', 'security/securityStaffPasswordInit.do?verbId=update')" alt="医务人员密码初始化" src="include/images/cmdEdit_s.jpg" border="0" style="cursor: hand; vertical-align: middle;" />
						</td>
					</tr>
					<%
							}
							}
					%>
				</tbody>
			</table>
			</div>
			<!--列表内容-->
			<table width="100%" align="center" class="tblScrollFooter">	
				<tr>
					<td colspan="11" align="center" class="footer">
		 		
						<input type="hidden" id="pageId" name="pageId" value="page_282881f53464507001346450701f0000" />
						<input type="hidden" id="reHref" name="reHref" value="<%=request.getContextPath()%>/security/securityStaffPasswordInit.do?verbId=query&itemCode=<%=securityStaffPasswordInit.getItemCode() %>"  />
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
			<script type="text/javascript" src="include/javascript/interval_row_color.js"></script>
		</form>
	</body>
</html>
