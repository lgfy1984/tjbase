//////////////////////////////////////////////////////////////////////////////////////
//////FUNCTION:根据引用页面的具体情况转向verbId为参数值的跳转/////////////////////////////////
//////NOTE:取用引用页面的第一个base链接的href属性以及第一个form的action属性与参数一起构成跳转URL///
///////////////////////////////////////////////////////////////////////////////////////
function fHrefToUrl(verbId){
	var base = document.all.tags('BASE')[0].href;
	var action = document.forms[0].action;
	var href = "";
	if(action.substring(0, base.length)===base){
		href=action+'?verbId='+verbId;
	}else{
		href=base+'/'+action+'?verbId='+verbId;
	}
	window.location.href = href;
}


////////////////////////////////////////////////////////////////////////////////////////
///////FUNCTION:根据引用页面的具体情况转向verbId为参数值的跳转，跳转之前提示是否执行跳转////////////
//////NOTE:取用引用页面的第一个base链接的href属性以及第一个form的action属性与参数一起构成跳转URL////
////////////////////////////////////////////////////////////////////////////////////////
function fHrefToUrlWithTip(verbId){
	if(confirm("Realmente la necesidad de abandonar la operación en curso?")){
		var base = document.all.tags('BASE')[0].href;
		var action = document.forms[0].action;
		var href = "";
		if(action.substring(0, base.length)===base){
			href=action+'?verbId='+verbId;
		}else{
			href=base+'/'+action+'?verbId='+verbId;
		}
		window.location.href = href;
	}
}


/*
 * 将页面上所有的name属性值为nameOfEachCheck的checkbox的状态切换为跟oAllCheck对象checked状态一致
 */
function checkAll(oAllCheck, nameOfEachCheck){
	var status = oAllCheck.checked;
	var checkboxSet = document.getElementsByName(nameOfEachCheck);
	if(checkboxSet!=null && checkboxSet.length>0){
		 for(var i=0; i<checkboxSet.length; i++){
		 	if(checkboxSet[i].tagName.toUpperCase()=="INPUT" && checkboxSet[i].type.toUpperCase()=="CHECKBOX" && checkboxSet[i].name==nameOfEachCheck){
		 		checkboxSet[i].checked=status;
		 	}
		 }
	}
}


/*
 * 根据Element对象的id或者name属性值取出同TR行的另一个tagName已指定的Element对象，注意：此两个方法只适用于一个TR下N个TD，且每个TD下的Element作为this使用，且同一个TR内没有id或name相同的Element
 * 如：<tr><td><input type="text" id="ia" name="na"></td><td><input type="text" id="ib" name="nb"></td></tr>
 * getElementInSameLineById(this, 'input', 'ib')	即根据id属性值为ia的对象this取得同行的属性id为ib且tagName为input的对象。
 * 同理
 * getElementInSameLineByName(this, 'input', 'nb')	即根据name属性值为na的对象this取得同行的属性name为nb且tagName为input的对象。
 */
