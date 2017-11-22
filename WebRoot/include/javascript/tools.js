<!--
//to replace all string1 with string2 in a fixed string.
function replaceString(s,s1,s2){
  if(s.indexOf(s1)>=0){
    s=s.substring(0,s.indexOf(s1))+s2+s.substring(s.indexOf(s1)+s1.length);
    s=replaceString(s,s1,s2);
  }
  return s;
}

//��HTML���ת��Ϊʵ�ʶ�Ӧ���ַ����õ��������ַ���
function getRealString(s){
  s=replaceString(s,"&lt;","<");
  s=replaceString(s,"&gt;",">");
  s=replaceString(s,"&amp;","&");
  s=replaceString(s,"&quot;","\"");

  return s;
}

//�ж��ַ����Ƿ����������
function str_numVerify(str){
	rStr=/[^0-9]/g;
	str=str.replace(rStr,"*!@#$%");
	if(str.indexOf("*!@#$%")!=-1){
		return false;
	}
	return true;
}

//Ϊ�˽��xp sp2�²��ܹ��������⣬�����е�window.open�滻�����������
function openwin_xp2(url, winName, p1, p2, p3, p4, p5, p6, p7){
  if (p1 == "fullscreen")
    return window.open(url,winName,p1);
  else
    return openwin(url, winName);
}

//����ṩ�������ƣ���ֻ��һ�����ڣ�����ÿ�δ�һ���´���
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

//�ж��Ƿ���������
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

//�ж��Ƿ���������,����ʾ
function isInteger(inputVal, strName){
  inputStr=inputVal.toString();

  for(var i=0;i<inputStr.length;i++) {
    var oneChar=inputStr.charAt(i);
//    if(i==0&&oneChar=="0")
//      return false;
    if(oneChar<"0"||oneChar>"9"){
      alert("��Ŀ��"+strName+"��ֻ����д����0������");
      return false;
    }
  }
  return true;
}

//�ж��Ƿ��ǿ��ַ�����ո��ַ���
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

//�ж�EAMIL�Ƿ�Ϸ�
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
	  alert("��Ŀ��"+strName+"������Ϊ�ա�");
	  objEmail.focus();
	  return false;
	  }
 if( emailAdd!="" && !check_email(emailAdd)){
	  	alert("��Ŀ��"+strName+"����ʽ����ȷ!\n�밴�������¸�ʽ:zonghua@126.com");
		objEmail.focus();
		return false;
	  }
  return true;
}
//�õ��ַ������ֽڳ���
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

//У��绰����¼�����Ч��,��Ч����true,��Ч����false
	strText=objText.value;
	if(strText=="" && blnEmpty==false ){
		alert("��Ŀ��"+strName+"��������д!");
	  	objText.focus();
      	return false;
	}else if(strText!=""){
		if(strText.length > intLength){
			alert("��Ŀ��"+strName+"��������ָ���ĳ���("+intLength+"�ַ�)���������");
	  		objText.focus();
      		return false;
		}
		sStr=/[0-9]/g;
		bln=strText.match(sStr);
		if(bln==null){
			alert("��Ŀ��"+strName+"���������������!");
	  		objText.focus()
      		return false;
		}
	}
 return true;
}


function textVerify(objText,blnEmpty,intLength,strName ){
//У���ı�¼�����Ч��,��Ч����true,��Ч����false
 //������󲻴��ڣ���������飬ֱ�ӷ�����
  if (objText==null ||objText.value==null){ return true; }
  
//objTextΪ��У���ҳ�������blnEmpty��ʾ���ı��Ƿ��Ϊ��,true��ʾ��Ϊ�գ�false��ʾ����Ϊ�գ�
//intLength����ֽڳ��ȣ�strNameУ��Ķ�����Ŀ������
  var strText=objText.value;
  var regStr=/\s/g;
  var newText=strText.replace(regStr,"");
  if((strText=="" || newText.length ==0) && blnEmpty==false){
    alert("��Ŀ��"+strName+"������Ϊ�ա�");
    objText.focus();
    return false;
  }
  if(calculateStrLen(strText) > intLength){
    alert("��Ŀ��"+strName+"��������ָ���ĳ���("+intLength+"�ַ�)���������");
    objText.focus();
    return false;
  }
  
  return true;
}

