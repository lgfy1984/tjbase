<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@page import="com.tianjian.util.comm.PageBean"%>
<jsp:useBean id="data" scope="request" class="com.tianjian.security.struts.form.SecurityDataObjectTypeForm"></jsp:useBean>
<jsp:useBean id="pb" scope="request" class="com.tianjian.util.comm.PageBean" />
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<%
			if (request.getServerPort() == 80) {
		%>
		<base
			href="http://<%=request.getServerName()%><%=request.getContextPath()%>/">
		<%
			} else {
		%>
		<base
			href="http://<%=request.getServerName()%>:<%=request.getServerPort()%><%=request.getContextPath()%>/">
		<%
			}
		%>

		<title><bean:message
				key="security.jsp.securityDataObject.list.title" bundle="security" />
		</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<script language="javascript" src="<bean:message key="comm.js.CommMessage.path" bundle="security" />"></script>
		<script language="javascript" src="include/javascript/eventOnKeyPress.js"></script>
		<link type="text/css" rev="stylesheet" rel="stylesheet" href="include/css/form.css" />
	</head>
	<script type="text/javascript">
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
	function goPage(page) {  
   document.form.page.value = page;
   document.form.verbId.value = "query";    
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
    if (!confirm(getCommMessage("<bean:message key="security.jsp.commom.warn14" bundle="security"/>?","",""))) return false;    
    document.form.id.value = id;  
    document.form.verbId.value = "delete";    
    document.form.submit();    
}

