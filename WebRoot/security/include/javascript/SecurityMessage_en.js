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

case "10001" : msg = "Please select the module!"; break;
case "10002" : msg = "Please select a role!"; break;
case "10003" : msg = "No menu to choose from!"; break;
case "10004" : msg = "Select User"; break;
case "10005" : msg = "Please enter the category code"; break;
case "10006" : msg = "Please enter the category name"; break;
case "10007" : msg = "Please enter the module code!";break;
case "10008" : msg = "Please enter the module name!";break;
case "10009" : msg = "Please enter a module description";break;

case "10010" : msg = "Please enter the role of code!";break;
case "10011" : msg = "Please enter the role name!";break; 
 
case "10013" : msg = "Please enter the name of the menu!";break;
case "10014" : msg = "Please enter the parent menu ID!";break;
case "10015" : msg = "Please enter the contents of the menu!";break;
case "10016" : msg = "Please enter the node marks the end of";break;
case "10017" : msg = "Please enter a level!";break;

	}
	return  msg;
}


