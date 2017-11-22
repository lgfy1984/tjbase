<%@ page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<%@ page import="com.tianjian.util.ResourcesUtil" %>
<jsp:useBean id="commConfigCardtype" scope="request" class="com.tianjian.comm.struts.form.CommConfigCardtypeForm" />
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
		<title></title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<link rel="stylesheet" rev="stylesheet" href="comm/include/css/comm_detail.css" />
	</head>
	<body>
		<form name="form" method="post">
			<input type="hidden" name="verbId" value="detail">
			<table border="0" cellspacing="1" cellpadding="0" class="table">
				<tr>
					<td class="biaoti" colspan="4" nowrap>
						<bean:message  bundle="comm.commLocale" key="comm.jsp.commconfigcardtypedetail.text15"/>
					</td>
				</tr>
				<tr>
					<td class="qian" nowrap>
						<bean:message  bundle="comm.commLocale" key="comm.jsp.commom.code"/>:
					</td>
					<td class="hou" nowrap>
						<%=commConfigCardtype.getItemCode()%>
					<td class="qian" nowrap>
						<bean:message  bundle="comm.commLocale" key="comm.jsp.commom.name"/>:
					</td>
					<td class="hou" nowrap>
						<%=commConfigCardtype.getItemName()%>
					</td>
				</tr>
				<tr>
					<td class="qian" nowrap>
						<bean:message  bundle="comm.commLocale" key="comm.jsp.commom.seqNo"/>:
					</td>
					<td class="hou" nowrap>
						<%=commConfigCardtype.getSeqNo()%>
					</td>
					<td class="qian" nowrap>
						<bean:message  bundle="comm.commLocale" key="comm.jsp.commom.inputCode"/>:
					</td>
					<td class="hou" nowrap>
						<%=commConfigCardtype.getInputCode()%>
					</td>
				</tr>
				<tr>
					<td class="qian" nowrap>
						<bean:message  bundle="comm.commLocale" key="comm.jsp.commom.comments"/>:
					</td>
					<td class="hou" nowrap>
						<%=commConfigCardtype.getComments()%>
					</td>
					<td class="qian" nowrap>
						<bean:message  bundle="comm.commLocale" key="comm.jsp.commom.state"/>:
					</td>
					<td class="hou" nowrap>
						<%	
							if(commConfigCardtype.getStopFlag().trim().equals("0")){
						%>
							<bean:message  bundle="comm.commLocale" key="comm.jsp.commom.text18"/>
						<%	
							}else{

								out.print("<bean:message  bundle='comm.commLocale' key='comm.jsp.commom.warn2'/>");


						%>
							<bean:message  bundle="comm.commLocale" key="comm.jsp.commom.warn2"/>
						<%

							} 
						%>
					</td>
				</tr>
			</table>
			<!-- Sheet operation button area -->
			<table width="100%" border="0" cellpadding="0" cellspacing="0">
				<tr>
					<td height="30" align="center">
						<input type="button" id="btnBack" name="btnBack" value="<bean:message  bundle="comm.commLocale" key="comm.jsp.commom.back"/>" onClick="history.go(-1);" />
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>
