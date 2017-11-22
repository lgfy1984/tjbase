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
case "10001" : msg = "\u8bf7\u8f93\u5165\u533b\u7597\u673a\u6784\u7f16\u7801"; break;
//---请输入医疗机构名称
case "10002" : msg = "\u8bf7\u8f93\u5165\u533b\u7597\u673a\u6784\u540d\u79f0"; break;
//---请选择医疗机构类别
case "10003" : msg = "\u8bf7\u9009\u62e9\u533b\u7597\u673a\u6784\u7c7b\u522b"; break;
//---请选择医疗机构等级
case "10004" : msg = "\u8bf7\u9009\u62e9\u533b\u7597\u673a\u6784\u7b49\u7ea7"; break;
//---确定要删除该条记录？
case "10005" : msg = "\u786e\u5b9a\u8981\u5220\u9664\u8be5\u6761\u8bb0\u5f55\uff1f"; break;
//---请输入代码
case "0-000001" : msg = "\u8bf7\u8f93\u5165\u4ee3\u7801"; break;
//---请输入名称
case "0-000002" : msg = "\u8bf7\u8f93\u5165\u540d\u79f0"; break;
//---您确定要保存该条记录？
case "0-000003" : msg = "\u60a8\u786e\u5b9a\u8981\u4fdd\u5b58\u8be5\u6761\u8bb0\u5f55\uff1f"; break;
//---您确定要删除该条记录？
case "0-000004" : msg = "\u60a8\u786e\u5b9a\u8981\u5220\u9664\u8be5\u6761\u8bb0\u5f55\uff1f"; break;
//---您确定要修改该条记录？
case "0-000005" : msg = "\u60a8\u786e\u5b9a\u8981\u4fee\u6539\u8be5\u6761\u8bb0\u5f55\uff1f"; break;



	}
	return  msg;
}


