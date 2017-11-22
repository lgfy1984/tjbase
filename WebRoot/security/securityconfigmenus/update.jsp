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
		<title>
			<bean:message key="security.jsp.securityconfigmenus.update.title" bundle="conf.security.security" />
		</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<script language="javascript" src="<bean:message key="includes.js.validator.path" bundle="security" />" defer="defer"></script>
		<script language="javascript" src="<bean:message key="include.js.TJMessage.path" bundle="security" />"></script>
		<script language="javascript" src="include/javascript/eventOnKeyPress.js"></script>
		<script language="javascript" src="<bean:message key="security.js.gettext.path" bundle="security" />"></script>
		<script type="text/javascript" src="security/include/javascript/jianbian.js"></script>
		<link rel="stylesheet" href="include/css/open.css" />
		<link type="text/css" rev="stylesheet" rel="stylesheet" href="include/css/form.css" />
		<script language="javascript" src="include/javascript/jquery-1.4.4.min.js"></script>
		<script language="javascript">
		function updatePara(){
				if(!Validator.Validate(document.forms.form,3)){
     			 return ;
   				}
				if (confirmMessage("<bean:message key='security.jsp.commom.save' bundle='security'/>")){
				    document.form.verbId.value = "update";
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
				    				var oldSecurityConfigPublicId = document.getElementById("oldSecurityConfigPublicId").value;
				      				var oldParentId = document.getElementById("oldParentId").value;
				    				var securityConfigPublicId = document.getElementById("securityConfigPublicId").value;
				      				var parentId = document.getElementById("parentId").value;
				      				if(parentId != null && parentId.length > 0){
				      					parent.frames["treeFrame"].refreshByIdAndType(parentId, "4");
				      				}else if(securityConfigPublicId != null && securityConfigPublicId.length > 0){
				      					parent.frames["treeFrame"].refreshByIdAndType(securityConfigPublicId, "3");
			      					}
				      				if(oldParentId != null && oldParentId.length > 0 && oldParentId != parentId){
				      					parent.frames["treeFrame"].refreshByIdAndType(oldParentId, "4");
				      				}else if(oldSecurityConfigPublicId != null && oldSecurityConfigPublicId.length > 0 && oldSecurityConfigPublicId != securityConfigPublicId){
				      					parent.frames["treeFrame"].refreshByIdAndType(oldSecurityConfigPublicId, "3");
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
		
	</head>
	<body>
		<form name="form" id="form" method="post" action="security/securityConfigMenus.do">
			<input type="hidden" name="verbId" value="update" />
			<input type="hidden" name="idHidden" value="${dataForm.idHidden}" />
			<input type="hidden" name="menuCodeHidden" value="${dataForm.data.menuCode}" />
			<input type="hidden" id="oldParentId" value="${dataForm.data.securityConfigMenusObject.id}" />
			<input type="hidden" id="oldSecurityConfigPublicId" value="${dataForm.data.securityConfigPublicId}" />
			<table border="0" cellpadding="0" cellspacing="0" class="tblFill" align="center">
				<tr>
					<td class="tblTitle" colspan="4"><span>※</span>
						<!--修改菜单信息  -->
						<bean:message key="security.jsp.securityconfigmenus.update.title"
							bundle="conf.security.security" />
						<span>※</span></td>
				</tr>
				<tr>
					<td class="tblLable">
						<!--模块代码  -->
						<span>*</span>模块:
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
					<td class="tblLable">
						<span>*</span> ID:
					</td>
					<td>
						<input type="text"  name="data.id" id="id" size="20"
							maxlength="32" require="true" dataType="Integer" msg="ID应为1-32位数字"
							onkeypress="eventOnKeyPress('menuDetail')"
							value="${dataForm.data.id}" readonly />
					</td>
					<td class="tblLable">
						<!--名称  -->
						<span>*</span>
						<bean:message key="jsp.name" bundle="conf.Init" />:
					</td>
					<td>
						<input type="text"  name="data.menuDetail"
							id="menuDetail" size="50" maxlength="100"
							max="100" require="true" dataType="LimitB" msg="名称的长度应在1-100字节范围"
							onkeypress="eventOnKeyPress('serialNo')"
							value="${dataForm.data.menuDetail}" />
					</td>
				</tr>
				<tr>
					<td class="tblLable">
						<!--序号  -->
						<bean:message key="jsp.serialNo" bundle="conf.Init" />:
					</td>
					<td>
						<input type="text"  name="data.serialNo"
							id="serialNo" size="30" maxlength="11"
							onkeypress="eventOnKeyPress('menuNotice')"
							value="${dataForm.data.serialNo}" readonly />
					</td>
					<td class="tblLable">
						<!--菜单说明  -->
						<bean:message
							key="security.jsp.securityconfigmenus.common.menuNotice"
							bundle="conf.security.security" />:
					</td>
					<td>
						<input type="text"  name="data.menuNotice"
							id="menuNotice" size="30" maxlength="100"
							max="100" dataType="LimitB" msg="菜单描述输入过长"
							onkeypress="eventOnKeyPress('menuUrl')"
							value="${dataForm.data.menuNotice}" />
					</td>
				</tr>
				<tr>
					<td class="tblLable">
						<!--链接地址  -->
						<bean:message
							key="security.jsp.securityconfigmenus.common.menuUrl"
							bundle="conf.security.security" />:
					</td>
					<td colspan="3">
						<input type="text"  name="data.menuUrl" id="menuUrl"
							size="30" maxlength="200" max="200" dataType="LimitB" msg="菜单URL输入过长"
							onkeypress="eventOnKeyPress('endLevelFlag')"
							value="${dataForm.data.menuUrl}" />
					</td>
				</tr>
				<tr>
					<td class="tblLable">
						<!--末节点标志  -->
						<bean:message
							key="security.jsp.securityconfigmenus.common.endLevelFlag"
							bundle="conf.security.security" />:
					</td>
					<td>
						<input type="text"  name="data.endLevelFlag"
							id="endLevelFlag" size="30" maxlength="20"
							onkeypress="eventOnKeyPress('menuLevel')"
							value="${dataForm.data.endLevelFlag}" />
					</td>
					<td class="tblLable">
						<!--菜单级别  -->
						<bean:message
							key="security.jsp.securityconfigmenus.common.menuLevel"
							bundle="conf.security.security" />:
					</td>
					<td>
						<input type="text"  name="data.menuLevel"
							id="menuLevel" size="30" maxlength="20"
							onkeypress="eventOnKeyPress('menuSeq')"
							value="${dataForm.data.menuLevel}" />
					</td>
				</tr>
				<tr>
					<td class="tblLable">
						<!--菜单序号  -->
						<bean:message
							key="security.jsp.securityconfigmenus.common.menuSeq"
							bundle="conf.security.security" />:
					</td>
					<td>
						<input type="text"  name="data.menuSeq" id="menuSeq"
							size="30" maxlength="20" onkeypress="eventOnKeyPress('menuIcon')"
							value="${dataForm.data.menuSeq}" />
					</td>
					<td class="tblLable"p>
						<!--菜单数据  -->
						<bean:message
							key="security.jsp.securityconfigmenus.common.menuData"
							bundle="conf.security.security" />:
					</td>
					<td>
						<input type="text" name="data.menuData"
							id="menuData" size="30" maxlength="75"
							max="150" dataType="LimitB" msg="菜单数据输入过长"
							onkeypress="eventOnKeyPress('menuTarget')"
							value="${dataForm.data.menuData}" />
					</td>
				</tr>
				<tr>
					<td class="tblLable">
						<!--菜单方法  -->
						<bean:message
							key="security.jsp.securityconfigmenus.common.menuMethod"
							bundle="conf.security.security" />:
					</td>
					<td>
						<input type="text" name="data.menuMethod" id="menuMethod" size="30" maxlength="50"
							max="50" dataType="LimitB" msg="菜单方法输入过长"
							onkeypress="eventOnKeyPress('parentIdname')"
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
						<!--图标  -->
						<bean:message
							key="security.jsp.securityconfigmenus.common.menuIcon"
							bundle="conf.security.security" />:
					</td>
					<td colspan="3">
						<input type="text"  name="data.menuIcon"
							id="menuIcon" size="30" maxlength="50"
							max="50" dataType="LimitB" msg="菜单图标输入过长"
							onkeypress="eventOnKeyPress('menuData')"
							value="${dataForm.data.menuIcon}" />
					</td>
				</tr>
				<tr>
					<td class="tblLable">
						<!--菜单目标  -->
						<bean:message
							key="security.jsp.securityconfigmenus.common.menuTarget"
							bundle="conf.security.security" />:
					</td>
					<td colspan="3">
						<input type="text" name="data.menuTarget"
							id="menuTarget" size="30" maxlength="200"
							max="200" dataType="LimitB" msg="菜单目标输入过长"
							onkeypress="eventOnKeyPress('menuMethod')"
							value="${dataForm.data.menuTarget}" />
					</td>
				</tr>
				<%-------------------------------------------原选择方法备份--------开始----------- 
					<td class="qian" nowrap>
						父类代码：
					</td>
						<td class="hou" nowrap>
						<select id="parentId" class="select" name="data.parentId" onkeypress="eventOnKeyPress('picPath')">
							<option value=""></option>
							<logic:notEmpty name="dataForm" property="securityConfigMenusList">
								<logic:iterate id="commmBean" name="dataForm" property="securityConfigMenusList" indexId="ind">
									<logic:equal name="commmBean" value="${dataForm.data.parentId}" property="id">
										<option value="${commmBean.id}" selected="selected">
											${commmBean.menuDetail}
										</option>
									</logic:equal>
									<logic:notEqual name="commmBean" value="${dataForm.data.parentId}" property="id">
										<option value="${commmBean.id}">
											${commmBean.menuDetail}
										</option>
									</logic:notEqual>
								</logic:iterate>
							</logic:notEmpty>
						</select>
					</td>
					-----------------------------------------原选择方法备份--------结束---------onfocus="if(this.value=='') add('security/securityConfigMenus.do?verbId=getSecurityConfigMenus','parentIdname','parentId')"----------%>
				<tr>
					<td class="tblLable">
						<!--父类菜单  -->
						<bean:message
							key="security.jsp.securityconfigmenus.common.parentId"
							bundle="conf.security.security" />:
					</td>
					<td  colspan="3" style="text-align: left">
						<input type="text" style="width:94%"
							id="parentIdname" name="parentIdname"
							onkeypress="eventOnKeyPress('displayType')"
							value="${dataForm.data.securityConfigMenusObject.menuDetail}"
							readonly/>
						<input type="hidden" id="parentId" name="data.parentId"
							value="${dataForm.data.securityConfigMenusObject.id}" />
						<img src="security/include/images/select.gif" onkeydown="huiche()"
							style="cursor: pointer; "
							onclick="add('security/securityConfigMenus.do?verbId=getSecurityConfigMenus','parentIdname','parentId')" />
					</td>
				</tr>
				<tr>
					<td class="tblLable">
						<bean:message
							key="security.jsp.securityconfigmenus.common.displayType"
							bundle="conf.security.security" />:
					</td>
					<td colspan="3">
						<select  id="displayType" name="data.displayType"
							onkeypress="eventOnKeyPress('comments')">
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
						<bean:message key="jsp.comments" bundle="conf.Init" />:
					</td>
					<td colspan="3">
						<input type="text"  name="data.comments"
							id="comments" size="30" maxlength="40"
							max="40" dataType="LimitB" msg="备注输入过长"
							onkeypress="eventOnKeyPress('btnSaveForm')"
							value="${dataForm.data.comments}" />
					</td>
				</tr>
			</table>
			<!-- Sheet operation button area -->
			<div class="btnSave">
				<input type="button" name="btnSaveForm" value="<bean:message key="security.jsp.commom.button1" bundle="security"/>" onClick="updatePara();" />
				<input type="button" style="font-size:12px;font-family:Arial;" name="btnBack" value='关闭' onClick="closeIframe();" />
			</div>
		</form>
	</body>
</html>