function goPage2() {
  var _tp=document.getElementById('_tp');
  var _total=document.getElementById('_total'); 
    if (!isMadeOf(_tp.value,'1234567890')) {
      alert("<bean:message key="security.jsp.commom.warn" bundle="security"/>!");
      return;
    }
    if (_tp.value<=0){
    	alert("<bean:message key="security.jsp.commom.warn1" bundle="security"/>!");
		return;
    }
    if(parseInt(_tp.value)>parseInt(_total.value)){
      alert("<bean:message key="security.jsp.commom.warn2" bundle="security"/>!");
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
  		function cmdAdd(){
  			window.location.href = "<%=request.getContextPath()%>/securityData.do?verbId=addinit";
  		}
  	</script>
	<body>
		<form name="form" method="post" action="<%=basePath%>securityData.do">
			<input type="hidden" name="verbId" value="<%=data.getVerbId()%>" />
			<input type="hidden" name="orderNo" value="<%=data.getOrderNo()%>" />
			<input type="hidden" name="asc" value="<%=data.getAsc()%>" />
			<input type="hidden" name="itemCodeHidden" value="<%=data.getItemCodeHidden()%>" />
			<!--查询条件-->
			<table border="0" cellpadding="0" cellspacing="0" class="tblSearch" align="center">
				<tr>
					<td class="tblTitle" colspan="4">
						<bean:message key="security.jsp.securityDataObject.list.item" bundle="security" />
					</td>
				</tr>
				<tr>
					<td>
						<bean:message key="security.jsp.securityDataObject.list.ID" bundle="security" />：
						<input name="id" type="text"  onkeypress="eventOnKeyPress('dataObjectTypeName')" value="<%=data.getId()%>" />

						<bean:message key="security.jsp.securityDataObject.list.dataObjectTypeName" bundle="security" />：
						<input name="dataObjectTypeName" type="text" onkeypress="eventOnKeyPress('comulnName')" value="<%=data.getDataObjectTypeName()%>" />

						<bean:message key="security.jsp.securityDataObject.list.comulnName" bundle="security" />：
						<input name="comulnName" type="text" onkeypress="eventOnKeyPress('button')" value="<%=data.getComulnName()%>" />

						<input type="button" name="button" class="btnSave" value="<bean:message key="security.jsp.commom.button10" bundle="security"/>" onClick="submitQueryForm();" />
					</td>
				</tr>
			</table>
			<!--列表标题-->
			<div id="dvh1">
			<table  border="0" cellpadding="0" cellspacing="0" class="tblSearchList" align="center">
				<caption style="text-align:left;">
					<img src="<%=request.getContextPath()%>/comm/include/images/comm_list_nav_red.jpg" width="20" height="20" /> <span style="font-weight:bold; color:#333;"><bean:message key="security.jsp.commom.item1" bundle="security"/></span>
					<span class="titleBtn"><img  src="<bean:message key="security.jsp.securityconfigpublicclass.add" bundle="conf.security.security"/>"  align="absmiddle" />
						<a href="javascript:cmdAdd()" target="_self"><bean:message key="security.jsp.commom.button3" bundle="security"/></a></span>
			    </caption>
				
				<thead>
			    	<tr class="lstName">
			            <th width="5%" height="26"><bean:message key="security.jsp.securityDataObject.list.ID"	bundle="security" /><div><img src="include/images/cmdOrderByAsc.gif" alt="Ascender" vspace="7" onclick="commandOrderBy('2', '0')" /><img border="0" onclick="commandOrderBy('1', '1')" alt="Descender" src="include/images/cmdOrderByDesc.gif" /></div></th>
			            <th width="15%" height="26"><bean:message key="security.jsp.securityDataObject.list.dataObjectTypeName" bundle="security" /><div><img src="include/images/cmdOrderByAsc.gif" alt="Ascender" vspace="7" onclick="commandOrderBy('2', '0')" /><img border="0" onclick="commandOrderBy('2', '1')" alt="Descender" src="include/images/cmdOrderByDesc.gif" /></div></th>
			            <th width="5%" height="26"><bean:message key="security.jsp.securityDataObject.list.comulnName" bundle="security" /><div><img src="include/images/cmdOrderByAsc.gif" alt="Ascender" vspace="7" onclick="commandOrderBy('3', '0')" /><img border="0" onclick="commandOrderBy('3', '1')" alt="Descender" src="include/images/cmdOrderByDesc.gif" /></div></th>
			            <th width="3%" height="26"><bean:message key="security.jsp.commom.button6" bundle="security" /></th>
			            <th width="3%" height="26"><bean:message key="security.jsp.commom.button7" bundle="security" /></th>
			            <th width="3%" height="26"><bean:message key="security.jsp.commom.button8" bundle="security" /></th>
			        </tr>
				</thead>
				<tbody id="interval_row_id">
					<%
						if (data.getIds() != null && data.getIds().length > 0) {
							for (int i = 0; i < data.getIds().length; i++) {
					%>
					<tr>
						<td><%=data.getIds()[i]%></td>
						<td style="text-align:left;padding-left:10px;"><%=data.getDataObjectTypeNames()[i]%></td>
						<td style="text-align:left;padding-left:10px;"><%=data.getComulnNames()[i]%></td>
						<td>
							<img onClick="cmdView('<%=data.getIds()[i]%>')"
								alt="<bean:message key="security.jsp.commom.button9" bundle="security"/>"
								src="include/images/cmdView_s.jpg" border="0"
								style="cursor: hand; vertical-align: middle;" />
						</td>
						<td>
							<img onClick="cmdEdit('<%=data.getIds()[i]%>')"
								alt="<bean:message key="security.jsp.commom.button7" bundle="security"/>"
								src="include/images/cmdEdit_s.jpg" border="0"
								style="cursor: hand; vertical-align: middle;" />
						</td>
						<td>
							<img onClick="cmdDel('<%=data.getIds()[i]%>')"
								alt="<bean:message key="security.jsp.commom.button8" bundle="security"/>"
								src="include/images/cmdDel_s.jpg" border="0"
								style="cursor: hand; vertical-align: middle;" />
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
						<input type="hidden" id="pageId" name="pageId" value="page_2828810b39b36c340139b36c347f0000" />
						<input type="hidden" id="reHref" name="reHref" value="<%=request.getContextPath()%>/securityData.do?verbId=query" />
						<%@ include file="/include/changepagesize.jsp" %>
						
						<input id="_total" name="totalPage" type="hidden"
							value="<%=totalPage%>" />
						<bean:message key="security.jsp.commom.item2" bundle="security" />
						<%=curPage%>
						<bean:message key="security.jsp.commom.item3" bundle="security" />
						<%=totalPage%>
						<bean:message key="security.jsp.commom.item4" bundle="security" />
						<%=totalNum%>
						<bean:message key="security.jsp.commom.item5" bundle="security" />
						&nbsp;|&nbsp;
						<%
							if (curPage > 1) {
						%>
						<a href="javascript:goPage('0')"><img
								src="include/images/shouye.gif" align="middle" border="0" />
						</a>&nbsp;&nbsp;
						<a href="javascript:goPage('<%=curPage - 1%>')"><img
								src="include/images/shang.gif" align="middle" border="0" />
						</a>&nbsp;
						<%
							} else {
								out
										.println("<img src='include/images/shouye_s.gif' align='middle'  border='0' />&nbsp;&nbsp;<img src='include/images/shang_s.gif' align='middle'  border='0' />&nbsp;");
							}
							if (curPage < totalPage) {
						%>
						<a href="javascript:goPage('<%=curPage + 1%>')"><img
								src="include/images/xia.gif" align="middle" border="0" />
						</a>&nbsp;&nbsp;
						<a href="javascript:goPage('<%=totalPage%>')"><img
								src="include/images/mo.gif" align="middle" border="0" />
						</a>
						<%
							} else {
								out.println("<img src='include/images/xia_x.gif' align='middle' border='0' />&nbsp;&nbsp;<img src='include/images/mo_m.gif' align='middle'  border='0' />");
							}
						%>
						| &nbsp;
						<bean:message key="security.jsp.commom.item6" bundle="security" />
						<input id="_tp" name="page" type="text" value="<%=curPage%>"
							size="2" class="txt" />
						<bean:message key="security.jsp.commom.item7" bundle="security" />
						&nbsp;
						<img style="cursor: hand;" src="include/images/go.jpg" width="18"
							border="0" onClick="goPage2()" />

					</td>
				</tr>

			</table>
		</form>
	</body>
	<script language="javascript"
		src="<%=basePath%>include/javascript/interval_row_color.js"></script>
</html>
