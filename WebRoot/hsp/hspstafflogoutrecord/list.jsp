<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="html" uri="/WEB-INF/struts-html.tld"%>
<%@ taglib prefix="bean" uri="/WEB-INF/struts-bean.tld"%>
<%@ taglib prefix="logic" uri="/WEB-INF/struts-logic.tld"%>
<%@page import="com.tianjian.util.comm.PageBean"%>
<jsp:useBean id="pb" scope="request" class="com.tianjian.util.comm.PageBean" />
<jsp:useBean id="dataForm" scope="request" class="com.tianjian.hsp.struts.form.HspStaffBaseinfoForm" />

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
	<%if(request.getServerPort() == 80) {%>
			<base href="http://<%=request.getServerName()%><%=request.getContextPath()%>/"/>
	<%} else {%>
			<base href="http://<%=request.getServerName()%>:<%=request.getServerPort()%><%=request.getContextPath()%>/"/>
	<%}%>
	<meta http-equiv="pragma" content="no-cache"/>
	<meta http-equiv="cache-control" content="no-cache"/>
	<meta http-equiv="expires" content="0"/>
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3"/>
	<meta http-equiv="description" content="This is my page"/>
	<link rel="stylesheet" href="<%=request.getContextPath()%>/hsp/include/css/open.css" />
	<link type="text/css" rev="stylesheet" rel="stylesheet" href="include/css/form.css" />
	<script language="javascript" src="<%=request.getContextPath()%><bean:message  key="hsp.js.commCommMessage.path"  bundle="hsp.hspLocale"/>"></script>
	<script language="javascript" src="<%=request.getContextPath()%><bean:message  bundle="hsp.hspLocale" key="hsp.js.gettext_staff.path"/>"></script>	
	<script type="text/javascript" src="<%=request.getContextPath()%>/include/javascript/jianbian.js" defer="defer"></script>
	<script src="<%=request.getContextPath()%>/include/javascript/searchsuggest.js"></script>
	<link rel="stylesheet" href="<%=request.getContextPath()%>/include/css/searchsuggest.css" />
	<script src="<%=request.getContextPath()%>/include/javascript/searchsuggest.js"></script>
	<link rel="stylesheet" href="<%=request.getContextPath()%>/include/css/searchsuggest.css" />
		<script language="javascript">
			//添加
			function cmdAdd() {
			    document.form.verbId.value = "addInit";    
			    document.form.submit();
			}
			function queryForm(num) {
			
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
			
			function goPage2() {			  
			  	var _tp=document.getElementById('_tp');
  var _total=document.getElementById('_total'); 
			    	if (!isMadeOf(_tp.value,'1234567890')) {
			      		alert("<bean:message  bundle="hsp.hspLocale" key="hsp.jsp.common.wrongInteger"/>!");
			      		return;
			    	}
			    	if (_tp.value<=0){
			    		alert("<bean:message  bundle="hsp.hspLocale" key="hsp.jsp.common.pageMustBigThanZero"/>!");
						return;
			    	}
			    	if(parseInt(_tp.value)>parseInt(_total.value)){
			      		alert("<bean:message  bundle="hsp.hspLocale" key="hsp.jsp.common.cannotBigThanTotal"/>!");
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
//查看详细时候用
			function viewForm(id){
				var array = id;
				var idHidden = id;
				var len = 0;
				if(id!=null && id!=""){ 
							len ++; 
				}
				if(len == 1){
					document.form.id.value = idHidden;
					document.form.verbId.value = "detail";
					document.form.submit();
				}else if(len <= 0){
					alert("<bean:message  bundle="hsp.hspLocale" key="hsp.jsp.common.warn4"/>!");
					return false;
				}else if(len > 1){
					alert("<bean:message  bundle="hsp.hspLocale" key="hsp.jsp.common.warn6"/>!");
					return false;
				}
			}	
			
//更新准备时候用
			function updateInitForm(id){
				var array = id;
				var idHidden = id;
				var len = 0;
				if(id!=null && id!=""){ 
							len ++; 
				}
				if(len == 1){
					document.form.id.value = idHidden;
					document.form.verbId.value = "updateInit";
					document.form.submit();
				}else if(len <= 0){
					alert("<bean:message  bundle="hsp.hspLocale" key="hsp.jsp.common.warn4"/>!");
					return false;
				}else if(len > 1){
					alert("<bean:message  bundle="hsp.hspLocale" key="hsp.jsp.common.warn6"/>!");
					return false;
				}
			}			
//删除时候用
			function deleteForm(id){
				var array = id;
				var len = 0;
				if(id!=null && id!=""){
					 
							len ++;
						 
				}
				if(len > 0){
					 if (!confirm("<bean:message  bundle="hsp.hspLocale" key="hsp.jsp.common.warn17"/>")) return false; 
					 document.form.id.value = id;
					 document.form.verbId.value = "delete";
					 document.form.submit();
				}else{
					alert("<bean:message  bundle="hsp.hspLocale" key="hsp.jsp.common.warn4"/>!");
					return false;
				}
			}
			
			//注销
			function cmdCanceled(id) {  /**
			    document.form.idHidden.value = id;  
			    document.form.verbId.value = "addLogOutInit";    
			    document.form.submit();   */
			    var array = id;
				var idHidden = id;
				var len = 0;
				if(id!=null && id!=""){ 
							len ++; 
				}
				if(len == 1){
					document.form.id.value = idHidden;
					document.form.verbId.value = "addLogOutInit";
					document.form.submit();
				}else if(len <= 0){
					alert("<bean:message  bundle="hsp.hspLocale" key="hsp.jsp.common.warn4"/>!");
					return false;
				}else if(len > 1){
					alert("<bean:message  bundle="hsp.hspLocale" key="hsp.jsp.common.warn6"/>!");
					return false;
				}
			}
			
			function selectAll(){
				var checkbox = document.getElementsByName('checkbx');
				if(form.checkB.checked){
					for(i=0; i<checkbox.length; i++){
						checkbox[i].checked = true;
					}
				}else{
					for(i=0; i<checkbox.length; i++){
						checkbox[i].checked = false;
					}
				}
			}	
		
	  function huiche(){
		if(event.keyCode==13){
			event.keyCode=9
		}
		
	} 
	       function huiche(){
				if(event.keyCode==13){
					event.keyCode=9;
				}			
			}
		</script>
		<link href="<%=request.getContextPath()%>/hsp/include/css/frame_add.css" rel="stylesheet" type="text/css" />
 		<script type="text/javascript" src="<%=request.getContextPath()%>/include/javascript/searchwindow.js"></script>	
		<script type="text/javascript" src="<%=request.getContextPath()%>/hsp/include/javascript/jianbian.js"></script>
		<link rel="stylesheet" href="<%=request.getContextPath()%>/include/css/searchwindow.css" />
  </head>  
  <body onload="showCommMessage('','${message }','1')">
  	<form name="form" action="<%=request.getContextPath()%>/hsp/hspStaffLogoutRecord.do">
		<input type="hidden" name="verbId"/>
		<input type="hidden" name="orderNo" value="${dataForm.orderNo}"/>
		<input type="hidden" name="asc" value="${dataForm.asc}"/>
		<input type="hidden" name="idHidden"/>
		<input type="hidden" name="id"/>
<!--查询条件-->
<table border="0" cellpadding="0" cellspacing="0" class="tblSearch">	
	<tr>
		<td class="tblTitle" colspan="4">
			<bean:message  bundle="hsp.hspLocale" key="hsp.jsp.common.selectCondition"/>
		</td>
	</tr>				
	<tr>
		<td >
				
					<bean:message  bundle="hsp.hspLocale" key="hsp.jsp.common.userId"/>：<input name="empNo" id="empNo" value="${dataForm.empNo}"  type="text" onkeydown="huiche()" />		
					<bean:message  bundle="hsp.hspLocale" key="hsp.jsp.common.name1"/>：<input name="name" id="name" value="${dataForm.name}"  type="text" onkeyup="GiveOptions(event, '/searchSuggest.do', 'getUserName_0000000001')" onkeydown="huanhang(event)"" />
					<span id="spanOutput" class="spanTextDropdown" style="display: none;"></span>
					<bean:message  bundle="hsp.hspLocale" key="hsp.jsp.common.idNo"/>：<input name="idNo" id="idNo" value="${dataForm.idNo}" " type="text"  onkeydown="if(event.keyCode==13){event.keyCode=9;document.getElementById('queryImg').className='queryImg_down'}" />
					
					<input type="checkbox" name="canceledFlag" value="1" <%="1".equals(request.getAttribute("canceledFlag"))?"checked":""%>/>
					<bean:message  bundle="comm.comIHE" key="comm.jsp.segment8"/>
					<input type="button" class="btnSave" id="queryImg" name="<bean:message  bundle="hsp.hspLocale" key="hsp.jsp.common.search2"/>" value="<bean:message  bundle="hsp.hspLocale" key="hsp.jsp.common.search1"/>" onclick="queryForm(0);" />
						
			</td>
	</tr>
	</table>
			<!--列表标题-->
			<div id="dvh1">
			<table border="0" cellpadding="0" cellspacing="0" class="tblSearchList">
			<!--
				<caption style="text-align:left;">
					<img src="<%=request.getContextPath()%>/comm/include/images/comm_list_nav_red.jpg" width="20" height="20" align="absmiddle" /> <span style="font-weight:bold; color:#333;"><bean:message key="security.jsp.commom.item1" bundle="security"/></span>
					<span class="titleBtn"><img  src="<bean:message key="security.jsp.securityconfigpublicclass.add" bundle="conf.security.security"/>"  align="absmiddle" />
						<a href="javascript:cmdAdd()" target="_self"><bean:message  bundle="comm.commLocale" key="comm.jsp.commom.add"/></a></span>
			    </caption>  -->
			    <thead>
			    	<tr class="lstName">
			            <th width="10%" height="26"><bean:message  bundle="hsp.hspLocale" key="hsp.jsp.common.userId"/>
			            <div><img src="include/images/cmdOrderByAsc.gif" alt="Ascender" vspace="7" onclick="commandOrderBy('1', '0')" /><img border="0" onclick="commandOrderBy('1', '1')" alt="Descender" src="include/images/cmdOrderByDesc.gif" /></div></th>
			            <th width="4%" height="26"><bean:message  bundle="hsp.hspLocale" key="hsp.jsp.common.name1"/>
			            <div><img src="include/images/cmdOrderByAsc.gif" alt="Ascender" vspace="7" onclick="commandOrderBy('2', '0')" /><img border="0" onclick="commandOrderBy('2', '1')" alt="Descender" src="include/images/cmdOrderByDesc.gif" /></div></th>
			            <th width="5%" height="26"><bean:message  bundle="hsp.hspLocale" key="hsp.jsp.hspstaffbaseinfolist.sex"/>
			            <div><img src="include/images/cmdOrderByAsc.gif" alt="Ascender" vspace="7" onclick="commandOrderBy('3', '0')" /><img border="0" onclick="commandOrderBy('3', '1')" alt="Descender" src="include/images/cmdOrderByDesc.gif" /></div></th>
			            <th width="15%" height="26"><bean:message  bundle="hsp.hspLocale" key="hsp.jsp.common.itemName"/>
			            <div><img src="include/images/cmdOrderByAsc.gif" alt="Ascender" vspace="7" onclick="commandOrderBy('4', '0')" /><img border="0" onclick="commandOrderBy('4', '1')" alt="Descender" src="include/images/cmdOrderByDesc.gif" /></div></th>
			            <th width="7%" height="26">		<bean:message  bundle="hsp.hspLocale" key="hsp.jsp.common.idNo"/>
			            <div><img src="include/images/cmdOrderByAsc.gif" alt="Ascender" vspace="7" onclick="commandOrderBy('5', '0')" /><img border="0" onclick="commandOrderBy('5', '1')" alt="Descender" src="include/images/cmdOrderByDesc.gif" /></div></th>
			             <th width="3%" height="26"><bean:message  bundle="comm.commLocale" key="comm.jsp.commom.search"/></th>
			            <% String flag = (String)request.getAttribute("canceledFlag");
						   if(flag!=null&&flag.equals("1")){}
						   else{
						%>
			            <th width="3%" height="26"><bean:message  bundle="comm.commLocale" key="comm.jsp.commom.canceled"/></th>
			            <%} %>
			         </tr>
			    </thead>
				<tbody id="interval_row_id">
		<% if (dataForm.getIdsHiddenArray() != null && dataForm.getIdsHiddenArray().length > 0) {
			for (int i = 0; i < dataForm.getIdsHiddenArray().length; i++) {
		%>
		<tr>	
			<td class="leftPadding td_shenglue" style="text-align:left;padding-left:10px;"><%=dataForm.getEmpNoArray()[i]%></td>			
			<td class="leftPadding td_shenglue" style="text-align:left;padding-left:10px;"><%=dataForm.getNameArray()[i]%></td>
			<td class="leftPadding td_shenglue" style="text-align:left;padding-left:10px;"><%=dataForm.getSexArray()[i]%></td>
			<td class="leftPadding td_shenglue" style="text-align:left;padding-left:10px;"><%=dataForm.getHspNameArray()[i]%></td>
			<td class="leftPadding td_shenglue"><%=dataForm.getIdNoArray()[i]%></td>
			<td class="td_shenglue">
              <img
				src="<%=request.getContextPath()%>/hsp/include/images/cmdView_s.jpg"
				style="cursor:hand; vertical-align:middle;" title="<bean:message  bundle="hsp.hspLocale" key="hsp.jsp.common.select"/>" onclick="viewForm('<%=dataForm.getIdsHiddenArray()[i]%>')" />
			</td>
			<% if(flag!=null&&flag.equals("1")){}
						   else{
						%>
						<td>
							<img onClick="cmdCanceled('<%=dataForm.getIdsHiddenArray()[i]%>')" alt="<bean:message  bundle="comm.commLocale" key="comm.jsp.commom.canceled"/>" src="include/images/cmdDel_s.jpg" border="0" style="cursor: hand; vertical-align: middle;" />
						</td>
						<%} %>
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
                         <input type="hidden" id="pageId" name="pageId" value="page_282881f339b4e9c20139b4e9c23a0000" />
						<input type="hidden" id="reHref" name="reHref" value="<%=request.getContextPath()%>/hsp/hspStaffBaseinfo.do?verbId=queryDetail" />
						<%@ include file="/include/changepagesize.jsp" %>
						<input id="_total" name="totalPage" type="hidden"
							value="<%=totalPage%>" />
						<bean:message  bundle="hsp.hspLocale" key="hsp.jsp.common.D"/>
						<%=curPage%>
						<bean:message  bundle="hsp.hspLocale" key="hsp.jsp.common.pageTotal1"/>
						<%=totalPage%>
						<bean:message  bundle="hsp.hspLocale" key="hsp.jsp.common.pageTotal"/>
						<%=totalNum%>
						<bean:message  bundle="hsp.hspLocale" key="hsp.jsp.common.record"/>
						<%
						if (curPage > 1) {
						%>
						<a href="javascript:goPage('0')"><img
								src="include/images/shouye.gif" align="middle" border="0" /> </a>&nbsp;
						<a href="javascript:goPage('<%=curPage - 1%>')"><img
								src="include/images/shang.gif" align="middle" border="0" /> </a>&nbsp;
						<%
								} else {
								out.println("<img src='include/images/shouye_s.gif' align='middle'  border='0px' />&nbsp;&nbsp;<img src='include/images/shang_s.gif' align='middle'  border='0px' />&nbsp;");
							}
							if (curPage < totalPage) {
						%>
						<a href="javascript:goPage('<%=curPage + 1%>')"><img
								src="include/images/xia.gif" align="middle" border="0" /> </a>&nbsp;
						<a href="javascript:goPage('<%=totalPage%>')"><img
								src="include/images/mo.gif" align="middle" border="0" /> </a>&nbsp;
						<%
								} else {
								out.println("<img src='include/images/xia_x.gif' align='middle' border='0px' />&nbsp;&nbsp;<img src='include/images/mo_m.gif' align='middle'  border='0px' />&nbsp;");
							}
						%>
						<bean:message  bundle="hsp.hspLocale" key="hsp.jsp.common.text1"/>
						<input id="_tp" name="page" type="text" value="<%=curPage%>"
							size="2" class="txt" />
						<bean:message  bundle="hsp.hspLocale" key="hsp.jsp.common.page"/>&nbsp;
						<img style="cursor: hand;" src="include/images/go.jpg" width="18"
							border="0" onclick="goPage2()" />
					</td>
				</tr><!--  
				<tr>
					<td colspan="7" align="center" bgcolor="#ffffff" height="35px">
						<input type="button" id="exportImg" name="<bean:message  bundle="hsp.hspLocale" key="hsp.jsp.common.import1"/>" value="<bean:message  bundle="hsp.hspLocale" key="hsp.jsp.common.import"/>" onclick="cmdExcelImport()" style="cursor: hand; background: url(include/images/tong.gif) no-repeat; border: 0; width: 50px; height: 23px; font-size: 12px;" />
			 			<input type="button" id="exportImg" name="<bean:message  bundle="hsp.hspLocale" key="hsp.jsp.common.export1"/>" value="<bean:message  bundle="hsp.hspLocale" key="hsp.jsp.common.export"/>" onclick="cmdExcelExport()" style="cursor: hand; background: url(include/images/tong.gif) no-repeat; border: 0; width: 50px; height: 23px; font-size: 12px;" />
					</td>
				</tr>-->	
	</table>
	</form>
	<script language="javascript" src="<%=request.getContextPath()%>/include/javascript/interval_row_color.js"></script>
	</body>
</html>	