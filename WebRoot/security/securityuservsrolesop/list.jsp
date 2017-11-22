<%@page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@page import="com.tianjian.util.comm.PageBean"%>
<jsp:useBean id="data" scope="request"
	type="com.tianjian.security.struts.form.SecurityUserVsRolesOpForm" />
<jsp:useBean id="pb" scope="request"
	class="com.tianjian.util.comm.PageBean" />
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
		<title></title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<script language="javascript" src="<bean:message key="comm.js.CommMessage.path" bundle="security" />"></script>
		<link type="text/css" rev="stylesheet" rel="stylesheet" href="include/css/form.css" />
		<script language="javascript" src="<%=request.getContextPath() %>/include/javascript/eventOnKeyPress.js"></script>
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
				document.form.id.value = id;  
			    document.form.verbId.value = "detail";    
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

</script>
	</head>
	<body>
		<!-- Form中的action项的值必须设置-->
		<form name="form" method="post"
			action="<%=request.getContextPath()%>/security/securityUserVsRolesOp.do">
			<!-- Head line -->
			<input type="hidden" name="verbId"	 		value="<%=data.getVerbId()%>">
			<input type="hidden" name="orderNo"  		value="<%=data.getOrderNo()%>">
			<input type="hidden" name="asc"		 		value="<%=data.getAsc()%>">
			<input type="hidden" name="id" 	value="<%=data.getId()%>">
			<!--查询条件-->
			<table border="0" cellpadding="0" cellspacing="0" class="tblSearch" align="center">
				<tr>
					<td class="tblTitle" colspan="4">
						<bean:message key="security.jsp.securityuservsrolesop.list.item" bundle="security"/>
					</td>
				</tr>
				<tr>
					<td>
						<bean:message key="security.jsp.securityrolevsmenus.SecurityRoleVsMenus.roleId" bundle="security"/>：
							<select  name="securityConfigRolesId" style="width: 170px;" id="securityConfigRolesId" onkeypress="eventOnKeyPress('hspConfigBaseinfoId')"> 
					            <%
									if(data.getSecurityConfigRolesIds()!= null && data.getSecurityConfigRolesIds().length > 0){
										for(int i = 0; i < data.getSecurityConfigRolesIds().length; i++){
											String tempId = data.getSecurityConfigRolesIds()[i];
											String tempName = data.getSecurityConfigRolesNames()[i];
								%>
					        	<option value="<%=tempId %>" <%=tempId.equals(data.getSecurityConfigRolesId()) ? "selected" : "" %>>
									<%=tempName %>
								</option>
								<%
									}
								}
								%>  
					    	</select>

						<bean:message key="security.jsp.securityuservsrolesop.list.hspConfigBaseinfoId" bundle="security"/>：
						<select  name="hspConfigBaseinfoId" style="width: 235px;" id="hspConfigBaseinfoId" onkeypress="eventOnKeyPress('querysubmit')"> 
							<%
									if(data.getHspConfigBaseinfoIds()!= null && data.getHspConfigBaseinfoIds().length > 0){
										for(int i = 0; i < data.getHspConfigBaseinfoIds().length; i++){
											String tempId = data.getHspConfigBaseinfoIds()[i];
											String tempName = data.getHspConfigBaseinfoNames()[i];
							%>
			        	<option value="<%=tempId %>" <%=tempId.equals(data.getHspConfigBaseinfoId()) ? "selected" : "" %>>
							<%=tempName %>
						</option>
							<%
								}
							}
							%>  
    				</select>

						<input type="button" class="btnSave" name="querysubmit" value="<bean:message key="security.jsp.securityConfigParamClass.query.button1" bundle="security"/>" onClick="submitQueryForm();" />
					</td>
				</tr>
			</table>
			<%
				if(data.getNameList() != null && data.getNameList().length > 0){
			 %>
			<!--列表标题-->
			<table	border="0" cellpadding="0" cellspacing="0" class="tblSearchList" align="center">
				<caption style="text-align:left;">
					<img src="<%=request.getContextPath()%>/comm/include/images/comm_list_nav_red.jpg" width="20" height="20" align="absmiddle" /> <span style="font-weight:bold; color:#333;"><bean:message key="security.jsp.commom.item1" bundle="security"/></span>
			    </caption>
			    <thead>
			    	<tr class="lstName">
			            <th width="5%" height="26"><bean:message key="security.jsp.securityuservsrolesop.detail.staffCode" bundle="security"/><div><img src="include/images/cmdOrderByAsc.gif" alt="Ascender" vspace="7" onclick="commandOrderBy('1', '0')" /><img border="0" onclick="commandOrderBy('1', '1')" alt="Descender" src="include/images/cmdOrderByDesc.gif" /></div></th>
			            <th width="4%" height="26"><bean:message key="security.jsp.commom.name" bundle="security"/><div><img src="include/images/cmdOrderByAsc.gif" alt="Ascender" vspace="7" onclick="commandOrderBy('2', '0')" /><img border="0" onclick="commandOrderBy('2', '1')" alt="Descender" src="include/images/cmdOrderByDesc.gif" /></div></th>
			            <th width="15%" height="26"><bean:message key="security.jsp.securitystaffbaseinfo.list4web.securityStaffBaseinfoName" bundle="security"/><div><img src="include/images/cmdOrderByAsc.gif" alt="Ascender" vspace="7" onclick="commandOrderBy('3', '0')" /><img border="0" onclick="commandOrderBy('3', '1')" alt="Descender" src="include/images/cmdOrderByDesc.gif" /></div></th>
			            <th width="15%" height="26"><bean:message key="security.jsp.securityrolevsmenus.SecurityRoleVsMenus.roleId" bundle="security"/><div><img src="include/images/cmdOrderByAsc.gif" alt="Ascender" vspace="7" onclick="commandOrderBy('4', '0')" /><img border="0" onclick="commandOrderBy('4', '1')" alt="Descender" src="include/images/cmdOrderByDesc.gif" /></div></th>
			            <th width="4%" height="26"><bean:message key="security.jsp.commom.commConfigSexId" bundle="security"/><div><img src="include/images/cmdOrderByAsc.gif" alt="Ascender" vspace="7" onclick="commandOrderBy('5', '0')" /><img border="0" onclick="commandOrderBy('5', '1')" alt="Descender" src="include/images/cmdOrderByDesc.gif" /></div></th>
			            <th width="4%" height="26"><bean:message key="security.jsp.commom.birthDay" bundle="security"/><div><img src="include/images/cmdOrderByAsc.gif" alt="Ascender" vspace="7" onclick="commandOrderBy('6', '0')" /><img border="0" onclick="commandOrderBy('6', '1')" alt="Descender" src="include/images/cmdOrderByDesc.gif" /></div></th>
			            <th width="3%" height="26"><bean:message key="security.jsp.commom.button6" bundle="security"/></th>
			        </tr>
				</thead>
				<tbody id="interval_row_id">
					<%if (data.getIdList() != null
						&& data.getIdList().length > 0) {
						for (int i = 0; i < data.getIdList().length; i++) {
					%>
					<tr class="list_neirong">
						<td class="td_shenglue leftPadding" height="25"><%=data.getStaffCodeList()[i]%></td>
						<td class="td_shenglue leftPadding"><%=data.getNameList()[i]%></td>
						<td class="td_shenglue leftPadding"><%=data.getHspConfigBaseinfoNameList()[i]%></td>
						<td class="td_shenglue leftPadding"><%=data.getSecurityConfigRolesNameList()[i]%></td>
						<td class="td_shenglue leftPadding"><%=data.getCommConfigSexNameList()[i]%></td>
						<td class="td_shenglue leftPadding"><%=data.getDateOfBirthList()[i]%></td>					
						<td>
							<img onClick="cmdView('<%=data.getIdList()[i] %>')" alt="<bean:message key="security.jsp.commom.button9" bundle="security"/>" src="<%= request.getContextPath()%>/include/images/cmdView_s.jpg" border="0" style="cursor:hand; vertical-align:middle;" />
						</td>									
					</tr>
					<%		}
						}
					%>
				</tbody>
			</table>
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
						<input type="hidden" id="pageId" name="pageId" value="page_282881f53464510601346451060b0000" />
						<input type="hidden" id="reHref" name="reHref" value="<%=request.getContextPath()%>/security/securityUserVsRolesOp.do?verbId=query" />
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
						<%
						} else {
						%>
							<input id="_tp" name="page" type="hidden" value="1" size="2" class="txt" />
						<%
						}
						%>
					</td>
				</tr>
			</table>

		</form>
		<script language="javascript"
			src="include/javascript/interval_row_color.js"></script>
</html>
