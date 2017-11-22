<!--
//to replace all string1 with string2 in a fixed string.
function replaceString(s,s1,s2){
  if(s.indexOf(s1)>=0){
    s=s.substring(0,s.indexOf(s1))+s2+s.substring(s.indexOf(s1)+s1.length);
    s=replaceString(s,s1,s2);
  }
  return s;
}

//将HTML编号转换为实际对应的字符，得到真正的字符串
function getRealString(s){
  s=replaceString(s,"&lt;","<");
  s=replaceString(s,"&gt;",">");
  s=replaceString(s,"&amp;","&");
  s=replaceString(s,"&quot;","\"");

  return s;
}

//判断字符串是否由数字组成
function str_numVerify(str){
	rStr=/[^0-9]/g;
	str=str.replace(rStr,"*!@#$%");
	if(str.indexOf("*!@#$%")!=-1){
		return false;
	}
	return true;
}

//为了解决xp sp2下不能滚动的问题，将所有的window.open替换成了这个函数
function openwin_xp2(url, winName, p1, p2, p3, p4, p5, p6, p7){
  if (p1 == "fullscreen")
    return window.open(url,winName,p1);
  else
    return openwin(url, winName);
}

//如果提供窗口名称，则只打开一个窗口，否则每次打开一个新窗口
//to open a new window without status
var winNum=0;
//var ObjWin=null;
//var winName="openWin";
var left=(screen.availWidth-640)/2;
var top_a=(screen.availHeight-480)/2;
function openwin(URL,winName,leftMargin,topMargin,winWidth,winHeight){
	if(winName !=null && leftMargin!=null && topMargin!=null && winWidth !=null && winHeight !=null){
		ObjWin=window.open(URL,winName,"left="+leftMargin+", top="+topMargin+", width="+winWidth+", height="+winHeight+", menu=yes, status=yes, resizable=yes, scrollbars=yes");
	}else{
	  if (winName == null){
			winNum++;
			winName="openWin"+winNum;
				//left+=winNum*20;
				//top_a+=winNum*20;
		}	
		ObjWin=window.open(URL,winName,"left="+left+", top="+top_a+", width=640,  height=480, menu=yes, status=yes, resizable=yes, scrollbars=yes");
	}
	ObjWin.opener=this;
	return ObjWin;
}

//判断是否是正整数
function isInteger(inputVal){
  inputStr=inputVal.toString();

  for(var i=0;i<inputStr.length;i++) {
    var oneChar=inputStr.charAt(i);
//    if(i==0&&oneChar=="0")
//      return false;
    if(oneChar<"0"||oneChar>"9"){
      return false;
    }
  }
  return true;
}

//判断是否是正整数,加提示
function isInteger(inputVal, strName){
  inputStr=inputVal.toString();

  for(var i=0;i<inputStr.length;i++) {
    var oneChar=inputStr.charAt(i);
//    if(i==0&&oneChar=="0")
//      return false;
    if(oneChar<"0"||oneChar>"9"){
      alert("Part '"+ strName +"' can only fill an integer greater than 0");
      return false;
    }
  }
  return true;
}

//判断是否是空字符串或空格字符串
function isVoidStr(inputVal){
  if(inputVal.length==0){
    return true;
  } else {
    var oneChar="";
    for(var i=0;i<inputVal.length;i++) {
      oneChar=inputVal.charAt(i);
      if(oneChar!=' ') {
        return false;
      }
    }
  }
  return true;
}

//判断EAMIL是否合法
function check_email(address) {
  address=address;
  if ((address == "") || (address.indexOf ('@@') != -1) || (address.indexOf ('..') != -1) || (address.indexOf ('@.') != -1) || (address.indexOf ('.@') != -1)|| (address.indexOf ('@') == -1) || (address.indexOf ('.') == -1))
    return false;
  return true;
}

