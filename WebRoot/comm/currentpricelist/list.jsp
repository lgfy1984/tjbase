<%@page contentType="text/html; charset=utf-8"%>
<%@page import="com.tianjian.util.comm.PageBean"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<jsp:useBean id="data" scope="request" class="com.tianjian.comm.struts.form.CurrentPriceListForm" />
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
		<title></title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<script language="javascript" src="include/javascript/TJMessage.js"></script>
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
    document.form.idHidden.value = id;  
    document.form.verbId.value = "delete";    
    document.form.submit();  
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
      alert(document.form.cwdzs.value);
      return;
    }
    if (_tp.value<=0){
    	alert(document.form.ysbxdy.value);
		return;
    }
    if(parseInt(_tp.value)>parseInt(_total.value)){
      alert(document.form.bndyzys.value);
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
		<form name="form" method="post" action="comm/currentpricelist.do">

		<input type="hidden" id="cwdzs" name="cwdzs" value='<bean:message  key="comm.jsp.drugpricelist.cwdzs" bundle="confcommdrugmessageInit"/>'/>
		<input type="hidden" id="ysbxdy" name="ysbxdy" value='<bean:message  key="comm.jsp.drugpricelist.ysbxdy" bundle="confcommdrugmessageInit"/>'/>
		<input type="hidden" id="bndyzys" name="bndyzys" value='<bean:message  key="comm.jsp.drugpricelist.bndyzys" bundle="confcommdrugmessageInit"/>'/>
		<!-- Head line -->
			<input type="hidden" name="verbId" value="<%=data.getVerbId()%>">
			<input type="hidden" name="orderNo" value="<%=data.getOrderNo()%>">
			<input type="hidden" name="asc" value="<%=data.getAsc()%>">
			<input type="hidden" name="idHidden" value="<%=data.getIdHidden()%>">
			<!--查询条件-->
			<table width="800px" class="table2" align="center" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td id="list_L"></td>
					<td class="list_biaoti">
						<bean:message  key="comm.jsp.drugpricelist.list.title" bundle="confcommdrugmessageInit"/>
					</td>
					<td id="list_C"></td>
					<td class="list_cc">
						<bean:message  key="comm.jsp.drugpricelist.add.eancode" bundle="confcommdrugmessageInit"/>:
						<input name="eanCode" type="text" onkeypress="eventOnKeyPress('itemName')" value="<%=data.getEanCode()%>">
					</td>
					<td class="list_cc">
						<bean:message  key="comm.jsp.drugpricelist.add.DRUG_CODE" bundle="confcommdrugmessageInit"/>:
						<input name="itemName" type="text" onkeypress="eventOnKeyPress('btnSearch')" value="<%=data.getDrugName()%>">
					</td>
					<td id="list_R"></td>
					<td class="button">
						<input type="button" id="btnSearch" name="btnSearch" style="cursor: hand; background: url(include/images/tong.gif) no-repeat; border: 0; width: 50px; height: 23px; font-size: 12px;" name='<bean:message  key="comm.jsp.drugpricelist.tijiao" bundle="confcommdrugmessageInit"/>' value='<bean:message  key="comm.jsp.drugpricelist.tijiao" bundle="confcommdrugmessageInit"/>' onClick="submitQueryForm();" />
					</td>
				</tr>
			</table>
			<!--列表标题-->
			<table width="800px" align="center" class="table" border="0" cellspacing="0" cellpadding="0">
				<tr class="list_title">
					<td colspan="3" height="5px">
						<b class="rt"><b class="r1"></b><b class="r2"></b><b class="r3"></b><b class="r4"></b> </b>
					</td>
				</tr>
				<tr class="yuan_height">
					<td width="5%" align="center" valign="middle" style="border-left: 1px solid #BFBFBF;">
						<img src="<%=request.getContextPath()%>/comm/include/images/comm_list_nav_red.jpg">
					</td>
					<td width="60%" align="left" class="jiacu">
						<bean:message  key="comm.jsp.drugpricelist.list.toptitle" bundle="confcommdrugmessageInit"/>
					</td>
				</tr>
			</table>
			<!--列表内容-->
			<table class="table" align="center" width="800px" cellspacing="1" cellpadding="0">
				<tr class="list_nav">
					<td width="100px">
						<table width="100%" border="0" cellpadding="0" cellspacing="0">
							<tr>
								<td rowspan="2" align="center">
									<bean:message  key="comm.jsp.drugpricelist.add.eancode" bundle="confcommdrugmessageInit"/>
								</td>
								<td align="bottom" height="12">
									<img src="include/images/cmdOrderByAsc.gif" border="0" alt='<bean:message  key="comm.jsp.drugpricelist.shengx" bundle="confcommdrugmessageInit"/>' style="cursor: hand" onClick="commandOrderBy('1', '0')">
								</td>
							</tr>
							<tr>
								<td height="12">
									<img src="include/images/cmdOrderByDesc.gif" border="0" alt='<bean:message  key="comm.jsp.drugpricelist.jiangx" bundle="confcommdrugmessageInit"/>' style="cursor: hand" onClick="commandOrderBy('1', '1')">
								</td>
							</tr>
						</table>
					</td>
					<td width="100px">
						<table width="100%" border="0" cellpadding="0" cellspacing="0">
							<tr>
								<td rowspan="2" align="center">
									<bean:message  key="comm.jsp.drugpricelist.add.DRUG_CODE" bundle="confcommdrugmessageInit"/>
								</td>
								<td align="bottom" height="12">
									<img src="include/images/cmdOrderByAsc.gif" border="0"  alt='<bean:message  key="comm.jsp.drugpricelist.shengx" bundle="confcommdrugmessageInit"/>' style="cursor: hand" onClick="commandOrderBy('2', '0')">
								</td>
							</tr>
							<tr>
								<td height="12">
									<img src="include/images/cmdOrderByDesc.gif" border="0" alt='<bean:message  key="comm.jsp.drugpricelist.jiangx" bundle="confcommdrugmessageInit"/>' style="cursor: hand" onClick="commandOrderBy('2', '1')">
								</td>
							</tr>
						</table>
					</td>
					<td width="50px">
						<table width="100%" border="0" cellpadding="0" cellspacing="0">
							<tr>
								<td rowspan="2" align="center">
									<bean:message  key="comm.jsp.drugpricelist.add.DRUG_SPEC" bundle="confcommdrugmessageInit"/>
								</td>
								<td align="bottom" height="12">
									<img src="include/images/cmdOrderByAsc.gif" border="0" alt='<bean:message  key="comm.jsp.drugpricelist.shengx" bundle="confcommdrugmessageInit"/>' style="cursor: hand" onClick="commandOrderBy('3', '0')">
								</td>
							</tr>
							<tr>
								<td height="12">
									<img src="include/images/cmdOrderByDesc.gif" border="0" alt='<bean:message  key="comm.jsp.drugpricelist.jiangx" bundle="confcommdrugmessageInit"/>' style="cursor: hand" onClick="commandOrderBy('3', '1')">
								</td>
							</tr>
						</table>
					</td>
					<td width="50px">
						<table width="100%" border="0" cellpadding="0" cellspacing="0">
							<tr>
								<td rowspan="2" align="center">
								<bean:message  key="comm.jsp.drugpricelist.add.UNITS" bundle="confcommdrugmessageInit"/>
								</td>
								<td align="bottom" height="12">
									<img src="include/images/cmdOrderByAsc.gif" border="0" alt='<bean:message  key="comm.jsp.drugpricelist.shengx" bundle="confcommdrugmessageInit"/>' style="cursor: hand" onClick="commandOrderBy('4', '0')">
								</td>
							</tr>
							<tr>
								<td height="12">
									<img src="include/images/cmdOrderByDesc.gif" border="0" alt='<bean:message  key="comm.jsp.drugpricelist.jiangx" bundle="confcommdrugmessageInit"/>' style="cursor: hand" onClick="commandOrderBy('4', '1')">
								</td>
							</tr>
						</table>
					</td>
					<td width="60px">
						<table width="100%" border="0" cellpadding="0" cellspacing="0">
							<tr>
								<td rowspan="2" align="center">
								<bean:message  key="comm.jsp.drugpricelist.add.RETAIL_PRICE" bundle="confcommdrugmessageInit"/>
								</td>
								<td align="bottom" height="12">
									<img src="include/images/cmdOrderByAsc.gif" border="0" alt='<bean:message  key="comm.jsp.drugpricelist.shengx" bundle="confcommdrugmessageInit"/>' style="cursor: hand" onClick="commandOrderBy('5', '0')">
								</td>
							</tr>
							<tr>
								<td height="12">
									<img src="include/images/cmdOrderByDesc.gif" border="0" alt='<bean:message  key="comm.jsp.drugpricelist.jiangx" bundle="confcommdrugmessageInit"/>' style="cursor: hand" onClick="commandOrderBy('5', '1')">
								</td>
							</tr>
						</table>
					</td>
					<td width="100px">
						<table width="100%" border="0" cellpadding="0" cellspacing="0">
							<tr>
								<td rowspan="2" align="center">
								<bean:message  key="comm.jsp.drugpricelist.add.START_DATE" bundle="confcommdrugmessageInit"/>
								</td>
								<td align="bottom" height="12">
									<img src="include/images/cmdOrderByAsc.gif" border="0" alt='<bean:message  key="comm.jsp.drugpricelist.shengx" bundle="confcommdrugmessageInit"/>' style="cursor: hand" onClick="commandOrderBy('6', '0')">
								</td>
							</tr>
							<tr>
								<td height="12">
									<img src="include/images/cmdOrderByDesc.gif" border="0" alt='<bean:message  key="comm.jsp.drugpricelist.jiangx" bundle="confcommdrugmessageInit"/>' style="cursor: hand" onClick="commandOrderBy('6', '1')">
								</td>
							</tr>
						</table>
					</td>
					<td width="100px">
						<table width="100%" border="0" cellpadding="0" cellspacing="0">
							<tr>
								<td rowspan="2" align="center">
								<bean:message  key="comm.jsp.drugpricelist.add.STOP_DATE" bundle="confcommdrugmessageInit"/>
								</td>
								<td align="bottom" height="12">
									<img src="include/images/cmdOrderByAsc.gif" border="0" alt='<bean:message  key="comm.jsp.drugpricelist.shengx" bundle="confcommdrugmessageInit"/>' style="cursor: hand" onClick="commandOrderBy('7', '0')">
								</td>
							</tr>
							<tr>
								<td height="12">
									<img src="include/images/cmdOrderByDesc.gif" border="0" alt='<bean:message  key="comm.jsp.drugpricelist.jiangx" bundle="confcommdrugmessageInit"/>' style="cursor: hand" onClick="commandOrderBy('7', '1')">
								</td>
							</tr>
						</table>
					</td>
					<td width="40px">
						<bean:message  key="comm.jsp.drugpricelist.list.view" bundle="confcommdrugmessageInit"/>
					</td>
					<!--<td width="40px">
						<bean:message  key="comm.jsp.drugpricelist.list.update" bundle="confcommdrugmessageInit"/>
					</td>
					<td width="40px">
						<bean:message  key="comm.jsp.drugpricelist.list.delete" bundle="confcommdrugmessageInit"/>
					</td>-->
				</tr>
				<tbody id="interval_row_id">
					<%
							if (data.getEanCodelist() != null && data.getEanCodelist().length > 0) {
							for (int i = 0; i < data.getEanCodelist().length; i++) {
					%>
					<tr class="list_neirong">
						<td>
							<%=data.getEanCodelist()[i]%>
						</td>
						<td>
							<%=data.getItemnamelist()[i]%>
						</td>
						<td>
							<%=data.getDrugSpeclist()[i]%>
						</td>
						<td>
							<%=data.getUnitslist()[i]%>
						</td>
						<td>
							<%=data.getRetailPricelist()[i]%>
						</td>
						<td>
							<%=data.getStartDatelist()[i]%>
						</td>
						<td>
							<%=data.getStopDatelist()[i]%>
						</td>
						<td>
							<img onClick="cmdView('<%=data.getIdlist()[i]%>')" alt='<bean:message  key="comm.jsp.drugpricelist.list.view" bundle="confcommdrugmessageInit"/>' src="include/images/cmdView_s.jpg" border="0" style="cursor: hand; vertical-align: middle;" />
						</td>
						<!--  <td>
							<img onClick="cmdEdit('<%=data.getIdlist()[i]%>')" alt='<bean:message  key="comm.jsp.drugpricelist.list.update" bundle="confcommdrugmessageInit"/>' src="include/images/cmdEdit_s.jpg" border="0" style="cursor: hand; vertical-align: middle;" />
						</td>
						<td>
							<img onClick="cmdDel('<%=data.getIdlist()[i]%>')" alt='<bean:message  key="comm.jsp.drugpricelist.list.delete" bundle="confcommdrugmessageInit"/>' src="include/images/cmdDel_s.jpg" border="0" style="cursor: hand; vertical-align: middle;" />
						</td>-->
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
						<bean:message  key="comm.jsp.drugpricelist.di" bundle="confcommdrugmessageInit"/>
						<%=curPage%>
						<bean:message  key="comm.jsp.drugpricelist.ye" bundle="confcommdrugmessageInit"/>/<bean:message  key="comm.jsp.drugpricelist.gong" bundle="confcommdrugmessageInit"/>
						<%=totalPage%>
						<bean:message  key="comm.jsp.drugpricelist.ye" bundle="confcommdrugmessageInit"/>&nbsp;<bean:message  key="comm.jsp.drugpricelist.gong" bundle="confcommdrugmessageInit"/>
						<%=totalNum%>
						<bean:message  key="comm.jsp.drugpricelist.tiao" bundle="confcommdrugmessageInit"/><bean:message  key="comm.jsp.drugpricelist.jilu" bundle="confcommdrugmessageInit"/>&nbsp;|&nbsp;
						<%
						if (curPage > 1) {
						%>
						<a href="javascript:goPage('0')"><img src="include/images/shouye.gif" align="middle" border="0" /> </a>&nbsp;
						<a href="javascript:goPage('<%=curPage - 1%>')"><img src="include/images/shang.gif" align="middle" border="0" /> </a>&nbsp;
						<%
								} else {
								out.println("<img src='include/images/shouye_s.gif' align='middle' border='0px' />&nbsp;&nbsp;<img src='include/images/shang_s.gif' align='middle' border='0px' />&nbsp;");
							}
							if (curPage < totalPage) {
						%>
						<a href="javascript:goPage('<%=curPage + 1%>')"><img src="include/images/xia.gif" align="middle" border="0" /> </a>&nbsp;
						<a href="javascript:goPage('<%=totalPage%>')"><img src="include/images/mo.gif" align="middle" border="0" /> </a>&nbsp;
						<%
								} else {
								out.println("<img src='include/images/xia_x.gif' align='middle' border='0px' />&nbsp;&nbsp;<img src='include/images/mo_m.gif' align='middle' border='0px' />&nbsp;");
							}
						%>
						| &nbsp;<bean:message  key="comm.jsp.drugpricelist.dao" bundle="confcommdrugmessageInit"/><bean:message  key="comm.jsp.drugpricelist.di" bundle="confcommdrugmessageInit"/>
						<input id="_tp" name="page" type="text" value="<%=curPage%>" size="2" class="txt">
						<bean:message  key="comm.jsp.drugpricelist.ye" bundle="confcommdrugmessageInit"/>&nbsp;
						<img style="cursor: hand;" src="include/images/go.jpg" width="18" border="0" onClick="goPage2()" />
					</td>
				</tr>
			</table>
		</form>
		<script language="javascript" src="include/javascript/interval_row_color.js"></script>
	</body>
</html>
