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

case "10001" : msg = "Por favor, seleccione el m�dulo!"; break;
case "10002" : msg = "Por favor, seleccione un papel!"; break;
case "10003" : msg = "No hay men� para elegir!"; break;
case "10004" : msg = "Por favor, seleccione el usuario!"; break;
case "10005" : msg = "Por favor, seleccione el ID de m�dicos!"; break;
case "10006" : msg = "Los datos s�lo se puede elegir!"; break;

	}
	return  msg;
}


