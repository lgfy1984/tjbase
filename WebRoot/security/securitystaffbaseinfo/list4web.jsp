<%@page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@page import="com.tianjian.util.comm.PageBean"%>
<jsp:useBean id="securityStaffBaseinfo" scope="request" type="com.tianjian.security.struts.form.SecurityStaffBaseinfoForm" />
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
		<title><bean:message key="security.jsp.securitystaffbaseinfo.list4web.title" bundle="security"/> </title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<script language="javascript" src="include/javascript/ymPrompt.js" ></script>
		<link rel="stylesheet" type="text/css" href="include/css/ymPrompt.css" />
		<script language="javascript" src="<bean:message key="include.js.TJMessage.path" bundle="security" />"></script>
		<script language="javascript" src="include/javascript/eventOnKeyPress.js"></script>
		<script language="javascript">
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
	//新增初始化工作，转到新增页面
	function addInitForm(){
		document.form.verbId.value = "addInit";
		document.form.submit();
	}
	//删除时候用
	function deleteForm(){
		var array = document.getElementsByName('checkbx');
		var len = 0;
		if(array.length>0){
			for(var i=0; i<array.length; i++){
				if(array[i].checked){
					len ++;
				}
			}
		}
		if(len > 0){
			 if (!ymPrompt.confirmInfo({message:'<bean:message key="security.jsp.commom.warn14" bundle="security"/>',handler:handler})) return false; 
			 document.form.verbId.value = "delete";
			 document.form.submit();
		}else{
			alert("<bean:message key="security.jsp.commom.warn15" bundle="security"/>！");
			return false;
		}
	}
	//查询时候用
	function queryForm(){
		document.form.page.value = 1; 
	  	document.form.orderNo.value = ""; 
	 	document.form.asc.value = ""; 
	  	document.form.method = "POST";
	  	document.form.verbId.value = "query4Web";
	  	document.form.submit();
	}
	//查看详细时候用
	function viewForm(){
		var array = document.getElementsByName('checkbx');
		var idHidden = "";
		var len = 0;
		if(array.length>0){
			for(var i=0; i<array.length; i++){
				if(array[i].checked){
					len ++;
					idHidden = array[i].value;
				}
			}
		}
		if(len == 1){
			document.form.id.value = idHidden;
			document.form.verbId.value = "detail";
			document.form.submit();
		}else if(len <= 0){
			alert("<bean:message key="security.jsp.commom.warn15" bundle="security"/>！");
			return false;
		}else if(len > 1){
			alert("<bean:message key="security.jsp.commom.warn16" bundle="security"/>");
			return false;
		}
	}
	//更新准备时候用
	function updateInitForm(){
		var array = document.getElementsByName('checkbx');
		var idHidden = "";
		var len = 0;
		if(array.length>0){
			for(var i=0; i<array.length; i++){
				if(array[i].checked){
					len ++;
					idHidden = array[i].value;
				}
			}
		}
		if(len == 1){
			document.form.id.value = idHidden;
			document.form.verbId.value = "updateInit";
			document.form.submit();
		}else if(len <= 0){
			alert("<bean:message key="security.jsp.commom.warn15" bundle="security"/>！");
			return false;
		}else if(len > 1){
			alert("<bean:message key="security.jsp.commom.warn17" bundle="security"/>");
			return false;
		}
	}	
	//------------------		
	function commandOrderBy(order, asc) { 
	  	document.form.orderNo.value = order; 
	  	document.form.asc.value = asc; 
	  	document.form.method = "POST";
	  	document.form.verbId.value = "query4Web";
	  	document.form.submit();
	}
	function goPage(page) {  
	   document.form.page.value = page;
	   document.form.verbId.value = "query4Web";    
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
	   
	  document.form.verbId.value = "query4Web";    
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
</script>
		<link rel="stylesheet" type="text/css" href="include/css/form.css" />
	</head>
	<body onload="showMessage('','<%=securityStaffBaseinfo.getMessage()%>','1');">
		<!-- Form中的action项的值必须设置-->
		<form name="form" method="post" action="security/securityStaffBaseinfo.do">
			<!-- Head line -->
			<input type="hidden" name="verbId" value="<%=securityStaffBaseinfo.getVerbId()%>" />
			<input type="hidden" name="orderNo" value="<%=securityStaffBaseinfo.getOrderNo()%>" />
			<input type="hidden" name="asc" value="<%=securityStaffBaseinfo.getAsc()%>" />
			<input type="hidden" name="id" value="<%=securityStaffBaseinfo.getId()%>" />
			<!--查询条件-->
			<table width="829" align="center" style="background-color: #828283;" cellpadding="0" cellspacing="1" border="0">
				<tr>
					<td class="searchBg">
						<bean:message key="security.jsp.securityConfigParamClass.query.item" bundle="security"/>
					</td>
				</tr>
				<tr>
					<td class="date_img">
						<bean:message key="security.jsp.commom.staffCode" bundle="security"/>：
						<input name="staffCode" type="text" class="input" onkeypress="eventOnKeyPress('name')" value="<%=securityStaffBaseinfo.getStaffCode()%>" />
						<bean:message key="security.jsp.commom.name" bundle="security"/>：
						<input name="name" type="text" class="input" onkeypress="eventOnKeyPress('inputCode')" value="<%=securityStaffBaseinfo.getName()%>" />
						<bean:message key="security.jsp.commom.inputcode" bundle="security"/>：
						<input name="inputCode" type="text" class="input" onkeydown="if(event.keyCode==13){event.keyCode=9;document.getElementById('queryImg').className='queryImg_down'}" onkeypress="eventOnKeyPress('dateOfBirth')" value="<%=securityStaffBaseinfo.getInputCode()%>" />
						<input type="button" name="queryImg" id="queryImg" class="queryImg" value="" onmouseover="this.className='queryImg_down'" onmouseout="this.className='queryImg'" onclick="queryForm()" hidefocus='true' />
					</td>
				</tr>
			</table>
			<table width="829" align="center" cellpadding="0" cellspacing="0" border="0">
				<tr>
					<td height="30" align="left" style="padding-top: 4px;">
						<img src="security/include/images/service_view.gif" onmouseover="this.src='security/include/images/service_view_over.gif'" onmouseout="this.src='security/include/images/service_view.gif'" title="<bean:message key="security.jsp.commom.button6" bundle="security"/>" onclick="viewForm()" />
					</td>
				</tr>
			</table>
			<table class="list_table" align="center" cellpadding="0" cellspacing="1" border="0">
				<tr class="tdnav">
					<td width="40">
						<input type="checkbox" name="checkB" onclick="selectAll()" />
					</td>
					<td width="100">
						<bean:message key="security.jsp.commom.staffCode" bundle="security"/>
					</td>
					<td width="110">
						<bean:message key="security.jsp.commmom.classCode" bundle="security"/>
					</td>
					<td width="191">
						<bean:message key="security.jsp.securitystaffbaseinfo.list4web.securityStaffBaseinfoName" bundle="security"/>
					</td>
					<td width="180">
						<bean:message key="security.jsp.securitystaffbaseinfo.list4web.HspConfigBaseinfoName" bundle="security"/>
					</td>
					<td width="90">
						<bean:message key="security.jsp.commom.commConfigSexId" bundle="security"/>
					</td>
					<td width="110">
						<bean:message key="security.jsp.commom.birthDay" bundle="security"/>
					</td>
				</tr>
				<tbody id="interval_row_id">
					<%
							if (securityStaffBaseinfo.getIdList() != null && securityStaffBaseinfo.getIdList().length > 0) {
							for (int i = 0; i < securityStaffBaseinfo.getIdList().length; i++) {
					%>
					<tr class="list_neirong">
						<td>
							<input type="checkbox" name="checkbx" value="<%=securityStaffBaseinfo.getIdList()[i]%>" />
						</td>
						<td class="td_shenglue leftPadding">
							<%=securityStaffBaseinfo.getStaffCodeList()[i]%>
						</td>
						<td class="td_shenglue leftPadding">
							<%=securityStaffBaseinfo.getNameList()[i]%>
						</td>
						<td class="td_shenglue leftPadding">
							<%=securityStaffBaseinfo.getHspConfigBaseinfoNameList()[i]%>
						</td>
						<td class="td_shenglue leftPadding">
						</td>
						<td class="td_shenglue leftPadding">
							<%=securityStaffBaseinfo.getCommConfigSexNameList()[i]%>
						</td>
						<td class="td_shenglue leftPadding">
							<%=securityStaffBaseinfo.getDateOfBirthList()[i]%>
						</td>
					</tr>
					<%
						}
						}
					%>
				</tbody>
				<tr>
					<td colspan="7" align="center" bgcolor="#ffffff" height="35px">
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
						<input id="_total" name="totalPage" type="hidden" value="<%=totalPage%>" />
						<bean:message key="security.jsp.commom.item2" bundle="security"/>
						<%=curPage%>
						<bean:message key="security.jsp.commom.item3" bundle="security"/>
						<%=totalPage%>
						<bean:message key="security.jsp.commom.item4" bundle="security"/>
						<%=totalNum%>
						<bean:message key="security.jsp.commom.item5" bundle="security"/>&nbsp;|&nbsp;
						<%
						if (curPage > 1) {
						%>
						<a href="javascript:goPage('0')"><img src="include/images/shouye.gif" align="middle" border="0" />
						</a>&nbsp;
						<a href="javascript:goPage('<%=curPage - 1%>')"><img src="include/images/shang.gif" align="middle" border="0" />
						</a>&nbsp;
						<%
								} else {
								out.println("<img src='include/images/shouye_s.gif' align='middle'  border='0px' />&nbsp;&nbsp;<img src='include/images/shang_s.gif' align='middle'  border='0px' />&nbsp;");
							}
							if (curPage < totalPage) {
						%>
						<a href="javascript:goPage('<%=curPage + 1%>')"><img src="include/images/xia.gif" align="middle" border="0" />
						</a>&nbsp;
						<a href="javascript:goPage('<%=totalPage%>')"><img src="include/images/mo.gif" align="middle" border="0" />
						</a>&nbsp;
						<%
								} else {
								out.println("<img src='include/images/xia_x.gif' align='middle' border='0px' />&nbsp;&nbsp;<img src='include/images/mo_m.gif' align='middle'  border='0px' />&nbsp;");
							}
						%>
						| &nbsp;<bean:message key="security.jsp.commom.item6" bundle="security"/>
						<input id="_tp" name="page" type="text" value="<%=curPage%>" size="2" class="txt" />
						<bean:message key="security.jsp.commom.item7" bundle="security"/>&nbsp;
						<img style="cursor: hand; margin-bottom: -3px;" src="include/images/go.jpg" width="18" border="0" onclick="goPage2()" />
					</td>
				</tr>
			</table>
		</form>
		<script language="javascript" src="include/javascript/interval_row_color.js"></script>
	</body>
</html>
