<%@ page contentType="text/html; charset=UTF-8"%>
<%@page import="com.tianjian.hsp.struts.form.HealthRegisterTreeForm"%>
<%@page import="com.tianjian.util.Converter"%>
<%@page import="java.util.LinkedHashMap"%>
<%@page import="java.util.Map"%>
<%@ taglib prefix="html" uri="/WEB-INF/struts-html.tld"%>
<%@ taglib prefix="bean" uri="/WEB-INF/struts-bean.tld"%>
<%@ taglib prefix="logic" uri="/WEB-INF/struts-logic.tld"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<jsp:useBean id="data" scope="request" class="com.tianjian.hsp.struts.form.HealthRegisterTreeForm" />
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
		    	var _select = document.getElementById("deptAttrCode");
		    	document.getElementById("deptAttrName").value=_select[_select.selectedIndex].text;
	            document.form.verbId.value = "add";
	            document.form.type.value="dept";
			    document.form.submit();
		    }
		   
		    function showMessage(){
		    	var json = <%=data.getMessage() != null ? data.getMessage() : "null"%>;
		        if(json != null && json.length > 0){
		        	var obj = json[0];
		        	if(obj.flag && obj.flag == 1){
		        		if(obj.parentTId != null){
		        			var left = self.parent.frames["treeFrame"];
		        			left.refreshNodeByTId(obj.parentTId);
		        		}
		        	}
	        		if(obj.msg){
	        			alert(obj.msg);
	        		}
		        }
		    }
		    window.onload = function(){showMessage();};
		</script>		
	</head>
	<%
		HealthRegisterTreeForm.DeptBean deptBean = data.getDeptBean();
	%>
	<body>
		<form name="form" method="post" action="<%=request.getContextPath()%>/hsp/healthRegisterTree.do">
			<input type="hidden" name="verbId" value="add" />
			<input type="hidden" name="type" value="dept" />
			<input type="hidden" name="parentTId" value="<%=Converter.toBlank(data.getParentTId()) %>" />
			<table border="0" cellpadding="0" cellspacing="0" class="tblFill" align="center">
				<tr>
					<td class="tblTitle" colspan="4">
					    <span>※</span>科室信息添加<span>※</span>
					</td>
				</tr>
				<tr>
				    <td class="tblLable"><span>*</span>所属机构:</td>
				    <td style="padding-right: 40px" colspan="3">
				        <input type="hidden" id="hspId" name="deptBean.hspId"  
				        	value="<%=Converter.toBlank(deptBean.getHspId()) %>" 
				        	maxlength="11" dataType="Require"  msg="所属机构不能为空！"/>
				        <input type="text" id="hspName" name="deptBean.hspConfigBaseinfoName" 
				        	value="<%=Converter.toBlank(deptBean.getHspConfigBaseinfoName()) %>" readonly="readonly" 
				        	dataType="LimitB" min="1" max="200" msg="所属机构不能为空！"
				        	onkeypress="eventOnKeyPress('seqNo')"/>
				        <img src="security/include/images/select.gif"
							style="cursor:pointer;position:absolute;"
							onclick="add('hsp/hspConfigBaseinfoBase.do?verbId=getHsp&hspType=3','hspName','hspId')" />
				    </td>
				</tr>
				<tr>
				    <td class="tblLable"><span>*</span>序号:</td>
				    <td>
				        <input type="text" id="seqNo" name="deptBean.seqNo" 
				        	value="<%=Converter.toBlank(deptBean.getSeqNo()) %>" 
				        	maxlength="11" require="true" dataType="Custom" regexp="^\d{1,11}$" msg="序号必须为1至11位的整数！"
				        	onkeypress="eventOnKeyPress('deptAttrCode')"/>
				    </td>
				    <td class="tblLable"><span>*</span>科室临床属性:</td>
				    <td>
				        <select id="deptAttrCode" name="deptBean.deptAttrCode"
				        	dataType="Require" msg="请选择一个科室临床属性！"
				        	onkeypress="eventOnKeyPress('healthBureauCode')">
					        <option value=""></option>
					        <%
					        LinkedHashMap<String, String> deptAttrMap = deptBean.getDeptAttrMap();
					        if(deptAttrMap != null){
					        	for(Map.Entry<String, String> entry : deptAttrMap.entrySet()){
					        		String selected = "";
					        		if(entry.getKey().equals(deptBean.getDeptAttrCode())){
					        			selected = "selected";
					        		}
					        		%>
					        		<option value="<%=entry.getKey()%>" <%=selected %>><%=entry.getValue() %></option>
					        		<%
					        	}
					        }					        
					        %>
					    </select>
					    <input type="hidden" id="deptAttrName" name="deptBean.deptAttrName" value="<%=Converter.toBlank(deptBean.getDeptAttrName()) %>"/>
				    </td>
				</tr>	
				<tr>
				    <td class="tblLable">卫生局代码:</td>
				    <td>
				        <input type="text" id="healthBureauCode" name="deptBean.healthBureauCode" 
				        	value="<%=Converter.toBlank(deptBean.getHealthBureauCode()) %>" 
				        	maxlength="32" dataType="LimitB" max="32" msg="卫生局代码最多不能超过32个字节（中文占3字节）"
				        	onkeypress="eventOnKeyPress('socialSecurityBureauCode')"/>
				    </td>
				    <td class="tblLable">社保局代码:</td>
				    <td>
				        <input type="text" id="socialSecurityBureauCode" name="deptBean.socialSecurityBureauCode" 
				        	value="<%=Converter.toBlank(deptBean.getSocialSecurityBureauCode()) %>" 
				        	maxlength="32" dataType="LimitB" max="32" msg="社保局代码最多不能超过32个字节（中文占3字节）"
				        	onkeypress="eventOnKeyPress('deptCode')"/>
				    </td>
				</tr>
				<tr>
				    <td class="tblLable"><span>*</span>科室代码:</td>
				    <td>
				        <input type="text" id="deptCode" name="deptBean.deptCode" 
				        	value="<%=Converter.toBlank(deptBean.getDeptCode()) %>" 
				        	maxlength="32" require="true" dataType="LimitB" min="1" max="32" msg="科室代码范围为1至32个字节（中文占3字节）"
				        	onkeypress="eventOnKeyPress('deptName')"/>
				    </td>
				    <td class="tblLable"><span>*</span>科室名称:</td>
				    <td>
				        <input type="text" id="deptName" name="deptBean.deptName" 
				        	value="<%=Converter.toBlank(deptBean.getDeptName()) %>" 
				        	maxlength="50" require="true" dataType="LimitB" min="1" max="40" msg="科室名称范围为1至40个字节（中文占3字节）"
				        	onkeypress="eventOnKeyPress('comments')"/>
				    </td>
				</tr>	
				<tr>
				    <td class="tblLable">备注:</td>
				    <td colspan="3">
				        <input type="text" id="comments" name="deptBean.comments" 
				        	value="<%=Converter.toBlank(deptBean.getComments()) %>" 
				        	maxlength="40" dataType="LimitB" max="40" msg="备注不能超过40个字节（中文占3字节）"
				        	onkeypress="eventOnKeyPress('btnSaveForm')"/>
				    </td>
				</tr>		
			</table>
			<!-- Sheet operation button area -->
			<div class="btnSave">
				<input type="button" id="btnSaveForm" name="btnSaveForm" value="<bean:message key="security.jsp.commom.button1" bundle="security"/>" onclick="save()" />
			</div>
		</form>
	</body>
</html>
