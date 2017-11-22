<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.tianjian.security.struts.form.SessionForm"%>
<%@page import="com.tianjian.security.struts.form.LoginMenuForm"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";

	HttpSession httpSession = request.getSession(true);
	com.tianjian.security.struts.form.SessionForm sessionForm = (com.tianjian.security.struts.form.SessionForm) httpSession
			.getAttribute("sessionForm");

	String ignoreThisData = (String) request.getSession().getAttribute(
			"ignoreThisData");
	Boolean timeFlag = (Boolean) session.getAttribute("timeFlag");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<base href="<%=basePath%>">
		<title>::: <%=application.getAttribute("security.SYSTEMNAME")%> :::</title>
		<meta http-equiv="X-UA-Compatible" content="IE=8" />
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<link rel="stylesheet" type="text/css" href="main/include/css/index_n.css" />
		<title>:::<%=application.getAttribute("security.SYSTEMNAME")%> :::</title>
		<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/main/ext-3.2.0/resources/css/ext-all.css" />
		<script type="text/javascript" src="<%=request.getContextPath()%>/main/ext-3.2.0/adapter/ext/ext-base.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/main/ext-3.2.0/ext-all.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/include/javascript/utrim.js"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/include/javascript/jquery-1.4.4.min.js"></script>
		<style>
p {
	line-height: 1.5em;
}
#nagivation_div ul#menu,ul#menu ul ul#menu li{
	font-size: 0.9em;
	font-family: Helvetica, Arial, sans-serif;
}

ul#menu,ul#menu ul {
	list-style-type: none;
	margin: 0;
	padding: 0;
	width: 15em;
}

ul#menu a {
	display: block;
	text-decoration: none;
}

ul#menu li {
	margin-bottom: 1px;
}

ul#menu li a {
	background: #2a63a6;
	color: #fff;
	padding: 0.5em;
}

ul#menu li a:hover {
	background: #000;
}

ul#menu li ul li a {
	background: #ccc;
	color: #000;
	padding-left: 20px;
}

ul#menu li ul li a:hover {
	background: #aaa;
	border-left: 5px #000 solid;
	padding-left: 15px;
}

.code {
	border: 1px solid #ccc;
	list-style-type: decimal-leading-zero;
	padding: 5px;
	margin: 0;
}

.code code {
	display: block;
	padding: 3px;
	margin-bottom: 0;
}

.code li {
	background: #ddd;
	border: 1px solid #ccc;
	margin: 0 0 2px 2.2em;
}

.indent1 {
	padding-left: 1em;
}

