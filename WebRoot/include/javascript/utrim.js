function trim(str){
 	return str.replace(/(^\s*)|(\s*$)/g, "");
}
function ltrim(str){
 	return str.replace(/(^\s*)/g,"");
}
function rtrim(str){
 	return str.replace(/(\s*$)/g,"");
}

function isDate(year, month, day){
	var ymd = trim(year) + "-" + trim(month) + "-" + trim(day);
	var patrn_birthday=/^((((1[6-9]|[2-9]\d)\d{2})-(0?[13578]|1[02])-(0?[1-9]|[12]\d|3[01]))|(((1[6-9]|[2-9]\d)\d{2})-(0?[13456789]|1[012])-(0?[1-9]|[12]\d|30))|(((1[6-9]|[2-9]\d)\d{2})-0?2-(0?[1-9]|1\d|2[0-8]))|(((1[6-9]|[2-9]\d)(0[48]|[2468][048]|[13579][26])|((16|[2468][048]|[3579][26])00))-0?2-29))$/;
	if(ymd.match(patrn_birthday)){
		return true;
	}else{
		return false;
	}
}

function countLength(str){
	var pat = new RegExp("[\u4e00-\u9fa5]","g");
	if(str!=null && typeof(str)=="string"){
		var val = str.replace(pat, "aa");
		return val.length;
	}else{
		return null;
	}	
}

function qjToBj(str){   
	var tmp = ""; 
	for(var i=0;i<str.length;i++){ 
		if(str.charCodeAt(i)==12288){
			tmp += " ";
		}else if(str.charCodeAt(i)>65248 && str.charCodeAt(i)<65375){ 
			tmp += String.fromCharCode(str.charCodeAt(i)-65248); 
		}else{ 
			tmp += String.fromCharCode(str.charCodeAt(i)); 
		} 
	} 
	return tmp  
}
/* 验证是否为0到8位整数 */
function isInteger( str ){ 
	var regu = /^[0-9]{0,8}$/; 
	return regu.test(str); 
} 

function dataViladate(strvillageNum){
	var biaozhun=/^[1-9]+[0-9]*$/;
	if(!biaozhun.test(strvillageNum)){  
		return false;
	}else{
		return true;
	}
}

