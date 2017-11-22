
function alertMessage(errorCode) {
	alert(getMessage(errorCode, "", ""));
}
function alertMessage(errorCode, arg1, arg2) {
	alert(getMessage(errorCode, arg1, arg2));
}
function confirmMessage(errorCode) {
	if (confirm(getMessage(errorCode, "", ""))) {
		return true;
	} else {
		return false;
	}
}
function getMessage(errorCode, arg1, arg2) {
	return " " + msgMgr(errorCode).replace("&1", arg1).replace("&2", arg2);
}
function showMessage(msgId, msgString, msgType) {
	if (msgString != "") {
		if (msgType == 0) {
			confirm(msgString);
		} else {
			alert(msgString);
		}
	} else {
		if (msgId != "") {
			if (msgType == 0) {
				confirmMessage(msgId, "", "");
			} else {
				alertMessage(msgId, "", "");
			}
		}
	}
}
function msgMgr(errorCode) {
	var msg = errorCode;
	switch (errorCode) {
	
	  case "10001":
//--------&1不能为空！	
		msg = "&1No puede estar en vacío"; 
		break;
	  case "10002":
//--------确定要删除吗?	
		msg = "¿Seguro de que desea eliminar?";
		break;
	  case "10003":
//--------确定要修改吗?
		msg = "¿Seguro de que desea modificar";
		break;
	  case "10004":
//--------确定要添加吗?
		msg = "¿Seguro de que desea agregar?";
		break;
	  case "10005":
//--------&1大小关系不正确！
		msg = "&1Relación de tamaño no es correcto";
		break;
	  case "10006":
//--------只能选择一条数据！
		msg = "Sólo se puede seleccionar un  datos";
		break;
	  case "10007":
//--------请至少选择一条数据！
		msg = "Por favor, seleccione al menos un datoe";
		break;
	  case "10008":
//--------时间格式不正确！
		msg = "Formato de la hora es incorrecta";
		break;
//---请输入代码
	  case "0-000001":
		msg = "Introduzca el código de";
		break;
//---请输入名称
	  case "0-000002":
		msg = "Por favor, introduzca un nombre";
		break;
//---您确定要保存该条记录？
	  case "0-000003":
		msg = "¿Esta seguro que desea guardar el regsitro?";
		break;
//---您确定要删除该条记录？
	  case "0-000004":
		msg = "¿Seguro de que desea eliminar los registros que";
		break;
//---您确定要修改该条记录？
	  case "0-000005":
		msg = "Se determinan los registros de modificar";
		break;
//---请输入类别名称
	  case "0-000006":
		msg = "Por favor, introduzca el nombre de la categoría";
		break;
//---请输入字典类别
	  case "0-000007":
		msg = "Por favor, introduzca la categoría diccionario";
		break;
	}
	return msg;
}