function emailVerify(objEmail,blnEmpty,strName){
  if (objEmail==null ||objEmail.value==null){ return true; }

  emailAdd=	objEmail.value;
  if((emailAdd==null || emailAdd=="") && blnEmpty==false){
	  alert("Part '"+ strName +"' can not be empty.");
	  objEmail.focus();
	  return false;
	  } 
 if( emailAdd!="" && !check_email(emailAdd)){
	  	alert("Part '"+ strName +"' is malformed! \ N Please refer to the following format: zonghua@126.com");
		objEmail.focus();
		return false;
	  }
  return true;
}
//得到字符串的字节长度
function calculateStrLen(str){
  var i,intLen=0;
  for(i=0;i<str.length;i++){
    if (str.charCodeAt(i)>126){
      intLen++;
    }
    intLen++;
  }
  return intLen;
}

function telVerify(objText,blnEmpty,intLength,strName ){
  if (objText==null ||objText.value==null){ return true; }

//校验电话号码录入的有效性,有效返回true,无效返回false
	strText=objText.value;
	if(strText=="" && blnEmpty==false ){
		alert("Part '"+ strName +"' must be filled!");
	  	objText.focus();
      	return false;
	}else if(strText!=""){
		if(strText.length > intLength){
			alert("Part '"+ strName +"' exceeds the specified length ("+ intLength +" character), please correct.");
	  		objText.focus();
      		return false;
		}
		sStr=/[0-9]/g;
		bln=strText.match(sStr);
		if(bln==null){
			alert("Part '"+ strName +"' required by the numbers!");
	  		objText.focus()
      		return false;
		}
	}
 return true;
}


function textVerify(objText,blnEmpty,intLength,strName ){
//校验文本录入的有效性,有效返回true,无效返回false
 //如果对象不存在，就跳过检查，直接返回真
  if (objText==null ||objText.value==null){ return true; }
  
//objText为待校验的页面表单对象；blnEmpty表示此文本是否可为空,true表示可为空，false表示不可为空；
//intLength最大字节长度；strName校验的对象栏目的名称
  var strText=objText.value;
  var regStr=/\s/g;
  var newText=strText.replace(regStr,"");
  if((strText=="" || newText.length ==0) && blnEmpty==false){
    alert("part'" + strName + "' can not be empty.");
    objText.focus();
    return false;
  }
  if(calculateStrLen(strText) > intLength){
    alert("Part '"+ strName +"' exceeds the specified length ("+ intLength +" character), please correct.");
    objText.focus();
    return false;
  }
  
  return true;
}

function charVerify(objText,blnEmpty,intLength,strName ){
  if (objText==null ||objText.value==null){ return true; }

//校验纯字母文本录入的有效性,有效返回true,无效返回false
  var strText=objText.value;
  if(strText=="" && blnEmpty==false){
    alert("Part '"+ strName +"' can not be empty.");
    objText.focus();
    return false;
  }
  var i;
  for(i=0;i<strText.length;i++){
    if(strText.charCodeAt(i)!=32){
      if(strText.charCodeAt(i)<65 || ( strText.charCodeAt(i)>90 && strText.charCodeAt(i)<97 )  ||  strText.charCodeAt(i)>122 ){
        alert("Part '"+ strName +"' can only fill in the letters.");
        objText.focus();
        return false;
      }
    }
  }
  if(strText.length > intLength){
    alert("Part '"+ strName +"' exceeds the specified length ("+ intLength +" character), please correct.");
    objText.focus();
    return false;
  }
  return true;
}

function char_numVerify(objText,blnEmpty,intLength,strName ){
  if (objText==null ||objText.value==null){ return true; }

//校验纯字母和数字文本录入的有效性,有效返回true,无效返回false
  var strText=objText.value;
  if(strText=="" && blnEmpty==false){
    alert("Part '"+ strName +"' can not be empty.");
    objText.focus();
    return false;
  }
  var i;
  for(i=0;i<strText.length;i++){
    if(strText.charCodeAt(i)!=32){
      if(strText.charCodeAt(i)<48 || ( strText.charCodeAt(i)>57 && strText.charCodeAt(i)<65 )  || ( strText.charCodeAt(i)>90 && strText.charCodeAt(i)<97 )  ||  strText.charCodeAt(i)>122 ){
        alert("Part '"+ strName +"' can only fill in the letters and numbers.");
        objText.focus();
        return false;
      }
    }
  }
  if(strText.length > intLength){
    alert("Part '"+ strName +"' exceeds the specified length ("+ intLength +" character), please correct.");
    objText.focus();
    return false;
  }
  return true;
}

