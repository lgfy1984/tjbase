<%@page import="java.util.Iterator"%>
<%@page import="com.tianjian.security.bean.SecurityConfigPublicClass"%>
<%@page import="java.util.List"%>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="html" uri="/WEB-INF/struts-html.tld"%>
<%@ taglib prefix="bean" uri="/WEB-INF/struts-bean.tld"%>
<%@ taglib prefix="logic" uri="/WEB-INF/struts-logic.tld"%>
<jsp:useBean id="dataForm" scope="request" class="com.tianjian.security.struts.form.SecurityConfigPublicClassForm" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<%
			if (request.getServerPort() == 80) {
		%>
		<base
			href="http://<%=request.getServerName()%><%=request.getContextPath()%>/">
		<%
			} else {
		%>
		<base
			href="http://<%=request.getServerName()%>:<%=request.getServerPort()%><%=request.getContextPath()%>/">
		<%
			}
		%>
		<title><bean:message
				key="security.jsp.securityconfigpublicclass.update.title"
				bundle="conf.security.security" />
		</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<script language="javascript" src='<bean:message key="include.js.TJMessage.path" bundle="security" />'></script>
		<script language="javascript" src="include/javascript/eventOnKeyPress.js"></script>
		<script language="javascript" src="<bean:message key="includes.js.validator.path" bundle="security" />" defer="defer"></script>
		<script language="javascript" src="include/javascript/jquery-1.4.4.min.js"></script>
		<script language="javascript">
			function saveForm(){
				if(!Validator.Validate(document.forms.form,3)){
			      	return ;
			   	}
				if(document.form.classCode.value == ""){
					alert('<bean:message key="security.jsp.securityConfigParamClass1.update.warn" bundle="security"/>');
				 	return ;
				}
				if(isNaN(document.form.classCode.value)){
						alert('<bean:message key="comm.jsp.commconfigtrue.add.sc" bundle="conf.comm.CommMessageguoh"/>');
						return ; 
				}
				if(document.form.className.value == ""){
					alert('<bean:message key="security.jsp.securityConfigParamClass1.update.warn1" bundle="security"/>');
				 	return ;
				}
				if (confirmMessage("<bean:message key='security.jsp.commom.update' bundle='security'/>")){     
					 document.form.verbId.value = "update";
					    var formData = $("#form").serialize();
					    $.ajax({
					    	dataType: "text/html",
					    	type:"POST",
					    	url:"security/securityConfigPublicClass.do",
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
					    				var oldParentId = document.getElementById("oldParentId").value;
					    				var parentId = document.getElementById("parentId").value;
					    				if(oldParentId.length == 0 || parentId.length == 0){
					    					parent.frames["treeFrame"].refreshRootNode();
					    				}else{
						      				if(parentId.length > 0){
						      					parent.frames["treeFrame"].refreshByIdAndType(parentId, "1");
						      				}
						      				<%--当改变了一级模块类别时，旧的一级模块类别节点也要刷新--%>
					      					if(oldParentId.length > 0 && oldParentId != parentId){
						      					parent.frames["treeFrame"].refreshByIdAndType(oldParentId, "1");
						      				}
					    				}
					      				alert(json[0].msg);
					    			}else{
					    				alert(json[0].msg);
					    			}
					    		}
					    	}
					    });
			    } 
			}
			function showMessage(message){
				if(message != '' && message != null){
					alert(message);
					return;
				}
			}
			function changlevelFlag(){
			 	var parentId = document.getElementById("parentId").value;
			 	if(parentId != null && parentId.length > 0){
			 		var radios= document.getElementsByName("data.levelFlag")
			 		radios[1].checked=true;;
			 	}else{
			 		var radios= document.getElementsByName("data.levelFlag")
			 		radios[0].checked=true;;
			 	}
			 }
	</script>
		<link type="text/css" rev="stylesheet" rel="stylesheet" href="include/css/form.css" />
	</head>
	<body onload="showMessage('${dataForm.message}')">
		<form name="form" id="form" method="post"
			action="security/securityConfigPublicClass.do">
			<input type="hidden" name="verbId" value="update" />
			<input type="hidden" name="data.id" value="${dataForm.data.id}" />
			<input type="hidden" name="idHidden" value="${dataForm.idHidden}" />
			<input type="hidden" name="classCodeHidden" value="${dataForm.data.classCode}" />
			<input type="hidden" id="oldParentId" value="${dataForm.data.parentId}" />
			<table border="0" cellpadding="0" cellspacing="0" class="tblFill" align="center">
				<tr>
					 <td class="tblTitle" colspan="4"><span>※</span>  <bean:message
							key="security.jsp.securityconfigpublicclass.update.topic"
							bundle="conf.security.security" />  <span>※</span></td>
				</tr>
				<tr>
					<td class="tblLable">
						<span>*</span>
						<bean:message
							key="security.jsp.securityconfigpublicclass.common.className"
							bundle="conf.security.security" />
						：
					</td>
					<td>
						<input type="text" name="data.className"
							id="className" size="50" maxlength="40" 
							max="40" dataType="LimitB" msg="名称输入过长"
							onkeypress="eventOnKeyPress('serialNo')"
							value="${dataForm.data.className}" />
					</td>
					<td class="tblLable">
						<span>*</span>
							<bean:message
							key="security.jsp.securityconfigpublicclass.common.classCode"
							bundle="conf.security.security" />
						：
					</td>
					<td>
						<input type="text"  name="data.classCode"
							id="classCode" size="20" maxlength="9" 
							max="20" dataType="LimitB" msg="代码输入过长"
							onkeypress="eventOnKeyPress('className')"
							value="${dataForm.data.classCode}" />
						
					</td>
				</tr>
				<tr>
					<td class="tblLable">
						<bean:message
							key="security.jsp.securityconfigpublicclass.common.serialNo"
							bundle="conf.security.security" />
						：
					</td>
					<td>
						<input type="text"  name="data.serialNo"
							id="serialNo" size="20" maxlength="11" 
							onkeypress="eventOnKeyPress('comments')"
							value="${dataForm.data.serialNo}" readonly />
					</td>
					<td class="tblLable">
						<bean:message
							key="security.jsp.securityconfigpublicclass.common.comments"
							bundle="conf.security.security" />
						：
					</td>
					<td>
						<input type="text"  name="data.comments"
							id="comments" size="50" maxlength="40" 
							max="40" dataType="LimitB" msg="备注输入过长"
							onkeypress="eventOnKeyPress('parentId')"
							value="${dataForm.data.comments}" />
					</td>
				</tr>
				<tr>
					<td class="tblLable">
						<bean:message
							key="security.jsp.securityconfigpublicclass.common.parentId"
							bundle="conf.security.security" />
						：
					</td>
					<td>
						<select id="parentId"  name="data.parentId" 
							onkeypress="eventOnKeyPress('levelFlag')" onchange="changlevelFlag();">
							<option value=""></option>
							<%
								List<?> list = dataForm.getSecurityConfigPublicClassList();
								if(list != null){
									Iterator<?> iterator = list.iterator();
									String parentId = dataForm.getData().getParentId();
									while(iterator.hasNext()){
										SecurityConfigPublicClass publicClass = (SecurityConfigPublicClass)iterator.next();
										if(!publicClass.getId().equals(dataForm.getData().getId())){
							%>
							<option value="<%=publicClass.getId() %>" <%=publicClass.getId().equals(parentId) ? "selected" : "" %>>
								<%=publicClass.getClassName() %>
							</option>
							<%
										}
									}
								}
							%>
						</select>
					</td>
					<td class="tblLable">
						<bean:message
							key="security.jsp.securityconfigpublicclass.common.levelFlag"
							bundle="conf.security.security" />
						：
					</td>
					<td>
							<%if(dataForm.getData().getLevelFlag()!=null && String.valueOf(dataForm.getData().getLevelFlag()).equals("1")){ %>
								<input type="radio"  name="data.levelFlag" id="levelFlag" style="width: 30px;"
				          		onkeypress="eventOnKeyPress('picPath')"
				             	 value="1" checked="checked" />1级
				              <input type="radio"  name="data.levelFlag" id="levelFlag" style="width: 30px;"
				         		 onkeypress="eventOnKeyPress('picPath')"
				              		value="2" />2级
							<%}else if(dataForm.getData().getLevelFlag()!=null && String.valueOf(dataForm.getData().getLevelFlag()).equals("2")){ %>
								<input type="radio"  name="data.levelFlag" id="levelFlag" style="width: 30px;"
				          		onkeypress="eventOnKeyPress('picPath')"
				             	 value="1" />1级
				              <input type="radio"  name="data.levelFlag" id="levelFlag" style="width: 30px;"
				         		 onkeypress="eventOnKeyPress('picPath')" checked="checked" 
				              		value="2" />2级
							<%}else{ %>
							<%} %>
					</td>
				</tr>
				<tr>
					<td class="tblLable">
						<bean:message
							key="security.jsp.securityconfigpublicclass.common.picPath"
							bundle="conf.security.security" />
						：
					</td>
					<td>
						<input type="text" name="data.picPath" id="picPath" 
							size="30" maxlength="32" onkeypress="eventOnKeyPress('sysFlag')"
							max="32" dataType="LimitB" msg="图片路径输入过长"
							value="${dataForm.data.picPath}" />
					</td>
					<td class="tblLable">
						<span>*</span><bean:message
							key="security.jsp.securityconfigpublicclass.common.sysFlag"
							bundle="conf.security.security" />
						：
					</td>
					<td>
						<%if(dataForm.getData().getSysFlag()!= null && String.valueOf(dataForm.getData().getSysFlag()).equals("0")){ %>
							<input type="radio"  name="data.sysFlag" id="sysFlag" style="width: 30px;"
				          		onkeypress="eventOnKeyPress('redirectUrl')"
				             	 value="0" checked="checked" />系统外
				              <input type="radio"  name="data.sysFlag" id="sysFlag" style="width: 30px;"
				         		 onkeypress="eventOnKeyPress('redirectUrl')"
				              		value="1" />系统内
						<%}else if(dataForm.getData().getSysFlag()!=null && String.valueOf(dataForm.getData().getSysFlag()).equals("1")){ %>
							<input type="radio"  name="data.sysFlag" id="sysFlag" style="width: 30px;"
				          		onkeypress="eventOnKeyPress('redirectUrl')"
				             	 value="0"  />系统外
				              <input type="radio"  name="data.sysFlag" id="sysFlag" style="width: 30px;"
				         		 onkeypress="eventOnKeyPress('redirectUrl')" checked="checked"
				              		value="1" />系统内
						<%}else{ %>
						<%} %>
					</td>
				</tr>
				<tr>

					<td class="tblLable">
						<bean:message
							key="security.jsp.securityconfigpublicclass.common.redirectUrl"
							bundle="conf.security.security" />
						：
					</td>
					<td>
						<input type="text"  name="data.redirectUrl" 
							id="redirectUrl" size="30" maxlength="100"
							onkeypress="eventOnKeyPress('appSysFlag')"
							max="100" dataType="LimitB" msg="转向地址输入过长"
							value="${dataForm.data.redirectUrl}" />
					</td>

					<td  class="tblLable">
						<span>*</span>
						所属系统分类：
					</td>
					<td>
						<%if(dataForm.getData().getAppSysFlag()!= null && String.valueOf(dataForm.getData().getAppSysFlag()).equals("0")){ %>
							<input type="radio"  name="data.appSysFlag" id="appSysFlag"  style="width:30px;"
             					value="0" checked="checked" />支撑系统
             					<input type="radio"   name="data.appSysFlag" id="appSysFlag" style="width:30px;"
         						onkeypress="eventOnKeyPress('supportSystem')"
             					value="1" />应用系统
						<%}else if(dataForm.getData().getAppSysFlag()!= null && String.valueOf(dataForm.getData().getAppSysFlag()).equals("1")){ %>
							<input type="radio"  name="data.appSysFlag" id="appSysFlag"  style="width:30px;"
             					value="0"/>支撑系统
             					<input type="radio"   name="data.appSysFlag" id="appSysFlag" style="width:30px;"
         						onkeypress="eventOnKeyPress('supportSystem')"  checked="checked" 
             					value="1" />应用系统
						<%}else{ %>
						<%} %>
						
					</td>
				</tr>
			</table>
			<p style="color: red;text-align: center">*注：一级模块类别的下一级可以是二级模块类别，也可以是模块，但两者不能共同存在。当一级模块类别下已添加模块时，不能再添加二级模块类别！</p>	
			<!-- Sheet operation button area -->
			 <div class="btnSave">
		  	   			<input type="button" name="btnSaveForm" value="<bean:message key="security.jsp.commom.button1" bundle="security"/>"
							onClick="saveForm()" />
						<input type="button" name="btnBack" value='<bean:message key="security.jsp.commom.button2" bundle="security"/>'
							onClick="history.go(-1);" /> 
		 	 </div>
		</form>
	</body>
</html>
