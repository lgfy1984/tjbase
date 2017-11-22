<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="html" uri="/WEB-INF/struts-html.tld"%>
<%@ taglib prefix="bean" uri="/WEB-INF/struts-bean.tld"%>
<%@ taglib prefix="logic" uri="/WEB-INF/struts-logic.tld"%>
<jsp:useBean id="dataForm" scope="request" class="com.tianjian.security.struts.form.SecurityConfigMenusForm" />
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
		<title><bean:message key="security.jsp.securityconfigmenus.add.title" bundle="conf.security.security" />
		</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<script language="javascript" src="<bean:message key="includes.js.validator.path" bundle="security" />" defer="defer"></script>
		<script language="javascript" src='<bean:message key="Comm.js.TJMessagepath" bundle="conf.comm.CommMessage"/>'></script>
		<script language="javascript" src="include/javascript/eventOnKeyPress.js"></script>
		<script type="text/javascript" src="security/include/javascript/jianbian.js"></script>
		<script language="javascript" src="<bean:message key="security.js.gettext.path" bundle="security" />"></script>
		<link rel="stylesheet" href="include/css/open.css" />
		<script language="javascript" src="include/javascript/jquery-1.4.4.min.js"></script>
		<script language="javascript">
		function savePara(){
			var id = document.getElementById("id").value;
			
				if(!Validator.Validate(document.forms.form,3)){
     			 return ;
   				}
			
				if (confirmMessage("<bean:message key='security.jsp.commom.save' bundle='security'/>")){
				    document.form.verbId.value = "add";
				    var formData = $("#form").serialize();
				    $.ajax({
				    	dataType: "text/html",
				    	type:"POST",
				    	url:"security/securityConfigMenus.do",
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
				    				var securityConfigPublicId = document.getElementById("securityConfigPublicId").value;
				      				var parentId = document.getElementById("parentId").value;
				      				if(parentId != null && parentId.length > 0){
				      					parent.frames["treeFrame"].refreshByIdAndType(parentId, "4");
				      				}else if(securityConfigPublicId != null && securityConfigPublicId.length > 0){
				      					parent.frames["treeFrame"].refreshByIdAndType(securityConfigPublicId, "3");
			      					}
				      				alert(json[0].msg);
				    				location.reload(false);	
				    			}else{
				    				alert(json[0].msg);
				    			}
				    		}
				    	}
				    });
		    	} 
		}
