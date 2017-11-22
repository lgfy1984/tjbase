<%@ page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="bean" uri="/WEB-INF/struts-bean.tld"%>
<html>
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
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<title><bean:message key="home.jsp.rules.title" bundle="conf.home.Message"/></title>
		<style type="text/css">
body,body * {
	margin: 0;
	padding: 0;
}

#divfull {
	width: 750px;
	height: 477px;
	background: url(home/include/images/clause.jpg) no-repeat 0 0;
	margin: 0 auto;
	margin-top: 20px;
	border: 1px solid #cccccc;
}

#buttonagree {
	width: 750px;
	height: 21px;
	margin: 0 auto;
	margin-top: 430px;
	text-align: center;
}

#buttonagree img {
	cursor: pointer;
}
</style>
		<script language="javascript">
function agree() {
    document.form.submit();
}
function disagree() {
    window.close();
}
</script>
	</head>
	<body>
		<form name="form" method="post" action="index.jsp?agree=true">
			<div id="divfull">
				<div id="buttonagree">
					<img src="home/include/images/agree.gif" style="margin-right: 20px;" onclick="agree();" />
					<img src="home/include/images/noagree.gif" onclick="disagree();" />
				</div>
			</div>
		</form>
	</body>
</html>
