/**
 * 定义全局变量
 */
var XMLHttpReq = null;
//WEB根目录路径
var WEBROOT_PATH = "";
//处理短信弹出层的基本路径（结束为“.do”），如“sms.do”，完整的路径将由WEB根目录、基本路径以及跳转标识拼装而来。直接设置这个值，那么使用弹出层时使用者将不关心整个后台处理的问题
var SMS_ACTION_PATH = "/sms/ajax.do";
//短信是否定时发送，true为时，否则（立即发送）为false
var SMS_TIMING_SENDING = false;

/**
 * 创建XMLHttpRequest对象
 */
function createXmlHttpRequest(){
	if(window.ActiveXObject){
		XMLHttpReq = new ActiveXObject("Microsoft.XMLHTTP");
	}else if(window.XMLHttpRequest){
		XMLHttpReq = new XMLHttpRequest(); 
	}
}

/**
 * 关闭短信弹出层
 */
function closed(){			
	document.getElementById("body_div").parentNode.removeChild(document.getElementById("body_div"));   
	document.getElementById("bg_div").parentNode.removeChild(document.getElementById("bg_div"));
}

/**
 * 显示/隐藏短信弹出层里短信模板的页面内容
 */
function showTemplate(){
	var display = document.getElementById("smsDivTemplateTr").style.display;
	if(display=="block"){
		document.getElementById("smsDivTemplateTr").style.display = "none";
		document.getElementById("smsDivTemplateTbody").style.display = "none";
	}else{
		document.getElementById("smsDivTemplateTr").style.display = "block";
		document.getElementById("smsDivTemplateTbody").style.display = "block";
	}
}

/**
 * 选中定时发送时，短信弹出层里定时时间控件和提交按钮的变化
 */
function changeButtonValue(){
	var checkbox = document.getElementById("smsDivTiming");
	if(checkbox.checked){
		document.getElementById("smsDivButton").value = "确定";
		document.getElementById("smsDivSendTimePlan").style.display = "block";
		SMS_TIMING_SENDING = true;
	}else{
		document.getElementById("smsDivButton").value = "发送";
		document.getElementById("smsDivSendTimePlan").style.display = "none";
		SMS_TIMING_SENDING = false;
	}
}

/**
 * 将oCheckbox控件的checked属性值拷贝到name属性值为checkboxsName的所有checkbox中
 */
function selectAll(oCheckbox, checkboxsName){
	var cbs = document.getElementsByName(checkboxsName);
	if(cbs==null || cbs.length<=0)
		return;
	if(oCheckbox==null)
		return;
	var checked = oCheckbox.checked;
	for(var i=0; i<cbs.length; i++){
		cbs[i].checked = checked;
	}
}
 			
/**
 * 选中短信模板主题时，将模板内容加载到短信内容里去
 */
function chooseTemplate(content){
	document.getElementById("smsDivSmsContent").innerText = content;
	showTemplate();
}

/**
 * 点击短信弹出层提交按钮（“发送”或“确定”按钮）时，按钮的变化和提交的过程
 */
