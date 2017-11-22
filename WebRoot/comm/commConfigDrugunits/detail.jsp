<%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib uri="/WEB-INF/struts-bean.tld" prefix="bean" %>
<jsp:useBean id="data" scope="request" class="com.tianjian.comm.struts.form.CommConfigDrugunitsForm" />
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<%if(request.getServerPort() == 80) {%>
		<base href="http://<%=request.getServerName()%><%=request.getContextPath()%>/"/>
		<%} else {%>
		<base href="http://<%=request.getServerName()%>:<%=request.getServerPort()%><%=request.getContextPath()%>/"/>
		<%}%>
		<title><bean:message key="comm.jsp.different.text15" bundle="conf.comm.comm"/>DETAIL.JSP</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
		<meta http-equiv="pragma" content="no-cache"/>
		<meta http-equiv="cache-control" content="no-cache"/>
		<meta http-equiv="expires" content="0"/>
		<script language="javascript">
			function goBack(){
				document.form.verbId.value="query";
				document.form.submit();
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
//修改
function cmdEdit(id) {
    document.form.itemCodeHidden.value = id;  
    document.form.verbId.value = "updateInit";    
    document.form.submit();     
}    
		</script>
		<link type="text/css" rev="stylesheet" rel="stylesheet" href="include/css/form.css" />
	</head>

	<body>
		<form name="form" method="post">
			<input type="hidden" name="verbId" value="detail" />
			<input type="hidden" name="itemCodeHidden" value="<%=data.getItemCodeHidden()%>"/>
			<table  width="100%" border="0" cellpadding="0" cellspacing="0" class="tblView" align="center">
				<tr>
					<td class="tblTitle" colspan="4">
						药品类别字典详细信息
				<div id="editDiv"><img src="include/images/114.gif" width="14" height="14" /><a href="javascript:cmdEdit('<%=data.getItemCode() %>')" > 修改</a><img src="include/images/printer.png" width="14" height="14" /><a href="javascript:qprint();"> 打印</a></div>
					</td>
				</tr>
				<tr>
					<td class="tblLable">
						<bean:message key="comm.jsp.common.text10" bundle="conf.comm.comm"/>：
					</td>
					<td class="hou">
						<%=data.getItemCode() %>
					<td class="tblLable" nowrap>
						<bean:message key="comm.jsp.common.text29" bundle="conf.comm.comm"/>：
					</td>
					<td class="hou">
						<%=data.getItemName() %>
					</td>
				</tr>
				<tr>
					<td class="tblLable">
						<bean:message key="comm.jsp.common.text48" bundle="conf.comm.comm"/>：
					</td>
					<td class="hou">
						<%=data.getSeqNo() %>

					</td>
					<td class="tblLable">
						<bean:message key="comm.jsp.common.text38" bundle="conf.comm.comm"/>：
					</td>
					<td class="hou">
						<%=data.getInputCode() %>
					</td>
				</tr>
				<tr>
					<td class="tblLable">
						<bean:message key="comm.jsp.common.text4" bundle="conf.comm.comm"/>：
					</td>
					<td class="hou" colspan="3">
						<%=data.getComments() %>
					</td>
				</tr>
			</table>

			<!-- Sheet operation button area -->
			<div class="btnSave" id="buttonTable">
				<input type="button" name="btnBack" value="<bean:message key="comm.jsp.common.text19" bundle="conf.comm.comm"/>" onClick="goBack();" />
			</div>
		</form>
	</body>
</html>