<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<%
	if (request.getServerPort() == 80) {
%>
<base
	href="http://<%=request.getServerName()%><%=request.getContextPath()%>/"/>
	<%
		} else {
	%>
	<base
		href="http://<%=request.getServerName()%>:<%=request.getServerPort()%><%=request.getContextPath()%>/"/>
		<%
			}
		%>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
		<title>模块类别、模块、菜单列表</title>
		<link rel="stylesheet"
			href="<%=request.getContextPath()%>/include/css/demo.css"
			type="text/css" />
		<link rel="stylesheet"
			href="<%=request.getContextPath()%>/include/css/zTreeStyle/zTreeStyle.css"
			type="text/css" />
		<link rel="stylesheet"
			href="<%=request.getContextPath()%>/include/css/searchsuggest.css" />
		<script
			src="<%=request.getContextPath()%>/include/javascript/searchsuggest.js"
			defer="defer"></script>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/include/javascript/jquery-1.4.4.min.js"></script>
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/include/javascript/jquery.ztree.core-3.5.js"></script>
		<script language="javascript"
			src="<%=request.getContextPath()%>/include/javascript/utrim.js"
			defer="defer"></script>
		<script language="javascript">
		var NodeType = {ROOT:"0", PUBLIC_CLASS_1:"1", PUBLIC_CLASS_2:"2", PUBLIC:"3", MENU:"4"};
		var zNodes =[{id:'0', pId:'-1', name:'', isParent:true, type:NodeType.ROOT }];
		var setting = {
			async: {
				enable: true,
				url: getDataUrl,
				dataFilter: getDataFilter
			},
			
			data: {
				simpleData: {
					enable: true
				}
			},
			
			view: {
				expandSpeed: "",
				dblClickExpand: dblClickExpand,
				selectedMulti: false,
				fontCss : getFontCss
			},
			callback: {
				onRightClick: OnRightClick,
				onAsyncSuccess: afterAsync
			}			
		};
		
		function dblClickExpand(treeId, treeNode) {
			return treeNode.level > 0;
		}
		
		function afterAsync(event, treeId, treeNode){
			<%--用于查询定位--%>
			if(startQuery && ids != null){
				queryLocate();
			}
		}
		
		function getDataFilter(treeId, parentNode, responseData){
			if(responseData){
				for(var i =0; i < responseData.length; i++) {
					var treeNode = responseData[i];
					treeNode.isParent = true;
					if(treeNode.type == NodeType.PUBLIC_CLASS_1 || treeNode.type == NodeType.PUBLIC_CLASS_2){
						treeNode.iconOpen = "<%=request.getContextPath()%>/include/css/zTreeStyle/img/open_folder.png";
						treeNode.iconClose = "<%=request.getContextPath()%>/include/css/zTreeStyle/img/close_folder.png";
					}else if(treeNode.type == NodeType.MENU){
						treeNode.icon = "<%=request.getContextPath()%>/include/css/zTreeStyle/img/leaf.png";
					}
					
			      }
			}
			return responseData;
		}
		function getFontCss(treeId, treeNode){
			/*if(treeNode.type == NODE_TYPE_DEPT_LIST){
				return {color : "#8f8f8f"};
			}
			return {};*/
		}
		function getDataUrl(treeId, treeNode){
			var url = "<%=request.getContextPath()%>/security/menuTree.do?verbId=getChildNodes";
			var param = "&pid="+treeNode.id+"&type="+treeNode.type;
			if(treeNode.type == NodeType.PUBLIC_CLASS_1){
				param += "&publicClassId="+treeNode.id;
			}else if(treeNode.type == NodeType.PUBLIC_CLASS_2){
				param += "&publicClassId="+treeNode.id;
			}else if(treeNode.type == NodeType.PUBLIC){
				param += "&publicId="+treeNode.id;
			}else if(treeNode.type == NodeType.MENU){
				param += "&menuId="+treeNode.id;
			}
			return url + param;
		}
		
		function refreshNode(){
			hideRMenu();
			var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
			var nodes = treeObj.getSelectedNodes();
			if (nodes.length>0) {
				treeObj.reAsyncChildNodes(nodes[0], "refresh");
				self.parent.frames["rightFrame"].location.href= "<%=request.getContextPath()%>/security/menutree/right.jsp";
			}
			
		}
		
		<%--按唯一tId刷新节点--%>
		function refreshNodeByTId(treeId){
			var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
			var node = treeObj.getNodeByTId(treeId);
			if(node != null){
				treeObj.reAsyncChildNodes(node, "refresh");
			}
		}
		
		<%--按节点id，type刷新节点--%>
		function refreshByIdAndType(id, type){
			var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
			var node = treeObj.getNodesByFilter(function(treeNode){return treeNode.type==type && treeNode.id == id}, true);
			if(node != null){
				treeObj.reAsyncChildNodes(node, "refresh");
			}
		}
		
		<%--刷新根节点--%>
		function refreshRootNode(){
			var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
			var node = zTree.getNodeByParam("level", "0");
			if(node != null){
				treeObj.reAsyncChildNodes(node, "refresh");
			}
		}
		
		var log, className = "dark", curDragNodes, autoExpandNode;

		function OnRightClick(event, treeId, treeNode) {
			hideRMenu();
			$("#rMenu ul").hide();
			if(!treeNode){
				treeNode = zTree.getNodeByParam("level", "0");
			}
			if(!treeNode) { alert(1); return;}
			<%--使用该方法代替zTree.selectNode，用于防止子节点过多超过屏幕高度时选中父节点后被置顶而发生位置变动。--%>
			$.fn.zTree._z.view.selectNode(zTree.setting, treeNode);
			if(treeNode.type == NodeType.PUBLIC_CLASS_1){
				if(treeNode.class1ChildType == NodeType.PUBLIC_CLASS_2){
					$("#li_addPublic").hide();
					$("#li_addPublicClass2").show();
				}else if(treeNode.class1ChildType == NodeType.PUBLIC){
					$("#li_addPublic").show();
					$("#li_addPublicClass2").hide();
				}else{
					$("#li_addPublic").show();
					$("#li_addPublicClass2").show();
				}
				$("#publicClass1Menu").show();
            }else if(treeNode.type == NodeType.PUBLIC_CLASS_2){
				$("#publicClass2Menu").show();
            }else if(treeNode.type == NodeType.PUBLIC){
            	$("#publicMenu").show();
            }else if(treeNode.type == NodeType.MENU){
            	$("#menuMenu").show();
            }else{
            	$("#rootMenu").show();
            }
            var x = $(document).scrollLeft()+event.clientX;
            var y = $(document).scrollTop()+event.clientY;
            var height = $("#rMenu").height();
            var width = $("#rMenu").width();
            if(x + width > $(document).width()){
            	x -= width;
            }
            if(y + height > $(document).height()){
            	y -= height;
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
		
		function menuSelected(operate, type){
			hideRMenu();
			var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
			var nodes  = treeObj.getSelectedNodes();
			var treeNode;
			if(nodes != null && nodes.length > 0){
				treeNode = nodes[0];
			}
			var url;
			if(type == NodeType.PUBLIC_CLASS_1){
				url = "<%=request.getContextPath()%>/security/securityConfigPublicClass.do?useForTree=1";
				if(operate == "delete"){
					url += "&verbId=delete&idHidden="+treeNode.id;
				}
				if(operate == "add"){
					url += "&verbId=addInit";
				}else if(operate == "modify"){
					url += "&verbId=updateInit&idHidden="+treeNode.id;
				}else if(operate == "detail"){
					url += "&verbId=detail&idHidden="+treeNode.id;
				}
			}else if(type == NodeType.PUBLIC_CLASS_2){
				url = "<%=request.getContextPath()%>/security/securityConfigPublicClass.do?useForTree=1";
				if(operate == "delete"){
					url += "&verbId=delete&idHidden="+treeNode.id;
				}
				if(operate == "add"){
					url += "&verbId=addInit&parentId="+treeNode.id;
				}else if(operate == "modify"){
					url += "&verbId=updateInit&idHidden="+treeNode.id;
				}else if(operate == "detail"){
					url += "&verbId=detail&idHidden="+treeNode.id;
				}
			}else if(type == NodeType.PUBLIC){
				url = "<%=request.getContextPath()%>/security/securityConfigPublic.do?useForTree=1";
				if(operate == "delete"){
					url += "&verbId=delete&idHidden="+treeNode.id;
				}
				if(operate == "add"){
					url += "&verbId=addInit&classId="+treeNode.id;
				}else if(operate == "modify"){
					url += "&verbId=updateInit&idHidden="+treeNode.id;
				}else if(operate == "detail"){
					url += "&verbId=detail&idHidden="+treeNode.id;
				}
			}else if(type == NodeType.MENU){
				url = "<%=request.getContextPath()%>/security/securityConfigMenus.do?useForTree=1";
				if(operate == "delete"){
					url += "&verbId=delete&menuId="+treeNode.id;
				}
				if(operate == "add"){
					if(treeNode.type == NodeType.PUBLIC){
						url += "&verbId=addClassId&classId="+treeNode.id;
					}else if(treeNode.type == NodeType.MENU){
						url += "&verbId=addInit&menuId="+treeNode.id;
					}
				}else if(operate == "modify"){
					url += "&verbId=updateInit&idHidden="+treeNode.id;
				}else if(operate == "detail"){
					url += "&verbId=detail&menuId="+treeNode.id;
				}
			}
			if(operate == "delete"){
				self.parent.frames["rightFrame"].location.href= "<%=request.getContextPath()%>/security/menutree/right.jsp";
				if(!confirm("您确定要删除 "+treeNode.name+" 吗？")){
					return false;
				}
				var xmlHttp = newXMLHttpRequest();
				var sendTo = url;
				xmlHttp.open("post", sendTo, true);
				var handlerFunction = getReadyStateHandler(xmlHttp, treeNode);
				xmlHttp.onreadystatechange = handlerFunction;
				xmlHttp.send(null);
			}else{
				self.parent.frames["rightFrame"].location.href= url;
			}
		}
		
		function getReadyStateHandler(req, treeNode) {
			return function () {
				if (req.readyState == 4) {
			      	if (req.status == 200) {
			      		try{
					    	var json = eval(req.responseText);
			      		}catch(e){
			      			alert("请重新登录！");
			      			return;
			      		}
					    	 if(json != null && json.length > 0){
						    	var obj = json[0];
							    if(obj.flag && obj.flag == 1){
							    	var parentNode = treeNode.getParentNode();
						    		zTree.removeNode(treeNode);
						    		<%-- 子节点删除完后，重新加载节点 --%>
						    		if(parentNode != null && parentNode.children.length == 0){
						    			parentNode.isParent = true;
						    			zTree.reAsyncChildNodes(parentNode, "refresh");
						    		}
							    }else if(obj.msg){
							    	alert(obj.msg);
							    }
						    }
					} else {
				        alert("HTTP error: " + req.status);
			      	}
			    }
			}
		}
		var zTree, rMenu;
		$(document).ready(function(){
			resize();
			$.fn.zTree.init($("#treeDemo"), setting, zNodes);
			zTree = $.fn.zTree.getZTreeObj("treeDemo");
			rMenu = $("#rMenu");
			var node = zTree.getNodeByParam("level", "0");
			zTree.expandNode(node, true, false, false, false);
		});
		$(window).resize(function(){resize();});
		function resize(){
			var top = $("#query_div").height();
			var height = $(window).height() - top - 25 + "px";
			$("#treeDemo").css("height", height);
		}
		var queryLevel;
        var queryCode;
        var queryName;
        var queryPinyinCode;
        var ids;
		function query(){
			queryLevel = $("input[name='queryLevel']:checked").val();
			queryCode = $("#queryCode").val();
			queryName = $("#queryName").val();
			queryPinyinCode = $("#queryPinyinCode").val();
	        if(trim(queryCode) == ""
	        		&& trim(queryName) == "" 
	        		&& trim(queryPinyinCode) == ""){
	        	alert("请至少输入一个查询条件!");
	        	return false;
        	}
	        queryByNum(1);
	    }
		function queryByNum(recordNum){
			 <%--防止点“下一个”按钮之前 查询条件被改变--%>
			 $("input[name='queryLevel']").val([queryLevel]);
		     $("#queryCode").val(queryCode);
		     $("#queryName").val(queryName);
		     $("#queryPinyinCode").val(queryPinyinCode);
			 $.ajax({
		        	type: "POST",
		        	processData: false,
		        	dataType: "json",
		        	url: "<%=request.getContextPath()%>/security/menuTree.do",
		        	data: "verbId=query&recordNum="+recordNum+"&queryLevel="+queryLevel+"&queryCode="+queryCode+"&queryName="+queryName+"&queryPinyinCode="+queryPinyinCode,
		        	error: function(a, b, c){
		        		alert(a + "-" + b + "-" + c);
		        	},
		        	success: function(json){
		        		if(json == null){
		        			$("#queryText").hide();
		        			$("#noResultText").show();
		        			return false;
		        		}else{
		        			$("#noResultText").hide();
		        			var count = json.count;
			        		var recordNum = json.recordNum;
			        		ids = json.ids;
			        		queryLocate();
			        		$("#totalNum").empty();<%--如果使用replaceWith 会出现第二次无法改变的问题--%>
			        		$("#totalNum").append(""+count);
			        		$("#recordNum").empty();
			        		$("#recordNum").append(""+recordNum);
			        		if(recordNum <=1){
			        			$("#previousBtn").attr("disabled", true);
			        		}else{
			        			$("#previousBtn").attr("disabled", false);
			        		}
			        		if(recordNum >= count){
			        			$("#nextBtn").attr("disabled", true);
			        		}else{
			        			$("#nextBtn").attr("disabled", false);
			        		}
			        		$("#queryText").show();
			        		resize();
		        		}
		        	}
		        });
		}
		
		function nextRecord(){
			var recordNum = Number($("#recordNum").text());
			var totalNum = Number($("#totalNum").text());
			if(recordNum >= 1 && recordNum < totalNum){
				queryByNum(++recordNum);
			}
		}
		
		function previousRecord(){
			var recordNum = Number($("#recordNum").text());
				var totalNum = Number($("#totalNum").text());
			if(recordNum > 1 && recordNum <= totalNum){
				queryByNum(--recordNum);
			}
		}
		var startQuery = false;
		function queryLocate(){
			startQuery = true;<%--正在查询标志，用于异步加回调事件--%>
			var node;
			while(ids.length > 1){
				<%--从最顶层但开始往下查找节点--%>
				var tempId = ids.pop();
				if(tempId == null)
					continue;
				<%--查询匹配id的节点--%>
				node = zTree.getNodeByParam("id", tempId, node);
				if(node == null) return;
				if(ids.length == 1){<%--当遍历到最底层的id时 --%>
					zTree.selectNode(node);
					if(node.open){<%--如果节点是展开状态，则将其折叠--%>
						zTree.expandNode(node, false);
					}
					startQuery = false;
					return;
				}
				if(!node.zAsync){<%--如果节点尚未异步加载，则进行加载后使用回调函数再调用节点法继续往下查找下一层节点--%>
					zTree.expandNode(node, true, false, false, true);
					return;
				}
			}
			startQuery = false;
		}
		
		</script>
		<style type="text/css">
div#rMenu {
	position: absolute;
	visibility: hidden;
	top: 0;
	background-color: #afafaf;
	text-align: center;
}

div#rMenu ul li {
	margin: 1px 1px 1px 1px;
	padding: 3px 30px;
	cursor: pointer;
	list-style: none outside none;
	background-color: #fafafa;
}