function charVerify(objText,blnEmpty,intLength,strName ){
  if (objText==null ||objText.value==null){ return true; }

//У�鴿��ĸ�ı�¼�����Ч��,��Ч����true,��Ч����false
  var strText=objText.value;
  if(strText=="" && blnEmpty==false){
    alert("��Ŀ��"+strName+"������Ϊ�ա�");
    objText.focus();
    return false;
  }
  var i;
  for(i=0;i<strText.length;i++){
    if(strText.charCodeAt(i)!=32){
      if(strText.charCodeAt(i)<65 || ( strText.charCodeAt(i)>90 && strText.charCodeAt(i)<97 )  ||  strText.charCodeAt(i)>122 ){
        alert("��Ŀ��"+strName+"��ֻ����д��ĸ��");
        objText.focus();
        return false;
      }
    }
  }
  if(strText.length > intLength){
    alert("��Ŀ��"+strName+"��������ָ���ĳ���("+intLength+"�ַ�)���������");
    objText.focus();
    return false;
  }
  return true;
}

function char_numVerify(objText,blnEmpty,intLength,strName ){
  if (objText==null ||objText.value==null){ return true; }

//У�鴿��ĸ�������ı�¼�����Ч��,��Ч����true,��Ч����false
  var strText=objText.value;
  if(strText=="" && blnEmpty==false){
    alert("��Ŀ��"+strName+"������Ϊ�ա�");
    objText.focus();
    return false;
  }
  var i;
  for(i=0;i<strText.length;i++){
    if(strText.charCodeAt(i)!=32){
      if(strText.charCodeAt(i)<48 || ( strText.charCodeAt(i)>57 && strText.charCodeAt(i)<65 )  || ( strText.charCodeAt(i)>90 && strText.charCodeAt(i)<97 )  ||  strText.charCodeAt(i)>122 ){
        alert("��Ŀ��"+strName+"��ֻ����д��ĸ�����֡�");
        objText.focus();
        return false;
      }
    }
  }
  if(strText.length > intLength){
    alert("��Ŀ��"+strName+"��������ָ���ĳ���("+intLength+"�ַ�)���������");
    objText.focus();
    return false;
  }
  return true;
}

function numberVerify(objText,blnEmpty,intLength,strName ){
  if (objText==null ||objText.value==null){ return true; }

//У�鴿�����ı�¼�����Ч��,��Ч����true,��Ч����false
  var strText=objText.value;
  if(strText=="" && blnEmpty==false){
    alert("��Ŀ��"+strName+"������Ϊ�ա�");
    objText.focus();
    return false;
  }
  var i;
  for(i=0;i<strText.length;i++){
    if((strText.charCodeAt(i)<48 || strText.charCodeAt(i)>57)){
      alert("��Ŀ��"+strName+"��ֻ����д���֡�");
      objText.focus();
      return false;
    }
  }
  if(strText.length > intLength){
    alert("��Ŀ��"+strName+"��������ָ���ĳ���("+intLength+"�ַ�)���������");
    objText.focus();
    return false;
  }
  return true;
}

function intVerify(objText,blnEmpty,intLength,strName ){
  if (objText==null ||objText.value==null){ return true; }

//У�������ı�¼�����Ч��,��Ч����true,��Ч����false
  var strText=objText.value;
  if(strText=="" && blnEmpty==false){
    alert("��Ŀ��"+strName+"������Ϊ�ա�");
    objText.focus();
    return false;
  }
  var i;
  for(i=0;i<strText.length;i++){
    if((strText.charCodeAt(i)<48 || strText.charCodeAt(i)>57)){
      alert("��Ŀ��"+strName+"��ֻ����д������");
      objText.focus();
      return false;
    }
  }
  if(strText.length > intLength){
    alert("��Ŀ��"+strName+"��������ָ���ĳ���("+intLength+"�ַ�)���������");
    objText.focus();
    return false;
  }
  return true;
}

function doubleVerify(objText,blnEmpty,totalLen,decimalLen,strName ){
  if (objText==null ||objText.value==null){ return true; }

//У���С�������ı�¼�����Ч��,��Ч����true,��Ч����false
  var strText=objText.value;
  if(strText=="" && blnEmpty==false){
    alert("��Ŀ��"+strName+"������Ϊ�ա�");
    objText.focus();
    return false;
  }
  var i;
  for(i=0;i<strText.length;i++){
    if((strText.charCodeAt(i)<48 || strText.charCodeAt(i)>57) &&  strText.charCodeAt(i)!=46&&  strText.charCodeAt(i)!=69){
      alert("��Ŀ��"+strName+"��ֻ����д���֡�");
      objText.focus();
      return false;
    }
  }

  var point=strText.indexOf('.');
  if( point==-1){
    if(strText.length > totalLen-decimalLen){
      alert("��Ŀ��"+strName+"����������ֹ����������");
      objText.focus();
      return false;
    }
  }else{
    if(point > totalLen-decimalLen){
      alert("��Ŀ��"+strName+"����������ֹ����������");
      objText.focus();
      return false;
    }
    if(strText.length-(point+1) > decimalLen){
      alert("��Ŀ��"+strName+"����������ֵ�С����λ��ֻ����"+decimalLen+"λ���������");
      objText.focus();
      return false;
    }
  }
  return true;
}