function smsDivSubmit(oButton){
	//检测录入值是否合法的过程：手机号码（必须）、定时时间（可选）、短信内容（必须），另检测格式和长度
	var smsDivPhoneNumber = document.getElementById("smsDivPhoneNumber");
	if(smsDivPhoneNumber.value==""){
		alert("手机号码不能为空");
		return;
	}
	//手机格式检测...
	var smsDivSendTimePlan = document.getElementById("smsDivSendTimePlan");
	if(SMS_TIMING_SENDING==true && smsDivSendTimePlan.value==""){
		alert("定时发送时所指定的发送日期不能为空");
		return;
	}
	var smsDivSmsContent = document.getElementById("smsDivSmsContent");
	if(smsDivSmsContent.innerText==""){
		alert("短信内容不能为空");
		return;
	}
	if(smsDivSmsContent.innerText.length>250){
		alert("短信内容最长不能超过250个字符");
		return;
	}
	
	//合法之后的处理过程
	if(SMS_TIMING_SENDING)
		oButton.value = "保存中...";
	else
		oButton.value = "发送中...";
	oButton.disabled = true;
	
	//后台处理过程：AJAX，处理完毕后回传到前台一个值，然后前台根据这个值提示用户相应的信息
	createXmlHttpRequest();	   
    XMLHttpReq.onreadystatechange = function(){
    	if(XMLHttpReq.readyState == 4){
        	if(XMLHttpReq.status == 200){			
                var returnCode = XMLHttpReq.responseXML.getElementsByTagName("returncode")[0].firstChild.data;
	            var returnMessage = XMLHttpReq.responseXML.getElementsByTagName("returnmessage")[0].firstChild.data;
                if(returnCode=="0"){
	                window.alert((returnMessage==""?"短信"+(SMS_TIMING_SENDING?"保存":"发送")+"成功":returnMessage)); 
	            	//最后关闭短信弹出层
	            	closed();                
                }else{
	                window.alert("短信"+(SMS_TIMING_SENDING?"保存":"发送")+"操作失败["+returnMessage+"]");
	            	if(SMS_TIMING_SENDING)
	            		oButton.value = "确定";
	            	else
	            		oButton.value = "发送";
	            	oButton.disabled = false;                 
                }
            }else{
                window.alert("短信"+(SMS_TIMING_SENDING?"保存":"发送")+"操作失败");
            	if(SMS_TIMING_SENDING)
            		oButton.value = "确定";
            	else
            		oButton.value = "发送";
            	oButton.disabled = false;            	
            }             	        
        }
    };
	var para = "mobileTel="+smsDivPhoneNumber.value+"&timing="+SMS_TIMING_SENDING+"&sendTime="+smsDivSendTimePlan.value+"&content="+smsDivSmsContent.innerText;
    XMLHttpReq.open("Post", WEBROOT_PATH+"/"+SMS_ACTION_PATH+"?verbId=sendSms", true);
    XMLHttpReq.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
	XMLHttpReq.send(para);
}

/**
 * 短信模板查询输入框AJAX查询过程
 * 当键盘按键弹起时执行本过程
 */
function searchSmsTemplate(){
	createXmlHttpRequest();	   
    XMLHttpReq.onreadystatechange = function(){
    	if(XMLHttpReq.readyState == 4){
        	if(XMLHttpReq.status == 200){
        		var templates = XMLHttpReq.responseXML.getElementsByTagName("template");
        		if(templates==null || templates.length<=0)
        			return;
        		var tbody = document.getElementById("smsDivTemplateTbody");
        		var children = tbody.getElementsByTagName("tr");
        		if(children!=null && children.length>0)
        			for(var i=children.length-1; i>=0; i--)
        				tbody.removeChild(children[i]);
        				
        		for(var i=0; i<templates.length; i=i+2){
        			var tr = document.createElement("tr");
        			tr.className = "pinyin_nav";
        			
        			var template = templates[i];
        			var name = template.getElementsByTagName("name")[0].firstChild.data;
        			var content = template.getElementsByTagName("content")[0].firstChild.data;
        			var td0 = document.createElement("td");
        			td0.colSpan = "2";
        			td0.align = "left";
        			td0.title = content;
        			
	        		td0.innerHTML = '<input type="radio" onclick="chooseTemplate(\''+content+'\')" name="smsDivTemplate"/>'+name;
					tr.appendChild(td0);
					
					if(i+1>=templates.length){//取得的模板的数目为单数，最后补充一个空白td
						var td1 = document.createElement("td");
	        			td1.colSpan = "2";
	        			td1.align = "left";
	        			tr.appendChild(td1);
					}else{
						template = templates[i+1];
	        			name = template.getElementsByTagName("name")[0].firstChild.data;
	        			content = template.getElementsByTagName("content")[0].firstChild.data;
	        			td2 = document.createElement("td");
	        			td2.colSpan = "2";
	        			td2.align = "left";
	        			td2.title = content;
	        			
	        			td2.innerHTML = '<input type="radio" onclick="chooseTemplate(\''+content+'\')" name="smsDivTemplate"/>'+name;
						tr.appendChild(td2);
					}
					tbody.appendChild(tr);
					document.getElementById('medd').innerText = document.getElementById('guoji_table').innerHTML;
        		}
            }else{
                window.alert("短信模板查询失败");
            }
        }
    };
	var para = "templateName="+document.getElementById("smsDivTemplate").value;
    XMLHttpReq.open("Post", WEBROOT_PATH+"/"+SMS_ACTION_PATH+"?verbId=getSmsTemplate", true);
    XMLHttpReq.setRequestHeader("Content-Type","application/x-www-form-urlencoded");
	XMLHttpReq.send(para);
}
/**
 * 弹出短信弹出层
 * WEBROOT_PATH：必须的，即jsp里的<%=request.getContextPath()%>表达式的值
 * phoneNumber：可选的，如果知道短信接收方手机号码就传入，不知道就传入null、空串或者干脆不传入
 */
