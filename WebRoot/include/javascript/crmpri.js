//*****************************************//
//********   By DZENALL   *****************//
//*****************************************//

//去除str字符串前后的所有空白符后返回剩余字符
function trim(str){
	return str.replace(/(^\s*)|(\s*$)/g, "");
}
//短信平台中人员姓名和手机号码以及结果显示之间关系的初始化
//acceptFull是页面上显示选/填结果的一个控件的ID，显示的全格式为:"姓名"<号码>,...
//acceptName是页面上隐藏的选/填结果的人员姓名属性字符串
//acceptPhone是页面上隐藏的选/填结果的人员手机号码属性字符串
function initAddressBox(acceptFull,acceptName,acceptPhone){
	if(trim(document.getElementById(acceptName).value)=="" && trim(document.getElementById(acceptPhone).value)==""){
		document.getElementById(acceptFull).value="";
		return;
	}else{		
		var org_ap = trim(document.getElementById(acceptFull).value);
		var acceptPhone = trim(document.getElementById(acceptPhone).value);
		var acceptName = trim(document.getElementById(acceptName).value);
		if(org_ap=="\"\"<>" || org_ap==""){
			document.getElementById(acceptFull).value = trim( "<" + acceptPhone + ">");
		}else if((document.getElementById(acceptFull).value).indexOf(acceptPhone) == -1){
			document.getElementById(acceptFull).value = trim(org_ap + ","  + "<" + acceptPhone + ">");	
		}else{		
			return false;
		}
	}
}
//短信平台中手工输入人员姓名和手机号码
//acceptFull是页面上显示选/填结果的一个控件的ID，显示的全格式为:"姓名"<号码>,...
//acceptName是页面上隐藏的选/填结果的人员姓名属性字符串
//acceptPhone是页面上隐藏的选/填结果的人员手机号码属性字符串
//这个函数是“填”操作，addressBox(self, acceptFull, acceptName, acceptPhone, divId)是“选”操作函数
function addressBoxPerson(acceptFull,acceptName,acceptPhone){
	//var strName = trim(window.prompt("请输入姓名!(例:张三，可选输入项)",""));
	var strPhone = trim(window.prompt("请输入手机号!(例:13312345678，必填项，必须以数字开头，除数字外，可以含有\"-\")",""));
	
	if(strPhone==""){
		alert("手机号码不能为空!");
		return ;
	}
	var patrn = /^[+]{0,1}(\d){1,3}[ ]?([-]?((\d)|[ ]){1,12})+$/;
	if(!patrn.exec(strPhone)){
		alert("不是手机号码!(必须以数字开头，除数字外，可以含有\"-\")");
		return;
	}
	var org_ap = trim(document.getElementById(acceptFull).value);
	
	if(org_ap=="\"\"<>" || org_ap==""){
		//document.getElementById(acceptName).value = trim(strName);
		document.getElementById(acceptPhone).value = trim(strPhone);
		document.getElementById(acceptFull).value = trim("<" + strPhone + ">");
	}else if((document.getElementById(acceptFull).value).indexOf(strPhone) == -1){
		//document.getElementById(acceptName).value = trim(document.getElementById(acceptName).value + "," + strName);
		document.getElementById(acceptPhone).value = trim(document.getElementById(acceptPhone).value + "," + strPhone);
		document.getElementById(acceptFull).value = trim(org_ap + "," +  "<" + strPhone + ">");	
	}else{		
		return false;
	}
	
}
//短信平台中从已有人员信息里选取人员姓名和手机号码
//self是一个系列li标签集合中的一个
//acceptFull是页面上显示选/填结果的一个控件的ID，显示的全格式为:"姓名"<号码>,...
//acceptName是页面上隐藏的选/填结果的人员姓名属性字符串
//acceptPhone是页面上隐藏的选/填结果的人员手机号码属性字符串
//divId是li向上ul向上div容器控件的ID属性
//这个函数是“选”操作，addressBoxPerson(acceptFull,acceptName,acceptPhone)是“填”操作函数
function addressBox(self, acceptFull, acceptName, acceptPhone, divId){	
	var org_ap = trim(document.getElementById(acceptFull).value);
	if(org_ap=="\"\"<>" || org_ap==""){
		document.getElementById(acceptName).value = trim(self.innerHTML);
		document.getElementById(acceptPhone).value = trim(self.title);
		document.getElementById(acceptFull).value = trim("\"" + self.innerHTML + "\"" + "<" + self.title + ">");
		document.getElementById(divId).style.display='none';
	}else if((document.getElementById(acceptFull).value).indexOf(self.title) == -1){
		document.getElementById(acceptName).value = trim(document.getElementById(acceptName).value + "," + self.innerHTML);
		document.getElementById(acceptPhone).value = trim(document.getElementById(acceptPhone).value + "," + self.title);
		document.getElementById(acceptFull).value = trim(org_ap + "," + "\"" + self.innerHTML + "\"" + "<" + self.title+ ">");	
		document.getElementById(divId).style.display='none';
	}else{		
		document.getElementById(divId).style.display='none';
		return false;
	}				
}
//将ID属性值为templateTextId的目标控件的值作为模板内容转到由actionName参与构成的URL页面中去
function toTemplate(actionName, templateTextId){
	var templateText = trim(document.getElementById(templateTextId).value);
	if(templateText==""){
		alert("消息内容不能空!");
		window.history.back();
	}else{
		var docForms = document.forms;
		if(docForms!=null){
			document.forms[0].action=actionName+".do?ctx="+templateText+"";
			document.forms[0].op.value="load";
			document.forms[0].submit();
		}else{
			alert("Promgramming error[no form element, can not be submit any way]!");
			window.history.back();
		}
	}
}
//将参数值赋给ID属性为aimComponentId的目标控件
function fillTemplateText(templateText, aimComponentId){
	templateText = trim(templateText);
	if(templateText!=null && templateText!=""){
		document.getElementById(aimComponentId).value=templateText;
	}
}
//展示系统提示，如果参数不合法或为空则不提示，反之则进行提示参数内容
function showTips(tip){
	if(tip==null || trim(tip)=="" || trim(tip)=="null" || trim(tip)=="NULL"){
	}else{
		alert(tip);
	}
}
//***************
//子页面单值返回处理的接收端
//打开actionName.do?op=operationId子页面，将从子页面接收来的单值按照简单追加的方式加到目标控件ID属性值为aimComponentId之后
//简单追加：如果目标控件的末字符不符合书写的分割意义，则加上一个规定的分隔符后再追加单值
//***************
function evtOnSelect(contextPath, actionName, operationId, aimComponentId){
	var returnValue = showModalDialog(actionName+".do?op="+operationId,'','dialogWidth:800px;dialogHeight:500;dialogLeft:200px;dialogTop:150px;center:yes;help:yes;resizable:yes;status:yes');
	var sendContent = document.getElementById(aimComponentId).value;
	if(sendContent=="" || sendContent==null){
		document.getElementById(aimComponentId).value=returnValue[0];
	}else if(isEndedWithSeparator(sendContent)==true){
		document.getElementById(aimComponentId).value=document.getElementById(aimComponentId).value+returnValue[0];
	}else{
		document.getElementById(aimComponentId).value=document.getElementById(aimComponentId).value+","+returnValue[0];
	}
}

//如果aimString结尾字符在separatorList字符集合中，则返回true，否则返回false
function isEndedWithSeparator(aimString){
	var separatorList = new Array(",", ".", " ", "，", "。", "-", "?", "？", "!", "！");
	var separatorDefault = ",";
	if(aimString==null || trim(aimString)==""){
		return false;
	}else{
		if(separatorList==null || separatorList.length<=0){
			alert("crmpri.js文件中常量separatorList未设定！");
			return false;
		}else if(separatorDefault==null || trim(separatorDefault)==""){
			alert("crmpri.js文件中常量separatorDefault未设定！");
			return false;
		}else{
			for(var i=0; i<separatorList.length; i++){
				var separator = separatorList[i];
				var lastChar = aimString.substr(aimString.length-1,1);
				if(separator==lastChar){
					return true;
				}
			}
			return false;
		}
	}	
}

