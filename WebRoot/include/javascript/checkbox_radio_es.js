	//此方法为校验"多选项"和有"无"和"其他"的选项
	//id为包含多选项checkbox的div的id
	//如果有"其他"选项的,设置otherNum和otherComment参数,否则不设置
	//otherNum为这个其他选项的value
	//otherComment为其他选项相照应的input的name
	
	
	//valueA为如"正常"的值
function checkNone(id, value, otherNum, otherComment) {
	var parentDiv = document.getElementById(id);
	var inputAll = parentDiv.getElementsByTagName("input");
	for (var i = 0; i < inputAll.length; i++) {
		if (inputAll[i].type == "checkbox" && inputAll[i].value == value) {
			if (inputAll[i].checked == true) {
				for (var j = 0; j < inputAll.length; j++) {
					if (j != i) {
						if (inputAll[j].type == "checkbox") {
							if (inputAll[j].checked == true) {
								inputAll[j].checked = false;
							}
							inputAll[j].disabled = true;
						}
						if (inputAll[j].type == "text") {
							inputAll[j].value = "";
							inputAll[j].disabled = true;
						}
					}
				}
			} else {
				for (var j = 0; j < inputAll.length; j++) {
					if (j != i) {
						if (inputAll[j].type == "checkbox") {
							inputAll[j].disabled = false;
						}
						if (inputAll[j].type == "text") {
							inputAll[j].disabled = false;
						}
					}
				}
			}
		}
	}
	if (otherNum != undefined) {
		if (event.srcElement.value == otherNum && event.srcElement.checked == false) {
			document.getElementsByName(otherComment)[0].value = "";
		}
	}
}
	//此方法为校验"单选项"和有"其他"选项
	//如果有"其他"选项的,设置otherNum和otherComment参数,否则不设置
	//otherNum为这个其他选项的value
	//otherComment为其他选项相照应的input的name
function radioOther(otherNum, otherComment) {
	if (event.srcElement.value != otherNum) {
		document.getElementsByName(otherComment)[0].value = "";
	}
    if(event.srcElement.checked==false){
      document.getElementsByName(otherComment)[0].value = "";
    }	
}
	
	//去掉时 清空
	//传入 check 的名字 和 text 的名字  以及下下表值
function makeItEmpty(checkName, textName1, textName2, textName3, num1, num2, num3) {
       textName1=textName1.replace(/ /g,"");
       textName2=textName2.replace(/ /g,"");
       textName3=textName3.replace(/ /g,"");
    if(textName1!=null){ 
			if (document.getElementsByName(checkName)[num1].checked == false) {
				document.getElementsByName(textName1)[0].value = "";
			}
	}
	 if(textName2!=null){ 
			if (document.getElementsByName(checkName)[num2].checked == false) {
				document.getElementsByName(textName2)[0].value = "";
			}
	} if(textName3!=null){ 
			if (document.getElementsByName(checkName)[num3].checked == false) {
				document.getElementsByName(textName3)[0].value = "";
			}
	}
}

//checkboxName beforenum为横线前框所代表的第几个框-1  behindName为横线的名字
	function clearText(checkboxName,beforenum,behindName){
		if(document.getElementsByName(checkboxName)[beforenum].checked==false){
			document.getElementsByName(behindName)[0].value = "";
		}
	}
//此函数功能是让单选按钮全不选

function noChoolse(){
	var divparent = event.srcElement.parentElement.getElementsByTagName("INPUT");
	for(var i=0; i<divparent.length; i++){
		if(divparent[i].type == "radio"){
			divparent[i].checked = false;		
		}
		if(divparent[i].type == "text"){
			divparent[i].value = "";		
		}
	}
}

//******************addnew ************

//验证手机号码是否有效
function checkcell()
{
     var code=document.getElementById("mobilecode").value;
     var re=/^(130|131|133|135|136|137|138|139|159|158|153|188)(\d){8}$/; 
        if(code!=null && code!=""){
     if(!re.test(code))
     {
        alert("Introduzca el número de teléfono que no es válido");
        document.getElementById("mobilecode").value="";
        return ;
     } 
     }
}

