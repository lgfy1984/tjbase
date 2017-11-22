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
						alert("\" "+ Itermname +" \"" + "parte entera debe ser menor o igual" + entero + "dígitos");
						return false;
					}
					if(arg2.length > decimal){
						alert("\" "+ Itermname +" \"" + "debe ser inferior a igual a la fracción de la" + decimal + "dígitos");
						return false;
					}				
				}else{
					if(inputvalue.length > integer){
						alert("\" "+ Itermname +" \"" + "parte entera debe ser menor o igual" + entero + "dígitos");						
						return false;
					}
				}			
			}else{
				alert("\" "+ Itermname +" \"debe introducir un número!");
				return false;
			}
		}		
	}