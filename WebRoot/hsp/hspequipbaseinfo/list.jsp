<%@page import="com.tianjian.comm.bean.CommEquipState"%>
<%@page import="com.tianjian.util.Converter"%>
<%@page import="java.util.Iterator"%>
<%@page contentType="text/html; charset=utf-8"%>
<%@page import="com.tianjian.util.comm.PageBean"%>
<%@page import="com.tianjian.hsp.bean.HspDeptBaseinfo"%>
<%@page import="com.tianjian.comm.bean.CommEquipUseage"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@page import="java.util.List"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="org.apache.commons.lang.StringUtils"%>
<jsp:useBean id="data" scope="request" class="com.tianjian.hsp.struts.form.HspEquipBaseinfoForm" />
<jsp:useBean id="pb" scope="request"   class="com.tianjian.util.comm.PageBean" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%
		if (request.getServerPort() == 80) {
		%>
		<base
			href="http://<%=request.getServerName()%><%=request.getContextPath()%>/" />
		<%
		} else {
		%>
		<base
			href="http://<%=request.getServerName()%>:<%=request.getServerPort()%><%=request.getContextPath()%>/" />
		<%
		}
		%>
		<title>医疗设备资源维护</title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
        <script language="javascript" src="<bean:message key="comm.js.CommMessage.path" bundle="conf.comm.comm"/>"></script>
		<link rel="stylesheet" rev="stylesheet" href="<%=request.getContextPath()%>/include/css/form.css" />
		
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/include/javascript/searchwindow.js"></script>
		<link rel="stylesheet"
			href="<%=request.getContextPath()%>/include/css/open.css" />

		<link rel="stylesheet" href="<%=request.getContextPath()%>/include/css/searchwindow.css" />
		<link rel="StyleSheet" href="include/calendar/theme.css" type="text/css" />
		<script src="include/calendar/calendar.js"></script>
		<script src="include/calendar/calendar-setup.js"></script>
		<script src="include/calendar/calendar-zh.js"></script>
		<script language="javascript" src="/include/javascript/global.js"></script>
        <script language="javascript" src="<%=request.getContextPath()%>/comm/include/javascript/CommMessage.js"></script>
        
        <script language="javascript" src="/hsp/include/javascript/gettext_staff.js"></script>	
		<script type="text/javascript" src="/hsp/include/javascript/jianbian.js" defer="defer"></script>
		
	    <script src="/include/javascript/searchsuggest.js"></script>
	    <link rel="stylesheet" href="/include/css/searchsuggest.css" />
	    <script src="/include/javascript/searchsuggest.js"></script>
        
		<script type="text/javascript">
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


function commandOrderBy(order, asc) { 
			  	document.form.orderNo.value = order; 
			  	document.form.asc.value = asc; 
			  	document.form.method = "POST";
			  	document.form.verbId.value = "query";
			  	document.form.submit();
			}			
		function queryForm(num){
			document.form.page.value = 1;   
		  	document.form.method = "POST";
		  	document.form.verbId.value = "query";
		  	document.form.submit();
		}
