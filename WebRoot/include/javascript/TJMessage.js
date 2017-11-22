
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
		msg = "&1\u4e0d\u80fd\u4e3a\u7a7a\uff01"; 
		break;
	  case "10002":
//--------确定要删除吗?	
		msg = "\u786e\u5b9a\u8981\u5220\u9664\u5417?";
		break;
	  case "10003":
//--------确定要修改吗?
		msg = "\u786e\u5b9a\u8981\u4fee\u6539\u5417?";
		break;
	  case "10004":
//--------确定要添加吗?
		msg = "\u786e\u5b9a\u8981\u6dfb\u52a0\u5417?";
		break;
	  case "10005":
//--------&1大小关系不正确！
		msg = "&1\u5927\u5c0f\u5173\u7cfb\u4e0d\u6b63\u786e\uff01";
		break;
	  case "10006":
//--------只能选择一条数据！
		msg = "\u53ea\u80fd\u9009\u62e9\u4e00\u6761\u6570\u636e\uff01";
		break;
	  case "10007":
//--------请至少选择一条数据！
		msg = "\u8bf7\u81f3\u5c11\u9009\u62e9\u4e00\u6761\u6570\u636e\uff01";
		break;
	  case "10008":
//--------时间格式不正确！
		msg = "\u65f6\u95f4\u683c\u5f0f\u4e0d\u6b63\u786e\uff01";
		break;
//---请输入代码
	  case "0-000001":
		msg = "\u8bf7\u8f93\u5165\u4ee3\u7801";
		break;
//---请输入名称
	  case "0-000002":
		msg = "\u8bf7\u8f93\u5165\u540d\u79f0";
		break;
//---您确定要保存该条记录？
	  case "0-000003":
		msg = "\u60a8\u786e\u5b9a\u8981\u4fdd\u5b58\u8be5\u6761\u8bb0\u5f55\uff1f";
		break;
//---您确定要删除该条记录？
	  case "0-000004":
		msg = "\u60a8\u786e\u5b9a\u8981\u5220\u9664\u8be5\u6761\u8bb0\u5f55\uff1f";
		break;
//---您确定要修改该条记录？
	  case "0-000005":
		msg = "\u60a8\u786e\u5b9a\u8981\u4fee\u6539\u8be5\u6761\u8bb0\u5f55\uff1f";
		break;
//---请输入类别名称
	  case "0-000006":
		msg = "\u8bf7\u8f93\u5165\u7c7b\u522b\u540d\u79f0";
		break;
//---请输入字典类别
	  case "0-000007":
		msg = "Please enter the dictionary category";
		break;
	}
	return msg;
}