.indent2 {
	padding-left: 2em;
}
</style>
		<script type="text/javascript">
		Ext.onReady(function(){
	    // 创建工具条
	    var tb = new Ext.Toolbar();
	    tb.render('toolbar');
	    // 为工具条添加4个按钮
	    tb.add(
	    	${menu}		
	    );
	    tb.doLayout();
	  	//异步ajax调用
	    /**
	     * 异步调用ajax，成功后返回值，作为回调函数的参数 调用失败会提示
	     * 
	     * @param {}
	     *            urlStr
	     * @param {}
	     *            paramsObj
	     */
	    var url3 = '<%=request.getContextPath()%>/security/security_loginSecond.do?verbId=findMenuByPublicId&';
	    function getMenuTree(urlStr, paramsObj, obj) {
	    	Ext.Ajax.request({
	    				url : urlStr,
	    				params : paramsObj,
	    				method : 'POST',
	    				success : function(response) {
	    						//var result = Ext.util.JSON.decode(response.responseText);
	    						var result = response.responseText;
	    						//alert("menu:" + result);
	    						var str = Ext.util.JSON.decode(result);
	    						//alert("length:" + str.items.length);
	    						var menuFile = new Ext.menu.Menu(str);
	    						obj.menu = menuFile;
	    						obj.showMenu();		
	    				},
	    				failure : function() {
	    					Ext.Msg.alert("提示", "方法调用失败");
	    				}
	    			});
	    }
	
	
	});
	function cmdView(flag,id,url){
		document.form.publicClassId.value = id; 
	    document.form.verbId.value = "login";
	    document.form.target="_parent";
		document.form.submit(); 
	}
	function goPublicClass(id){
		document.form.publicClassId.value = id; 
	    document.form.verbId.value = "login";
	    document.form.target="_parent";
		document.form.submit(); 
	}
	function goDiv(url){
		if(url != null && trim(url) != ''){
			if(url.substring(0,4)!='http'){
				document.getElementById("iframemain").src = "<%=request.getContextPath()%>/" + url;
			}else{
				document.getElementById("iframemain").src = url;
			}
			
		}
	}
	var daohang='';
	function handleDaohang(obj){
		var textall = obj.text;
		daohang = getDaohangAll(obj,textall);
		var public = getPublic()
		document.getElementById("daohang").innerHTML= public + daohang;
		document.getElementById("daohang").style.color="#0683CB";
		document.getElementById("daohang").style.fontSize="12px";
	}
	function getPublic(){
		var text = '';
		<%
		LoginMenuForm lmForm = (LoginMenuForm)session.getAttribute("lmForm");
		if (lmForm != null){
		 	if(lmForm.getParentPublicClassName() != null && lmForm.getParentPublicClassName().trim().length() > 0){
		%>
		text = "<%=lmForm.getParentPublicClassName()%>&nbsp;&nbsp;>>&nbsp;";
		<%
			}
		%>
		text += "<%=lmForm.getSelectedPublicClassName()%>&nbsp;&nbsp;>>&nbsp;";
		<%
		}
		%>
		return text;
	}
	function getDaohangAll(obj,textall){
		textall = "<a href=\"javascript:showMenu('"+obj.id+"')\" style=\"color:#0683CB; font-size:12px;\">"+obj.text+"</a>";
	    if(obj.ownerCt != null && obj.ownerCt.ownerCt != null && obj.ownerCt.ownerCt.text != 'undefined'){
	    	textall = getDaohangAll(obj.ownerCt.ownerCt,textall) + '&nbsp;&nbsp; >> &nbsp;' + textall;
		}
		return textall;
	}
	function showMenu(id){
		try{
			if(id == ""){
				return;
			}
			var ele = Ext.getDom(id);
			var obj = Ext.getCmp(id);
			if(obj.ownerCt!=null&&obj.ownerCt.ownerCt!=null&&obj.ownerCt.ownerCt.text!='undefined'){
				showMenu(obj.ownerCt.ownerCt.id);
			}
			if(ele.tagName=="TABLE"){
				ele.click();
				//obj.addClass("x-btn-over");
			}else if(ele.tagName=="A"){
				obj.expandMenu();
				//var li = ele.parentElement ? ele.parentElement : ele.parentNode;
				//if(li){
				//	li.className+=" x-menu-item-active";
				//}
			}
		}catch(e){
		}
	}


	function iFrameHeight() { 
		var ifm= document.getElementById("iframemain"); 
		var subWeb = document.frames ? document.frames["iframemain"].document : ifm.contentDocument; 
		ifm.height = document.getElementById("iframe2").offsetHeight;
	} 	
	function initMenu() {
  $('#menu ul').hide();
  $('#menu ul:first').show();
  $('#menu li a').click(
    function() {
      var checkElement = $(this).next();
      if((checkElement.is('ul')) && (checkElement.is(':visible'))) {
        return false;
        }
      if((checkElement.is('ul')) && (!checkElement.is(':visible'))) {
        $('#menu ul:visible').slideUp('normal');
        checkElement.slideDown('normal');
        return false;
        }
      }
    );
  }
