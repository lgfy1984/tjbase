<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<%@page import="com.tianjian.hsp.bean.ZTreeNode"%>
<%@page import="java.util.*"%>
<jsp:useBean id="dataForm" scope="request" class="com.tianjian.hsp.struts.form.OrgMenuForm" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
	<title> ZTREE DEMO - select menu</title>
	<meta http-equiv="content-type" content="text/html; charset=UTF-8"/>
	<link rel="stylesheet" href="<%=request.getContextPath() %>/include/css/demo.css" type="text/css"/>
	<link rel="stylesheet" href="<%=request.getContextPath() %>/include/css/zTreeStyle/zTreeStyle.css" type="text/css"/>
	<link rel="stylesheet" href="<%=request.getContextPath() %>/include/css/searchsuggest.css" />
	<script src="<%=request.getContextPath() %>/include/javascript/searchsuggest.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/include/javascript/jquery-1.4.4.min.js"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/include/javascript/jquery.ztree.core-3.5.js"></script>
	<!-- <script type="text/javascript" src="<%=request.getContextPath() %>/include/javascript/jquery.ztree.excheck-3.5.js"></script> -->
	<script type="text/javascript" src="<%=request.getContextPath() %>/include/javascript/jquery.ztree.exedit-3.5.js"></script>
	<script type="text/javascript">
		var setting = {
		    async: {
				enable: true,//异步处理

				//contentType: "application/json",//提交参数方式，这里 JSON 格式，默认form格式
				
				url: '<%=request.getContextPath()%>/hsp/orgMenu.do?verbId=childNode',//异步获取json格式数据的路径

				autoParam: ["id","name","orgId","itemCode","rMenuFlag","hspStaffId","deptCode"]//异步加载时需要提交的参数，多个用逗号分隔
							
			},
					
			data: {
				simpleData: {
					enable: true
				}
			},
			view: {
				dblClickExpand: false
			},
			check: {
				enable: true
			},
			callback: {
				onRightClick: OnRightClick,
				onClick: zTreeOnClick
			}
		};
		var zNodes =[
		    
		    {name:"机构列表", id:"1",open:true,isParent:true,children:[
		    <%
		    List<ZTreeNode> firstList = dataForm.getFirstNodeList();
		    List<ZTreeNode> secondList = dataForm.getSecondNodeList();
		    List<ZTreeNode> thirdList = dataForm.getThirdNodeList();
		    List<ZTreeNode> fourthList = dataForm.getFourthNodeList();
		    List<ZTreeNode> fifthList = dataForm.getFifthNodeList();
		    if(firstList!=null&&firstList.size()>0){
		    	for(ZTreeNode node1 : firstList){
		    		String id1 = node1.getId();
		    		String name1 = node1.getName();
		    		String orgId1 = node1.getOrgId();
		    		String rMenuFlag1 = node1.getRMenuFlag();
		    		
		    	%>
		    	{name:"<%=name1%>",id:"<%=id1%>",open:true,orgId:"<%=orgId1%>",isParent:true,rMenuFlag:"<%=rMenuFlag1%>",children:[
		    	<%
		    	if(secondList!=null&&secondList.size()>0){
		    	for(ZTreeNode node2 : secondList){
		    		if(id1.equals(node2.getPId())){
		    		String id2 = node2.getId();
		    		String name2 = node2.getName();
		    		String orgId2 = node2.getOrgId();
		    		String rMenuFlag2 = node2.getRMenuFlag();
		    		String deptCode2 = node2.getDeptCode();
		    		String url2 = node2.getUrl();
		    		String target2 = node2.getTarget();
		    		boolean isParent2 = node2.isParent();
		    	%>
			    {name:"<%=name2%>",id:"<%=id2%>",<%if(orgId2!=null&&orgId2.trim().length()>0){%>orgId:"<%=orgId2%>",<%}%>
			    <%if(deptCode2!=null&&deptCode2.trim().length()>0){%>deptCode:"<%=deptCode2%>",<%}%>
			    <%if(url2!=null&&url2.trim().length()>0){%>url:"<%=request.getContextPath()+"/"+url2%>",target:"<%=target2%>",<%}%>
			    isParent:<%=isParent2%>,rMenuFlag:"<%=rMenuFlag2%>",<%if(isParent2){%>open:true,children:[
			    <%
			    if(thirdList!=null&&thirdList.size()>0){
		    	for(ZTreeNode node3 : thirdList){
		    		if(id2.equals(node3.getPId())){
		    		String id3 = node3.getId();
		    		String name3 = node3.getName();
		    		String orgId3 = node3.getOrgId();
		    		String rMenuFlag3 = node3.getRMenuFlag();
		    		String deptCode3 = node3.getDeptCode();
		    		String url3 = node3.getUrl();
		    		String target3 = node3.getTarget();
		    		String hspStaffId3 = node3.getHspStaffId();
		    		boolean isParent3 = node3.isParent();
		    	%>
			    {name:"<%=name3%>",id:"<%=id3%>",<%if(orgId3!=null&&orgId3.trim().length()>0){%>orgId:"<%=orgId3%>",<%}%>
			    <%if(deptCode3!=null&&deptCode3.trim().length()>0){%>deptCode:"<%=deptCode3%>",<%}%>
			    <%if(hspStaffId3!=null&&hspStaffId3.trim().length()>0){%>hspStaffId:"<%=hspStaffId3%>",<%}%>
			    <%if(url3!=null&&url3.trim().length()>0){%>url:"<%=request.getContextPath()+"/"+url3%>",target:"<%=target3%>",<%}%>
			    isParent:<%=isParent3%>,rMenuFlag:"<%=rMenuFlag3%>",<%if(isParent3){%>open:true,children:[
			    <%
			    if(fourthList!=null&&fourthList.size()>0){
			    for(ZTreeNode node4 : fourthList){
			    	if(id3.equals(node4.getPId())){
			    	String id4 = node4.getId();
		    		String name4 = node4.getName();
		    		String orgId4 = node4.getOrgId();
		    		String deptCode4 = node4.getDeptCode();
		    		String rMenuFlag4 = node4.getRMenuFlag();
		    		String url4 = node4.getUrl();
		    		String target4 = node4.getTarget();
		    		String hspStaffId4= node4.getHspStaffId();
		    		boolean isParent4 = node4.isParent();
		    	%>
			    {name:"<%=name4%>",id:"<%=id4%>",<%if(orgId4!=null&&orgId4.trim().length()>0){%>orgId:"<%=orgId4%>",<%}%>
			    <%if(deptCode4!=null&&deptCode4.trim().length()>0){%>deptCode:"<%=deptCode4%>",<%}%>
			    <%if(hspStaffId4!=null&&hspStaffId4.trim().length()>0){%>hspStaffId:"<%=hspStaffId4%>",<%}%>
			    <%if(url4!=null&&url4.trim().length()>0){%>url:"<%=request.getContextPath()+"/"+url4%>",target:"<%=target4%>",<%}%>
			    isParent:<%=isParent4%>,rMenuFlag:"<%=rMenuFlag4%>",<%if(isParent4){%>open:true,children:[
			    <%
			    if(fifthList!=null&&fifthList.size()>0){
			    for(ZTreeNode node5 : fifthList){
			    	if(id4.equals(node5.getPId())){
			    	String id5 = node5.getId();
		    		String name5 = node5.getName();
		    		String hspStaffId = node5.getHspStaffId();
		    		String rMenuFlag5 = node5.getRMenuFlag();
		    		String url5 = node5.getUrl();
		    		String target5 = node5.getTarget();
		    	%>
				{name:"<%=name5%>",id:"<%=id5%>",hspStaffId:"<%=hspStaffId%>",isParent:false,rMenuFlag:"<%=rMenuFlag5%>",url:"<%=request.getContextPath()+"/"+url5%>",target:"<%=target5%>"},
				<%
			        }
			    }
			    }
		    	%>
			    ]<%}%>},
			    <%
		    	    }
			    }
			    }
		    	%>
			    ]<%}%>},
			    <%
		    		}
		    	}
			    }
		    	%>
			    ]<%}%>},
			    <%
			        }
		    	}
		    	}
		    	%>
		    	]},
		    	<%
		    	}		    	
		    }
		    %>
		    ]}
		];
		$(document).ready(function(){
			$.fn.zTree.init($("#treeDemo"), setting, zNodes);
		});
		
		function zTreeOnClick(event, treeId, treeNode) {
            var url = treeNode.uri;
            if(url!=null&&url!=""){
                url = "<%=request.getContextPath()%>/"+url;
                self.parent.frames["righFrame"].location.href= url;
            }          
        }

		function OnRightClick(event, treeId, treeNode) {
			if (!treeNode && event.target.tagName.toLowerCase() != "button" && $(event.target).parents("a").length == 0) {
				zTree.cancelSelectedNode();
				showRMenu("root", event.clientX, event.clientY);
			} else if (treeNode && !treeNode.noR) {
				zTree.selectNode(treeNode);
				//showRMenu("node", event.clientX, event.clientY);
				if(treeNode.rMenuFlag=="1"){
				    showRMenu1("node", event.clientX, event.clientY);
				}else if(treeNode.rMenuFlag=="2"){
				    showRMenu2("node", event.clientX, event.clientY);
				}else if(treeNode.rMenuFlag=="3"){
				    showRMenu3("node", event.clientX, event.clientY);
				}else if(treeNode.rMenuFlag=="4"){
				    showRMenu4("node", event.clientX, event.clientY);
				}else{
				   showRMenu0("node", event.clientX, event.clientY);
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
			rMenu.css({"top":y+"px", "left":x+"px", "visibility":"visible"});

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
			rMenu.css({"top":y+"px", "left":x+"px", "visibility":"visible"});

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
				$("#m_modify").hide();
				$("#m_del").hide();		
				$("#m_add1").show();	
				$("#m_modify1").hide();
				$("#m_del1").hide();		
			}
			rMenu.css({"top":y+"px", "left":x+"px", "visibility":"visible"});

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
				$("#m_add").hide();	
				$("#m_modify").hide();
				$("#m_del").hide();	
				$("#m_add1").hide();	
				$("#m_modify1").hide();
				$("#m_del1").hide();		
			}
			rMenu.css({"top":y+"px", "left":x+"px", "visibility":"visible"});

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
			rMenu.css({"top":y+"px", "left":x+"px", "visibility":"visible"});

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
				$("#m_add1").show();	
				$("#m_modify1").hide();
				$("#m_del1").hide();		
			}
			rMenu.css({"top":y+"px", "left":x+"px", "visibility":"visible"});

			$("body").bind("mousedown", onBodyMouseDown);
		}
		
		function hideRMenu() {
			if (rMenu) rMenu.css({"visibility": "hidden"});
			$("body").unbind("mousedown", onBodyMouseDown);
		}
		function onBodyMouseDown(event){
			if (!(event.target.id == "rMenu" || $(event.target).parents("#rMenu").length>0)) {
				rMenu.css({"visibility" : "hidden"});
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
			xmlHttp.open("GET", sendTo, true);
			var handlerFunction = getReadyStateHandler(xmlHttp);
			xmlHttp.onreadystatechange = handlerFunction;
			xmlHttp.send(null);
		}
		function deleteTree(orgId,deptCode){
			var xmlHttp = newXMLHttpRequest();
			var url = "<%=request.getContextPath()%>/hsp/orgMenu.do?verbId=delDept&orgId="+orgId+"&deptCode="+deptCode;
			var sendTo = url;
			xmlHttp.open("GET", sendTo, true);
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
			xmlHttp.open("GET", sendTo, true);
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
			            <input type="hidden" id="queryType" name="queryType" value="2"/>
			            <input type="text" id="itemName" name="itemName" size="24" value="${dataForm.itemName}"
						onkeyup="GiveOptions(event, '<%=request.getContextPath()%>/searchSuggest.do', 'getHspName_00000000004', 'itemCode')"
						onkeydown="huanhang(event)" />
						<input type="hidden" name="itemCode" id="itemCode" value="${dataForm.itemCode}"/>
						<span id="spanOutput" class="spanTextDropdown" style="display: none;"></span> 
			        </td>
			    </tr>
			    <tr>
			        <td align="right">名字：
			            <input type="hidden" id="queryKey" name="queryKey" value="0"/>
			        </td>
			        <td align="left">
			            <input type="text" id="queryValue" name="queryValue" size="15" value="${dataForm.queryValue}" 
			            onkeyup="GiveOptions(event, '<%=request.getContextPath()%>/searchSuggest.do', 'getHspStaffName_0000000001')" onkeydown="huanhang(event)" />
			            <input type="button" id="btnQuery" name="btnQuery" value="查询" onclick="query()"/>
			        </td>
			    </tr>
			</table>
		</form>
	    <div class="ztree" id="treeDemo">		
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