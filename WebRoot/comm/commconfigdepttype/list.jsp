<%@page contentType="text/html; charset=utf-8"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@page import="com.tianjian.util.comm.PageBean"%>
<jsp:useBean id="commConfigDepttype" scope="request" type="com.tianjian.comm.struts.form.CommConfigDepttypeForm" />
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
		<title><bean:message  bundle="comm.commLocale" key="comm.jsp.commconfigdepttypelist.text19"/></title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<script language="javascript" src="<bean:message  key="comm.js.includeTJMessage.path"  bundle="comm.commLocale"/>"></script>
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
      alert("<bean:message  bundle="comm.commLocale" key="comm.jsp.commom.warn4"/>!");
      return;
    }
    if (_tp.value<=0){
    	alert("<bean:message  bundle="comm.commLocale" key="comm.jsp.commom.pageGreateThanZero"/>!");
		return;
    }
    if(parseInt(_tp.value)>parseInt(_total.value)){
      alert("<bean:message  bundle="comm.commLocale" key="comm.jsp.commom.warn3"/>!");
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
		<form name="form" method="post" action="comm/commConfigDepttype.do">
			<!-- Head line -->
			<input type="hidden" name="verbId" value="<%=commConfigDepttype.getVerbId()%>">
			<input type="hidden" name="orderNo" value="<%=commConfigDepttype.getOrderNo()%>">
			<input type="hidden" name="asc" value="<%=commConfigDepttype.getAsc()%>">
			<input type="hidden" name="itemCodeHidden" value="<%=commConfigDepttype.getItemCodeHidden()%>">
			<!--查询条件-->
			<table width="95%" class="table2" align="center" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td id="list_L"></td>
					<td class="list_biaoti">
						<bean:message  bundle="comm.commLocale" key="comm.jsp.commconfigdepttypelist.text17"/>
					</td>
					<td id="list_C"></td>
					<td class="list_cc">
						<bean:message  bundle="comm.commLocale" key="comm.jsp.commom.code"/>:
						<input name="itemCode" type="text" onkeypress="eventOnKeyPress('itemName')" value="<%=commConfigDepttype.getItemCode()%>">
					</td>
					<td class="list_cc">
						<bean:message  bundle="comm.commLocale" key="comm.jsp.commom.name"/>:
						<input name="itemName" type="text" onkeypress="eventOnKeyPress('inputCode')" value="<%=commConfigDepttype.getItemName()%>">
					</td>
					<td class="list_cc">
						<bean:message  bundle="comm.commLocale" key="comm.jsp.commom.inputCode"/>:
						<input name="inputCode" type="text" onkeypress="eventOnKeyPress('<bean:message  bundle="comm.commLocale" key="comm.jsp.commom.submit"/>')" value="<%=commConfigDepttype.getInputCode()%>">
					</td>
					<td id="list_R"></td>
					<td class="button">
						<input type="button"  name="<bean:message  bundle="comm.commLocale" key="comm.jsp.commom.submit"/>" value="<bean:message  bundle="comm.commLocale" key="comm.jsp.commom.submit1"/>" onClick="submitQueryForm();" />
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
						<bean:message  bundle="comm.commLocale" key="comm.jsp.commom.selectList"/>
					</td>
					<td width="35%" align="right" style="border-right: 1px solid #BFBFBF; padding: 0px 10px 0px 0px;">
						<a href="javascript:cmdAdd()"><img border=0 alt="<bean:message  bundle="comm.commLocale" key="comm.jsp.commom.add"/>" src="<bean:message key="security.jsp.securityconfigpublicclass.add" bundle="conf.security.security"/>" style="cursor: hand">
						</a>
					</td>
				</tr>
			</table>
			<!--列表内容-->
			<table class="table" align="center" width="95%" cellspacing="1" cellpadding="0">
				<tr class="list_nav">
					<td width="116px">
						<table width="100%" border="0" cellpadding="0" cellspacing="0">
							<tr>
								<td rowspan="2" align="center">
									<bean:message  bundle="comm.commLocale" key="comm.jsp.commom.code"/>
								</td>
								<td align="bottom" height="12">
									<img src="include/images/cmdOrderByAsc.gif" border="0" alt="<bean:message  bundle="comm.commLocale" key="comm.jsp.commom.text21"/>" style="cursor: hand" onClick="commandOrderBy('1', '0')">
								</td>
							</tr>
							<tr>
								<td height="12">
									<img src="include/images/cmdOrderByDesc.gif" border="0" alt="<bean:message  bundle="comm.commLocale" key="comm.jsp.commom.text17"/>" style="cursor: hand" onClick="commandOrderBy('1', '1')">
								</td>
							</tr>
						</table>
					</td>
					<td width="116px">
						<table width="100%" border="0" cellpadding="0" cellspacing="0">
							<tr>
								<td rowspan="2" align="center">
									<bean:message  bundle="comm.commLocale" key="comm.jsp.commom.name"/>
								</td>
								<td align="bottom" height="12">
									<img src="include/images/cmdOrderByAsc.gif" border="0" alt="<bean:message  bundle="comm.commLocale" key="comm.jsp.commom.text21"/>" style="cursor: hand" onClick="commandOrderBy('2', '0')">
								</td>
							</tr>
							<tr>
								<td height="12">
									<img src="include/images/cmdOrderByDesc.gif" border="0" alt="<bean:message  bundle="comm.commLocale" key="comm.jsp.commom.text17"/>" style="cursor: hand" onClick="commandOrderBy('2', '1')">
								</td>
							</tr>
						</table>
					</td>
					<td width="116px">
						<table width="100%" border="0" cellpadding="0" cellspacing="0">
							<tr>
								<td rowspan="2" align="center">
									<bean:message  bundle="comm.commLocale" key="comm.jsp.commom.seqNo"/>
								</td>
								<td align="bottom" height="12">
									<img src="include/images/cmdOrderByAsc.gif" border="0" alt="<bean:message  bundle="comm.commLocale" key="comm.jsp.commom.text21"/>" style="cursor: hand" onClick="commandOrderBy('3', '0')">
								</td>
							</tr>
							<tr>
								<td height="12">
									<img src="include/images/cmdOrderByDesc.gif" border="0" alt="<bean:message  bundle="comm.commLocale" key="comm.jsp.commom.text17"/>" style="cursor: hand" onClick="commandOrderBy('3', '1')">
								</td>
							</tr>
						</table>
					</td>
					<td width="116px">
						<table width="100%" border="0" cellpadding="0" cellspacing="0">
							<tr>
								<td rowspan="2" align="center">
									<bean:message  bundle="comm.commLocale" key="comm.jsp.commom.inputCode"/>
								</td>
								<td align="bottom" height="12">
									<img src="include/images/cmdOrderByAsc.gif" border="0" alt="<bean:message  bundle="comm.commLocale" key="comm.jsp.commom.text21"/>" style="cursor: hand" onClick="commandOrderBy('4', '0')">
								</td>
							</tr>
							<tr>
								<td height="12">
									<img src="include/images/cmdOrderByDesc.gif" border="0" alt="<bean:message  bundle="comm.commLocale" key="comm.jsp.commom.text17"/>" style="cursor: hand" onClick="commandOrderBy('4', '1')">
								</td>
							</tr>
						</table>
					</td>
					<td width="40px">
						<bean:message  bundle="comm.commLocale" key="comm.jsp.commom.search"/>
					</td>
					<td width="40px">
						<bean:message  bundle="comm.commLocale" key="comm.jsp.commom.change"/>
					</td>
					<td width="40px">
						<bean:message  bundle="comm.commLocale" key="comm.jsp.commom.delete"/>
					</td>
				</tr>
				<tbody id="interval_row_id">
					<%
							if (commConfigDepttype.getItemCodeList() != null && commConfigDepttype.getItemCodeList().length > 0) {
							for (int i = 0; i < commConfigDepttype.getItemCodeList().length; i++) {
					%>
					<tr class="list_neirong">
						<td>
							<%=commConfigDepttype.getItemCodeList()[i]%>
						</td>
						<td>
							<%=commConfigDepttype.getItemNameList()[i]%>
						</td>
						<td>
							<%=commConfigDepttype.getSeqNoList()[i]%>
						</td>
						<td>
							<%=commConfigDepttype.getInputCodeList()[i]%>
						</td>
						<td>
							<img onClick="cmdView('<%=commConfigDepttype.getItemCodeList()[i]%>')" alt="<bean:message  bundle="comm.commLocale" key="comm.jsp.commom.detail"/>" src="include/images/cmdView_s.jpg" border="0" style="cursor: hand; vertical-align: middle;" />
						</td>
						<td>
							<img onClick="cmdEdit('<%=commConfigDepttype.getItemCodeList()[i]%>')" alt="<bean:message  bundle="comm.commLocale" key="comm.jsp.commom.change"/>" src="include/images/cmdEdit_s.jpg" border="0" style="cursor: hand; vertical-align: middle;" />
						</td>
						<td>
							<img onClick="cmdDel('<%=commConfigDepttype.getItemCodeList()[i]%>')" alt="<bean:message  bundle="comm.commLocale" key="comm.jsp.commom.delete"/>" src="include/images/cmdDel_s.jpg" border="0" style="cursor: hand; vertical-align: middle;" />
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
						<bean:message  bundle="comm.commLocale" key="comm.jsp.commom.text15"/>
						<%=curPage%>
						<bean:message  bundle="comm.commLocale" key="comm.jsp.commom.pageTotal"/>
						<%=totalPage%>
						<bean:message  bundle="comm.commLocale" key="comm.jsp.commom.pageTotal1"/>
						<%=totalNum%>
						<bean:message  bundle="comm.commLocale" key="comm.jsp.commom.record"/>
						<%
						if (curPage > 1) {
						%>
						<a href="javascript:goPage('0')"><img src="include/images/shouye.gif" align="middle" border="0" />
						</a>&nbsp;
						<a href="javascript:goPage('<%=curPage - 1%>')"><img src="include/images/shang.gif" align="middle" border="0" />
						</a>&nbsp;
						<%
								} else {
								out.println("<img src='include/images/shouye_s.gif' align='middle' border='0px' />&nbsp;&nbsp;<img src='include/images/shang_s.gif' align='middle' border='0px' />&nbsp;");
							}
							if (curPage < totalPage) {
						%>
						<a href="javascript:goPage('<%=curPage + 1%>')"><img src="include/images/xia.gif" align="middle" border="0" />
						</a>&nbsp;
						<a href="javascript:goPage('<%=totalPage%>')"><img src="include/images/mo.gif" align="middle" border="0" />
						</a>&nbsp;
						<%
								} else {
								out.println("<img src='include/images/xia_x.gif' align='middle' border='0px' />&nbsp;&nbsp;<img src='include/images/mo_m.gif' align='middle' border='0px' />&nbsp;");
							}
						%>
						<bean:message  bundle="comm.commLocale" key="comm.jsp.commom.text2"/>
						<input id="_tp" name="page" type="text" value="<%=curPage%>" size="2" class="txt">
						<bean:message  bundle="comm.commLocale" key="comm.jsp.commom.page"/>&nbsp;
						<img style="cursor: hand;" src="include/images/go.jpg" width="18" border="0" onClick="goPage2()" />
					</td>
				</tr>
			</table>
		</form>
		<script language="javascript" src="include/javascript/interval_row_color.js"></script>
	</body>
</html>
