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
<link rel="stylesheet" type="text/css"
	href="main/include/css/index_n.css" />
<title>:::<%=application.getAttribute("security.SYSTEMNAME")%>
	:::
</title>

	<link href="<%=request.getContextPath()%>/style/styles.css" rel="stylesheet" type="text/css"/>
	<link href="<%=request.getContextPath()%>/style/emoji.css" rel="stylesheet" type="text/css"/>
	<link href="<%=request.getContextPath()%>/style/jishixiaoxi.css" rel="stylesheet" type="text/css"/>
	<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/style/demo.css">

<link rel="stylesheet" type="text/css"	href="<%=request.getContextPath()%>/style/easyui/themes/default/easyui.css">
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
<link rel="stylesheet" type="text/css"
	href="<%=request.getContextPath()%>/style/callCenterControls.css" />
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/callCenterControls.js"></script>
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/style/default.css">
<link rel="stylesheet" type="text/css"	href="<%=request.getContextPath()%>/style/easyui/themes/default/calendar.css">
<script type="text/javascript"	src="<%=request.getContextPath()%>/style/easyui/locale/easyui-lang-zh_CN.js"></script>		
	<script src="<%=request.getContextPath()%>/js/jishixiaoxi.js"></script>
	<script src="<%=request.getContextPath()%>/js/json2.js"></script>
	<script src="<%=request.getContextPath()%>/js/emoji.js"></script>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/js/mainTab.js"></script>
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/style/mainTab.css">
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/style/outpPrescInfo.css">
<link rel="stylesheet" type="text/css"	href="<%=request.getContextPath()%>/style/easyui/themes/icon.css">
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/style/electronic.css">
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/style/outpExamInfo.css">
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/style/outpLabInfo.css">
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/style/outpBillInfo.css">
<link type="text/css" rel="stylesheet" href="<%=request.getContextPath()%>/style/kbs.css"/>
<style type="text/css">
.toggleDiv{
   width:243px;
   float:right;
   background-image:url(<%=request.getContextPath()%>/main/include/images/toggleBg.jpg);
   background-repeat:repeat-y;
   background-color:white;
   border-top:1px solid #dddddd;
   position:relative;
   border-bottom:1px solid #dddddd;
  
}
.displayDiv{
  margin-right:243px;
}
.toggleButton{
   position:absolute;
   width:9px;
   height:71px;
   left:2px;
   background-image:url(<%=request.getContextPath()%>/main/include/images/closed.png);
   top:200px;
   cursor:pointer;
}
.right_black {
	width: 225px;
	height: 500px;
	font-size: 10pt;;
	background-color: #FFFFFF;
	margin-left:14px;
	 overflow:hidden;
}

