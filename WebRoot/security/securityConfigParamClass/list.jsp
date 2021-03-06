﻿<%@page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@page import="com.tianjian.util.comm.PageBean"%>
<jsp:useBean id="data" scope="request"
	class="com.tianjian.security.struts.form.SecurityConfigParamClassForm" />
<jsp:useBean id="pb" scope="request"
	class="com.tianjian.util.comm.PageBean" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<%if(request.getServerPort() == 80) {%>
		<base
			href="http://<%=request.getServerName()%><%=request.getContextPath()%>/">
		<%} else {%>
		<base
			href="http://<%=request.getServerName()%>:<%=request.getServerPort()%><%=request.getContextPath()%>/">
		<%}%>
		<title><bean:message key="security.jsp.securityConfigParamClassM.list.title" bundle="security"/></title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<script language="javascript" src="include/javascript/TJMessage.js"></script>
		<script language="javascript" src="comm/include/javascript/CommMessage.js"></script>
		<link type="text/css" rev="stylesheet" rel="stylesheet" href="include/css/form.css" />
		<script language="javascript" src="include/javascript/eventOnKeyPress.js"></script>
		<script language="javascript">
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

//添加
function cmdAdd() {
    document.form.verbId.value = "addInit";    
    document.form.submit();
}

//查看详细
function cmdView(id) {
    document.form.classCode1.value = id;  
    document.form.verbId.value = "detail";    
    document.form.submit();
}

//修改
function cmdEdit(id) {
    document.form.classCode1.value = id;  
    document.form.verbId.value = "updateInit";    
    document.form.submit();     
}

