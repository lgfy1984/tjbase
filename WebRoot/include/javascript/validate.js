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
						alert("\""+itermname+"\""+"的整数部分必须小于等于"+integer+"位数字！");
						return false;
					}
					if(arg2.length > decimal){
						alert("\""+itermname+"\""+"的小数部分必须小于等于"+decimal+"位数字！");
						return false;
					}				
				}else{
					if(inputvalue.length > integer){
						alert("\""+itermname+"\""+"的整数部分必须小于等于"+integer+"位数字！");						
						return false;
					}
				}			
			}else{
				alert("\""+itermname+"\"必须输入数字！");
				return false;
			}
		}		
	}