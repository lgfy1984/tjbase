<%@page contentType="text/html; charset=utf-8"%>
<%@page import="com.tianjian.util.comm.PageBean"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<jsp:useBean id="data" scope="request" type="com.tianjian.security.struts.form.SecurityXtSysLogForm" />
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
		<title>系统日志信息</title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<script language="javascript" src="<bean:message  key="comm.js.includeTJMessage.path"  bundle="comm.commLocale"/>"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/include/javascript/My97DatePicker/WdatePicker.js" defer="defer"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/include/javascript/divideTime.js" defer="defer"></script>
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


		//查看详细
		function cmdView(id) {
		    document.form.idHidden.value = id;  
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
	function showHspMessage(message){
		if(message != ''&& message != null){
			alert(message);
			return;
		}
	}
</script>
		<link type="text/css" rev="stylesheet" rel="stylesheet" href="include/css/form.css" />
	</head>
	<body onload="showHspMessage('<%=data.getMessage()%>')">
		<!-- Form中的action项的值必须设置-->
		<form name="form" method="post" action="security/securityXtSysLog.do">
			<!-- Head line -->
			<input type="hidden" name="verbId" value="<%=data.getVerbId()%>" />
			<input type="hidden" name="orderNo" value="<%=data.getOrderNo()%>" />
			<input type="hidden" name="asc" value="<%=data.getAsc()%>" />
			<input type="hidden" name="idHidden" value="<%=data.getIdHidden()%>" />
			<!--查询条件-->
			<table border="0" cellpadding="0" cellspacing="0" class="tblSearch" align="center">
				<tr>
					<td class="tblTitle" colspan="4">
						系统日志信息
					</td>
				</tr>
				<tr>
					<td>
							日志等级：
							<select id="logLevel" name="logLevel">
							  <option value =""></option>
							  <option value ="fatal">严重</option>
							  <option value ="error">错误</option>
							  <option value="warn">警告</option>
							  <option value="info">信息</option>
							  <option value="debug">调试</option>
							</select>
							日志内容
							<input type="text" id="logMessage" name="logMessage" value="<%=data.getLogMessage() %>">
							日志生成时间（开始段）:
							<input type="text" id="startTime" name="startTime"
								 size="135px" readonly="readonly"
								onkeydown="huiche()" value="<%=data.getStartTime() %>"
								onclick="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'%y-%M-%d'})" />
							<img
								onclick="WdatePicker({el:'startTime',dateFmt:'yyyy-MM-dd',maxDate:'%y-%M-%d'})"
								src="<%=request.getContextPath()%>/include/javascript/My97DatePicker/skin/datePicker.gif"
								style="cursor: pointer;" />
					
						日志生成时间（结束段）:
						<input type="text" id="endTime" name="endTime"
								 size="135px" readonly="readonly"
								onkeydown="huiche()" value="<%=data.getEndTime() %>"
								onclick="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'%y-%M-%d'})" />
							<img
								onclick="WdatePicker({el:'endTime',dateFmt:'yyyy-MM-dd',maxDate:'%y-%M-%d'})"
								src="<%=request.getContextPath()%>/include/javascript/My97DatePicker/skin/datePicker.gif"
								style="cursor: pointer;" />
					
						<input type="button" class="btnSave" id="btnSearch" name="btnSearch"  
							value="<bean:message  bundle="comm.commLocale" key="comm.jsp.commom.submit1"/>" onClick="submitQueryForm();" />
					</td>
				</tr>
			</table>
			<!--列表标题-->
			<div id="dvh1">
			<table border="0" cellpadding="0" cellspacing="0" class="tblSearchList" align="center">
				<caption style="text-align:left;">
					<img src="<%=request.getContextPath()%>/comm/include/images/comm_list_nav_red.jpg" width="20" height="20" align="absmiddle" /> <span style="font-weight:bold; color:#333;"><bean:message  bundle="comm.commLocale" key="comm.jsp.commom.selectList"/></span>
			    </caption>
				<thead>
			    	<tr class="lstName">
			            <th width="6%" height="26">日志生成时间<div><img src="include/images/cmdOrderByAsc.gif" alt="Ascender" vspace="7" onclick="commandOrderBy('1', '0')" /><img border="0" onclick="commandOrderBy('1', '1')" alt="Descender" src="include/images/cmdOrderByDesc.gif" /></div></th>
			            <th width="5%" height="26">日志等级<div><img src="include/images/cmdOrderByAsc.gif" alt="Ascender" vspace="7" onclick="commandOrderBy('2', '0')" /><img border="0" onclick="commandOrderBy('2', '1')" alt="Descender" src="include/images/cmdOrderByDesc.gif" /></div></th>
			            <th width="15%" height="26">日志生成位置<div><img src="include/images/cmdOrderByAsc.gif" alt="Ascender" vspace="7" onclick="commandOrderBy('3', '0')" /><img border="0" onclick="commandOrderBy('3', '1')" alt="Descender" src="include/images/cmdOrderByDesc.gif" /></div></th>
			            <th width="8%" height="26">日志内容<div><img src="include/images/cmdOrderByAsc.gif" alt="Ascender" vspace="7" onclick="commandOrderBy('4', '0')" /><img border="0" onclick="commandOrderBy('4', '1')" alt="Descender" src="include/images/cmdOrderByDesc.gif" /></div></th>
			            <th width="3%" height="26"><bean:message  bundle="comm.commLocale" key="comm.jsp.commom.search"/></th>
			        </tr>
				</thead>
				<tbody id="interval_row_id">
					<%
							if (data.getIdList() != null && data.getIdList().length > 0) {
							for (int i = 0; i < data.getIdList().length; i++) {
					%>
					<tr>
						<td style="text-align:left;padding-left:10px;">
							<%=data.getLogTimeList()[i]%>
						</td>
						<td style="text-align:left;padding-left:10px;">
							<%=data.getLogLevelList()[i]%>
						</td>
						<td style="text-align:left;padding-left:10px;">
							<%=data.getLogActionList()[i]%>
						</td>
						<td style="text-align:left;padding-left:10px;">
							<%=data.getLogMessageList()[i]%>
						</td>
						<td>
							<img onClick="cmdView('<%=data.getIdList()[i]%>')" alt="<bean:message  bundle="comm.commLocale" key="comm.jsp.commom.detail"/>" src="include/images/cmdView_s.jpg" border="0" style="cursor: hand; vertical-align: middle;" />
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
			<table  width="100%" align="center" class="tblScrollFooter">
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
						<input type="hidden" id="pageId" name="pageId" value="page_282881f53463902b013463902b220000" />
						<input type="hidden" id="reHref" name="reHref" value="<%=request.getContextPath()%>/comm/commConfigAbo.do?verbId=query" />
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
					</td>
				</tr>
			</table>
		</form>
		<script language="javascript" src="include/javascript/interval_row_color.js"></script>
	</body>
</html>
