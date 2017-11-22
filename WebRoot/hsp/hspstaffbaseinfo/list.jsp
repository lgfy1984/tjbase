<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib prefix="html" uri="/WEB-INF/struts-html.tld"%>
<%@ taglib prefix="bean" uri="/WEB-INF/struts-bean.tld"%>
<%@ taglib prefix="logic" uri="/WEB-INF/struts-logic.tld"%>
<%@page import="com.tianjian.util.comm.PageBean"%>
<jsp:useBean id="pb" scope="request" class="com.tianjian.util.comm.PageBean" />
<jsp:useBean id="dataForm" scope="request" class="com.tianjian.hsp.struts.form.HspStaffBaseinfoForm" />

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
	<%if(request.getServerPort() == 80) {%>
			<base href="http://<%=request.getServerName()%><%=request.getContextPath()%>/"/>
	<%} else {%>
			<base href="http://<%=request.getServerName()%>:<%=request.getServerPort()%><%=request.getContextPath()%>/"/>
	<%}%>
	<meta http-equiv="pragma" content="no-cache"/>
	<meta http-equiv="cache-control" content="no-cache"/>
	<meta http-equiv="expires" content="0"/>
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3"/>
	<meta http-equiv="description" content="This is my page"/>
	<script language="javascript" src="<bean:message key="security.js.gettext_staff.path" bundle="security" />"  ></script>
	<link rel="stylesheet" href="include/css/open.css" />
	<link type="text/css" rev="stylesheet" rel="stylesheet" href="include/css/form.css" />
	<script language="javascript" src="<%=request.getContextPath()%><bean:message  key="hsp.js.commCommMessage.path"  bundle="hsp.hspLocale"/>"></script>
	<script type="text/javascript" src="<%=request.getContextPath()%>/include/javascript/jianbian.js" defer="defer"></script>
	
		<script language="javascript">
			//添加
			function cmdAdd() {
			    document.form.verbId.value = "addInit";    
			    document.form.submit();
			}
			function queryForm(num) {
			if(num == 1){
		    	for(i=0; i<argArray.length; i++){
					document.getElementById(argArray[i]).value = document.getElementById(argArray[i]+"2").value;		
				}	
		    }else if(num == 0){
		    	var searchdiv = document.getElementById("searchDiv");
		    	var divinput = searchdiv.getElementsByTagName("input");
		    	var divselect = searchdiv.getElementsByTagName("select");
		    	for(i=0; i<divinput.length; i++){
					divinput[i].value = "";		
				}
				for(j=0; j<divselect.length; j++){
					divselect[j].value = "";		
				}
		    }	
			  document.form.page.value = 1; 
			  document.form.orderNo.value = ""; 
			  document.form.asc.value = ""; 
			  document.form.method = "POST";
			  document.form.verbId.value = "query";
			  document.form.submit();
			}
			function commandOrderBy(order, asc) { 
			  document.form.orderNo.value = order; 
			  document.form.asc.value = asc; 
			  document.form.method = "POST";
			  document.form.verbId.value = "query";
			  document.form.submit();
			}
			function goPage(page) {  
			   document.form.page.value = page;
			   document.form.verbId.value = "query";    
			   document.form.submit();
			}
			
			function goPage2() {			  
			  	var _tp=document.getElementById('_tp');
  var _total=document.getElementById('_total'); 
			    	if (!isMadeOf(_tp.value,'1234567890')) {
			      		alert("<bean:message  bundle="hsp.hspLocale" key="hsp.jsp.common.wrongInteger"/>!");
			      		return;
			    	}
			    	if (_tp.value<=0){
			    		alert("<bean:message  bundle="hsp.hspLocale" key="hsp.jsp.common.pageMustBigThanZero"/>!");
						return;
			    	}
			    	if(parseInt(_tp.value)>parseInt(_total.value)){
			      		alert("<bean:message  bundle="hsp.hspLocale" key="hsp.jsp.common.cannotBigThanTotal"/>!");
			      		return;
			    	} 
			  	  
			  	document.form.verbId.value = "query";    
			  	document.form.submit();
			}	
			function isMadeOf(val,str)
			{
				var jj;
				var chr;
				for (jj=0;jj<val.length;++jj){
					chr=val.charAt(jj);
					if (str.indexOf(chr,0)==-1)
						return false;
				}
				return true;
			}
