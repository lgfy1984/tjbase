<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="bean" uri="/WEB-INF/struts-bean.tld"%>
<jsp:useBean id="pb" scope="request" class="com.tianjian.util.comm.PageBean" />
<jsp:useBean id="dataForm" scope="request" class="com.tianjian.hsp.struts.form.HspStaffBaseinfoLocalBaseForm" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
		<%
		if (request.getServerPort() == 80) {
		%><base
			href="http://<%=request.getServerName()%><%=request.getContextPath()%>/" />
		<%
		} else {
		%>
		<base
			href="http://<%=request.getServerName()%>:<%=request.getServerPort()%><%=request.getContextPath()%>/" />
		<%
		}
		%>

	<head>
	<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
	<title>iframe1</title>
<script language="JavaScript" src="dragiframe.js"></script>	
<script language="javascript">
	function closeIframe(){
		parent.document.getElementById("mask").style.visibility='hidden'
		parent.document.getElementById("iframe1").style.display = "none";
	}
	
	
	 function queryForm(){
      document.form.page.value = 1; 
	  document.form.orderNo.value = ""; 
	  document.form.asc.value = ""; 
	  document.form.method = "POST";
	  document.form.verbId.value = "getXml";	  
	  document.form.submit();
 	}

	function goPage(page) {  
	   document.form.page.value = page;
	   document.form.verbId.value = "getXml";    
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
	  document.form.verbId.value = "getXml";    
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
<script language="javascript">
	function selectThis(value1,value2,value3,value4,value5,value6,hspstaffid,mobileTel){
    	window.parent.document.getElementById("hiddenInputId_1").value = value1;
    	window.parent.document.getElementById("displayInputId_1").value = value2;
    	window.parent.document.getElementById("displayInputId_2").value = value3;
    	window.parent.document.getElementById("hspStaffBaseinfoId").value = hspstaffid;
    	window.parent.document.getElementById("idNo").value = value4;
    	window.parent.document.getElementById("mobilecode").value = mobileTel;
    	if(value6 != null){
	    	window.parent.document.getElementById("statYear").value = value6.substring(0,4);
	    	window.parent.document.getElementById("month").value = value6.substring(4,6);
	    	window.parent.document.getElementById("day").value = value6.substring(6,8);
    	}
    	var len = window.parent.document.form.commConfigSexId.length;
    	var i = 0;
    	for(i = 0; i < len; i++){
    		if(value5 == window.parent.document.form.commConfigSexId[i].value){
    			window.parent.document.form.commConfigSexId[i].checked = true;
    		}
    	}
	    closeIframe();
	}
	function select(value1,value2,value3,value4,value5,value6,hspstaffid,mobileTel){
		document.getElementById("checkedHspId").value    =   value1;
		document.getElementById("checkedHspName").value  =   value2;
		document.getElementById("checkedName").value     =   value3;
		document.getElementById("checkeIdNo").value      =   value4;
		document.getElementById("checkedCommSexId").value=  value5;
		document.getElementById("checkedhspStaffBaseinfoId").value=  hspstaffid;
		document.getElementById("checkedMobileTel").value=  mobileTel;
		
		if(value6 != null){
			document.getElementById("year").value        =  value6.substring(0,4);
			document.getElementById("month").value       =  value6.substring(4,6);
			document.getElementById("day").value         =  value6.substring(4,6);
		}
	}	
	function checkok(){
	    window.parent.document.getElementById("displayInputId_1").value = document.getElementById("checkedHspName").value;
    	window.parent.document.getElementById("hiddenInputId_1").value  = document.getElementById("checkedHspId").value;
    	
    	window.parent.document.getElementById("displayInputId_2").value = document.getElementById("checkedName").value;
    	window.parent.document.getElementById("idNo").value             = document.getElementById("checkeIdNo").value;
    	window.parent.document.getElementById("hspStaffBaseinfoId").value             = document.getElementById("checkedhspStaffBaseinfoId").value;
    	window.parent.document.getElementById("mobilecode").value             = document.getElementById("checkedMobileTel").value;
    	
    	window.parent.document.getElementById("statYear").value = document.getElementById("year").value;
    	window.parent.document.getElementById("month").value = document.getElementById("month").value;
    	window.parent.document.getElementById("day").value = document.getElementById("day").value ;
    	
    	var len = window.parent.document.form.commConfigSexId.length;
    	var i = 0;
    	for(i = 0; i < len; i++){
    		if(document.getElementById("checkedCommSexId").value == window.parent.document.form.commConfigSexId[i].value){
    			window.parent.document.form.commConfigSexId[i].checked = true;
    		}
    	}
	    closeIframe();		
	}
</script>
	
<script language="JavaScript" src="dragiframe.js"></script>
<style type="text/css">
a {
	color: #000;
	font-size: 12px;
	text-decoration: none
}

a:hover {
	color: #900;
	text-decoration: underline
}

#massage_box {
	position: absolute;
	left: expression((body.clientWidth-350)/ 2 );
	top: expression((body.clientHeight-200)/ 2 );
	width: 350px;
	height: 200px;
	filter: dropshadow(color = #666666, offx = 3, offy = 3, positive = 2);
	z-index: 2;
}

.massage {
	border: orange solid;
	border-width: 1 1 3 1;
	height: 173px;
	overflow-y: auto;
	background: #fff;
	color: #036;
	font-size: 12px;
	line-height: 150%
}

.header {
	background: orange;
	height: 24px;
	padding: 3 5 0 5;
	color: #fff;
	cursor: move;
}
</style>


<script type="text/javascript" src="<%=request.getContextPath()%>/include/javascript/interval_open_color.js"></script>
<link rel="stylesheet" href="<%=request.getContextPath()%>/include/css/form.css" />
<link rel="stylesheet" href="<%=request.getContextPath()%>/include/css/open.css" />
<script language="javascript" src="<bean:message key="security.js.gettext_staff.path" bundle="security" />"  ></script>

	</head>
	<body>
	<form name="form" method="post" action="<%=request.getContextPath()%>/hsp/hspStaffBaseinfoLocalBase.do">
		<input type="hidden" name="verbId"/>
		<input type="hidden" name="orderNo"/>
		<input type="hidden" name="asc"/>
		<input type="hidden" name="idHidden"/>
	
	<table align="center" width="570" style="background-color:#828283; margin-top:10px;" cellspacing="1" cellpadding="0" border="0"> 

		<tr>
			<td class="searchBg">查询条件</td>
		</tr>
		<tr>
			<td class="date_img">
			机构：<input type="hidden" id="hiddenInputId_1" name="hspConfigBaseinfoIdQuery" value="${dataForm.hspConfigBaseinfoIdQuery}"/>
			<input type="text" id="displayInputId_1" name="hspConfigBaseinfoName" readonly="readonly" onkeydown="huiche()" />
			<img src="<%=request.getContextPath()%>/include/images/select.gif" style="cursor:pointer;" 
			onclick="add('<%=request.getContextPath()%>/hsp/hspConfigBaseinfoBase.do?verbId=getHsp&hspType=3','displayInputId_1','hiddenInputId_1')" />
			姓名：<input name="nameQuery" id="nameQuery" value="${dataForm.nameQuery}"  type="text" onkeyup="GiveOptions(event, '/searchSuggest.do', 'getUserName_0000000001')" onkeydown="huanhang(event)"" />
			<span id="spanOutput" class="spanTextDropdown" style="display: none;"></span>
			<br />
			证件：<input name="idNoQuery" id="idNoQuery" value="${dataForm.idNoQuery}"  type="text"  onkeydown="if(event.keyCode==13){event.keyCode=9;document.getElementById('queryImg').className='queryImg_down'}" />	
				<input type="button" name="queryImg" id="queryImg" class="queryImg" value="" onclick="queryForm();" onmouseover="this.className='queryImg_down'" onmouseout="this.className='queryImg'" />
			</td>
		</tr>		
	</table>
	<table align="center" class="list_table" width="570" cellspacing="1" cellpadding="0" border="0"> 
		<tr class="tdnav">
			<td width="40">
				姓名
			</td>
			<td width="20">
				性别
			</td>
			<td width="50">
				出生日期
			</td>
			<td width="80">
				证件号码
			</td>
			<td width="130">
				所在机构
			</td>
		</tr>
		<tbody id="interval_row_id">
		
		 <% if (dataForm.getIdsHiddenArray() != null && dataForm.getIdsHiddenArray().length > 0) {
			for (int i = 0; i < dataForm.getIdsHiddenArray().length; i++) {
		%>
		<tr <%=i%2==0?"class='trColor_white'" : "class='trColor_defale'"%>
			onclick="select('<%=dataForm.getHspIdArray()[i] %>','<%=dataForm.getHspNameArray()[i] %>','<%=dataForm.getNameArray()[i] %>','<%=dataForm.getIdNoArray()[i] %>','<%=dataForm.getCommSexIdArray()[i] %>','<%=dataForm.getDateOfBirthArray()[i] %>','<%=dataForm.getIdsHiddenArray()[i] %>','<%=dataForm.getMobileTelArray()[i] %>');tresOn(this)" 
			ondblclick="selectThis('<%=dataForm.getHspIdArray()[i] %>','<%=dataForm.getHspNameArray()[i]  %>','<%=dataForm.getNameArray()[i] %>','<%=dataForm.getIdNoArray()[i] %>','<%=dataForm.getCommSexIdArray()[i] %>','<%=dataForm.getDateOfBirthArray()[i] %>','<%=dataForm.getIdsHiddenArray()[i] %>','<%=dataForm.getMobileTelArray()[i] %>')" 
			onmouseover="overColor(this)"
			>
			<td class="leftPadding td_shenglue "><%=dataForm.getNameArray()[i]%></td>
			<td class="leftPadding td_shenglue "><%=dataForm.getCommSexNameArray()[i]%></td>
			<td class="leftPadding td_shenglue "><%=dataForm.getDateOfBirthArray()[i]%></td>
			<td class="leftPadding td_shenglue "><%=dataForm.getIdNoArray()[i]%></td>
			<td class="leftPadding td_shenglue "><%=dataForm.getHspNameArray()[i]%></td>
		</tr>

		<%
			}
			}
		%>
		</tbody>
		<tr>
			<td colspan="5" align="center" bgcolor="#ffffff" height="35px">
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
				<a href="javascript:goPage('0')"><img src="include/images/shouye.gif" align="middle" border="0" />
				</a>&nbsp;
				<a href="javascript:goPage('<%=curPage - 1%>')"><img src="include/images/shang.gif" align="middle" border="0" /></a>&nbsp;
				<%
					} else {
						out.println("<img src='include/images/shouye_s.gif' align='middle'  border='0px' />&nbsp;&nbsp;<img src='include/images/shang_s.gif' align='middle'  border='0px' />&nbsp;");
					}
					if (curPage < totalPage) {
				%>
				<a href="javascript:goPage('<%=curPage + 1%>')"><img src="include/images/xia.gif" align="middle" border="0" /></a>&nbsp;
				<a href="javascript:goPage('<%=totalPage%>')"><img src="include/images/mo.gif" align="middle" border="0" /></a>&nbsp;
				<%
					} else {
						out.println("<img src='include/images/xia_x.gif' align='middle' border='0px' />&nbsp;&nbsp;<img src='include/images/mo_m.gif' align='middle'  border='0px' />&nbsp;");
					}
				%>
				| &nbsp;到第
				<input id="_tp" name="page" type="text" value="<%=curPage%>" size="2" class="txt" />
				页&nbsp;
				<img style="cursor: hand;" src="include/images/go.jpg" width="18" border="0" onclick="goPage2()" />
			</td>
		</tr>
	</table>
	
	<table align="center" style="margin-top:10px;">
		<tr>
			<td>
				<input type="hidden" name="checkedHspId" id="checkedHspId" />
				<input type="hidden" name="checkedHspName" id="checkedHspName" />
				<input type="hidden" name="checkedHspName" id="checkedName" />
				<input type="hidden" name="checkeIdNo" id="checkeIdNo" />
				<input type="hidden" name="checkedCommSexId" id="checkedCommSexId" />
				<input type="hidden" name="checkedhspStaffBaseinfoId" id="checkedhspStaffBaseinfoId" />
				<input type="hidden" name="checkedMobileTel" id="checkedMobileTel" />
				
				<input type="hidden" name="year" id="year" />
				<input type="hidden" name="month" id="month" />
				<input type="hidden" name="day" id="day" />
				<input type="button" class="button" value="确定" onclick="checkok()" />
				<input type="button" class="button" value="关闭" onclick="closeIframe()" />
			</td>
		</tr>			
	</table>
	</form>
	</body>
</html>
