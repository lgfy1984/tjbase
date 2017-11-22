<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="html" uri="/WEB-INF/struts-html.tld"%>
<%@ taglib prefix="bean" uri="/WEB-INF/struts-bean.tld"%>
<%@ taglib prefix="logic" uri="/WEB-INF/struts-logic.tld"%>
<jsp:useBean id="data" scope="request" class="com.tianjian.comm.struts.form.CommConfigLocationGroupForm" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%if(request.getServerPort() == 80) {%>
<base href="http://<%=request.getServerName()%><%=request.getContextPath()%>/" />
<%} else {%>
<base href="http://<%=request.getServerName()%>:<%=request.getServerPort()%><%=request.getContextPath()%>/" />
<%}%>
<title><bean:message key="comm.jsp.common.jmzd" bundle="conf.comm.Comm"/>detail.jsp</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
<meta http-equiv="pragma" content="no-cache"/>
<meta http-equiv="cache-control" content="no-cache"/>
<meta http-equiv="expires" content="0"/>
<link rel="stylesheet" rev="stylesheet" href="comm/include/css/comm_detail.css" />
</head>
<body onload="document.form.btnBack.focus();">
<form name="form" method="post">
<input type="hidden" name="verbId" value="detail" />
<input type="hidden" name="id" value="<%=data.getId()%>" />
<table border="0" cellspacing="1" cellpadding="0" align="center" class="table">
	<tr>
		<td class="biaoti" colspan="4">
			<bean:message key="comm.jsp.common.jmzd" bundle="conf.comm.Comm"/>
		</td>
	</tr>
	<tr>
		<td class="qian">
			<bean:message key="comm.jsp.common.province" bundle="conf.comm.Comm"/>：
		</td>
		<td class="hou">
			<%=data.getCommProvinceName()%>
		</td>
		<td class="qian">
			<bean:message key="comm.jsp.common.city" bundle="conf.comm.Comm"/>：
		</td>
		<td class="hou">
			<%=data.getCommCityName()%>
		</td>
	</tr>
	<tr>
		<td class="qian">
			<bean:message key="comm.jsp.common.town" bundle="conf.comm.Comm"/>：
		</td>
		<td class="hou">
			<%=data.getCommCountyName()%>
		</td>
		<td class="qian">
			<bean:message key="comm.jsp.common.tv" bundle="conf.comm.Comm"/>：
		</td>
		<td class="hou">
			<%=data.getCommCltName()%>
		</td>					
	</tr>
	<tr>
		<td class="qian">
			<bean:message key="comm.jsp.common.commClvName" bundle="conf.comm.Comm"/>：
		</td>
		<td class="hou">
			<%=data.getCommClvName()%>
		</td>	
		<td class="qian">
			<bean:message key="comm.jsp.common.item" bundle="conf.comm.Comm"/>：
		</td>
		<td class="hou">
			<%=data.getItemCode()%>
		</td>		
	</tr>
	<tr>	
		<td class="qian">
			<bean:message key="comm.jsp.common.names" bundle="conf.comm.Comm"/>：
		</td>
		<td class="hou">
			<%=data.getItemName()%>
		</td>
		<td class="qian">
			<bean:message key="comm.jsp.common.seqNo" bundle="conf.comm.Comm"/>：
		</td>
		<td class="hou">
			<%=data.getSeqNo()%>
		</td>
	</tr>
	<tr>		
		<td class="qian">
			<bean:message key="comm.jsp.common.inputItemCode" bundle="conf.comm.Comm"/>：
		</td>
		<td class="hou">
			<%=data.getInputCode()%>
		</td>
		<td class="qian">
			<bean:message key="comm.jsp.common.comments" bundle="conf.comm.Comm"/>：
		</td>
		<td class="hou" >
			<%=data.getComments() %>
		</td>
	</tr>

</table>		
<!-- Sheet operation button area -->
<table width="100%" border="0" cellpadding="0" cellspacing="0">
			<tr>
				<td height="30" align="center">
					<input type="button" name="btnBack" value="<bean:message key="comm.jsp.common.return" bundle="conf.comm.Comm"/>" onclick="history.go(-1);" />
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>
