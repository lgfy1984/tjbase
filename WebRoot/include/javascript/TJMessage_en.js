
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
		msg = "&1can not be empty"; 
		break;
	  case "10002":
//--------确定要删除吗?	
		msg = "You sure you want to delete it";
		break;
	  case "10003":
//--------确定要修改吗?
		msg = "You sure you want to modify it?";
		break;
	  case "10004":
//--------确定要添加吗?
		msg = "Sure you want to add?";
		break;
	  case "10005":
//--------&1大小关系不正确！
		msg = "&1Size relationship is not correct";
		break;
	  case "10006":
//--------只能选择一条数据！
		msg = "Can only select a data";
		break;
	  case "10007":
//--------请至少选择一条数据！
		msg = "Can only select a data";
		break;
	  case "10008":
//--------时间格式不正确！
		msg = "Time format is incorrect";
		break;
//---请输入代码
	  case "0-000001":
		msg = "Enter code";
		break;
//---请输入名称
	  case "0-000002":
		msg = "Please enter a name";
		break;
//---您确定要保存该条记录？
	  case "0-000003":
		msg = "Are you sure you want to save the record which";
		break;
//---您确定要删除该条记录？
	  case "0-000004":
		msg = "You sure you want to delete records which";
		break;
//---您确定要修改该条记录？
	  case "0-000005":
		msg = "You determine which records to modify";
		break;
//---请输入类别名称
	  case "0-000006":
		msg = "Please enter the category name";
		break;
//---请输入字典类别
	  case "0-000007":
		msg = "\u8bf7\u8f93\u5165\u5b57\u5178\u7c7b\u578b";
		break;
	}
	return msg;
}

