<%@page contentType="text/html; charset=utf-8"%>
<%@taglib prefix="bean" uri="/WEB-INF/struts-bean.tld"%>
<%@page import="com.tianjian.util.comm.PageBean"%>
<jsp:useBean id="data" scope="request" type="com.tianjian.comm.struts.form.CommCongihMidwiferyForm" />
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
		<title><bean:message key="comm.jsp.commcongihmidwifery.list.title" bundle="conf.comm.CommMessageguoh"/></title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<script language="javascript" src="include/javascript/TJMessage.js"></script>
		<script language="javascript" src="include/javascript/eventOnKeyPress.js"></script>
		<script language="javascript" src='<%=request.getContextPath() %><bean:message key="comm.js.commMessageguoh" bundle="conf.comm.CommMessageguoh"/>' defer="defer">
		</script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/hsp/include/javascript/jianbian.js" defer="defer"></script>
		<script language="javascript" src="<%=request.getContextPath()%>/hsp/include/javascript/gettext_staff.js" defer="defer"></script>
		<link rel="stylesheet" href="<%=request.getContextPath()%>/hsp/include/css/open.css" />
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
//删除
function cmdDel(id,id1) {  
      if (confirmMessage("0-000004")){ 
    document.form.hspConfigBaseinfoIdHidden.value = id1;  
    document.form.hspCalssCodeHidden.value = id;  
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
      alert('<bean:message key="comm.jsp.commcongihmidwifery.list.gopage2_1" bundle="conf.comm.CommMessageguoh"/>');
      return;
    }
    if (_tp.value<=0){
    	alert('<bean:message key="comm.jsp.commcongihmidwifery.list.gopage2_2" bundle="conf.comm.CommMessageguoh"/>');
		return;
    }
    if(parseInt(_tp.value)>parseInt(_total.value)){
      alert('<bean:message key="comm.jsp.commcongihmidwifery.list.gopage2_3" bundle="conf.comm.CommMessageguoh"/>');
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
function huiche(){
	if(event.keyCode==13){
		event.keyCode=9;
	}
}
</script>
		<link rel="stylesheet" type="text/css" href="include/css/form.css" />
	</head>
	<body  onload="showCommMessageguoh('','<%=data.getMessage() %>','1')">
		<!-- Form中的action项的值必须设置-->
		<form name="form" method="post" action="comm/commCongihMidwifery.do">
			<!-- Head line -->
			<input type="hidden" name="verbId" value="<%=data.getVerbId()%>">
			<input type="hidden" name="orderNo" value="<%=data.getOrderNo()%>">
			<input type="hidden" name="asc" value="<%=data.getAsc()%>">
			<input type="hidden" name="hspConfigBaseinfoIdHidden" value="<%=data.getHspConfigBaseinfoIdHidden()%>">
			<input type="hidden" name="hspCalssCodeHidden" value="<%=data.getHspCalssCodeHidden()%>">
			<!--查询条件-->
			<table width="95%" class="table2" align="center" border="0" cellspacing="0" cellpadding="0">
				<tr>
					<td id="list_L"></td>
					<td class="list_biaoti">
						<bean:message key="comm.jsp.commcongihmidwifery.list.td1" bundle="conf.comm.CommMessageguoh"/>
					</td>
					
					<td id="list_C"></td>
					<td class="list_cc">
						<bean:message key="comm.jsp.commcongihmidwifery.list.td2" bundle="conf.comm.CommMessageguoh"/>
						<input type="text" id="displayInputId_2"
							name="hspCalssName" style="font-size: 14px"
							value="" readonly="readonly"
							onkeydown="huiche()" />
						<input type="hidden" id="hiddenInputId_2"
							value="<%=data.getHspClassCode()%>" name="hspClassCode" />
						<img
							src="<%=request.getContextPath()%>/hsp/include/images/select.gif"
							style="cursor: pointer;"
							onclick="add('<%=request.getContextPath()%>/comm/commCongihMidwifery.do?verbId=getType','displayInputId_2','hiddenInputId_2','','')" />
					</td>
					
					<td class="list_cc">
						<bean:message key="comm.jsp.commcongihmidwifery.list.td3" bundle="conf.comm.CommMessageguoh"/>
						<input type="text" id="displayInputId_1"
							name="hspConfigBaseinfoName" style="font-size: 14px"
							value="" readonly="readonly"
							onkeydown="huiche()" />
						<input type="hidden" id="hiddenInputId_1"
							value="<%=data.getHspConfigBaseinfoId()%>" name="hspConfigBaseinfoId" />
						<img
							src="<%=request.getContextPath()%>/hsp/include/images/select.gif"
							style="cursor: pointer;"
							onclick="add('<%=request.getContextPath()%>/hsp/hspConfigBaseinfo.do?verbId=getHsp&hspType=3','displayInputId_1','hiddenInputId_1','','')" />
					</td>
					<td id="list_R"></td>
					<td class="button">
						<input type="button" id="btnSearch" name="btnSearch" 
						name='<bean:message key="comm.jsp.commcongihmidwifery.list.td5" bundle="conf.comm.CommMessageguoh"/>' value='<bean:message key="comm.jsp.commcongihmidwifery.list.td6" bundle="conf.comm.CommMessageguoh"/>' onClick="submitQueryForm();" />
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
						<bean:message key="comm.jsp.commcongihmidwifery.list.td7" bundle="conf.comm.CommMessageguoh"/>
					</td>
					<td width="35%" align="right" style="border-right: 1px solid #BFBFBF; padding: 0px 10px 0px 0px;">
						<a href="javascript:cmdAdd()"><img border=0 alt='<bean:message key="comm.jsp.commcongihmidwifery.list.td8" bundle="conf.comm.CommMessageguoh"/>' src="<bean:message key="security.jsp.securityconfigpublicclass.add" bundle="conf.security.security"/>" style="cursor: hand"> </a>
					</td>
				</tr>
			</table>
			<!--列表内容-->
			<table class="table" align="center" width="95%" cellspacing="1" cellpadding="0">
				<tr class="list_nav">
					<td width="120px">
						<table width="100%" border="0" cellpadding="0" cellspacing="0">
							<tr>
								<td rowspan="2" align="center">
									<bean:message key="comm.jsp.commcongihmidwifery.list.td11" bundle="conf.comm.CommMessageguoh"/>
								</td>
								<td align="bottom" height="12">
									<img src="include/images/cmdOrderByAsc.gif" border="0" alt='<bean:message key="comm.jsp.commcongihmidwifery.list.img5" bundle="conf.comm.CommMessageguoh"/>' style="cursor: hand" onClick="commandOrderBy('1', '0')">
								</td>
							</tr>
							<tr>
								<td height="12">
									<img src="include/images/cmdOrderByDesc.gif" border="0" alt='<bean:message key="comm.jsp.commcongihmidwifery.list.img6" bundle="conf.comm.CommMessageguoh"/>' style="cursor: hand" onClick="commandOrderBy('1', '1')">
								</td>
							</tr>
						</table>
					</td>
					<td width="300px">
						<table width="100%" border="0" cellpadding="0" cellspacing="0">
							<tr>
								<td rowspan="2" align="center">
									<bean:message key="comm.jsp.commcongihmidwifery.list.td13" bundle="conf.comm.CommMessageguoh"/>
								</td>
								
							</tr>
							
						</table>
					</td>
					<td width="300px">
						<table width="100%" border="0" cellpadding="0" cellspacing="0">
							<tr>
								<td rowspan="2" align="center">
									<bean:message key="comm.jsp.commcongihmidwifery.list.td14" bundle="conf.comm.CommMessageguoh"/>
								</td>
								
							</tr>

						</table>
					</td>
					<td width="80px">
						<bean:message key="comm.jsp.commcongihmidwifery.list.td15" bundle="conf.comm.CommMessageguoh"/>
					</td>
				</tr>
				<tbody id="interval_row_id">
					<%
							if (data.getHspConfigBaseinfoIdList() != null && data.getHspConfigBaseinfoIdList().size() > 0) {
							for (int i = 0; i < data.getHspConfigBaseinfoIdList().size(); i++) {
					%>
					<tr class="list_neirong">
						<td style="text-align: center;">
							<%=data.getSeqNoList().get(i)%>
						</td>
						<td style="text-align: center;">
							<%=data.getHspConfigBaseinfoNameList().get(i)%>
						</td>
						<td style="text-align: center;">
							<%=data.getHspCalssNameList().get(i)%>
						</td>
						<td style="text-align: center;">
							<img onClick="cmdDel('<%=data.getHspClassCodeList().get(i)%>','<%=data.getHspConfigBaseinfoIdList().get(i)%>')" alt='<bean:message key="comm.jsp.commcongihmidwifery.list.img9" bundle="conf.comm.CommMessageguoh"/>' src="include/images/cmdDel_s.jpg" border="0" style="cursor: hand; vertical-align: middle;" />
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
						<input id="_total" name="totalPage" type="hidden" value="<%=totalPage%>">
						<bean:message key="comm.jsp.commcongihmidwifery.list.page1" bundle="conf.comm.CommMessageguoh"/>
						<%=curPage%>
						<bean:message key="comm.jsp.commcongihmidwifery.list.page2" bundle="conf.comm.CommMessageguoh"/>
						<%=totalPage%>
						<bean:message key="comm.jsp.commcongihmidwifery.list.page3" bundle="conf.comm.CommMessageguoh"/>
						<%=totalNum%>
						<bean:message key="comm.jsp.commcongihmidwifery.list.page4" bundle="conf.comm.CommMessageguoh"/>
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
						<bean:message key="comm.jsp.commcongihmidwifery.list.page5" bundle="conf.comm.CommMessageguoh"/>
						<input id="_tp" name="page" type="text" value="<%=curPage%>" size="2" class="txt">
						<bean:message key="comm.jsp.commcongihmidwifery.list.page6" bundle="conf.comm.CommMessageguoh"/>
						<img style="cursor: hand;" src="include/images/go.jpg" width="18" border="0" onClick="goPage2()" />
					</td>
				</tr>
			</table>
		</form>
		<script language="javascript" src="include/javascript/interval_row_color.js"></script>
	</body>
</html>
