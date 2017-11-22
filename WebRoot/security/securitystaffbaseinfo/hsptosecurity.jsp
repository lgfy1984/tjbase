<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="html" uri="/WEB-INF/struts-html.tld"%>
<%@ taglib prefix="bean" uri="/WEB-INF/struts-bean.tld"%>
<%@ taglib prefix="logic" uri="/WEB-INF/struts-logic.tld"%>
<%@page import="com.tianjian.util.comm.PageBean"%>
<jsp:useBean id="pb" scope="request" class="com.tianjian.util.comm.PageBean" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%if (request.getServerPort() == 80) {%>
		<base href="http://<%=request.getServerName()%><%=request.getContextPath()%>/" />
		<%} else {%>
		<base href="http://<%=request.getServerName()%>:<%=request.getServerPort()%><%=request.getContextPath()%>/" />
		<%}%>
		<title><bean:message key="security.jsp.securitystaffbaseinfo.hsptosecurity.title" bundle="security"/></title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<!-- link rel="stylesheet" href="<%=request.getContextPath()%>/include/css/open.css" /-->
		<script language="javascript" src="<bean:message key="include.js.gettext_staff.path" bundle="security" />"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/include/javascript/jianbian.js"></script>
		<script language="javascript" src="<%=request.getContextPath()%>/include/javascript/eventOnKeyPress.js"></script>
		<link rel="stylesheet" href="<%=request.getContextPath()%>/include/css/open.css" />
 		<script language="javascript">
			function submitQueryForm(){ 
			  document.form.page.value = 1; 
			  document.form.orderNo.value = ""; 
			  document.form.asc.value = ""; 
			  document.form.method = "POST";
			  document.form.verbId.value = "hspToSecurity";
			  document.form.submit();
			}
			function commandOrderBy(order, asc) { 
			  document.form.orderNo.value = order; 
			  document.form.asc.value = asc; 
			  document.form.method = "POST";
			  document.form.verbId.value = "hspToSecurity";
			  document.form.submit();
			}
			function goPage(page) {  
			   document.form.page.value = page;
			   document.form.verbId.value = "hspToSecurity";    
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
			   
			  document.form.verbId.value = "hspToSecurity";    
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
					document.form.idHidden.value = idHidden;
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
			function updateInitForm(){ 
				var array = document.getElementsByName('checkbx');
				
				var arrayTransfer = document.getElementsByName('transfer');
				var idHidden = "";
				var len = 0;
				if(array.length>0){
					for(var i=0; i<array.length; i++){
						if(array[i].checked){
							len ++;
							idHidden = array[i].value;
						if(arrayTransfer[i].value=="exist"){
						alert("<bean:message key="security.jsp.securitystaffbaseinfo.hsptosecurity.warn2" bundle="security"/>!");
						
						return false;
						}
						}
						
					}
				}
				
				
				if(len >0){ 
				if (!confirm("<bean:message key="security.jsp.securitystaffbaseinfo.hsptosecurity.warn3" bundle="security"/>?")) return false;
					document.form.verbId.value = "hspSaveToSecurity";
					document.form.submit();
				}else {
					alert("<bean:message key="security.jsp.commom.warn15" bundle="security"/>!");
					return false;
				} 
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
					 if (!confirm("<bean:message key="security.jsp.commom.warn14" bundle="security"/>?")) return false; 
					 document.form.verbId.value = "delete";
					 document.form.submit();
				}else{
					alert("<bean:message key="security.jsp.commom.warn15" bundle="security"/>!");
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
			//新增初始化工作，转到新增页面
			function addInitForm(){
				document.form.verbId.value = "addInit";
				document.form.submit();
			}
		</script>
		<link rel="stylesheet" type="text/css" href="/include/css/form.css" />
	</head>
	
	<body>
		<form name="form" action="<%=request.getContextPath()%>/hsp/hspStaffBaseinfo.do">
		<input type="hidden" name="verbId"/>
		<input type="hidden" name="orderNo" value="${dataForm.orderNo}"/>
		<input type="hidden" name="asc" value="${dataForm.asc}"/>
		<input type="hidden" name="idHidden"/>
<!--查询条件-->
<table width="95%" align="center" style="background-color: #828283;" cellpadding="0" cellspacing="1" border="0">
	<tr>
					<td class="searchBg">
						<bean:message key="security.jsp.securityConfigParamClass.query.item" bundle="security"/>
					</td>
				</tr>
				<tr>
					<td class="date_img">
						<bean:message key="security.jsp.securitystaffbaseinfo.hsptosecurity.hspConfigName" bundle="security"/>：
						<input class="input" onkeypress="eventOnKeyPress('name')" id="displayInputId_2" name="hspConfigName" value="<%=dataForm.getHspConfigName() %>"/>
						<input type="hidden" id="hiddenInputId_2" name="hspConfigBaseinfoIdHidden" value="<%=dataForm.getHspConfigBaseinfoIdHidden() %>" />
						<img src="security/include/images/select.gif"
							style="cursor: pointer;"
							onclick="add('<%=request.getContextPath()%>/hsp/hspStaffBaseinfo.do?verbId=getHspConfigBase','displayInputId_2','hiddenInputId_2')" />
						
						<bean:message key="security.jsp.commom.name" bundle="security"/>：
						<input type="text"  class="input" name="name" onkeypress="eventOnKeyPress('idNo')" value="<%=dataForm.getName()%>"/>
						<bean:message key="security.jsp.commom.idNo" bundle="security"/>：
						<input type="text"  class="input" name="idNo" style="width: 120px" onkeypress="eventOnKeyPress('sub')" value="<%=dataForm.getIdNo() %>"/>
						<input type="button" name="sub" value="<bean:message key="security.jsp.commom.button10" bundle="security"/>" onClick="submitQueryForm();" />
					</td>
				</tr>
</table>
<!--列表标题-->
			<table width="700px" align="center" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td colspan="3" height="5px">
					</td>
				</tr>
				<tr>
					<td width="48" align="center"  valign="middle" style="border-left: 0px solid #BFBFBF;">
					</td>
					<td width="481" align="left" class="jiacu">
					</td>
					<td width="300" align="right" valign="middle" style="border-right: 0px solid #BFBFBF; padding: 0px 10px 0px 0px;">
						<a href="javascript:cmdAdd()">	
						</a>
						<img
						src="<%=request.getContextPath()%>/hsp/include/images/cmdChange.gif"
						onmouseover="this.src='<%=request.getContextPath()%>/hsp/include/images/service_change_over.gif'"
						onmouseout="this.src='<%=request.getContextPath()%>/hsp/include/images/cmdChange.gif'"
						title="<bean:message key="security.jsp.securitystaffbaseinfo.hsptosecurity.item" bundle="security"/>" onclick="updateInitForm()" />
					</td>
			
				</tr>
		</table>
<!--列表内容-->
<table class="list_table" align="center" cellpadding="0" cellspacing="1" border="0">
		<tr class="tdnav">
					<td width="">
					<input type="checkbox" name="checkB" onclick="selectAll()" />
									<bean:message key="security.jsp.securitystaffbaseinfo.hsptosecurity.item1" bundle="security"/>
</td>
					<td width=""><bean:message key="security.jsp.commom.name" bundle="security"/>
					</td>
					<td width="">
					<bean:message key="security.jsp.commom.commConfigSexId" bundle="security"/>
								</td>
					<td width="">
									<bean:message key="security.jsp.securitystaffbaseinfo.hsptosecurity.hspName" bundle="security"/>
							</td>
					<td width="">
									<bean:message key="security.jsp.securitystaffbaseinfo.hsptosecurity.item2" bundle="security"/>
							</td>
	</tr>			
	<tbody id="interval_row_id">
		<% if (dataForm.getIdsHiddenArray() != null && dataForm.getIdsHiddenArray().length > 0) {
			  
			for (int i = 0; i < dataForm.getIdsHiddenArray().length; i++) {
		%>
		<tr>	
			<td>
				<input type="checkbox" name="checkbx" value=<%=dataForm.getIdsHiddenArray()[i]%> />
			<%=dataForm.getEmpNoArray()[i]%>
			</td> 
			<td class="leftPadding td_shenglue"><%=dataForm.getNameArray()[i]%></td>
			<td class="leftPadding td_shenglue"><%=dataForm.getSexArray()[i]%></td>
			<td class="leftPadding td_shenglue"><%=dataForm.getHspNameArray()[i]%></td>
			
			<%   int flag=0;
			for(int j=0;j<dataForm.getSecurityStaffBaseinfoIdArray().length;j++){  
			%> 
          <%  if(dataForm.getSecurityStaffBaseinfoIdArray()[j]!=null && dataForm.getSecurityStaffBaseinfoIdArray()[j].equals(dataForm.getIdsHiddenArray()[i])){ 
                 flag = 1; 
				 break;
                 }
				 }
				 if(flag==1){
			  %> 
			  <td class="leftPadding td_shenglue"><font color="red"><bean:message key="security.jsp.securitystaffbaseinfo.hsptosecurity.item3" bundle="security"/></font><input type="hidden" name="transfer"  value="exist" /></td>
			 <%   }else{ %> 
			   <td class="leftPadding td_shenglue"><bean:message key="security.jsp.securitystaffbaseinfo.hsptosecurity.item4" bundle="security"/><input type="hidden" name="transfer"  value="empty"  /></td>
			    <%   
			 }
			  %> 
		</tr>
		 <%   
			 }
			 }
			  %> 
	</tbody>
		<tr>
					<td colspan="5" align="center" bgcolor="#ffffff" height="35px">
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
							value="<%=totalPage%>" />
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
						| &nbsp;<bean:message key="security.jsp.commom.item6" bundle="security"/>
						<input id="_tp" name="page" type="text" value="<%=curPage%>"
							size="2" class="txt" />
						<bean:message key="security.jsp.commom.item7" bundle="security"/>&nbsp;
						<img style="cursor: hand;" src="include/images/go.jpg" width="18"
							border="0" onclick="goPage2()" />
					</td>
				</tr>
	</table>
	<div class="buttonDiv">
					<input type="button"  name="btnBack" value="<bean:message key="security.jsp.commom.button2" bundle="security"/>" onclick="history.go(-1)" />
			   </div>
	</form>
	<script language="javascript" src="<%=request.getContextPath()%>/include/javascript/interval_row_color.js"></script>
	</body>
</html>