function isDate(oTextbox) {

　　var regex = new RegExp("^(?:(?:([0-9]{4}(-|\/)(?:(?:0?[1,3-9]|1[0-2])(-|\/)(?:29|30)|((?:0?[13578]|1[02])(-|\/)31)))|([0-9]{4}(-|\/)(?:0?[1-9]|1[0-2])(-|\/)(?:0?[1-9]|1\\d|2[0-8]))|(((?:(\\d\\d(?:0[48]|[2468][048]|[13579][26]))|(?:0[48]00|[2468][048]00|[13579][26]00))(-|\/)0?2(-|\/)29))))$");

　　var dateValue = oTextbox.value;
   if(dateValue!=""){
　　if (!regex.test(dateValue)) {

　　alert("日期有误，期待格式为：XXXX-XX-XX！");

　　oTextbox.value = "";
　　return;


　   　}
     }
　　}
 	function cmdExcelExport(){				
		if(confirm("<bean:message  bundle="hsp.hspLocale" key="hsp.jsp.common.warn7"/>")){
			document.form.method = "POST";
			document.form.verbId.value = "elsExport";
			document.form.submit();
			}
		}
	function cmdExcelImport(){
		window.location="<%=request.getContextPath()%>/hsp/hspequipbaseinfo/import.jsp";
	}
	function huiche(){
		if(event.keyCode==13){
			event.keyCode=9
		}				
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
		
		function getReadyStateHandler(req, responseXmlHandler,current) {
			return function () {
				if (req.readyState == 4) {
		      		if (req.status == 200) {
		      			//alert(req.responseText); 
		      			if(current==""){
					    	responseXmlHandler(req.responseXML);
					    }else{
					    	responseXmlHandler(req.responseXML,current);
					    }
					} else {
					    alert("HTTP error: " + req.status);
		      		}
		    	}
		  	}
		}
		function selectDept(hspIdInput, deptNameInput, deptCodeInput){
			var hspId = document.getElementById(hspIdInput).value;
			if(hspId == ''){
				alert("请先在下拉列表选择机构！");
				return;
			}
			add("<%=request.getContextPath()%>/hsp/healthRegisterTree.do?verbId=getDept&hspId="+hspId, deptNameInput, deptCodeInput);
		}
		  function clearDept(_hspId){
		    	if(_hspId.value == ""){
		    		document.form.deptCode.value = "";
		    		document.form.deptName.value = "";
		    	}
		    }
		function queryForm(num) {
			  document.form.page.value = 1; 
			  document.form.orderNo.value = ""; 
			  document.form.asc.value = ""; 
			  document.form.method = "POST";
			  document.form.verbId.value = "query";
			  document.form.submit();
			}
	</script>

	</head>
	<body onload="showCommMessage('','<%=data.getMessage() %>','1')">
		<!-- Form中的action项的值必须设置-->
		<form name="form" method="post" action="<%=request.getContextPath()%>/hsp/hspEquipBaseinfo.do">
			<!-- Head line -->
		    <input type="hidden" name="verbId" value="<%=data.getVerbId()%>" />
			<input type="hidden" name="orderNo" value="<%=data.getOrderNo()%>" />
			<input type="hidden" name="asc" value="<%=data.getAsc()%>" />
			<input type="hidden" name="idHidden" value="<%=data.getId() %>" />
			<!--查询条件-->		
            <table border="0" cellpadding="0" cellspacing="0" class="tblSearch">	
				<tr>
					<td class="tblTitle" colspan="4">
						<bean:message  bundle="hsp.hspLocale" key="hsp.jsp.common.selectCondition"/>
					</td>
				</tr>				
				<tr>
					<td >
						机构名称：
						<span id="spanOutput" class="spanTextDropdown" style="display: none;"></span> 
						<input type="text" id="hspName" name="hspName" value="${data.hspName}" 
							onkeyup="GiveOptions(event, '<%=request.getContextPath()%>/searchSuggest.do', 'getHspName_Code_ID', new Array('hspCode', 'orgId'))"
							onkeydown="huanhang(event)"/>
						<input type="hidden" id="hspCode" name="hspCode" value="${data.hspCode}"/>
			            <input type="hidden" id="orgId" name="orgId" value="${data.orgId}"
			            	onpropertychange="clearDept(this)"/>
						科室：
						<input type="text" id="deptName" name="deptName" value="${data.deptName}" readonly="readonly"/>
			            <input type="hidden" id="deptCode" name="deptCode" value="${data.deptCode}"/>
			            <img src="<%=request.getContextPath()%>/include/images/select.gif" style="cursor: pointer;"
							onclick="selectDept('orgId', 'deptName', 'deptCode')" />
						设备编号：
						<input name="equipCode" name="equipCode" value="${data.equipCode}"  type="text" onkeydown="huiche()" />		
						设备名称：
						<input name="equipName" name="equipName" value="${data.equipName}"  type="text" onkeydown="huiche()" />
						使用情况：
						<select name="usage">
							<option/>
							<%
								List<?> usageList = data.getUseageList();
								String usage = data.getUsage();
								if(usageList != null){
									for(Iterator<?> it = usageList.iterator(); it.hasNext(); ){
										CommEquipUseage comm = (CommEquipUseage)it.next();
							%>
							<option value="<%=comm.getItemCode()%>" <%=comm.getItemCode().equals(usage) ? "selected" : ""%>>
								<%=Converter.toBlank(comm.getItemName()) %></option>
							<%
									}
								}
							%>
						</select>
						<input type="button" class="btnSave" id="queryImg" value="查　询" onclick="queryForm();" />
					</td>
				</tr>
			</table> 
            <div id="dvh1">
			<!--列表内容-->
			<table border="0" cellpadding="0" cellspacing="0" class="tblSearchList">
			   <caption style="text-align:left;">
					<img src="include/images/list_nav1.jpg" width="20" height="20" align="absmiddle" /> <span style="font-weight:bold; color:#333;">查询结果列表</span>
			    <span class="titleBtn"> <a href="javascript:cmdAdd()"><img border=0 alt="添加" src="include/images/add.gif" align="absmiddle" style="cursor: hand" />添加</a></span>
			    </caption>
			    <thead>
				<tr class="lstName">
					<th width="15%" height="26">机构名称<div><img src="include/images/cmdOrderByAsc.gif" alt="Ascender" vspace="7" onclick="commandOrderBy('1', '0')" /><img border="0" onclick="commandOrderBy('1', '1')" alt="Descender" src="include/images/cmdOrderByDesc.gif" /></div></th>
					<th width="15%" height="26">科室<div><img src="include/images/cmdOrderByAsc.gif" alt="Ascender" vspace="7" onclick="commandOrderBy('2', '0')" /><img border="0" onclick="commandOrderBy('2', '1')" alt="Descender" src="include/images/cmdOrderByDesc.gif" /></div></th>
					<th width="15%" height="26">设备名称<div><img src="include/images/cmdOrderByAsc.gif" alt="Ascender" vspace="7" onclick="commandOrderBy('3', '0')" /><img border="0" onclick="commandOrderBy('3', '1')" alt="Descender" src="include/images/cmdOrderByDesc.gif" /></div></th>
					<th width="15%" height="26">设备型号<div><img src="include/images/cmdOrderByAsc.gif" alt="Ascender" vspace="7" onclick="commandOrderBy('4', '0')" /><img border="0" onclick="commandOrderBy('4', '1')" alt="Descender" src="include/images/cmdOrderByDesc.gif" /></div></th>
					<th width="15%" height="26">出厂编号<div><img src="include/images/cmdOrderByAsc.gif" alt="Ascender" vspace="7" onclick="commandOrderBy('5', '0')" /><img border="0" onclick="commandOrderBy('5', '1')" alt="Descender" src="include/images/cmdOrderByDesc.gif" /></div></th>				    				    
				    <th width="10%" height="26">使用情况<div><img src="include/images/cmdOrderByAsc.gif" alt="Ascender" vspace="7" onclick="commandOrderBy('6', '0')" /><img border="0" onclick="commandOrderBy('6', '1')" alt="Descender" src="include/images/cmdOrderByDesc.gif" /></div></th>				    				    
					<th width="5%" height="26">查看</th>
			        <th width="5%" height="26">修改</th>
			        <th width="5%" height="26">删除</th>
				</tr>
				</thead>
				<tbody id="interval_row_id">
					<%
						List<?> dataList = data.getDataList();
						if (dataList != null) {
							for (Iterator<?> it = dataList.iterator(); it.hasNext(); ) {
								Object[] obj = (Object[])it.next();
					%>
					<tr class="list_neirong">					
						<td>
							<%=Converter.toBlank(obj[1]) %>
						</td>
						<td>
							<%=Converter.toBlank(obj[2]) %>
						</td>
						<td>
							<%=Converter.toBlank(obj[3]) %>
						</td>
						<td>
							<%=Converter.toBlank(obj[4]) %>
						</td>
						<td>
							<%=Converter.toBlank(obj[5]) %>
						</td>
						<td>
							<%=Converter.toBlank(obj[8]) %>
						</td>
						<td>
							<img
								src="<%=request.getContextPath()%>/include/images/cmdView_s.jpg"
								title="查看" onclick="cmdView('<%=Converter.toBlank(obj[0])%>')" />
						</td>
						<td>
							<img
								src="<%=request.getContextPath()%>/include/images/cmdEdit_s.jpg"
								title="修改" onclick="cmdEdit('<%=Converter.toBlank(obj[0])%>')" />
						</td>
						<td>
							<img
								src="<%=request.getContextPath()%>/include/images/cmdDel_s.jpg"
								title="删除" onclick="cmdDel('<%=Converter.toBlank(obj[0])%>')" />
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
					<td colspan="6" align="center"  class="footer" >
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
						<input type="hidden" id="pageId" name="pageId" value="page_282881153ad4909b013ad4909b8c0000" />
						<input type="hidden" id="reHref" name="reHref" value="<%=request.getContextPath()%>/hsp/hspEquipBaseinfo.do?verbId=query" />
						<%@ include file="/include/changepagesize.jsp" %>
						
						<input id="_total" name="totalPage" type="hidden"
							value="<%=totalPage%>" />
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
						<a href="javascript:goPage('0')"><img
								src="include/images/shouye.gif" align="middle" border="0" /> </a>&nbsp;
						<a href="javascript:goPage('<%=curPage - 1%>')"><img
								src="include/images/shang.gif" align="middle" border="0" /> </a>&nbsp;
						<%
								} else {
								out
								.println("<img src='include/images/shouye_s.gif' align='middle'  border='0px' />&nbsp;&nbsp;<img src='include/images/shang_s.gif' align='middle'  border='0px' />&nbsp;");
							}
							if (curPage < totalPage) {
						%>
						<a href="javascript:goPage('<%=curPage + 1%>')"><img
								src="include/images/xia.gif" align="middle" border="0" /> </a>&nbsp;
						<a href="javascript:goPage('<%=totalPage%>')"><img
								src="include/images/mo.gif" align="middle" border="0" /> </a>&nbsp;
						<%
								} else {
								out
								.println("<img src='include/images/xia_x.gif' align='middle' border='0px' />&nbsp;&nbsp;<img src='include/images/mo_m.gif' align='middle'  border='0px' />&nbsp;");
							}
						%>
						| &nbsp;到第
						<input id="_tp" name="page" type="text" value="<%=curPage%>"
							size="2" class="txt" />
						页&nbsp;
						<img style="cursor: hand;" src="include/images/go.jpg" width="18"
							border="0" onclick="goPage2()" />
					</td>
				</tr>
				<tr>
					<td colspan="7" align="center" bgcolor="#ffffff" height="35px">
						<input type="button" id="exportImg" name="<bean:message  bundle="hsp.hspLocale" key="hsp.jsp.common.import1"/>" value="<bean:message  bundle="hsp.hspLocale" key="hsp.jsp.common.import"/>" onclick="cmdExcelImport()" style="cursor: hand; background: url(include/images/tong.gif) no-repeat; border: 0; width: 50px; height: 23px; font-size: 12px;" />
			 			<input type="button" id="exportImg" name="<bean:message  bundle="hsp.hspLocale" key="hsp.jsp.common.export1"/>" value="<bean:message  bundle="hsp.hspLocale" key="hsp.jsp.common.export"/>" onclick="cmdExcelExport()" style="cursor: hand; background: url(include/images/tong.gif) no-repeat; border: 0; width: 50px; height: 23px; font-size: 12px;" />
					</td>
				</tr>
			</table>
		</form>
        <script language="javascript" src="include/javascript/interval_row_color.js"></script>
	</body>
</html>
