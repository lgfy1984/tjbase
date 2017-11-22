<%@page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="html" uri="/WEB-INF/struts-html.tld"%>
<%@ taglib prefix="bean" uri="/WEB-INF/struts-bean.tld"%>
<%@ taglib prefix="logic" uri="/WEB-INF/struts-logic.tld"%>
<%@page import="com.tianjian.util.comm.PageBean"%>
<jsp:useBean id="commDictPublicLevel" scope="request" type="com.tianjian.comm.struts.form.CommDictPublicLevelForm" />
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
		<title><bean:message key="Comm.jsp.commom.genearlLevelDictInfoMaintain" bundle="conf.comm.CommMessage"/></title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<script language="javascript" src="<bean:message  key="Comm.js.TJMessagepath"  bundle="conf.comm.CommMessage"/>"></script>
		<script language="javascript" src="include/javascript/eventOnKeyPress.js"></script>
		<script language="javascript" src="<bean:message key="security.js.gettext_staff.path" bundle="security" />"></script>
		<link rel="stylesheet" href="<%=request.getContextPath()%>/hsp/include/css/open.css" />
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
	if (confirmMessage("<bean:message key='comm.jsp.add.shanchu' bundle='comm.commLocale'/>")){     
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
    if(!isMadeOf(_tp.value,'1234567890')) {
      alert('<bean:message key="js.pagewarn1" bundle="conf.Init" />');
      return;
    }
    if(_tp.value<=0){
    	alert('<bean:message key="js.pagewarn2" bundle="conf.Init" />');
		return;
    }
    if(parseInt(_tp.value)>parseInt(_total.value)){
      alert('<bean:message key="js.pagewarn3" bundle="conf.Init" />');
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
	function showHspMessage(message){
		if(message != ''&& message != null){
			alert(message);
			return;
		}
	}
</script>
		<link type="text/css" rev="stylesheet" rel="stylesheet" href="include/css/form.css" />
	</head>
	<body onload="showHspMessage('<%=commDictPublicLevel.getMessage()%>')">
		<!-- Form中的action项的值必须设置-->
		<form name="form" method="post" action="comm/commDictPublicLevel.do">
			<!-- Head line -->
			<input type="hidden" name="verbId" value="<%=commDictPublicLevel.getVerbId()%>">
			<input type="hidden" name="orderNo" value="<%=commDictPublicLevel.getOrderNo()%>">
			<input type="hidden" name="asc" value="<%=commDictPublicLevel.getAsc()%>">
			<input type="hidden" name="idHidden" value="<%=commDictPublicLevel.getIdHidden()%>">
			<!--查询条件-->
			<table  border="0" cellpadding="0" cellspacing="0" class="tblSearch" align="center">
				<tr>
					<td class="tblTitle" colspan="4">
						<bean:message key="Comm.jsp.commom.GeneralDict" bundle="conf.comm.CommMessage" />
					</td>
				</tr>
				<tr>
					<td>
						<bean:message key="Comm.jsp.commom.dictType" bundle="conf.comm.CommMessage"/>:
						<input type="text" class="input" id="displayInputId_1"
							name="tableName" style="font-size:12px; width:180px"
							onkeydown="huiche()" value="<%=commDictPublicLevel.getTableCode()%>"
							readonly="readonly" onkeypress="eventOnKeyPress('itemName')" />
						<input type="hidden" id="hiddenInputId_1"
							value="<%=commDictPublicLevel.getTableName()%>" name="tableCode" />
						<img src="security/include/images/select.gif"
							style="cursor: pointer;"
							onclick="add('<%=request.getContextPath()%>/comm/commDictPublicChar.do?verbId=getClass','displayInputId_1','hiddenInputId_1')" />

						<bean:message key="Comm.jsp.commom.ParentItem" bundle="conf.comm.CommMessage"/>:
						<select name="parentItemCode" id="parentItemCode" style="width: 80px;" onkeypress="eventOnKeyPress('itemName')">
							<%
									if (commDictPublicLevel.getParentItemCodes() != null && commDictPublicLevel.getParentItemCodes().length > 0) {
									for (int i = 0; i < commDictPublicLevel.getParentItemCodes().length; i++) {
										String tempId = commDictPublicLevel.getParentItemCodes()[i];
										String tempName = commDictPublicLevel.getParentItemNames()[i];
							%>
							<option value="<%=tempId%>" <%=tempId.equals(commDictPublicLevel.getParentItemCode()) ? "selected" : ""%>>
								<%=tempName%>
							</option>
							<%
								}
								}
							%>
						</select>

						<bean:message key="jsp.dictValue" bundle="conf.Init"/>:
						<input name="itemName" type="text" onkeypress="eventOnKeyPress('inputCode')" value="<%=commDictPublicLevel.getItemName()%>">

						<bean:message key="jsp.inputNo" bundle="conf.Init"/>:
						<input name="inputCode" type="text" onkeypress="eventOnKeyPress('<bean:message key="jsp.btnsubmit" bundle="conf.Init"/>')" value="<%=commDictPublicLevel.getInputCode()%>">

						<input type="button" class="btnSave" name="<bean:message key="jsp.btnsubmit" bundle="conf.Init"/>" value="<bean:message key="jsp.btnsubmitSpace" bundle="conf.Init"/>" onClick="submitQueryForm();" />
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
			            <th width="5%" height="26"><bean:message key="Comm.jsp.commom.itemLevel" bundle="conf.comm.CommMessage"/><div><img src="include/images/cmdOrderByAsc.gif" alt="Ascender" vspace="7" onclick="commandOrderBy('1', '0')" /><img border="0" onclick="commandOrderBy('1', '1')" alt="Descender" src="include/images/cmdOrderByDesc.gif" /></div></th>
			            <th width="15%" height="26"><bean:message key="jsp.dictValue" bundle="conf.Init"/><div><img src="include/images/cmdOrderByAsc.gif" alt="Ascender" vspace="7" onclick="commandOrderBy('2', '0')" /><img border="0" onclick="commandOrderBy('2', '1')" alt="Descender" src="include/images/cmdOrderByDesc.gif" /></div></th>
			            <th width="5%" height="26"><bean:message key="jsp.seqNo" bundle="conf.Init"/><div><img src="include/images/cmdOrderByAsc.gif" alt="Ascender" vspace="7" onclick="commandOrderBy('3', '0')" /><img border="0" onclick="commandOrderBy('3', '1')" alt="Descender" src="include/images/cmdOrderByDesc.gif" /></div></th>
			            <th width="5%" height="26"><bean:message key="Comm.jsp.commom.parentItemName" bundle="conf.comm.CommMessage"/><div><img src="include/images/cmdOrderByAsc.gif" alt="Ascender" vspace="7" onclick="commandOrderBy('4', '0')" /><img border="0" onclick="commandOrderBy('4', '1')" alt="Descender" src="include/images/cmdOrderByDesc.gif" /></div></th>
			            <th width="10%" height="26"><bean:message key="Comm.jsp.commom.dictType" bundle="conf.comm.CommMessage"/><div><img src="include/images/cmdOrderByAsc.gif" alt="Ascender" vspace="7" onclick="commandOrderBy('5', '0')" /><img border="0" onclick="commandOrderBy('5', '1')" alt="Descender" src="include/images/cmdOrderByDesc.gif" /></div></th>
			            <th width="3%" height="26"><bean:message key="jsp.view" bundle="conf.Init" /></th>
			        </tr>
				</thead>
				<tbody id="interval_row_id">
					<%
							if (commDictPublicLevel.getItemCodeList() != null && commDictPublicLevel.getItemCodeList().length > 0) {
							for (int i = 0; i < commDictPublicLevel.getItemCodeList().length; i++) {
					%>
					<tr>
						<td style="text-align:left;padding-left:10px;">
							<%=commDictPublicLevel.getClassLevelList()[i]%>
						</td>
						<td style="text-align:left;padding-left:10px;">
							<%=commDictPublicLevel.getItemNameList()[i]%>
						</td>
						<td style="text-align:left;padding-left:10px;">
							<%=commDictPublicLevel.getSeqInLevelList()[i]%>
						</td>
						<td style="text-align:left;padding-left:10px;">
							<%=commDictPublicLevel.getParentItemNameList()[i]%>
						</td>
						<td style="text-align:left;padding-left:10px;">
							<%=commDictPublicLevel.getTableNameList()[i]%>
						</td>
						<td> 
							<img onClick="cmdView('<%=commDictPublicLevel.getItemCodeList()[i]%>')" alt="<bean:message key="jsp.detail" bundle="conf.Init" />" src="include/images/cmdView_s.jpg" border="0" style="cursor: hand; vertical-align: middle;" />
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
						<input type="hidden" id="pageId" name="pageId" value="page_282881f534636f140134636f14d50000" />
						<input type="hidden" id="reHref" name="reHref" value="<%=request.getContextPath()%>/comm/commDictPublicLevel.do?verbId=queryDetail" />
						<%@ include file="/include/changepagesize.jsp" %>
							
						<input id="_total" name="totalPage" type="hidden" value="<%=totalPage%>">
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
						<input id="_tp" name="page" type="text" value="<%=curPage%>" size="2" class="txt">
						<bean:message key="jsp.pagetext4" bundle="conf.Init"/>
						&nbsp;
						<img style="cursor:hand;" src="include/images/go.jpg" width="18" border="0" onClick="goPage2()" />
						%>
					</td>
				</tr>
			</table>
		</form>
		<script language="javascript" src="include/javascript/interval_row_color.js"></script>
	</body>
</html>
