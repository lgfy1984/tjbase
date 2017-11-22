<%@page contentType="text/html; charset=utf-8"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<jsp:useBean id="data" scope="request" class="com.tianjian.hsp.struts.form.HspStaffBaseinfoMergeForm" />
<jsp:useBean id="pb" scope="request" class="com.tianjian.util.comm.PageBean" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
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
		<title>查询页面</title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
		<script language="javascript" src="<%=request.getContextPath()%>/comm/include/javascript/CommMessage.js"></script>
		
		<link rel="stylesheet" href="include/calendar/theme.css" />
		<script src="include/calendar/calendar.js"></script>
		<script src="include/calendar/calendar-setup.js"></script>
		<script src="include/calendar/calendar-zh.js"></script>
		<script language="javascript">
			function saveForm(idHidden){
				 document.form.idHidden.value = idHidden;
			     var ChkEls=document.getElementsByName("id");
				 var checkedIds="";
				 for(var i=0;i < ChkEls.length;i++){
				 	 if(ChkEls.item(i).type != "checkbox")continue;
				 		 var oEl = ChkEls.item(i);
				  		 if(oEl.checked) {
				   			checkedIds = checkedIds + (oEl.value + ",");
				  		 }
				 }
				 document.form.checkValue.value = checkedIds;
				 if (checkedIds == ""){  
					 alert("请选择对比条件");
					 return false;
				 }
			    
			    document.form.method = "POST";
				document.form.verbId.value = "compareQuery";
				document.form.submit();
			}
			function submitQueryForm() { 
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
			  
			  with(document.all) {
			    if (!isMadeOf(_tp.value,'1234567890')) {
			      alert("错误的整数!");
			      return;
			    }
			    if (_tp.value<=0){
			    	alert("页数必须大于0!");
					return;
			    }
			    if(parseInt(_tp.value)>parseInt(_total.value)){
			      alert("不能大于总页数!");
			      return;
			    } 
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
			function commandOrderBy(order, asc) { 
			  	document.form.orderNo.value = order; 
			  	document.form.asc.value = asc; 
			  	document.form.method = "POST";
			  	document.form.verbId.value = "query";
			  	document.form.submit();
			}
</script>
		<link type="text/css" rev="stylesheet" rel="stylesheet" href="include/css/form.css" />
	</head>
	<body onload="showCommMessage('','<%=data.getMessage() %>','1')">
		<!-- Form中的action项的值必须设置-->
		<form name="form" method="post" action="<%=request.getContextPath()%>/hsp/hspStaffBaseinfoMerge.do">
			<!-- Head line -->
			<input type="hidden" name="queryFlag" value="queryFlag" />
			<input type="hidden" name="verbId" value="<%=data.getVerbId()%>" />
			<input type="hidden" name="orderNo" value="<%=data.getOrderNo()%>" />
			<input type="hidden" name="asc" value="<%=data.getAsc()%>" />
			<input type="hidden" name="idHidden" value="<%=data.getId()%>" />
			<input type="hidden" name="checkValue" value="<%=data.getCheckValue()%>" />

			<!--查询条件-->
			<table  border="0" cellpadding="0" cellspacing="0" class="tblSearch" align="center">
				<tr>
					<td class="tblTitle" colspan="4">
						<span>※</span>卫生人员信息<span>※</span>
					</td>
				</tr>
				<tr>
					<td>
						<input type="checkbox" name="id" value="name"  />姓名：
						<input name="name" id="id" type="text"  value="<%=data.getName()%>" />
						<input type="checkbox" name="id" value="commConfigSexId"   />性别：
						<select  id="commConfigSexId" name="commConfigSexId" onkeypress="eventOnKeyPress('birthPlace')">
		            		<option value=""></option>
		            		<%
							if(data.getCommConfigSexIds() != null && data.getCommConfigSexIds().length > 0){
								for(int i = 0; i < data.getCommConfigSexIds().length; i++){
									String tempId = data.getCommConfigSexIds()[i];
									String tempName = data.getCommConfigSexNames()[i];
							%>
				        	<option value="<%=tempId %>" <%=tempId.equals(data.getCommConfigSexId()) ? "selected" : "" %>>
								<%=tempName %>
							</option>
							<%
								}
							}
							%>  
					</select>
						<input type="checkbox" name="id" value="dateOfBirth"  />出生日期：
						<input name="birthday" id="dateOfBirth" type="text" dataType="Custom" regexp="^(?:(?:([0-9]{4}(-|\/)(?:(?:0?[1,3-9]|1[0-2])(-|\/)(?:29|30)|((?:0?[13578]|1[02])(-|\/)31)))|([0-9]{4}(-|\/)(?:0?[1-9]|1[0-2])(-|\/)(?:0?[1-9]|1\\d|2[0-8]))|(((?:(\\d\\d(?:0[48]|[2468][048]|[13579][26]))|(?:0[48]00|[2468][048]00|[13579][26]00))(-|\/)0?2(-|\/)29))))$"
							msg="日期格式不正确，期待格式为：XXXX-XX-XX！" readonly="readonly"
							require="false"  value="<%=data.getBirthday()%>" /><img id="dateOfBirthButton" src="include/calendar/calendar.gif"
							style="cursor: hand;" align="middle" />
						<input type="checkbox" name="id" value="idNo"   />身份证号：
						<input name="idNo" id="idNo" type="text" value="<%=data.getIdNo()%>" />
						<input type="button" class="btnSave" name="查询" value="查&nbsp&nbsp询" onClick="submitQueryForm();" />
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
			    		<th width="5%" height="26">人员编码<div><img src="include/images/cmdOrderByAsc.gif" alt="Ascender" vspace="7" onclick="commandOrderBy('1', '0')" /><img border="0" onclick="commandOrderBy('1', '1')" alt="Descender" src="include/images/cmdOrderByDesc.gif" /></div></th>
			            <th width="5%" height="26">姓名<div><img src="include/images/cmdOrderByAsc.gif" alt="Ascender" vspace="7" onclick="commandOrderBy('2', '0')" /><img border="0" onclick="commandOrderBy('2', '1')" alt="Descender" src="include/images/cmdOrderByDesc.gif" /></div></th>
			            <th width="4%" height="26">性别<div><img src="include/images/cmdOrderByAsc.gif" alt="Ascender" vspace="7" onclick="commandOrderBy('3', '0')" /><img border="0" onclick="commandOrderBy('3', '1')" alt="Descender" src="include/images/cmdOrderByDesc.gif" /></div></th>
			            <th width="5%" height="26">身份证号<div><img src="include/images/cmdOrderByAsc.gif" alt="Ascender" vspace="7" onclick="commandOrderBy('4', '0')" /><img border="0" onclick="commandOrderBy('4', '1')" alt="Descender" src="include/images/cmdOrderByDesc.gif" /></div></th>
			          	<th width="5%" height="26">出生日期<div><img src="include/images/cmdOrderByAsc.gif" alt="Ascender" vspace="7" onclick="commandOrderBy('5', '0')" /><img border="0" onclick="commandOrderBy('5', '1')" alt="Descender" src="include/images/cmdOrderByDesc.gif" /></div></th>
			          	<th width="3%" height="26">对比</th>
			        </tr>
				</thead>
			
			<!--列表内容-->
				<tbody id="interval_row_id">
					<%if (data.getIdArray() != null && data.getIdArray().length > 0) {
						for (int i = 0; i < data.getIdArray().length; i++) {
					%>
						<tr>
							<td>
								<%=data.getEmpNoArray()[i] %><%=data.getEmpNoArray()[i] %>
							</td>
							<td style="text-align:left;padding-left:10px;">
								<%=data.getNameArray()[i]%>
							</td>
							<td style="text-align:left;padding-left:10px;">
								<%=data.getCommConfigSexNameArray()[i]%>
							</td>
							<td>
								<%=data.getIdNoArray()[i] %>
							</td>
							<td style="text-align:left;padding-left:10px;">
								<%=data.getBirthdayArray()[i] %>
							</td>
							<td>
								<img onClick="saveForm('<%=data.getIdArray()[i]%>')" alt="<bean:message  bundle="comm.commLocale" key="comm.jsp.commom.detail"/>" src="include/images/cmdView_s.jpg" border="0" style="cursor: hand; vertical-align: middle;" />
							</td>
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
						<input type="hidden" id="pageId" name="pageId" value="page_2828810b3a6785e1013a6785e1ba0000"/>
						<input type="hidden" id="reHref" name="reHref" value="<%=request.getContextPath()%>/empi/empicustomcompare.do?verbId=query"/>
						<%@ include file="/include/changepagesize.jsp" %>
						<input id="_total" name="totalPage" type="hidden" value="<%=totalPage%>"/>
						第
						<%=curPage%>
						页/共
						<%=totalPage%>
						页&nbsp;共
						<%=totalNum%>
						条记录&nbsp;|&nbsp;
						<%
						if (curPage > 1) {
						%>
						<a href="javascript:goPage('0')"><img src="include/images/shouye.gif" align="middle" border="0" /></a>&nbsp;&nbsp;<a href="javascript:goPage('<%=curPage - 1%>')"><img src="include/images/shang.gif" align="middle" border="0" /></a>&nbsp;
						<%
								} else {
								out
								.println("<img src='include/images/shouye_s.gif' align='middle'  border='0' />&nbsp;&nbsp;<img src='include/images/shang_s.gif' align='middle'  border='0' />&nbsp;");
							}
							if (curPage < totalPage) {
						%>
						<a href="javascript:goPage('<%=curPage + 1%>')"><img src="include/images/xia.gif" align="middle" border="0" /></a>&nbsp;&nbsp;<a href="javascript:goPage('<%=totalPage%>')"><img src="include/images/mo.gif" align="middle" border="0" /></a>
						<%
								} else {
								out
								.println("<img src='include/images/xia_x.gif' align='middle' border='0' />&nbsp;&nbsp;<img src='include/images/mo_m.gif' align='middle'  border='0' />");
							}
						%>
						| &nbsp;到第
						<input id="_tp" name="page" type="text" value="<%=curPage%>"
							size="2" class="txt"/>
						页&nbsp;
						<img style="cursor: hand;" src="include/images/go.jpg" width="18"
							border="0" onClick="goPage2()" />

					</td>
				</tr>
			</table>
		</form>
		<script>	
		 Calendar.setup({
			  inputField    : "dateOfBirth",
			  button        : "dateOfBirthButton",
			  ifFormat      : "%Y-%m-%d",
			  showsTime     :  false
			});
		 
		</script>
		<script language="javascript"
			src="include/javascript/interval_row_color.js"></script>
</html>
