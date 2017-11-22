<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<jsp:useBean id="securityStaffBaseinfo" scope="request"
	class="com.tianjian.security.struts.form.SecurityStaffBaseinfoForm" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%
			if (request.getServerPort() == 80) {
		%>
		<base
			href="http://<%=request.getServerName()%><%=request.getContextPath()%>/" />
		<%
			} else {
		%>
		<base
			href="http://<%=request.getServerName()%>:<%=request.getServerPort()%><%=request.getContextPath()%>/" />
		<%
			}
		%>
		<title><bean:message
				key="security.jsp.securitystaffbaseinfo.update.title"
				bundle="security" />
		</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<meta http-equiv="pragma" content="no-cache" />
		<meta http-equiv="cache-control" content="no-cache" />
		<meta http-equiv="expires" content="0" />
		<script language="javascript" src="<bean:message key="include.js.TJMessage.path" bundle="security" />"></script>
		<script language="javascript" src="include/javascript/eventOnKeyPress.js"></script>
		<script language="javascript" src="<bean:message key="include.js.time.path" bundle="security" />"></script>
		<link type="text/css" rev="stylesheet" rel="stylesheet" href="include/css/form.css" />
		<script language="javascript" src="<bean:message key="includes.js.validator.path" bundle="security" />" defer="defer"></script>
		<script language="javascript" src="<bean:message key="security.js.gettext_staff.path" bundle="security" />"></script>
		<script type="text/javascript" src="security/include/javascript/jianbian.js"></script>
		<link rel="stylesheet" href="include/css/open.css" />
		<script type="text/javascript" src="include/javascript/utrim.js"></script>
		
		<link rel="stylesheet" href="include/calendar/theme.css" />
		<script src="include/calendar/calendar.js"></script>
		<script src="include/calendar/calendar-setup.js"></script>
		<script src="include/calendar/calendar-zh.js"></script>
		
		<script language="javascript">
			function huiche(){
				if(event.keyCode==13){
						event.keyCode=9
					}
			}
			function saveForm(){	
				if(!Validator.Validate(document.forms.form,3)){
      				return ;
   				}
			
				if(document.form.hspConfigBaseinfoName.value == ""){
					alert('<bean:message key="security.jsp.securitystaffbaseinfo.update.warn1" bundle="security"/>');
				 	return ;
				}	
				if(document.form.name.value == ""){
					alert('<bean:message key="security.jsp.securitystaffbaseinfo.update.warn2" bundle="security"/>');
				 	return ;
				}	
				if(document.form.email.value == "") {
					alert('<bean:message key="security.jsp.findpassword.warn1" bundle="security"/>');
  					return ;
  				}else{
  					if(!regIsEmail(document.form.email.value)){ 
						alert('<bean:message key="security.jsp.findpassword.warn2" bundle="security"/>');
						return ;
 			    	}			
  				}	
				if(document.form.comments.value.length>50){
					alert('<bean:message key="security.jsp.securitystaffbaseinfo.update.warn3" bundle="security"/>');
				 	return ;
				}
				if(document.form.idNo.value != ""){		
					if(checkIdcard() == false){
					return;
					}
				}		
				//修改
				 if (confirmMessage("<bean:message key='comm.jsp.update.gengxin' bundle='comm.commLocale'/>")){     
				    document.form.verbId.value = "update";    
				    document.form.submit(); 
				    }   
			}
			function regIsEmail(fData){
	       	 	var reg = new RegExp(/^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/);
	       		return reg.test(fData);
    		}
			function newXMLHttpRequest() {
				var xmlreq = false;
				if (window.XMLHttpRequest) {
					xmlreq = new XMLHttpRequest();		
				} else if (window.ActiveXObject) {
				    try {
			      		xmlreq = new ActiveXObject("Msxml2.XMLHTTP");
					} catch (e1) {
						try {
			        		xmlreq = new ActiveXObject("Microsoft.XMLHTTP");
						} catch (e2) {
						xmlreq = false;
				    	}
			    	}
				}	
				return xmlreq;
			}		
			function getReadyStateHandler(req, responseXmlHandler) {
				return function () {
					if (req.readyState == 4) {
			      		if (req.status == 200) {
			      			//alert(req.responseText); 
						    responseXmlHandler(req.responseXML);
						} else {
						    alert("HTTP error: " + req.status);
			      		}
			    	}
			  	}
			}
			<!--处理科室-->
			function setHspDept(url){
				var hspConfigBaseinfoId = document.getElementById("hspConfigBaseinfoId").value;
				var xmlHttp = newXMLHttpRequest();
				var sendTo = url + "?verbId=setHspDeptBaseinfo&hspConfigBaseinfoId=" + hspConfigBaseinfoId;
				xmlHttp.open("GET", sendTo, true);
				var handlerFunction = getReadyStateHandler(xmlHttp, updateHspDept);
				xmlHttp.onreadystatechange = handlerFunction;
				xmlHttp.send(null);				
			}
			<!--更新科室-->
			function updateHspDept(deptXML) {
				var dept = document.getElementById("hspDeptBaseinfoId");
				while (dept.options.length) {
			        dept.remove(0);
			    }
			    
				var indexObj = deptXML.getElementsByTagName("index")[0];
				var index = indexObj.childNodes[0].nodeValue;
				for (var i = 0; i < index; i++) {
					var keyObj = deptXML.getElementsByTagName("key")[i];
					var valueObj = deptXML.getElementsByTagName("value")[i];
					var newElem = document.createElement("option");
					if(valueObj.childNodes[0] == null){
						newElem.text = "" ;
					} else {
						newElem.text = valueObj.childNodes[0].nodeValue ;
					}
					if(keyObj.childNodes[0] == null){
						newElem.value = "" ;
					} else {
						newElem.value = keyObj.childNodes[0].nodeValue ;
					}
					dept.add(newElem);
				}
			}
		</script>
		<script language="javascript">
	 		function trim(str){
	 			return str.replace(/(^\s*)|(\s*$)/g, "");
			} 
			function checkId(){
			var id;
			id = trim(document.form.idNo.value);
			var stry="";
			var strm="";
			var strd="";
			var lastStr="";
			if(id.length == 18){
				stry=id.substr(6, 4);
				strm=id.substr(10, 2);
				strd=id.substr(12, 2);
				strsex=id.substr(16, 1);
				if(checkDate(stry,strm,strd) && checkIdcard()!=false){
					document.form.year.value = stry;
					document.form.month.value = strm;
					document.form.day.value = strd;
					if(strsex%2==0){
						document.form.commConfigSexId.value=2;
					}else{
						document.form.commConfigSexId.value=1;
					}
					document.form.year.focus();
				}
				}
				if(id.length == 15){
			    document.form.year.focus();
			}
		}
       function checkDate(y,m,d) 
		{ 
			var dateElement = y+"-"+m+"-"+d;
			var pattern=/^((((1[6-9]|[2-9]\d)\d{2})-(0?[13578]|1[02])-(0?[1-9]|[12]\d|3[01]))|(((1[6-9]|[2-9]\d)\d{2})-(0?[13456789]|1[012])-(0?[1-9]|[12]\d|30))|(((1[6-9]|[2-9]\d)\d{2})-0?2-(0?[1-9]|1\d|2[0-8]))|(((1[6-9]|[2-9]\d)(0[48]|[2468][048]|[13579][26])|((16|[2468][048]|[3579][26])00))-0?2-29-))$/; 
			if(pattern.test(dateElement)){
				return true;
			} 
			else{
				return false;
			}
		}