function sendSms(webRootPath, phoneNumber){
	WEBROOT_PATH = webRootPath;
	var newele = document.createElement("div");
	newele.id = "body_div";
	newele.style.display = "block";			
	newele.style.background = "#000000";		
	
	if(document.body.scrollHeight<462){
		newele.style.width= "100%";
		newele.style.height= "100%";
	}else{
		newele.style.width= document.body.scrollWidth + "px";
		newele.style.height= document.body.scrollHeight + "px";
	}
			
	newele.style.top="0";
	newele.style.left="0";
	newele.style.position="absolute";
	if(document.all){
		newele.style.filter = "alpha(Opacity=30)";	
	}else{
		newele.style.opacity="0.3";
	}		
	document.body.appendChild(newele);
	
	//弹出窗口背景
	var bgdiv = document.createElement("div");
	bgdiv.id = "bg_div";
	bgdiv.style.visibility="";
	bgdiv.style.position="absolute";
	var bodywidth = document.body.clientWidth;
	bgdiv.style.top="70px";
	bgdiv.style.backgroundImage = "url("+WEBROOT_PATH+"/include/images/smsbg.gif)";
	bgdiv.style.left=(bodywidth-385)/2 + "px";
	
	//弹出窗口标题
	var nav_div = document.createElement("div");
	nav_div.id = "div_title";
	nav_div.onclick = function(){
		body_div.parentNode.removeChild(body_div);   
		bg_div.parentNode.removeChild(bg_div);
	}
	bgdiv.appendChild(nav_div);	
	
	//弹出窗口div框架
	var divList = document.createElement("div");
	divList.id = "div_pinyin";						
	bgdiv.appendChild(divList);
	
	document.body.appendChild(bgdiv);
	var htmlString = '<div class="guoji_div" align="center">'
		+'<table id="guoji_table" border="0" cellspacing="0" cellpadding="0" style="width:350px">'
		+'<tr class="pinyin_nav" style="width:100%">'
		+'<td style="width:60px" align="right">手机号码&nbsp;&nbsp;</td>';	
	if(phoneNumber && phoneNumber!="" && phoneNumber!="undefined")
		htmlString += '<td style="width:115px" align="left"><input type="text" id="smsDivPhoneNumber" class="write_input" style="width:115px;" value="'+phoneNumber+'" onmouseover="this.title=this.value" onchange="this.title=this.value" onkeyup="this.title=this.value"/></td>';
	else
		htmlString += '<td style="width:115px" align="left"><input type="text" id="smsDivPhoneNumber" class="write_input" style="width:115px;" value="" onmouseover="this.title=this.value" onchange="this.title=this.value" onkeyup="this.title=this.value"/></td>';
	htmlString += '<td style="width:55px" align="right">'
		+'<input id="smsDivTiming" type="checkbox" onclick="changeButtonValue()"/>定时&nbsp;&nbsp;</td>'
		+'<td style="width:120px" align="left">'
		+'<input id="smsDivSendTimePlan" type="text" style="width:105px;display:none" readonly onclick="WdatePicker({el:\'smsDivSendTimePlan\',dateFmt:\'yyyy-MM-dd HH\',minDate:\'%y-%M-%d HH\'})"/>'
		+'</td>'
		+'</tr>'
		+'<tr class="pinyin_nav">'
		+'<td align="right">短信内容&nbsp;&nbsp;<br/><a href="javascript:showTemplate()" title="展开或收缩模板列表">（模板）</a>&nbsp;&nbsp;</td>'
		+'<td colspan="3" align="left"><textarea id="smsDivSmsContent" style="width:276px;height:100px" title="短信内容过长时将分条发送（一般60字之外将分条发送）"></textarea></td>'
		+'</tr>'
		+'<tr class="pinyin_nav" id="smsDivTemplateTr" style="display:none">'
		+'<td colspan="4" align="left">'
		+'模板主题:&nbsp;&nbsp;&nbsp;<input type="text" id="smsDivTemplate" name="smsDivTemplate" onkeyup="searchSmsTemplate()"/>'
		+'</td>'
		+'</tr>'
		+'<tbody id="smsDivTemplateTbody" style="display:none">'
		+'<tr class="pinyin_nav">'
		+'<td colspan="2" align="left" title="模板一的内容">'
		+'<input type="radio" onclick="chooseTemplate(\'模板一的内容\')" name="smsDivTemplate"/>模板一'
		+'</td>'
		+'<td colspan="2" align="left" title="模板二的内容">'
		+'<input type="radio" onclick="chooseTemplate(\'模板二的内容\')" name="smsDivTemplate"/>模板二'
		+'</td>'
		+'</tr>'
		+'<tr class="pinyin_nav">'
		+'<td colspan="2" align="left" title="模板三的内容">'
		+'<input type="radio" onclick="chooseTemplate(\'模板三的内容\')" name="smsDivTemplate"/>模板三'
		+'</td>'
		+'<td colspan="2" align="left" title="模板四的内容">'
		+'<input type="radio" onclick="chooseTemplate(\'模板四的内容\')" name="smsDivTemplate"/>模板四'
		+'</td>'
		+'</tr>'
		+'<tr class="pinyin_nav">'
		+'<td colspan="2" align="left" title="模板五的内容">'
		+'<input type="radio" onclick="chooseTemplate(\'模板五的内容\')" name="smsDivTemplate"/>模板五 '
		+'</td>'
		+'<td colspan="2" align="left" title="模板六的内容">'
		+'<input type="radio" onclick="chooseTemplate(\'模板六的内容\')" name="smsDivTemplate"/>模板六'
		+'</td>'
		+'</tr>'
		+'<tr class="pinyin_nav">'
		+'<td colspan="2" align="left" title="模板七的内容">'
		+'<input type="radio" onclick="chooseTemplate(\'模板七的内容\')" name="smsDivTemplate"/>模板七'
		+'</td>'
		+'<td colspan="2" align="left" title="模板八的内容">'
		+'<input type="radio" onclick="chooseTemplate(\'模板八的内容\')" name="smsDivTemplate"/>模板八'
		+'</td>'
		+'</tr>'
		+'</tbody>'
		+'<tr class="pinyin_nav">'
		+'<td colspan="4" align="center">'
		+'<input id="smsDivButton" style="width:60px" type="button" class="button" value="发送" onclick="smsDivSubmit(this)" />'
		+'</td>'
		+'</tr>'
		+'</table>'
		+'</div>';	
	div_pinyin.innerHTML = htmlString;
	fade("body_div");//调用该函数作用是背景渐变透明
	if(phoneNumber && phoneNumber!="" && phoneNumber!="undefined")//设置默认焦点
		document.getElementById("smsDivSmsContent").focus();
	else
		document.getElementById("smsDivPhoneNumber").focus();
} 