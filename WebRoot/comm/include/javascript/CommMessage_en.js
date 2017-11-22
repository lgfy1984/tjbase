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
case "10001" : msg = "Please enter the medical coding"; break;
//---请输入医疗机构名称
case "10002" : msg = "Please enter the name of medical institution"; break;
//---请选择医疗机构类别
case "10003" : msg = "Please select the type of medical institutions"; break;
//---请选择医疗机构等级
case "10004" : msg = "Please select the level of medical institutions"; break;
//---确定要删除该条记录？
case "10005" : msg = "Determine which records you want to delete?"; break;
//---请输入代码
case "0-000001" : msg = "Please enter the code"; break;
//---请输入名称
case "0-000002" : msg = "Please enter a name"; break;
//---您确定要保存该条记录？
case "0-000003" : msg = "Are you sure you want to save this record?"; break;
//---您确定要删除该条记录？
case "0-000004" : msg = "Are you sure you want to delete this record?"; break;
//---您确定要修改该条记录？
case "0-000005" : msg = "Are you sure you want to update this record?"; break;



	}
	return  msg;
}