//查看详细时候用
			function viewForm(id){
				var array = id;
				var idHidden = id;
				var len = 0;
				if(id!=null && id!=""){ 
							len ++; 
				}
				if(len == 1){
					document.form.id.value = idHidden;
					document.form.verbId.value = "detail";
					document.form.submit();
				}else if(len <= 0){
					alert("<bean:message  bundle="hsp.hspLocale" key="hsp.jsp.common.warn4"/>!");
					return false;
				}else if(len > 1){
					alert("<bean:message  bundle="hsp.hspLocale" key="hsp.jsp.common.warn6"/>!");
					return false;
				}
			}	
			
//更新准备时候用
			function updateInitForm(id){
				var array = id;
				var idHidden = id;
				var len = 0;
				if(id!=null && id!=""){ 
							len ++; 
				}
				if(len == 1){
					document.form.id.value = idHidden;
					document.form.verbId.value = "updateInit";
					document.form.submit();
				}else if(len <= 0){
					alert("<bean:message  bundle="hsp.hspLocale" key="hsp.jsp.common.warn4"/>!");
					return false;
				}else if(len > 1){
					alert("<bean:message  bundle="hsp.hspLocale" key="hsp.jsp.common.warn6"/>!");
					return false;
				}
			}			
//删除时候用
			function deleteForm(id){
				var array = id;
				var len = 0;
				if(id!=null && id!=""){
					 
							len ++;
						 
				}
				if(len > 0){
					 if (!confirm("<bean:message  bundle="hsp.hspLocale" key="hsp.jsp.common.warn17"/>")) return false; 
					 document.form.id.value = id;
					 document.form.verbId.value = "delete";
					 document.form.submit();
				}else{
					alert("<bean:message  bundle="hsp.hspLocale" key="hsp.jsp.common.warn4"/>!");
					return false;
				}
			}
			function selectAll(){
				var checkbox = document.getElementsByName('checkbx');
				if(form.checkB.checked){
					for(i=0; i<checkbox.length; i++){
						checkbox[i].checked = true;
					}
				}else{
					for(i=0; i<checkbox.length; i++){
						checkbox[i].checked = false;
					}
				}
			}	
			//新增初始化工作，转到新增页面
			function addInitForm(){
				document.form.verbId.value = "addInit";
				document.form.submit();
			}
			
		</script>
		<script language="javascript">
	
	  function huiche(){
		if(event.keyCode==13){
			event.keyCode=9
		}
		
	} 
	       function cmdExcelExport(){				
				if(confirm("<bean:message  bundle="hsp.hspLocale" key="hsp.jsp.common.warn7"/>")){
				  	document.form.method = "POST";
				  	document.form.verbId.value = "elsExport";
				  	document.form.submit();
			  	}
			}
			function cmdExcelImport(){
				window.location="<%=request.getContextPath()%>/hsp/hspstaffbaseinfo/import.jsp";
			}
			function huiche(){
				if(event.keyCode==13){
					event.keyCode=9;
				}			
			}
		</script>
		<link href="<%=request.getContextPath()%>/include/css/form.css" rel="stylesheet" type="text/css" />
 		<script type="text/javascript" src="<%=request.getContextPath()%>/include/javascript/searchwindow.js"></script>	
		<script type="text/javascript" src="<%=request.getContextPath()%>/hsp/include/javascript/jianbian.js"></script>
		<link rel="stylesheet" href="<%=request.getContextPath()%>/include/css/searchwindow.css" />
  </head>  
  <body onload="showCommMessage('','${message }','1')">
  	<form name="form" action="<%=request.getContextPath()%>/hsp/hspStaffBaseinfo.do">
		<input type="hidden" name="verbId"/>
		<input type="hidden" name="orderNo" value="${dataForm.orderNo}"/>
		<input type="hidden" name="asc" value="${dataForm.asc}"/>
		<input type="hidden" name="idHidden"/>
		<input type="hidden" name="id"/>
