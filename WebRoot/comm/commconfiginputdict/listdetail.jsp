﻿<%@page contentType="text/html; charset=utf-8"%>
<%@page import="com.tianjian.util.comm.PageBean"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<jsp:useBean id="commConfigInputDict" scope="request" type="com.tianjian.comm.struts.form.CommConfigInputDictForm" />
<jsp:useBean id="pb" scope="request" class="com.tianjian.util.comm.PageBean" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<%if(request.getServerPort() == 80) {%>
<base href="http://<%=request.getServerName()%><%=request.getContextPath()%>/" />
<%} else {%>
<base href="http://<%=request.getServerName()%>:<%=request.getServerPort()%><%=request.getContextPath()%>/" />
<%}%>
<title><bean:message key="comm.jsp.common.text59" bundle="conf.comm.comm"/></title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />	
<script language="javascript" src="<bean:message key="comm.js.CommMessage.path" bundle="conf.comm.comm"/>"></script>
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
    document.form.itemCodeHidden.value = id;  
    document.form.verbId.value = "detail";    
    document.form.submit();
}

//修改
function cmdEdit(id) {
    document.form.itemCodeHidden.value = id;  
    document.form.verbId.value = "updateInit";    
    document.form.submit();     
}

//删除
function cmdDel(id) {    
    if (!confirm(getCommMessage("10005","",""))) return false;    
    document.form.itemCodeHidden.value = id;  
    document.form.verbId.value = "delete";    
    document.form.submit();    
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
      alert("<bean:message key="comm.jsp.common.text5" bundle="conf.comm.comm"/>!");
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

</script>
	<link type="text/css" rev="stylesheet" rel="stylesheet" href="include/css/form.css" />
	</head>
	<body>
		<!-- Form中的action项的值必须设置-->
		<form name="form" method="post" action="comm/commConfigInputDict.do">
			<!-- Head line -->
			<input type="hidden" name="verbId" value="<%=commConfigInputDict.getVerbId() %>" />
			<input type="hidden" name="orderNo" value="<%=commConfigInputDict.getOrderNo() %>" />
			<input type="hidden" name="asc" value="<%=commConfigInputDict.getAsc() %>" />
			<input type="hidden" name="itemCodeHidden" value="<%=commConfigInputDict.getItemCodeHidden() %>" />
			<!--查询条件-->
			<table border="0" cellpadding="0" cellspacing="0" class="tblSearch" align="center">
				<tr>
					<td class="tblTitle" colspan="4">
						<bean:message key="comm.jsp.common.text37" bundle="conf.comm.comm"/>
					</td>
				</tr>
				<tr>
					<td>
						<bean:message key="comm.jsp.common.text10" bundle="conf.comm.comm"/>：<input name="itemCode" type="text" onkeypress="eventOnKeyPress('itemName')"
						 	value="<%=commConfigInputDict.getItemCode() %>"  maxlength="5"/>
					
						<bean:message key="comm.jsp.common.text29" bundle="conf.comm.comm"/>：<input name="itemName" type="text" onkeypress="eventOnKeyPress('inputCode')"
							value="<%=commConfigInputDict.getItemName() %>"  maxlength="1"/>
					
						<bean:message key="comm.jsp.common.text38" bundle="conf.comm.comm"/>：<input name="inputCode" type="text" onkeypress="eventOnKeyPress('inputCodeWb')" 
							value="<%=commConfigInputDict.getInputCode()%>" />
				
						<bean:message key="comm.jsp.common.text45" bundle="conf.comm.comm"/>：<input name="inputCodeWb" type="text"  onkeypress="eventOnKeyPress('btnsubmit')"
							value="<%=commConfigInputDict.getInputCodeWb() %>" />
					
						<input type="button" class="btnSave" name="btnsubmit" value="<bean:message key="comm.jsp.common.text40" bundle="conf.comm.comm"/>" onclick="submitQueryForm();" />
					</td>
				</tr>
			</table>
			<!--列表标题-->
			<div id="dvh1">
			<table border="0" cellpadding="0" cellspacing="0" class="tblSearchList" align="center">
				<caption style="text-align:left;">
					<img src="<%=request.getContextPath()%>/comm/include/images/comm_list_nav_red.jpg" width="20" height="20" align="absmiddle" /> <span style="font-weight:bold; color:#333;"><bean:message key="comm.jsp.common.text7" bundle="conf.comm.comm"/></span>
			    </caption>
			    <thead>
			    	<tr class="lstName">
			            <th width="5%" height="26"><bean:message key="comm.jsp.common.text10" bundle="conf.comm.comm"/><div><img src="include/images/cmdOrderByAsc.gif" alt="Ascender" vspace="7" onclick="commandOrderBy('1', '0')" /><img border="0" onclick="commandOrderBy('1', '1')" alt="Descender" src="include/images/cmdOrderByDesc.gif" /></div></th>
			            <th width="15%" height="26"><bean:message key="comm.jsp.common.text29" bundle="conf.comm.comm"/><div><img src="include/images/cmdOrderByAsc.gif" alt="Ascender" vspace="7" onclick="commandOrderBy('2', '0')" /><img border="0" onclick="commandOrderBy('2', '1')" alt="Descender" src="include/images/cmdOrderByDesc.gif" /></div></th>
			            <th width="5%" height="26"><bean:message key="comm.jsp.common.text38" bundle="conf.comm.comm"/><div><img src="include/images/cmdOrderByAsc.gif" alt="Ascender" vspace="7" onclick="commandOrderBy('3', '0')" /><img border="0" onclick="commandOrderBy('3', '1')" alt="Descender" src="include/images/cmdOrderByDesc.gif" /></div></th>
			            <th width="4%" height="26"><bean:message key="comm.jsp.common.text45" bundle="conf.comm.comm"/><div><img src="include/images/cmdOrderByAsc.gif" alt="Ascender" vspace="7" onclick="commandOrderBy('4', '0')" /><img border="0" onclick="commandOrderBy('4', '1')" alt="Descender" src="include/images/cmdOrderByDesc.gif" /></div></th>
			            <th width="3%" height="26"><bean:message key="comm.jsp.common.text6" bundle="conf.comm.comm"/></th>
			        </tr>
				</thead>
				<tbody id="interval_row_id">
				<%if (commConfigInputDict.getItemCodeList() != null && commConfigInputDict.getItemCodeList().length > 0) {
					for (int i = 0; i < commConfigInputDict.getItemCodeList().length; i++) {
				%>
				<tr>
					<td style="text-align:left;padding-left:10px;">
						<%=commConfigInputDict.getItemCodeList()[i]%>
					</td>
					<td style="text-align:left;padding-left:10px;">
						<%=commConfigInputDict.getItemNameList()[i]%>
					</td>
					<td style="text-align:left;padding-left:10px;"> 
						<%=commConfigInputDict.getInputCodeList()[i]%>
					</td>	
					<td style="text-align:left;padding-left:10px;">
						<%=commConfigInputDict.getInputCodeWbList()[i]%>
					</td>	
					<td>
						<img onclick="cmdView('<%=commConfigInputDict.getItemCodeList()[i] %>')" alt="<bean:message key="comm.jsp.common.text46" bundle="conf.comm.comm"/>" src="include/images/cmdView_s.jpg" border="0" style="cursor:hand; vertical-align:middle;" />
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
						 <%int curPage = 0;
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
						<input type="hidden" id="pageId" name="pageId" value="page_2828810b39763ab50139763ab5de0000" />
						<input type="hidden" id="reHref" name="reHref" value="<%=request.getContextPath()%>/comm/commConfigInputDict.do?verbId=queryDetail" />
						<%@ include file="/include/changepagesize.jsp" %>
						
						<input id="_total" name="totalPage" type="hidden" value="<%=totalPage%>" />
						<bean:message key="comm.jsp.common.text18" bundle="conf.comm.comm"/>
						<%=curPage%>
						<bean:message key="comm.jsp.common.text55" bundle="conf.comm.comm"/>
						<%=totalPage%>
						<bean:message key="comm.jsp.common.text22" bundle="conf.comm.comm"/>
						<%=totalNum%>
						<bean:message key="comm.jsp.common.text43" bundle="conf.comm.comm"/>&nbsp;|&nbsp;
						<%if (curPage > 1) {
						%>
							<a href="javascript:goPage('0')"><img src="include/images/shouye.gif" align="middle" border="0" /></a>&nbsp; <a href="javascript:goPage('<%=curPage - 1 %>')"><img src="include/images/shang.gif" align="middle" border="0" /></a>&nbsp;
						<%} else {
							out.println("<img src='include/images/shouye_s.gif' align='middle' border='0px' />&nbsp;&nbsp;<img src='include/images/shang_s.gif' align='middle' border='0px' />&nbsp;");
						}
							if (curPage < totalPage) {
						%>
							<a href="javascript:goPage('<%=curPage + 1 %>')"><img src="include/images/xia.gif" align="middle" border="0" /></a>&nbsp; <a href="javascript:goPage('<%=totalPage %>')"><img src="include/images/mo.gif" align="middle" border="0" /></a>&nbsp;
						<%} else {
							out.println("<img src='include/images/xia_x.gif' align='middle' border='0px' />&nbsp;&nbsp;<img src='include/images/mo_m.gif' align='middle' border='0px' />&nbsp;");
							}
						%>
						| &nbsp;<bean:message key="comm.jsp.common.text12" bundle="conf.comm.comm"/>
						<input id="_tp" name="page" type="text" value="<%=curPage%>" size="2" class="txt" />
						<bean:message key="comm.jsp.common.text53" bundle="conf.comm.comm"/>&nbsp;
						<img style="cursor:hand;" src="include/images/go.jpg" width="18" border="0" onclick="goPage2()" />
					</td>
				</tr>
			</table>
		</form>
		<script language="javascript" src="include/javascript/interval_row_color.js"></script>
	</body>
</html>