</script>
		<script language="javascript">
		function checkBirthday(){
	        var idNumber = document.form.idNo.value;
	        var y=document.form.year.value;
	        var m=document.form.month.value;
	        var d=document.form.day.value;
	        if(idNumber != null && idNumber.length > 0){
	           if(document.form.year.value != null){
	           		if(y != idNumber.substr(6, 4)){
							alert('<bean:message key="security.jsp.securitystaffbaseinfo.add.warn4" bundle="security"/>');
				           	document.form.year.value="";
				           	document.form.year.focus();
				           	return;
				   }
	           }
	            
	        }
		}

function checkMonth(){
        if(trim(document.form.month.value)>13){
		alert('<bean:message key="security.jsp.securitystaffbaseinfo.add.warn7" bundle="security"/>');
	        document.form.month.value="";
	        document.form.month.focus();
	        return;
        }
}

function checkDay(){
        if(trim(document.form.day.value)>32){
		alert('<bean:message key="security.jsp.securitystaffbaseinfo.add.warn8" bundle="security"/>');
	        document.form.day.value="";
	        document.form.day.focus();
	        return;
        }
}

function checkGender(){
         var idNumber=document.form.idNo.value;
          if(idNumber.length == 18){
         var strsex=idNumber.substr(16, 1);
        if(idNumber!=null){
         if(strsex%2==0){
						document.form.commConfigSexId.value=2;
					}if(strsex%2==1){
						document.form.commConfigSexId.value=1;
					}else{
					document.form.commConfigSexId.value="";
					}
         }
         }
}
</script>
		<script language="javascript"> 
	function checkIdcard(){
		var idcard=document.form.idNo.value;
		var area={11:"",12:"",13:"",14:"",15:"",21:"",22:"",23:"",31:"",32:"",33:"",34:"",35:"",36:"",37:"",41:"",42:"",43:"",44:"",45:"",46:"",50:"",51:"",52:"",53:"",54:"",61:"",62:"",63:"",64:"",65:"",71:"",81:"",82:"",91:""}
		var idcard,Y,JYM;
		var S,M;
		var idcard_array = new Array();
			idcard_array = idcard.split("");
		//地区检验
		if(area[parseInt(idcard.substr(0,2))]==null){
			alert("<bean:message key="security.jsp.securitystaffbaseinfo.add.warn10" bundle="security"/>!");
			//alert("身份证地区非法!");
			//doit = false;
			return false;
		}
		//身份号码位数及格式检验
		switch(idcard.length){
			case 15:
				if ( (parseInt(idcard.substr(6,2))+1900) % 4 == 0 || ((parseInt(idcard.substr(6,2))+1900) % 100 == 0 && (parseInt(idcard.substr(6,2))+1900) % 4 == 0 )){
					ereg=/^[1-9][0-9]{5}[0-9]{2}((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|[1-2][0-9]))[0-9]{3}$/;//测试出生日期的合法性
				} else {
					ereg=/^[1-9][0-9]{5}[0-9]{2}((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|1[0-9]|2[0-8]))[0-9]{3}$/;//测试出生日期的合法性
				}
				if(!ereg.test(idcard)){
					//alert("证件号码码出生日期超出范围或含有非法字符!");
					alert("<bean:message key="security.jsp.securitystaffbaseinfo.add.warn10" bundle="security"/>!");
					doit = false;
					return false;
				}
				break;
			case 18:
				if ( parseInt(idcard.substr(6,4)) % 4 == 0 || (parseInt(idcard.substr(6,4)) % 100 == 0 && parseInt(idcard.substr(6,4))%4 == 0 )){
					ereg=/^[1-9][0-9]{5}19[0-9]{2}((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|[1-2][0-9]))[0-9]{3}[0-9Xx]$/;//闰年出生日期的合法性正则表达式
				} else {
					ereg=/^[1-9][0-9]{5}19[0-9]{2}((01|03|05|07|08|10|12)(0[1-9]|[1-2][0-9]|3[0-1])|(04|06|09|11)(0[1-9]|[1-2][0-9]|30)|02(0[1-9]|1[0-9]|2[0-8]))[0-9]{3}[0-9Xx]$/;//平年出生日期的合法性正则表达式
				}
				if(ereg.test(idcard)){
					//测试出生日期的合法性
					//计算校验位
					S = (parseInt(idcard_array[0]) + parseInt(idcard_array[10])) * 7
						+ (parseInt(idcard_array[1]) + parseInt(idcard_array[11])) * 9
						+ (parseInt(idcard_array[2]) + parseInt(idcard_array[12])) * 10
						+ (parseInt(idcard_array[3]) + parseInt(idcard_array[13])) * 5
						+ (parseInt(idcard_array[4]) + parseInt(idcard_array[14])) * 8
						+ (parseInt(idcard_array[5]) + parseInt(idcard_array[15])) * 4
						+ (parseInt(idcard_array[6]) + parseInt(idcard_array[16])) * 2
						+ parseInt(idcard_array[7]) * 1 
						+ parseInt(idcard_array[8]) * 6
						+ parseInt(idcard_array[9]) * 3 ;
					Y = S % 11;
					M = "F";
					JYM = "10X98765432";
					M = JYM.substr(Y,1);//判断校验位
					if(M != idcard_array[17]){
						//alert("证件号码码校验错误!");
						alert("<bean:message key="security.jsp.securitystaffbaseinfo.add.warn10" bundle="security"/>!");
						//doit = false;
						return false;
					}
				}else{
					//alert("证件号码码出生日期超出范围或含有非法字符!");
					alert("<bean:message key="security.jsp.securitystaffbaseinfo.add.warn10" bundle="security"/>!");
					//doit = false;
					return false;
				}
				break;
			default:
			{
				alert("<bean:message key="security.jsp.securitystaffbaseinfo.add.warn11" bundle="security"/>!");
				//doit = false;
				return false;
			}
			break;
		}
	}
	function showHspMessage(message){
				if(message != ''&& message != null){
					alert(message);
					return;
				}
			}
	function regIsEmail(fData){
   	 	var reg = new RegExp(/^\w+([-+.]\w+)*@\w+([-.]\w+)*\.\w+([-.]\w+)*$/);
   		return reg.test(fData);
	}
	
	function checkcell(){
	     var code=document.getElementById("mobilecode").value;
	     var re=/^0{0,1}(13[0-9]|15[0-9]|153|156|18[7-9])[0-9]{8}$/; 
	        if(code!=null && code!=""){
	     if(!re.test(code)){  
	        alert("手机输入的格式不对");
	        document.getElementById("mobilecode").value="";
	        return ;
	     } 
	     }
	}
	function goBack(){
			location.href = "<%=request.getContextPath()%>/security/securityStaffBaseinfo.do?verbId=init&navtext=操作员管理主菜单-操作人员查询";
		}
