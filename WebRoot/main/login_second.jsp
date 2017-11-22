﻿<%@ page contentType="text/html; charset=UTF-8"%>
<%@ page import="com.tianjian.security.struts.form.LoginSecondForm"%>
<%@ page import="com.tianjian.security.struts.form.SessionForm"%>
<%@ page import="java.util.Date,com.tianjian.util.comm.DateUtil"%>
<%
	LoginSecondForm loginSecondForm = (LoginSecondForm) request.getAttribute("loginSecondForm");
	if (loginSecondForm != null) {
		session.setAttribute("loginSecondForm", loginSecondForm);
	}
	loginSecondForm = (LoginSecondForm) session.getAttribute("loginSecondForm");
	session.removeAttribute("loginSecondForm");

	SessionForm sessionForm = (SessionForm)session.getAttribute("sessionForm");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
		<%
		if (request.getServerPort() == 80) {
		%>
		<base href="http://<%=request.getServerName()%><%=request.getContextPath()%>/" />
		<%
		} else {
		%>
		<base href="http://<%=request.getServerName()%>:<%=request.getServerPort()%><%=request.getContextPath()%>/" />
		<%
		}
		%>
<meta http-equiv="X-UA-Compatible" content="IE=8"/>		
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />

<title>::: <%=application.getAttribute("security.SYSTEMNAME") %> :::</title>
<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/main/include/css/index_n.css" />
<link href="<%=request.getContextPath()%>/main/include/css/menuGuide.css" rel="stylesheet" type="text/css" />

<script language="javascript" src="include/javascript/jquery-1.4.2.min.js"></script>
<script language="javascript" src="main/include/javascript/redstorm.js"></script>

<link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/main/ext-3.2.0/resources/css/ext-all.css" />
<script type="text/javascript" src="<%=request.getContextPath()%>/main/ext-3.2.0/adapter/ext/ext-base.js" ></script>
<script type="text/javascript" src="<%=request.getContextPath()%>/main/ext-3.2.0/ext-all.js" ></script>

<script language="javascript" ><!--
shareObj = {};
//此处是调出二级模块类别
 function clickA(rootflag,id) {
  
		$.ajax({
			url:'security/security_login.do?verbId=getEndPublicClass&publicClassId='+id,
			type: 'GET',
			dataType: 'xml',
			timeout: 10000,
			cache:false,
			async: false,
			error:function(XmlHttpRequest,textStatus, errorThrown)
			  {
			  //alert("获取失败!"+XmlHttpRequest.responseText);
			  alert("获取失败!");
			  document.location = "<%=request.getContextPath()%>/home/nologin.jsp";
			  },
						
			success: function(xml){
				var publicClassId  = new Array();
				var publicClassName  = new Array();
				var publicClassPicPath  = new Array();
				var publicClassComments  = new Array();
				var publicClassSysFlag  = new Array();
				var publicClassRedirectUrl  = new Array();
				//清空重新生成
				shareObj = {};
				
				$(xml).find("publicClass").each(function(i){
					
					//遍历所有节点
					publicClassId[i] = $(this).children("publicClassId").text();
					publicClassName[i] = $(this).children("publicClassName").text();
					publicClassPicPath[i] = $(this).children("publicClassPicPath").text();
					publicClassComments[i] = $(this).children("publicClassComments").text();
					publicClassSysFlag[i] = $(this).children("publicClassSysFlag").text();
					publicClassRedirectUrl[i] = $(this).children("publicClassRedirectUrl").text();
					//构造

					eval("var publicclass"+i +"= {"+
							"name: '"+publicClassName[i]+"',"+
							"outsiteFlag: '"+publicClassSysFlag[i]+"',"+
							"publicClassRedirectUrl: '"+publicClassRedirectUrl[i]+"',"+
							"url: 'security/security_loginSecond.do',"+
							"param: 'verbId=login&publicClassId="+publicClassId[i]+"'"+
				   "}");
					eval("shareObj['publicclass"+i+"'] = publicclass"+i);
					
				});//each结束
				//构造完成
				
			}
		});//ajax结束
}; 

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
	return function() {
		if (req.readyState == 4) {
			if (req.status == 200) {
				//alert(req.responseText); 
				//alert(req.responseXML);
				var message="";
				var date = req.responseXML.getElementsByTagName("date")[0].childNodes[0].nodeValue;
				if(date>0){
					message+="<a href='<%=request.getContextPath()%>/drug/drugExpiryPop.do?verbId=queryDate'>"+date+"条药品有效期警告信息！</a><br/>";
					
				}
				var quantity = req.responseXML.getElementsByTagName("quantity")[0].childNodes[0].nodeValue;
				if(quantity>0){
				 	message+="<a href='<%=request.getContextPath()%>/drug/drugExpiryPop.do?verbId=queryQuantity'>"+quantity+"条药品库存警告信息！</a>";
				}
				 isShowToastWarning("XX发生一例食物中毒事件!<br/>"+message);	
			} else {
				alert("HTTP error: " + req.status);
			}
		}
	};
}

