<%@ page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="bean" uri="/WEB-INF/struts-bean.tld"%>
<jsp:useBean id="data" scope="request" class="com.tianjian.comm.struts.form.CommCongihMidwiferyForm" />
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
		<title><bean:message key="comm.jsp.commcongihmidwifery.add.title" bundle="conf.comm.CommMessageguoh"/></title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<script language="javascript" src='<bean:message key="comm.js.TJMessage" bundle="conf.comm.CommMessageguoh"/>'></script>
		<script language="javascript" src="include/javascript/eventOnKeyPress.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/hsp/include/javascript/jianbian.js" defer="defer"></script>
		<script language="javascript" src="<%=request.getContextPath()%>/hsp/include/javascript/gettext_staff.js" defer="defer"></script>
		<link rel="stylesheet" href="<%=request.getContextPath()%>/hsp/include/css/open.css" />
		<script language="javascript">
function saveForm(){
if (confirmMessage("0-000003")){ 
	document.form.verbId.value = "add";
	document.form.submit();
	}
}
function huiche(){
	if(event.keyCode==13){
		event.keyCode=9;
	}
}
function goback(){
	document.form.hspCalssCode.value="";
	document.form.hspConfigBaseinfoId.value="";
	document.form.verbId.value = "query";
	document.form.submit();
}
</script>
		<link rel="stylesheet" rev="stylesheet" href="comm/include/css/comm_add.css" />
	</head>
	<body onload="showMessage('','<%=data.getMessage()%>','1')">
		<form name="form" method="post" action="comm/commCongihMidwifery.do">
			<input type="hidden" name="verbId" value="add" />
			<table border="0" cellspacing="1" cellpadding="0" class="table" align="center">
				<tr>
					<td height="30px" class="biaoti" colspan="4">
						<font color="red">※</font>&nbsp;&nbsp;<bean:message key="comm.jsp.commcongihmidwifery.add.td1" bundle="conf.comm.CommMessageguoh"/>&nbsp;&nbsp;
						<font color="red">※</font>
					</td>
				</tr>
				<tr>
					<td class="qian" nowrap>
						<font color="red">*</font> <bean:message key="comm.jsp.commcongihmidwifery.add.td2" bundle="conf.comm.CommMessageguoh"/>
					</td>
					<td class="hou" nowrap>
						<input type="text" id="displayInputId_2"
							name="hspCalssName" style="font-size: 14px"
							value="" readonly="readonly"
							onkeydown="huiche()" />
						<input type="hidden" id="hiddenInputId_2"
							value="<%=data.getHspClassCode()%>" name="hspClassCode" />
						<img
							src="<%=request.getContextPath()%>/hsp/include/images/select.gif"
							style="cursor: pointer;"
							onclick="add('<%=request.getContextPath()%>/comm/commCongihMidwifery.do?verbId=getType','displayInputId_2','hiddenInputId_2','','')" />
					</td>
					<td class="qian" nowrap>
						<font color="red">*</font> <bean:message key="comm.jsp.commcongihmidwifery.add.td3" bundle="conf.comm.CommMessageguoh"/>
					</td>
					<td class="hou" nowrap>
						<input type="text" id="displayInputId_1"
							name="hspConfigBaseinfoName" style="font-size: 14px"
							value="" readonly="readonly"
							onkeydown="huiche()" />
						<input type="hidden" id="hiddenInputId_1"
							value="<%=data.getHspConfigBaseinfoId()%>" name="hspConfigBaseinfoId" />
						<img
							src="<%=request.getContextPath()%>/hsp/include/images/select.gif"
							style="cursor: pointer;"
							onclick="add('<%=request.getContextPath()%>/hsp/hspConfigBaseinfo.do?verbId=getHsp&hspType=3','displayInputId_1','hiddenInputId_1','','')" />
					</td>
				</tr>
				<tr>
					<td class="qian" nowrap>
						<bean:message key="comm.jsp.commcongihmidwifery.add.td4" bundle="conf.comm.CommMessageguoh"/>
					</td>
					<td class="hou" nowrap>
						<input type="text" class="kuandu" name="seqNo" readonly="seqNo" size="30" maxlength="20" onkeydown="huiche()" value="<%=data.getSeqNo()%>" />
					</td>
					<td class="qian" nowrap></td>
					<td class="hou" nowrap></td>
				</tr>
			</table>
			<!-- Sheet operation button area -->
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td height="30" align="center">
						<input type="button" name="btnSave" id="btnSave" style="cursor: hand; background: url(include/images/tong.gif) no-repeat; border: 0; width: 50px; height: 23px; font-size: 12px;" name="btnSaveForm" value='<bean:message key="comm.jsp.commcongihmidwifery.add.td5" bundle="conf.comm.CommMessageguoh"/>' onClick="saveForm()" />
						<input type="button" name="btnHistory" id="btnHistory" style="cursor: hand; background: url(include/images/tong.gif) no-repeat; border: 0; width: 50px; height: 23px; font-size: 12px;" name="btnBack" value='<bean:message key="comm.jsp.commcongihmidwifery.add.td6" bundle="conf.comm.CommMessageguoh"/>' onClick="goback();" />
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>