</script>
	</head>
	<body
		onload="showHspMessage('<%=securityStaffBaseinfo.getMessage()%>')">
		<form name="form" method="post"
			action="security/securityStaffBaseinfo.do">
			<input type="hidden" name="verbId" value="add" />
			<input type="hidden" name="id"
				value="<%=securityStaffBaseinfo.getId()%>" />
			<input type="hidden" name="hspStaffBaseinfoId"
				value="<%=securityStaffBaseinfo.getHspStaffBaseinfoId()%>"/>
			<input type="hidden" name="seqNo"
				value="<%=securityStaffBaseinfo.getSeqNo()%>" />
			<table border="0" cellpadding="0" cellspacing="0" class="tblFill">
				<tr>
					<td class="tblTitle" colspan="4"><span>※</span>  
						<bean:message
							key="security.jsp.securitystaffbaseinfo.update.title"
							bundle="security" />   <span>※</span></td>
				</tr>
				
				<tr>
				
				   <td class="tblLable">
						<span>*</span>
						<bean:message key="security.jsp.commom.name" bundle="security" />
						：
					</td>
					<td >
						<input type="text" name="name" size="20" style="width: 375px;"
							maxlength="40" onkeypress="eventOnKeyPress('hspConfigBaseinfoName')"
							max="40" dataType="LimitB" msg="姓名输入过长"
							value="<%=securityStaffBaseinfo.getName()%>" />
					</td>
				
					<td class="tblLable">
						<span>*</span>
						<bean:message key="security.jsp.commom.hspConfigBaseinfoName"
							bundle="security" />
						：
					</td>
					<td style="padding-right:40px;">
						<input type="text"  id="displayInputId_1" name="hspConfigBaseinfoName"
							value="<%=securityStaffBaseinfo.getHspConfigBaseinfoName()%>"
							readonly="true" onkeypress="eventOnKeyPress('nameEn')" style="width: 375px;"/>
						<!--这是准备存储到数据的字段-->
						<input type="hidden" id="hiddenInputId_1"
							value="<%=securityStaffBaseinfo.getHspConfigBaseinfoId()%>"
							name="hspConfigBaseinfoId" />
						<!--弹出的选择按钮，add(arg1,arg2,arg3)其中arg1代表基本录入框的id，arg2代表保存存储到数据库字段对应的input的id
				arg3代表需要传递到.do的数据库检索参数
				-->
						<!-- hspType 1为除去卫生服务站2为只包括服务站和服务中心3为所有 -->
						<img src="security/include/images/select.gif"
							style="cursor:pointer;position:absolute;"
							onclick="add('hsp/hspConfigBaseinfo.do?verbId=getHsp&hspType=3','displayInputId_1','hiddenInputId_1')" />
					</td>

					
				</tr>
				<tr>
					<td class="tblLable">
						<bean:message key="security.jsp.commom.nameEn" bundle="security" />
						：
					</td>
					<td>
						<input type="text" onkeyup="value=value.replace(/[^\''\w]/g,'')" name="nameEn"
							onkeypress="eventOnKeyPress('idNo')" maxlength="40" style="width: 375px;"
							onkeydown="huiche()" max="40" dataType="LimitB" msg="英文姓名输入过长"
							value="<%=securityStaffBaseinfo.getNameEn()%>" />
					</td>

					<td class="tblLable">
						<bean:message key="security.jsp.commom.idNo" bundle="security" />
						：
					</td>
					<td>
						<input type="text" name="idNo" maxlength="40"
							max="40" dataType="LimitB" msg="身份证输入过长" style="width: 400px;"
							onkeypress="eventOnKeyPress('year')" onchange="checkId();"
							value="<%=securityStaffBaseinfo.getIdNo()%>" />
					</td>
				</tr>
				<tr>
					<td class="tblLable">
						<bean:message key="security.jsp.commom.birthDay" bundle="security" />
						：
					</td>
					<td>
						<input type="text" size="3" name="year" style="width:100px;"
							onkeypress="eventOnKeyPress('month')" 
							value="<%=securityStaffBaseinfo.getYear()%>" onkeydown="huiche()"
							onkeyup="value=value.replace(/[^\d]|\\.{1}/g,'')"
							id="statYear" maxlength="4" />
						<bean:message key="security.jsp.commom.Year" bundle="security" />
						&nbsp;
						<input type="text"  name="month" style="width: 100px;"
							onkeypress="eventOnKeyPress('day')" 
							value="<%=securityStaffBaseinfo.getMonth()%>"
							onkeydown="huiche()"
							onkeyup="value=value.replace(/[^\d]|\\.{1}/g,'')" maxlength="2" />
						<bean:message key="security.jsp.commom.month" bundle="security" />
						&nbsp;
						<input type="text"  name="day" style="width: 100px;"
							onkeypress="eventOnKeyPress('email')"
							value="<%=securityStaffBaseinfo.getDay()%>" onkeydown="huiche()"
							onkeyup="value=value.replace(/[^\d]|\\.{1}/g,'')" maxlength="2" />
						<bean:message key="security.jsp.commom.day" bundle="security" />
						
						
						
					</td>

					<td class="tblLable">
						<span>*</span>
						<bean:message key="security.jsp.securitystaffbaseinfo.commom1"
							bundle="security" />
						：
					</td>
					<td>
						<input type="text" id="email" name="email"  style="width: 400px;"
							max="100" dataType="LimitB" msg="邮箱输入过长" maxlength="100"
							onkeypress="eventOnKeyPress('commConfigSexId')"
							value="<%=securityStaffBaseinfo.getEmail()%>" />
					</td>
				</tr>
				<tr>
					<td class="tblLable">
						<bean:message key="security.jsp.commom.commConfigSexId"
							bundle="security" />
						：
					</td>
					<td >
						<select name="commConfigSexId"
							id="commConfigSexId" onblur="checkGender()"
							onkeypress="eventOnKeyPress('commConfigStafftypeId')">
							<%
								if (securityStaffBaseinfo.getCommConfigSexIds() != null
										&& securityStaffBaseinfo.getCommConfigSexIds().length > 0) {
									for (int i = 0; i < securityStaffBaseinfo.getCommConfigSexIds().length; i++) {
										String tempId = securityStaffBaseinfo.getCommConfigSexIds()[i];
										String tempName = securityStaffBaseinfo
												.getCommConfigSexNames()[i];
							%>
							<option value="<%=tempId%>"
								<%=tempId.equals(securityStaffBaseinfo
									.getCommConfigSexId()) ? "selected" : ""%>>
								<%=tempName%>
							</option>
							<%
								}
								}
							%>
						</select>
					</td>

					<td class="tblLable">
						<bean:message key="security.jsp.commom.commConfigStafftypeId"
							bundle="security" />
						：
					</td>
					<td>
						<select name="commConfigStafftypeId" 
							id="commConfigStafftypeId"
							onkeypress="eventOnKeyPress('islocation')">
							<%
								if (securityStaffBaseinfo.getCommConfigStafftypeIds() != null
										&& securityStaffBaseinfo.getCommConfigStafftypeIds().length > 0) {
									for (int i = 0; i < securityStaffBaseinfo
											.getCommConfigStafftypeIds().length; i++) {
										String tempId = securityStaffBaseinfo
												.getCommConfigStafftypeIds()[i];
										String tempName = securityStaffBaseinfo
												.getCommConfigStafftypeNames()[i];
							%>
							<option value="<%=tempId%>"
								<%=tempId.equals(securityStaffBaseinfo
									.getCommConfigStafftypeId()) ? "selected"
							: ""%>>
								<%=tempName%>
							</option>
							<%
								}
								}
							%>
						</select>
					</td>
				</tr>
				<tr>
					<td class="tblLable">
						<bean:message key="security.jsp.commom.islocation"
							bundle="security" />
						：
					</td>
					<td>
						<select name="islocation" id="islocation" 
							onkeypress="eventOnKeyPress('phone')">
							<%
								if (securityStaffBaseinfo.getIslocationIds() != null
										&& securityStaffBaseinfo.getIslocationIds().length > 0) {
									for (int i = 0; i < securityStaffBaseinfo.getIslocationIds().length; i++) {
										String tempId = securityStaffBaseinfo.getIslocationIds()[i];
										String tempName = securityStaffBaseinfo
												.getIslocationNames()[i];
							%>
							<option value="<%=tempId%>"
								<%=tempId.equals(securityStaffBaseinfo
									.getIslocation()) ? "selected" : ""%>>
								<%=tempName%>
							</option>
							<%
								}
								}
							%>
						</select>
					</td>

					<td class="tblLable">
						<bean:message key="security.jsp.commom.mobilecode"
							bundle="security" />
						：
					</td>
					<td>
						<input type="text"  id="mobilecode" name="phone" 
							onblur="checkcell()" maxlength="40" style="width: 400px;"
							max="40" dataType="LimitB" msg="手机号输入过长"
							onkeypress="eventOnKeyPress('stopDate')"
							value="<%=securityStaffBaseinfo.getPhone()%>" />
					</td>
				</tr>
				
				<tr>
					<td class="tblLable" >
						有效截止日期：
					</td>
					<td>
						<input type="text"  id="stopDate" name="stopDate" style="width: 350px;"
							dataType="Custom" regexp="^(?:(?:([0-9]{4}(-|\/)(?:(?:0?[1,3-9]|1[0-2])(-|\/)(?:29|30)|((?:0?[13578]|1[02])(-|\/)31)))|([0-9]{4}(-|\/)(?:0?[1-9]|1[0-2])(-|\/)(?:0?[1-9]|1\\d|2[0-8]))|(((?:(\\d\\d(?:0[48]|[2468][048]|[13579][26]))|(?:0[48]00|[2468][048]00|[13579][26]00))(-|\/)0?2(-|\/)29))))$"
							msg="日期格式不正确，期待格式为：XXXX-XX-XX！"
							require="false" readonly="readonly"
							onkeypress="eventOnKeyPress('comments')"
							value="<%=securityStaffBaseinfo.getStopDate()%>" 
							style="width:80%"/>
						<img id="stopDateButton" src="include/calendar/calendar.gif"
							style="cursor: hand;" align="middle" />
					</td>

					<td class="tblLable">
					
					</td>
					<td>
						
					</td>
				</tr>
				<!--<tr>
					<td class="tdLeft_L">
						<bean:message key="security.jsp.commom.homePageType" bundle="security"/>：
					</td>
					<td class="tdRight_R">
						<select name="homePageType" id="homePageType" style="width: 150px;" onkeypress="eventOnKeyPress('comments')">
							
							<option value="0" <%="0".equals(securityStaffBaseinfo
									.getHomePageType()) ? "selected" : ""%>>
								
							</option>
							<option value="1" <%="1".equals(securityStaffBaseinfo
									.getHomePageType()) ? "selected" : ""%>>
							<bean:message key="security.jsp.commom.item8" bundle="security"/>	
							</option>
							<option value="2" <%="2".equals(securityStaffBaseinfo
									.getHomePageType()) ? "selected" : ""%>>
							<bean:message key="security.jsp.commom.item9" bundle="security"/>	
							</option>
							<option value="3" <%="3".equals(securityStaffBaseinfo
									.getHomePageType()) ? "selected" : ""%>>
							<bean:message key="security.jsp.commom.item10" bundle="security"/>
							</option>
							
						</select>
					</td>
				</tr>  -->
				<tr>
    				<td class="tblLable"><bean:message key="security.jsp.commmom.comments" bundle="security"/>：</td>
  				    <td colspan="3"><textarea name="comments"  max="100" dataType="LimitB" msg="备注输入过长" id="comments" cols="45" rows="5"><%=securityStaffBaseinfo.getComments()%></textarea></td>
  				</tr>
			</table>
			<!-- Sheet operation button area -->
			<div class="btnSave">
				<input type="button" 
					name="btnSaveForm"
					value="<bean:message key="security.jsp.commom.button1" bundle="security"/>"
					onclick="saveForm();" />
				&nbsp;&nbsp;
				<input type="button" 
					name="btnBack"
					value="<bean:message key="security.jsp.commom.button2" bundle="security"/>"
					onclick="goBack();" />
			</div>
		</form>
		<script>	
		 Calendar.setup({
			  inputField    : "stopDate",
			  button        : "stopDateButton",
			  ifFormat      : "%Y-%m-%d",
			  showsTime     :  false
			});
		 
		</script>
	</body>
</html>
