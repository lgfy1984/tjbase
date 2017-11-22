<%@ page language="java" pageEncoding="UTF-8" import="com.tianjian.comm.bean.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<jsp:useBean id="data" scope="request"
	class="com.tianjian.hsp.struts.form.OrgMenuForm" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
	<meta http-equiv="pragma" content="no-cache"/>
	<meta http-equiv="cache-control" content="no-cache"/>
	<meta http-equiv="expires" content="0"/>    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3"/>
	<meta http-equiv="description" content="This is my page"/>
	<link rel="stylesheet" href="<%=request.getContextPath()%>/include/css/open.css" />	
	<link type="text/css" rev="stylesheet" rel="stylesheet" href="<%=request.getContextPath()%>/include/css/form.css" />
	 <script language="javascript" src="<%=request.getContextPath()%>/archive/include/javascript/gettext_staff.js" defer="defer"></script>
    <script language="javascript" src="<%=request.getContextPath()%><bean:message  bundle="hsp.hspLocale" key="hsp.js.gettext_staff11.path"/>" defer="defer"></script>
	<script type="text/javascript" src="<%=request.getContextPath() %>/include/javascript/checkbox_radio.js" defer="defer"></script> 
	<script language="javascript" src="<%=request.getContextPath()%><bean:message  key="hsp.js.commCommMessage.path"  bundle="hsp.hspLocale"/>" defer="defer"></script>
	 <script type="text/javascript" src="<%=request.getContextPath()%>/include/javascript/jianbian.js" defer="defer"></script>
	 <script language="javascript" src="<%=request.getContextPath() %>/include/javascript/eventOnKeyPress.js" defer="defer"></script>
	<script language="javascript" src="<%=request.getContextPath()%><bean:message  key="hsp.js.includevalidator.path"  bundle="hsp.hspLocale"/>" defer="defer"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/include/javascript/My97DatePicker/WdatePicker.js" defer="defer"></script>
	<script language="javascript">	
		var re;