function numberVerify(objText,blnEmpty,intLength,strName ){
  if (objText==null ||objText.value==null){ return true; }

//校验纯数字文本录入的有效性,有效返回true,无效返回false
  var strText=objText.value;
  if(strText=="" && blnEmpty==false){
    alert("Part '"+ strName +"' can not be empty.");
    objText.focus();
    return false;
  }
  var i;
  for(i=0;i<strText.length;i++){
    if((strText.charCodeAt(i)<48 || strText.charCodeAt(i)>57)){
      alert("Part '"+ strName +"' Please use numbers only.");
      objText.focus();
      return false;
    }
  }
  if(strText.length > intLength){
    alert("Part '"+ strName +"' exceeds the specified length ("+ intLength +" character), please correct.");
    objText.focus();
    return false;
  }
  return true;
}

function intVerify(objText,blnEmpty,intLength,strName ){
  if (objText==null ||objText.value==null){ return true; }

//校验整数文本录入的有效性,有效返回true,无效返回false
  var strText=objText.value;
  if(strText=="" && blnEmpty==false){
    alert("Part '"+ strName +"' can not be empty.");
    objText.focus();
    return false;
  }
  var i;
  for(i=0;i<strText.length;i++){
    if((strText.charCodeAt(i)<48 || strText.charCodeAt(i)>57)){
      alert("Part '"+ strName +"' can only fill an integer.");
      objText.focus();
      return false;
    }
  }
  if(strText.length > intLength){
    alert("Part '"+ strName +"' exceeds the specified length ("+ intLength +" character), please correct.");
    objText.focus();
    return false;
  }
  return true;
}

function doubleVerify(objText,blnEmpty,totalLen,decimalLen,strName ){
  if (objText==null ||objText.value==null){ return true; }

//校验带小数数字文本录入的有效性,有效返回true,无效返回false
  var strText=objText.value;
  if(strText=="" && blnEmpty==false){
    alert("Part '"+ strName +"' can not be empty.");
    objText.focus();
    return false;
  }
  var i;
  for(i=0;i<strText.length;i++){
    if((strText.charCodeAt(i)<48 || strText.charCodeAt(i)>57) &&  strText.charCodeAt(i)!=46&&  strText.charCodeAt(i)!=69){
      alert("Part '"+ strName +"' Please use numbers only.");
      objText.focus();
      return false;
    }
  }

  var point=strText.indexOf('.');
  if( point==-1){
    if(strText.length > totalLen-decimalLen){
      alert("Part '"+ strName +"' Enter the number is too large, correct.");
      objText.focus();
      return false;
    }
  }else{
    if(point > totalLen-decimalLen){
      alert("Part '"+ strName +"' Enter the number is too large, correct.");
      objText.focus();
      return false;
    }
    if(strText.length-(point+1) > decimalLen){
      alert("Part '"+ strName +"' Enter the number of decimal places can only have "+ decimalLen +" bit, please correct.");
      objText.focus();
      return false;
    }
  }
  return true;
}

