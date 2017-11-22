<%@page import="com.tianjian.util.Converter"%>
<%@page import="com.tianjian.security.bean.MenuTreeNode"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Iterator"%>
<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<jsp:useBean id="dataForm" scope="request"
	class="com.tianjian.security.struts.form.SecurityRoleVsMenusForm" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<%
	if (request.getServerPort() == 80) {
%>
<base
	href="http://<%=request.getServerName()%><%=request.getContextPath()%>/" />
<%
	} else {
%>
<base
	href="http://<%=request.getServerName()%>:<%=request.getServerPort()%><%=request.getContextPath()%>/" />
<%
	}
%>
<title><bean:message
		key="security.jsp.securityrolevsmenus.SecurityRoleVsMenus.title"
		bundle="security" />
</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="pragma" content="no-cache" />
<meta http-equiv="cache-control" content="no-cache" />
<meta http-equiv="expires" content="0" />
<link type="text/css" rev="stylesheet" rel="stylesheet"
	href="include/css/form.css" />
<link rel="stylesheet" href="include/css/demo.css" type="text/css">
<link rel="stylesheet" href="include/css/zTreeStyle/zTreeStyle.css"
	type="text/css">
<script type="text/javascript"
	src="include/javascript/jquery-1.4.4.min.js"></script>
<script type="text/javascript"
	src="include/javascript/jquery.ztree.core-3.5.js"></script>
<script type="text/javascript"
	src="include/javascript/jquery.ztree.excheck-3.5.js"></script>
<script language="javascript"
	src="<bean:message key="include.js.treeView.MzTreeView10_addradio_rmdaohang.path" bundle="security"/>"></script>
<script language="javascript"
	src="<bean:message key="security.js.SecurityMessage.path" bundle="security" />"></script>
<style type="text/css">
.ztree li span.button.switch.level0 {
	visibility: hidden;
	width: 1px;
	display: none;
}

.ztree li ul.level0 {
	padding-left: 0;
	background: none;
}

