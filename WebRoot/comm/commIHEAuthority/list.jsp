<%@page contentType="text/html; charset=utf-8"%>
<%@page import="com.tianjian.util.comm.PageBean"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<jsp:useBean id="data" scope="request" type="com.tianjian.comm.struts.form.CommIHEAuthorityForm" />
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
		<title><bean:message  bundle="comm.comIHE" key="comm.jsp.commIHEAuthority.list"/></title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<script language="javascript" src="<bean:message  key="comm.js.includeTJMessage.path"  bundle="comm.commLocale"/>"></script>
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
			      if (confirmMessage("0-000004")){ 
			    document.form.idHidden.value = id;  
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
			      alert("<bean:message  bundle="comm.commLocale" key="comm.jsp.commom.warn4"/>!");
			      return;
			    }
			    if (_tp.value<=0){
			    	alert("<bean:message  bundle="comm.commLocale" key="comm.jsp.commom.pageGreateThanZero"/>!");
					return;
			    }
			    if(parseInt(_tp.value)>parseInt(_total.value)){
			      alert("<bean:message  bundle="comm.commLocale" key="comm.jsp.commom.warn3"/>!");
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
		<link rel="stylesheet" type="text/css" href="include/css/form.css" />
	</head>
	<body>
		<!-- Form中的action项的值必须设置-->
		<form name="form" method="post" action="comm/commIHEAuthority.do">
			<!-- Head line -->
			<input type="hidden" name="verbId" value="<%=data.getVerbId()%>">
			<input type="hidden" name="orderNo" value="<%=data.getOrderNo()%>">
			<input type="hidden" name="asc" value="<%=data.getAsc()%>">
			<input type="hidden" name="idHidden" value="<%=data.getIdHidden()%>">
			<!--查询条件-->
			<table width="95%" class="table2" align="center" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td id="list_L"></td>
					<td class="list_biaoti">
						<bean:message  bundle="comm.comIHE" key="comm.jsp.commIHEAuthority.list"/>
					</td>
					<td id="list_C"></td>
					<td class="list_cc">
						<bean:message  bundle="comm.comIHE" key="comm.jsp.segment"/>:
						<input name="seqNo" type="text" onkeypress="eventOnKeyPress('universalId')" value="<%=data.getSeqNo()%>" style="height:17px;">
					</td>
					<td class="list_cc">
						<bean:message  bundle="comm.comIHE" key="comm.jsp.segment1"/>:
						<input name="universalId" type="text" onkeypress="eventOnKeyPress('namespaceId')" value="<%=data.getUniversalId()%>" style="height:17px;">
					</td>
					<td class="list_cc">
						<bean:message  bundle="comm.comIHE" key="comm.jsp.segment3"/>:
						<input name="namespaceId" type="text" value="<%=data.getNamespaceId()%>" onkeypress="eventOnKeyPress('btnSearch')" style="height:17px;">
					</td>
					<td id="list_R"></td>
					<td class="button">
						<input type="button" id="btnSearch" name="btnSearch"  value="<bean:message  bundle="comm.commLocale" key="comm.jsp.commom.submit1"/>" onClick="submitQueryForm();" />
					</td>
				</tr>
			</table>
			<!--列表标题-->
			<table width="95%" align="center" class="table" border="0" cellspacing="0" cellpadding="0">
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
						<bean:message  bundle="comm.commLocale" key="comm.jsp.commom.selectList"/>
					</td>
					<td width="35%" align="right" style="border-right: 1px solid #BFBFBF; padding: 0px 10px 0px 0px;">
						<a href="javascript:cmdAdd()"><img border=0 alt="<bean:message  bundle="comm.commLocale" key="comm.jsp.commom.add"/>" src="include/images/cmdAdd_zh.gif" style="cursor: hand"> </a>
					</td>
				</tr>
			</table>
			<!--列表内容-->
			<table class="table" align="center" width="95%" cellspacing="1" cellpadding="0">
				<tr class="list_nav">
					<td width="">
						<table width="100%" border="0" cellpadding="0" cellspacing="0">
							<tr>
								<td rowspan="2" align="center">
									<bean:message  bundle="comm.comIHE" key="comm.jsp.segment"/>
								</td>
								<td align="bottom" height="12">
									<img src="include/images/cmdOrderByAsc.gif" border="0" alt="<bean:message  bundle="comm.commLocale" key="comm.jsp.commom.text21"/>" style="cursor: hand" onClick="commandOrderBy('1', '0')">
								</td>
							</tr>
							<tr>
								<td height="12">
									<img src="include/images/cmdOrderByDesc.gif" border="0" alt="<bean:message  bundle="comm.commLocale" key="comm.jsp.commom.text17"/>" style="cursor: hand" onClick="commandOrderBy('1', '1')">
								</td>
							</tr>
						</table>
					</td>
					<td width="">
						<table width="100%" border="0" cellpadding="0" cellspacing="0">
							<tr>
								<td rowspan="2" align="center">
									<bean:message  bundle="comm.comIHE" key="comm.jsp.segment1"/>
								</td>
								<td align="bottom" height="12">
									<img src="include/images/cmdOrderByAsc.gif" border="0" alt="<bean:message  bundle="comm.commLocale" key="comm.jsp.commom.text21"/>" style="cursor: hand" onClick="commandOrderBy('2', '0')">
								</td>
							</tr>
							<tr>
								<td height="12">
									<img src="include/images/cmdOrderByDesc.gif" border="0" alt="<bean:message  bundle="comm.commLocale" key="comm.jsp.commom.text17"/>" style="cursor: hand" onClick="commandOrderBy('2', '1')">
								</td>
							</tr>
						</table>
					</td>
					<td width="">
						<table width="100%" border="0" cellpadding="0" cellspacing="0">
							<tr>
								<td rowspan="2" align="center">
									<bean:message  bundle="comm.comIHE" key="comm.jsp.segment2"/>
								</td>
								<td align="bottom" height="12">
									<img src="include/images/cmdOrderByAsc.gif" border="0" alt="<bean:message  bundle="comm.commLocale" key="comm.jsp.commom.text21"/>" style="cursor: hand" onClick="commandOrderBy('3', '0')">
								</td>
							</tr>
							<tr>
								<td height="12">
									<img src="include/images/cmdOrderByDesc.gif" border="0" alt="<bean:message  bundle="comm.commLocale" key="comm.jsp.commom.text17"/>" style="cursor: hand" onClick="commandOrderBy('3', '1')">
								</td>
							</tr>
						</table>
					</td>
					<td width="">
						<table width="100%" border="0" cellpadding="0" cellspacing="0">
							<tr>
								<td rowspan="2" align="center">
									<bean:message  bundle="comm.comIHE" key="comm.jsp.segment3"/>
								</td>
								<td align="bottom" height="12">
									<img src="include/images/cmdOrderByAsc.gif" border="0" alt="<bean:message  bundle="comm.commLocale" key="comm.jsp.commom.text21"/>" style="cursor: hand" onClick="commandOrderBy('4', '0')">
								</td>
							</tr>
							<tr>
								<td height="12">
									<img src="include/images/cmdOrderByDesc.gif" border="0" alt="<bean:message  bundle="comm.commLocale" key="comm.jsp.commom.text17"/>" style="cursor: hand" onClick="commandOrderBy('4', '1')">
								</td>
							</tr>
						</table>
					</td>
					<td width="30">
						<bean:message  bundle="comm.commLocale" key="comm.jsp.commom.search"/>
					</td>
					<td width="30">
						<bean:message  bundle="comm.commLocale" key="comm.jsp.commom.change"/>
					</td>
					<td width="30">
						<bean:message  bundle="comm.commLocale" key="comm.jsp.commom.delete"/>
					</td>
				</tr>
				<tbody id="interval_row_id">
					<%
							if (data.getIdList() != null && data.getIdList().length > 0) {
							for (int i = 0; i < data.getIdList().length; i++) {
					%>
					<tr class="list_neirong">
						<td class="td_shenglue " height="25">
							<%=data.getSeqNoList()[i]%>
						</td>
						<td class="td_shenglue leftPadding" >
							<%=data.getUniversalIdList()[i]%>
						</td>
						<td class="td_shenglue leftPadding" >
							<%=data.getUniversalIdTypeList()[i]%>
						</td>
						<td class="td_shenglue leftPadding" >
							<%=data.getNamespaceIdList()[i]%>
						</td>
						
						<td>
							<img onClick="cmdView('<%=data.getIdList()[i]%>')" alt="<bean:message  bundle="comm.commLocale" key="comm.jsp.commom.detail"/>" src="include/images/cmdView_s.jpg" border="0" style="cursor: hand; vertical-align: middle;" />
						</td>
						<td>
							<img onClick="cmdEdit('<%=data.getIdList()[i]%>')" alt="<bean:message  bundle="comm.commLocale" key="comm.jsp.commom.change"/>" src="include/images/cmdEdit_s.jpg" border="0" style="cursor: hand; vertical-align: middle;" />
						</td>
						<td>
							<img onClick="cmdDel('<%=data.getIdList()[i]%>')" alt="<bean:message  bundle="comm.commLocale" key="comm.jsp.commom.delete"/>" src="include/images/cmdDel_s.jpg" border="0" style="cursor: hand; vertical-align: middle;" />
						</td>
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
						<input type="hidden" id="pageId" name="pageId" value="page_2828810b369603cc01369603cc480000" />
						<input type="hidden" id="reHref" name="reHref" value="<%=request.getContextPath()%>/comm/commIHEAuthority.do?verbId=query" />
						<%@ include file="/include/changepagesize.jsp" %>
						
						<input id="_total" name="totalPage" type="hidden" value="<%=totalPage%>">
						<bean:message  bundle="comm.commLocale" key="comm.jsp.commom.text15"/>
						<%=curPage%>
						<bean:message  bundle="comm.commLocale" key="comm.jsp.commom.pageTotal"/>
						<%=totalPage%>
						<bean:message  bundle="comm.commLocale" key="comm.jsp.commom.pageTotal1"/>
						<%=totalNum%>
						<bean:message  bundle="comm.commLocale" key="comm.jsp.commom.record"/>
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
						<bean:message  bundle="comm.commLocale" key="comm.jsp.commom.text2"/>
						<input id="_tp" name="page" type="text" value="<%=curPage%>" size="2" class="txt">
						<bean:message  bundle="comm.commLocale" key="comm.jsp.commom.page"/>&nbsp;
						<img style="cursor: hand;" src="include/images/go.jpg" width="18" border="0" onClick="goPage2()" />
					</td>
				</tr>
			</table>
		</form>
		<script language="javascript" src="include/javascript/interval_row_color.js"></script>
	</body>
</html>
