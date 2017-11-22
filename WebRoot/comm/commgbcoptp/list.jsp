<%@page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="html" uri="/WEB-INF/struts-html.tld"%>
<%@ taglib prefix="bean" uri="/WEB-INF/struts-bean.tld"%>
<%@ taglib prefix="logic" uri="/WEB-INF/struts-logic.tld"%>
<%@page import="com.tianjian.util.comm.PageBean"%>
<jsp:useBean id="commGbCoptp" scope="request" type="com.tianjian.comm.struts.form.CommGbCoptpForm" />
<jsp:useBean id="pb" scope="request" class="com.tianjian.util.comm.PageBean" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%if(request.getServerPort() == 80) {%>
<base href="http://<%=request.getServerName()%><%=request.getContextPath()%>/" />
<%} else {%>
<base href="http://<%=request.getServerName()%>:<%=request.getServerPort()%><%=request.getContextPath()%>/" />
<%}%>
<title><bean:message key="Comm.jsp.commom.ptpDictInfo" bundle="conf.comm.CommMessage"/></title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />	
<script language="javascript" src="<bean:message  key="Comm.js.TJMessagepath"  bundle="conf.comm.CommMessage"/>" ></script>
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
      alert("<bean:message key="js.pagewarn1" bundle="conf.Init" />");
      return;
    }
    if (_tp.value<=0){
    	alert("<bean:message key="js.pagewarn2" bundle="conf.Init" />");
		return;
    }
    if(parseInt(_tp.value)>parseInt(_total.value)){
      alert("<bean:message key="js.pagewarn3" bundle="conf.Init" />");
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
		<form name="form" method="post" action="comm/commGbCoptp.do">
			<!-- Head line -->
			<input type="hidden" name="verbId" value="<%=commGbCoptp.getVerbId() %>" />
			<input type="hidden" name="orderNo" value="<%=commGbCoptp.getOrderNo() %>" />
			<input type="hidden" name="asc" value="<%=commGbCoptp.getAsc() %>" />
			<input type="hidden" name="itemCodeHidden" value="<%=commGbCoptp.getItemCodeHidden() %>" />

			<!--查询条件-->
			<table width="95%" class="table2" align="center" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td id="list_L"></td>
					<td class="list_biaoti">
						<bean:message key="Comm.jsp.commom.ptpDict" bundle="conf.comm.CommMessage"/>
					</td>
					<td id="list_C"></td>
					<td class="list_cc">
						<bean:message key="Comm.jsp.commom.code" bundle="conf.comm.CommMessage"/>：
						<input name="itemCode" type="text" onkeypress="eventOnKeyPress('itemName')" value="<%=commGbCoptp.getItemCode() %>" />
					</td>
					<td class="list_cc">
						<bean:message key="Comm.jsp.commom.name" bundle="conf.comm.CommMessage"/>：
						<input name="itemName" type="text"  onkeypress="eventOnKeyPress('inputCode')" value="<%=commGbCoptp.getItemName() %>" />
					</td>
					<td class="list_cc">
						<bean:message key="jsp.inputNo" bundle="conf.Init"/>：
						<input name="inputCode" type="text" onkeypress="eventOnKeyPress('<bean:message key="jsp.btnsubmit" bundle="conf.Init"/>')" value="<%=commGbCoptp.getInputCode() %>" />
					</td>
					<td id="list_R"></td>
					<td class="button">
						<input type="button" style="cursor:hand; background:url(include/images/tong.gif) no-repeat; border:0; width:50px; height:23px; font-size:12px;" name="<bean:message key="jsp.btnsubmit" bundle="conf.Init"/>" value="<bean:message key="jsp.btnsubmitSpace" bundle="conf.Init"/>" onclick="submitQueryForm();" />
					</td>
				</tr>
			</table>
			<!--列表标题-->
			<table width="95%" align="center" class="table" border="0" cellspacing="0" cellpadding="0">
				<tr class="list_title">
					<td colspan="3" height="5px">
						<b class="rt"><b class="r1"></b><b class="r2"></b><b class="r3"></b><b class="r4"></b></b>
					</td>
				</tr>
				<tr class="yuan_height">
					<td width="48" align="center" valign="middle" style="border-left:1px solid #BFBFBF;">
						<img src="comm/include/images/comm_list_nav_red.jpg" />
					</td>
					<td width="481" align="left" class="jiacu">
						<bean:message key="jsp.resultlist" bundle="conf.Init"/>
					</td>
					<td width="300" align="right" style="border-right:1px solid #BFBFBF; padding:0px 10px 0px 0px;">
						<a href="javascript:cmdAdd()"><img border=0 alt="<bean:message key="jsp.add" bundle="conf.Init" />" src="<bean:message key="security.jsp.securityconfigpublicclass.add" bundle="conf.security.security"/>" style="cursor:hand" /></a>
					</td>
				</tr>
			</table>
			<!--列表内容-->
			<table class="table" align="center" width="95%" cellspacing="1" cellpadding="0">
				<tr class="list_nav">
					<td width="80px">
						<bean:message key="Comm.jsp.commom.code" bundle="conf.comm.CommMessage"/><span style="margin:0;padding:0;width:7px;height:15px;display:block;position:absolute;right:5px;top:0px;">
			<span style="margin:0;padding:0;width:7px;height:4px;display:block;"><img src="include/images/cmdOrderByAsc.gif" border="0" alt="<bean:message key="jsp.ascorder" bundle="conf.Init"/>" style="cursor:hand" onclick="commandOrderBy('1', '0')" /></span>
			<span style="margin:0;margin-top:5px;padding:0;width:7px;height:4px;display:block;"><img src="include/images/cmdOrderByDesc.gif" border="0" alt="<bean:message key="jsp.descorder" bundle="conf.Init"/>" style="cursor: hand" onclick="commandOrderBy('1', '1')" /></span>
			</span>						
					</td>
					<td width="100px">
						<bean:message key="Comm.jsp.commom.name" bundle="conf.comm.CommMessage"/><span style="margin:0;padding:0;width:7px;height:15px;display:block;position:absolute;right:5px;top:0px;">
			<span style="margin:0;padding:0;width:7px;height:4px;display:block;"><img src="include/images/cmdOrderByAsc.gif" border="0" alt="<bean:message key="jsp.ascorder" bundle="conf.Init"/>" style="cursor:hand" onclick="commandOrderBy('2', '0')" /></span>
			<span style="margin:0;margin-top:5px;padding:0;width:7px;height:4px;display:block;"><img src="include/images/cmdOrderByDesc.gif" border="0" alt="<bean:message key="jsp.descorder" bundle="conf.Init"/>" style="cursor: hand" onclick="commandOrderBy('2', '1')" /></span>
			</span>						
					</td>
					<td width="80px">
						<bean:message key="jsp.seqNo" bundle="conf.Init"/><span style="margin:0;padding:0;width:7px;height:15px;display:block;position:absolute;right:5px;top:0px;">
			<span style="margin:0;padding:0;width:7px;height:4px;display:block;"><img src="include/images/cmdOrderByAsc.gif" border="0" alt="<bean:message key="jsp.ascorder" bundle="conf.Init"/>" style="cursor:hand" onclick="commandOrderBy('3', '0')" /></span>
			<span style="margin:0;margin-top:5px;padding:0;width:7px;height:4px;display:block;"><img src="include/images/cmdOrderByDesc.gif" border="0" alt="<bean:message key="jsp.descorder" bundle="conf.Init"/>" style="cursor: hand" onclick="commandOrderBy('3', '1')" /></span>
			</span>
						
					</td>
					<td width="80px">
						<bean:message key="Comm.jsp.commom.level" bundle="conf.comm.CommMessage"/><span style="margin:0;padding:0;width:7px;height:15px;display:block;position:absolute;right:5px;top:0px;">
			<span style="margin:0;padding:0;width:7px;height:4px;display:block;"><img src="include/images/cmdOrderByAsc.gif" border="0" alt="<bean:message key="jsp.ascorder" bundle="conf.Init"/>" style="cursor:hand" onclick="commandOrderBy('4', '0')" /></span>
			<span style="margin:0;margin-top:5px;padding:0;width:7px;height:4px;display:block;"><img src="include/images/cmdOrderByDesc.gif" border="0" alt="<bean:message key="jsp.descorder" bundle="conf.Init"/>" style="cursor: hand" onclick="commandOrderBy('4', '1')" /></span>
			</span>
						
					</td>
					<td width="100px">
						<bean:message key="Comm.jsp.commom.belongType" bundle="conf.comm.CommMessage"/><span style="margin:0;padding:0;width:7px;height:15px;display:block;position:absolute;right:5px;top:0px;">
			<span style="margin:0;padding:0;width:7px;height:4px;display:block;"><img src="include/images/cmdOrderByAsc.gif" border="0" alt="<bean:message key="jsp.ascorder" bundle="conf.Init"/>" style="cursor:hand" onclick="commandOrderBy('5', '0')" /></span>
			<span style="margin:0;margin-top:5px;padding:0;width:7px;height:4px;display:block;"><img src="include/images/cmdOrderByDesc.gif" border="0" alt="<bean:message key="jsp.descorder" bundle="conf.Init"/>" style="cursor: hand" onclick="commandOrderBy('5', '1')" /></span>
			</span>
						
					</td>
					<td width="100px">
						<bean:message key="jsp.inputNo" bundle="conf.Init"/><span style="margin:0;padding:0;width:7px;height:15px;display:block;position:absolute;right:5px;top:0px;">
			<span style="margin:0;padding:0;width:7px;height:4px;display:block;"><img src="include/images/cmdOrderByAsc.gif" border="0" alt="<bean:message key="jsp.ascorder" bundle="conf.Init"/>" style="cursor:hand" onclick="commandOrderBy('6', '0')" /></span>
			<span style="margin:0;margin-top:5px;padding:0;width:7px;height:4px;display:block;"><img src="include/images/cmdOrderByDesc.gif" border="0" alt="<bean:message key="jsp.descorder" bundle="conf.Init"/>" style="cursor: hand" onclick="commandOrderBy('6', '1')" /></span>
			</span>
						
					</td>			
					
					<td width="60px">
						<bean:message key="jsp.view" bundle="conf.Init" />
					</td>
					<td width="60px">
						<bean:message key="jsp.update" bundle="conf.Init" />
					</td>
					<td width="60px">
						<bean:message key="jsp.delete" bundle="conf.Init" />
					</td>
				</tr>	
				<tbody id="interval_row_id">
				<%if (commGbCoptp.getItemCodeList() != null && commGbCoptp.getItemCodeList().length > 0) {
					for (int i = 0; i < commGbCoptp.getItemCodeList().length; i++) {
				%>
				<tr class="list_neirong">
					<td class="leftPadding">
						<%=commGbCoptp.getItemCodeList()[i]%>
					</td>
					<td class="leftPadding">
						<%=commGbCoptp.getItemNameList()[i]%>
					</td>
					<td class="leftPadding">
						<%=commGbCoptp.getSeqNoList()[i]%>
					</td>
					<td class="leftPadding">
						<%=commGbCoptp.getLevelFlagList()[i]%>
					</td>
					<td class="leftPadding">
						<%=commGbCoptp.getParentItemCodeNameList()[i]%>
					</td>
					<td class="leftPadding">
						<%=commGbCoptp.getInputCodeList()[i]%>
					</td>		
					<td>
						<img onclick="cmdView('<%=commGbCoptp.getItemCodeList()[i] %>')" alt="<bean:message key="jsp.detail" bundle="conf.Init" />" src="include/images/cmdView_s.jpg" border="0" style="cursor:hand; vertical-align:middle;" />
					</td>
					<td>
						<img onclick="cmdEdit('<%=commGbCoptp.getItemCodeList()[i] %>')" alt="<bean:message key="jsp.update" bundle="conf.Init" />" src="include/images/cmdEdit_s.jpg" border="0" style="cursor:hand; vertical-align:middle;" />
					</td>
					<td>
						<img onclick="cmdDel('<%=commGbCoptp.getItemCodeList()[i] %>')" alt="<bean:message key="jsp.delete" bundle="conf.Init" />" src="include/images/cmdDel_s.jpg" border="0" style="cursor:hand; vertical-align:middle;" />
					</td>				
				</tr>
				<%}
			}

			%>
				</tbody>
				<tr>
					<td colspan="9" align="center" bgcolor="#ffffff" height="35px">
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

						<input id="_total" name="totalPage" type="hidden" value="<%=totalPage%>" />
						<bean:message key="jsp.pagetext1" bundle="conf.comm.CommMessage"/> 
						<%=curPage%>
						<bean:message key="jsp.pagetext2" bundle="conf.comm.CommMessage"/>  
						<%=totalPage%>
						<bean:message key="jsp.pagetext3" bundle="conf.comm.CommMessage"/>
						|&nbsp;
						<bean:message key="jsp.pagetext4" bundle="conf.comm.CommMessage"/>
						<%=totalNum%>
						<bean:message key="jsp.pagetext5" bundle="conf.comm.CommMessage"/>
						&nbsp;|&nbsp;
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
						&nbsp;|&nbsp;<bean:message key="jsp.pagetext6" bundle="conf.comm.CommMessage" />
						<input id="_tp" name="page" type="text" value="<%=curPage%>" size="2" class="txt" />
						<bean:message key="jsp.pagetext7" bundle="conf.comm.CommMessage" />
						&nbsp;&nbsp;
						
						<img style="cursor:hand;" src="include/images/go.jpg" width="18" border="0" onclick="goPage2()" />

					</td>
				</tr>

			</table>

		</form>
		<script language="javascript" src="include/javascript/interval_row_color.js"></script>
	</body>
</html>