table {
	font-family: Arial, Helvetica, sans-serif, 宋体;
	font-size: 12px;
	color: #000;
	margin: 0 auto;
}

.rMenuImg {
	vertical-align: middle;
	margin-right: 8px;
}

#query_div {
	top: 0; left 0;
	border: 1px solid #617775;
	text-align: center;
}
.ztree li span.button.switch.level0 {visibility:hidden; width:1px;display: none}
.ztree li ul.level0 {padding:0; background:none;}
.ztree li a.level0{display:none};

</style>
</head>
<body>
	<span id="spanOutput" class="spanTextDropdown" style="display: none;"></span>
	<div id="query_div">
		<form name="form" autocomplete="off" onsubmit="return false;">
			<table>
				<tr>
					<td align="right">查询类型：</td>
					<td align="left">
						 <input type="radio" name="queryLevel" value="0" checked/>综合查询
						 <input type="radio" name="queryLevel" value="1"/>模块类别
						 <input type="radio" name="queryLevel" value="2"/>模块
						 <input type="radio" name="queryLevel" value="3"/>菜单
					</td>
				</tr>
				<tr>
					<td align="right">代码：</td>
					<td align="left"><input type="text" id="queryCode" size="22"
						value="" onkeydown="huanhang(event)" /></td>
				</tr>
				<tr>
					<td align="right">名称：</td>
					<td align="left">
						<input type="text" id="queryName" size="22"
							value="" onkeydown="huanhang(event)" />
					</td>
				</tr>
				<tr>
					<td align="right">拼音码：</td>
					<td align="left">
						<input type="text" id="queryPinyinCode" size="15"
							onkeydown="huanhang(event)" />
						<input type="button"
							id="btnQuery" value="查询" onclick="query()" />
					</td>
				</tr>
			</table>
		</form>
		<p id="queryText" style="display:none">
			共&nbsp;<span id="totalNum">0</span>个匹配&nbsp;
			当前第&nbsp;<span id="recordNum">0</span>个&nbsp;
			<input type="button" id="previousBtn"
				onclick="previousRecord();" value="上一个" />
			<input type="button"
				id="nextBtn" onclick="nextRecord();" value="下一个" />
		</p>
		<p id="noResultText" style="display:none">未找到匹配的搜索结果！</p>
	</div>
	<ul id="treeDemo" class="ztree"></ul>
	<div id="rMenu" style="white-space:nowrap">
		<ul id="rootMenu">
			<li onclick="refreshNode();"><img class="rMenuImg"
				src="<%=request.getContextPath()%>/include/css/zTreeStyle/img/refresh.png" />&nbsp;&nbsp;&nbsp;&nbsp;刷新列表&nbsp;&nbsp;&nbsp;&nbsp;</li>
			<li onclick="menuSelected('add', NodeType.PUBLIC_CLASS_1)"><img class="rMenuImg"
				src="<%=request.getContextPath()%>/include/css/zTreeStyle/img/add.png" />添加模块类别</li>
			
		</ul>
		<ul id="publicClass1Menu">
			<li onclick="refreshNode();"><img class="rMenuImg"
				src="<%=request.getContextPath()%>/include/css/zTreeStyle/img/refresh.png" />&nbsp;&nbsp;&nbsp;&nbsp;刷新列表&nbsp;&nbsp;&nbsp;&nbsp;</li>
			<li onclick="menuSelected('detail', NodeType.PUBLIC_CLASS_1)"><img
				class="rMenuImg"
				src="<%=request.getContextPath()%>/include/css/zTreeStyle/img/detail.png" />&nbsp;&nbsp;&nbsp;&nbsp;查看详细&nbsp;&nbsp;&nbsp;&nbsp;</li>
			<li onclick="menuSelected('modify', NodeType.PUBLIC_CLASS_1)"><img
				class="rMenuImg"
				src="<%=request.getContextPath()%>/include/css/zTreeStyle/img/edit.png" />修改模块类别</li>
			<li onclick="menuSelected('delete', NodeType.PUBLIC_CLASS_1)"><img
				class="rMenuImg"
				src="<%=request.getContextPath()%>/include/css/zTreeStyle/img/del.png" />删除模块类别</li>
			<li id="li_addPublicClass2" onclick="menuSelected('add', NodeType.PUBLIC_CLASS_2)"><img class="rMenuImg"
				src="<%=request.getContextPath()%>/include/css/zTreeStyle/img/add.png" />添加二级类别</li>
			<li id="li_addPublic" onclick="menuSelected('add', NodeType.PUBLIC)"><img class="rMenuImg"
				src="<%=request.getContextPath()%>/include/css/zTreeStyle/img/add.png" />&nbsp;&nbsp;&nbsp;&nbsp;添加模块&nbsp;&nbsp;&nbsp;&nbsp;</li>
		</ul>
		<ul id="publicClass2Menu">
			<li onclick="refreshNode();"><img class="rMenuImg"
				src="<%=request.getContextPath()%>/include/css/zTreeStyle/img/refresh.png" />&nbsp;&nbsp;&nbsp;&nbsp;刷新列表&nbsp;&nbsp;&nbsp;&nbsp;</li>
			<li onclick="menuSelected('detail', NodeType.PUBLIC_CLASS_2)"><img
				class="rMenuImg"
				src="<%=request.getContextPath()%>/include/css/zTreeStyle/img/detail.png" />&nbsp;&nbsp;&nbsp;&nbsp;查看详细&nbsp;&nbsp;&nbsp;&nbsp;</li>
			<li onclick="menuSelected('modify', NodeType.PUBLIC_CLASS_2)"><img
				class="rMenuImg"
				src="<%=request.getContextPath()%>/include/css/zTreeStyle/img/edit.png" />修改模块类别</li>
			<li onclick="menuSelected('delete', NodeType.PUBLIC_CLASS_2)"><img
				class="rMenuImg"
				src="<%=request.getContextPath()%>/include/css/zTreeStyle/img/del.png" />删除模块类别</li>
			<li onclick="menuSelected('add', NodeType.PUBLIC)"><img class="rMenuImg"
				src="<%=request.getContextPath()%>/include/css/zTreeStyle/img/add.png" />&nbsp;&nbsp;&nbsp;&nbsp;添加模块&nbsp;&nbsp;&nbsp;&nbsp;</li>
		</ul>
		<ul id="publicMenu">
			<li onclick="refreshNode();"><img class="rMenuImg"
				src="<%=request.getContextPath()%>/include/css/zTreeStyle/img/refresh.png" />刷新列表</li>
			<li onclick="menuSelected('detail', NodeType.PUBLIC)"><img
				class="rMenuImg"
				src="<%=request.getContextPath()%>/include/css/zTreeStyle/img/detail.png" />查看详细</li>
			<li onclick="menuSelected('modify', NodeType.PUBLIC)"><img
				class="rMenuImg"
				src="<%=request.getContextPath()%>/include/css/zTreeStyle/img/edit.png" />修改模块</li>
			<li onclick="menuSelected('delete', NodeType.PUBLIC)"><img
				class="rMenuImg"
				src="<%=request.getContextPath()%>/include/css/zTreeStyle/img/del.png" />删除模块</li>
			<li onclick="menuSelected('add', NodeType.MENU)"><img class="rMenuImg"
				src="<%=request.getContextPath()%>/include/css/zTreeStyle/img/add.png" />添加菜单</li>
		</ul>
		<ul id="menuMenu">
			<li onclick="refreshNode();"><img class="rMenuImg"
				src="<%=request.getContextPath()%>/include/css/zTreeStyle/img/refresh.png" />&nbsp;&nbsp;刷新列表&nbsp;&nbsp;</li>
			<li onclick="menuSelected('detail', NodeType.MENU)"><img
				class="rMenuImg"
				src="<%=request.getContextPath()%>/include/css/zTreeStyle/img/detail.png" />&nbsp;&nbsp;查看详细&nbsp;&nbsp;</li>
			<li onclick="menuSelected('modify', NodeType.MENU)"><img
				class="rMenuImg"
				src="<%=request.getContextPath()%>/include/css/zTreeStyle/img/edit.png" />&nbsp;&nbsp;修改菜单&nbsp;&nbsp;</li>
			<li onclick="menuSelected('delete', NodeType.MENU)"><img
				class="rMenuImg"
				src="<%=request.getContextPath()%>/include/css/zTreeStyle/img/del.png" />&nbsp;&nbsp;删除菜单&nbsp;&nbsp;</li>
			<li onclick="menuSelected('add', NodeType.MENU)"><img class="rMenuImg"
				src="<%=request.getContextPath()%>/include/css/zTreeStyle/img/add.png" />添加子菜单</li>
		</ul>
	</div>
</body>
</html>