//删除
function cmdDel(id) {   
	if (confirmMessage("<bean:message key='security.jsp.commom.delete' bundle='security'/>")){     
    document.form.classCode1.value = id;  
    document.form.verbId.value = "delete";    
    document.form.submit(); 
    }    
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
			function showMessage(message){
				if(message != ''&& message != null){
					alert(message);
					return;
				}
			}

</script>
	</head>
	<body onload="showMessage('<%=data.getMessage() %>')">
		<!-- Form中的action项的值必须设置-->
		<form name="form" method="post"
			action="<%=request.getContextPath()%>/security/securityConfigParamClass.do">
			<!-- Head line -->
			<input type="hidden" name="verbId" value="<%=data.getVerbId()%>">
			<input type="hidden" name="orderNo" value="<%=data.getOrderNo()%>">
			<input type="hidden" name="asc" value="<%=data.getAsc()%>">
			<input type="hidden" id="classCode1" name="classCode1" value="<%=data.getClassCode1()%>">

			<!--查询条件-->
			<table  border="0" cellpadding="0" cellspacing="0" class="tblSearch" align="center">
				<tr>
					<td class="tblTitle" colspan="4">
						<bean:message key="security.jsp.securityConfigParamClassM.list.title" bundle="security"/>
					</td>
				</tr>
				<tr>
				<td>
					所属工程:
					<input name="projectName" type="text" onkeypress="eventOnKeyPress('classCode')" value="<%=data.getProjectName()%>" />				
				
					程序包代码:
				    <input name="classCode" type="text"  onkeypress="eventOnKeyPress('className')" value="<%=data.getClassCode()%>" />
				    
				    <bean:message key="security.jsp.securityConfigParamClass1.commom2" bundle="security"/>:
				    <input name="className" type="text"  onkeypress="eventOnKeyPress('inputCode')" value="<%=data.getClassName()%>" />
					
					<bean:message key="security.jsp.securityConfigParamClass1.commom3" bundle="security"/>:
					<input name="inputCode" type="text" onkeypress="eventOnKeyPress('btnsubmit')" value="<%=data.getInputCode()%>" />
					
							
					<input type="button" class="btnSave" name="btnsubmit" value="<bean:message key="security.jsp.securityConfigParamClass.query.button1" bundle="security"/>" onClick="submitQueryForm();" />
				</td>
				</tr>
			</table>
			
			<!--列表标题-->
			<div id="dvh1">
			<table border="0" cellpadding="0" cellspacing="0" class="tblSearchList" align="center">
				<caption style="text-align:left;">
					<img src="<%=request.getContextPath()%>/comm/include/images/comm_list_nav_red.jpg" width="20" height="20" align="absmiddle" /> <span style="font-weight:bold; color:#333;"><bean:message key="security.jsp.commom.item1" bundle="security"/></span>
					<span class="titleBtn"><img  src="<bean:message key="security.jsp.securityconfigpublicclass.add" bundle="conf.security.security"/>"  align="absmiddle" />
						<a href="javascript:cmdAdd()" target="_self"><bean:message key="security.jsp.commom.button3" bundle="security"/></a></span>
	   			</caption>
				<thead>
			    	<tr class="lstName">
			    	<th width="5%" height="26">程序包代码<div><img src="include/images/cmdOrderByAsc.gif" alt="Ascender" vspace="7" onclick="commandOrderBy('1', '0')" /><img border="0" onclick="commandOrderBy('1', '1')" alt="Descender" src="include/images/cmdOrderByDesc.gif" /></div></th>
			            <th width="5%" height="26"><bean:message key="security.jsp.securityConfigParamClass1.commom2" bundle="security"/><div><img src="include/images/cmdOrderByAsc.gif" alt="Ascender" vspace="7" onclick="commandOrderBy('2', '0')" /><img border="0" onclick="commandOrderBy('2', '1')" alt="Descender" src="include/images/cmdOrderByDesc.gif" /></div></th>
			            <th width="4%" height="26"><bean:message key="security.jsp.securityConfigParamClass1.commom3" bundle="security"/><div><img src="include/images/cmdOrderByAsc.gif" alt="Ascender" vspace="7" onclick="commandOrderBy('3', '0')" /><img border="0" onclick="commandOrderBy('3', '1')" alt="Descender" src="include/images/cmdOrderByDesc.gif" /></div></th>
			            <th width="15%" height="26"><bean:message key="security.jsp.securityConfigParamClass1.commom4" bundle="security"/><div><img src="include/images/cmdOrderByAsc.gif" alt="Ascender" vspace="7" onclick="commandOrderBy('4', '0')" /><img border="0" onclick="commandOrderBy('4', '1')" alt="Descender" src="include/images/cmdOrderByDesc.gif" /></div></th>
			            <th width="4%" height="26">所属的工程<div><img src="include/images/cmdOrderByAsc.gif" alt="Ascender" vspace="7" onclick="commandOrderBy('5', '0')" /><img border="0" onclick="commandOrderBy('5', '1')" alt="Descender" src="include/images/cmdOrderByDesc.gif" /></div></th>
			            <th width="3%" height="26"><bean:message key="security.jsp.commom.button6" bundle="security"/></th>
			            <th width="3%" height="26"><bean:message key="security.jsp.commom.button7" bundle="security"/></th>
			            <th width="3%" height="26"><bean:message key="security.jsp.commom.button8" bundle="security"/></th>
			        </tr>
				</thead>
				<tbody id="interval_row_id">
					<%if (data.getClassCodeList() != null&& data.getClassCodeList().length > 0) {
						for (int i = 0; i < data.getClassCodeList().length; i++) {
					%>
					<tr>
						<td>
							<%=data.getClassCodeList()[i]%>
						</td>
						<td style="text-align:left;padding-left:10px;">
							<%=data.getClassNameList()[i]%>
						</td>
						<td style="text-align:left;padding-left:10px;">
							<%=data.getInputCodeList()[i]%>
						</td>
						<td style="text-align:left;padding-left:10px;">
							<%=data.getFunctionDescriptionList()[i]%>
						</td>
						<td style="text-align:left;padding-left:10px;">
							<%=data.getProjectNameList()[i]%>
						</td>
						<td>
							<img onClick="cmdView('<%=data.getClassCodeList()[i] %>')"
								alt="<bean:message key="security.jsp.commom.button6" bundle="security"/>" src="include/images/cmdView_s.jpg" border="0"
								style="cursor: hand; vertical-align: middle;" />
						</td>
						<td>
							<img onClick="cmdEdit('<%=data.getClassCodeList()[i] %>')"
								alt="<bean:message key="security.jsp.commom.button7" bundle="security"/>" src="include/images/cmdEdit_s.jpg" border="0"
								style="cursor: hand; vertical-align: middle;" />
						</td>
						<td>
							<img onClick="cmdDel('<%=data.getClassCodeList()[i] %>')"
								alt="<bean:message key="security.jsp.commom.button8" bundle="security"/>" src="include/images/cmdDel_s.jpg" border="0"
								style="cursor: hand; vertical-align: middle;" />
						</td>
					</tr>
					<%
						}
						}
					%>
				</tbody>
			</table>
			
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
						<input type="hidden" id="pageId" name="pageId" value="page_282881f534645a2c0134645a2cd00000" />
						<input type="hidden" id="reHref" name="reHref" value="<%=request.getContextPath()%>/security/securityConfigParamClassM.do?verbId=query"  />
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

		</form>
	</body>
	<script language="javascript"
		src="include/javascript/interval_row_color.js"></script>
</html>