function getElementInSameLineByName(oElement, tagnameOfTargetElement, nameOfTargetElement){
	var oTr = oElement.parentNode.parentNode;
	var oTargets = oTr.getElementsByTagName(tagnameOfTargetElement);
	if(oTargets!=null && oTargets.length>0){
		for(var i=0; i<oTargets.length; i++){
			if(oTargets[i].name==nameOfTargetElement){
				return oTargets[i];
			}
		}
	}
	return null;
}
function getElementInSameLineById(oElement, tagnameOfTargetElement, idOfTargetElement){
	var oTr = oElement.parentNode.parentNode;
	var oTargets = oTr.getElementsByTagName(tagnameOfTargetElement);
	if(oTargets!=null && oTargets.length>0){
		for(var i=0; i<oTargets.length; i++){
			if(oTargets[i].id==idOfTargetElement){
				return oTargets[i];
			}
		}
	}
	return null;
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
function getReadyStateHandler(req, responseXmlHandler) {
	return function () {
		if (req.readyState == 4) {
      		if (req.status == 200) {
      			//alert(req.responseText); 
			    responseXmlHandler(req.responseXML);
			} else {
			    alert("HTTP error: " + req.status);
      		}
    	}
  	}
}
//新增初始化工作，转到新增页面
function cmdToAdd(){
	document.form.verbId.value = "addInit";
	document.form.submit();
}
//查看详细时候用
function cmdView(id){
	document.form.idHidden.value = id;
	document.form.verbId.value = "detail";
	document.form.submit();
}
//更新准备时候用
function cmdToUpdate(id){
	document.form.idHidden.value = id;
	document.form.verbId.value = "updateInit";
	document.form.submit();
}	
//单个删除时候用
function cmdDelete(id){
	if(id!=null && rTrim(lTrim(id)).length>0){
		if(!confirm("¿Seguro de que desea borrar？")){
			return false; 
		}
		document.form.idHidden.value = id;
		document.form.verbId.value = "delete";
		document.form.submit();
	}
}
//批量删除时使用
function cmdDeleteBatch(nameOfComponent){
	var components = document.getElementsByName(lTrim(rTrim(nameOfComponent)));
	if(components!=null && components.length>0){
		for(var i=0; i<components.length; i++){
			if(components[i].checked==true){
				document.form.verbId.value = "deleteBatch";
				document.form.submit();
				return true;
			}
		}
	}
	alert("No ha seleccionado ningún registro");
	return false;
}
function submitQueryForm() { 
  	document.forms[0].page.value = 1; 
  	document.forms[0].orderNo.value = ""; 
  	document.forms[0].asc.value = ""; 
  	document.forms[0].method = "POST";
 	document.forms[0].verbId.value = "query";
  	document.forms[0].submit();
}			
function commandOrderBy(order, asc) { 
  	document.forms[0].orderNo.value = order; 
  	document.forms[0].asc.value = asc; 
  	document.forms[0].method = "POST";
  	document.forms[0].verbId.value = "query";
  	document.forms[0].submit();
}						
function goPage(page) {  
   document.forms[0].page.value = page;
   document.forms[0].verbId.value = "query";    
   document.forms[0].submit();
}			
function goPage2(){			  
  	var _tp=document.getElementById('_tp');
  var _total=document.getElementById('_total');
    	if(!isMadeOf(_tp.value,'1234567890')){
      		alert("Error entero!");
      		return;
    	}
    	if(_tp.value<=0){
    		alert("Las páginas deben ser mayor que 0!");
			return;
    	}
    	if(parseInt(_tp.value)>parseInt(_total.value)){
      		alert("No puede ser mayor que el número total de páginas!");
      		return;
    	} 
  	
  	documents[0].form.verbId.value = "query";    
  	documents[0].form.submit();
}
function isMadeOf(val, str){
	var jj;
	var chr;
	for (jj=0;jj<val.length;++jj){
		chr=val.charAt(jj);
		if (str.indexOf(chr,0)==-1)
			return false;
	}			
	return true;
}

///////////
function isInteger(tip, oElm){
	if(!isMadeOf(oElm.value,'1234567890')){
   		alert("Error ["+ punta +"] de entrada, por favor re-entrada");
   		oElm.value="";
   		oElm.focus();
   		return;
  	}
}
function isNull(oElm){
	var msg = "FUNCTION isNull("+oElm.name+")";
	if(oElm!=null){
		if(oElm.tagName.toUpperCase()=="INPUT" && oElm.type.toUpperCase()=="TEXT"){
			msg += "\n target:input.text";
			if(oElm.value==null || lTrim(rTrim(oElm.value)).length<=0){
				msg += "\n return true";
				//alert(msg);
   				return true;
			}
		}else if(oElm.tagName.toUpperCase()=="TEXTAREA"){
			msg += "\n target:textarea";
			if(oElm.innerText==null || lTrim(rTrim(oElm.innerText)).length<=0){
				msg += "\n return true";
				//alert(msg);
				return true;
			}
		}
	}
	msg += "\n return false";
	//alert(msg);
	return false;
}
//判断是否为float型数据
function checkFloatNum(val_num){
  	var checkOK = "0123456789.";
  	var checkStr = val_num;
  	var allValid = true;
  	var decPoints = 0;
  	var allNum = "";
 	var n=0;
  	for(i=0; i<checkStr.length; i++){
	    ch = checkStr.charAt(i);
	    if(ch==checkOK.charAt(10)){
	       n++;//判断该字符串中有几个点
	    }
	    for(j=0; j<checkOK.length; j++)
	    	if(ch==checkOK.charAt(j))
	    		break;
	    if(j==checkOK.length){
	      	allValid = false;
	      	break;
	    }
	 	if(n>1){//如果字符串中点的个数>1的,错误 
	  		allValid=false;
	  		break;
	 	}
  	}
  	if (!allValid){
    	return (false);
  	}
  	return (true);
} 
//ADD页面简单提交FORM的JS
function fSimpleSavingSubmit(){	
	var msg = "FUNCTION fSimpleSavingSubmit()";
	//alert(msg);
	//input
	var inputs = document.getElementsByTagName("input");
	if(inputs!=null && inputs.length>0){
		for(var i=0; i<inputs.length; i++){											
			var input = inputs[i];
			var type = input.type.toUpperCase();
			if(type==null || type=="" || type=="HIDDEN" || type=="BUTTON"){
				continue;
			}
			msg += "\n【input="+input.name+"】";
			msg += "\n type="+type;							
			var value = lTrim(rTrim(input.value));
			msg += ", value="+value;
			msg += ", isNull(input)="+isNull(input);
			var ulmust = getUlmust(input);
			msg += ", ulmust="+ulmust;
			var ultip = getUltip(input);
			msg += ", ultip="+ultip;
			var ultype = getUltype(input);
			msg += ", ultype="+ultype;
			var ullen = getUllen(input);
			msg += ", ullen="+ullen;
			if(type=="TEXT"){//input.text
				if(ulmust==true && isNull(input)){
					//alert(msg);
					alert(ultip+"No puede estar vacío, por favor, de entrada！");
					return false;
				}
				if(ullen!=-1){
					if(ullen<lengthOfString(value)){
						//alert(msg);
						alert(ultip + "de entrada de longitud no puede exceder de" +  Ullen +", ha entrado en la duración de" + value.length);
						return false;
					}
				}
				if(ultype=="INT"){
					if(ulmust==true && isNull(input) && !isDigit(value)){
						//alert(msg);
					 	alert(ultip+"Valor debe introducirse en");
						return false;
					}
				}
				if(ultype=="DATE"){
					if(ulmust==true && isNull(input) && !testSimpleDate(value)){
						//alert(msg);
					 	alert(ultip + "se debe introducir un formato de fecha válido \ n formato: yyyy-MM-dd");
						return false;
					}
				}
				if(ultype=="FLOAT"){
					try{
						if(checkFloatNum(value)==false){
							alert(ultip + "entero o fraccionario, por favor rellene el correcto");
							return false;						
						}
						var f = parseFloat(value);
						if(f>99999999){
							alert(ultip + "no más de 99.999.999");
							return false;
						}
					}catch(e){
						alert(ultip + "entero o fraccionario, por favor rellene el correcto");
						return false;
					}
				}
				
			}else if(type=="RADIO"){//input.radio
			}else if(type=="CHECKBOX"){//input.checkbox
			}
		}
	}
	//textarea
	var textareas = document.getElementsByTagName("textarea");
	//alert(textareas);
	if(textareas!=null && textareas.length>0){
		for(var i=0; i<textareas.length; i++){
			var textarea = textareas[i];
			msg += "\n【textarea="+textarea.name+"】";
			var value = textarea.innerText;
			msg += ", innerText="+value;
			var must = getUlmust(textarea);
			msg += ", ulmust="+must;
			var tip = getUltip(textarea);
			msg += ", ultip="+tip;
			var type = getUltype(textarea);
			msg += ", ultype="+type;
			var len = getUllen(textarea);
			msg += ", ullen="+len;
			if(must==true && value==""){
				alert(tip+"No puede estar vacío, por favor, ayuda!");
				//alert(msg);
				return false;
			}
			if(len!=-1){
				if(len<lengthOfString(value)){
					alert(tip+"La longitud de la entrada no puede ser superior"+len);
				//	alert(msg);
					return false;
				}
			}
			if(type=="INT"){
				if(must==true && value=="" && !isDigit(value)){
				 	alert(tip+"Valor debe introducirse en");
					//alert(msg);
					return false;
				}
			}
			if(ultype=="DATE"){
				if(must==true && value=="" && !testSimpleDate(value)){
					//alert(msg);
				 	alert(ultip + "se debe introducir un formato de fecha válido \ n formato: yyyy-MM-dd");
					return false;
				}
			}
			if(type=="FLOAT"){
				try{
					if(checkFloatNum(value)==false){
						alert(ultip + "entero o fraccionario, por favor rellene el correcto");
						return false;						
					}
					var f = parseFloat(value);
					if(f>99999999){
						alert(ultip + "no más de 99.999.999");
						return false;
					}
				}catch(e){
					alert(ultip+ "entero o fraccionario, por favor rellene el correcto");
					return false;
				}
			}
		}
	}
	//select
	var selects = document.getElementsByTagName("select");
	if(selects!=null && selects.length>0){
		for(var i=0; i<selects.length; i++){
			var select = selects[i];
			var value = select.value;
			var must = getUlmust(select);
			var tip = getUltip(select);
			var type = getUltype(select);
			var len = getUllen(select);
			if(must==true && value==""){
				alert(tip+"No puede estar vacío, por favor, ayuda!");
				return false;
			}
			if(len!=-1){
				if(len<lengthOfString(value)){
					alert(tip+"La longitud de la entrada no puede ser superior"+len);
					return false;
				}
			}
			if(type=="INT"){
				if(must==true && value=="" && !isDigit(value)){
				 	alert(tip+"Valor debe introducirse en");
					return false;
				}
			}
			if(ultype=="DATE"){
				if(must==true && value=="" && !testSimpleDate(value)){
					//alert(msg);
				 	alert(ultip + "se debe introducir un formato de fecha válido \ n formato: yyyy-MM-dd");
					return false;
				}
			}
			if(type=="FLOAT"){
				try{					
					if(checkFloatNum(value)==false){
						alert(ultip + "entero o fraccionario, por favor rellene el correcto");
						return false;						
					}
					var f = parseFloat(value);
					if(f>99999999){
						alert(ultip + "no más de 99.999.999");
						return false;
					}
				}catch(e){
					alert(ultip + "entero o fraccionario, por favor rellene el correcto");
					return false;
				}
			}
		}
	}
	//alert(msg);
	return true;
}
function lengthOfString(str) {
	if(str==null){
		return 0;
	}
	var len = 0;
	for (var i = 0; i < str.length; i++) {
		if (str.charCodeAt(i) > 255) 
			len += 2; 
		else 
			len ++;
	}
	return len;
}
//取得控件自定义属性ultype的值，如果返回入参为null或者没有设置该属性则返回"STRING"字符串，如果设置了该属性且其值(大小写不敏感)为INT则返回字符串INT、为FLOAT则返回字符串FLOAT，否则一律返回字符串STRING
function getUltype(oElm){
	if(oElm==null){
		return "STRING";
	}
	var type = oElm.ultype;
	if(type==null || type=="undefined" || typeof(type)=="undefined"){
		return "STRING";
	}	
	type = type.toUpperCase();
	if(type=="INT"){
		return "INT";
	}if(type=="DATE"){
		return "DATE";
	}if(type=="DATETIME"){
		return "DATETIME";
	}else if(type=="FLOAT"){
		return "FLOAT";
	}else{
	 	return "STRING";	
	}
}
//取得控件自定义属性ultip的值，如果返回入参为null或者没有设置该属性则返回空串，如果设置了该属性则返回该属性的去除前后空白符后的字符串
function getUltip(oElm){
	if(oElm==null){
		return "";
	}
	var tip = oElm.ultip;
	if(tip==null || tip=="undefined" || typeof(tip)=="undefined"){
		return "";
	}	
	return tip;
}
//取得控件自定义属性ulmust的值，如果返回入参为null或者没有设置该属性则返回false，如果设置了该属性但不在true或false(大小写不敏感)范围之内则抛出异常，一切正常则返回一个布尔型的值
function getUlmust(oElm){
	if(oElm==null){
		return false;
	}
	var must = oElm.ulmust;
	if(must==null || must=="undefined" || typeof(must)=="undefined"){
		return false;
	}	
	must = must.toUpperCase();
	if(must=="TRUE"){
		return true;
	}else if(must=="FALSE"){
		return false;
	}else{
	 	throw new Error(oElm.name + "a medida ulmust atributos deben ser verdaderas o falsas");	
	}
}
//取得控件自定义属性ullen的值，如果返回-1则入参为null或没有设置该属性，如果设置了该属性但其值不是整数则抛出异常，一切正常则返回一个整数
function getUllen(oElm){
	if(oElm==null){
		return -1;
	}
	var len = oElm.ullen;
	if(len==null || len=="undefined" || typeof(len)=="undefined"){
		return -1;
	}	
	if(!isDigit(len)){
	 	throw new Error(oElm.name + "atributos personalizados Ullen debe ser un valor entero.");
	}
	return parseInt(len);
}
function lTrim(str){
	if(str.charAt(0)==" "){
		str = str.slice(1);
		str = lTrim(str);
	}
	return str;
}
function rTrim(str){
	var iLength;
	iLength = str.length;
	if(str.charAt(iLength-1)==" "){
		str = str.slice(0, iLength - 1);
		str = rTrim(str);
	}
	return str;
}
/*
 * @Name: trim(sNameOrIdOfElm)
 * @Description: 将页面上指定控件(通过id或name)的值进行空白检测后返回去除其值前后空白后的字符串值，适用的控件有input.text、input.radio、input.checkbox、select、textarea
 * @Param: 页面控件的id属性值或name属性值
 * @Return: input.text/input.radio/select/textarea返回控件的value或者innerText，input.checkbox返回第一个被选中的复选框的value；供选择的控件如果没有被选中返回空白串
 * @Author: Dzenall
 * @Since: 2010-3-1
 */
function trim(sNameOrIdOfElm){
	var oElm = document.all(sNameOrIdOfElm);
	var length = oElm.length
	if(typeof(length)=='undefined' || length==1 || length=="1"){
		var tagName = oElm.tagName.toUpperCase();
		if(tagName=="INPUT"){
			return lTrim(rTrim(oElm.value));
		}else if(tagName=="TEXTAREA"){
			return lTrim(rTrim(oElm.innerText));
		}
	}else{
		var tagName = "";
		var type = "";
		for(var i=0; i<length; i=i+1){
			if(i==0){
				tagName = oElm[i].tagName.toUpperCase();
				if(tagName=="INPUT"){					
					type = oElm[i].type.toUpperCase();
				}		
			}else{
				if(tagName!=oElm[i].tagName.toUpperCase()){
					throw(new Error(-1, "No es el mismo tipo de control!"))
				}
			}
		}
		if(tagName=="INPUT"){
			if(type=="CHECKBOX"){
				for(var i=0; i<length; i=i+1){
					if(oElm[i].checked=="checked" || oElm[i].checked=="CHECKED" || oElm[i].checked==true || oElm[i].checked=="true"){
						alert("["+lTrim(rTrim(oElm[i].value))+"]");
						return lTrim(rTrim(oElm[i].value));
					} 
				}
			}else if(type=="RADIO"){
				for(var i=0; i<length; i=i+1){
					if(oElm[i].checked=="checked" || oElm[i].checked=="CHECKED" || oElm[i].checked==true || oElm[i].checked=="true"){
						return lTrim(rTrim(oElm[i].value));
					} 
				}
			}
		}else if(tagName=="OPTION"){
			for(var i=0; i<length; i=i+1){
				if(oElm[i].selected=="selected" || oElm[i].selected=="SELECTED" || oElm[i].selected==true || oElm[i].selected=="true"){
					return lTrim(rTrim(oElm[i].value));
				} 
			}
		}
	}
	return "";	
}

//验证如果dateString不为空串的时候的格式为yyyy-MM-dd格式，为空返回true，不为空时匹配则返回true、不匹配则返回false
function testSimpleDate(dateString){
	var patrn = /^((((19|20)\d{2})-(0?[13578]|1[02])-(0?[1-9]|[12]\d|3[01]))|(((19|20)\d{2})-(0?[469]|11)-(0?[1-9]|[12]\d|30))|(((19|20)\d{2})-0?2-(0?[1-9]|1\d|2[0-8]))|((((19|20)([13579][26]|[2468][048]|0[48]))|(2000))-0?2-(0?[1-9]|[12]\d)))$/;
	if (!patrn.exec(dateString)){
		return false;
	}else{
		return true;
	}
}
//验证digitString是否是正整数，如果是则返回true，否则返回false
function isDigit(digitString){
	var patrn=/^[0-9]{1,20}$/;   
	if (!patrn.exec(digitString)){
		return false;
	}else{
		return true;
	}
}  
/*------------------------ 
功能：检测是否是有效全格式日期yyyy-MM-dd hh:mm:ss,未完成
-------------------------*/ 
function isDateTime(strDate){ /*
	strDate = (lTrim(rTrim((strDate)))); 
	if(strDate.length==0) 
		return (false); 
	reVal = ((^((1[8-9]\d{2})|([2-9]\d{3}))([-\/\._])(10|12|0?[13578])([-\/\._])(3[01]|[12][0-9]|0?[1-9])$)|(^((1[8-9]\d{2})|([2-9]\d{3}))([-\/\._])(11|0?[469])([-\/\._])(30|[12][0-9]|0?[1-9])$)|(^((1[8-9]\d{2})|([2-9]\d{3}))([-\/\._])(0?2)([-\/\._])(2[0-8]|1[0-9]|0?[1-9])$)|(^([2468][048]00)([-\/\._])(0?2)([-\/\._])(29)$)|(^([3579][26]00)([-\/\._])(0?2)([-\/\._])(29)$)|(^([1][89][0][48])([-\/\._])(0?2)([-\/\._])(29)$)|(^([2-9][0-9][0][48])([-\/\._])(0?2)([-\/\._])(29)$)|(^([1][89][2468][048])([-\/\._])(0?2)([-\/\._])(29)$)|(^([2-9][0-9][2468][048])([-\/\._])(0?2)([-\/\._])(29)$)|(^([1][89][13579][26])([-\/\._])(0?2)([-\/\._])(29)$)|(^([2-9][0-9][13579][26])([-\/\._])(0?2)([-\/\._])(29)$));
	return (reVal.test(strDate));*/
	return true; 
} 
/*将合法的日期字符串格式化为标准全格式 yyyy-MM-dd hh:mm:ss 并返回之*/
function formatToFullDateTime(id){
	var msg = "";
	var dateStr = lTrim(rTrim(fDbc2Sbc(document.getElementById(id).value)));
	msg += "dateStr="+dateStr;
	var tDate = new Date();
	tDate.setDate(1);// 设置 Date 对象中月的某一天 (1 ~ 31)。 
	tDate.setMonth(0);// 设置 Date 对象中月份 (0 ~ 11)。 
	tDate.setFullYear(2000);// 设置 Date 对象中的年份（四位数字）。(>1600) 
	tDate.setHours(0);// 设置 Date 对象中的小时 (0 ~ 23)。 
	tDate.setMinutes(0);// 设置 Date 对象中的分钟 (0 ~ 59)。 
	tDate.setSeconds(0);// 设置 Date 对象中的秒钟 (0 ~ 59)。 
	tDate.setMilliseconds(0);// 设置 Date 对象中的毫秒 (0 ~ 999)。 
	if(dateStr==""){
		document.getElementById(id).value="";
		alert(msg);
		return false;
	}
	var dateVtime = dateStr.split(" ");
	var date = lTrim(rTrim(dateVtime[0]));
	msg += "date="+date;
	var time = lTrim(rTrim(dateVtime[1]));
	if(date!=""){
		var ymd = date.split(new RegExp("[.|-|/]"));
		tDate.setFullYear(ymd[0]);
		tDate.setMonth(parseInt(ymd[1])-1);
		tDate.setDate(parseInt(ymd[2]));
		if(time!=""){
			if(time.length>1 && time.substring(0,1)===":"){
				time = time.substring(1, time.length);
			}
			msg += "time="+time;
			var hms = time.split(new RegExp("[.|:|/]"));
			if(hms!=null){
				if(hms.length>=1){//hh
					try{
						tDate.setHours(hms[0]);
						msg += "setHours="+tDate.getHours();
					}catch(e){}
				
				}
				if(hms.length>=2){//mm
					try{
						tDate.setMinutes(hms[1]);
						msg += "setMinutes="+tDate.getMinutes();
					}catch(e){}
				
				}
				if(hms.length>=3){//ss
					try{
						tDate.setSeconds(hms[2]);
						msg += "setSeconds="+tDate.getSeconds();
					}catch(e){}
				}
			}
		}				
		var year = tDate.getFullYear();
		var month = tDate.getMonth()+1;
		var day = tDate.getDate();
		var hour = tDate.getHours();
		var minute = tDate.getMinutes();
		var second = tDate.getSeconds();
		
		dateStr=year
			+ "-" + ((month<10)?("0"+month):(""+month))
			+ "-" + ((day<10)?("0"+day):(""+day))
			+ " " + ((hour<10)?("0"+hour):(""+hour))
			+ ":"+ ((minute<10)?("0"+minute):(""+minute))
			+ ":"+ ((second<10)?("0"+second):(""+second));		
		document.getElementById(id).value=dateStr;
		msg += "dateStr="+dateStr;
		return true;
	}
	return false;
}
/*全角转半角*/
function fDbc2Sbc(str){
	var result = '';
	for(i=0; i<str.length; i++){
		code = str.charCodeAt(i);//获取当前字符的unicode编码
		if(code>=65281 && code<=65373){//在这个unicode编码范围中的是所有的英文字母已经各种字符  
			result += String.fromCharCode(str.charCodeAt(i) - 65248);//把全角字符的unicode编码转换为对应半角字符的unicode码
		}else if(code == 12288){//空格  
			result += String.fromCharCode(str.charCodeAt(i) - 12288 + 32);
		}else{
			result += str.charAt(i);
		}
	}
	return result;
}
function textInputLimit(){
	var msg = "FUNCTION textInputLengthLimit()";
	var inputs = document.getElementsByTagName("input");
	var count = inputs.length;
	msg += "\nlength of inputs is "+count;
	if(count<=0){
		//alert(msg+"\nnone input taget exsits, process ends.");
		return;
	}
	for(var i=0; i<count; i++){
		var input = inputs[i];
		if(input.type.toUpperCase()=="TEXT"){
			msg += "\ninputs["+i+"]:"+input.name+" is the text type, processing now...";
			var des = input.des;//控件文字性描述
			var eff = input.eff;//控件有效位数
			var dec = input.dec;//控件小数位数
			var nul = input.nul;//控件是否必须录入
			var val = lTrim(rTrim(input.value));
			if(val==""){//录入为空
				if(nul=="1"){
					alert(des+"No puede estar vacío, por favor llene!");
					//alert(msg);
					return false;
				}
			}else{//录入非空
				var length = val.length;
				if(length>eff){//长度检测
					alert(des + "ya que" + eff + ", por favor consulte!");
					//alert(msg);
					return false;
				}
			}
		}else{
			msg += "\ninputs["+i+"]:"+input.name+" is not the text type, process continue.";
		}
	}
	//alert(msg);
}
function textareaInputLimit(){
	var msg = "FUNCTION textareaInputLimit()";
	var textareas = document.getElementsByTagName("textarea");
	var count = textareas.length;
	msg += "\nlength of textareas is "+count;
	if(count<=0){
		//alert(msg+"\nnone textareas taget exsits, process ends.");
		return;
	}
	for(var i=0; i<count; i++){
		var textarea = textareas[i];
		msg += "\ntextareas["+i+"]:"+textarea.name+" is the textarea type, processing now...";
		var des = textarea.des;//控件文字性描述
		var eff = textarea.eff;//控件有效位数
		var dec = textarea.dec;//控件小数位数
		var nul = textarea.nul;//控件是否必须录入
		var val = lTrim(rTrim(textarea.innerText));
		if(val==""){//录入为空
			if(nul=="1"){
				alert(des+"No puede estar vacío, por favor llene!");
				//alert(msg);
				return false;
			}
		}else{//录入非空
			var length = val.length;
			if(length>eff){//长度检测
				alert(des + "ya que" + eff + ", por favor consulte!");
				//alert(msg);
				return false;
			}
		}
	}
	//alert(msg);
}