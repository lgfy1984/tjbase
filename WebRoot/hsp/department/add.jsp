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
		    function save(){
		    	if(!Validator.Validate(document.form, 3)){
		    		return;
		    	}
	            document.form.verbId.value = "addDept";
			    document.form.submit();
		    }
		    function showMessage(status,treeName,orgId,deptCode){
		        if(status==1){
		            var left = self.parent.frames["menuFrame"];
		            left.addNode(treeName,orgId,deptCode);
		            alert("保存成功！");
		        }
		    }
		</script>		
	</head>
	<body onload="showMessage('<%=dataForm.getStatus() %>','<%=dataForm.getDeptName() %>','<%=dataForm.getHspConfigBaseinfoId() %>','<%=dataForm.getDeptCode() %>');">
		<form name="form" method="post" action="<%=request.getContextPath()%>/hsp/orgMenu.do">
			<input type="hidden" name="verbId" value=addDept />
			<input type="hidden" name="hspConfigBaseinfoId" value="${dataForm.hspConfigBaseinfoId}" />		
			<input type="hidden" name="hspConfigBaseinfoName" value="${dataForm.hspConfigBaseinfoName}" />	
			<input type="hidden" name="menuId" value="${dataForm.menuId}" />		
			<table border="0" cellpadding="0" cellspacing="0" class="tblFill" align="center">
				<tr>
					<td class="tblTitle" colspan="4">
					    <span>※</span>科室信息添加<span>※</span>
					</td>
				</tr>
				<tr>
				    <td class="tblLable"><span>*</span>序号:</td>
				    <td>
				        <input type="text" id="seqNo" name="seqNo" maxlength="11" require="true" dataType="Custom" regexp="^\d{1,11}$" msg="序号必须为1至11位的整数！"/>
				    </td>
				    <td class="tblLable"><span>*</span>科室临床属性:</td>
				    <td>
				        <select id="deptAttrCode" name="deptAttrCode" onkeypress="eventOnKeyPress('btnSaveForm')" dataType="Require" msg="请选择一个科室临床属性！">
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
					        		<option value="<%=deptAttr.getItemCode()%>" <%=selected %>><%=deptAttr.getItemName() %></option>
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
				        <input type="text" id="healthBureauCode" name="healthBureauCode" maxlength="32" dataType="LimitB" max="32" msg="卫生局代码最多不能超过32个字节（中文占3字节）"/>
				    </td>
				    <td class="tblLable">社保局代码:</td>
				    <td>
				        <input type="text" id="socialSecurityBureauCode" name="socialSecurityBureauCode" maxlength="32" dataType="LimitB" max="32" msg="社保局代码最多不能超过32个字节（中文占3字节）"/>
				    </td>
				</tr>
				<tr>
				    <td class="tblLable"><span>*</span>科室代码:</td>
				    <td>
				        <input type="text" id="deptCode" name="deptCode" maxlength="32" require="true" dataType="LimitB" min="1" max="32" msg="科室代码范围为1至32个字节（中文占3字节）"/>
				    </td>
				    <td class="tblLable"><span>*</span>科室名称:</td>
				    <td>
				        <input type="text" id="deptName" name="deptName" maxlength="50" require="true" dataType="LimitB" min="1" max="40" msg="科室名称范围为1至40个字节（中文占3字节）"/>
				    </td>
				</tr>	
				<tr>
				    <td class="tblLable">备注:</td>
				    <td colspan="3">
				        <input type="text" id="comments" name="comments" maxlength="40" dataType="LimitB" max="40" msg="备注不能超过40个字节（中文占3字节）"/>
				    </td>
				</tr>		
			</table>
			<!-- Sheet operation button area -->
			<div class="btnSave">
				<input type="button" name="btnSaveForm" value="<bean:message key="security.jsp.commom.button1" bundle="security"/>" onclick="save()" />
				<input type="button" name="btnSaveForm" value="返回" onclick="javascript:history.back(-1)" />
			</div>
		</form>
	</body>
</html>