.right_history_blank {
	width: 100%;
	border: 1px #DDDDDD solid;
	height: 245px;
	border-top:none;
	border-left:none;
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
	
	/* 获取OCX控件对象 */
	/*聊天记录点击次数*/
	var display=0;
	/*聊天记录框是否显示*/	
	function chatMessage(){
		if (display==1){
			var v=	document.getElementById('chats');
			var r=	document.getElementById('records');
			v.style.display="inline";
			r.style.display="none";
			display=0;
		}else {
			var v=	document.getElementById('chats');
			var r=	document.getElementById('records');
			v.style.display="none";
			r.style.display="inline";
			display=1;
			var pageNo = $("#chatsNum").val();
			getMsg(pageNo,"2");
		}
	}
	/* 关闭聊天记录框 */
	function closeChats(){
		var v=	document.getElementById('chats');
		v.style.display="none";
	}
	
	/*-------------------------------表情------------------------------------------*/
	
	
	//标记表情面板是否已经打开
	var emojiflag=false;
	/* 加载表情 */
	function loadBiaoQing(){
		//如果表情面板没打开
		if(!emojiflag){
			var str="";
			var json=jEmoji.EMOJI_MAP;
			for(var key in json){
					str+=key;
			}
			 var message=jEmoji.unifiedToHTML(str);
			$(".wrap_bq").html(message);
			//显示表情弹出框
			$(".wrap_bq").show();
			
			$(".biaoqing").css("background","url(<%=request.getContextPath()%>/include/images/biaoqing_down.png) 0 3px no-repeat");
			//打开表情面板
			emojiflag=true;
		}else{
			 //隐藏表情弹出框
			 $(".wrap_bq").hide();
			 $(".biaoqing").css("background","url(<%=request.getContextPath()%>/include/images/biaoqing.png) 0 3px no-repeat");
			//关闭表情面板
		     emojiflag=false;
		}
	}
	

	function loadfujian(){
			 $(".fujian").css("background","url(<%=request.getContextPath()%>/include/images/fujian_down.png) 0 3px no-repeat");
			 document.getElementById("file1").click();
			 $(".fujian").css("background","url(<%=request.getContextPath()%>/include/images/fujian.png) 0 3px no-repeat");		
	}
	
	function dealVoice(){
			$('#dlg').dialog('open') ;
			makeVoice();
 		}

	function emojitohtml(value){
		var text = jEmoji.softbankToUnified(value);
		 var message=jEmoji.unifiedToHTML(text);
		 fn_insertPos(message+" ");
		 //隐藏表情弹出框
		 $(".wrap_bq").hide();
	}
	//在光标处插入内容
	function fn_insertPos(textValue)
	{     
		//var msg=document.getElementById("msg");
	    if(pos!=null)
	    {
	       // pos.text="插入文字内容";
	        pos.pasteHTML(textValue);
	        //设置光标位置函数
	        
	        //释放位置
	        pos=null;
	    }else{
	    	$("#msg").append(textValue);
	    }  
	} 
 	function stop(){ 
		return false; 
		} 
		document.oncontextmenu=stop; 
		
		document.onkeydown = function(){
			if(window.event && window.event.keyCode == 123) {
			    return false;   
		    }
		}
	</script>
  <script type="text/javascript">
  
 		function pageLoad(){
			/* 判断是否已经安装OCX控件 */
			if (CallCenter.callid == undefined) {// 检查是否安装OCX控件
				alert("您还没有安装OCX控件,请先下载安装！");// 您还没有安装OCX控件

				window.location.href='http://docs.cloopen.com/images/a/ac/CCP_PC_OCX_WINDOWS_v2.5.7r.zip';
			}
			/* 初始化SDK，在调用其他接口之前调用。 */
			var initParm=CallCenter.CCPinit();
			if(initParm!="0"&&initParm!="-1000"){
				alert("初始化失败!");
				//return;
			}
			
  			var RESTIP = $("#RESTIP").val();
			var RESTPOST =$("#RESTPOST").val();
			var accountSid =$("#accountSid").val();
			var authToken = $("#authToken").val();
			var subaccount = $("#subaccount").val();
			var subaccounttoken = $("#subaccounttoken").val();
			var voip =$("#voip").val();
			var voiptoken = $("#voiptoken").val(); 
			
			 var v=CallCenter.CCPlogin(RESTIP,RESTPOST,accountSid,authToken
					,subaccount,subaccounttoken
					,voip,voiptoken); 
				//alert(v);
			if(v!='0'){
				alert("未登陆成功!");
			} 
		}
 		
 		//-------------语音-------------//
 		 var time = 60;
 		 var timeOut = 0;//0 超时 1，未超时
 		function uuid() {
		    var s = [];
		    var hexDigits = "0123456789abcdef";
		    for (var i = 0; i < 36; i++) {
		        s[i] = hexDigits.substr(Math.floor(Math.random() * 0x10), 1);
		    }
		    s[14] = "4";  // bits 12-15 of the time_hi_and_version field to 0010
		    s[19] = hexDigits.substr((s[19] & 0x3) | 0x8, 1);  // bits 6-7 of the clock_seq_hi_and_reserved to 01
		    s[8] = s[13] = s[18] = s[23] = "-";
		    var uuid = s.join("");
		    return uuid;
		}
 		
 		var filepath="";
 		function makeVoice(){
 			
 			//获取当前准备发送的voip
 			var voip=$("#currentVoipIp").val();
 			if(voip==""){
 				alert("请选择VoIP!");
 				return false;
 			}
 			$(".voice").css("background","url(<%=request.getContextPath()%>/include/images/yuyin_down.png) 0 3px no-repeat");//设置鼠标按下控件的样式
 			$(".voice").attr("disabled",true);   //设置控件的属性
 			//颜色置灰
 			$(".voice").css("color","gray");
 			$("#graph").show();	
 			time = 60;
 			go();
 			//生成js uuid
 			 filepath=fileDownPath+"\\"+uuid()+".amr";
 			var v = CallCenter.CCPstartVoiceRecording(filepath,voip);
 			timeOut = 0 ;
 		}
 		/* 语音时间条 */
		
 		function go() {
 			var bar=$("#bar");
 			if ( time > 0) {
 				bar.width(bar.width() + 4.5 + "px");
 				time--;
 				bar.text(time);
 				setTimeout(function(){go();},1000);
 			} else if(time == 0){//当time==0 时，发送语音消息
 				time = -1;
 				if(timeOut==0){
 					alert("录音超时，请重试！");
 				}
 				$(".voice").attr("disabled",false);	
 				//bar.width(0);	
 				$(".graph").hide();	
 				cancelVoice();
 				timeOut = 1;//设置为未超时
 		     }
 		}
 		//如果sendflag=true,则点击的是"取消";sendflag=false,则点击的是"发送"
 		var sendflag=false;
 		function sendVoice1(){
 			sendflag=false;	
 			time=0;
 			$(".voice").attr("disabled",false);
 			$(".voice").css("background","url(<%=request.getContextPath()%>/include/images/yuyin.png) 0 3px no-repeat");
 			//停止录音
 			CallCenter.CCPstopVoiceRecording();
 			scro();
 		}
 		//发送语音
 		function sendvoice(){
 			var voip=$("#currentVoipIp").val();
 			var v=CallCenter.CCPsendInstanceMessage(voip,"",filepath,"");
 			//保存聊天记录
 			sendMsgSaveServer(filepath,"2",$("#voip").val(),$("#fromVoip").val(),"1","语音",false,true,$("#hspName").val(),$("#userName").val(),$("#hspConfigBaseinfoId").val(),$("#idHidden").val());
 			var json=eval('('+v+')');
 			$("#msg").text("");

 			if(json.msgId!="" && json.result=='0'){
 				var timeLoong = CallCenter.CCPgetVoiceDuration(filepath);
 				var lastIndex = filepath.lastIndexOf("\\");
 				var fileName = filepath.substring(lastIndex+1, filepath.length);
 				$("#send_get").append("<div class='replyer'><img class='pic2' src='<%=request.getContextPath()%>/include/images/s_touxiang.jpg' alt=''/><span><a href='javascript:void(0)' onclick='palyVoice(\""+fileName+"\")'>语音</a></span>"+(timeLoong/1000)+"秒</div>");
 			}else{
 				alert("发送失败");
 			}
 		}
 		
  		//文件存放目录
 		var fileDownPath="";
 		$(function(){
 			setTimeout(function(){
 				fileDownPath=CallCenter.appDataDir;
 		   		/* 消息到达事件
 		 		function CallCenter::OnReceiveInstanceMessage(msg){
 		 			acceptmsg(msg);
 		 		} */
 		 		//语音停止录制
 		 		function CallCenter::OnRecordingTimeOut(msg){
 		 			OnRecordingTimeOut(msg);
 		 		}
 		 		function OnRecordingTimeOut(msg){
 		 			if(!sendflag){
 		 				//发送语音	
 		 				sendvoice();
 		 				deleteVoice();
 		 			}	
 		 			
 		 		}
 		 		/* 接收到语音文件和附件时，回调事假 -- 多媒体消息事件 */
 		 		function CallCenter::OnReceiveInstanceMessageMedia(msg){
 		 			OnReceiveInstanceMessageMedia(msg);
 		 		}
 				
 				
 			}, 10000); 
 		
 		});
 			
 		function OnReceiveInstanceMessageMedia(msg){
 			var json=eval('('+msg+')');
 			if(json.fileExt=="amr"){
 				acceptvoiceFile(json);/* 接收语音文件 */
 			}else{
 				acceptFile(json);/* 接收附件文件 */
 			}
 			scro();
 		}
 		function acceptvoiceFile(json){
 			var voip=$("#currentVoipIp").val();
 			var  filename=json.fileUrl.split('=')[1];
 			var path=fileDownPath+"\\"+filename;
 			//获取语音文件目录
 				//下载附件 
 			var param = new Array();
 			param[0] = new Object();
 			param[0]["fileName"]=path;
 			param[0]["fileUrl"]=json.fileUrl;
 			param[0]["msgId"]=json.msgId;
 			param[0]["sender"]=json.sender;

 			var strjson = JSON.stringify(param);
 				var v=CallCenter.CCPdownloadAttached(strjson,"");
 				if(v!=0){
 					alert("语音文件下载失败!");
 					return false;
 				}
 				var sender="";
 				var fun="";
 				var index=json.receiver.indexOf("g");
 				if(index==-1){
 					sender=json.sender;	
 					fun="showMsg(this)";
 				}else if(index!=-1){//消息来自群组
 					sender=json.receiver;	
 					//fun="showMsg(this,\"group\")";
 					fun="showMsg(this)";
 				}
 	 			//保存聊天记录
 	 			//sendMsgSaveServer(path,"2",json.sender,json.receiver,"1","语音",true,false,$("#userName").val(),$("#hspName").val(),$("#hspConfigBaseinfoId").val(),$("#idHidden").val());
 				var timeLoong = CallCenter.CCPgetVoiceDuration(path);
 				if(sender==voip){
 						$("#send_get").append("<div class='sender'><img class='pic1' src='<%=request.getContextPath()%>/include/images/s_touxiang.jpg'/><img class='pic1' src='<%=request.getContextPath()%>/include/images/tag2.png' alt='' onclick='palyVoice(\""+filename+"\");'/><span></span>"+(timeLoong/1000)+"秒</div>");	
 				}else{
 					$("#huihua").append("<li class='clearfix' style='cursor:hand' onclick='"+fun+"'>"+
 							"<img class='touxiang' src='<%=request.getContextPath()%>/include/images/b_touxiang.jpg' alt=''/>"+
 							"<div class='info'>"+
 							"<span>"+sender+"</span>"+
 							"<em style='display: none;'><div class='sender'><img class='pic1' src='<%=request.getContextPath()%>/include/images/tag2.png' alt='' onclick='palyVoice(\""+filename+"\");'/><span></span>"+(timeLoong/1000)+"秒</div></em>"+
 							"<em>[语音]</em>"+
 							"</div>"+
 							"<i class='dotted'></i>"+
 							"</li>");
 							$(".tablist").find("i").attr("class","dotted");
 				}
 		}

/*  		function acceptFile(json){
 			var voip=$("#currentVoipIp").val();
 			var filename=json.userData;
 			var fileurl=json.fileUrl;
 			var msgId=json.msgId;
 			var sender=json.sender;
 				var sender="";
 				var fun="";
 				var index=json.receiver.indexOf("g");
 				if(index==-1){
 					sender=json.sender;	
 					fun="showMsg(this)";
 				}else if(index!=-1){//消息来自群组
 					sender=json.receiver;	
 					//fun="showMsg(this,\"group\")";
 					fun="showMsg(this)";
 				}
 				if(sender==voip){
 					$("#send_get").append("<div class='sender'><span><a href=\"javascript:savefile('"+filename+"','"+fileurl+"','"+msgId+"','"+sender+"')\">"+filename+"->接收</a></span></div>");
 				}else{
 					$("#huihua").append("<li class='clearfix' style='cursor:hand' onclick='"+fun+"'>"+
 							"<img class='touxiang' src='<%=request.getContextPath()%>/include/images/b_touxiang.jpg' alt=''/>"+
 							"<div class='info'>"+
 							"<span>"+sender+"</span>"+
 							"<em style='display: none;'><div class='sender'><span><a href=\"javascript:savefile('"+filename+"','"+fileurl+"','"+msgId+"','"+sender+"')\">"+filename+"->接收</a></span></div></em>"+
 							"<em>[附件]</em>"+
 							"</div>"+
 							"<i class='dotted'></i>"+
 							"</li>");
 							$(".tablist").find("i").attr("class","dotted");
 				}
 		} */
 		/* 播放语音 */
 		function palyVoice(filename){
 			var path=fileDownPath+"\\"+filename;
 			var v=CallCenter.CCPplayVoiceMsg(path,"");
 			if(v!='0'){
 				alert("播放失败!");
 				return;
 			}
 		}
 		//取消
 		function cancelVoice(){
 			sendflag=true;
 			CallCenter.CCPstopVoiceRecording();
 			deleteVoice();
 		}
 		function deleteVoice(){	
			var bar=$("#bar");
			bar.width(0); 
			timeOut = 1;
			time = 0;
 			$('#dlg').dialog('close');
 			$(".voice").attr("disabled",false);
 			$(".voice").css("background","url(<%=request.getContextPath()%>/include/images/yuyin.png) 0 3px no-repeat");
 		}
 	 	//保存聊天记录
 	   	function sendMsgSaveServer(msg,type,fromVoipId,toVoip,isFile,fileName,isHospital,isUser,hspName,userName,hspId,userId){
 	 	   		$.ajax( {
 	   			type : "post",
 	   			url : '${path}/msg/crmChatMessageRecord.do?verbId=saveMsgToServer',
 	   			data:{"fromVoip":fromVoipId,"toVoip":toVoip,"content":msg,
 	   				"isFile":isFile,"fileName":fileName,"type":type,
 	   				"isHospital":isHospital,"isUser":isUser,
 	   				"toUserBaseinfoName":hspName,"fromUserBaseinfoName":userName,
 	   				"toUserBaseinfoId":hspId,"fromUserBaseinfoId":userId
 	   				},
 	   			dataType : "json",
 	   			success : function(data) {
 	   				var pg = eval(data);
 	   				try {

 	   				} catch (e) {
 	   					alert(e);
 	   				}
 	   			}
 	   		});
 	   	}
 	 //获取聊天记录
 	   function getMsg(pageIndex,type){
 	   	$.ajax({
 	   		url : '${path}/msg/crmChatMessageRecord.do?verbId=getChatRecord',
 	   		type:"post",
 	   		data:{"fromVoip":$("#fromVoip").val(),"toVoip":$("#currentVoipIp").val(),"type":type,"pageIndex":pageIndex},
 	   		dataType:"json",
 	   		success:function(msg){
 	   			if(msg.count==undefined){
 	   				$("#countPage").text("/0");
 	   			}else{
 	   				$("#countPage").text("/"+msg.count);
 	   				$("#countPage1").val(msg.count);
 	   			}
 	   			var msgContent = $("#msgContent");
 	   			msgContent.empty();
 	   			for(var i=0;i<msg.list.length;i++){
 	   				var ss = msg.list[i].content;
 	   				var pos = ss.lastIndexOf(".");
 	   				var ty = ss.substring(pos+1,ss.length);
 	   				var color = "";
 	   				if(msg.list[i].fromVoip==$("#fromVoip").val()){
 	   					color = "green";
 	   				}else{
 	   					color = "blue";
 	   				}
 	   				if(msg.list[i].isFile!=""){
 	   					if(ty=="amr"){
 	   						var timeLoong = CallCenter.CCPgetVoiceDuration(ss);
 	   		 				var lastIndex = ss.lastIndexOf("\\");
 	   		 				var fileName = ss.substring(lastIndex+1, ss.length);
 	   						var m = "<font color=\""+color+"\" style='margin-left:10px;'>"+msg.list[i].fromUserBaseinfoName+"("+msg.list[i].createTime+")</font><br/>" +
 	   								"<div class='sender'><span><a href='javascript:void(0)' onclick='palyVoice(\""+fileName+"\")'>语音</a></span>"+(timeLoong/1000)+"秒</div>";
 	   						msgContent.append(m);
 	   					}else if(ty=="jpg" || ty == "bmp"||ty=="gif" || ty=="jpeg"){
 	   						var m = "<font color=\""+color+"\" style='margin-left:10px;'>"+msg.list[i].fromUserBaseinfoName+"("+msg.list[i].createTime+")</font><br/>" +
 	   						"<div>&nbsp;&nbsp;&nbsp;<img src='"+msg.list[i].content+"' onload=\"javascript:if(this.height>this.width){this.height=150}else{this.width=150}\"/></div>";
 	   						msgContent.append(m);
 	   					}else{
 	   						var lastIndex = ss.lastIndexOf("\\");
 	   		 				var fileName = ss.substring(lastIndex+1, ss.length);
 	   						var m = "<font color=\""+color+"\" style='margin-left:10px;'>"+msg.list[i].fromUserBaseinfoName+"("+msg.list[i].createTime+")</font><br/>" +
 	   						"<div class='sender'><span><a href=\"javascript:savefile('"+msg.list[i].content+"','"+fileName+"','"+""+"','"+""+"')\">附件</span>"+msg.list[i].fileName+"</div>";
 	   						msgContent.append(m);
 	   					}
 	   				}else{
 	   					var m = "<font color=\""+color+"\" style='margin-left:10px;'>"+msg.list[i].fromUserBaseinfoName+"("+msg.list[i].createTime+")</font><br/><div>&nbsp;&nbsp;&nbsp;"+msg.list[i].content+"</div>";
 	   					msgContent.append(m);
 	   				}
 	   			}
 	   		},
 	   		error:function(msg){
 	   			//alert(msg);
 	   		}
 	   		
 	   	});
 	   }
  </script>
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
			var height = pageHeight-300-60;
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
			var height = pageHeight-300-60;
			$(".iframe_div > iframe").css("height", height);
		}
		var div_height =pageHeight-300;
		$(".multi_pages").css("height", div_height);

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
		//resizeSlide();
		resizeContainer();
	};
	var pageHeight;
	$(function() {
		//判断文档高度
		pageHeight=$(document).height()<window.screen.availHeight?window.screen.availHeight:$(document).height();
		resizeSlide();
		resizeContainer();
		/*头部导航栏*/
		$('#ca-container').contentcarousel();
		/*页面滚动时显示导航栏下级菜单*/
		var bg_wrapper=$('#ca-container .bg_wrapper');
		var ul_pc_2=$('#ca-container .ul_pc_2');
		$('body').scroll(function(){
			   var top=-$(this).scrollTop()+83;
			   bg_wrapper.css('top',top);
			   ul_pc_2.css('top',top);
               
			})
	});
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
		var height = pageHeight-300-60;
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
			openOtherWindow('1256289171',"查询","search/synthesisSearch.do?keyWord="+encodeURIComponent(message)+"&langue=1");
		}else{
			alert("请输入查询内容！");
		}
	}
	$(function(){
		hideHistoryData();
		$.ajax({
	        type: "post",
	        url: "iframe.do?verbId=getComplaintsTODo",
	        dataType: "text",
	        success: function(data){
	        	$('#complaintsValue').html(data);
	           }
	    });
		$.ajax({
	        type: "post",
	        url: "iframe.do?verbId=getConsultationTODo",
	        dataType: "text",
	        success: function(data){
	        	$('#consultationValue').html(data);
	           }
	    });
		
		$.ajax({
	        type: "post",
	        url: "<%=request.getContextPath()%>/followUp/followUpPlan.do?verbId=personNum",
	        dataType: "text",
	        success: function(data){
	        	$('#followUpValue').html(data);
	           }
	    });
		$.ajax({
	        type: "post",
	        url: "<%=request.getContextPath()%>/satisfied/satisfiedPlan.do?verbId=personNum",
	        dataType: "text",
	        success: function(data){
	        	$('#satisfedValue').html(data);
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
			tableData=tableData+"<tr><td>"+json[i].name+"</td>"+"<td>"+json[i].flag+"</td>"+"<td><a href=\"javascript:showComplaintsData('"+json[i].cid+"')\">"+json[i].time+"</a></td></tr>";
		}
		tableData = tableData+"</table>";
		$('#complaintsHistoryDetail').html(tableData);
		showHistoryData();
	}
	
	function getHistoryFollowUp(json,type){
		var tableData;
		if(type=="0"){
			tableData = "<table style='width:100%;'><tr  style=\"font-weight: bold;text-align: center;\" ><td style='width:40%;' >随访时间</td><td style='width:30%;'>状态</td><td style='width:30%;'>随访人</td></tr>";
		}
		for(var i=0;i<json.length;i++){
			tableData=tableData+"<tr style=\"text-align: center;\"  onmouseover=\"this.style.background='#EAF2FF'\" onmouseout=\"this.style.background='#ffffff'\"><td title=\"随访内容： \"><label style=\"cursor: pointer;\" onclick=\"showFollowUpDetal('"+json[i].taskId+"','"+json[i].planId+"','"+json[i].personId+"','"+json[i].authorId+"')\">"+json[i].followUpTime+"</label></td>"+"<td>"+json[i].resultStatus+":"+json[i].followStatus+"</td>"+"<td>"+json[i].followName+"</td></tr>";
		}
		tableData = tableData+"</table>";
		$('#complaintsHistoryDetail').html(tableData);
		showHistoryData();
	}
	
	function getHistoryConsultation(json,type){
		var tableData;
		if(type=="0"){
			tableData = "<table style='width:100%;'><tr  style=\"font-weight: bold;text-align: center;\" ><td style='width:40%;' >咨询时间</td><td style='width:30%;'>状态</td><td style='width:30%;'>记录人</td></tr>";
		}
		for(var i=0;i<json.length;i++){
			tableData=tableData+"<tr style=\"text-align: center;\"  onmouseover=\"this.style.background='#EAF2FF'\" onmouseout=\"this.style.background='#ffffff'\"><td title=\"咨询内容："+json[i].consultationComments+" \"><label style=\"cursor: pointer;\" onclick=\"showConsultationDetal('"+json[i].id+"')\">"+json[i].consultationTime+"</label></td>"+"<td>"+json[i].executedFlagId+"</td>"+"<td>"+json[i].executedOperateHuman+"</td></tr>";
		}
		tableData = tableData+"</table>";
		$('#complaintsHistoryDetail').html(tableData);
		showHistoryData();
	}
	function showComplaintsData(id){
		openOtherWindow('129128917','投诉信息查看','complaints.do?verbId=showData&type=1'+'&id='+id);
	}
	function showFollowUpDetal(taskId,planId,pid,authorId){
		openOtherWindow(Math.floor(Math.random()*1000000),'随访历史查看','followUp/historicalFollowUp.do?verbId=detailHistory&taskId='+taskId+'&planId='+planId+'&pid='+pid+'&authorId='+authorId);
	}
	function showConsultationDetal(id){
		openOtherWindow(Math.floor(Math.random()*1000000),'咨询信息查看','crm/crmConsultation.do?verbId=detail&type=1&id='+id);
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
		}else if(id=="402880f94c112879014c1142e7d7005b" && textcomp!=null && textcomp!=""){ 
			showHistoryData();
		}else{
			hideHistoryData();
		}
	}
	function closeCrmSubWindow(id){
		var textcomp = $('#complaintsHistoryDetail').html();
		if(id=="140100"){
			clearnHistorydate();
			hideHistoryData();
		}else if(id=="402880f94c112879014c1142e7d7005b"){
			clearnHistorydate();
			hideHistoryData();
		}else if(textcomp!=null && textcomp!=""){
			showHistoryData();
		}
		
	}
function addBg(value){
	$(value).addClass("liActive");
	
}
function deleteBg(value){
	$(value).removeClass("liActive");
}
</script>
<style>
html{
    overflow:hidden;
    height:100%;
}
body{
    height:100%;
    background-color: rgb(239, 239, 239);
    overflow-y:scroll;
   
}
 #footer table{
 padding-top:5px;
 }
 #footer table td{
   color:black;
   padding:0px;
   height:20px;
   margin:0px;
}
#footer table td div{
   margin-left:10px;
   position:relative;
   background-color:#fafafa;
   border:1px solid #dedede;
   width:100%;
   padding-left:10px;
   margin-left:-10px;
   line-height:20px;
}
#footer #jbxx .tab_table {
	width:90%;
	border:none;
	margin-top:10px;
	margin-left:20px;
}
#footer #jbxx .tab_table td{
   text-align:right;
}
#footer .tab_table{
	border-collapse:collapse;
	margin-top:10px;
	margin-left:90px;
	float:left;
	margin-right:30px;
}
#footer .tab_table td{
   padding-right:10px;
   text-align:left;
}
.tab_gengduo{
	color: blue;
	float:left;
	border:none;
	margin-top:160px;
}
#footer #tj .tab_table td{
   width:auto;
}
 
