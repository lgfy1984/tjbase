  // JavaScript Document
// JavaScript Document
var arrOptions = new Array();//弹出层的行数据
var ids = new Array();
var strLastValue = "";
var theTextBox; //输入字符的控件
var values = {};
var currentValueSelected = -1; //当前选择弹出层的行数
var mouseFlag = 0; //标识鼠标是否点击弹出层上的行，用来防止控件失去焦点时未将值放入控件就隐藏了弹出层
function GiveOptions(e, url, ssid, hiddenid) {
	var eve = window.event ? window.event : (e ? e : null);
	var intKey = eve.keyCode || eve.which;
	theTextBox = eve.srcElement ? eve.srcElement : eve.target;
	ids = new Array(theTextBox.id);
	if(hiddenid instanceof Array){//如果是数组类型，即多个隐藏输入框的id数组
		ids = ids.concat(hiddenid);
	}else{
		ids.push(hiddenid);
	}
	if (window.attachEvent) {
		theTextBox.attachEvent("onblur", checkMouse);
	} else {
		theTextBox.addEventListener("blur", checkMouse, false);
	}
	if (theTextBox.value.length == 0) {
		clearText();
		HideTheBox();
		strLastValue = "";
		return false;
	}
	if (intKey == 13) {
		if (arrOptions.length != 0) {
			GrabHighlighted();
			return false;
		}
	} else {
		if (intKey == 38) {
			MoveHighlight(-1);
			return false;
		} else {
			if (intKey == 40) {
				MoveHighlight(1);
				return false;
			}
		}
	}
	if (values[theTextBox.id] == null) {
		values[theTextBox.id] == theTextBox.value;
	}
	if (theTextBox.value.indexOf(strLastValue) != 0 || arrOptions.length == 0 || (strLastValue.length == 0 && theTextBox.value.length > 0) || (theTextBox.value.length != strLastValue.length)) {
		if (values[theTextBox.id] != theTextBox.value) {
			clearText();
			values[theTextBox.id] = theTextBox.value;
		}
		arrOptions = new Array();
		SetElementPosition();
		document.getElementById("spanOutput").innerHTML = "<font style='color:#a0a0a0'>\u6b63\u5728\u7528\u5173\u952e\u8bcd\u641c\u7d22\uff0c\u8bf7\u7a0d\u7b49...</font>";
		strLastValue = theTextBox.value;
		TypeAhead(url, theTextBox.value, ssid);
	} else {
		SetElementPosition();
		BuildList(theTextBox.value);
		strLastValue = theTextBox.value;
	}
}
function TypeAhead(url, xStrText, ssid) {
	if (window.XMLHttpRequest) {
		req = new XMLHttpRequest();
	} else {
		if (window.ActiveXObject) {
			req = new ActiveXObject("Microsoft.XMLHTTP");
		}
	}
	if (req) {
		//
	    //req.setRequestHeader("Cache-Control","no-cache");
		req.onreadystatechange = callback;
		req.open("Post", url, true);
		req.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
		var param = "ssid=" + ssid + "&param=" + xStrText;
		req.send(param);
	}
}
function callback() {
	if (req.readyState == 4) {
		if (req.status == 200) {
			parseMessage();

                 // update the HTML DOM based on whether or not message is valid
		} else {
			alert("\u670d\u52a1\u8c03\u7528\u5931\u8d25" + req.statusText);
		}
	} else {
	}
}
function parseMessage() {
	var xmlDoc = req.responseXML.documentElement;
	if (xmlDoc) {
		var node = xmlDoc.getElementsByTagName("info");
		arrOptions = new Array();
		for (var i = 0; i < node.length; i++) {
			var temparr = new Array();
			for (var j = 0; j < node[i].childNodes.length; j++) {
				if ("hidden" == node[i].childNodes[j].nodeName) {
					temparr[j] = "<td style='display:none'>" + node[i].childNodes[j].firstChild.nodeValue + "</td>";
				} else {
					temparr[j] = node[i].childNodes[j].firstChild.nodeValue;
				}
			}
			arrOptions[i] = temparr;
		}
		BuildList(theTextBox.value);
		strLastValue = theTextBox.value;
	} else {
		document.getElementById("spanOutput").innerHTML = "<font style='color:#a0a0a0'>\u5bf9\u4e0d\u8d77\uff0c\u670d\u52a1\u8c03\u7528\u5931\u8d25\uff01</font>";
	}
}
function BuildList(theText) {
	var inner = "<table id='buildTable' width='100%'>";
	inner += "<tbody>";
	var theMatches = MakeMatches(theText);
	for (var i = 0; i < theMatches.length; i++) {
		inner += theMatches[i];
	}
	inner += "</tbody></table>";
	if (theMatches.length > 0) {
		document.getElementById("spanOutput").innerHTML = inner;
		document.getElementById("OptionsList_0").style.backgroundColor = "#f0f0f0";
		currentValueSelected = 0;
	} else {
		document.getElementById("spanOutput").innerHTML = "<font style='color:#a0a0a0'>\u672a\u627e\u5230\u76f8\u5e94\u641c\u7d22\u7ed3\u679c\uff01\u8bf7\u91cd\u65b0\u8f93\u5165</font>";
	}
}
function SetElementPosition() {
	var selectedPosX = 0;
	var selectedPosY = 0;
	var theElement = theTextBox;
	if (!theElement) {
		return;
	}
	var theElemHeight = theElement.offsetHeight;
	var theElemWidth = theElement.offsetWidth;
	while (theElement != null) {
		selectedPosX += theElement.offsetLeft;
		selectedPosY += theElement.offsetTop;
		theElement = theElement.offsetParent;
	}
	xPosElement = document.getElementById("spanOutput");
	xPosElement.style.left = selectedPosX + "px";
	xPosElement.style.width = theElemWidth + "px";
	xPosElement.style.top = selectedPosY + theElemHeight + "px";
	xPosElement.style.display = "block";
}
var countForId = 0;
function MakeMatches(xCompareStr) {
	countForId = 0;
	var matchArray = new Array();
	for (var i = 0; i < arrOptions.length; i++) {
		var regExp = new RegExp(xCompareStr, "ig");
		matchArray[matchArray.length] = CreateUnderline(arrOptions[i], xCompareStr, i);
	}
	return matchArray;
}
function isMatch(arr, regExp) {
	for (var i = 0; i < arr.length; i++) {
		if (arr[i].search(regExp) >= 0) {
			return true;
		}
	}
	return false;
}
var redStart = "<font style='color:red'>";
var redEnd = "</font>";
var selectSpanStart = "<tr onmouseover='SetHighColor(this); mouseFlag=1' onmouseout='mouseFlag=0' ";
var selectSpanEnd = "</tr>";
function CreateUnderline(strArr, xTextMatch, xVal) {
	selectSpanMid = "onclick='SetText(" + xVal + ")'" + " id='OptionsList_" + countForId + "' theArrayNumber='" + xVal + "'>";
	countForId++;
	var regExp = new RegExp(xTextMatch, "ig");
	var Replacestr = "";
	for (var i = 0; i < strArr.length; i++) {
		if (strArr[i].substring(0, 25) == "<td style='display:none'>") {
			Replacestr += strArr[i];
		} else {
			var start = strArr[i].search(regExp);
			var matchedText = strArr[i].substring(start, start + xTextMatch.length);
			Replacestr += "<td>" + strArr[i].replace(regExp, redStart + matchedText + redEnd) + "</td>";
		}
	}
	return selectSpanStart + selectSpanMid + Replacestr + selectSpanEnd;
}
function SetHighColor(theTr) {
	for (var i = 0; i < countForId; i++) {
		document.getElementById("OptionsList_" + i).style.backgroundColor = "";
	}
	if (theTr) {
		currentValueSelected = theTr.id.slice(theTr.id.indexOf("_") + 1, theTr.id.length);
	}
	document.getElementById("OptionsList_" + currentValueSelected).style.backgroundColor = "#f0f0f0";
	document.getElementById("OptionsList_" + currentValueSelected).style.cursor = "hand";
}
function SetText(xVal) {
	for (var i = 0; i < ids.length; i++) {
		if (ids[i] != null && ids[i] != "") {
			if (arrOptions[xVal][i].length > 25 && arrOptions[xVal][i].substring(0, 25) == "<td style='display:none'>") {
				document.getElementById(ids[i]).value = arrOptions[xVal][i].substring(25, arrOptions[xVal][i].length - 5);
			} else {
				document.getElementById(ids[i]).value = arrOptions[xVal][i];
			}
		}
	}
	document.getElementById("spanOutput").style.display = "none";
	currentValueSelected = -1; //remove the selected index
	values[theTextBox.id] = theTextBox.value;
	theTextBox.focus();
}
function GrabHighlighted() {
	if (currentValueSelected >= 0) {
		xVal = document.getElementById("OptionsList_" + currentValueSelected).getAttribute("theArrayNumber");
		SetText(xVal);
		HideTheBox();
	}
}
function clearText() {
	for (var i = 0; i < ids.length; i++) {
		if (ids[i] != null && ids[i] != "" && ids[i] != theTextBox.id) {
			document.getElementById(ids[i]).value = "";
		}
	}
}
function checkMouse() {
	if (mouseFlag == 1) {
		return false;
	} else {
		HideTheBox();
	}
}
function HideTheBox() {
	document.getElementById("spanOutput").style.display = "none";
	arrOptions = new Array();
	ids = new Array();
	currentValueSelected = -1;
}
function MoveHighlight(xDir) {
	if (currentValueSelected >= 0) {
		newValue = parseInt(currentValueSelected) + parseInt(xDir);
		if (newValue > -1 && newValue < countForId) {
			currentValueSelected = newValue;
			SetHighColor(null);
		} else {
			if (newValue == -1) {
				currentValueSelected = countForId - 1;
				SetHighColor(null);
			} else {
				if (newValue == countForId) {
					currentValueSelected = 0;
					SetHighColor(null);
				}
			}
		}
	}
}
function getNextInput(input) {
	var fs=document.forms;
	for (var x = 0; x < fs.length; x++) {
		var form = fs[x];
		for (var i = 0; i < form.elements.length; i++) {
			if (form.elements[i] == input) {
				break;
			}
		}
		while (true) {
			if (i++ < form.elements.length) {
				if (form.elements[i]&&form.elements[i].type != "hidden") {
					return form.elements[i];
				}
			} else {
				return null;
			}
		}
	}
}
function huanhang(e) {
	var eve = window.event ? window.event : (e ? e : null);
	var intKey = eve.keyCode || eve.which;
	var txt = eve.srcElement ? eve.srcElement : eve.target;
	if (intKey==13) {
		if (currentValueSelected == -1) {
			getNextInput(txt).focus();
		}
		if(window.event){
			eve.keyCode=0;
		}else{
			return false;
		}
	}
}

