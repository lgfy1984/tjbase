<%@ page language="java" pageEncoding="utf-8"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">
		<title><bean:message  bundle="hsp.hspLocale" key="hsp.jsp.hspstaffbaseinfoimport.userExcelImpData"/></title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<script language="javascript" src="<%=request.getContextPath()%>/include/javascript/utrim.js"></script>
		<script type="text/javascript">
			function upload(){
				var name=document.getElementById("file").value;
			  	if((null == name) ||("" == name)){   
			   		alert("<bean:message  bundle="hsp.hspLocale" key="hsp.jsp.common.fileNotAppoint"/>");   
			   		return false;   
			 	 }else{
			 	 	if(confirm("<bean:message  bundle="hsp.hspLocale" key="hsp.jsp.common.warn5"/>"+name+"\n<bean:message  bundle="hsp.hspLocale" key="hsp.jsp.common.warn2"/>")){
			 	 		return true;
			 	 		window.close();
			 	 	}else{
			 	 		return false;
			 	 	}
			 	 } 
			}	
			function showImportReport(){
				<%
					String message = (String)request.getAttribute("message");
				%>
				var msg = '<%=message%>';
				var reportDiv = document.getElementById("reportDiv");
				if(msg!=null && trim(msg)!="" && trim(msg)!="null"){
					//处理动态内容msg
					var token = msg.split("[");
					var divMsg = "";
					if(token!=null && token.length>0){
						for(var i=1; i<token.length; i++){
							divMsg += "["+token[i]+"<br>";
						}
						reportDiv.innerHTML=divMsg;
					}
				}
			}
		</script>
	</head>

	<body onload="showImportReport()">
		<center>
			<form name="form" action="<%=basePath%>hsp/hspEquipBaseinfo.do?verbId=elsImport" method="post" enctype="multipart/form-data">
				<input id="file" type="file" name="file">
				<input type="submit" value="<bean:message  bundle="hsp.hspLocale" key="hsp.jsp.common.import1"/>" onclick="return upload()">
			</form>
			<div id="reportDiv">
				<bean:message  bundle="hsp.hspLocale" key="hsp.jsp.common.warn15"/><br>	
				<div align="left">
					<font color="red"><bean:message  bundle="hsp.hspLocale" key="hsp.jsp.common.warn18"/>:</font>
					<img src="http://localhost:7001/hsp/include/images/hspEquipBaseInfo.png"/>
				</div>
			</div>
		</center>
	</body>
</html>