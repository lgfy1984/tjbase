<%@ page pageEncoding="utf-8"%>
<%@ page contentType="text/html;charset=utf-8"%>
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
		<title>模块菜单</title>
		<meta http-equiv=Content-Type content="text/html; charset=UTF-8"/>
		<style type="text/css">
		html,body{ width:100%; height:100%;}
		body{background:url(main/include/images/default.jpg) bottom right no-repeat #e9f0f8;}
		</style>
	</head>
	<body>
	</body>
</html>
