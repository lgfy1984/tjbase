<%@page contentType="text/html; charset=utf-8"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<jsp:useBean id="data" scope="request" class="com.tianjian.hsp.struts.form.OrgMenuForm" />
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
		    function query(){
		        var queryType = document.getElementById("queryType").value;
		        var queryKey = document.getElementById("queryKey").value;
		        var queryValue = document.getElementById("queryValue").value;
		        var flag = true;
		        if(queryType==null||queryType==""){
		            alert("请选择查询类型！");
		            flag = false;
		        }
		        if(queryKey==null||queryKey==""){
		            alert("请选择关键字类型！");
		            flag = false;
		        }
		        if(queryValue==null||queryValue==""){
		            alert("请输入查询内容！");
		            flag = false;
		        }
		        if(flag){
		            document.form.verbId.value = "query";    
			        var url = "<%=request.getContextPath()%>/hsp/orgMenu.do?verbId=query&queryType="+queryType+"&queryKey="+queryKey+"&queryValue="+queryValue;
		            self.parent.frames["middleFrame"].frames["menuFrame"].location.href= url;
		        }
		    }
		
	    </script>

	</head>
	<body>
		<!-- Form中的action项的值必须设置-->
		<form name="form" method="post" action="<%=request.getContextPath()%>/hsp/orgMenu.do">
			<input type="hidden" id="verbId" name="verbId" value="query"/>
			<table>
			    <tr>
			        <td>&nbsp;&nbsp;所属机构：</td>
			        <td>
			            <input type="hidden" id="queryType" name="queryType" value="2"/>
			            <input type="text" id="itemName" name="itemName" 
						onkeyup="GiveOptions(event, '<%=request.getContextPath()%>/searchSuggest.do', 'getHspName_00000000004', 'itemCode')"
						onkeydown="huanhang(event)" />
						<input type="hidden" name="itemCode" id="itemCode"/>
						<span id="spanOutput" class="spanTextDropdown" style="display: none;"></span> 
			        </td>
			        <td>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
			            <select id="queryKey" name="queryKey">
			                <option value="0">名字</option>
			                <option value="1">输入码</option>
			            </select>：
			        </td>
			        <td>
			            <input type="text" id="queryValue" name="queryValue"/>
			        </td>
			        <td>
			            <input type="button" id="btnQuery" name="btnQuery" value="查询" onclick="query()"/>
			        </td>
			    </tr>
			</table>
		</form>
	</body>
</html>