function dateOrderVerify(objStart,objEnd,blnEmpty,strStartName,strEndName ){
//校验关联日期的有效性,有效返回true,无效返回false
  var strStart=objStart.value;
  var strEnd=objEnd.value;
  if(blnEmpty){
    if(strStart=="" || strEnd==""){
      return true;
    }
  }else{
    if(strStart==""){
      alert("Part '"+ strStartName +"' can not be empty.");
      objStart.focus();
      return false;
    }
    if(strEnd==""){
      alert("Part '"+ strEndName +"' can not be empty.");
      objEnd.focus();
      return false;
    }
  }

  var intStart=(strStart.substring(0,4))*10000+(strStart.substring(5,7))*100+(strStart.substring(8,10));
  var intEnd=(strEnd.substring(0,4))*10000+(strEnd.substring(5,7))*100+(strEnd.substring(8,10));
  if(intStart>intEnd){
    alert("Part '"+ strStartName +"' specified date not more than part '"+ strEndName +"' specified date night.");
    objStart.focus();
    return false;
  }else{
    return true;
  }
}


//检查所输入日期与现在日期进行比较
//@objname 需要检查的输入对象的名称
//@blnNowtimeAfter 布尔值 true表示输入的时间要求比现在的时间晚
//@strName	栏目名称
// add by huangliang 2004-09-01

function dateCompareNowtimeVerify(objname,blnNowtimeAfter,strName){
  if (objname==null ||objname.value==null){ return true; }

var inputTime=objname.value;
//暂不是必输项
if (inputTime==null || inputTime=="")
  return true;
  
var inputDate;
var nowDate=new Date();
if(inputTime.indexOf(":")<0){		
		inputDate=new Date(inputTime.substring(0,4),(inputTime.substring(5,7)-1),inputTime.substring(8,10));
}else{
		inputDate=new Date(inputTime.substring(0,4),(inputTime.substring(5,7)-1),inputTime.substring(8,10),inputTime.substring(11,13),inputTime.substring(14,16))
}
if(blnNowtimeAfter && !(nowDate - 24*3600000 <= inputDate)){
			alert("Sorry! Section \"" + strName + "\" enter the time necessary than it is now late");
			objname.focus();
			return false;
		}
if(!blnNowtimeAfter && !(nowDate>=inputDate)){
			alert("Sorry! Section \"" + strName + "\" input time earlier than it is now necessary")
			objname.focus();
			return false;
	}
return true;
}


// 用于在网页写出 Title
// titleName 要在 Title 栏中显示的内容

function createTitle(titleName){
  var strTable = "";
  strTable += "<table width='100%'  border='0' align='center' cellpadding='0' cellspacing='0' class='seachTitle_topmargin'>";
  strTable += "<tr>";
  strTable += "<td class='seachTitle'><table width='100%'  border='0' cellspacing='0' cellpadding='0' class='seachTitle'>";
  strTable += "<tr>";
  strTable += "<td width='4%' align='center' valign='middle'><img src='/teapot/images/icon_new_title01.gif' width='10' height='10' hspace='8' align='absmiddle'></td>";
  strTable += "<td width='92%'>"+ titleName +"</td>";
  strTable += "<td width='4%'><img src='/teapot/images/icon_new_title01.gif' width='10' height='10' hspace='8' align='absmiddle'></td>";
  strTable += "</tr>";
  strTable += "</table></td>";
  strTable += "</tr>";
  strTable += "</table>";
  document.write (strTable);
}

// 检查 Select 是否为空，不为空返回true,空返回false
function selectVerify(objText,strName){
  if (objText==null ||objText.value==null){ return true; }

  if(objText != null){
    if(objText.value != ""){
      return true;
    }
    alert("Part '"+ strName +"' can not be empty.");
    objText.focus();
    return false;
  }else{
    alert("Part '"+ strName +"' does not exist.");
    return false;
  }
}

