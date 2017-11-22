function alertDexcMessage(errorCode){	
	alert(getDexcMessage(errorCode,"",""));
}

function alertDexcMessage(errorCode,arg1,arg2){	
	alert(getDexcMessage(errorCode,arg1,arg2));
}

function confirmDexcMessage(errorCode){	
	confirm(getDexcMessage(errorCode,"",""));
}

function confirmDexcMessage(errorCode,arg1,arg2){	
	confirm(getDexcMessage(errorCode,arg1,arg2));
}

function getDexcMessage(errorCode,arg1,arg2){	
	return " " + msgDexcMgr(errorCode).replace("&1",arg1).replace("&2",arg2);
}

function showDexcMessage(msgId, msgString, msgType){
	if(msgString != ""){
		if(msgType == 0){
			confirm(msgString);
		} else {
			alert(msgString);
		}		
	}else if(msgId != ""){
		if(msgType == 0){
			confirmDexcMessage(msgId, "", "");
		} else {
			alertDexcMessage(msgId, "", "");
		}		
	}	
}

function msgDexcMgr(errorCode){
	var msg = errorCode;
	switch (errorCode){

case "10001" : msg = "\u8bf7\u9009\u62e9\u6a21\u5757\uff01"; break;
case "10002" : msg = "\u8bf7\u9009\u62e9\u89d2\u8272\uff01"; break;
case "10003" : msg = "\u6ca1\u6709\u83dc\u5355\u53ef\u4ee5\u9009\u62e9\uff01"; break;
case "10004" : msg = "\u8bf7\u9009\u62e9\u7528\u6237\uff01"; break;
case "10005" : msg = "\u8bf7\u8f93\u5165\u7c7b\u522b\u4ee3\u7801\uff01"; break;
case "10006" : msg = "\u8bf7\u8f93\u5165\u7c7b\u522b\u540d\u79f0\uff01"; break;
case "10007" : msg = "\u8bf7\u8f93\u5165\u6a21\u5757\u4ee3\u7801\uff01";break;
case "10008" : msg = "\u8bf7\u8f93\u5165\u6a21\u5757\u540d\u79f0\uff01";break;
case "10009" : msg = "\u8bf7\u8f93\u5165\u6a21\u5757\u63cf\u8ff0\uff01";break;

case "10010" : msg = "\u8bf7\u8f93\u5165\u89d2\u8272\u4ee3\u7801\uff01";break;
case "10011" : msg = "\u8bf7\u8f93\u5165\u89d2\u8272\u540d\u79f0\uff01";break; 
 
case "10013" : msg = "\u8bf7\u8f93\u5165\u83dc\u5355\u540d\u79f0!";break;
case "10014" : msg = "\u8bf7\u8f93\u5165\u7236\u7c7b\u83dc\u5355ID\uff01";break;
case "10015" : msg = "\u8bf7\u8f93\u5165\u83dc\u5355\u5185\u5bb9!";break;
case "10016" : msg = "\u8bf7\u8f93\u5165\u672b\u8282\u70b9\u6807\u5fd7\uff01";break;
case "10017" : msg = "\u8bf7\u8f93\u5165\u7ea7\u522b\uff01";break;

	}
	return  msg;
}