</style>
</head>
<body>
<input id="zycPath" type="hidden" value="<%=request.getContextPath()%>"/>
	<form id="form" name="form" method="post"
		action="security/security_loginSecond.do" style='overflow:hidden;margin-bottom:25px'>
		<input type="hidden" name="verbId" value="" /> <input type="hidden"
			name="publicClassId" value="" />
		<div id="header">
			<img src="main/include/images/login_03.jpg" height="85"
				style="float: left" />
			<ul class="nav">
				<li class="user">欢迎您！<%=sessionForm.getStaffName()%></li>
				<li><a
					href="<%=request.getContextPath()%>/security/security_login.do?verbId=reLogin">返回首页</a></li>
				<li><a
					href="<%=request.getContextPath()%>/index.jsp?agree=true" onclick="zuoXiOffWork();">退出系统</a></li>
			</ul>
			<div id="topNav" style='margin-left:0px;margin-right:0px;float:right'>
				<div id="ca-container" class="ca-container">
					<div class="ca-wrapper" >
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
										<div class='bg_wrapper'></div>
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
									<li class="li_pc_1 pc_<%=pc.getId()%>" onmouseover="addBg(this)" onmouseout="deleteBg(this)"
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
		<div
			style="width: 100%; background-color: rgb(209, 209, 209); height: 1px;"></div>
		<div
			style="width: 100%; background-color: #FFFFFF;overflow:hidden;padding-bottom:5px;line-height: 35px; font-size: 9pt; color: #888888">
			<div style="float: left;margin-right:10px;padding-left:5px;margin-bottom:-7px;">
				<span style="margin-left: 0px;">坐席号: </span><span id="agentNo">8001</span>
				<span style="margin-left: 3px;">当前状态: </span><span id="nowSatate">等待坐席准备</span>
				<span style="margin-left: 3px;">主叫号码: </span><span id="mainCallNo">4008517517</span>
			</div>
            <div style='overflow:hidden;white-space:nowrap;width:930px;'>
			<div id="hjzxkz" style="float: left;" >
				<!-- 呼叫中心电话控制 zycb-->
				<span id="zhunbeijiuxu" class="phone_button phone_current" onclick="yangshizhuanhuan(id);ready();">请准备</span>				
				<span id="cuoshangzhuanjie" class="phone_button phone_disable" onclick="yangshizhuanhuan(id);showCallPan();">来电</span> 
				<span id="waibo" class="phone_button phone_disable" onclick="yangshizhuanhuan(id);openWindow('bhtcc','bhzzc');">外拨</span>
				<div id="bhtcc" class="white_content">
					<div id="tccTitle" class="tccTitle">
						<img src="<%=request.getContextPath()%>/main/include/images/bohao_close.png" onClick="closeWindow('bhtcc','bhzzc');"/>
					</div>
					<div id="tccNo" class="tccNo"><span id="pn"></span></div>
					<table id="tccBhp"class="tccBhp" cellspacing='5'>
						<tr>
							<td onclick="bohao('1');" onmouseover='$(this).addClass("over")' onmouseout='$(this).removeClass("over")'>1</td>
							<td onclick="bohao('2');" onmouseover='$(this).addClass("over")' onmouseout='$(this).removeClass("over")'>2</td>
							<td onclick="bohao('3');" onmouseover='$(this).addClass("over")' onmouseout='$(this).removeClass("over")'>3</td>
						</tr>
						<tr>
							<td onclick="bohao('4');" onmouseover='$(this).addClass("over")' onmouseout='$(this).removeClass("over")'>4</td>
							<td onclick="bohao('5');" onmouseover='$(this).addClass("over")' onmouseout='$(this).removeClass("over")'>5</td>
							<td onclick="bohao('6');" onmouseover='$(this).addClass("over")' onmouseout='$(this).removeClass("over")'>6</td>
						</tr>
						<tr>
							<td onclick="bohao('7');" onmouseover='$(this).addClass("over")' onmouseout='$(this).removeClass("over")'>7</td>
							<td onclick="bohao('8');" onmouseover='$(this).addClass("over")' onmouseout='$(this).removeClass("over")'>8</td>
							<td onclick="bohao('9');" onmouseover='$(this).addClass("over")' onmouseout='$(this).removeClass("over")'>9</td>
						</tr>
						<tr>
							<td onclick="bohao('*');" onmouseover='$(this).addClass("over")' onmouseout='$(this).removeClass("over")'>*</td>
							<td onclick="bohao('0');" onmouseover='$(this).addClass("over")' onmouseout='$(this).removeClass("over")'>0</td>
							<td onclick="bohao('#');" onmouseover='$(this).addClass("over")' onmouseout='$(this).removeClass("over")'>#</td>
						</tr>
						<tr class='createSpace'>
							<td></td>
							<td></td>
							<td></td>
						</tr>
						<tr>
							<td class='bohao_call' onclick="hujiao();ocxwaibo();"></td>
							<td class='bohao_back' onclick="houtui();" onmousedown="continuousDel();" onmouseup="stopDel();"></td>
							<td class='bohao_hangOff' onclick="guaduan();"></td>
						</tr>
					</table>
				</div>
				<div id="bhzzc" class="black_overlay"></div>
				
				<span id="cuoshang" class="phone_button phone_disable" onclick="yangshizhuanhuan(id);acceptCall();">接听</span>
				<span id="baochi" class="phone_button phone_disable" onclick="yangshizhuanhuan(id);baochiqiehuan();">保持</span> 
				<span id="guaduan" class="phone_button phone_disable" onclick="yangshizhuanhuan(id);releaseCall();">挂断</span>
				
				<span id="zhuanjie" class="phone_button phone_disable" onclick="yangshizhuanhuan(id);youlaidian();">转接</span><!-- youlaidian();zhuanyuyue(); -->				 
				<span id="waixian" class="phone_button phone_disable" onclick="yangshizhuanhuan(id);">外线</span> 
				<span id="jianting" class="phone_button phone_disable" onclick="yangshizhuanhuan(id);openWindow('jttcc','jtzzc');">监听</span>
				<!--span id="huiyi" class="phone_button phone_disable" onclick="yangshizhuanhuan(id);youlaidian();">会议</span--> 
				<span id="huiyi" class="phone_button phone_disable" onclick="$('#win').window('open');">聊天</span> 
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

<div id="call_pan" style="width:350px;height:400px;z-index:1002;background-image:url('<%=request.getContextPath()%>/images/phone/call_background.png');display:none">
  <div style="height: 30px;text-align:right;padding-right: 12px;padding-top: 12px;">
  	<img src="<%=request.getContextPath()%>/images/phone/call_close.png" onclick="$('#call_pan').hide();">
  </div>
  <div style="height: 100px;margin-top: 10px;text-align:center;">
  	<img id="callingState1" src="<%=request.getContextPath()%>/images/phone/call_incoming.png">
  </div>
  <div id="calledno" style="text-align:center;font-size:40px;color:white;height:50px;line-height:50px;font-family:Arial;margin-top: 20px;">
  13368098310
  </div>
  <div id="timeCount" style="text-align:center;font-size:12px;;color:white;height:30px;line-height:30px;font-family:Arial;">
  <span id="hSpan"></span><span id="xsSpan"></span><span id="mSpan"></span><span id="fzSpan"></span><span id="sSpan"></span>
  </div>
  <div id="incomingCall" style="height: 45px;margin-top: 50px;text-align:center;">
  	<input type="button" onclick="acceptCall();" style="width:90px;height:45px;background-image:url('<%=request.getContextPath()%>/images/phone/call_call.png');border:0;border:none;"/>
  	<input type="button" onclick="rejectCall();" style="width:90px;height:45px;background-image:url('<%=request.getContextPath()%>/images/phone/call_hangup.png');border:0;border:none;margin-left:50px;"/>
  </div>
  <div id="callingState2" style="height: 45px;margin-top: 50px;text-align:center;display: none;">
  	<input type="button" onclick="zhuanyuyue();" style="width:90px;height:45px;background-image:url('<%=request.getContextPath()%>/images/phone/order.png');border:0;border:none;"/>
  	<input type="button" onclick="zhuanzixun();" style="width:90px;height:45px;background-image:url('<%=request.getContextPath()%>/images/phone/consult.png');border:0;border:none;margin-left:10px"/>
  	<input type="button" onclick="zhuantousu();" style="width:90px;height:45px;background-image:url('<%=request.getContextPath()%>/images/phone/complaint.png');border:0;border:none;margin-left:10px"/>
  </div>
