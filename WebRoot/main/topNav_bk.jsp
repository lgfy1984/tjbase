<%@page import="com.tianjian.security.bean.SecurityConfigPublicClass"%>
<%@page import="com.tianjian.security.bean.PublicClassBean"%>
<%@page import="com.tianjian.security.struts.form.LoginSecondForm"%>
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" contentType="text/html; charset=utf-8"%>
<%@ page import="com.tianjian.security.struts.form.SessionForm"%>
<%@page import="com.tianjian.security.struts.form.LoginMenuForm"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
	
	HttpSession httpSession = request.getSession(true);
	com.tianjian.security.struts.form.SessionForm sessionForm = (com.tianjian.security.struts.form.SessionForm) httpSession.getAttribute("sessionForm");
	
	LoginSecondForm loginSecondForm = (LoginSecondForm)request.getAttribute("loginSecondForm");
	List<SecurityConfigPublicClass> pcList = null;
	Map<String, List<SecurityConfigPublicClass>> childMap = null;
	
	String firstPubicClassId = null;
	String currentPublicClassName = "";
	if(loginSecondForm != null){
		pcList = loginSecondForm.getPcList();
		if(pcList != null && pcList.size() > 0){
	SecurityConfigPublicClass pc = pcList.get(0);
	firstPubicClassId = pc.getId();
	currentPublicClassName = pc.getClassName();
		}
		childMap = loginSecondForm.getChildMap();
		if(childMap != null){
	List<SecurityConfigPublicClass> childList = childMap.get(firstPubicClassId);
	if(childList != null && childList.size() > 0){
		SecurityConfigPublicClass child = childList.get(0);
		firstPubicClassId = child.getId();
		currentPublicClassName += "&nbsp;&nbsp;&gt;&gt;&nbsp;" + child.getClassName();
	}
		}
	}
%>
<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>">
<title>::: <%=application.getAttribute("security.SYSTEMNAME")%>
	:::
</title>
<meta http-equiv="X-UA-Compatible" content="IE=8" />
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="stylesheet" type="text/css" href="main/include/css/index_n.css" />
<title>:::<%=application.getAttribute("security.SYSTEMNAME")%>
	:::
</title>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/main/ext-3.2.0/resources/css/ext-all.css" />
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/main/include/css/multiPage.css" />
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/main/include/css/pcpop.css" />
<script type="text/javascript"
	src="<%=request.getContextPath()%>/main/ext-3.2.0/adapter/ext/ext-base.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/main/ext-3.2.0/ext-all.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/include/javascript/utrim.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/include/javascript/jquery-1.4.2.min.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/include/javascript/jquery.contextmenu.r2.packed.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/include/javascript/jquery.easing.1.3.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/include/javascript/jquery.mousewheel.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/include/javascript/jquery.contentcarousel.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/include/javascript/jquery.easyui.min.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/include/javascript/jquery.easyui.mobile.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/callCenterControls.js"></script>
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/style/callCenterControls.css" />
	
<link rel="stylesheet" type="text/css"	href="<%=request.getContextPath()%>/style/easyui/themes/default/calendar.css">
<script type="text/javascript"	src="<%=request.getContextPath()%>/style/easyui/locale/easyui-lang-zh_CN.js"></script>		
	
<style type="text/css">
.right_black {
	width: 225px;
	height: 500px;
	font-size: 10pt;;
	background-color: #FFFFFF
}

.right_history_blank {
	width: 100%;
	border: 1px #DDDDDD solid;
	height: 245px;
}

.right_title {
	width: 100%;
	background-color: #F9F9F9;
	height: 33px;
	line-height: 33px;
	color: #3994DA;
}

.right_title span {
	margin-left: 5px;
}

.line {
	width: 100%;
	height: 1px;
	background-color: #DDDDDD;
}

.right_content_blank {
	width: 221px;
	margin: 2px auto;
}

.calendar_show {
	width: 100%;
	text-align: center;
	margin: 0;
	border-collapse: collapse;
	border-spacing: 0;
	font-family: Arial, Helvetica, sans-serif
}

.calendar_show td {
	height: 27px;
}

.calendar_show thead td {
	background-color: #3994DA;
	color: white;
	height: 30px;
}

.weekday td {
	height: 28px;
	width: 30px
}

.month_day td {
	background-color: #F9F9F9;
}

.month_day td.holiday {
	background-color: #EEEEEE
}

.weekday,.month_day {
	color: #666666
}

.calendar_show a {
	text-decoration: none;
}

.current_link a span {
	display: block;
	background-color: white;
	border: 1px #0099FF solid;
	width: 97%;
	height: 25px;
	line-height: 27px;
}

.month_link a span {
	display: block;
	background-color: #0099FF;
	color: white;
	width: 100%;
	height: 27px;
	line-height: 27px;
}

.next_month_link a span {
	display: block;
	background-color: #4AB7FF;
	color: white;
	width: 100%;
	height: 27px;
	line-height: 27px;
}

.line_15 {
	height: 15px;
}

.log_item {
	height: 42px;
	line-height: 42px;
	width: 100%
}

.log_item span {
	margin-left: 5px;
}

.even {
	background-color: #F3F3F3
}

.phone_button {
	width: 53px;
	height: 35px;
	line-height: 35px;
	display: block;
	float: left;
	margin-left: 5px;
	background-image:
		url('<%=request.getContextPath()%>/include/images/phone_wait.jpg');
	background-position: center;
	background-repeat: no-repeat;
	text-align: center;
}

.phone_button a {
	color: #787878;
}

.phone_current {
	color: white;
	background-image:
		url('<%=request.getContextPath()%>/include/images/phone_current.jpg')
}

