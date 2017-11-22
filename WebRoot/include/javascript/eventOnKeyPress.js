function setItemFocus(i_item){
	str="document.form."+i_item+".type.toLowerCase()";
	str=eval(str);
	switch(str) {
		case"text":
			str="document.form."+i_item+".focus()";
			str=eval(str);
			str="document.form."+i_item+".select()";
			str=eval(str);
			break;

		case"password":
			str="document.form."+i_item+".focus()";
			str=eval(str);
			str="document.form."+i_item+".select()";
			str=eval(str);
			break;

		case"select-one":
			str="document.form."+i_item+".focus()";
			str=eval(str);
			break;
		
		case"radio":
			str="document.form."+i_item+".focus()";
			str=eval(str);
			break;
			
		case"select-multiple":case"radio":case"file":
		case"textarea":
			str="document.form."+i_item+".focus()";
			str=eval(str);
			str="document.form."+i_item+".select()";
			str=eval(str);
			break;

		case"button":
			str="document.form."+i_item+".focus()";
			str=eval(str);
			//str="document.form."+i_item+".select()";
			//str=eval(str);
			return false;
			break;

		case"checkbox":
			str="document.form."+i_item+".focus()";
			str=eval(str);			
			break;
		case"submit":
			return true;
			break;
	}
}
function __firefox(){
	if(HTMLElement.prototype.__defineGetter__){
		HTMLElement.prototype.__defineGetter__("runtimeStyle", __element_style);
	}
	if(window.constructor.prototype.__defineGetter__){
		window.constructor.prototype.__defineGetter__("event", __window_event);
	}
	if(Event.prototype.__defineGetter__){
		Event.prototype.__defineGetter__("srcElement", __event_srcElement);
	}
}   
function __element_style(){   
    return this.style;   
}   
function __window_event(){   
    return __window_event_constructor();   
}   
function __event_srcElement(){   
    return this.target;   
}   
function __window_event_constructor(){   
    if(document.all){   
        return window.event;   
    }   
    var _caller = __window_event_constructor.caller;   
    while(_caller!=null){   
        var _argument = _caller.arguments[0];   
        if(_argument){   
            var _temp = _argument.constructor;   
            if(_temp.toString().indexOf("Event")!=-1){   
                return _argument;   
            }   
        }   
        _caller = _caller.caller;    
    }   
    return null;   
}   
if(window.addEventListener){   
    __firefox();   
} 
function eventOnKeyPress(i_item){

	if (window.event.keyCode==13 || window.event.keyCode==10){
		window.event.keyCode=0;
		setItemFocus(i_item);
	} 
}

function eventOnMouseDown(i_item, i_nextitem){
	str="document.form."+i_item+".checked = true";
	str=eval(str);
	setItemFocus(i_nextitem);	
}

function eventOnCheckBoxBlur(i_checkBox, i_item){
	str="document.form."+i_checkBox+".checked";
	str=eval(str);
	if(str){
		str="document.form."+i_item+".value='1'";
		str=eval(str);	
	} else {
		str="document.form."+i_item+".value='0'";
		str=eval(str);	
	}
}

function formDisabled(tForm, setFlg){
	var objArray = tForm.elements;
	for(i = 0; i < objArray.length; i++){
		objArray[i].disabled = setFlg;
	}
}

function setFirstFocus(tForm){
	var inputList = tForm.elements;
	for(i = 0; i < inputList.length; i++){
		if(inputList[i].type != "hidden"){
			if(!inputList[i].disabled && !inputList[i].readOnly){
				inputList[i].focus();
				break;
			}			
		}
	}
}

function pageSetFocus(focusId){
	if(focusId != ""){
		setItemFocus(focusId);
	} else {
		setFirstFocus(document.form);
	}
}


function keyDisabled(){
	if (window.event.ctrlKey && window.event.keyCode == 86)	{
		window.event.returnValue = false;
	}
}

function eventOnKeyInteger(z_item, dotFlg)
{
	var keyArray = new Array(48,		// 0
							 49,		// 1
							 50,		// 2
							 51,		// 3
							 52,		// 4
							 53,		// 5
							 54,		// 6
							 55,		// 7
							 56,		// 8
							 57,		// 9
							 8,			// BackSpace
							 9,			// Tab
							 46,		// Delete
							 39,		// Right
							 37,		// Left
							 36,		// Home
							 35,		// End
							 27,		// ESC
							 13,		// Enter
							 96,		// 0
							 97,		// 1
							 98,		// 2
							 99,		// 3
							 100,		// 4
							 101,		// 5
							 102,		// 6
							 103,		// 7
							 104,		// 8
							 105,		// 9
							 144,		// NumLock
							 91,		// Window Menu
							 92,		// Window Menu
							 93			// Mouse Right Key Menu
							 )

	var keyFlg = false;
	var temp = "document.form." + z_item + ".value";
	temp = eval(temp);
	
	// .
	if((window.event.keyCode == 190 || window.event.keyCode == 110)&&!window.event.shiftKey) {
		if(!dotFlg){
			window.event.returnValue = false;
			return;
		}
		if(temp.search(/\./) == -1 && temp != ""){
			return;
		} else {
			window.event.returnValue = false;
			return;
		}
	}
	for(i = 0; i < keyArray.length; i++){
		if (window.event.keyCode == keyArray[i]&&!window.event.shiftKey){
			keyFlg = true;
			break;
		}
	}

	if(!keyFlg){
		window.event.returnValue = false;
	}
}

