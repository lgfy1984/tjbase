<%@page import="com.tianjian.util.Converter"%>
<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@ page language="java" pageEncoding="UTF-8" import="com.tianjian.comm.bean.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<jsp:useBean id="data" scope="request" class="com.tianjian.hsp.struts.form.HspStaffBaseinfoForm" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<meta http-equiv="pragma" content="no-cache" />
		<meta http-equiv="cache-control" content="no-cache" />
		<meta http-equiv="expires" content="0" />
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3" />
		<meta http-equiv="description" content="This is my page" />
		<link rel="stylesheet" href="<%=request.getContextPath()%>/include/css/open.css" />
		<link type="text/css" rev="stylesheet" rel="stylesheet" href="<%=request.getContextPath()%>/include/css/form.css" />
		<script language="javascript" src="<%=request.getContextPath()%>/archive/include/javascript/gettext_staff.js"
			defer="defer"></script>
		<script language="javascript"
			src="<%=request.getContextPath()%><bean:message  bundle="hsp.hspLocale" key="hsp.js.gettext_staff11.path"/>"
			defer="defer"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/include/javascript/checkbox_radio.js" defer="defer"></script>
		<script language="javascript"
			src="<%=request.getContextPath()%><bean:message  key="hsp.js.commCommMessage.path"  bundle="hsp.hspLocale"/>"
			defer="defer"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/include/javascript/jianbian.js" defer="defer"></script>
		<script language="javascript" src="<%=request.getContextPath()%>/include/javascript/eventOnKeyPress.js" defer="defer"></script>
		<script language="javascript"
			src="<%=request.getContextPath()%><bean:message  key="hsp.js.includevalidator.path"  bundle="hsp.hspLocale"/>"
			defer="defer"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/include/javascript/My97DatePicker/WdatePicker.js"
			defer="defer"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/include/javascript/jquery-1.4.4.min.js"
			defer="defer"></script>
		<script type="text/javascript" src="<%=request.getContextPath()%>/include/javascript/cookie.js"
			defer="defer"></script>
		<script language="javascript">	
		var re;
		function setTime(){
			if(document.getElementById("idNo").value.length==18){
				var time=document.getElementById("idNo").value;
				var year=time.substring(6,10);
				var month=time.substring(10,12);
				var day=time.substring(12,14);
				document.form.birthdayYear.value=year+"-"+month+"-"+day;
			}
		}
		function checkGender(){
	         var idNumber= $("#idNo").val();
	         if(idNumber.length == 18){
		         var strsex=idNumber.substr(16, 1);
		       	 if(idNumber!=null){
	        	 	if(strsex%2==0){
						$("#commConfigSexId").val("2");
					}else{
						$("#commConfigSexId").val("1");
					}
         		 }
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
			if(!Validator.Validate(document.forms.form,3)){
     			return ;
   			}
			var sexs=document.form.commConfigSexId;
			if (!confirm("<bean:message key='security.jsp.commom.save' bundle='security'/>")){
				return;
			}
			document.form.verbId.value = "add";
			<%
				//树状图使用jquery提交表单
				if("1".equals(request.getParameter("useForTree"))){
			%>
		    var formData = $("#form").serialize();
		    $.ajax({
		    	dataType: "text/html",
		    	type:"POST",
		    	url:"<%=request.getContextPath()%>/hsp/hspStaffBaseinfo.do",
		    	processData:true,
		    	data:formData,
		    	error: function(a, b, c){
	        		alert(a + "-" + b + "-" + c);
	        	},
		    	success:function(data){
		    		if(data != null){
		    			try{
		    				var json = eval(data);
		    			}catch(e){
		    				alert("请重新登录！");
		    				return;
		    			}
		    			if(json[0].flag == '1'){
		      				var hspId = $("#hspConfigBaseinfoId").val();
		      				var deptCode = $("#deptCode").val();
		      				if(hspId != null && hspId.length > 0){
			      				if(deptCode != null && deptCode.length > 0){
			      					parent.frames["treeFrame"].refreshByIdAndType("dept", hspId, null, deptCode);
			      				}else{
			      					parent.frames["treeFrame"].refreshByIdAndType("otherdept", hspId);
		      					}
		      				}
		      				alert(json[0].msg);
		    				location.reload(false);	
		    			}else{
		    				alert(json[0].msg);
		    			}
		    		}
		    	}
		    });
			<%
				}else{
			%>
			document.form.submit();
			<%
				}
			%>
		}
		function goBack(url){
			window.location=url;
	    }
		
		function selectDept(hspIdInput, deptNameInput, deptCodeInput){
			var hspId = $("#"+hspIdInput).val();
			if($.trim(hspId) == ''){
				alert("请先选择机构！");
				return;
			}
			add("<%=request.getContextPath()%>/hsp/healthRegisterTree.do?verbId=getDept&hspId="+hspId, deptNameInput, deptCodeInput);
		}
		  function clearDept(_hspId){
		    	if(_hspId.value == ""){
		    		document.form.deptCode.value = "";
		    		document.form.deptName.value = "";
		    	}
		    }

		function extendInfo(){
				var _div = $('#moreinfo_div');
				var srcEle = document.getElementById('extendTd');
				while(srcEle.childNodes !=null && srcEle.childNodes.length > 0){
					srcEle.removeChild(srcEle.childNodes[0]);
				}
				if(_div.css("display") == "none"){
					var _text = document.createTextNode("隐藏 ︽");
					srcEle.appendChild(_text);
					Set_Cookie("isHiddenMore_staff", true, 7);
				}else{
					var _text = document.createTextNode("显示更多︾");
					srcEle.appendChild(_text);
					Set_Cookie("isHiddenMore_staff", false, 7);
				}
				_div.slideToggle();
			}
			function showHiddenMore(){
				var _div = $('#moreinfo_div');
				var srcEle = document.getElementById('extendTd');
				if(Get_Cookie("isHiddenMore_staff") == "false"){
					if(_div.css("display") != "none"){
						while(srcEle.childNodes !=null && srcEle.childNodes.length > 0){
							srcEle.removeChild(srcEle.childNodes[0]);
						}
						var _text = document.createTextNode("显示更多︾");
						srcEle.appendChild(_text);
						_div.css("display", "none");
					}
				}else{
					if(_div.css("display") == "none"){
						while(srcEle.childNodes !=null && srcEle.childNodes.length > 0){
							srcEle.removeChild(srcEle.childNodes[0]);
						}
						var _text = document.createTextNode("隐藏 ︽");
						srcEle.appendChild(_text);
						_div.css("display", "block");
					}
				}
			};
</script>
	</head>
	<body onload="showCommMessage('','${message }','1');load();showHiddenMore()	">
		<form name="form" id="form" action="<%=request.getContextPath()%>/hsp/hspStaffBaseinfo.do" method="post"
			enctype="multipart/form-data">
			<input type="hidden" id="verbId" name="verbId" value="add" />
			<input type="hidden" name="useForTree" value="<%=request.getParameter("useForTree") %>" />;
				<table align="center" border="0" cellpadding="0" cellspacing="0" class="tblFill">
					<tr>
						<td class="tblTitle" colspan="4">
							<span>※</span>
							<bean:message bundle="hsp.hspLocale" key="hsp.jsp.hspstaffbaseinfoadd.addUser" />
							<span>※</span>
						</td>
					</tr>
					<tr>
						<td class="tblLable" style="width:20%">
							<span>*</span>
							<bean:message bundle="hsp.hspLocale" key="hsp.jsp.hspstaffbaseinfodetail.organ" />
							：
						</td>
						<td style="padding-right: 40px; width: 30%">
							<input type="text" id="hspConfigBaseinfoName" name="hspConfigBaseinfoName" readonly
								value="<%=Converter.toBlank(data.getHspConfigBaseinfoName())%>" onkeydown="huiche()" onkeypress="eventOnKeyPress('deptName')" />
							<input type="hidden" id="hspConfigBaseinfoId" name="hspConfigBaseinfoId"
								onpropertychange="clearDept(this)"
								value="<%=Converter.toBlank(data.getHspConfigBaseinfoId())%>" require="true" msg="<bean:message  bundle="hsp.hspLocale" key="hsp.jsp.common.warn16"/>"/>
							<%
								if (!data.isStaffManagerRole()) {
							%>
							<img src="<%=request.getContextPath()%>/include/images/select.gif" style="cursor: pointer; position: absolute;"
								onclick="add('<%=request.getContextPath()%>/hsp/hspStaffBaseinfo.do?verbId=getHsp&hspType=3','hspConfigBaseinfoName','hspConfigBaseinfoId','','empNo','<%=data.getPersonnelCode().equals("true") ? "1"
								: "0"%>')" />
							<%
								}
							%>
						</td>

						<td class="tblLable" style="width:20%">
							科室：
						</td>
						<td style="padding-right:40px; width:30%">
							<input type="text" id="deptName" name="deptName" readonly
								value="<%=Converter.toBlank(data.getDeptName())%>" onkeydown="huiche()" onkeypress="eventOnKeyPress('empNo')" />
							<input type="hidden" id="deptCode" name="deptCode"
								value="<%=Converter.toBlank(data.getDeptCode())%>"/>
							<img src="<%=request.getContextPath()%>/include/images/select.gif" style="cursor: pointer; position: absolute;"
								onclick="selectDept('hspConfigBaseinfoId', 'deptName', 'deptCode')" />
						</td>
					</tr>
					<tr>
						<td class="tblLable"">
							<span>*</span>
							<bean:message bundle="hsp.hspLocale" key="hsp.jsp.hspstaffbaseinfoadd.userCode" />
						</td>
						<td>
							<input type="text" name="empNo" id="empNo" class="input"
								dataType="Require" msg="<bean:message  bundle="hsp.hspLocale" key="hsp.jsp.hspstaffbaseinfoadd.userCodeWrong"/>" 
								value="<%=data.getEmpNo()%>" onkeyup="value=value.replace(/[^\d\w]/g,'')" onkeypress="eventOnKeyPress('name')"
								onkeydown="huiche()" maxlength="18" />
						</td>
						<td class="tblLable">
							<span>*</span>
							<bean:message bundle="hsp.hspLocale" key="hsp.jsp.hspstaffbaseinfodetail.name" />
							：
						</td>
						<td>
							<input type="text" name="name" id="name" value="<%=data.getName()%>"
								dataType="Chinese" require="true" msg="姓名不能为空，且必须为中文" maxlength="13"
								onkeypress="eventOnKeyPress('idNo')" onkeydown="huiche()" onfocus="getInfomation()" />
						</td>
					</tr>
					<tr>
						<td class="tblLable">
							<span>*</span>
							<bean:message bundle="hsp.hspLocale" key="hsp.jsp.common.idNo" />
							：
						</td>
						<td>
							<input type="text" id="idNo" name="idNo" value="<%=data.getIdNo()%>"
								dataType="IdCard" msg="身份证号格式不正确"
								onkeydown="huiche()" onblur="setTime();checkGender()" onkeypress="eventOnKeyPress('birthdayYear')" maxlength="18"/>
						</td>
						<td class="tblLable">
							<span>*</span>
							<bean:message bundle="hsp.hspLocale" key="hsp.jsp.common.birthDay" />
							：
						</td>
						<td>
							<input type="text" name="birthdayYear" readonly="readonly" id="birthdayYear"
								value="<%=data.getBirthdayYear()%>" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'%y-%M-%d'})"
								onkeydown="huiche()" onkeypress="eventOnKeyPress('commConfigSexId')" maxlength="10" 
								dataType="Require" msg="<bean:message  bundle="hsp.hspLocale" key="hsp.jsp.hspstaffbaseinfoadd.birthDayNotNull"/>"/>
						</td>
					</tr>
					<tr>
						<td class="tblLable">
							<span>*</span>
							<bean:message bundle="hsp.hspLocale" key="hsp.jsp.hspstaffbaseinfodetail.sex1" />
							：
						</td>
						<td>
							<select id="commConfigSexId" name="commConfigSexId" onkeydown="huiche()"
								dataType="Require" msg="<bean:message  bundle="hsp.hspLocale" key="hsp.jsp.hspstaffbaseinfoadd.sexNotNull"/>"
								onkeypress="eventOnKeyPress('commConfigNationalityName')">
								<option/>
								<%
									List<?> commConifgSexIdList = data.getCommConfigSexIdList();
									if (commConifgSexIdList != null) {
										Iterator<?> iterator = commConifgSexIdList.iterator();
										while(iterator.hasNext()) {
											CommConfigSex nCommConfigSex = (CommConfigSex)iterator.next();
											String tempId = nCommConfigSex.getItemCode();
											String tempName = nCommConfigSex.getItemName();
								%>
								<option value="<%=tempId%>" <%=tempId.equals(data.getCommConfigSexId()) ? "selected"
									: ""%>>
									<%=tempName%>
								</option>
								<%
										}
									}
								%>
							</select>
						</td>
						<td class="tblLable">
							<span>*</span>
							<bean:message bundle="hsp.hspLocale" key="hsp.jsp.hspstaffbaseinfodetail.nation1" />
							：
						</td>
						<td style="padding-right: 40px; width: 30%">
							<input type="text" id="displayInputId_2" value="<%=data.getCommConfigNationalityName()%>"
								onkeypress="eventOnKeyPress('officeTel')" name="commConfigNationalityName" onkeydown="huiche()" readonly />
							<input type="hidden" id="hiddenInputId_2" value="<%=data.getCommConfigNationalityId()%>" name="commConfigNationalityId"
								dataType="Require" msg="<bean:message  bundle="hsp.hspLocale" key="hsp.jsp.common.warn9"/>"/>
							<img src="<%=request.getContextPath()%>/include/images/select.gif" style="cursor: pointer; position: absolute;"
								onclick="add('<%=request.getContextPath()%>/hsp/hspStaffBaseinfo.do?verbId=nationality','displayInputId_2','hiddenInputId_2','','tel','0')" />
						</td>
					</tr>
					<tr>
						<td class="tblLable">
							<bean:message bundle="hsp.hspLocale" key="hsp.jsp.common.workDate" />
							：
						</td>
						<td>
							<input type="text" name="startWorkDateYear" id="startWorkDateYear"
								value="<%=data.getStartWorkDateYear()%>" onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'%y-%M-%d'})"
								onkeydown="huiche()" maxlength="10"/>
						</td>
						<td class="tblLable">
							从居民中选择
						</td>
						<td>
							<input type="hidden" id="hiddenInputId_3" name="securityUserBaseinfoId"
								value="<%=data.getSecurityUserBaseinfoId()%>" />
							<input type="hidden" id="checkisorno" value="no" />
							<input type="hidden" id="displayInputId_3" value="" />
							<img src="<%=request.getContextPath()%>/include/images/select.gif" style="cursor: pointer;"
								onclick="add('<%=request.getContextPath()%>/hsp/hspStaffBaseinfo.do?verbId=getAllPeople','displayInputId_3','hiddenInputId_3')" />
						</td>
					</tr>
					<tr>
						<td class="tblLable">
							<bean:message bundle="hsp.hspLocale" key="hsp.jsp.common.officePhone" />
							：
						</td>
						<td>
							<input type="text" value="<%=data.getOfficeTel()%>" onkeypress="eventOnKeyPress('mobileTel')" id="tel"
								style="font-size: 12px" onblur="checktel()" name="officeTel" onkeydown="huiche()" maxlength="20" />
						</td>
						<td class="tblLable">
							<bean:message bundle="hsp.hspLocale" key="hsp.jsp.common.phone" />
							：
						</td>
						<td>
							<input type="text" name="mobileTel" onkeypress="eventOnKeyPress('workCertificateNo')"
								value="<%=data.getMobileTel()%>" id="mobilecode" onblur="checkcell()" onkeydown="huiche()" maxlength="20" />
						</td>
					</tr>
					<tr>
						<td class="tblLable">
							<bean:message bundle="hsp.hspLocale" key="hsp.jsp.common.doctorCardId" />
							：
						</td>
						<td>
							<input type="text" name="workCertificateNo" value="<%=data.getWorkCertificateNo()%>"
								onkeypress="eventOnKeyPress('islocation')" onkeyup="value=value.replace(/[^\d\w]/g,'')" onkeydown="huiche()"
								maxlength="32" />
						</td>
						<td class="tblLable">
							<bean:message bundle="hsp.hspLocale" key="hsp.jsp.common.onStatusSign" />
							：
						</td>
						<td>
							<select name="islocation" onkeydown="huiche()"
								onkeypress="eventOnKeyPress('commDictPublicCharId1')">
								<option value=""></option>
								<%
									boolean isLocation = true; 
									if (data.getIslocation() != null && data.getIslocation() >= 0) {
										isLocation = data.getIslocation() == 1L;
									}
								%>
								<option value="1" <%=isLocation ? "selected" : ""%>>
									<bean:message bundle="hsp.hspLocale" key="hsp.jsp.common.on" />
								</option>
								<option value="0" <%=isLocation ? "" : "seleted"%>>
									<bean:message bundle="hsp.hspLocale" key="hsp.jsp.common.notIn" />
								</option>
							</select>
						</td>
					</tr>
				</table>
			<div id="moreinfo_div" style="display: none">
				<table align="center" border="0" cellpadding="0" cellspacing="0" class="tblFill" style="margin-top: -1px">
					<tr>
						<td class="tblLable" style="width:20%">
							<bean:message bundle="hsp.hspLocale" key="hsp.jsp.common.majorClass" />
							：
						</td>
						<td style="width:30%">
							<select onkeypress="eventOnKeyPress('commDictPublicCharId2')"
								name="commDictPublicCharId1" onkeydown="if(event.keyCode==13)event.keyCode=9">
								<option value=""></option>
								<%
									if (data.getCommDictPublicCharId1List() != null
											&& data.getCommDictPublicCharId1List().size() > 0) {
										for (int i = 0; i < data.getCommDictPublicCharId1List().size(); i++) {
											CommDictPublicChar nCommDictPublicChar = (CommDictPublicChar) data
													.getCommDictPublicCharId1List().get(i);
								%>
								<option value="<%=nCommDictPublicChar.getId()%>"
									<%out.print(data.getCommDictPublicCharId1().trim().equals(nCommDictPublicChar.getId())?"selected":""); %>>
									<%=nCommDictPublicChar.getDictValue()%>
								</option>
								<%
									}
									}
								%>
							</select>
						</td>
						<td class="tblLable" style="width:20%">
							<bean:message bundle="hsp.hspLocale" key="hsp.jsp.common.doctorSort" />
							：
						</td>
						<td style="width:30%">
							<select onkeypress="eventOnKeyPress('commConfigPositiontitleId')"
								name="commDictPublicCharId2" onkeydown="if(event.keyCode==13)event.keyCode=9">
								<option value=""></option>
								<%
									if (data.getCommDictPublicCharId2List() != null
											&& data.getCommDictPublicCharId2List().size() > 0) {
										for (int i = 0; i < data.getCommDictPublicCharId2List().size(); i++) {
											CommDictPublicChar nCommDictPublicChar = (CommDictPublicChar) data
													.getCommDictPublicCharId2List().get(i);
								%>
								<option value="<%=nCommDictPublicChar.getId()%>"
									<%out.print(data.getCommDictPublicCharId2().trim().equals(nCommDictPublicChar.getId())?"selected":""); %>>
									<%=nCommDictPublicChar.getDictValue()%>
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
							<bean:message bundle="hsp.hspLocale" key="hsp.jsp.common.managePost" />
						</td>
						<td>
							<select onkeypress="eventOnKeyPress('commConfigEmptitleId')"
								name="commConfigPositiontitleId" onkeydown="if(event.keyCode==13)event.keyCode=9">
								<option value=""></option>
								<%
									if (data.getCommConfigPositiontitleIdList() != null
											&& data.getCommConfigPositiontitleIdList().size() > 0) {
										for (int i = 0; i < data.getCommConfigPositiontitleIdList()
												.size(); i++) {
											CommConfigPositiontitle commConfigPositiontitle = (CommConfigPositiontitle) data
													.getCommConfigPositiontitleIdList().get(i);
								%>
								<option value="<%=commConfigPositiontitle.getItemCode()%>"
									<%= data.getCommConfigPositiontitleId().trim().equals(commConfigPositiontitle.getItemCode())?"selected":"" %>>
									<%=commConfigPositiontitle.getItemName()%>
								</option>
								<%
									}
									}
								%>
							</select>
						</td>
						<td class="tblLable">
							<bean:message bundle="hsp.hspLocale" key="hsp.jsp.common.skillJudge" />
							：
						</td>
						<td>
							<select onkeypress="eventOnKeyPress('commDictPublicCharId3')" name="commConfigEmptitleId"
								onkeydown="if(event.keyCode==13)event.keyCode=9">
								<option value=""></option>
								<%
									if (data.getCommConfigEmptitleIdList() != null
											&& data.getCommConfigEmptitleIdList().size() > 0) {
										for (int i = 0; i < data.getCommConfigEmptitleIdList().size(); i++) {
											CommConfigEmptitle nCommDictPublicChar = (CommConfigEmptitle) data
													.getCommConfigEmptitleIdList().get(i);
								%>
								<option value="<%=nCommDictPublicChar.getItemCode()%>"
									<%out.print(data.getCommConfigEmptitleId().trim().equals(nCommDictPublicChar.getItemCode())?"selected":""); %>>
									<%=nCommDictPublicChar.getItemName()%>
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
							<bean:message bundle="hsp.hspLocale" key="hsp.jsp.common.skillPost" />
							：
						</td>
						<td>
							<select onkeypress="eventOnKeyPress('commConfigDegreeId')" name="commDictPublicCharId3"
								onkeydown="if(event.keyCode==13)event.keyCode=9">
								<option value=""></option>
								<%
									if (data.getCommDictPublicCharId3List() != null
											&& data.getCommDictPublicCharId3List().size() > 0) {
										for (int i = 0; i < data.getCommDictPublicCharId3List().size(); i++) {
											CommDictPublicChar nCommDictPublicChar = (CommDictPublicChar) data
													.getCommDictPublicCharId3List().get(i);
								%>
								<option value="<%=nCommDictPublicChar.getId()%>"
									<%out.print(data.getCommDictPublicCharId3().trim().equals(nCommDictPublicChar.getId())?"selected":""); %>>
									<%=nCommDictPublicChar.getDictValue()%>
								</option>
								<%
									}
									}
								%>
							</select>
						</td>
						<td class="tblLable">
							<bean:message bundle="hsp.hspLocale" key="hsp.jsp.common.educationBg" />
							：
						</td>
						<td>
							<select onkeypress="eventOnKeyPress('commConfigDegreeLevelId')" name="commConfigDegreeId"
								onkeydown="if(event.keyCode==13)event.keyCode=9">
								<option value=""></option>
								<%
									if (data.getCommConfigDegreeIdList() != null
											&& data.getCommConfigDegreeIdList().size() > 0) {
										for (int i = 0; i < data.getCommConfigDegreeIdList().size(); i++) {
											CommConfigDegree commConfigDegree = (CommConfigDegree) data
													.getCommConfigDegreeIdList().get(i);
								%>
								<option value="<%=commConfigDegree.getItemCode()%>"
									<%= data.getCommConfigDegreeId().trim().equals(commConfigDegree.getItemCode())?"selected":"" %>>
									<%=commConfigDegree.getItemName()%>
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
							<bean:message bundle="hsp.hspLocale" key="hsp.jsp.common.degree" />
							：
						</td>
						<td>
							<select onkeypress="eventOnKeyPress('commConfigProfessionId')"
								name="commConfigDegreeLevelId" onkeydown="if(event.keyCode==13)event.keyCode=9">
								<option value=""></option>
								<%
									if (data.getCommConfigDegreeLevelIdList() != null
											&& data.getCommConfigDegreeLevelIdList().size() > 0) {
										for (int i = 0; i < data.getCommConfigDegreeLevelIdList()
												.size(); i++) {
											CommConfigDegreeLevel commConfigDegreeLevel = (CommConfigDegreeLevel) data
													.getCommConfigDegreeLevelIdList().get(i);
								%>
								<option value="<%=commConfigDegreeLevel.getItemCode()%>"
									<%= data.getCommConfigDegreeLevelId().trim().equals(commConfigDegreeLevel.getItemCode())?"selected":"" %>>
									<%=commConfigDegreeLevel.getItemName()%>
								</option>
								<%
										}
									}
								%>
							</select>
						</td>
						<td class="tblLable">
							<bean:message bundle="hsp.hspLocale" key="hsp.jsp.common.studyMajor" />
							：
						</td>
						<td>
							<select onkeypress="eventOnKeyPress('startWorkDateYear')" name="commConfigProfessionId"
								onkeydown="if(event.keyCode==13)event.keyCode=9">
								<option value=""></option>
								<%
									if (data.getCommConfigProfessionIdList() != null
											&& data.getCommConfigProfessionIdList().size() > 0) {
										for (int i = 0; i < data.getCommConfigProfessionIdList().size(); i++) {
											CommConfigProfession commConfigProfession = (CommConfigProfession) data
													.getCommConfigProfessionIdList().get(i);
								%>
								<option value="<%=commConfigProfession.getItemCode()%>"
									<%= data.getCommConfigProfessionId().trim().equals(commConfigProfession.getItemCode())?"selected":"" %>>
									<%= commConfigProfession.getItemName()%>
								</option>
								<%
									}
									}
								%>
							</select>
						</td>
					</tr>
				</table>
			</div>
			<table align="center" border="0" cellpadding="0" cellspacing="0" class="tblFill" style="margin-top: -1px">
				<tr>
					<td class="tblLable" style="text-align: center; width:80px; height: 12px; line-height: 12px"
						id="extendTd"
						onclick="extendInfo()">
						显示更多︾
					</td>
				</tr>
			</table>
			<!-- Sheet operation button area -->
			<div class="btnSave" id="budiv" name="budiv">
				<input type="button" name="btnSaveForm"
					value="<bean:message  bundle="hsp.hspLocale" key="hsp.jsp.hspstaffbaseinfoupdate.save1"/>" onclick="saveForm()" />
				<%
					if(!"1".equals(request.getParameter("useForTree"))){
				%>
					<input type="button" name="btnBack"
						value="<bean:message  bundle="hsp.hspLocale" key="hsp.jsp.hspstaffbaseinfoupdate.back"/>" onclick="history.go(-1)" />
				<%
					}
				%>
			</div>
		</form>
	</body>
</html>
