function ymPromptAdaper(){
	var language = (window.navigator.systemLanguage || window.navigator.language).toUpperCase();
	if(language=="ES-VE"){
		es();
	}else if(language=="ZH-CN"){
		cn();
	}else if(language=="EN" || language=="EN-US" || language=="EN-GB"){
		en();
	}else{
		es();
	}
}
function ymPromptAdapter(){
	var language = (window.navigator.systemLanguage || window.navigator.language).toUpperCase();
	if(language=="ES-VE"){
		es();
	}else if(language=="ZH-CN"){
		cn();
	}else if(language=="EN" || language=="EN-US" || language=="EN-GB"){
		en();
	}else{
		es();
	}
}