function showYmPromptMessage(messageType, message){
	if(messageType!==null){
		if(messageType=='0'){
			showYmPromptAlertMessage(message);
		}else if(messageType==='1'){
			showYmPromptSucceedMessage(message);
		}else if(messageType==='-1'){
			showYmPromptErrorMessage(message);
		}else{
			showYmPromptErrorMessage(message);
		}
	}else{
		showYmPromptErrorMessage(message);
	}
}
function showYmPromptAlertMessage(message){
	if(message != ''&& message != null){
		ymPrompt.alert(message);
		return;
	}
}
function showYmPromptSucceedMessage(message){
	if(message != ''&& message != null){
		ymPrompt.succeedInfo(message);
		return;
	}
}
function showYmPromptErrorMessage(message){
	if(message != ''&& message != null){
		ymPrompt.errorInfo(message);
		return;
	}
}