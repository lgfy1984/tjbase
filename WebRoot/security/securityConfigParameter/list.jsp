<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@page import="com.tianjian.util.comm.PageBean"%>
<jsp:useBean id="data" scope="request" class="com.tianjian.security.struts.form.SecurityConfigParameterForm" />
<jsp:useBean id="pb" scope="request" class="com.tianjian.util.comm.PageBean" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<%if(request.getServerPort() == 80) {%>
		<base
			href="http://<%=request.getServerName()%><%=request.getContextPath()%>/">
		<%} else {%>
		<base
			href="http://<%=request.getServerName()%>:<%=request.getServerPort()%><%=request.getContextPath()%>/">
		<%}%>
		<title><bean:message key="security.jsp.securityConfigParamClass1.list.title" bundle="security"/></title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8">	
		<script language="javascript" src="<bean:message key="comm.js.CommMessage.path" bundle="security" />"></script>
		<script language="javascript" src="include/javascript/TJMessage.js"></script>
		<script language="javascript" src="include/javascript/eventOnKeyPress.js"></script>
		<script language="javascript" src="security/include/javascript/gettext_parameter.js"></script>
		<script type="text/javascript" src="security/include/javascript/jianbian.js"></script>
		<script language="javascript" src="include/javascript/validator.js" defer="defer"></script>
		<link rel="stylesheet" href="<%=request.getContextPath()%>/include/css/open.css" />
		<link type="text/css" rev="stylesheet" rel="stylesheet" href="include/css/form.css" />
		
		<script language="javascript">
			function submitQueryForm() { 
			 if(!Validator.Validate(document.forms.form,1)){
			 	return false;
			 }
   			  
			  document.form.page.value = 1; 
			  document.form.orderNo.value = ""; 
			  document.form.asc.value = ""; 
			  document.form.method = "POST";
			  document.form.verbId.value = "query";
			  document.form.submit();
			}
			
			function submitInit() {
				document.form.verbId.value = "refreshInit";
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
			function cmdAdd1() {
			    document.form.verbId.value = "addInit";    
			    document.form.submit();
			}
			//查看详细
			function cmdView(id) {
			    document.form.hiddenId.value = id;  
			    document.form.verbId.value = "detail";    
			    document.form.submit();
			}
			
			//修改
			function cmdEdit(id) {
			    document.form.hiddenId.value = id;  
			    document.form.verbId.value = "updateInit";    
			    document.form.submit();     
			}
	
			//删除
			function cmdDel(id) {     
				if (confirmMessage("<bean:message key='security.jsp.commom.delete' bundle='security'/>")){     
			    	document.form.hiddenId.value = id;
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
					alert('<bean:message key="security.jsp.commom.warn" bundle="security"/>!');
			      return;
			    }
			    if (_tp.value<=0){
					alert('<bean:message key="security.jsp.commom.warn1" bundle="security"/>!');
					return;
			    }
			    if(parseInt(_tp.value)>parseInt(_total.value)){
				  alert('<bean:message key="security.jsp.commom.warn2" bundle="security"/>!');
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
			function showMessage(message){
					if(message != ''&& message != null){
						alert(message);
						return;
					}
				}
			/**
			* 增加行
			*/
			
			function addRow(){
			  var index = document.getElementById("interval_row_id").getElementsByTagName("TR").length;
			  var item=      "<tr>"+
			                        
									"<td style=\"padding-right:30px;\">"+
										"<span>*</span><input type=\"text\" id=\"displayInputId_"+index+"\" name=\"list["+index+"].className\"  value=\"\" readonly=\"readonly\" />"+
										"<input type=\"hidden\" id=\"hiddenInputId_"+index+"\" value=\"\" name=\"list["+index+"].classCode\" />"+
										"<img src=\"security/include/images/select.gif\" style=\"cursor: pointer;position:absolute;\" onclick=\"add('<%=request.getContextPath()%>/security/securityConfigParameter.do?verbId=system','displayInputId_"+index+"','hiddenInputId_"+index+"')\" />"+
									"</td>"+
									"<td>"+
										"<span>*</span><input type=\"text\" name=\"list["+index+"].itemName\" id=\"list["+index+"].itemName\"  value=\"\" />"+
									"</td>"+
									"<td>"+
										"<span>*</span><input type=\"text\" name=\"list["+index+"].itemValue\" id=\"list["+index+"].itemValue\"   value=\"\" />"+
									"</td>"+
									"<td>"+
										"<input type=\"text\" name=\"list["+index+"].initValue\" id=\"list["+index+"].initValue\"   value=\"\" />"+
									"</td>"+
									"<td>"+
										"<span>*</span><input type=\"text\" name=\"list["+index+"].usageDescription\" style=\"width:95%\" id=\"list["+index+"].usageDescription\"   value=\"\"  />"+
										"<input type=\"hidden\" name=\"list["+index+"].id\" id=\"list["+index+"]id\" style=\"width: 280px\"  value=\"\" />"+
									"</td>"+
									"<td>"+
										"<img onClick=\"deleRow(this)\" alt=\"删除\" src=\"include/images/cmdDel_s.jpg\" border=\"0\" style=\"cursor: hand; vertical-align: middle;\" />"+
									"</td>"+
			                 "</tr>";
			 InsertRow(table1,item);
			 //index ++;
			}
			function InsertRow(table,rowHtml)
			{ 
			    var o=document.createElement("div"),ol;
			    o.innerHTML="<table>"+rowHtml+"</table>" 
			    ol=o.childNodes[0].tBodies[0].rows 
			    while(ol.length>0){ 
			        table.tBodies[0].appendChild(ol[0]) 
			    } 
			}
 			 function deleRow(obj){
				   var index = obj.parentNode.parentNode.rowIndex;
				   var tableObj = document.getElementById("table1");
				   tableObj.deleteRow(index);
				}
				
			//添加
			function cmdAdd() {
				 var index = document.getElementById("interval_row_id").getElementsByTagName("TR").length;
				for(var i = 0;i < index; i++){
					var displayInputId = document.getElementById("displayInputId_"+i).value;
					if(displayInputId == ""){
						alert("请选择程序包名称！");
						return false;
					}
					var itemName = document.getElementById("list["+ i +"].itemName").value;
					if(itemName == ""){
						alert("请填写配置参数名称！");
						return false;
					}
					var itemValue = document.getElementById("list["+i+"].itemValue").value;
					if(itemValue == ""){
						alert("请填写配置参数值！");
						return false;
					}
				}
			   	if (confirmMessage("你确定要保存该条记录？")){     
				    document.form.verbId.value = "addList";    
			    	document.form.submit();
			    }   
			}
</script>
	</head>
	<body onload="showMessage('<%=data.getMessage() %>')">
		<!-- Form中的action项的值必须设置-->
		<form name="form" method="post" 
			action="<%=request.getContextPath()%>/security/securityConfigParameter.do">
			<!-- Head line -->
			<input type="hidden" name="verbId" value="<%=data.getVerbId()%>" />
			<input type="hidden" name="orderNo" value="<%=data.getOrderNo()%>" />
			<input type="hidden" name="asc" value="<%=data.getAsc()%>" />
			<input type="hidden" id="hiddenId" name="hiddenId" value="<%=data.getId()%>" />
			<!--查询条件-->
			<table border="0" cellpadding="0" cellspacing="0" class="tblSearch" align="center">
				<tr>
					<td class="tblTitle" colspan="4">
						<bean:message key="security.jsp.securityConfigParamClass1.list.item" bundle="security"/>
					</td>
				</tr>
				<tr>
					<td>
						<!-- <span>*</span>工程名称:
						<input type="text" class="input" id="displayInputId_p1" 
							name="projectName"
							value="${data.projectName}" readonly="readonly" 
							onkeypress="eventOnKeyPress('className')"
							dataType="Require" msg="请选择一个工程!"
							 />
						<input type="hidden" id="hiddenInputId_p1"
							 name="projectCode" />
						<img src="security/include/images/select.gif"
							style="cursor: pointer;"
							onclick="add('<%=request.getContextPath()%>/security/securityConfigParamProject.do?verbId=getProject','displayInputId_p1','hiddenInputId_p1')" />
						 -->
						
						<bean:message key="security.jsp.securityConfigParamClass1.commom2" bundle="security"/>:
						<input type="text" id="displayInputId_c1" name="className"  value="${data.className}" onkeypress="eventOnKeyPress('itemName')" readonly="readonly" />
								<input type="hidden" id="hiddenInputId_c1" name="classCode"  value=""  />
								<img src="security/include/images/select.gif" style="cursor: pointer;" 
								onclick="add('<%=request.getContextPath()%>/security/securityConfigParameter.do?verbId=system','displayInputId_c1','hiddenInputId_c1')"  />
						
						
						
						<bean:message key="security.jsp.securityConfigParamClass1.commom7" bundle="security"/>:
						<input name="itemName" type="text"  onkeypress="eventOnKeyPress('btnsubmit')" value="${data.itemName}" />
						
						<input type="button" class="btnSave"  name="btnsubmit" value="<bean:message key="security.jsp.securityConfigParamClass.query.button1" bundle="security"/>" onClick="submitQueryForm();" />
						<input type="button" class="btnSave"  name="btnrefresh" value="<bean:message key="security.jsp.securityConfigParamClass1.refresh" bundle="security"/>" onClick="submitInit();" />
					</td>
				</tr>
			</table>
			<!--列表标题-->
			<div id="dvh1">
			<table  border="0" cellpadding="0" cellspacing="0" class="tblSearchList" align="center" id="table1">
					<caption style="text-align:left;">
						<img src="<%=request.getContextPath()%>/comm/include/images/comm_list_nav_red.jpg" width="20" height="20" align="absmiddle" /> <span style="font-weight:bold; color:#333;"><bean:message key="security.jsp.commom.item1" bundle="security"/></span>
						<span class="titleBtn">
							<input type="button" value="添加" name="appendRow" onclick="addRow();"/>
							<input type="button" value="保存" name="保存" onclick="cmdAdd();"/>
						</span>
			   		</caption>
					<thead>
				    	<tr class="lstName">
				            
				            <th width="10%" height="26"><bean:message key="security.jsp.securityConfigParamClass1.commom2" bundle="security"/><div><img src="include/images/cmdOrderByAsc.gif" alt="Ascender" vspace="7" onclick="commandOrderBy('2', '0')" /><img border="0" onclick="commandOrderBy('2', '1')" alt="Descender" src="include/images/cmdOrderByDesc.gif" /></div></th>
				            <th width="10%" height="26"><bean:message key="security.jsp.securityConfigParamClass1.commom7" bundle="security"/><div><img src="include/images/cmdOrderByAsc.gif" alt="Ascender" vspace="7" onclick="commandOrderBy('3', '0')" /><img border="0" onclick="commandOrderBy('3', '1')" alt="Descender" src="include/images/cmdOrderByDesc.gif" /></div></th>
				            <th width="10%" height="26"><bean:message key="security.jsp.securityConfigParamClass1.commom8" bundle="security"/><div><img src="include/images/cmdOrderByAsc.gif" alt="Ascender" vspace="7" onclick="commandOrderBy('4', '0')" /><img border="0" onclick="commandOrderBy('4', '1')" alt="Descender" src="include/images/cmdOrderByDesc.gif" /></div></th>
				            <th width="10%" height="26">初始值<div><img src="include/images/cmdOrderByAsc.gif" alt="Ascender" vspace="7" onclick="commandOrderBy('1', '0')" /><img border="0" onclick="commandOrderBy('1', '1')" alt="Descender" src="include/images/cmdOrderByDesc.gif" /></div></th>
				            <th width="30%" height="26">用法<div><img src="include/images/cmdOrderByAsc.gif" alt="Ascender" vspace="7" onclick="commandOrderBy('1', '0')" /><img border="0" onclick="commandOrderBy('1', '1')" alt="Descender" src="include/images/cmdOrderByDesc.gif" /></div></th>
				            <th width="5%" height="26">&nbsp;<bean:message key="security.jsp.commom.button8" bundle="security"/></th>
				        	
				        </tr>
					</thead>
					<tbody id="interval_row_id">
						<%if (data.getIdList() != null&& data.getIdList().length > 0) {
							for (int i = 0; i < data.getIdList().length; i++) {
						%>
						<tr >
							<td style="padding-right:30px;">
								<span>*</span><input type="text" id="displayInputId_<%=i%>" name="list[<%=i%>].className"  value="<%=data.getClassNameList()[i]%>" readonly="readonly" />
								<input type="hidden" id="hiddenInputId_<%=i%>" name="list[<%=i%>].classCode"  value="<%=data.getClassCodeList()[i] %>"  />
								<img src="security/include/images/select.gif" style="cursor: pointer;position:absolute;" 
								onclick="add('<%=request.getContextPath()%>/security/securityConfigParameter.do?verbId=system','displayInputId_<%=i%>','hiddenInputId_<%=i%>')"  />
							</td>
							<td>
								<input type="hidden" name="list[<%=i%>].id" id="list[<%=i %>].id"   value="<%=data.getIdList()[i]%>" />
								<span>*</span><input type="text" name="list[<%=i %>].itemName" id="list[<%=i %>].itemName"   value="<%=data.getItemNameList()[i]%>" />
							</td>
							<td>
								<span>*</span><input type="text" name="list[<%=i %>].itemValue" id="list[<%=i %>].itemValue"   value="<%=data.getItemValueList()[i]%>" />
							</td>
							<td>
								<input type="text" name="list[<%=i%>].initValue" id="list[<%=i %>].initValue"   value="<%=data.getInitValueList()[i]%>" />
							</td>
							<td>
								<span>*</span><input type="text" style="width:95%" name="list[<%=i%>].usageDescription" id="list[<%=i%>].usageDescription"  value="<%=data.getUsageDescriptionList()[i]%>" />
								
							</td>
							
							<td>
								<img onClick="cmdDel('<%=data.getIdList()[i] %>')"
									alt="<bean:message key="security.jsp.commom.button8" bundle="security"/>" src="include/images/cmdDel_s.jpg" border="0"
									style="cursor: hand; vertical-align: middle;" />
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
						<input type="hidden" id="pageId" name="pageId" value="page_282881f53464511d013464511d870000" />
						<input type="hidden" id="reHref" name="reHref" value="<%=request.getContextPath()%>/security/securityConfigParamClass1.do?verbId=query" />
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
	</body>
	<script language="javascript" src="include/javascript/interval_row_color.js"></script>
</html>
