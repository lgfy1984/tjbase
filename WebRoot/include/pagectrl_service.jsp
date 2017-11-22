<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/tj-html.tld" prefix="tj"%>
<html>
<head>
	<style>
		.PageCtrlTable td image{
			vertical-align:middle;
			margin-right:5px;
		}
		.PageCtrlTable td input{
			vertical-align:middle;
			height:20px;
			width:25px;
			font-size:12px;
		}
		.PageCtrlTable{			
			font-size:12px; 
			text-align:center; 
			width:700px; 
			height:30px;
			margin-bottom:5px;
			border:1px solid #bfbfbf;
			background-color:#f7f7f7;
			border-top:0;
		}
		.fanhui{
			width:50px;
			height:23px;
			border:0;
			background:url(include/images/tong.gif) no-repeat 0 0;
		}
	</style>
</head>
<body>
<input type="hidden" name="ctrlOp" />
<table class="PageCtrlTable" align="center"  style="border:0;">		
	<tr>
		<td style="border:0;"> ${scope.cRMRemindRecordsActionForm.ctrl.allRecord }共<tj:write property="ctrl.allRecord"/>条记录 第<html:text property="ctrl.nowPage"/>页/共<tj:write property="ctrl.allPage"/>页 每页<html:text property="ctrl.pageSize"/>行			
			<logic:equal name="org.apache.struts.taglib.html.BEAN" property="ctrl.nowPage" value="1">
				<img src="include/images/shouye_s.gif" border="0" />
				<img src="include/images/shang_s.gif" border="0" />
			</logic:equal>
			<logic:notEqual name="org.apache.struts.taglib.html.BEAN" property="ctrl.nowPage" value="1">
				<img src="include/images/shouye.gif" border="0" onclick="op.value='list';ctrlOp.value='first';submit();"/> 
				<img src="include/images/shang.gif" border="0" onclick="op.value='list';ctrlOp.value='back';submit();"/>
			</logic:notEqual>
			<logic:equal name="org.apache.struts.taglib.html.BEAN" property="ctrl.nowPage" value='${requestScope["org.apache.struts.taglib.html.BEAN"].ctrl.allPage}'>
					<img src="include/images/xia_x.gif" border="0" />		 
					<img src="include/images/mo_m.gif" border="0" />
			</logic:equal>
			<logic:notEqual name="org.apache.struts.taglib.html.BEAN" property="ctrl.nowPage" value='${requestScope["org.apache.struts.taglib.html.BEAN"].ctrl.allPage}'>		 
				<img src="include/images/xia.gif" border="0" onclick="op.value='list';ctrlOp.value='next';submit();" />
				<img src="include/images/mo.gif" border="0" onclick="op.value='list';ctrlOp.value='last';submit();" />
			</logic:notEqual>
		</td>
	</tr>
</table>
</body>
</html>