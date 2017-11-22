<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix="html" uri="/WEB-INF/struts-html.tld"%>
<%@ taglib prefix="bean" uri="/WEB-INF/struts-bean.tld"%>
<%@ taglib prefix="logic" uri="/WEB-INF/struts-logic.tld"%>
<%@ page import="com.tianjian.security.bean.SecurityConfigPublicClass"%>
<jsp:useBean id="dataForm" scope="request" class="com.tianjian.security.struts.form.SecurityConfigPublicForm" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<head>
<%if(request.getServerPort() == 80) {%>
  <base href="http://<%=request.getServerName()%><%=request.getContextPath()%>/">
  <%} else {%>
  <base href="http://<%=request.getServerName()%>:<%=request.getServerPort()%><%=request.getContextPath()%>/">
<%}%>
<title><bean:message key="security.jsp.securityConfigpublicadd.title" bundle="security"/></title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<script language="javascript" src="<bean:message key="include.js.TJMessage.path" bundle="security" />"></script>
<script language="javascript" src="<bean:message key="includes.js.validator.path" bundle="security" />" defer="defer"></script>
<script language="javascript" src="include/javascript/eventOnKeyPress.js"></script>
<script language="javascript" src="include/javascript/jquery-1.4.4.min.js"></script>
		<script language="javascript">
		function savePara(){
			if(!Validator.Validate(document.forms.form,3)){
    			 return ;
  				}
			var modCode = $.trim($("#modeCode").val());
			if(isNaN(modCode)){
				alert('<bean:message key="comm.jsp.commconfigtrue.add.sc" bundle="conf.comm.CommMessageguoh"/>');
				return ; 
			}
			if (confirmMessage("<bean:message key='security.jsp.commom.save' bundle='security'/>")){
			    document.form.verbId.value = "add";
			    var formData = $("#form").serialize();
			    $.ajax({
			    	dataType: "text/html",
			    	type:"POST",
			    	url:"security/securityConfigPublic.do",
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
			    				var scpcId = document.getElementById("scpcId").value;
			      				if(scpcId != null && scpcId.length > 0){
			      					parent.frames["treeFrame"].refreshByIdAndType(scpcId, "1");
			      					parent.frames["treeFrame"].refreshByIdAndType(scpcId, "2");
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
<form name="form" id="form" method="post" action= "security/securityConfigPublic.do" >
<input type="hidden" name="verbId" value="add" />
 <table border="0" cellpadding="0" cellspacing="0" class="tblFill" align="center">
		<tr>
			<td class="tblTitle" colspan="4"><span>※</span><bean:message key="security.jsp.securityConfigpublicadd.title" bundle="security"/><span>※</span></td>
		</tr>
    <tr>
        <td class="tblLable">
            <span>*</span> <bean:message key="security.jsp.commmom.classCode" bundle="security"/>：
        </td>
        <td>
            <input type="text" class="kuandu" name="data.reason" id="reason" size="50" maxlength="50"
              onkeypress="eventOnKeyPress('serialNo')" min="1" max="50" dataType="LimitB" msg="名称的长度应在1-50个字节范围内"
              value="${dataForm.data.reason}" />
        </td>
        <td class="tblLable">
         	<span>*</span> <bean:message key="security.jsp.commom.menuCode" bundle="security"/>：
        </td>
        <td>
            <input type="text" class="kuandu" name="data.modCode" id="modCode" size="20" maxlength="20"
            	onkeypress="eventOnKeyPress('reason')" require="true" dataType="Integer" msg="代码应为1-20位数字"
              	value="${dataForm.data.modCode}" />
        </td>  
    </tr>
    <tr>
        <td class="tblLable">
           <bean:message key="security.jsp.commom.serialNo" bundle="security"/>：
        </td>
        <td>
            <input type="text" class="kuandu" name="data.serialNo" id="serialNo" size="30" maxlength="11" dataType="Integer"
               onkeypress="eventOnKeyPress('comments')" 
              value="${dataForm.data.serialNo}" readonly/>
        </td>
        <td class="tblLable">
            <bean:message key="security.jsp.commmom.comments" bundle="security"/>：
        </td>
        <td>
            <input type="text" class="kuandu" name="data.comments" id="comments" size="30" maxlength="40"
         	 onkeypress="eventOnKeyPress('reasonValue')" max="40" dataType="LimitB" msg="备注输入过长"
              value="${dataForm.data.comments}" />
        </td>
    </tr>
    <tr>
        <td class="tblLable">
           <bean:message key="security.jsp.commom.SecurityConfigPublic" bundle="security"/>：
        </td>
        <td>
            <input type="text" class="kuandu" name="data.reasonValue" id="reasonValue" size="30" maxlength="100"
               onkeypress="eventOnKeyPress('scpcId')" max="100" dataType="LimitB" msg="模块描述输入过长"
               value="${dataForm.data.reasonValue}" />
        </td>
        <td class="tblLable" >
            <span>*</span><bean:message key="security.jsp.commom.scpcId" bundle="security"/>：
        </td>
        <td>
           <!--  <select id="scpcId" name="data.scpcId" onkeypress="eventOnKeyPress('picPath')" min="1" dataType="LimitB" msg="模块类别不能为空">
				<option value=""></option>
					<logic:iterate id="commBean" name="dataForm" property="securityConfigPublicClassList" indexId="ind">
						<option value="${commBean.id}">
							${commBean.className}
						</option>
					</logic:iterate>
			</select>-->
			<select name="data.scpcId" id="scpcId" onkeypress="eventOnKeyPress('publicUrl')" min="1" dataType="LimitB" msg="模块类别不能为空" readonly="readonly">
							<%
								if (dataForm.getSecurityConfigPublicClassList() != null&& dataForm.getSecurityConfigPublicClassList().size() > 0) {
									for (int i = 0; i < dataForm.getSecurityConfigPublicClassList().size(); i++) {
										SecurityConfigPublicClass data = (SecurityConfigPublicClass)dataForm.getSecurityConfigPublicClassList().get(i);
										String tempId = data.getId();
										String tempName = data.getClassName();
							%>
									<option value="<%=tempId%>"
										<%=tempId.equals(dataForm.getSecurityPublicClassId()) ? "selected": ""%>>
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
        <td class="tblLable" >
           	模块链接：
        </td>
        <td colspan="3">
            <input type="text" class="kuandu" name="data.publicUrl" id="publicUrl" size="30" maxlength="200"
          		onkeypress="eventOnKeyPress('publicTarget')"  max="200" dataType="LimitB" msg="模块地址输入过长"
             	value="${dataForm.data.publicUrl}" />
        </td>
    </tr>
    <tr>
    	<td class="tblLable" >
           	模块目标：
        </td>
        <td>
            <input type="text" class="kuandu" name="data.publicTarget" id="publicTarget" size="30" maxlength="200"
          		onkeypress="eventOnKeyPress('picPath')"  max="200" dataType="LimitB" msg="模块目标输入过长"
             	value="${dataForm.data.publicTarget}" />
        </td>
        <td class="tblLable" >
           <bean:message key="security.jsp.commom.picPath" bundle="security"/>：
        </td>
        <td>
            <input type="text" class="kuandu" name="data.picPath" id="picPath" size="30" maxlength="40"
          		onkeypress="eventOnKeyPress('btnSaveForm')"  max="40" dataType="LimitB" msg="模块描述输入过长"
             	value="${dataForm.data.picPath}" />
        </td>
    </tr>
  </table>
  <p style="color: red;text-align: center">*注：一级模块类别的下一级可以是二级模块类别，也可以是模块，但两者不能共同存在。当一级模块类别下已添加二级模块类别时，不能再添加模块！</p>
  <!-- Sheet operation button area -->
  <div class="btnSave">
		<input type="button" name="btnSaveForm" value="<bean:message key="security.jsp.commom.button1" bundle="security"/>" onClick="savePara();" />
  </div>
</form>

</body>
</html>