</div>			
			<div
				style="float: right; height: 27px; line-height: 27px; padding: 0px; margin-top: 5px; margin-right: 5px">
				<input type="text" id="mainSearchText"
					style="width:133px;height:27px;line-height:27px;border:none;+border:0;padding-left:30px;background-image:url('<%=request.getContextPath()%>/include/images/search_box.jpg')"><input
					type="button" value="搜索" onclick="mainSearch()"
					style="margin:0;margin-left:-3px;width:44px;text-align:center;height:27px;line-height:27px;border:none;background-color:rgb(131,204,86);color:white;background-image:url('<%=request.getContextPath()%>/include/images/search_box.jpg');background-position:center right">
			</div>
			</div>
		</div>
		<div
			style="width:100%;background-image: url('<%=request.getContextPath()%>/include/images/v.jpg');height:11px;"></div>

		<div style="width: 100%; background-color: rgb(239, 239, 239);overflow:hidden;">
			<div class='toggleDiv'>
                <div class='toggleButton'></div>
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
					<div class="right_history_blank" style='border-top:1px solid #dddddd'>
						<div class="right_title">
							<span>工作日志</span>
						</div>
						<div class="line"></div>
						<div class="log_item odd">
							<a href="javascript:openOtherWindow('129128917','随访信息处理','followUp/followUpPlan.do?verbId=list');"><span>随访提醒（<label id="followUpValue"></label>）</span></a>
						</div>
						<div class="log_item even">
							<a href="javascript:openOtherWindow('129128918','投诉信息处理','complaints.do?verbId=list&type=2');"><span>投诉提醒（<label id="complaintsValue"></label>）</span></a>
						</div>
						<div class="log_item odd">
							<a href="javascript:openOtherWindow('129128919','咨询信息处理','crm/crmConsultation.do?verbId=queryConsultation&executedFlag=1');"><span>咨询提醒（<label id="consultationValue"></label>）</span></a>
						</div>
						<div class="log_item even">
							<a href="javascript:openOtherWindow('129128920','满意度信息处理','satisfied/satisfiedPlan.do?verbId=list');"><span>满意度提醒（<label id="satisfedValue"></label>）</span></a>
						</div>
						<div class="log_item odd">
							<span>院内通知（1）</span>
						</div>
					</div>


				</div>


			</div>
			<div class='displayDiv'>
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
		<div
			style="margin: 0 auto; margin-left: 10px; margin-right: 10px; border: 1px rgb(210, 210, 210) solid; background-color: #FFFFFF">
			<div
				style="height: 30px; width: 100%; background-color: rgb(249, 249, 249);border-bottom:1px solid #d8d8d8">
				<div class="margin_left">
					<%--<input type="hidden" id="authorityId">
					<input type="hidden" id="pid">--%>
					<input type="hidden" id="securityUserBaseinfoId">
					<input type="hidden" id="tabId" value="jbxxTab"/><!-- 用来隐藏当前tab标签的id -->
					<input type="hidden" id="tabDivId" value="jbxx"/><!-- 用来隐藏当前tab对应div的id -->
					<!-- 注意查询标志再电话结束后要释放 -->
					<%--<input type="hidden" id="oldPid" value="old"/><!-- 用来对比新旧pid -->
					<input type="hidden" id="checkPid" value="no"/><!-- 检查Pid是否改变标志 -->--%>
					
					<input type="hidden" id="oldSecurityUserBaseinfoId" value="old"/><!-- 用来对比新旧securityUserBaseinfoId -->
					<input type="hidden" id="checkSecurityUserBaseinfoId" value="no"/><!-- 检查SecurityUserBaseinfoId是否改变标志 -->
					<input type="hidden" id="checkzjzd" value="yes"/><!-- 有SecurityUserBaseinfoId最近诊断查询一次后就不查询标志 -->
					<input type="hidden" id="checkzjss" value="yes"/><!-- 有SecurityUserBaseinfoId最近手术查询一次后就不查询标志 -->
					<input type="hidden" id="checkzjyy" value="yes"/><!-- 有SecurityUserBaseinfoId最近用药查询一次后就不查询标志 -->
					<input type="hidden" id="checkjc" value="yes"/><!-- 有SecurityUserBaseinfoId检查查询一次后就不查询标志 -->
					<input type="hidden" id="checkjy" value="yes"/><!-- 有SecurityUserBaseinfoId检验查询一次后就不查询标志 -->
					<input type="hidden" id="checkfy" value="yes"/><!-- 有SecurityUserBaseinfoId费用查询一次后就不查询标志 -->
					<input type="hidden" id="checktj" value="yes"/><!-- 有SecurityUserBaseinfoId体检查询一次后就不查询标志 -->
				</div>
				<div class="tool_tab tool_tab_active" id="jbxxTab" onclick="tabChange(id);">基本信息</div>
				<div class="tool_tab" id="zjzdTab" onclick="tabChange(id);zjzdGetData();">最近诊断</div>
				<div class="tool_tab" id="zjssTab" onclick="tabChange(id);zjssGetData();">最近手术</div>
				<div class="tool_tab" id="zjyyTab" onclick="tabChange(id);zjyyGetData();">最近用药</div>
				<div class="tool_tab" id="jcTab" onclick="tabChange(id);jcGetData();">检查</div>
				<div class="tool_tab" id="jyTab" onclick="tabChange(id);jyGetData();">检验</div>
				<div class="tool_tab" id="fyTab" onclick="tabChange(id);fyGetData();">费用</div>
				<div class="tool_tab" id="tjTab" onclick="tabChange(id);tjGetData();">体检</div>
				<div class='footer_hidden' onclick="toggle_footer('fbody')" id="f_button"></div>
				<div style="clear: both"></div>
			</div>
			<div style="height: 180px; width: 100%;display: none;" id="fbody">
				<!-- 
				<div style="height: 1px; background-color: rgb(210, 210, 210); width: 100%"></div>
				 -->
				<div id="jbxx">
					<table class="tab_table">
						<tr>
							<td class="jbxx_tr_font_right">患者姓名：</td>
							<td>
								<div class="jbxx_div"><span id="huanzhexingming"></span>&nbsp;</div>
							</td>
							<td class="jbxx_tr_font_right">性别：</td>
							<td>
								<div class="jbxx_div"><span id="xingbie"></span>&nbsp;</div>
							</td>
							<td class="jbxx_tr_font_right">年龄：</td>
							<td>
								<div class="jbxx_div"><span id="nianling"></span>&nbsp;</div>
							</td>
							<td class="jbxx_tr_font_right">国籍：</td>
							<td>
								<div class="jbxx_div"><span id="guoji"></span>&nbsp;</div>
							</td>
						</tr>
						<tr>
							<td class="jbxx_tr_font_right">身份证号：</td>
							<td colspan="3">
								<div class="jbxx_div"><span id="shenfenzhenghao"></span>&nbsp;</div>
							</td>
							<td class="jbxx_tr_font_right">婚姻状态：</td>
							<td>
								<div class="jbxx_div"><span id="hunyinzhuangtai"></span>&nbsp;</div>
							</td>
							<td class="jbxx_tr_font_right">职业：</td>
							<td>
								<div class="jbxx_div"><span id="zhiye"></span>&nbsp;</div>
							</td>
						</tr>
						<tr>
							<td class="jbxx_tr_font_right">居民健康卡：</td>
							<td colspan="3">
								<div class="jbxx_div"><span id="juminjiankangka"></span>&nbsp;</div>
							</td>
							<td class="jbxx_tr_font_right">教育程度：</td>
							<td>
								<div class="jbxx_div"><span id="jaoyuchengdu"></span>&nbsp;</div>
							</td>
							<td class="jbxx_tr_font_right">职位：</td>
							<td>
								<div class="jbxx_div"><span id="zhiwei"></span>&nbsp;</div>
							</td>
						</tr>
						<tr>
							<td class="jbxx_tr_font_right">家庭住址：</td>
							<td colspan="3">
								<div class="jbxx_div"><span id="jiatingzhuzhi"></span>&nbsp;</div>
							</td>
							<td class="jbxx_tr_font_right">邮政编码：</td>
							<td>
								<div class="jbxx_div"><span id="youzhengbianma"></span>&nbsp;</div>
							</td>
							<td class="jbxx_tr_font_right">电话：</td>
							<td>
								<div class="jbxx_div"><span id="dianhua"></span>&nbsp;</div>
							</td>
						</tr>
					</table>
				</div>
				<div id="zjzd" class="tab_div_hide">
					<table class="tab_table table_xiahuaxian">
						<tr>
							<td>诊断时间</td>
							<td>诊断名称</td>
							<td>就诊科室</td>
							<td>类别</td>
						</tr>
						<tr id="zjzdtr1" class="table_tr_hide">
							<td>
								<input id="authorityId1" type="hidden" value=""/>
								<input id="pid1" type="hidden" value=""/>
								<input id="clinicId1" type="hidden" value=""/>
								<span id="zdsj1" class="blue_font_tab" onmouseover="addUnderLine(this);" onmouseout="removeUnderLine(this);" onclick="zjzdDetail('1');"></span>
							</td>
							<td id="zdmc1"></td>
							<td id="jzks1"></td>
							<td id="lb1"></td>
						</tr>
						<tr id="zjzdtr2" class="table_tr_hide">
							<td>
								<input id="authorityId2" type="hidden" value=""/>
								<input id="pid2" type="hidden" value=""/>
								<input id="clinicId2" type="hidden" value=""/>
								<span id="zdsj2" class="blue_font_tab" onmouseover="addUnderLine(this);" onmouseout="removeUnderLine(this);" onclick="zjzdDetail('2');"></span>
							</td>
							<td id="zdmc2"></td>
							<td id="jzks2"></td>
							<td id="lb2"></td>
						</tr>
						<tr id="zjzdtr3" class="table_tr_hide">
							<td>
								<input id="authorityId3" type="hidden" value=""/>
								<input id="pid3" type="hidden" value=""/>
								<input id="clinicId3" type="hidden" value=""/>
								<span id="zdsj3" class="blue_font_tab" onmouseover="addUnderLine(this);" onmouseout="removeUnderLine(this);" onclick="zjzdDetail('3');"></span>
							</td>
							<td id="zdmc3"></td>
							<td id="jzks3"></td>
							<td id="lb3"></td>
						</tr>
					</table>
					<span id="zjzdgd" class="tab_gengduo" onmouseover="addUnderLine(this);" onmouseout="removeUnderLine(this);" onclick="zjzdLieBiao();">更多></span>
				</div>
				<div id="zjss" class="tab_div_hide">
					<table class="tab_table table_xiahuaxian">
						<tr>
							<td>手术时间</td>
							<td>手术名称</td>
							<td>就诊科室</td>
						</tr>
						<tr id="zjsstr1" class="table_tr_hide">
							<td id="sssj1"></td>
							<td id="ssmc1"></td>
							<td id="ssks1"></td>
						</tr>	
						<tr id="zjsstr2" class="table_tr_hide">
							<td id="sssj2"></td>
							<td id="ssmc2"></td>
							<td id="ssks2"></td>
						</tr>	
						<tr id="zjsstr3" class="table_tr_hide">
							<td id="sssj3"></td>
							<td id="ssmc3"></td>
							<td id="ssks3"></td>
						</tr>																								
					</table>
					<span id="zjssgd" class="tab_gengduo" onmouseover="addUnderLine(this);" onmouseout="removeUnderLine(this);" onclick="zjssLieBiao();">更多></span>
				</div>
				<div id="zjyy" class="tab_div_hide">
					<table class="tab_table table_xiahuaxian">
						<tr id="zjyytr1a">
							<td id="kysj1t">开药时间</td>
							<td id="ypmc1t">药品名称(西药)</td>
							<td id="gg1t">规格</td>
							<td id="yf1t">用法</td>
							<td id="yl1t">用量</td>
							<td id="sl1t">数量</td>
						</tr>
						<tr id="zjyytr1b" class="table_tr_hide">
							<td>
								<input id="yptype1" type="hidden" value=""/>
								<input id="zjyyauthorityId1" type="hidden" value=""/>
								<input id="zjyypid1" type="hidden" value=""/>
								<input id="zjyyprescriptionId1" type="hidden" value=""/>
								<input id="zjyyclinicId1" type="hidden" value=""/>
								<span id="kysj1" class="blue_font_tab" onmouseover="addUnderLine(this);" onmouseout="removeUnderLine(this);" onclick="zjyyDetail('1');"></span>
							</td>
							<td id="ypmc1"></td>
							<td id="gg1"></td>
							<td id="yf1"></td>
							<td id="yl1"></td>
							<td id="sl1"></td>
						</tr>
					</table>
					<span id="zjyygd" class="tab_gengduo" onmouseover="addUnderLine(this);" onmouseout="removeUnderLine(this);" onclick="zjyyLieBiao();">更多></span>
				</div>
				<div id="jc" class="tab_div_hide">
					<table class="tab_table table_xiahuaxian">
						<tr>
							<td>检查时间</td>
							<td>科室</td>
							<td>项目名称</td>
						</tr>
						<tr id="jctr1" class="table_tr_hide">
							<td>
								<input id="jcexaminationId1" type="hidden" value=""/>
								<input id="jcauthorityId1" type="hidden" value=""/>
								<input id="jcpid1" type="hidden" value=""/>
								<input id="jcclinicId1" type="hidden" value=""/>
								<input id="jcclinicType1" type="hidden" value=""/>
								<span id="jcsj1" class="blue_font_tab" onmouseover="addUnderLine(this);" onmouseout="removeUnderLine(this);" onclick="jcDetail('1');"></span>
							</td>
							<td id="jcks1"></td>
							<td id="jcxm1"></td>
						</tr>
						<tr id="jctr2" class="table_tr_hide">
							<td>
								<input id="jcexaminationId2" type="hidden" value=""/>
								<input id="jcauthorityId2" type="hidden" value=""/>
								<input id="jcpid2" type="hidden" value=""/>
								<input id="jcclinicId2" type="hidden" value=""/>
								<input id="jcclinicType2" type="hidden" value=""/>
								<span id="jcsj2" class="blue_font_tab" onmouseover="addUnderLine(this);" onmouseout="removeUnderLine(this);" onclick="jcDetail('2');"></span>
							</td>
							<td id="jcks2"></td>
							<td id="jcxm2"></td>
						</tr>
						<tr id="jctr3" class="table_tr_hide">
							<td>
								<input id="jcexaminationId3" type="hidden" value=""/>
								<input id="jcauthorityId3" type="hidden" value=""/>
								<input id="jcpid3" type="hidden" value=""/>
								<input id="jcclinicId3" type="hidden" value=""/>
								<input id="jcclinicType3" type="hidden" value=""/>
								<span id="jcsj3" class="blue_font_tab" onmouseover="addUnderLine(this);" onmouseout="removeUnderLine(this);" onclick="jcDetail('3');"></span>
							</td>
							<td id="jcks3"></td>
							<td id="jcxm3"></td>
						</tr>																	
					</table>
					<span id="jcgd" class="tab_gengduo" onmouseover="addUnderLine(this);" onmouseout="removeUnderLine(this);" onclick="jcLieBiao();">更多></span>
				</div>
				<div id="jy" class="tab_div_hide">
					<table class="tab_table table_xiahuaxian">
						<tr>
							<td>检验时间</td>
							<td>科室</td>
							<td>项目名称</td>
						</tr>
						<tr id="jytr1" class="table_tr_hide">
							<td>
								<input id="jylabId1" type="hidden" value=""/>
								<input id="jyauthorityId1" type="hidden" value=""/>
								<input id="jypid1" type="hidden" value=""/>
								<input id="jyclinicId1" type="hidden" value=""/>
								<input id="jyclinicType1" type="hidden" value=""/>
								<span id="jysj1" class="blue_font_tab" onmouseover="addUnderLine(this);" onmouseout="removeUnderLine(this);" onclick="jyDetail('1');"></span>
							</td>
							<td id="jyks1"></td>
							<td id="jyxm1"></td>
						</tr>
						<tr id="jytr2" class="table_tr_hide">
							<td>
								<input id="jylabId2" type="hidden" value=""/>
								<input id="jyauthorityId2" type="hidden" value=""/>
								<input id="jypid2" type="hidden" value=""/>
								<input id="jyclinicId2" type="hidden" value=""/>
								<input id="jyclinicType2" type="hidden" value=""/>
								<span id="jysj2" class="blue_font_tab" onmouseover="addUnderLine(this);" onmouseout="removeUnderLine(this);" onclick="jyDetail('2');"></span>
							</td>
							<td id="jyks2"></td>
							<td id="jyxm2"></td>
						</tr>
						<tr id="jytr3" class="table_tr_hide">
							<td>
								<input id="jylabId3" type="hidden" value=""/>
								<input id="jyauthorityId3" type="hidden" value=""/>
								<input id="jypid3" type="hidden" value=""/>
								<input id="jyclinicId3" type="hidden" value=""/>
								<input id="jyclinicType3" type="hidden" value=""/>
								<span id="jysj3" class="blue_font_tab" onmouseover="addUnderLine(this);" onmouseout="removeUnderLine(this);" onclick="jyDetail('3');"></span>
							</td>
							<td id="jyks3"></td>
							<td id="jyxm3"></td>
						</tr>					
					</table>
					<span id="jygd" class="tab_gengduo" onmouseover="addUnderLine(this);" onmouseout="removeUnderLine(this);" onclick="jyLieBiao();">更多></span>
				</div>
				<div id="fy" class="tab_div_hide">
					<table class="tab_table table_xiahuaxian">
						<tr>
							<td>就诊时间</td>
							<td>就诊科室</td>
							<td>发票号</td>
							<td>发票总金额</td>
						</tr>
						<tr id="fytr1" class="table_tr_hide">
							<td id="fysj1"></td>
							<td id="fyks1"></td>
							<td id="fyfp1"></td>
							<td>
								<input id="fyreceiptNumber1" type="hidden" value=""/>
								<input id="fyauthorityId1" type="hidden" value=""/>
								<input id="fypid1" type="hidden" value=""/>
								<input id="fyclinicId1" type="hidden" value=""/>
								<input id="fyclinicType1" type="hidden" value=""/>
								<span id="fyje1" class="blue_font_tab" onmouseover="addUnderLine(this);" onmouseout="removeUnderLine(this);" onclick="fyDetail('1');"></span>
							</td>
						</tr>
						<tr id="fytr2" class="table_tr_hide">
							<td id="fysj2"></td>
							<td id="fyks2"></td>
							<td id="fyfp2"></td>
							<td>
								<input id="fyreceiptNumber2" type="hidden" value=""/>
								<input id="fyauthorityId2" type="hidden" value=""/>
								<input id="fypid2" type="hidden" value=""/>
								<input id="fyclinicId2" type="hidden" value=""/>
								<input id="fyclinicType2" type="hidden" value=""/>
								<span id="fyje2" class="blue_font_tab" onmouseover="addUnderLine(this);" onmouseout="removeUnderLine(this);" onclick="fyDetail('2');"></span>
							</td>
						</tr>
						<tr id="fytr3" class="table_tr_hide">
							<td id="fysj3"></td>
							<td id="fyks3"></td>
							<td id="fyfp3"></td>
							<td>
								<input id="fyreceiptNumber3" type="hidden" value=""/>
								<input id="fyauthorityId3" type="hidden" value=""/>
								<input id="fypid3" type="hidden" value=""/>
								<input id="fyclinicId3" type="hidden" value=""/>
								<input id="fyclinicType3" type="hidden" value=""/>
								<span id="fyje3" class="blue_font_tab" onmouseover="addUnderLine(this);" onmouseout="removeUnderLine(this);" onclick="fyDetail('3');"></span>
							</td>
						</tr>																								
					</table>
					<span id="fygd" class="tab_gengduo" onmouseover="addUnderLine(this);" onmouseout="removeUnderLine(this);" onclick="fyLieBiao();">更多></span>
				</div>
				<div id="tj" class="tab_div_hide">
					<table class="tab_table table_xiahuaxian" >
						<tr>
							<td class="tjsjWidth">体检时间</td>
							<td>总体评价类容</td>
						</tr>
						<tr id="tjtr1" class="table_tr_hide">
							<td>
								<input id="tjauthorityId1" type="hidden" value=""/>
								<input id="tjpid1" type="hidden" value=""/>
								<input id="tjpeId1" type="hidden" value=""/>
								<input id="tjpeVisitId1" type="hidden" value=""/>
								<span id="tjsj1" class="blue_font_tab" onmouseover="addUnderLine(this);" onmouseout="removeUnderLine(this);" onclick="tjDetail('1');"></span>
							</td>
							<td id="tjpj1"></td>
						</tr>
						<tr id="tjtr2" class="table_tr_hide">
							<td>
								<input id="tjauthorityId2" type="hidden" value=""/>
								<input id="tjpid2" type="hidden" value=""/>
								<input id="tjpeId2" type="hidden" value=""/>
								<input id="tjpeVisitId2" type="hidden" value=""/>
								<span id="tjsj2" class="blue_font_tab" onmouseover="addUnderLine(this);" onmouseout="removeUnderLine(this);" onclick="tjDetail('2');"></span>
							</td>
							<td id="tjpj2"></td>
						</tr>
						<tr id="tjtr3" class="table_tr_hide">
							<td>
								<input id="tjauthorityId3" type="hidden" value=""/>
								<input id="tjpid3" type="hidden" value=""/>
								<input id="tjpeId3" type="hidden" value=""/>
								<input id="tjpeVisitId3" type="hidden" value=""/>
								<span id="tjsj3" class="blue_font_tab" onmouseover="addUnderLine(this);" onmouseout="removeUnderLine(this);" onclick="tjDetail('3');"></span>
							</td>
							<td id="tjpj3"></td>
						</tr>																								
					</table>
					<span id="tjgd" class="tab_gengduo" onmouseover="addUnderLine(this);" onmouseout="removeUnderLine(this);" onclick="tjLieBiao();">更多></span>
				</div>					
			</div>
		</div>
	</div>
	