function dateVerify(objText,blnEmpty,strFormat,strName){
  if (objText==null ||objText.value==null){ return true; }

//校验日期的有效性,有效返回true,无效返回false

  strText=objText.value;
  if(strText=="" && blnEmpty==false){
    alert("Part '"+ strName +"' can not be empty.");
    objText.focus();
    return false;
  }

  if(strText.length!=strFormat.length){
    alert("Part '"+ strName +"' is not formatted correctly, follow the '"+ strFormat +"' format for input date.");
    objText.focus();
    return false;
  }

  var i,j;
  var strFormatTemp;
  var strTemp;

  for(i=0;i<strText.length;){
  	strFormatTemp=strFormat.substring(i,i+4);
  	if(strFormatTemp=="yyyy" || strFormatTemp=="YYYY" ){
  	  strTemp=strText.substring(i,i+4);
  	  for(j=0;j<4;j++){
        if(strTemp.charCodeAt(j)<48 || strTemp.charCodeAt(j)>57){
          alert("Part '"+ strName +"' is not formatted correctly, follow the '"+ strFormat +"' format for input date.");
          objText.focus();
          return false;
        }
  	  }
  	  i=i+4;
    }else{
  	  strFormatTemp=strFormat.substring(i,i+2);
	  if(strFormatTemp=="MM" || strFormatTemp=="DD" || strFormatTemp=="dd" || strFormatTemp=="hh" || strFormatTemp=="mm" || strFormatTemp=="ss" ){
		  strTemp=strText.substring(i,i+2);
		  if(!str_numVerify(strTemp)){
			alert("Part '"+ strName +"' is not formatted correctly, follow the '"+ strFormat +"' format for input date.");
			objText.focus();
			return false;
		  }
	  if(strFormatTemp=="MM" && (strTemp>12 || strTemp<1)){
          alert("Part '"+ strName +"' of the month is entered incorrectly, follow the '"+ strFormat +"' format for input date.");
          objText.focus();
          return false;
	  }
	  if((strFormatTemp=="dd" || strFormatTemp=="DD")  && (strTemp>31 || strTemp<1)){
          alert("Part '"+ strName +"' input date is incorrect, according to '"+ strFormat +"' format for input date");
          objText.focus();
          return false;
	  }
	  if(strFormatTemp=="hh" && (strTemp>24 || strTemp<0)){
          alert("Part '"+ strName +"' The hour is entered incorrectly, follow the '"+ strFormat +"' format for input date.");
          objText.focus();
          return false;
	  }
	  if((strFormatTemp=="mm" || strFormatTemp=="ss" ) && (strTemp>59 || strTemp<0)){
          alert("Part '"+ strName +"' of minutes or seconds is entered incorrectly, follow the '"+ strFormat +"' format for input date.");
          objText.focus();
          return false;
	  }
  	  i=i+2;
    }else{
      if(strText.substring(i,i+1)!=strFormat.substring(i,i+1)){
        alert("Part '"+ strName +"' is not formatted correctly, follow the '"+ strFormat +"' format for input date.");
        objText.focus();
        return false;
      }
  	  i=i+1;
    }
    }
  }
  return true;
}

function initBusiMgrSelect(selectObjName, empArray, defaultValue){

    var curEmpSelect = document.getElementById(selectObjName);

       curEmpSelect.length = empArray.length + 1;

       curEmpSelect[0] = new Option("--Please select the business manager--","");

    for (i=0; i<empArray.length; i++) {

              curEmpSelect[i+1] = new Option(empArray[i][3],empArray[i][2]);
              if (curEmpSelect[i+1].value == defaultValue)
      			  curEmpSelect[i+1].selected = true;

       }

}
function changeshow(id,imgid){
	obj=new Object();
	//alert(document.all[id]);
	obj=document.all[id];
	if(obj.style.display==''){
			obj.style.display='none';
			imgid.src='../images/icon_new_title02_down.gif';
		}else{
			obj.style.display='';
			imgid.src='../images/icon_new_title02_up.gif';
			}
	}

function Path(){
		if(parent.path.rows!='34,*'){
				parent.path.rows='34,*';
			}
	}
function hiddenPath(){
		parent.path.rows='0,*';
	}


//创建一个select级联菜单
//@default_empId为当前用户id;
//@objname需要创建的对象附加名(一般用于同一页面有多个级联选单);
//@arrayName传入数组的附加名(与orgtree方法对应);

