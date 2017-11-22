<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<%@ page contentType="text/html; charset=UTF-8"%>
<html>
	<head>
		<%if (request.getServerPort() == 80) {%>
		<base href="http://<%=request.getServerName()%><%=request.getContextPath()%>/">
		<%} else {%>
		<base href="http://<%=request.getServerName()%>:<%=request.getServerPort()%><%=request.getContextPath()%>/">
		<%}%>
		<meta http-equiv="Content-Type" content="text/html; charset=gb2312" />
		<title>middle</title>
		<style>
		body {
		font-family:Arial, Helvetica, sans-serif,宋体;
		font-size:12px;
		margin:0 auto;
		}
		</style>
    </head>
        <frameset cols="350, *" id="middle" frameBorder=no>
             <frame src="<%=request.getContextPath()%>/security/menutree/tree.jsp" id="treeFrame" name="treeFrame" scrolling="no"></frame>
             <frame src="<%=request.getContextPath()%>/security/menutree/right.jsp" id="rightFrame" name="rightFrame" scrolling="auto"></frame>
        </frameset>
<noframes>
<body>
此浏览器不支持本框架!
</body>
</noframes>
</html>