function expiryPop() {
	var xmlHttp = newXMLHttpRequest();
	var sendTo = 'drug/drugExpiryPop.do?verbId=getExpiryMessage' ;
	xmlHttp.open("GET", sendTo, true);
	var handlerFunction = getReadyStateHandler(xmlHttp);
	xmlHttp.onreadystatechange = handlerFunction;
	xmlHttp.send(null);
}


function getWarningData() {
	expiryPop();
																											   
}

function isShowToastWarning(temp) {
		var v = Number(temp);   
		if(v == 0) {							
			//alert("Don't open toastWindow when warning data is null");							
		}else {
			toastWindowObj().show(document);	
			$("#show").html(temp);
		}
}

Ext.namespace("Ext.ux");

Ext.ux.ToastWindowMgr = {
    positions: [] 
};

Ext.ux.ToastWindow = Ext.extend(Ext.Window, {
    initComponent: function(){
          Ext.apply(this, {
              	iconCls: this.iconCls || 'information',
	            width: 250,
	            height: 160,
	            autoScroll: false,
	            autoDestroy: false,
	            plain: false,
	            shadow:false
          });
        this.task = new Ext.util.DelayedTask(this.hide, this);
        Ext.ux.ToastWindow.superclass.initComponent.call(this);
    },
    setMessage: function(msg){
        this.body.update(msg);
    },
    setTitle: function(title, iconCls){
        Ext.ux.ToastWindow.superclass.setTitle.call(this, title, iconCls||this.iconCls);
    },
    onRender:function(ct, position) {
        Ext.ux.ToastWindow.superclass.onRender.call(this, ct, position);
    },
    onDestroy: function(){
        Ext.ux.ToastWindowMgr.positions.remove(this.pos);
        Ext.ux.ToastWindow.superclass.onDestroy.call(this);
        toastWindowFlag = false;//标识弹出框已隐藏
    },
    afterShow: function(){
        Ext.ux.ToastWindow.superclass.afterShow.call(this);
        this.on('move', function(){
               Ext.ux.ToastWindowMgr.positions.remove(this.pos);
            this.task.cancel();}
        , this);
        this.task.delay(600000);
    },
    animShow: function(){
        this.pos = 0;
        while(Ext.ux.ToastWindowMgr.positions.indexOf(this.pos)>-1)
            this.pos++;
        Ext.ux.ToastWindowMgr.positions.push(this.pos);
        this.setSize(250,160);
        this.el.alignTo(document, "br-br", [ -20, -20-((this.getSize().height+10)*this.pos) ]);
        this.el.slideIn('b', {
            duration: 2,
            callback: this.afterShow,
            scope: this
        });    
    },
    animHide: function(){
           Ext.ux.ToastWindowMgr.positions.remove(this.pos);
           this.el.ghost("b", {
           duration: 2,
           remove: true,
           scope: this,
           callback: this.destroy
        });    
    }
}); 