function SelectOrgTreeVerify(strName,blnEmpty,objname){
	objname==null?objname="":'';
	areaStr="areaId"+objname;
	deptStr="deptId"+objname;
	empStr="employeeId"+objname;
	//alert(areaStr);
	areaObj=document.getElementById(areaStr);
	deptObj=document.getElementById(deptStr);
	empObj=document.getElementById(empStr);
	//alert(areaObj.value);
	//alert(!blnEmpty && (areaObj.value=="" || deptObj.value==""  || empObj.value==""));
	if(deptObj!=null && empStr!=null){
		if(!blnEmpty && (areaObj.value=="" || deptObj.value==""  || empObj.value=="")){
		alert("Part '"+ strName +"' can not be empty, please select the appropriate personnel！‘");
		areaObj.value==""?areaObj.focus():'';
		deptObj.value==""?deptObj.focus():'';
		empObj.value==""?empObj.focus():'';
		}
		return false;
	}
	if(areaObj!=null && areaObj.value=="" ){
		alert("Part '"+ strName +"' can not be empty, please select the appropriate personnel！‘");
		areaObj.value==""?areaObj.focus():'';
		return false;
	}
	return true;
}

//赵国辉修改　2005.3.18，增加了后面2个参数，如果默认人员不在有权限的数组里，则默认公司采用参数传递的值
//如果人员在数组里，就取默认人员的公司和部门
function SelectOrgTree(default_empId,objname,arrayName,btnSelect,default_area,default_dept){

		tObj=conList(arrayName);
		areaArray_n=tObj.areaArray;
		deptArray_n=tObj.deptArray;
		empArray_n=tObj.empArray;
		//var btnSelected=false;
		//if((objname || !objname) && arrayName==null && btnSelect==null ){
			//objname?btnSelected=true:btnSelected=false;
			//objname==null
		//}
		//if((objname || !objname) && arrayName==null && btnSelect=null ){
			//objname?btnSelected=true:btnSelected=false;
			//objname==null
		//}
		objname==null?objname="":"";
		idObj="SelectOrgTree"+objname;

		document.write("<table><tr><td id='"+idObj+"'></td></tr></table>");
		if(objname==""){
			areaObj=document.createElement(strElement("areaId","areaId_OnChange",objname,arrayName));
			deptObj=document.createElement(strElement("deptId","deptId_OnChange",objname,arrayName));
			empObj=document.createElement(strElement("employeeId","",objname,arrayName));
		}else{
			areaObj=document.createElement(strElement("AreaId","areaId_OnChange",objname,arrayName));
			deptObj=document.createElement(strElement("DeptId","deptId_OnChange",objname,arrayName));
			empObj=document.createElement(strElement("EmployeeId","",objname,arrayName));
		}
		document.all[idObj].appendChild(areaObj);
		document.all[idObj].appendChild(deptObj);
		document.all[idObj].appendChild(empObj);

		default_empId==null?default_empId="":'';
  	if(default_empId!=""){
		  peson=initId(default_empId,arrayName);
		  if (peson.areaNum != null) default_area = peson.areaNum;
		  if (peson.deptNum != null) default_dept = peson.deptNum;
		}



	//初始化公司列表

	areaObj[0]=new Option("--Please Select Company--","");
	for(i=0;i<areaArray_n.length;i++){
		areaObj[i+1]=new Option(areaArray_n[i][1],areaArray_n[i][0]);
		if(default_area != null && default_area != ""){
			if(areaArray_n[i][0]==default_area){
				areaObj[i+1].selected = true;
			}
		}
	}

	//初始化部门列表
	deptObj[0]=new Option("--Select department--","");
	for(i=0;i<deptArray_n.length;i++){
			deptObj[i+1]=new Option(deptArray_n[i][2],deptArray_n[i][1]);
			if(deptArray_n[i][1]==default_dept ){
				deptObj[i+1].selected = true;
			}
	}

	//初始化人员列表；
	empObj[0]=new Option("--Please select staff--","");
		for(i=0,j=0;i<empArray_n.length;i++){
			if(empArray_n[i][1]==default_dept && empArray_n[i][0]==default_area ){
				empObj[j+1]=new Option(empArray_n[i][3],empArray_n[i][2]);
				btnSelect&&empArray_n[i][2]==default_empId?empObj[j+1].selected=true:"";
				j++;
			}
	}

	peson=null;
	tObj=null;
	areaArray_n=null;
	deptArray_n=null;
	empArray_n=null;
	areaObj=null;
	deptObj=null;
	empObj=null;
}


