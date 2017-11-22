function alertCommMessage(errorCode){	
	alert(getCommMessage(errorCode,"",""));
}

function alertCommMessage(errorCode,arg1,arg2){	
	alert(getCommMessage(errorCode,arg1,arg2));
}

function confirmCommMessage(errorCode){	
	confirm(getCommMessage(errorCode,"",""));
}

function confirmCommMessage(errorCode,arg1,arg2){	
	confirm(getCommMessage(errorCode,arg1,arg2));
}

function getCommMessage(errorCode,arg1,arg2){	
	return " " + msgCommMgr(errorCode).replace("&1",arg1).replace("&2",arg2);
}

function showCommMessage(msgId, msgString, msgType){
	if(msgString != ""){
		if(msgType == 0){
			confirm(msgString);
		} else {
			alert(msgString);
		}		
	}else if(msgId != ""){
		if(msgType == 0){
			confirmCommMessage(msgId, "", "");
		} else {
			alertCommMessage(msgId, "", "");
		}		
	}	
}

function msgCommMgr(errorCode){
	var msg = errorCode;
	switch (errorCode){
//---请输入医疗机构编码
case "10001" : msg = "Por favor introduzca la codificaci\u00F3n de m\u00E9dico"; break;
//---请输入医疗机构名称
case "10002" : msg = "Por favor introduzca el nombre de la la instituci\u00F3n m\u00E9dica"; break;
//---请选择医疗机构类别
case "10003" : msg = "Por favor, seleccione la categor\u00EDa de las instituciones m\u00E9dicas"; break;
//---请选择医疗机构等级
case "10004" : msg = "Por favor, seleccione el nivel de establecimientos m\u00E9dicos"; break;
//---确定要删除该条记录？
case "10005" : msg = "Determinar estos registros que desee eliminar?"; break;
//---请输入代码
case "0-000001" : msg = "Introduzca el c\u00F3digo de"; break;
//---请输入名称
case "0-000002" : msg = "introduzca un nombre"; break;
//---您确定要保存该条记录？
case "0-000003" : msg = "\u00BFSeguro de que  estos registros que desee eliminar?"; break;
//---您确定要删除该条记录？
case "0-000004" : msg = "\u00BFSeguro de que  estos registros que desee salvo?"; break;
//---您确定要修改该条记录？
case "0-000005" : msg = "\u00BFSeguro de que  estos registros que desee modificar?"; break;



	}
	return  msg;
}


