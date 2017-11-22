<%@page contentType="text/html; charset=utf-8"%>
<%@page import="com.tianjian.util.*"%>
<%@page import="com.tianjian.comm.bean.CommConfigUnitgrade"%>
<%@page import="com.tianjian.comm.bean.CommConfigUnittype"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<jsp:useBean id="data" scope="request" type="com.tianjian.hsp.struts.form.HspConfigBaseinfoForm" />
<jsp:useBean id="pb" scope="request" class="com.tianjian.util.comm.PageBean" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%if(request.getServerPort()==80){%>
		<base href="http://<%=request.getServerName()%><%=request.getContextPath()%>/" />
		<%}else{%>
		<base href="http://<%=request.getServerName()%>:<%=request.getServerPort()%><%=request.getContextPath()%>/" />
		<%}%>
		<title><bean:message  bundle="hsp.hspLocale" key="hsp.jsp.common.itemInfoSafeguard"/></title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		
		<link type="text/css" rev="stylesheet" rel="stylesheet" href="include/css/form.css" />
		<link rel="stylesheet" href="<%=request.getContextPath()%>/hsp/include/css/open.css" />
		<link rel="stylesheet" href="<%=request.getContextPath()%>/include/css/searchsuggest.css" />
		<script src="<%=request.getContextPath()%>/include/javascript/searchsuggest.js"></script>
		<link rel="stylesheet" href="<%=request.getContextPath()%>/include/css/searchwindow.css" />
		
		<script language="javascript" src="<%=request.getContextPath()%><bean:message  key="hsp.js.commCommMessage.path"  bundle="hsp.hspLocale"/>"></script>
		<script language="javascript">	
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
			//添加
			function cmdAdd() {
			    document.form.verbId.value = "addInit";    
			    document.form.submit();
			}
			//删除时候用
			function deleteForm(id){ 
				var len = 0;
				if(id!=null && id!=""){
					   	len ++; 
				}
				if(len > 0){
					 if (!confirm("<bean:message  bundle="hsp.hspLocale" key="hsp.jsp.common.warn17"/>")) return false; 
					 document.form.idHidden.value = id;
					 document.form.verbId.value = "delete";
					 document.form.submit();
				}else{
					alert("<bean:message  bundle="hsp.hspLocale" key="hsp.jsp.common.warn4"/>!");
					return false;
				}
			}
			//查询时候用
			function queryForm(num) {
			    document.form.page.value = 1; 
			    document.form.orderNo.value = ""; 
			    document.form.asc.value = ""; 
			    document.form.method = "POST";
			    document.form.verbId.value = "query";
			    document.form.submit();
			}
			//查看详细时候用
			function viewForm(id){
				var array =  id;
				var idHidden =id;
				var len = 0;
				if(array!=null && array!=""){ 
							len ++;  
				}
				if(len == 1){
					document.form.idHidden.value = id;
					document.form.verbId.value = "detail";
					document.form.submit();
				}
			}
			//更新准备时候用
			function updateInitForm(id){
				var array = id
				var idHidden =id;
				var len = 0;
				if(id!=null && id!=""){ 
							len ++; 
				}
				if(len == 1){
					document.form.idHidden.value = id;
					document.form.verbId.value = "updateInit";
					document.form.submit();
				}else if(len <= 0){
					alert("<bean:message  bundle="hsp.hspLocale" key="hsp.jsp.common.warn4"/>!");
					return false;
				}else if(len > 1){
					alert("<bean:message  bundle="hsp.hspLocale" key="hsp.jsp.common.warn6"/>!");
					return false;
				}
			}	
			//注销
			function cmdCanceled(id) {  /**
			    document.form.idHidden.value = id;  
			    document.form.verbId.value = "addLogOutInit";    
			    document.form.submit();   */
			    var array = id;
				var idHidden = id;
				var len = 0;
				if(id!=null && id!=""){ 
							len ++; 
				}
				if(len == 1){
					document.form.id.value = idHidden;
					document.form.verbId.value = "addLogOutInit";
					document.form.submit();
				}else if(len <= 0){
					alert("<bean:message  bundle="hsp.hspLocale" key="hsp.jsp.common.warn4"/>!");
					return false;
				}else if(len > 1){
					alert("<bean:message  bundle="hsp.hspLocale" key="hsp.jsp.common.warn6"/>!");
					return false;
				}
			}
			//------------------		
			function commandOrderBy(order, asc) { 
			  	document.form.orderNo.value = order; 
			  	document.form.asc.value = asc; 
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
			  	var _tp=document.getElementById('_tp');
  var _total=document.getElementById('_total');
			    	if (!isMadeOf(_tp.value,'1234567890')) {
			      		alert("<bean:message  bundle="hsp.hspLocale" key="hsp.jsp.common.wrongInteger"/>!");
			      		return;
			    	}
			    	if (_tp.value<=0){
			    		alert("<bean:message  bundle="hsp.hspLocale" key="hsp.jsp.common.pageMustBigThanZero"/>!");
						return;
			    	}
			    	if(parseInt(_tp.value)>parseInt(_total.value)){
			      		alert("<bean:message  bundle="hsp.hspLocale" key="hsp.jsp.common.cannotBigThanTotal"/>!");
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
			function newXMLHttpRequest() {
				var xmlreq = false;
				if (window.XMLHttpRequest) {
					xmlreq = new XMLHttpRequest();		
				} else if (window.ActiveXObject) {
			    	try {
		      			xmlreq = new ActiveXObject("Msxml2.XMLHTTP");
					} catch (e1) {
						try {
		        			xmlreq = new ActiveXObject("Microsoft.XMLHTTP");
						} catch (e2) {
							xmlreq = false;
			    		}
		    		}
				}	
				return xmlreq;
			}		
			function getReadyStateHandler(req, responseXmlHandler) {
				return function () {
					if (req.readyState == 4) {
			      		if (req.status == 200) {
			      			//alert(req.responseText); 
						    responseXmlHandler(req.responseXML);
						} else {
						    alert("HTTP error: " + req.status);
			      		}
			    	}
			  	}
			}
			function displays(arg){
				var divdisplay = document.getElementById("hightsear");
				if(divdisplay.style.display == "none"){
					divdisplay.style.display = "block";
					arg.innerHTML = '<img src="<%=request.getContextPath()%>/stat/include/images/Cnext.gif" />';
				}else{
					divdisplay.style.display = "none";
					arg.innerHTML = '<img src="<%=request.getContextPath()%>/stat/include/images/Cprevious.gif" />';
				}
			}	
		  	function huiche(){
				if(event.keyCode==13){
					event.keyCode=9
				}			
			}
		</script>
	
		<script language="javascript" src="<%=request.getContextPath()%><bean:message  bundle="hsp.hspLocale" key="hsp.js.gettext_staff.path"/>"></script>
		
		<script type="text/javascript" src="<%=request.getContextPath()%>/include/javascript/searchwindow.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/include/javascript/jianbian.js"></script>
		<script language="javascript" src="<%=request.getContextPath()%><bean:message key="hsp.js.includevalidator.path"  bundle="hsp.hspLocale"/>"></script>
		
		
	</head>
    

	<body onload="showCommMessage('','${message }','1')">
		<!-- Form中的action项的值必须设置-->
		<form name="form" method="post" action="<%=request.getContextPath()%>/hsp/hspLogoutRecord.do">
			<!-- Head line -->
			<input type="hidden" name="verbId" value="<%=data.getVerbId()%>" />
			<input type="hidden" name="orderNo" value="<%=data.getOrderNo()%>" />
			<input type="hidden" name="asc" value="<%=data.getAsc()%>" />
			<input type="hidden" name="idHidden" value="<%=data.getIdHidden()%>" />
			<!--查询条件-->
			<table  border="0" cellpadding="0" cellspacing="0" class="tblSearch" align="center">
				<tr>
					<td class="tblTitle" colspan="4">
						<bean:message  bundle="hsp.hspLocale" key="hsp.jsp.common.selectCondition"/>
					</td>
				</tr>
				<tr>
					<td>
						<bean:message  bundle="hsp.hspLocale" key="hsp.jsp.common.mingch1"/>：
						<input type="text" id="itemName" name="itemName" 
						onkeyup="GiveOptions(event, '<%=request.getContextPath()%>/searchSuggest.do', 'getHspName_00000000004', 'id')"
						onkeydown="huanhang(event)" value="<%=data.getItemName()%>" />
						<input type="hidden" name="id" id="id" value="<%=data.getItemCode()%>" />
						<span id="spanOutput" class="spanTextDropdown" style="display: none;"></span> 
						
						&nbsp;&nbsp;<bean:message  bundle="hsp.hspLocale" key="hsp.jsp.hspconfigbaseinfolist.pinyinInputCode"/>：
						<input name="inputCode" id="inputCode" type="text" style="width:130px;"  value="<%=data.getInputCode()%>" onkeydown="if(event.keyCode==13){event.keyCode=9;document.getElementById('queryImg').className='queryImg_down'}" />
						<input type="checkbox" name="canceledFlag" value="1" <%="1".equals(request.getAttribute("canceledFlag"))?"checked":""%> />
					    <bean:message  bundle="comm.comIHE" key="comm.jsp.segment8"/>
						<input type="button" class="btnSave" id="queryImg" name="<bean:message  bundle="hsp.hspLocale" key="hsp.jsp.common.search2"/>" value="<bean:message  bundle="hsp.hspLocale" key="hsp.jsp.common.search1"/>" onclick="queryForm(0);" />
						
					</td>
				</tr>
			</table>
			<!--列表标题-->
			<div id="dvh1">
			<table  border="0" cellpadding="0" cellspacing="0" class="tblSearchList" align="center">
				<thead>
			    	<tr class="lstName">
			            <th width="15%" height="26"><bean:message  bundle="hsp.hspLocale" key="hsp.jsp.common.itemCode"/>
			            <div><img src="include/images/cmdOrderByAsc.gif" alt="Ascender" vspace="7" onclick="commandOrderBy('1', '0')" /><img border="0" onclick="commandOrderBy('1', '1')" alt="Descender" src="include/images/cmdOrderByDesc.gif" /></div></th>
			            <th width="15%" height="26"><bean:message  bundle="hsp.hspLocale" key="hsp.jsp.common.itemName"/>
			            <div><img src="include/images/cmdOrderByAsc.gif" alt="Ascender" vspace="7" onclick="commandOrderBy('2', '0')" /><img border="0" onclick="commandOrderBy('2', '1')" alt="Descender" src="include/images/cmdOrderByDesc.gif" /></div></th>
			            <th width="15%" height="26">地址
			            <div><img src="include/images/cmdOrderByAsc.gif" alt="Ascender" vspace="7" onclick="commandOrderBy('6', '0')" /><img border="0" onclick="commandOrderBy('6', '1')" alt="Descender" src="include/images/cmdOrderByDesc.gif" /></div></th>
			            <!-- 
			            <th width="5%" height="26"><bean:message  bundle="hsp.hspLocale" key="hsp.jsp.hspconfigbaseinfodetail.contactPerson"/></th>
			            <th width="4%" height="26"><bean:message  bundle="hsp.hspLocale" key="hsp.jsp.hspconfigbaseinfodetail.contactPhone"/>	</th>
			             -->
			            <th width="4%" height="26"><bean:message  bundle="hsp.hspLocale" key="hsp.jsp.common.seqNo"/>
			            <div><img src="include/images/cmdOrderByAsc.gif" alt="Ascender" vspace="7" onclick="commandOrderBy('5', '0')" /><img border="0" onclick="commandOrderBy('5', '1')" alt="Descender" src="include/images/cmdOrderByDesc.gif" /></div></th>
			             <th width="3%" height="26"><bean:message  bundle="comm.commLocale" key="comm.jsp.commom.search"/></th>
			             <% String flag = (String)request.getAttribute("canceledFlag");
						   if(flag!=null&&flag.equals("1")){}
						   else{
						%>
			            <th width="3%" height="26"><bean:message  bundle="comm.commLocale" key="comm.jsp.commom.canceled"/></th>
			            <%} %>
			        </tr>
				</thead>
				<tbody id="interval_row_id">
					<%
						if(data.getIdList()!=null && data.getIdList().length>0){
							for(int i=0; i<data.getIdList().length; i++){
					%>
					<tr class="list_neirong">
						<td class="td_shenglue leftPadding" height="25" style="text-align:left;padding-left:10px;" ><%=data.getItemCodeList()[i]%></td>
						<td class="td_shenglue leftPadding" style="text-align:left;padding-left:10px;"  ><%=data.getItemNameList()[i]%></td>
						<td class="td_shenglue leftPadding" style="text-align:left;padding-left:10px;"  ><%=data.getAddressList()[i]%></td>
						<!-- 
						<td class="td_shenglue leftPadding"><%=data.getPhoneList()[i]%></td>
						<td class="td_shenglue leftPadding"><%=data.getContactPersonNameList()[i]%></td>
						
						 -->
						<td class="td_shenglue "><%=data.getSeqNoList()[i]%></td>
						<td class="td_shenglue ">
							<img src="<%=request.getContextPath()%>/hsp/include/images/cmdView_s.jpg" style="cursor: hand; vertical-align: middle;" title="<bean:message  bundle="hsp.hspLocale" key="hsp.jsp.common.select"/>" onclick="viewForm('<%=data.getIdList()[i]%>')" />
						</td>
						<% if(flag!=null&&flag.equals("1")){}
						   else{
						%>
						<td>
							<img onclick="cmdCanceled('<%=data.getIdList()[i]%>')" alt="<bean:message  bundle="comm.commLocale" key="comm.jsp.commom.canceled"/>" src="include/images/cmdDel_s.jpg" border="0" style="cursor: hand; vertical-align: middle;" />
						</td>
						<%} %>
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
							<input type="hidden" id="pageId" name="pageId" value="page_282881f53463cbef013463cbef2c0000" />
						<input type="hidden" id="reHref" name="reHref" value="<%=request.getContextPath()%>/hsp/hspConfigBaseinfo.do?verbId=queryDetail" />
						<%@ include file="/include/changepagesize.jsp" %>
						
						
						
						<input id="_total" name="totalPage" type="hidden"
							value="<%=totalPage%>" />
						<bean:message  bundle="hsp.hspLocale" key="hsp.jsp.common.D"/>
						<%=curPage%>
						<bean:message  bundle="hsp.hspLocale" key="hsp.jsp.common.pageTotal1"/>
						<%=totalPage%>
						<bean:message  bundle="hsp.hspLocale" key="hsp.jsp.common.pageTotal"/>
						<%=totalNum%>
						<bean:message  bundle="hsp.hspLocale" key="hsp.jsp.common.record"/>
						<%
						if (curPage > 1) {
						%>
						<a href="javascript:goPage('0')">
							<img src="include/images/shouye.gif" align="middle" border="0" />
						</a>&nbsp;
						<a href="javascript:goPage('<%=curPage - 1%>')">
							<img src="include/images/shang.gif" align="middle" border="0" />
						</a>&nbsp;
						<%
								} else {
								out
								.println("<img src='include/images/shouye_s.gif' align='middle'  border='0px' />&nbsp;&nbsp;<img src='include/images/shang_s.gif' align='middle'  border='0px' />&nbsp;");
							}
							if (curPage < totalPage) {
						%>
						<a href="javascript:goPage('<%=curPage + 1%>')"><img src="include/images/xia.gif" align="middle" border="0" /></a>&nbsp;
						<a href="javascript:goPage('<%=totalPage%>')"><img src="include/images/mo.gif" align="middle" border="0" /></a>&nbsp;
						<%
								} else {
								out
								.println("<img src='include/images/xia_x.gif' align='middle' border='0px' />&nbsp;&nbsp;<img src='include/images/mo_m.gif' align='middle'  border='0px' />&nbsp;");
							}
						%>
						<bean:message  bundle="hsp.hspLocale" key="hsp.jsp.common.text1"/>
						<input id="_tp" name="page" type="text" value="<%=curPage%>" size="2" class="txt" />
						<bean:message  bundle="hsp.hspLocale" key="hsp.jsp.common.page"/>&nbsp;
						<img style="cursor: hand;" src="include/images/go.jpg" width="18" border="0" onclick="goPage2()" />
					</td>
				</tr><!-- 
				<tr>
					<td colspan="8" class="btnSave" align="center" bgcolor="#ffffff" height="35px">
						<input type="button" id="exportImg" name="<bean:message  bundle="hsp.hspLocale" key="hsp.jsp.common.import1"/>" value="<bean:message  bundle="hsp.hspLocale" key="hsp.jsp.common.import"/>" onclick="cmdExcelImport()"  />
			 			<input type="button" id="exportImg" name="<bean:message  bundle="hsp.hspLocale" key="hsp.jsp.common.export1"/>" value="<bean:message  bundle="hsp.hspLocale" key="hsp.jsp.common.export"/>" onclick="cmdExcelExport(0)"  />
					</td>
				</tr> -->
			</table>
		</form>
		<script language="javascript" src="include/javascript/interval_row_color.js"></script>
	</body>
</html>