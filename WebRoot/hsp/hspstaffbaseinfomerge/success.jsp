<%@ page contentType="text/html; charset=UTF-8" %>
<jsp:useBean id="data" scope="request" class="com.tianjian.hsp.struts.form.HspStaffBaseinfoMergeForm" />
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>消息页面！！</title>
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
    <meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	</head>
  
  <body>
  <table width="400" border="0" align="center" class="js_table" cellspacing="1" cellpadding="0">
			<tr class="js_nav">
				<td>
					<img src="<%=request.getContextPath()%>/include/images/mq.gif" border="0" style="vertical-align: middle; margin-right: 5px;" />
					系统提示
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
								
								<p class="margin_b">
									
                                    <%=data.getMessage() %>
								</p>
								
							</td>
						</tr>
					</table>
				</td>
			</tr>
		</table>
  </body>
</html>
