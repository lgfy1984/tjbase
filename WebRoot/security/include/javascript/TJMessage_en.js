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

case "10001" : msg = "Please select the module!"; break;
case "10002" : msg = "Please select a role!"; break;
case "10003" : msg = "No menu to choose from!"; break;
case "10004" : msg = "Please select the user!"; break;
case "10005" : msg = "Please select the medical ID!"; break;
case "10006" : msg = "Data can only choose one!"; break;

	}
	return  msg;
}


