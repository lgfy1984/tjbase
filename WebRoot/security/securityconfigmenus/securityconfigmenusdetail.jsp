<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<jsp:useBean id="securityConfigMenusForm" scope="request" class="com.tianjian.security.struts.form.SecurityConfigMenusForm" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<HEAD>
	<TITLE> ZTREE DEMO - select menu</TITLE>
	<meta http-equiv="content-type" content="text/html; charset=UTF-8">
	<link rel="stylesheet" href="<%=request.getContextPath() %>/include/css/demo.css" type="text/css">
	<link rel="stylesheet" href="<%=request.getContextPath() %>/include/css/zTreeStyle/zTreeStyle.css" type="text/css">
	<script type="text/javascript" src="<%=request.getContextPath() %>/include/javascript/jquery-1.4.4.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/include/javascript/jquery.ztree.core-3.5.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/include/javascript/jquery.ztree.excheck-3.5.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/include/javascript/jquery.ztree.exedit-3.5.js"></script>
	<SCRIPT type="text/javascript">
		var setting = {
			view: {
				dblClickExpand: false
			},
			check: {
				enable: true
			},
			callback: {
				onRightClick: OnRightClick
			}
		};
		var zNodes =[
		<%	
			int totalShows = 0;
			if (securityConfigMenusForm.getMenuIds() != null && securityConfigMenusForm.getMenuIds().length > 0) {			
				for(int i = 0; i < securityConfigMenusForm.getMenuIds().length; i++){//第一层菜单
					String menuId = securityConfigMenusForm.getMenuIds()[i];
					String parentMenuId = securityConfigMenusForm.getParentMenuIds()[i];
					String menuDetail = securityConfigMenusForm.getMenuDetails()[i];
					totalShows = totalShows + 1;
		%> 
					<%
						if(parentMenuId!=null&&parentMenuId.equals("-1")){
						
					%>
						{id:"<%=menuId%>", name:"<%=menuDetail%>", open:true,children:[
						<%
							for(int a = 0; a < securityConfigMenusForm.getMenuIds().length; a++){//循环读取第二层菜单
								String Id = securityConfigMenusForm.getMenuIds()[a];
								String parentId = securityConfigMenusForm.getParentMenuIds()[a];
								String Detail = securityConfigMenusForm.getMenuDetails()[a];
							if(parentId!=null&&parentId.equals(menuId)){
						%>
								{id:"<%=Id%>", name:"<%=Detail%>",children:[
									<%
										for(int b = 0; b < securityConfigMenusForm.getMenuIds().length; b++){//循环读取第三层菜单
											String Id1 = securityConfigMenusForm.getMenuIds()[b];
											String parentId1 = securityConfigMenusForm.getParentMenuIds()[b];
											String Detail1 = securityConfigMenusForm.getMenuDetails()[b];
											if(parentId1!=null&&parentId1.equals(Id)){
									%>
										{id:"<%=Id1%>", name:"<%=Detail1%>"},
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
					%>
		<%	        
				}
			}
		%>
		];
		function OnRightClick(event, treeId, treeNode) {
			if (!treeNode && event.target.tagName.toLowerCase() != "button" && $(event.target).parents("a").length == 0) {
				zTree.cancelSelectedNode();
				showRMenu("root", event.clientX, event.clientY);
			} else if (treeNode && !treeNode.noR) {
				zTree.selectNode(treeNode);
				showRMenu("node", event.clientX, event.clientY);
			}
		}

		function showRMenu(type, x, y) {
			$("#rMenu ul").show();
			if (type=="root") {
				$("#m_detail").hide();
				$("#m_update").hide();
				$("#m_delete").hide();
			} else {
				$("#m_add").show();
				$("#m_delete").show();
				$("#m_update").show();
				$("#m_detail").show();
			}
			rMenu.css({"top":y+"px", "left":x+"px", "visibility":"visible"});

			$("body").bind("mousedown", onBodyMouseDown);
		}
		function hideRMenu() {
			if (rMenu) rMenu.css({"visibility": "hidden"});
			$("body").unbind("mousedown", onBodyMouseDown);
		}
		function newXMLHttpRequest() {
				var xmlreq = false;
				if (window.XMLHttpRequest) {
					xmlreq = new XMLHttpRequest();		
				} else if (window.ActiveXObject) {
				    try {
			      		xmlreq = new ActiveXObject("Msxml2.XMLHTTP");
					} catch (e1) {
						try {
			        		xmlreq = new ActiveXObject("Microsoft.XMLHTTP");
						} catch (e2) {
						xmlreq = false;
				    	}
			    	}
				}	
				return xmlreq;
			}		
			function getReadyStateHandler(req) {
				return function () {
					if (req.readyState == 4) {
			      		if (req.status == 200) {
			      			//alert("删除成功"); 
						    //responseXmlHandler(req.responseXML);
						} else {
						    alert("HTTP error: " + req.status);
			      		}
			    	}
			  	}
			}
		function onBodyMouseDown(event){
			if (!(event.target.id == "rMenu" || $(event.target).parents("#rMenu").length>0)) {
				rMenu.css({"visibility" : "hidden"});
			}
		}
		var addCount = 1;
		function openLayer() {
			if (zTree.getSelectedNodes()[0]) {
				var menuId = zTree.getSelectedNodes()[0].id;
				var url = "<%=request.getContextPath()%>/security/securityConfigMenus.do?verbId=addInit&menuId="+menuId;//构建子菜单
				parent.openLayer(url);
			}else{
				var classId = document.getElementById("classId").value;
				var url = "<%=request.getContextPath()%>/security/securityConfigMenus.do?verbId=addClassId&classId="+classId;//构建模块下面的菜单
				parent.openLayer(url);
			}
		}
		
		function addTreeNode(){
			var treeId = parent.document.getElementById("treeId").value;
			var treeName = parent.document.getElementById("treeName").value;
			hideRMenu();
			var newNode = {id:treeId,name:treeName};
			if (zTree.getSelectedNodes()[0]) {
				newNode.checked = zTree.getSelectedNodes()[0].checked;
				zTree.addNodes(zTree.getSelectedNodes()[0], newNode);
			} else {
				zTree.addNodes(null, newNode);
			}
		}
		
		function detailTree(){
			if (zTree.getSelectedNodes()[0]) {
				var menuId = zTree.getSelectedNodes()[0].id;
				var url = "<%=request.getContextPath()%>/security/securityConfigMenus.do?verbId=detail&menuId="+menuId;
				parent.openLayer(url);
			}
		}
		function updateTree(){
			if (zTree.getSelectedNodes()[0]) {
				var menuId = zTree.getSelectedNodes()[0].id;
				var url = "<%=request.getContextPath()%>/security/securityConfigMenus.do?verbId=updateMenu&menuId="+menuId;
				parent.openLayer(url);
			}
		}
		function removeTreeNode() {
			hideRMenu();
			var nodes = zTree.getSelectedNodes();
			var menuId = zTree.getSelectedNodes()[0].id;
			if (nodes && nodes.length>0) {
				if (nodes[0].children && nodes[0].children.length > 0) {
					var msg = "该菜单是父类菜单，不容许删除！";
					alert(msg);
					//if (confirm(msg)==true){
						//zTree.removeNode(nodes[0]);
					//}
				} else {
					deleteTree(menuId);
					zTree.removeNode(nodes[0]);
				}
			}
		}
		function deleteTree(menuId){
			var xmlHttp = newXMLHttpRequest();
			var url   = "<%=request.getContextPath()%>/security/securityConfigMenus.do?verbId=delete&menuId="+menuId;
			var sendTo = url;
			xmlHttp.open("GET", sendTo, true);
			var handlerFunction = getReadyStateHandler(xmlHttp);
			xmlHttp.onreadystatechange = handlerFunction;
			xmlHttp.send(null);
		}
		function checkTreeNode(checked) {
			var nodes = zTree.getSelectedNodes();
			if (nodes && nodes.length>0) {
				zTree.checkNode(nodes[0], checked, true);
			}
			hideRMenu();
		}
		function resetTree() {
			hideRMenu();
			$.fn.zTree.init($("#treeDemo"), setting, zNodes);
		}

		var zTree, rMenu;
		$(document).ready(function(){
			$.fn.zTree.init($("#treeDemo"), setting, zNodes);
			zTree = $.fn.zTree.getZTreeObj("treeDemo");
			rMenu = $("#rMenu");
		});
	</SCRIPT>
	<style type="text/css">
		div#rMenu {position:absolute; visibility:hidden; top:0; background-color: #555;text-align: left;padding: 2px;}
		div#rMenu ul li{
			margin: 1px 0;
			padding: 0 5px;
			cursor: pointer;
			list-style: none outside none;
			background-color: #DFDFDF;
		}
	</style>
 </HEAD>
	<body >	
	<form name="form" method="post" action="security/securityConfigMenus.do">
		<input type="hidden" name="classId" id="classId" value="<%=securityConfigMenusForm.getModId()%>" />
	<div class="content_wrap">
		<div class="">
			<ul id="treeDemo" class="ztree"></ul>
		</div>
	</div>
	<div id="rMenu">
		<ul>
			<li id="m_add" onclick="openLayer();">增加菜单</li>
			<li id="m_update" onclick="updateTree();">修改菜单</li>
			<li id="m_delete" onclick="removeTreeNode();">删除菜单</li>
			<li id="m_detail" onclick="detailTree();">详细菜单</li>
			 <!--  <li id="m_check" onclick="checkTreeNode(true);">Check节点</li>
			<li id="m_unCheck" onclick="checkTreeNode(false);">unCheck节点</li>
			<li id="m_reset" onclick="resetTree();">恢复zTree</li>-->
		</ul>
	</div>
	</form>
	</body>
</html>