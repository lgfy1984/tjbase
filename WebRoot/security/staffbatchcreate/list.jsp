<%@page contentType="text/html; charset=utf-8"%>
<%@page import="com.tianjian.util.Converter"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<jsp:useBean id="data" scope="request" type="com.tianjian.security.struts.form.StaffBatchCreateForm" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<%
		if (request.getServerPort() == 80) {
		%>
		<base href="http://<%=request.getServerName()%><%=request.getContextPath()%>/"/>
		<%
		} else {
		%>
		<base href="http://<%=request.getServerName()%>:<%=request.getServerPort()%><%=request.getContextPath()%>/"/>
		<%
		}
		%>
		<title>批量创建操作人员</title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
		<meta http-equiv=Cache-Control content=no-cache /> 
		<script language="javascript" src="<bean:message  key="comm.js.includeTJMessage.path"  bundle="comm.commLocale"/>"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/include/javascript/My97DatePicker/WdatePicker.js" defer="defer"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/include/javascript/divideTime.js" defer="defer"></script>
		<script language="javascript" src="include/javascript/eventOnKeyPress.js"></script>
		<script language="javascript">
		function submitQueryForm() { 
		  document.form.orderNo.value = ""; 
		  document.form.asc.value = ""; 
		  document.form.method = "POST";
		  document.form.verbId.value = "query";
		  document.form.submit();
		}
		
		function submitFilterForm() { 
		  document.form.orderNo.value = ""; 
		  document.form.asc.value = ""; 
		  document.form.method = "POST";
		  document.form.verbId.value = "<%=data.getVerbId()%>";
		  document.form.submit();
		}
		
		function submitCreateForm() {
			var flag = false;
			var _selectedHspIds = document.form.selectedHspIds;
			if(_selectedHspIds != null && _selectedHspIds.length > 0){
				for(var i = 0 ; i < _selectedHspIds.length; i++){
					if(_selectedHspIds[i].checked){
						flag = true;
						break;
					}
				}
			}
			if(!flag){
				alert("尚未选中任何机构！");
				return;
			}
			document.form.method = "POST";
			document.form.verbId.value = "createBySelectedHspIds";
			document.form.submit();
		}
		
		function submitCreateAllForm() {
			document.form.method = "POST";
			document.form.verbId.value = "createAll";
			document.form.submit();
		}
		
		function commandOrderBy(order, asc) { 
		  document.form.orderNo.value = order; 
		  document.form.asc.value = asc; 
		  document.form.method = "POST";
		  document.form.verbId.value = "query";
		  document.form.submit();
		}

	function showHspMessage(message){
		if(message != ''&& message != null){
			alert(message);
			return;
		}
	}
	
	function selectAllCheckBox(s){
		var _selectedHspIds = document.form.selectedHspIds;
		if(_selectedHspIds != null && _selectedHspIds.length > 0){
			for(var i = 0 ; i < _selectedHspIds.length; i++){
				_selectedHspIds[i].checked = s.checked;
			}
		}
	}
