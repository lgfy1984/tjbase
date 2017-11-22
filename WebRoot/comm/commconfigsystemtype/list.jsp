<%@page contentType="text/html; charset=utf-8"%>
<%@page import="com.tianjian.util.comm.PageBean"%>
<%@taglib prefix="bean" uri="/WEB-INF/struts-bean.tld"%>
<jsp:useBean id="commConfigSystemtype" scope="request" class="com.tianjian.comm.struts.form.CommConfigSystemtypeForm" />
<jsp:useBean id="pb" scope="request" class="com.tianjian.util.comm.PageBean" />
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
		<title><bean:message key="comm.jsp.commconfigsystemtype.list.title" bundle="conf.comm.CommMessageguoh"/></title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<script language="javascript" src='<bean:message key="comm.js.TJMessage" bundle="conf.comm.CommMessageguoh"/>'></script>
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
    if (confirmMessage("0-000004")){     
    document.form.itemCodeHidden.value = id;  
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
      alert('<bean:message key="comm.jsp.commconfigsystemtype.list.gopage2_1" bundle="conf.comm.CommMessageguoh"/>');
      return;
    }
    if (_tp.value<=0){
    	alert('<bean:message key="comm.jsp.commconfigsystemtype.list.gopage2_2" bundle="conf.comm.CommMessageguoh"/>');
		return;
    }
    if(parseInt(_tp.value)>parseInt(_total.value)){
      alert('<bean:message key="comm.jsp.commconfigsystemtype.list.gopage2_3" bundle="conf.comm.CommMessageguoh"/>');
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

</script>
		<link rel="stylesheet" type="text/css" href="comm/include/css/comm_zidian.css" />
	</head>
	<body>
		<!-- Form中的action项的值必须设置-->
		<form name="form" method="post" action="comm/commConfigSystemtype.do">
			<!-- Head line -->
			<input type="hidden" name="verbId" value="<%=commConfigSystemtype.getVerbId()%>">
			<input type="hidden" name="orderNo" value="<%=commConfigSystemtype.getOrderNo()%>">
			<input type="hidden" name="asc" value="<%=commConfigSystemtype.getAsc()%>">
			<input type="hidden" name="itemCodeHidden" value="<%=commConfigSystemtype.getItemCodeHidden()%>">
			<!--查询条件-->
			<table width="95%" style="font-size: 14px" class="table2" align="center" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td id="list_L"></td>
					<td class="list_biaoti1">
						<bean:message key="comm.jsp.commconfigsystemtype.list.td1" bundle="conf.comm.CommMessageguoh"/>
					</td>
					<td id="list_C"></td>
					<td class="list_cc">
						<bean:message key="comm.jsp.commconfigsystemtype.list.td2" bundle="conf.comm.CommMessageguoh"/>
						<input name="itemCode" type="text" onkeypress="eventOnKeyPress('itemName')" value="<%=commConfigSystemtype.getItemCode()%>">
					</td>
					<td class="list_cc">
						<bean:message key="comm.jsp.commconfigsystemtype.list.td3" bundle="conf.comm.CommMessageguoh"/>
						<input name="itemName" type="text" onkeypress="eventOnKeyPress('inputCode')" value="<%=commConfigSystemtype.getItemName()%>">
					</td>
					<td class="list_cc">
						<bean:message key="comm.jsp.commconfigsystemtype.list.td4" bundle="conf.comm.CommMessageguoh"/>
						<input name="inputCode" type="text" value="<%=commConfigSystemtype.getInputCode()%>" onkeypress="eventOnKeyPress('btnSearch')">
					</td>
					<td id="list_R"></td>
					<td class="button">
						<input type="button" id="btnSearch" name="btnSearch" style="cursor: hand; background: url(include/images/tong.gif) no-repeat; border: 0; width: 50px; height: 23px; font-size: 12px;" value='<bean:message key="comm.jsp.commconfigsystemtype.list.td6" bundle="conf.comm.CommMessageguoh"/>' onClick="submitQueryForm();" />
					</td>
				</tr>
			</table>
			<!--列表标题-->
			<table width="95%" align="center" class="table" border="0" cellspacing="0" cellpadding="0">
				<tr class="list_title">
					<td colspan="3" height="5px">
						<b class="rt"><b class="r1"></b><b class="r2"></b><b class="r3"></b><b class="r4"></b>
						</b>
					</td>
				</tr>
				<tr class="yuan_height">
					<td width="5%" align="center" valign="middle" style="border-left: 1px solid #BFBFBF;">
						<img src="<%=request.getContextPath()%>/comm/include/images/comm_list_nav_red.jpg">
					</td>
					<td width="60%" align="left" class="jiacu">
						<bean:message key="comm.jsp.commconfigsystemtype.list.td7" bundle="conf.comm.CommMessageguoh"/>
					</td>
					<td width="35%" align="right" style="border-right: 1px solid #BFBFBF; padding: 0px 10px 0px 0px;">
						<a href="javascript:cmdAdd()"><img border=0 alt='<bean:message key="comm.jsp.commconfigsystemtype.list.td8" bundle="conf.comm.CommMessageguoh"/>' src="<bean:message key="security.jsp.securityconfigpublicclass.add" bundle="conf.security.security"/>" style="cursor: hand">
						</a>
					</td>
				</tr>
			</table>
			<!--列表内容-->
			<table class="table" align="center" style="font-size: 14px" width="95%" cellspacing="1" cellpadding="0">
				<tr class="list_nav">
					<td width="116px">
						<table width="100%" border="0" cellpadding="0" cellspacing="0">
							<tr>
								<td rowspan="2" align="center">
									<bean:message key="comm.jsp.commconfigsystemtype.list.td9" bundle="conf.comm.CommMessageguoh"/>
								</td>
								<td align="bottom" height="12">
									<img src="include/images/cmdOrderByAsc.gif" border="0" alt='<bean:message key="comm.jsp.commconfigsystemtype.list.img7" bundle="conf.comm.CommMessageguoh"/>' style="cursor: hand" onClick="commandOrderBy('1', '0')">
								</td>
							</tr>
							<tr>
								<td height="12">
									<img src="include/images/cmdOrderByDesc.gif" border="0" alt='<bean:message key="comm.jsp.commconfigsystemtype.list.img8" bundle="conf.comm.CommMessageguoh"/>' style="cursor: hand" onClick="commandOrderBy('1', '1')">
								</td>
							</tr>
						</table>
					</td>
					<td width="116px">
						<table width="100%" border="0" cellpadding="0" cellspacing="0">
							<tr>
								<td rowspan="2" align="center">
									<bean:message key="comm.jsp.commconfigsystemtype.list.td10" bundle="conf.comm.CommMessageguoh"/>
								</td>
								<td align="bottom" height="12">
									<img src="include/images/cmdOrderByAsc.gif" border="0" alt='<bean:message key="comm.jsp.commconfigsystemtype.list.img7" bundle="conf.comm.CommMessageguoh"/>' style="cursor: hand" onClick="commandOrderBy('2', '0')">
								</td>
							</tr>
							<tr>
								<td height="12">
									<img src="include/images/cmdOrderByDesc.gif" border="0" alt='<bean:message key="comm.jsp.commconfigsystemtype.list.img8" bundle="conf.comm.CommMessageguoh"/>' style="cursor: hand" onClick="commandOrderBy('2', '1')">
								</td>
							</tr>
						</table>
					</td>
					<td width="116px">
						<table width="100%" border="0" cellpadding="0" cellspacing="0">
							<tr>
								<td rowspan="2" align="center">
									<bean:message key="comm.jsp.commconfigsystemtype.list.td11" bundle="conf.comm.CommMessageguoh"/>
								</td>
								<td align="bottom" height="12">
									<img src="include/images/cmdOrderByAsc.gif" border="0" alt='<bean:message key="comm.jsp.commconfigsystemtype.list.img7" bundle="conf.comm.CommMessageguoh"/>' style="cursor: hand" onClick="commandOrderBy('3', '0')">
								</td>
							</tr>
							<tr>
								<td height="12">
									<img src="include/images/cmdOrderByDesc.gif" border="0" alt='<bean:message key="comm.jsp.commconfigsystemtype.list.img8" bundle="conf.comm.CommMessageguoh"/>' style="cursor: hand" onClick="commandOrderBy('3', '1')">
								</td>
							</tr>
						</table>
					</td>
					<td width="116px">
						<table width="100%" border="0" cellpadding="0" cellspacing="0">
							<tr>
								<td rowspan="2" align="center">
									<bean:message key="comm.jsp.commconfigsystemtype.list.td12" bundle="conf.comm.CommMessageguoh"/>
								</td>
								<td align="bottom" height="12">
									<img src="include/images/cmdOrderByAsc.gif" border="0" alt='<bean:message key="comm.jsp.commconfigsystemtype.list.img7" bundle="conf.comm.CommMessageguoh"/>' style="cursor: hand" onClick="commandOrderBy('4', '0')">
								</td>
							</tr>
							<tr>
								<td height="12">
									<img src="include/images/cmdOrderByDesc.gif" border="0" alt='<bean:message key="comm.jsp.commconfigsystemtype.list.img8" bundle="conf.comm.CommMessageguoh"/>' style="cursor: hand" onClick="commandOrderBy('4', '1')">
								</td>
							</tr>
						</table>
					</td>
					<td width="40px">
						<bean:message key="comm.jsp.commconfigsystemtype.list.td13" bundle="conf.comm.CommMessageguoh"/>
					</td>
					<td width="40px">
						<bean:message key="comm.jsp.commconfigsystemtype.list.td14" bundle="conf.comm.CommMessageguoh"/>
					</td>
					<td width="40px">
						<bean:message key="comm.jsp.commconfigsystemtype.list.td15" bundle="conf.comm.CommMessageguoh"/>
					</td>
				</tr>
				<tbody id="interval_row_id">
					<%
							if (commConfigSystemtype.getItemCodeList() != null && commConfigSystemtype.getItemCodeList().length > 0) {
							for (int i = 0; i < commConfigSystemtype.getItemCodeList().length; i++) {
					%>
					<tr class="list_neirong">
						<td>
							<%=commConfigSystemtype.getItemCodeList()[i]%>
						</td>
						<td>
							<%=commConfigSystemtype.getItemNameList()[i]%>
						</td>
						<td>
							<%=commConfigSystemtype.getSeqNoList()[i]%>
						</td>
						<td>
							<%=commConfigSystemtype.getInputCodeList()[i]%>
						</td>
						<td>
							<img onClick="cmdView('<%=commConfigSystemtype.getItemCodeList()[i]%>')" alt='<bean:message key="comm.jsp.commconfigsystemtype.list.img9" bundle="conf.comm.CommMessageguoh"/>' src="include/images/cmdView_s.jpg" border="0" style="cursor: hand; vertical-align: middle;" />
						</td>
						<td>
							<img onClick="cmdEdit('<%=commConfigSystemtype.getItemCodeList()[i]%>')" alt='<bean:message key="comm.jsp.commconfigsystemtype.list.img10" bundle="conf.comm.CommMessageguoh"/>' src="include/images/cmdEdit_s.jpg" border="0" style="cursor: hand; vertical-align: middle;" />
						</td>
						<td>
							<img onClick="cmdDel('<%=commConfigSystemtype.getItemCodeList()[i]%>')" alt='<bean:message key="comm.jsp.commconfigsystemtype.list.img11" bundle="conf.comm.CommMessageguoh"/>' src="include/images/cmdDel_s.jpg" border="0" style="cursor: hand; vertical-align: middle;" />
						</td>
					</tr>
					<%
						}
						}
					%>
				</tbody>
				<tr>
					<td colspan="8" align="center" bgcolor="#ffffff" height="35px">
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
						<input id="_total" name="totalPage" type="hidden" value="<%=totalPage%>">
						<bean:message key="comm.jsp.commconfigsystemtype.list.page1" bundle="conf.comm.CommMessageguoh"/>
						<%=curPage%>
						<bean:message key="comm.jsp.commconfigsystemtype.list.page2" bundle="conf.comm.CommMessageguoh"/>
						<%=totalPage%>
						<bean:message key="comm.jsp.commconfigsystemtype.list.page3" bundle="conf.comm.CommMessageguoh"/>
						<%=totalNum%>
						<bean:message key="comm.jsp.commconfigsystemtype.list.page4" bundle="conf.comm.CommMessageguoh"/>
						<%
						if (curPage > 1) {
						%>
						<a href="javascript:goPage('0')"><img src="include/images/shouye.gif" align="middle" border="0" /></a>&nbsp;
						<a href="javascript:goPage('<%=curPage - 1%>')"><img src="include/images/shang.gif" align="middle" border="0" /></a>&nbsp;
						<%
								} else {
								out.println("<img src='include/images/shouye_s.gif' align='middle' border='0px' />&nbsp;&nbsp;<img src='include/images/shang_s.gif' align='middle' border='0px' />&nbsp;");
							}
							if (curPage < totalPage) {
						%>
						<a href="javascript:goPage('<%=curPage + 1%>')"><img src="include/images/xia.gif" align="middle" border="0" /></a>&nbsp;
						<a href="javascript:goPage('<%=totalPage%>')"><img src="include/images/mo.gif" align="middle" border="0" /></a>&nbsp;
						<%
								} else {
								out.println("<img src='include/images/xia_x.gif' align='middle' border='0px' />&nbsp;&nbsp;<img src='include/images/mo_m.gif' align='middle' border='0px' />&nbsp;");
							}
						%>
						<bean:message key="comm.jsp.commconfigsystemtype.list.page5" bundle="conf.comm.CommMessageguoh"/>
						<input id="_tp" name="page" type="text" value="<%=curPage%>" size="2" class="txt">
						<bean:message key="comm.jsp.commconfigsystemtype.list.page6" bundle="conf.comm.CommMessageguoh"/>
						<img style="cursor: hand;" src="include/images/go.jpg" width="18" border="0" onClick="goPage2()" />
					</td>
				</tr>
			</table>
		</form>
		<script language="javascript" src="include/javascript/interval_row_color.js"></script>
	</body>
</html>