<!--查询条件-->
<table border="0" cellpadding="0" cellspacing="0" class="tblSearch">	
	<tr>
		<td class="tblTitle" colspan="4">
			<bean:message  bundle="hsp.hspLocale" key="hsp.jsp.common.selectCondition"/>
		</td>
	</tr>				
	<tr>
		<td >
					<bean:message  bundle="hsp.hspLocale" key="hsp.jsp.common.itemName"/>：<input type="hidden" id="hiddenInputId_1" name="hspConfigBaseinfoId" value="${dataForm.hspConfigBaseinfoId}"/>
					<input type="text" id="displayInputId_1" name="hspConfigBaseinfoName" readonly="readonly" onkeydown="huiche()" value="${dataForm.hspConfigBaseinfoName}"/>
					<%
					//卫生人员管理者角色只能管理自己所在机构的卫生人员
					if(!dataForm.isStaffManagerRole()){
					%>
					<img src="<%=request.getContextPath()%>/include/images/select.gif" style="cursor:pointer;" 
					onclick="add('<%=request.getContextPath()%>/hsp/hspStaffBaseinfo.do?verbId=getHsp&hspType=3','displayInputId_1','hiddenInputId_1')" />
					<%
					}
					%>
					<bean:message  bundle="hsp.hspLocale" key="hsp.jsp.common.userId"/>：<input name="empNo" id="empNo" value="${dataForm.empNo}"  type="text" onkeydown="huiche()" />		
					<bean:message  bundle="hsp.hspLocale" key="hsp.jsp.common.name1"/>：<input name="name" id="name" value="${dataForm.name}"  type="text" onkeydown="huiche()" />
					<bean:message  bundle="hsp.hspLocale" key="hsp.jsp.common.idNo"/>：<input name="idNo" id="idNo" value="${dataForm.idNo}" " type="text"  onkeydown="if(event.keyCode==13){event.keyCode=9;document.getElementById('queryImg').className='queryImg_down'}" />	
					<input type="button" class="btnSave" id="queryImg" name="<bean:message  bundle="hsp.hspLocale" key="hsp.jsp.common.search2"/>" value="<bean:message  bundle="hsp.hspLocale" key="hsp.jsp.common.search1"/>" onclick="queryForm(0);" />
					<input type="button" class="btnSave" name="<bean:message  bundle="hsp.hspLocale" key="hsp.jsp.common.highGrade1"/>" value="<bean:message  bundle="hsp.hspLocale" key="hsp.jsp.common.highGrade"/>" onclick="searchMore('displayInputId_1#empNo#name#idNo#hiddenInputId_1');" />
					
					<div id="searchDiv" style="display:none;">
					<table border="0" width="600" cellspacing="0" cellpadding="0" align="center" style="font-size: 14px; margin: 0px 0px 1px 0px; text-align: center; border: 1px solid #BFBFBF;">
						<tr>
							<td height="25" colspan="4" class="trbg"><bean:message  bundle="hsp.hspLocale" key="hsp.jsp.common.highGradeSearch"/></td>
						</tr>				
						<tr>
							<td width="210" height="35" align="right">
								<bean:message  bundle="hsp.hspLocale" key="hsp.jsp.common.itemName"/>：
							</td>
							<td align="left" width="140">
								<input type="hidden" id="hiddenInputId_12" name="hspConfigBaseinfoIdHidden2" value="${dataForm.hspConfigBaseinfoId }"/>
								<input type="text" class="input" style="width:110px;" id="displayInputId_12" name="hspConfigBaseinfoName2" value="${dataForm.hspConfigBaseinfoName}" readonly="readonly"	onkeydown="huiche()"/>
								<%
								if(!dataForm.isStaffManagerRole()){
								%>
								<img src="<%=request.getContextPath()%>/include/images/select.gif" style="cursor:pointer;"	onclick="add('<%=request.getContextPath()%>/hsp/hspStaffBaseinfo.do?verbId=getHsp&hspType=3','displayInputId_12','hiddenInputId_12','searchDiv')" />
								<%
								}
								%>
							</td>							
							<td width="110" height="35" align="right">							
								<bean:message  bundle="hsp.hspLocale" key="hsp.jsp.common.userId"/>：
							</td>
							<td align="left" width="140">
								<input name="empNo2" id="empNo2" value="${dataForm.empNo}" class="input" type="text" style="width:110px" onkeydown="huiche()" />
							</td>
						</tr>
						<tr>
							<td height="35" align="right">									
								<bean:message  bundle="hsp.hspLocale" key="hsp.jsp.common.name1"/>：
							</td>
							<td align="left">
								<input name="name2" id="name2" value="${dataForm.name}" class="input" style="width:110px" type="text" onkeydown="huiche()" />
							</td>					
							<td height="33" align="right">									
								<bean:message  bundle="hsp.hspLocale" key="hsp.jsp.common.idNo"/>：
							</td>
							<td align="left">
								<input name="idNo2" id="idNo2" value="${dataForm.idNo}" class="input" style="width:110px;" type="text" onkeydown="huiche()" />
							</td>
						</tr>						
						<tr>							
							<td height="33" align="right">									
								<bean:message  bundle="hsp.hspLocale" key="hsp.jsp.common.doctorCardId"/>：
							</td>
							<td align="left">
								<input type="text" name="workCertificateNo" value="${dataForm.workCertificateNo}" class="input"  onkeyup="value=value.replace(/[^\d\w]/g,'')" style="width:110px" onkeydown="huiche()" maxlength="32"/>
							</td>
							<td align="right">									
								<bean:message  bundle="hsp.hspLocale" key="hsp.jsp.hspstaffbaseinfolist.nation"/>：
							</td>
							<td align="left">
								<input type="text" id="displayInputId_2" 
					 name="commConfigNationalityId" value="${dataForm.commConfigNationalityId}" class="input" style="width:85px" onkeydown="huiche()" readonly />
					<input type="hidden" id="hiddenInputId_2" name="nationalityIdHidden" value="${dataForm.commConfigNationalityName}"/>
					<img src="<%=request.getContextPath()%>/include/images/select.gif" style="cursor:pointer;" 
					onclick="add('<%=request.getContextPath()%>/hsp/hspStaffBaseinfo.do?verbId=nationality','displayInputId_2','hiddenInputId_2','searchDiv')" />
							</td>
						</tr>
						<tr>
							<td height="33" align="right">									
								<bean:message  bundle="hsp.hspLocale" key="hsp.jsp.common.majorClass"/>：
							</td>
							<td align="left">
								<select style="width:110px;"  name="commDictPublicCharId1" onkeydown="huiche()">
							<option value=""></option>
							<logic:iterate id="commBean" name="dataForm" property="commDictPublicCharId1List" indexId="ind">
								<option value="${commBean.id}" <logic:equal name="dataForm" property="commDictPublicCharId1" value="${commBean.id }">selected</logic:equal>>
									${commBean.dictValue}
								</option>
							</logic:iterate>
						</select>
							</td>
							<td align="right">									
								<bean:message  bundle="hsp.hspLocale" key="hsp.jsp.common.doctorSort"/>：
							</td>
							<td align="left">
								<select style="width:110px;"  name="commDictPublicCharId2" onkeydown="huiche()">
							<option value=""></option>
							<logic:iterate id="commBean" name="dataForm" property="commDictPublicCharId2List" indexId="ind">
								<option value="${commBean.id}" <logic:equal name="dataForm" property="commDictPublicCharId2" value="${commBean.id }">selected</logic:equal>>
									${commBean.dictValue}
								</option>
							</logic:iterate>
						</select>
							</td>
						</tr>
						<tr>
							<td height="33" align="right">									
								<bean:message  bundle="hsp.hspLocale" key="hsp.jsp.common.managePost"/>：
							</td>
							<td align="left">
								<select style="width:110px;"  name="commConfigPositiontitleId" onkeydown="huiche()">
							<option value=""></option>
							<logic:iterate id="commBean" name="dataForm" property="commConfigPositiontitleIdList" indexId="ind">
								<option value="${commBean.itemCode}" <logic:equal name="dataForm" property="commConfigPositiontitleId" value="${commBean.itemCode }">selected</logic:equal>>
									${commBean.itemName}
								</option>
							</logic:iterate>
						</select>
							</td>
							<td height="33" align="right">									
								<bean:message  bundle="hsp.hspLocale" key="hsp.jsp.common.educationBg"/>：
							</td>
							<td align="left">
								<select style="width:110px;"  name="commConfigDegreeId" onkeydown="huiche()">
							<option value=""></option>
							<logic:iterate id="commBean" name="dataForm" property="commConfigDegreeIdList" indexId="ind">
								<option value="${commBean.itemCode}" <logic:equal name="dataForm" property="commConfigDegreeId" value="${commBean.itemCode }">selected</logic:equal>>
									${commBean.itemName}
								</option>
							</logic:iterate>
						</select>
							</td>
							
						</tr>
						
						
						<tr>			
							<td height="33" align="right">									
								<bean:message  bundle="hsp.hspLocale" key="hsp.jsp.common.degree"/>：
							</td>
							<td align="left">
								<select style="width:110px;"  name="commConfigDegreeLevelId" onkeydown="huiche()">
							<option value=""></option>
							<logic:iterate id="commBean" name="dataForm" property="commConfigDegreeLevelIdList" indexId="ind">
								<option value="${commBean.itemCode}" <logic:equal name="dataForm" property="commConfigDegreeLevelId" value="${commBean.itemCode }">selected</logic:equal>>
									${commBean.itemName}
								</option>
							</logic:iterate>
						</select>
							</td>
							
						</tr>
						
						
						<tr>
							<td align="right">									
								<bean:message  bundle="hsp.hspLocale" key="hsp.jsp.common.skillPost"/>：
							</td>
							<td align="left">
								<select style="width:110px;"  name="commDictPublicCharId3" onkeydown="huiche()">
							<option value=""></option>
							<logic:iterate id="commBean" name="dataForm" property="commDictPublicCharId3List" indexId="ind">
								<option value="${commBean.id}" <logic:equal name="dataForm" property="commDictPublicCharId3" value="${commBean.id }">selected</logic:equal>>
									${commBean.dictValue}
								</option>
							</logic:iterate>
						</select>					
							</td>
							
							<td align="right">									
								<bean:message  bundle="hsp.hspLocale" key="hsp.jsp.common.studyMajor"/>：
							</td>
							<td align="left">
								<select style="width:110px;"  name="commConfigProfessionId" onkeydown="if(event.keyCode==13){event.keyCode=9;document.getElementById('queryImg').className='queryImg_down'}">
							<option value=""></option>
							<logic:iterate id="commBean" name="dataForm" property="commConfigProfessionIdList" indexId="ind">
								<option value="${commBean.itemCode}" <logic:equal name="dataForm" property="commConfigProfessionId" value="${commBean.itemCode }">selected</logic:equal>>
									${commBean.itemName}
								</option>
							</logic:iterate>
						</select>	
							</td>							
						</tr>						
						<tr>
							<td height="35" colspan="8" align="center" style="background-color:#F7F7F7">
								<input type="button" style="cursor: hand; background: url(include/images/tong.gif) no-repeat; border: 0; width: 50px; height: 23px; font-size: 12px;" id="queryImg" name="<bean:message  bundle="hsp.hspLocale" key="hsp.jsp.common.search2"/>" value="<bean:message  bundle="hsp.hspLocale" key="hsp.jsp.common.search1"/>" onclick="queryForm(1);" />
								<input type="button" style="cursor: hand; background: url(include/images/tong.gif) no-repeat; border: 0; width: 50px; height: 23px; font-size: 12px;"  name="<bean:message  bundle="hsp.hspLocale" key="hsp.jsp.common.close1"/>" value="<bean:message  bundle="hsp.hspLocale" key="hsp.jsp.common.close"/>" onclick="searchclosed();" />								
							</td>								
						</tr>
					</table>
				</div>			
			</td>
	</tr>
	</table>
			<!--列表标题-->
			<div id="dvh1">
			<table border="0" cellpadding="0" cellspacing="0" class="tblSearchList">
			
				<caption style="text-align:left;">
					<img src="<%=request.getContextPath()%>/comm/include/images/comm_list_nav_red.jpg" width="20" height="20" align="absmiddle" /> <span style="font-weight:bold; color:#333;"><bean:message key="security.jsp.commom.item1" bundle="security"/></span>
					<span class="titleBtn"><img  src="<bean:message key="security.jsp.securityconfigpublicclass.add" bundle="conf.security.security"/>"  align="absmiddle" />
						<a href="javascript:cmdAdd()" target="_self"><bean:message key="security.jsp.commom.button3" bundle="security"/></a></span>
			    </caption>
			    <thead>
			    	<tr class="lstName">
			            <th width="10%" height="26"><bean:message  bundle="hsp.hspLocale" key="hsp.jsp.common.userId"/><div><img src="include/images/cmdOrderByAsc.gif" alt="Ascender" vspace="7" onclick="commandOrderBy('1', '0')" /><img border="0" onclick="commandOrderBy('1', '1')" alt="Descender" src="include/images/cmdOrderByDesc.gif" /></div></th>
			            <th width="4%" height="26"><bean:message  bundle="hsp.hspLocale" key="hsp.jsp.common.name1"/><div><img src="include/images/cmdOrderByAsc.gif" alt="Ascender" vspace="7" onclick="commandOrderBy('2', '0')" /><img border="0" onclick="commandOrderBy('2', '1')" alt="Descender" src="include/images/cmdOrderByDesc.gif" /></div></th>
			            <th width="5%" height="26"><bean:message  bundle="hsp.hspLocale" key="hsp.jsp.hspstaffbaseinfolist.sex"/><div><img src="include/images/cmdOrderByAsc.gif" alt="Ascender" vspace="7" onclick="commandOrderBy('3', '0')" /><img border="0" onclick="commandOrderBy('3', '1')" alt="Descender" src="include/images/cmdOrderByDesc.gif" /></div></th>
			            <th width="15%" height="26"><bean:message  bundle="hsp.hspLocale" key="hsp.jsp.common.itemName"/><div><img src="include/images/cmdOrderByAsc.gif" alt="Ascender" vspace="7" onclick="commandOrderBy('4', '0')" /><img border="0" onclick="commandOrderBy('4', '1')" alt="Descender" src="include/images/cmdOrderByDesc.gif" /></div></th>
			            <th width="3%" height="26"><bean:message  bundle="hsp.hspLocale" key="hsp.jsp.common.search"/></th>
			            <th width="3%" height="26"><bean:message  bundle="hsp.hspLocale" key="hsp.jsp.common.chg"/></th>
			            <th width="3%" height="26"><bean:message  bundle="hsp.hspLocale" key="hsp.jsp.common.del"/></th>
			        </tr>
			    </thead>
				<tbody id="interval_row_id">
		<% if (dataForm.getIdsHiddenArray() != null && dataForm.getIdsHiddenArray().length > 0) {
			for (int i = 0; i < dataForm.getIdsHiddenArray().length; i++) {
		%>
		<tr>	
			<td class="leftPadding td_shenglue"><%=dataForm.getEmpNoArray()[i]%></td>			
			<td class="leftPadding td_shenglue"><%=dataForm.getNameArray()[i]%></td>
			<td class="leftPadding td_shenglue"><%=dataForm.getSexArray()[i]%></td>
			<td class="leftPadding td_shenglue"><%=dataForm.getHspNameArray()[i]%></td>
			<td class="td_shenglue">
              <img
				src="<%=request.getContextPath()%>/hsp/include/images/cmdView_s.jpg"
				style="cursor:hand; vertical-align:middle;" title="<bean:message  bundle="hsp.hspLocale" key="hsp.jsp.common.select"/>" onclick="viewForm('<%=dataForm.getIdsHiddenArray()[i]%>')" />
			</td>
			<td class=" td_shenglue">
			  <img
				src="<%=request.getContextPath()%>/hsp/include/images/cmdEdit_s.jpg"
				style="cursor:hand; vertical-align:middle;" title="<bean:message  bundle="hsp.hspLocale" key="hsp.jsp.common.change"/>" onclick="updateInitForm('<%=dataForm.getIdsHiddenArray()[i]%>')" />
			</td>
			<td class="td_shenglue">
			<img
				src="<%=request.getContextPath()%>/hsp/include/images/cmdDel_s.jpg"
				style="cursor:hand; vertical-align:middle;" title="<bean:message  bundle="hsp.hspLocale" key="hsp.jsp.common.delete"/>" onclick="deleteForm('<%=dataForm.getIdsHiddenArray()[i]%>')" />
			</td>
		</tr>
		<%}
		}
		%>
	</tbody>
				
			</table>	
			</div>
		<!--列表内容-->
		<table width="100%" align="center" class="tblScrollFooter">
			<tr>
					<td colspan="11" align="center" class="footer">
						<%
							int curPage = 0;
							int totalNum = 0;
							int pageSize = 0;

							curPage = pb.getPage();
							totalNum = pb.getCount();
							pageSize = pb.getPageSize();

							int totalPage = totalNum / pageSize;
							if (totalNum % pageSize > 0)
								totalPage += 1;
							if (totalPage == 0) {
								curPage = 0;
							}
						%>

						<input id="_total" name="totalPage" type="hidden"
							value="<%=totalPage%>" />
						<bean:message  bundle="hsp.hspLocale" key="hsp.jsp.common.D"/>
						<%=curPage%>
						<bean:message  bundle="hsp.hspLocale" key="hsp.jsp.common.pageTotal1"/>
						<%=totalPage%>
						<bean:message  bundle="hsp.hspLocale" key="hsp.jsp.common.pageTotal"/>
						<%=totalNum%>
						<bean:message  bundle="hsp.hspLocale" key="hsp.jsp.common.record"/>
						<%
						if (curPage > 1) {
						%>
						<a href="javascript:goPage('0')"><img
								src="include/images/shouye.gif" align="middle" border="0" /></a>&nbsp;
						<a href="javascript:goPage('<%=curPage - 1%>')"><img
								src="include/images/shang.gif" align="middle" border="0" /></a>&nbsp;
						<%
								} else {
								out.println("<img src='include/images/shouye_s.gif' align='middle'  border='0px' />&nbsp;&nbsp;<img src='include/images/shang_s.gif' align='middle'  border='0px' />&nbsp;");
							}
							if (curPage < totalPage) {
						%>
						<a href="javascript:goPage('<%=curPage + 1%>')"><img
								src="include/images/xia.gif" align="middle" border="0" /></a>&nbsp;
						<a href="javascript:goPage('<%=totalPage%>')"><img
								src="include/images/mo.gif" align="middle" border="0" /></a>&nbsp;
						<%
								} else {
								out.println("<img src='include/images/xia_x.gif' align='middle' border='0px' />&nbsp;&nbsp;<img src='include/images/mo_m.gif' align='middle'  border='0px' />&nbsp;");
							}
						%>
						<bean:message  bundle="hsp.hspLocale" key="hsp.jsp.common.text1"/>
						<input id="_tp" name="page" type="text" value="<%=curPage%>"
							size="2" class="txt" />
						<bean:message  bundle="hsp.hspLocale" key="hsp.jsp.common.page"/>&nbsp;
						<img style="cursor: hand;" src="include/images/go.jpg" width="18"
							border="0" onclick="goPage2()" />
					</td>
				</tr>	
				<tr>
					<td colspan="7" align="center" bgcolor="#ffffff" height="35px">
						<input type="button" id="exportImg" name="<bean:message  bundle="hsp.hspLocale" key="hsp.jsp.common.import1"/>" value="<bean:message  bundle="hsp.hspLocale" key="hsp.jsp.common.import"/>" onclick="cmdExcelImport()" style="cursor: hand; background: url(include/images/tong.gif) no-repeat; border: 0; width: 50px; height: 23px; font-size: 12px;" />
			 			<input type="button" id="exportImg" name="<bean:message  bundle="hsp.hspLocale" key="hsp.jsp.common.export1"/>" value="<bean:message  bundle="hsp.hspLocale" key="hsp.jsp.common.export"/>" onclick="cmdExcelExport()" style="cursor: hand; background: url(include/images/tong.gif) no-repeat; border: 0; width: 50px; height: 23px; font-size: 12px;" />
					</td>
				</tr>
	</table>
	</form>
	<script language="javascript" src="<%=request.getContextPath()%>/include/javascript/interval_row_color.js"></script>
	</body>
</html>	