<%@ page contentType="text/html; charset=UTF-8"%>
<jsp:useBean id="data" scope="request" class="com.tianjian.hsp.struts.form.OrgMenuForm" />
<html>
	<head>
		<%
		if (request.getServerPort() == 80) {
		%>
		<base href="http://<%=request.getServerName()%><%=request.getContextPath()%>/">
		<%
		} else {
		%>
		<base href="http://<%=request.getServerName()%>:<%=request.getServerPort()%><%=request.getContextPath()%>/">
		<%
		}
		%>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>科室-menu</title>		
		<link rel="stylesheet" href="<%=request.getContextPath() %>/include/css/demo.css" type="text/css"/>
		<link rel="stylesheet" href="<%=request.getContextPath() %>/include/css/zTreeStyle/zTreeStyle.css" type="text/css"/>
		<link rel="stylesheet" href="<%=request.getContextPath() %>/include/css/searchsuggest.css" />
		<script src="<%=request.getContextPath() %>/include/javascript/searchsuggest.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath() %>/include/javascript/jquery-1.4.4.min.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath() %>/include/javascript/jquery.ztree.core-3.5.js"></script>
		<!-- <script type="text/javascript" src="<%=request.getContextPath() %>/include/javascript/jquery.ztree.excheck-3.5.js"></script> -->
		<script type="text/javascript" src="<%=request.getContextPath() %>/include/javascript/jquery.ztree.exedit-3.5.js"></script>
		<script language="javascript">
		var setting = {
			async: { 
				enable: true,//异步处理

				//contentType: "application/json",//提交参数方式，这里 JSON 格式，默认form格式
				
				url: '<%=request.getContextPath()%>/hsp/orgMenu.do?verbId=childNodeDept',//异步获取json格式数据的路径

				autoParam: ["id","name","orgId","itemCode","rMenuFlag","hspStaffId","deptCode"]//异步加载时需要提交的参数，多个用逗号分隔
							
			},
					
			data: {
				simpleData: {
					enable: true
				}
			},
			
			view: {
				dblClickExpand: true,
				selectedMulti: false
			},
			edit: {
				drag: {
					autoExpandTrigger: true,
					prev: dropPrev,
					inner: dropInner,
					next: dropNext
				},
				enable: true,
				showRemoveBtn: false,
				showRenameBtn: false
			},
			check: {
				enable: false
			},
			callback: {
				onRightClick: OnRightClick,
				beforeDrag: beforeDrag,
				beforeDrop: beforeDrop,
				beforeDragOpen: beforeDragOpen,
				onDrag: onDrag,
				onDrop: onDrop,
				onClick: zTreeOnClick			
			}			
		};

		var zNodes =[
			{name:"机构列表", id:"1",pid:"0",isParent:true,orgId:"null",itemCode:"null",drag:false,dropInner:false}
		];
		
		function dropPrev(treeId, nodes, targetNode) {
			var pNode = targetNode.getParentNode();
			if (pNode && pNode.dropInner === false) {
				return false;
			} else {
				for (var i=0,l=curDragNodes.length; i<l; i++) {
					var curPNode = curDragNodes[i].getParentNode();
					if (curPNode && curPNode !== targetNode.getParentNode() && curPNode.childOuter === false) {
						return false;
					}
				}
			}
			return true;
		}
		function dropInner(treeId, nodes, targetNode) {
			if (targetNode && targetNode.dropInner === false) {
				return false;
			} else {
				for (var i=0,l=curDragNodes.length; i<l; i++) {
					if (!targetNode && curDragNodes[i].dropRoot === false) {
						return false;
					} else if (curDragNodes[i].parentTId && curDragNodes[i].getParentNode() !== targetNode && curDragNodes[i].getParentNode().childOuter === false) {
						return false;
					}
				}
			}
			return true;
		}
		function dropNext(treeId, nodes, targetNode) {
			var pNode = targetNode.getParentNode();
			if (pNode && pNode.dropInner === false) {
				return false;
			} else {
				for (var i=0,l=curDragNodes.length; i<l; i++) {
					var curPNode = curDragNodes[i].getParentNode();
					if (curPNode && curPNode !== targetNode.getParentNode() && curPNode.childOuter === false) {
						return false;
					}
				}
			}
			return true;
		}
		var log, className = "dark", curDragNodes, autoExpandNode;
		function beforeDrag(treeId, treeNodes) {
			className = (className === "dark" ? "":"dark");
			for (var i=0,l=treeNodes.length; i<l; i++) {
				if (treeNodes[i].drag === false) {
					curDragNodes = null;
					return false;
				} else if (treeNodes[i].parentTId && treeNodes[i].getParentNode().childDrag === false) {
					curDragNodes = null;
					return false;
				}
			}
			curDragNodes = treeNodes;
			return true;
		}
		function zTreeOnClick(event, treeId, treeNode) {
            var url = treeNode.uri;
            if(url!=null&&url!=""){
                url = "<%=request.getContextPath()%>/"+url;
                self.parent.frames["righFrame"].location.href= url;
            }          
        }

		function beforeDragOpen(treeId, treeNode) {
			autoExpandNode = treeNode;
			return true;
		}
		function beforeDrop(treeId, treeNodes, targetNode, moveType, isCopy) {
			className = (className === "dark" ? "":"dark");
			return true;
		}
		function onDrag(event, treeId, treeNodes) {
			className = (className === "dark" ? "":"dark");
			//alert("[ onDrag ]&nbsp;&nbsp;&nbsp;&nbsp; drag: " + treeNodes.length + " nodes." );
		}
		function onDrop(event, treeId, treeNodes, targetNode, moveType, isCopy) {
			className = (className === "dark" ? "":"dark");
			var curNode = treeNodes[0];
			var prNode = treeNodes[0].getParentNode();
			var orgId = prNode.orgId;
			var deptCode = prNode.deptCode;
			var hspStaffId = curNode.hspStaffId;
			//alert("机构Id："+orgId+";科室代码："+deptCode+ ";人员Id："+hspStaffId);
			var url = "<%=request.getContextPath()%>/hsp/orgMenu.do?verbId=moveStaff&orgId="+orgId+"&deptCode="+deptCode+"&hspStaffId="+hspStaffId;
			moveTree(url);
		}
		
		$(document).ready(function(){
			$.fn.zTree.init($("#treeDemo"), setting, zNodes);
		});

		function OnRightClick(event, treeId, treeNode) {
			if (!treeNode && event.target.tagName.toLowerCase() != "button" && $(event.target).parents("a").length == 0) {
				zTree.cancelSelectedNode();
				showRMenu("root", $(document).scrollLeft()+event.clientX, $(document).scrollTop()+event.clientY);
			} else if (treeNode && !treeNode.noR) {
				zTree.selectNode(treeNode);
				//showRMenu("node", event.clientX, event.clientY);
				if(treeNode.rMenuFlag=="1"){
				    showRMenu1("node", $(document).scrollLeft()+event.clientX, $(document).scrollTop()+event.clientY);
				}else if(treeNode.rMenuFlag=="2"){
				    showRMenu2("node", $(document).scrollLeft()+event.clientX, $(document).scrollTop()+event.clientY);
				}else if(treeNode.rMenuFlag=="4"){
				    showRMenu4("node", $(document).scrollLeft()+event.clientX, $(document).scrollTop()+event.clientY);
				}else{
				   showRMenu0("node", $(document).scrollLeft()+event.clientX, $(document).scrollTop()+event.clientY);
				}
			}
		}

		function showRMenu(type, x, y) {
			$("#rMenu ul").show();
			if (type=="root") {
				$("#m_add").hide();
				$("#m_modify").hide();
				$("#m_del").hide();				
			} else {
				$("#m_add").show();
				$("#m_modify").show();
				$("#m_del").show();				
			}
			$("#rMenu").css({"top":y+"px", "left":x+"px", "visibility":"visible"});

			$("body").bind("mousedown", onBodyMouseDown);
		}
		function showRMenu0(type, x, y) {
			$("#rMenu ul").show();
			if (type=="root") {
				$("#m_add").hide();
				$("#m_modify").hide();
				$("#m_del").hide();		
				$("#m_add1").hide();	
				$("#m_modify1").hide();
				$("#m_del1").hide();		
			} else {
				$("#m_add").hide();
				$("#m_modify").hide();
				$("#m_del").hide();	
				$("#m_add1").hide();	
				$("#m_modify1").hide();
				$("#m_del1").hide();			
			}
			$("#rMenu").css({"top":y+"px", "left":x+"px", "visibility":"visible"});

			$("body").bind("mousedown", onBodyMouseDown);
		}
		function showRMenu2(type, x, y) {
			$("#rMenu ul").show();
			if (type=="root") {
			    $("#m_add").hide();
				$("#m_modify").hide();
				$("#m_del").hide();	
				$("#m_add1").hide();	
				$("#m_modify1").hide();
				$("#m_del1").hide();			
			} else {
			    $("#m_add").hide();
				$("#m_modify").show();
				$("#m_del").show();		
				$("#m_add1").hide();	
				$("#m_modify1").hide();
				$("#m_del1").hide();		
			}
			$("#rMenu").css({"top":y+"px", "left":x+"px", "visibility":"visible"});

			$("body").bind("mousedown", onBodyMouseDown);
		}
		function showRMenu1(type, x, y) {
			$("#rMenu ul").show();
			if (type=="root") {
				$("#m_add").hide();	
				$("#m_modify").hide();
				$("#m_del").hide();	
				$("#m_add1").hide();	
				$("#m_modify1").hide();
				$("#m_del1").hide();		
			} else {
				$("#m_add").show();	
				$("#m_modify").hide();
				$("#m_del").hide();	
				$("#m_add1").hide();	
				$("#m_modify1").hide();
				$("#m_del1").hide();		
			}
			$("#rMenu").css({"top":y+"px", "left":x+"px", "visibility":"visible"});

			$("body").bind("mousedown", onBodyMouseDown);
		}
		function showRMenu3(type, x, y) {
			$("#rMenu ul").show();
			if (type=="root") {
			    $("#m_add").hide();
				$("#m_modify").hide();
				$("#m_del").hide();	
				$("#m_add1").hide();	
				$("#m_modify1").hide();
				$("#m_del1").hide();			
			} else {
			    $("#m_add").hide();
				$("#m_modify").hide();
				$("#m_del").hide();		
				$("#m_add1").hide();	
				$("#m_modify1").show();
				$("#m_del1").show();		
			}
			$("#rMenu").css({"top":y+"px", "left":x+"px", "visibility":"visible"});

			$("body").bind("mousedown", onBodyMouseDown);
		}
		function showRMenu4(type, x, y) {
			$("#rMenu ul").show();
			if (type=="root") {
			    $("#m_add").hide();
				$("#m_modify").hide();
				$("#m_del").hide();	
				$("#m_add1").hide();	
				$("#m_modify1").hide();
				$("#m_del1").hide();			
			} else {
			    $("#m_add").hide();
				$("#m_modify").hide();
				$("#m_del").hide();		
				$("#m_add1").hide();	
				$("#m_modify1").hide();
				$("#m_del1").hide();		
			}
			$("#rMenu").css({"top":y+"px", "left":x+"px", "visibility":"visible"});

			$("body").bind("mousedown", onBodyMouseDown);
		}
		
		function hideRMenu() {
			if ($("#rMenu")) $("#rMenu").css({"visibility": "hidden"});
			$("body").unbind("mousedown", onBodyMouseDown);
		}
		function onBodyMouseDown(event){
			if (!(event.target.id == "rMenu" || $(event.target).parents("#rMenu").length>0)) {
				$("#rMenu").css({"visibility" : "hidden"});
			}
		}
		function modifyDept(){
		    hideRMenu();
		    var nodes = zTree.getSelectedNodes();
		    if (nodes && nodes.length>0) {
		        var orgId = nodes[0].orgId; 
		        var deptCode = nodes[0].deptCode;
		        var menuId = nodes[0].id;
		        var pId = nodes[0].pId;
				var url = "<%=request.getContextPath()%>/hsp/orgMenu.do?verbId=modifyinit&orgId="+orgId+"&deptCode="+deptCode+"&menuId="+menuId+"&pId="+pId;
		        self.parent.frames["righFrame"].location.href= url;
			}
		}
		function modifyNode(treeName){
		    var nodes = zTree.getSelectedNodes();
			if (nodes && nodes.length>0) {
			    nodes[0].name = treeName;
			    zTree.updateNode(nodes[0]);			    
			}
		}
		function delDept(){
		    hideRMenu();
		    var nodes = zTree.getSelectedNodes();
			if (nodes && nodes.length>0) {
				if (nodes[0].children && nodes[0].children.length > 0) {
					var msg = "该菜单是父类菜单,请先删除下面子菜单！";
					alert(msg);
				} else {
				    if (confirm("确定删除此科室？")){
				        var orgId = nodes[0].orgId;
					    var deptCode = nodes[0].deptCode;
						deleteTree(orgId,deptCode);
						zTree.removeNode(nodes[0]);
					}
				}
			}
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
		function moveTree(url){
		    var xmlHttp = newXMLHttpRequest();
			var sendTo = url;
			xmlHttp.open("post", sendTo, true);
			var handlerFunction = getReadyStateHandler(xmlHttp);
			xmlHttp.onreadystatechange = handlerFunction;
			xmlHttp.send(null);
		}
		function deleteTree(orgId,deptCode){
			var xmlHttp = newXMLHttpRequest();
			var url = "<%=request.getContextPath()%>/hsp/orgMenu.do?verbId=delDept&orgId="+orgId+"&deptCode="+deptCode;
			var sendTo = url;
			xmlHttp.open("post", sendTo, true);
			var handlerFunction = getReadyStateHandler(xmlHttp);
			xmlHttp.onreadystatechange = handlerFunction;
			xmlHttp.send(null);
		}
		function addDept(){
		    hideRMenu();
		    var nodes = zTree.getSelectedNodes();
		    if (nodes && nodes.length>0) {
		        var orgId = nodes[0].orgId; 
		        var orgName = nodes[0].name; 
		        var menuId = nodes[0].id;
				var url = "<%=request.getContextPath()%>/hsp/orgMenu.do?verbId=addInit&orgId="+orgId+"&orgName="+orgName+"&menuId="+menuId;
		        self.parent.frames["righFrame"].location.href= url;
			}
		}
		function addNode(treeName,orgId,deptCode){
		    var newNode = {name:treeName,rMenuFlag:"2",target:"righFrame",isParent:false,orgId:orgId,deptCode:deptCode,url:"hsp/orgMenu.do?verbId=detail&deptCode="+deptCode+"&orgId="+orgId};
		    if (zTree.getSelectedNodes()[0]) {
				newNode.checked = zTree.getSelectedNodes()[0].checked;
				zTree.addNodes(zTree.getSelectedNodes()[0], newNode);
			} else {
				zTree.addNodes(null, newNode);
			}
		}
		function addStaff(){
		    hideRMenu();
		    var nodes = zTree.getSelectedNodes();
		    if (nodes && nodes.length>0) {
		        var orgId = nodes[0].orgId; 
		        var menuId = nodes[0].id;
		        var deptCode = nodes[0].deptCode;
				var url = "<%=request.getContextPath()%>/hsp/orgMenu.do?verbId=staffAddInit&orgId="+orgId+"&menuId="+menuId+"&deptCode="+deptCode;
		        self.parent.frames["righFrame"].location.href= url;
			}
		}
		function addStaffNode(treeName,hspStaffId){
		    var newNode = {name:treeName,rMenuFlag:"3",target:"righFrame",isParent:false,hspStaffId:hspStaffId,url:"hsp/orgMenu.do?verbId=staffDetail&hspStaffId="+hspStaffId};
		    if (zTree.getSelectedNodes()[0]) {
				newNode.checked = zTree.getSelectedNodes()[0].checked;
				zTree.addNodes(zTree.getSelectedNodes()[0], newNode);
			} else {
				zTree.addNodes(null, newNode);
			}
		}
		function modifyStaff(){
		    hideRMenu();
		    var nodes = zTree.getSelectedNodes();
		    if (nodes && nodes.length>0) {
		        var hspStaffId = nodes[0].hspStaffId;
		        var menuId = nodes[0].id;
		        var pId = nodes[0].pId;
				var url = "<%=request.getContextPath()%>/hsp/orgMenu.do?verbId=staffUpdateInit&hspStaffId="+hspStaffId+"&menuId="+menuId+"&pId="+pId;
		        self.parent.frames["righFrame"].location.href= url;
			}
		}
		function delStaff(){
		    hideRMenu();
		    var nodes = zTree.getSelectedNodes();
			if (nodes && nodes.length>0) {
				if (nodes[0].children && nodes[0].children.length > 0) {
					var msg = "该菜单是父类菜单,请先删除下面子菜单！";
					alert(msg);
				} else {
				    if (confirm("确定删除此卫生人员？")){
				        var hspStaffId = nodes[0].hspStaffId;
					    deleteStaff(hspStaffId);
						zTree.removeNode(nodes[0]);
					}
				}
			}
		}
		function deleteStaff(hspStaffId){
		    var xmlHttp = newXMLHttpRequest();
			var url = "<%=request.getContextPath()%>/hsp/orgMenu.do?verbId=staffDel&hspStaffId="+hspStaffId;
			var sendTo = url;
			xmlHttp.open("post", sendTo, true);
			var handlerFunction = getReadyStateHandler(xmlHttp);
			xmlHttp.onreadystatechange = handlerFunction;
			xmlHttp.send(null);
		}
		var zTree, rMenu;
		$(document).ready(function(){
			$.fn.zTree.init($("#treeDemo"), setting, zNodes);
			zTree = $.fn.zTree.getZTreeObj("treeDemo");
			rMenu = $("#rMenu");
		});
		function query(){
		        var queryType = document.getElementById("queryType").value;
		        var queryKey = document.getElementById("queryKey").value;
		        var queryValue = document.getElementById("queryValue").value;
		        var flag = true;
		        if(queryType==null||queryType==""){
		            alert("请选择查询类型！");
		            flag = false;
		        }
		        if(queryKey==null||queryKey==""){
		            alert("请选择关键字类型！");
		            flag = false;
		        }
		        if(queryValue==null||queryValue==""){
		            alert("请输入查询内容！");
		            flag = false;
		        }
		        if(flag){
		            document.form.verbId.value = "query";    
			        document.form.submit();
		        }
		    }
		</script>
		<style type="text/css">
            div#rMenu {position:absolute; visibility:hidden; top:0; background-color: #555;text-align: left;padding: 2px;}
            div#rMenu ul li{
	            margin: 1px 0;
	            padding: 0 5px;
	            cursor: pointer;
	            list-style: none outside none;
	            background-color: #DFDFDF;
            }
            table {
				font-family: Arial, Helvetica, sans-serif, 宋体;
				font-size: 12px;
				color: #000;
				margin: 0 auto;
			}
	    </style>
	</head>
	<body>
	    <form name="form" method="post" action="<%=request.getContextPath()%>/hsp/orgMenu.do">
			<input type="hidden" id="verbId" name="verbId" value="query"/>
			<table>
			    <tr>
			        <td align="right">机构名称：</td>
			        <td align="left">
			            <input type="hidden" id="queryType" name="queryType" value="1"/>
			            <input type="text" id="itemName" name="itemName" size="22" value="${data.itemName}"
						onkeyup="GiveOptions(event, '<%=request.getContextPath()%>/searchSuggest.do', 'getHspName_00000000004', 'itemCode')"
						onkeydown="huanhang(event)" />
						<input type="hidden" name="itemCode" id="itemCode" value="${data.itemCode}"/>
						<span id="spanOutput" class="spanTextDropdown" style="display: none;"></span> 
			        </td>
			    </tr>
			    <tr>
			        <td>
			            <select id="queryKey" name="queryKey">
			                <option value="0">名称</option>
			                <option value="1">输入码</option>
			            </select>：
			        </td>
			        <td align="left">
			            <input type="text" id="queryValue" name="queryValue" size="15"/>
			            <input type="button" id="btnQuery" name="btnQuery" value="查询" onclick="query()"/>
			        </td>
			    </tr>
			</table>
		</form>
	    <div  class="ztree" id="treeDemo">
		
	    </div>
	    <div id="rMenu">
	        <ul>
		        <li id="m_add" onclick="addDept();">增加科室</li>
		        <li id="m_modify" onclick="modifyDept();">修改科室</li>
		        <li id="m_del" onclick="delDept();">删除科室</li>
		        <li id="m_add1" onclick="addStaff();">添加人员</li>
		        <li id="m_modify1" onclick="modifyStaff();">修改人员</li>
		        <li id="m_del1" onclick="delStaff();">删除人员</li>
		    </ul>
		</div>
	</body>
</html>
