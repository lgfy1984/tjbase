	function trim(str){
			return str.replace(/(^\s*)|(\s*$)/g, "");
	}
	function yznumber(id,itermname,integer,decimal){
		var inputvalue= trim(document.getElementById(id).value);
		if(inputvalue != ""){
			var reg = /^[0-9]+(\.[0-9]+)?$/;
			if(reg.test(inputvalue)){
				if(inputvalue.indexOf(".")!=-1){
					array = inputvalue.split(".");
					arg1 = array[0];
					arg2 = array[1];										
					if(arg1.length > integer){
						alert("\" "+ itermname +" \"" + "integer part must be less than or equal" + integer + "digit!");
						return false;
					}
					if(arg2.length > decimal){
						alert("\" "+ itermname +" \"" + "must be less than equal to the fractional part of the" + decimal + "digit!");
						return false;
					}				
				}else{
					if(inputvalue.length > integer){
						alert("\" "+ itermname +" \"" + "integer part must be less than or equal" + integer + "digit!");						
						return false;
					}
				}			
			}else{
				alert("\" "+ itermname +" \"must enter a number!");
				return false;
			}
		}		
	}