.phone_disable {
	color: #CCCCCC;
	background-image:
		url('<%=request.getContextPath()%>/include/images/phone_wait.jpg')
}

.tool_tab {
	width: 75px;
	height: 30px;
	background-image:
		url('<%=request.getContextPath()%>/include/images/footer_tab_normal.jpg');
	background-position: center bottom;
	background-repeat: no-repeat;
	float: left;
	line-height: 38px;
	color: rgb(85, 85, 85);
}

.tool_tab_active {
	background-image:
		url('<%=request.getContextPath()%>/include/images/footer_tab_active.jpg');
	color: #FFFFFF;
}

.margin_left {
	width: 10px;
	height: 30px;
	float: left;
}

.footer_show {
	width: 30px;
	height: 30px;
	float: right;
	background-image:
		url('<%=request.getContextPath()%>/include/images/footer_hidden.jpg');
	background-position: center center;
	background-repeat: no-repeat;
	cursor: pointer;
}

.footer_hidden {
	width: 30px;
	height: 30px;
	float: right;
	background-image:
		url('<%=request.getContextPath()%>/include/images/footer_show.jpg');
	background-position: center center;
	background-repeat: no-repeat;
	cursor: pointer;
}
</style>
<script type="text/javascript">
		var tb;
	 	var currentPublicClassName = "<%=currentPublicClassName%>";
		Ext.onReady(
			function(){
		   		 // 创建工具条
			    tb = new Ext.Toolbar();
			    tb.render('toolbar');
			    getPublicByClassId("<%=firstPubicClassId%>");
			}
		);
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

	function getPublicByClassId(publicClassId){
		if($.trim(publicClassId).length == 0){
			return;
		}
		$.ajax({
			type: "POST",
        	processData: false,
        	dataType: "application/json",
        	url: "<%=request.getContextPath()%>/security/security_loginSecond.do",
        	data: "verbId=getPublic&publicClassId="+publicClassId,
        	success: function(res){
        		if(res){
        			var json;
        			try{
	        			json = eval(res);
        			}catch(e){
        				alert("获取失败!");
          			  	document.location = "<%=request.getContextPath()%>/home/nologin.jsp";
        			}
        			if(json){
	        			tb.removeAll();
	        			tb.add(json);
	        			tb.doLayout();
	        			$(".li_pc_1").removeClass("pc_1_selected");
	        			$(".li_pc_2").removeClass("pc_2_selected");
	        			var _pc_li = $(".pc_"+publicClassId);
	        			if(_pc_li.hasClass("li_pc_1")){
	        				_pc_li.addClass("pc_1_selected");
	        			}else{
	        				_pc_li.addClass("pc_2_selected");
	        				_pc_li.parents(".li_pc_1").addClass("pc_1_selected");
	        			}
        			}
        		}
        	},
        	error: function(a, b, c){
        		alert("获取失败!");
  			  	document.location = "<%=request.getContextPath()%>/home/nologin.jsp";
        	}
		});
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
	
	function handleDaohang(obj){
		var textall = obj.text;
		var daohang = getDaohangAll(obj,textall);
		document.getElementById("daohang").innerHTML= daohang;
		document.getElementById("daohang").style.color="#0683CB";
		document.getElementById("daohang").style.fontSize="12px";
	}

	function getDaohangAll(obj,textall){
		textall = obj.text;
	    if(obj.ownerCt != null && obj.ownerCt.ownerCt != null && obj.ownerCt.ownerCt.text != 'undefined'){
	    	textall = getDaohangAll(obj.ownerCt.ownerCt,textall) + '&nbsp;&nbsp;&gt;&gt;&nbsp;' + textall;
		}
		return textall;
	}
	
	$(document).ready(
			function(){
				$(".tab").live("click", 
					function(){
						var id = $(this).attr("id");
						id = id.substr(4);
						focusOnePage(id);
					}
				);
				$(".tabs > li > .close").live("click",
					function(){
						var id = $(this).parent().attr("id");
						id = id.substr(4);
						closeOnePage(id);
						return false;
					}
				);
				
				var prev_move_tabs = function(){
					if($(".tabs").offset().left < 0){
						$(".tabs").animate({"margin-left": "+=10"}, 1, prev_move_tabs);
					}
					if($(".tabs").offset().left > 0){
						$(".tabs").animate({"margin-left": 0}, "fast");
					}
				}
				$(".tabs_tool_btn .prev_tab_btn").bind("mousedown",
					function(e){
						if(e.which == 1){
							prev_move_tabs();
							return false;
						}
					}		
				);
				var next_move_tabs = function(){
					var _last_tab = $(".tab").last()
					if($(window).width() < (_last_tab.offset().left + _last_tab.width() + 40)){
						$(".tabs").animate({"margin-left":"-=10"}, 1, next_move_tabs);
					}
					if($(window).width() > (_last_tab.offset().left + _last_tab.width() + 40)){
						var temp = $(window).width() - _last_tab.offset().left - _last_tab.width() - 40;
						$(".tabs").animate({"margin-left":"+="+temp}, "fast");
					}
				}
				$(".tabs_tool_btn .next_tab_btn").bind("mousedown",
						function(e){
							if(e.which == 1){
								next_move_tabs();
								return false;
							}
						}		
					);
				$(".tabs_tool_btn div").bind("mouseup",
						function(e){
							if(e.which == 1){
								$(".tabs").stop();
								return false;
							}
						}		
					);
			}
		);
		
		var addPage = function(obj, id, name, src){
			clickTextSubText(id);
			var daohang = "";
			if(obj != null){
				var textall = obj.text;
				daohang = getDaohangAll(obj,textall);
				if(currentPublicClassName){
					daohang = currentPublicClassName + '&nbsp;&nbsp;&gt;&gt;&nbsp;' + daohang;
				}
				daohang = "当前位置：" + daohang;
			}
			if(src.substring(0,4).toUpperCase() !='HTTP'){
				if(src.substring(0, 1) == '/'){
					src = src.substring(1, src.length);
				}
				src = "<%=request.getContextPath()%>/" + src;
		}

		if ($("#page_" + id).length == 0) {
			var page_div = document.createElement("div");
			$(page_div).attr("id", "page_" + id);
			$(page_div).addClass("one_page");

			var leader = document.createElement("div");
			$(leader).addClass("leader");
			$(leader).html(daohang);
			$(page_div).append(leader);
			var iframe_div = document.createElement("div");
			$(iframe_div).addClass("iframe_div");
			var iframe = document.createElement("iframe");
			
			iframe.src = src;
			$(iframe).attr("frameborder", "0");
			$(iframe).bind("activate", function() {
				Ext.menu.MenuMgr.hideAll();
				$.contextMenu.hidden();
			});
			$(iframe_div).append(iframe);
			$(page_div).append(iframe_div);
			$(".page_container").append(page_div);
			/*2015.03.17*/
			$(iframe_div).attr("ifid_"+id);
			$(iframe).attr("name","_"+id);
			$(iframe).attr("id","_"+id);
			var ee = document.getElementById("_"+id);
			var ed = ee.window;
			
			var li = document.createElement("li");
			$(li).attr("id", "tab_" + id);
			$(li).addClass("tab");
			$(li).addClass("tab_active");

			var name_span = document.createElement("span");
			$(name_span).addClass("page_name");
			$(name_span).append(document.createTextNode(name));
			$(name_span).attr("title", name);
			$(li).append(name_span);

			var close_span = document.createElement("span");
			$(close_span).addClass("close");
			$(close_span).attr("title", "关闭");
			$(li).append(close_span);
			$(li).contextMenu("right_key_menu", {
				bindings : {
					'refresh_page_btn' : function(x) {
						iframe.src = src;
					},
					'close_page_btn' : function(x) {
						closeOnePage(id);
					},
					'close_other_page_btn' : function(x) {
						var arr = $(".tab");
						for (var i = 0; i < arr.length; i++) {
							var temp = $(arr[i]);
							var temp_id = temp.attr("id");
							temp_id = temp_id.substr(4);
							if (temp_id != id) {
								$("#page_" + temp_id).remove();
								temp.remove();
							}
						}
						focusOnePage(id);
						slideTabs();
					},
					'single_window_btn' : function(x) {
						window.open(src, "_blank", null);
					}
				}
			});
			$(".tabs").append(li);

			var height = $(window).height() - $(iframe_div).offset().top
					- $("#footer").height();
			$(iframe).height(height);
		}
		focusOnePage(id);
	}

	var focusOnePage = function(id) {
		clickTextSubText(id);
		var page_id = "page_" + id;
		var tab_id = "tab_" + id;
		var _page = $("#page_" + id);
		$(".page_container").children().hide();
		$("#" + page_id).show();
		$(".tab").removeClass("tab_active");
		var _tab = $("#" + tab_id);
		_tab.addClass("tab_active");
		slideTabs();
	}

	var closeOnePage = function(id) {
		var _tab = $("#tab_" + id);
		var focus_li = _tab.next("li");
		if (focus_li.length == 0) {
			focus_li = _tab.prev("li");
		}
		_tab.remove();
		$("#page_" + id).remove();
		if ($(".tab_active").length > 0) {
			focus_li = $(".tab_active");
		}
		if (focus_li.length > 0) {
			var focus_id = focus_li.attr("id");
			focus_id = focus_id.substr(4);
			focusOnePage(focus_id);
		}
		closeCrmSubWindow(id);
	}
	var slideTabs = function() {
		var _tab = $(".tab_active");
		//当活动标签在屏幕右边时，向左滑动
		if ((_tab.offset().left + _tab.width()) > $(window).width()) {
			$(".tabs_tool_btn").show();
			var _margin_left = $(window).width() - _tab.offset().left
					- _tab.width() + $(".tabs").offset().left;
			_margin_left -= 40;//滑动按钮的宽度+边距
			$(".tabs").animate({
				"margin-left" : _margin_left
			});
		}
		//当活动标签在屏幕左边时，向右滑动
		if (_tab.offset().left < 0) {
			$(".tabs_tool_btn").show();
			var _margin_left = $(".tabs").offset().left - _tab.offset().left;
			$(".tabs").animate({
				"margin-left" : _margin_left
			});
		}

		var _last_tab = $(".tab").last();
		//当最后一个标签不在屏幕最右边时,向右滑动
		if ($(".tabs").offset().left < 0
				&& (_last_tab.offset().left + _last_tab.width()) < $(window)
						.width()) {
			var _margin_left = $(".tabs").offset().left
					+ ($(window).width() - _last_tab.offset().left - _last_tab
							.width());
			_margin_left -= 40;
			if (_margin_left >= 0) {
				_margin_left = 0;
				$(".tabs_tool_btn").hide();
				$(".tabs").animate({
					"margin-left" : _margin_left
				});
			} else if (_tab.offset().left >= 0) {
				$(".tabs").animate({
					"margin-left" : _margin_left
				});
			}

		}
	}
	var showPC2 = function(_li) {
		var jq_li = $(_li);
		var jq_ul = jq_li.children("ul");
		jq_ul.show();
		if (jq_ul.offset().left + jq_ul.width() > $(document).width()) {
			var _left = jq_li.offset().left - jq_ul.width() + jq_li.width();
			jq_ul.css({
				"left" : _left
			});
		}
		jq_li.addClass("pc1_active");
	}
	var hidePC2 = function(_li) {
		var jq_li = $(_li);
		jq_li.children("ul").hide();
		jq_li.removeClass("pc1_active");
	}
	var resizeSlide = function() {
		if ($(".iframe_div:visible").length > 0) {
			var height = $(window).height()
					- $(".iframe_div:visible").offset().top
					- $("#footer").height();
			$(".iframe_div > iframe").height(height);
		}
		var div_height = $("#footer").offset().top
				- $(".multi_pages").offset().top;
		$(".multi_pages").height(div_height);
	}
	var resizeContainer = function() {
		var _width = $(document).width() - $("#header > img").width() - 100;
		var _item_width = $(".ca-item").width();
		var _item_count = $(".ca-item").length;
		_width = parseInt(_width / _item_width) * _item_width; //得到整数倍的item长度
		var _all_item_width = _item_width * _item_count;
		_width = _width < _all_item_width ? _width : _all_item_width
		$('#ca-container').css("width", _width);
	}
	window.onresize = function() {
		resizeSlide();
		resizeContainer();
	};
	window.onload = function() {
		resizeSlide();
		resizeContainer();
		$('#ca-container').contentcarousel();
	};
	function toggle_footer(id) {

		$("#" + id).toggle('normal', function() {
			if ($("#" + id).is(":hidden")) {
				$("#f_button").attr('class', 'footer_hidden');
			} else
				$("#f_button").attr('class', 'footer_show');
		});
	}
	function resetIframeSize(e,w,h)
	{
		
	}

	
	
	
	$(function(){
		$.ajax({
	        type: "GET",
	        url: "iframe.do?verbId=getComplaintsTODo",
	        dataType: "text",
	        success: function(data){
	        	$('#complaintsValue').html(data);
	           }
	    });
		
	});
	function openOtherWindow( id, name, src){
		if(src.substring(0,4).toUpperCase() !='HTTP'){
			if(src.substring(0, 1) == '/'){
				src = src.substring(1, src.length);
			}
			src = "<%=request.getContextPath()%>/" + src;
	}

	if ($("#page_" + id).length == 0) {
		var page_div = document.createElement("div");
		$(page_div).attr("id", "page_" + id);
		$(page_div).addClass("one_page");

		var leader = document.createElement("div");
		$(leader).addClass("leader");
		$(page_div).append(leader);
		var iframe_div = document.createElement("div");
		$(iframe_div).addClass("iframe_div");
		var iframe = document.createElement("iframe");
		
		iframe.src = src;
		$(iframe).attr("frameborder", "0");
		$(iframe).bind("activate", function() {
			Ext.menu.MenuMgr.hideAll();
			$.contextMenu.hidden();
		});
		$(iframe_div).append(iframe);
		$(page_div).append(iframe_div);
		$(".page_container").append(page_div);
		/*2015.03.17*/
		$(iframe_div).attr("ifid_"+id);
		$(iframe).attr("name","_"+id);
		$(iframe).attr("id","_"+id);
		var ee = document.getElementById("_"+id);
		var ed = ee.window;
		
		var li = document.createElement("li");
		$(li).attr("id", "tab_" + id);
		$(li).addClass("tab");
		$(li).addClass("tab_active");

		var name_span = document.createElement("span");
		$(name_span).addClass("page_name");
		$(name_span).append(document.createTextNode(name));
		$(name_span).attr("title", name);
		$(li).append(name_span);

		var close_span = document.createElement("span");
		$(close_span).addClass("close");
		$(close_span).attr("title", "关闭");
		$(li).append(close_span);
		$(li).contextMenu("right_key_menu", {
			bindings : {
				'refresh_page_btn' : function(x) {
					iframe.src = src;
				},
				'close_page_btn' : function(x) {
					closeOnePage(id);
				},
				'close_other_page_btn' : function(x) {
					var arr = $(".tab");
					for (var i = 0; i < arr.length; i++) {
						var temp = $(arr[i]);
						var temp_id = temp.attr("id");
						temp_id = temp_id.substr(4);
						if (temp_id != id) {
							$("#page_" + temp_id).remove();
							temp.remove();
						}
					}
					focusOnePage(id);
					slideTabs();
				},
				'single_window_btn' : function(x) {
					window.open(src, "_blank", null);
				}
			}
		});
		$(".tabs").append(li);

		var height = $(window).height() - $(iframe_div).offset().top
				- $("#footer").height();
		$(iframe).height(height);
	}
	focusOnePage(id);
	}
	
	
	function mainSearch(){
		var message=$("#mainSearchText").val();
		var mes = message.replace(/(^\s*)|(\s*$)/g, '');
		var reg=new RegExp("[%{}()（）【】‘’'\"]","g");//正则表达式
		var sc=mes.replace(reg,"");
		if(message!=null && sc!=""){
			closeOnePage('1256289171');
			openOtherWindow('1256289171',"查询","search/synthesisSearch.do?keyWord="+message+"&langue=1");
		}else{
			alert("请输入查询内容！");
		}
	}
	$(function(){
		hideHistoryData();
		$.ajax({
	        type: "GET",
	        url: "iframe.do?verbId=getComplaintsTODo",
	        dataType: "text",
	        success: function(data){
	        	$('#complaintsValue').html(data);
	           }
	    });
		$.ajax({
	        type: "GET",
	        url: "iframe.do?verbId=getConsultationTODo",
	        dataType: "text",
	        success: function(data){
	        	$('#consultationValue').html(data);
	           }
	    });
	});
	function getHistoryData(data,type){
		eval("var json = "+data);
		var tableData;
		if(type=="0"){
			tableData = "<table style='width:100%;'><tr><td style='width:20%;' >记录人</td><td style='width:30%;'>状态</td><td style='width:50%;'>记录时间</td></tr>";
		}
		for(var i=0;i<json.length;i++){
			tableData=tableData+"<tr><td>"+json[i].name+"</td>"+"<td>"+json[i].flag+"</td>"+"<td>"+json[i].time+"</td></tr>";
		}
		tableData = tableData+"</table>";
		$('#complaintsHistoryDetail').html(tableData);
		showHistoryData();
	}
	function hideHistoryData(){
		$('#complaintsHistoryDetail').hide();
		$('#timeControl').show();
	}
	function showHistoryData(){
		$('#complaintsHistoryDetail').show();
		$('#timeControl').hide();
	}
	function clearnHistorydate(){
		$('#complaintsHistoryDetail').html("");
	}
	
	function clickTextSubText(id){
		var textcomp = $('#complaintsHistoryDetail').html();
		if(id=="140100" && textcomp!=null && textcomp!=""){
			showHistoryData();
		}else{
			hideHistoryData();
		}
	}
	function closeCrmSubWindow(id){
		if(id=="140100"){
			clearnHistorydate();
		}
		hideHistoryData();
	}
//----------页面加载后获取根据操作人员获取主账号、密码，子帐号，密码，VOIP账号、密码，APPID，坐席编号-------------------
 var loginUrl;
 var loginPort;
 var appid;
 var agentId;
 var mainAccount;
 var mainAccountPwd;
 var subAccount;
 var subAccountPwd;
 var voipId;
 var voipPwd;
 var callType,callid,caller;
$(function(){
	function CallCenter::OnConnected(){
		alert("connect success");
		}
	function CallCenter::OnConnectError(s){alert("connect faild:"+s);}
	function CallCenter::OnIncomingCallReceived(msg){
		$('#calledno').html(msg.caller);
		$('#call_pan').show();
	}
	function CallCenter::OnCallProceeding (msg){alert(msg);}
	function CallCenter::OnCallAlerting(msg){alert(msg);}
	function CallCenter::OnCallAnswered(msg){alert(msg);}
	function CallCenter::OnMakeCallFailed(msg){alert(msg);}
	function CallCenter::OnCallMediaUpdateResponse(msg){alert(msg);}
	function CallCenter::OnFirewallPolicyEnabled(msg){alert(msg);}
	function CallCenter::OnCallReleased (msg){alert(msg);}
	function CallCenter::OnCallPaused (msg){alert(msg);}
	function CallCenter::OnResumed (msg){alert(msg);}
	function CallCenter::OnRecordingAmplitude (msg){alert(msg);}
	function CallCenter::OnRecordingTimeOut (msg){alert(msg);}
	function CallCenter::OnFinishedPlaying (msg){alert(msg);}
	$.ajax({
		type:"post",
		url:"${path}/agent/voip.do?verbId=agent",
		data:{},
		dataType:"json",
		success: function(data){
			if(typeof data.appid == 'undefined')
			{
				//说明用户没有VOIP账号
				return;
			}
			loginUrl=data.loginUrl;
			loginPort=data.loginPort;
			appid=data.appid;
			mainAccount=data.accountSid;
			mainAccountPwd=data.accountToken;
			subAccount=data.sunAccountSid;
			subAccountPwd=data.subAccountToken;
			voipId=data.voipId;
			voipPwd=data.voipPwd;
			agentId=data.agentId;
			if (CallCenter.callid == undefined) {//检查是否安装OCX控件
		  		alert("您还没有安装OCX控件");//您还没有安装OCX控件
			}
			var ccpInit=CallCenter.CCPinit();//初始化
			var ccpLogin=CallCenter.CCPlogin( loginUrl, loginPort,
					mainAccount, mainAccountPwd, 
					subAccount, subAccountPwd,
					voipId, voipPwd);//登陆
			if(ccpLogin=="0"){
				alert("连接OCX成功");
			}else{
				alert("连接OCX失败");
			}
		},
		error:function(data){
			alert("取值失败");
		}
	});

}
);

</script>
</head>
<body>
	<form id="form" name="form" method="post" action="security/security_loginSecond.do">
		<input type="hidden" name="verbId" value="" />
		 <input type="hidden" name="publicClassId" value="" />
		<div id="header">
			<img src="main/include/images/login_03.jpg" height="85"	style="float: left" />
			<ul class="nav">
				<li class="user">欢迎您！<%=sessionForm.getStaffName()%></li>
				<li><a
					href="<%=request.getContextPath()%>/security/security_login.do?verbId=reLogin">返回首页</a></li>
				<li><a
					href="<%=request.getContextPath()%>/index.jsp?agree=true">退出系统</a></li>
			</ul>
			<div id="topNav">
				<div id="ca-container" class="ca-container">
					<div class="ca-wrapper">
						<%
							if (pcList != null) {
								for (SecurityConfigPublicClass pc : pcList) {
									List<SecurityConfigPublicClass> childList = null;
									if (childMap != null) {
										childList = childMap.get(pc.getId());
									}
						%>
						<div class="ca-item">
							<div class="ca-item-main">
								<ul class="ul_pc_1" title="<%=pc.getClassName()%>">
									<%
										if (childList != null) {
									%>
									<li class="li_pc_1 pc_<%=pc.getId()%>"
										onmouseover="showPC2(this)" onmouseout="hidePC2(this)">
										<div style="cursor: pointer;width:70px;height:40px;position:relative;margin:0 auto;background-image: url(main/include/images/<%=pc.getPicPath() %>);"></div>
										<h3><%=pc.getClassName()%></h3>
										<ul class="ul_pc_2">
											<%
												for (SecurityConfigPublicClass child : childList) {
											%>
											<li class="li_pc_2 pc_<%=child.getId()%>"
												onclick="currentPublicClassName='<%=pc.getClassName()%>&nbsp;&nbsp;&gt;&gt;&nbsp;<%=child.getClassName()%>';getPublicByClassId('<%=child.getId()%>');"
												title="<%=child.getClassName()%>">
												<h4><%=child.getClassName()%></h4>
											</li>
											<%
												}
											%>
										</ul>
									</li>
									<%
										} else {
									%>
									<li class="li_pc_1 pc_<%=pc.getId()%>"
										onclick="currentPublicClassName='<%=pc.getClassName()%>';getPublicByClassId('<%=pc.getId()%>');"
										title="<%=pc.getClassName()%>">
										<div style="cursor: pointer;width:70px;height:40px;position:relative;margin:0 auto;background-image: url(main/include/images/<%=pc.getPicPath() %>);"></div>
										<h3><%=pc.getClassName()%></h3>
									</li>
									<%
										}
									%>
								</ul>
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
		<div id="toolbar"></div>
		<!-- phone bar -->
		<div style="width: 100%; background-color: rgb(209, 209, 209); height: 1px;"></div>
		<div style="width: 100%; background-color: #FFFFFF; height: 35px; line-height: 35px; font-size: 9pt; color: #888888">
			<div style="float: left">
				<span style="margin-left: 20px;">坐席号: 8001</span> <span
					style="margin-left: 20px;">当前状态: 通话中</span> <span
					style="margin-left: 20px;">主叫号码: 4008517517</span>
			</div>
			<div id="hjzxkz" style="float: left; margin-left: 30px">
			<!-- 
				<span class="phone_button phone_disable">摘机</span> <span
					class="phone_button phone_current">保持</span> <span
					class="phone_button"><a
					href="javascript:phone({event:3,data:{number:8002}});">转接</a></span> <span
					class="phone_button"><a
					href="javascript:phone({event:4,data:{number:13800138000}});">外拨</a></span>
				<span class="phone_button">磋商</span> <span class="phone_button">磋商转接</span>
				<span class="phone_button">外线</span> <span class="phone_button">暂停</span>
				<span class="phone_button">会议</span> <span class="phone_button">监听</span>
				<span class="phone_button">强插</span> <span class="phone_button">转分机</span>
				 -->
				<!-- 呼叫中心电话控制 zycb-->
				<span id="zhaiji" class="phone_button phone_disable" onclick="yangshizhuanhuan(id);">摘机</span> 
				<span id="baochi" class="phone_button phone_current" onclick="yangshizhuanhuan(id);">保持</span> 
				<span id="zhuanjie" class="phone_button phone_disable" onclick="yangshizhuanhuan(id);">转接</span> 
				<span id="waibo" class="phone_button phone_disable" onclick="yangshizhuanhuan(id);openWindow('bhtcc','bhzzc');">外拨</span>
				<div id="bhtcc" class="white_content">
					<div id="tccTitle" class="tccTitle">
						<span>拨号盘</span>
						<img src="<%=request.getContextPath()%>/style/img/button_close.png" onClick="closeWindow('bhtcc','bhzzc');"/>
					</div>
					<div id="tccNo" class="tccNo">&nbsp;<span id="pn" ></span></div>
					<table id="tccBhp"class="tccBhp">
						<tr>
							<td onclick="bohao('1');">1</td>
							<td onclick="bohao('2');">2</td>
							<td onclick="bohao('3');">3</td>
						</tr>
						<tr>
							<td onclick="bohao('4');">4</td>
							<td onclick="bohao('5');">5</td>
							<td onclick="bohao('6');">6</td>
						</tr>
						<tr>
							<td onclick="bohao('7');">7</td>
							<td onclick="bohao('8');">8</td>
							<td onclick="bohao('9');">9</td>
						</tr>
						<tr>
							<td onclick="bohao('*');">*</td>
							<td onclick="bohao('0');">0</td>
							<td onclick="bohao('#');">#</td>
						</tr>
						<tr>
							<td onclick="hujiao();">呼叫</td>
							<td onclick="houtui();">后退</td>
							<td onclick="guaduan();">挂断</td>
						</tr>
					</table>
				</div>
				<div id="bhzzc" class="black_overlay"></div>
				<span id="cuoshang" class="phone_button phone_disable" onclick="yangshizhuanhuan(id);">磋商</span> 
				<span id="cuoshangzhuanjie" class="phone_button phone_disable" onclick="yangshizhuanhuan(id);">磋商转接</span>
				<span id="waixian" class="phone_button phone_disable" onclick="yangshizhuanhuan(id);">外线</span> 
				<span id="zanting" class="phone_button phone_disable" onclick="yangshizhuanhuan(id);">暂停</span>
				<span id="huiyi" class="phone_button phone_disable" onclick="yangshizhuanhuan(id);">会议</span> 
				<span id="jianting" class="phone_button phone_disable" onclick="yangshizhuanhuan(id);openWindow('jttcc','jtzzc');">监听</span>
				<div id="jttcc" class="tccxllb">
					<TABLE id="jtTable" class="tccTable">
						<TBODY>
							<TR>
								<TD class="tccTd">
									<span>正在通话的人员</span>
									<img src="<%=request.getContextPath()%>/style/img/button_close.png" class="closeTcc" onClick="closeWindow('jttcc','jtzzc');"/>
								</TD>
							</TR>
							<TR>
								<TD>
									<DIV id="jtDiv" class="tccDiv">
										<P id="jt001" onmouseover="addBackgroundColor(this);" onmouseout="removeBackgroundColor(this);">张三</P>
										<P id="jt002" onmouseover="addBackgroundColor(this);" onmouseout="removeBackgroundColor(this);">李四</P>
										<P id="jt003" onmouseover="addBackgroundColor(this);" onmouseout="removeBackgroundColor(this);">王五</P>
										<P id="jt004" onmouseover="addBackgroundColor(this);" onmouseout="removeBackgroundColor(this);">赵六</P>
										<P id="jt005" onmouseover="addBackgroundColor(this);" onmouseout="removeBackgroundColor(this);">钱七</P>
										<P id="jt006" onmouseover="addBackgroundColor(this);" onmouseout="removeBackgroundColor(this);">李刚</P>
										<P id="jt007" onmouseover="addBackgroundColor(this);" onmouseout="removeBackgroundColor(this);">张三</P>
										<P id="jt008" onmouseover="addBackgroundColor(this);" onmouseout="removeBackgroundColor(this);">李四</P>
										<P id="jt009" onmouseover="addBackgroundColor(this);" onmouseout="removeBackgroundColor(this);">王五</P>
										<P id="jt010" onmouseover="addBackgroundColor(this);" onmouseout="removeBackgroundColor(this);">赵六</P>
										<P id="jt011" onmouseover="addBackgroundColor(this);" onmouseout="removeBackgroundColor(this);">钱七</P>
										<P id="jt012" onmouseover="addBackgroundColor(this);" onmouseout="removeBackgroundColor(this);">李刚</P>
									</DIV>
								</TD>
							</TR>
						</TBODY>
					</TABLE>
				</div>
				<div id="jtzzc" class="zzcxllb"></div>

				<span id="qiangcha" class="phone_button phone_disable" onclick="yangshizhuanhuan(id);openWindow('qctcc','qczzc');">强插</span>
				<div id="qctcc" class="tccxllb">
					<TABLE id="qcTable" class="tccTable">
						<TBODY>
							<TR>
								<TD class="tccTd">
									<span>正在通话的人员</span>
									<img src="<%=request.getContextPath()%>/style/img/button_close.png" class="closeTcc" onClick="closeWindow('qctcc','qczzc');"/>
								</TD>
							</TR>
							<TR>
								<TD>
									<DIV id="qcDiv" class="tccDiv">
										<P id="qc001" onmouseover="addBackgroundColor(this);" onmouseout="removeBackgroundColor(this);">张三</P>
										<P id="qc002" onmouseover="addBackgroundColor(this);" onmouseout="removeBackgroundColor(this);">李四</P>
										<P id="qc003" onmouseover="addBackgroundColor(this);" onmouseout="removeBackgroundColor(this);">王五</P>
										<P id="qc004" onmouseover="addBackgroundColor(this);" onmouseout="removeBackgroundColor(this);">赵六</P>
										<P id="qc005" onmouseover="addBackgroundColor(this);" onmouseout="removeBackgroundColor(this);">钱七</P>
										<P id="qc006" onmouseover="addBackgroundColor(this);" onmouseout="removeBackgroundColor(this);">李刚</P>
										<P id="qc007" onmouseover="addBackgroundColor(this);" onmouseout="removeBackgroundColor(this);">张三</P>
										<P id="qc008" onmouseover="addBackgroundColor(this);" onmouseout="removeBackgroundColor(this);">李四</P>
										<P id="qc009" onmouseover="addBackgroundColor(this);" onmouseout="removeBackgroundColor(this);">王五</P>
										<P id="qc010" onmouseover="addBackgroundColor(this);" onmouseout="removeBackgroundColor(this);">赵六</P>
										<P id="qc011" onmouseover="addBackgroundColor(this);" onmouseout="removeBackgroundColor(this);">钱七</P>
										<P id="qc012" onmouseover="addBackgroundColor(this);" onmouseout="removeBackgroundColor(this);">李刚</P>
									</DIV>
								</TD>
							</TR>
						</TBODY>
					</TABLE>
				</div>
				<div id="qczzc" class="zzcxllb"></div>
				<span id="zhuanfenji" class="phone_button phone_disable" onclick="yangshizhuanhuan(id);openWindow('zfjtcc','zfjzzc');">转分机</span>
				<div id="zfjtcc" class="tccxllb">
					<TABLE id="zfjTable" class="tccTable">
						<TBODY>
							<TR>
								<TD class="tccTd">
									<span>可转的人员</span>
									<img src="<%=request.getContextPath()%>/style/img/button_close.png" class="closeTcc" onClick="closeWindow('zfjtcc','zfjzzc');"/>
								</TD>
							</TR>
							<TR>
								<TD>
									<DIV id="zfjDiv" class="tccDiv">
										<P id="zfj001" onmouseover="addBackgroundColor(this);" onmouseout="removeBackgroundColor(this);">张三</P>
										<P id="zfj002" onmouseover="addBackgroundColor(this);" onmouseout="removeBackgroundColor(this);">李四</P>
										<P id="zfj003" onmouseover="addBackgroundColor(this);" onmouseout="removeBackgroundColor(this);">王五</P>
										<P id="zfj004" onmouseover="addBackgroundColor(this);" onmouseout="removeBackgroundColor(this);">赵六</P>
										<P id="zfj005" onmouseover="addBackgroundColor(this);" onmouseout="removeBackgroundColor(this);">钱七</P>
										<P id="zfj006" onmouseover="addBackgroundColor(this);" onmouseout="removeBackgroundColor(this);">李刚</P>
										<P id="zfj007" onmouseover="addBackgroundColor(this);" onmouseout="removeBackgroundColor(this);">张三</P>
										<P id="zfj008" onmouseover="addBackgroundColor(this);" onmouseout="removeBackgroundColor(this);">李四</P>
										<P id="zfj009" onmouseover="addBackgroundColor(this);" onmouseout="removeBackgroundColor(this);">王五</P>
										<P id="zfj010" onmouseover="addBackgroundColor(this);" onmouseout="removeBackgroundColor(this);">赵六</P>
										<P id="zfj011" onmouseover="addBackgroundColor(this);" onmouseout="removeBackgroundColor(this);">钱七</P>
										<P id="zfj012" onmouseover="addBackgroundColor(this);" onmouseout="removeBackgroundColor(this);">李刚</P>
									</DIV>
								</TD>
							</TR>
						</TBODY>
					</TABLE>
				</div>
				<div id="zfjzzc" class="zzcxllb"></div>
				<!-- 呼叫中心电话控制 zyce-->
			</div>
			<div
				style="float: right; height: 27px; line-height: 27px; padding: 0px; margin-top: 5px; margin-right: 5px">
				<input type="text" id="mainSearchText"
					style="width:133px;height:27px;line-height:27px;border:none;+border:0;padding-left:30px;background-image:url('<%=request.getContextPath()%>/include/images/search_box.jpg')"><input
					type="button" value="搜索" onclick="mainSearch()"
					style="margin:0;margin-left:-3px;width:44px;text-align:center;height:27px;line-height:27px;border:none;background-color:rgb(131,204,86);color:white;background-image:url('<%=request.getContextPath()%>/include/images/search_box.jpg');background-position:center right">
			</div>
		</div>
		<div style="width:100%;background-image: url('<%=request.getContextPath()%>/include/images/v.jpg');height:11px;"></div>

		<div style="width: 100%; background-color: rgb(239, 239, 239)">
			<div style="width: 17%; float: right;">

				<div class="right_black">
					<div class="right_history_blank">
						<div class="right_title">
							<span>历史信息</span>
						</div>
						<div class="line"></div>
						<div class="right_content_blank" >
							<div style="width:221px; height:200px;  overflow:auto;" id="complaintsHistoryDetail" ></div>
							<div style="width:221px; height:200px;" class="easyui-calendar" id="timeControl" ></div>
						</div>
					</div>
					<div class="line_15"></div>
					<div class="right_history_blank">
						<div class="right_title">
							<span>工作日志</span>
						</div>
						<div class="line"></div>
						<div class="log_item odd">
							<a href="javascript:openOtherWindow('129128917','随访信息处理','');"><span>随访提醒（<label id="">9</label>）</span></a>
						</div>
						<div class="log_item even">
							<a href="javascript:openOtherWindow('129128918','投诉信息处理','complaints.do?verbId=list&type=2');"><span>投诉提醒（<label id="complaintsValue"></label>）</span></a>
						</div>
						<div class="log_item odd">
							<a href="javascript:openOtherWindow('129128919','咨询信息处理','crm/crmConsultation.do?verbId=queryConsultation&executedFlag=1');"><span>咨询提醒（<label id="consultationValue"></label>）</span></a>
						</div>
						<div class="log_item even">
							<span>聊天信息（14）</span>
						</div>
						<div class="log_item odd">
							<span>院内通知（1）</span>
						</div>
					</div>


				</div>


			</div>
			<div style="width: 83%; float: left">
				<div
					style="margin: 0 auto; margin-left: 10px; margin-right: 10px; border: 1px rgb(210, 210, 210) solid; background-color: #FFFFFF">
					<div class="multi_pages">
						<ul class="tabs">
						</ul>
						<div class="tabs_tool_btn">
							<div class="prev_tab_btn" title="按住向前滑动"></div>
							<div class="next_tab_btn" title="按住向后滑动"></div>
						</div>
						<div class="page_container"></div>
						<div class="contextMenu" id="right_key_menu">
							<ul>
								<li id="refresh_page_btn">刷新</li>
								<li id="close_page_btn">关闭</li>
								<li id="close_other_page_btn">关闭其他</li>
								<li id="single_window_btn">独立窗口显示</li>
							</ul>
						</div>
					</div>
				</div>
			</div>
		</div>
	</form>
	<div id="footer">
		<div style="margin: 0 auto; margin-left: 10px; margin-right: 10px; border: 1px rgb(210, 210, 210) solid; background-color: #FFFFFF">
			<div style="height: 30px; width: 100%; background-color: rgb(249, 249, 249)">
				<div class="margin_left"></div>
				<div class="tool_tab">基本信息</div>
				<div class="tool_tab tool_tab_active">最近诊断</div>
				<div class="tool_tab">最近手术</div>
				<div class="tool_tab">最近用药</div>
				<div class="tool_tab">检查</div>
				<div class="tool_tab">检验</div>
				<div class="tool_tab">费用</div>
				<div class="tool_tab">体检</div>
				<div class='footer_show' onclick="toggle_footer('fbody')" id="f_button"></div>
				<div style="clear: both"></div>
			</div>
			<div style="height: 180px; width: 100%" id="fbody">
			<div style="height: 1px; background-color: rgb(210, 210, 210); width: 100%"></div>
			
			</div>
		</div>
	</div>
	
<div id="call_pan" style="width:421px;height:290px;background-image:url('${path}/include/images/calling-back.png');position:absolute;top:100px;left:25%;display:none">
  <div style="text-align:right;padding:3px"><img alt="" src="${path}/include/images/calling_close.png" onclick="$('#call_pan').hide()"></div>
  <div style="height:60px;"></div>
  <div style="text-align:center;font-size:38pt;color:white;height:100px;line-height:100px;font-family:Arial" id="calledno">18702321980</div>
  <div style=""><input type="button" style="width:95px;height:40px;background-image:url(${path}/include/images/ts.png);border:0;border:none;margin-left:85px"><input type="button" style="width:95px;height:40px;background-image:url(${path}/include/images/zx.png);border:0;border:none;margin-left:60px"></div>
</div>
<OBJECT ID="CallCenter" CLASSID="CLSID:F20C5A15-A3E3-4375-9A8B-8275489017B8" style="width:0px;height:0px;"></OBJECT>
	
</body>
</html>

