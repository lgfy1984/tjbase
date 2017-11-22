<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="html" uri="/WEB-INF/struts-html.tld"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@ taglib prefix="logic" uri="/WEB-INF/struts-logic.tld"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title><bean:message key="include.jsp.error.errPage" bundle="conf.include.include"/></title>
		<style type="text/css">
.js_table {
	background-color: #005B88;
	font-size: 14px;
}

.js_nav {
	background: url(<%=request.getContextPath()%>/ include/ images/ trbg
		. gif ) repeat-x 0 0;
	text-align: left;
	padding-left: 10px;
	height: 25px;
	line-height: 25px;
}

.error p {
	margin: 0;
	padding: 0px;
	line-height: 20px;
}

td.error .margin_b {
	margin-bottom: 10px;
}
</style>
	</head>
	<body>
		<table width="400" border="0" align="center" class="js_table" cellspacing="1" cellpadding="0">
			<tr class="js_nav">
				<td>
					<img src="<%=request.getContextPath()%>/include/images/mq.gif" border="0" style="vertical-align: middle; margin-right: 5px;" />
					<bean:message key="include.jsp.error.systemMsg" bundle="conf.include.include"/>
				</td>
			</tr>
			<tr>
				<td height="200" align="center" bgcolor="#FFFFFF">
					<table border="0" align="center" cellspacing="0" cellpadding="0">
						<tr>
							<td width="130" align="center">
								<img src="<%=request.getContextPath()%>/include/images/pic.gif" style="border: 0;" />
							</td>
							<td width="270" align="left" class="error">
								<p>
									<bean:message key="include.jsp.error.err" bundle="conf.include.include"/>
								</P>
								<p class="margin_b">
								   <bean:message key="include.jsp.error.pageMsg" bundle="conf.include.include"/>
								</P>
								<p>
									Sorry，
								</P>
								<p class="margin_b">
									You visit the page is unable to demonstrate normally！
								</P>
								<input type="button" style="cursor: hand; background: url(<%=request.getContextPath()%>/ include/ images/ tong . gif ) no-repeat; border: 0; width: 50px; height: 23px; font-size: 12px;" name="<bean:message key="jsp.btnBack" bundle="conf.Init"/>" value="<bean:message key="jsp.btnBack" bundle="conf.Init"/>" onclick="history.go(-1)" />
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
	</body>
</html>