//限制日期格式 2007-1-1 或2007-01-01为有效格式 此方法限定只能输入2000年以上的日期
   function checkdate()
{
     var date=document.getElementById("riqi").value;
     var re=/((20)[0-9][0-9])[-](1[0-2]|0?[1-9])[-](3[0,1]|[1,2][0-9]|0?[1-9])/;
     if(!re.test(date))
     {
         alert("el formato de fecha como 01/01/2007 o 01/01/2007");
         document.getElementById("riqi").value="";
         return ;
     }
}
 
//检测用户输入是否为合法邮箱地址
function checkemail()
{
      var re=/^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/;
      var email = document.getElementById("email").value;
     if(email!=null && email!=""){
      if(!re.test(email))
      {
          alert("la dirección de correo electrónico no es válida！");
          document.getElementById("email").value="";
          return ;
      }
}
}

//检测用户输入是否是数字形式
function checknumber(idName)
{
      var re=/\D/;
      var num=document.getElementById(idName).value;
      if(re.test(num))
      {
         alert("los números sólo se pueda ser como enteros！");
         document.getElementById(idName).value="";
         return ;
      }
} 


//检测用户输入是否是汉字形式
function checkchinese(idName)
{
      var re=/\D/;
      var num=document.getElementById(idName).value;
      if(!re.test(num))
      {
         alert("Sólo pueden formar los caracteres chinos！");
         document.getElementById(idName).value="";
         return ;
      }
} 

//检测用户输入的是否是有效的电话号码 区号可以为3位，也可以为4位，号码可以为7位，也可以为8位
   function checktel()
{
     var re=/^(\d{3,4}-)?\d{7,9}$/g;
     var tel=document.getElementById("tel").value;
    
     if(!re.test(tel))
     {
        alert("El número de teléfono no es válido");
        document.getElementById("tel").value="";
        return ;
     }
}

//比较通用的电话号码验证

   function checktel()
{
     var re=/((^0\d{1,4}(\-)?)?\d{4,8}$)|((\d{1,4}(\-)?\d{2,4}(\-)?)?\d{4,8}$)|((\d{1,4}(\-)?\d{2,4}(\-)?\d{2,4}(\-))?\d{4,8}$)/;
     var tel=document.getElementById("tel").value;
          if(tel!=null && tel!=""){
     if(!re.test(tel))
     {
        alert("El número de teléfono no es válido");
        document.getElementById("tel").value="";
        
        return ;
     }
     }
}


/**
*校验数字及小数位的位数
*/
function Transfer(thetxtNum){ 
		var _value= parseFloat(thetxtNum.value);
		if(isNaN(_value))
   			{
      			 return;
   				}
		thetxtNum.value = _value.toFixed(4);
		}	


/* 
* 校验邮编 
*/ 
function  checkPostCode(id){
    
    var ref=/^[1-9][0-9]{5}$/;
    var value=document.getElementById(id).value;
   if(value!=null && value!=""){
    if(!ref.test(value)){
       alert(value+" No es forma correcta de código postal!");
       document.getElementById(id).value="";
       
       return;
    }
    }
}
 
/* 
* 校验四位年 
*/ 
function  check4year(id){
    var ref=/^[1-2][0-9]{3}$/;
    var value=document.getElementById(id).value;
      if(value!=null && value!=""){
    if(!ref.test(value)){
       alert(value+"No es la forma correcta de año!");
       document.getElementById(id).value="";
       return;
    }
    var saat=new Date();
    if(saat.getYear()<value){
    	alert("Introduzca el año correcto");
    }
    }
}
 
/* 
* 校验3位代码 
*/ 
function  check3Code(id){
    
    var ref=/^[0-9][0-9]{2}$/;
    var value=document.getElementById(id).value;
   			if(value!=null && value!=""){
    if(!ref.test(value)){
       alert(value+"No es forma correcta de código!");
       document.getElementById(id).value="";
       return;
    }
    }
}