</script>
		<link type="text/css" rev="stylesheet" rel="stylesheet" href="include/css/form.css" />
	</head>
	<body>
		<form name="form" id="form" method="post" action="security/securityConfigMenus.do">
			<input type="hidden" name="verbId" value="add" />
			<table border="0" cellpadding="0" cellspacing="0" class="tblFill" align="center">
				<tr>
					<td class="tblTitle" colspan="4"><span>※</span>
						<bean:message key="security.jsp.securityconfigmenus.add.topic"
							bundle="conf.security.security" />
						<span>※</span></td>
				</tr>

				<tr>
					<td class="tblLable">
						<span>*</span>模块：
					</td>
					<td colspan="3">
						<select name="data.securityConfigPublicId" id="securityConfigPublicId" onkeypress="eventOnKeyPress('menuCode')" onkeydown="if(event.keyCode==13){event.keyCode=9}" require="true" msg="模块不能为空">
							<%
								if (dataForm.getSecurityConfigPublicIds() != null&& dataForm.getSecurityConfigPublicIds().length > 0) {
									for (int i = 0; i < dataForm.getSecurityConfigPublicIds().length; i++) {
										String tempId = dataForm.getSecurityConfigPublicIds()[i];
										String tempName = dataForm.getSecurityConfigPublicNames()[i];
							%>
									<option value="<%=tempId%>"
										<%=tempId.equals(dataForm.getData().getSecurityConfigPublicId()) ? "selected": ""%>>
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
					<!--<td class="qian" nowrap>
						<font color="red">*</font> <bean:message key="jsp.code" bundle="conf.Init"/>:
					</td>
					<td class="hou" nowrap>
						<input type="text"  onkeypress="eventOnKeyPress('menuDetail')" name="data.menuCode" id="menuCode" size="20" maxlength="20" onkeydown="if(event.keyCode==13){event.keyCode=9}" value="${dataForm.data.menuCode}" />
					</td>  -->
					<td class="tblLable">
						<span>*</span> ID:
					</td>
					<td>
						<input type="text"  name="data.id" id="id" onkeypress="eventOnKeyPress('menuDetail')" size="20" maxlength="32" onkeydown="if(event.keyCode==13){event.keyCode=9}" require="true" dataType="Integer" msg="ID应为1-32位数字" value="${dataForm.data.id}" />
					</td>
					<td class="tblLable">
						<span>*</span>
						<bean:message key="jsp.name" bundle="conf.Init" />:
					</td>
					<td>
						<input type="text"  name="data.menuDetail" onkeypress="eventOnKeyPress('serialNo')" id="menuDetail" size="50" maxlength="100" max="100" require="true" dataType="LimitB" msg="名称的长度应在1-100字节范围" onkeydown="if(event.keyCode==13){event.keyCode=9}" value="" />
					</td>
				</tr>
				<tr>
					<td class="tblLable">
						<bean:message key="jsp.serialNo" bundle="conf.Init" />:
					</td>
					<td>
						<input type="text"  name="data.serialNo" onkeypress="eventOnKeyPress('menuNotice')" id="serialNo" size="30" maxlength="11" onkeydown=" if(event.keyCode==13){event.keyCode=9}" value="${dataForm.data.serialNo}" readonly />
					</td>
					<td class="tblLable">
						<bean:message
							key="security.jsp.securityconfigmenus.common.menuNotice"
							bundle="conf.security.security" />
						:
					</td>
					<td>
						<input type="text"  name="data.menuNotice"
							onkeypress="eventOnKeyPress('menuUrl')" id="menuNotice" size="30"
							maxlength="20" onkeydown="if(event.keyCode==13){event.keyCode=9}"
							max="100" dataType="LimitB" msg="菜单描述输入过长"
							value="${dataForm.data.menuNotice}" />
					</td>
				</tr>
				<tr>
					<td class="tblLable">
						<!--菜单URL  -->
						<bean:message
							key="security.jsp.securityconfigmenus.common.menuUrl"
							bundle="conf.security.security" />
						:
					</td>
					<td colspan="3">
						<input type="text"  name="data.menuUrl" id="menuUrl"
							onkeypress="eventOnKeyPress('endLevelFlag')" size="30"
							max="200" dataType="LimitB" msg="菜单URL输入过长"
							maxlength="200" value="${dataForm.data.menuUrl}"
							onkeydown="if(event.keyCode==13){event.keyCode=9}" />
					</td>
				</tr>
				<tr>
					<td class="tblLable">
						<!--末节点标志  -->
						<bean:message
							key="security.jsp.securityconfigmenus.common.endLevelFlag"
							bundle="conf.security.security" />
						:
					</td>
					<td>
						<input type="text"  name="data.endLevelFlag"
							onkeypress="eventOnKeyPress('menuLevel')" id="endLevelFlag"
							size="30" maxlength="1"
							onkeydown="if(event.keyCode==13){event.keyCode=9}"
							value="${dataForm.data.endLevelFlag}" />
					</td>
					<td class="tblLable">
						<!--菜单级别  -->
						<bean:message
							key="security.jsp.securityconfigmenus.common.menuLevel"
							bundle="conf.security.security" />
						:
					</td>
					<td>
						<input type="text"  name="data.menuLevel"
							id="menuLevel" onkeypress="eventOnKeyPress('menuSeq')" size="30"
							maxlength="1" value="${dataForm.data.menuLevel}"
							onkeydown="if(event.keyCode==13){event.keyCode=9}" />
					</td>
				</tr>
				<tr>
					<td class="tblLable">
						<!--菜单序号  -->
						<bean:message
							key="security.jsp.securityconfigmenus.common.menuSeq"
							bundle="conf.security.security" />
						:
					</td>
					<td>
						<input type="text"  name="data.menuSeq" id="menuSeq"
							onkeypress="eventOnKeyPress('menuIcon')" size="30" maxlength="3"
							onkeydown="if(event.keyCode==13){event.keyCode=9}"
							value="${dataForm.data.menuSeq}" />
					</td>
					<td class="tblLable">
						<!--菜单数据  -->
						<bean:message
							key="security.jsp.securityconfigmenus.common.menuData"
							bundle="conf.security.security" />
						:
					</td>
					<td>
						<input type="text"  name="data.menuData"
							id="menuData" onkeypress="eventOnKeyPress('menuTarget')"
							size="30" maxlength="150" max="150" dataType="LimitB" msg="菜单数据输入过长"
							onkeydown="if(event.keyCode==13){event.keyCode=9}"
							value="${dataForm.data.menuData}" />
					</td>
				</tr>
				<tr>
					<td class="tblLable">
						<!-- 菜单方法 -->
						<bean:message
							key="security.jsp.securityconfigmenus.common.menuMethod"
							bundle="conf.security.security" />
						:
					</td>
					<td>
						<input type="text" name="data.menuMethod" id="menuMethod"
							onkeypress="eventOnKeyPress('parentIdname')" size="30"
							max="50" dataType="LimitB" msg="菜单方法输入过长"
							maxlength="50" onkeydown="if(event.keyCode==13){event.keyCode=9}"
							value="${dataForm.data.menuMethod}" />
					</td>
					<td class="tblLable">
						终端类型:
					</td>
					<td>
						<%
							String isFlagMenu = dataForm.getData().getIsFlatMenu();
						%>
						<select name="data.isFlagMenu">
							<option>web浏览器</option>
							<option value="1" <%="1".equals(isFlagMenu) ? "selected" : "" %>>平板</option>
						</select>
					</td>
				</tr>
				<tr>
					<td class="tblLable">
						<!--菜单图标  -->
						<bean:message
							key="security.jsp.securityconfigmenus.common.menuIcon"
							bundle="conf.security.security" />
						:
					</td>
					<td colspan="3">
						<input type="text"  name="data.menuIcon"
							id="menuIcon" onkeypress="eventOnKeyPress('menuData')" size="30"
							max="50" dataType="LimitB" msg="菜单图标输入过长"
							maxlength="50" value="${dataForm.data.menuIcon}"
							onkeydown="if(event.keyCode==13){event.keyCode=9}" />
					</td>
				</tr>
				<tr>
					<td class="tblLable">
						<!--菜单目标-->
						<bean:message
							key="security.jsp.securityconfigmenus.common.menuTarget"
							bundle="conf.security.security" />
						:
					</td>
					<td colspan="3">
						<input type="text"  name="data.menuTarget"
							id="menuTarget" onkeypress="eventOnKeyPress('menuMethod')"
							size="30" maxlength="200" value="${dataForm.data.menuTarget}"
							max="200" dataType="LimitB" msg="菜单目标输入过长"
							onkeydown="if(event.keyCode==13){event.keyCode=9}" />
					</td>
				</tr>
				<%-------------------------源代码屏蔽-------开始--------------------------  
		
					
					<td class="qian" nowrap>
						父类代码：
					</td>
					<td class="hou" nowrap>
						 <select class="select" id="parentId" name="data.parentId" onkeydown="eventOnKeyPress('picPath')">
							<option value=""></option>
							<logic:iterate id="commBean" name="dataForm" property="securityConfigMenusList" indexId="ind">
								<option value="${commBean.id}">
									${commBean.menuDetail}
								</option>
							</logic:iterate>
						</select>
					</td>
				
				------------------------源代码屏蔽-------结束-----------------onfocus="if(this.value=='')add('security/securityConfigMenus.do?verbId=getSecurityConfigMenus','parentIdname','parentId');"-----%>
				<tr>
					<td class="tblLable">
						<bean:message
							key="security.jsp.securityconfigmenus.common.parentId"
							bundle="conf.security.security" />
						:
					</td>
					<td colspan="3" style="text-align: left">
						<input type="text" style="width:94%"
							id="parentIdname" onkeypress="eventOnKeyPress('displayType')"
							name="parentIdname" value="<%=dataForm.getData().getMenuDetail() %>"
							onkeydown="if(event.keyCode==13){event.keyCode=9}" />
						<input type="hidden" id="parentId" value="${dataForm.idHidden}" name="data.parentId" />
						<img src="security/include/images/select.gif" onkeydown="huiche()"
							style="cursor: pointer;"
							onclick="add('security/securityConfigMenus.do?verbId=getSecurityConfigMenus','parentIdname','parentId');" />
					</td>
				</tr>

				<tr>
					<td class="tblLable">
						<bean:message
							key="security.jsp.securityconfigmenus.common.displayType"
							bundle="conf.security.security" />
						:
					</td>
					<td colspan="3">
						<select class="select" onkeypress="eventOnKeyPress('comments')" id="displayType"
							name="data.displayType"
							onkeydown="if(event.keyCode==13){event.keyCode=9}">
							<option value="0"
								<logic:equal name="dataForm" property="data.displayType" value="0">selected</logic:equal>>
								<bean:message
									key="security.jsp.securityconfigmenus.common.displayTypeOption1"
									bundle="conf.security.security" />
							</option>
							<option value="1"
								<logic:equal  name="dataForm" property="data.displayType" value="1">selected</logic:equal>>
								<bean:message
									key="security.jsp.securityconfigmenus.common.displayTypeOption2"
									bundle="conf.security.security" />
							</option>
						</select>
					</td>
				</tr>
				<tr>
					<td class="tblLable">
						<bean:message key="jsp.comments" bundle="conf.Init" />
						:
					</td>
					<td colspan="3">
						<input type="text"  name="data.comments" onkeypress="eventOnKeyPress('btnSaveForm')" id="comments"
							max="40" dataType="LimitB" msg="备注输入过长"
							size="30" maxlength="40" value="${dataForm.data.comments}"
							onkeydown="if(event.keyCode==13){event.keyCode=9}" />
					</td>
				</tr>
			</table>
			<!-- Sheet operation button area -->
			<div class="btnSave">
				<input type="button" name="btnSaveForm" value="<bean:message key="security.jsp.commom.button1" bundle="security"/>" onClick="savePara();" />
			</div>
		</form>
	</body>
</html>
