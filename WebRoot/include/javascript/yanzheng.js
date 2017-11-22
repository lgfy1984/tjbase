function judgment(name,input){
if(name=="data.title"){
 if(input.value.length>30){
 alert("标题最多为30个字");
 input.value=input.value.substring(0,30);
 }
}
if(name=="data.author"){
 if(input.value.length>10){
 alert("作者最多为10个字");
  input.value=input.value.substring(0,10);
 }
}
if(name=="data.keyword"){
 if(input.value.length>10){
 alert("关键字最多为10个字");
  input.value=input.value.substring(0,10);
 }
}
if(name=="data.content"){
 if(input.value.length>200){
 alert("内容最多为200个字");
  input.value=input.value.substring(0,200);
 }
}
if(name=="data.comments"){
 if(input.value.length>20){
 alert("备注最多为20个字");
  input.value=input.value.substring(0,20);
 }
}
if(name=="data.name"){
 if(input.value.length>10){
 alert("姓名最多为10个字");
  input.value=input.value.substring(0,10);
 }
}
if(name=="data.age"){
if(input.value.match(/\D/)!=null){
				alert("年龄必须为数字！");
				input.value="";
				return false;
			}
    if(input.value!=""){
			if(input.value >200 || input.value < 1){
				alert("您输入的年龄不合法，年龄范围应在1-200之间，请重新输入！");
				input.value="";
				return false;
			}
		}
}
if(name=="data.tel"){
var reg=/^([0-9]|[\-])+$/g;
      if(input.value!=""){
       if(input.value.length<7 || input.value.length>18){
       alert("电话号码7位至18位!");
       input.value="";
        return false;
       }
         if(!reg.exec(input.value)){
       alert("电话号码输入不合法!");
       
       input.value="";
       input.focus();
        return false;
       }
      }
   }
   
 if(name=="data.kinPhone"){
var reg=/^([0-9]|[\-])+$/g;
      if(input.value!=""){
       if(input.value.length<7 || input.value.length>18){
       alert("电话号码7位至18位!");
       input.value="";
       input.focus();
        return false;
       }
           if(!reg.exec(input.value)){
       alert("电话号码输入不合法!");
       input.value="";
       input.focus();
        return false;
       }
      }
   }
   
  if(name=="data.mobile"){
var reg=/^([0-9]|[\-])+$/g;
      if(input.value!=""){
       if(input.value.length<11 || input.value.length>15){
       alert("手机号码输入不合法!");
       input.value="";
       input.focus();
        return false;
       }
           if(!reg.exec(input.value)){
      alert("手机号码输入不合法!");
       input.value="";
       input.focus();
        return false;
       }
      }
   } 
       
   if(name=="data.email"){
 var pattern = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(\.[a-zA-Z0-9_-])+/;
  if(input.value!=""){
    chkFlag = pattern.test(input.value);
  if(chkFlag){
        return true;
       }else{
     alert("邮箱地址的格式不正确！");
     input.value="";
     input.focus();
     return false;
     }
  }
  }  
  if(name=="data.consultationComments"){
    if(input.value.length>200){
    alert("咨询内容最多为200个字！");
    input.value=input.value.substring(0,200);
    }
  }
   if(name=="data.returnNotes"){
  if(input.value.length>200){
 alert("回复咨询内容最多为200个字！");
  input.value=input.value.substring(0,200);
 }
  }
if(name=="data.indictComments"){
  if(input.value.length>200){
 alert("投诉内容最多为200个字！");
  input.value=input.value.substring(0,200);
   }
  }
      if(name=="data.question"){
  if(input.value.length>200){
 alert("随访问题最多为200个字！");
  input.value=input.value.substring(0,200);
 }
  }
       if(name=="data.satisfaction"){
  if(input.value.length>100){
 alert("满意方面最多为100个字！");
  input.value=input.value.substring(0,100);
 }
  }
  
      if(name=="data.satisfactionNo"){
  if(input.value.length>100){
 alert("不满意方面最多为100个字！");
  input.value=input.value.substring(0,100);
 }
 }
        if(name=="data.advice"){
  if(input.value.length>100){
 alert("建议最多为100个字！");
  input.value=input.value.substring(0,100);
 }
 }
 
         if(name=="data.remindTitle"){
  if(input.value.length>20){
 alert("提醒主题最多为20个字！");
  input.value=input.value.substring(0,20);
 }
 }
   if(name=="data.remindContent"){
  if(input.value.length>190){
 alert("提醒内容最多为190个字！");
  input.value=input.value.substring(0,190);
 }
 }
 
    if(name=="data.sendContent"){
  if(input.value.length>260){
 alert("发送内容最多为260个字！");
  input.value=input.value.substring(0,260);
 }
 }
 
    if(name=="data.tempContent"){
  if(input.value.length>190){
 alert("模板内容最多为190个字！");
  input.value=input.value.substring(0,190);
 }
 }
 
     if(name=="data.societyJob"){
  if(input.value.length>200){
 alert("社会任职内容最多为200个字！");
  input.value=input.value.substring(0,200);
 }
 }
 
      if(name=="data.speciality"){
  if(input.value.length>200){
 alert("专业特长内容最多为250个字！");
  input.value=input.value.substring(0,250);
 }
 }

  
  
}

