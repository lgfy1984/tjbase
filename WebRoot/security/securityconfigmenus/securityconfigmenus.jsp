<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<jsp:useBean id="securityConfigMenusForm" scope="request" class="com.tianjian.security.struts.form.SecurityConfigMenusForm" />
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
		<title><bean:message key="security.jsp.securityrolevsmenus.SecurityRoleVsMenus.title" bundle="security"/></title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<meta http-equiv="pragma" content="no-cache" />
		<meta http-equiv="cache-control" content="no-cache" />
		<meta http-equiv="expires" content="0" />
		<link rel="stylesheet" href="<%=request.getContextPath() %>/include/css/demo.css" type="text/css">
		<link rel="stylesheet" href="<%=request.getContextPath() %>/include/css/zTreeStyle/zTreeStyle.css" type="text/css">
		<script type="text/javascript" src="<%=request.getContextPath() %>/include/javascript/jquery-1.4.4.min.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath() %>/include/javascript/jquery.ztree.core-3.5.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath() %>/include/javascript/jquery.ztree.excheck-3.5.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath() %>/include/javascript/jquery.ztree.exedit-3.5.js"></script>
		<script language="javascript" src="<bean:message key="include.js.treeView.MzTreeView10_addradio_rmdaohang.path" bundle="security"/>"></script>
		<script language="javascript" src="<bean:message key="security.js.SecurityMessage.path" bundle="security" />"></script>
		<script language="javascript">
		function closeIframe(){
				document.getElementById("mask").style.visibility='hidden'
				document.getElementById("iframe1").style.display = "none";
				document.getElementById('menufr').contentWindow.addTreeNode();
			}
		function openLayer(url){
			iframe1.location=url;
			document.getElementById('iframe1').style.display='';
			document.getElementById('iframe1').style.visibility='visible';
			var obj = document.getElementById('iframe1');
	       	centerLayer(obj);
       	}
       	function centerLayer(obj) {
			var ocw = obj.clientWidth;
			var och = obj.clientHeight;
			var bsl = document.body.scrollLeft || document.documentElement.scrollLeft;
			var bst = document.body.scrollTop || document.documentElement.scrollTop;
			var bcw = document.body.clientWidth || document.documentElement.clientWidth;
			var bch = document.body.clientHeight || document.documentElement.clientHeight;
			var osl = bsl + Math.floor( ( bcw - ocw ) / 2 );
				osl = Math.max( bsl , osl );
			var ost = bst + Math.floor( ( bch - och ) / 2 );
				ost = Math.max( bst , ost );
			obj.style.left  = osl + 'px';
			obj.style.top   = ost + 'px';
		}
		
		function securityRoleVsMenus_pageSearch(id){	
			var ms = document.getElementsByName("modIds");
			if(ms==null || ms.length==0){
				alert('<bean:message key="security.jsp.securityrolevsmenus.SecurityRoleVsMenus.warn" bundle="security"/>');
			}else{
				for(var i=0;i < ms.length;i++)
				{
					if(ms[i].checked){
						document.form.modId.value = ms[i].value;
					}
				}
			}
			document.form.verbId.value = "search";
			document.form.submit();
		}
		var setting = {
									data: {
											simpleData: {
												enable: true
											}
										}
									};
									var zNodes =[
										<%	
											for(int i = 0; i < securityConfigMenusForm.getModIds().length-1; i++){
												String modId = securityConfigMenusForm.getModIds()[i];
												String parentModClassId = securityConfigMenusForm.getParentModClassIds()[i];
												String modName = securityConfigMenusForm.getModNames()[i];
												//String checkAble = data.getCheckAbles()[i];
												//if(checkAble.equals("true")){
													//temp = "ctrl:true;ctrlType:radio;ctrlName:modIds;ctrlValue:" + modId + ";ctrlChecked:false;" ;
												//}
												//String url = request.getContextPath()+"/security/securityConfigPublic.do?verbId=search&classId="+modId;
												if(parentModClassId!=null && parentModClassId.equals("-1")){
										%>
											{id:"<%=modId%>", pId:0, name:"<%=modName%>", open:true, collapse:false, children:[
												<%
													for(int a = 0; a < securityConfigMenusForm.getModIds().length; a++){//循环读取第二层菜单
														String Id = securityConfigMenusForm.getModIds()[a];
														String parentId = securityConfigMenusForm.getParentModClassIds()[a];
														String Detail = securityConfigMenusForm.getModNames()[a];
														String url = request.getContextPath()+"/security/securityConfigMenus.do?verbId=search&modId="+Id;
													if(parentId != null && parentId.equals(modId)){
												%>
													{id:"<%=Id%>",pId:1, name:"<%=Detail%>", open:true, collapse:false, url:"<%=url%>",target:"menufr",children:[
														<%
															for(int b = 0; b < securityConfigMenusForm.getModIds().length; b++){//循环读取第二层菜单
																String Id1 = securityConfigMenusForm.getModIds()[b];
																String parentId1 = securityConfigMenusForm.getParentModClassIds()[b];
																String Detail1 = securityConfigMenusForm.getModNames()[b];
																String url1 = request.getContextPath()+"/security/securityConfigMenus.do?verbId=search&modId="+Id1;
															if(parentId1 != null && parentId1.equals(Id)){
														%>
															{id:"<%=Id1%>",pId:2, name:"<%=Detail1%>", url:"<%=url1%>",target:"menufr"},
														<%
																}
															}
														%>
													]},
												<%
														}
													}
												%>
											]},
										<%	    
												}    
											}
										%>
									];
									$(document).ready(function(){
										$.fn.zTree.init($("#treeDemo"), setting, zNodes);
									});
	</script>
	<style>
		.tblSearch{
			width:85%;
			margin:0 auto;
			text-align:center;	
			border-collapse:collapse;
			border:1px solid #848284;
			background-color:#f9f9f9;
		}
		.tblSearch td{
			height:30px;
			line-height:30px;
			border:1px solid #848284;
		}
		.tblSearch td.tblTitle{
			font-size:14px;
			font-weight:bold;
			background:url(../include/images/bg.gif) repeat-x bottom;	
		}
		.tblView td.tblTitle{
			height:30px;
			font-weight:bold;
			text-align:center;
			background:url(../include/images/bg.gif) repeat-x bottom;
		}
		.tblView td.tblLable{
			width:20%;
			text-align:right;
			padding-right:5px;
			background:#F7F7F7;
		}
	</style>
	</head>
	<body>
		<iframe name="iframe1" id="iframe1"
			style="display: none; width:1200px; height: 364px; position:absolute; top:50; background-color:white; left:225; border:#DAEBF5 solid; border-width: 1 1 3 1; z-index: 101;"
			scrolling="no"  frameborder=0></iframe>
		<div id="mask"></div>
		<form name="form" method="post" target="menufr" action="security/securityConfigMenus.do">
			<input type="hidden" name="verbId" value="<%=securityConfigMenusForm.getVerbId()%>" />
			<input type="hidden" name="selectedRow" value="" />
			<input type="hidden" name="roleIdSelected" value="" />
			<input type="hidden" name="treeId" id="treeId" value="" />
			<input type="hidden" name="treeName" id="treeName" value="" />
			<input type="hidden" name="modId"  value="" />
			<table border="0" cellpadding="0" cellspacing="0" class="tblSearch" align="center">
				<tr>
					<td id="biaoti" class="tblTitle" colspan="2">
						菜单维护
					</td>
				</tr>
			</table>
			<table border="0" cellpadding="0" cellspacing="0" class="tblSearch" align="center">
				<tr class="tblTitle">
					<td width="45%" align="center" class="tblLable">
						模块
						<ul id="treeDemo" class="ztree"></ul>
					</td>
					<td  width="45%" align="center" class="tblLable">
						菜单
						<ul id="treeMenus" >
							<iframe src="" id="menufr" name="menufr" 
							allowtransparency="true" width="100%" height="380px" 
								 marginwidth="0" marginheight="0" hspace="0" vspace="0" frameborder="0" scrolling="yes"></iframe>
						</ul>
					</td>
				</tr>
			</table>
		</form>
	</body>
</html>