$(document).ready(function() {initMenu();});
function showMenuDiv(_p){
	if($('#publicClass_div').css('width')=='190px'){
		$('#publicClass_div').animate({width:'0'});
		_p.innerHTML="快速导航▼";
	}else{
		$('#publicClass_div').animate({width:'190px'});
		_p.innerHTML="快速导航▲";
	}
}
	</script>
	</head>
	<body>
		<form id="form" name="form" method="post" action="security/security_loginSecond.do">
			<input type="hidden" name="verbId" value="" />
			<input type="hidden" name="publicClassId" value="" />
			<div id="header">
				<img src="main/include/images/login_03.jpg" width="536" height="85" />
				<ul class="nav">
					<li class="user">
						欢迎您！<%=sessionForm.getStaffName()%></li>
					<li>
						<a href="<%=request.getContextPath()%>/security/security_login.do?verbId=reLogin">返回首页</a>
					</li>
					<li>
						<a href="<%=request.getContextPath()%>/index.jsp?agree=true">退出系统</a>
					</li>
				</ul>
				<div id="topNav">
                  <div id="ca-container" class="ca-container">
                        <div class="ca-wrapper">
                        	<% 
								if(sessionForm.getPublicClassIds()!=null && sessionForm.getPublicClassIds().length>0){ 
									for(int i = 0;i < sessionForm.getPublicClassIds().length; i++){
							%>	
							
							 <div class="ca-item">
                                <div class="ca-item-main">
                                    <div class="ca-icon"><a href="javascript:void(0);" onclick="cmdView('<%=sessionForm.getPublicClassSysFlags()[i]%>','<%=sessionForm.getPublicClassIds()[i] %>','<%=sessionForm.getPublicClassRedirectUrls()[i]%>');" title="<%=sessionForm.getPublicClassNames()[i]%>"> </a> </div>
                                    <h3><a href="javascript:void(0);" onclick="cmdView('<%=sessionForm.getPublicClassSysFlags()[i]%>','<%=sessionForm.getPublicClassIds()[i] %>','<%=sessionForm.getPublicClassRedirectUrls()[i]%>');" title="<%=sessionForm.getPublicClassNames()[i]%>"><%=sessionForm.getPublicClassNames()[i]%> </a> </h3>
                                </div>
                            </div>								
										
							<%
									}
								}
							 %>
                        </div>	
                           
                	</div>
				</div>
			</div>
			<div id="toolbar">
			</div>
			<div class="currentPos">
				当前位置：
				<span id="daohang"></span>
			</div>
			<div id="iframe2" onactivate="Ext.menu.MenuMgr.hideAll();">
				<iframe width="100%" scrolling="yes" height="75%" frameborder="0" marginheight="0" marginwidth="0" id="iframemain"
					name="iframemain" src="<%=request.getContextPath()%>/main/default.jsp" onload="iFrameHeight();"></iframe>
			</div>
			<div id="nagivation_div" style="position:absolute; top: 133px; right: 0; cursor: pointer"> 
				<p style="float:left;WRITING-MODE:tb-rl;TEXT-ALIGN:left;letter-spacing:0.2em;color:#fff; background-color: #2a63a6;padding: 20px 3px 10px 3px"
					onclick="showMenuDiv(this)">
					快速导航▼
				</p>
				<div id="publicClass_div" style="width: 0;float: right;background-color: #fafafa">
					<ul id="menu">
						<li>
							<a href="javascript:void(0);">系统管理</a>
							<ul>
								<li>
									<a href="javascript:goPublicClass('1010');">EMPI管理</a>
								</li>
								<li>
									<a href="javascript:goPublicClass('1011');">操作员管理</a>
								</li>
								<li>
									<a href="javascript:goPublicClass('1012');">权限管理</a>
								</li>
								<li>
									<a href="javascript:goPublicClass('1013');">字典管理</a>
								</li>
								<li>
									<a href="javascript:goPublicClass('1014');">系统参数配置</a>
								</li>
							</ul>
						</li>
						<li>
							<a href="javascript:void(0);">综合管理</a>
							<ul>
								<li>
									<a href="javascript:goPublicClass('1110');">卫生注册</a>
								</li>
							</ul>
						</li>
						<li>
							<a href="javascript:void(0);">决策支持</a>
							<ul>
								<li>
									<a href="javascript:goPublicClass('1210');">卫生资源</a>
								</li>
								<li>
									<a href="javascript:goPublicClass('2828810b3d1f6ab4013d1f92b5330005');">医疗保障</a>
								</li>
							</ul>
						</li>
					</ul>
				</div>
			</div>
			<div id="footer"><%=application.getAttribute("security.SYSTEMNAME")%>&nbsp;
				<%
					SessionForm sessionFormBottom = (SessionForm) session
							.getAttribute("sessionForm");
					if (sessionFormBottom != null
							&& sessionFormBottom.getVersionUserName() != null) {
				%>用户:<%=sessionFormBottom.getVersionUserName()%>
				<%
					}
				%>
			</div>

			<script type="text/javascript" src="include/javascript/jquery-1.4.2.min.js"></script>
			<script type="text/javascript" src="include/javascript/jquery.easing.1.3.js"></script>
			<!-- the jScrollPane script -->
			<script type="text/javascript" src="include/javascript/jquery.mousewheel.js"></script>
			<script type="text/javascript" src="include/javascript/jquery.contentcarousel.js"></script>
			<script type="text/javascript">
			$('#ca-container').contentcarousel();
		</script>
		</form>
	</body>
</html>

