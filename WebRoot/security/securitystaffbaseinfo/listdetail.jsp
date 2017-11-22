<%@page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ page import="com.tianjian.util.comm.TJInit" %>
<jsp:useBean id="securityStaffBaseinfo" 
	scope="request" type="com.tianjian.security.struts.form.SecurityStaffBaseinfoForm" />
<jsp:useBean id="pb" scope="request" class="com.tianjian.util.comm.PageBean" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%
		if (request.getServerPort() == 80) {
		%>
		<base href="http://<%=request.getServerName()%><%=request.getContextPath()%>/" />
		<%
		} else {
		%>
		<base href="http://<%=request.getServerName()%>:<%=request.getServerPort()%><%=request.getContextPath()%>/" />
		<%
		}
		%>
		<title><bean:message key="security.jsp.securitystaffbaseinfo.list.title" bundle="security"/></title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<script language="javascript" src="<bean:message key="include.js.TJMessage.path" bundle="security" />"></script>
		<script type="text/javascript"src="security/include/javascript/jianbian.js"></script>
		<link type="text/css" rev="stylesheet" rel="stylesheet" href="include/css/form.css" />
		<script src="<%=request.getContextPath()%>/include/javascript/searchsuggest.js"></script>
		<link rel="stylesheet" href="<%=request.getContextPath()%>/include/css/searchsuggest.css" />
 		<script type="text/javascript" src="<%=request.getContextPath()%>/include/javascript/searchwindow.js"></script>	
		<script language="javascript">
		function huiche(){
				if(event.keyCode==13){
						event.keyCode=9
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

		
	function cmdAdd(){
		addInitForm();
	}		
	//新增初始化工作，转到新增页面
	function addInitForm(){
		document.form.verbId.value = "addInit";
		document.form.submit();
	}
	//删除时候用
	function deleteForm(id){
		var array =id;
		var len = 0;
		if(id!=null && id!=""){ 
					len ++; 
		}
		if(len > 0){
			 
			 //删除
		if (confirmMessage("<bean:message key='comm.jsp.add.shanchu' bundle='comm.commLocale'/>")){     
		    document.form.id.value = id;  
		    document.form.verbId.value = "delete";    
		    document.form.submit(); 
	    }   
		}else{
			alert("<bean:message key="security.jsp.commom.warn15" bundle="security"/>!");
			return false;
		}
	}
	//查询时候用
	function queryForm(){
		document.form.page.value = 1; 
	  	document.form.orderNo.value = ""; 
	 	document.form.asc.value = ""; 
	  	document.form.method = "POST";
	  	document.form.verbId.value = "queryDetail";
	  	document.form.submit();
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
			alert("<bean:message key="security.jsp.commom.warn15" bundle="security"/>!");
			return false;
		}else if(len > 1){
			alert("<bean:message key="security.jsp.commom.warn16" bundle="security"/>!");
			return false;
		}
	}
	//更新准备时候用
	function updateInitForm(id){
		var array = id;
		var idHidden = id;
		var len = 0;
		if(id!=null&&id!=""){
			len ++;					 
		}
		if(len == 1){
			document.form.id.value = idHidden;
			document.form.verbId.value = "updateInit";
			document.form.submit();
		}else if(len <= 0){
			alert("<bean:message key="security.jsp.commom.warn15" bundle="security"/>!");
			return false;
		}else if(len > 1){
			alert("<bean:message key="security.jsp.commom.warn17" bundle="security"/>!");
			return false;
		}
	}	
	function commandOrderBy(order, asc) { 
	  	document.form.orderNo.value = order; 
	  	document.form.asc.value = asc; 
	  	document.form.method = "POST";
	  	document.form.verbId.value = "queryDetail";
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
	  
	  document.form.verbId.value = "queryDetail";    
	  document.form.submit();
	}			
	function isMadeOf(val,str){
		var jj;
		var chr;
		for (jj=0;jj<val.length;++jj){
			chr=val.charAt(jj);
			if (str.indexOf(chr,0)==-1)
				return false;
		}			
		return true;
	}
	function handler(tp){
		if(tp =='ok'){
			okFn();
  		}
	}
	function showHspMessage(message){
				if(message != ''&& message != null){
					alert(message);
					return;
				}
			}
</script>
		
	</head>
	<body onload="showHspMessage('<%=securityStaffBaseinfo.getMessage()%>')">
		<!-- Form中的action项的值必须设置-->
		<form name="form" method="post" action="security/securityStaffBaseinfo.do">
			<!-- Head line -->
			<input type="hidden" name="verbId" value="<%=securityStaffBaseinfo.getVerbId()%>" />
			<input type="hidden" name="orderNo" value="<%=securityStaffBaseinfo.getOrderNo()%>" />
			<input type="hidden" name="asc" value="<%=securityStaffBaseinfo.getAsc()%>" />
			<input type="hidden" name="id" value="<%=securityStaffBaseinfo.getId()%>" />
			<!--查询条件-->
			<table  border="0" cellpadding="0" cellspacing="0" class="tblSearch">
				<tr>
					<td class="tblTitle" colspan="4">
						<bean:message key="security.jsp.securityConfigParamClass.query.item" bundle="security"/>
					</td>
				</tr>
				<tr>
					<td>
						<bean:message key="security.jsp.commom.staffCode" bundle="security"/>：
						<input name="staffCode" id="staffCode" type="text"  onKeyDown="huiche()"  value="<%=securityStaffBaseinfo.getStaffCode()%>" />
						
						<bean:message key="security.jsp.commom.name" bundle="security"/>：
						<input id="name" name="name" type="text"  value="<%=securityStaffBaseinfo.getName()%>" 
							onkeyup="GiveOptions(event, '<%=request.getContextPath()%>/searchSuggest.do', 'getStaffName_0000000001')" 
							onkeydown="huanhang(event)" />
						<span id="spanOutput" class="spanTextDropdown" style="display: none;"></span>
						<!--<bean:message key="security.jsp.commom.inputcode" bundle="security"/>：
						<input name="inputCode" type="text"  onkeydown="if(event.keyCode==13){event.keyCode=9;document.getElementById('queryImg').className='queryImg_down'}" onkeypress="eventOnKeyPress('hspConfigBaseinfoName')" value="<%=securityStaffBaseinfo.getInputCode()%>" />-->
						
						<bean:message key="security.jsp.commom.hspConfigBaseinfoName" bundle="security"/>：
						<input type="text" id="hspConfigBaseinfoName" name="hspConfigBaseinfoName" value="<%=securityStaffBaseinfo.getHspConfigBaseinfoName()%>"
							onkeyup="GiveOptions(event, '<%=request.getContextPath()%>/searchSuggest.do', 'getHspName_00000000004', 'hspConfigBaseinfoId')"
							onkeydown="huanhang(event)"/>
					    <input type="hidden" name="hspConfigBaseinfoId" id="hspConfigBaseinfoId" value="<%=securityStaffBaseinfo.getHspConfigBaseinfoId()%>" />
					    
					    
					    <input type="button" class="btnSave" name="<bean:message key="security.jsp.securityrolevsmenus.SecurityRoleVsMenus.item1" bundle="security"/>" value="<bean:message key="security.jsp.securityrolevsmenus.SecurityRoleVsMenus.item1" bundle="security"/>" onclick="queryForm()" />
					</td>
				</tr>
			</table>
			 <!--列表标题-->
		<div id="dvh1">
			<table border="0" cellpadding="0" cellspacing="0" class="tblSearchList">
   				 <caption style="text-align:left;">
					<img src="security/include/images/comm_list_nav_red.jpg" width="20" height="20" align="absmiddle" /> <span><bean:message key="security.jsp.commom.item1" bundle="security"/></span>
			    </caption>
   				 <thead>
	   				 <tr class="lstName">
	   				 	<td width="5%" height="26"><bean:message key="security.jsp.commom.staffCode" bundle="security"/></td>
	            		<td width="7%" height="26"><bean:message key="security.jsp.commom.name" bundle="security"/></td>
	            		<td width="15%" height="26"><bean:message key="security.jsp.commom.hspConfigBaseinfoName" bundle="security"/></td>
	            		<td width="4%" height="26"><bean:message key="security.jsp.commom.commConfigSexId" bundle="security"/></td>
	            		<td width="7%" height="26">激活时间</td>
	            		<td width="7%" height="26">有效时间</td>
	                    <td width="3%" height="26"><bean:message key="security.jsp.commom.button6" bundle="security"/></td>
	   				 </tr>
   				 </thead>
   			<tbody id="interval_row_id">
   				 <%
					if (securityStaffBaseinfo.getIdList() != null && securityStaffBaseinfo.getIdList().length > 0) {
						for (int i = 0; i < securityStaffBaseinfo.getIdList().length; i++) {
				 %>

				 <tr>		 
            		<td style="text-align:left;padding-left:10px;"><%=securityStaffBaseinfo.getStaffCodeList()[i]%></td>
            		<td style="text-align:left;padding-left:10px;"><%=securityStaffBaseinfo.getNameList()[i]%></td>
            		<td style="text-align:left;padding-left:10px;"><%=securityStaffBaseinfo.getHspConfigBaseinfoNameList()[i]%></td>
            		<td style="text-align:left;padding-left:10px;"><%=securityStaffBaseinfo.getCommConfigSexNameList()[i]%></td>
            		<td style="text-align:left;padding-left:10px;"><%=securityStaffBaseinfo.getRegTimeList()[i]%></td>
            		<td style="text-align:left;padding-left:10px;"><%=securityStaffBaseinfo.getStopTimeList()[i]%></td>
            		<td><img src="include/images/cmdView_s.jpg" style="cursor: hand; vertical-align: middle;" title="<bean:message key="security.jsp.commom.button6" bundle="security"/>" onclick="viewForm('<%=securityStaffBaseinfo.getIdList()[i]%>')" /></td>
   				</tr>
   				<%
					}
				}
				%>
			</tbody>
			</table>
		</div>
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
						<input type="hidden" id="pageId" name="pageId" value="page_282881f5346450530134645053210000" />
						<input type="hidden" id="reHref" name="reHref" value="<%=request.getContextPath()%>/security/securityStaffBaseinfo.do?verbId=queryDetail" />
						<%@ include file="/include/changepagesize.jsp" %>
							
						<input id="_total" name="totalPage" type="hidden" value="<%=totalPage%>" />
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
						<input id="_tp" name="page" type="text" value="<%=curPage%>" size="2" class="txt" />
						<bean:message key="jsp.pagetext4" bundle="conf.Init"/>
						&nbsp;
						<img style="cursor:hand;" src="include/images/go.jpg" width="18" border="0" onClick="goPage2()" />
					</td>
				</tr>
			</table>
			
		</form>
		<script language="javascript" src="include/javascript/interval_row_color.js"></script>
	</body>
</html>