function dateOrderVerify(objStart,objEnd,blnEmpty,strStartName,strEndName ){
//У��������ڵ���Ч��,��Ч����true,��Ч����false
  var strStart=objStart.value;
  var strEnd=objEnd.value;
  if(blnEmpty){
    if(strStart=="" || strEnd==""){
      return true;
    }
  }else{
    if(strStart==""){
      alert("��Ŀ��"+strStartName+"������Ϊ�ա�");
      objStart.focus();
      return false;
    }
    if(strEnd==""){
      alert("��Ŀ��"+strEndName+"������Ϊ�ա�");
      objEnd.focus();
      return false;
    }
  }

  var intStart=(strStart.substring(0,4))*10000+(strStart.substring(5,7))*100+(strStart.substring(8,10));
  var intEnd=(strEnd.substring(0,4))*10000+(strEnd.substring(5,7))*100+(strEnd.substring(8,10));
  if(intStart>intEnd){
    alert("��Ŀ��"+strStartName+"��ָ�������ڲ��ܱ���Ŀ��"+strEndName+"��ָ����������");
    objStart.focus();
    return false;
  }else{
    return true;
  }
}


//����������������������ڽ��бȽ�
//@objname ��Ҫ����������������
//@blnNowtimeAfter ����ֵ true��ʾ�����ʱ��Ҫ������ڵ�ʱ����
//@strName	��Ŀ����
// add by huangliang 2004-09-01

function dateCompareNowtimeVerify(objname,blnNowtimeAfter,strName){
  if (objname==null ||objname.value==null){ return true; }

var inputTime=objname.value;
//�ݲ��Ǳ�����
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
			alert("�Բ�����Ŀ\""+strName+"\"�����ʱ���������ڵ�ʱ����");
			objname.focus();
			return false;
		}
if(!blnNowtimeAfter && !(nowDate>=inputDate)){
			alert("�Բ�����Ŀ\""+strName+"\"�����ʱ���������ڵ�ʱ����")
			objname.focus();
			return false;
	}
return true;
}


// ��������ҳд�� Title
// titleName Ҫ�� Title ������ʾ������

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

// ��� Select �Ƿ�Ϊ�գ���Ϊ�շ���true,�շ���false
function selectVerify(objText,strName){
  if (objText==null ||objText.value==null){ return true; }

  if(objText != null){
    if(objText.value != ""){
      return true;
    }
    alert("��Ŀ��"+strName+"������Ϊ�ա�");
    objText.focus();
    return false;
  }else{
    alert("��Ŀ��"+strName+"�������ڡ�");
    return false;
  }
}

function dateVerify(objText,blnEmpty,strFormat,strName){
  if (objText==null ||objText.value==null){ return true; }

//У�����ڵ���Ч��,��Ч����true,��Ч����false

  strText=objText.value;
  if(strText=="" && blnEmpty==false){
    alert("��Ŀ��"+strName+"������Ϊ�ա�");
    objText.focus();
    return false;
  }

  if(strText.length!=strFormat.length){
    alert("��Ŀ��"+strName+"���ĸ�ʽ����ȷ,�밴�ա�"+strFormat+"����ʽ�������ڡ�");
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
          alert("��Ŀ��"+strName+"���ĸ�ʽ����ȷ,�밴�ա�"+strFormat+"����ʽ�������ڡ�");
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
			alert("��Ŀ��"+strName+"���ĸ�ʽ����ȷ,�밴�ա�"+strFormat+"����ʽ�������ڡ�");
			objText.focus();
			return false;
		  }
	  if(strFormatTemp=="MM" && (strTemp>12 || strTemp<1)){
          alert("��Ŀ��"+strName+"�����·����벻��ȷ,�밴�ա�"+strFormat+"����ʽ�������ڡ�");
          objText.focus();
          return false;
	  }
	  if((strFormatTemp=="dd" || strFormatTemp=="DD")  && (strTemp>31 || strTemp<1)){
          alert("��Ŀ��"+strName+"�����������벻��ȷ,�밴�ա�"+strFormat+"����ʽ�������ڡ�");
          objText.focus();
          return false;
	  }
	  if(strFormatTemp=="hh" && (strTemp>24 || strTemp<0)){
          alert("��Ŀ��"+strName+"����Сʱ���벻��ȷ,�밴�ա�"+strFormat+"����ʽ�������ڡ�");
          objText.focus();
          return false;
	  }
	  if((strFormatTemp=="mm" || strFormatTemp=="ss" ) && (strTemp>59 || strTemp<0)){
          alert("��Ŀ��"+strName+"���ķ��ӻ������벻��ȷ,�밴�ա�"+strFormat+"����ʽ�������ڡ�");
          objText.focus();
          return false;
	  }
  	  i=i+2;
    }else{
      if(strText.substring(i,i+1)!=strFormat.substring(i,i+1)){
        alert("��Ŀ��"+strName+"���ĸ�ʽ����ȷ,�밴�ա�"+strFormat+"����ʽ�������ڡ�");
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

       curEmpSelect[0] = new Option("--��ѡ��ҵ����--","");

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


//����һ��select�����˵�
//@default_empIdΪ��ǰ�û�id;
//@objname��Ҫ�����Ķ��󸽼���(һ������ͬһҳ���ж������ѡ��);
//@arrayName��������ĸ�����(��orgtree������Ӧ);

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
		alert("��Ŀ��"+strName+"��������Ϊ�գ���ѡ����Ӧ����Ա����");
		areaObj.value==""?areaObj.focus():'';
		deptObj.value==""?deptObj.focus():'';
		empObj.value==""?empObj.focus():'';
		}
		return false;
	}
	if(areaObj!=null && areaObj.value=="" ){
		alert("��Ŀ��"+strName+"��������Ϊ�գ���ѡ����Ӧ����Ա����");
		areaObj.value==""?areaObj.focus():'';
		return false;
	}
	return true;
}

