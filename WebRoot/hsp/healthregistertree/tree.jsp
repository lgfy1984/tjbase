<%@page import="com.tianjian.util.Converter"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<%
	//末节点是卫生人员或者卫生设备
	String staffOrEquip = Converter.toBlank(request.getParameter("staffOrEquip"));
	boolean isQueryEquip = "equip".equals(staffOrEquip);

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
		<title>卫生人员树状列表</title>
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
		<script type="text/javascript"
			src="<%=request.getContextPath()%>/include/javascript/jquery.ztree.exedit-3.5.js"></script>
		<script language="javascript"
			src="<%=request.getContextPath()%>/include/javascript/utrim.js"
			defer="defer"></script>
		<script language="javascript">
		var NODE_TYPE_ROOT = "root";
		var NODE_TYPE_HSP = "hsp";
		var NODE_TYPE_DEPT_LIST = "deptlist";
		var NODE_TYPE_DEPT = "dept";
		var NODE_TYPE_OTHER_DEPT = "otherdept";
		var NODE_TYPE_STAFF = "staff";
		var NODE_TYPE_EQUIP = "equip";
		var zNodes =[{id:'1', pId:'0', name:'机构列表', isParent:true, type:'root'}];
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
		function afterAsync(event, treeId, treeNode){
			<%--用于查询定位--%>
			if(startQuery && ids != null){
				queryLocate();
			}
		}
		function dblClickExpand(treeId, treeNode) {
			return treeNode.level > 1;
		}
		
		function getDataFilter(treeId, parentNode, responseData){
			if(responseData){
				for(var i =0; i < responseData.length; i++) {
					var treeNode = responseData[i];
					if(treeNode.type == NODE_TYPE_HSP){
						treeNode.icon = "<%=request.getContextPath()%>/hsp/include/images/tree_hsp.png";
					}else if(treeNode.type == NODE_TYPE_DEPT){
						treeNode.icon = "<%=request.getContextPath()%>/hsp/include/images/tree_dept.png";
					}else if(treeNode.type == NODE_TYPE_OTHER_DEPT){
						treeNode.icon = "<%=request.getContextPath()%>/hsp/include/images/tree_other_dept.png";
					}else if(treeNode.type == NODE_TYPE_STAFF){
						treeNode.icon = "<%=request.getContextPath()%>/hsp/include/images/tree_staff.png";
					}
					if(treeNode.type == NODE_TYPE_STAFF || treeNode.type == NODE_TYPE_EQUIP){
						treeNode.isParent = false;
					}else{
						treeNode.isParent = true;
					}
			      }
			}
			return responseData;
		}
		function getFontCss(treeId, treeNode){
			if(treeNode.type == NODE_TYPE_DEPT_LIST){
				return {color : "#8f8f8f"};
			}
			return {};
		}
		function getDataUrl(treeId, treeNode){
			var curCount = (treeNode.nodes) ? treeNode.nodes.length : 0;
			var url = "<%=request.getContextPath()%>/hsp/healthRegisterTree.do?verbId=getChildNodes&staffOrEquip=<%=staffOrEquip%>";
			var param = "&pid="+treeNode.id+"&type="+treeNode.type;
			if(treeNode.type == NODE_TYPE_HSP){
				param += "&parentItemCode="+treeNode.hspCode+"&hspId="+treeNode.hspId;
			}else if(treeNode.type == NODE_TYPE_DEPT_LIST){
				param += "&hspId="+treeNode.hspId;
			}else if(treeNode.type == NODE_TYPE_DEPT){
				param += "&hspId="+treeNode.hspId+"&deptCode="+treeNode.deptCode;
			}else if(treeNode.type == NODE_TYPE_OTHER_DEPT){
				param += "&hspId="+treeNode.hspId;
			}
			return url + param;
		}
		
		function refreshNode(){
			hideRMenu();
			var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
			var nodes = treeObj.getSelectedNodes();
			if (nodes.length>0) {
				treeObj.reAsyncChildNodes(nodes[0], "refresh");
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
		function refreshByIdAndType(type, hspId, hspCode, deptCode, staffId){
			var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
			var node = treeObj.getNodesByFilter(function(treeNode){
								var flag = treeNode.type==type;
								if(hspId != null && $.trim(hspId).length > 0){
									flag = flag && treeNode.hspId == hspId;
								}
								if(hspCode != null && $.trim(hspCode).length > 0){
									flag = flag && treeNode.hspCode == hspCode;
								}
								if(deptCode != null && $.trim(deptCode).length > 0){
									flag = flag && treeNode.deptCode == deptCode;
								}
								if(staffId != null && $.trim(staffId).length > 0){
									flag = flag && treeNode.staffId == staffId;
								}
								return flag;
							}, true);
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
		var log, className = "dark", curDragNodes, autoExpandNode;
		
		function OnRightClick(event, treeId, treeNode) {
			hideRMenu();
			$("#rMenu ul").hide();
			if(treeNode){
				<%--使用该方法代替zTree.selectNode，用于防止子节点过多超过屏幕高度时选中父节点后被置顶而发生位置变动。--%>
				$.fn.zTree._z.view.selectNode(zTree.setting, treeNode);
				if(treeNode.type == NODE_TYPE_ROOT){
					$("#rootMenu").show();
	            }else if(treeNode.type == NODE_TYPE_HSP){
					$("#hspMenu").show();
	            }else if(treeNode.type == NODE_TYPE_DEPT_LIST){
	            	$("#deptlistMenu").show();
	            }else if(treeNode.type == NODE_TYPE_DEPT){
	            	$("#deptMenu").show();
	            }else if(treeNode.type == NODE_TYPE_OTHER_DEPT){
	            	$("#nulldeptMenu").show();
	            }else if(treeNode.type == NODE_TYPE_STAFF){
	            	$("#staffMenu").show();
	            }else if(treeNode.type == NODE_TYPE_EQUIP){
	            	$("#equipMenu").show();
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
		
		function modifyNode(treeName){
		    var nodes = zTree.getSelectedNodes();
			if (nodes && nodes.length>0) {
			    nodes[0].name = treeName;
			    zTree.updateNode(nodes[0]);			    
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
		function moveTree(url){
		    var xmlHttp = newXMLHttpRequest();
			var sendTo = url;
			xmlHttp.open("post", sendTo, true);
			var handlerFunction = getReadyStateHandler(xmlHttp);
			xmlHttp.onreadystatechange = handlerFunction;
			xmlHttp.send(null);
		}
		function menuSelected(operate, type){
			hideRMenu();
			var treeObj = $.fn.zTree.getZTreeObj("treeDemo");
			var nodes  = treeObj.getSelectedNodes();
			var treeNode;
			if(nodes != null && nodes.length > 0){
				treeNode = nodes[0];
			}
			if(operate == "delete"){
				var url = "<%=request.getContextPath()%>/hsp/healthRegisterTree.do?verbId="+operate+"&type="+type;
				if(type == NODE_TYPE_HSP){
	           		url += "&hspId="+treeNode.hspId;
	            }else if(type == NODE_TYPE_DEPT){
	            	url += "&hspId="+treeNode.hspId+"&deptCode="+treeNode.deptCode;
	            }else if(type == NODE_TYPE_OTHER_DEPT){
	            	url += "&hspId="+treeNode.hspId;
	            }else if(type == NODE_TYPE_STAFF){
	            	url += "&staffId="+treeNode.staffId;
	            }else if(type == NODE_TYPE_EQUIP){
	            	url += "&equipId="+treeNode.equipId;
	            }else{
	            	return false;
	            }
				if(!confirm("您确定要删除"+treeNode.name+"吗？")){
					return false;
				}
				var xmlHttp = newXMLHttpRequest();
				var sendTo = url;
				xmlHttp.open("post", sendTo, true);
				var handlerFunction = getReadyStateHandler(xmlHttp, treeNode);
				xmlHttp.onreadystatechange = handlerFunction;
				xmlHttp.send(null);
			}else{
				var url;
				if(type == NODE_TYPE_DEPT){
	            	url = "<%=request.getContextPath()%>/hsp/healthRegisterTree.do?type="+type+"&hspId="+treeNode.hspId+"&deptCode="+treeNode.deptCode;
	            }else if(type == NODE_TYPE_OTHER_DEPT){
	            	url = "<%=request.getContextPath()%>/hsp/healthRegisterTree.do?type="+type+"&hspId="+treeNode.hspId;
	            }
				if(operate == "add"){
					if(type == NODE_TYPE_HSP){
		            	url = "<%=request.getContextPath()%>/hsp/hspConfigBaseinfo.do?useForTree=1&tree_hspId="+treeNode.hspId+"&tree_hspCode="+treeNode.hspCode;
		            }else if(type == NODE_TYPE_STAFF){
		            	url = "<%=request.getContextPath()%>/hsp/hspStaffBaseinfo.do?useForTree=1&hspId="+treeNode.hspId+"&deptCode="+treeNode.deptCode;
		            }else if(type == NODE_TYPE_EQUIP){
		            	url = "<%=request.getContextPath()%>/hsp/hspEquipBaseinfo.do?useForTree=1&orgId="+treeNode.hspId+"&deptCode="+treeNode.deptCode;
		            }
					url += "&verbId=addInit&parentTId="+treeNode.tId;
				}else{
					if(type == NODE_TYPE_HSP){
		            	url = "<%=request.getContextPath()%>/hsp/hspConfigBaseinfo.do?useForTree=1&idHidden="+treeNode.hspId;
		            }else if(type == NODE_TYPE_STAFF){
		            	url = "<%=request.getContextPath()%>/hsp/hspStaffBaseinfo.do?useForTree=1&id="+treeNode.staffId;
		            }else if(type == NODE_TYPE_EQUIP){
		            	url = "<%=request.getContextPath()%>/hsp/hspEquipBaseinfo.do?useForTree=1&idHidden="+treeNode.equipId;
		            }
					if(operate == "modify"){
						url += "&verbId=updateInit&parentTId="+treeNode.parentTId;
					}else if(operate == "detail"){
						url += "&verbId=detail";
					}else{
						return;
					}
				}
				self.parent.frames["rightFrame"].location.href= url;
			}
		}
		
		function getReadyStateHandler(req, treeNode) {
			return function () {
				if (req.readyState == 4) {
			      	if (req.status == 200) {
					    var json = eval(req.responseText);
					    if(json != null && json.length > 0){
					    	var obj = json[0];
						    if(obj.flag && obj.flag == 1){
					    		zTree.removeNode(treeNode);
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
		var hspName;
        var hspCode;
        var deptCode;
        var deptName;
        <%
        	if(isQueryEquip){
        %>
        var equipCode;
        var equipName;
        <%
        	}else{
        %>
        var empNo;
        var empName;
        <%
        	}
        %>
		var ids;
		function query(){
	        hspName = $("#hspName").val();
	        hspCode = $("#hspCode").val();
	        deptCode = $("#deptCode").val();
	        deptName = $("#deptName").val();
      	<%
       	if(isQueryEquip){
        %>  
       		equipCode = $("#equipCode").val();
       		equipName = $("#equipName").val();
       	 	if(trim(hspName) == "" && trim(hspCode) == ""
	     		&& trim(deptCode) == "" && trim(deptName) == ""
	     		 && trim(equipCode) == "" && trim(equipName) == ""){
		        	alert("请至少输入一个查询条件!");
		        	return false;
	    	}
        <%
       	}else{
         %>
	        empNo = $("#empNo").val();
	        empName = $("#empName").val();
	        if(trim(hspName) == "" && trim(hspCode) == ""
        		&& trim(deptCode) == "" && trim(deptName) == ""
        		 && trim(empNo) == "" && trim(empName) == ""){
	        	alert("请至少输入一个查询条件!");
	        	return false;
	    	}
       	<%
       	}
        %>
	       
	        queryByNum(1);
	    }
		function queryByNum(recordNum){
			 <%--防止点“下一个”按钮之前 查询条件被改变--%>
			 $("#hspName").val(hspName);
		     $("#hspCode").val(hspCode);
		     $("#deptCode").val(deptCode);
		     $("#deptName").val(deptName);
		    <%
	       	if(isQueryEquip){
	        %>
	         $("#equipCode").val(equipCode);
		     $("#equipName").val(equipName);
	        <%
	       	}else{
	        %>
		     $("#empNo").val(empNo);
		     $("#empName").val(empName);
		    <%
	      		}
	       	%>
       		url = "verbId=query&recordNum="+recordNum+"&hspName="+hspName+"&hspCode="+hspCode+"&deptCode="+deptCode+"&deptName="+deptName;
	        <%
	       	if(isQueryEquip){
	        %>
        	url += "&equipCode="+equipCode+"&equipName="+equipName;
	        <%
	       	}else{
	        %>
       		url += "&empNo="+empNo+"&empName="+empName;
		    <%
	      		}
	       	%>
			 $.ajax({
		        	type: "POST",
		        	processData: false,
		        	dataType: "json",
		        	url: "<%=request.getContextPath()%>/hsp/healthRegisterTree.do",
		        	data: url,
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
		var startQuery = false;		<%--根据人员id， 科室代码， 机构代码--%>
		function queryLocate(){
			startQuery = true;<%--正在查询标志，用于异步加回调事件--%>
			var sId = ids[0];
			var dCode = ids[1];
			var hId = ids[2];
			var node;
			var queryLevel;
			if(sId != null){
				queryLevel = 1;<%--查询的是人员--%>
			}else if(dCode != null){
				queryLevel = 2;<%--查询的是科室--%>
			}else{
				queryLevel = 3;<%--查询的是机构--%>
			}
			while(ids.length > 3){
				<%--从最顶层机构开始往下查找节点--%>
				var hCode = ids.pop();
				if(hCode == null)
					continue;
				<%--查询匹配机构代码的机构节点--%>
				node = zTree.getNodesByFilter(function(treeNode){return treeNode.type==NODE_TYPE_HSP && treeNode.hspCode == hCode}, true);
				if(node == null) return;
				if(ids.length == queryLevel){<%--当遍历到最底层的机构代码时，如果查询的是机构--%>
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
			if(sId != null || dCode != null){
				<%--查询匹配机构id的科室列表节点--%>
				node = zTree.getNodesByFilter(function(treeNode){return treeNode.type==NODE_TYPE_DEPT_LIST && treeNode.hspId == hId}, true);
				if(node == null) return;
				if(!node.zAsync){<%--则进行加载，使用回调函数再次调用该方法继续往下执行法继续往下查找下一层节点--%>
					zTree.expandNode(node, true, false, false, true);
					return;
				}
				if(dCode == null){<%--如果科室代码为空--%>
					<%--查找匹配机构id的未登记科室节点--%>
					node = zTree.getNodesByFilter(function(treeNode){return treeNode.type==NODE_TYPE_OTHER_DEPT && treeNode.hspId == hId}, true);
					if(node == null) return;
					if(!node.zAsync){
						zTree.expandNode(node, true, false, false, true);
						return;
					}
				}else{
					<%--查找匹配机构id、科室代码的科室节点--%>
					node = zTree.getNodesByFilter(function(treeNode){return treeNode.type==NODE_TYPE_DEPT && treeNode.hspId == hId && treeNode.deptCode == dCode}, true);
					if(node == null) return;
					if(queryLevel == 2){<%--如果查询的是科室--%>
						zTree.selectNode(node);
						if(node.open){
							zTree.expandNode(node, false);
						}
						startQuery = false;
						return;
					}else{
						if(!node.zAsync){
							zTree.expandNode(node, true, false, false, true);
							return;
						}
					}
				}
				if(sId != null){
					<%
			       	if(isQueryEquip){
			        %>
			        node = zTree.getNodesByFilter(function(treeNode){return treeNode.type==NODE_TYPE_EQUIP && treeNode.equipId == sId}, true);
			        <%
			       	}else{
			        %>
			        node = zTree.getNodesByFilter(function(treeNode){return treeNode.type==NODE_TYPE_STAFF && treeNode.staffId == sId}, true);
				    <%
			      		}
			       	%>
					if(node == null) return;
					zTree.selectNode(node);
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

.ztree li span.button.switch.level0 {
	visibility: hidden;
	width: 1px;
}

.ztree li ul.level0 {
	padding-left: 17px;
	background: none;
}
</style>
</head>
<body>
	<span id="spanOutput" class="spanTextDropdown" style="display: none;"></span>
	<div id="query_div">
		<form name="form" autocomplete="off" onsubmit="return false;">
			<input type="hidden" name="staffOrEquip" value="<%=staffOrEquip %>"/>
			<table>
				<tr>
					<td align="right">机构：</td>
					<td align="left"><input type="text" id="hspName" size="22"
						value=""
						onkeyup="GiveOptions(event, '<%=request.getContextPath()%>/searchSuggest.do', 'getHspName_00000000002', 'hspCode')"
						onkeydown="huanhang(event)" /> <input type="hidden"
						name="hspCode" id="hspCode" value="" />
					</td>
				</tr>
				<tr>
					<td align="right">科室代码：</td>
					<td align="left"><input type="text" id="deptCode" size="22"
						value="" onkeydown="huanhang(event)" /></td>
				</tr>
				<tr>
					<td align="right">科室名称：</td>
					<td align="left">
						<input type="text" id="deptName" size="22"
							value="" onkeydown="huanhang(event)" />
					</td>
				</tr>
				<%
					if("equip".equals(staffOrEquip)){
				%>
				<tr>
					<td align="right">出厂编号：</td>
					<td align="left">
						<input type="text" id="equipCode" size="15"
							onkeydown="huanhang(event)" />
					</td>
				</tr>
				<tr>
					<td align="right">设备名称：</td>
					<td align="left">
						<input type="text" id="equipName" size="15"
							onkeydown="huanhang(event)" /> 
						<input type="button"
							id="btnQuery" value="查询" onclick="query()" />
					</td>
				</tr>
				<%
					}else{
				%>
				<tr>
					<td align="right">人员编码：</td>
					<td align="left">
						<input type="text" id="empNo" size="15"
							onkeydown="huanhang(event)" />
					</td>
				</tr>
				<tr>
					<td align="right">人员姓名：</td>
					<td align="left">
						<input type="text" id="empName" size="15"
							onkeydown="huanhang(event)" /> 
						<input type="button"
							id="btnQuery" value="查询" onclick="query()" />
					</td>
				</tr>
				<%
					}
				%>
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
				src="<%=request.getContextPath()%>/include/css/zTreeStyle/img/refresh.png" />刷新列表</li>
		</ul>
		<ul id="hspMenu">
			<li onclick="refreshNode();"><img class="rMenuImg"
				src="<%=request.getContextPath()%>/include/css/zTreeStyle/img/refresh.png" />刷新列表</li>
			<li onclick="menuSelected('detail', 'hsp')"><img
				class="rMenuImg"
				src="<%=request.getContextPath()%>/include/css/zTreeStyle/img/detail.png" />查看详细</li>
			<li onclick="menuSelected('modify', 'hsp')"><img
				class="rMenuImg"
				src="<%=request.getContextPath()%>/include/css/zTreeStyle/img/edit.png" />修改机构</li>
			<li onclick="menuSelected('delete', 'hsp')"><img
				class="rMenuImg"
				src="<%=request.getContextPath()%>/include/css/zTreeStyle/img/del.png" />删除机构</li>
			<li onclick="menuSelected('add', 'hsp')"><img class="rMenuImg"
				src="<%=request.getContextPath()%>/include/css/zTreeStyle/img/add.png" />添加机构</li>
		</ul>
		<ul id="deptlistMenu">
			<li onclick="refreshNode();"><img class="rMenuImg"
				src="<%=request.getContextPath()%>/include/css/zTreeStyle/img/refresh.png" />刷新列表</li>
			<li onclick="menuSelected('add', 'dept')"><img class="rMenuImg"
				src="<%=request.getContextPath()%>/include/css/zTreeStyle/img/add.png" />添加科室</li>
		</ul>
		<ul id="deptMenu">
			<li onclick="refreshNode();"><img class="rMenuImg"
				src="<%=request.getContextPath()%>/include/css/zTreeStyle/img/refresh.png" />刷新列表</li>
			<li onclick="menuSelected('detail', 'dept')"><img
				class="rMenuImg"
				src="<%=request.getContextPath()%>/include/css/zTreeStyle/img/detail.png" />查看详细</li>
			<li onclick="menuSelected('modify', 'dept')"><img
				class="rMenuImg"
				src="<%=request.getContextPath()%>/include/css/zTreeStyle/img/edit.png" />修改科室</li>
			<li onclick="menuSelected('delete', 'dept')"><img
				class="rMenuImg"
				src="<%=request.getContextPath()%>/include/css/zTreeStyle/img/del.png" />删除科室</li>
			<%
				if("equip".equals(staffOrEquip)){
			%>
				<li onclick="menuSelected('add', 'equip')"><img class="rMenuImg"
				src="<%=request.getContextPath()%>/include/css/zTreeStyle/img/add.png" />添加设备</li>
			<%
				}else{
			%>
			<li onclick="menuSelected('add', 'staff')"><img class="rMenuImg"
				src="<%=request.getContextPath()%>/include/css/zTreeStyle/img/add.png" />添加人员</li>
			<%
				}
			%>
		</ul>
		<ul id="nulldeptMenu">
			<li onclick="refreshNode();"><img class="rMenuImg"
				src="<%=request.getContextPath()%>/include/css/zTreeStyle/img/refresh.png" />刷新列表</li>
			<%
				if("equip".equals(staffOrEquip)){
			%>
				<li onclick="menuSelected('add', 'equip')"><img class="rMenuImg"
				src="<%=request.getContextPath()%>/include/css/zTreeStyle/img/add.png" />添加设备</li>
			<%
				}else{
			%>
			<li onclick="menuSelected('add', 'staff')"><img class="rMenuImg"
				src="<%=request.getContextPath()%>/include/css/zTreeStyle/img/add.png" />添加人员</li>
			<%
				}
			%>
		</ul>
		<ul id="staffMenu">
			<li onclick="menuSelected('detail', 'staff')"><img
				class="rMenuImg"
				src="<%=request.getContextPath()%>/include/css/zTreeStyle/img/detail.png" />查看详细</li>
			<li onclick="menuSelected('modify', 'staff')"><img
				class="rMenuImg"
				src="<%=request.getContextPath()%>/include/css/zTreeStyle/img/edit.png" />修改人员</li>
			<li onclick="menuSelected('delete', 'staff')"><img
				class="rMenuImg"
				src="<%=request.getContextPath()%>/include/css/zTreeStyle/img/del.png" />删除人员</li>
		</ul>
		<ul id="equipMenu">
			<li onclick="menuSelected('detail', 'equip')"><img
				class="rMenuImg"
				src="<%=request.getContextPath()%>/include/css/zTreeStyle/img/detail.png" />查看详细</li>
			<li onclick="menuSelected('modify', 'equip')"><img
				class="rMenuImg"
				src="<%=request.getContextPath()%>/include/css/zTreeStyle/img/edit.png" />修改设备</li>
			<li onclick="menuSelected('delete', 'equip')"><img
				class="rMenuImg"
				src="<%=request.getContextPath()%>/include/css/zTreeStyle/img/del.png" />删除设备</li>
		</ul>
	</div>
</body>
</html>