.ztree li a.level0 span {
	font-size: 20px;
}
</style>
<script language="javascript">
		var roles_setting = {
			async: {
				enable: true,
				url:"<%=request.getContextPath()%>/security/security_securityRoleVsMenus.do?verbId=getRoleTreeNodes"
			},
			view :{
				showIcon: showIconForRoleTree
			},
			check : {
				enable : true,
				chkStyle : "radio",
				radioType : "level"
			},
			data : {
				simpleData : {
					enable : true
				}
			},
			callback: {
				beforeCheck: beforeRoleCheck,
				onCheck: onRoleCheck
			}
		};

		var roles_zNodes =[{ id:0, pId:-1, name:"角色列表", nocheck:true, isParent:true}]
		function showIconForRoleTree(treeId, treeNode){
			return treeNode.level == 0;
		}
		
		function beforeRoleCheck(treeId, treeNode){
			menuTree.checkAllNodes(false);
		}
		function onRoleCheck(e, treeId, treeNode){
			if(!treeNode.checked){
				return;
			}
			 $.ajax({
		        	type: "POST",
		        	processData: false,
		        	dataType: "text/xml",
		        	url: "<%=request.getContextPath()%>/security/security_securityRoleVsMenus.do",
		        	data: "verbId=getMenusBySelectedRole&roleId="+treeNode.id,
		        	error: function(a, b, c){
		        		alert(a + "-" + b + "-" + c);
		        	},
		        	success: function(json){
		        		var ids = eval(json);
		        		if(ids != null){
	        				var nodes = menuTree.getNodesByFilter(function(treeNode){return treeNode.type==NodeType.MENU && $.inArray(treeNode.id, ids)>=0 }, false);
	        				for(var i = 0; i < nodes.length; i++){
	        					menuTree.checkNode(nodes[i], true, true, false);
	        				}
		        		}
		        	}
		        });
		}
		
		
		var NodeType = {ROOT:"0", PUBLIC_CLASS_1:"1", PUBLIC_CLASS_2:"2", PUBLIC:"3", MENU:"4"};
		var menus_setting = {
				async: {
					enable: true,
					url:getDataUrl,
					dataFilter: getDataFilter
				},
				check: {
					enable: true
				},
				data: {
					simpleData: {
						enable: true
					}
				},
				callback: {
					beforeCheck: beforeMenuCheck
				}
			};
		
		var menus_zNodes =[{id:0, pId:-1, name:"模块菜单列表", isParent:true, type:NodeType.ROOT}];
		function getDataUrl(treeId, treeNode){
			if(treeNode.type == NodeType.ROOT){
				return "<%=request.getContextPath()%>/security/security_securityRoleVsMenus.do?verbId=getMenuTreeNodes";
			}
			return null;
		}
		function getDataFilter(treeId, parentNode, responseData){
			if(responseData){
				for(var i =0; i < responseData.length; i++) {
					var treeNode = responseData[i];
					if(treeNode.type == NodeType.PUBLIC_CLASS_1 || treeNode.type == NodeType.PUBLIC_CLASS_2){
						treeNode.icon = "<%=request.getContextPath()%>/include/css/zTreeStyle/img/close_folder.png";
						treeNode.iconOpen = "<%=request.getContextPath()%>/include/css/zTreeStyle/img/open_folder.png";
						treeNode.iconClose = "<%=request.getContextPath()%>/include/css/zTreeStyle/img/close_folder.png";
					}else if(treeNode.type == NodeType.PUBLIC){
						treeNode.icon = "<%=request.getContextPath()%>/include/css/zTreeStyle/img/close_folder_yellow.png";
					}else if(treeNode.type == NodeType.MENU){
						treeNode.icon = "<%=request.getContextPath()%>/include/css/zTreeStyle/img/leaf.png";
					}
					
			      }
			}
			return responseData;
		}
		function beforeMenuCheck(treeId, treeNode){
			var selectedRoleNodes = roleTree.getCheckedNodes(true);
			if(selectedRoleNodes == null || selectedRoleNodes.length == 0){
				alert('<bean:message key="security.jsp.role.commom.warn" bundle="security"/>');
				return false;
			}
		}
		function save(){
			var selectedRoleNodes = roleTree.getCheckedNodes(true);
			if(selectedRoleNodes == null || selectedRoleNodes.length == 0){
				alert('<bean:message key="security.jsp.role.commom.warn" bundle="security"/>');
				return;
			}
			if(!window.confirm("确定要保存吗？")){
				return false;
			}
			var selectedMenuNodes = menuTree.getCheckedNodes(true);
			var menuIds = new Array();
			if(selectedMenuNodes != null){
				for(var i = 0; i < selectedMenuNodes.length; i++){
					var node = selectedMenuNodes[i];
					if(node.type == NodeType.MENU){
						menuIds.push(node.id);
					}
				}
			}
			 $.ajax({
		        	type: "POST",
		        	processData: false,
		        	dataType: "text/xml",
		        	url: "<%=request.getContextPath()%>/security/security_securityRoleVsMenus.do",
		        	data: "verbId=save&roleId="+selectedRoleNodes[0].id+"&menuIds="+menuIds.join(","),
		        	error: function(a, b, c){
		        		alert(a + "-" + b + "-" + c);
		        	},
		        	success: function(str){
		        		alert(str);
		        	}
		        });
		}
		$(document).ready(function() {
			resize();
			$.fn.zTree.init($("#rolesTree"), roles_setting, roles_zNodes);
			$.fn.zTree.init($("#menusTree"), menus_setting, menus_zNodes);
			roleTree = $.fn.zTree.getZTreeObj("rolesTree");
			var roleRootNode = roleTree.getNodeByParam("level", "0");
			roleTree.expandNode(roleRootNode, true, false, false, false);
			menuTree = $.fn.zTree.getZTreeObj("menusTree");
			var menuRootNode = menuTree.getNodeByParam("level", "0");
			menuTree.expandNode(menuRootNode, true, false, false, false);
		});
		$(window).resize(function(){resize();});
		function resize(){
			var height = $(window).height() - 30;
			$("#rolesTree").height(height);
			$("#menusTree").height(height);
		}
	</script>
</head>
<body>
	<div class="content_wrap" style="width:100%;">
		<div class="left" style="width:10%">&nbsp;</div>
		<div class="left" style="width: 30%">
			<ul id="rolesTree" class="ztree"></ul>
		</div>
		<div class="right" style="width:18%; padding-top: 10px;">
			<p>使用说明：</p>
			<p>1.在左侧选中一个角色。</p>
			<p>2.在模块菜单列表中选择好角色对应的菜单后，点击保存按钮。</p>
			<table style="margin: 10px;text-align: left">
				<tr>
					<td><img
						src="<%=request.getContextPath()%>/include/css/zTreeStyle/img/close_folder.png" />
					</td>
					<td>：模块类别</td>
				</tr>
				<tr>
					<td><img
						src="<%=request.getContextPath()%>/include/css/zTreeStyle/img/close_folder_yellow.png" />
					</td>
					<td>：模块</td>
				</tr>
				<tr>
					<td><img
						src="<%=request.getContextPath()%>/include/css/zTreeStyle/img/leaf.png" />
					</td>
					<td>：菜单</td>
				</tr>
			</table>
			<div class="btnSave"  style="text-align: left;margin: 50px 5px 5px 5px">
				<input type="button" 
					value="<bean:message key="security.jsp.commom.button1" bundle="security"/>"
					name="btnSaveForm" onclick="save()" />
			</div>
		</div>
		<div class="right" style="width: 40%">
			<ul id="menusTree" class="ztree"></ul>
		</div>
	</div>
</body>
</html>