function setTime(){
//	if(document.getElementById("idNo").value==""){
//		return;
//	}
//	if(checkIdcard() == false){
//		return;
//	}
	if(document.getElementById("idNo").value.length==18){
		var time=document.getElementById("idNo").value;
		var year=time.substring(6,10);
		var month=time.substring(10,12);
		var day=time.substring(12,14);
		document.form.birthdayYear.value=year+"-"+month+"-"+day;
	}
}
		function load(){
			re=document.getElementById("hiddenInputId_3").value;
		}
		function getInfomation(){
			var check=document.getElementById("checkisorno").value;
			var isorno=document.getElementById("hiddenInputId_3").value;
			if(isorno==null||isorno==""){
				return;
			}
			if(re==isorno){
				return;
			}
			if(check=="getAllPeople"){
				document.getElementById("verbId").value="getInfomation";
				document.form.submit();
			}else{
				return;
			}
		}
		function trim(str){
 			return str.replace(/(^\s*)|(\s*$)/g, "");
		}
		function huiche(){
			if(event.keyCode==13){
				event.keyCode=9
			}
		}
		function saveForm(){
			var id;
			id = trim(document.form.idNo.value);
	 var test1=/^(\d{15,19}$)|(\d{17,18}(?:\d|x|X)$)/;
        document.getElementById("messageno").innerHTML="";
	    //if(! Validator.IsIdCard(id)){			    
		//	document.getElementById("messageno").innerHTML=" <font color='red'> * 身份证输入错误</font>";
	    //}
	
		if(!test1.test(id)){
			document.getElementById("messageno").innerHTML=" <font color='red'>*<bean:message  bundle="hsp.hspLocale" key="hsp.jsp.common.warn1"/></font>";
     	    document.getElementById("idNo").focus();
			return false;
		}	
			if(trim(document.form.hspConfigBaseinfoId.value)==""){
				alert("<bean:message  bundle="hsp.hspLocale" key="hsp.jsp.common.warn16"/>!");
				return;
			}
			if(!Validator.Validate(document.forms.form,3)){
       				return ;
    			}
    			<%
					if(data.getPersonnelCode().equals("true")){
  				 %>
			if(trim(document.form.empNo.value)==""){
				alert("<bean:message  bundle="hsp.hspLocale" key="hsp.jsp.common.warn10"/>!");
				document.form.empNo.focus();
				return;
			}
			<%}%>
			if(trim(document.form.name.value)==""){
				alert("<bean:message  bundle="hsp.hspLocale" key="hsp.jsp.common.warn12"/>!");
				document.form.name.focus();
				return;
			}
			if(trim(document.form.birthdayYear.value)==""){
				alert("<bean:message  bundle="hsp.hspLocale" key="hsp.jsp.common.warn8"/>!");
				document.form.birthdayYear.focus();
				return;
			}
			var sexs=document.form.commConfigSexId;
			if(sexs[0].checked==false&&sexs[1].checked==false&&sexs[2].checked==false&&sexs[3].checked==false){
			alert("<bean:message  bundle="hsp.hspLocale" key="hsp.jsp.common.warn11"/>!");
			sexs[0].focus();
			return;
			}
		 if(trim(document.form.commConfigNationalityId.value)==""){
				alert("<bean:message  bundle="hsp.hspLocale" key="hsp.jsp.common.warn9"/>!");
				document.form.commConfigNationalityId.focus();
				return;
			}
			
			
	//		if(checkIdcard() == false){
	//			return;
	//		}
			if(trim(document.form.birthdayYear.value)==""){
		//		var y = trim(document.form.birthdayYear.value);
		//		var m = trim(document.form.birthdayMonth.value);
		//		var d = trim(document.form.birthdayDay.value);
		//		if(checkDate(y,m,d)){
		//		}
		//		else{
					alert("<bean:message  bundle="hsp.hspLocale" key="hsp.jsp.hspstaffbaseinfoadd.birthDayNotNull"/>!");
		//			document.form.birthdayYear.focus();
					return;
		//		} 
			}
			//if(trim(document.form.startWorkDateYear.value)==""){
			//	var y = trim(document.form.startWorkDateYear.value);
			//	var m = trim(document.form.startWorkDateMonth.value);
			////	if(checkDate2(y,m)){
			//	}else{
					//alert("参加工作日期不能为空!");
			//		document.form.startWorkDateYear.focus();
					//return;
				//}
			//}
			document.form.submit();
		}
	</script>
	<script language="javascript"> 
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
				
				if(checkDate(stry,strm,strd)&& checkIdcard()!=false){
					document.form.birthdayYear.value = stry;
					document.form.birthdayMonth.value = strm;
					document.form.birthdayDay.value = strd;
					if(strsex%2==0){
						document.form.commConfigSexId[1].checked=true;
					}else{
						document.form.commConfigSexId[0].checked=true;
					}
					document.form.birthdayYear.focus();
				}
				}
				if(id.length == 15){
				document.form.birthdayYear.focus();
				}
			
			
		}
		function goBack(url){
			window.location=url;
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
		function checkDate2(y,m) 
		{ 
			var dateElement = y+"-"+m;
			var pattern=/^((((1[6-9]|[2-9]\d)\d{2})-(0?[13578]|1[02]))|(((1[6-9]|[2-9]\d)\d{2})-(0?[13456789]|1[012]))|(((1[6-9]|[2-9]\d)\d{2})-0?2)|(((1[6-9]|[2-9]\d)(0[48]|[2468][048]|[13579][26])|((16|[2468][048]|[3579][26])00))-0?2))$/; 
			if(pattern.test(dateElement)){
				return true;
			} 
			else{
				return false;
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
			alert("<bean:message  bundle="hsp.hspLocale" key="hsp.jsp.common.warn3"/>!");
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
					alert("<bean:message  bundle="hsp.hspLocale" key="hsp.jsp.common.warn3"/>!");
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
						alert("<bean:message  bundle="hsp.hspLocale" key="hsp.jsp.common.warn3"/>!");
						//doit = false;
						return false;
					}
				}else{
					//alert("证件号码码出生日期超出范围或含有非法字符!");
					alert("<bean:message  bundle="hsp.hspLocale" key="hsp.jsp.common.warn3"/>!");
					//doit = false;
					return false;
				}
				break;
			default:
			{
				alert("<bean:message  bundle="hsp.hspLocale" key="hsp.jsp.common.idNoSizeWrong"/>!");
				//doit = false;
				return false;
			}
			break;
		}
	}
</script> 
	
<script language="javascript">
 