<div id="win" class="easyui-window" title="聊天对话窗口" style="width:800px;height:500px;" data-options="iconCls:'icon-save',modal:false,onOpen:function(){openTalk();},onClose:function(){closeTalk();},closed:true">
  <input type="hidden" value="${RESTIP}" id="RESTIP">
  <input type="hidden" value="${RESTPOST}" id="RESTPOST">
  <input type="hidden" value="${accountSid}" id="accountSid">
  <input type="hidden" value="${authToken}" id="authToken">
  <input type="hidden" value="${subaccount}" id="subaccount">
  <input type="hidden" value="${subaccounttoken}" id="subaccounttoken">
  <input type="hidden" value="${voip}" id="voip">
  <input type="hidden" value="${voiptoken}" id="voiptoken">
  <input type="hidden" value="0" id="talk_status">


			<div
				style="width: 90%; height: 500px; margin: 0 auto; margin-top: 20px;">
				<div id="middleConversation"
					style="width: 68%; height: 500px; border: red 0px solid; float: left; padding: 0px; margin-right: 0px"
					class="personal_words">
					<div class="title" style='width: 100%'>
						<input type="hidden" id="currentVoipIp" value="87365300000080">
						<input type="hidden" id="hspName" value="${hspName }">
						<input type="hidden" id="userName" value="${userName}">
						<input type="hidden" id="fromVoip" >
						<span id="currentVoip">当前对话：87365300000080</span>
					</div>
					<div class="infobox" style="width: 100%; padding: 0px;">
						<div class="info_get" id="send_get" style="width: 100%">
							<!--  聊天信息  -->
						</div>
						<div style="display: none;">
							<div id="dlg" class="easyui-dialog" title="录音" closed="true"
								data-options="iconCls:'icon-save',modal:true,closable:false,buttons:'#yuyinButton'"
								style="width:266px;height:240px;padding:0px;overflow:hidden;background-color:RGB(50,192,202);background-image:url('<%=request.getContextPath()%>/include/images/huatong.png');background-repeat:no-repeat;background-position:50% 50%;">
								<div class='graph' id='graph' align='center'
									style='width: 100%; top: 140px; display: none;'>
									<div id='bar' style='width: 0px; text-align: left;'>
									</div>
								</div>
							</div>
							<div id="yuyinButton" style="text-align: center;">
								<a class="easyui-linkbutton" onclick="sendVoice1()"
									onmouseover="this.style.color='white'"
									onmouseout="this.style.color='black'"
									style="width: 80px; height: 25px; line-height: 36px; text-align: center; background: RGB(50, 192, 202); color: RGB(0, 0, 0); font-size: 16px; margin-top: 2px; text-decoration: none; cursor: pointer">发送</a>
								<a class="easyui-linkbutton" onclick="cancelVoice()"
									onmouseover="this.style.color='white'"
									onmouseout="this.style.color='black'"
									style="width: 80px; height: 25px; line-height: 36px; text-align: center; background: RGB(255, 0, 0); color: RGB(0, 0, 0); font-size: 16px; margin-top: 2px; text-decoration: none; cursor: pointer">取消</a>
							</div>
						</div>
						<div class="info_choose" style='width: 100%;'>
							<a style="cursor: pointer" onclick="loadBiaoQing();"
								class="biaoqing" title="表情"></a>
							<!-- <a href="javascript:void(0);"  class="screenshot" title="截图"></a> -->
							<a style="cursor: pointer" onclick="loadfujian();" class="fujian"
								title="附件"></a>
							<input class="file" type="file" style="display: none;" id="file1"
								name="file1" size=".01" onchange="uploadfile();" />
							<a style="cursor: pointer" onclick="dealVoice()" class="voice"
								title="语音"></a>
							<a style="cursor: pointer" onclick="chatMessage();"
								class="chatmessages" title="聊天记录"></a>
							<!-- 表情框 -->
							<div class="wrap_bq" style="display: none"></div>
						</div>
						<div class="info_send" style="margin: 0px">
							<div class="area" onclick="getPos();" onkeydown="getHuiche();"
								contenteditable="true" id="msg"
								style="width: auto; padding: 0px; margin: 0px; color: black; text-align: left; padding-left: 3px">
							</div>
							<div style="display: none" id="sendValue"></div>
							<div class="btnbox"
								style="padding: 0px; margin: 0px; width: 100%">
								<a id="send" class="send_btn" style="cursor: pointer;">发 送</a>
							</div>
						</div>
					</div>
				</div>
				<div id="zjzdmzxx" class="easyui-window" title="诊断详细信息"
					style="width: 800px; height: 500px;"
					data-options="iconCls:'icon-save',modal:true,closed:true">
					<!--门诊诊断详细信息 -->
					<div id="outp_base_info" class="div_width_100">
						<br><h5 id="hsopital_zjzdmzxx" align="center"></h5>
						<br>
						<h5 align="center">门诊诊断详细信息</h5>
						
								<table class="div_width_100 table_td_interval3" style="margin-left: 0px;">
									<tr>
										<td width="25%" class="table_titile">
											就诊号:
										</td>
										<td width="25%" id="mzjzh"></td>
										<td width="25%" class="table_titile">
											就诊时间:
										</td>
										<td width="25%" id="mzjzsj"></td>
									</tr>
									<tr>
										<td class="table_titile">
											就诊科室:
										</td>
										<td id="mzjzks" colspan="3"></td>
									</tr>
									<tr>
										<td class="table_titile">
											诊断描述:
										</td>
										<td id="mzzdms" colspan="3"></td>
									</tr>
								</table>
					</div>	
				</div>
				<div id="zjzdzyxx" class="easyui-window" title="诊断详细信息"
					style="width: 800px; height: 500px"
					data-options="iconCls:'icon-save',modal:true,closed:true">
					<!--住院诊断详细信息  -->
					<div id="outp_base_info" class="div_width_100">
						<br><h5 id="hsopital_zjzdzyxx" align="center">${configBaseinfo.itemName}</h5>
						<br>
						<h5 align="center">住院诊断详细信息</h5>
								<table class="div_width_100 table_td_interval3" style="margin-left: 0px;">
									<tr>
										<td width="25%" class="table_titile">
											住院号:
										</td>
										<td width="25%" id="zyjzh"></td>
										<td width="25%" class="table_titile">
											入院时间:
										</td>
										<td width="25%" id="zyjzsj"></td>
									</tr>
									<tr>
										<td class="table_titile">
											就诊科室:
										</td>
										<td id="zyjzks"></td>
										<td class="table_titile">
											出院时间:
										</td>
										<td id="zycysj"></td>
									</tr>
									<tr>
										<td class="table_titile">
											诊断描述:
										</td>
										<td id="zyzdms" colspan="3"></td>
									</tr>
								</table>
					</div>
				</div>
				<div id="zjyyxx" class="easyui-window" title="处方详细信息"
					style="width: 800px; height: 500px"
					data-options="iconCls:'icon-save',modal:true,closed:true">
					<!--处方详细信息  -->
					<div class="div_width_100">
						<br><h5 id="hospital_zjyyxx" style="text-align: center;"></h5>
						<br><h5 style="text-align: center;">处方详细信息</h5><br>
						<div id="outp_presc_info" style="width: 98%;height: 380px;margin-left: 1%;margin-top: 5px;">
							<!-- 药方 -->
							<c:choose>
								<div class="div_width_100">
									<h5 id="cflb" class="text_center"></h5>
									<hr />
									<div id="p_base_info">
										<table class="div_width_100  table_td_width1666 table_td_interval3">
											<tr>
												<td class="td_right">
													医疗机构：
												</td>
												<td id="cfyljg"></td>
												<td class="td_right">
													开药时间：
												</td>
												<td id="cfsj"></td>
												<td class="td_right">
													处方编号：
												</td>
												<td id="cfbh"></td>
											</tr>
											<tr>
												<td class="td_right">
													姓名：
												</td>
												<td id="cfxm"></td>
												<td class="td_right">
													性别：
												</td>
												<td id="cfxb"></td>
												<td class="td_right">
													年龄：
												</td>
												<td id="cfnl"></td>

											</tr>
											<tr>
												<td class="td_right">
													病历号：
												</td>
												<td id="cfblh"></td>
												<td class="td_right">
													科别：
												</td>
												<td id="cfkb"></td>
												<td class="td_right">
													费别：
												</td>
												<td id="cffb"></td>
											</tr>
										</table>
									</div>
									<div id="medicine_content" style="margin-top: 3px;">
										<table class="div_width_100 medicine_content_table table_td_interval3">
											<tr class="height_200px">
												<td style='border-bottom: none;padding-left: 4px;padding-right: 4px;'>
													<div>
														<strong class="text_center">疾病诊断:</strong>
													</div>
													<div class="horizontal_line_7"></div>
													<div>
														<span id="cfjbzd" class="lab_item_name" style="display:block;text-indent: 24px;"
															onclick="toItemDiseaseDetail(this)"></span>
													</div>
												</td>
												<td class="td_no_bottom_line" style="height:200px;padding-left: 4px;padding-right: 4px;">
													<div>
														<strong>R</strong>
													</div>
													<div class="horizontal_line_5"></div>
													<table id="cfxx" class="div_width_100 medicine_list_table">
													</table>
												</td>
											</tr>
											<tr>
												<td style="padding-left: 4px;padding-right: 4px;">
													<div id="cfsm"></div>
													<div class="horizontal_line_3"></div>
												</td>
												<td class="td_no_top_line sign_td" align="center">
													<div id="cfxxbq"></div>
													<div class="horizontal_line_3"></div>
												</td>
											</tr>
										</table>
									</div>
								</div>
							</c:choose>
						</div>
					</div>
				</div>
				<div id="zjjcxx" class="easyui-window" title="检查详细信息"
					style="width: 800px; height: 500px"
					data-options="iconCls:'icon-save',modal:true,closed:true">
					<!--检查详细信息  -->
					<div class="div_width_100">
						<div id="outp_Exam_info" style="width: 98%;height: 380px;mamargin-top: 0px;margin-left: 1%;margin-top: 5px;">
							<!-- 检查报告表 -->
							<div class="exam_report">
								<table class="exam_report_table div_width_100 table_td_interval3">
									<tr>
										<td>
											<div style="width: 100%">
												<br>
												<h5 id="jcjcyy" class="text_center"></h5>
												<br>
												<h5 id="jcjclb" class="text_center"></h5>
												<br>
												<table class="inner_table table_td25" style="width: 100%;">
													<tr>
														<td class="td_right td_left_indent">
															检查号：
														</td>
														<td id="jcjch" class="td_left_indent"></td>
														<td class="td_right td_left_indent">
															检查时间：
														</td>
														<td id="jcjcsj" class="td_left_indent"></td>
													</tr>
												</table>
												<div class="horizontal_line_3"></div>
											</div>
										</td>
									</tr>
									<tr>
										<td>
											<div style="width: 100%;">
												<table class="inner_table table_td1666 table_td_interval3" style="width: 100%;">
													<tr>
														<td class="td_right">
															姓名：
														</td>
														<td id="jcxm"></td>
														<td class="td_right">
															性别：
														</td>
														<td id="jcxb"></td>
														<td class="td_right">
															年龄：
														</td>
														<td id="jcnl"></td>
													</tr>
													<tr>
														<td class="td_right">
															门诊号：
														</td>
														<td id="jcmzh"></td>
														<td class="td_right">
															科别：
														</td>
														<td id="jckb"></td>
														<td class="td_right">
															申请医生：
														</td>
														<td id="jcsqys"></td>
													</tr>
													<tr>
														<td class="td_right">
															检查部位：
														</td>
														<td>
															<span id="jcjcbw" class="lab_item_name"
																onclick="toItemDetail(this);"></span>
														</td>
													</tr>
												</table>
												<div class="horizontal_line_3"></div>
											</div>
										</td>
									</tr>
									<tr>
										<td>
								 			<div style="width: 100%;margin-top: 3px;">
								 				<span style="display:block;text-indent:4px;">检查参数：</span>
								 			</div>
								 			<div style="width: 100%;height:60px;margin-top: 5px;">
								 				<span id="jcjccs" style="display:block;text-indent:28px;"></span>
								 			</div>
								 			<div style="width: 100%;margin-top: 3px;">
								 				<span style="display:block;text-indent:4px;">检查所见：</span>
								 			</div>
								 			<div style="width: 100%;height:200px;margin-top: 5px;">
								 				<span id="jcjianchasuojian" style="display:block;text-indent:28px;">${report.examinationResult }</span>
								 			</div>
								 			<br />
										</td>
									</tr>
								</table>
							</div>
						</div>
					</div>

				</div>
				<div id="zjjyxx" class="easyui-window" title="检验详细信息"
					style="width: 800px; height: 500px"
					data-options="iconCls:'icon-save',modal:true,closed:true">
					<!--检验详细信息  -->
					<div style="width: 100%">
						<div id="outp_lab_info"
							style="width: 98%;height: 380px;mamargin-top: 0px;margin-left: 1%;margin-top: 5px;">
							<!-- 检验报告表 -->
							<div class="div_width_100 lab_report_div">
								<br>
								<h5 id="jybt" class="text_center"></h5>
								<br>
								<h5 class="text_center">检验报告单</h5>
								<br>
								<div class="lab_base_info_div div_width_100">
									<table class="div_width_100 table_td125 table_td_interval3">
										<tr>
											<td colspan="6"></td>
											<td class="td_right">
												样本号：
											</td>
											<td id="jyybh"></td>
										</tr>
										<tr>
											<td class="td_right">
												姓名：
											</td>
											<td id="jyxm"></td>
											<td class="td_right">
												科室：
											</td>
											<td id="jyks"></td>
											<td class="td_right">
												标本：
											</td>
											<td id="jybb"></td>
											<td class="td_right">
												送检时间：
											</td>
											<td id="jysjsj"></td>
										</tr>
										<tr>
											<td class="td_right">
												门诊号：
											</td>
											<td id="jymzh"></td>
											<td class="td_right">
												床号：
											</td>
											<td id="jych"></td>
											<td class="td_right">
												病人ID：
											</td>
											<td id="jybrid"></td>
											<td class="td_right">
												采样时间：
											</td>
											<td id="jycysj"></td>
										</tr>
										<tr>
											<td class="td_right">
												性别：
											</td>
											<td id="jyxb"></td>
											<td class="td_right">
												年龄：
											</td>
											<td id="jynl"></td>
											<td class="td_right">
												诊断：
											</td>
											<td id="jyzd"></td>
											<td class="td_right">
												备注：
											</td>
											<td id="jybz"></td>
										</tr>
									</table>
								</div>
								<div class="horizontal_line_3"></div>
								<div class="lab_detail_div" style="height:277px;">
									<table id="labDetails1" style="text-align: center;width: 44%;margin-left: 4%;"
										class="div_left table_td_interval3">
									</table>
									<table id="labDetails2" style="text-align: center;width: 44%;margin-left: 4%;"
										class="div_left table_td_interval3">
									</table>
								</div>
								<div class="horizontal_line_3"></div>
								<div class="div_width_100">
									<table class="div_width_100 table_td125 table_td_interval3">
										<tr>
											<td class="td_right">
												申请医生：
											</td>
											<td id="jysqys"></td>
											<td class="td_right">
												报告时间：
											</td>
											<td id="jybgsj"></td>
											<td class="td_right">
												检验者：
											</td>
											<td id="jyjyz"></td>
											<td class="td_right">
												审核者：
											</td>
											<td id="jyshz"></td>
										</tr>
										<tr>
											<td colspan="8" style="text-align: center;">
												注：本报告仅对本次检测的标本负责！如有异议7日内咨询有效！
											</td>
										</tr>
									</table>
								</div>
							</div>
						</div>
					</div>

				</div>
				<%--<div id="zjfyxx" class="easyui-window" title="门诊费用详细信息"
					style="width: 800px; height: 500px"
					data-options="iconCls:'icon-save',modal:true,closed:true">
				</div>
				--%>
				<div id="zjmzfyxx" class="easyui-window" title="门诊费用详细信息"
					style="width: 800px; height: 500px"
					data-options="iconCls:'icon-save',modal:true,closed:true">
					<!-- 门诊费用详细 -->
					<div>
						<!-- 门诊费用详细 -->
						<div class="div_width_100">
							<!-- 大类下的收费主记录 -->
							<div id="else_fee_mz" class="div_width_100">

							</div>
						</div>
					</div>
				</div>
				<div id="zjzyfyxx" class="easyui-window" title="住院费用详细信息"
					style="width: 800px; height: 500px"
					data-options="iconCls:'icon-save',modal:true,closed:true">
					<!-- 住院费用 -->
					<div>
						<!-- 住院费用详细信息 -->
						<div class="div_width_100">
							<!-- 除挂号费的其他形式 -->
							<div id="else_fee_zy" class="div_width_100">
							</div>
						</div>
					</div>
				</div>
				<div id="zjtjxx" class="easyui-window" title="体检详细信息"
					style="width: 800px; height: 500px"
					data-options="iconCls:'icon-save',modal:true,closed:true">
				</div>
				<div id="jyxxxxxx" class="easyui-window" title="检查检验项目信息"
					style="width: 800px; height: 500px"
					data-options="iconCls:'icon-save',modal:true,closed:true">
					<div id="kbm_content_div" class="">
						<table class="kbm_table" cellspacing=1>
							<tr class="kbm_content_title">
								<td class="kbm_table_label">
									项目名称
								</td>
								<td id="jyxmmc" class="kbm_td_text" style="font-weight: bold"></td>
								<td class="kbm_table_label">
									父类名称
								</td>
								<td id="jyflmc" class="kbm_td_text"></td>
							</tr>
							<tr>
								<td class="kbm_table_label">
									标题
								</td>
								<td id="jybt" class="kbm_td_text"></td>
								<td class="kbm_table_label">
									关键字
								</td>
								<td id="jygjz" class="kbm_td_text"></td>
							</tr>
							<tr class="kbm_tr">
								<td class="kbm_td1" colspan="4">
									参考值
								</td>
							</tr>
							<tr>
								<td id="jyckz" class="kbm_td2" colspan="4"></td>
							</tr>
							<tr class="kbm_tr">
								<td class="kbm_td1" colspan="4">
									常见疾病
								</td>
							</tr>
							<tr>
								<td id="jycjjb" class="kbm_td2" colspan="4"></td>
							</tr>
							<tr class="kbm_tr">
								<td class="kbm_td1" colspan="4">
									临床意义
								</td>
							</tr>
							<tr>
								<td id="jylcyy" class="kbm_td2" colspan="4"></td>
							</tr>
							<tr class="kbm_tr">
								<td class="kbm_td1" colspan="4">
									检验介绍
								</td>
							</tr>
							<tr>
								<td id="jyjyjs" class="kbm_td2" colspan="4"></td>
							</tr>
						</table>
					</div>
				</div>
				<div id="diseaseWin" class="easyui-window" title="疾病知识库"
					style="width: 800px; height: 500px"
					data-options="iconCls:'icon-save',modal:true,closed:true">
					<div id="kbm_content_div" class="">
						<table class="kbm_table" cellspacing=1>
							<tr class="kbm_content_title">
								<td class="kbm_table_label">
									疾病名称
								</td>
								<td id="jbmc" class="kbm_td_text" style="font-weight: bold">
									&nbsp;
								</td>
								<td class="kbm_table_label">
									父类名称
								</td>
								<td id="flmc" class="kbm_td_text">
									&nbsp;
								</td>
							</tr>
							<tr>
								<td class="kbm_table_label">
									标题
								</td>
								<td id="bt" class="kbm_td_text">
									&nbsp;
								</td>
								<td class="kbm_table_label">
									关键字
								</td>
								<td id="gjz" class="kbm_td_text">
									&nbsp;
								</td>
							</tr>
							<tr class="kbm_tr">
								<td class="kbm_td1" colspan="4">
									疾病描述
								</td>
							</tr>
							<tr>
								<td id="jbms" class="kbm_td2" colspan="4">
									&nbsp;
								</td>
							</tr>
							<tr class="kbm_tr">
								<td class="kbm_td1" colspan="4">
									临床诊断
								</td>
							</tr>
							<tr>
								<td id="lczd" class="kbm_td2" colspan="4">
									&nbsp;
								</td>
							</tr>
							<tr class="kbm_tr">
								<td class="kbm_td1" colspan="4">
									诊断依据
								</td>
							</tr>
							<tr>
								<td id="zdyj" class="kbm_td2" colspan="4">
									&nbsp;
								</td>
							</tr>
							<tr class="kbm_tr">
								<td class="kbm_td1" colspan="4">
									治疗方法
								</td>
							</tr>
							<tr>
								<td id="zlff" class="kbm_td2" colspan="4">
									&nbsp;
								</td>
							</tr>
							<tr class="kbm_tr">
								<td class="kbm_td1" colspan="4">
									建议
								</td>
							</tr>
							<tr>
								<td id="jy" class="kbm_td2" colspan="4">
									&nbsp;
								</td>
							</tr>
							<tr class="kbm_tr">
								<td class="kbm_td1" colspan="4">
									备注
								</td>
							</tr>
							<tr>
								<td id="bz" class="kbm_td2" colspan="4">
									&nbsp;
								</td>
							</tr>
						</table>
					</div>
				</div>
				<div id="ethicalWin" class="easyui-window" title="药品知识库"
					style="width: 800px; height: 500px"
					data-options="iconCls:'icon-save',modal:true,closed:true">
					<div id="kbm_content_div">
						<table class="kbm_table" cellspacing=1>
							<tr class="kbm_content_title">
								<td class="kbm_table_label">
									药品名称
								</td>
								<td id="ypmc_ethical" class="kbm_td_text"
									style="font-weight: bold">
									青霉素
								</td>
								<td class="kbm_table_label">
									父类名称
								</td>
								<td id="flmc_ethical" class="kbm_td_text">
									青霉素类
								</td>
							</tr>
							<tr>
								<td class="kbm_table_label">
									标题
								</td>
								<td id="bt_ethical" class="kbm_td_text">
									青霉素Benzylpenicillin
								</td>
								<td class="kbm_table_label">
									关键字
								</td>
								<td id="gjz_ethical" class="kbm_td_text">
									青霉素Benzylpenicillin
								</td>
							</tr>
							<tr class="kbm_tr">
								<td class="kbm_td1" colspan="4">
									药理学
								</td>
							</tr>
							<tr>
								<td id="ylx_ethical" class="kbm_td2" colspan="4">
									通过干扰细菌细胞壁的合成，对繁殖期细菌起杀菌作用。青霉素钠、钾不耐酸，口服吸收差，不宜用于口服。对溶血性链球菌等链球菌属、肺炎链球菌和不产青霉素酶的葡萄球菌具有良好抗菌作用。对肠球菌有中等度抗菌作用。淋病奈瑟菌、脑膜炎奈瑟菌、白喉棒状杆菌、炭疽芽孢杆菌、牛型放线菌、念珠状链杆菌、李斯特菌、钩端螺旋体和梅毒螺旋体对本品敏感。本品对流感嗜血杆菌和百日咳鲍特菌亦具一定抗菌活性，其他革兰阴性需氧或兼性厌氧菌对本品敏感性差。本品对梭状芽孢杆菌属、消化链球菌厌氧菌以及产黑色素拟杆菌等具良好抗菌作用，对脆弱拟杆菌的抗菌作用差
								</td>
							</tr>
							<tr class="kbm_tr">
								<td class="kbm_td1" colspan="4">
									适应症
								</td>
							</tr>
							<tr>
								<td id="syz_ethical" class="kbm_td2" colspan="4">
									用于敏感菌或敏感病原体所致的感染。溶血性链球菌引起的咽炎、扁桃体炎、猩红热、心内膜炎、丹毒、蜂窝织炎、产褥热等。肺炎球菌引起的肺炎、中耳炎、脑膜炎、菌血症等。梭状芽袍杆菌引起的破伤风、气性坏疽等。对革兰阳性杆菌(白喉杆菌)、螺旋体、放线菌及部分拟杆菌有抗菌作用。
								</td>
							</tr>
							<tr class="kbm_tr">
								<td class="kbm_td1" colspan="4">
									禁忌
								</td>
							</tr>
							<tr>
								<td id="jj_ethical" class="kbm_td2" colspan="4">
									对本品或其他青霉素类药过敏者禁用。
								</td>
							</tr>
							<tr class="kbm_tr">
								<td class="kbm_td1" colspan="4">
									不良反应
								</td>
							</tr>
							<tr>
								<td id="blfy_ethical" class="kbm_td2" colspan="4">
									常见过敏反应，包括严重的过敏性休克和血清病型反应、白细胞减少、药疹、接触性皮炎、哮喘发作等。
									毒性反应少见。肌内注射部位可发生周围神经炎。鞘内注射超过2万单位或静脉大剂量滴注可引起抽搐、肌肉阵挛、昏睡等，也可致短暂的精神失常，停药或降低剂量可恢复。
									二重感染。可出现耐青霉素金黄色葡萄球菌、革兰阴性杆菌或念珠菌感染。
								</td>
							</tr>
							<tr class="kbm_tr">
								<td class="kbm_td1" colspan="4">
									注意事项
								</td>
							</tr>
							<tr>
								<td id="zysx_ethical" class="kbm_td2" colspan="4">
									(1)首先详细询问过敏史，有过敏史者一般不宜做皮试。 (2)用前要按规定方法进行皮试(浓度为500单位/ml，皮内注射0.
									05？0.1m1)。 (3)一旦出现过敏性休克症状，应立即肌内注射0.1%的肾上腺素0.
									5？0.1lml，临床表现无改善者，半小时后重复1次，同时配合其他对症治疗。 (4)不宜鞘内注射。
									(5)重肾功能损害者应调整剂量或延长给药间隔。
									(6)大剂量给药时，应考虑到带入的钠离子或钾离子，可引起高钠血症或高钾血症。青霉素钠100万单位含钠离子1.7mmo1(39mg)；青霉素G钾100万单位含钾离子1.
									5mmo1（65mg) 。 (7 )本品水溶液不稳定，易水解，因此注射液应新鲜配制，必须保存时，应置冰箱冷藏，24小时内用完。
								</td>
							</tr>
							<tr class="kbm_tr">
								<td class="kbm_td1" colspan="4">
									药物相互作用
								</td>
							</tr>
							<tr>
								<td id="ywxhzy_ethical" class="kbm_td2" colspan="4">
									(1)与丙磺舒(一次0.
									5g，一日3次口服)、阿司匹林、吲哚美辛和磺胺类药物合用，可减少青霉素类药物的排泄，可使青霉素类血药浓度升高，作用增强，但毒性反应也可能增加。
									(2)与四环素类、红霉素、氯霉素、磺胺类等抑菌药合用，可能降低本药抗菌作用。 (3)与华法林合用，可加强抗凝血作用。
									(4)同时服用避孕药，可能影响避孕效果。
								</td>
							</tr>
							<tr class="kbm_tr">
								<td class="kbm_td1" colspan="4">
									用法和用量
								</td>
							</tr>
							<tr>
								<td id="yfhyl_ethical" class="kbm_td2" colspan="4">
									(1)肌内注射:①成人，一日80万？320万单位，分3？4次给药。②儿童，一日3万？5万单位/kg，分2？4次给予。
									(2)静脉滴注:适用于重病感染，①成人，一日200万？2000万单位，分2？4次静脉滴注。给药速度不能超过每分钟50万单位。②儿童，一日5万？20万单位/kg，分2
									？4次静脉滴注。 1)感染性心内膜炎:推荐剂量1000万？2000万单位，连续或每4小时分次静脉滴注，疗程4周。
									2)化脓性脑膜炎:一日20万？30万单位/kg，疗程4日。 3)气性坏疽:一次100万？200万单位，每2？3小时一次。
									4)梅毒:推荐剂量200万？400万单位，每4小时1次，共10？14日。
								</td>
							</tr>
							<tr class="kbm_tr">
								<td class="kbm_td1" colspan="4">
									制剂和规格
								</td>
							</tr>
							<tr>
								<td id="zjhgg_ethical" class="kbm_td2" colspan="4">
									(1)注射用青霉素钠:① 0. 48g （80万单位）；②0.6g （100万单位)； ③2. 4g（400万单位)。
									（2)注射用青霉素钾:0. 25g （4万单位）。
								</td>
							</tr>
							<tr class="kbm_tr">
								<td class="kbm_td1" colspan="4">
									备注
								</td>
							</tr>
							<tr>
								<td id="bz_ethical" class="kbm_td2" colspan="4">
									&nbsp;
								</td>
							</tr>
						</table>
					</div>
				</div>
				<div id="chineseMedWin" class="easyui-window" title="药品知识库"
					style="width: 800px; height: 500px"
					data-options="iconCls:'icon-save',modal:true,closed:true">
					<div id="kbm_content_div">
						<table class="kbm_table" cellspacing=1>
							<tr class="kbm_content_title">
								<td class="kbm_table_label">
									药材名称
								</td>
								<td id="ycmc_cMedical" class="kbm_td_text"
									style="font-weight: bold">
									大枣
								</td>
								<td class="kbm_table_label">
									父类名称
								</td>
								<td id="flmc_cMedical" class="kbm_td_text">
									补气药
								</td>
							</tr>
							<tr>
								<td class="kbm_table_label">
									标题
								</td>
								<td id="bt_cMedical" class="kbm_td_text">
									大枣Benzylpenicillin
								</td>
								<td class="kbm_table_label">
									关键字
								</td>
								<td id="gjz_cMedical" class="kbm_td_text">
									大枣Benzylpenicillin
								</td>
							</tr>
							<tr class="kbm_tr">
								<td class="kbm_td1" colspan="4">
									别名
								</td>
							</tr>
							<tr>
								<td id="bm_cMedical" class="kbm_td2" colspan="4">
									红枣、干枣、枣子
								</td>
							</tr>
							<tr class="kbm_tr">
								<td class="kbm_td1" colspan="4">
									功效
								</td>
							</tr>
							<tr>
								<td id="gx_cMedical" class="kbm_td2" colspan="4">
									果（大枣）：补中益气，养血安神。 树皮：消炎，止血，止泻。 根：行气，活血，调经。
								</td>
							</tr>
							<tr class="kbm_tr">
								<td class="kbm_td1" colspan="4">
									主治
								</td>
							</tr>
							<tr>
								<td id="zz_cMedical" class="kbm_td2" colspan="4">
									果（大枣）：用于脾虚食少，乏力便溏，妇人脏躁。 树皮：用于气管炎，肠炎，痢疾，崩漏；外用治外伤出血。
									根：用于月经不调，红崩，白带。
								</td>
							</tr>
							<tr class="kbm_tr">
								<td class="kbm_td1" colspan="4">
									性味
								</td>
							</tr>
							<tr>
								<td id="xw_cMedical" class="kbm_td2" colspan="4">
									果：甘，温。归脾、胃经。 树皮：苦、涩，温。 根：甘，温。
								</td>
							</tr>
							<tr class="kbm_tr">
								<td class="kbm_td1" colspan="4">
									科属分类
								</td>
							</tr>
							<tr>
								<td id="ksfl_cMedical" class="kbm_td2" colspan="4">
									蔷薇科
								</td>
							</tr>
							<tr class="kbm_tr">
								<td class="kbm_td1" colspan="4">
									资源分布
								</td>
							</tr>
							<tr>
								<td id="zyfb_cMedical" class="kbm_td2" colspan="4">
									全国大部分地区有产，主产于新疆、山西、河北、河南、山东、四川、贵州等地
								</td>
							</tr>
							<tr class="kbm_tr">
								<td class="kbm_td1" colspan="4">
									生药材鉴别
								</td>
							</tr>
							<tr>
								<td id="sycjb_cMedical" class="kbm_td2" colspan="4">
									大枣(4张)取本品粉末2g，加石油醚（60～90℃）10ml浸泡10分钟，超声处理约10分钟，滤过，弃去石油醚液，药渣晾干，加乙醚20ml，浸泡1小时，超声处理约15分钟，滤过，滤液浓缩至2ml，作为供试品溶液。另取齐墩果酸对照品，加乙醇制成每1ml含1mg的溶液，作为对照品溶液。照薄层色谱法（附录Ⅵ
									B）试验，吸取供试品溶液10μl、对照品溶液3μl，分别点于同一硅胶G薄层板上，以甲苯-醋酸乙酯-冰醋酸（14:4:0.5）为展开剂，展开，取出，晾干，喷以10%硫酸乙醇溶液，加热至斑点显色清晰。供试品色谱中，在与对照品色谱相应的位置上，显相同颜色的斑点
								</td>
							</tr>
							<tr class="kbm_tr">
								<td class="kbm_td1" colspan="4">
									名家论述
								</td>
							</tr>
							<tr>
								<td id="mjls_cMedical" class="kbm_td2" colspan="4">
									大枣(4张)取本品粉末2g，加石油醚（60～90℃）10ml浸泡10分钟，超声处理约10分钟，滤过，弃去石油醚液，药渣晾干，加乙醚20ml，浸泡1小时，超声处理约15分钟，滤过，滤液浓缩至2ml，作为供试品溶液。另取齐墩果酸对照品，加乙醇制成每1ml含1mg的溶液，作为对照品溶液。照薄层色谱法（附录Ⅵ
									B）试验，吸取供试品溶液10μl、对照品溶液3μl，分别点于同一硅胶G薄层板上，以甲苯-醋酸乙酯-冰醋酸（14:4:0.5）为展开剂，展开，取出，晾干，喷以10%硫酸乙醇溶液，加热至斑点显色清晰。供试品色谱中，在与对照品色谱相应的位置上，显相同颜色的斑点
								</td>
							</tr>
							<tr class="kbm_tr">
								<td class="kbm_td1" colspan="4">
									出处
								</td>
							</tr>
							<tr>
								<td id="cc_cMedical" class="kbm_td2" colspan="4">
									摘录《中国药典》《全国中草药汇编》
								</td>
							</tr>
						</table>
					</div>
				</div>
				<div class="commonright"
					style="margin-top: 0px; height: 500px; background-color: #f4f4f4; width: 30%; float: right; margin-left: 0px">
					<div id="chats"
						style="width: 100%; height: 500px; border: #999999 0px solid; float: left; overflow: auto; margin-left: 2px;"
						class="chats">
						<ul class="tablist" style="width: 100%; padding: 0px">
							<li class="li1 active">
								会话
								<i></i>
							</li>
						</ul>
						<div class="ulbox" style="display: block; width: 100%">
							<ul class="ul2 child" id="contact"
								style="display: block; width: 100%">
								<!-- 联系人 -->
							</ul>
						</div>
					</div>
					<div id="records"
						style="width: 100%; height: 500px; border: #999999 0px solid; float: left; display: none; overflow: auto; margin-left: 2px;"
						class="chats">
						<ul class="tablist" style="width: 100%; padding: 0px">
							<li class="li1 active">
								聊天记录
								<i></i>
							</li>
						</ul>
						<div class="message" id="msgContent" style="overflow: auto;">
							<!--  聊天记录  -->
						</div>
						<div class="bottom">
							<a href="javascript:void(0);" class="upOn" id="upOn">上一页</a>
							<input type="text" style="width: 50px" id="chatsNum" value="1" />
							<input type="hidden" id="countPage1" disabled="disabled">
							<span id="countPage">/0</span>
							<a href="javascript:void(0);" class="next" id="nextOn">下一页</a>
						</div>
					</div>
				</div>
			</div>



		</div>
