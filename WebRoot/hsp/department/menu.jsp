<%@ page contentType="text/html; charset=UTF-8"%>
<%@page import="com.tianjian.hsp.struts.form.OrgMenuForm,java.util.*"%>
<%@page import="com.tianjian.hsp.bean.ZTreeNode"%>
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
		<script type="text/javascript" src="<%=request.getContextPath() %>/include/javascript/jquery-1.4.4.min.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath() %>/include/javascript/jquery.ztree.core-3.5.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath() %>/include/javascript/jquery.ztree.excheck-3.5.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath() %>/include/javascript/jquery.ztree.exedit-3.5.js"></script>
		<script language="javascript">
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
		List<ZTreeNode> firstList = data.getFirstNodeList();
		if(firstList!=null&&firstList.size()>0){
			for(ZTreeNode firstNode : firstList){
				%>
				{id:"<%=firstNode.getId()%>", name:"<%=firstNode.getName()%>",noR:true,open:true,children:[
				<%
				List<ZTreeNode> secondNodeList = data.getSecondNodeList();
				if(secondNodeList!=null&&secondNodeList.size()>0){
					for(ZTreeNode secondNode : secondNodeList){
						if(secondNode.getPId().equals(firstNode.getId())){
							if(secondNode.isParent()){
							%>
							{id:"<%=secondNode.getId()%>", name:"<%=secondNode.getName()%>",noR:true,open:true,children:[
							<%
							List<ZTreeNode> thirdNodeList = data.getThirdNodeList();
							if(thirdNodeList!=null&&thirdNodeList.size()>0){
								for(ZTreeNode thirdNode : thirdNodeList){
									if(thirdNode.getPId().equals(secondNode.getId())){
										if(thirdNode.isParent()){
											%>
											{id:"<%=thirdNode.getId()%>", name:"<%=thirdNode.getName()%>",noR:true,open:true,children:[
											<%
											List<ZTreeNode> fourthNodeList = data.getFourthNodeList();
											if(fourthNodeList!=null&&fourthNodeList.size()>0){
												for(ZTreeNode fourthNode : fourthNodeList){
													if(fourthNode.getPId().equals(thirdNode.getId())){
														if(fourthNode.isParent()){
															List<ZTreeNode> fifthNodeList = data.getFifthNodeList();
															if(fifthNodeList!=null&&fifthNodeList.size()>0){
																for(ZTreeNode fifthNode : fifthNodeList){
																	if(fifthNode.getPId().equals(fourthNode.getId())){
																		%>
																		{id:"<%=fifthNode.getId()%>", name:"<%=fifthNode.getName()%>", url:"<%=fifthNode.getUrl()%>"},
																		<%
																	}
																}
															}
														}else{
															%>
															{id:"<%=fourthNode.getId()%>", name:"<%=fourthNode.getName()%>", url:"<%=fourthNode.getUrl()%>"},
															<%
														}
													}
												}
											}
											%>
											]},
											<%
										}else{
											%>
											{id:"<%=thirdNode.getId()%>", name:"<%=thirdNode.getName()%>", url:"<%=thirdNode.getUrl()%>"},
											<%
										}
									}
								}
							}
							%>
							]},
							<%
							}else{
								%>
								{id:"<%=secondNode.getId()%>", name:"<%=secondNode.getName()%>", url:"<%=secondNode.getUrl()%>"},
								<%
							}
						}
					}
				}
				%>
				]}
				<%
			}
		}
		%>
		]
		
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
				$("#m_modify").hide();
				$("#m_del").hide();
				$("#m_veiw").hide();
			} else {
				$("#m_modify").show();
				$("#m_del").show();
				$("#m_veiw").show();
			}
			rMenu.css({"top":y+"px", "left":x+"px", "visibility":"visible"});

			$("body").bind("mousedown", onBodyMouseDown);
		}
		var zTree, rMenu;
		$(document).ready(function(){
			$.fn.zTree.init($("#treeDemo"), setting, zNodes);
			zTree = $.fn.zTree.getZTreeObj("treeDemo");
			rMenu = $("#rMenu");
		});
		function modifyDept(){
		    alert("修改");
		}
		function delDept(){
		    alert("删除");
		}
		function viewDept(){
		    alert("查看");
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
	    </style>
	</head>
	<body style="background: #b4cee1" >
		<form action="<%=request.getContextPath()%>/hsp/orgMenu.do" name="form" method="post">
			<input type="hidden" id="verbId" name="verbId" />
			<div class="content_wrap">
		       <ul id="treeDemo" class="ztree"></ul>
	        </div>
			<div id="rMenu">
	           <ul>
		          <li id="m_modify" onclick="modifyDept();">修改科室</li>
		          <li id="m_del" onclick="delDept();">删除科室</li>
		          <li id="m_veiw" onclick="viewDept();">科室明细</li>
		      </ul>
			</div>
		</form>
	</body>
</html>
