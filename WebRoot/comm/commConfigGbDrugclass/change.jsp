<%@page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@page import="com.tianjian.util.comm.PageBean"%>
<jsp:useBean id="data" scope="request" class="com.tianjian.comm.struts.form.CommConfigGbDrugclassForm" />
<jsp:useBean id="pb" scope="request" class="com.tianjian.util.comm.PageBean" />

<html>
	<head>
	<%if(request.getServerPort() == 80) {%>
  <base href="http://<%=request.getServerName()%><%=request.getContextPath()%>/">
  <%} else {%>
  <base href="http://<%=request.getServerName()%>:<%=request.getServerPort()%><%=request.getContextPath()%>/">
<%}%>
		<title><bean:message key="comm.jsp.common.text50" bundle="conf.comm.comm"/></title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">	
		<script language="javascript" src="<bean:message key="comm.js.CommMessage.path" bundle="conf.comm.comm"/>"></script>
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
    document.form.itemCode.value = id;  
    document.form.verbId.value = "detail";    
    document.form.submit();
}

//修改
function cmdEdit(id) {
    document.form.itemCode.value = id;  
    document.form.verbId.value = "updateInit";    
    document.form.submit();     
}

//删除
function cmdDel(id) {    
    if (!confirm(getCommMessage("10005","",""))) return false;    
    document.form.itemCode.value = id;  
    document.form.verbId.value = "delete";    
    document.form.submit();    
}

