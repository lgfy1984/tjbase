<%@ page contentType="text/html; charset=UTF-8"%>
<%@page import="com.tianjian.hsp.struts.form.HealthRegisterTreeForm"%>
<%@page import="com.tianjian.util.Converter"%>
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
		    function printTure() 
			{
				var budivList = document.getElementsByName("budiv");
				for(var i=0; i<budivList.length; i++){
					budivList[i].style.display="none";
				}
				window.print();
				for(var i=0; i<budivList.length; i++){
					budivList[i].style.display="";
				}
			}
		</script>		
	</head>
	<%
		HealthRegisterTreeForm.DeptBean deptBean = data.getDeptBean();
	%>
	<body>
		<form name="form" method="post" action="security/securityConfigMenus.do">
			<input type="hidden" name="verbId" value="modifyDept" />
			<input type="hidden" name="hspConfigBaseinfoId" value="${dataForm.hspConfigBaseinfoId}" />		
			<input type="hidden" name="hspConfigBaseinfoName" value="${dataForm.hspConfigBaseinfoName}" />			
			<table border="0" cellpadding="0" cellspacing="0" class="tblFill" align="center">
				<tr>
					<td class="tblTitle" colspan="4">
					    <span>※</span>科室信息查看<span>※</span>
					</td>
				</tr>
				<tr>
				    <td class="tblLable"><span>*</span>机构名称:</td>
				    <td colspan="3">
				       <%=Converter.toBlank(deptBean.getHspConfigBaseinfoName()) %>
				    </td>
				</tr>	
				<tr>
				    <td class="tblLable"><span>*</span>序号:</td>
				    <td>
				       <%=deptBean.getSeqNo() %>
				    </td>
				    <td class="tblLable"><span>*</span>科室临床属性:</td>
				    <td>
				        <%=deptBean.getDeptAttrName() %>
				    </td>
				</tr>	
				<tr>
				    <td class="tblLable">卫生局代码:</td>
				    <td>
				        <%=deptBean.getHealthBureauCode() %>
				    </td>
				    <td class="tblLable">社保局代码:</td>
				    <td>
				        <%=deptBean.getSocialSecurityBureauCode() %>
				    </td>
				</tr>
				<tr>
				    <td class="tblLable"><span>*</span>科室代码:</td>
				    <td>
				       <%=deptBean.getDeptCode() %>
				    </td>
				    <td class="tblLable"><span>*</span>科室名称:</td>
				    <td>
				        <%=deptBean.getDeptName() %>
				    </td>
				</tr>	
				<tr>
				    <td class="tblLable">备注:</td>
				    <td colspan="3">
				        <%=deptBean.getComments() %>
				    </td>
				</tr>		
			</table>
			<!-- Sheet operation button area -->
			<div class="btnSave">
		        <input type="button"  value="<bean:message  bundle="hsp.hspLocale" key="hsp.jsp.hspconfigbaseinfodetail.print"/>" onclick="printTure();" />
			</div>
		</form>
	</body>
</html>
