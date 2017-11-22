/////////////////////////////////////////////////////////////
////////////////////////   DZENALL   ////////////////////////
//////////////////////// 2008-07-31 /////////////////////////
/////////////////////////////////////////////////////////////
//<script language="javascript" src="<%=request.getContextPath()%>/include/javascript/ufloat.js"></script>

/////////////////////////////////////////////////////////////
// Function: isFloat(valueOfStr).
// Description: Check out that if the parameter valueOfStr is
// 	one float number.
// Param: A string or a number.
// Return: True when it is or it can be cast to a float number,
// 	otherwise return false.
// ie: 	isFloat('45s.5')		false
//		isFolat('455.5')		true
//		isFloat(455.5)   		true
/////////////////////////////////////////////////////////////
function isFloat(valueOfStr){
	var patrn1 = /^([+|-]?\d+)(\.\d+)?$/;
	try{
		//alert("isFloat("+valueOfStr+").patrn="+patrn1.exec(valueOfStr));
		if (patrn1.exec(valueOfStr)){
					return true;
				}else{
					return false;
				}
	}catch(e){
		return false;
	}
}

/////////////////////////////////////////////////////////////
// Function: isPlusFloat(valueOfStr).
// Description: Check out that if the parameter valueOfStr is
// 	one plus float number.
// Param: A string or a number.
// Return: True when it is or it can be cast to a plus float 
//  number,	otherwise return false.
/////////////////////////////////////////////////////////////
function isPlusFloat(valueOfStr){
	var patrn1 = /^(\d+)(\.\d+)?$/;
	try{
		//alert("isPlusFloat("+valueOfStr+").patrn="+patrn1.exec(valueOfStr));
		if (patrn1.exec(valueOfStr)){
					return true;
				}else{
					return false;
				}
	}catch(e){
		return false;
	}
}

/////////////////////////////////////////////////////////////
// Function: isNegativeFloat(valueOfStr).
// Description: Check out that if the parameter valueOfStr is
// 	one negative float number.
// Param: A string or a number.
// Return: True when it is or it can be cast to a negative float 
//  number,	otherwise return false.
/////////////////////////////////////////////////////////////
function isNegativeFloat(valueOfStr){
	var patrn1 = /^(-\d+)(\.\d+)?$/;
	try{
		//alert("isNegativeFloat("+valueOfStr+").patrn="+patrn1.exec(valueOfStr));
		if (patrn1.exec(valueOfStr)){
					return true;
				}else{
					return false;
				}
	}catch(e){
		return false;
	}
}

/////////////////////////////////////////////////////////////
// Function: isFloatD4(valueOfStr).
// Description: Check out that if the parameter valueOfStr is
// 	one float number with 4 decimals.
// Param: A string or a number.
// Return: True when it is or it can be cast to a float number
// 	and with 4 decimals, otherwise return false.
/////////////////////////////////////////////////////////////
function isFloatD4(valueOfStr){
	var patrn1 = /^([+|-]?[0-9])+(.[0-9]{0,4})?$/;
	try{
		//alert("isFloatD4("+valueOfStr+").patrn="+patrn1.exec(valueOfStr));
		if (patrn1.exec(valueOfStr)){
					return true;
				}else{
					return false;
				}
	}catch(e){
		return false;
	}
}

/////////////////////////////////////////////////////////////
// Function: isPlusFloatD4(valueOfStr).
// Description: Check out that if the parameter valueOfStr is
// 	one plus float number with 4 decimals.
// Param: A string or a number.
// Return: True when it is or it can be cast to a plus float 
//  number and with 4 decimals, otherwise return false.
/////////////////////////////////////////////////////////////
function isPlusFloatD4(valueOfStr){
	var patrn1 = /^([+|]?[0-9])+(.[0-9]{0,4})?$/;
	try{
		//alert("isPlusFloatD4("+valueOfStr+").patrn="+patrn1.exec(valueOfStr));
		if (patrn1.exec(valueOfStr)){
					return true;
				}else{
					return false;
				}
	}catch(e){
		return false;
	}
}

/////////////////////////////////////////////////////////////
// Function: isNegativeFloatD4(valueOfStr).
// Description: Check out that if the parameter valueOfStr is
// 	one negative float number with 4 decimals.
// Param: A string or a number.
// Return: True when it is or it can be cast to a negative float 
//  number and with 4 decimals, otherwise return false.
/////////////////////////////////////////////////////////////
function isNegativeFloatD4(valueOfStr){
	var patrn1 = /^(-[0-9])+(.[0-9]{0,4})?$/;
	try{
		//alert("isNegativeFloatD4("+valueOfStr+").patrn="+patrn1.exec(valueOfStr));
		if (patrn1.exec(valueOfStr)){
					return true;
				}else{
					return false;
				}
	}catch(e){
		return false;
	}
}

/////////////////////////////////////////////////////////////
// Function: isFloatTD4(valueOfStr, eLength).
// Description: Check out that if the parameter valueOfStr is
// 	one float number with effective length eLength and 4 decimals.
// Param: A string or a number.
// Return: True when it is or it can be cast to a float 
//  number and with effective length eLength and 4 decimals, otherwise 
//  return false.
/////////////////////////////////////////////////////////////
function isFloatTD4(valueOfStr, eLength){
	var patrn1 = /^([+|-]?[0-9])+(.[0-9]{0,4})?$/;
	try{
		//alert("isFloatTD4("+valueOfStr+","+eLength+").patrn="+patrn1.exec(valueOfStr));
		if (patrn1.exec(valueOfStr)){		
			var tlen = valueOfStr.length;
			var dot = valueOfStr.indexOf('.');
			var plus = valueOfStr.indexOf('+');
			var neg = valueOfStr.indexOf('-');
			var rlen = tlen;
			if(dot != -1)
				rlen = rlen - 1;
			if(plus != -1)
				rlen = rlen - 1;
			if(neg != -1)
				rlen = rlen - 1;
				
			if(rlen==parseInt(eLength))				
				return true;
			else 
				return false;
		}else{
			return false;
		}
	}catch(e){
		return false;
	}
}

