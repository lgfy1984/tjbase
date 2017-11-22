<%@page contentType="text/html; charset=utf-8"%>
<%@taglib prefix="bean" uri="/WEB-INF/struts-bean.tld"%>
<%@page import="com.tianjian.util.comm.PageBean"%>
<jsp:useBean id="commConfigVocation" scope="request" type="com.tianjian.comm.struts.form.CommConfigVocationForm" />
<jsp:useBean id="pb" scope="request" class="com.tianjian.util.comm.PageBean" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%if(request.getServerPort() == 80) {%>
<base href="http://<%=request.getServerName()%><%=request.getContextPath()%>/" />
<%} else {%>
<base href="http://<%=request.getServerName()%>:<%=request.getServerPort()%><%=request.getContextPath()%>/" />
<%}%>
<title><bean:message key="comm.jsp.commconfigvocation.list.title" bundle="conf.comm.CommMessageguoh"/>
</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />	
<script language="javascript" src='<bean:message key="comm.js.commMessageguoh" bundle="conf.comm.CommMessageguoh"/>'></script>
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
      alert('<bean:message key="comm.jsp.commconfigrh.list.gopage2_1" bundle="conf.comm.CommMessageguoh"/>');
      return;
    }
    if (_tp.value<=0){
    	alert('<bean:message key="comm.jsp.commconfigrh.list.gopage2_2" bundle="conf.comm.CommMessageguoh"/>');
		return;
    }
    if(parseInt(_tp.value)>parseInt(_total.value)){
      alert('<bean:message key="comm.jsp.commconfigrh.list.gopage2_3" bundle="conf.comm.CommMessageguoh"/>');
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
		<form name="form" method="post" action="comm/commConfigVocation.do">
			<!-- Head line -->
			<input type="hidden" name="verbId" value="<%=commConfigVocation.getVerbId() %>" />
			<input type="hidden" name="orderNo" value="<%=commConfigVocation.getOrderNo() %>" />
			<input type="hidden" name="asc" value="<%=commConfigVocation.getAsc() %>" />
			<input type="hidden" name="itemCodeHidden" value="<%=commConfigVocation.getItemCodeHidden() %>" />

			<!--查询条件-->
			<table border="0" cellpadding="0" cellspacing="0" class="tblSearch" align="center">
				<tr>
					<td class="tblTitle" colspan="4">
					<bean:message key="comm.jsp.commconfigvocation.list.td1" bundle="conf.comm.CommMessageguoh"/>
					</td>
				</tr>
				<tr>
					<td>
						<bean:message key="comm.jsp.commconfigrh.list.td2" bundle="conf.comm.CommMessageguoh"/>
						<input name="itemCode" type="text" onkeypress="eventOnKeyPress('itemName')" value="<%=commConfigVocation.getItemCode() %>"/>
					
						<bean:message key="comm.jsp.commconfigrh.list.td3" bundle="conf.comm.CommMessageguoh"/>
						<input name="itemName" type="text" onkeypress="eventOnKeyPress('inputCode')" value="<%=commConfigVocation.getItemName() %>"/>
				
						<bean:message key="comm.jsp.commconfigrh.list.td4" bundle="conf.comm.CommMessageguoh"/>
						<input name="inputCode" type="text" value="<%=commConfigVocation.getInputCode() %>" onkeypress="eventOnKeyPress('btnSearch')"/>
				
						<input type="button" class="btnSave" id="btnSearch" name="btnSearch" value='<bean:message key="comm.jsp.commconfigrh.list.td6" bundle="conf.comm.CommMessageguoh"/>' onclick="submitQueryForm();" />
					</td>
				</tr>
			</table>
			<!--列表标题-->
			<div id="dvh1">
			<table border="0" cellpadding="0" cellspacing="0" class="tblSearchList" align="center">
				<caption style="text-align:left;">
					<img src="<%= request.getContextPath()%>/comm/include/images/comm_list_nav_red.jpg" width="20" height="20" align="absmiddle" /> <span style="font-weight:bold; color:#333;"><bean:message key="comm.jsp.commconfigrh.list.td7" bundle="conf.comm.CommMessageguoh"/></span>
			    </caption>
			    <thead>
			    	<tr class="lstName">
			            <th width="5%" height="26"><bean:message key="comm.jsp.commconfigrh.list.td9" bundle="conf.comm.CommMessageguoh"/><div><img src="include/images/cmdOrderByAsc.gif" alt="Ascender" vspace="7" onclick="commandOrderBy('1', '0')" /><img border="0" onclick="commandOrderBy('1', '1')" alt="Descender" src="include/images/cmdOrderByDesc.gif" /></div></th>
			            <th width="15%" height="26"><bean:message key="comm.jsp.commconfigrh.list.td10" bundle="conf.comm.CommMessageguoh"/><div><img src="include/images/cmdOrderByAsc.gif" alt="Ascender" vspace="7" onclick="commandOrderBy('2', '0')" /><img border="0" onclick="commandOrderBy('2', '1')" alt="Descender" src="include/images/cmdOrderByDesc.gif" /></div></th>
			            <th width="5%" height="26"><bean:message key="comm.jsp.commconfigrh.list.td11" bundle="conf.comm.CommMessageguoh"/><div><img src="include/images/cmdOrderByAsc.gif" alt="Ascender" vspace="7" onclick="commandOrderBy('3', '0')" /><img border="0" onclick="commandOrderBy('3', '1')" alt="Descender" src="include/images/cmdOrderByDesc.gif" /></div></th>
			            <th width="4%" height="26"><bean:message key="comm.jsp.commconfigrh.list.td12" bundle="conf.comm.CommMessageguoh"/><div><img src="include/images/cmdOrderByAsc.gif" alt="Ascender" vspace="7" onclick="commandOrderBy('4', '0')" /><img border="0" onclick="commandOrderBy('4', '1')" alt="Descender" src="include/images/cmdOrderByDesc.gif" /></div></th>
			            <th width="3%" height="26"><bean:message key="comm.jsp.commconfigrh.list.td13" bundle="conf.comm.CommMessageguoh"/></th>
			        </tr>
				</thead>
				<tbody id="interval_row_id">
				<%if (commConfigVocation.getItemCodeList() != null
					&& commConfigVocation.getItemCodeList().length > 0) {
				for (int i = 0; i < commConfigVocation.getItemCodeList().length; i++) {

					%>
				<tr>
					<td style="text-align:left;padding-left:10px;">
						<%=commConfigVocation.getItemCodeList()[i]%>
					</td>
					<td style="text-align:left;padding-left:10px;">
						<%=commConfigVocation.getItemNameList()[i]%>
					</td>
					<td style="text-align:left;padding-left:10px;">
						<%=commConfigVocation.getSeqNoList()[i]%>
					</td>
					<td style="text-align:left;padding-left:10px;">
						<%=commConfigVocation.getInputCodeList()[i]%>
					</td>
					<td>
						<img onclick="cmdView('<%=commConfigVocation.getItemCodeList()[i] %>')" alt='<bean:message key="comm.jsp.commconfigrh.list.img9" bundle="conf.comm.CommMessageguoh"/>' src="include/images/cmdView_s.jpg" border="0" style="cursor:hand; vertical-align:middle;" />
					</td>
				</tr>
				<%}
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
						<input type="hidden" id="pageId" name="pageId" value="page_2828810b39763bf50139763bf5cf0000" />
						<input type="hidden" id="reHref" name="reHref" value="<%=request.getContextPath()%>/comm/commConfigVocation.do?verbId=queryDetail" />
						<%@ include file="/include/changepagesize.jsp" %>
							
						<input id="_total" name="totalPage" type="hidden" value="<%=totalPage%>"/>
						<bean:message key="comm.jsp.commconfigrh.list.page1" bundle="conf.comm.CommMessageguoh"/>

						<%=curPage%>
						<bean:message key="comm.jsp.commconfigrh.list.page2" bundle="conf.comm.CommMessageguoh"/>

						<%=totalPage%>
						<bean:message key="comm.jsp.commconfigrh.list.page3" bundle="conf.comm.CommMessageguoh"/>

						<%=totalNum%>
						<bean:message key="comm.jsp.commconfigrh.list.page4" bundle="conf.comm.CommMessageguoh"/>

						<%if (curPage > 1) {
%>
						<a href="javascript:goPage('0')"><img src="include/images/shouye.gif" align="middle" border="0" /></a>&nbsp;
						<a href="javascript:goPage('<%=curPage - 1 %>')"><img src="include/images/shang.gif" align="middle" border="0" /></a>&nbsp;
						<%} else {
				out.println("<img src='include/images/shouye_s.gif' align='middle' border='0px' />&nbsp;&nbsp;<img src='include/images/shang_s.gif' align='middle' border='0px' />&nbsp;");
			}
			if (curPage < totalPage) {
%>
						<a href="javascript:goPage('<%=curPage + 1 %>')"><img src="include/images/xia.gif" align="middle" border="0" /></a>&nbsp;
						<a href="javascript:goPage('<%=totalPage %>')"><img src="include/images/mo.gif" align="middle" border="0" /></a>&nbsp;
						<%} else {
				out.println("<img src='include/images/xia_x.gif' align='middle' border='0px' />&nbsp;&nbsp;<img src='include/images/mo_m.gif' align='middle' border='0px' />&nbsp;");
			}

			%>
						|<bean:message key="comm.jsp.commconfigrh.list.page5" bundle="conf.comm.CommMessageguoh"/>

						<input id="_tp" name="page" type="text" value="<%=curPage%>" size="2" class="txt" />
						<bean:message key="comm.jsp.commconfigrh.list.page6" bundle="conf.comm.CommMessageguoh"/>

						
						<img style="cursor:hand;" src="include/images/go.jpg" width="18" border="0" onclick="goPage2()" />

					</td>
				</tr>

			</table>

		</form>
		<script language="javascript" src="include/javascript/interval_row_color.js"></script>
	</body>
</html>