function checkMonth(){
	var value=document.form.birthdayMonth.value;
        if(value>=13){
        
        alert("<bean:message  bundle="hsp.hspLocale" key="hsp.jsp.common.warn14"/>!");
        document.form.birthdayMonth.value="";
        document.form.birthdayMonth.focus();
        return;
        }
}
function workDateMonth(){
	var value=document.getElementById("startWorkDateMonth").value;
	if(value>=13){
		alert("<bean:message  bundle="hsp.hspLocale" key="hsp.jsp.hspstaffbaseinfoadd.MonthWrong"/>");
	}
}
function checkDay(){
		var value=document.form.birthdayDay.value;
        if(value>=32){ 
        alert("<bean:message  bundle="hsp.hspLocale" key="hsp.jsp.common.warn13"/>!");
        document.form.birthdayDay.value="";
        document.form.birthdayDay.focus();
        return;
        }
}

function checkGender(){
         var idNumber=document.form.idNo.value;
          if(idNumber.length == 18){
         var strsex=idNumber.substr(16, 1);
        if(idNumber!=null){
         if(strsex%2==0){
						document.form.commConfigSexId[1].checked=true;
					}else{
						document.form.commConfigSexId[0].checked=true;
					}
         }
         }
}
            function showMessage(status,treeName,hspStaffId){
		        if(status==1){
		            var left = self.parent.frames["menuFrame"];
		            left.addStaffNode(treeName,hspStaffId);
		            alert("保存成功！");
		        }
		    }