/////////////////////////////////////////////////////////////
// Function: isPlusFloatTD4(valueOfStr, eLength).
// Description: Check out that if the parameter valueOfStr is
// 	one plus float number with effective length eLength and 4 decimals.
// Param: A string or a number.
// Return: True when it is or it can be cast to a plus float 
//  number and with effective length eLength and 4 decimals, otherwise 
//  return false.
/////////////////////////////////////////////////////////////
function isPlusFloatTD4(valueOfStr, eLength){
	var patrn1 = /^([+|]?[0-9])+(.[0-9]{0,4})?$/;
	try{
		//alert("isPlusFloatTD4("+valueOfStr+","+eLength+").patrn="+patrn1.exec(valueOfStr));
		if (patrn1.exec(valueOfStr)){
			var tlen = valueOfStr.length;
			var dot = valueOfStr.indexOf('.');
			var plus = valueOfStr.indexOf('+');
			var neg = valueOfStr.indexOf('-');
			var rlen = tlen;
			if(dot != -1)
				rlen = rlen - 1;
			if(plus != -1)
				rlen = rlen - 1;
			if(neg != -1)
				rlen = rlen - 1;
				
			if(rlen==parseInt(eLength))				
				return true;
			else 
				return false;
		}else{
			return false;
		}
	}catch(e){
		return false;
	}
}

/////////////////////////////////////////////////////////////
// Function: isNegativeFloatTD4(valueOfStr, eLength).
// Description: Check out that if the parameter valueOfStr is
// 	one negative float number with eLength length and 4 decimals.
// Param: A string or a number.
// Return: True when it is or it can be cast to a negative float 
//  number and with eLength length and 4 decimals, otherwise 
//  return false.
/////////////////////////////////////////////////////////////
function isNegativeFloatTD4(valueOfStr){
	var patrn1 = /^(-[0-9])+(.[0-9]{0,4})?$/;
	try{
		//alert("isNegativeFloatTD4("+valueOfStr+").patrn="+patrn1.exec(valueOfStr));
		if (patrn1.exec(valueOfStr)){
			var tlen = valueOfStr.length;
			var dot = valueOfStr.indexOf('.');
			var plus = valueOfStr.indexOf('+');
			var neg = valueOfStr.indexOf('-');
			var rlen = tlen;
			if(dot != -1)
				rlen = rlen - 1;
			if(plus != -1)
				rlen = rlen - 1;
			if(neg != -1)
				rlen = rlen - 1;
				
			if(rlen==parseInt(eLength))				
				return true;
			else 
				return false;
		}else{
			return false;
		}
	}catch(e){
		return false;
	}
}
/////////////////////////////////////////////////////////////
// Function: testFloat(valueOFinput, plusORnegative, decimal).
// Description: Check out that if the parameter valueOFinput is
// 		a float number with its sign according to parameter plusORnegative
//  	and its max decimal digits is parameter decimal.
// Param: A string or a number deing tested.
//    	A string implies plus ,negative or throw the reins to the first parameter.
//		A string points out how many the decimal digits is. It would be casted to a integer number.
// Return: True when valueOFinput can be cast to a float number with its sign according to parameter plusORnegative
//  	and its max decimal digits is parameter decimal, otherwise return false.
// ie:	testFloat('ffr', '', 7)-------------false
//		testFloat('d45', '', '7')-----------fasle
//		testFloat('65.254', '+', '3')-------true
//		testFloat('65.254', '+', '2')-------false
//		testFloat('65.254', '-', '3')-------false
//		testFloat('65.254', '+', '4')-------true  
/////////////////////////////////////////////////////////////
function testFloat(valueOFinput, plusORnegative, decimal){
	valueOFinput = valueOFinput.replace(/(^\s*)|(\s*$)/g, "");
	plusORnegative = plusORnegative.replace(/(^\s*)|(\s*$)/g, "");
	decimal = decimal.replace(/(^\s*)|(\s*$)/g, "");
	
	if(valueOFinput==null || valueOFinput==""){
		alert("Illegimate number input!");
		return false;
	}
	if(plusORnegative==null && plusORnegative!="+" && plusORnegative!="-" && plusORnegative!=""){
		alert("Illegimate sign input!");
		return false;
	}
	var dec = parseInt(decimal);
	if(typeof(dec)=="undefine" || dec == null){
		alert("Illegimate decimal number input!");
		return false;
	}else if(dec < 0){
		alert("negative decimal number input! Make it positive number");
	}
	
	var rg = "";
	
	if(plusORnegative=="+"){
		rg += '^([+|]?';
	}else if(plusORnegative=="-"){
		rg += '^([-]';
	}else{
		rg += '^([+|-|]?';
	}
	
	var pp = new RegExp(rg + '[0-9]{1,9})+(.[0-9]{0,'+dec+'})?$');
	//alert(pp);
		
    if(!pp.exec(valueOFinput)){
		alert("Illegimate value input with "+dec+" decimal!");
		return false;
	}else{
		alert("Legimate value input with "+dec+" decimal!");
		return true;
	}
}