//Start Add by huangliang 2004-7-15
//根据数组的后缀名转化数组，要与orgtree配合使用
function conList(arrayName){
	ListObj=new Object();
	//alert(arrayName);
	if(arrayName==null || arrayName==""){
		ListObj.areaArray=eval("areaArray");
		ListObj.deptArray=eval("deptArray");
		ListObj.empArray=eval("empArray");
	}else{
		ListObj.areaArray=eval(arrayName+"AreaArray");
		ListObj.deptArray=eval(arrayName+"DeptArray");
		ListObj.empArray=eval(arrayName+"EmpArray");		
	}	
	return ListObj;
}

//创建只有一个下接框的组合下接框；
//@default_empId为当前用户id;
//@objname需要创建的对象附加名(一般用于同一页面有多个级联选单);
//@arrayName传入数组的附加名(与orgtree方法对应);
function SelectOrgTreeAll(default_empId,objname,arrayName){

		tObj=conList(arrayName);
		areaArray_n=tObj.areaArray;
		deptArray_n=tObj.deptArray;
		empArray_n=tObj.empArray;

		objname==null?objname="":"";
		idObj="OrgTreeAll"+objname;
		document.write("<table><tr><td id='"+idObj+"'></td></tr></table>");
		areaObj=document.createElement(strElement("areaID","",objname,arrayName));
		document.all[idObj].appendChild(areaObj);
		default_empId==null?default_empId="":'';

		areaObj[0]=new Option("--Please select--","");

		for(i=0;i<empArray_n.length;i++){
			tPeson=initId(empArray_n[i][2],arrayName);
			tName=tPeson.areaname+"    "+tPeson.deptname+"    "+empArray_n[i][3];
			tValue=empArray_n[i][2];
			areaObj[i+1]=new Option(tName,tValue);
			if(tValue==default_empId && default_empId!="" ){
				areaObj[i+1].selected=true;
			}
		}

	tObj=null;
	areaArray_n=null;
	deptArray_n=null;
	empArray_n=null;
}

//初始化，或得到人员的相关属性；
//@default_empId为当前用户id;
//@arrayName传入数组的附加名(与orgtree方法对应);
function initId(default_empId,arrayName){
	person=new Object();
	for(j=0;j<empArray_n.length;j++){
		if(empArray_n[j][2]==default_empId){
			person.deptNum=empArray_n[j][1];
			person.areaNum=empArray_n[j][0];
			person.name=empArray_n[j][3];
			break;
		}
	}
	for(k=0;k<deptArray_n.length;k++){
		if(deptArray_n[k][1]==person.deptNum){
			person.deptname=deptArray_n[k][2];
			k++;
			break;
		}
	}
	for(m=0;m<areaArray_n.length;m++){
		if(areaArray_n[m][0]==person.areaNum){
			person.areaname=areaArray_n[m][1];
			m++;
			break;
		}
	}
	//alert(person.areaname);
	return person;
}

function strElement(str,changeAction,objname,arrayName){
	objname==null?objname='':'';
	arrayName==null?arrayName='':'';
	id=objname+str;
	//alert(id);
	changeAction==null?changeAction="":changeAction+="(\""+objname+"\",\""+arrayName+"\")";
	str="<select id='"+ id +"' name='" + id +"' "+(changeAction!=""?("onchange='"+changeAction+"'"):'')+">";
	//alert(str);
	return str;
}