</script>
		<link type="text/css" rev="stylesheet" rel="stylesheet" href="include/css/form.css" />
	</head>
	<body onload="showHspMessage('<%=data.getMessage()%>')">
		<!-- Form中的action项的值必须设置-->
		<form name="form" method="post" action="security/staffBatchCreate.do">
			<!-- Head line -->
			<input type="hidden" name="verbId" value="<%=data.getVerbId()%>" />
			<input type="hidden" name="orderNo" value="<%=data.getOrderNo()%>" />
			<input type="hidden" name="asc" value="<%=data.getAsc()%>" />
			<input type="hidden" name="idHidden" value="<%=data.getIdHidden()%>" />
			<!--查询条件-->
			<table border="0" cellpadding="0" cellspacing="0" class="tblSearch" align="center">
				<tr>
					<td class="tblTitle" colspan="4">
						为机构批量创建操作人员
					</td>
				</tr>
				<tr>
					<td>
						当前<%="filter".equals(data.getFilter())?"暂无操作人员的机构":"所有的机构" %>共<%=data.getCount()%>所
					</td>
				</tr>
				<tr>
					<td>
						<input type="checkbox" name="filter" <%="filter".equals(data.getFilter())?"checked":"" %> 
							style="border: 0" value="filter" onclick="submitFilterForm()"/>筛选出暂无操作人员的机构
						<%
							if("init".equals(data.getVerbId())){
						%>
						<input type="button" class="btnSave" id="btnCreate" name="btnCreate"
							value="查看机构列表" onclick="submitQueryForm()"/> 
						<input type="button" class="btnSave" id="btnCreate" name="btnCreate"
							value="不查看直接创建操作员" onclick="submitCreateAllForm()"/> 
						<%
							}else{
						%>
						<input type="button" class="btnSave" id="btnCreate" name="btnCreate"
							value="为选中的机构创建操作员" onclick="submitCreateForm()"/>
						<%
							}
						%>
						<font style="color:red">(*机构代码已被其他操作员使用的，或者已创建的将不会创建)</font>
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
			            <th width="8%" height="26"><input type="checkbox" onchange="selectAllCheckBox(this)"/>全选</th>
			            <th width="25%" height="26">机构代码<div><img src="include/images/cmdOrderByAsc.gif" alt="Ascender" vspace="7" onclick="commandOrderBy('1', '0')" /><img border="0" onclick="commandOrderBy('1', '1')" alt="Descender" src="include/images/cmdOrderByDesc.gif" /></div></th>
			            <th width="28%" height="26">机构名称<div><img src="include/images/cmdOrderByAsc.gif" alt="Ascender" vspace="7" onclick="commandOrderBy('2', '0')" /><img border="0" onclick="commandOrderBy('2', '1')" alt="Descender" src="include/images/cmdOrderByDesc.gif" /></div></th>
			            <th width="13%" height="26">现有操作人员数量<div><img src="include/images/cmdOrderByAsc.gif" alt="Ascender" vspace="7" onclick="commandOrderBy('3', '0')" /><img border="0" onclick="commandOrderBy('3', '1')" alt="Descender" src="include/images/cmdOrderByDesc.gif" /></div></th>
			            <th width="13%" height="26">机构代码是否被其他操作员使用<div><img src="include/images/cmdOrderByAsc.gif" alt="Ascender" vspace="7" onclick="commandOrderBy('4', '0')" /><img border="0" onclick="commandOrderBy('4', '1')" alt="Descender" src="include/images/cmdOrderByDesc.gif" /></div></th>
			            <th width="13%" height="26">是否已创建卫生人员维护操作员<div><img src="include/images/cmdOrderByAsc.gif" alt="Ascender" vspace="7" onclick="commandOrderBy('5', '0')" /><img border="0" onclick="commandOrderBy('5', '1')" alt="Descender" src="include/images/cmdOrderByDesc.gif" /></div></th>
			        </tr>
				</thead>
				<tbody id="interval_row_id">
					<%
						if (data.getResultList() != null && data.getResultList().size() > 0) {
							for (int i = 0; i < data.getResultList().size(); i++) {
								Object[] obj = (Object[]) data.getResultList().get(i);
					%>
					<tr>
						<td>
							<input type="checkbox" name="selectedHspIds" value="<%=Converter.toBlank(obj[0]) %>"/>
						</td>
						<td style="text-align:left;padding-left:10px;">
							<%=Converter.toBlank(obj[1]) %>
						</td>
						<td style="text-align:left;padding-left:10px;">
							<%=Converter.toBlank(obj[2]) %>
						</td>
						<td>
							<%=Converter.toBlank(obj[3]) %>
						</td>
						<td>
							<%="".equals(Converter.toBlank(obj[4]))?"否":"<font style='color:red'>已被使用</font>" %>
						</td>
						<td>
							<%="".equals(Converter.toBlank(obj[5]))?"否":"<font style='color:green'>已创建</font>" %>
						</td>
					</tr>
					<%
							}
						}
					%>
				</tbody>
			</table>
			</div>
		</form>
		<script language="javascript" src="include/javascript/interval_row_color.js"></script>
	</body>
</html>
