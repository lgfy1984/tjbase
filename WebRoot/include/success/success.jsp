<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@ taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@ taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<%
		if (request.getServerPort() == 80) {
		%>
		<base href="http://<%=request.getServerName()%><%=request.getContextPath()%>/" />
		<%
		} else {
		%>
		<base href="http://<%=request.getServerName()%>:<%=request.getServerPort()%><%=request.getContextPath()%>/" />
		<%
		}
		%>
	<title><bean:message key="security.jsp.findpassword.item" bundle="security"/></title>
	<meta http-equiv="pragma" content="no-cache"/>
	<meta http-equiv="cache-control" content="no-cache"/>
	<meta http-equiv="expires" content="0"/>
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3"/>
	<meta http-equiv="description" content="This is my page"/>
	<link rel="stylesheet" rev="stylesheet" href="security/include/css/securitystaffbaseinfo_add_update.css" />
	<link rel="stylesheet" type="text/css" href="include/css/ymPrompt.css" />
	<style type="text/css">
		*{font-family:Arial;}
	</style>
	<script language="javascript" src="<bean:message key="security.js.SecurityMessage.path" bundle="security" />"></script>
	<script language="javascript" src="include/javascript/eventOnKeyPress.js"></script>
	<script language="javascript" src="include/javascript/ymPrompt.js" ></script>
	<link type="text/css" rev="stylesheet" rel="stylesheet" href="main/include/css/secondindex.css" />
</head>
<body>
	<form name="form" method="post" action="security/findPassword.do">
		<input type="hidden" name="verbId" value="modify"/>
		<div id="full">
			<div id="top">
				<div id="banner"></div>
				<div id="bannerRight">
				<div id="windowbutton">
					<div id="lxwm" onclick="alert('<bean:message key="main.jsp.top.div.alert" bundle="conf.main.main"/>：010-83607272');"></div>
					<!--  <div id="cxdl" onclick="top.location.href='<%=request.getContextPath()%>/index.jsp?agree=true'"></div>-->
						<div id="cxdl" onclick="top.location.href='<%=request.getContextPath()%>/security/logout.do'"></div>
				</div>
				<div id="bannerpic"></div>				 
				</div>
			</div>
			<div id="nav">
			<span class="navinfor"><bean:message key="main.jsp.top.div.span" bundle="conf.main.main"/>!<bean:message key="main.jsp.top.div.span2" bundle="conf.main.main"/></span>
				<span  ></span>
			</div>
		</div>
		<div id="full"  >
		<div id="bannerregist">
		<table border="0" cellspacing="1" cellpadding="0" class="table" align="center">
			<bean:message key="security.jsp.success.warn" bundle="security"/>			
		</table>
		</div>
		</div>
		
		<div id="full">
			<div id="footer">
				<%=application.getAttribute("security.SYSTEMNAME") %>
					
			</div>
		</div>
	</form>
</body>
</html>