//区域选择变化时的部门框的变化方法
function areaId_OnChange(objname,arrayName){

	tObj=conList(arrayName);
	areaArray_n=tObj.areaArray;
	deptArray_n=tObj.deptArray;
	empArray_n=tObj.empArray;
	if(objname!=""){
		areaStr=objname+"AreaId";
		deptStr=objname+"DeptId";
	}else{
		areaStr="areaId";
		deptStr="deptId";
	}
	areaObj=document.getElementById(areaStr);
	deptObj=document.getElementById(deptStr);
	deptObj[0]=new Option("--Select department--","");
	for(i=0,j=1;i<deptArray_n.length;i++){
		if(deptArray_n[i][0]==areaObj.value){
			deptObj[j]=new Option(deptArray_n[i][2],deptArray_n[i][1]);
			j++;
		}
		deptObj.length=j;
	}
	deptId_OnChange(objname);

	tObj=null;
	areaArray_n=null;
	deptArray_n=null;
	empArray_n=null;
}

//部门选择变化时的人员的变化方法
function deptId_OnChange(objname,arrayName){

	tObj=conList(arrayName);
	areaArray_n=tObj.areaArray;
	deptArray_n=tObj.deptArray;
	empArray_n=tObj.empArray;

	if(objname!=""){
		deptStr=objname+"DeptId";
		empStr=objname+"EmployeeId";
	}else{
		deptStr="deptId";
		empStr="employeeId";
	}

	deptObj=document.getElementById(deptStr);
	empObj=document.getElementById(empStr);
	empObj[0]=new Option("--Please select staff--","");
	for(i=0,j=1;i<empArray_n.length;i++){
		if(empArray_n[i][1]==deptObj.value){
			empObj[j]=new Option(empArray_n[i][3],empArray_n[i][2]);
			j++;
		}
		empObj.length=j;
	}

	tObj=null;
	areaArray_n=null;
	deptArray_n=null;
	empArray_n=null;
}
// End Add by huangliang 2004-7-15

//Start Add by huangliang 2004-7-30
//创建一个由输入框组成的文件上传输入框

function createFileUpdate(objname){
document.write("<input name='file_text' type='text' style='width:120px' readonly>  <input type='button' class='button'  onMouseOver='fclick("+objname+")' value='浏 览...' name='fileButton'><input name='"+objname+"' type='file'  style='position:relative;filter:alpha(opacity=0);width:30px;' onchange='file_text.value=this.value'>");
}
function fclick(obj){
  with(obj){
    style.posTop=0;
	x=event.srcElement.offsetWidth;
	style.posLeft=-x-2;
	}
}

//End Add by huangliang 2004-7-30


//设置Iframe高度为整个文档的高度
//@ objName 为iframe 的名字
//add by huangliang 2004-09-01
var timeAction;
function autoFitIframeHeight(objName) {	
		
		var lastHeight;
		var $objIframe,$objName;
		var hollycrmIframeobj;	
		
		$objName=eval(objName);
		$objIframe=document.all[objName];		
    setIframeHeight();
    lastHeight= $objName.document.body.scrollHeight;
    timeAction=setInterval(getIframeHeight,10);
     function setIframeHeight(){		
    			$objIframe.height=$objName.document.body.scrollHeight + 18 ;    
		}
    function getIframeHeight(){
       if($objName.document.body.scrollHeight!=lastHeight) {
       	//alert(1);
           setIframeHeight();
            lastHeight= $objName.document.body.scrollHeight;
            }
        }    
   
}

//校验百分比是否合法
//@ objname为输入框对象名
//@ blnPassHundred 布尔值为false表示不能大于100%;否则表示可以大于100%
//@ strName为栏目名称

function percentVerify(objname,blnPassHundred,strName){
		if(!doubleVerify(objname,false,6,2,strName)){
			return false;
		}
		if(objname.value/100>1 && !blnPassHundred ){
				alert("Section \"" + strName + "\" input value can not exceed 100%");
			objname.focus();
			return false;
		}
		return true;
}

-->