function eventOnKeyEnter(z_item)
{
	var keyArray = new Array(10
							,13		// Enter
							 )

	var keyFlg = false;
	var temp = "document.form." + z_item + ".value";
	temp = eval(temp);
	
	for(i = 0; i < keyArray.length; i++){
		if (window.event.keyCode == keyArray[i]&&!window.event.shiftKey){
			keyFlg = true;
			break;
		}
	}

	if(!keyFlg){
		window.event.returnValue = false;
	}
}

function eventOnKeyDate(z_item){
	if (window.event.keyCode == 191 && !window.event.shiftKey){
		window.event.returnValue = true;
		return;
	} else {
		eventOnKeyInteger(z_item, false);
	}
}


function getNumberValue(z_item){
	temp = "document.form." + z_item + ".value";
	temp = eval(temp);
	strDot = ",";
	strEmp = "";
	n = temp.search(strDot);
	while(n >= 0){
		n = temp.search(strDot);
		if (n > -1){
			temp = temp.replace(strDot,strEmp);
		}
	}
	return temp;
}

function eventOnNumberFocus(z_item){
	temp = "document.form." + z_item + ".value";
	temp = eval(temp);
	strDot = ",";
	strEmp = "";
	n = temp.search(strDot);
	while(n >= 0){
		n = temp.search(strDot);
		if (n > -1){
			temp = temp.replace(strDot,strEmp);
		}
	}
	str = "document.form." + z_item + ".value = temp" ;
	str = eval(str);
	setItemFocus(z_item);
}
function eventOnNumberOnly(z_item){
	temp = "document.form." + z_item + ".value";
	temp = eval(temp);
	strDot = ",";
	strEmp = "";
	n = temp.search(strDot);
	while(n >= 0){
		n = temp.search(strDot);
		if (n > -1){
			temp = temp.replace(strDot,strEmp);
		}
	}
	str = "document.form." + z_item + ".value = temp" ;
	str = eval(str);
}

function eventOnNumberBlur(z_item){
	str = "document.form." + z_item + ".value" ;
	str = eval(str);
	if(str == ""){
		str = "document.form." + z_item + ".value = 0" ;
		str = eval(str);
	}
	temp = getNumberValue(z_item);
	temp = eval(temp);
	num = new Number(temp);
	itemStr = num.toString();
	intStr = "";
	dotStr = "";
	minusStr = "";	
	len = itemStr.length;
	minus_flag = "-";
	dot_flag = ".";

	n = itemStr.search(",");
	while(n >= 0){	
		n = itemStr.search(",");
		if (n >= 0){
			itemStr = itemStr.replace(",","");
		}
	}
	if (isNaN(itemStr)){	
		str = "document.form." + z_item + ".value = " + temp;
		str = eval(str);
		return;
	}
	if(itemStr.indexOf(minus_flag) > -1){
		minusStr = minus_flag;
		itemStr = itemStr.substring(1, itemStr.length);
	}
	
	var minusNum = itemStr.indexOf(dot_flag);
	if(minusNum > 0){		
		intStr = itemStr.substring(0, minusNum);
		dotStr = itemStr.substring(minusNum + 1, itemStr.length);
		if(dotStr.length > 0 ){
	        dotStr = dot_flag + dotStr;
	    }
	} else {
		intStr = itemStr
	}
	var intLen = intStr.length;
    var head = "";
    var leftStr = "";
    if(intLen % 3 == 1){
        head = intStr.substring(0, 1);
        leftStr = intStr.substring(1,intStr.length);
    } else if(intLen % 3 == 2){
        head = intStr.substring(0, 2);
        leftStr = intStr.substring(2,intStr.length);
    } else {
        leftStr = intStr;
    }
    
    n = leftStr.length;
    if(n > 0 &&  head.length > 0){
        head = head + ",";
    }
    var temp = "";    
    while(n > 0){	
        temp = temp + leftStr.substring(0,3);
        leftStr = leftStr.substring(3,leftStr.length);
        n = leftStr.length;
        if(n > 0){
            temp = temp + ",";
        }
    }
    var finalStr = minusStr + head + temp + dotStr;
    str = "document.form." + z_item + ".value = finalStr";
	str = eval(str);   
}