</script>
  </head>
  <body onload="showMessage('<%=data.getStatus() %>','<%=data.getName() %>','<%=data.getId() %>');">
  	 <form name="form" action="<%=request.getContextPath()%>/hsp/orgMenu.do" 
  	 	method="post" enctype="multipart/form-data"> 
  	 	<input type="hidden" id="verbId" name="verbId" value="staffAdd" />
  	 	<input type="hidden" id="hiddenInputId_1" name="hspConfigBaseinfoId1" value="${data.hspConfigBaseinfoId1}"/>
  		 <table align="center" border="0" cellpadding="0" cellspacing="0" class="tblFill" border="0" cellpadding="0" cellspacing="0" class="tblFill">
  				 <tr>
					<td class="tblTitle" colspan="4"><span>※</span> 
						<bean:message  bundle="hsp.hspLocale" key="hsp.jsp.hspstaffbaseinfoadd.addUser"/>
					 <span>※</span></td>
				</tr>
			<tr>
  				<%
					if(data.getPersonnelCode().equals("true")){
  				 %>
				<td class="tblLable"><span>*</span><bean:message  bundle="hsp.hspLocale" key="hsp.jsp.hspstaffbaseinfodetail.organ"/>：</td>
  				<td style="padding-right:40px;">
					<input type="text" id="hspConfigBaseinfoName" name="hspConfigBaseinfoName" value="${data.hspConfigBaseinfoName}" readonly="readonly" />
					<input type="hidden" id="hiddenInputId_1" name="hspConfigBaseinfoId" value="${data.hspConfigBaseinfoId1}"/>
  				</td>
  				
  				<td class="tblLable""><span>*</span><bean:message  bundle="hsp.hspLocale" key="hsp.jsp.hspstaffbaseinfoadd.userCode"/></td>
  				<td><input type="text" name="empNo" id="empNo" style="font-size:12px" dataType="Number" msg="<bean:message  bundle="hsp.hspLocale" key="hsp.jsp.hspstaffbaseinfoadd.userCodeWrong"/>" class="input" value="<%=data.getEmpNo() %>"
  				 onkeyup="value=value.replace(/[^\d\w]/g,'')" onkeypress="eventOnKeyPress('name')"  onkeydown="huiche()" maxlength="18"/>
  				</td>
  				<%}if(data.getPersonnelCode().equals("false")){
  				%>
  				<td class="tblLable"><span>*</span><bean:message  bundle="hsp.hspLocale" key="hsp.jsp.hspstaffbaseinfodetail.organ"/></td>
  				<td style="padding-right:40px;">
					<input type="text" id="hspConfigBaseinfoName" name="hspConfigBaseinfoName" value="${data.hspConfigBaseinfoName}" readonly="readonly" />
					<input type="hidden" id="hiddenInputId_1" name="hspConfigBaseinfoId" value="${data.hspConfigBaseinfoId1}"/>					
  				</td>
  				
  				<td class="tblLable"><span>*</span><bean:message  bundle="hsp.hspLocale" key="hsp.jsp.hspstaffbaseinfoadd.userCode"/>：</td>
  				<td><input type="text" name="empNo" style="font-size:12px" id="empNo" value="<%=data.getEmpNo() %>"
  				 onkeyup="value=value.replace(/[^\d\w]/g,'')" onkeypress="eventOnKeyPress('name')"  onkeydown="huiche()" maxlength="18"/>
  				</td>
  				<% } %>
  			</tr>
  			<tr>
  				<td class="tblLable" ><span>*</span><bean:message  bundle="hsp.hspLocale" key="hsp.jsp.hspstaffbaseinfodetail.name"/>：</td>
  				<td><input type="text" name="name" style="font-size:12px" id="name"  msg="<bean:message  bundle="hsp.hspLocale" key="hsp.jsp.common.nameMustBeCN"/>"
  				 onkeypress="eventOnKeyPress('idNo')" onkeydown="huiche()" onfocus="getInfomation()" maxlength="50"/> 						
  				</td>
  				<td class="tblLable"><span>*</span><bean:message  bundle="hsp.hspLocale" key="hsp.jsp.common.idNo"/>：</td>
  				<td>
  					<input type="text" id="idNo" name="idNo" style="font-size:12px"  onkeydown="huiche()"  onblur="setTime()"
  					onkeypress="eventOnKeyPress('birthdayYear')" maxlength="18"/><span id="messageno"></span>
  				</td> 
            </tr>
  			<tr>
  				<td class="tblLable"><span>*</span><bean:message  bundle="hsp.hspLocale" key="hsp.jsp.common.birthDay"/>：</td>
  				<td>
  				<!-- 	<input type="text" onfocus="setTime()" name="birthdayYear" id="birthdayYear" value="<%=data.getBirthdayYear() %>" onkeypress="eventOnKeyPress('birthdayMonth')" onkeyup="value=value.replace(/[^(\d\.)]/g,'')" onblur="check4year('birthdayYear');" class="input" style="width:40px;" onkeydown="huiche()" maxlength="4"/>年
  					<input type="text" name="birthdayMonth" value="<%=data.getBirthdayMonth() %>" onkeypress="eventOnKeyPress('birthdayDay')" onkeyup="value=value.replace(/[^(\d\.)]/g,'')"  onblur="checkMonth()" class="input" style="width:20px;" onkeydown="huiche()" maxlength="2"/>月
  					<input type="text" name="birthdayDay" value="<%=data.getBirthdayDay() %>" onkeypress="eventOnKeyPress('commConfigSexId')" onkeyup="value=value.replace(/[^(\d\.)]/g,'')"   onblur="checkDay()"    class="input" style="width:20px;" onkeydown="huiche()" maxlength="2"/>日
  				 -->
  				 <input type="text" name="birthdayYear" style="font-size:12px" readonly="readonly" id="birthdayYear"  onclick="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'%y-%M-%d'})" onkeydown="huiche()" maxlength="10"/>
  				</td>
  				<td class="tblLable"><span>*</span><bean:message  bundle="hsp.hspLocale" key="hsp.jsp.hspstaffbaseinfodetail.sex1"/>：</td>
  				<td>
  					 <select  id="commConfigSexId" name="commConfigSexId" onkeydown="huiche()" style="font-size:12px"  dataType="Require"  msg="<bean:message  bundle="hsp.hspLocale" key="hsp.jsp.hspstaffbaseinfoadd.sexNotNull"/>"
  					 onkeypress="eventOnKeyPress('commConfigNationalityId')">
			            <%
						if(data.getCommConfigSexIdList() != null && data.getCommConfigSexIdList().size() > 0){
							for(int i = 0; i < data.getCommConfigSexIdList().size(); i++){
								CommConfigSex nCommConfigSex=(CommConfigSex)data.getCommConfigSexIdList().get(i);
								String tempId = nCommConfigSex.getItemCode();
								String tempName = nCommConfigSex.getItemName();
						%>
						        	<option value="<%=tempId %>" >
										<%=tempName %>
									</option>
						<%
							}
						}
						%>  
					</select>
				</td>

  				
  			</tr>
  			<tr>
  				<td class="tblLable"><bean:message  bundle="hsp.hspLocale" key="hsp.jsp.hspstaffbaseinfodetail.nation1"/>：</td>
				<td style="padding-right:40px;">
				    <input type="text" id="displayInputId_2" 
				    	onkeypress="eventOnKeyPress('officeTel')" name="commConfigNationalityId" onkeydown="huiche()" readonly="readonly" />
					<input type="hidden" id="hiddenInputId_2" name="nationalityIdHidden" />
					<img src="<%=request.getContextPath()%>/include/images/select.gif"  style="cursor:pointer;position:absolute;"
						onclick="add('<%=request.getContextPath()%>/hsp/hspStaffBaseinfo.do?verbId=nationality','displayInputId_2','hiddenInputId_2','','tel','0')" />
				</td>				
  				<td class="tblLable"><bean:message  bundle="hsp.hspLocale" key="hsp.jsp.hspstaffbaseinfoadd.selectUser"/></td>
  				<td>
  				<input type="hidden" id="hiddenInputId_3" name="securityUserBaseinfoId"   />
				<input type="hidden" id="checkisorno" value="no" />
				<input type="hidden" id="displayInputId_3" value="" />
				<img src="<%=request.getContextPath()%>/include/images/select.gif"
							style="cursor: pointer;"
							onclick="add('<%=request.getContextPath()%>/hsp/hspStaffBaseinfo.do?verbId=getAllPeople','displayInputId_3','hiddenInputId_3')" />
  				</td>
  		   </tr>
  			<tr>
  				<td class="tblLable"><bean:message  bundle="hsp.hspLocale" key="hsp.jsp.common.officePhone"/>：</td>
  				<td>
  					<input type="text" 
  					onkeypress="eventOnKeyPress('mobileTel')" id="tel" style="font-size:12px"  onblur="checktel()" name="officeTel" onkeydown="huiche()" maxlength="20"/>
  				</td>
  				<td class="tblLable"><bean:message  bundle="hsp.hspLocale" key="hsp.jsp.common.phone"/>：</td>
  				<td>
  					<input type="text" name="mobileTel" style="font-size:12px" onkeypress="eventOnKeyPress('workCertificateNo')"
  					 id="mobilecode" onblur="checkcell()" onkeydown="huiche()" maxlength="20"/>
  				</td>
  		
  				
            </tr>
  			<tr>
  				<td class="tblLable"><bean:message  bundle="hsp.hspLocale" key="hsp.jsp.common.doctorCardId"/>：</td>
  				<td>
  					<input type="text" name="workCertificateNo" style="font-size:12px"  onkeypress="eventOnKeyPress('islocation')"
  						onkeyup="value=value.replace(/[^\d\w]/g,'')" onkeydown="huiche()" maxlength="32"/>
  				</td>
  				<td class="tblLable"><bean:message  bundle="hsp.hspLocale" key="hsp.jsp.common.onStatusSign"/>：</td>
  				<td>
  					<select name="islocation" style="font-size:12px" onkeydown="huiche()" onkeypress="eventOnKeyPress('commDictPublicCharId1')">			
  						<option value=""  ></option>
  						<%if(data.getIslocation()!=null&&data.getIslocation()>=0){ %>
  							<option value="1"><bean:message  bundle="hsp.hspLocale" key="hsp.jsp.common.on"/></option>
  							<option value="0"><bean:message  bundle="hsp.hspLocale" key="hsp.jsp.common.notIn"/></option>
  						<%}else{ %>
  							<option value="1"  ><bean:message  bundle="hsp.hspLocale" key="hsp.jsp.common.on"/></option>
  							<option value="0" ><bean:message  bundle="hsp.hspLocale" key="hsp.jsp.common.notIn"/></option>
  						<%} %>
  					</select>
  				</td>
  			</tr>
  			<tr>
  					<td class="tblLable"><bean:message  bundle="hsp.hspLocale" key="hsp.jsp.common.majorClass"/>：</td>
  					<td>
  						<select  onkeypress="eventOnKeyPress('commDictPublicCharId2')" style="font-size:12px"  name="commDictPublicCharId1" onkeydown="if(event.keyCode==13)event.keyCode=9">
							<option value=""></option>
							<%if(data.getCommDictPublicCharId1List()!=null&&data.getCommDictPublicCharId1List().size()>0){
									for(int i=0;i<data.getCommDictPublicCharId1List().size();i++){
										CommDictPublicChar nCommDictPublicChar=(CommDictPublicChar)data.getCommDictPublicCharId1List().get(i);
							%>
											<option value="<%=nCommDictPublicChar.getId() %>"  >
												<%=nCommDictPublicChar.getDictValue() %>
											</option>
							<%										
									}
								}
							 %>
						</select>
					</td>
  				<td class="tblLable"><bean:message  bundle="hsp.hspLocale" key="hsp.jsp.common.doctorSort"/>：</td>
  				<td>
  					<select onkeypress="eventOnKeyPress('commConfigPositiontitleId')" style="font-size:12px" name="commDictPublicCharId2" onkeydown="if(event.keyCode==13)event.keyCode=9">
							<option value=""></option>
							<%if(data.getCommDictPublicCharId2List()!=null&&data.getCommDictPublicCharId2List().size()>0){
									for(int i=0;i<data.getCommDictPublicCharId2List().size();i++){
										CommDictPublicChar nCommDictPublicChar=(CommDictPublicChar)data.getCommDictPublicCharId2List().get(i);
							%>
											<option value="<%=nCommDictPublicChar.getId() %>" >
												<%=nCommDictPublicChar.getDictValue() %>
											</option>
							<%										
									}
								}
							 %>
					</select>
  				</td>
  			</tr>
  			<tr>
  				<td class="tblLable"><bean:message  bundle="hsp.hspLocale" key="hsp.jsp.common.managePost"/></td>
  					<td>
  						<select onkeypress="eventOnKeyPress('commConfigEmptitleId')" style="font-size:12px" name="commConfigPositiontitleId" onkeydown="if(event.keyCode==13)event.keyCode=9">
							<option value=""></option>
							<%if(data.getCommConfigPositiontitleIdList()!=null&&data.getCommConfigPositiontitleIdList().size()>0){
									for(int i=0;i<data.getCommConfigPositiontitleIdList().size();i++){
										CommDictPublicChar nCommDictPublicChar=(CommDictPublicChar)data.getCommConfigPositiontitleIdList().get(i);
							%>
											<option value="<%=nCommDictPublicChar.getId() %>" >
												<%=nCommDictPublicChar.getDictValue() %>
											</option>
							<%										
									}
								}
							 %>
						</select>
					</td>
  				<td class="tblLable"><bean:message  bundle="hsp.hspLocale" key="hsp.jsp.common.skillJudge"/>：</td>
  				<td>
  					<select onkeypress="eventOnKeyPress('commDictPublicCharId3')" style="font-size:12px" name="commConfigEmptitleId" onkeydown="if(event.keyCode==13)event.keyCode=9">
							<option value=""></option>
							<%if(data.getCommConfigEmptitleIdList()!=null&&data.getCommConfigEmptitleIdList().size()>0){
									for(int i=0;i<data.getCommConfigEmptitleIdList().size();i++){
										CommConfigEmptitle nCommDictPublicChar=(CommConfigEmptitle)data.getCommConfigEmptitleIdList().get(i);
							%>
											<option value="<%=nCommDictPublicChar.getItemCode() %>"  >
												<%=nCommDictPublicChar.getItemName() %>
											</option>
							<%										
									}
								}
							 %>
						</select>
  				</td>
  			</tr>
  			<tr>
  					<td class="tblLable"><bean:message  bundle="hsp.hspLocale" key="hsp.jsp.common.skillPost"/>：</td>
  					<td>
  						<select onkeypress="eventOnKeyPress('commConfigDegreeId')" style="font-size:12px" name="commDictPublicCharId3" onkeydown="if(event.keyCode==13)event.keyCode=9">
							<option value=""></option>
							<%if(data.getCommDictPublicCharId3List()!=null&&data.getCommDictPublicCharId3List().size()>0){
									for(int i=0;i<data.getCommDictPublicCharId3List().size();i++){
										CommDictPublicChar nCommDictPublicChar=(CommDictPublicChar)data.getCommDictPublicCharId3List().get(i);
							%>
											<option value="<%=nCommDictPublicChar.getId() %>" >
												<%=nCommDictPublicChar.getDictValue() %>
											</option>
							<%										
									}
								}
							 %>
						</select>
					</td>
  				<td class="tblLable"><bean:message  bundle="hsp.hspLocale" key="hsp.jsp.common.educationBg"/>：</td>
  				<td>
  					<select onkeypress="eventOnKeyPress('commConfigDegreeLevelId')" style="font-size:12px"  name="commConfigDegreeId" onkeydown="if(event.keyCode==13)event.keyCode=9">
							<option value=""></option>
							<%if(data.getCommConfigDegreeIdList()!=null&&data.getCommConfigDegreeIdList().size()>0){
									for(int i=0;i<data.getCommConfigDegreeIdList().size();i++){
										CommDictPublicChar nCommDictPublicChar=(CommDictPublicChar)data.getCommConfigDegreeIdList().get(i);
							%>
											<option value="<%=nCommDictPublicChar.getId() %>" >
												<%=nCommDictPublicChar.getDictValue() %>
											</option>
							<%										
									}
								}
							 %>
						</select>
  				</td>
  			</tr>
  			<tr>
  					<td class="tblLable"><bean:message  bundle="hsp.hspLocale" key="hsp.jsp.common.degree"/>：</td>
  					<td>
  						<select onkeypress="eventOnKeyPress('commConfigProfessionId')" style="font-size:12px" name="commConfigDegreeLevelId" onkeydown="if(event.keyCode==13)event.keyCode=9">
							<option value=""></option>
							<%if(data.getCommConfigDegreeLevelIdList()!=null&&data.getCommConfigDegreeLevelIdList().size()>0){
									for(int i=0;i<data.getCommConfigDegreeLevelIdList().size();i++){
										CommDictPublicChar nCommDictPublicChar=(CommDictPublicChar)data.getCommConfigDegreeLevelIdList().get(i);
							%>
											<option value="<%=nCommDictPublicChar.getId() %>" >
												<%=nCommDictPublicChar.getDictValue() %>
											</option>
							<%										
									}
								}
							 %>
						</select>
					</td>
  				<td class="tblLable"><bean:message  bundle="hsp.hspLocale" key="hsp.jsp.common.studyMajor"/>：</td>
  				<td>
  					<select onkeypress="eventOnKeyPress('startWorkDateYear')" style="font-size:12px" name="commConfigProfessionId" onkeydown="if(event.keyCode==13)event.keyCode=9">
							<option value=""></option>
							<%if(data.getCommConfigProfessionIdList()!=null&&data.getCommConfigProfessionIdList().size()>0){
									for(int i=0;i<data.getCommConfigProfessionIdList().size();i++){
										CommDictPublicChar nCommDictPublicChar=(CommDictPublicChar)data.getCommConfigProfessionIdList().get(i);
							%>
											<option value="<%=nCommDictPublicChar.getId() %>" >
												<%=nCommDictPublicChar.getDictValue() %>
											</option>
							<%										
									}
								}
							 %>
						</select>
  				</td>
  			</tr>
  			<tr>
  				<td class="tblLable"><bean:message  bundle="hsp.hspLocale" key="hsp.jsp.common.workDate"/>：</td>
  				<td>
  					<!--  <input type="text" name="startWorkDateYear" id="startWorkDateYear" onblur="check4year('startWorkDateYear')" 
  					onkeypress="eventOnKeyPress('startWorkDateMonth')"
  					value="<%=data.getStartWorkDateYear() %>" class="input" style="width:40px" onkeydown="huiche()" maxlength="4"/>年
  					<input type="text" name="startWorkDateMonth" id="startWorkDateMonth" onblur="workDateMonth()"
  					onkeypress="eventOnKeyPress('officeTel')"
  					value="<%=data.getStartWorkDateMonth() %>" class="input" style="width:20px" onkeydown="huiche()" maxlength="2"/>月
  					-->
  					<input type="text" name="startWorkDateYear" id="startWorkDateYear"  style="font-size:12px"
  					 onclick="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'%y-%M-%d'})"  onkeydown="huiche()" maxlength="10"/>
  				</td>
  				<td class="tblLable">所属科室：</td>
  				<td>
					<input type="text" id="deptName" name="deptName" value="<%=data.getDeptName() %>" readonly="readonly" />
					<input type="hidden" id="relatedDepartment" name="relatedDepartment" value="<%=data.getRelatedDepartment() %>"/>
  				</td>
  			</tr>
  		</table>
  			<!-- Sheet operation button area -->
  			<div class="btnSave" id="budiv" name="budiv">
	        	<input type="button" name="btnSaveForm" value="<bean:message  bundle="hsp.hspLocale" key="hsp.jsp.hspstaffbaseinfoupdate.save1"/>" onclick="saveForm()" />
	        	<input type="button" name="btnBack" value="<bean:message  bundle="hsp.hspLocale" key="hsp.jsp.hspstaffbaseinfoupdate.back"/>" onclick="history.go(-1)" />
	     	</div>   
  	</form>
  </body>
</html>
