<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="html" uri="/WEB-INF/struts-html.tld"%>
<%@ taglib prefix="bean" uri="/WEB-INF/struts-bean.tld"%>
<%@ taglib prefix="logic" uri="/WEB-INF/struts-logic.tld"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="java.util.List"%>
<%@page import="com.tianjian.hsp.struts.form.OrgMenuForm"%>
<%@page import="com.tianjian.comm.bean.CommConfigDeptAttr"%>
<jsp:useBean id="dataForm" scope="request" class="com.tianjian.hsp.struts.form.OrgMenuForm" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<%
			if (request.getServerPort() == 80) {
		%>
		<base href="http://<%=request.getServerName()%><%=request.getContextPath()%>/" />
		<%
			} else {
		%>
		<base href="http://<%=request.getServerName()%>:<%=request.getServerPort()%><%=request.getContextPath()%>/" />
		<%
		    dataForm = (OrgMenuForm)request.getAttribute("dataForm");
			}
		%>
		<title>
			<bean:message key="security.jsp.securityconfigmenus.update.title" bundle="conf.security.security" />
		</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<meta http-equiv="pragma" content="no-cache" />
		<meta http-equiv="cache-control" content="no-cache" />
		<meta http-equiv="expires" content="0" />
		<link type="text/css" rev="stylesheet" rel="stylesheet" href="include/css/form.css" />
		<script language="javascript" src="<bean:message key="includes.js.validator.path" bundle="security" />" defer="defer"></script>
		<script language="javascript" src="<bean:message key="include.js.TJMessage.path" bundle="security" />"></script>
		<script language="javascript" src="include/javascript/eventOnKeyPress.js"></script>
		<script language="javascript" src="<bean:message key="security.js.gettext.path" bundle="security" />"></script>
		<script type="text/javascript" src="security/include/javascript/jianbian.js"></script>
		<link rel="stylesheet" href="include/css/open.css" />
		<script language="javascript">
		    function updatePara(){
		        var seqNo = document.getElementById("seqNo");
		        var deptAttrCode = document.getElementById("deptAttrCode");
		        var deptCode = document.getElementById("deptCode");
		        var deptName = document.getElementById("deptName");
		        var flag = true;
		        if(seqNo==null||seqNo==""){
		            flag = false;
		            alert("序号不能为空");
		        }
		        if(deptAttrCode==null||deptAttrCode==""){
		            flag = false;
		            alert("科室属性不能为空");
		        }
		        if(deptCode==null||deptCode==""){
		            flag = false;
		            alert("科室代码不能为空");
		        }
		        if(deptName==null||deptName==""){
		            flag = false;
		            alert("科室名不能为空");
		        }
		        if(flag){
		            document.form.verbId.value = "modifyDept";
				    document.form.submit();
		        }
		    }
		    function showMessage(status,message,detpName){
		        if(status==1){
		            
		                var left = self.parent.frames["menuFrame"];
		                left.modifyNode(detpName);
		                alert("保存成功！");
		            
		        }
		    }
		</script>		
	</head>
	<body onload="showMessage('<%=dataForm.getStatus() %>','<%=dataForm.getMessage() %>','<%=dataForm.getDeptName() %>');">
		<form name="form" method="post" action="<%=request.getContextPath()%>/hsp/orgMenu.do">
			<input type="hidden" name="verbId" value="modifyDept" />
			<input type="hidden" name="hspConfigBaseinfoId" value="${dataForm.hspConfigBaseinfoId}" />		
			<input type="hidden" name="hspConfigBaseinfoName" value="${dataForm.hspConfigBaseinfoName}" />
			<input type="hidden" name="menuId" value="${dataForm.menuId}" />
			<input type="hidden" name="pId" value="<%=dataForm.getPId() %>" />			
			<table border="0" cellpadding="0" cellspacing="0" class="tblFill" align="center">
				<tr>
					<td class="tblTitle" colspan="4">
					    <span>※</span>科室信息修改<span>※</span>
					</td>
				</tr>
				<tr>
				    <td class="tblLable"><span>*</span>序号:</td>
				    <td>
				        <input type="text" id="seqNo" name="seqNo" value="${dataForm.seqNo}" maxlength="11" />
				    </td>
				    <td class="tblLable"><span>*</span>科室临床属性:</td>
				    <td>
				        <select id="deptAttrCode" name="deptAttrCode" onkeypress="eventOnKeyPress('btnSaveForm')"  onchange="select()">
					        <option value=""></option>
					        <%
					        List<CommConfigDeptAttr> deptAttrList = dataForm.getDeptAttrList();
					        if(deptAttrList!=null&&deptAttrList.size()>0){
					        	for(CommConfigDeptAttr deptAttr : deptAttrList){
					        		String selected = "";
					        		if(dataForm.getDeptAttrCode().equals(deptAttr.getItemCode())){
					        			selected = "selected"; 
					        		}
					        		%>
					        		<option value="<%=deptAttr.getItemCode()%>" <%=selected%> ><%=deptAttr.getItemName() %></option>
					        		<%
					        	}
					        }					        
					        %>
					    </select>
				    </td>
				</tr>	
				<tr>
				    <td class="tblLable">卫生局代码:</td>
				    <td>
				        <input type="text" id="healthBureauCode" name="healthBureauCode" value="${dataForm.healthBureauCode}" maxlength="32"/>
				    </td>
				    <td class="tblLable">社保局代码:</td>
				    <td>
				        <input type="text" id="socialSecurityBureauCode" name="socialSecurityBureauCode" value="${dataForm.socialSecurityBureauCode}" maxlength="32"/>
				    </td>
				</tr>
				<tr>
				    <td class="tblLable"><span>*</span>科室代码:</td>
				    <td>
				        <input type="text" id="deptCode" name="deptCode" value="${dataForm.deptCode}" readonly="readonly" maxlength="32"/>
				    </td>
				    <td class="tblLable"><span>*</span>科室名称:</td>
				    <td>
				        <input type="text" id="deptName" name="deptName" value="${dataForm.deptName}" maxlength="50"/>
				    </td>
				</tr>	
				<tr>
				    <td class="tblLable">备注:</td>
				    <td colspan="3">
				        <input type="text" id="comments" name="comments" value="${dataForm.comments}" maxlength="40"/>
				    </td>
				</tr>		
			</table>
			<!-- Sheet operation button area -->
			<div class="btnSave">
				<input type="button" name="btnSaveForm" value="<bean:message key="security.jsp.commom.button1" bundle="security"/>" onclick="updatePara();" />
				<input type="button" name="btnSaveForm" value="返回" onclick="javascript:history.back(-1)" />
			</div>
		</form>
	</body>
</html>
