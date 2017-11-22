function alertMessage(errorCode){	
	alert(getMessage(errorCode,"",""));
}

function alertMessage(errorCode,arg1,arg2){	
	alert(getMessage(errorCode,arg1,arg2));
}

function confirmMessage(errorCode){	
	confirm(getMessage(errorCode,"",""));
}

function confirmMessage(errorCode,arg1,arg2){	
	confirm(getMessage(errorCode,arg1,arg2));
}

function getMessage(errorCode,arg1,arg2){	
	return " " + msgMgr(errorCode).replace("&1",arg1).replace("&2",arg2);
}

function showMessage(msgId, msgString, msgType){
	if(msgString != ""){
		if(msgType == 0){
			confirm(msgString);
		} else {
			alert(msgString);
		}		
	}else if(msgId != ""){
		if(msgType == 0){
			confirmMessage(msgId, "", "");
		} else {
			alertMessage(msgId, "", "");
		}		
	}	
}

function msgMgr(errorCode){
	var msg = errorCode;
	switch (errorCode){

case "10001" : msg = "\u8bf7\u9009\u62e9\u6a21\u5757\uff01"; break;
case "10002" : msg = "\u8bf7\u9009\u62e9\u89d2\u8272\uff01"; break;
case "10003" : msg = "\u6ca1\u6709\u83dc\u5355\u53ef\u4ee5\u9009\u62e9\uff01"; break;
case "10004" : msg = "\u8bf7\u9009\u62e9\u7528\u6237\uff01"; break;
case "10005" : msg = "\u8bf7\u9009\u62e9\u4f53\u68c0ID\uff01"; break;
case "10006" : msg = "\u53ea\u80fd\u9009\u62e9\u4e00\u6761\u6570\u636e\uff01"; break;

	}
	return  msg;
}


