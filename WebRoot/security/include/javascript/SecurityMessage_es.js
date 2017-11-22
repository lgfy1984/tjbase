function alertSecurityMessage(errorCode){	
	alert(getSecurityMessage(errorCode,"",""));
}

function alertSecurityMessage(errorCode,arg1,arg2){	
	alert(getSecurityMessage(errorCode,arg1,arg2));
}

function confirmSecurityMessage(errorCode){	
	confirm(getSecurityMessage(errorCode,"",""));
}

function confirmSecurityMessage(errorCode,arg1,arg2){	
	confirm(getSecurityMessage(errorCode,arg1,arg2));
}

function getSecurityMessage(errorCode,arg1,arg2){	
	return " " + msgSecurityMgr(errorCode).replace("&1",arg1).replace("&2",arg2);
}

function showSecurityMessage(msgId, msgString, msgType){
	if(msgString != ""){
		if(msgType == 0){
			confirm(msgString);
		} else {
			alert(msgString);
		}		
	}else if(msgId != ""){
		if(msgType == 0){
			confirmSecurityMessage(msgId, "", "");
		} else {
			alertSecurityMessage(msgId, "", "");
		}		
	}	
}

function msgSecurityMgr(errorCode){
	var msg = errorCode;
	switch (errorCode){

case "10001" : msg = "Por favor, seleccione el módulo"; break;
case "10002" : msg = "Por favor, seleccione un papel!"; break;
case "10003" : msg = "No hay menú para elegir!"; break;
case "10004" : msg = "Por favor, seleccione el usuario!"; break;
case "10005" : msg = "Por favor, introduzca el código de categoría!"; break;
case "10006" : msg = "Por favor, introduzca el nombre de la categoría!"; break;
case "10007" : msg = "Por favor, introduzca el código del módulo!";break;
case "10008" : msg = "Por favor, introduzca el nombre del módulo!";break;
case "10009" : msg = "Por favor, introduzca una descripción del módulo!";break;

case "10010" : msg = "Por favor introduce el papel del código!";break;
case "10011" : msg = "Por favor, introduzca el nombre de la función!";break; 
 
case "10013" : msg = "Por favor, introduzca el nombre del menú!";break;
case "10014" : msg = "Por favor, introduzca el ID de menú principal!";break;
case "10015" : msg = "Por favor introduce el contenido del menú!";break;
case "10016" : msg = "Por favor, introduzca la bandera último nodo!";break;
case "10017" : msg = "Por favor, introduzca un nivel!";break;

	}
	return  msg;
}