function goPage(page) {  
   document.form.page.value = page;
   document.form.verbId.value = "change";    
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
		<form name="form" method="post"
			action="<%=request.getContextPath()%>/comm/commConfigGbDrugclass.do">
			<!-- Head line -->
			<input type="hidden" name="verbId" value="<%=data.getVerbId()%>">
			<input type="hidden" name="orderNo" value="<%=data.getOrderNo()%>">
			<input type="hidden" name="asc" value="<%=data.getAsc()%>">
			

			<!--查询条件-->
			<table width="700px" align="center" class="table3" align="center" border="0"cellspacing="0" cellpadding="0">
				<tr>
					<td id="list_L"></td>
					<td class="list_biaoti">
						<bean:message key="comm.jsp.different.text60" bundle="conf.comm.comm"/>
					</td>
					<td id="list_C"></td>
					<td class="list_cc">
						<bean:message key="comm.jsp.common.text27" bundle="conf.comm.comm"/>：
						<input name="itemCode" type="text" style="width: 100px;" onkeypress="eventOnKeyPress('name')"
							value="<%=data.getItemCode()%>">
					</td>
					<td class="list_cc">
						<bean:message key="comm.jsp.common.text28" bundle="conf.comm.comm"/>：
						<input name="name" type="text" style="width: 100px;" onkeypress="eventOnKeyPress('inputCode')"
							value="<%=data.getItemName()%>">
					</td>
					<td class="list_cc">
						<bean:message key="comm.jsp.common.text38" bundle="conf.comm.comm"/>：
						<input name="inputCode" type="text" style="width: 100px;" onkeypress="eventOnKeyPress('<bean:message key="comm.jsp.common.text40" bundle="conf.comm.comm"/>')"
							value="<%=data.getInputCode()%>">
					</td>
					<td id="list_R"></td>
					<td class="button">
						<input type="button"
							style="cursor: hand; background: url(include/images/tong.gif) no-repeat; border: 0; width: 50px; height: 23px; font-size: 12px;"
							name="<bean:message key="comm.jsp.common.text40" bundle="conf.comm.comm"/>" value="<bean:message key="comm.jsp.common.text401" bundle="conf.comm.comm"/>" onClick="submitQueryForm();" />
					</td>
				</tr>
			</table>
			<!--列表标题-->
			<table width="700px" align="center" class="table" border="0"
				cellspacing="0" cellpadding="0">
				<tr class="list_title">
					<td colspan="3" height="5px">
						<b class="rt"><b class="r1"></b><b class="r2"></b><b
							class="r3"></b><b class="r4"></b>
						</b>
					</td>
				</tr>
				<tr class="yuan_height">
					<td width="5%" align="center" valign="middle"
						style="border-left: 1px solid #BFBFBF;">
						<img
							src="<%=request.getContextPath()%>/comm/include/images/comm_list_nav_red.jpg">
					</td>
					<td width="60%" align="left" class="jiacu">
						<bean:message key="comm.jsp.common.text7" bundle="conf.comm.comm"/>
					</td>
					<td width="35%" align="right"
						style="border-right: 1px solid #BFBFBF; padding: 0px 10px 0px 0px;">
						
					</td>
				</tr>
			</table>
			<!--列表内容-->
			<table class="table" align="center" width="700px" cellspacing="1"
				cellpadding="0">
				<tr class="list_nav">
					<td width="130px">
						<table width="100%" border="0" cellpadding="0" cellspacing="0">
							<tr>
								<td rowspan="2" align="center">
									<bean:message key="comm.jsp.common.text27" bundle="conf.comm.comm"/>
								</td>
								<td align="bottom" height="12">
									<img src="include/images/cmdOrderByAsc.gif" border="0" alt="<bean:message key="comm.jsp.common.text36" bundle="conf.comm.comm"/>"
										style="cursor: hand" onClick="commandOrderBy('1', '0')">
								</td>
							</tr>
							<tr>
								<td height="12">
									<img src="include/images/cmdOrderByDesc.gif" border="0"
										alt="<bean:message key="comm.jsp.common.text26" bundle="conf.comm.comm"/>" style="cursor: hand"
										onClick="commandOrderBy('1', '1')">
								</td>
							</tr>
						</table>
					</td>
					<td width="140px">
						<table width="100%" border="0" cellpadding="0" cellspacing="0">
							<tr>
								<td rowspan="2" align="center">
									<bean:message key="comm.jsp.common.text28" bundle="conf.comm.comm"/>
								</td>
								<td valign="bottom" height="12">
									<img src="include/images/cmdOrderByAsc.gif" border="0" alt="<bean:message key="comm.jsp.common.text36" bundle="conf.comm.comm"/>"
										style="cursor: hand" onClick="commandOrderBy('2', '0')">
								</td>
							</tr>
							<tr>
								<td height="12">
									<img src="include/images/cmdOrderByDesc.gif" border="0"
										alt="<bean:message key="comm.jsp.common.text26" bundle="conf.comm.comm"/>" style="cursor: hand"
										onClick="commandOrderBy('2', '1')">
								</td>
							</tr>
						</table>
					</td>
					<td width="190px">
						<table width="100%" border="0"  align="center" cellpadding="0" cellspacing="0">
							<tr>
								<td rowspan="2" align="center">
									<bean:message key="comm.jsp.common.text38" bundle="conf.comm.comm"/>
								</td>
								<td align="bottom" height="12">
									<img src="include/images/cmdOrderByAsc.gif" border="0" alt="<bean:message key="comm.jsp.common.text36" bundle="conf.comm.comm"/>"
										style="cursor: hand" onClick="commandOrderBy('3', '0')">
								</td>
							</tr>
							<tr>
								<td height="12">
									<img src="include/images/cmdOrderByDesc.gif" border="0"
										alt="<bean:message key="comm.jsp.common.text26" bundle="conf.comm.comm"/>" style="cursor: hand"
										onClick="commandOrderBy('3', '1')">
								</td>
							</tr>
						</table>
					</td>
					<td width="100px">
						<table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
							<tr>
								<td rowspan="2" align="center">
									<bean:message key="comm.jsp.common.text4" bundle="conf.comm.comm"/>
								</td>
								<td align="bottom" height="12">
									<img src="include/images/cmdOrderByAsc.gif" border="0" alt="<bean:message key="comm.jsp.common.text36" bundle="conf.comm.comm"/>"
										style="cursor: hand" onClick="commandOrderBy('4', '0')">
								</td>
							</tr>
							<tr>
								<td height="12">
									<img src="include/images/cmdOrderByDesc.gif" border="0"
										alt="<bean:message key="comm.jsp.common.text26" bundle="conf.comm.comm"/>" style="cursor: hand"
										onClick="commandOrderBy('4', '1')">
								</td>
							</tr>
						</table>

					</td>
					<td width="40px">
						<bean:message key="comm.jsp.common.text47" bundle="conf.comm.comm"/>
					</td>
					<td width="40px">
						<bean:message key="comm.jsp.common.text35" bundle="conf.comm.comm"/>
					</td>

					
				</tr>
				<tbody id="interval_row_id">
					<%if (data.getItemCodeList() != null
							&& data.getItemCodeList().length > 0) {
						for (int i = 0; i < data.getItemCodeList().length; i++) 
					{
					%>
						<tr class="list_neirong">
							<td>
								<%=data.getItemCodeList()[i]%>
							</td>
							<td>
								<%=data.getItemNameList()[i]%>
							</td>
							<td>
								<%=data.getInputCodeList()[i]%>
							</td>
							<td>
								<%=data.getCommentsList()[i]%>
							</td>
							
							<td>
							<img onClick="cmdEdit('<%=data.getItemCodeList()[i] %>')"
								alt="<bean:message key="comm.jsp.common.text47" bundle="conf.comm.comm"/>" src="include/images/cmdEdit_s.jpg" border="0"
								style="cursor: hand; vertical-align: middle;" />
						</td>
						<td>
							<img onClick="cmdDel('<%=data.getItemCodeList()[i] %>')"
								alt="<bean:message key="comm.jsp.common.text35" bundle="conf.comm.comm"/>" src="include/images/cmdDel_s.jpg" border="0"
								style="cursor: hand; vertical-align: middle;" />
						</td>
						</tr>
					<%
						}
						}
					%>
				</tbody>
				<tr>
					<td colspan="10" align="center" bgcolor="#ffffff" height="35px">
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

						<input id="_total" name="totalPage" type="hidden"
							value="<%=totalPage%>">
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
						| &nbsp;<bean:message key="comm.jsp.common.text12" bundle="conf.comm.comm"/>
						<input id="_tp" name="page" type="text" value="<%=curPage%>"
							size="2" class="txt">
						<bean:message key="comm.jsp.common.text53" bundle="conf.comm.comm"/>&nbsp;
						<img style="cursor: hand;" src="include/images/go.jpg" width="18"
							border="0" onClick="goPage2()" />

					</td>
				</tr>

			</table>

		</form>
		</body>
		<script language="javascript"
			src="include/javascript/interval_row_color.js"></script>
</html>
