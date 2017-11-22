<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="/WEB-INF/struts-html.tld" prefix="html"%>
<%@ taglib prefix="bean" uri="/WEB-INF/struts-bean.tld"%>
<%@ taglib uri="/WEB-INF/struts-logic.tld" prefix="logic"%>
<%@ taglib uri="/WEB-INF/tj-html.tld" prefix="tj"%>

<input type="hidden" name="ctrlOp" />
<table class="PageCtrlTable" align="center">		
	<tr>
		<td><bean:message key="include.jsp.msg" bundle="conf.include.include"/><tj:write property="ctrl.allRecord"/><bean:message key="include.jsp.msg1" bundle="conf.include.include"/><html:text property="ctrl.nowPage"/><bean:message key="include.jsp.msg2" bundle="conf.include.include"/><tj:write property="ctrl.allPage"/><bean:message key="include.jsp.msg3" bundle="conf.include.include"/> <html:text property="ctrl.pageSize"/><bean:message key="include.jsp.msg4" bundle="conf.include.include"/>		
			<logic:equal name="org.apache.struts.taglib.html.BEAN" property="ctrl.nowPage" value="1">
				<img src="include/images/shouye_s.gif" border="0" />
				<img src="include/images/shang_s.gif" border="0" />
			</logic:equal>
			<logic:notEqual name="org.apache.struts.taglib.html.BEAN" property="ctrl.nowPage" value="1">
				<img src="include/images/shouye.gif" border="0" onclick="op.value='list';ctrlOp.value='first';submit();"/> 
				<img src="include/images/shang.gif" border="0" onclick="op.value='list';ctrlOp.value='back';submit();"/>
			</logic:notEqual>
			<logic:equal name="org.apache.struts.taglib.html.BEAN" property="ctrl.nowPage" value='${requestScope["org.apache.struts.taglib.html.BEAN"].ctrl.allPage}'>
				<img src="include/images/xia_x.gif" border="0" />		 
				<img src="include/images/mo_m.gif" border="0" />
			</logic:equal>
			<logic:notEqual name="org.apache.struts.taglib.html.BEAN" property="ctrl.nowPage" value='${requestScope["org.apache.struts.taglib.html.BEAN"].ctrl.allPage}'>		 
				<img src="include/images/xia.gif" border="0" onclick="op.value='list';ctrlOp.value='next';submit();" />
				<img src="include/images/mo.gif" border="0" onclick="op.value='list';ctrlOp.value='last';submit();" />
			</logic:notEqual>
		</td>
	</tr>
</table>
