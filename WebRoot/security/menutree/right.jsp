<%@ page pageEncoding="utf-8"%>
<%@ page contentType="text/html;charset=utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
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
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>table_index</title>
<link href="<%=request.getContextPath()%>/healthFileBrower/include/css/default.css" type=text/css rel=stylesheet/>
<meta http-equiv=Content-Type content="text/html; charset=UTF-8"/>
<style type="text/css">
		/*主界面样式*/
			body{
			font-family:Arial, Helvetica, sans-serif,"宋体";
			font-size:14px;
			color:#000;
			margin:0 auto;
			}
			h2{
			font-weight:bolder;
			font-size:14px;
			color:#666;
			text-align:center;
			vertical-align:middle;
			font-family:"黑体";
			border-bottom:1px solid #245981;
			margin-top:50px;
			}
			h4{color:#000;text-align:center;}
		</style>
</head>

<body>
</body>
</html>