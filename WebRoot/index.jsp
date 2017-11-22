<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
	<meta http-equiv="content-type" content="text/html; charset=UTF-8"/>
	</head>
	<body ></body>
<script type="text/javascript">
var browser= navigator.appName;
var local_version= navigator.appVersion;
var version= local_version.split(";");
var trim_Version=version[1].replace(/[ ]/g,"");


	window.location='<%=request.getContextPath()%>/home/login.jsp';

</script>
</html>
