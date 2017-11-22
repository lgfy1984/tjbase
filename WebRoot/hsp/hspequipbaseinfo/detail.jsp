<%@ page contentType="text/html; charset=UTF-8"%>
<%@taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@taglib uri="/WEB-INF/struts-bean.tld" prefix="bean"%>
<jsp:useBean id="data" scope="request" class="com.tianjian.hsp.struts.form.HspEquipBaseinfoForm" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%if(request.getServerPort() == 80) {%>
		<base href="http://<%=request.getServerName()%><%=request.getContextPath()%>/" />
		<%} else {%>
		<base href="http://<%=request.getServerName()%>:<%=request.getServerPort()%><%=request.getContextPath()%>/" />
		<%}%>
		<title>卫生机构设备详细信息</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<meta http-equiv="pragma" content="no-cache" />
		<meta http-equiv="cache-control" content="no-cache" />
		<meta http-equiv="expires" content="0" />
		<script language="javascript" src="<%=request.getContextPath() %>/comm/include/javascript/CommMessage.js"></script>
		<script language="javascript" src="<%=request.getContextPath() %>/include/javascript/eventOnKeyPress.js"></script>
		<script language="javascript" src="<bean:message key="includes.js.validator.path" bundle="security" />" defer="defer"></script>
		<script>
			
			function goBack(){
				document.form.verbId.value = "query";
				document.form.submit()
			}
			
function qprint(){
    if(document.all.PrintActiveX == undefined || document.all.PrintActiveX ==null){
		document.body.innerHTML="<object id=\"PrintActiveX\" style=\"display: none\" classid=\"clsid:3ede745c-4adb-42a6-ab25-5621edbdfd6b\" codebase=\"<%=request.getContextPath()%>/include/QWPrint.cab#version=1,3,8,2\" ></object>"+document.body.innerHTML;
	}
	        PrintActiveX.pageName = "A4"; //设置纸张类型
       
			printed = true;
    
		    document.getElementById("buttonTable").style.display="none";
		     document.getElementById("editDiv").style.display="none";
		    PrintActiveX.PrintView();
		    document.getElementById("buttonTable").style.display="block";
		     document.getElementById("editDiv").style.display="block";
}
				
		</script>
		<link rel="stylesheet" rev="stylesheet" href="include/css/form.css" />
	</head>

	<body onload="showCommMessage('','<%=data.getMessage() %>','1')">
		<form name="form" id="form" method="post" action="<%=request.getContextPath()%>/hsp/hspEquipBaseinfo.do" enctype="multipart/form-data">
			<input type="hidden" name="verbId" value="" />
			<input type="hidden" name="idHidden" value="" />
			<table border="0" cellpadding="0" cellspacing="0" class="tblView">
				<tr>
					<td height="30px" class="tblTitle" colspan="6">
						<span>※</span>&nbsp;&nbsp;卫生机构设备详细信息&nbsp;&nbsp;
						<span>※</span>
				        <div id="editDiv"> <img src="include/images/print.gif" width="14" height="14" /><a href="javascript:qprint();"> 打印</a></div>
					</td>
				</tr>
				<tr>
					<td class="tblLable" nowrap>
						<span>*</span>医疗机构：
					</td>
					<td class="hou">
                         ${data.hspName }
					</td>
				
					<td class="tblLable" nowrap>
						<span>*</span>科室类别：
					</td>
					<td class="hou">
                        ${data.bdeptName }
					</td>
					
				</tr>
				<tr>   
				  <td class="tblLable" nowrap>
						<span>*</span>科室：
					</td>
					<td class="hou">
                        ${data.deptName }
					</td>
				   <td class="tblLable" nowrap >
					<span>*</span>设备名称：
					</td>
					<td class="hou">
						${data.equipName }	
                     </td>
				   
				</tr>
				<tr>
				        
                        <td class="tblLable" nowrap >
						<span>*</span>设备型号：
						</td>
						<td class="hou">
                          ${data.equipType}
						</td>
						<td class="tblLable" nowrap >
						   <span>*</span>出厂编号：
						</td>
						<td class="hou">
                         ${data.equipCode}
						</td>
						
				</tr>
				
				
				
				
				<tr>
				         <td class="tblLable" nowrap >
						  计算单位：
						</td>
						<td class="hou">
                            ${data.unit }
						</td>
                        <td class="tblLable" nowrap >
							 <span>*</span>供货单位：
						</td>
						<td class="hou">
                         ${data.supplier}
						</td>
						
				</tr>
				<tr>
				       <td class="tblLable" nowrap >
						   	 <span>*</span>供货单位联系方式：
						</td>
						<td class="hou">
                           ${data.supplierTel}
						</td>
						 <td class="tblLable" nowrap >
							 <span>*</span>生产厂家：
						</td>
						<td class="hou">
                         ${data.productor}
						</td>
					
				</tr>
					<tr>
					    	<td class="tblLable" nowrap >
						   	 <span>*</span>生产厂家联系方式：
						</td>
						<td class="hou" >
                            ${data.productorTel}
						</td>
						 <td class="tblLable" nowrap >
							 <span>*</span>生产日期：
						</td>
						<td class="hou">
                             ${data.productDate}
						</td>
					
				</tr>
				<tr>
				       	<td class="tblLable" nowrap >
						   	 <span>*</span>购入日期：
						</td>
						<td class="hou">
                             ${data.purchaseDate}
						</td>
						 	<td class="tblLable" nowrap >
						   	<span>*</span>开始使用日期：
						</td>
						<td class="hou">
                             ${data.instrumentUseStartDate}
						</td>
				</tr>
				<tr>
				         <td class="tblLable" nowrap >
						   	 终止使用日期：
						</td>
						<td class="hou">
                             ${data.instrumentUseEndDate}
						</td>
						 <td class="tblLable" nowrap >
							<span>*</span>购买单价（万元）：
						</td>
						<td class="hou">
                         ${data.price}
						</td>
						
				</tr>
				<tr>
				         <td class="tblLable" nowrap >
						   <span>*</span>	购买时新旧状态：
						</td>
						<td class="hou">
                          ${data.stateName }
						</td>
						 <td class="tblLable" nowrap >
							<span>*</span>设计寿命（年）：
						</td>
						<td class="hou">
                         ${data.designLifetime}
						</td>
						
				</tr>
				<tr>
				        <td class="tblLable" nowrap >
						   	<span>*</span>使用情况：
						</td>
						<td class="hou" >
                           ${data.useageName}
						</td>
						 <td class="tblLable" nowrap >
							<span>*</span>产地（国别）：
						</td>
						<td class="hou" >
						     ${data.sourceName}
						</td>
				</tr>
				<tr>
						 <td class="tblLable" nowrap >
							<span>*</span>用途：
						</td>
						<td class="hou"  colspan="3">
                          ${data.application}
						</td>
				</tr>
				<tr>
						 <td class="tblLable" nowrap >
							备注：
						</td>
						<td class="hou"  colspan="3">
                          ${data.comments}
						</td>
						
				</tr>
			</table>
			<%
				if(!"1".equals(request.getParameter("useForTree"))){
			%>
			<!-- Sheet operation button area -->
			<div class="btnSave">
			<table width="100%" border="0" cellpadding="0" cellspacing="0" id="buttonTable">
				<tr>
					<td height="30" align="center">
						<input type="button"
							class="btnSave"
							name="btnBack" value="返&nbsp&nbsp回"
							onclick="history.go(-1)" />
					</td>
				</tr>
			</table>
			<%
				}
			%>
			
			</div>
		</form>
	</body>
</html>