</div>
<input type="hidden" id="imServerPath" value="http://localhost:8003/ChartPatientWebhttp://localhost:8003/ChatPatientWeb/">
<OBJECT ID="CallCenter" CLASSID="CLSID:F20C5A15-A3E3-4375-9A8B-8275489017B8" style="width: 0px;height: 0px;"></OBJECT>
<script>

      //右边伸缩
     
           var footerWidth=$('#footer').width();
           $('#footer').width(footerWidth-260+'px')
           var divOpen=true;
           $('.toggleButton').click(function(){
        	
        	   if(divOpen==true){
        		   divOpen=false;
        		   $(this).css({
        			   'backgroundImage':'url(<%=request.getContextPath()%>/main/include/images/open.png)'
        		   })
        		   $('.toggleDiv').animate({
        			   'marginRight':'-229px'
        		   }) 
        		   $('.displayDiv').animate({
        			   'margin-right':'10px'
        		   })
        	   }
        	   else{
        		   divOpen=true;
        		   $(this).css({
        			   'backgroundImage':'url(<%=request.getContextPath()%>/main/include/images/closed.png)'
        		   })
        		   $('.toggleDiv').animate({
        			   'marginRight':'0px'
        		   }) 
        		   $('.displayDiv').animate({
        			   'margin-right':'243px'
        		   })
        	   }
        	   
           })
           
         
</script>

</body>
</html>

