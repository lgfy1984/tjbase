<%@page contentType="text/html; charset=utf-8"%>
<%@ taglib prefix="html" uri="/WEB-INF/struts-html.tld"%>
<%@ taglib prefix="bean" uri="/WEB-INF/struts-bean.tld"%>
<%@ taglib prefix="logic" uri="/WEB-INF/struts-logic.tld"%>
<%@page import="com.tianjian.util.comm.PageBean"%>
<jsp:useBean id="data" scope="request" type="com.tianjian.comm.struts.form.CommConfigLocationTownForm" />
<jsp:useBean id="pb" scope="request" class="com.tianjian.util.comm.PageBean" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%if (request.getServerPort() == 80) {%>
		<base href="http://<%=request.getServerName()%><%=request.getContextPath()%>/"/>
		<%} else {%>
		<base href="http://<%=request.getServerName()%>:<%=request.getServerPort()%><%=request.getContextPath()%>/" />
		<%}%>
		<title><bean:message key="comm.jsp.common.selectProvince" bundle="conf.comm.Comm"/>list.jsp</title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<script language="javascript" src="<bean:message key="comm.js.comm.message" bundle="conf.comm.Comm"/>"></script>
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
			    if (!confirm(getCommMessage("10005","",""))) return false;    
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
			      alert("<bean:message key="comm.jsp.common.error" bundle="conf.comm.Comm"/>!");
			      return;
			    }
			    if (_tp.value<=0){
			    	alert("<bean:message key="comm.jsp.common.alert2" bundle="conf.comm.Comm"/>!");
					return;
			    }
			    if(parseInt(_tp.value)>parseInt(_total.value)){
			      alert("<bean:message key="comm.jsp.common.morethanpage" bundle="conf.comm.Comm"/>!");
			      return;
			    } 
			    
			  document.form.verbId.value = "query";    
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
	<body onload="document.form.inputCode.focus();showCommMessage('','<%=data.getMessage()%>','1')">
		<!-- Form中的action项的值必须设置-->
		<form name="form" method="post" action="comm/commConfigLocationTown.do">
			<!-- Head line -->
			<input type="hidden" name="verbId" value="<%=data.getVerbId()%>"/>
			<input type="hidden" name="orderNo" value="<%=data.getOrderNo()%>"/>
			<input type="hidden" name="asc" value="<%=data.getAsc()%>"/>
			<input type="hidden" name="id" value="<%=data.getId()%>" />
			<input type="hidden" name="idHidden" value="<%=data.getIdHidden()%>"/>

			<!--查询条件-->
			<table width="95%" class="table7" align="center" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td id="list_L"></td>
					<td class="list_biaoti"><bean:message key="comm.jsp.common.selectProvince" bundle="conf.comm.Comm"/></td>
					<td id="list_C"></td>
					<td class="list_cc">
						<bean:message key="comm.jsp.common.inputItemCode" bundle="conf.comm.Comm"/>：<input name="inputCode" type="text" onkeypress="eventOnKeyPress('itemCode')" value="<%=data.getInputCode()%>"/>
					</td>
					<td class="list_cc">
						<bean:message key="comm.jsp.common.villagecode" bundle="conf.comm.Comm"/>：<input name="itemCode" type="text" onkeypress="eventOnKeyPress('itemName')" value="<%=data.getItemCode()%>"/>
					</td>
					<td class="list_cc">
						<bean:message key="comm.jsp.common.townName" bundle="conf.comm.Comm"/>：<input name="itemName" type="text" onkeypress="eventOnKeyPress('<bean:message key="comm.jsp.common.commit" bundle="conf.comm.Comm"/>')" value="<%=data.getItemName()%>"/>
					</td>
					<td id="list_R"></td>
					<td class="button">
						<input type="button" name="<bean:message key="comm.jsp.common.commit" bundle="conf.comm.Comm"/>" value="<bean:message key="comm.jsp.common.commit2" bundle="conf.comm.Comm"/>" onclick="submitQueryForm();" />
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
					<td width="48" align="center" valign="middle" style="border-left: 1px solid #BFBFBF;">
						<img src="<%=request.getContextPath()%>/comm/include/images/comm_list_nav_red.jpg"/>
					</td>
					<td width="481" align="left" class="jiacu">
						<bean:message key="comm.jsp.common.chekresult" bundle="conf.comm.Comm"/>
					</td>
					<td width="" align="right" style="border-right: 1px solid #BFBFBF; padding: 0px 10px 0px 0px;">
						<a href="javascript:cmdAdd()"><img border=0 alt="<bean:message key="comm.jsp.common.add" bundle="conf.comm.Comm"/>" src="<bean:message key="security.jsp.securityconfigpublicclass.add" bundle="conf.security.security"/>" style="cursor: hand"/> </a>
					</td>
				</tr>
			</table>
			<!--列表内容-->
			<table class="table" align="center" width="95%" cellspacing="1" cellpadding="0">
				<tr class="list_nav">
					<td width="">
						<bean:message key="comm.jsp.common.villagecode" bundle="conf.comm.Comm"/>
						<span style="margin:0;padding:0;width:7px;height:15px;display:block;position:absolute;right:5px;top:0px;">
			<span style="margin:0;padding:0;width:7px;height:4px;display:block;"><img src="include/images/cmdOrderByAsc.gif" border="0" alt="<bean:message key="comm.jsp.common.up" bundle="conf.comm.Comm"/>" style="cursor:hand" onclick="commandOrderBy('1', '0')" /></span>
			<span style="margin:0;margin-top:5px;padding:0;width:7px;height:4px;display:block;"><img src="include/images/cmdOrderByDesc.gif" border="0" alt="<bean:message key="comm.jsp.common.down" bundle="conf.comm.Comm"/>" style="cursor: hand" onclick="commandOrderBy('1', '1')" /></span>
			</span>				
					</td>
					<td width="">
						<bean:message key="comm.jsp.common.townName" bundle="conf.comm.Comm"/><span style="margin:0;padding:0;width:7px;height:15px;display:block;position:absolute;right:5px;top:0px;">
			<span style="margin:0;padding:0;width:7px;height:4px;display:block;"><img src="include/images/cmdOrderByAsc.gif" border="0" alt="<bean:message key="comm.jsp.common.up" bundle="conf.comm.Comm"/>" style="cursor:hand" onclick="commandOrderBy('2', '0')" /></span>
			<span style="margin:0;margin-top:5px;padding:0;width:7px;height:4px;display:block;"><img src="include/images/cmdOrderByDesc.gif" border="0" alt="<bean:message key="comm.jsp.common.down" bundle="conf.comm.Comm"/>" style="cursor: hand" onclick="commandOrderBy('2', '1')" /></span>
			</span>					
					</td>
					<td width="">
						<bean:message key="comm.jsp.common.inputItemCode" bundle="conf.comm.Comm"/><span style="margin:0;padding:0;width:7px;height:15px;display:block;position:absolute;right:5px;top:0px;">
			<span style="margin:0;padding:0;width:7px;height:4px;display:block;"><img src="include/images/cmdOrderByAsc.gif" border="0" alt="<bean:message key="comm.jsp.common.up" bundle="conf.comm.Comm"/>" style="cursor:hand" onclick="commandOrderBy('3', '0')" /></span>
			<span style="margin:0;margin-top:5px;padding:0;width:7px;height:4px;display:block;"><img src="include/images/cmdOrderByDesc.gif" border="0" alt="<bean:message key="comm.jsp.common.down" bundle="conf.comm.Comm"/>" style="cursor: hand" onclick="commandOrderBy('3', '1')" /></span>
			</span>					
					</td>
					<td width="">
						<bean:message key="comm.jsp.common.place" bundle="conf.comm.Comm"/><span style="margin:0;padding:0;width:7px;height:15px;display:block;position:absolute;right:5px;top:0px;">
			<span style="margin:0;padding:0;width:7px;height:4px;display:block;"><img src="include/images/cmdOrderByAsc.gif" border="0" alt="<bean:message key="comm.jsp.common.up" bundle="conf.comm.Comm"/>" style="cursor:hand" onclick="commandOrderBy('4', '0')" /></span>
			<span style="margin:0;margin-top:5px;padding:0;width:7px;height:4px;display:block;"><img src="include/images/cmdOrderByDesc.gif" border="0" alt="<bean:message key="comm.jsp.common.down" bundle="conf.comm.Comm"/>" style="cursor: hand" onclick="commandOrderBy('4', '1')" /></span>
			</span>			
					</td>
					<td width="30">
						<bean:message key="comm.jsp.common.check" bundle="conf.comm.Comm"/>
					</td>
					<td width="30">
						<bean:message key="comm.jsp.common.alter" bundle="conf.comm.Comm"/>
					</td>
					<td width="30">
						<bean:message key="comm.jsp.common.delete" bundle="conf.comm.Comm"/>
					</td>
				</tr>
				<tbody id="interval_row_id">
					<%
						if (data.getIdList() != null && data.getIdList().length > 0) {
							for (int i = 0; i < data.getIdList().length; i++) {
					%>
					<tr class="list_neirong">
						<td class="td_shenglue leftPadding" height="25">
							<%=data.getItemCodeList()[i]%>
						</td>
						<td class="td_shenglue leftPadding" >
							<%=data.getItemNameList()[i]%>
						</td>
						<td class="td_shenglue leftPadding" >
							<%=data.getInputCodeList()[i]%>
						</td>
						<td class="td_shenglue leftPadding" >
							<%=data.getCommConfigLocationNameList3()[i]%>
						</td>
						<td>
							<img onclick="cmdView('<%=data.getIdList()[i]%>')" alt="<bean:message key="comm.jsp.common.detail" bundle="conf.comm.Comm"/>"
								src="include/images/cmdView_s.jpg" border="0"
								style="cursor: hand; vertical-align: middle;" />
						</td>
						<td>
							<img onclick="cmdEdit('<%=data.getIdList()[i]%>')" alt="<bean:message key="comm.jsp.common.alter" bundle="conf.comm.Comm"/>"
								src="include/images/cmdEdit_s.jpg" border="0"
								style="cursor: hand; vertical-align: middle;" />
						</td>
						<td>
							<img onclick="cmdDel('<%=data.getIdList()[i]%>')" alt="<bean:message key="comm.jsp.common.delete" bundle="conf.comm.Comm"/>"
								src="include/images/cmdDel_s.jpg" border="0"
								style="cursor: hand; vertical-align: middle;" />
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
					<bean:message key="comm.jsp.common.page1" bundle="conf.comm.Comm" arg0="<%=String.valueOf(curPage)%>" arg1="<%=String.valueOf(totalPage)%>"/>
					&nbsp;<bean:message key="comm.jsp.common.page2" bundle="conf.comm.Comm" arg0="<%=String.valueOf(totalNum)%>" />
					&nbsp;|&nbsp;
					<%if (curPage > 1) {%>
					<a href="javascript:goPage('0')"><img src="include/images/shouye.gif" align="middle" border="0" /></a>&nbsp; <a href="javascript:goPage('<%=curPage - 1 %>')"><img src="include/images/shang.gif" align="middle" border="0" /></a>&nbsp;
					<%} else {
				out.println("<img src='include/images/shouye_s.gif' align='middle' border='0px' />&nbsp;&nbsp;<img src='include/images/shang_s.gif' align='middle' border='0px' />&nbsp;");
			}
			if (curPage < totalPage) {%>
					<a href="javascript:goPage('<%=curPage + 1 %>')"><img src="include/images/xia.gif" align="middle" border="0" /></a>&nbsp; <a href="javascript:goPage('<%=totalPage %>')"><img src="include/images/mo.gif" align="middle" border="0" /></a>&nbsp;
					<%} else {
				out.println("<img src='include/images/xia_x.gif' align='middle' border='0px' />&nbsp;&nbsp;<img src='include/images/mo_m.gif' align='middle' border='0px' />&nbsp;");
			}%>
					| &nbsp;<bean:message key="comm.jsp.common.page3" bundle="conf.comm.Comm"/>
					<input id="_tp" name="page" type="text" value="<%=curPage%>" size="2" class="txt">
					<bean:message key="comm.jsp.common.page4" bundle="conf.comm.Comm"/>
					&nbsp;		
					<img style="cursor:hand;" src="include/images/go.jpg" width="18" border="0" onclick="goPage2()" />
				</td>
				</tr>
			</table>
		</form>
		<script language="javascript" src="include/javascript/interval_row_color.js"></script>
	</body>
</html>