function toastWindowObj() {
		var toastWindow;
		 toastWindow = new Ext.ux.ToastWindow({//显示弹出框
			 title: '预警提示信息',
			 html: '<div id="show"></div>',
				 buttons:[                        
		                    {                      
		                    	text:'忽略',                  
		                    	disabled:false,
		                    	handler:function(){
	                    			toastWindow.hide();  
	                    			ignoreThisData(); 
	                    			toastWindowFlag = false;
	                        	}                                  
		                    },{                                       
		                        text:'再次提醒',                                        
		                        handler:function(){//点击时触发的事件         
		                    		toastWindow.hide(); 
		                    		againRemind();     
		                    		toastWindowFlag = false;
		                        }                                    
		                    }                                    
	                  ] 
			})
		return toastWindow;
	
}

	function ignoreThisData() {
		//取消定时取预警信息
		self.clearInterval(clock);
		//$.ajax({});
	}

	function againRemind() {
		$.ajax({
			type:"POST",
			dataType:"html",
			url:"warning/toastWindow.do",
			data:{
				verbId:"againRemind",			
				},
			success:function(responseText) {				
				//	alert(responseText);															   
			        }
		});
	}
--></script>

</head>

<body onload="getWarningData();">

	<form id="form" name="form" method="post" action="security/security_loginSecond.do">
	<input type="hidden" name="verbId" value="" />
	<input type="hidden" name="publicClassId" value=""/>
		<div id="header">
			<img src="main/include/images/login_03.jpg" width="536" height="85" />
				<ul class="nav">
					<li class="user">欢迎您！<%=sessionForm.getStaffName()%></li>
					<li><a href="#">消息发布</a></li>
					<li><a href="<%=request.getContextPath()%>/index.jsp?agree=true">退出系统 ></a></li>
				</ul>
			</div>
<div id="top">
    <img src="main/include/images/laba_06.png" width="14" height="16" align="absmiddle" /> 欢迎您！今天是<%=sessionForm.getStaffLoginTime() %>
    <%
		if(sessionForm.getStaffLicenseStopDate()!=null&&sessionForm.getStaffLicenseStopDate().trim().length()>0){
			Date date = new Date();
			Date stopDate = DateUtil.string2DateTime(sessionForm.getStaffLicenseStopDate(),"yyyy-MM-dd");
			Date warnDate = DateUtil.addDays(stopDate,-30);
			if(date.after(warnDate)){
				long t = stopDate.getTime() - date.getTime();
				long day = t / 1000 / 60 / 60 / 24;
				%>
				<span  style="color:red  ">   提醒：您的帐号有效期仅剩下 <%=day %> 天,为免影响使用,请及时与管理员联系!</span>
				<% 
			}
		}
	%>
</div>

<div style="display: block;" id="iframe1">
	<div class="content_box" id="content_box">
		<ul>
			<% if(loginSecondForm.getPublicClassIdList() != null && loginSecondForm.getPublicClassIdList().length > 0){
			for(int i=0;i<loginSecondForm.getPublicClassIdList().length;i++){
				%>
				<li>	
					<a id="<%=loginSecondForm.getPublicClassIdList()[i]%>" 
					   isPublicClass ="true"
					   publicClassSysFlag="<%=loginSecondForm.getPublicClassSysFlagList()[i]%>" 
					   rootflag="<%=loginSecondForm.getPublicClassRootFlagList()[i]%>"
					   link="<%=loginSecondForm.getPublicClassRedirectUrlList()[i] %>" title="<%=loginSecondForm.getPublicClassNameList()[i] %>"  target="_self" 
					   style="background:url(main/include/images/<%=loginSecondForm.getPublicClassPicPathList()[i] %>) ; "></a>
					   <!-- style="background:url(main/include/images/menuName.png) ; 
					   <!--class=""-->
				</li>
			<% 
				}
			} %>		
		</ul>
	</div>
</div>



<div id="footer"><%=application.getAttribute("security.SYSTEMNAME") %>&nbsp; <% 
				SessionForm sessionFormBottom = (SessionForm)session.getAttribute("sessionForm");
				if(sessionFormBottom!=null&&sessionFormBottom.getVersionUserName()!=null){ %>用户:<%=sessionFormBottom.getVersionUserName()%><%} %></div>
</form>
</body>
</html>