//�Թ����޸ġ�2005.3.18�������˺���2�����������Ĭ����Ա������Ȩ�޵��������Ĭ�Ϲ�˾���ò������ݵ�ֵ
//�����Ա���������ȡĬ����Ա�Ĺ�˾�Ͳ���
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



	//��ʼ����˾�б�

	areaObj[0]=new Option("--��ѡ��˾--","");
	for(i=0;i<areaArray_n.length;i++){
		areaObj[i+1]=new Option(areaArray_n[i][1],areaArray_n[i][0]);
		if(default_area != null && default_area != ""){
			if(areaArray_n[i][0]==default_area){
				areaObj[i+1].selected = true;
			}
		}
	}

	//��ʼ�������б�
	deptObj[0]=new Option("--��ѡ����--","");
	for(i=0;i<deptArray_n.length;i++){
			deptObj[i+1]=new Option(deptArray_n[i][2],deptArray_n[i][1]);
			if(deptArray_n[i][1]==default_dept ){
				deptObj[i+1].selected = true;
			}
	}

	//��ʼ����Ա�б�
	empObj[0]=new Option("--��ѡ����Ա--","");
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
//��������ĺ�׺��ת�����飬Ҫ��orgtree���ʹ��
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

//����ֻ��һ���½ӿ������½ӿ�
//@default_empIdΪ��ǰ�û�id;
//@objname��Ҫ�����Ķ��󸽼���(һ������ͬһҳ���ж������ѡ��);
//@arrayName��������ĸ�����(��orgtree������Ӧ);
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

		areaObj[0]=new Option("--��ѡ��--","");

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

//��ʼ������õ���Ա��������ԣ�
//@default_empIdΪ��ǰ�û�id;
//@arrayName��������ĸ�����(��orgtree������Ӧ);
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

//����ѡ��仯ʱ�Ĳ��ſ�ı仯����
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
	deptObj[0]=new Option("--��ѡ����--","");
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

//����ѡ��仯ʱ����Ա�ı仯����
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
	empObj[0]=new Option("--��ѡ����Ա--","");
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
//����һ�����������ɵ��ļ��ϴ������

function createFileUpdate(objname){
document.write("<input name='file_text' type='text' style='width:120px' readonly>  <input type='button' class='button'  onMouseOver='fclick("+objname+")' value='� ��...' name='fileButton'><input name='"+objname+"' type='file'  style='position:relative;filter:alpha(opacity=0);width:30px;' onchange='file_text.value=this.value'>");
}
function fclick(obj){
  with(obj){
    style.posTop=0;
	x=event.srcElement.offsetWidth;
	style.posLeft=-x-2;
	}
}

//End Add by huangliang 2004-7-30


//����Iframe�߶�Ϊ�����ĵ��ĸ߶�
//@ objName Ϊiframe ������
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

//У��ٷֱ��Ƿ�Ϸ�
//@ objnameΪ����������
//@ blnPassHundred ����ֵΪfalse��ʾ���ܴ���100%;�����ʾ���Դ���100%
//@ strNameΪ��Ŀ����

function percentVerify(objname,blnPassHundred,strName){
		if(!doubleVerify(objname,false,6,2,strName)){
			return false;
		}
		if(objname.value/100>1 && !blnPassHundred ){
				alert("��Ŀ\""+strName+"\"�����ֵ���ܳ���100%");
			objname.focus();
			return false;
		}
		return true;
}

-->
