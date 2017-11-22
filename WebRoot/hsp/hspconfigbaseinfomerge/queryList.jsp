<%@page contentType="text/html; charset=utf-8"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<jsp:useBean id="data" scope="request" class="com.tianjian.hsp.struts.form.HspConfigBaseinfoMergeForm" />
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
			    var ChkEls=document.getElementsByName("id");
			    var newId = document.form.idHidden.value;
				 var checkedIds="";
				 for(var i=0;i<ChkEls.length;i++){
				  if(ChkEls.item(i).type != "checkbox")continue;
				  var oEl = ChkEls.item(i);
				  if(oEl.checked) {
				   checkedIds = checkedIds + (oEl.value+":"+newId+ ",");
				  }
				 }
				 document.form.checkValue.value = checkedIds;
				 if ( checkedIds == "" ){  
					 alert("请选择记录"); 
					 document.getElementById("btnSaveForm").disabled=""; 
					 return false;
				 }
				 
			  document.form.orderNo.value = ""; 
			  document.form.asc.value = ""; 
			  document.form.method = "POST";
			  document.form.verbId.value = "compareAct";
			  document.form.submit();
			}

			function goPage(page) {  
			   document.form.page.value = page;
			   document.form.verbId.value = "compareQuery";    
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
			  document.form.verbId.value = "compareQuery";    
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
			var checkflag = "false";
			function check(field) {
			    if(field.length == undefined){
				  if(checkflag=="false"){
				    field.checked = true;
				    checkflag = "true";
				    return
				  }else{
				    field.checked = false;
				    checkflag = "false";
				    return
				  }
				}
				if (checkflag == "false") {
					for (i = 0; i < field.length; i++) {
						field[i].checked = true;
					}
						checkflag = "true";
						return "false"; 
					}else {
						for (i = 0; i < field.length; i++) {
							field[i].checked = false; }
							checkflag = "false";
							return "true"; 
						}
			}
</script>
		<link type="text/css" rev="stylesheet" rel="stylesheet" href="include/css/form.css" />
	</head>
	<body onLoad="showCommMessage('','<%=data.getMessage() %>','1')">
		<!-- Form中的action项的值必须设置-->
		<form name="form" method="post" action="<%=request.getContextPath()%>/hsp/hspConfigBaseinfoMerge.do">
			<!-- Head line -->
			<input type="hidden" name="queryFlag" value="queryFlag" />
			<input type="hidden" name="verbId" value="<%=data.getVerbId()%>" />
			<input type="hidden" name="orderNo" value="<%=data.getOrderNo()%>" />
			<input type="hidden" name="asc" value="<%=data.getAsc()%>" />
			<input type="hidden" name="idHidden" value="<%=data.getIdHidden()%>" />
			<input type="hidden" name="checkValue" value="<%=data.getCheckValue()%>" />
			<!--查询条件-->
			<table  border="0" cellpadding="0" cellspacing="0" class="tblSearch" align="center">
				<tr>
					<td class="tblTitle" colspan="4">
						<span>※</span>对照信息<span>※</span>
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
			    		<th width="5%" height="26">全选<input type=checkbox value="全选" onClick="this.value=check(this.form.id)" /></th>
			    		<th width="5%" height="26">机构代码</th>
			    		<th width="5%" height="26">机构名称</th>
			    		<th width="5%" height="26">机构地址</th>
			            <th width="5%" height="26">序号</th>
			            <th width="5%" height="26">输入码</th>
			       </tr>
				</thead>
			
			<!--列表内容-->
				<tbody id="interval_row_id">
					<%if (data.getIdArray() != null && data.getIdArray().length > 0) {
						for (int i = 0; i < data.getIdArray().length; i++) {
					%>
						<tr>
							<td style="padding-left:28px;">
							  <input type="checkbox" name="id" value="<%=data.getIdArray()[i] %>"  />
						    </td>
							<td>
								<%=data.getItemCodeArray()[i] %>
							</td>
							<td style="text-align:left;padding-left:10px;">
								<%=data.getItemNameArray()[i] %>
							</td>
							<td>
								<%=data.getItemAddressArray()[i] %>
							</td>
							<td style="text-align:left;padding-left:10px;">
								<%=data.getSeqNoArray()[i]%>
							</td>
							<td style="text-align:left;padding-left:10px;">
								<%=data.getInputCodeArray()[i]%>
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
			
			<div class="btnSave">
				<input type="button" name="btnSaveForm" value="合&nbsp&nbsp并" onClick="this.disabled=true;submitQueryForm();" />
				<input type="button" name="btnBack" value="重&nbsp&nbsp置" onClick="goback('<%=request.getContextPath()%>/')" />
			</div>
		</form>
		<script language="javascript"
			src="include/javascript/interval_row_color.js"></script>
</html>
